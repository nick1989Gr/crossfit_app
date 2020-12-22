package com.ilieskou.crossfitbackend.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.sql.Timestamp;


@Entity(name="crossfit_class_registration")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ClassRegistration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "crossfit_class_registration_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "crossfit_class_id")
    private CrossfitClass crossfitClass;

    @ManyToOne()
    @JoinColumn(name = "athlete_id")
    private Athlete athlete;

    @Column(name = "crossfit_class_instance_ts")
    private Timestamp classTimestamp;

    public ClassRegistration() {

    }

    public CrossfitClass getCrossfitClass() {
        return crossfitClass;
    }

    public void setCrossfitClass(CrossfitClass crossfitClass) {
        this.crossfitClass = crossfitClass;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long class_registration_id) {
        this.id = class_registration_id;
    }

    public Athlete getAthlete() {
        return athlete;
    }

    public void setAthlete(Athlete athlete_id) {
        this.athlete = athlete_id;
    }

    public Timestamp getClassTimestamp() {
        return classTimestamp;
    }

    public void setClassTimestamp(Timestamp class_instance_ts) {
        this.classTimestamp = class_instance_ts;
    }
}

