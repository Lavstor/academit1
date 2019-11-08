package ru.academit.school.reflecton.class2;

public class Cat {
    private String name = "Котик";
    private int age =  15;

    public Cat(String name, int age){
        this.name = name;
        this.age = age;
    }

    public void setAge(int age){
        this.age = age;
    }

    public void setName(String name) {
   this.name = name;
    }

    public int getCatAge(){
        return age;
    }

    public String getCatName(){
        return name;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Возраст " + age);
        builder.append(System.lineSeparator());
        builder.append("Имя " + name);

        return builder.toString();
    }
}
