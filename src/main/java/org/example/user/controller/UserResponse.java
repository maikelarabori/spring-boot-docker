package org.example.user.controller;

import java.util.Objects;

import static java.util.Objects.hash;

final class UserResponse {

  private String username;
  private String email;
  private Integer yearOfBirth;
  private Integer status;

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

  public Integer getStatus() {
    return status;
  }

  public void setStatus(final Integer status) {
    this.status = status;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    final UserResponse that = (UserResponse) o;
    return Objects.equals(username, that.username) &&
        Objects.equals(email, that.email) &&
        Objects.equals(yearOfBirth, that.yearOfBirth) &&
        Objects.equals(status, that.status);
  }

  @Override
  public int hashCode() {
    return hash(username, email, yearOfBirth, status);
  }

  @Override
  public String toString() {
    return "UserResponse{" +
        "username='" + username + '\'' +
        ", email='" + email + '\'' +
        ", yearOfBirth=" + yearOfBirth +
        ", status=" + status +
        '}';
  }
}
