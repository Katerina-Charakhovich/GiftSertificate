package com.epam.esm.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GiftCertificateDto extends Dto {
    private Long id;
    @NotBlank
    @Pattern(regexp = "[a-zA-Zа-яА-ЯёЁ0-9\\s?!,.:'\\-]{3,45}")
    @Size(min = 3, max = 45)
    private String name;
    @NotBlank
    @Size(min = 1, max = 1000)
    @Pattern(regexp = "^[a-zA-Zа-яА-ЯёЁ0-9\\s?!,.:'\\-]{1,1000}")
    private String description;
    @Positive
    @Min(1)
    @Max(30)
    private int duration;
    @Positive
    @Digits(integer = 5, fraction = 2)
    private BigDecimal price;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    LocalDateTime lastUpdateDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    LocalDateTime createDate;
    List<TagDto> listTagDto;
}
