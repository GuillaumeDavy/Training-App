package com.guillaume.training.repository.mapper;

import com.guillaume.training.repository.entity.UserEntity;
import com.guillaume.training.service.model.User;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserMapper {
    public static UserEntity getEntityFromModel(User user){
        return new UserEntity(
                user.getId(),
                user.getName(),
                user.getEmail()
        );
    }

    public static User getModelFromEntity(UserEntity userEntity){
        return new User(
                userEntity.getId(),
                userEntity.getName(),
                userEntity.getEmail()
        );
    }
}
