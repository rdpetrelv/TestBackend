package com.laundry.laundryMgmt.models;

import jakarta.persistence.*;

@Entity
@Table(name = "MACHINE")
public class MachineEntity {

    @Id
    @GeneratedValue
    public long id;

    @Column(nullable = false, length = 255)
    private String name;

}
