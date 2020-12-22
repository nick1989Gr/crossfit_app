package com.ilieskou.crossfitbackend.repositories;

import com.ilieskou.crossfitbackend.models.ClassRegistration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CrossfitClassRegistrationRepository extends JpaRepository<ClassRegistration, Long> {
}
