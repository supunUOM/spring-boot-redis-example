package com.supun.springredis.controller;

import com.supun.springredis.service.UserService;
import com.vinesh.rediscache.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
	UserService userService;

    @GetMapping("/{id}")
    @Cacheable(value = "users", key = "#id")
    public User getUser(@PathVariable Integer id) {
        log.info(">> User Controller: get user by id: " + id);
        return userService.getUserById(id);
    }

    @PostMapping
    public User addUser(@RequestBody User user) {
        log.info(">> User Controller: save new user: " + user.toString());
        return userService.create(user);
    }

    @PutMapping
    @CachePut(value = "users", key = "#user")
    public User updateUser(@RequestBody User user) {
        log.info(">> User Controller: update user: " + user.toString());
        return userService.update(user);
    }

    @DeleteMapping("/{id}")
    @CacheEvict(value = "users", allEntries = false, key = "#id")
    public void removeUser(@PathVariable Integer id) {
        log.info(">> User Controller: delete user: " + id);
        userService.delete(id);
    }
}
