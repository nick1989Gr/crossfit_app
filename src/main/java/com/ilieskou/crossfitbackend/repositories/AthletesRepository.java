package com.ilieskou.crossfitbackend.repositories;

import com.ilieskou.crossfitbackend.models.Athlete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AthletesRepository extends JpaRepository<Athlete, Long> {

    @Query(value =
            "SELECT * FROM athletes WHERE email=:thisEmail", nativeQuery = true)
    public Athlete findAthleteByEmail(@Param("thisEmail") String email);
}
