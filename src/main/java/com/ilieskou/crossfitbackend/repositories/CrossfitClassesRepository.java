package com.ilieskou.crossfitbackend.repositories;

import com.ilieskou.crossfitbackend.models.CrossfitClass;
import com.ilieskou.crossfitbackend.repositories.projections.IExtraSchedule;
import com.ilieskou.crossfitbackend.repositories.projections.ISchedule;
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
                    "cast(c.crossfit_class_ts  as time) as classTime, " +
                    "FREE_SLOTS.availableSlots " +
            "FROM crossfit_classes as c  " +
            "JOIN (" +
                    "SELECT CLASSES.crossfit_class_id, " +
                           "CLASSES.max_participants - COUNT(CLASSES_ATHLETES.crossfit_class_id) as availableSlots\n" +
                    "FROM crossfit_classes as CLASSES \n" +
                    "LEFT JOIN crossfit_classes_athletes as CLASSES_ATHLETES " +
                    "ON CLASSES.crossfit_class_id = CLASSES_ATHLETES.crossfit_class_id " +
                    "GROUP BY CLASSES.crossfit_class_id" +
                   ") as FREE_SLOTS " +
            "ON FREE_SLOTS.crossfit_class_id = c.crossfit_class_id " +
            "WHERE c.crossfit_class_ts >=CAST(:start_time AS TIMESTAMP)  " +
            "AND c.crossfit_class_ts <=CAST(:end_time AS TIMESTAMP) " +
            "ORDER BY crossfitClassId", nativeQuery = true)
    public List<ISchedule> getCrossfitClassesForTimePeriod(
            @Param("start_time") Timestamp start_time,
            @Param("end_time") Timestamp end_time);


    @Query(value =
            "SELECT c.crossfit_class_id AS crossfitClassId, " +
                    "c.crossfit_class_type AS classType,  " +
                    "c.crossfit_class_ts AS ts,  " +
                    "cast(c.crossfit_class_ts AS date) AS classDate,  " +
                    "cast(c.crossfit_class_ts  AS time) AS classTime,  " +
                    "c.max_participants - coalesce(i.enroledAthletes, 0) as availableSlots,  " +
            "CASE WHEN ii.athleteEnrolled=0 THEN 'false' else 'true' END athleteEnrolled  " +
            "FROM crossfit_classes as c   " +
            "LEFT JOIN  " +
            "         (SELECT crossfit_class_id,  " +
            "                 count(crossfit_class_id) AS enroledAthletes  " +
            "          FROM crossfit_classes_athletes GROUP BY crossfit_class_id " +
            "          ) AS i               " +
            "ON i.crossfit_class_id=c.crossfit_class_id " +
            "JOIN(SELECT " +
            "    c.crossfit_class_id, " +
            "    COUNT(ca.crossfit_class_id) AS athleteEnrolled " +
            "FROM crossfit_classes c " +
            "LEFT JOIN crossfit_classes_athletes ca " +
            "    ON ca.crossfit_class_id = c.crossfit_class_id AND " +
            "       ca.athlete_id = :athleteId " +
            "GROUP BY " +
            "    c.crossfit_class_id " +
            "ORDER BY " +
            "    c.crossfit_class_id) AS ii " +
            "ON ii.crossfit_class_id=c.crossfit_class_id " +
            "WHERE c.crossfit_class_ts >=CAST(:start_time AS TIMESTAMP)  " +
            "        AND c.crossfit_class_ts <=CAST(:end_time AS TIMESTAMP) " +
            "ORDER BY crossfitClassId ", nativeQuery = true)
    public List<IExtraSchedule> getCrossfitClassesForTimePeriodWithAthletePresence(
            @Param("athleteId") Long athleteId,
            @Param("start_time") Timestamp start_time,
            @Param("end_time") Timestamp end_time);

}
