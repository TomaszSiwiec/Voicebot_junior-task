package voicebot.developer.task.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Employees implements Serializable {
    @JsonProperty("employees")
    private List<Employee> employees;

    public Employees() {
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employees employees1 = (Employees) o;
        return Objects.equals(getEmployees(), employees1.getEmployees());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEmployees());
    }

    @Override
    public String toString() {
        return "Employees{" +
                "employees=" + employees +
                '}';
    }
}
