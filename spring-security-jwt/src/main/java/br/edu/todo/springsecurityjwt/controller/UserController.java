package br.edu.todo.springsecurityjwt.controller;

import br.edu.todo.springsecurityjwt.model.User;
import br.edu.todo.springsecurityjwt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    @RequestMapping("/users")
    public void saveUser(@RequestBody User user) {
        userService.createUser(user);
    }
}
