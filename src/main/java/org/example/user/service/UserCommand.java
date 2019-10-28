package org.example.user.service;

import org.example.user.model.Token;
import org.example.user.model.TokenType;
import org.example.user.model.User;
import org.example.user.repository.TokenRepository;
import org.example.user.repository.UserRepository;
import org.springframework.stereotype.Service;

/**
 * This class is responsible to change the state of users.
 * It does not contain any business rules. The methods implemented
 * here will only execute a specific task, and should not know about
 * any validation or business rules. It should never query for any data.
 */
@Service
public class UserCommand {

  private final UserRepository userRepository;
  private final TokenRepository tokenRepository;

  public UserCommand(final UserRepository userRepository,
                     final TokenRepository tokenRepository) {
    this.userRepository = userRepository;
    this.tokenRepository = tokenRepository;
  }

  public void create(final User user) {
    final Token token = new Token();
    token.setUserId(user.getId());
    token.setType(TokenType.ACCOUNT_ACTIVATION);
    tokenRepository.save(token);

    user.setLastToken(token.getId());
    userRepository.save(user);
  }

  public void delete(final User user) {
    user.markForDeletion();
    userRepository.save(user);
  }
}
