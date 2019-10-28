package org.example.user.model;

import java.util.HashMap;
import java.util.Map;

public enum UserStatus {
  NEW(0), ACTIVE(1), LOCKED(2), DISABLED(-1);

  private final Integer code;

  private static final Map<Integer, UserStatus> userStatusMap = new HashMap<>();

  static {
    for (final UserStatus type : UserStatus.values()) {
      userStatusMap.put(type.code, type);
    }
  }

  UserStatus(final Integer code) {
    this.code = code;
  }

  public final Integer code() {
    return code;
  }

  public static final UserStatus from(final Integer code) {
    return userStatusMap.get(code);
  }
}
