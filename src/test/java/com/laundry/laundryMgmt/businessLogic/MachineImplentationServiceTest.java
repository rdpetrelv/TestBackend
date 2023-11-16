package com.laundry.laundryMgmt.businessLogic;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;

@ExtendWith(OutputCaptureExtension.class)
public class MachineImplentationServiceTest {

    @Test
    public void timeTest(CapturedOutput output){
        MachineService machineService = new MachineImplementationService();
        machineService.washingTime(50, 65);
        Assertions.assertThat(output.getAll()).contains("15");
    }

}
