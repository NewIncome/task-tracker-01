/**
 * TaskList EndPoints
 *  GET     /task-lists                   List Task Lists
 *  POST    /task-lists                   Create Task Lists
 *  GET     /task-lists/{task_list_id}    Get Task List by ID
 *  PUT     /task-lists/{task_list_id}    Update Task list
 *  DELETE  /task-lists/{task_list_id}    Delete Task List
 */
package com.jalfredev.tasktracker.controllers;

import com.jalfredev.tasktracker.domain.dtos.TaskListDto;
import com.jalfredev.tasktracker.mappers.TaskListMapper;
import com.jalfredev.tasktracker.services.TaskListService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping(path = "task-list")
public class TaskListController {

  private final TaskListService taskListService;

  private final TaskListMapper taskListMapper;

  public TaskListController(TaskListService taskListService, TaskListMapper taskListMapper) {
    this.taskListService = taskListService;
    this.taskListMapper = taskListMapper;
  }

  @GetMapping
  public List<TaskListDto> listTaskLists() {
    return taskListService.listTaskLists()
            .stream()
            .map(taskListMapper::toDto)
            .toList();
  }

}
