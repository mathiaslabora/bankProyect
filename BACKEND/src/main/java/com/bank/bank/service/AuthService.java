package com.bank.bank.service;

import com.bank.bank.model.AuthResponseLogin;
import com.bank.bank.model.Usuario;
import com.bank.bank.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UsuarioRepository usuarioRepository;
    public AuthResponseLogin login(Long documento, String contrasena){

        Optional<Usuario> usuario = usuarioRepository.findById(documento);

        boolean decodedPass = passwordEncoder.matches(contrasena, usuario.get().getContrasena());


        if(usuario!=null && decodedPass){
            return new AuthResponseLogin(true, usuario.get().getTipoUsu());
        }
        return new AuthResponseLogin(false, null);
    }


}
