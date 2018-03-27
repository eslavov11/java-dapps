package com.java_dapps.store;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Company {
    private String name;
    private int maxEmployees;
    private List<Employee> employees;

    public Company(String name, int maxEmployees) {
        this.name = name;
        this.maxEmployees = maxEmployees;
        this.employees = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxEmployees() {
        return maxEmployees;
    }

    public void setMaxEmployees(int maxEmployees) {
        this.maxEmployees = maxEmployees;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public void hireEmployee(String name, double salary) {
        Employee employee = new Employee(name);
        employee.setSalary(salary);

        this.getEmployees().add(employee);
    }

    public void fireEmployee(Employee employee) {
        Optional<Employee> employeeToRemove = this.getEmployees().stream()
                .findAny()
                .filter(e -> e.getEmployeeNumber() == employee.getEmployeeNumber());

        if (!employeeToRemove.isPresent()) {
            return;
        }

        this.getEmployees().remove(employeeToRemove.get());
    }

    public double getEmployeesSalary() {
        return this.getEmployees().stream().mapToDouble(Employee::getSalary).sum();
    }
}


/**
 6. Метод за увеличаване на заплатите на всички служители в компанията с един и същи
 процента за всички служители.
 7. Метод за увеличаване на заплатата на един служител. Методът има два аргумента:
 обект от тип Служител и процент за увеличаване на заплатата на служителя.
 8. Метод, който отпечатва името, максималния брой служители и броя на наетите
 служители в компанията.
 Ако възникне нужда от допълнителни методи, които да Ви помогнат за манипулиране на
 обекти от тип Служител и тип Компания, трябва да ги включите в дефиницията на съответния
 клас Служител и/или клас Компания.
 Необходимо е запазите класовете Служител и Компания в отделни .java файлове
 (Employee.java и Company.java)
 В main() метода на основния файл в проекта трябва да се реализира следното:
 1. Да се създадат два масива от по 5 служители.
 2. Да се създадат два масива от по 5 стойности за заплатите на служителите.
 3. Да се създадат два обекта от тип Компания.
 4. В първата компания да наемете служителите от първия масив, а във втората компания
 да наемете служителите от втория масив.
 5. Да се уволнят по един служител от всяка компания.
 6. Да тествате резултатите от изпълнението на: метода за увеличаване на заплатите на
 всички служители, метода за увеличаване на заплатата на един служите и метода,
 който връща сумата от всички заплати на служители във компаният
 */

class Employee {
    private static long currentEmployeeNumber = 1;

    private String name;
    private long employeeNumber;
    private double salary;

    public Employee(String name) {
        this.setName(name);
        this.setEmployeeNumber(currentEmployeeNumber++);
        this.setSalary(0);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(long employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void addSalaryByPercentage(double percentage) {
        this.setSalary(this.getSalary() + (percentage / 100 * this.getSalary()));
    }

    public void print() {
        System.out.println(
                "Name: " + this.getName() + System.lineSeparator() +
                        "Employee #: " + this.getEmployeeNumber() + System.lineSeparator() +
                        "Salary: " + this.getSalary()
        );
    }
}