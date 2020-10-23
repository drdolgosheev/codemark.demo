package com.codemark.demoapp.repositories;

import com.codemark.demoapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    public List<User> findAll();
    public User findByUserId(int id);
    public User findUserByMail(String mail);
    public Boolean existsByMail(String mail);
}
