package bg.softuni.myproject.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

@Entity(name = "ex_rates")
@Table(name = "ex_rates")
public class ExRateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty
    @Column(unique = true)
    private String currency;


    @Positive
    @NotNull
    private BigDecimal rate;

    public long getId() {
        return id;
    }

    public ExRateEntity setId(long id) {
        this.id = id;
        return this;
    }

    public String getCurrency() {
        return currency;
    }

    public ExRateEntity setCurrency(String currency) {
        this.currency = currency;
        return this;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public ExRateEntity setRate(BigDecimal rate) {
        this.rate = rate;
        return this;
    }

    public ExRateEntity() {
    }

    @Override
    public String toString() {
        return "ExRateEntity{" +
                "id=" + id +
                ", currency='" + currency + '\'' +
                ", rate=" + rate +
                '}';
    }
}
