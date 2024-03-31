package com.bank.bank.service;

import com.bank.bank.model.Task;
import com.bank.bank.repository.TaskRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TaskServiceImpl implements TaskService{

    TaskRepository taskRepository;
    @Override
    public Flux<Task> getAllTasks() {
        return Flux.fromIterable(taskRepository.findAll());
    }

    @Override
    public Mono<Task> getTaskById(int id) {
        return Mono.justOrEmpty(taskRepository.findById(Long.valueOf(id)));
    }

    @Override
    public Mono<Task> saveTask(Task task) {
        return Mono.just(taskRepository.save(task));
    }

    @Override
    public Mono<Task> updateTask(Task task) {
        return getTaskById(task.getId())
                .flatMap(existingTask -> {
                    existingTask.setDate_task(task.getDate_task());
                    existingTask.setEmployee_document(task.getEmployee_document());
                    existingTask.setDescription(task.getDescription());
                    return Mono.just(taskRepository.save(existingTask));
                });
    }

    @Override
    public Mono<Boolean> deleteTask(int id) {
        return getTaskById(id)
                .flatMap(existingTask -> {
                    taskRepository.delete(existingTask);
                    return Mono.just(true);
                })
                .defaultIfEmpty(false);
    }
}
