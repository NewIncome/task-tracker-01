package com.jalfredev.tasktracker.mappers;

import com.jalfredev.tasktracker.domain.dtos.TaskDto;
import com.jalfredev.tasktracker.domain.entities.Task;

public interface TaskMapper {

  Task fromDTO(TaskDto taskDto);

  TaskDto toDto(Task task);

}
