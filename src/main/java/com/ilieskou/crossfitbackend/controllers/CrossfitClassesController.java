package com.ilieskou.crossfitbackend.controllers;

import com.ilieskou.crossfitbackend.models.dto.CrossfitClassDetailsDto;
import com.ilieskou.crossfitbackend.models.dto.CrossfitClassDto;
import com.ilieskou.crossfitbackend.models.dto.TimePeriodDto;
import com.ilieskou.crossfitbackend.models.projections.ISchedule;
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
    public List<CrossfitClassDto> list() {
        return crossfitClassesService.getAllCrossfitClasses();
    }

    @GetMapping
    @RequestMapping("{id}")
    public CrossfitClassDetailsDto get(@PathVariable Long id) {
        return crossfitClassesService.getCrossfitClass(id);
    }

    @PostMapping
    @RequestMapping(path = "registration/{athlete_id}/{class_id}")
    public CrossfitClassDetailsDto registerAthleteToClass(
            @PathVariable("athlete_id") final Long athleteId,
            @PathVariable("class_id") final Long classId
    ) {
        return crossfitClassesService.registerAthleteToClass(athleteId, classId);
    }

    @DeleteMapping(value = "registration/{athlete_id}/{class_id}")
    public CrossfitClassDetailsDto deleteRegistrationAthleteToClass(
            @PathVariable("athlete_id") final Long athleteId,
            @PathVariable("class_id") final Long classId
    ) {
        return crossfitClassesService.deleteRegistration(athleteId, classId);
    }

    @PostMapping
    @RequestMapping("schedule")
    public List<ISchedule> getSchedule(@RequestBody TimePeriodDto timePeriodDto) {
        return crossfitClassesService.getSchedule(timePeriodDto);
    }
}





