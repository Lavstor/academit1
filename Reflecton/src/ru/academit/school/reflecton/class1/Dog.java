package ru.academit.school.reflecton.class1;

public class Dog {
    private String name;
    private int age;

    public Dog(String name, int age){
        this.name = name;
        this.age = age;
    }

    public int getDogAge(){
        return age;
    }

    public String getDogName(){
        return name;
    }
}
