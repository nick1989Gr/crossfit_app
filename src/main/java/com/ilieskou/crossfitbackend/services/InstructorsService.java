package com.ilieskou.crossfitbackend.services;

import com.ilieskou.crossfitbackend.controllers.dto.InstructorDto;
import com.ilieskou.crossfitbackend.models.Instructor;
import com.ilieskou.crossfitbackend.repositories.InstructorsRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InstructorsService {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private InstructorsRepository instructorsRepository;

    public List<InstructorDto> getAllInstructors() {
        List<Instructor> instructors = instructorsRepository.findAll();
        return instructors.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public InstructorDto getInstructor(Long id) {
        Instructor instructor = instructorsRepository.findById(id).get();
        return convertToDto(instructor);
    }

    private InstructorDto convertToDto(Instructor instructor) {
        return modelMapper.map(instructor, InstructorDto.class);
    }

}
