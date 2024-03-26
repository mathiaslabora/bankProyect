package com.bank.bank.service;

import com.bank.bank.model.Usuario;
import com.bank.bank.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;



@Service
public interface UsuarioService {


    Usuario newUsuario(Usuario newUsuario);

    Iterable<Usuario> getAllUsuarios();
    Usuario getUsuarioById(Long id) ;

    Usuario updateUsuario(Usuario usuarioDetails);


    boolean deleteUsuario(Long id) ;
}