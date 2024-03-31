package ru.neoflex.vacationpaycalculator.service;

import org.springframework.stereotype.Service;
import ru.neoflex.vacationpaycalculator.dto.VacationPayRequest;
import ru.neoflex.vacationpaycalculator.dto.VacationPayResponse;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Service
public class VacationPayCalculatorServiceImpl implements VacationPayCalculatorService {

    private static final BigDecimal AVERAGE_NUMBER_OF_DAYS_PER_MONTH = BigDecimal.valueOf(29.3);

    private static final List<DayOfWeek> WEEKENDS = List.of(DayOfWeek.SATURDAY , DayOfWeek.SUNDAY);

    private static final int CURRENT_YEAR = LocalDate.now().getYear();
    private static final List<LocalDate> HOLIDAYS = List.of(
            LocalDate.of(CURRENT_YEAR, Month.JANUARY, 1),
            LocalDate.of(CURRENT_YEAR, Month.JANUARY, 2),
            LocalDate.of(CURRENT_YEAR, Month.JANUARY, 3),
            LocalDate.of(CURRENT_YEAR, Month.JANUARY, 4),
            LocalDate.of(CURRENT_YEAR, Month.JANUARY, 5),
            LocalDate.of(CURRENT_YEAR, Month.JANUARY, 8),
            LocalDate.of(CURRENT_YEAR, Month.FEBRUARY, 23),
            LocalDate.of(CURRENT_YEAR, Month.MARCH, 8),
            LocalDate.of(CURRENT_YEAR, Month.APRIL, 29),
            LocalDate.of(CURRENT_YEAR, Month.APRIL, 30),
            LocalDate.of(CURRENT_YEAR, Month.MAY, 1),
            LocalDate.of(CURRENT_YEAR, Month.MAY, 9),
            LocalDate.of(CURRENT_YEAR, Month.MAY, 10),
            LocalDate.of(CURRENT_YEAR, Month.JUNE, 12),
            LocalDate.of(CURRENT_YEAR, Month.NOVEMBER, 4),
            LocalDate.of(CURRENT_YEAR, Month.DECEMBER, 30),
            LocalDate.of(CURRENT_YEAR, Month.DECEMBER, 31)
    );

    @Override
    public VacationPayResponse calculateVacationPay(VacationPayRequest request) {
        int vacationDurationInDays = (request.getVacationStartDate() != null)
                ? getVacationDurationInDaysExcludingHolidays(request)
                : request.getVacationDurationInDays();

        BigDecimal vacationPay = request.getAverageMonthlySalary()
                .divide(AVERAGE_NUMBER_OF_DAYS_PER_MONTH, 2, RoundingMode.HALF_EVEN)
                .multiply(BigDecimal.valueOf(vacationDurationInDays));

        return new VacationPayResponse(vacationPay);
    }

    private int getVacationDurationInDaysExcludingHolidays(VacationPayRequest request) {
        LocalDate startDate = request.getVacationStartDate();
        LocalDate endDate = startDate.plusDays(request.getVacationDurationInDays());

        return (int) startDate.datesUntil(endDate)
                .filter(date -> !(HOLIDAYS.contains(date) || WEEKENDS.contains(date.getDayOfWeek())))
                .count();
    }
}
