package org.github.marcosvnmelo.livingtogether.expense;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.github.marcosvnmelo.livingtogether.house.House;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity(name = "expenses")
public class Expense extends PanacheEntity {
  private String name;
  private String description;
  private Double value;
  private Date date;

  @ManyToOne()
  @JoinColumn(name = "house_id", nullable = false)
  private House house;
}
