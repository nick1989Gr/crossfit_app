package com.ilieskou.crossfitbackend.controllers;

import com.ilieskou.crossfitbackend.models.dto.AthleteDto;
import com.ilieskou.crossfitbackend.services.AthletesService;
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
@RequestMapping("/api/v1/athletes")
public class AthletesController {

    @Autowired
    private AthletesService athletesService;

    @Operation(summary = "Get all the athletes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of athletes was returned",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AthleteDto.class))}),
            @ApiResponse(responseCode = "401", description = "Unauthorized request",
                    content = @Content)})
    @GetMapping
    public List<AthleteDto> getAllAthletes() {
        return athletesService.getAllAthletes();
    }

    @Operation(summary = "Get an athlete's data by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Athlete found and returned",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AthleteDto.class))}),
            @ApiResponse(responseCode = "500", description = "No value present because of wrong athlete id",
                    content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized request",
                    content = @Content)})
    
    @GetMapping
    @RequestMapping("{id}")
    public AthleteDto getAthlete(@PathVariable Long id) {
        return athletesService.getAthlete(id);
    }

    @Operation(summary = "Add an athlete to the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Athlete was added succesfuly to the database",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AthleteDto.class))}),
            @ApiResponse(responseCode = "401", description = "Unauthorized request",
                    content = @Content)})
    @PostMapping
    public AthleteDto create(@RequestBody final AthleteDto athleteDto) {
        return athletesService.create(athleteDto);
    }

    @Operation(summary = "Delete an athlete by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Athlete was deleted",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "No value present because of wrong athlete id",
                    content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized request",
                    content = @Content)})
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        athletesService.delete(id);
    }
}
