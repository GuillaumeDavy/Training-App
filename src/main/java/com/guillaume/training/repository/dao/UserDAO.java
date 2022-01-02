package com.guillaume.training.repository.dao;

import com.guillaume.training.exception.UserAlreadyExistsException;
import com.guillaume.training.repository.UserRepository;
import com.guillaume.training.repository.mapper.UserMapper;
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
        //Check if email already exists in BD
        userRepository.findByEmail(user.getEmail()).map(u -> {
            throw new UserAlreadyExistsException(user.getEmail());
        });

        return UserMapper.getModelFromEntity(userRepository.save(UserMapper.getEntityFromModel(user)));
    }
}
