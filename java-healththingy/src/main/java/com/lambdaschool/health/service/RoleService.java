package com.lambdaschool.health.service;

import com.lambdaschool.health.model.Role;

import java.util.List;

public interface RoleService
{
    List<Role> findAll();

    Role findRoleById(long id);

    void delete(long id);

    Role update(Role role, long id);

    Role save(Role role);

    void saveUserRole(long userid, long roleid);

    void deleteUserRole(long userid, long roleid);
}