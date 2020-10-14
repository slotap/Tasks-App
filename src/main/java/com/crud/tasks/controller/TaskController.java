package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public TaskDto getTask(@PathVariable Long taskId){
        Task task = dbService.findTaskById(taskId);
        return taskMapper.mapToTask(task);
    }

    @DeleteMapping(value = "deleteTask/{taskId}")
    public void deleteTask(@PathVariable Long taskId){

    }

    @PutMapping(value = "updateTask")
    public TaskDto updateTask(TaskDto task){
        return new TaskDto(1L,"Updated task","Updated task content");
    }

    @PostMapping(value = "createTask")
    public void createTask(TaskDto taskDto){

    }

}
