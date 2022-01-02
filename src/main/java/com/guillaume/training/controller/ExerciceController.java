package com.guillaume.training.controller;

import com.guillaume.training.controller.dto.ExercicePayload;
import com.guillaume.training.controller.dto.ExerciceResponse;
import com.guillaume.training.controller.mapper.ExerciceMapper;
import com.guillaume.training.service.ExerciceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    @ResponseStatus(HttpStatus.OK)
    public List<ExerciceResponse> findAll(){
        return exerciceService.findAll()
                .stream()
                .map(ExerciceMapper::getDTOFromModel)
                .collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ExerciceResponse add(@RequestBody ExercicePayload exercicePayload){
        return ExerciceMapper.getDTOFromModel(
                exerciceService.add(ExerciceMapper.getModelFromDTO(exercicePayload))
        );
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ExerciceResponse findById(@PathVariable Long id){
        return ExerciceMapper.getDTOFromModel(exerciceService.findById(id));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ExerciceResponse replaceExercice(
            @RequestBody ExercicePayload exercicePayload,
            @PathVariable Long id
    ){

        return ExerciceMapper.getDTOFromModel(
                exerciceService.update(ExerciceMapper.getModelFromDTO(exercicePayload), id)
        );
    }
}
