package com.laundry.laundryMgmt.controllers;

import com.laundry.laundryMgmt.dao.ReservationDao;
import com.laundry.laundryMgmt.mappers.ReservationMapper;
import com.laundry.laundryMgmt.models.ReservationEntity;
import com.laundry.laundryMgmt.records.ReservationCommand;
import com.laundry.laundryMgmt.records.ReservationRecord;
import jakarta.persistence.Id;
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
    @GetMapping
    public List<ReservationRecord> findAll() {
        return reservationDao.findAll()
                .stream()
                .map(ReservationMapper::of)
                .sorted(Comparator.comparing(ReservationRecord::Id))
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/{id}")
    public ReservationRecord findById(@PathVariable Long id) {
        return reservationDao.findById(id).map(ReservationMapper::of).orElse(null);
    }

    @PostMapping
    public ResponseEntity<ReservationRecord> create(@RequestBody ReservationCommand reservation) {
        ReservationEntity entity = new ReservationEntity(reservation.Id(), reservation.reservationDate() );
        ReservationEntity saved = reservationDao.save(entity);
        return ResponseEntity.ok(ReservationMapper.of(saved));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<ReservationRecord> update(@PathVariable Long id, @RequestBody ReservationCommand reservationCommand) {
        ReservationEntity entity = reservationDao.findById(id).orElse(null);
        if (entity == null) {
            return ResponseEntity.badRequest().build();
        }
        entity.setReservationDate(reservationCommand.reservationDate());
        entity.setReservationTimeFrom(reservationCommand.reservationTimeFrom());

        return ResponseEntity.ok(ReservationMapper.of(entity));
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable Long id) {
        reservationDao.deleteById(id);
    }


}




