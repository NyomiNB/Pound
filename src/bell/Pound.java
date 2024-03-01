/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package bell;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author HPro1
 */
public class Pound extends javax.swing.JFrame {

    final int POUND_SIZE = 10;
    Dog[] dogs = new Dog[POUND_SIZE];
    int totalDogs;
    int currentDog = -1;
//add file in project folder 
    //

    /**
     * Creates new form Pound
     */
    public Pound() {
        UIManager.put("Button.background", new Color(177, 156,  217));
//                       UIManager.put("Button.background", new Color (206, 255, 172));

        initComponents();
//jList1MouseClicked {
//if (evt.getClickCoung()==1) {//change to 2 if wnat double click
//int index = jList1.locationToIndex(evt.getPoint());
//currentDog = index;
//updateStats(dogs[currentDog]);
//changPicture("default);
//}
        /*Dog myDog = new Dog();
        myDog.bark();
        System.out.println(myDog);*/
    }

    public void updatePicture(String picture) {
        iconLabel.setIcon(new ImageIcon(getClass().getResource("dog1s20" + picture + ".png")));

    }

    public void enableButtons() {
        sitButton.setEnabled(true);
        layDownButton.setEnabled(true);
        flyButton.setEnabled(true);
        peeButton.setEnabled(true);
        poopButton.setEnabled(true);
        fetchButton.setEnabled(true);
        eatButton.setEnabled(true);
        sleepButton.setEnabled(true);
        attackButton.setEnabled(true);
        selectButton.setEnabled(true);
        barkButton.setEnabled(true);
        runButton.setEnabled(true);
        saveButton.setEnabled(true);
        editButton.setEnabled(true);
        openButton.setEnabled(true);
        if (totalDogs < POUND_SIZE) {
            newButton.setEnabled(true);
        }
    }

    public void disableButtons() {
        sitButton.setEnabled(false);
        layDownButton.setEnabled(false);
        flyButton.setEnabled(false);
        peeButton.setEnabled(false);
        poopButton.setEnabled(false);
        fetchButton.setEnabled(false);
        eatButton.setEnabled(false);
        sleepButton.setEnabled(false);
        attackButton.setEnabled(false);
        selectButton.setEnabled(false);
        barkButton.setEnabled(false);
        runButton.setEnabled(false);
        saveButton.setEnabled(false);
        editButton.setEnabled(false);
        openButton.setEnabled(false);
        newButton.setEnabled(false);
    }

    public void updateStats(Dog dog) {
        nameLabel.setText("Name: " + dog.getName());
        ageLabel.setText("Age: " + dog.getAge());
        colorLabel.setText("Color: " + dog.getColor());
        tempLabel.setText("Temperament: " + dog.getTemperament()); 
        breedLabel.setText("Breed: " + dog.getBreed());
        SexLabel.setText("Gender: " + dog.getSex());
        heightLabel.setText("Height: " + dog.getHeight() + " inches");
        weightLabel.setText("Weight: " + dog.getWeight() + " pounds");
        howFullLabel.setText("How full is " + dogs[currentDog].getName() + "?");
        hungerProgressBar.setValue(dog.getHowFull());
        howEepyLabel.setText("How eepy is " + dogs[currentDog].getName() + "?");
        eepyProgressBar.setValue(dog.getHowEepy());
        conditionsTextArea.setText("");
        for (int i = 0; i < dog.getMedicalConditions().size(); i++) {
            conditionsTextArea.append(dog.getMedicalConditions().get(i));
            conditionsTextArea.append("\n");
        }
    }

    public void updatePanels() {
        displayPanel.paintImmediately(0, 0, displayPanel.getWidth(), displayPanel.getHeight());
        controlPanel.paintImmediately(0, 0, controlPanel.getWidth(), controlPanel.getHeight());
        statsPanel.paintImmediately(0, 0, statsPanel.getWidth(), statsPanel.getHeight());
    }

