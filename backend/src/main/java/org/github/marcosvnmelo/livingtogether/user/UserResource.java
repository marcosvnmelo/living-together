package org.github.marcosvnmelo.livingtogether.user;

import java.util.List;
import java.util.Optional;
import javax.validation.constraints.NotNull;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Name;
import org.eclipse.microprofile.graphql.Query;
import org.github.marcosvnmelo.livingtogether.core.error.BusinessException;

@GraphQLApi
public class UserResource {
  @Query
  public List<User> listAllUser() {
    return User.findAll().list();
  }

  @Query
  public User findUserById(@NotNull @Name("id") Long id) {
    final Optional<User> user = User.findByIdOptional(id);

    if (user.isEmpty()) {
      throw new BusinessException("User not found");
    }

    return user.get();
  }
}
