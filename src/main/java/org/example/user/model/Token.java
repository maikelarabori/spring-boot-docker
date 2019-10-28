package org.example.user.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

/**
 * Class representing a token used by the application
 * in a few use cases where we need extra layer of assurance.
 */
@Entity
@Table(name = "tokens")
public class Token implements Serializable {

  private static final long serialVersionUID = 1;

  private static final Integer TOKEN_EXPIRATION_HOURS = 24;

  @Id
  private UUID id;

  private UUID userId;

  @Column(nullable = false)
  private TokenType type;

  @Column(nullable = false)
  private Boolean used;

  @Column(nullable = false)
  private Boolean active;

  @Column(nullable = false)
  private Date insertedAt;

  @Column(nullable = false)
  private Date updatedAt;

  public Token() {
    setId(UUID.randomUUID());
  }

  public Token(final UUID userId) {
    this.userId = userId;
  }

  public UUID getId() {
    return id;
  }

  public void setId(final UUID id) {
    this.id = id;
  }

  public UUID getUserId() {
    return userId;
  }

  public void setUserId(final UUID userId) {
    this.userId = userId;
  }

  public TokenType getType() {
    return type;
  }

  public void setType(final TokenType type) {
    this.type = type;
  }

  public Boolean getUsed() {
    return used;
  }

  public void setUsed(final Boolean used) {
    this.used = used;
  }

  public Boolean getActive() {
    return active;
  }

  public void setActive(final Boolean active) {
    this.active = active;
  }

  public Date getInsertedAt() {
    return insertedAt;
  }

  public void setInsertedAt(final Date insertedAt) {
    this.insertedAt = insertedAt;
  }

  public Date getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(final Date updatedAt) {
    this.updatedAt = updatedAt;
  }

  public void disable() {
    setActive(false);
  }

  public void discard() {
    setUsed(true);
  }

  public boolean isExpired() {
    if (getInsertedAt() != null) {
      final Instant expiringDate = getInsertedAt().toInstant()
          .plus(TOKEN_EXPIRATION_HOURS, ChronoUnit.HOURS);
      final Instant now = new Date().toInstant();

      if (now.isBefore(expiringDate)) {
        return false;
      }
    }
    return true;
  }

  public boolean isActive() {
    return active != null ? active : false;
  }

  @PrePersist
  private void beforeCreating() {
    final Date date = new Date();
    setInsertedAt(date);
    setUpdatedAt(date);
    setActive(true);
    setUsed(false);
  }

  @PreUpdate
  private void beforeUpdating() {
    setUpdatedAt(new Date());
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    final Token token = (Token) o;
    return Objects.equals(id, token.id) &&
        userId.equals(token.userId) &&
        type == token.type &&
        used.equals(token.used) &&
        active.equals(token.active) &&
        insertedAt.equals(token.insertedAt) &&
        updatedAt.equals(token.updatedAt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, userId, type, used, active, insertedAt, updatedAt);
  }
}
