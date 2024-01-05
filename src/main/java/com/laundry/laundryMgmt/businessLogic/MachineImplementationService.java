package com.laundry.laundryMgmt.businessLogic;

import org.springframework.stereotype.Service;

import java.util.Date;
/**
 * @author  David PETREL
 * Service implementation class for machine operations.
 */
@Service
public class MachineImplementationService implements MachineService {

    /**
     * Calculates washing time based on starting date and current date.
     *
     * @param startingDate The starting date for the washing cycle.
     * @param currentDate  The current date used for calculations.
     */
    @Override
    public void washingTime(int startingDate, int currentDate) {
        int time = currentDate - startingDate;
        System.out.println("Output time is " + time);
    }
}
