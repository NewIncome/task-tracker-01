package com.jalfredev.tasktracker.domain.dtos;

import com.jalfredev.tasktracker.domain.entities.TaskPriority;
import com.jalfredev.tasktracker.domain.entities.TaskStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record TaskDto(
          UUID id,
          String title,
          String description,
          LocalDateTime dueDate,
          TaskPriority priority,
          TaskStatus status
  /* • created & updated, won't be added here since those are more internal concerns
   * • taskList, will be handled through URL's in our RestAPI
   */
) {}
