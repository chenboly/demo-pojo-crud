package com.example.demopojocrud.services.implemetation;


import com.example.demopojocrud.models.User;
import com.example.demopojocrud.repositories.UserRepository;
import com.example.demopojocrud.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImplementation implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getALl() {

        //business rule
        return this.userRepository.getAll();
    }

    @Override
    public boolean saveUser(User user) {

        return this.userRepository.saveUser(user);

    }

    @Override
    public boolean updateUser(User user) {
        return this.userRepository.updateUser(user);
    }

    @Override
    public boolean deleteUser(Integer id) {
        return this.userRepository.deleteUser(id);
    }

    @Override
    public User getOne(Integer id) {
        return userRepository.getOne(id);
    }


}
