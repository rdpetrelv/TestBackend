package com.laundry.laundryMgmt.dao;

import com.laundry.laundryMgmt.models.SensorEntity;
import com.laundry.laundryMgmt.models.SensorType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
/**
 * @author  Fatima GHAOUI
 * A DAO (Data Access Object) interface for SensorEntity objects.
 * Extends JpaRepository to provide basic CRUD operations for SensorEntity.
 */
public interface SensorDao extends JpaRepository<SensorEntity, Long> {


}
