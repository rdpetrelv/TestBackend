package com.laundry.laundryMgmt.models;

import jakarta.persistence.*;

import java.time.LocalTime;
import java.util.Set;

@Entity
@Table(name = "MACHINE_ENTITY")
public class MachineEntity {
    public MachineEntity() {
    }

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

    @OneToMany(mappedBy = "machine")
    private Set<ReservationEntity> reservationEntitySet = Set.of();

    @OneToMany(mappedBy = "machine")
    private Set<SensorEntity> sensorEntitySet = Set.of();


    public long getMachineId() {
        return machineId;
    }

    public String getName() {
        return name;
    }

    public boolean isAvailable() {
        return Available;
    }

    public boolean isMachineStatus() {
        return MachineStatus;
    }

    public int getProgress() {
        return Progress;
    }

    public CycleType getCycleType() {
        return cycleType;
    }

    public LocalTime getStartingTime() {
        return StartingTime;
    }

    public Set<ReservationEntity> getReservationEntitySet() {
        return reservationEntitySet;
    }

    public Set<SensorEntity> getSensorEntitySet() {
        return sensorEntitySet;
    }

    public void setMachineId(long machineId) {
        this.machineId = machineId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAvailable(boolean available) {
        Available = available;
    }

    public void setMachineStatus(boolean machineStatus) {
        MachineStatus = machineStatus;
    }

    public void setProgress(int progress) {
        Progress = progress;
    }

    public void setCycleType(CycleType cycleType) {
        this.cycleType = cycleType;
    }

    public void setStartingTime(LocalTime startingTime) {
        StartingTime = startingTime;
    }

    public void setReservationEntitySet(Set<ReservationEntity> reservationEntitySet) {
        this.reservationEntitySet = reservationEntitySet;
    }

    public void setSensorEntitySet(Set<SensorEntity> sensorEntitySet) {
        this.sensorEntitySet = sensorEntitySet;
    }
}
