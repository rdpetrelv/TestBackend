package com.laundry.laundryMgmt.records;

import java.time.LocalTime;
import java.util.Date;

public record ReservationRecord(Long Id, Date reservationDate, Date ReservationTimeFrom,Date ReservationTimeto ) {

}
