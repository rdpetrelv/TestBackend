package com.laundry.laundryMgmt.controllers;

import com.laundry.laundryMgmt.dao.MachineDao;
import com.laundry.laundryMgmt.mappers.MachineMapper;
import com.laundry.laundryMgmt.models.MachineEntity;
import com.laundry.laundryMgmt.records.MachineCommand;
import com.laundry.laundryMgmt.records.MachineRecord;
import jakarta.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@CrossOrigin(origins = {"http://localhost:5173"}, maxAge = 3600)
@RestController
@RequestMapping("/api/machines")
@Transactional

public class MachineController {
    private final MachineDao machineDao;
    private static final Logger logger = LogManager.getLogger(MachineController.class);
    public static Marker LogToMachinesLogFile = MarkerManager.getMarker("LogToMachinesLogFile");

    public MachineController(MachineDao machineDao) {
        this.machineDao = machineDao;
    }

    @GetMapping
    public List<MachineRecord> findAll() {
        List<MachineRecord> machineList = machineDao.findAll()
                .stream()
                .map(MachineMapper::of)
                .sorted(Comparator.comparing(MachineRecord::name))
                .collect(Collectors.toList());
        logger.info(LogToMachinesLogFile, "Accessed the /api/machines get request, returned " + machineList.size() + " element(s).");
        return machineList;
    }

    @GetMapping(path = "/{id}")
    public MachineRecord findById(@PathVariable Long id) {
        MachineRecord machineFound = machineDao.findById(id).map(MachineMapper::of).orElse(null);
        if (machineFound == null) {
            logger.info(LogToMachinesLogFile, "Accessed the /api/machines/{id} get request, but no element found.");
        } else {
            logger.info(LogToMachinesLogFile, "Accessed the /api/machines/" + id + " get request, returned element name " + machineFound.name());
        }
        return machineFound;
    }

    @PostMapping
    public ResponseEntity<MachineRecord> create(@RequestBody MachineCommand machineCommand) {
        MachineEntity machineEntity = new MachineEntity(machineCommand.machineId(), machineCommand.name(), machineCommand.machineStatus());
        MachineEntity machineSaved = machineDao.save(machineEntity);
        ResponseEntity<MachineRecord> response = ResponseEntity.ok(MachineMapper.of(machineSaved));
        logger.info(LogToMachinesLogFile, "Accessed the /api/machines post request, status:" +
                response.getStatusCode().toString() +
                ". Element "+ machineEntity.getName() + " created." );
        return response;
    }

//    @PutMapping(path = "/{id}")
//    public ResponseEntity<MachineRecord> updateStatus(@PathVariable Long id, @RequestBody MachineCommand machineCommand) {
//        MachineEntity machineEntity =machineDao.findById(id).orElse(null);
//        if (machineEntity == null){
//            return ResponseEntity.badRequest().build();
//        }
//        machineEntity.setMachineStatus(machineCommand.machineStatus());
//        return ResponseEntity.ok(MachineMapper.of(machineEntity));
//    }

    @PutMapping(path = "/updateAvailable/{id}")
    public ResponseEntity<MachineRecord> updateStatus(@PathVariable Long id) {
        MachineEntity machineEntity = machineDao.findById(id).orElse(null);
        ResponseEntity<MachineRecord> response;
        if (machineEntity == null) {
            response = ResponseEntity.badRequest().build();
            logger.info(LogToMachinesLogFile, "Accessed the /api/machines/updateAvailable/{id} put request, status:" +
                    response.getStatusCode().toString() + ". Not entity found by the id: " + id);
            return response;
        }
        machineEntity.setAvailable(!machineEntity.isAvailable());
        response = ResponseEntity.ok(MachineMapper.of(machineEntity));
        logger.info(LogToMachinesLogFile, "Accessed the /api/machines/updateAvailable/" + id +" put request, status:" +
                response.getStatusCode().toString() + ". Updated availability of entity found by the id: " + id);
        return response;
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable Long id) {
        if (machineDao.findById(id).isEmpty()) {
            logger.info(LogToMachinesLogFile, "Accessed the /api/machines/{id} delete request, status:" +
                    ResponseEntity.badRequest().build().getStatusCode().toString() + " not entity found by the id: " + id);
        } else {
            machineDao.deleteById(id);
        logger.info(LogToMachinesLogFile, "Accessed the /api/machines/"+id+" delete request, status:" +
                ResponseEntity.ok().build().getStatusCode().toString() + " deleted entity found by the id: " + id);
        }
    }

}

