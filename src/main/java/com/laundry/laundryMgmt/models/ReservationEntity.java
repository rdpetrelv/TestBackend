package com.laundry.laundryMgmt.models;

import jakarta.persistence.*;

import java.time.LocalTime;
import java.util.Date;

@Entity
@Table(name = "reservation")
public class ReservationEntity {

    @Id
    @GeneratedValue
    public long reservationId;

    @Column(name = "reservation_date")
    private Date reservationDate;

    @Column(name = "reservationStart_hour")
    private Date ReservationTimeFrom;

    @Column(name = "reservationEnd_hour")
    private Date ReservationTimeto;

    @ManyToOne
    private MachineEntity machine;

    public ReservationEntity(long reservationId, Date reservationDate) {
        this.reservationId = reservationId;
        this.reservationDate = reservationDate;
    }

    public ReservationEntity() {
    }

    public long getReservationId() {
        return reservationId;
    }

    public Date getReservationDate() {
        return reservationDate;
    }

    public Date getReservationTimeFrom() {
        return ReservationTimeFrom;
    }

    public Date getReservationTimeto() {
        return ReservationTimeto;
    }

    public MachineEntity getMachine() {
        return machine;
    }

    public void setReservationId(long reservationId) {
        this.reservationId = reservationId;
    }

    public void setReservationDate(Date reservationDate) {
        this.reservationDate = reservationDate;
    }

    public void setReservationTimeFrom(Date reservationTimeFrom) {
        ReservationTimeFrom = reservationTimeFrom;
    }

    public void setReservationTimeto(Date reservationTimeto) {
        ReservationTimeto = reservationTimeto;
    }

    public void setMachine(MachineEntity machine) {
        this.machine = machine;
    }
}
