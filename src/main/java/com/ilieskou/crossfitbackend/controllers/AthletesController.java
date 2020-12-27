package com.ilieskou.crossfitbackend.controllers;

import com.ilieskou.crossfitbackend.models.Athlete;
import com.ilieskou.crossfitbackend.models.dto.AthleteDto;
import com.ilieskou.crossfitbackend.repositories.AthletesRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/athletes")
public class AthletesController {

    @Autowired
    private AthletesRepository athletesRepository;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public List<AthleteDto> list() {
        List<Athlete> athletes = athletesRepository.findAll();
        return athletes.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping
    @RequestMapping("{id}")
    public AthleteDto get(@PathVariable Long id) {
        Athlete athlete = athletesRepository.findById(id).get();
        return modelMapper.map(athlete, AthleteDto.class);
    }

    @PostMapping
    public AthleteDto create(@RequestBody final AthleteDto athleteDto) throws ParseException {
        Athlete athlete = convertToEntity(athleteDto);
        Athlete athleteCreated = athletesRepository.saveAndFlush(athlete);
        return convertToDto(athleteCreated);
    }

    private AthleteDto convertToDto(Athlete athlete) {
        AthleteDto athleteDto = modelMapper.map(athlete, AthleteDto.class);
        return athleteDto;
    }

    private Athlete convertToEntity(AthleteDto athleteDto) throws ParseException {
        Athlete athlete = modelMapper.map(athleteDto, Athlete.class);
        return athlete;
    }
}
