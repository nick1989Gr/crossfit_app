package com.ilieskou.crossfitbackend.repositories;

import com.ilieskou.crossfitbackend.models.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstructorsRepository extends JpaRepository<Instructor, Long> {
}
