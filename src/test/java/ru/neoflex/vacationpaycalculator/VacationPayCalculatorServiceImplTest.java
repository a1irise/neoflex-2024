package ru.neoflex.vacationpaycalculator;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ru.neoflex.vacationpaycalculator.dto.VacationPayRequest;
import ru.neoflex.vacationpaycalculator.dto.VacationPayResponse;
import ru.neoflex.vacationpaycalculator.service.VacationPayCalculatorService;
import ru.neoflex.vacationpaycalculator.service.VacationPayCalculatorServiceImpl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class VacationPayCalculatorServiceImplTest {

	private final VacationPayCalculatorService vacationPayCalculatorService = new VacationPayCalculatorServiceImpl();

	private final BigDecimal AVERAGE_MONTHLY_SALARY = BigDecimal.valueOf(30000);
	private final int VACATION_DURATION_IN_DAYS = 14;
	private final LocalDate VACATION_START_DATE = LocalDate.of(2024, Month.JUNE, 10);
	private static final int VACATION_DURATION_IN_DAYS_EXCLUDING_HOLIDAYS = 9;
	private static final BigDecimal AVERAGE_NUMBER_OF_DAYS_PER_MONTH = BigDecimal.valueOf(29.3);

	@Test
	public void calculateVacationPay_WithoutVacationStartDate_CalculateVacationPay() {
		BigDecimal expected = AVERAGE_MONTHLY_SALARY
				.divide(AVERAGE_NUMBER_OF_DAYS_PER_MONTH, 2, RoundingMode.HALF_EVEN)
				.multiply(BigDecimal.valueOf(VACATION_DURATION_IN_DAYS));

		VacationPayRequest request = new VacationPayRequest(AVERAGE_MONTHLY_SALARY, VACATION_DURATION_IN_DAYS);
		VacationPayResponse response = vacationPayCalculatorService.calculateVacationPay(request);

		assertEquals(expected, response.getVacationPay());
	}

	@Test
	public void calculateVacationPay_WithVacationStartDate_CalculateVacationPay() {
		BigDecimal expected = AVERAGE_MONTHLY_SALARY
				.divide(AVERAGE_NUMBER_OF_DAYS_PER_MONTH, 2, RoundingMode.HALF_EVEN)
				.multiply(BigDecimal.valueOf(VACATION_DURATION_IN_DAYS_EXCLUDING_HOLIDAYS));

		VacationPayRequest request = new VacationPayRequest(AVERAGE_MONTHLY_SALARY, VACATION_DURATION_IN_DAYS, VACATION_START_DATE);
		VacationPayResponse response = vacationPayCalculatorService.calculateVacationPay(request);

		assertEquals(expected, response.getVacationPay());
	}
}
