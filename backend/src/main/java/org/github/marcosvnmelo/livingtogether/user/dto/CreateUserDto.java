package org.github.marcosvnmelo.livingtogether.user.dto;

import java.io.Serializable;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateUserDto implements Serializable {
  @NotNull
  private String name;
  @NotNull
  private String lastName;
}
