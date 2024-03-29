package org.example.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public final class ConversionHelper {

  public static final String asJson(final Object object) throws JsonProcessingException {
    return new ObjectMapper().writeValueAsString(object);
  }
}
