package com.ilieskou.crossfitbackend.services;

import com.ilieskou.crossfitbackend.models.Athlete;
import com.ilieskou.crossfitbackend.models.dto.AthleteDto;
import com.ilieskou.crossfitbackend.repositories.AthletesRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AthletesService {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private AthletesRepository athletesRepository;


    public List<AthleteDto> getAllAthletes() {
        List<Athlete> athletes = athletesRepository.findAll();
        return athletes.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public AthleteDto getAthlete(Long id) {
        Athlete athlete = athletesRepository.findById(id).get();
        return modelMapper.map(athlete, AthleteDto.class);
    }

    public AthleteDto create(final AthleteDto athleteDto) {
        Athlete athlete = convertToEntity(athleteDto);
        Athlete athleteCreated = athletesRepository.saveAndFlush(athlete);
        return convertToDto(athleteCreated);
    }


    public void delete(Long id) {
        athletesRepository.deleteById(id);
    }

    private AthleteDto convertToDto(Athlete athlete) {
        return modelMapper.map(athlete, AthleteDto.class);
    }

    private Athlete convertToEntity(AthleteDto athleteDto) {
        return modelMapper.map(athleteDto, Athlete.class);
    }

}
