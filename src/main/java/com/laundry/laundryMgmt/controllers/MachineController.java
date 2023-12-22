package com.laundry.laundryMgmt.controllers;

import com.laundry.laundryMgmt.dao.MachineDao;
import com.laundry.laundryMgmt.mappers.MachineMapper;
import com.laundry.laundryMgmt.models.MachineEntity;
import com.laundry.laundryMgmt.records.MachineCommand;
import com.laundry.laundryMgmt.records.MachineRecord;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.CrossOrigin;


@CrossOrigin(origins = { "http://localhost:5173" }, maxAge = 3600)
@RestController
@RequestMapping("/api/machines")
@Transactional

public class MachineController {
    private final MachineDao machineDao;

    public MachineController(MachineDao machineDao) {
        this.machineDao = machineDao;
    }

    @GetMapping
    public List<MachineRecord> findAll() {
        return machineDao.findAll()
                .stream()
                .map(MachineMapper::of)
                .sorted(Comparator.comparing(MachineRecord::name))
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/{id}")
    public MachineRecord findById(@PathVariable Long id) {
        return machineDao.findById(id).map(MachineMapper::of).orElse(null);
    }

    @PostMapping
    public ResponseEntity<MachineRecord> create(@RequestBody MachineCommand machineCommand){
        MachineEntity machineEntity =new MachineEntity(machineCommand.machineId(), machineCommand.name(), machineCommand.machineStatus());
        MachineEntity machineSaved = machineDao.save(machineEntity);
        return ResponseEntity.ok(MachineMapper.of(machineSaved));
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
        MachineEntity machineEntity =machineDao.findById(id).orElse(null);
        if (machineEntity == null){
            return ResponseEntity.badRequest().build();
        }
        machineEntity.setAvailable(!machineEntity.isAvailable());
        return ResponseEntity.ok(MachineMapper.of(machineEntity));
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable Long id){
        machineDao.deleteById(id);
    }



}