    public void editStats() {

        if (totalDogs < POUND_SIZE) {
            ImageIcon icon = new ImageIcon(getClass().getResource("dog1s20beast.png"));
            Object[] editPossibilities = {"Name", "Breed", "Gender", "Color", "Age", "Weight", "Height", "Medical Conditions", "Temperament"};
            String editChoice = (String) JOptionPane.showInputDialog(displayPanel,
                    "What information would you like to update?", "Update",
                    JOptionPane.PLAIN_MESSAGE,
                    icon,
                    editPossibilities,
                    "Name");

            if (editChoice != null) {
                switch (editChoice) {
                    case "Name":
                        //code here
                        Object[] possibleNames = {"Fido", "Spot", "Spike", "Butch", "Lady", "Duke"};
                        String nameInput = (String) JOptionPane.showInputDialog(
                                displayPanel,
                                "Please Enter the Dog's Name!", "Update",
                                JOptionPane.PLAIN_MESSAGE, null,
                                null, possibleNames[totalDogs % possibleNames.length]);//drop down menny put array in selectionValues

                        if ((nameInput != null) && (nameInput.length() > 0)) {
                            dogs[currentDog].setName(nameInput);
                            updateStats(dogs[currentDog]);
                        } else {
                            messageLabel.setText("Update Cancelled.");
                        }
                        break;
                    case "Breed":
                        String breedInput = (String) JOptionPane.showInputDialog(displayPanel,
                                "Please enter the dog's breed", "Update",
                                JOptionPane.PLAIN_MESSAGE,
                                null,
                                null,
                                "Mutt");
                        if ((breedInput != null) && (breedInput.length() > 0)) {
                            dogs[currentDog].setBreed(breedInput);
                            updateStats(dogs[currentDog]);

                        } else {
                            messageLabel.setText("Update Cancelled.");
                        }
                        break;
                    case "Gender":
                        Object[] options = {"Cancel", "Female",
                            "Male"
                        };
                        int sexChoice = JOptionPane.showOptionDialog(displayPanel,
                                "What would you like to change " + dogs[currentDog].getName() + "'s sex to?",
                                "Update",
                                JOptionPane.YES_NO_CANCEL_OPTION,
                                JOptionPane.QUESTION_MESSAGE,
                                null,
                                options,
                                options[2]);
                        if (sexChoice == 2) {
                            dogs[currentDog].setSex("Male");
                            updateStats(dogs[currentDog]);

                        } else if (sexChoice == 1) {
                            dogs[currentDog].setSex("Female");
                            updateStats(dogs[currentDog]);
                        } else {
                            messageLabel.setText("Action Cancelled");

                        }
                        updateStats(dogs[currentDog]);
                        break;
                    case "Color":
                        String colorInput = (String) JOptionPane.showInputDialog(displayPanel,
                                "Please enter the dog's color", "Update",
                                JOptionPane.PLAIN_MESSAGE,
                                null,
                                null,
                                "Brown");
                        if ((colorInput != null) && (colorInput.length() > 0)) {
                            dogs[currentDog].setColor(colorInput);
                            updateStats(dogs[currentDog]);

                        } else {
                            messageLabel.setText("Update Cancelled.");
                        }
                        break;
                    case "Age":
                        SpinnerNumberModel spinnerNumberModel = new SpinnerNumberModel(dogs[currentDog].getAge(), 0, 31, 1);//current, minimum, max, incrimentation
                        JSpinner spinner = new JSpinner(spinnerNumberModel);

                        int newAge = JOptionPane.showOptionDialog(displayPanel,
                                spinner, "Enter Dog Age",
                                JOptionPane.OK_CANCEL_OPTION,
                                JOptionPane.QUESTION_MESSAGE,
                                null, //do not use a custom Icon
                                null, //the titles of buttons
                                null); //default button title
                        if (newAge == JOptionPane.OK_OPTION) {
                            dogs[currentDog].setAge((int) spinner.getValue());
                            updateStats(dogs[currentDog]);
                            messageLabel.setText("Happy Birthday, " + dogs[currentDog].getName() + "!");

                        } else {
                            messageLabel.setText("Update Cancelled.");
                        }
                        break;
                    case "Weight":
                        String weightInput = (String) JOptionPane.showInputDialog(
                                displayPanel,
                                "Please Enter " + dogs[currentDog].getName() + "'s weight in pounds!", "Update",
                                JOptionPane.PLAIN_MESSAGE, null,
                                null, null);//drop down menny put array in selectionValues

                        if ((weightInput != null) && (weightInput.length() > 0)) {
                            if (isNumeric(weightInput)) {
                                Double.parseDouble(weightInput);
                                if (Integer.parseInt(weightInput) > 0) {
                                    if (Integer.parseInt(weightInput) < 350) {
                                        dogs[currentDog].setWeight(Integer.parseInt(weightInput));
                                        updateStats(dogs[currentDog]);

                                    } else {
                                        messageLabel.setText("Please enter a weight 0-350 pounds");

                                    }
                                }
                            } else {
                                messageLabel.setText("Please enter a number value-ex. 23.");
                            }
                        }
                        break;
                    case "Height":
                        String heightInput = (String) JOptionPane.showInputDialog(
                                displayPanel,
                                "Please Enter " + dogs[currentDog].getName() + "'s height in inches!", "Update",
                                JOptionPane.PLAIN_MESSAGE, null,
                                null, null);//drop down menny put array in selectionValues

                        if ((heightInput != null) && (heightInput.length() > 0)) {
                            if (isNumeric(heightInput)) {

                                Double.parseDouble(heightInput);
                                if (Double.parseDouble(heightInput) > 2) {
                                    if (Double.parseDouble(heightInput) < 96) {
                                        dogs[currentDog].setHeight(Double.parseDouble(heightInput));
                                        updateStats(dogs[currentDog]);

                                    }

                                } else {
                                    messageLabel.setText("Please enter a height 2-96 inches");

                                }
                            } else {
                                messageLabel.setText("Please enter a number value-ex. 23.");
                            }
                        }
                        break;
                    case "Medical Conditions":
                        if (dogs[currentDog].getMedicalConditions().size() == 0) {
                            String medInput = (String) JOptionPane.showInputDialog(displayPanel,
                                    "Please enter medical condition", "Update",
                                    JOptionPane.PLAIN_MESSAGE,
                                    null,
                                    null,
                                    null);
                            if ((medInput != null) && (medInput.length() > 0)) {
                                dogs[currentDog].addCondition(medInput);
                                updateStats(dogs[currentDog]);

                            }
                        } else {
                            Object[] conditionInputOption = {"add Condition", "Remove Condition", "Cancel"};
                            int conditionChoices = JOptionPane.showOptionDialog(displayPanel,
                                    "Medical Update",
                                    "Update",
                                    JOptionPane.YES_NO_CANCEL_OPTION,
                                    JOptionPane.QUESTION_MESSAGE,
                                    null,
                                    conditionInputOption,
                                    conditionInputOption[2]);
                            if (conditionChoices == 0) {
                                String conditionInput = (String) JOptionPane.showInputDialog(
                                        displayPanel, "Please enter medical condition.", "Update Information", JOptionPane.PLAIN_MESSAGE, null, null, null);

                                if ((conditionInput != null) && (conditionInput.length() > 0)) {
                                    dogs[currentDog].addCondition(conditionInput);
                                    updateStats(dogs[currentDog]);
                                }
                            } else if (conditionChoices == 1) {
                                String conditionInput = (String) JOptionPane.showInputDialog(
                                        displayPanel, "Please enter medical condition.", "Update Information", JOptionPane.PLAIN_MESSAGE, null, null, null);
                                String[] currentConditions = new String[dogs[currentDog].getMedicalConditions().size()];
                                for (int i = 0; i < currentConditions.length; i++) {
                                    currentConditions[i] = dogs[currentDog].getMedicalConditions().get(i);
                                    dogs[currentDog].removeCondition(conditionInput);
                                    updateStats(dogs[currentDog]);

                                }

                            } else {
                                messageLabel.setText("Update Cancelled.");

                            }
                            break;
                        }
                            case "Temperament":
                             String temperamentInput = (String) JOptionPane.showInputDialog(displayPanel,
                                "Please enter the dog's temperament", "Update",
                                JOptionPane.PLAIN_MESSAGE,
                                null,
                                null,
                                "Tame");
                        if ((temperamentInput != null) && (temperamentInput.length() > 0)) {
                            dogs[currentDog].setTemperament(temperamentInput);
                            updateStats(dogs[currentDog]);

                        } else {
                            messageLabel.setText("Update Cancelled.");
                        }
                        break; 
            }
            }
        }
    }//end of edit stats

