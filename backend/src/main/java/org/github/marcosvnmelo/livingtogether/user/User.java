package org.github.marcosvnmelo.livingtogether.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.github.marcosvnmelo.livingtogether.house.House;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
@Entity(name = "users")
public class User extends PanacheEntity {
  private String name;
  @Column(name = "last_name")
  private String lastName;

  @ManyToOne()
  @JoinColumn(name = "current_house_id", nullable = true)
  private House house;

  public Boolean isResident() {
    return this.house != null;
  }

  public Boolean isManager() {
    return this.isResident() && this.house.getManager().id.equals(this.id);
  }
}
