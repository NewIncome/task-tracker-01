/**
 * TaskList EndPoints
 *  GET     /task-lists/{task_list_id}/tasks              List TASKS
 *  POST    /task-lists/{task_list_id}/tasks              Create TASK
 *  GET     /task-lists/{task_list_id}/tasks/{task_id}    Get TASK by ID
 *  PUT     /task-lists/{task_list_id}/tasks/{task_id}    Update TASK
 *  DELETE  /task-lists/{task_list_id}/tasks/{task_id}    Delete TASK
 */
package com.jalfredev.tasktracker.controllers;

import com.jalfredev.tasktracker.domain.dtos.TaskDto;
import com.jalfredev.tasktracker.domain.entities.Task;
import com.jalfredev.tasktracker.mappers.TaskMapper;
import com.jalfredev.tasktracker.services.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/task-lists/{task_list_id}/tasks")
public class TaskController {

  private final TaskService taskService;

  private final TaskMapper taskMapper;

  public TaskController(TaskService taskService, TaskMapper taskMapper) {
    this.taskService = taskService;
    this.taskMapper = taskMapper;
  }

  @GetMapping
  public List<TaskDto> listTasks(@PathVariable("task_list_id") UUID taskListId) {
    // Here the  @PathVariable("task_list_id") exact definition is needed because of
    // the complexity of the path, it's not straight forward
    return taskService.listTasks(taskListId)
                        .stream()
                        .map(taskMapper::toDto)
                        .toList();
  }

}
