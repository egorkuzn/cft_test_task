package com.example.cft_test_task.model.entities.technics;

import com.example.cft_test_task.model.entities.TechnicsEntity;

import javax.persistence.*;

@Entity
@Table
public class TechEntityBase{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    public Long id;

    @OneToOne
    @JoinColumn
    public TechnicsEntity technicsEntity;
}
