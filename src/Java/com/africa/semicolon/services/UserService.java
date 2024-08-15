package com.africa.semicolon.services;

import com.africa.semicolon.DTO.request.DeleteRequest;
import com.africa.semicolon.DTO.request.LoginRequest;
import com.africa.semicolon.DTO.request.UpdateUserRequest;
import com.africa.semicolon.DTO.request.UserRequest;
import com.africa.semicolon.DTO.response.DeleteResponse;
import com.africa.semicolon.DTO.response.LoginResponse;
import com.africa.semicolon.DTO.response.UpdateUserResponse;
import com.africa.semicolon.DTO.response.UserResponse;

import java.util.List;

public interface UserService {
    UserResponse signUp (UserRequest userRequest);
    LoginResponse login (LoginRequest loginRequest);
    UpdateUserResponse updateUser (UpdateUserRequest updateUserRequest);
    DeleteResponse deleteUser (DeleteRequest deleteRequest);
}
