/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package csfmarksanalysismvc;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.text.BadLocationException;

/**
 *
 * @author Tommy Grabowski
 */
public class CSFController {

    private final boolean dMode = CSFMarksAnalysisMVC.developerMode;
    private final CSFModel model;
    private final CSFView view;
    private final String default_file = System.getProperty("user.dir")+"\\gradesfile.txt";
    File defaultFile = new File(default_file);

    /**
     * Linking Controller to View and Model.
     * @param m model reference
     * @param v view reference
     */
    public CSFController(CSFModel m, CSFView v) {
        if(dMode) System.out.println("Controller: CSFController(m, v) Constructor");
        model = m;
        view = v;
        initView();
        initModel();
    }

    /**
     * Setting up few variables important for handling View.
     */
    public final void initView() {
        if(dMode) System.out.println("Controller: initView()");
        JButton defaultButton = view.getBtnAddElement();
        SwingUtilities.getRootPane(defaultButton).setDefaultButton(defaultButton);
        view.setVisible(true);
        view.getFileChooserTxt().setCurrentDirectory(defaultFile);
    }

    /**
     * Linking gradesArray between View and Model.
     */
    public final void initModel() {
        if(dMode) System.out.println("Controller: initModel()");
        model.setGradesArray(view.getGradesArray());
    }

