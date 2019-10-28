package org.example.util;

import static org.example.util.Utilities.randomSleep;

/**
 * This class contains methods that can help in the
 * mitigation of software security breaches and general
 * security utilities.
 */
public final class SecurityUtil {

  public static final void preventTimingAttack() {
    randomSleep(3000);
  }
}
