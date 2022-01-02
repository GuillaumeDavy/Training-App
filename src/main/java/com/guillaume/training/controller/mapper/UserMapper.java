package com.guillaume.training.controller.mapper;

import com.guillaume.training.controller.dto.UserPayload;
import com.guillaume.training.controller.dto.UserResponse;
import com.guillaume.training.service.model.User;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserMapper {
    public static User getModelFromDTO(UserPayload userPayload){
        return new User(
                userPayload.getId(),
                userPayload.getName(),
                userPayload.getEmail()
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
