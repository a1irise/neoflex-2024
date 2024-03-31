package ru.neoflex.vacationpaycalculator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.neoflex.vacationpaycalculator.dto.VacationPayRequest;
import ru.neoflex.vacationpaycalculator.dto.VacationPayResponse;
import ru.neoflex.vacationpaycalculator.service.VacationPayCalculatorService;

@RestController
public class VacationPayCalculatorController {

    private final VacationPayCalculatorService vacationPayCalculatorService;

    @Autowired
    public VacationPayCalculatorController(VacationPayCalculatorService vacationPayCalculatorService) {
        this.vacationPayCalculatorService = vacationPayCalculatorService;
    }

    @GetMapping(value = "/calculate")
    public @ResponseBody VacationPayResponse calculateVacationPay(@RequestBody VacationPayRequest request) {
        return vacationPayCalculatorService.calculateVacationPay(request);
    }
}
