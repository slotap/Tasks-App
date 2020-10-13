package com.crud.tasks.controller;

import com.crud.tasks.domain.TaskDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/task")
public class TaskController {
    public List<TaskDto> getTasks(){
        return new ArrayList<>();
    }
    public TaskDto getTask(Long taskId){
        return new TaskDto((long)1,"Test Task","Test Task content");
    }
    public void deleteTask(Long taskId){

    }
    public TaskDto updateTask(TaskDto task){
        return new TaskDto(1L,"Updated task","Updated task content");
    }
    public void createTask(TaskDto taskDto){

    }

}
