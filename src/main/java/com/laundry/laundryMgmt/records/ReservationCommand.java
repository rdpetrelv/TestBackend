package com.laundry.laundryMgmt.records;

import java.time.LocalTime;
import java.util.Date;

public record ReservationCommand(Long Id, Date reservationDate, Date reservationTimeFrom, Date reservationTimeto ) {
}
