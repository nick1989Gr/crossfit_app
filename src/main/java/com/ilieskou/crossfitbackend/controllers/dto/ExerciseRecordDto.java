package com.ilieskou.crossfitbackend.controllers.dto;

import java.util.List;

public class ExerciseRecordDto {

    private String exerciseName;
    private String exerciseMeasurement;
    private List<String> athleteLastName;
    private List<Double> Record;


    public ExerciseRecordDto(String exerciseName,
                             String exerciseMeasurement,
                             List<String> athleteLastName,
                             List<Double> record) {
        this.exerciseName = exerciseName;
        this.exerciseMeasurement = exerciseMeasurement;
        this.athleteLastName = athleteLastName;
        Record = record;
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

    public List<String> getAthleteLastName() {
        return athleteLastName;
    }

    public void setAthleteLastName(List<String> athleteLastName) {
        this.athleteLastName = athleteLastName;
    }

    public List<Double> getRecord() {
        return Record;
    }

    public void setRecord(List<Double> record) {
        Record = record;
    }


}
