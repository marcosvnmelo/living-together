package org.github.marcosvnmelo.livingtogether.core.error;

import io.smallrye.graphql.api.ErrorCode;

@ErrorCode("business-exception")
public class BusinessException extends RuntimeException {
  public BusinessException(final String message) {
    super(message);
  }
}
