package bg.softuni.myproject.service.dto;

import java.math.BigDecimal;
import java.util.Map;

public class ExRatesDto {

    private String base;
    private Map<String, BigDecimal> rates;

    public ExRatesDto() {
    }

    public String getBase() {
        return base;
    }

    public ExRatesDto setBase(String base) {
        this.base = base;
        return this;
    }

    public Map<String, BigDecimal> getRates() {
        return rates;
    }

    public ExRatesDto setRates(Map<String, BigDecimal> rates) {
        this.rates = rates;
        return this;
    }
}
