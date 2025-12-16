package com.jalfredev.tasktracker.mappers.impl;

import com.jalfredev.tasktracker.domain.dtos.TaskListDto;
import com.jalfredev.tasktracker.domain.entities.Task;
import com.jalfredev.tasktracker.domain.entities.TaskList;
import com.jalfredev.tasktracker.domain.entities.TaskStatus;
import com.jalfredev.tasktracker.mappers.TaskListMapper;
import com.jalfredev.tasktracker.mappers.TaskMapper;

import java.util.List;
import java.util.Optional;

public class TaskListMapperImpl implements TaskListMapper {
  /* Will handle:
   * - conversion of the tasksCollection
   * - calculate Task count
   * - calculate progress
   */

  private final TaskMapper taskMapper;

  public TaskListMapperImpl(TaskMapper taskMapper) {
    this.taskMapper = taskMapper;
  }


  @Override
  public TaskList fromDto(TaskListDto taskListDto) {
    return new TaskList(
        taskListDto.id(),
        taskListDto.title(),
        taskListDto.description(),
        Optional.ofNullable(taskListDto.tasks())          //in case we get null
                .map(tasks -> tasks.stream()  //not null case
                        .map(taskMapper::fromDTO)
                        .toList()
                ).orElse(null),                     //null case
        null, //because it'll be used for creation, mainly, from the HttpRequest
        null
    );
  }

  @Override
  public TaskListDto toDto(TaskList taskList) {
    return new TaskListDto(
        taskList.getId(),
        taskList.getTitle(),
        taskList.getDescription(),
        Optional.ofNullable(taskList.getTasks())
                  .map(List::size)
                  .orElse(0),
        calculateTaskListProgress(taskList.getTasks()),
        Optional.ofNullable(taskList.getTasks())
                  .map(tasks -> tasks.stream()
                          .map(taskMapper::toDto)
                          .toList()
                  ).orElse(null)
    );
  }

  private Double calculateTaskListProgress(List<Task> tasks) {
    if(tasks == null) return null;

    //check how many tasks are closed
    long closedTaskCount = tasks.stream().filter(task ->
        task.getStatus() == TaskStatus.CLOSED
    ).count();

    return (double) closedTaskCount / tasks.size(); //used casting to return a double
  }

}
