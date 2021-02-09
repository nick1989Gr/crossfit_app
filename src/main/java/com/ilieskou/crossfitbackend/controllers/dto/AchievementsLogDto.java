package com.ilieskou.crossfitbackend.controllers.dto;

import java.util.Date;
import java.util.List;

public class AchievementsLogDto {
    private String exerciseName;
    private String exerciseMeasurement;
    private List<AchievementEntry> achievements;

    public AchievementsLogDto(String exerciseName, String exerciseMeasurement, List<AchievementEntry> achievements) {
        this.exerciseName = exerciseName;
        this.exerciseMeasurement = exerciseMeasurement;
        this.achievements = achievements;
    }

    public String getExerciseMeasurement() {
        return exerciseMeasurement;
    }

    public void setExerciseMeasurement(String exerciseMeasurement) {
        this.exerciseMeasurement = exerciseMeasurement;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public List<AchievementEntry> getAchievements() {
        return achievements;
    }

    public void setAchievements(List<AchievementEntry> achievements) {
        this.achievements = achievements;
    }

    public static class AchievementEntry {
        private Date ts;
        private Double value;

        public AchievementEntry(Date ts, Double value) {
            this.ts = ts;
            this.value = value;
        }

        public Date getTs() {
            return ts;
        }

        public void setTs(Date ts) {
            this.ts = ts;
        }

        public Double getValue() {
            return value;
        }

        public void setValue(Double value) {
            this.value = value;
        }
    }
}


