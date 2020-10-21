package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/task")
public class TaskController {

    private final TaskMapper taskMapper;
    private final DbService dbService;

    @Autowired
    public TaskController(TaskMapper taskMapper, DbService dbService) {
        this.taskMapper = taskMapper;
        this.dbService = dbService;
    }

    @GetMapping(value = "getTasks")
    public List<TaskDto> getTasks(){
        List<Task> tasks = dbService.getAllTasks();
        return taskMapper.mapToTaskDtoList(tasks);
    }

    @GetMapping(value = "getTask/{taskId}")
    public TaskDto getTask(@PathVariable Long taskId)throws TaskNotFoundException{
        return taskMapper.mapToTask(dbService.getTask(taskId).orElseThrow(TaskNotFoundException::new));
    }

    @DeleteMapping(value = "deleteTask/{taskId}")
    public void deleteTask(@PathVariable Long taskId){
        dbService.deleteTask(taskId);
    }

    @PutMapping(value = "updateTask", consumes = MediaType.APPLICATION_JSON_VALUE)
    public TaskDto updateTask(@RequestBody TaskDto taskDto){
        Task task = taskMapper.mapToTaskDto(taskDto);
        Task savedTask = dbService.saveTask(task);
        return taskMapper.mapToTask(savedTask);
    }

    @PostMapping(value = "createTask", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createTask(@RequestBody TaskDto taskDto){
        Task task = taskMapper.mapToTaskDto(taskDto);
        dbService.saveTask(task);
    }
}
