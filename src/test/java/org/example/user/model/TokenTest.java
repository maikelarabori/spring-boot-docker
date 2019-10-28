package org.example.user.model;

import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class TokenTest {

  @Test
  void isExpired_when_token_is_not_expired() {
    // Given
    final Token stubToken = new Token();
    stubToken.setInsertedAt(new Date());

    // When
    final boolean isTokenExpired = stubToken.isExpired();

    // Then
    assertThat(isTokenExpired, is(false));
  }

  @Test
  void isExpired_when_token_is_expired() {
    // Given
    final Date expiredDate = new Date(Instant.now().plus(-24, ChronoUnit.HOURS).toEpochMilli());
    final Token stubToken = new Token();
    stubToken.setInsertedAt(expiredDate);

    // When
    final boolean isTokenExpired = stubToken.isExpired();

    // Then
    assertThat(isTokenExpired, is(true));
  }

  @Test
  void isExpired_when_insertedAt_is_null() {
    // Given
    final Token stubToken = new Token();
    stubToken.setInsertedAt(null);

    // When
    final boolean isTokenExpired = stubToken.isExpired();

    // Then
    assertThat(isTokenExpired, is(true));
  }
}
