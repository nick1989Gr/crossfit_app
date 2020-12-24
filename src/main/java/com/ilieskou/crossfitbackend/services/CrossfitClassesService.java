package com.ilieskou.crossfitbackend.services;

import com.ilieskou.crossfitbackend.models.CrossfitClass;
import com.ilieskou.crossfitbackend.repositories.CrossfitClassesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CrossfitClassesService {

    @Autowired
    private CrossfitClassesRepository crossfitClassesRepository;

    public List<CrossfitClass> getAllCrossfitClasses(){
        return crossfitClassesRepository.findAll();
    }

    public CrossfitClass getCrossfitClass(Long id) {
        return crossfitClassesRepository.findById(id).get();
    }

}
