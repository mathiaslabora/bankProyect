package com.bank.bank.service;

import com.bank.bank.model.Usuario;
import com.bank.bank.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public Usuario newUsuario(Usuario newUsuario) {
        String encodePass = passwordEncoder.encode(newUsuario.getContrasena());
        newUsuario.setContrasena(encodePass);
        System.out.println("Encoded: " + encodePass);
        return usuarioRepository.save(newUsuario);
    }

    @Override
    public Iterable<Usuario> getAllUsuarios() {
        return this.usuarioRepository.findAll();
    }

    @Override
    public Usuario getUsuarioById(Long id) {
        return this.usuarioRepository.getReferenceById(id);
    }

    @Override
    public Usuario createUsuario(Usuario usuario) {
        return this.usuarioRepository.save(usuario);
    }

    @Override
    public Usuario updateUsuario(Usuario usuario) {
        Optional<Usuario> usuarioFind= this.usuarioRepository.findById(Long.valueOf(usuario.getDocumento()));
        if(usuarioFind.get()!=null){
            //usuarioFind.get().setDocumento(usuario.getDocumento());
            usuarioFind.get().setContrasena(usuario.getContrasena());
            usuarioFind.get().setCorreo(usuario.getCorreo());
            usuarioFind.get().setDireccion(usuario.getDireccion());
            usuarioFind.get().setTipoUsu(usuario.getTipoUsu());
            usuarioFind.get().setNombreCompleto(usuario.getNombreCompleto());
            return this.newUsuario(usuarioFind.get());
        }
        return null;
    }

    @Override
    public boolean deleteUsuario(Long id) {
        this.usuarioRepository.deleteById(id);
        return true;
    }
}
