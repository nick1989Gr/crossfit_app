package com.ilieskou.crossfitbackend.controllers;

import com.ilieskou.crossfitbackend.models.ClassRegistration;
import com.ilieskou.crossfitbackend.repositories.CrossfitClassRegistrationRepository;

import com.ilieskou.crossfitbackend.repositories.AthleteRegistration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/classregistration")
public class ClassRegistrationController {

    @Autowired
    private CrossfitClassRegistrationRepository crossfitClassRegistrationRepository;

    @GetMapping
    public List<ClassRegistration> list() {
        return crossfitClassRegistrationRepository.findAll();
    }

    @GetMapping
    @RequestMapping("{athlete_id}")
    public List<AthleteRegistration> getRegistrations(@PathVariable Long athlete_id) {
        return crossfitClassRegistrationRepository.findRegistrationsForAthleteId(athlete_id);
    }

}
