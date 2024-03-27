package com.bank.bank.controller;

import com.bank.bank.model.User;
import com.bank.bank.service.UserService;
import com.bank.bank.service.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private UserService userService = Mockito.mock(UserServiceImpl.class);
    @Autowired
    private UserController userController =  Mockito.mock(UserController.class);
    private MockMvc mockMvc;
    private MockMvc mockMvcServ;

    //mockMvc = MockMvcBuilders.standaloneSetup(usuarioController).build();


    @BeforeEach
    void setUp() {

        //mockMvcServ = MockMvcBuilders.standaloneSetup(userService).build();

        //mockMvc = MockMvcBuilders.standaloneSetup(userController).build();

        mockMvc = MockMvcBuilders.standaloneSetup(userService).build();
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
        given(userController.updateUsuario(user)).willReturn(user);
        given(userController.getAll()).willReturn(usuariosFlux);
        given(userService.getAllUser()).willReturn(usuariosFlux);

    }

    @Test
    void creatingNewUsuario() throws Exception {
        User user = new User();
        user.setDocument(123123123);
        user.setPassword("123123");




      String response =   mockMvc.perform(MockMvcRequestBuilders.post("/user/new")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(user)))
                        .andExpect(MockMvcResultMatchers.
                                        status()
                                .isOk()
                                ).andReturn().getResponse().getContentAsString();
        System.out.println(response);
                        ;

    }

    @Test
    void getAllUsers() throws Exception {
      //  verify(usuarioService, times(1)).getAllUsuarios();
       final var mockResponse = mockMvc.perform(MockMvcRequestBuilders.get("/user/getAllUser"))
                .andExpect(status().isOk())
                //.andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2)))
//                .andDo(result -> {
//                    String responseJson = result.getResponse().getContentAsString();
//                    System.out.println(responseJson);
////                    try {
////                       ObjectMapper objectMapper = new ObjectMapper();
////                        List<User> users = objectMapper.readValue(responseJson, new TypeReference<List<User>>() {});
////
////                        users.forEach(user -> System.out.println(user.toString()));
////                    } catch (IOException e) {
////                        e.printStackTrace();
////                    }
//                })

               ;




    }


    @Test
    void updateUsuario() throws Exception {

        User user = new User();
        user.setDocument(123123123);
        user.setPassword("123123456");


        //when(usuarioService.newUsuario(usuario)).thenReturn(usuario);

//        String response =
        final var mockResponse =mockMvc.perform(MockMvcRequestBuilders.post("/user/updateUser")
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