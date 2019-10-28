package org.example.user.model;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public final class TokenTypeConverter implements AttributeConverter<TokenType, Integer> {

  @Override
  public Integer convertToDatabaseColumn(final TokenType attribute) {
    return attribute.code();
  }

  @Override
  public TokenType convertToEntityAttribute(final Integer code) {
    return TokenType.from(code);
  }
}
