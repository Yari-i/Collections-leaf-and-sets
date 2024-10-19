package pro.sky.Listofemployeesdemo.employeeService;

import org.springframework.stereotype.Service;
import pro.sky.Listofemployeesdemo.model.Employee;
import pro.sky.Listofemployeesdemo.exceptions.EmployeeAlreadyAddedException;
import pro.sky.Listofemployeesdemo.exceptions.EmployeeNotFoundException;
import pro.sky.Listofemployeesdemo.exceptions.EmployeeStorageIsFullException;


import java.util.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final Map<String, Employee> employees;

    public EmployeeServiceImpl(Map<String, Employee> employees) {
        this.employees = new HashMap<>();
    }

    private static final int MAX_EMPLOYEES = 2;

    @Override
    public Employee addEmployee(String firstName, String lastName)
            throws EmployeeStorageIsFullException, EmployeeAlreadyAddedException {
        if (employees.size() >= MAX_EMPLOYEES) {
            throw new EmployeeStorageIsFullException("Лимит сотрудников превышен.");
        }
        Employee employee = new Employee(firstName, lastName);
        if (employees.containsKey(employee.getFullName())) {
            throw new EmployeeAlreadyAddedException("Такой сотрудник уже существует.");
        }
        employees.put(employee.getFullName(), employee);
        return employee;
    }

    @Override
    public Employee removeEmployee(String firstName, String lastName) throws EmployeeNotFoundException {
        Employee employee = new Employee(firstName, lastName);
        if (employees.containsKey(employee.getFullName())) {
            employees.remove(employee.getFullName());
            return employee;
        }
        throw new EmployeeNotFoundException("Сотрудник не найден.");
    }

    @Override
    public Employee findEmployee(String firstName, String lastName) throws EmployeeNotFoundException {
        Employee employee = new Employee(firstName, lastName);
        if (employees.containsKey(employee.getFullName())) {
            return employees.get(employee.getFullName());
        }
        throw new EmployeeNotFoundException("Сотрудник не найден.");
    }

    @Override
    public Collection<Employee> getAllEmployees() {
        return Collections.unmodifiableCollection(employees.values());
    }
}
