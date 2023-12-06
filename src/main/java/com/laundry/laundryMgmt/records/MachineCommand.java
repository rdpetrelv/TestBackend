package com.laundry.laundryMgmt.records;

import com.laundry.laundryMgmt.models.CycleType;

import java.time.LocalTime;

public record MachineCommand(long machineId, String name, boolean available, boolean machineStatus, int progress) {
}
