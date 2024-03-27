package com.bank.bank.service;

import com.bank.bank.model.Client;
import com.bank.bank.model.User;
import com.bank.bank.repository.ClientRepository;
import com.bank.bank.repository.EmployeeRepository;
import com.bank.bank.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    @Autowired
    MockMvc mockMvc;
    @Mock
    private UserRepository userRepository;

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private EmployeeRepository employeeRepository;

   @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userService;
    @BeforeEach
    void setUp() {

        MockitoAnnotations.initMocks(this);


    }
    @Test
    void newUsuario() {

        User newUser = new User();
        newUser.setDocument(123456789);
        newUser.setPassword("password");
        newUser.setUser_type("Cliente");

        Client client = new Client();
        client.setDocument_cli(newUser.getDocument());
        client.setActive(true);
        client.setRole("Web");
//        when(passwordEncoder.encode(newUser.getPassword())).thenReturn("Password Confidential");
//        when(userRepository.save(newUser)).thenReturn(newUser);
//        when(clientRepository.save(client)).thenReturn(client);
        StepVerifier.create(userService.newUser(newUser))
                .expectNextMatches(savedUser -> savedUser.getPassword().equals("Password Confidential"))
                .expectComplete();
    }

    @Test
    void getAllUsuarios() {
    }

    @Test
    void getUsuarioById() {
    }

    @Test
    void createUsuario() {
    }

    @Test
    void updateUsuario() {
    }

    @Test
    void deleteUsuario() {
    }
}