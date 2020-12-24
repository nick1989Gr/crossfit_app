package com.ilieskou.crossfitbackend.controllers;

import com.ilieskou.crossfitbackend.models.CrossfitClass;
import com.ilieskou.crossfitbackend.services.CrossfitClassesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/classes")
public class CrossfitClassesController {

    @Autowired
    private CrossfitClassesService crossfitClassesService;

    @GetMapping
    public List<CrossfitClass> list() {
        return crossfitClassesService.getAllCrossfitClasses();
    }

    @GetMapping
    @RequestMapping("{id}")
    public CrossfitClass get(@PathVariable Long id) {
        return crossfitClassesService.getCrossfitClass(id);
    }

    @PostMapping
    @RequestMapping(path = "/{athlete_id}/{class_id}")
    public CrossfitClass registerAthleteToClass(
            @PathVariable("athlete_id") final Long athleteId,
            @PathVariable("class_id") final Long classId
    ) {
        return crossfitClassesService.registerAthleteToClass(athleteId, classId);
    }

    @DeleteMapping(value = "{athlete_id}/{class_id}")
    public CrossfitClass deleteRegistrationAthleteToClass(
            @PathVariable("athlete_id") final Long athleteId,
            @PathVariable("class_id") final Long classId
    ) {
        return crossfitClassesService.deleteRegistration(athleteId, classId);
    }
}





