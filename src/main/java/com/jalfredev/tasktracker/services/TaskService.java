package com.jalfredev.tasktracker.services;

import com.jalfredev.tasktracker.domain.entities.Task;

import java.util.List;
import java.util.UUID;

public interface TaskService {

  List<Task> listTasks(UUID taskListId);

}
