package com.example.demopojocrud.controllers;


import com.example.demopojocrud.models.User;
import com.example.demopojocrud.services.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Controller
//the @PropertySource annotation use for call the property file
@PropertySource("classpath:/project_file.properties")
public class UserController {
    private UserService userService;
    //set value (path) for variable SERVER_PATH;
    @Value("${server.path}")
    private String SERVER_PATH;
    @Value("${client.path}")
    private String CLIENT_PATH;


    public UserController(UserService userService) {

        this.userService = userService;
    }

    @GetMapping("/user/all")
    public String getAll(ModelMap modelMap) {
        List<User> users = userService.getALl();
        modelMap.addAttribute("userList", users);

        return "index";
    }

    //the controller for view user by userID
    //to get only user id
    @GetMapping("/user/one/{user_id}")
    public String getOne(@PathVariable("user_id") Integer id, ModelMap modelMap) {
        User user = userService.getOne(id);
        modelMap.addAttribute("userId", user);
        return "view-user";
    }

    //the controller go to add new user page
    @GetMapping("/user/add")
    public String showAddUserForm(Model model) {
        model.addAttribute("user", new User());
        return "add-user";
    }

    //the controller for get the information sent from html add-user form
    //if the form method using method="post" then spring controller need to use PostMapping
    @PostMapping("/user/add/submit")
    public String submitUserAdded(@Valid User user, BindingResult bindingResult, @RequestParam("my-file") MultipartFile file) {
        //set date input validation

        if (bindingResult.hasErrors()) {
            System.out.println("Error occur");
            return "add-user";
        }
        //create directory if not exist
        File path = new File(SERVER_PATH);
        if(!path.exists())
            path.mkdirs();

        //create new random file name
        String fileName = file.getOriginalFilename();
        String extension = fileName.substring(fileName.lastIndexOf(".png"));
        fileName = UUID.randomUUID() + "." +extension;
        System.out.println(extension);
        System.out.println(fileName);

        //copy file from pc to server
        try {
            Files.copy(file.getInputStream(), Paths.get(SERVER_PATH, fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //path fileName to setProfile field
        user.setProfile(fileName);
        System.out.println(user);
        this.userService.saveUser(user);
        return "redirect:/user/all";//return to index after save
    }


    //the controller for update user by id
    @GetMapping("/user/update/{id}")
    public String showUpdateUserForm(@PathVariable("id") Integer id, Model model) {
        User user = this.userService.getOne(id);
        model.addAttribute("user", user);
        return "update-user";
    }

    //the controller for get the information from the update submit button
    //if the form using method-post then spring controller need to use PostMapping
    @PostMapping("/user/update/submit")
    public String submitUserUpdate(User user) {
        System.out.println(user);
        this.userService.updateUser(user);
        return "redirect:/user/all";
    }

    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id) {
        this.userService.deleteUser(id);

        return "redirect:/user/all";
    }
}
