package pro.sky.Listofemployeesdemo.employeeService;

import pro.sky.Listofemployeesdemo.model.Employee;
import pro.sky.Listofemployeesdemo.exceptions.EmployeeAlreadyAddedException;
import pro.sky.Listofemployeesdemo.exceptions.EmployeeNotFoundException;
import pro.sky.Listofemployeesdemo.exceptions.EmployeeStorageIsFullException;

import java.util.List;

public interface EmployeeService {
    Employee addEmployee(String firstName, String lastName)
            throws EmployeeStorageIsFullException, EmployeeAlreadyAddedException;
    Employee removeEmployee(String firstName, String lastName)
            throws EmployeeNotFoundException;
    Employee findEmployee(String firstName, String lastName)
            throws EmployeeNotFoundException;
    List<Employee> getAllEmployees();


}
