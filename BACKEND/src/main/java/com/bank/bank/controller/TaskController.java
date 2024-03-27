package com.bank.bank.controller;

import com.bank.bank.model.Task;
import com.bank.bank.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    TaskService taskService;

    @GetMapping("/getAll")
    public Flux<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/{id}")
    public Mono<Task> getTaskById(@PathVariable int id) {
        return taskService.getTaskById(id);
    }

    @PostMapping("/new")
    public Mono<Task> saveTask(@RequestBody Task task) {
        return taskService.saveTask(task);
    }

    @PutMapping("/updateTask")
    public Mono<Task> updateTask(@RequestBody Task task) {
        return taskService.updateTask(task);
    }

    @DeleteMapping("/{id}")
    public Mono<Boolean> deleteTask(@PathVariable int id) {
        return taskService.deleteTask(id);
    }


}
