package com.jalfredev.tasktracker.mappers.impl;

import com.jalfredev.tasktracker.domain.dtos.TaskDto;
import com.jalfredev.tasktracker.domain.entities.Task;
import com.jalfredev.tasktracker.mappers.TaskMapper;
import org.springframework.stereotype.Component;

@Component
public class TaskMapperImpl implements TaskMapper {

  @Override
  public Task fromDTO(TaskDto taskDto) {
    return new Task(
        taskDto.id(),
        taskDto.title(),
        taskDto.description(),
        taskDto.dueDate(),
        taskDto.status(),
        taskDto.priority(),
        null,
        null,
        null
    );
  }

  @Override
  public TaskDto toDto(Task task) {
    return new TaskDto(
        task.getId(),
        task.getTitle(),
        task.getDescription(),
        task.getDueDate(),
        task.getPriority(),
        task.getStatus()
    );
  }

}
