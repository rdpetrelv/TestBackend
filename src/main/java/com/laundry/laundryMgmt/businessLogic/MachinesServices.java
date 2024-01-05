package com.laundry.laundryMgmt.businessLogic;

import java.util.List;
/**
 * @author  David PETREL
 * Interface defining machine services related to washing times.
 */
public interface MachinesServices {

    /**
     * Calculates washing times based on starting times and the current time.
     *
     * @param startingTimes List of integers representing starting times for washing cycles.
     * @param currentTime   The current time used for calculations.
     */
    public void washingTimes(List<Integer> startingTimes, int currentTime);
}
