package com.backend.backend.services;

import java.util.List;

import com.backend.backend.repositorys.Users;

import org.springframework.stereotype.Service;

@Service
public interface UsersServises {

    List<Users> allUsers();

    List<Users> searchUsers(String text);

    Users findUserById(Integer id);

    void saveUser(Users user);

    void updateUsers(Users user);

    void deleteUsers(Integer ids[]);

}
