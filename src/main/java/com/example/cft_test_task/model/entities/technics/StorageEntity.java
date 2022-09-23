package com.example.cft_test_task.model.entities.technics;

import com.example.cft_test_task.model.entities.TechnicsEntity;

import javax.persistence.*;

@Entity
@Table(name = "Storages")
public class StorageEntity extends TechEntityBase{
    @Column
    public int volume;
}
