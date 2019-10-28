package org.example.user.model;

import java.util.HashMap;
import java.util.Map;

public enum TokenType {
  ACCOUNT_ACTIVATION(0), ACCOUNT_UNLOCK(1), EMAIL_UPDATE(2), PWD_RESETTING(3);

  private final Integer code;

  private static final Map<Integer, TokenType> tokenTypesMap = new HashMap<>();

  static {
    for (final TokenType type : TokenType.values()) {
      tokenTypesMap.put(type.code, type);
    }
  }

  TokenType(final Integer code) {
    this.code = code;
  }

  public final Integer code() {
    return code;
  }

  public static final TokenType from(final Integer code) {
    return tokenTypesMap.get(code);
  }
}
