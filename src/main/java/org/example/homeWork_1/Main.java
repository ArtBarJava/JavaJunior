package org.example.homeWork_1;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.example.homeWork_1.Homework.*;

public class Main {

    public static void main(String[] args) {
        List<Homework.Person> people = List.of(
                new Homework.Person("Alice", 30, 5000, new Homework.Department("HR")),
                new Homework.Person("Bob", 25, 4500, new Homework.Department("IT")),
                new Homework.Person("Charlie", 35, 6000, new Homework.Department("Finance")),
                new Homework.Person("Lie", 45, 5500, new Homework.Department("Finance")),
                new Homework.Person("Diana", 28, 5200, new Homework.Department("HR")),
                new Homework.Person("Eve", 40, 7000, new Homework.Department("IT")),
                new Homework.Person("Mark", 28, 5900, new Homework.Department("IT"))
        );

        Optional<Person> youngestPerson = findMostYoungestPerson(people);
        youngestPerson.ifPresent(person -> System.out.println("The youngest person is: " + person.getName() + ", age: " + person.getAge()));

        Optional<Homework.Department> mostExpensiveDepartment = findMostExpensiveDepartment(people);
        mostExpensiveDepartment.ifPresent(department -> System.out.println("Department with highest salary is " + department.getName()));

        Map<Department, List<Person>> groupedByDepartment = groupByDepartment(people);

        for (Map.Entry<Department, List<Person>> entry : groupedByDepartment.entrySet()) {
            System.out.println("Department: " + entry.getKey().getName());
            System.out.println("Employees: ");
            for (Person person : entry.getValue()) {
                System.out.println("t" + person.getName() + " (Age: " + person.getAge() + ", Salary: " + person.getSalary() + ")");
            }
            System.out.println();
        }

        Map<String, Homework.Person> departmentOldestPerson = getDepartmentOldestPerson(people);
        departmentOldestPerson.forEach((department, person) -> {
            System.out.println("Department: " + department + ", Oldest Person: " + person.getName() + " (Age: " + person.getAge() + ")");
        });


        List<Person> cheapPeople = cheapPersonsInDepartment(people);

        System.out.println("Employees with minimum wages in their department");
        for (Person person : cheapPeople) {
            System.out.println("Name: " + person.getName() + ", Salary: " + person.getSalary() + ", Department: " + person.getDepart().getName());
        }

    }
}
