package com.ilieskou.crossfitbackend.repositories;

import com.ilieskou.crossfitbackend.models.CrossfitClassInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CrossfitClassInfoRepository extends JpaRepository<CrossfitClassInfo, Long> {
}
