package com.laundry.laundryMgmt.businessLogic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * @author  David PETREL
 * Service implementation class handling machine-related business logic.
 */
@Service
public class MachinesServicesImplementation implements MachinesServices {

    private MachineService machineService;

    /**
     * Sets the MachineService for this implementation.
     *
     * @param machineService The MachineService instance to be used for machine operations.
     */
    @Autowired
    public void setMachineService(MachineService machineService) {
        this.machineService = machineService;
    }

    /**
     * Calculates washing times based on starting times and the current time.
     *
     * @param startingTimes List of integers representing starting times for washing cycles.
     * @param currentTime   The current time used for calculations.
     */
    @Override
    public void washingTimes(List<Integer> startingTimes, int currentTime) {
        for (int i : startingTimes) {
            machineService.washingTime(i, currentTime);
        }
    }
}
