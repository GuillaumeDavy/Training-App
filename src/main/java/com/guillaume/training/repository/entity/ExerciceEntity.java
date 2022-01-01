package com.guillaume.training.repository.entity;

import com.guillaume.training.service.model.Exercice;
import com.guillaume.training.service.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "exercice")
@AllArgsConstructor
@NoArgsConstructor
public class ExerciceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;
}