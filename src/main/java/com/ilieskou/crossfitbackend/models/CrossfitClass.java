package com.ilieskou.crossfitbackend.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;



@Entity(name="crossfit_classes")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class CrossfitClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "crossfit_class_id")
    private Long id;

    @Column(name = "crossfit_class_type")
    private String type;
    @Column(name = "crossfit_class_ts")
    private Timestamp ts;
    @Column(name = "crossfit_class_duration")
    private Integer classDuration;
    @Column(name = "max_participants")
    private Integer maxParticipants;
    @Column(name = "remaining_positions")
    private Integer remainingPositions;

    @ManyToMany
    @JoinTable(
            name = "crossfit_classes_instructors",
            joinColumns = @JoinColumn(name = "crossfit_class_id"),
            inverseJoinColumns = @JoinColumn(name = "instructor_id"))
    private List<Instructor> instructors;


    @ManyToMany
//    @Size(min=1, max=10)
    @JoinTable(
            name = "crossfit_classes_athletes",
            joinColumns = @JoinColumn(name = "crossfit_class_id"),
            inverseJoinColumns = @JoinColumn(name = "athlete_id"))
    private List<Athlete> athletes;

    public CrossfitClass(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Timestamp getTs() {
        return ts;
    }

    public void setTs(Timestamp ts) {
        this.ts = ts;
    }

    public Integer getClassDuration() {
        return classDuration;
    }

    public void setClassDuration(Integer classDuration) {
        this.classDuration = classDuration;
    }

    public Integer getMaxParticipants() {
        return maxParticipants;
    }

    public void setMaxParticipants(Integer maxParticipants) {
        this.maxParticipants = maxParticipants;
    }

    public Integer getRemainingPositions() {
        return remainingPositions;
    }

    public void setRemainingPositions(Integer remainingPositions) {
        this.remainingPositions = remainingPositions;
    }

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
