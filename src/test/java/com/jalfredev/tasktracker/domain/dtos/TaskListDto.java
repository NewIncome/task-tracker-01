package com.jalfredev.tasktracker.domain.dtos;

import java.util.List;
import java.util.UUID;

public record TaskListDto(
        UUID id,
        String title,
        String description,
        Integer count,    //count of tasks in the list
        Double progress,  //number between 0-1 to represent how many tasks have been completed
        List<TaskDto> tasks
        /* excluded internal concerns like timestamps
         */
) {
}
