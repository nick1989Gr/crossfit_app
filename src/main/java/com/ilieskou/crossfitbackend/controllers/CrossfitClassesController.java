package com.ilieskou.crossfitbackend.controllers;

import com.ilieskou.crossfitbackend.models.dto.CrossfitClassDetailsDto;
import com.ilieskou.crossfitbackend.models.dto.CrossfitClassDto;
import com.ilieskou.crossfitbackend.models.dto.CrossfitClassInfoDto;
import com.ilieskou.crossfitbackend.models.dto.TimePeriodDto;
import com.ilieskou.crossfitbackend.models.projections.IExtraSchedule;
import com.ilieskou.crossfitbackend.models.projections.ISchedule;
import com.ilieskou.crossfitbackend.services.CrossfitClassesService;
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
@RequestMapping("/api/v1/classes")
public class CrossfitClassesController {

    @Autowired
    private CrossfitClassesService crossfitClassesService;

    @Operation(summary = "Get all classes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "A list of classes that are in the database",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CrossfitClassDetailsDto.class))}),
            @ApiResponse(responseCode = "401", description = "Unauthorized request",
                    content = @Content)})
    @GetMapping
    public List<CrossfitClassDto> list() {
        return crossfitClassesService.getAllCrossfitClasses();
    }

    @Operation(summary = "Get a class by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Class found and returned",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CrossfitClassDetailsDto.class))}),
            @ApiResponse(responseCode = "401", description = "Unauthorized request",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "No value present because of wrong class id",
                    content = @Content)})
    @GetMapping
    @RequestMapping("{id}")
    public CrossfitClassDetailsDto get(@PathVariable Long id) {
        return crossfitClassesService.getCrossfitClass(id);
    }

    @Operation(summary = "Get information about class types")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "A list of class types info",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CrossfitClassInfoDto.class))})})
    @GetMapping
    @RequestMapping("/info")
    public List<CrossfitClassInfoDto> get() {
        return crossfitClassesService.getCrossfitClassesInfo();
    }

    @Operation(summary = "Register an athlete in a class")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The updated class description",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CrossfitClassDetailsDto.class))}),
            @ApiResponse(responseCode = "401", description = "Unauthorized request",
                    content = @Content),
            @ApiResponse(responseCode = "406", description = "Class is full or athlete is already registered",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Athlete or class id was not correct",
                    content = @Content)})
    @GetMapping
    @RequestMapping(path = "registration/{athlete_id}/{class_id}")
    public CrossfitClassDetailsDto registerAthleteToClass(
            @PathVariable("athlete_id") final Long athleteId,
            @PathVariable("class_id") final Long classId
    ) {
        return crossfitClassesService.registerAthleteToClass(athleteId, classId);
    }

    @Operation(summary = "Delete registration of an athlete from a class")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The updated class description",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CrossfitClassDetailsDto.class))}),
            @ApiResponse(responseCode = "401", description = "Unauthorized request",
                    content = @Content),
            @ApiResponse(responseCode = "406", description = "Class is full or athlete is already registered or athlete was not registered to the specific class",
                    content = @Content)})
    @DeleteMapping(value = "registration/{athlete_id}/{class_id}")
    public CrossfitClassDetailsDto deleteRegistrationAthleteToClass(
            @PathVariable("athlete_id") final Long athleteId,
            @PathVariable("class_id") final Long classId
    ) {
        return crossfitClassesService.deleteRegistration(athleteId, classId);
    }

    @Operation(summary = "Get all available classes for a time period")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "A list of classes for that time period",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ISchedule.class))}),
            @ApiResponse(responseCode = "401", description = "Unauthorized request",
                    content = @Content)})
    @PostMapping
    @RequestMapping("schedule")
    public List<ISchedule> getSchedule(@RequestBody TimePeriodDto timePeriodDto) {
        return crossfitClassesService.getSchedule(timePeriodDto);
    }

    @Operation(summary = "Get all available classes for a time period with info regarding a specific athlete registration")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "A list of classes for that time period",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = IExtraSchedule.class))}),
            @ApiResponse(responseCode = "401", description = "Unauthorized request",
                    content = @Content)})
    @PostMapping
    @RequestMapping("schedule/{athlete_id}")
    public List<IExtraSchedule> getScheduleWithAthleteInfo(@PathVariable("athlete_id") final Long athleteId,
                                                           @RequestBody TimePeriodDto timePeriodDto) {
        return crossfitClassesService.getScheduleWithAthleteInfo(athleteId, timePeriodDto);
    }
}