    /**
     * Introducing Action Listeners to Interface's buttons.
     */
    public final void initController() {
        if(dMode) System.out.println("Controller: initController()");
        view.getBtnAddElement().addActionListener(e -> {
            addElement(e);
            update();
        });
        view.getBtnClearAll().addActionListener(e -> {
            clearAll(e);
            update();
        });
        view.getBtnDeleteElement().addActionListener(e -> {
            deleteElement(e);
            update();
        });
        view.getBtnExit().addActionListener(e -> exitApp(e));
        view.getBtnExport().addActionListener(e -> exportToFile(e));
        view.getBtnExportReport().addActionListener(e -> exportReportToFile(e));
        view.getBtnImport().addActionListener(e -> {
            importFromFile(e);
            update();
        });
    }
    private void addElement(ActionEvent evt) {
        if(dMode) System.out.println("Controller: addElement(evt)");
        try
        {
            if(!(view.getTxtNewGrade().getText().isBlank())
                && view.getTxtNewGrade().getText().length() == 3
                && Integer.parseInt(view.getTxtNewGrade().getText(0, 1)) < 6
                && Integer.parseInt(view.getTxtNewGrade().getText(1, 1)) < 6
                && Integer.parseInt(view.getTxtNewGrade().getText(2, 1)) < 6)
            {
                view.getGradesArray().addElement(view.getTxtNewGrade().getText());
                view.getTxtNewGrade().setText("");
            }
            else
            {
                view.getTxtNewGrade().setText("Please provide correct format [###; 0-5]!");
                view.getTxtNewGrade().setBorder(BorderFactory.createLineBorder(Color.red));
            }
        }catch(NumberFormatException | BadLocationException e){
            view.getTxtNewGrade().setText("Please provide correct format [###; 0-5]!");
            view.getTxtNewGrade().setBorder(BorderFactory.createLineBorder(Color.red));
        }
    }
    private void clearAll(ActionEvent evt) {
        if(dMode) System.out.println("Controller: clearAll(evt)");
        view.getGradesArray().clear();
    }
    private void deleteElement(ActionEvent evt) {
        if(dMode) System.out.println("Controller: deleteElement(evt)");
        if(!model.getGradesArray().isEmpty() && view.getListGrades().getSelectedIndex()!=-1)
        {
            int confirm = JOptionPane.showConfirmDialog(null ,"Sure? You want to delete this element?", "Confirm!",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
            if(confirm == JOptionPane.YES_OPTION){
               model.getGradesArray().remove(view.getListGrades().getSelectedIndex());
            }
        }
        else if(!model.getGradesArray().isEmpty() && view.getListGrades().getSelectedIndex()==-1)
        {
            JOptionPane.showMessageDialog(null , "No element was selected!", "Warning!", JOptionPane.WARNING_MESSAGE);
        }
    }
    private void exportToFile(ActionEvent evt) {
        if(dMode) System.out.println("Controller: exportToFile(evt)");
        if(!view.getCBoxDefaultFile().isSelected())
        {
            int returnVal = view.getFileChooserTxt().showOpenDialog(view);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = view.getFileChooserTxt().getSelectedFile();
                System.out.println("Selected file: " + file.getAbsolutePath());
                try {
                    BufferedWriter gradesFileWriter = new BufferedWriter(new FileWriter(file.getAbsolutePath()));
                    String data;
                    for(int i = 0; i < model.getGradesArray().getSize(); i++)
                    {
                        data = model.getGradesArray().getElementAt(i).toString();
                        gradesFileWriter.write(data);
                        gradesFileWriter.newLine();
                    }
                    gradesFileWriter.flush();
                    gradesFileWriter.close();
                    System.out.println("Successfully wrote to the file.");
                } catch (IOException e) {
                  System.out.println("problem accessing file" + file.getAbsolutePath());
                }
            } else {
                System.out.println("File access cancelled by user.");
            }
        }
        else
        {
            if(dMode) System.out.println("Selected file: " + defaultFile.getAbsolutePath());
            try {
                BufferedWriter gradesFileWriter = new BufferedWriter(new FileWriter(defaultFile.getAbsolutePath()));
                String data;
                for(int i = 0; i < model.getGradesArray().getSize(); i++)
                {
                    data = model.getGradesArray().getElementAt(i).toString();
                    gradesFileWriter.write(data);
                    gradesFileWriter.newLine();
                }
                gradesFileWriter.flush();
                gradesFileWriter.close();
                System.out.println("Successfully wrote to the file.");
            } catch (IOException e) {
                  System.out.println("problem accessing file" + defaultFile.getAbsolutePath());
            }
        }
    }
    private void exportReportToFile(ActionEvent evt) {
        if(dMode) System.out.println("Controller: exportReportToFile(evt)");
        int returnVal = view.getFileChooserTxt().showOpenDialog(view);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = view.getFileChooserTxt().getSelectedFile();
            System.out.println("Selected file: " + file.getAbsolutePath());
            try {
                BufferedWriter gradesFileWriter = new BufferedWriter(new FileWriter(file.getAbsolutePath()));
                for(String data : generateReport())
                {
                    gradesFileWriter.write(data);
                    gradesFileWriter.newLine();
                }
                gradesFileWriter.flush();
                gradesFileWriter.close();
                System.out.println("Successfully wrote to the file.");
            } catch (IOException e) {
              System.out.println("problem accessing file" + file.getAbsolutePath());
            }
        } else {
            System.out.println("File access cancelled by user.");
        }
    }
    private ArrayList<String> generateReport(){
        if(dMode) System.out.println("Controller: generateReport()");
        ArrayList<String> reportData = new ArrayList();
        reportData.add("Number of elements:\t\t\t"
                + model.getGradesArray().size());

        reportData.add("");
        reportData.add("PASS (total):\t\t\t\t"
                + model.getTotalFullPaperPass());
        reportData.add("PASS (%):\t\t\t\t\t"
                + (double)(model.getTotalFullPaperPass()*100)/model.getGradesArray().size() + "%");

        reportData.add("");
        reportData.add("FAIL (total):\t\t\t\t"
                + (model.getGradesArray().size()-model.getTotalFullPaperPass()));
        reportData.add("FAIL (%):\t\t\t\t\t"
                + (double)((model.getGradesArray().size()-model.getTotalFullPaperPass())*100)/model.getGradesArray().size() + "%");

        reportData.add("");
        reportData.add("FullMarks Pass (total):\t\t"
                + model.getTotalFullMarksPass());
        reportData.add("FullMarks Pass (%):\t\t\t"
                + (double)(model.getTotalFullMarksPass()*100)/model.getGradesArray().size() + "%");
        reportData.add("BorderLine Pass (total):\t"
                + model.getTotalBorderLinePass());
        reportData.add("BorderLine Pass (%):\t\t"
                + (double)(model.getTotalBorderLinePass()*100)/model.getGradesArray().size() + "%");

        reportData.add("");
        reportData.add("Section 1 Fail (%):\t\t\t"
                + model.getPercFailSec(1) + "%");
        reportData.add("Section 2 Fail (%):\t\t\t"
                + model.getPercFailSec(2) + "%");
        reportData.add("Section 3 Fail (%):\t\t\t"
                + model.getPercFailSec(3) + "%");

        reportData.add("");
        reportData.add("Elements: " + model.getGradesArray().toString());


        return reportData;
    }
    private void importFromFile(ActionEvent evt) {
        if(dMode) System.out.println("Controller: importFromFile(evt)");
        if(!view.getCBoxDefaultFile().isSelected())
        {
            int returnVal = view.getFileChooserTxt().showOpenDialog(view);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = view.getFileChooserTxt().getSelectedFile();
                System.out.println("Selected file: " + file.getAbsolutePath());
                try {
                    File gradesFile = new File(file.getAbsolutePath());
                    Scanner in = new Scanner(gradesFile);
                    while (in.hasNext()) {
                        model.getGradesArray().addElement(in.nextLine());
                    }
                } catch (FileNotFoundException e) {
                  System.out.println("problem accessing file" + file.getAbsolutePath());
                }
            } else {
                System.out.println("File access cancelled by user.");
            }
        }
        else
        {
            try {
                    File gradesFile = new File(defaultFile.getAbsolutePath());
                System.out.println("Selected file: " + gradesFile.getAbsolutePath());
                    Scanner in = new Scanner(gradesFile);
                    while (in.hasNext()) {
                        model.getGradesArray().addElement(in.nextLine());
                    }
                } catch (FileNotFoundException e) {
                  System.out.println("problem accessing file" + defaultFile.getAbsolutePath());
                }
        }
    }
    private void exitApp(ActionEvent evt) {
        if(dMode) System.out.println("Controller: exitApp(evt)");
        System.exit(0);
    }
    private void update(){
        if(dMode) System.out.println("Controller: update()");
        updateModel();
        updateView();
    }
    private void updateModel(){
        if(dMode) System.out.println("Controller: updateModel()");

        //Pass Percentag
        model.setPercPass((double)(statsFullPaperPass() * 100) / numberOfMarksTotal());

        //Fail Percentage
        model.setPercFail((double)((numberOfMarksTotal() - statsFullPaperPass()) * 100) / numberOfMarksTotal());

        //First Section Fail Percentage
        model.setPercFailSec((double)(statsFailSec(1) * 100)/numberOfMarksTotal(), 1);

        //Second Section Fail Percentage
        model.setPercFailSec((double)(statsFailSec(2) * 100) / numberOfMarksTotal(), 2);

        //Third Section Fail Percentage
        model.setPercFailSec((double)(statsFailSec(3) * 100) / numberOfMarksTotal(), 3);

        //Border Line Pass Total
        model.setTotalBorderLinePass(statsBorderLinePass());

        //Full Mark Pass Total
        model.setTotalFullMarksPass(statsFullMarksPass());

        //Full Paper Pass Total
        model.setTotalFullPaperPass(statsFullPaperPass());
    }
    private int statsFullMarksPass(){
        if(dMode) System.out.println("Controller: statsFullMarksPass()");
        int numberOfMarksTotal = model.getGradesArray().size();
        int statsFullMarksPass = 0;
        for(int i = 0; i<numberOfMarksTotal; i++){
            if(model.getGradesArray().get(i).equals("555"))
                statsFullMarksPass++;
        }
        return statsFullMarksPass;
    }
    private int statsBorderLinePass(){
        if(dMode) System.out.println("Controller: statsBorderLinePass()");
        int numberOfMarksTotal = model.getGradesArray().size();
        int statsBorderLinePass = 0;
        for(int i = 0; i<numberOfMarksTotal; i++){
            if(model.getGradesArray().get(i).toString().equals("333"))
                statsBorderLinePass++;
        }
        return statsBorderLinePass;
    }
    private int statsFullPaperPass(){
        if(dMode) System.out.println("Controller: statsFullPaperPass()");
        int numberOfMarksTotal = model.getGradesArray().size();
        int statsFullPaperPass = 0;
        for(int i = 0; i<numberOfMarksTotal; i++){
            if(model.getGradesArray().get(i).toString().matches("[3-5][3-5][3-5]"))
                statsFullPaperPass++;
        }
        return statsFullPaperPass;
    }
    private int statsFailSec(int section){
        if(dMode) System.out.println("Controller: statsFailSec("+ section +")");
        int numberOfMarksTotal = model.getGradesArray().size();
        int statsFailSec = 0;
        String regex = "";
        switch (section){
            case 1 -> regex = "[0-2][0-5][0-5]";
            case 2 -> regex = "[0-5][0-2][0-5]";
            case 3 -> regex = "[0-5][0-5][0-2]";
        }
        for(int i = 0; i<numberOfMarksTotal; i++){
            if(model.getGradesArray().get(i).toString().matches(regex))
                statsFailSec++;
        }
        return statsFailSec;
    }
    private int numberOfMarksTotal(){
        if(dMode) System.out.println("Controller: numberOfMarksTotal()");
        return model.getGradesArray().size();
    }
    private void updateView(){
        if(dMode) System.out.println("Controller: updateView()");
        //Refreshing View
        DecimalFormat df = new DecimalFormat("#,###.##");
        df.setRoundingMode(RoundingMode.HALF_UP);

        view.getLblBorderLinePass().setText(df.format(
                model.getTotalBorderLinePass()
        ));

        view.getLblFail().setText(df.format(
                model.getPercFail()
        ) + "%");

        view.getLblFailedSection(1).setText(df.format(
                model.getPercFailSec(1)
        ) + "%");

        view.getLblFailedSection(2).setText(df.format(
                model.getPercFailSec(2)
        ) + "%");

        view.getLblFailedSection(3).setText(df.format(
                model.getPercFailSec(3)
        ) + "%");

        view.getLblFullMarksPass().setText(Integer.toString(
                model.getTotalFullMarksPass()
        ));

        view.getLblFullPaperPass().setText(Integer.toString(
                model.getTotalFullPaperPass()
        ));

        view.getLblNumberOfElements().setText(Integer.toString(
                model.getGradesArray().getSize()
        ));

        view.getLblPass().setText(df.format(
                model.getPercPass()
        )+ "%");
    }
}