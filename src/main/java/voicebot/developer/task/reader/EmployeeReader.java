package voicebot.developer.task.reader;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import voicebot.developer.task.Main;
import voicebot.developer.task.domain.Employee;
import voicebot.developer.task.domain.Employees;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EmployeeReader {

    private final Logger logger = Logger.getLogger(EmployeeReader.class.getName());

    public List<Employee> getEmployeesFromCSVFile(File file) {
        List<Employee> employees = new ArrayList<>();
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        InputStream inputStream;

        try {
            if (file == null) {
                 inputStream = loader.getResourceAsStream("employees.csv");
            } else {
                inputStream = new FileInputStream(file);
            }
            Reader reader = new InputStreamReader(inputStream);
            CSVParser parser = new CSVParserBuilder().withSeparator(';').build();
            CSVReader csvReader = new CSVReaderBuilder(reader)
                    .withCSVParser(parser)
                    .withSkipLines(1)
                    .build();

            String[] nextRecord;

            while ((nextRecord = csvReader.readNext()) != null) {
                Employee employee = new Employee();
                employee.setId(Long.valueOf(nextRecord[0].trim()));
                employee.setName(nextRecord[1].trim());
                employee.setSurname(nextRecord[2].trim());
                employee.setJob(nextRecord[3].trim());
                employee.setSalary(nextRecord[4].trim());
                employees.add(employee);
            }
        } catch (FileNotFoundException e) {
            logger.log(Level.WARNING, "File employees.csv not found!");
        } catch (IOException e) {
            logger.log(Level.WARNING, e.getMessage());
        }
        return employees;
    }

    public List<Employee> getEmployeesFromJSONFile(File file) {
        List<Employee> employees = new ArrayList<>();
        InputStream is;
        ObjectMapper mapper = new ObjectMapper();

        try {
            if (file == null) {
                is = Main.class.getResourceAsStream("/employees.json");
            } else {
                is = new FileInputStream(file);
            }
            employees = mapper.readValue(is, Employees.class).getEmployees();
        } catch (FileNotFoundException e) {
            logger.log(Level.WARNING, "File employees.json not found");
        } catch (IOException e) {
            logger.log(Level.WARNING, e.getMessage());
        }
        return employees;
    }
}
