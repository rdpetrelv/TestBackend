package com.laundry.laundryMgmt.dao;

import com.laundry.laundryMgmt.models.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * @author  Angela GALEANO
 * A DAO (Data Access Object) interface for ReservationEntity objects.
 * Extends JpaRepository to provide basic CRUD operations for ReservationEntity.
 */
public interface ReservationDao extends JpaRepository<ReservationEntity, Long> {

}
