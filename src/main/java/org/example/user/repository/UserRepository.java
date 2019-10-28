package org.example.user.repository;

import org.example.user.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface UserRepository extends CrudRepository<User, UUID> {

  User findByUsername(@Param("username") final String username);

  User findByEmail(@Param("email") final String email);

  User findByLastToken(@Param("lastToken") final UUID tokenId);
}
