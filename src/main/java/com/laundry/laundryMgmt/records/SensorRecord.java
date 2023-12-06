package com.laundry.laundryMgmt.records;

import com.laundry.laundryMgmt.models.SensorType;

public record SensorRecord(Long id, String name, int measure, SensorType sensorType) {
}