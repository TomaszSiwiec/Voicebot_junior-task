package voicebot.developer.task.reader;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVWriter;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import voicebot.developer.task.calculator.Calculator;
import voicebot.developer.task.domain.Employee;
import voicebot.developer.task.domain.Employees;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;

public class EmployeeReaderTestSuite {

    private final Logger logger = Logger.getLogger(EmployeeReaderTestSuite.class.getName());

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Before
    public void init() {
        try {
            temporaryFolder.create();
        } catch (IOException e) {
            logger.log(Level.WARNING, e.getMessage());
        }
    }

    @After
    public void tearDown() {
        temporaryFolder.delete();
    }

    @Test
    public void testReadingEmployeesFromCSVFile() throws IOException {
        //given
        final File tempCSVFile = temporaryFolder.newFile("employees.csv");
        Calculator calculator = new Calculator();
        EmployeeReader employeeReader = new EmployeeReader();
        List<Employee> employees = initEmployees().getEmployees();

        //when
        writeObjectsListToCSV(employees, tempCSVFile);
        Map<String, BigDecimal> mapWithSalariesFromCSVFile = calculator
                .getMapWithSalariesOfJobs(employeeReader.getEmployeesFromCSVFile(tempCSVFile));

        //then
        assertEquals(BigDecimal.valueOf(3100.10).setScale(2), mapWithSalariesFromCSVFile.get("Teacher").setScale(2));
        assertEquals(BigDecimal.valueOf(4900.20).setScale(2), mapWithSalariesFromCSVFile.get("Janitor").setScale(2));
        assertEquals(BigDecimal.valueOf(2300.99), mapWithSalariesFromCSVFile.get("Priest").setScale(2));
        tempCSVFile.delete();
    }

    private void writeObjectsListToCSV(List<Employee> employees, File file) {
        try {
            Writer writer = new BufferedWriter(new FileWriter(file.getAbsoluteFile()));
            CSVWriter csvWriter = new CSVWriter(writer,
                    ';',
                    CSVWriter.NO_QUOTE_CHARACTER,
                    CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                    CSVWriter.DEFAULT_LINE_END);

            String[] headerRecord = {"id", "name", "surname", "job", "salary"};
            csvWriter.writeNext(headerRecord);

            for (Employee employee : employees) {
                csvWriter.writeNext(new String[]{employee.getId().toString(), employee.getName(),
                        employee.getSurname(), employee.getJob(), employee.getSalary()});
            }
            csvWriter.close();
        } catch (IOException e) {
            logger.log(Level.WARNING, e.getMessage());
        }
    }

    @Test
    public void testReadingEmployeesFromJsonFile() throws IOException {
        //given
        final File tempCSVFile = temporaryFolder.newFile("employees.json");
        Calculator calculator = new Calculator();
        EmployeeReader employeeReader = new EmployeeReader();
        Employees employees = initEmployees();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(tempCSVFile, employees);
        //when
        Map<String, BigDecimal> mapWithSalariesFromCSVFile = calculator
                .getMapWithSalariesOfJobs(employeeReader.getEmployeesFromJSONFile(tempCSVFile));

        //then
        assertEquals(BigDecimal.valueOf(3100.10).setScale(2), mapWithSalariesFromCSVFile.get("Teacher").setScale(2));
        assertEquals(BigDecimal.valueOf(4900.20).setScale(2), mapWithSalariesFromCSVFile.get("Janitor").setScale(2));
        assertEquals(BigDecimal.valueOf(2300.99).setScale(2), mapWithSalariesFromCSVFile.get("Priest").setScale(2));
        tempCSVFile.delete();
    }

    public Employees initEmployees() {
        Employees employees = new Employees();
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee(1L, "John", "Bohn", "Teacher", "1200.00"));
        employeeList.add(new Employee(2L, "Martin", "Kovalenko", "Teacher", "1900.10"));
        employeeList.add(new Employee(3L, "Mike", "White", "Janitor", "4900.20"));
        employeeList.add(new Employee(4L, "Michael", "Buffalo", "Priest", "2300.99"));
        employees.setEmployees(employeeList);
        return employees;
    }
}
