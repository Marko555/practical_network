//package com.company;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.*;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class ViewComputersGUI extends JFrame {


    private ComputerLaptop cl;
    private UserLaptop ul;


    private ArrayList<JCheckBox> checkBoxes;

    private JPanel contentPane;
    private JPanel tablePane;
    private JPanel infoPanel;
    private JPanel buttonPane;
    private JScrollPane scrollPane;
    private DefaultTableModel modelTable;
    Class[] colTypes;
    TableModel data;

    private JTable jTable;
    private JLabel lUnit;
    private JComboBox unitsBox;
    private JButton bAdd;
    private JButton bDelete;
    private JButton bDone;

    //private Unit selectedUnit;

    public ViewComputersGUI() {

        checkBoxes = new ArrayList<JCheckBox>();
        scrollPane = new JScrollPane();
        modelTable = new DefaultTableModel() {
            Class[] types = new Class [] {
                    java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        };
        jTable = new JTable(modelTable);

        try {
            FileInputStream saveFile = new FileInputStream("units.dat");
            ObjectInputStream save = new ObjectInputStream(saveFile);
            this.unitList = (UnitList) save.readObject();
            save.close();

        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "First you have to create New Student Class", "WRONG !!!", JOptionPane.ERROR_MESSAGE);

        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        contentPane = (JPanel) this.getContentPane();
        contentPane.setPreferredSize(new Dimension(400, 100));
        tablePane = new JPanel();
        this.setTitle("Look at the created Student Classes");

        infoPanel = new JPanel();
        infoPanel.setLayout(new FlowLayout());
        lUnit = new JLabel("Unit:");
        infoPanel.add(lUnit);
        unitsBox = new JComboBox();
        try {
            for (Unit u : unitList.getUnits()) {
                unitsBox.addItem(u.getUnitName());
            }
        } catch (NullPointerException ex) {

        }
        unitsBox.setSelectedIndex(-1);
        unitsBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                click_actionPerformed(e);
            }
        });

        buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout());
        bAdd = new JButton("Add Students");
        bAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JPanel inputDialog = new JPanel();
                JPanel info = new JPanel();
                JPanel buttons = new JPanel();
                JLabel lName = new JLabel("Name");
                JTextField tName = new JTextField();
                JLabel lTel = new JLabel("Telephone Number");
                JTextField tTel = new JTextField();
                JLabel lEmail = new JLabel("Email");
                JTextField tEmail = new JTextField();
                info.setLayout(new GridLayout(3, 3));
                info.add(lName);
                info.add(tName);
                info.add(lTel);
                info.add(tTel);
                info.add(lEmail);
                info.add(tEmail);
                inputDialog.add(info, BorderLayout.CENTER);
                Student newStudent = null;
                int result = JOptionPane.showConfirmDialog(null, inputDialog,
                        "Please Enter New Students Info", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    try {
                        newStudent = new Student(tName.getText(), Integer.parseInt(tTel.getText()), tEmail.getText());
                        studentList.addStudent(newStudent);
                        modelTable.addRow(new Object[]{newStudent.getId(), newStudent.getName(), newStudent.getTel(), newStudent.getEmail(), newStudent.checkAbsenceLimit(studentList.getUnit().getUnitName()), studentList.getUnit().getAbsencesPermitted()});

                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Specify student name and email with letters and telephone with numbers." + "\n" + "Try again by cliking add students button", "ATTENTION !!!", JOptionPane.ERROR_MESSAGE);
                    }
                }
                try {
                    FileOutputStream saveFile = new FileOutputStream("saveFile_" + unitsBox.getSelectedItem().toString() + ".dat");
                    ObjectOutputStream save = new ObjectOutputStream(saveFile);
                    save.writeObject(studentList);
                    save.close();
                } catch (FileNotFoundException ex) {

                } catch (IOException ex) {
                    ex.printStackTrace();
                }


                try {
                    FileInputStream saveFile = new FileInputStream("saveFile_" + unitsBox.getSelectedItem().toString() + ".dat");
                    ObjectInputStream save = new ObjectInputStream(saveFile);
                    studentList = (StudentList) save.readObject();
                    save.close();

                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }


            }
        });


        bDelete = new JButton("Delete Students");
        bDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Object id = modelTable.getValueAt(jTable.getSelectedRow(), 0);
                studentList.deleteStudent(studentList.getStudentById((Integer) id));


                if (jTable.getSelectedRow() != -1){
                    modelTable.removeRow(jTable.getSelectedRow());
                }
                else
                    JOptionPane.showMessageDialog(null, "No line selected!", "Warning", JOptionPane.WARNING_MESSAGE);

                try {
                    FileOutputStream saveFile = new FileOutputStream("saveFile_" + unitsBox.getSelectedItem().toString() + ".dat");
                    ObjectOutputStream save = new ObjectOutputStream(saveFile);
                    save.writeObject(studentList);
                    save.close();
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

            }
        });


        infoPanel.add(unitsBox);
        bDone = new JButton("Done");
        bDone.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                MainGUI mg = new MainGUI();
                mg.setLocationRelativeTo(null);
                mg.pack();
                mg.setVisible(true);
            }
        });
        buttonPane.add(bDone);

        contentPane.add(buttonPane, BorderLayout.SOUTH);
        contentPane.add(infoPanel, BorderLayout.NORTH);
    }

    private void click_actionPerformed(ActionEvent e) {
        this.setSize(600, 400);

        int rows = modelTable.getRowCount();
        for(int i = rows - 1; i >=0; i--)
        {
            modelTable.removeRow(i);
        }

        try {
            FileInputStream saveFile = new FileInputStream("saveFile_" + this.unitsBox.getSelectedItem().toString() + ".dat");
            ObjectInputStream save = new ObjectInputStream(saveFile);
            this.studentList = (StudentList) save.readObject();
            save.close();

            modelTable.setColumnCount(6);
            modelTable.setColumnIdentifiers(new Object[]{"Student ID", "Student Name","Contact Number", "Email", "Student Absences", "Absences Permitted"});
            for (Student s : studentList.getArrayOfStudents()) {
                modelTable.addRow(new Object[]{s.getId(), s.getName(), s.getTel(), s.getEmail(), s.checkAbsenceLimit(studentList.getUnit().getUnitName()), studentList.getUnit().getAbsencesPermitted()});
            }
            scrollPane.setViewportView(jTable);

            buttonPane.add(bAdd);
            buttonPane.add(bDelete);

            buttonPane.repaint();

            contentPane.add(scrollPane, BorderLayout.CENTER);

        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        } catch (FileNotFoundException e1) {
            int res = JOptionPane.showConfirmDialog(null, "There is not a Student List for that Unit!\nDo you want to create one now?","Warning", JOptionPane.YES_NO_OPTION);
            if (res == JOptionPane.YES_OPTION){
                dispose();
                JFrame unitGui = new UnitGUI();
                unitGui.pack();
                unitGui.setLocationRelativeTo(null);
                unitGui.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                unitGui.setVisible(true);
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }

    }
}
