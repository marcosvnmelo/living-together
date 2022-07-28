package org.github.marcosvnmelo.livingtogether.task;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class TaskRepository implements PanacheRepository<Task> {
  @Inject
  EntityManager entityManager;
}
