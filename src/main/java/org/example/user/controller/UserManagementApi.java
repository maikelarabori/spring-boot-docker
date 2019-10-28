package org.example.user.controller;

import org.example.user.model.User;
import org.example.user.service.UserManagementService;
import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

import static org.example.util.SecurityUtil.preventTimingAttack;
import static org.slf4j.LoggerFactory.getLogger;
import static org.springframework.beans.BeanUtils.copyProperties;
import static org.springframework.http.HttpStatus.*;

/**
 * Class responsible to expose REST endpoints,
 * related to users, able to change state.
 */
@RestController
@RequestMapping(("/api/users"))
public class UserManagementApi {

  private final Logger log = getLogger(this.getClass().getName());

  private final UserManagementService userManagementService;

  public UserManagementApi(final UserManagementService userManagementService) {
    this.userManagementService = userManagementService;
  }

  @PostMapping
  public ResponseEntity createUser(@RequestBody @Valid final UserRequest userRequest) {
    preventTimingAttack();
    log.debug("Creating new user: {}.", userRequest);

    final User user = new User();
    copyProperties(userRequest, user);

    final boolean userCreated = userManagementService.createUser(user);

    if (!userCreated) {
      return new ResponseEntity(CONFLICT);
    }
    return new ResponseEntity(CREATED);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity deleteUser(@PathVariable("id") final UUID userId) {
    userManagementService.deleteUser(userId);
    return new ResponseEntity(OK);
  }
}
