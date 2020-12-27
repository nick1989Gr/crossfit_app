package com.ilieskou.crossfitbackend.controllers;

import com.ilieskou.crossfitbackend.models.Athlete;
import com.ilieskou.crossfitbackend.models.dto.AthleteDto;
import com.ilieskou.crossfitbackend.repositories.AthletesRepository;
import com.ilieskou.crossfitbackend.services.AthletesService;
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
    private AthletesService athletesService;


    @GetMapping
    public List<AthleteDto> getAllAthletes() {
        return athletesService.getAllAthletes();
    }

    @GetMapping
    @RequestMapping("{id}")
    public AthleteDto getAthlete(@PathVariable Long id) {
        return athletesService.getAthlete(id);
    }

    @PostMapping
    public AthleteDto create(@RequestBody final AthleteDto athleteDto) throws ParseException {
        return athletesService.create(athleteDto);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        athletesService.delete(id);
    }
}
