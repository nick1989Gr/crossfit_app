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
    private Long crossfit_class_id;

    private String crossfit_class_type;
    private String crossfit_class_day;
    private Time crossfit_class_time;
    private Integer crossfit_class_duration;
    private Integer max_participants;

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

    public Long getCrossfit_class_id() {
        return crossfit_class_id;
    }

    public void setCrossfit_class_id(Long athlete_id) {
        this.crossfit_class_id = athlete_id;
    }

    public String getCrossfit_class_type() {
        return crossfit_class_type;
    }

    public void setCrossfit_class_type(String class_type) {
        this.crossfit_class_type = class_type;
    }

    public String getCrossfit_class_day() {
        return crossfit_class_day;
    }

    public void setCrossfit_class_day(String class_day) {
        this.crossfit_class_day = class_day;
    }

    public Time getCrossfit_class_time() {
        return crossfit_class_time;
    }

    public void setCrossfit_class_time(Time class_time) {
        this.crossfit_class_time = class_time;
    }

    public Integer getCrossfit_class_duration() {
        return crossfit_class_duration;
    }

    public void setCrossfit_class_duration(Integer class_duration) {
        this.crossfit_class_duration = class_duration;
    }

    public Integer getMax_participants() {
        return max_participants;
    }

    public void setMax_participants(Integer max_participants) {
        this.max_participants = max_participants;
    }
}
