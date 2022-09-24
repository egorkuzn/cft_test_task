package com.example.cft_test_task.model.entities.technics;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class DisplayEntity extends TechEntityBase{
    @Column
    public float diagonal;
}
