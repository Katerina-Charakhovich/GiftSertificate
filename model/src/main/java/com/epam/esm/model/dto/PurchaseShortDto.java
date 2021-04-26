package com.epam.esm.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PurchaseShortDto extends Dto {
    @Positive
    long id;
    @Positive
    long userId;
    @Positive
    @Digits(integer = 5, fraction = 2)
    private BigDecimal price;
    List<Long> listGiftCertificate;
}
