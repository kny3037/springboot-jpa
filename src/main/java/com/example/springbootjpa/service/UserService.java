package com.example.springbootjpa.service;

import com.example.springbootjpa.model.entity.User;
import com.example.springbootjpa.model.dto.UserRequest;
import com.example.springbootjpa.model.dto.UserResponse;
import com.example.springbootjpa.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    public final UserRepository userRepository;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

        public UserResponse getUser(Long id){
        Optional<User> optUser = userRepository.findById(id);
        if (optUser.isEmpty()){
            return new UserResponse(id, "", "해당 id의 유저가 없습니다.");
        }else {
            User user = optUser.get();
            return new UserResponse(user.getId(), user.getUsername(), "");
            }
        }

    public UserResponse addUser(UserRequest dto){
        User user = dto.toEntity();
        //중복체크
        Optional<User> seletedUser = userRepository.findByUsername(dto.getUsername());
        if (seletedUser.isEmpty()) {
            User savedUser = userRepository.save(user);
            return new UserResponse(savedUser.getId(), savedUser.getUsername(), "회원 등록 성공");
        } else {
            return new UserResponse(null, dto.getUsername(), "이 user는 이미 존재 합니다. 다른 이름을 사용하세요");
        }
        }
    }
