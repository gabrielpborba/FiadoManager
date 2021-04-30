package com.fiadomanager.service;

import com.fiadomanager.infrastructure.repository.LoginRepository;
import com.fiadomanager.models.domain.Users;
import com.fiadomanager.models.dto.login.LoginRequestDTO;
import com.fiadomanager.models.dto.login.LoginResponseDTO;
import com.fiadomanager.models.dto.login.UserRequestDTO;
import com.fiadomanager.models.exception.FiadoManagerCustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private LoginRepository loginRepository;


    public boolean newUser(UserRequestDTO userRequestDTO) throws FiadoManagerCustomException {
        try {

            Users findUser = loginRepository.findByUsername(userRequestDTO.getUsername());
            if (null == findUser) {
                Users users = new Users();
                users.setUsername(userRequestDTO.getUsername());
                users.setName(userRequestDTO.getName());
                users.setPassword(userRequestDTO.getPassword());
                loginRepository.saveAndFlush(users);
                return true;
            } else {
                throw new FiadoManagerCustomException(HttpStatus.NOT_FOUND, "Usuário já existe");
            }

        } catch (Exception e) {
            throw e;
        }
    }


    public LoginResponseDTO login(LoginRequestDTO userRequestDTO) throws FiadoManagerCustomException {

        try {
            LoginResponseDTO loginResponseDTO = new LoginResponseDTO();

            Users findUser = loginRepository.findByUsername(userRequestDTO.getUsername());
            if (findUser != null && findUser.getUsername().equals(userRequestDTO.getUsername()) && findUser.getPassword().equals(userRequestDTO.getPassword())) {
                loginResponseDTO.setId(findUser.getId());
                loginResponseDTO.setUsername(findUser.getUsername());
                loginResponseDTO.setPassword(findUser.getPassword());
                return loginResponseDTO;
            } else {
                throw new FiadoManagerCustomException(HttpStatus.UNAUTHORIZED, "Usuário não permitido");
            }

        } catch (Exception e) {
            throw e;
        }
    }
}
