package com.bank.bank.controller;

import com.bank.bank.model.AuthResponseLogin;
import com.bank.bank.model.User;
import com.bank.bank.service.AuthService;
import com.bank.bank.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private AuthService authService;
    @PostMapping("/new")
    public Mono<User> newUser(@RequestBody User newUser){
        return this.userService.newUser(newUser);
    }
    @GetMapping("/getAllUser")
    public Flux<User> getAll(){
        return userService.getAllUser();
    }

    @PostMapping("/updateUser")
    public User updateUser(@RequestBody User user){
        return this.userService.updateUser(user);
    }

    @PostMapping(value = "/deleteUser/{id}")
    public boolean deleteUser(@PathVariable(value="id") Long id){
        return this.userService.deleteUser(id);
    }

    @PostMapping("/login")
    public AuthResponseLogin login(@RequestBody User loginUser){
        return this.authService.login(  Long.valueOf(loginUser.getDocument()), loginUser.getPassword());
    }

}
