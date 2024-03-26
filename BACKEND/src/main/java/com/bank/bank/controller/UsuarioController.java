package com.bank.bank.controller;

import com.bank.bank.model.AuthResponseLogin;
import com.bank.bank.model.Usuario;
import com.bank.bank.service.AuthService;
import com.bank.bank.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Usuario")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private AuthService authService;
    @PostMapping("/new")
    public Usuario newUsuario(@RequestBody Usuario newUsuario){
        return this.usuarioService.newUsuario(newUsuario);
    }
    @GetMapping("/getAllUsuarios")
    public Iterable<Usuario> getAll(){
        return usuarioService.getAllUsuarios();
    }

    @PostMapping("/updateUsuario")
    public Usuario updateUsuario(@RequestBody Usuario usuario){
        return this.usuarioService.updateUsuario(usuario);
    }

    @PostMapping(value = "/deleteUsuario/{id}")
    public boolean deleteUsuario(@PathVariable(value="id") Long id){
        return this.usuarioService.deleteUsuario(id);
    }

    @PostMapping("/login")
    public AuthResponseLogin login(@RequestBody Usuario loginUsuario){
        return this.authService.login( Long.valueOf(loginUsuario.getDocumento()), loginUsuario.getContrasena());
    }

}
