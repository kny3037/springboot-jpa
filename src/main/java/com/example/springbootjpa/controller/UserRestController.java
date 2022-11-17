package com.example.springbootjpa.controller;

import com.example.springbootjpa.model.dto.UserRequest;
import com.example.springbootjpa.model.dto.UserResponse;
import com.example.springbootjpa.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserRestController {

    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable Long id) {
        return ResponseEntity.ok().body(userService.getUser(id));
    }
    // ResponseEntity는 httpEntity를 상속받는, 결과 데이터와 HTTP 상태 코드를 직접 제어할 수 있는 클래스이다.
    //HttpEntity를 상속 받고 사용자의 응답 데이터가 포함된 클래스이기 때문에
    //HttpStatus, HttpHeaders, HttpBody를 포함.
    //HttpHeaders : 요청/응답에 대한 요구사항이 적혀있다.
    //HttpBody : 요구사항에 대한 내용이 적혀있다..?

    @PostMapping
    public ResponseEntity<UserResponse> addUser(@RequestBody UserRequest dto) {
        return ResponseEntity.ok().body(userService.addUser(dto));
    }

}

