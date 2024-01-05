package com.laundry.laundryMgmt.mappers;

import com.laundry.laundryMgmt.models.SensorEntity;
import com.laundry.laundryMgmt.records.SensorRecord;
/**
 * @author  Fatima GHAOUI
 * A mapper class responsible for converting SensorEntity objects to SensorRecord objects.
 */
public class SensorMapper {
    /**
     * Converts a SensorEntity object to a SensorRecord object.
     *
     * @param sensor The SensorEntity object to be converted.
     * @return The corresponding SensorRecord object.
     */
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