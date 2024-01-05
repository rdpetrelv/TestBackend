
package com.laundry.laundryMgmt.records;

import com.laundry.laundryMgmt.models.CycleType;

import java.time.LocalTime;

/**
 * @author David PETREL
 * @version 1.0
 *
 * Represents a machine record used in laundry management.
 *
 * @param machineId unique identifier for the laundry machine.
 * @param name name or identifier of the machine.
 * @param available the status of the machine
 * @param machineStatus the current status or condition of the machine (operational, out of order).
 * @param progress the current progress or state of a running cycle
 * @param cycleType type of cycle the machine
 * @param startingTime the time when the machine's cycle started or is scheduled to start.
 */
public record MachineRecord(long machineId, String name, boolean available, boolean machineStatus, int progress, CycleType cycleType, LocalTime startingTime) {
    // Constructor and methods for MachineRecord
}