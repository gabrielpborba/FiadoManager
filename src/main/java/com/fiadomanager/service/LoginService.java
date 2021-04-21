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
                loginRepository.save(users);
            } else {
                throw new Exception("Usuário já existe");
            }

            return true;
        } catch (Exception e) {
            return false;
        }

    }


    public LoginResponseDTO login(LoginRequestDTO userRequestDTO) throws Exception {

        LoginResponseDTO loginResponseDTO = null;
        try {
            Users findUser = loginRepository.findByUsername(userRequestDTO.getUsername());
            if (findUser.getUsername().equals(userRequestDTO.getUsername()) && findUser.getPassword().equals(userRequestDTO.getPassword())) {
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
