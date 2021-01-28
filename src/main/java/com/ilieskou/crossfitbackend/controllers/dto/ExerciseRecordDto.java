package com.ilieskou.crossfitbackend.controllers.dto;

import java.util.List;

public class ExerciseRecordDto {

    private String exerciseName;
    private String exerciseMeasurement;
    private List<RecordEntry> recordEntries;


    public ExerciseRecordDto(String exerciseName,
                             String exerciseMeasurement,
                             List<RecordEntry> recordEntries) {
        this.exerciseName = exerciseName;
        this.exerciseMeasurement = exerciseMeasurement;
        this.recordEntries = recordEntries;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public String getExerciseMeasurement() {
        return exerciseMeasurement;
    }

    public void setExerciseMeasurement(String exerciseMeasurement) {
        this.exerciseMeasurement = exerciseMeasurement;
    }

    public List<RecordEntry> getRecordEntries() {
        return recordEntries;
    }

    public void setRecordEntries(List<RecordEntry> recordEntries) {
        this.recordEntries = recordEntries;
    }

    public static class RecordEntry {
        private String athleteName;
        private Double record;

        public RecordEntry(String athleteName, Double record) {
            this.athleteName = athleteName;
            this.record = record;
        }

        public String getAthleteName() {
            return athleteName;
        }

        public void setAthleteName(String athleteName) {
            this.athleteName = athleteName;
        }

        public Double getRecord() {
            return record;
        }

        public void setRecord(Double record) {
            this.record = record;
        }
    }
}
