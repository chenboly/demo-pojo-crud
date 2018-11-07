package com.example.demopojocrud.repositories;


import com.example.demopojocrud.models.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {

    List<User> userList = new ArrayList<>();
    {
        userList.add(new User(1, "Boly", "Male", "Profile"));
        userList.add(new User(2, "Vandy", "Female", "Profile"));
        userList.add(new User(3, "Kimsay", "Male", "Profile"));
        userList.add(new User(4, "Kimseng", "Male", "Profile"));
        userList.add(new User(5, "Yat", "Male", "Profile"));
    }

    public List<User> getAll(){
        return this.userList;
    }

    //repository to get user id from selected id
    public User getOne(Integer id){

        //loop to get user id
        for (User u: userList) {
            Integer userId = u.getId();
            if(userId.equals(id)){
             return u;
            }

        }
        return null;
    }

    //save new user to repository or db
    public boolean saveUser(User user){
        return userList.add(user);
    }

    //update user information to repository or db
    public boolean updateUser (User user){
        //for loop to get index and update user by id
        for (int i = 0 ; i <userList.size() ; i++){
            if(userList.get(i).getId().equals(user.getId())){
                userList.set(i, user);
                return true;
            }
        }
        return false;
    }

    //delete user by id
    public boolean deleteUser (Integer id){
        //for loop to get index and delete
        for (int i = 0; i <userList.size() ; i++) {
            if(userList.get(i).getId().equals(id)){
                userList.remove(i);
                return true;
            }

        }
        return false;
    }
}
