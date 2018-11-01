package com.example.demopojocrud.services;



import com.example.demopojocrud.models.User;

import java.util.List;

public interface UserService {

    List<User> getALl();

    boolean saveUser(User user);
    boolean updateUser(User user);
    boolean deleteUser(Integer id);

    User getOne(Integer id);
}
