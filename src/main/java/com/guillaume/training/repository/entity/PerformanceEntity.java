package com.guillaume.training.repository.entity;

import com.guillaume.training.service.model.Performance;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "performance")
@AllArgsConstructor
@NoArgsConstructor
public class PerformanceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "max_weight", nullable = false)
    private float maxWeight;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    @ManyToOne(optional = false)
    @JoinColumn(name = "exercice_id")
    private ExerciceEntity exerciceEntity;
}
