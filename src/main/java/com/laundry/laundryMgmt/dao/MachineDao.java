
package com.laundry.laundryMgmt.dao;

import com.laundry.laundryMgmt.models.MachineEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
/**
 * @author  David PETREL
 * A DAO (Data Access Object) interface for MachineEntity objects.
 * Extends JpaRepository to provide basic CRUD operations for MachineEntity.
 */
public interface MachineDao extends JpaRepository<MachineEntity, Long> {



}

