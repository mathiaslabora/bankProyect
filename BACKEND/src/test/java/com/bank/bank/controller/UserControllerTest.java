package com.bank.bank.controller;

import com.bank.bank.model.User;
import com.bank.bank.service.UserService;
import com.bank.bank.service.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.List;

import static com.bank.bank.util.UtilsTools.asJsonString;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@WebMvcTest(controllers = UsuarioController.class)
@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    private UserService userService = Mockito.mock(UserServiceImpl.class);

    private UserController userController =  Mockito.mock(UserController.class);
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


        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();


        User user = new User();
        user.setDocument(123123123);
        user.setPassword("123123");

        User user2 = new User();
        user2.setDocument(543214321);
        user2.setPassword("contrasena123");

        List<User> usuariosList = Arrays.asList(
                user, user2
        );
        Iterable<User> usuariosIterable = usuariosList;
        given(userController.getAll()).willReturn(Flux.fromIterable(usuariosIterable));
        given(userController.updateUsuario(user)).willReturn(user);
    }

    @Test
    void creatingNewUsuario() throws Exception {
        User user = new User();
        user.setDocument(123123123);
        user.setPassword("123123");


        //when(usuarioService.newUsuario(usuario)).thenReturn(usuario);

        mockMvc.perform(MockMvcRequestBuilders.post("/Usuario/new")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(user)))
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
    void updateUsuario() throws Exception {

        User user = new User();
        user.setDocument(123123123);
        user.setPassword("123123456");


        //when(usuarioService.newUsuario(usuario)).thenReturn(usuario);

//        String response =
        final var mockResponse =mockMvc.perform(MockMvcRequestBuilders.post("/Usuario/updateUsuario")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(user)))
                        .andExpect(MockMvcResultMatchers.
                        status()
                        .isOk()
                ).andReturn().getResponse().getContentAsString()
        ;
       System.out.println("Respuesta: " + mockResponse);

    }

    @Test
    void deleteUsuario() {
    }

    @Test
    void login() {
    }
}