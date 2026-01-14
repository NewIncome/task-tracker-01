package com.jalfredev.tasktracker.services.impl;

import com.jalfredev.tasktracker.domain.entities.Task;
import com.jalfredev.tasktracker.domain.entities.TaskList;
import com.jalfredev.tasktracker.domain.entities.TaskPriority;
import com.jalfredev.tasktracker.domain.entities.TaskStatus;
import com.jalfredev.tasktracker.repositories.TaskListRepository;
import com.jalfredev.tasktracker.repositories.TaskRepository;
import com.jalfredev.tasktracker.services.TaskService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskServiceImpl implements TaskService {

  private final TaskRepository taskRepository;

  private final TaskListRepository taskListRepository;

  public TaskServiceImpl(TaskRepository taskRepository, TaskListRepository taskListRepository) {
    this.taskRepository = taskRepository;
    this.taskListRepository = taskListRepository;
  }

  @Override
  public List<Task> listTasks(UUID taskListId) {
    return taskRepository.findByTaskListId(taskListId);  //custom query we created with help from JPA
  }

  @Override
  public Task createTask(UUID taskListId, Task task) {
    // Validations, make sure the task.id is null to create not update, and a required title
    if(task.getId() != null) throw new IllegalArgumentException("Task already has an ID!");
    if(task.getTitle() == null || task.getTitle().isBlank()) {
      throw new IllegalArgumentException("Task must have a title");
    }
    // Definition of Defaults
    TaskPriority taskPriority = Optional.ofNullable(task.getPriority())
            .orElse(TaskPriority.MEDIUM);  // Instead of using if conditions
    TaskStatus taskStatus = TaskStatus.OPEN;  // Default status to Open
    TaskList parentTaskList = taskListRepository.findById(taskListId)
              .orElseThrow(() -> new IllegalArgumentException("Invalid Task List ID provided! " + taskListId));
    LocalDateTime now = LocalDateTime.now();

    Task taskToSave = new Task(
        null,
        task.getTitle(),
        task.getDescription(),
        task.getDueDate(),
        taskStatus,
        taskPriority,
        parentTaskList,
        now,
        now
    );

    return taskRepository.save(taskToSave);
  }

}
