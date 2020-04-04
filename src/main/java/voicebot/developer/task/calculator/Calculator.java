package voicebot.developer.task.calculator;

import voicebot.developer.task.domain.Employee;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Calculator {
    public Map<String, BigDecimal> getMapWithSalariesOfJobs(List<Employee> employees) {
        Map<String, BigDecimal> salaries = new HashMap<>();
        BigDecimal sumOfFoundSalaries;
        for (Employee employee : employees) {
            if (salaries.containsKey(employee.getJob())) {
                sumOfFoundSalaries = salaries.get(employee.getJob()).add(employee.getConvertedSalary());
                salaries.put(employee.getJob(), sumOfFoundSalaries.setScale(2));
            } else {
                salaries.put(employee.getJob(), employee.getConvertedSalary().setScale(2));
            }
        }
        return salaries;
    }
}
