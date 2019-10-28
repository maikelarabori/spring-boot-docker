package org.example.user.controller;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

import static java.util.Objects.hash;

final class UserRequest {

  @NotBlank
  private String username;

  @NotBlank
  private String email;

  @Min(1930)
  private Integer yearOfBirth;

  @NotBlank
  private String password;

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

  @Override
  public boolean equals(final Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    final UserRequest that = (UserRequest) o;
    return Objects.equals(username, that.username) &&
        Objects.equals(email, that.email) &&
        Objects.equals(yearOfBirth, that.yearOfBirth) &&
        Objects.equals(password, that.password);
  }

  @Override
  public int hashCode() {
    return hash(username, email, yearOfBirth, password);
  }

  @Override
  public String toString() {
    return "UserRequest{" +
        "username='" + username + '\'' +
        ", email='" + email + '\'' +
        ", yearOfBirth=" + yearOfBirth +
        ", password='" + password + '\'' +
        '}';
  }
}
