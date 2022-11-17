package com.example.springbootjpa.service;

import com.example.springbootjpa.model.dto.UserRequest;
import com.example.springbootjpa.model.dto.UserResponse;
import com.example.springbootjpa.model.entity.User;
import com.example.springbootjpa.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class UserServiceTest {

    private UserRepository userRepository = Mockito.mock(UserRepository.class);

    private UserService userService;

    @BeforeEach
    void setUp() {
        userService = new UserService(userRepository);  //수동 DI
    }

    @Test
    @DisplayName("회원 등록 성공 메시지가 나오는 지")
    void addTest() {
        Mockito.when(userRepository.save(any()))
                .thenReturn(new User(1l, "kimnayeong", "123465"));

        UserResponse userResponse = userService.addUser(new UserRequest("kimnayeong","123465"));
        assertEquals("kimnayeong", userResponse.getUsername());
        assertEquals("회원 등록 성공", userResponse.getMessage());
    }
}