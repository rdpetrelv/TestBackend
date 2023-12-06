package com.laundry.laundryMgmt.mappers;

import com.laundry.laundryMgmt.models.MachineEntity;
import com.laundry.laundryMgmt.records.MachineRecord;

public class MachineMapper {
    public static MachineRecord of (MachineEntity machineEntity){
        return new MachineRecord(
                machineEntity.getMachineId(),
                machineEntity.getName(),
                machineEntity.isAvailable(),
                machineEntity.isMachineStatus(),
                machineEntity.getProgress(),
                machineEntity.getCycleType(),
                machineEntity.getStartingTime()
        );
    }
}
