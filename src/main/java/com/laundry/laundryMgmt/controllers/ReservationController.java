package com.laundry.laundryMgmt.controllers;

import com.laundry.laundryMgmt.dao.ReservationDao;
import com.laundry.laundryMgmt.mappers.ReservationMapper;
import com.laundry.laundryMgmt.models.ReservationEntity;
import com.laundry.laundryMgmt.records.ReservationCommand;
import com.laundry.laundryMgmt.records.ReservationRecord;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/api/reservation")
@Transactional
public class ReservationController {

    private final ReservationDao reservationDao;

    public ReservationController( ReservationDao reservationDao){
        this.reservationDao = reservationDao;
    }

    @GetMapping  //Busca todas las reservas y las ordena segun la ID
    public List<ReservationRecord> findAll() {
        return reservationDao.findAll()
                .stream()
                .map(ReservationMapper::of)
                .sorted(Comparator.comparing(ReservationRecord::reservationId))
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/{id}") // Busca una reserva especifica por el Id
    public ReservationRecord findById(@PathVariable Long id) {
        return reservationDao.findById(id).map(ReservationMapper::of).orElse(null);
    }

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

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable Long id) {
        reservationDao.deleteById(id);
    }


}




