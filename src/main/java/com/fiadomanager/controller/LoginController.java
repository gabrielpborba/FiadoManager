package com.fiadomanager.controller;

import com.fiadomanager.models.dto.login.LoginRequestDTO;
import com.fiadomanager.models.dto.login.LoginResponseDTO;
import com.fiadomanager.models.dto.login.UserRequestDTO;
import com.fiadomanager.models.exception.FiadoManagerCustomException;
import com.fiadomanager.service.impl.LoginServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;

@RestController
public class LoginController {

    @Autowired
    private LoginServiceImpl loginServiceImpl;

    @PostMapping(path = "/newUser")
    public ResponseEntity<Boolean> newUser(@RequestBody UserRequestDTO userRequestDTO) throws FiadoManagerCustomException, NoSuchAlgorithmException {
        Boolean newUser = loginServiceImpl.newUser(userRequestDTO);
        return ResponseEntity.status(newUser ? HttpStatus.OK : HttpStatus.CONFLICT).body(newUser);
    }

    @PostMapping(path = "/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO) throws FiadoManagerCustomException, NoSuchAlgorithmException {
        LoginResponseDTO newUser = loginServiceImpl.login(loginRequestDTO);
        return ResponseEntity.status(null != newUser ? HttpStatus.OK : HttpStatus.UNAUTHORIZED).body(newUser);
    }

}
