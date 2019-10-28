package org.example.user.model;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public final class UserStatusConverter implements AttributeConverter<UserStatus, Integer> {

  @Override
  public Integer convertToDatabaseColumn(final UserStatus attribute) {
    return attribute.code();
  }

  @Override
  public UserStatus convertToEntityAttribute(final Integer code) {
    return UserStatus.from(code);
  }
}
