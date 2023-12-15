package com.laundry.laundryMgmt.controllers;

import com.laundry.laundryMgmt.dao.SensorDao;
import com.laundry.laundryMgmt.models.SensorEntity;
import com.laundry.laundryMgmt.records.SensorRecord;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.laundry.laundryMgmt.models.SensorType;


import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.CrossOrigin;


@CrossOrigin(origins = { "http://localhost:5173" }, maxAge = 3600)
@RestController // (1)
@RequestMapping("/api/sensors") // (2)
@Transactional // (3)
public class SensorController {
    private final SensorDao sensorDao;

    public SensorController(SensorDao sensorDao) {
        this.sensorDao = sensorDao;
    }

    @GetMapping
    public List<SensorEntity> findAll() {
        return sensorDao.findAll()
                .stream()
                .sorted(Comparator.comparing(SensorEntity::getSensorName))
                .collect(Collectors.toList());
    }
    @GetMapping(path = "/by-id/{id}")
    public SensorEntity findById(@PathVariable Long id) {
        return sensorDao.findById(id).orElse(null);
    }

    @PostMapping
    public ResponseEntity<SensorEntity> create(@RequestBody SensorRecord sensor) {
        SensorEntity sensorEntity = new SensorEntity(sensor.sensorType(), sensor.name(), sensor.status());
        SensorEntity saved = sensorDao.save(sensorEntity);
        return ResponseEntity.ok(saved);
    }

    @PutMapping(path = "/{id}/status")
    public ResponseEntity<SensorEntity> update(@PathVariable Long id) {
        SensorEntity existingSensor = sensorDao.findById(id).orElse(null);
        if (existingSensor == null) {
            return ResponseEntity.badRequest().build();
        }

        existingSensor.setStatus(!existingSensor.getStatus());
        SensorEntity updatedSensor = sensorDao.save(existingSensor);
        return ResponseEntity.ok(updatedSensor);
    }
    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable Long id) {
        sensorDao.deleteById(id);
    }


    @GetMapping("/types")
    public List<SensorType> findAllSensorTypes() {
        return Arrays.asList(SensorType.values());
    }



}
