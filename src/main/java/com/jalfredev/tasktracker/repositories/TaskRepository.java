package com.jalfredev.tasktracker.repositories;

import com.jalfredev.tasktracker.domain.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TaskRepository extends JpaRepository<Task, UUID> {

  List<Task> findByTaskList(UUID taskListId);  //naming is very important here

  Optional<Task> findByTaskListIdAndId(UUID taskListId, UUID id);

}
