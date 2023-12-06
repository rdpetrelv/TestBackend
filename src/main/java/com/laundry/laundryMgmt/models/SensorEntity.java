package com.laundry.laundryMgmt.models;

import jakarta.persistence.*;

@Entity
public class SensorEntity {

    @Id
    @GeneratedValue
    public long sensorId;

    @Column
    private int Measure;

    @Column
    @Enumerated(EnumType.STRING)
    private SensorType sensorType;


    @ManyToOne
    private MachineEntity machine;

}
