package com.africa.semicolon.services;

import com.africa.semicolon.DTO.request.DeleteRequest;
import com.africa.semicolon.DTO.request.LoginRequest;
import com.africa.semicolon.DTO.request.UpdateUserRequest;
import com.africa.semicolon.DTO.request.UserRequest;
import com.africa.semicolon.DTO.response.DeleteResponse;
import com.africa.semicolon.DTO.response.LoginResponse;
import com.africa.semicolon.DTO.response.UpdateUserResponse;
import com.africa.semicolon.DTO.response.UserResponse;
import com.africa.semicolon.data.models.Contact;
import com.africa.semicolon.data.models.User;
import com.africa.semicolon.data.repositories.UserContactRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserServiceImpl implements UserService {

    private final UserContactRepository userContactRepository;

    public UserServiceImpl (UserContactRepository userContactRepository) {
        this.userContactRepository = userContactRepository;
    }

    @Override
    public UserResponse signUp(UserRequest userRequest) {
        if (userRequest.getPassword().length() < 8) {
            return new UserResponse("Password must be at least 8 characters long");
        }
        User user = new User();
        user.setUsername(userRequest.getUsername());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());

        userContactRepository.save(user);

        ArrayList<Contact> contacts = new ArrayList<>();
        user.setContacts(contacts);
        return new UserResponse("User signed up successfully");
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        User user = userContactRepository.findByUsername(loginRequest.getUsername());
        if (user == null) {
            return new LoginResponse("User not found");
        }
        if (!user.getPassword().equals(loginRequest.getPassword())) {
            return new LoginResponse("Invalid password");
        }
        return new LoginResponse("Login successful");
    }

    @Override
    public UpdateUserResponse updateUser(UpdateUserRequest updateUserRequest) {
        User user = userContactRepository.findByUsername(updateUserRequest.getUsername());
        if (user == null) {
            return new UpdateUserResponse("User not found");
        }
        user.setUsername(updateUserRequest.getUsername());
        user.setEmail(updateUserRequest.getEmail());

        if (updateUserRequest.getPassword() != null) {
            user.setPassword(updateUserRequest.getPassword());
        }
        userContactRepository.save(user);

        return new UpdateUserResponse("User updated successfully");
    }

    @Override
    public DeleteResponse deleteUser(DeleteRequest deleteRequest) {
        User user = userContactRepository.findByUsername(deleteRequest.getUsername());
        if (user == null) {
            return new DeleteResponse("User not found");
        }
        userContactRepository.delete(user);
        return new DeleteResponse("User deleted successfully");
    }
}
