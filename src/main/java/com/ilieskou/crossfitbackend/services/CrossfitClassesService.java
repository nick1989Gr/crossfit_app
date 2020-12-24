package com.ilieskou.crossfitbackend.services;

import com.ilieskou.crossfitbackend.models.Athlete;
import com.ilieskou.crossfitbackend.models.CrossfitClass;
import com.ilieskou.crossfitbackend.repositories.AthletesRepository;
import com.ilieskou.crossfitbackend.repositories.CrossfitClassesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class CrossfitClassesService {

    @Autowired
    private CrossfitClassesRepository crossfitClassesRepository;

    @Autowired
    private AthletesRepository athletesRepository;

    public List<CrossfitClass> getAllCrossfitClasses() {
        return crossfitClassesRepository.findAll();
    }

    public CrossfitClass getCrossfitClass(Long id) {
        return crossfitClassesRepository.findById(id).get();
    }

    public CrossfitClass deleteRegistration(Long athleteId, Long classId) {
        Optional<CrossfitClass> crossfitClass = crossfitClassesRepository.findById(classId);
        Optional<Athlete> athlete = athletesRepository.findById(athleteId);
        throwIfNotPresent(crossfitClass, "Class Not Found");
        throwIfNotPresent(athlete, "Athlete Not Found");

        List<Athlete> registeredAthletes = crossfitClass.get().getAthletes();
        for (Athlete a : registeredAthletes) {
            if (a.getId() == athleteId) {
                registeredAthletes.remove(a);
                crossfitClass.get().setAthletes(registeredAthletes);
                return crossfitClassesRepository.saveAndFlush(crossfitClass.get());
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Athlete was not enrolled to the class");
    }

    public CrossfitClass registerAthleteToClass(Long athleteId, Long classId) {
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
            return crossfitClassesRepository.saveAndFlush(crossfitClass.get());
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


}
