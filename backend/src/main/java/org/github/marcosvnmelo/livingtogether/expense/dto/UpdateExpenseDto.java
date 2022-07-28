package org.github.marcosvnmelo.livingtogether.expense.dto;

import java.io.Serializable;
import java.time.Instant;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateExpenseDto implements Serializable {
  @NotNull
  private String id;

  private String name;

  private String description;

  private Double value;

  private Instant date;
}
