package com.laundry.laundryMgmt.models;

import jakarta.persistence.*;

import java.time.LocalTime;
import java.util.Set;

@Entity
@Table(name = "MACHINE")
public class MachineEntity {

    @Id
    @GeneratedValue
    public long Machineid;

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

    @OneToMany
    private Set<ReservationEntity> reservationEntitySet = Set.of();

    @OneToMany
    private Set<SensorEntity> sensorEntitySet = Set.of();



}
