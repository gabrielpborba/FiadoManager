package com.fiadomanager.service;

import com.fiadomanager.infrastructure.repository.LoginRepository;
import com.fiadomanager.models.domain.Users;
import com.fiadomanager.models.dto.login.LoginRequestDTO;
import com.fiadomanager.models.dto.login.LoginResponseDTO;
import com.fiadomanager.models.dto.login.UserRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private LoginRepository loginRepository;


    public boolean newUser(UserRequestDTO userRequestDTO) {
        try {

            Users findUser = loginRepository.findByUsername(userRequestDTO.getUsername());


            if (null == findUser) {
                Users users = new Users();
                users.setUsername(userRequestDTO.getUsername());
                users.setName(userRequestDTO.getName());
                users.setPassword(userRequestDTO.getPassword());
                loginRepository.saveAndFlush(users);
            } else {
                return false;
            }

        } catch (Exception e) {
            return false;
        }
        return false;
    }


    public LoginResponseDTO login(LoginRequestDTO userRequestDTO) throws Exception {

        LoginResponseDTO loginResponseDTO = new LoginResponseDTO();
        try {
            Users findUser = loginRepository.findByUsername(userRequestDTO.getUsername());
            if (findUser != null && findUser.getUsername().equals(userRequestDTO.getUsername()) && findUser.getPassword().equals(userRequestDTO.getPassword())) {
                loginResponseDTO.setId(findUser.getId());
                loginResponseDTO.setUsername(findUser.getUsername());
                loginResponseDTO.setPassword(findUser.getPassword());
                return loginResponseDTO;
            } else {
                return null;
            }

        } catch (Exception e) {
            throw new Exception("Erro de login");
        }
    }
}
