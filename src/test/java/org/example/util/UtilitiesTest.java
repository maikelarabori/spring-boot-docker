package org.example.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.both;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@DisplayName("Testing the Utilities class.")
class UtilitiesTest {

  @Test
  @DisplayName("Testing randomNumber() with success.")
  void randomLong() {
    // Given
    final long leftLimit = 1000;
    final long rightLimit = 3000;

    // When
    final long randomNumber = Utilities.randomLong(leftLimit, rightLimit);

    // Then
    assertThat(randomNumber, is(both(greaterThanOrEqualTo(leftLimit)).and(lessThanOrEqualTo(rightLimit))));
  }

  @Test
  @DisplayName("Testing randomSleep() with success.")
  void randomSleep() {
    // Given
    final Long maxMilliseconds = 3000l;

    // When
    final Long sleepingTime = Utilities.randomSleep(maxMilliseconds);

    // Then
    assertThat(sleepingTime, is(lessThan(maxMilliseconds)));
  }
}
