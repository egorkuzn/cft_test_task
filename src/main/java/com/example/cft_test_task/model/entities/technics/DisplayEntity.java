package com.example.cft_test_task.model.entities.technics;

import com.example.cft_test_task.model.entities.TechnicsEntity;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table
@Data
public class DisplayEntity implements TechEntityBase {
    @Id
    @Column
    @GeneratedValue
    private Long id;

    @JoinColumn
    @OneToOne
    private TechnicsEntity technicsEntity;

    @Column
    private float diagonal;
}