    public static boolean isDouble(String text) {
        double newDouble = 0.0;
        try {
            newDouble = Double.valueOf(text);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }//end of isDouble method

    public static boolean isInteger(String text) {
        int newInt = 0;
        try {
            newInt = Integer.valueOf(text);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }//end of isInteger method

    public static boolean isNumeric(String text) {
        return (isDouble(text) || isInteger(text));
    }//end of isNumeric

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        displayPanel = new javax.swing.JPanel();
        messageLabel = new javax.swing.JLabel();
        iconLabel = new javax.swing.JLabel();
        controlPanel = new javax.swing.JPanel();
        layDownButton = new javax.swing.JButton();
        flyButton = new javax.swing.JButton();
        eatButton = new javax.swing.JButton();
        fetchButton = new javax.swing.JButton();
        selectButton = new javax.swing.JButton();
        newButton = new javax.swing.JButton();
        barkButton = new javax.swing.JButton();
        openButton = new javax.swing.JButton();
        peeButton = new javax.swing.JButton();
        poopButton = new javax.swing.JButton();
        sleepButton = new javax.swing.JButton();
        sitButton = new javax.swing.JButton();
        saveButton = new javax.swing.JButton();
        attackButton = new javax.swing.JButton();
        runButton = new javax.swing.JButton();
        editButton = new javax.swing.JButton();
        statsPanel = new javax.swing.JPanel();
        breedLabel = new javax.swing.JLabel();
        nameLabel = new javax.swing.JLabel();
        SexLabel = new javax.swing.JLabel();
        colorLabel = new javax.swing.JLabel();
        tempLabel = new javax.swing.JLabel();
        ageLabel = new javax.swing.JLabel();
        heightLabel = new javax.swing.JLabel();
        weightLabel = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        conditionsTextArea = new javax.swing.JTextArea();
        howFullLabel = new javax.swing.JLabel();
        hungerProgressBar = new javax.swing.JProgressBar();
        howEepyLabel = new javax.swing.JLabel();
        eepyProgressBar = new javax.swing.JProgressBar();
        howFullLabel1 = new javax.swing.JLabel();
        howFullLabel2 = new javax.swing.JLabel();
        howFullLabel3 = new javax.swing.JLabel();
        howFullLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        messageLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        messageLabel.setText(" ");

        iconLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bell/dog1s20release.png"))); // NOI18N

        controlPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Control Panel"));

        layDownButton.setText("Lay Down");
        layDownButton.setEnabled(false);
        layDownButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                layDownButtonActionPerformed(evt);
            }
        });

        flyButton.setText("Fly");
        flyButton.setEnabled(false);
        flyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                flyButtonActionPerformed(evt);
            }
        });

        eatButton.setText("Eat");
        eatButton.setEnabled(false);
        eatButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eatButtonActionPerformed(evt);
            }
        });

        fetchButton.setText("Fetch");
        fetchButton.setEnabled(false);
        fetchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fetchButtonActionPerformed(evt);
            }
        });

        selectButton.setText("Select");
        selectButton.setEnabled(false);
        selectButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectButtonActionPerformed(evt);
            }
        });

        newButton.setText("New");
        newButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newButtonActionPerformed(evt);
            }
        });

        barkButton.setText("Bark");
        barkButton.setEnabled(false);
        barkButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                barkButtonActionPerformed(evt);
            }
        });

        openButton.setText("Open");
        openButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openButtonActionPerformed(evt);
            }
        });

        peeButton.setText("Pee");
        peeButton.setEnabled(false);
        peeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                peeButtonActionPerformed(evt);
            }
        });

        poopButton.setText("Poop");
        poopButton.setEnabled(false);
        poopButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                poopButtonActionPerformed(evt);
            }
        });

        sleepButton.setText("Sleep");
        sleepButton.setEnabled(false);
        sleepButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sleepButtonActionPerformed(evt);
            }
        });

        sitButton.setText("Sit");
        sitButton.setEnabled(false);
        sitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sitButtonActionPerformed(evt);
            }
        });

        saveButton.setText("Save");
        saveButton.setEnabled(false);
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        attackButton.setText("Attack");
        attackButton.setEnabled(false);
        attackButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                attackButtonActionPerformed(evt);
            }
        });

        runButton.setText("Run");
        runButton.setEnabled(false);
        runButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                runButtonActionPerformed(evt);
            }
        });

        editButton.setText("Edit");
        editButton.setEnabled(false);
        editButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout controlPanelLayout = new javax.swing.GroupLayout(controlPanel);
        controlPanel.setLayout(controlPanelLayout);
        controlPanelLayout.setHorizontalGroup(
            controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(controlPanelLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(newButton)
                    .addComponent(selectButton)
                    .addComponent(layDownButton)
                    .addComponent(fetchButton))
                .addGap(18, 18, 18)
                .addGroup(controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(controlPanelLayout.createSequentialGroup()
                        .addComponent(flyButton)
                        .addGap(18, 18, 18)
                        .addComponent(peeButton)
                        .addGap(18, 18, 18)
                        .addComponent(poopButton))
                    .addGroup(controlPanelLayout.createSequentialGroup()
                        .addComponent(eatButton)
                        .addGap(18, 18, 18)
                        .addComponent(sleepButton)
                        .addGap(18, 18, 18)
                        .addComponent(attackButton))
                    .addGroup(controlPanelLayout.createSequentialGroup()
                        .addComponent(barkButton)
                        .addGap(18, 18, 18)
                        .addComponent(sitButton)
                        .addGap(18, 18, 18)
                        .addComponent(runButton))
                    .addGroup(controlPanelLayout.createSequentialGroup()
                        .addComponent(openButton)
                        .addGap(18, 18, 18)
                        .addComponent(saveButton)
                        .addGap(18, 18, 18)
                        .addComponent(editButton)))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        controlPanelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {attackButton, barkButton, eatButton, editButton, fetchButton, flyButton, layDownButton, newButton, openButton, peeButton, poopButton, runButton, saveButton, selectButton, sitButton, sleepButton});

        controlPanelLayout.setVerticalGroup(
            controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(controlPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(flyButton)
                    .addComponent(peeButton)
                    .addComponent(poopButton)
                    .addComponent(layDownButton))
                .addGap(18, 18, 18)
                .addGroup(controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fetchButton)
                    .addComponent(eatButton)
                    .addComponent(sleepButton)
                    .addComponent(attackButton))
                .addGap(18, 18, 18)
                .addGroup(controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(selectButton)
                    .addComponent(barkButton)
                    .addComponent(sitButton)
                    .addComponent(runButton))
                .addGap(18, 18, 18)
                .addGroup(controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(newButton)
                    .addComponent(openButton)
                    .addComponent(saveButton)
                    .addComponent(editButton))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        controlPanelLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {attackButton, barkButton, eatButton, editButton, fetchButton, flyButton, layDownButton, newButton, openButton, peeButton, poopButton, runButton, saveButton, selectButton, sitButton, sleepButton});

        javax.swing.GroupLayout displayPanelLayout = new javax.swing.GroupLayout(displayPanel);
        displayPanel.setLayout(displayPanelLayout);
        displayPanelLayout.setHorizontalGroup(
            displayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(displayPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(displayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, displayPanelLayout.createSequentialGroup()
                        .addComponent(controlPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, displayPanelLayout.createSequentialGroup()
                        .addGroup(displayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(messageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 377, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(iconLabel))
                        .addGap(92, 92, 92))))
        );
        displayPanelLayout.setVerticalGroup(
            displayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, displayPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(iconLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addComponent(messageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(controlPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );

        statsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(""), "Stats Panel"));

        breedLabel.setText("Breed:");

        nameLabel.setText("Name:");

        SexLabel.setText("Gender:");

        colorLabel.setText("Color:");

        tempLabel.setText("Temperament:");

        ageLabel.setText("Age:");

        heightLabel.setText("Height:");

        weightLabel.setText("Weight:");

        jLabel10.setText("Medical Conditions:");

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        conditionsTextArea.setEditable(false);
        conditionsTextArea.setColumns(20);
        conditionsTextArea.setRows(5);
        conditionsTextArea.setText(" ");
        jScrollPane1.setViewportView(conditionsTextArea);

        howFullLabel.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        howFullLabel.setText("How full is...");

        howEepyLabel.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        howEepyLabel.setText("How eepy is...");

        howFullLabel1.setText("0                                                         100");

        howFullLabel2.setText("0                                                         100");

        howFullLabel3.setText("exhausted                                  energized");

        howFullLabel4.setText("starved                                                full");

        javax.swing.GroupLayout statsPanelLayout = new javax.swing.GroupLayout(statsPanel);
        statsPanel.setLayout(statsPanelLayout);
        statsPanelLayout.setHorizontalGroup(
            statsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statsPanelLayout.createSequentialGroup()
                .addGroup(statsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(tempLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ageLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(weightLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(heightLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE)
                    .addComponent(colorLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(statsPanelLayout.createSequentialGroup()
                .addGroup(statsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(SexLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(breedLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(nameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(statsPanelLayout.createSequentialGroup()
                        .addGroup(statsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(statsPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(howFullLabel2))
                            .addComponent(howEepyLabel)
                            .addComponent(howFullLabel)
                            .addComponent(howFullLabel1))
                        .addGap(0, 2, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(hungerProgressBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1)
            .addComponent(eepyProgressBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(statsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(howFullLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(howFullLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        statsPanelLayout.setVerticalGroup(
            statsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statsPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(nameLabel)
                .addGap(18, 18, 18)
                .addComponent(breedLabel)
                .addGap(18, 18, 18)
                .addComponent(SexLabel)
                .addGap(18, 18, 18)
                .addComponent(colorLabel)
                .addGap(18, 18, 18)
                .addComponent(tempLabel)
                .addGap(18, 18, 18)
                .addComponent(ageLabel)
                .addGap(18, 18, 18)
                .addComponent(weightLabel)
                .addGap(18, 18, 18)
                .addComponent(heightLabel)
                .addGap(18, 18, 18)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(howFullLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(hungerProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(howFullLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(howFullLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(howEepyLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(eepyProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(howFullLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(howFullLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(234, 234, 234)
                .addComponent(displayPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(81, 81, 81)
                .addComponent(statsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(846, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(displayPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(statsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(460, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void newButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newButtonActionPerformed
        if (totalDogs < POUND_SIZE) {
            Object[] possibleNames = {"Fido", "Spot", "Spike", "Butch", "Lady", "Duke"};
            String nameInput = (String) JOptionPane.showInputDialog(
                    displayPanel,
                    "Please Enter the Dog's Name!", "New Dog",
                    JOptionPane.PLAIN_MESSAGE, null,
                    null, possibleNames[totalDogs % possibleNames.length]);//drop down  put array in selectionValues

            if ((nameInput != null) && (nameInput.length() > 0)) {
                dogs[totalDogs] = new Dog(this, nameInput);
                updatePicture("default");
                messageLabel.setText("Hi, my name is " + dogs[totalDogs].getName() + ".");
                currentDog = totalDogs;
                updateStats(dogs[currentDog]);
                totalDogs++;

                enableButtons();
                if (totalDogs < POUND_SIZE) {
                    newButton.setEnabled(true);
                } else {
                    newButton.setEnabled(false);
                    System.out.println("Your pound has reached the limit");

                }
            }
        }

    }//GEN-LAST:event_newButtonActionPerformed

    private void layDownButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_layDownButtonActionPerformed
        updatePicture("tired");
    }//GEN-LAST:event_layDownButtonActionPerformed

    private void flyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_flyButtonActionPerformed
        if (dogs[currentDog].getHowEepy() >= 12 && dogs[currentDog].getHowFull() >= 20) {
            messageLabel.setText("");
            controlPanel.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            disableButtons();
            dogs[currentDog].fly(3);
            controlPanel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            enableButtons();
            System.out.println(dogs[currentDog].getHowEepy());
            System.out.println(dogs[currentDog].getHowFull());
        } else if (dogs[currentDog].getHowEepy() < 12 && dogs[currentDog].getHowFull() >= 20) {
            messageLabel.setText(dogs[currentDog].getName() + " is too tired to fly!");

        } else if (dogs[currentDog].getHowEepy() >= 12 && dogs[currentDog].getHowFull() < 20) {
            messageLabel.setText(dogs[currentDog].getName() + " is too hungry to fly!");
        } else {
            messageLabel.setText(dogs[currentDog].getName() + " is too tired and hungry to fly!");
        }
    }//GEN-LAST:event_flyButtonActionPerformed

    private void eatButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eatButtonActionPerformed
        messageLabel.setText("");
        if (dogs[currentDog].getHowFull() < 100) {
            controlPanel.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            disableButtons();
            messageLabel.setText(dogs[currentDog].getName() + " is eating!");
            dogs[currentDog].eat();
            controlPanel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            enableButtons();            
            messageLabel.setText("");

        } else {
            messageLabel.setText(dogs[currentDog].getName() + " is not hungry right now!");
        }

    }//GEN-LAST:event_eatButtonActionPerformed

    private void attackButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_attackButtonActionPerformed
        if (dogs[currentDog].getHowEepy() >= 12 && dogs[currentDog].getHowFull() >= 20) {
            messageLabel.setText("");
            controlPanel.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            disableButtons();
            dogs[currentDog].attack();
            controlPanel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            enableButtons();
            System.out.println(dogs[currentDog].getHowEepy());
            System.out.println(dogs[currentDog].getHowFull());
        } else if (dogs[currentDog].getHowEepy() < 12 && dogs[currentDog].getHowFull() >= 20) {
            messageLabel.setText(dogs[currentDog].getName() + " is too tired to attack!");

        } else if (dogs[currentDog].getHowEepy() >= 12 && dogs[currentDog].getHowFull() < 20) {
            messageLabel.setText(dogs[currentDog].getName() + " is too hungry to attack!");
        } else {
            messageLabel.setText(dogs[currentDog].getName() + " is too tired and hungry to attack!");

        }
    }//GEN-LAST:event_attackButtonActionPerformed

    private void fetchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fetchButtonActionPerformed
        messageLabel.setText("");
        //Custom button text
        if (dogs[currentDog].getHowEepy() < 12 && dogs[currentDog].getHowFull() >= 20) {
            messageLabel.setText(dogs[currentDog].getName() + " is too tired to fetch!");

        } else if (dogs[currentDog].getHowEepy() >= 12 && dogs[currentDog].getHowFull() < 20) {
            messageLabel.setText(dogs[currentDog].getName() + " is too hungry to fetch!");
        } else if (dogs[currentDog].getHowEepy() <= 12 && dogs[currentDog].getHowFull() < 20) {
            messageLabel.setText(dogs[currentDog].getName() + " is too tired and hungry to fetch!");

        } else {
            Object[] options = {"Stick", "Newspaper", "Cancel"};
            int userChoice = JOptionPane.showOptionDialog(displayPanel,
                    "What would you like " + dogs[currentDog].getName() + " to fetch?",
                    "Fetch",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE, null, options, options[2]);
            controlPanel.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            disableButtons();
            if (userChoice == 0) {
                dogs[currentDog].fetch("fetch2");
            } else if (userChoice == 1) {
                dogs[currentDog].fetch("fetch1");
            } else {
                messageLabel.setText("Action Cancelled");

            }
            controlPanel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            enableButtons();
        }
    }//GEN-LAST:event_fetchButtonActionPerformed

    private void selectButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectButtonActionPerformed
        messageLabel.setText("");
        String[] possibleNames = new String[totalDogs];
        for (int i = 0; i < totalDogs; i++) {
            possibleNames[i] = dogs[i].getName();
        }
        String nameInput = (String) JOptionPane.showInputDialog(
                displayPanel,
                "Please enter the dog's name.", "Select Dog",
                JOptionPane.PLAIN_MESSAGE, null,
                possibleNames, possibleNames[0]);
         if (nameInput != null) {
            currentDog = Arrays.asList(possibleNames).indexOf(nameInput);
            updateStats(dogs[currentDog]);
            updatePicture("default");
            controlPanel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            enableButtons();
        }
    }//GEN-LAST:event_selectButtonActionPerformed

    private void barkButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_barkButtonActionPerformed
        messageLabel.setText("");
        dogs[currentDog].bark();
    }//GEN-LAST:event_barkButtonActionPerformed

    private void runButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_runButtonActionPerformed
        if (dogs[currentDog].getHowEepy() >= 12 && dogs[currentDog].getHowFull() >= 20) {
            messageLabel.setText("");
            controlPanel.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            disableButtons();
            dogs[currentDog].run(4);
            controlPanel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            enableButtons();
            System.out.println(dogs[currentDog].getHowEepy());
            System.out.println(dogs[currentDog].getHowFull());
        } else if (dogs[currentDog].getHowEepy() < 12 && dogs[currentDog].getHowFull() >= 20) {
            messageLabel.setText(dogs[currentDog].getName() + " is too tired to run!");

        } else if (dogs[currentDog].getHowEepy() >= 12 && dogs[currentDog].getHowFull() < 20) {
            messageLabel.setText(dogs[currentDog].getName() + " is too hungry to run!");
        } else {
            messageLabel.setText(dogs[currentDog].getName() + " is too tired and hungry to run!");

        }

    }//GEN-LAST:event_runButtonActionPerformed

    private void sleepButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sleepButtonActionPerformed
        //goes up by 50
        if (dogs[currentDog].getHowEepy() < 100) {
            controlPanel.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            disableButtons();
            messageLabel.setText("");
            dogs[currentDog].sleep();
            controlPanel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            enableButtons();
        } else if (dogs[currentDog].getHowEepy() == 100) {
            messageLabel.setText(dogs[currentDog].getName() + " is not tired right now!");
        }

           }//GEN-LAST:event_sleepButtonActionPerformed

    private void sitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sitButtonActionPerformed
        updatePicture("default");
    }//GEN-LAST:event_sitButtonActionPerformed

    private void editButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editButtonActionPerformed
        editStats();
    }//GEN-LAST:event_editButtonActionPerformed

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        messageLabel.setText("");
        //Custom button text
        Object[] options = {"Dog", "Pound", "Cancel"};
        int userChoice = JOptionPane.showOptionDialog(displayPanel,
                "Would you like to save " + dogs[currentDog].getName() + " or your entire pound?",
                "Save",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, options, options[2]);
        if (userChoice == 0) {//save dog
            JFileChooser fileChooser = new JFileChooser();
            FileNameExtensionFilter dogFilter = new FileNameExtensionFilter("dog files (*.dog)", "dog");
            fileChooser.addChoosableFileFilter(dogFilter);
            fileChooser.setFileFilter(dogFilter);
            fileChooser.setSelectedFile(new File(dogs[currentDog].getName() + ".dog"));
            if (fileChooser.showSaveDialog(displayPanel) == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                String filePath = selectedFile.getAbsolutePath();//will get entire file and save it as a string
                if (!filePath.endsWith(".dog")) {
                    selectedFile = new File(filePath + ".dog");
                }
                try {
//                    System.out.println(dogs[currentDog].toString());
                    ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(selectedFile));//converting dog instance into file
                    outputStream.writeObject(dogs[currentDog]);//writing current dog to file
                    outputStream.close();
                    messageLabel.setText("Save of " + (dogs[currentDog].getName()) + " Successful!");
                } catch (IOException err) {
                    JOptionPane.showMessageDialog(displayPanel,
                            "Error writing to location.",
                            "Save Error!",
                            JOptionPane.WARNING_MESSAGE);

                }
            }
        } else if (userChoice == 1) {//save entire pound :0
            JFileChooser fileChooser = new JFileChooser();
            FileNameExtensionFilter poundFilter = new FileNameExtensionFilter("pound files (*.pnd)", "pnd");
            fileChooser.addChoosableFileFilter(poundFilter);
            fileChooser.setFileFilter(poundFilter);
            fileChooser.setSelectedFile(new File("Pound.pnd"));
            if (fileChooser.showSaveDialog(displayPanel) == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                String filePath = selectedFile.getAbsolutePath();//will get entire file and save it as a string
                if (!filePath.endsWith(".pnd")) {
                    selectedFile = new File(filePath + ".pnd");
                }
                try {
                    ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(selectedFile));//converting dog instance into file
                    outputStream.writeObject(dogs);// 
                    outputStream.close();
                    messageLabel.setText("Save of Pound Successful!");
                } catch (IOException err) {
                    JOptionPane.showMessageDialog(displayPanel,
                            "Error writing to location.",
                            "Save Error!",
                            JOptionPane.WARNING_MESSAGE);

                }
            }
        } else {
            //custom title, warning icon
            JOptionPane.showMessageDialog(displayPanel,
                    "Save Action Cancelled",
                    "Save Cancelled",
                    JOptionPane.WARNING_MESSAGE);

            messageLabel.setText("Action Cancelled");
        }
        controlPanel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        enableButtons(); // TODO add your handling code here:
    }//GEN-LAST:event_saveButtonActionPerformed

    private void openButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openButtonActionPerformed
        messageLabel.setText("");
        //Custom button text
        Object[] options = {"Dog", "Pound", "Cancel"};
        int userChoice = JOptionPane.showOptionDialog(displayPanel,
                "Would you like to open a dog or an entire pound?",
                "Open",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, options, options[2]);
        if (userChoice == 0) {//open dog
            JFileChooser fileChooser = new JFileChooser();
            FileNameExtensionFilter dogFilter = new FileNameExtensionFilter("dog files (*.dog)", "dog");
            fileChooser.addChoosableFileFilter(dogFilter);
            fileChooser.setFileFilter(dogFilter);

            if (fileChooser.showOpenDialog(displayPanel) == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                //String filePath = selectedFile.getAbsolutePath();//will get entire file and save it as a string
//                if (!filePath.endsWith(".dog")) {
//                    selectedFile = new File(filePath + ".dog");//not necessary-computer will open file regardless as long as it's a dog fle
//                }
                try {
                    ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(selectedFile));//converting dog instance into file
                    Dog dog = (Dog) inputStream.readObject();
//                    System.out.println(dog.toString());
//                    messageLabel.setText("Open of " + (dogs[currentDog].getName()) + " Successful!");
                    inputStream.close();
                    if (totalDogs < POUND_SIZE) {
                        dogs[totalDogs] = dog;
                        dogs[totalDogs].setCurrentPound(this);
                        currentDog = totalDogs;
                        updateStats(dogs[currentDog]);
                        totalDogs++;
                        updatePicture("default");
                        messageLabel.setText("Hello, my name is " + dogs[currentDog].getName());

                        enableButtons();
                    } else {
                        if (totalDogs <= POUND_SIZE) {
                            newButton.setEnabled(false);
                        } else {

                            Object[] openOptions = {"Overwrite", "Cancel"};
                            int overwriteChoice = JOptionPane.showOptionDialog(displayPanel,
                                    "Would you like to overwrite the current dog?",
                                    "Pound at Capacity :(",
                                    JOptionPane.YES_NO_OPTION,
                                    JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
                            if (overwriteChoice == 0) {
                                dogs[currentDog] = dog;
                                updateStats(dogs[currentDog]);
                                updatePicture("default");
                                messageLabel.setText("Hello, my name is " + dogs[currentDog].getName());
                                enableButtons();
                            } else {
                                messageLabel.setText("Action Cancelled");
                            }
                            newButton.setEnabled(false);
                            System.out.println("Your pound has reached the limit");

                        }
                    }
                } catch (IOException err) {
                    err.printStackTrace();
                    JOptionPane.showMessageDialog(displayPanel,
                            "Error reading file.",
                            "Reading Error!",
                            JOptionPane.WARNING_MESSAGE);
                } catch (ClassNotFoundException err) {
                    JOptionPane.showMessageDialog(displayPanel,
                            "Invalid Dog File",
                            "Open Error",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        } else if (userChoice == 1) {//open entire pound :0
            JOptionPane.showMessageDialog(displayPanel,
                    "Are you sure? The current pound will be DELETED D:",
                    "Overwrite pound?",
                    JOptionPane.WARNING_MESSAGE);
            JFileChooser fileChooser = new JFileChooser();
            FileNameExtensionFilter poundFilter = new FileNameExtensionFilter("pound files (*.pnd)", "pnd");
            fileChooser.addChoosableFileFilter(poundFilter);
            fileChooser.setFileFilter(poundFilter);

            if (fileChooser.showOpenDialog(displayPanel) == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                try {
                    ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(selectedFile));//converting dog instance into file
                    Dog[] doggies = (Dog[]) inputStream.readObject();
                    inputStream.close();
                    if (doggies.length <= POUND_SIZE) {
                        dogs = doggies;
                        dogs[totalDogs].setCurrentPound(this);
                        currentDog = 0;
                        updateStats(dogs[currentDog]);
                        for (int i = 0; i < doggies.length; i++) {
                            if (doggies[i] == null) {
                                totalDogs = i;
                                break;
                            } else {
                                doggies[i].setCurrentPound(this);
                                messageLabel.setText("Hello, my name is " + dogs[currentDog].getName() + "!");

                            }
                        }
                        updatePicture("default");
                        enableButtons();
                    } else {
                        if (totalDogs >= POUND_SIZE) {
                            newButton.setEnabled(false);
                        } else {

                            JOptionPane.showMessageDialog(displayPanel,
                                    "The pound size is too big.",
                                    "Open Error!",
                                    JOptionPane.WARNING_MESSAGE);

                        }
                    }
                } catch (IOException err) {
                    JOptionPane.showMessageDialog(displayPanel,
                            "Error reading file.",
                            "Reading Error!",
                            JOptionPane.WARNING_MESSAGE);
                } catch (ClassNotFoundException err) {
                    JOptionPane.showMessageDialog(displayPanel,
                            "Invalid Dog File",
                            "Open Error",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        } else {
            messageLabel.setText("Action Cancelled");
        }


    }//GEN-LAST:event_openButtonActionPerformed

    private void peeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_peeButtonActionPerformed
        if (dogs[currentDog].getHowEepy() >= 12 && dogs[currentDog].getHowFull() >= 20) {
            messageLabel.setText("");
            controlPanel.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            disableButtons();
            dogs[currentDog].pee();
            controlPanel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            enableButtons();
            System.out.println(dogs[currentDog].getHowEepy());
            System.out.println(dogs[currentDog].getHowFull());
        } else if (dogs[currentDog].getHowEepy() < 12 && dogs[currentDog].getHowFull() >= 20) {
            messageLabel.setText(dogs[currentDog].getName() + " is too tired to go on a walk!");

        } else if (dogs[currentDog].getHowEepy() >= 12 && dogs[currentDog].getHowFull() < 20) {
            messageLabel.setText(dogs[currentDog].getName() + " is too hungry to go on a walk!");
        } else {
            messageLabel.setText(dogs[currentDog].getName() + " is too tired and hungry to go on a walk!");

        } 
    }//GEN-LAST:event_peeButtonActionPerformed

    private void poopButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_poopButtonActionPerformed
        if (dogs[currentDog].getHowEepy() >= 12 && dogs[currentDog].getHowFull() >= 20) {
            messageLabel.setText("");
            controlPanel.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            disableButtons();
            dogs[currentDog].poop();
            controlPanel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            enableButtons();
            System.out.println(dogs[currentDog].getHowEepy());
            System.out.println(dogs[currentDog].getHowFull());
        } else if (dogs[currentDog].getHowEepy() < 12 && dogs[currentDog].getHowFull() >= 20) {
            messageLabel.setText(dogs[currentDog].getName() + " is too tired to go on a walk!");

        } else if (dogs[currentDog].getHowEepy() >= 12 && dogs[currentDog].getHowFull() < 20) {
            messageLabel.setText(dogs[currentDog].getName() + " is too hungry to go on a walk!");
        } else {
            messageLabel.setText(dogs[currentDog].getName() + " is too tired and hungry to go on a walk!");

        }
    }//GEN-LAST:event_poopButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            //Nimbus(NetBeans)
            UIManager.put("nimbusOrange", new Color(173, 216, 230));
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
//Java Default 
//UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
// Set System L&F
//UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Pound.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Pound.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Pound.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Pound.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Pound pound = new Pound();
                Image image = new ImageIcon("dogs.png").getImage();
                pound.setIconImage(image);
                pound.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel SexLabel;
    private javax.swing.JLabel ageLabel;
    private javax.swing.JButton attackButton;
    private javax.swing.JButton barkButton;
    private javax.swing.JLabel breedLabel;
    private javax.swing.JLabel colorLabel;
    private javax.swing.JTextArea conditionsTextArea;
    private javax.swing.JPanel controlPanel;
    private javax.swing.JPanel displayPanel;
    private javax.swing.JButton eatButton;
    private javax.swing.JButton editButton;
    private javax.swing.JProgressBar eepyProgressBar;
    private javax.swing.JButton fetchButton;
    private javax.swing.JButton flyButton;
    private javax.swing.JLabel heightLabel;
    private javax.swing.JLabel howEepyLabel;
    private javax.swing.JLabel howFullLabel;
    private javax.swing.JLabel howFullLabel1;
    private javax.swing.JLabel howFullLabel2;
    private javax.swing.JLabel howFullLabel3;
    private javax.swing.JLabel howFullLabel4;
    private javax.swing.JProgressBar hungerProgressBar;
    private javax.swing.JLabel iconLabel;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton layDownButton;
    private javax.swing.JLabel messageLabel;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JButton newButton;
    private javax.swing.JButton openButton;
    private javax.swing.JButton peeButton;
    private javax.swing.JButton poopButton;
    private javax.swing.JButton runButton;
    private javax.swing.JButton saveButton;
    private javax.swing.JButton selectButton;
    private javax.swing.JButton sitButton;
    private javax.swing.JButton sleepButton;
    private javax.swing.JPanel statsPanel;
    private javax.swing.JLabel tempLabel;
    private javax.swing.JLabel weightLabel;
    // End of variables declaration//GEN-END:variables
}
