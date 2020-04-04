package voicebot.developer.task.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

public class Employee implements Serializable {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("surname")
    private String surname;

    @JsonProperty("job")
    private String job;

    @JsonProperty("salary")
    private String salary;

    public Employee() {

    }

    public Employee(Long id, String name, String surname, String job, String salary) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.job = job;
        this.salary = salary;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    @JsonIgnore
    public BigDecimal getConvertedSalary() {
        BigDecimal filteredSalary;
        if (getSalary().contains(",")) {
            filteredSalary = new BigDecimal(getSalary().replace(",", "."));
        } else {
            filteredSalary = new BigDecimal(getSalary());
        }
        return filteredSalary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(getId(), employee.getId()) &&
                Objects.equals(getName(), employee.getName()) &&
                Objects.equals(getSurname(), employee.getSurname()) &&
                Objects.equals(getJob(), employee.getJob()) &&
                Objects.equals(getSalary(), employee.getSalary());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getSurname(), getJob(), getSalary());
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", job='" + job + '\'' +
                ", salary='" + salary + '\'' +
                '}';
    }
}
