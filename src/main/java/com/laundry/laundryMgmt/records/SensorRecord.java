package com.laundry.laundryMgmt.records;

import com.laundry.laundryMgmt.models.SensorType;

/**
 * @author Fatima GHAOUI
 * @version 1.0
 *
 * Represents a sensor record used in laundry management.
 *
 * @param id unique identifier for the sensor.
 * @param name name of sensor
 * @param measure measurement value from the sensor.
 * @param sensorType type of sensor used.
 * @param status status of the sensor (active/inactive).
 */
public record SensorRecord(Long id, String name, int measure, SensorType sensorType, boolean status) {
    // Constructor and methods for SensorRecord
}