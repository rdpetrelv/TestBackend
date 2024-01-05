package com.laundry.laundryMgmt.models;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * @author  Angela GALEANO
 * Represents a reservation entity used in the laundry management system.
 */
@Entity
@Table(name = "reservation")
public class ReservationEntity {

    @Id
    @GeneratedValue
    public long reservationId;

    @Column(name = "reservation_available")
    private Boolean reservationAvailable;

    @Column(nullable = false, length = 255, name = "reservation_user")
    private String reservationUser;

    @Column(name = "reservation_date")
    private LocalDate reservationDate;

    @Column(name = "reservationStart")
    @JsonFormat(pattern="HH:mm:ss")
    private LocalTime reservationStartTime;

    @Column(name = "reservationEnd")
    @JsonFormat(pattern="HH:mm:ss")
    private LocalTime reservationEndTime;

    @ManyToOne
    private MachineEntity machine;

    /**
     * Constructor with parameters for ReservationEntity.
     *
     * @param reservationUser Name of the user who made the reservation.
     * @param reservationDate date of the reservation.
     * @param reservationStartTime start time of reservation.
     * @param reservationEndTime end time of the reservation
     */
    public ReservationEntity(String reservationUser, LocalDate reservationDate, LocalTime reservationStartTime, LocalTime reservationEndTime) {
        this.reservationUser = reservationUser;
        this.reservationDate = reservationDate;
        this.reservationStartTime = reservationStartTime;
        this.reservationEndTime = reservationEndTime;
    }

    /**
     * Default constructor for SensorEntity.
     */
    public ReservationEntity() {
        // Constructor vacio requerido
    }

    /**
     * Get the reservation ID
     * @return the reservation ID
     */
    public long getReservationId() {
        return reservationId;
    }

    /**
     * Set the reservation ID
     * @param reservationId The reservation ID to set.
     */
    public void setReservationId(long reservationId) {
        this.reservationId = reservationId;
    }

    /**
     * Get the reservation available (status)
     * @return the status of the reservation
     */
    public Boolean getReservationAvailable() {
        return reservationAvailable;
    }

    /**
     * Set the reservation status
     * @param reservationAvailable the reservation status to set.
     */
    public void setReservationAvailable(Boolean reservationAvailable) {
        this.reservationAvailable = reservationAvailable;
    }

    /**
     * Get the user's name of the reservation
     * @return user's name
     */
    public String getReservationUser() {
        return reservationUser;
    }

    /**
     * Set the user's name
     * @param reservationUser the user's name to set.
     */
    public void setReservationUser(String reservationUser) {
        this.reservationUser = reservationUser;
    }

    /**
     * Get the date of the reservation
     * @return date of the reservation
     */
    public LocalDate getReservationDate() {
        return reservationDate;
    }

    /**
     * Set the date of the reservation
     * @param reservationDate date of the reservation to set
     */
    public void setReservationDate(LocalDate reservationDate) {
        this.reservationDate = reservationDate;
    }

    /**
     * Get start time of the reservation
     * @return start time of the reservation
     */
    public LocalTime getReservationStartTime() {
        return reservationStartTime;
    }

    /**
     * Set the start time of the reservation
     * @param reservationStartTime the start time of the reservation to set.
     */
    public void setReservationStartTime(LocalTime reservationStartTime) {
        this.reservationStartTime = reservationStartTime;
    }

    /**
     * Get the end time of the reservation
     * @return the end time of the reservation
     */
    public LocalTime getReservationEndTime() {
        return reservationEndTime;
    }

    /**
     * Set the end time of the reservation
     * @param reservationEndTime the end time of the reservation to set
     */
    public void setReservationEndTime(LocalTime reservationEndTime) {
        this.reservationEndTime = reservationEndTime;
    }

    /**
     * Gets the associated machine entity.
     *
     * @return The associated machine entity.
     */
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
}