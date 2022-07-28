package org.github.marcosvnmelo.livingtogether.expense;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class ExpenseRepository implements PanacheRepository<Expense> {
  @Inject
  EntityManager entityManager;
}
