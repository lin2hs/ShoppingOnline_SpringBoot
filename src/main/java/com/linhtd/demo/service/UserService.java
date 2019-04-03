/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.linhtd.demo.service;

import com.linhtd.demo.entity.User;
import com.linhtd.demo.repository.UserRepository;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import static org.springframework.data.jpa.domain.Specification.where;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author Linh
 */
@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    public Page<User> findAllUser(String search, int page, int size) {
        Pageable sortedByCreatedDateDesc
                = PageRequest.of(page, size, Sort.by("createdDate").descending());
        Page<User> result = userRepository.findAll(where(UserRepository.filterByName(search)), sortedByCreatedDateDesc);
        return result;
    }

    public User createUser(User user) {
        Optional<User> duplicateUser = userRepository.findByName(user.getName());
        if (duplicateUser.isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "This account exist!");
        } else {
            user.setRole("ROLE_USER");
            user.setActive(true);
            user.setPassword(encoder.encode(user.getPassword()));
            user.setCreatedDate(Timestamp.valueOf(LocalDateTime.now()));
            user.setUpdatedDate(Timestamp.valueOf(LocalDateTime.now()));
            return userRepository.save(user);
        }
    }

    public User getUserByUserName(String name) {
        User user = userRepository.findByName(name).get();
        return user;
    }

}
