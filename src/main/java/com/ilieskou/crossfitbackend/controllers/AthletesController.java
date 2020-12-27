package com.ilieskou.crossfitbackend.controllers;

import com.ilieskou.crossfitbackend.models.Athlete;
import com.ilieskou.crossfitbackend.models.dto.AthleteDto;
import com.ilieskou.crossfitbackend.repositories.AthletesRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public Athlete get(@PathVariable Long id) {
        return athletesRepository.findById(id).get();
    }

    @PostMapping
    public Athlete create(@RequestBody final Athlete athlete) {
        return athletesRepository.saveAndFlush(athlete);
    }

    private AthleteDto convertToDto(Athlete athlete) {
        AthleteDto athleteDto = modelMapper.map(athlete, AthleteDto.class);
        return athleteDto;
    }
}
