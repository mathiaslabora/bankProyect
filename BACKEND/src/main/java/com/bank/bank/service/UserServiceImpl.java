package com.bank.bank.service;

import com.bank.bank.model.Client;
import com.bank.bank.model.Employee;
import com.bank.bank.model.User;
import com.bank.bank.repository.ClientRepository;
import com.bank.bank.repository.EmployeeRepository;
import com.bank.bank.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public Mono<User> newUser(User newUser) {
        return Mono.just(newUser)
                .flatMap(user -> {
                    String encodePass = passwordEncoder.encode(user.getPassword());
                    user.setPassword(encodePass);
                   // System.out.println("Encoded: " + encodePass);
                    return Mono.just(user);
                })
                .map(user -> this.userRepository.save(user))
                .flatMap(user -> {
                    if (user.getUser_type().equals("Cliente")){
                        Client client = new Client();
                        client.setDocument_cli(user.getDocument());
                        client.setActive(true);
                        client.setRole("Web");
                        return Mono.just(client)
                                .map(clientRepository::save)
                                .thenReturn(user);
                    } else {
                        Employee employee = new Employee();
                        employee.setDocument_emp(user.getDocument());
                        employee.setActive(true);
                        employee.setRole("Empleado");
                        return Mono.just(employee)
                                .map(employeeRepository::save)
                                .thenReturn(user);
                    }
                })
                .map(user -> {
                    //Creo un usuario para no devolverlo con la password
                    User userWithoutPass = user;
                    userWithoutPass.setPassword("Password Confidential");
                    return userWithoutPass;
                })
                .onErrorResume(error -> {
                    System.out.println("Error creating user: " + error.getMessage());
                    return Mono.error(error);
                });
    }

    @Override
    public Flux<User> getAllUser() {

        return Flux.fromIterable(this.userRepository.findAll())
                .map(user -> {
                    user.setPassword("Password confidential");
                    return user;
                });
    }

    @Override
    public User getUserById(Long id) {
        return this.userRepository.getReferenceById(id);
    }

    @Override
    public User updateUser(User user) {
        Optional<User> usuarioFind= this.userRepository.findById(Long.valueOf(user.getDocument()));
        if(usuarioFind.get()!=null){
            usuarioFind.get().setPassword(user.getPassword());
            usuarioFind.get().setEmail(user.getEmail());
            usuarioFind.get().setAddress(user.getAddress());
            usuarioFind.get().setUser_type(usuarioFind.get().getUser_type());//no permito la modificacion del tipo de usuario
            usuarioFind.get().setFull_name(user.getFull_name());
            return this.userRepository.save(usuarioFind.get());
        }
        return null;
    }

    @Override
    public boolean deleteUser(Long id) {
        this.userRepository.deleteById(id);
        return true;
    }
}
