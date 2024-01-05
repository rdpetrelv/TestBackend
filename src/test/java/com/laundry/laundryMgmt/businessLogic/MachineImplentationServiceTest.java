package com.laundry.laundryMgmt.businessLogic;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;

/**
 * @author David PETREL
 * Test class for validating the behavior of MachineImplementationService.
 */
@ExtendWith(OutputCaptureExtension.class)
public class MachineImplentationServiceTest {

    /**
     * Tests the behavior of the washingTime method in MachineImplementationService.
     *
     * @param output CapturedOutput instance for capturing console output.
     */
    @Test
    public void timeTest(CapturedOutput output){
        // Create an instance of MachineImplementationService
        MachineService machineService = new MachineImplementationService();
        // Test the washingTime method with specific starting and current time
        machineService.washingTime(50, 65);
        // Asserts that the output contains the expected difference in time (15)
        Assertions.assertThat(output.getAll()).contains("15");
    }

}
