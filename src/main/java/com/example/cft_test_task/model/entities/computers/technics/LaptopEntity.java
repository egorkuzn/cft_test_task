package com.example.cft_test_task.model.entities.computers.technics;

import com.example.cft_test_task.model.details.tech.laptop.LaptopDiagonal;
import com.example.cft_test_task.model.entities.computers.TechnicsEntity;

import javax.persistence.*;

@Entity
@Table(name = "laptops")
public class LaptopEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    @OneToOne
    @JoinColumn
    public TechnicsEntity technicsEntity;

    @Column
    public LaptopDiagonal diagonal;
}
