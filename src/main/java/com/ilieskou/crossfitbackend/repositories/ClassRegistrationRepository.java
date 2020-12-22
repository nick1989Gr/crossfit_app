package com.ilieskou.crossfitbackend.repositories;

import com.ilieskou.crossfitbackend.models.ClassRegistration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassRegistrationRepository extends JpaRepository<ClassRegistration, Long> {
}
