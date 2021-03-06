package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DbService {

    private final TaskRepository taskRepository;

    public DbService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Optional<Task> getTask(final Long id){
        return taskRepository.findTaskById(id);
    }

    public Task saveTask(final Task task) {
        return taskRepository.save(task);
    }

    public void deleteTask(final Long id){
        taskRepository.deleteById(id);
    }
}
