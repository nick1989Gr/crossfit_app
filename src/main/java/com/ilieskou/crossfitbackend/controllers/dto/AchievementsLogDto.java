package com.ilieskou.crossfitbackend.controllers.dto;

import java.util.Date;
import java.util.List;

public class AchievementsLogDto {
    private String exerciseName;
    private List<Date> ts;
    private List<Double> achievements;

    public AchievementsLogDto(String exerciseName, List<Date> ts, List<Double> achievement) {
        this.exerciseName = exerciseName;
        this.ts = ts;
        this.achievements = achievement;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public List<Date> getTs() {
        return ts;
    }

    public void setTs(List<Date> ts) {
        this.ts = ts;
    }

    public List<Double> getAchievements() {
        return achievements;
    }

    public void setAchievements(List<Double> achievements) {
        this.achievements = achievements;
    }
}


