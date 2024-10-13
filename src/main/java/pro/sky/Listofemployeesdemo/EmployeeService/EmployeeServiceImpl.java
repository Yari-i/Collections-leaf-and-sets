package pro.sky.Listofemployeesdemo.EmployeeService;

import pro.sky.Listofemployeesdemo.Employee.Employee;
import pro.sky.Listofemployeesdemo.Exceptions.EmployeeNotFoundException;
import pro.sky.Listofemployeesdemo.Exceptions.EmployeeStorageIsFullException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class EmployeeServiceImpl implements EmployeeService {
    static final int maxCountOfEmployees = 20;


    List<Employee> employees = new ArrayList<>(Arrays.asList(
            new Employee("", ""),
            new Employee("", ""),
            new Employee("", ""),
            new Employee("", ""),
            new Employee("", ""),
            new Employee("", ""),
            new Employee("", ""),
            new Employee("", ""),
            new Employee("", "")
    ));


    @Override
    public String addEmployee(String firstName, String lastName) {
        if (employees.size() > maxCountOfEmployees) {
            throw new EmployeeStorageIsFullException("Лимит сотрудников превышен!");
        } else {
            return "Сотрудник: " + firstName + lastName + " добавлен.";
        }
    }

    @Override
    public String deleteEmployee(String firstName, String lastName) {
        if (firstName == null || lastName == null) {
            throw new EmployeeNotFoundException("Такого сотрудника не существует!");
        } else {
            return "Сотрудник: " + firstName + lastName + " удален.";
        }
    }

    @Override
    public String findEmployee(String firstName, String lastName) {
        if (firstName == null || lastName == null) {
            throw new EmployeeNotFoundException("Такого сотрудника не существует!");
        } else {
            return "Сотрудник: " + firstName + lastName + " найден.";
        }

    }
}
