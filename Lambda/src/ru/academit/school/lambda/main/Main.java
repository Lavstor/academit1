package ru.academit.school.lambda.main;

import ru.academit.school.lambda.person.Person;

import java.util.*;
import java.util.stream.*;

public class Main {
    public static void main(String[] args) {
        Person p1 = new Person(38, "Lilia");
        Person p2 = new Person(25, "Lilli");
        Person p3 = new Person(21, "Vanya");
        Person p4 = new Person(94, "Jack");
        Person p5 = new Person(21, "Kate");
        Person p6 = new Person(5, "Jack");
        Person p7 = new Person(26, "Katya");

        List<Person> persons = Arrays.asList(p1, p2, p3, p4, p5, p6, p7);

        List<String> filteredByNames = persons.stream()
                .map(Person::getName)
                .distinct()
                .collect(Collectors.toList());

        System.out.println();

        String printNames = filteredByNames
                .stream()
                .collect(Collectors.joining(", ", "Names: ", "."));

        System.out.println(printNames);
        System.out.println("__________________");

        List<Person> filteredByAge = persons.stream()
                .filter(x -> x.getAge() < 18)
                .collect(Collectors.toList());

        String printNames2 = filteredByAge
                .stream().map(Person::getName)
                .collect(Collectors.joining(", ", "Names: ", "."));

        System.out.println(printNames2);

        @SuppressWarnings("OptionalGetWithoutIsPresent")
        double age = filteredByAge.stream()
                .mapToInt(Person::getAge)
                .average()
                .getAsDouble();

        System.out.println("Average: " + age);
        System.out.println("__________________");

        Map<String, Double> personsByNames = persons.stream()
                .collect(Collectors.groupingBy(Person::getName, Collectors.averagingDouble(Person::getAge)));

        personsByNames.forEach((name, averageAge) ->
                System.out.printf("Average age %s = %s%n", name, averageAge));

        System.out.println("__________________");
        List<Person> persons3 = persons.stream()
                .filter(p -> p.getAge() > 20 && p.getAge() < 45)
                .sorted(Comparator.comparingInt(Person::getAge).reversed())
                .collect(Collectors.toList());

        persons3.forEach(p -> System.out.println(p.getName()));
        System.out.println("__________________");

        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите кол-во итераций: ");

        DoubleStream sqrtStream = DoubleStream.iterate(1, x -> x + 1)
                .map(Math::sqrt)
                .limit(scanner.nextLong());

        sqrtStream.forEach(s -> System.out.print(s + " "));
        System.out.println();
        System.out.println("________________________");

        System.out.println("Числа Фибоначчи. Введите кол-во итераций: ");

        System.out.print(Stream.iterate(new int[]{0, 1}, num -> new int[]{num[1], num[0] + num[1]})
                .limit(scanner.nextLong())
                .map(t -> t[0])
                .collect(Collectors.toList())
                .toString());
    }
}
