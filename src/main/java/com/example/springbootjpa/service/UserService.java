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
        //Optional<T>
        //null이 올 수 있는 값을 감싸는 Wrapper클래스
        //참조하더라도 NullPointerException(NPE)가 발생하지 않도록 도와준다.
        //Optional.get() : Optional에 랩핑된 객체의 연산이 끝나고 최종적으로 값을 가져옴
                        // get()은 랩핑된 객체가 null이 아닌 경우에만 값을 반환할 수 있다.
                        // 그렇지 않으면 NoSuchElementException이 발생

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
