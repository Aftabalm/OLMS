package com.learningapi.LearningApi.services;

import com.learningapi.LearningApi.models.ProfileChangeDTO;
import com.learningapi.LearningApi.models.UserDTO;
import com.learningapi.LearningApi.entity.User;
import com.learningapi.LearningApi.repository.UserRepository;
import com.learningapi.LearningApi.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserUtil userUtil;

    public User getLoggedInUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return getUser(email);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User addUser(UserDTO userDTO) {
        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setGender(userDTO.getGender());
        user.setAddress(userDTO.getAddress());
        user.setFirstName(userDTO.getFirstName());
        user.setDateOfBirth(userDTO.getDateOfBirth());
        user.setLastName(userDTO.getLastName());
        userRepository.save(user);
        return user;
    }

    public User getUser(String email) {
        return userRepository.findUserByEmail(email);
    }

    public UserDTO getUserDetails() {
        UserDTO userDTO = new UserDTO();
        User user = getLoggedInUser();
        userDTO.setFirstName(user.getFirstName());
        userDTO.setEmail(user.getEmail());
        return userDTO;
    }
    public ResponseEntity<?> getProfile() {
        User currentUser = getLoggedInUser();
        User user = new User();
        user.setFirstName(currentUser.getFirstName());
        user.setLastName(currentUser.getLastName());
        user.setEmail(currentUser.getEmail());
        user.setAddress(currentUser.getAddress());
        user.setDateOfBirth(currentUser.getDateOfBirth());
        user.setGender(currentUser.getGender());
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    public ResponseEntity<?> changeProfile(@RequestBody ProfileChangeDTO updatedProfile) {
        User currentUser = getLoggedInUser();
        currentUser.setFirstName(updatedProfile.getFirstName());
        currentUser.setLastName(updatedProfile.getLastName());
        currentUser.setEmail(updatedProfile.getEmail());
        currentUser.setAddress(updatedProfile.getAddress());
        userRepository.save(currentUser);
        return new ResponseEntity<>(currentUser, HttpStatus.OK);
    }
}

