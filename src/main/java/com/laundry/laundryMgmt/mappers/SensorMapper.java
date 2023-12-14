package com.laundry.laundryMgmt.mappers;

import com.laundry.laundryMgmt.models.SensorEntity;
import com.laundry.laundryMgmt.records.SensorRecord;

public class SensorMapper {
    public static SensorRecord of(SensorEntity sensor) {
        return new SensorRecord(
                sensor.getSensorId(),
                sensor.getSensorName(),
                sensor.getMeasure(),
                sensor.getSensorType(),
                sensor.getStatus()
        );
    }
}