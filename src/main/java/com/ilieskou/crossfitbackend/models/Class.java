package com.ilieskou.crossfitbackend.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.sql.Time;
import java.util.List;
import java.util.Set;

@Entity(name="classes")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Class {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long class_id;

    private String class_type;
    private String class_day;
    private Time class_time;
    private Integer class_duration;
    private Integer max_participants;

    @ManyToMany
    @JoinTable(
            name = "classes_instructors",
            joinColumns = @JoinColumn(name = "class_id"),
            inverseJoinColumns = @JoinColumn(name = "instructor_id"))
    private List<Instructor> instructors;

    @OneToMany(mappedBy = "_class")
    Set<ClassRegistration> registrations;

    public Class(){

    }

    public Long getClass_id() {
        return class_id;
    }

    public void setClass_id(Long athlete_id) {
        this.class_id = athlete_id;
    }

    public String getClass_type() {
        return class_type;
    }

    public void setClass_type(String class_type) {
        this.class_type = class_type;
    }

    public String getClass_day() {
        return class_day;
    }

    public void setClass_day(String class_day) {
        this.class_day = class_day;
    }

    public Time getClass_time() {
        return class_time;
    }

    public void setClass_time(Time class_time) {
        this.class_time = class_time;
    }

    public Integer getClass_duration() {
        return class_duration;
    }

    public void setClass_duration(Integer class_duration) {
        this.class_duration = class_duration;
    }

    public Integer getMax_participants() {
        return max_participants;
    }

    public void setMax_participants(Integer max_participants) {
        this.max_participants = max_participants;
    }
}
