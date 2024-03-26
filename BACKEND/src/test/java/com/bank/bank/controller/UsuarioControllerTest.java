package com.bank.bank.controller;

import com.bank.bank.model.Usuario;
import com.bank.bank.repository.UsuarioRepository;
import com.bank.bank.service.AuthService;
import com.bank.bank.service.UsuarioService;
import com.bank.bank.service.UsuarioServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.bank.bank.util.UtilsTools.asJsonString;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@WebMvcTest(controllers = UsuarioController.class)
@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
class UsuarioControllerTest {

    private UsuarioService usuarioService = Mockito.mock(UsuarioServiceImpl.class);

    private UsuarioController usuarioController =  Mockito.mock(UsuarioController.class);
    private MockMvc mockMvc;

    //mockMvc = MockMvcBuilders.standaloneSetup(usuarioController).build();


    @BeforeEach
    void setUp() {

//        Usuario usuario = new Usuario();
//        usuario.setDocumento(123123123);
//        usuario.setContrasena("123123");
//        //Mockito.when(usuarioRepositoryMock.save(usuario)).thenReturn(usuario);
//        //UsuarioController usuarioController = new UsuarioController();
//        UsuarioController usuarioController = Mockito.mock(UsuarioController.class);
//    mockMvc = MockMvcBuilders.standaloneSetup(usuarioController).build();


        mockMvc = MockMvcBuilders.standaloneSetup(usuarioController).build();


        Usuario usuario = new Usuario();
        usuario.setDocumento(123123123);
        usuario.setContrasena("123123");

        Usuario usuario2 = new Usuario();
        usuario2.setDocumento(543214321);
        usuario2.setContrasena("contrasena123");

        List<Usuario> usuariosList = Arrays.asList(
                usuario, usuario2
        );
        Iterable<Usuario> usuariosIterable = usuariosList;
        given(usuarioController.getAll()).willReturn(usuariosIterable);

    }

    @Test
    void creatingNewUsuario() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setDocumento(123123123);
        usuario.setContrasena("123123");


        //when(usuarioService.newUsuario(usuario)).thenReturn(usuario);

        mockMvc.perform(MockMvcRequestBuilders.post("/Usuario/new")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(usuario)))
                        .andExpect(MockMvcResultMatchers.
                                        status()
                                .isOk()
                                )

                        ;

       // verify(usuarioController, times(1)).newUsuario(usuario);

    }

    @Test
    void gettingAllUsers() throws Exception {

      //  verify(usuarioService, times(1)).getAllUsuarios();
       final var mockResponse = mockMvc.perform(MockMvcRequestBuilders.get("/Usuario/getAllUsuarios"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2)))
                .andReturn()
                .getResponse().getContentAsString();
        // assertThat (mockResponse).isArray().size().isEqualTo(2)
                //.andExpect(MockMvcResultMatchers.jsonPath("$[0].documento").value(123123123))
        ;
        System.out.println(mockResponse);
       // verifyNoMoreInteractions(usuarioService);
    }

    @Test
    void updateUsuario() {
    }

    @Test
    void deleteUsuario() {
    }

    @Test
    void login() {
    }
}