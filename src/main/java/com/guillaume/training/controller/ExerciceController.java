package com.guillaume.training.controller;

import com.guillaume.training.controller.dto.ExercicePayload;
import com.guillaume.training.controller.dto.ExerciceResponse;
import com.guillaume.training.controller.mapper.ExerciceMapper;
import com.guillaume.training.repository.entity.ExerciceEntity;
import com.guillaume.training.service.ExerciceService;
import com.guillaume.training.service.model.Exercice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/exercices")
public class ExerciceController {
    private final ExerciceService exerciceService;

    @Autowired
    public ExerciceController(ExerciceService exerciceService) {
        this.exerciceService = exerciceService;
    }

    @GetMapping
    public List<ExerciceResponse> findAll(){
        return exerciceService.findAll()
                .stream()
                .map(ExerciceMapper::getDTOFromModel)
                .collect(Collectors.toList());
    }

    @PostMapping
    public ExerciceResponse add(@RequestBody ExercicePayload exercicePayload){
        return ExerciceMapper.getDTOFromModel(
                exerciceService.add(ExerciceMapper.getModelFromDTO(exercicePayload))
        );
    }

    @GetMapping("/{id}")
    public ExerciceResponse findById(@PathVariable Long id){
        return ExerciceMapper.getDTOFromModel(exerciceService.findById(id));
    }

    @PutMapping("/{id}")
    public ExerciceResponse replaceExercice(
            @RequestBody ExercicePayload exercicePayload,
            @PathVariable Long id
    ){

        return ExerciceMapper.getDTOFromModel(
                exerciceService.update(ExerciceMapper.getModelFromDTO(exercicePayload), id)
        );
    }
}
