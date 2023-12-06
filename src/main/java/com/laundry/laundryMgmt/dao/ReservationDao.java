package com.laundry.laundryMgmt.dao;

import com.laundry.laundryMgmt.models.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationDao extends JpaRepository<ReservationEntity, Long> {

}
