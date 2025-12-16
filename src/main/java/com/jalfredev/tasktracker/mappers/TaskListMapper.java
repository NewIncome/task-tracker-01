package com.jalfredev.tasktracker.mappers;

import com.jalfredev.tasktracker.domain.dtos.TaskListDto;
import com.jalfredev.tasktracker.domain.entities.TaskList;

public interface TaskListMapper {

  TaskList fromDto(TaskListDto taskListDto);

  TaskListDto toDto(TaskList taskList);

}
