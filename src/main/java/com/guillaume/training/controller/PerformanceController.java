package com.guillaume.training.controller;

import com.guillaume.training.controller.dto.PerformancePayload;
import com.guillaume.training.controller.dto.PerformanceResponse;
import com.guillaume.training.controller.mapper.PerformanceMapper;
import com.guillaume.training.service.ExerciceService;
import com.guillaume.training.service.PerformanceService;
import com.guillaume.training.service.UserService;
import com.guillaume.training.service.model.Exercice;
import com.guillaume.training.service.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/performances")
public class PerformanceController {
    private PerformanceService performanceService;
    private UserService userService;
    private ExerciceService exerciceService;

    @Autowired
    public PerformanceController(
            PerformanceService performanceService,
            UserService userService,
            ExerciceService exerciceService
    ) {
        this.performanceService = performanceService;
        this.userService = userService;
        this.exerciceService = exerciceService;
    }

    @GetMapping
    public List<PerformanceResponse> findAll(){
        return performanceService.findAll()
                .stream()
                .map(PerformanceMapper::getDTOFromModel)
                .collect(Collectors.toList());
    }

    @PostMapping
    public PerformanceResponse add(@RequestBody PerformancePayload performancePayload){
        User user = userService.findById(performancePayload.getUserID());
        Exercice exercice = exerciceService.findById(performancePayload.getExerciceID());
        return PerformanceMapper.getDTOFromModel(
                performanceService.add(PerformanceMapper.getModelFromDTO(performancePayload, user, exercice))
        );
    }

    @GetMapping("/{id}")
    public PerformanceResponse findById(@PathVariable Long id){
        return PerformanceMapper.getDTOFromModel(
                performanceService.findById(id)
        );
    }

    @PutMapping("/{id}")
    public PerformanceResponse update(
            @RequestBody PerformancePayload performancePayload,
            @PathVariable Long id
    ){
        User user = userService.findById(performancePayload.getUserID());
        Exercice exercice = exerciceService.findById(performancePayload.getExerciceID());
        return PerformanceMapper.getDTOFromModel(
                performanceService.update(
                        PerformanceMapper.getModelFromDTO(performancePayload, user, exercice),
                        id
                )
        );
    }
}
