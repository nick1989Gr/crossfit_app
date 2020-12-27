package com.ilieskou.crossfitbackend.models.dto;

import com.ilieskou.crossfitbackend.models.Athlete;
import com.ilieskou.crossfitbackend.models.Instructor;

import java.util.List;

public class CrossfitClassDetailsDto extends CrossfitClassDto {

    private List<Instructor> instructors;
    private List<Athlete> athletes;

    public List<Instructor> getInstructors() {
        return instructors;
    }

    public void setInstructors(List<Instructor> instructors) {
        this.instructors = instructors;
    }

    public List<Athlete> getAthletes() {
        return athletes;
    }

    public void setAthletes(List<Athlete> athletes) {
        this.athletes = athletes;
    }
}
