package ru.neoflex.vacationpaycalculator.dto;

import java.math.BigDecimal;

public class VacationPayResponse {

    private BigDecimal vacationPay;

    public VacationPayResponse() {

    }

    public VacationPayResponse(BigDecimal vacationPay) {
        this.vacationPay = vacationPay;
    }

    public BigDecimal getVacationPay() {
        return vacationPay;
    }

    public void setVacationPay(BigDecimal vacationPay) {
        this.vacationPay = vacationPay;
    }
}
