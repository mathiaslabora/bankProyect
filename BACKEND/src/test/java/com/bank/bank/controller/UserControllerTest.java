package com.bank.bank.controller;

import com.bank.bank.model.User;
import com.bank.bank.repository.UserRepository;
import com.bank.bank.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

import static com.bank.bank.util.UtilsTools.asJsonString;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@WebMvcTest(controllers = UsuarioController.class)
@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @InjectMocks
    UserController userController;
    @MockBean
    UserService userService;
    @Autowired
    private ObjectMapper objectMapper;
    @BeforeEach
    void setUp(@Mock UserRepository userRepository) {

        MockitoAnnotations.initMocks(this);

      //  mockMvc = MockMvcBuilders.standaloneSetup(userController).build();

//        User user = new User();
//        user.setDocument(123123123);
//        user.setPassword("123123");
//
//        User user2 = new User();
//        user2.setDocument(543214321);
//        user2.setPassword("contrasena123");
//
//        List<User> usuariosList = Arrays.asList(
//                user, user2
//        );
//        Iterable<User> usuariosIterable = usuariosList;
//        Flux<User> usuariosFlux = Flux.fromIterable(usuariosList);
//        given(userController.updateUsuario(user)).willReturn(user);
//        given(userController.getAll()).willReturn(usuariosFlux);
//        given(userService.getAllUser()).willReturn(usuariosFlux);

    }

    @Test
    void creatingNewUser() throws Exception {
        User user2 = new User();
        user2.setDocument(123123123);
        user2.setPassword("123123123");

        //when(userService.newUser(any(User.class))).thenReturn(Mono.just(user2));
        //given(userService.newUser(any(User.class))).willReturn(Mono.just(user2));
        given(userService.newUser(ArgumentMatchers.any())).willAnswer(userx -> Mono.just(userx.getArgument(0)));

        ResultActions response = mockMvc.perform(post("/user/new")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user2)))
                        ;
        response.andExpect(status().isOk())
                .andDo(result -> {
                    System.out.println(asJsonString(user2));
                    System.out.println("Respuesta" + result.getResponse().getContentType());
                });

    }

    @Test
    void getAllUsers() throws Exception {
      //  verify(usuarioService, times(1)).getAllUsuarios();
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
        Flux<User> usuariosFlux = Flux.fromIterable(usuariosList);
        when(userService.getAllUser()).thenReturn(usuariosFlux);
       final var mockResponse = mockMvc.perform(get("/user/getAllUser"))
                .andExpect(status().isOk())
               .andDo(result -> {
                   System.out.println("Respuesta" + result.getResponse().getContentAsByteArray().length);
                   System.out.println("Respuesta" + MockMvcResultMatchers.jsonPath("$.document", CoreMatchers.is(user.getDocument())));
               });

               ;




    }


    @Test
    void updateUsuario() throws Exception {

        User user = new User();
        user.setDocument(123123123);
        user.setPassword("123123456");


        //when(usuarioService.newUsuario(usuario)).thenReturn(usuario);

//        String response =
        final var mockResponse =mockMvc.perform(post("/user/updateUser")
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