package com.guillaume.training.repository.dao;

import com.guillaume.training.controller.mapper.UserMapper;
import com.guillaume.training.repository.UserRepository;
import com.guillaume.training.service.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class UserDAO {
    private final UserRepository userRepository;

    @Autowired
    public UserDAO(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll(){
        return userRepository.findAll().stream()
                .map(UserMapper::getModelFromEntity)
                .collect(Collectors.toList());
    }

    public Optional<User> findById(Long id){
        return userRepository.findById(id)
                .map(UserMapper::getModelFromEntity);
    }

    public User save(User user){
        return UserMapper.getModelFromEntity(
                userRepository.save(UserMapper.getEntityFromModel(user))
        );
    }
}
