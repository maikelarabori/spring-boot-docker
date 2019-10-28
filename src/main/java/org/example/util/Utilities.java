package org.example.util;

import org.slf4j.Logger;

import java.security.SecureRandom;

import static java.lang.Math.random;
import static java.lang.Thread.sleep;
import static org.slf4j.LoggerFactory.getLogger;

/**
 * This class implements methods that can be reused by any application.
 * They are not related to any domain or specific application rules.
 * They must be very generic and reusable in ANY project/module.
 */
public final class Utilities {

  private final static Logger log = getLogger(Utilities.class.getName());
  private static final int MINIMUM_LIMIT = 1000;

  public static long randomSleep(final long maxMilliseconds) {
    final long sleepingTime = randomLong(MINIMUM_LIMIT, (maxMilliseconds));
    try {
      sleep(sleepingTime);
    } catch (final InterruptedException e) {
      log.error("Random sleep error.", e);
    }
    return sleepingTime;
  }

  static long randomLong(final long leftLimit, final long rightLimit) {
    return leftLimit + (long) (random() * (rightLimit - leftLimit));
  }
}
