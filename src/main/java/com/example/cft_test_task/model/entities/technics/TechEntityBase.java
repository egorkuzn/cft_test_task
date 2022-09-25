package com.example.cft_test_task.model.entities.technics;

import com.example.cft_test_task.model.entities.TechnicsEntity;

import javax.persistence.*;

public class TechEntityBase {
    @Id
    @Column
    @GeneratedValue
    public Long id;

    @JoinColumn
    @OneToOne
    public TechnicsEntity technicsEntity;
}
