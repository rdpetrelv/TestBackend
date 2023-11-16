package com.laundry.laundryMgmt.businessLogic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MachinesServicesImplementation implements MachinesServices{

    private MachineService machineService;

    @Autowired
    public void MachineServiceImp (MachineService machineService){
        this.machineService =machineService;
    }

    @Override
    public void washingTimes(List<Integer> startingTimes, int currentTime) {
        for (int i :startingTimes ){
            machineService.washingTime( i, currentTime);
        }
    }
}
