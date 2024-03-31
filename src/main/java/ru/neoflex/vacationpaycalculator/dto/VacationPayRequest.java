package ru.neoflex.vacationpaycalculator.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class VacationPayRequest {

    private BigDecimal averageMonthlySalary;
    private int vacationDurationInDays;
    private LocalDate vacationStartDate;

    public VacationPayRequest() {

    }

    public VacationPayRequest(BigDecimal averageMonthlySalary, int vacationDurationInDays) {
        this.averageMonthlySalary = averageMonthlySalary;
        this.vacationDurationInDays = vacationDurationInDays;
        this.vacationStartDate = null;
    }

    public VacationPayRequest(BigDecimal averageMonthlySalary, int vacationDurationInDays, LocalDate vacationStartDate) {
        this.averageMonthlySalary = averageMonthlySalary;
        this.vacationDurationInDays = vacationDurationInDays;
        this.vacationStartDate = vacationStartDate;
    }

    public BigDecimal getAverageMonthlySalary() {
        return averageMonthlySalary;
    }

    public void setAverageMonthlySalary(BigDecimal averageMonthlySalary) {
        this.averageMonthlySalary = averageMonthlySalary;
    }

    public int getVacationDurationInDays() {
        return vacationDurationInDays;
    }

    public void setVacationDurationInDays(int vacationDurationInDays) {
        this.vacationDurationInDays = vacationDurationInDays;
    }

    public LocalDate getVacationStartDate() {
        return vacationStartDate;
    }

    public void setVacationStartDate(LocalDate vacationStartDate) {
        this.vacationStartDate = vacationStartDate;
    }
}
