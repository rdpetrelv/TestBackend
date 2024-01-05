package com.laundry.laundryMgmt.businessLogic;

import java.util.Date;
/**
 * @author David PETREL
 * Interface defining machine-related services.
 */
public interface MachineService {

    /**
     * Calculates washing time based on starting date and current date.
     *
     * @param startingDate The starting date for the washing cycle.
     * @param currentDate  The current date used for calculations.
     */
    public void washingTime (int startingDate, int currentDate);
}
