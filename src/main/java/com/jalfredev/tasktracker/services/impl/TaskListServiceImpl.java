package com.jalfredev.tasktracker.services.impl;

import com.jalfredev.tasktracker.domain.entities.TaskList;
import com.jalfredev.tasktracker.repositories.TaskListRepository;
import com.jalfredev.tasktracker.services.TaskListService;
import org.springframework.stereotype.Service;

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

}
