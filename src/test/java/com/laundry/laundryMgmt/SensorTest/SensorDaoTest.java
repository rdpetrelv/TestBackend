package com.laundry.laundryMgmt.SensorTest;

import com.laundry.laundryMgmt.dao.SensorDao;
import com.laundry.laundryMgmt.models.SensorEntity;
import com.laundry.laundryMgmt.models.SensorType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
/**
 * @author  Fatima GHAOUI
 * Test class for the SensorDao functionalities.
 */
@DataJpaTest
public class SensorDaoTest {
    @Autowired
    private SensorDao sensorDao;
    /**
     * Tests the functionality to find a sensor by ID by the data access object.
     * verifies that specific details of the retrieved machine match the expected values.
     */
    @Test
    public void shouldFindAnySensorbyId(){
        SensorEntity sensor = sensorDao.getReferenceById(-1L);
        Assertions.assertThat(sensor.getSensorName()).isEqualTo("TEST SENSOR");
        Assertions.assertThat(sensor.getSensorType().toString()).isEqualTo("TEMPERATURE");
    }
}
