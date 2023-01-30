package com.learningapi.LearningApi.services.security;

import com.learningapi.LearningApi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        com.learningapi.LearningApi.entity.User myUser = userRepository.findUserByEmail(email);
        if (myUser == null) {
            throw new UsernameNotFoundException(" user not found with email:  " + email);
        }
        return new User(myUser.getEmail(), myUser.getPassword(), new ArrayList<>());
    }

}
