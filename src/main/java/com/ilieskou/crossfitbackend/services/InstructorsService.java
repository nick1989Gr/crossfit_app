package com.ilieskou.crossfitbackend.services;

import com.ilieskou.crossfitbackend.models.Instructor;
import com.ilieskou.crossfitbackend.repositories.InstructorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstructorsService {

    @Autowired
    private InstructorsRepository instructorsRepository;

    public List<Instructor> getAllInstructors() {
        return instructorsRepository.findAll();
    }

    public Instructor getInstructor(Long id) {
        return instructorsRepository.findById(id).get();
    }

}
