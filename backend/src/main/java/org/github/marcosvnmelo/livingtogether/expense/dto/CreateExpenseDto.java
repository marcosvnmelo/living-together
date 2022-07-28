package org.github.marcosvnmelo.livingtogether.expense.dto;

import java.io.Serializable;
import java.time.Instant;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateExpenseDto implements Serializable {
  @NotNull
  private String name;

  private String description;

  @NotNull
  private Double value;

  @NotNull
  private Instant date;

  @NotNull
  private Long houseId;
}
