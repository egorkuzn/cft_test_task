package com.example.cft_test_task.model.entities.technics;

import com.example.cft_test_task.model.entities.TechnicsEntity;
import javax.persistence.*;

/** Child class should contain:
 *
 *     @Id
 *     @Column
 *     @GeneratedValue
 *     public Long id;
 *
 *     @JoinColumn
 *     @OneToOne
 *     public TechnicsEntity technicsEntity;
 */


public interface TechEntityBase {
    TechnicsEntity getTechnicsEntity();
    void setTechnicsEntity(TechnicsEntity technicsEntity);
}
