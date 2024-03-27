package com.bank.bank.service;

import com.bank.bank.model.Task;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TaskService {

    Flux<Task> getAllTasks();

    Mono<Task> getTaskById(int id);

    Mono<Task> saveTask(Task task);

    Mono<Task> updateTask(Task task);

    Mono<Boolean> deleteTask(int id);
}
