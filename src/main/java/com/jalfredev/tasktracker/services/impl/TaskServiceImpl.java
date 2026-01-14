package com.jalfredev.tasktracker.services.impl;

import com.jalfredev.tasktracker.domain.entities.Task;
import com.jalfredev.tasktracker.domain.entities.TaskList;
import com.jalfredev.tasktracker.domain.entities.TaskPriority;
import com.jalfredev.tasktracker.domain.entities.TaskStatus;
import com.jalfredev.tasktracker.repositories.TaskListRepository;
import com.jalfredev.tasktracker.repositories.TaskRepository;
import com.jalfredev.tasktracker.services.TaskService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
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

  @Transactional
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

  @Override
  public Optional<Task> findTask(UUID taskListId, UUID taskId) {
    return taskRepository.findByTaskListIdAndId(taskListId, taskId);
  }

  @Transactional
  @Override
  public Task updateTask(UUID taskListId, UUID taskId, Task task) {
    // VALIDATIONS
    if(task.getId() == null) throw new IllegalArgumentException("Task must have an ID!");
    if(!Objects.equals(taskId, task.getId())) throw new IllegalArgumentException("Task ID's do not match");
    if(task.getPriority() == null) throw new IllegalArgumentException("Task must have a valid priority");
    if(task.getStatus() == null) throw new IllegalArgumentException("Task must have a valid status");

    // Get the TaskList from the DB
    Task existingTask = taskRepository.findByTaskListIdAndId(taskListId, taskId)
        .orElseThrow(() -> new IllegalArgumentException("Task not found!"));

    // Set all the information from 'task'
    existingTask.setTitle(task.getTitle());
    existingTask.setDescription(task.getDescription());
    existingTask.setPriority(task.getPriority());
    existingTask.setStatus(task.getStatus());
    existingTask.setDueDate(task.getDueDate());
    existingTask.setUpdated(LocalDateTime.now());

    return taskRepository.save(existingTask);
  }

  @Transactional
  @Override
  public void deleteTask(UUID taskListId, UUID taskId) {
    taskRepository.deleteByTaskListIdAndId(taskListId, taskId);
  }
  /* @Transactional annotation, is necessary when there are multiple call to the DB(-between modifications-)
      it ensures the 'delete' happens within A Database Transaction,
      so if any part of that operation-transaction the operation is brought back,
      to maintain DataBase consistency.
      It's important when we delete data or when we attempt to make any changes(update)
      //to have encapsulate many DB-calls as one transaction

      If not used we get a:  jakarta.persistence. TransactionRequiredException
   */

}
