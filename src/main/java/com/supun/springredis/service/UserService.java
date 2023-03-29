package com.supun.springredis.service;

import com.supun.springredis.entity.User;
import com.supun.springredis.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    @Autowired
    UserRepository userRepository;

    public User getUserById(Integer id) {
        log.info("Getting customer information for id {}", id);
        Optional<User> user = userRepository.findById(id);

        if (user.isPresent()) {
            return user.get();
        } else {
            return null;
        }
    }

    public User create(User user) {
        return userRepository.save(user);
    }

    public User update(User user) {
        Integer id = user.getId();
        User userInDb = getUserById(id);
        if (userInDb != null) {
            return create(user);
        } else {
            return null;
        }
    }

    public void delete(Integer id) {
        userRepository.deleteById(id);
    }
}
