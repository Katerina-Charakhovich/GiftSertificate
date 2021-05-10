package com.epam.esm.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserRegistrationDto extends Dto {
    @NotBlank
    @Pattern(regexp = "[a-zA-Zа-яА-ЯёЁ]{45}")
    @Size(min = 3, max = 45)
    String name;
    @NotBlank
    @Size(min = 1, max = 50)
    @Pattern(regexp = "[a-zA-Zа-яА-ЯёЁ]{50}")
    String surname;
    @NotBlank
    @Pattern(regexp = "[a-zA-z]{4,20}")
    String login;
    @NotBlank
    @Pattern(regexp = "[a-zA-Z]{6,20}")
    String password;
}
