package com.practice;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Practice4 {
    public static void main(String[] args) {
        List<Employee> listOfEmployee = Arrays.asList(
            new Employee("Ayushman", 26, 100, "IT"),
            new Employee("Hanuman", 26, 200, "Finance"),
            new Employee("Raman", 30, 5000, "HR"),
            new Employee("Sita", 28, 860, "IT"),
            new Employee("Lakshman", 28, 930, "Finance"),
            new Employee("Gita", 25, 607, "IT")
        );

        // Grouping by age
        Map<Integer, List<Employee>> result = listOfEmployee.stream()
                .collect(Collectors.groupingBy(Employee::getAge));
        System.out.println("Grouping by age: " + result);

        // Average age
        System.out.println("Average age: " +
                listOfEmployee.stream().collect(Collectors.averagingDouble(Employee::getAge)));

        System.out.println("Average age: " +
                listOfEmployee.stream().mapToInt(Employee::getAge).average());

        // Sort by age then name
        listOfEmployee.stream()
                .sorted(Comparator.comparing(Employee::getAge).thenComparing(Employee::getName))
                .forEach(System.out::println);
        System.out.println();

        // Skip n and get next highest age
        int n = 1;
        Optional<Employee> sortedByAge = listOfEmployee.stream()
                .sorted(Comparator.comparing(Employee::getAge).reversed())
                .skip(n).findFirst();
        System.out.println(sortedByAge);

        // Sort by salary
        List<Employee> sortedBySalary = listOfEmployee.stream()
                .sorted(Comparator.comparingDouble(Employee::getSalary))
                .collect(Collectors.toList());
        System.out.println(sortedBySalary);

		Map<String, Double> sortedByDepartment = listOfEmployee.stream().collect(Collectors.groupingBy(
			e -> e.getDepartment(), Collectors.averagingDouble(e -> e.getSalary())));
		System.out.println(sortedByDepartment);

		Map<String, Optional<Employee>> sortedByDepartmentMaxSalary =listOfEmployee.stream().collect(Collectors.groupingBy(
			e -> e.getDepartment(), Collectors.maxBy(Comparator.comparing(e -> e.getSalary()))));
        System.out.println(sortedByDepartmentMaxSalary);

		listOfEmployee.stream().collect(Collectors.groupingBy(e -> e.getDepartment(), Collectors.counting()))
			.entrySet().stream().filter(e -> e.getValue()>2).forEach(System.out::println);

		listOfEmployee.stream().collect(Collectors.groupingBy(e -> e.getDepartment(), Collectors.averagingDouble(e -> e.getSalary()))).entrySet()
			.stream().max(Comparator.comparing(e -> e.getValue())).ifPresent(System.out::println);
			 
    }
}
//record Employee(String name, int age, double salary, String department) {} 
class Employee {
    String name;
    int age;
    double salary;
    String department;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public double getSalary() { return salary; }
    public void setSalary(double salary) { this.salary = salary; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public Employee(String name, int age, double salary, String department) {
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.department = department;
    }
    @Override
    public String toString() {
        return "Employee [name=" + name + ", age=" + age +
               ", salary=" + salary + ", department=" + department + "]";
    }
}
