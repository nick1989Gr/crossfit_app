package com.ilieskou.crossfitbackend.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity(name="instructors")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Instructor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long instructor_id;

    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "date_of_birth")
    private Date dateOfBirth;
    private String email;
    @Column(name = "phone_number")
    private String phoneNumber;

    @ManyToMany(mappedBy = "instructors")
    private List<CrossfitClass> crossfitClasses;

    public Instructor() {

    }

    public Long getInstructor_id() {
        return instructor_id;
    }

    public void setInstructor_id(Long instructor_id) {
        this.instructor_id = instructor_id;
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
