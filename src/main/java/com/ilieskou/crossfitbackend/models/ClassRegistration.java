package com.ilieskou.crossfitbackend.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.sql.Timestamp;


@Entity(name="class_registration")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ClassRegistration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long class_registration_id;

    @ManyToOne
    @JoinColumn(name = "class_id")
    private Class _class;

    @ManyToOne()
    @JoinColumn(name = "athlete_id")
    private Athlete athlete;

    private Timestamp class_instance_ts;

    public ClassRegistration() {

    }

    public Long getClass_registration_id() {
        return class_registration_id;
    }

    public void setClass_registration_id(Long class_registration_id) {
        this.class_registration_id = class_registration_id;
    }

    public Class get_class() {
        return _class;
    }

    public void set_class(Class class_id) {
        this._class = class_id;
    }

    public Athlete getAthlete() {
        return athlete;
    }

    public void setAthlete(Athlete athlete_id) {
        this.athlete = athlete_id;
    }

    public Timestamp getClass_instance_ts() {
        return class_instance_ts;
    }

    public void setClass_instance_ts(Timestamp class_instance_ts) {
        this.class_instance_ts = class_instance_ts;
    }
}

