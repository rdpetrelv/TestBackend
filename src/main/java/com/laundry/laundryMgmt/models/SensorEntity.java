
package com.laundry.laundryMgmt.models;
import jakarta.persistence.*;

/**
 * @author  Fatima GHAOUI
 * Represents a sensor entity used in the laundry management system.
 */
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
    private boolean status;

    @Column
    @Enumerated(EnumType.STRING)
    private SensorType sensorType;

    @ManyToOne
    private MachineEntity machine;

    // Constructor
    /**
     * Default constructor for SensorEntity.
     */
    public SensorEntity() {
    }

    /**
     * Constructor with parameters for SensorEntity.
     *
     * @param sensorType The type of sensor.
     * @param name       The name of the sensor.
     * @param status     The status of the sensor.
     */
    public SensorEntity(SensorType sensorType, String name, boolean status) { // (9).
        this.sensorName = name;
        this.sensorType = sensorType;
        this.status=status;
    }

    /**
     * Constructor with parameters for SensorEntity.
     *
     * @param sensorType The type of sensor.
     * @param name       The name of the sensor.
     * @param status     The status of the sensor.
     * @param measure    the measure ofthe sensor.
     */
    public SensorEntity(SensorType sensorType, String name, boolean status, int measure) { // (9).
        this.sensorName = name;
        this.sensorType = sensorType;
        this.status=status;
        this.Measure = measure;
    }

    /**
     * Gets the sensor ID.
     *
     * @return The sensor ID.
     */
    // Getter and setter for sensorId
    public long getSensorId() {
        return sensorId;
    }

    /**
     * Sets the sensor ID.
     *
     * @param sensorId The sensor ID to set.
     */
    public void setSensorId(long sensorId) {
        this.sensorId = sensorId;
    }

    /**
     * Gets the sensor name.
     *
     * @return The sensor name.
     */
    // Getter et setter pour sensorName
    public String getSensorName() {
        return sensorName;
    }

    /**
     * Sets the sensor name.
     *
     * @param sensorName The sensor name to set.
     */
    public void setSensorName(String sensorName) {
        this.sensorName = sensorName;
    }

    /**
     * Gets the sensor measure.
     *
     * @return The sensor measure.
     */
    // Getter and setter for measure
    public int getMeasure() {
        return Measure;
    }

    /**
     * Sets the sensor measure.
     *
     * @param measure The sensor measure to set.
     */
    public void setMeasure(int measure) {
        this.Measure = measure;
    }

    /**
     * Gets the sensor type.
     *
     * @return The sensor type.
     */
    // Getter and setter for sensorType
    public SensorType getSensorType() {
        return sensorType;
    }

    /**
     * Sets the sensor type.
     *
     * @param sensorType The sensor type to set.
     */
    public void setSensorType(SensorType sensorType) {
        this.sensorType = sensorType;
    }

    /**
     * Gets the associated machine entity.
     *
     * @return The associated machine entity.
     */
    // Getter and setter for machine
    public MachineEntity getMachine() {
        return machine;
    }

    /**
     * Sets the associated machine entity.
     *
     * @param machine The machine entity to set.
     */
    public void setMachine(MachineEntity machine) {
        this.machine = machine;
    }


    /**
     * Gets the status of the sensor.
     *
     * @return The status of the sensor.
     */
    // Getter and setter for status

    public boolean getStatus() {
        return status;
    }

    /**
     * Sets the status of the sensor.
     *
     * @param status The status of the sensor to set.
     */
    public void setStatus(boolean status) {
        this.status = status;
    }

}
