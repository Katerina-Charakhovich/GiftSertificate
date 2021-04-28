package com.epam.esm.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto extends Dto {
    Long id;
    @NotBlank
    @Pattern(regexp = "[a-zA-Zа-яА-ЯёЁ]{50}")
    @Size(min = 3, max = 45)
    String name;
    @NotBlank
    @Size(min = 1, max = 1000)
    @Pattern(regexp = "^[a-zA-Zа-яА-ЯёЁ]{50}")
    String surname;
    List<PurchaseDto> listPurchase;
}
