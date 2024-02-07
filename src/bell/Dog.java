/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bell;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author HPro1
 */
public class Dog {

    private Pound currentPound;
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

    public Dog(Pound currentPound, String name) {
        this.currentPound = currentPound;
        this.name = "Spot";
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
        String barkSound = "";
        if (weight < 7) {
            System.out.println("Yip Yip");
            barkSound = "bark_1.wav";
        } else if (weight < 40) {
            System.out.println("Arf Arf");
            barkSound = "bark_2.wav";

        } else if (weight < 70) {
            System.out.println("Bark Bark");
            barkSound = "bark_3.wav";

        } else {
            System.out.println("Woof Woof");
            barkSound = "bark_5.wav";

        }
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(getClass().getResource(barkSound));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
            audioInputStream.close();
        } catch (UnsupportedAudioFileException ex) {
            System.err.print(ex);
        } catch (IOException ex) {
            System.err.print(ex);
        } catch (Exception ex) {
            System.err.print(ex);
        }
    }

    public void sleep() {
        System.out.println("honkShoo");
        currentPound.updatePicture("sleep");
        while (howFull > 10) {
            setHowFull(howFull - 10);
            currentPound.updateStats(this);
            try {
                Thread.sleep(4);
            } catch (InterruptedException err) {
                //Lmao
            }
        }
        currentPound.updatePicture("tired");
        currentPound.updateStats(this);
    }

    public void eat() throws InterruptedException {

        currentPound.updatePicture("eat");
        currentPound.updatePanels();

        while (howFull < 100) {
             Thread.sleep(34);
            setHowFull(howFull + 10);
            currentPound.updateStats(this);
        }
        currentPound.updateStats(this);
        currentPound.updatePicture("tired");
        currentPound.updatePanels();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException err) {
            //Lmao
        }
        currentPound.updateStats(this);
        currentPound.updatePicture("default");
        currentPound.updatePanels();


    }

    public void run(int howLong) {
        int paws = 200;
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(getClass().getResource("dog_pant.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
            audioInputStream.close();
        } catch (UnsupportedAudioFileException ex) {
            System.err.print(ex);
        } catch (IOException ex) {
            System.err.print(ex);
        } catch (Exception ex) {
            System.err.print(ex);
        }
        try {

            for (int i = 0; i < howLong; i++) {
                currentPound.disableButtons();

                currentPound.updatePicture("run1");
                currentPound.updatePanels();
                Thread.sleep(paws);
                currentPound.updatePicture("run2");
                currentPound.updatePanels();
                Thread.sleep(paws);
                currentPound.updatePicture("run3");
                currentPound.updatePanels();
                Thread.sleep(paws);
                currentPound.updatePicture("run2");
                currentPound.updatePanels();
                Thread.sleep(paws);
                setHowFull(getHowFull() - 10);
                if (getHowFull() < 10) {
                    currentPound.enableButtons();
                    currentPound.disableActionButtons();

                    break;
                }
                currentPound.updateStats(this);
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Dog.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (howLong > 2) {
            currentPound.updatePicture("tired");
        } else {
            currentPound.updatePicture("default");

        }
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
        if (weight > 0 || height <= 350) {
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
