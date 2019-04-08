/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.linhtd.demo.controller;

import com.linhtd.demo.entity.User;
import com.linhtd.demo.service.UserService;
import java.security.Principal;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Linh
 */
@RestController
@RequestMapping(value = "/auth")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //GET current user
    @GetMapping(value = "/user/me")
    @CrossOrigin(value = "http://wwww.localhost:4200")
    public ResponseEntity<User> getMe(Principal principal) {
        if (principal != null) {
            String name = principal.getName();
            User user = userService.getUserByUserName(name);
            if (user == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            //remove password
            user.setPassword(null);
            return ResponseEntity.status(HttpStatus.OK).body(user);

        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    //POST create user
    @PostMapping(value = "/user/sign-up")
    @CrossOrigin(value = "http://wwww.localhost:4200")
    public ResponseEntity<User> signUp(@RequestBody User user) {
        User result = userService.createUser(user);
        return ResponseEntity.ok().body(result);
    }

    //GET list all users
    @GetMapping(value = "/admin/user/getAll")
    @CrossOrigin(value = "http://wwww.localhost:4200")
    public ResponseEntity<Page<User>> findAllCategoryAdmin(@RequestParam(value = "search", required = false) String search, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size) {
        Page<User> result = userService.findAllUser(search, page, size);
        for (int i = 0; i < result.getSize(); i++) {
            result.getContent().get(i).setPassword(null);
        }
        return ResponseEntity.ok(result);
    }

}
