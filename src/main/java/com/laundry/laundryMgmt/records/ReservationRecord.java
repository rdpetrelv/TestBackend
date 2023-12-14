package com.laundry.laundryMgmt.records;

import java.time.LocalDate;
import java.time.LocalTime;

public record ReservationRecord(Long reservationId, String reservationUser, LocalDate reservationDate,
                                LocalTime reservationStartTime, LocalTime reservationEndTime, Boolean reservationAvailable) {

}
