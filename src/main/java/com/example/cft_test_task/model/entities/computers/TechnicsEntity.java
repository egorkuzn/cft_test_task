package com.example.cft_test_task.model.entities.computers;

import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import java.math.BigDecimal;

// Serial №| Producer | Price | Count of elem
@Entity
public class TechnicsEntity {
    @Id
    Long serialNumber;
    String producer;
    BigDecimal price;
    Integer countOfElems;
}
