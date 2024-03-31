package com.bank.bank.service;

import com.bank.bank.model.Client;
import com.bank.bank.model.Employee;
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
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    void shouldCreateNewUserAndRetunData() {

        User newUser = new User();
        newUser.setDocument(12345678);
        newUser.setPassword("password");
        newUser.setUser_type("Cliente");
        when(passwordEncoder.encode(newUser.getPassword())).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenReturn(newUser);
        when(clientRepository.save(any(Client.class))).thenReturn(new Client());

        Mono<User> result = userService.newUser(newUser);
        assertNotNull(result.block());
        assertEquals(12345678,result.block().getDocument());
    }

    @Test
    void shouldGetAllUsersAndReturnTypeClientAndPasswordConfidential() {
        List<User> userList = new ArrayList<>();

        User user = new User();
        user.setDocument(123123123);
        user.setPassword("123123");
        user.setUser_type("Cliente");

        User user2 = new User();
        user2.setDocument(543214321);
        user2.setPassword("contrasena123");
        user2.setUser_type("Cliente");

        userList.add(user);
        userList.add(user2);

         when(userRepository.findAll()).thenReturn(userList);

          Flux<User> result = userService.getAllUser();

        assertEquals(userList.size(), result.collectList().block().size());
        assertTrue(result.collectList().block().stream().allMatch(listedUser -> listedUser.getPassword().equals("Password confidential")));
        assertTrue(result.collectList().block().stream().allMatch(listedUser -> listedUser.getUser_type().equals("Cliente")));
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void shouldGetUserByDocument() {
        int userDoc = 123123123;
        User user2 = new User();
        user2.setDocument(userDoc);
        user2.setPassword("contrasena123");
        user2.setUser_type("Cliente");


        when(userRepository.getReferenceById(Long.valueOf(userDoc))).thenReturn(user2);
        User result = userService.getUserById(Long.valueOf(userDoc));

        assertEquals(userDoc, result.getDocument());
        verify(userRepository, times(1)).getReferenceById(Long.valueOf(userDoc));
    }

    @Test
    void shouldCreateUserClientAndReturnPasswordConfidential() {
        User newUser = new User();
        newUser.setDocument(1234567890);
        newUser.setUser_type("Cliente");
        newUser.setPassword("password123");

        when(passwordEncoder.encode(newUser.getPassword())).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenReturn(newUser);
        when(clientRepository.save(any(Client.class))).thenReturn(new Client());

        Mono<User> result = userService.newUser(newUser);

        assertNotNull(result.block());
        assertEquals("Password Confidential", result.block().getPassword());
       // assertEquals("Cliente", result.block().getUser_type()); bien pero dejo solo un resultado
        verify(userRepository, times(2)).save(any(User.class));
        verify(clientRepository, times(2)).save(any(Client.class));
    }

    @Test
    void shouldCreateUserEmployeeAndReturnPasswordConfidential() {
        User newUser = new User();
        newUser.setDocument(1234567890);
        newUser.setUser_type("Employee");
        newUser.setPassword("password123");

        when(passwordEncoder.encode(newUser.getPassword())).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenReturn(newUser);
        when(employeeRepository.save(any(Employee.class))).thenReturn(new Employee());

        Mono<User> result = userService.newUser(newUser);

        assertNotNull(result.block());
        assertEquals("Password Confidential", result.block().getPassword());
        // assertEquals("Cliente", result.block().getUser_type()); bien pero dejo solo un resultado
        verify(userRepository, times(2)).save(any(User.class));
        verify(employeeRepository, times(2)).save(any(Employee.class));
    }


    @Test
    void shouldUpdateUser() {
        User user = new User();
        user.setDocument(1234567890);
        user.setEmail("maty@labora.com");
        user.setFull_name("MathiasUser");
        when(userRepository.findById(Long.valueOf(user.getDocument()))).thenReturn(Optional.of(user));
         when(userRepository.save(user)).thenReturn(user);

        User result = userService.updateUser(user);
        assertNotNull(result);
       assertEquals(user.getEmail(), result.getEmail());
        assertEquals(user.getFull_name(), result.getFull_name());
        verify(userRepository, times(1)).findById(Long.valueOf(user.getDocument()));
        verify(userRepository, times(1)).save(user);

    }

    @Test
    void shouldDeleteUserAndReturnTrue() {
        int doc = 51114096;
        boolean result = userService.deleteUser(Long.valueOf(doc));
        assertTrue(result);
        verify(userRepository, times(1)).deleteById(Long.valueOf(doc));
    }
}