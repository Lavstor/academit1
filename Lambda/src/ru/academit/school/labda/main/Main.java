package ru.academit.school.labda.main;

import ru.academit.school.labda.person.Person;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        Person p1 = new Person(18, "Lili");
        Person p2 = new Person(15, "Lili");
        Person p3 = new Person(11, "Vanya");
        Person p4 = new Person(94, "Jack");
        Person p5 = new Person(21, "Kate");
        Person p6 = new Person(94, "Jack");
        Person p7 = new Person(21, "Kate");

        Consumer<String> names = c -> System.out.println("Name: " + c + " ");

        List<Person> persons = Arrays.asList(p1, p2, p3, p4, p5, p6, p7);


        List<String> filteredByNames = persons.stream()
                .map(Person::getName)
                .distinct()
                .collect(Collectors.toList());

        filteredByNames.forEach(names);
        System.out.println();

        String printNames = filteredByNames
                .stream()
                .collect(Collectors.joining(", ", "Names: ", "."));

        System.out.println(printNames);
        System.out.println();

        List<Person> filteredByAge = persons.stream()
                .filter(x -> x.getAge() < 18)
                .collect(Collectors.toList());

        filteredByAge.forEach(x -> System.out.println(x.getName()));

        int age = filteredByAge.stream().mapToInt(x -> x.getAge() / filteredByAge.size());
    }
}
