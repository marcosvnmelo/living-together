package org.github.marcosvnmelo.livingtogether.house;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class HouseRepository implements PanacheRepository<House> {
  @Inject
  EntityManager entityManager;
}
