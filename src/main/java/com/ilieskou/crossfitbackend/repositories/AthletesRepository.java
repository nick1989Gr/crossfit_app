package com.ilieskou.crossfitbackend.repositories;

import com.ilieskou.crossfitbackend.models.Athlete;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AthletesRepository extends JpaRepository<Athlete, Long> {
}
