package com.ilieskou.crossfitbackend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity(name = "athletes")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Athlete {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "athlete_id")
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "date_of_birth")
    private Date dateOfBirth;
    @Column(name = "enrolled_date")
    private Date enrolledDate;
    private String email;
    @Column(name = "phone_number")
    private String phoneNumber;

    @ManyToMany(mappedBy = "athletes")
    @JsonIgnore
    List<CrossfitClass> crossfitClasses;

    public Athlete() {

    }

    public Athlete(Long id, String firstName, String lastName, Date dateOfBirth, Date enrolledDate, String email, String phoneNumber) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.enrolledDate = enrolledDate;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public List<CrossfitClass> getCrossfitClasses() {
        return crossfitClasses;
    }

    public void setCrossfitClasses(List<CrossfitClass> crossfitClasses) {
        this.crossfitClasses = crossfitClasses;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long athlete_id) {
        this.id = athlete_id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String first_name) {
        this.firstName = first_name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String last_name) {
        this.lastName = last_name;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date date_of_birth) {
        this.dateOfBirth = date_of_birth;
    }

    public Date getEnrolledDate() {
        return enrolledDate;
    }

    public void setEnrolledDate(Date enrolled_date) {
        this.enrolledDate = enrolled_date;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phone_number) {
        this.phoneNumber = phone_number;
    }
}
