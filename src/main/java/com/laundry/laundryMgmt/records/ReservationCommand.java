package com.laundry.laundryMgmt.records;

import java.time.LocalDate;
import java.time.LocalTime;
/**
 * @author Angela GALEANO
 * @version 1.0
 *
 * Represents commands related to reservations in laundry management.
 * This record be used to handle specific operations associated
 * with reservation management.
 *
 * This class encapsulate methods for creating, updating, deleting or
 * manipulating reservation-related data without storing historical
 * records or additional functionalities present in ReservationRecord.
 *
 * @param reservationId unique identifier for the reservation.
 * @param reservationUser Name of the user who made the reservation.
 * @param reservationDate date of the reservation.
 * @param reservationStartTime start time of reservation.
 * @param reservationEndTime end time of the reservation
 * @param reservationAvailable status of reservation (active/inactive).
 */

public record ReservationCommand(Long reservationId, String reservationUser, LocalDate reservationDate,
                                 LocalTime reservationStartTime, LocalTime reservationEndTime, Boolean reservationAvailable) {
        //These methods designed to interact with other parts of the
        // system to perform specific reservation-related operations
}
