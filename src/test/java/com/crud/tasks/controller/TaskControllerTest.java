package com.crud.tasks.controller;

import com.crud.tasks.domain.TaskDto;
import com.google.gson.Gson;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringJUnitWebConfig
@WebMvcTest(TaskController.class)
class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskController taskController;

    @Test
    void shouldFetchAllTasks() throws Exception{
        //Given
        List<TaskDto> taskDtoList = new ArrayList<>();
        taskDtoList.add(new TaskDto((long) 1,"testTask","test content"));
        when(taskController.getTasks()).thenReturn(taskDtoList);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/task/getTasks")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].title", Matchers.is("testTask")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].content", Matchers.is("test content")));
    }
    @Test
    void shouldFetchOneTask() throws Exception {
        //Given
        TaskDto taskDto = new TaskDto((long) 1,"testTask","test content");
        when(taskController.getTask((long)1)).thenReturn(taskDto);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/task/getTask/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.is("testTask")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content", Matchers.is("test content")));
    }
    @Test
    void shouldDeleteTask() throws Exception {
        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .delete("/v1/task/deleteTask/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }
    @Test
    void shouldUpdateTask() throws Exception{
        //Given
        TaskDto taskDto = new TaskDto((long) 1,"testTask","test content");
        TaskDto taskDtoUpdated = new TaskDto((long) 2,"testTaskUpdated","test content updated");
        when(taskController.updateTask(any(TaskDto.class))).thenReturn(taskDtoUpdated);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/v1/task/updateTask")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.is("testTaskUpdated")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content", Matchers.is("test content updated")));
    }

    @Test
    void shouldCreateTask() throws Exception{
        //Given
        TaskDto taskDto = new TaskDto((long) 1,"testTask","test content");

        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/v1/task/createTask")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

}