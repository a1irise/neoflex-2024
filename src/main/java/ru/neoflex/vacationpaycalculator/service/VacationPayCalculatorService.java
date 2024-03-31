package ru.neoflex.vacationpaycalculator.service;

import ru.neoflex.vacationpaycalculator.dto.VacationPayRequest;
import ru.neoflex.vacationpaycalculator.dto.VacationPayResponse;

public interface VacationPayCalculatorService {

    VacationPayResponse calculateVacationPay(VacationPayRequest request);
}
