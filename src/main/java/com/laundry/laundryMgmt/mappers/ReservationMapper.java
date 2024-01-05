package com.laundry.laundryMgmt.mappers;

import com.laundry.laundryMgmt.models.ReservationEntity;
import com.laundry.laundryMgmt.records.ReservationRecord;
/**
 * @author  Angela GALEANO
 * A mapper class responsible for converting SensorEntity objects to SensorRecord objects.
 */
public class ReservationMapper {
    /**
     * Converts a ReservationEntity object to a ReservationRecord object.
     *
     * @param reservation the ReservationEntity object to be converted.
     * @return The corresponding ResrvationRecord object.
     */
    public static ReservationRecord of (ReservationEntity reservation){
        return new ReservationRecord(
                        reservation.getReservationId(),
                        reservation.getReservationUser(),
                        reservation.getReservationDate(),
                        reservation.getReservationStartTime(),
                        reservation.getReservationEndTime(),
                        reservation.getReservationAvailable()
                );
    }
}