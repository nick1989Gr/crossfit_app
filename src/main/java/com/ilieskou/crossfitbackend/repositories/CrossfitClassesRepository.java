package com.ilieskou.crossfitbackend.repositories;
import com.ilieskou.crossfitbackend.models.CrossfitClass;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CrossfitClassesRepository extends JpaRepository<CrossfitClass, Long> {

}
