package org.github.marcosvnmelo.livingtogether.house.dto;

import java.io.Serializable;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateHouseDto implements Serializable {
  @NotNull
  private Long id;

  private String name;

  private String address;
}
