package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class TaskMapperTest {

        TaskMapper taskMapper = new TaskMapper();

    @Test
    void mapToTaskDtoTest() {
        //Given
        Task testedTask = new Task((long)1,"test","test");
        TaskDto taskDto = new TaskDto((long)1,"test","test");

        //When
        Task mappedTask = taskMapper.mapToTaskDto(taskDto);

        //Then
        assertEquals(mappedTask.getId(),testedTask.getId());
        assertEquals(mappedTask.getContent(),testedTask.getContent());
    }

    @Test
    void mapToTaskTest() {
        //Given
        Task testedTask = new Task((long)1,"test","test");
        TaskDto taskDto = new TaskDto((long)1,"test","test");

        //When
        TaskDto mappedTask = taskMapper.mapToTask(testedTask);

        //Then
        assertEquals(mappedTask.getId(),taskDto.getId());
        assertEquals(mappedTask.getContent(),taskDto.getContent());
    }

    @Test
    void mapToTaskDtoListTest() {
        //Given
        List<Task> taskList = new ArrayList<>();
        taskList.add(new Task((long)1,"test","test"));

        //When
        List<TaskDto> mappedTaskList = taskMapper.mapToTaskDtoList(taskList);

        //Then
        assertThat(mappedTaskList.size()).isEqualTo(1);
        assertThat(mappedTaskList.get(0).getId()).isEqualTo(1);
    }
}