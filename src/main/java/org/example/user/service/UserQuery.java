package org.example.user.service;

import org.example.user.model.User;
import org.example.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

/**
 * Expose read-only methods related to users.
 */
@Service
public class UserQuery {

  private final UserRepository userRepository;

  public UserQuery(final UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public Optional<User> getById(final UUID id) {
    return userRepository.findById(id);
  }

  public Iterable<User> getAll() {
    return userRepository.findAll();
  }

  public User getByUsername(final String username) {
    return userRepository.findByUsername(username);
  }

  public User getByEmail(final String email) {
    return userRepository.findByEmail(email);
  }

  public User getByLastToken(final UUID tokenId) {
    return userRepository.findByLastToken(tokenId);
  }

  public boolean usernameExists(final String username) {
    return getByUsername(username) != null;
  }

  public boolean emailExists(final String email) {
    return getByEmail(email) != null;
  }
}
