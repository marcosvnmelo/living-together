package org.github.marcosvnmelo.livingtogether.task;

import java.util.List;
import java.util.Optional;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Mutation;
import org.eclipse.microprofile.graphql.Name;
import org.eclipse.microprofile.graphql.Query;
import org.github.marcosvnmelo.livingtogether.core.error.BusinessException;
import org.github.marcosvnmelo.livingtogether.task.dto.CreateTaskDto;
import org.github.marcosvnmelo.livingtogether.task.dto.UpdateTaskDto;

@GraphQLApi
public class TaskResource {
  @Inject
  TaskMapper mapper;

  @Inject
  TaskRepository repository;

  @Mutation
  @Transactional
  public Task createTask(@NotNull @Name("data") CreateTaskDto dto) {
    final Task task = mapper.taskFromCreateTaskDto(dto);

    if (task.getHouse() == null) {
      throw new BusinessException("House not found");
    }

    task.persist();

    return task;
  }

  @Query
  public List<Task> listAllTask() {
    return repository.findAll().list();
  }

  @Query
  public Task findTaskById(@NotNull @Name("id") Long id) {
    final Optional<Task> task = repository.findByIdOptional(id);

    return task.orElseThrow(() -> new BusinessException("Task not found"));
  }

  @Mutation
  @Transactional
  public Task updateTask(@NotNull @Name("data") UpdateTaskDto dto) {
    final Optional<Task> databaseTask = repository.findByIdOptional(dto.getId());

    if (databaseTask.isEmpty()) {
      throw new BusinessException("Task not found");
    }

    final var task = databaseTask.get();

    mapper.updateTaskFromUpdateTaskDto(dto, task);

    task.persistAndFlush();

    return task;
  }

  @Mutation
  @Transactional
  public Boolean deleteTask(@NotNull @Name("id") Long id) {
    final Boolean isDeleted = repository.deleteById(id);

    if (!isDeleted) {
      throw new BusinessException("Error deleting task");
    }

    return isDeleted;
  }
}
