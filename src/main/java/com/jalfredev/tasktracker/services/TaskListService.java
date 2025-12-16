package com.jalfredev.tasktracker.services;

import com.jalfredev.tasktracker.domain.entities.TaskList;

import java.util.List;

public interface TaskListService {

  List<TaskList> listTaskLists();

}
