package org.example.user.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

/**
 * Internal representation of a simple user.
 * This user is used during the API authentications
 * required by the protected endpoints in this application.
 */
@Entity
@Table(name = "users")
public class User implements Serializable {

  private static final long serialVersionUID = 1;

  @Id
  private UUID id;

  @NotBlank
  @Column(nullable = false, unique = true)
  private String username;

  @NotBlank
  @Column(nullable = false, unique = true)
  private String email;

  @Min(1930)
  @Column(nullable = false)
  private Integer yearOfBirth;

  @NotBlank
  @Column(nullable = false)
  private String password;

  @Column(nullable = false)
  private UserStatus status;

  @Column(nullable = false)
  private UUID lastToken;

  @Column(nullable = false)
  private Boolean persistent;

  @Column(nullable = false)
  private Date insertedAt;

  @Column(nullable = false)
  private Date updatedAt;

  @Column(nullable = false)
  private Boolean deleted;

  public User() {
    setId(UUID.randomUUID());
  }

  public UUID getId() {
    return id;
  }

  public void setId(final UUID id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(final String username) {
    this.username = username;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(final String email) {
    this.email = email;
  }

  public Integer getYearOfBirth() {
    return yearOfBirth;
  }

  public void setYearOfBirth(final Integer yearOfBirth) {
    this.yearOfBirth = yearOfBirth;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(final String password) {
    this.password = password;
  }

  public UserStatus getStatus() {
    return status;
  }

  public void setStatus(final UserStatus status) {
    this.status = status;
  }

  public UUID getLastToken() {
    return lastToken;
  }

  public void setLastToken(final UUID lastToken) {
    this.lastToken = lastToken;
  }

  public Boolean getPersistent() {
    return persistent;
  }

  public void setPersistent(final Boolean persistent) {
    this.persistent = persistent;
  }

  public Date getInsertedAt() {
    return insertedAt != null ? (Date) insertedAt.clone() : null;
  }

  public void setInsertedAt(final Date insertedAt) {
    this.insertedAt = (Date) insertedAt.clone();
  }

  public Date getUpdatedAt() {
    return updatedAt != null ? (Date) updatedAt.clone() : null;
  }

  public void setUpdatedAt(final Date updatedAt) {
    this.updatedAt = (Date) updatedAt.clone();
  }

  public Boolean getDeleted() {
    return deleted;
  }

  public void setDeleted(final Boolean deleted) {
    this.deleted = deleted;
  }

  public void activate() {
    setStatus(UserStatus.ACTIVE);
  }

  public void lock() {
    setStatus(UserStatus.LOCKED);
  }

  public void disable() {
    setStatus(UserStatus.DISABLED);
  }

  public void markForDeletion() {
    setDeleted(true);
  }

  @PrePersist
  private void beforeCreating() {
    final Date date = new Date();
    setInsertedAt(date);
    setUpdatedAt(date);
    setDeleted(false);
    setPersistent(false);
    setStatus(UserStatus.NEW);
  }

  @PreUpdate
  private void beforeUpdating() {
    setUpdatedAt(new Date());
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    final User user = (User) o;
    return Objects.equals(id, user.id) &&
        Objects.equals(username, user.username) &&
        Objects.equals(email, user.email) &&
        Objects.equals(yearOfBirth, user.yearOfBirth) &&
        Objects.equals(password, user.password) &&
        Objects.equals(status, user.status) &&
        Objects.equals(lastToken, user.lastToken) &&
        Objects.equals(persistent, user.persistent);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, username, email, yearOfBirth, password, status, lastToken, persistent);
  }
}
