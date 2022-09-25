package com.example.cft_test_task.model.entities.technics;

import com.example.cft_test_task.model.enums.tech.details.laptop.LaptopDiagonal;

import javax.persistence.*;

@Entity
@Table
public class LaptopEntity extends TechEntityBase{
    @Enumerated(EnumType.ORDINAL)
    @Column
    public LaptopDiagonal diagonal;
}
