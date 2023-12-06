package com.laundry.laundryMgmt.mappers;

import com.laundry.laundryMgmt.models.ReservationEntity;
import com.laundry.laundryMgmt.records.ReservationRecord;

public class ReservationMapper {
    public static ReservationRecord of (ReservationEntity reservation){
        return new ReservationRecord(
                reservation.getReservationId(),
                reservation.getReservationDate(),
                reservation.getReservationTimeFrom(),
                reservation.getReservationTimeto()
        );
    }
}