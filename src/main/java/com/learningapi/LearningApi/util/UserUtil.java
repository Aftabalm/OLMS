package com.learningapi.LearningApi.util;

import com.learningapi.LearningApi.entity.User;
import com.learningapi.LearningApi.models.UserDTO;
import org.springframework.stereotype.Component;

@Component
public class UserUtil {
    public UserDTO entityToDto(User user) {
        UserDTO userdto = new UserDTO();
        userdto.setFirstName(user.getFirstName());
        userdto.setEmail(user.getEmail());
        return userdto;
    }

    public User dtoToEntity(UserDTO userDTO) {
        User user = new User();
        user.setFirstName(userDTO.getFirstName());
        user.setEmail(userDTO.getEmail());
        return user;
    }
}
