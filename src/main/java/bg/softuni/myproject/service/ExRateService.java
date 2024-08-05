package bg.softuni.myproject.service;

import bg.softuni.myproject.service.dto.ExRatesDto;

import java.math.BigDecimal;
import java.util.List;

public interface ExRateService {
    BigDecimal convert(String from, String to, BigDecimal amount);

    boolean hasInitializedExRates();

    ExRatesDto fetchExRates();

    void updateRates(ExRatesDto exRatesDto);

     List<String> allSupportedCurrencies();
}
