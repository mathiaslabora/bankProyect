package com.bank.bank.service;

import com.bank.bank.model.Cliente;
import com.bank.bank.model.Empleado;
import com.bank.bank.model.Usuario;
import com.bank.bank.repository.ClienteRepository;
import com.bank.bank.repository.EmpleadoRepository;
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
    private ClienteRepository clienteRepository;
    @Autowired
    private EmpleadoRepository empleadoRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public Usuario newUsuario(Usuario newUsuario) {
        String encodePass = passwordEncoder.encode(newUsuario.getContrasena());
        newUsuario.setContrasena(encodePass);
        System.out.println("Encoded: " + encodePass);
        Usuario usuario = this.usuarioRepository.save(newUsuario);
        if (usuario.getTipoUsu().equals("Cliente")){
            Cliente cliente = new Cliente();
            cliente.setDocumentoCli(usuario.getDocumento());
            cliente.setActivo(true);
            cliente.setTipoCli("Web");
            this.clienteRepository.save(cliente);
        }else {
            Empleado empleado = new Empleado();
            empleado.setDocumentoEm(usuario.getDocumento());
            empleado.setActivo(true);
            empleado.setRol("Empleado");
            this.empleadoRepository.save(empleado);
        }
        //Creo un usuario para no devolverlo con la password
        Usuario usuarioWhitoutPass = usuario;
        usuarioWhitoutPass.setContrasena("Password Confidential");
        return usuarioWhitoutPass;
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
    public Usuario updateUsuario(Usuario usuario) {
        Optional<Usuario> usuarioFind= this.usuarioRepository.findById(Long.valueOf(usuario.getDocumento()));
        if(usuarioFind.get()!=null){
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
