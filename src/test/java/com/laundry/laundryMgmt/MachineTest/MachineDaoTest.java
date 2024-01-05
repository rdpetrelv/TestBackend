package com.laundry.laundryMgmt.MachineTest;

import com.laundry.laundryMgmt.dao.MachineDao;
import com.laundry.laundryMgmt.models.MachineEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
/**
 * @author  David PETREL
 * Test class for the MachineDao functionalities.
 */
@DataJpaTest
public class MachineDaoTest {
    @Autowired
    private MachineDao machineDao;

    /**
     * Tests the functionality to find a machine by ID by the data access object.
     * verifies that specific details of the retrieved machine match the expected values.
     */
    @Test
    public void shouldFindAnyMachineByID(){
        MachineEntity machineEntity = machineDao.getReferenceById(-2L);
        Assertions.assertThat(machineEntity.getName()).isEqualTo("TEST MACHINE");
    }
}
