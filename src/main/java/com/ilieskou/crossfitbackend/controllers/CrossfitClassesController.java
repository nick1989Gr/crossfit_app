package com.ilieskou.crossfitbackend.controllers;
import com.ilieskou.crossfitbackend.models.CrossfitClass;
import com.ilieskou.crossfitbackend.repositories.CrossfitClassesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/classes")
public class CrossfitClassesController {

    @Autowired
    private CrossfitClassesRepository crossfitClassesRepository;

    @GetMapping
    public List<CrossfitClass> list() {
        return crossfitClassesRepository.findAll();
    }

    @GetMapping
    @RequestMapping("{id}")
    public CrossfitClass get(@PathVariable Long id) {
        return crossfitClassesRepository.findById(id).get();
    }
}





