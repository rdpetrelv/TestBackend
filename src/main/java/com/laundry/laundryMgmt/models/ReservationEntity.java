package com.laundry.laundryMgmt.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.LocalTime;
@Entity
public class ReservationEntity {

    @Id
    @GeneratedValue
    public long reservationId;

    @Column
    private LocalTime ReservationTimeFrom;

    @Column
    private LocalTime ReservationTimeto;

}
