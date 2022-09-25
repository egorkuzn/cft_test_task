package com.example.cft_test_task.model.entities.technics;

import com.example.cft_test_task.model.entities.TechnicsEntity;
import com.example.cft_test_task.model.enums.tech.details.laptop.LaptopDiagonal;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table
@Data
public class LaptopEntity implements TechEntityBase {
    @Id
    @Column
    @GeneratedValue
    private Long id;

    @JoinColumn
    @OneToOne
    private TechnicsEntity technicsEntity;
    @Enumerated(EnumType.ORDINAL)
    @Column
    private LaptopDiagonal diagonal;
}
