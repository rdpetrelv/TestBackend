
package com.laundry.laundryMgmt.records;

import com.laundry.laundryMgmt.models.CycleType;

import java.time.LocalTime;

public record MachineRecord(long machineId, String name, boolean available, boolean machineStatus, int progress, CycleType cycleType, LocalTime startingTime) {
}