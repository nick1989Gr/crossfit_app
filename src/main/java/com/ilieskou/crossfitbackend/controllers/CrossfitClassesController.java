package com.ilieskou.crossfitbackend.controllers;
import com.ilieskou.crossfitbackend.models.CrossfitClass;
import com.ilieskou.crossfitbackend.repositories.CrossfitClassesRepository;
import com.ilieskou.crossfitbackend.services.CrossfitClassesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
}





