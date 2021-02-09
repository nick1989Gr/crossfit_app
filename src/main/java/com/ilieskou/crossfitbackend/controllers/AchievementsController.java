package com.ilieskou.crossfitbackend.controllers;

import com.ilieskou.crossfitbackend.controllers.dto.AchievementsLogDto;
import com.ilieskou.crossfitbackend.controllers.dto.ExerciseRecordDto;
import com.ilieskou.crossfitbackend.services.AchievementsService;
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
@RequestMapping("/api/v1/achievements")
public class AchievementsController {

    @Autowired
    private AchievementsService achievementsService;

    @Operation(summary = "Get up to the records for up to 10 athletes for the given exercise id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Achievements returned",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExerciseRecordDto.class))}),
            @ApiResponse(responseCode = "500", description = "No records available for this exercise id",
                    content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized request",
                    content = @Content)})
    @GetMapping("top/{exerciseId}")
    public ExerciseRecordDto getTopAchievements(@PathVariable Long exerciseId) {
        return achievementsService.getTopAchievements(exerciseId);
    }

    @Operation(summary = "Get all the achievements for a specific athlete id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Achievements returned",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AchievementsLogDto.class))}),
            @ApiResponse(responseCode = "500", description = "No records available for this athlete id",
                    content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized request",
                    content = @Content)})
    @GetMapping("athlete/{athleteId}")
    public List<AchievementsLogDto> getTopAchievementsForAthlete(@PathVariable Long athleteId) {
        return achievementsService.getTopAchievementsForAthlete(athleteId);
    }


}
