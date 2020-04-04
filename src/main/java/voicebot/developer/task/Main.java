package voicebot.developer.task;

import voicebot.developer.task.calculator.Calculator;
import voicebot.developer.task.reader.EmployeeReader;

import java.math.BigDecimal;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        EmployeeReader employeeReader = new EmployeeReader();
        Calculator calculator = new Calculator();

        System.out.println("Sum of salaries for employees from CSV file:");
        Map<String, BigDecimal> mapWithSalariesFromCSVFile =
                calculator.getMapWithSalariesOfJobs(employeeReader.getEmployeesFromCSVFile(null));
        showSumOfSalariesForJobs(mapWithSalariesFromCSVFile);

        System.out.println("\nSum of salaries for eployees from Json file:");
        Map<String, BigDecimal> mapWithSalariesFromJsonFile = calculator
                .getMapWithSalariesOfJobs(employeeReader.getEmployeesFromJSONFile(null));
        showSumOfSalariesForJobs(mapWithSalariesFromJsonFile);
    }

    private static void showSumOfSalariesForJobs(Map<String, BigDecimal> salaries) {
        for (Map.Entry result : salaries.entrySet()) {
            System.out.println(result.getKey() + " - " + result.getValue());
        }
    }
}
