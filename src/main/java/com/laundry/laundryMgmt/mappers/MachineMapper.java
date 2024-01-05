package com.laundry.laundryMgmt.mappers;

import com.laundry.laundryMgmt.models.MachineEntity;
import com.laundry.laundryMgmt.records.MachineRecord;
/**
 * @author  David PETREL
 * A mapper class responsible for converting MachineEntity objects to MachineRecord objects.
 */
public class MachineMapper {
    /**
     * Converts a MachineEntity object to a MachineRecord object.
     * @param machineEntity the MachineEntity object to be converted.     *
     * @return The corresponding MachineRecord object.
     */
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
