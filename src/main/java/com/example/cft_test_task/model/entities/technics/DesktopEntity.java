package com.example.cft_test_task.model.entities.technics;

import com.example.cft_test_task.model.enums.tech.details.desktop.PCFormFactor;
import com.example.cft_test_task.model.entities.TechnicsEntity;

import javax.persistence.*;

@Entity
@Table(name = "Desktops")
public class DesktopEntity extends TechEntityBase{
    @Enumerated(EnumType.ORDINAL)
    @Column
    public PCFormFactor formFactor;
}
