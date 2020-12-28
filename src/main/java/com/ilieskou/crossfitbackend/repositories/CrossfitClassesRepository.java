package com.ilieskou.crossfitbackend.repositories;

import com.ilieskou.crossfitbackend.models.CrossfitClass;
import com.ilieskou.crossfitbackend.models.projections.ISchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.List;

public interface CrossfitClassesRepository extends JpaRepository<CrossfitClass, Long> {


    @Query(value =
            "SELECT c.crossfit_class_id as crossfitClassId, " +
                    "c.crossfit_class_type as classType, " +
                    "c.crossfit_class_ts as ts, " +
                    "cast(c.crossfit_class_ts as date) as classDate, " +
                    "cast(c.crossfit_class_ts  as time) as classTime " +
                    "FROM crossfit_classes as c  " +
                    "WHERE c.crossfit_class_ts>=CAST(:start_time AS TIMESTAMP)  " +
                    "and c.crossfit_class_ts<=CAST(:end_time AS TIMESTAMP)", nativeQuery = true)
    public List<ISchedule> getCrossfitClassesForTimePeriod(
            @Param("start_time") Timestamp start_time,
            @Param("end_time") Timestamp end_time);

}
