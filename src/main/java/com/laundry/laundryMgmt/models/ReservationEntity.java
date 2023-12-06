package com.laundry.laundryMgmt.models;

import jakarta.persistence.*;

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


    @ManyToOne
    private MachineEntity machine;

}
