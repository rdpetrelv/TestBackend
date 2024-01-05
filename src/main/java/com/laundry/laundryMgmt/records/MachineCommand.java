package com.laundry.laundryMgmt.records;

import com.laundry.laundryMgmt.models.CycleType;

import java.time.LocalTime;

/**
 * @author David PETREL
 * @version 1.0
 *
 * Represents commands related to machines in laundry management.
 * This record be used to handle specific operations associated
 * with machines management.
 *
 * This class encapsulate methods for creating, updating, deleting or
 * manipulating machines-related data without storing historical
 * records or additional functionalities present in MachineRecord.
 *
 * @param machineId unique identifier for the laundry machine.
 * @param name name or identifier of the machine.
 * @param available the status of the machine
 * @param machineStatus the current status or condition of the machine (operational, out of order).
 * @param progress the current progress or state of a running cycle
 */
public record MachineCommand(long machineId, String name, boolean available, boolean machineStatus, int progress) {
}
