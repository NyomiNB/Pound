/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package bell;

import java.awt.Cursor;
import java.io.File;
import java.util.Arrays;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
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

    /**
     * Creates new form Pound
     */
    public Pound() {
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
        walkButton.setEnabled(true);
        bathButton.setEnabled(true);
        spinButton.setEnabled(true);
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
        walkButton.setEnabled(false);
        bathButton.setEnabled(false);
        spinButton.setEnabled(false);
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
        if (totalDogs < POUND_SIZE) {
            newButton.setEnabled(false);
        }
    }

    public void disableActionButtons() {
        bathButton.setEnabled(false);
        runButton.setEnabled(false);

        barkButton.setEnabled(false);
        spinButton.setEnabled(false);
        fetchButton.setEnabled(false);
        attackButton.setEnabled(false);
        walkButton.setEnabled(false);
        sitButton.setEnabled(false);
        sleepButton.setEnabled(false);
        if (totalDogs < POUND_SIZE) {
            newButton.setEnabled(false);
        }
    }

    public void updateStats(Dog dog) {
        nameLabel.setText("Name: " + dog.getName());
        ageLabel.setText("Age: " + dog.getAge());
        colorLabel.setText("Color: " + dog.getColor());
        //tempLabel.setText("Temperament: " + dog.getTemp());

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
            Object[] editPossibilities = {"Name", "Breed", "Gender", "Color", "Age", "Weight", "Height", "Medical Conditions"};
            String editChoice = (String) JOptionPane.showInputDialog(displayPanel,
                    "What information would you like to update?", "Update",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
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
                        } else {
                            messageLabel.setText("Update Canceled.");
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

                        } else {
                            messageLabel.setText("Update Canceled.");
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
                        } else if (sexChoice == 1) {
                            dogs[currentDog].setSex("Female");
                        } else {
                            messageLabel.setText("Action Canceled");

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

                        } else {
                            messageLabel.setText("Update Canceled.");
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
                        } else {
                            messageLabel.setText("Update Canceled.");
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
                                dogs[currentDog].setHeight(Double.parseDouble(weightInput));

                            } else {
                                messageLabel.setText("Please enter a valid number value-ex. 5.4, 4, 10.2");
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
                                dogs[currentDog].setHeight(Double.parseDouble(heightInput));

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
                            }
//                        } else {
// Object[] conditionInputOption = {"add Condition", "Remove Condition", "Cancel"};
//int conditionChoices = JOptionPane.showOptionDialog(displayPanel,
//    "Medical Update",
//    "Update",
//    JOptionPane.YES_NO_CANCEL_OPTION,
//    JOptionPane.QUESTION_MESSAGE,
//    null,
//    options,
//    options[2]);
//                        }  
//                                if ((conditionChoices != null) && (conditionChoices.length() > 0)) {
//                                    dogs[currentDog].addCondition(medInput);
//                                }
//                                String[] possibleConditions = new String[totalDogs];
//                                for (int i = 0; i < totalDogs; i++) {
//                                    possibleConditions[i] = dogs[i].getName();
//                                }
//                                String removeInput = (String) JOptionPane.showInputDialog(
//                                        displayPanel,
//                                        "Please enter the dog's name.", "Select Dog",
//                                        JOptionPane.PLAIN_MESSAGE, null,
//                                        removeInput, possibleConditions[0]);
//                                controlPanel.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
//                                disableButtons();
//                                if (removeInput != null) {
//                                    currentDog = Arrays.asList(possibleConditions).indexOf(removeInput);
//                                    updateStats(dogs[currentDog]);
//                                    updatePicture("default");
//                                    controlPanel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
//                                    enableButtons();
//                                }
//                                else if (conditionChoice ==1) {
//                                   String [] currentConditions = new String [dogs[currentDog].getMedicalConditions().size()];
//                                    for (int i = 0; i <currentConditions.length;i++) {
//                                        currentConditions[i] = dogs[currentDog].getMedicalConditions().get(i);
//                                    
//                                }

                        }

                        break;

                }
            } else {
                newButton.setEnabled(false);
                System.out.println("Your pound has reached the limit");

            }
            updateStats(dogs[currentDog]);
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
        walkButton = new javax.swing.JButton();
        eatButton = new javax.swing.JButton();
        fetchButton = new javax.swing.JButton();
        selectButton = new javax.swing.JButton();
        newButton = new javax.swing.JButton();
        barkButton = new javax.swing.JButton();
        openButton = new javax.swing.JButton();
        bathButton = new javax.swing.JButton();
        spinButton = new javax.swing.JButton();
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        messageLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        messageLabel.setText("Hello World");

        iconLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bell/dog1s20release.png"))); // NOI18N

        controlPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Control Panel"));

        layDownButton.setText("Lay Down");
        layDownButton.setEnabled(false);
        layDownButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                layDownButtonActionPerformed(evt);
            }
        });

        walkButton.setText("Go on Walk");
        walkButton.setEnabled(false);
        walkButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                walkButtonActionPerformed(evt);
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

        bathButton.setText("Take Bath");
        bathButton.setEnabled(false);

        spinButton.setText("Spin");
        spinButton.setEnabled(false);

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
                        .addComponent(walkButton)
                        .addGap(18, 18, 18)
                        .addComponent(bathButton)
                        .addGap(18, 18, 18)
                        .addComponent(spinButton))
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

        controlPanelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {attackButton, barkButton, bathButton, eatButton, editButton, fetchButton, layDownButton, newButton, openButton, runButton, saveButton, selectButton, sitButton, sleepButton, spinButton, walkButton});

        controlPanelLayout.setVerticalGroup(
            controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(controlPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(walkButton)
                    .addComponent(bathButton)
                    .addComponent(spinButton)
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

        controlPanelLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {attackButton, barkButton, bathButton, eatButton, editButton, fetchButton, layDownButton, newButton, openButton, runButton, saveButton, selectButton, sitButton, sleepButton, spinButton, walkButton});

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
                        .addComponent(messageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 377, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(113, 113, 113))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, displayPanelLayout.createSequentialGroup()
                        .addComponent(iconLabel)
                        .addGap(98, 98, 98))))
        );
        displayPanelLayout.setVerticalGroup(
            displayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, displayPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(iconLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(messageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
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

        howFullLabel.setText("How full is...");

        howEepyLabel.setText("How eepy is...");

        javax.swing.GroupLayout statsPanelLayout = new javax.swing.GroupLayout(statsPanel);
        statsPanel.setLayout(statsPanelLayout);
        statsPanelLayout.setHorizontalGroup(
            statsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statsPanelLayout.createSequentialGroup()
                .addGroup(statsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(statsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(tempLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ageLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(weightLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(heightLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE)
                        .addComponent(colorLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(statsPanelLayout.createSequentialGroup()
                .addGroup(statsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(SexLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(breedLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(nameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(eepyProgressBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(hungerProgressBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(statsPanelLayout.createSequentialGroup()
                        .addGroup(statsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(howFullLabel)
                            .addComponent(howEepyLabel))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        statsPanelLayout.setVerticalGroup(
            statsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statsPanelLayout.createSequentialGroup()
                .addContainerGap()
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
                .addComponent(howFullLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(hungerProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(howEepyLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(eepyProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(234, 234, 234)
                .addComponent(displayPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(101, 101, 101)
                .addComponent(statsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(332, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(displayPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(statsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(389, Short.MAX_VALUE))
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
                    null, possibleNames[totalDogs % possibleNames.length]);//drop down menny put array in selectionValues

            if ((nameInput != null) && (nameInput.length() > 0)) {
                dogs[totalDogs] = new Dog(this, nameInput);
                updatePicture("default");
                messageLabel.setText("Hi, my name is " + dogs[totalDogs].getName() + ".");
                currentDog = totalDogs;
                updateStats(dogs[currentDog]);
                totalDogs++;
                enableButtons();
                if (totalDogs >= POUND_SIZE) {
                    newButton.setEnabled(false);
                }
            } else {
                newButton.setEnabled(false);
                System.out.println("Your pound has reached the limit");

            }
        }


    }//GEN-LAST:event_newButtonActionPerformed

    private void layDownButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_layDownButtonActionPerformed
        updatePicture("tired");

    }//GEN-LAST:event_layDownButtonActionPerformed

    private void walkButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_walkButtonActionPerformed
        updatePicture("run2");
    }//GEN-LAST:event_walkButtonActionPerformed

    private void eatButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eatButtonActionPerformed
        messageLabel.setText("");
        dogs[currentDog].eat();
    }//GEN-LAST:event_eatButtonActionPerformed

    private void attackButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_attackButtonActionPerformed
        updatePicture("beast");

    }//GEN-LAST:event_attackButtonActionPerformed

    private void fetchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fetchButtonActionPerformed
        messageLabel.setText("");
        //Custom button text
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
            messageLabel.setText("Action Canceled");

        }
        controlPanel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        enableButtons();
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
        controlPanel.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        disableButtons();
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
        messageLabel.setText("");
        controlPanel.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        disableButtons();
        dogs[currentDog].run(4);
        controlPanel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        enableButtons();

    }//GEN-LAST:event_runButtonActionPerformed

    private void sleepButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sleepButtonActionPerformed
        controlPanel.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        disableButtons();
        messageLabel.setText("");
        dogs[currentDog].sleep();
        controlPanel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        enableButtons();
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
             FileNameExtensionFilter dogFilter = new FileNameExtensionFilter("dog files (*.dog)","dog");
            fileChooser.addChoosableFileFilter(dogFilter);
            fileChooser.setFileFilter(dogFilter);
            if (fileChooser.showSaveDialog(displayPanel) == JFileChooser.APPROVE_OPTION) {
             File selectedFile = fileChooser.getSelectedFile();
            }
        } else if (userChoice == 1) {//save entire pound :0
            dogs[currentDog].fetch("fetch1");
        } else {
            //custom title, warning icon
            JOptionPane.showMessageDialog(displayPanel,
                    "Save Action Cancelled",
                    "Save Cancelled",
                    JOptionPane.WARNING_MESSAGE);

            messageLabel.setText("Action Canceled");
        }
        controlPanel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        enableButtons();
    }//GEN-LAST:event_saveButtonActionPerformed

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
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
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
                new Pound().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel SexLabel;
    private javax.swing.JLabel ageLabel;
    private javax.swing.JButton attackButton;
    private javax.swing.JButton barkButton;
    private javax.swing.JButton bathButton;
    private javax.swing.JLabel breedLabel;
    private javax.swing.JLabel colorLabel;
    private javax.swing.JTextArea conditionsTextArea;
    private javax.swing.JPanel controlPanel;
    private javax.swing.JPanel displayPanel;
    private javax.swing.JButton eatButton;
    private javax.swing.JButton editButton;
    private javax.swing.JProgressBar eepyProgressBar;
    private javax.swing.JButton fetchButton;
    private javax.swing.JLabel heightLabel;
    private javax.swing.JLabel howEepyLabel;
    private javax.swing.JLabel howFullLabel;
    private javax.swing.JProgressBar hungerProgressBar;
    private javax.swing.JLabel iconLabel;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton layDownButton;
    private javax.swing.JLabel messageLabel;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JButton newButton;
    private javax.swing.JButton openButton;
    private javax.swing.JButton runButton;
    private javax.swing.JButton saveButton;
    private javax.swing.JButton selectButton;
    private javax.swing.JButton sitButton;
    private javax.swing.JButton sleepButton;
    private javax.swing.JButton spinButton;
    private javax.swing.JPanel statsPanel;
    private javax.swing.JLabel tempLabel;
    private javax.swing.JButton walkButton;
    private javax.swing.JLabel weightLabel;
    // End of variables declaration//GEN-END:variables
}
