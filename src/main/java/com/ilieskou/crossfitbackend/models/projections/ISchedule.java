package com.ilieskou.crossfitbackend.models.projections;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

public interface ISchedule {
    Long getCrossfitClassId();

    String getClassType();

    Timestamp getTs();

    Date getClassDate();

    Time getClassTime();

    Integer getAvailableSlots();
}
