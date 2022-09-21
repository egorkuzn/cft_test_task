package com.example.cft_test_task.model.entities.computers.technics;

import com.example.cft_test_task.model.entities.computers.TechnicsEntity;

import javax.persistence.*;

@Entity
@Table(name = "Displays")
public class DisplayEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    public Long id;

    @OneToOne
    @JoinColumn
    public TechnicsEntity technicsEntity;

    @Column
    public float diagonal;
}
