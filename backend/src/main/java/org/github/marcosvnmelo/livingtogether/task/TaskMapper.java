package org.github.marcosvnmelo.livingtogether.task;

import javax.inject.Inject;
import org.github.marcosvnmelo.livingtogether.house.HouseRepository;
import org.github.marcosvnmelo.livingtogether.task.dto.CreateTaskDto;
import org.github.marcosvnmelo.livingtogether.task.dto.UpdateTaskDto;
import org.github.marcosvnmelo.livingtogether.user.UserRepository;
import org.mapstruct.Mapper;
import org.mapstruct.MapperConfig;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "cdi")
@MapperConfig(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public abstract class TaskMapper {
  @Inject
  HouseRepository houseRepository;
  @Inject
  UserRepository userRepository;

  @Mapping(target = "house", expression = "java(houseRepository.findById(dto.getHouseId()))")
  @Mapping(target = "responsible",
      expression = "java((dto.getResponsibleUserId() != null ? userRepository.findById(dto.getResponsibleUserId()) : null))")
  public abstract Task taskFromCreateTaskDto(CreateTaskDto dto);

  @Mapping(target = "responsible",
      expression = "java((dto.getResponsibleUserId() != null ? userRepository.findById(dto.getResponsibleUserId()) : null))")
  @Mapping(target = "done",
      nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  public abstract void updateTaskFromUpdateTaskDto(UpdateTaskDto dto, @MappingTarget Task task);
}
