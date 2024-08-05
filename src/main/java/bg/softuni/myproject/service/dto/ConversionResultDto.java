package bg.softuni.myproject.service.dto;

import java.math.BigDecimal;

public record ConversionResultDto (String from,
                                   String to,
                                   BigDecimal amount,
                                   BigDecimal result) {
}