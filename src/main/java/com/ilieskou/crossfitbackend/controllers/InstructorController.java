package com.ilieskou.crossfitbackend.controllers;

import com.ilieskou.crossfitbackend.models.Instructor;
import com.ilieskou.crossfitbackend.repositories.InstructorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/instructors")
public class InstructorController {

    @Autowired
    private InstructorsRepository instructorsRepository;

    @GetMapping
    public List<Instructor> list() {
        return instructorsRepository.findAll();
    }

    @GetMapping
    @RequestMapping("{id}")
    public Instructor get(@PathVariable Long id) {
        return instructorsRepository.findById(id).get();
    }
}


