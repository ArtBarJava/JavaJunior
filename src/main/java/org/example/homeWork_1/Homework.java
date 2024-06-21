package org.example.homeWork_1;

import java.util.*;
import java.util.stream.Collectors;

public class Homework {
    public static class Department {
        private String name;

        public Department(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class Person {
        private String name;
        private int age;
        private double salary;
        private Department depart;


        public Person(String name, int age, double salary, Department depart) {
            this.name = name;
            this.age = age;
            this.salary = salary;
            this.depart = depart;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public double getSalary() {
            return salary;
        }

        public void setSalary(double salary) {
            this.salary = salary;
        }

        public Department getDepart() {
            return depart;
        }

        public void setDepart(Department depart) {
            this.depart = depart;
        }


    }

    /**
     * Найти самого молодого сотрудника
     */
    static Optional<Person> findMostYoungestPerson(List<Person> people) {
        return people.stream()
                .min(Comparator.comparingInt(Person::getAge));
    }

    /**
     * Найти департамент, в котором работает сотрудник с самой большой зарплатой
     */
    static Optional<Department> findMostExpensiveDepartment(List<Person> people) {
        return people.stream()  // Создаем поток из списка Person
                .filter(p -> p.getSalary() > 0)  // Отфильтровываем нулевые зарплаты
                .max(Comparator.comparingDouble(Person::getSalary))  // Находим максимальную зарплату
                .map(Person::getDepart);  // Получаем департамент для сотрудника с самой высокой зарплатой
    }

    /**
     * Сгруппировать сотрудников по департаментам
     */
    static Map<Department, List<Person>> groupByDepartment(List<Person> people) {
        return people.stream()
                .collect(Collectors.groupingBy(Person::getDepart));
    }

    /**
     * Сгруппировать сотрудников по названиям департаментов
     */
    static Map<String, List<Person>> groupByDepartmentName(List<Person> people) {
        return people.stream()
                .collect(Collectors.groupingBy(person -> person.getDepart().getName()));
    }

    /**
     * В каждом департаменте найти самого старшего сотрудника
     */
    static Map<String, Person> getDepartmentOldestPerson(List<Person> people) {
        return people.stream()
                .collect(Collectors.groupingBy(p -> p.getDepart().getName(),
                        Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparingInt(Person::getAge)),
                                p -> p.orElse(null))));
    }

    /**
     * *Найти сотрудников с минимальными зарплатами в своем отделе
     * (прим. можно реализовать в два запроса)
     */
    static List<Person> cheapPersonsInDepartment(List<Person> people) {
        // Группируем людей по их отделу
        Map<Department, List<Person>> peopleByDepartment = people.stream()
                .collect(Collectors.groupingBy(Person::getDepart));
        // Находим сотрудников с минимальными зарплатами в своем отделе
        return peopleByDepartment.values().stream()
                .map(departmentPeople -> departmentPeople.stream()
                        .min(Comparator.comparingDouble(Person::getSalary))
                        .orElse(null))
                .filter(Objects::nonNull) // Удаляем null элементы
                .collect(Collectors.toList());
    }

}
