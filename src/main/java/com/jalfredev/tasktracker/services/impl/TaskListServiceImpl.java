package com.jalfredev.tasktracker.services.impl;

import com.jalfredev.tasktracker.domain.entities.TaskList;
import com.jalfredev.tasktracker.repositories.TaskListRepository;
import com.jalfredev.tasktracker.services.TaskListService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TaskListServiceImpl implements TaskListService {

  private final TaskListRepository taskListRepository;

  public TaskListServiceImpl(TaskListRepository taskListRepository) {
    this.taskListRepository = taskListRepository;
  }

  @Override
  public List<TaskList> listTaskLists() {
    return taskListRepository.findAll();
  }

  @Override
  public TaskList createTaskList(TaskList taskList) {
    //specific errors for Title validations
    if(taskList.getId() != null)
      throw new IllegalArgumentException("Task list already has an ID!");

    if(taskList.getTitle() == null || taskList.getTitle().isBlank())
      throw new IllegalArgumentException("Task list title must be present!");

    LocalDateTime now = LocalDateTime.now();
    return taskListRepository.save(new TaskList(
        null,
        taskList.getTitle(),
        taskList.getDescription(),
        null,
        now,
        now
    ));
  }

}
