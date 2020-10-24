package com.crud.tasks.controller;

import com.crud.tasks.domain.TaskDto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/task")
public class TaskController {

    @GetMapping(value = "getTasks")
    public List<TaskDto> getTasks(){
        return new ArrayList<>();
    }

    @GetMapping(value = "getTask/{taskId}")
    public TaskDto getTask(@PathVariable Long taskId){
        return new TaskDto((long)1,"Test Task","Test Task content");
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
