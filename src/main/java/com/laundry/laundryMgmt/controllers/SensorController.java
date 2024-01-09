package com.laundry.laundryMgmt.controllers;

import com.laundry.laundryMgmt.dao.SensorDao;
import com.laundry.laundryMgmt.models.SensorEntity;
import com.laundry.laundryMgmt.records.SensorRecord;
import jakarta.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
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
    private static final Logger logger = LogManager.getLogger(SensorController.class);
    public static Marker LogToAPILogFile = MarkerManager.getMarker("LogToAPILogFile");

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
        List<SensorEntity> sensorEntityList = sensorDao.findAll()
                .stream()
                .sorted(Comparator.comparing(SensorEntity::getSensorName))
                .collect(Collectors.toList());
        logger.debug(LogToAPILogFile, "Sensor DAO access verification: List of sensors records created with "+ sensorEntityList.size()+" elements.");
        logger.info(LogToAPILogFile, "Accessed the /api/sensors get request, returned " + sensorEntityList.size() + " element(s).");
        return sensorEntityList;
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
        SensorEntity sensorEntity = new SensorEntity(sensor.sensorType(), sensor.name(), sensor.status(), sensor.measure());
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
     * Updates the measure of a sensor by its ID.
     *
     * @param id The ID of the sensor to update.
     * @return The ResponseEntity containing the updated SensorEntity object if successful, otherwise a bad request.
     */
    @PutMapping(path = "/update/{id}")
    public ResponseEntity<SensorEntity> updateMeasure(@PathVariable Long id, @RequestBody SensorRecord sensor) {
        SensorEntity existingSensor = sensorDao.findById(id).orElse(null);
        SensorEntity sensorEntity = new SensorEntity(sensor.sensorType(), sensor.name(), sensor.status(), sensor.measure());
        if (existingSensor == null) {
            return ResponseEntity.badRequest().build();
        }

        existingSensor.setMeasure(sensorEntity.getMeasure());
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
        if (sensorDao.findById(id).isEmpty()) {
            logger.trace(LogToAPILogFile, "Not sensor found");
            logger.error(LogToAPILogFile, "Accessed the /api/sensors/{id} delete request, status:" +
                    ResponseEntity.badRequest().build().getStatusCode().toString() + " not entity found by the id: " + id);
        } else {
            sensorDao.deleteById(id);
            logger.info(LogToAPILogFile, "Accessed the /api/sensors/"+id+" delete request, status:" +
                    ResponseEntity.ok().build().getStatusCode().toString() + " deleted entity found by the id: " + id);
        }
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
