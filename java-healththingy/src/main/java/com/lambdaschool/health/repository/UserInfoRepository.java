package com.lambdaschool.health.repository;

import com.lambdaschool.health.model.UserInfo;
import org.springframework.data.repository.CrudRepository;

public interface UserInfoRepository extends CrudRepository<UserInfo, Long>
{
    UserInfo findByUsername(String username);

}
