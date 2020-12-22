package com.ilieskou.crossfitbackend.controllers;
import com.ilieskou.crossfitbackend.models.Class;
import com.ilieskou.crossfitbackend.repositories.ClassesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/classes")
public class ClassesController {

    @Autowired
    private ClassesRepository classesRepository;

    @GetMapping
    public List<Class> list() {
        return classesRepository.findAll();
    }

    @GetMapping
    @RequestMapping("{id}")
    public Class get(@PathVariable Long id) {
        return classesRepository.findById(id).get();
    }
}





