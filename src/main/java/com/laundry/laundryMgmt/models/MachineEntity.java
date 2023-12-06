package com.laundry.laundryMgmt.models;

import jakarta.persistence.*;

import java.time.LocalTime;
import java.util.Set;

@Entity
@Table(name = "MACHINE_ENTITY")
public class MachineEntity {

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



}
