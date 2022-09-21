package com.example.cft_test_task.model.entities.computers.technics;

import com.example.cft_test_task.model.enums.tech.details.laptop.LaptopDiagonal;
import com.example.cft_test_task.model.entities.computers.TechnicsEntity;

import javax.persistence.*;

@Entity
@Table(name = "Laptops")
public class LaptopEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    public Long id;

    @OneToOne
    @JoinColumn
    public TechnicsEntity technicsEntity;

    @Enumerated(EnumType.ORDINAL)
    @Column
    public LaptopDiagonal diagonal;
}
