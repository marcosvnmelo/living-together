package org.github.marcosvnmelo.livingtogether.task;

import java.time.Instant;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import org.github.marcosvnmelo.livingtogether.house.House;
import org.github.marcosvnmelo.livingtogether.user.User;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity(name = "tasks")
public class Task extends PanacheEntity {
  private String name;
  private String description;
  private Instant date;

  @Column(columnDefinition = "boolean default false", nullable = false)
  private Boolean done = false;

  @ManyToOne()
  @JoinColumn(name = "house_id", nullable = false)
  private House house;

  @OneToOne()
  @JoinColumn(name = "responsible_user_id", nullable = true)
  private User responsible;
}
