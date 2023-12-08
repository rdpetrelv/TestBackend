package com.laundry.laundryMgmt.dao;

import com.laundry.laundryMgmt.models.SensorEntity;
import com.laundry.laundryMgmt.models.SensorType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SensorDao extends JpaRepository<SensorEntity, Long> {


}
