package bg.softuni.myproject.service.impl;

import bg.softuni.myproject.config.ForexApiConfig;
import bg.softuni.myproject.model.entity.ExRateEntity;
import bg.softuni.myproject.repo.ExRateRepository;
import bg.softuni.myproject.service.ExRateService;
import bg.softuni.myproject.service.dto.ExRatesDto;
import bg.softuni.myproject.service.exception.ApiObjectNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ExRateServiceImpl implements ExRateService {
    private final Logger LOGGER = LoggerFactory.getLogger(ExRateServiceImpl.class);


    private final ForexApiConfig forexApiConfig;
    private final ExRateRepository exRateRepository;

    private final RestClient restClient;

    public ExRateServiceImpl(ForexApiConfig forexApiConfig, ExRateRepository exRateRepository,
                            @Qualifier("genericRestClient") RestClient restClient) {
        this.forexApiConfig = forexApiConfig;
        this.exRateRepository = exRateRepository;
        this.restClient = restClient;
    }

    @Override
    public BigDecimal convert(String from, String to, BigDecimal amount) {
        return findExRate(from, to)
                .orElseThrow(()-> new ApiObjectNotFoundException("Conversion from " + from + " to " + to + " not possible", from + "~" + to))
                .multiply(amount);
    }

    @Override
    public boolean hasInitializedExRates() {
        return exRateRepository.count() > 0;
    }

    @Override
    public ExRatesDto fetchExRates() {
        String fullUrl = UriComponentsBuilder.fromUriString(forexApiConfig.getUrl())
                .buildAndExpand(forexApiConfig.getKey())
                .toUriString();
        return restClient
                .get()
                .uri(fullUrl)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(ExRatesDto.class);
    }

    @Override
    public void updateRates(ExRatesDto exRatesDto) {
        LOGGER.info("Updating {} rates.", exRatesDto.getRates().size());


        if (!forexApiConfig.getBase().equals(exRatesDto.getBase())){
            throw new IllegalArgumentException("The exchange rates that should be updated are not based on " +
                    forexApiConfig.getBase() + " but rather on " + exRatesDto.getBase());
        }
        exRatesDto.getRates().forEach((currency, rate)->{
            ExRateEntity exRateEntity = exRateRepository.findByCurrency(currency)
                    .orElseGet(() -> new ExRateEntity().setCurrency(currency).setRate(rate));

            exRateEntity.setRate(rate);
            exRateRepository.save(exRateEntity);
        });

    }


    private Optional<BigDecimal> findExRate(String from, String to){

        if (Objects.equals(from, to)){
            return Optional.of(BigDecimal.ONE);
        }

      Optional<BigDecimal> fromOpt =  forexApiConfig.getBase().equals(from)? Optional.of(BigDecimal.ONE):
                exRateRepository.findByCurrency(from).map(ExRateEntity::getRate);

        Optional<BigDecimal> toOpt = forexApiConfig.getBase().equals(to)?
                Optional.of(BigDecimal.ONE):
                exRateRepository.findByCurrency(to).map(ExRateEntity::getRate);


        if (fromOpt.isEmpty() || toOpt.isEmpty()){
            return Optional.empty();
        } else {
            return Optional.of(toOpt.get().divide(fromOpt.get(),2, RoundingMode.HALF_DOWN));
        }
    }

    public List<String> allSupportedCurrencies(){
        return exRateRepository
                .findAll()
                .stream()
                .map(ExRateEntity::getCurrency)
                .toList();
    }
}
