package org.github.marcosvnmelo.livingtogether.house;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import org.github.marcosvnmelo.livingtogether.expense.Expense;
import org.github.marcosvnmelo.livingtogether.task.Task;
import org.github.marcosvnmelo.livingtogether.user.User;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
@Entity(name = "houses")
public class House extends PanacheEntity {
  private String name;
  private String address;

  @OneToOne()
  @JoinColumn(name = "manager_id", nullable = false)
  private User manager;

  @OneToMany(mappedBy = "house")
  private List<User> residents;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "house")
  private List<Expense> expenses;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "house")
  private List<Task> tasks;
}
