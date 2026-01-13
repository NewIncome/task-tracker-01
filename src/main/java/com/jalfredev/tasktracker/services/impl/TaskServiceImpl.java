package com.jalfredev.tasktracker.services.impl;

import com.jalfredev.tasktracker.domain.entities.Task;
import com.jalfredev.tasktracker.repositories.TaskRepository;
import com.jalfredev.tasktracker.services.TaskService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TaskServiceImpl implements TaskService {

  private final TaskRepository taskRepository;

  public TaskServiceImpl(TaskRepository taskRepository) {
    this.taskRepository = taskRepository;
  }

  @Override
  public List<Task> listTasks(UUID taskListId) {
    return taskRepository.findByTaskListId(taskListId);  //custom query we created with help from JPA
  }

}
