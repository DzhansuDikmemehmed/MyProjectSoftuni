package bg.softuni.myproject.controller;

import bg.softuni.myproject.service.ExRateService;
import bg.softuni.myproject.service.dto.ConversionResultDto;
import bg.softuni.myproject.service.exception.ApiObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
public class CurrencyController {

    private  final ExRateService exRateService;

    public CurrencyController(ExRateService exRateService) {
        this.exRateService = exRateService;
    }


    @GetMapping("/api/convert")
    public ResponseEntity<ConversionResultDto> convert(
            @RequestParam("from") String from,
            @RequestParam("to") String to,
            @RequestParam("amount")BigDecimal amount
            ){
        BigDecimal result = exRateService.convert(from, to, amount);

        return ResponseEntity.ok(new ConversionResultDto(
                from,
                to,
                amount,
                result
        ));

    }


    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ApiObjectNotFoundException.class)
    @ResponseBody
    public  NotFoundErrorInfo handleApiObjectNotFoundException(ApiObjectNotFoundException apiObjectNotFoundException){

        return new NotFoundErrorInfo("NOT FOUND", apiObjectNotFoundException.getId());
    }


    record NotFoundErrorInfo(String code, Object id){};
}
