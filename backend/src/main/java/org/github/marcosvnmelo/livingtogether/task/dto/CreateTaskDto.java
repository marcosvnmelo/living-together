package org.github.marcosvnmelo.livingtogether.task.dto;

import java.io.Serializable;
import java.time.Instant;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateTaskDto implements Serializable {
  @NotNull
  private String name;

  private String description;

  @NotNull
  private Instant date;

  @NotNull
  private Long houseId;

  private Long responsibleUserId;
}
