package com.ilieskou.crossfitbackend.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.sql.Time;
import java.util.List;
import java.util.Set;

@Entity(name="crossfit_classes")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class CrossfitClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "crossfit_class_id")
    private Long id;

    @Column(name = "crossfit_class_type")
    private String type;
    @Column(name = "crossfit_class_day")
    private String classDay;
    @Column(name = "crossfit_class_time")
    private Time classTime;
    @Column(name = "crossfit_class_duration")
    private Integer classDuration;
    @Column(name = "max_participants")
    private Integer maxParticipants;

    @ManyToMany
    @JoinTable(
            name = "crossfit_classes_instructors",
            joinColumns = @JoinColumn(name = "crossfit_class_id"),
            inverseJoinColumns = @JoinColumn(name = "instructor_id"))
    private List<Instructor> instructors;

    @OneToMany(mappedBy = "crossfitClass")
    Set<ClassRegistration> registrations;

    public CrossfitClass(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long athlete_id) {
        this.id = athlete_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String class_type) {
        this.type = class_type;
    }

    public String getClassDay() {
        return classDay;
    }

    public void setClassDay(String class_day) {
        this.classDay = class_day;
    }

    public Time getClassTime() {
        return classTime;
    }

    public void setClassTime(Time class_time) {
        this.classTime = class_time;
    }

    public Integer getClassDuration() {
        return classDuration;
    }

    public void setClassDuration(Integer class_duration) {
        this.classDuration = class_duration;
    }

    public Integer getMaxParticipants() {
        return maxParticipants;
    }

    public void setMaxParticipants(Integer max_participants) {
        this.maxParticipants = max_participants;
    }
}
