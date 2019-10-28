package org.example.user.service;

import org.example.user.model.Token;
import org.example.user.repository.TokenRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Expose read-only methods related to tokens.
 */
@Service
public class TokenQuery {

  private final TokenRepository tokenRepository;

  public TokenQuery(final TokenRepository tokenRepository) {
    this.tokenRepository = tokenRepository;
  }

  public Token getByUserId(final UUID userId) {
    return tokenRepository.findByUserId(userId);
  }
}
