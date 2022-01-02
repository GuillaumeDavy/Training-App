package com.guillaume.training.controller;

import com.guillaume.training.controller.dto.UserPayload;
import com.guillaume.training.controller.dto.UserResponse;
import com.guillaume.training.controller.mapper.UserMapper;
import com.guillaume.training.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<UserResponse> findAll() {
        return userService.findAll()
                .stream()
                .map(UserMapper::getDTOFromModel)
                .collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    UserResponse add(@RequestBody UserPayload userPayload){
        return UserMapper.getDTOFromModel(
                userService.add(UserMapper.getModelFromDTO(userPayload))
        );
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    UserResponse findById(@PathVariable Long id){
        return UserMapper.getDTOFromModel(
                userService.findById(id)
        );
    }
}
