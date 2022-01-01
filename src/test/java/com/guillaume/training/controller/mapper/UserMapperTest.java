package com.guillaume.training.controller.mapper;

import com.guillaume.training.controller.dto.UserPayload;
import com.guillaume.training.controller.dto.UserResponse;
import com.guillaume.training.repository.entity.UserEntity;
import com.guillaume.training.service.model.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserMapperTest {

    @Test
    public void shouldMapCorrectlyAnUserPayloadToUserModel(){
        UserPayload user = new UserPayload(1L, "User Name", "email@email.fr");
        User expected = new User(1L, "User Name", "email@email.fr");
        User actual = UserMapper.getModelFromDTO(user);

        assertEquals(expected, actual);
    }

    @Test
    public void shouldMapCorrectlyAnUserModelToUserEntity(){
        User user = new User(1L, "User Name", "email@email.fr");
        UserEntity expected = new UserEntity(1L, "User Name", "email@email.fr");
        UserEntity actual = UserMapper.getEntityFromModel(user);

        assertEquals(expected, actual);
    }

    @Test
    public void shouldMapCorrectlyAnUserEntityToUserModel(){
        UserEntity user = new UserEntity(1L, "User Name", "email@email.fr");
        User expected = new User(1L, "User Name", "email@email.fr");
        User actual = UserMapper.getModelFromEntity(user);

        assertEquals(expected, actual);
    }

    @Test
    public void shouldMapCorrectlyAnUserModelToUserResponse(){
        User user = new User(1L, "User Name", "email@email.fr");
        UserResponse expected = new UserResponse(1L, "User Name", "email@email.fr");
        UserResponse actual = UserMapper.getDTOFromModel(user);

        assertEquals(expected, actual);
    }

}