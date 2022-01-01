package com.guillaume.training.controller.mapper;

import com.guillaume.training.service.model.User;
import com.guillaume.training.controller.dto.UserPayload;
import com.guillaume.training.controller.dto.UserResponse;
import com.guillaume.training.repository.entity.UserEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserMapper {
    //Payload ---> Model ---> Entity
    public static User getModelFromDTO(UserPayload userPayload){
        return new User(
                userPayload.getId(),
                userPayload.getName(),
                userPayload.getEmail()
        );
    }

    public static UserEntity getEntityFromModel(User user){
        return new UserEntity(
                user.getId(),
                user.getName(),
                user.getEmail()
        );
    }

    //Entity ---> Model ---> Response
    public static User getModelFromEntity(UserEntity userEntity){
        return new User(
                userEntity.getId(),
                userEntity.getName(),
                userEntity.getEmail()
        );
    }

    public static UserResponse getDTOFromModel(User user){
        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail()
        );
    }
}
