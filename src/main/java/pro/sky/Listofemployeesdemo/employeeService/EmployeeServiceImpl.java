package pro.sky.Listofemployeesdemo.employeeService;

import org.springframework.stereotype.Service;
import pro.sky.Listofemployeesdemo.model.Employee;
import pro.sky.Listofemployeesdemo.exceptions.EmployeeAlreadyAddedException;
import pro.sky.Listofemployeesdemo.exceptions.EmployeeNotFoundException;
import pro.sky.Listofemployeesdemo.exceptions.EmployeeStorageIsFullException;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final List<Employee> employees = new ArrayList<>();

    private static final int MAX_EMPLOYEES = 2;

    @Override
    public Employee addEmployee(String firstName, String lastName)
            throws EmployeeStorageIsFullException, EmployeeAlreadyAddedException {
        if (employees.size() >= MAX_EMPLOYEES) {
            throw new EmployeeStorageIsFullException("Лимит сотрудников превышен.");
        }
        Employee employee = new Employee(firstName, lastName);
        if (employees.contains(employee)) {
            throw new EmployeeAlreadyAddedException("Такой сотрудник уже существует.");
        }
        employees.add(employee);
        return employee;
    }

    @Override
    public Employee removeEmployee(String firstName, String lastName) throws EmployeeNotFoundException {
        Employee employee = new Employee(firstName, lastName);
        if (!employees.remove(employee)) {
            throw new EmployeeNotFoundException("Сотрудник не найден.");
        }
        return employee;
    }

    @Override
    public Employee findEmployee(String firstName, String lastName) throws EmployeeNotFoundException {
        for (Employee employee : employees) {
            if (employee.getFirstName().equals(firstName) && employee.getLastName().equals(lastName)) {
                return employee;
            }
        }
        throw new EmployeeNotFoundException("Сотрудник не найден.");
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employees;
    }
}