package com.example.cft_test_task.model.entities.technics;

import com.example.cft_test_task.model.entities.TechnicsEntity;
import lombok.Data;

import javax.persistence.*;

@Entity
public class TechEntityBase{
    @Id
    @GeneratedValue
    public Long id;
    @OneToOne
    @JoinColumn
    public TechnicsEntity technicsEntity;
}
