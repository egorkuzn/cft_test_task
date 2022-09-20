package com.example.cft_test_task.model.entities.computers;

import javax.persistence.*;
import java.math.BigDecimal;

// Serial №| Producer | Price | Count of elem
@Entity
@Table(name = "technics")
public class TechnicsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    public Long serialNumber;

    @Column
    public String producer;

    @Column
    public BigDecimal price;

    @Column
    public Integer countOfElems;
}
