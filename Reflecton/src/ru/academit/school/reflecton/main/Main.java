package ru.academit.school.reflecton.main;

import ru.academit.school.reflecton.class2.Cat;

import java.lang.reflect.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, InstantiationException {
        String c1Name = Cat.class.getName();
        System.out.println(c1Name);

        Class c1 = Class.forName(c1Name);

        int modifiers = c1.getModifiers();

        if (Modifier.isPublic(modifiers)) {
            System.out.println("Public");
        }

        Field[] fields = c1.getDeclaredFields();
        Arrays.stream(fields).forEach(System.out::print);

        Constructor ctor = c1.getConstructor(String.class, int.class);

        Cat cat1 = (Cat)ctor.newInstance("Kitty", 2);

        System.out.println(cat1);

        Method method1 = c1.getMethod("setName", String.class);

        System.out.println(method1);

        fields[0].setAccessible(true);
        fields[0].set(cat1, "!!223123");

        System.out.println(cat1);
    }
}
