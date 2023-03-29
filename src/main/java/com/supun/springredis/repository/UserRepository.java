package com.supun.springredis.repository;

import com.supun.springredis.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

}
