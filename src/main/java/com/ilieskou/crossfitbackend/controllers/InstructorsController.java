package com.ilieskou.crossfitbackend.controllers;

import com.ilieskou.crossfitbackend.models.Instructor;
import com.ilieskou.crossfitbackend.services.InstructorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/instructors")
public class InstructorsController {

    @Autowired
    private InstructorsService instructorsService;

    @GetMapping
    public List<Instructor> getAllInstructors() {
        return instructorsService.getAllInstructors();
    }

    @GetMapping
    @RequestMapping("{id}")
    public Instructor getInstructor(@PathVariable Long id) {
        return instructorsService.getInstructor(id);
    }
}


