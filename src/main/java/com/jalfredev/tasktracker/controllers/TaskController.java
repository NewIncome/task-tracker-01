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
import java.util.Optional;
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

  @PostMapping
  public TaskDto createTask(@PathVariable("task_list_id") UUID taskListId,
                            @RequestBody TaskDto taskDto) {
    Task createdTaskEntity = taskService.createTask(
                                    taskListId,
                                    taskMapper.fromDTO(taskDto)
    );

    return taskMapper.toDto(createdTaskEntity);
  }

  @GetMapping("/{task_id}")
  public Optional<TaskDto> getTask(@PathVariable("task_list_id") UUID taskListId,
                                   @PathVariable("task_id") UUID taskId) {
    return taskService.findTask(taskListId, taskId).map(taskMapper::toDto);  // To instead of returning an Optional<TaskEntity> it's an Optional<TaskDto>
  }

  @PutMapping("/{task_id}")
  public TaskDto updateTask(@PathVariable("task_list_id") UUID taskListId,
                            @PathVariable("task_id") UUID taskId,
                            @RequestBody TaskDto task) {
    return taskMapper.toDto(taskService.updateTask(
                                taskListId,
                                taskId,
                                taskMapper.fromDTO(task)));
  }

}
