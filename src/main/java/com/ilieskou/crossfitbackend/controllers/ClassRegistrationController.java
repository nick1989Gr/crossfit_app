package com.ilieskou.crossfitbackend.controllers;

import com.ilieskou.crossfitbackend.models.ClassRegistration;
import com.ilieskou.crossfitbackend.repositories.CrossfitClassRegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/classregistration")
public class ClassRegistrationController {


    @Autowired
    private CrossfitClassRegistrationRepository crossfitClassRegistrationRepository;

    @GetMapping
    public List<ClassRegistration> list() {
        return crossfitClassRegistrationRepository.findAll();
    }

    @GetMapping
    @RequestMapping("{id}")
    public ClassRegistration get(@PathVariable Long id) {
        return crossfitClassRegistrationRepository.findById(id).get();
    }
}
