package com.ilieskou.crossfitbackend.services;

import com.ilieskou.crossfitbackend.models.Athlete;
import com.ilieskou.crossfitbackend.models.CrossfitClass;
import com.ilieskou.crossfitbackend.models.CrossfitClassInfo;
import com.ilieskou.crossfitbackend.models.dto.CrossfitClassDetailsDto;
import com.ilieskou.crossfitbackend.models.dto.CrossfitClassDto;
import com.ilieskou.crossfitbackend.models.dto.CrossfitClassInfoDto;
import com.ilieskou.crossfitbackend.models.dto.TimePeriodDto;
import com.ilieskou.crossfitbackend.models.projections.IExtraSchedule;
import com.ilieskou.crossfitbackend.models.projections.ISchedule;
import com.ilieskou.crossfitbackend.repositories.AthletesRepository;
import com.ilieskou.crossfitbackend.repositories.CrossfitClassInfoRepository;
import com.ilieskou.crossfitbackend.repositories.CrossfitClassesRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CrossfitClassesService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CrossfitClassesRepository crossfitClassesRepository;

    @Autowired
    private CrossfitClassInfoRepository crossfitClassesInfoRepository;

    @Autowired
    private AthletesRepository athletesRepository;

    public List<CrossfitClassDto> getAllCrossfitClasses() {
        List<CrossfitClass> crossfitClasses = crossfitClassesRepository.findAll();
        return crossfitClasses.stream()
                .map(this::convertToCrossfitClassDto)
                .collect(Collectors.toList());
    }

    public CrossfitClassDetailsDto getCrossfitClass(Long id) {
        CrossfitClass crossfitClass = crossfitClassesRepository.findById(id).get();
        return convertToCrossfitClassDetailsDto(crossfitClass);
    }

    public List<CrossfitClassInfoDto> getCrossfitClassesInfo() {
        List<CrossfitClassInfo> crossfitClassesInfo = crossfitClassesInfoRepository.findAll();
        return crossfitClassesInfo.stream()
                .map(this::convertToCrossfitClassInfoDto)
                .collect(Collectors.toList());
    }


    public List<ISchedule> getSchedule(TimePeriodDto timePeriodDto) {
        return crossfitClassesRepository.getCrossfitClassesForTimePeriod(timePeriodDto.getStart(),
                timePeriodDto.getEnd());
    }

    public List<IExtraSchedule> getScheduleWithAthleteInfo(Long athleteId, TimePeriodDto timePeriodDto) {
        return crossfitClassesRepository.getCrossfitClassesForTimePeriodWithAthletePresence(athleteId,
                timePeriodDto.getStart(),
                timePeriodDto.getEnd());
    }


    public CrossfitClassDetailsDto deleteRegistration(Long athleteId, Long classId) {
        Optional<CrossfitClass> crossfitClass = crossfitClassesRepository.findById(classId);
        Optional<Athlete> athlete = athletesRepository.findById(athleteId);
        throwIfNotPresent(crossfitClass, "Class Not Found");
        throwIfNotPresent(athlete, "Athlete Not Found");

        List<Athlete> registeredAthletes = crossfitClass.get().getAthletes();
        for (Athlete a : registeredAthletes) {
            if (a.getId() == athleteId) {
                registeredAthletes.remove(a);
                crossfitClass.get().setAthletes(registeredAthletes);
                CrossfitClass c = crossfitClassesRepository.saveAndFlush(crossfitClass.get());
                return convertToCrossfitClassDetailsDto(c);
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Athlete was not enrolled to the class");
    }

    public CrossfitClassDetailsDto registerAthleteToClass(Long athleteId, Long classId) {
        Optional<CrossfitClass> crossfitClass = crossfitClassesRepository.findById(classId);
        Optional<Athlete> athlete = athletesRepository.findById(athleteId);
        throwIfNotPresent(crossfitClass, "Class Not Found");
        throwIfNotPresent(athlete, "Athlete Not Found");

        if (canClassFitMoreAthletes(crossfitClass)) {
            List<Athlete> existingAthletes = crossfitClass.get().getAthletes();
            if (includesAthlete(existingAthletes, athleteId)) {
                throw new ResponseStatusException(
                        HttpStatus.NOT_ACCEPTABLE, "Athlete is already registered to the course");
            }
            existingAthletes.add(athlete.get());
            crossfitClass.get().setAthletes(existingAthletes);
            CrossfitClass c = crossfitClassesRepository.saveAndFlush(crossfitClass.get());
            return convertToCrossfitClassDetailsDto(c);
        } else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_ACCEPTABLE, "Class is full");
        }

    }

    public static Boolean includesAthlete(List<Athlete> list, Long id) {
        return !(list.stream().filter(a -> id.equals(a.getId())).findFirst().orElse(null) == null);
    }

    private void throwIfNotPresent(Optional<?> entity, String msg) {
        if (entity.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, msg);

        }
    }

    private boolean canClassFitMoreAthletes(Optional<CrossfitClass> crossfitClass) {
        return crossfitClass.get().getAthletes().size() < crossfitClass.get().getMaxParticipants();
    }


    private CrossfitClassDto convertToCrossfitClassDto(CrossfitClass crossfitClass) {
        return modelMapper.map(crossfitClass, CrossfitClassDto.class);
    }

    private CrossfitClass crossfitClassDtoconvertToEntity(CrossfitClassDto crossfitClassDto) {
        return modelMapper.map(crossfitClassDto, CrossfitClass.class);
    }

    private CrossfitClassDetailsDto convertToCrossfitClassDetailsDto(CrossfitClass crossfitClass) {
        return modelMapper.map(crossfitClass, CrossfitClassDetailsDto.class);
    }

    private CrossfitClassInfoDto convertToCrossfitClassInfoDto(CrossfitClassInfo crossfitClassInfo) {
        return modelMapper.map(crossfitClassInfo, CrossfitClassInfoDto.class);
    }


}
