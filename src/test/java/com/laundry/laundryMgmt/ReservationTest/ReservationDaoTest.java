package com.laundry.laundryMgmt.ReservationTest;

import com.laundry.laundryMgmt.dao.ReservationDao;
import com.laundry.laundryMgmt.models.ReservationEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class ReservationDaoTest {

    @Autowired
    private ReservationDao reservationDao;

    @Test
    public void shouldFindAnyReservationById(){
        ReservationEntity reservation = reservationDao.getReferenceById(-3L);
        Assertions.assertThat(reservation.getReservationUser()).isEqualTo("TEST RESERVATION");
        Assertions.assertThat(reservation.getReservationAvailable()).isEqualTo(false);
    }
}