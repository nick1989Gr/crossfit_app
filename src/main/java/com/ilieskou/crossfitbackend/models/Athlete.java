package com.ilieskou.crossfitbackend.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity(name="athletes")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Athlete {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long athlete_id;

    private String first_name;
    private String last_name;
    private Date date_of_birth;
    private Date enrolled_date;
    private String email;
    private String phone_number;

    public Athlete(){

    }

    public Long getAthlete_id() {
        return athlete_id;
    }

    public void setAthlete_id(Long athlete_id) {
        this.athlete_id = athlete_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public Date getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(Date date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public Date getEnrolled_date() {
        return enrolled_date;
    }

    public void setEnrolled_date(Date enrolled_date) {
        this.enrolled_date = enrolled_date;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }
}
