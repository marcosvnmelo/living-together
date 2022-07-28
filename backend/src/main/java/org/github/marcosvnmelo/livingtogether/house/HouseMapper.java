package org.github.marcosvnmelo.livingtogether.house;

import javax.inject.Inject;
import org.github.marcosvnmelo.livingtogether.house.dto.CreateHouseDto;
import org.github.marcosvnmelo.livingtogether.house.dto.UpdateHouseDto;
import org.github.marcosvnmelo.livingtogether.user.UserRepository;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "cdi")
public abstract class HouseMapper {
  @Inject
  UserRepository repository;

  @Mapping(target = "manager", expression = "java(repository.findById(dto.getUserId()))")
  public abstract House houseFromCreateHouseDto(CreateHouseDto dto);

  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  public abstract void updateHouseFromUpdateHouseDto(@MappingTarget UpdateHouseDto dto,
      House house);
}
