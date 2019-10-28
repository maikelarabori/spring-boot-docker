package org.example.user.controller;

import org.example.user.model.User;
import org.example.user.service.UserQuery;
import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.UUID;

import static org.apache.commons.collections4.IterableUtils.isEmpty;
import static org.slf4j.LoggerFactory.getLogger;
import static org.springframework.beans.BeanUtils.copyProperties;
import static org.springframework.http.HttpStatus.FOUND;
import static org.springframework.http.HttpStatus.NOT_FOUND;

/**
 * Class responsible to expose query/read ONLY REST endpoints,
 * related to users.
 */
@RestController
@RequestMapping(("/api/users"))
public class UserQueryingApi {

  private final Logger log = getLogger(this.getClass().getName());

  private final UserQuery userQuery;

  public UserQueryingApi(final UserQuery userQuery) {
    this.userQuery = userQuery;
  }

  @GetMapping
  public ResponseEntity<Iterable<User>> getAllUsers() {
    final Iterable<User> users = userQuery.getAll();
    if (!isEmpty(users)) {
      return new ResponseEntity<>(userQuery.getAll(), FOUND);
    }
    return new ResponseEntity<>(NOT_FOUND);
  }

  @GetMapping("/{id}")
  public ResponseEntity<UserResponse> getUser(@PathVariable("id") final UUID id) {
    final Optional<User> optionalUser = userQuery.getById(id);
    if (optionalUser.isPresent()) {
      final UserResponse userResponse = new UserResponse();
      final User existingUser = optionalUser.get();
      copyProperties(existingUser, userResponse, "status");
      userResponse.setStatus(existingUser.getStatus().code());
      return new ResponseEntity<>(userResponse, FOUND);
    } else {
      log.warn("Trying to find an invalid user with ID: {}", id);
      return new ResponseEntity<>(NOT_FOUND);
    }
  }
}
