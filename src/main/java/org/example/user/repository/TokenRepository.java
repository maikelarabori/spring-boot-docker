package org.example.user.repository;

import org.example.user.model.Token;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface TokenRepository extends CrudRepository<Token, UUID> {

  Token findByUserId(@Param("userId") final UUID userId);
}
