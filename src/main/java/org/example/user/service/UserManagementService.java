package org.example.user.service;

import org.example.user.model.User;
import org.slf4j.Logger;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Class responsible to encapsulated and handle all
 * the business rules related to an user.
 */
@Service
public class UserManagementService {

  private final Logger log = getLogger(this.getClass().getName());

  private final UserQuery userQuery;
  private final UserCommand userCommand;

  public UserManagementService(final UserQuery userQuery,
                               final UserCommand userCommand) {
    this.userQuery = userQuery;
    this.userCommand = userCommand;
  }

  @Transactional
  public boolean createUser(final User user) {
    try {
      userCommand.create(user);
    } catch (final DataIntegrityViolationException e) {
        return false;
    }
    return true;
  }

  public void deleteUser(final UUID userId) {
    final Optional<User> optionalUser = userQuery.getById(userId);

    if (optionalUser.isPresent()) {
      final User existingUser = optionalUser.get();

      log.info("Deleting user with id {}.", userId);
      userCommand.delete(existingUser);
    }
  }

}
