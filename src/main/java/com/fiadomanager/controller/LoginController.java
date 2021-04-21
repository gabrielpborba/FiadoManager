package com.fiadomanager.controller;

import com.fiadomanager.models.dto.login.LoginRequestDTO;
import com.fiadomanager.models.dto.login.LoginResponseDTO;
import com.fiadomanager.models.dto.login.UserRequestDTO;
import com.fiadomanager.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping(path = "/newUser")
    public ResponseEntity<Boolean> newUser(UserRequestDTO userRequestDTO) {
        Boolean newUser = loginService.newUser(userRequestDTO);
        return ResponseEntity.status(newUser ? HttpStatus.OK : HttpStatus.CONFLICT).body(newUser);
    }

    @PostMapping(path = "/login")
    public ResponseEntity<LoginResponseDTO> login(LoginRequestDTO loginRequestDTO) throws Exception {
        LoginResponseDTO newUser = loginService.login(loginRequestDTO);
        return ResponseEntity.status(null != newUser ? HttpStatus.OK : HttpStatus.UNAUTHORIZED).body(newUser);
    }

}
