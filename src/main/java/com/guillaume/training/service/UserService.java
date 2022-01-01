package com.guillaume.training.service;

import com.guillaume.training.exception.UserNotFoundException;
import com.guillaume.training.repository.UserRepository;
import com.guillaume.training.repository.dao.UserDAO;
import com.guillaume.training.service.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserDAO userDAO;

    @Autowired
    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public List<User> findAll(){
        return userDAO.findAll();
    }

    public User findById(Long id) {
        return userDAO.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    public User add(User user){
        return userDAO.save(user);
    }
}
