package com.lambdaschool.health.repository;

import com.lambdaschool.health.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long>
{
    User findByUsername(String username);
}
