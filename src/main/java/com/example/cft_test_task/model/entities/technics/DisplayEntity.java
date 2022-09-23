package com.example.cft_test_task.model.entities.technics;

import com.example.cft_test_task.model.entities.TechnicsEntity;

import javax.persistence.*;

@Entity
@Table(name = "Displays")
public class DisplayEntity extends TechEntityBase{
    @Column
    public float diagonal;
}
