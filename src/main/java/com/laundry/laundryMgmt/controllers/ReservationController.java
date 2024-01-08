package com.laundry.laundryMgmt.controllers;

import com.laundry.laundryMgmt.dao.ReservationDao;
import com.laundry.laundryMgmt.mappers.ReservationMapper;
import com.laundry.laundryMgmt.models.ReservationEntity;
import com.laundry.laundryMgmt.records.ReservationCommand;
import com.laundry.laundryMgmt.records.ReservationRecord;
import jakarta.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * @author  Angela GALEANO
 * Controller class handling endpoints related to reservation in the laundry management system.
 */
@CrossOrigin(origins = { "http://localhost:5173" }, maxAge = 3600)
@RestController
@RequestMapping("/api/reservation")
@Transactional
public class ReservationController {

    private final ReservationDao reservationDao;

    private static final Logger logger = LogManager.getLogger(ReservationController.class);
    public static Marker LogToAPILogFile = MarkerManager.getMarker("LogToAPILogFile");

    /**
     * Constructor for ReservationController.
     * @param reservationDao The Data Access Object for ReservationEntity
     */
    public ReservationController( ReservationDao reservationDao){
        this.reservationDao = reservationDao;
    }

    /**
     * Retrieves all reservation sorted by reservation ID
     * @return list of ReservationEntity objects sorted by reservation ID
     */
    @GetMapping  //Busca todas las reservas y las ordena segun la ID
    public List<ReservationRecord> findAll() {
        List<ReservationRecord> recordList = reservationDao.findAll()
                .stream()
                .map(ReservationMapper::of)
                .sorted(Comparator.comparing(ReservationRecord::reservationId))
                .collect(Collectors.toList());
        logger.debug(LogToAPILogFile, "Record DAO access verification: List of reservation records created with "+ recordList.size()+" elements.");
        logger.info(LogToAPILogFile, "Accessed the /api/reservation get request, returned " + recordList.size() + " element(s).");
        return recordList;
    }

    /**
     * Retrieves a reservation by its ID.
     * @param id The ID of the reservation to retrieve.
     * @return the ReservationEntity object if found, otherwise null.
     */
    @GetMapping(path = "/{id}") // Busca una reserva especifica por el Id
    public ReservationRecord findById(@PathVariable Long id) {
        ReservationRecord reservationfind = reservationDao.findById(id).map(ReservationMapper::of).orElse(null);
        logger.debug(LogToAPILogFile, "Machine DAO access verification: reservation records created with "+ reservationfind.reservationId()+".");
        if (reservationfind == null) {
            logger.trace(LogToAPILogFile, "Not reservation found");
            logger.error(LogToAPILogFile, "Accessed the /api/reservation/{id} get request, but no element found.");
        } else {
            logger.info(LogToAPILogFile, "Accessed the /api/reservation/" + id + " get request, returned element id " + reservationfind.reservationId());
        }
        return reservationfind;
    }

    /**
     * Create a new reservation
     * @param reservation the ReservationRecord object representing the reservation to be created
     * @return The ResponseEntity containing the created ReservationEntity object.
     */
    @PostMapping //Crea la reserva
    public ResponseEntity<ReservationRecord> create(@RequestBody ReservationCommand reservation) {
        ReservationEntity entity = new ReservationEntity(reservation.reservationUser(), reservation.reservationDate(),
                                    reservation.reservationStartTime(), reservation.reservationEndTime());
        ReservationEntity saved = reservationDao.save(entity);

        if (reservation.reservationUser() != null &&
                reservation.reservationDate() != null &&
                reservation.reservationStartTime() != null &&
                reservation.reservationEndTime() != null) {
            // Establecer reservationAvailable como true
            entity.setReservationAvailable(true);
        }
        else
            entity.setReservationAvailable(false);

        return ResponseEntity.ok(ReservationMapper.of(saved));
    }

    /**
     * Updates a reservation by its ID.
     *
     * @param id                The ID of the reservation to update.
     * @param reservationCommand The ReservationCommand object containing updated reservation details.
     * @return A ResponseEntity containing the updated ReservationRecord if successful, otherwise a bad request.
     */
    @PutMapping(path = "/{id}") // Editar
    public ResponseEntity<ReservationRecord> update(@PathVariable Long id, @RequestBody ReservationCommand reservationCommand) {
        ReservationEntity entity = reservationDao.findById(id).orElse(null);
        if (entity == null) {
            return ResponseEntity.badRequest().build();
        }
        entity.setReservationDate(reservationCommand.reservationDate());
        entity.setReservationStartTime(reservationCommand.reservationStartTime());
        entity.setReservationEndTime(reservationCommand.reservationEndTime());

        return ResponseEntity.ok(ReservationMapper.of(entity));
    }

    /**
     * Deletes a reservation by its ID.
     *
     * @param id The ID of the reservation to delete.
     */
    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable Long id) {
        if (reservationDao.findById(id).isEmpty()) {
            logger.trace(LogToAPILogFile, "Not reservation found");
            logger.error(LogToAPILogFile, "Accessed the /api/reservation/{id} delete request, status:" +
                    ResponseEntity.badRequest().build().getStatusCode().toString() + " not entity found by the id: " + id);
        } else {
            reservationDao.deleteById(id);
            logger.info(LogToAPILogFile, "Accessed the /api/reservation/"+id+" delete request, status:" +
                    ResponseEntity.ok().build().getStatusCode().toString() + " deleted entity found by the id: " + id);
        }
        reservationDao.deleteById(id);
    }
}




