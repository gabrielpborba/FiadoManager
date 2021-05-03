package com.fiadomanager.service;

import com.fiadomanager.models.dto.login.LoginRequestDTO;
import com.fiadomanager.models.dto.login.LoginResponseDTO;
import com.fiadomanager.models.dto.login.UserRequestDTO;
import com.fiadomanager.models.exception.FiadoManagerCustomException;

import java.security.NoSuchAlgorithmException;

public interface LoginService {

    boolean newUser(UserRequestDTO userRequestDTO) throws FiadoManagerCustomException, NoSuchAlgorithmException;

    LoginResponseDTO login(LoginRequestDTO userRequestDTO) throws FiadoManagerCustomException, NoSuchAlgorithmException;



}
