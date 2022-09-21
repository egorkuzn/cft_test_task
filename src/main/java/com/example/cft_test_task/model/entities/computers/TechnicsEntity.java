package com.example.cft_test_task.model.entities.computers;

import com.example.cft_test_task.model.enums.tech.TechnicTypes;

import javax.persistence.*;
import java.math.BigDecimal;

// Serial №| Producer | Price | Count of elem
@Entity
@Table(name = "Technics")
public class TechnicsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    public Long serialNumber;

    @Column
    public String producer;

    @Column
    public BigDecimal price;

    @Column
    public Integer countOfElems;
}
