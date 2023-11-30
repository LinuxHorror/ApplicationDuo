package com.example.applicationduo.model;

import com.example.applicationduo.entity.UserEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
public class CurrentUser {
    @Getter
    public static UserEntity entity;

    private CurrentUser() {
    }

    public static void setEntity(UserEntity entity) {
        CurrentUser.entity = entity;
    }
}
