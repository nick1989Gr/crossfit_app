package com.ilieskou.crossfitbackend.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.sql.Timestamp;


@Entity(name="crossfit_class_registration")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ClassRegistration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long crossfit_class_registration_id;

    @ManyToOne
    @JoinColumn(name = "crossfit_class_id")
    private CrossfitClass crossfitClass;

    @ManyToOne()
    @JoinColumn(name = "athlete_id")
    private Athlete athlete;

    private Timestamp crossfit_class_instance_ts;

    public ClassRegistration() {

    }

    public Long getCrossfit_class_registration_id() {
        return crossfit_class_registration_id;
    }

    public void setCrossfit_class_registration_id(Long class_registration_id) {
        this.crossfit_class_registration_id = class_registration_id;
    }

    public CrossfitClass get_class() {
        return crossfitClass;
    }

    public void set_class(CrossfitClass crossfitClass_id) {
        this.crossfitClass = crossfitClass_id;
    }

    public Athlete getAthlete() {
        return athlete;
    }

    public void setAthlete(Athlete athlete_id) {
        this.athlete = athlete_id;
    }

    public Timestamp getCrossfit_class_instance_ts() {
        return crossfit_class_instance_ts;
    }

    public void setCrossfit_class_instance_ts(Timestamp class_instance_ts) {
        this.crossfit_class_instance_ts = class_instance_ts;
    }
}

