package com.laundry.laundryMgmt.records;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * @author Angela GALEANO
 * @version 1.0
 *
 * Represents a reservation record used in laundry management.
 *
 * @param reservationId unique identifier for the reservation.
 * @param reservationUser Name of the user who made the reservation.
 * @param reservationDate date of the reservation.
 * @param reservationStartTime start time of reservation.
 * @param reservationEndTime end time of the reservation
 * @param reservationAvailable status of reservation (active/inactive).
 */
public record ReservationRecord(Long reservationId, String reservationUser, LocalDate reservationDate,
                                LocalTime reservationStartTime, LocalTime reservationEndTime, Boolean reservationAvailable) {

    // Constructor and methods for ReservationRecord
}
