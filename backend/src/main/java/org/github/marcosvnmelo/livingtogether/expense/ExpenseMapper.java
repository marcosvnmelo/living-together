package org.github.marcosvnmelo.livingtogether.expense;

import javax.inject.Inject;
import org.github.marcosvnmelo.livingtogether.expense.dto.CreateExpenseDto;
import org.github.marcosvnmelo.livingtogether.expense.dto.UpdateExpenseDto;
import org.github.marcosvnmelo.livingtogether.house.HouseRepository;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "cdi")
public abstract class ExpenseMapper {
  @Inject
  HouseRepository repository;

  @Mapping(target = "house", expression = "java(repository.findById(dto.getHouseId()))")
  public abstract Expense expenseFromCreateExpenseDto(CreateExpenseDto dto);

  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  public abstract void updateExpenseFromUpdateExpenseDto(@MappingTarget UpdateExpenseDto dto,
      Expense task);
}
