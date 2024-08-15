package com.africa.semicolon.web;

import com.africa.semicolon.DTO.request.DeleteRequest;
import com.africa.semicolon.DTO.request.LoginRequest;
import com.africa.semicolon.DTO.request.UpdateUserRequest;
import com.africa.semicolon.DTO.request.UserRequest;
import com.africa.semicolon.DTO.response.DeleteResponse;
import com.africa.semicolon.DTO.response.LoginResponse;
import com.africa.semicolon.DTO.response.UpdateUserResponse;
import com.africa.semicolon.DTO.response.UserResponse;
import com.africa.semicolon.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/signUp")
    public UserResponse signUp(@RequestBody UserRequest userRequest) {
        return userService.signUp(userRequest);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        return userService.login(loginRequest);
    }

    @PutMapping("/update")
    public UpdateUserResponse updateUser(@RequestBody UpdateUserRequest updateUserRequest) {
        return userService.updateUser(updateUserRequest);
    }

    @DeleteMapping("/delete")
    public DeleteResponse deleteUser(@RequestBody DeleteRequest deleteRequest) {
        return userService.deleteUser(deleteRequest);
    }
}
