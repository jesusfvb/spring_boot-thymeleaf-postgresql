package com.backend.backend.services;

import java.util.Arrays;
import java.util.List;

import com.backend.backend.controls.exceptions.UserException;
import com.backend.backend.repositorys.Users;
import com.backend.backend.repositorys.UsersI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsersServisesI implements UsersServises {

    @Autowired
    private UsersI repository;

    private PasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    public List<Users> allUsers() {
        return repository.findAllUserOrderByName();
    }

    @Override
    public List<Users> searchUsers(String text) {
        return repository.searchUser(text);
    }

    @Override
    public Users findUserById(Integer id) {
        if (id == null) {
            throw new UserException("findUserById");
        } else {
            return repository.findById(id).get();
        }
    }

    @Override
    public void saveUser(Users user) {
        if (user == null) {
            throw new UserException("saveUser");
        } else if (user.getId() != null) {
            throw new UserException("saveUser1");
        } else {
            user.setPassword(encoder.encode(user.getPassword()));
            user.addOrUpdateSerch();
            repository.save(user);
        }
    }

    @Override
    public void updateUsers(Users user) {
        if (user.getId() == null) {
            throw new UserException("updateUsers");
        } else {
            if (user.getPassword().length() == 0) {
                user.setPassword(findUserById(user.getId()).getPassword());
            } else {
                user.setPassword(encoder.encode(user.getPassword()));
            }
            user.addOrUpdateSerch();
            repository.save(user);
        }
    }

    @Override
    public void deleteUsers(Integer[] ids) {
        repository.deleteAll(repository.findAllById(Arrays.asList(ids)));
    }

    @Override
    public Users findUserByUseName(String userName) {
        return repository.findByUserName(userName);
    }
}
