package org.github.marcosvnmelo.livingtogether.task.dto;

import java.io.Serializable;
import java.time.Instant;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateTaskDto implements Serializable {
  @NotNull
  private Long id;

  private String name;

  private String description;

  private Instant date;

  private Long responsibleUserId;

  private Boolean done;
}
