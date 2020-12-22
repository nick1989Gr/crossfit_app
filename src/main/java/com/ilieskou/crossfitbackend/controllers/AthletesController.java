package com.ilieskou.crossfitbackend.controllers;

import com.ilieskou.crossfitbackend.models.Athlete;
import com.ilieskou.crossfitbackend.repositories.AthletesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/athletes")
public class AthletesController {

    @Autowired
    private AthletesRepository athletesRepository;

    @GetMapping
    public List<Athlete> list() {
        return athletesRepository.findAll();
    }

    @GetMapping
    @RequestMapping("{id}")
    public Athlete get(@PathVariable Long id) {
        return athletesRepository.findById(id).get();
    }

    @PostMapping
    public Athlete create(@RequestBody final Athlete athlete){
        return athletesRepository.saveAndFlush(athlete);
    }
}
