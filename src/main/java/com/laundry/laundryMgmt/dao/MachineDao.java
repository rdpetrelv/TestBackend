
package com.laundry.laundryMgmt.dao;

import com.laundry.laundryMgmt.models.MachineEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MachineDao extends JpaRepository<MachineEntity, Long> {



}

