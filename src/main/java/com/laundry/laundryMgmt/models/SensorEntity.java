package com.laundry.laundryMgmt.models;

import jakarta.persistence.*;

@Entity
public class SensorEntity {

    @Id
    @GeneratedValue
    public long sensorId;

    @Column
    public String sensorName;

    @Column
    private int Measure;

    @Column
    @Enumerated(EnumType.STRING)
    private SensorType sensorType;

    @ManyToOne
    private MachineEntity machine;

    // Constructor
    public SensorEntity() {
    }
    public SensorEntity(SensorType sensorType, String name) { // (9).
        this.sensorName = name;
        this.sensorType = sensorType;
    }

    // Getter and setter for sensorId
    public long getSensorId() {
        return sensorId;
    }

    public void setSensorId(long sensorId) {
        this.sensorId = sensorId;
    }

    // Getter et setter pour sensorName
    public String getSensorName() {
        return sensorName;
    }

    public void setSensorName(String sensorName) {
        this.sensorName = sensorName;
    }


    // Getter and setter for measure
    public int getMeasure() {
        return Measure;
    }

    public void setMeasure(int measure) {
        this.Measure = measure;
    }

    // Getter and setter for sensorType
    public SensorType getSensorType() {
        return sensorType;
    }

    public void setSensorType(SensorType sensorType) {
        this.sensorType = sensorType;
    }

    // Getter and setter for machine
    public MachineEntity getMachine() {
        return machine;
    }

    public void setMachine(MachineEntity machine) {
        this.machine = machine;
    }

}
