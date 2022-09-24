package com.example.cft_test_task.model.entities.technics;

import com.example.cft_test_task.model.enums.tech.details.desktop.PCFormFactor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
public class DesktopEntity extends TechEntityBase{
    @Enumerated(EnumType.ORDINAL)
    @Column
    public PCFormFactor formFactor;
}
