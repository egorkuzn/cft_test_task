package com.example.cft_test_task.model.entities.computers.technics;

import com.example.cft_test_task.model.enums.tech.details.desktop.PCFormFactor;
import com.example.cft_test_task.model.entities.computers.TechnicsEntity;

import javax.persistence.*;

@Entity
@Table(name = "Desktops")
public class DesktopEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    public Long id;

    @OneToOne
    @JoinColumn
    public TechnicsEntity technicsEntity;

    @Enumerated(EnumType.ORDINAL)
    @Column
    public PCFormFactor formFactor;
}
