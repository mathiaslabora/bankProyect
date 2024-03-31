package com.bank.bank.service;

import com.bank.bank.model.AuthResponseLogin;
import com.bank.bank.model.User;
import com.bank.bank.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    public AuthResponseLogin login(Long documento, String contrasena){

        Optional<User> usuario = userRepository.findById(documento);

        boolean decodedPass = passwordEncoder.matches(contrasena, usuario.get().getPassword());


        if(usuario!=null && decodedPass){
            return new AuthResponseLogin(true, usuario.get().getUser_type());
        }
        return new AuthResponseLogin(false, null);
    }


}
