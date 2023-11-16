package com.laundry.laundryMgmt;

import com.laundry.laundryMgmt.businessLogic.MachineService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LaundryMgmtApplicationConfig {
    @Bean
    public CommandLineRunner washingTimeCommandLine(MachineService machineService){
        return args -> {
            machineService.washingTime(55,65);
        };
    }
}
