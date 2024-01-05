package com.laundry.laundryMgmt.models;

import jakarta.persistence.*;

import java.time.LocalTime;
import java.util.Set;

/**
 * @author  David PETREL
 * Represents a machines entity used in the laundry management system.
 */
@Entity
@Table(name = "MACHINE_ENTITY")
public class MachineEntity {

    /**
     * Default constructor for SensorEntity.
     */
    public MachineEntity() {
    }

    /**
     * Constructor with parameters for MachineEntity.
     *
     * @param machineId     The unique identifier for the machine.
     * @param name          The name or identifier of the machine.
     * @param machineStatus The status of the machine (operational, out of order).
     */
   public MachineEntity(long machineId, String name, boolean machineStatus) {
        this.machineId = machineId;
        this.name = name;
        MachineStatus = machineStatus;
    }

    @Id
    @GeneratedValue
    public long machineId;

    @Column(nullable = false, length = 255)
    private String name;

    @Column
    private boolean Available;

    @Column
    private boolean MachineStatus;

    @Column
    private int Progress;

    @Column
    @Enumerated(EnumType.STRING)
    private CycleType cycleType;

    @Column
    private LocalTime StartingTime;

    //@OneToMany(mappedBy = "machine")
    //private Set<ReservationEntity> reservationEntitySet = Set.of();

    //@OneToMany(mappedBy = "machine")
    //private Set<SensorEntity> sensorEntitySet = Set.of();

    /**
     * Get the machine ID
     * @return the machine ID
     */
    public long getMachineId() {
        return machineId;
    }

    /**
     * Get the name of the machine
     * @return the mane of the machine
     */
    public String getName() {
        return name;
    }

    /**
     * Checks the status of the machine
     * @return the status of the machine, if is available is true
     */
    public boolean isAvailable() {
        return Available;
    }

    /**
     * Checks the current status or condition of the machine
     * @return true if is available the condition of the machine
     */
    public boolean isMachineStatus() {
        return MachineStatus;
    }

    /**
     * Get the progress of the machine
     * @return the progress of the machine
     */
    public int getProgress() {
        return Progress;
    }

    /**
     * Get the cycle type of the machine
     * @return the cycle type of the machine
     */
    public CycleType getCycleType() {
        return cycleType;
    }

    /**
     * Get tje start time of the cycle's machine
      * @return the start time of the cycle's machine
     */
    public LocalTime getStartingTime() {
        return StartingTime;
    }

    /**
     * Gets the set of reservations associated with this machine.
     *
     * @return The set of ReservationEntity objects associated with this machine.
     */
    //public Set<ReservationEntity> getReservationEntitySet() {
    //    return reservationEntitySet;
    //}

    /**
     * Gets the set of sensors associated with this machine.
     *
     * @return The set of SensorEntity objects associated with this machine.
     */
    //public Set<SensorEntity> getSensorEntitySet() {
    //    return sensorEntitySet;
    //}

    /**
     * Sets the ID of the machine.
     *
     * @param machineId The ID of the machine to set.
     */
    public void setMachineId(long machineId) {
        this.machineId = machineId;
    }
    /**
     * Sets the name of the machine.
     *
     * @param name The name of the machine to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the availability status of the machine.
     *
     * @param available The availability status of the machine to set.
     */
    public void setAvailable(boolean available) {
        Available = available;
    }

    /**
     * Sets the status of the machine.
     *
     * @param machineStatus The status of the machine to set.
     */
    public void setMachineStatus(boolean machineStatus) {
        MachineStatus = machineStatus;
    }

    /**
     * Sets the progress of the machine's cycle.
     *
     * @param progress The progress of the machine's cycle to set.
     */
    public void setProgress(int progress) {
        Progress = progress;
    }

    /**
     * Sets the cycle type of the machine.
     *
     * @param cycleType The cycle type of the machine to set.
     */
    public void setCycleType(CycleType cycleType) {
        this.cycleType = cycleType;
    }

    /**
     * Sets the starting time of the machine's cycle.
     *
     * @param startingTime The starting time of the machine's cycle to set.
     */
    public void setStartingTime(LocalTime startingTime) {
        StartingTime = startingTime;
    }

    /**
     * Sets the set of reservations associated with this machine.
     *
     * @param reservationEntitySet The set of ReservationEntity objects to associate with this machine.
     */
    //public void setReservationEntitySet(Set<ReservationEntity> reservationEntitySet) {
    //    this.reservationEntitySet = reservationEntitySet;
    //}

    /**
     * Sets the set of sensors associated with this machine.
     *
     * @param sensorEntitySet The set of SensorEntity objects to associate with this machine.
     */
    //    public void setSensorEntitySet(Set<SensorEntity> sensorEntitySet) {
//        this.sensorEntitySet = sensorEntitySet;
//    }
}
