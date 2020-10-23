package com.codemark.demoapp.repositories;

import com.codemark.demoapp.model.User;
import com.codemark.demoapp.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<UserRole, Integer> {
    UserRole findUserRoleByRoleId(int id);
    UserRole findUserRoleByRoleName(String name);
    Boolean existsByRoleName(String name);
}
