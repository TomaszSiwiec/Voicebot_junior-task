package voicebot.developer.task.calculator;

import org.junit.Assert;
import org.junit.Test;
import voicebot.developer.task.domain.Employee;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;

public class CalculatorTestSuite {

    @Test
    public void testMapWithSalariesOfJobs() {
        // given
        Calculator calculator = new Calculator();
        List<Employee> employees = initEmployees();
        Map<String, BigDecimal> expectedMap = new HashMap<>();
        expectedMap.put("Teacher", BigDecimal.valueOf(3100.10).setScale(2));
        expectedMap.put("Janitor", BigDecimal.valueOf(4900.20).setScale(2));
        expectedMap.put("Priest", BigDecimal.valueOf(2300.99).setScale(2));

        // when
        Map<String, BigDecimal> resultMap = calculator.getMapWithSalariesOfJobs(employees);

        // then
        Assert.assertThat(expectedMap, is(resultMap));
    }

    private List<Employee> initEmployees() {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1L, "John", "Bohn", "Teacher", "1200.00"));
        employees.add(new Employee(2L, "Martin", "Kovalenko", "Teacher", "1900.10"));
        employees.add(new Employee(3L, "Mike", "White", "Janitor", "4900.20"));
        employees.add(new Employee(4L, "Michael", "Buffalo", "Priest", "2300.99"));
        return employees;
    }
}
