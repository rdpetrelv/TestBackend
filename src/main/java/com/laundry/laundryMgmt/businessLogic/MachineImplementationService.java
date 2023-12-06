package com.laundry.laundryMgmt.businessLogic;

import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class MachineImplementationService implements MachineService {


    @Override
    public void washingTime(int startingDate, int currentDate) {
        int time = currentDate - startingDate;
        System.out.println("Output time is " + time);
    }
}
