package org.example.user.controller;

import org.example.user.model.User;
import org.example.user.model.UserStatus;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public final class StubProvider {

  public static Iterable<User> users() {
    final List<User> users = new ArrayList<>();
    users.add(user());
    users.add(user());
    return users;
  }

  public static final User user() {
    final User user = new User();
    user.setId(UUID.randomUUID());
    user.setEmail("anyEmail@email.trx");
    user.setDeleted(false);
    user.setStatus(UserStatus.ACTIVE);
    user.setUpdatedAt(new Date());
    user.setInsertedAt(new Date());
    user.setLastToken(UUID.randomUUID());
    user.setPassword("anyPassword");
    user.setPersistent(false);
    user.setUsername("anyUsername");
    user.setYearOfBirth(1983);
    return user;
  }

  public static final UserRequest userRequest() {
    final UserRequest userRequest = new UserRequest();
    userRequest.setEmail("anyEmail@email.trx");
    userRequest.setPassword("anyPassword");
    userRequest.setUsername("anyUsername");
    userRequest.setYearOfBirth(1983);
    return userRequest;
  }
}
