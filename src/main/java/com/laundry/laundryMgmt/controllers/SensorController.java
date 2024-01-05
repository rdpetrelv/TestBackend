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

/**
 * @author  Fatima GHAOUI
 * Controller class handling endpoints related to sensors in the laundry management system.
 */
@CrossOrigin(origins = { "http://localhost:5173" }, maxAge = 3600)
@RestController // (1)
@RequestMapping("/api/sensors") // (2)
@Transactional // (3)
public class SensorController {

    private final SensorDao sensorDao;

    /**
     * Constructor for SensorController.
     *
     * @param sensorDao The Data Access Object for SensorEntity.
     */
    public SensorController(SensorDao sensorDao) {
        this.sensorDao = sensorDao;
    }

    /**
     * Retrieves all sensors sorted by sensor name.
     *
     * @return A list of SensorEntity objects sorted by sensor name.
     */
    @GetMapping
    public List<SensorEntity> findAll() {
        return sensorDao.findAll()
                .stream()
                .sorted(Comparator.comparing(SensorEntity::getSensorName))
                .collect(Collectors.toList());
    }

    /**
     * Retrieves a sensor by its ID.
     *
     * @param id The ID of the sensor to retrieve.
     * @return The SensorEntity object if found, otherwise null.
     */
    @GetMapping(path = "/by-id/{id}")
    public SensorEntity findById(@PathVariable Long id) {
        return sensorDao.findById(id).orElse(null);
    }

    /**
     * Creates a new sensor.
     *
     * @param sensor The SensorRecord object representing the sensor to be created.
     * @return The ResponseEntity containing the created SensorEntity object.
     */
    @PostMapping
    public ResponseEntity<SensorEntity> create(@RequestBody SensorRecord sensor) {
        SensorEntity sensorEntity = new SensorEntity(sensor.sensorType(), sensor.name(), sensor.status());
        SensorEntity saved = sensorDao.save(sensorEntity);
        return ResponseEntity.ok(saved);
    }

    /**
     * Updates the status of a sensor by its ID.
     *
     * @param id The ID of the sensor to update.
     * @return The ResponseEntity containing the updated SensorEntity object if successful, otherwise a bad request.
     */
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

    /**
     * Deletes a sensor by its ID.
     *
     * @param id The ID of the sensor to delete.
     */
    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable Long id) {
        sensorDao.deleteById(id);
    }

    /**
     * Retrieves all available sensor types.
     *
     * @return A list of SensorType objects containing all available sensor types.
     */
    @GetMapping("/types")
    public List<SensorType> findAllSensorTypes() {
        return Arrays.asList(SensorType.values());
    }



}
