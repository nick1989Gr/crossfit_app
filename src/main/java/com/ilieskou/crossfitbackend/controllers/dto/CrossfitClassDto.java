package com.ilieskou.crossfitbackend.controllers.dto;

import java.sql.Timestamp;

public class CrossfitClassDto {

    private Long id;
    private String type;
    private Timestamp ts;
    private Integer classDuration;
    private Integer maxParticipants;

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

}
