package org.github.marcosvnmelo.livingtogether.expense;

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
import org.github.marcosvnmelo.livingtogether.expense.dto.CreateExpenseDto;
import org.github.marcosvnmelo.livingtogether.expense.dto.UpdateExpenseDto;

@GraphQLApi
public class ExpenseResource {
  @Inject
  ExpenseMapper mapper;

  @Mutation
  @Transactional
  public Expense createExpense(@NotNull @Name("data") CreateExpenseDto data) {
    final Expense expense = mapper.expenseFromCreateExpenseDto(data);

    if (expense.getHouse() == null) {
      throw new BusinessException("House not found");
    }

    expense.persist();

    return expense;
  }

  @Query
  public List<Expense> listAllExpenses() {
    return Expense.findAll().list();
  }

  @Query
  public Expense findExpenseById(@NotNull @Name("id") String id) {
    final Optional<Expense> expense = Expense.findByIdOptional(id);

    return expense.orElseThrow(() -> new BusinessException("Expense not found"));
  }

  @Mutation
  @Transactional
  public Expense updateExpense(@NotNull @Name("data") UpdateExpenseDto data) {
    final Optional<Expense> expense = Expense.findByIdOptional(data.getId());

    if (expense.isEmpty()) {
      throw new BusinessException("Expense not found");
    }

    mapper.updateExpenseFromUpdateExpenseDto(data, expense.get());

    expense.get().persist();

    return expense.get();
  }

  @Mutation
  @Transactional
  public Boolean deleteExpense(@NotNull @Name("id") String id) {
    final Boolean isDeleted = Expense.deleteById(id);

    if (!isDeleted) {
      throw new BusinessException("Expense not found");
    }

    return isDeleted;
  }
}
