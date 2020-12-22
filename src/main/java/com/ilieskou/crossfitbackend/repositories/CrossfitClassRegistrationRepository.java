package com.ilieskou.crossfitbackend.repositories;

import com.ilieskou.crossfitbackend.models.ClassRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CrossfitClassRegistrationRepository extends JpaRepository<ClassRegistration, Long> {
    @Query(value = "SELECT CL.crossfit_class_type AS classType , REG.crossfit_class_instance_ts as classTimestamp " +
                   "FROM crossfit_class_registration AS REG " +
                   "JOIN crossfit_classes as CL on CL.crossfit_class_id=REG.crossfit_class_id " +
                   "WHERE REG.athlete_id=:athleteId ",
                   nativeQuery = true)
    List<AthleteRegistration> findRegistrationsForAthleteId(@Param("athleteId") Long athleteId);
}




