package com.africa.semicolon.services;

import com.africa.semicolon.DTO.request.DeleteRequest;
import com.africa.semicolon.DTO.request.LoginRequest;
import com.africa.semicolon.DTO.request.UpdateUserRequest;
import com.africa.semicolon.DTO.request.UserRequest;
import com.africa.semicolon.DTO.response.DeleteResponse;
import com.africa.semicolon.DTO.response.LoginResponse;
import com.africa.semicolon.DTO.response.UpdateUserResponse;
import com.africa.semicolon.DTO.response.UserResponse;
import com.africa.semicolon.data.models.User;
import com.africa.semicolon.data.repositories.UserContactRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
public class UserServiceImplTest {

    @Autowired
    private UserService userService;
    @Autowired
    private UserContactRepository userContactRepository;

    @Test
    public void testSignUp() {
        UserRequest userRequest = new UserRequest();
        userRequest.setUsername("Nelly");
        userRequest.setPassword("nelson01");
        userRequest.setEmail("nelson@gmail.com");

        UserResponse response = userService.signUp(userRequest);
        assertThat(response).isNotNull();
        assertThat(response.getMessage()).isEqualTo("User signed up successfully");
    }

    @Test
    public void testInvalidPassword() {
        UserRequest userRequest = new UserRequest();
        userRequest.setUsername("Kimberly");
        userRequest.setPassword("kim");
        userRequest.setEmail("kim@gmail.com");

        UserResponse response = userService.signUp(userRequest);
        assertThat(response).isNotNull();
        assertThat(response.getMessage()).isEqualTo("Password must be at least 8 characters long");
    }

    @Test
    public void testLogin() {
        User user = new User();
        user.setUsername("Stephen");
        user.setPassword("nelson01");
        user.setEmail("nelson@gmail.com");
        userContactRepository.save(user);

        LoginRequest request = new LoginRequest();
        request.setUsername("Stephen");
        request.setPassword("nelson01");

        LoginResponse response = userService.login(request);

        assertThat(response).isNotNull();
        assertThat(response.getMessage()).isEqualTo("Login successful");
    }

    @Test
    public void testUserNotFoundLogin() {
        LoginRequest request = new LoginRequest();
        request.setUsername("Smart");
        request.setPassword("password");

        LoginResponse response = userService.login(request);

        assertThat(response).isNotNull();
        assertThat(response.getMessage()).isEqualTo("User not found");
    }

    @Test
    public void testLoginInvalidPassword() {
        User user = new User();
        user.setUsername("Kim");
        user.setPassword("kimberly");
        user.setEmail("kim@gmail.com");
        userContactRepository.save(user);

        LoginRequest request = new LoginRequest();
        request.setUsername("Kim");
        request.setPassword("nelson");

        LoginResponse response = userService.login(request);

        assertThat(response).isNotNull();
        assertThat(response.getMessage()).isEqualTo("Invalid password");
    }

    @Test
    public void testUpdateUser() {
        User user = new User();
        user.setUsername("Smart");
        user.setPassword("12345678");
        user.setEmail("samuel@gmail.com");
        userContactRepository.save(user);

        UpdateUserRequest request = new UpdateUserRequest();
        request.setUsername("Smart");
        request.setEmail("sammy@gmail.com");
        request.setPassword("sammy011");

        UpdateUserResponse response = userService.updateUser(request);

        assertThat(response.getMessage()).isEqualTo("User updated successfully");
    }

    @Test
    public void testUserNotFoundUpdate() {
        UpdateUserRequest request = new UpdateUserRequest();
        request.setUsername("Sunday");
        request.setEmail("sunny@gmail.com");
        request.setPassword("sammy011");

        UpdateUserResponse response = userService.updateUser(request);

        assertThat(response.getMessage()).isEqualTo("User not found");
    }

    @Test
    public void testDeleteUser() {
        User user = new User();
        user.setUsername("Joshua");
        user.setPassword("joshua01");
        user.setEmail("josh@gmail.com");
        userContactRepository.save(user);

        DeleteRequest request = new DeleteRequest();
        request.setUsername("Joshua");

        DeleteResponse response = userService.deleteUser(request);

        assertThat(response.getMessage()).isEqualTo("User deleted successfully");
    }

    @Test
    public void testUnknownUser() {
        DeleteRequest request = new DeleteRequest();
        request.setUsername("Joshua");

        DeleteResponse response = userService.deleteUser(request);

        assertThat(response.getMessage()).isEqualTo("User not found");
    }
}
