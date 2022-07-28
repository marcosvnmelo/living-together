package org.github.marcosvnmelo.livingtogether.house.dto;

import java.io.Serializable;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateHouseDto implements Serializable {
  @NotNull
  private String name;

  private String address;

  @NotNull
  private Long userId;
}
