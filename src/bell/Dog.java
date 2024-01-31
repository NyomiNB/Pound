/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bell;

import java.util.ArrayList;

/**
 *
 * @author HPro1
 */
public class Dog {

    private String name;
    private int age;
    private String breed;
    private String color;
    private double height;//in inches
    private double weight;//in pounds
    private String sex;
    private int howFull;//1-10-100 being full 1 being hungry
    private ArrayList<String> medicalConditions = new ArrayList<>();

    public Dog(String name, int age, String breed, String color, int height, double weight, String sex, ArrayList<String> medicalConditions) {
        this.name = name;
        this.age = age;
        this.breed = breed;
        this.color = color;
        this.height = height;
        this.weight = weight;
        this.sex = sex;
        howFull = 5;
    }

    public Dog() {
        name = "Spot";
        age = 0;
        breed = "Golden Retriever";
        color = "Brown";
        height = 22;
        weight = 70;
        sex = "Female";
        howFull = 5;
    }

    //custom methods here
    public void bark() {
        if (weight < 7) {
            System.out.println("Yip Yip");
        } else if (weight < 40) {
            System.out.println("Arf Arf");
        } else if (weight < 70) {
            System.out.println("Bark Bark");
        } else {
            System.out.println("Woof Woof");
        }
    }

    public void sleep() {
        System.out.println("honkShoo");
    }
    //getter and setters after constructors

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
        if (age < 35 && age >= 0) {
            this.age = age;
        }
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        
        this.breed = breed;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        String newColor = color.toLowerCase();
         for (int i = 0; i < color.length(); i++) {
            if (newColor.charAt(i) < 'a' || (newColor.charAt(i) > 'z')) {
                if (newColor.charAt(i) != ' ') {
                    return;
                }
            }
        }
        this.color = newColor;
    }

    public double getHeight() {
          if (height >= 2 || height < 96) {
              this.height = height;
        }   
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
         if (weight > 0|| height <= 350) {
              this.height = height;
        }   
        
        this.weight = weight;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        if (sex.equalsIgnoreCase("male") || (sex.equalsIgnoreCase("male")) || (sex.equalsIgnoreCase("boy"))) {
            this.sex = "Male";
        } else if (sex.equalsIgnoreCase("female") || (sex.equalsIgnoreCase("f")) || (sex.equalsIgnoreCase("girl"))) {
            this.sex = "Female";
        }
        this.sex = sex;
    }

    public int getHowFull() {
        return howFull;
    }

    public void setHowFull(int howFull) {
        if (howFull > 100) {
            this.howFull = 100;
        } else if (howFull < 1) {
            this.howFull = 1;
        } else {
            this.howFull = howFull;
        }
    }

    public ArrayList<String> getMedicalConditions() {
        return medicalConditions;
    }

    public void setMedicalConditions(ArrayList<String> medicalConditions) {
        this.medicalConditions = medicalConditions;
    }//end of doge

    public void addCondition(String condition) {
        medicalConditions.add(condition);
    }

    public void removeCondition(String condition) {
        medicalConditions.remove(condition);//deletes only first instance
    }

    @Override
    public String toString() {
        String output = "Name: " + name + ", age=" + age + ", breed=" + breed + ", color=" + color + ", height=" + height + ", weight=" + weight + ", sex=" + sex + ", howFull=" + howFull + '}';
        output += "\nMedical Conditions:";
        for (int i = 0; i < medicalConditions.size(); i++) {
            output += medicalConditions.get(i) + "\n";
        }
        return output;
    }

}
