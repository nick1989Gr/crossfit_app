package com.ilieskou.crossfitbackend.controllers;

import com.ilieskou.crossfitbackend.models.dto.InstructorDto;
import com.ilieskou.crossfitbackend.services.InstructorsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/instructors")
public class InstructorsController {

    @Autowired
    private InstructorsService instructorsService;

    @Operation(summary = "Get all instructors")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "A list of instructors is returned",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = InstructorDto.class))})})
    @GetMapping
    public List<InstructorDto> getAllInstructors() {
        return instructorsService.getAllInstructors();
    }


    @Operation(summary = "Get an instructors's data by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Instructor found and returned",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = InstructorDto.class))}),
            @ApiResponse(responseCode = "500", description = "No value present because of wrong instructor id",
                    content = @Content)})
    @GetMapping
    @RequestMapping("{id}")
    public InstructorDto getInstructor(@PathVariable Long id) {
        return instructorsService.getInstructor(id);
    }
}


