package com.laundry.laundryMgmt.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

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

    public ReservationEntity(String reservationUser, LocalDate reservationDate, LocalTime reservationStartTime, LocalTime reservationEndTime) {
        this.reservationUser = reservationUser;
        this.reservationDate = reservationDate;
        this.reservationStartTime = reservationStartTime;
        this.reservationEndTime = reservationEndTime;
    }

    public ReservationEntity() {
        // Constructor vacio requerido
    }


    public long getReservationId() {
        return reservationId;
    }

    public void setReservationId(long reservationId) {
        this.reservationId = reservationId;
    }

    public Boolean getReservationAvailable() {
        return reservationAvailable;
    }

    public void setReservationAvailable(Boolean reservationAvailable) {
        this.reservationAvailable = reservationAvailable;
    }

    public String getReservationUser() {
        return reservationUser;
    }

    public void setReservationUser(String reservationUser) {
        this.reservationUser = reservationUser;
    }

    public LocalDate getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(LocalDate reservationDate) {
        this.reservationDate = reservationDate;
    }

    public LocalTime getReservationStartTime() {
        return reservationStartTime;
    }

    public void setReservationStartTime(LocalTime reservationStartTime) {
        this.reservationStartTime = reservationStartTime;
    }

    public LocalTime getReservationEndTime() {
        return reservationEndTime;
    }

    public void setReservationEndTime(LocalTime reservationEndTime) {
        this.reservationEndTime = reservationEndTime;
    }

    public MachineEntity getMachine() {
        return machine;
    }

    public void setMachine(MachineEntity machine) {
        this.machine = machine;
    }
}