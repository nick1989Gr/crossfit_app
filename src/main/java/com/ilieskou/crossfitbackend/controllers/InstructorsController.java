package com.ilieskou.crossfitbackend.controllers;

import com.ilieskou.crossfitbackend.models.dto.InstructorDto;
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
    public List<InstructorDto> getAllInstructors() {
        return instructorsService.getAllInstructors();
    }

    @GetMapping
    @RequestMapping("{id}")
    public InstructorDto getInstructor(@PathVariable Long id) {
        return instructorsService.getInstructor(id);
    }
}


