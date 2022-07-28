package org.github.marcosvnmelo.livingtogether.house;

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
import org.github.marcosvnmelo.livingtogether.house.dto.CreateHouseDto;
import org.github.marcosvnmelo.livingtogether.house.dto.UpdateHouseDto;
import org.github.marcosvnmelo.livingtogether.user.User;

@GraphQLApi
public class HouseResource {
  @Inject
  HouseMapper mapper;

  @Mutation
  @Transactional
  public House createHouse(@NotNull @Name("data") CreateHouseDto dto) {
    // TODO user data from jwt
    final House house = mapper.houseFromCreateHouseDto(dto);

    final User manager = house.getManager();

    if (manager == null) {
      throw new BusinessException("User not found");
    }

    if (manager.isManager()) {
      throw new BusinessException("User is already a manager");
    }

    house.persist();

    manager.setHouse(house);

    manager.persist();

    return house;
  }

  @Query
  public List<House> listAllHouse() {
    return House.findAll().list();
  }

  @Query
  public House findHouseById(@NotNull @Name("id") Long id) {
    final Optional<House> house = House.findByIdOptional(id);

    return house.orElseThrow(() -> new BusinessException("House not found"));
  }

  @Mutation
  @Transactional
  public House updateHouse(@NotNull @Name("data") UpdateHouseDto dto) {
    final Optional<House> house = House.findByIdOptional(dto.getId());

    if (house.isEmpty()) {
      throw new BusinessException("House not found");
    }

    mapper.updateHouseFromUpdateHouseDto(dto, house.get());

    house.get().persist();

    return house.get();
  }

  @Mutation
  @Transactional
  public Boolean deleteHouse(@NotNull @Name("id") Long id) {
    final Boolean isDeleted = House.deleteById(id);

    if (!isDeleted) {
      throw new BusinessException("House not found");
    }

    return isDeleted;
  }
}
