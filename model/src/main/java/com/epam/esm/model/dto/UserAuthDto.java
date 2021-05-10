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
public class UserAuthDto extends Dto {
    @NotBlank
    @Pattern(regexp = "[a-zA-z]{20}")
    @Size(min = 6, max = 20)
    String login;
    @NotBlank
    @Size(min = 8, max = 20)
    @Pattern(regexp = "^[a-zA-Z0-9]{20}")
    String password;
}
