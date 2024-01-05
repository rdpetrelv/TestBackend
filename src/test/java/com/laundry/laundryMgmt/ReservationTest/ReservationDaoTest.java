package com.laundry.laundryMgmt.ReservationTest;

import com.laundry.laundryMgmt.dao.ReservationDao;
import com.laundry.laundryMgmt.models.ReservationEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

/**
 * @author  Angela GALEANO
 * Test class for the ReservationDao functionalities.
 */
@DataJpaTest
public class ReservationDaoTest {

    @Autowired
    private ReservationDao reservationDao;

    /**
     * Tests the functionality to find a reservation by ID.
     * verifies that specific details of the retrieved reservation match the expected values.
     */
    @Test
    public void shouldFindAnyReservationById(){
        // Retrieve a reservation using the Dao method
        ReservationEntity reservation = reservationDao.getReferenceById(-3L);
        // Assert specific details of the retrieved reservation
        Assertions.assertThat(reservation.getReservationUser()).isEqualTo("TEST RESERVATION");
        Assertions.assertThat(reservation.getReservationAvailable()).isEqualTo(false);
    }
}