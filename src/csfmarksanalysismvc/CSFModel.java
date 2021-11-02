/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package csfmarksanalysismvc;

import javax.swing.DefaultListModel;


/**
 *
 * @author tg
 */
public class CSFModel {
    private DefaultListModel gradesArray = new DefaultListModel();
    
    private final boolean dMode = CSFMarksAnalysisMVC.developerMode;
    private int totalFullMarksPass;
    private int totalBorderLinePass;
    private int totalFullPaperPass;
    private double percFailSecOne;
    private double percFailSecTwo;
    private double percFailSecThree;
    private double percPass;
    private double percFail;
    
    /**
     * COnstructor for CSFModel().
     */
    public CSFModel() {
        if(dMode) System.out.println("Model: CSFModel() Constructor");
    }

    /**
     * Setting gradesArray
     * @param gradesArray
     */
    public void setGradesArray(DefaultListModel gradesArray){
        if(dMode) System.out.println("Model: setGradeArray()");
        this.gradesArray = gradesArray;
    }

    /**
     * Getting gradesArray
     * @return
     */
    public DefaultListModel getGradesArray(){
        if(dMode) System.out.println("Model: getGradeArray()");
        return gradesArray;
    }

    /**
     * set totalFullMarksPass
     * @param score
     */
    public void setTotalFullMarksPass(int score){
        if(dMode) System.out.println("Model: setTotalFullMarksPass(" + score +")");
        this.totalFullMarksPass = score;
    }

    /**
     * set totalBorderLinePass
     * @param score
     */
    public void setTotalBorderLinePass(int score){
        if(dMode) System.out.println("Model: setTotalBorderLinePass(" + score +")");
        this.totalBorderLinePass = score;        
    }

    /**
     * set totalFullPaperPass
     * @param score
     */
    public void setTotalFullPaperPass(int score){
        if(dMode) System.out.println("Model: setTotalFullPaperPass(" + score +")");
        this.totalFullPaperPass = score;   
    }

    /**
     * set percFailSec
     * @param score
     * @param section
     */
    public void setPercFailSec(double score, int section){
        if(dMode) System.out.println("Model: setPercFailSec(" + score + ", " + section + ")");
        if(!(score>=0)) score = 0;
        switch(section){
            case 1 -> this.percFailSecOne = score;
            case 2 -> this.percFailSecTwo = score;
            case 3 -> this.percFailSecThree = score;
        }  
    }

    /**
     * set percPass
     * @param score
     */
    public void setPercPass(double score){
        if(dMode) System.out.println("Model: setPercPass(" + score +")");
        if(!(score>=0)) score = 0;
        this.percPass = score;
    }

    /**
     * set percFail
     * @param score
     */
    public void setPercFail(double score){
        if(dMode) System.out.println("Model: setPercFail(" + score +")");
        if(!(score>=0)) score = 0;
        this.percFail = score;
    }

    /**
     * get totalFullMarksPass
     * @return
     */
    public int getTotalFullMarksPass(){
        if(dMode) System.out.println("Model: getTotalFullMarksPass()");
        return totalFullMarksPass;
    }

    /**
     * get totalBorderLinePass
     * @return
     */
    public int getTotalBorderLinePass(){
        if(dMode) System.out.println("Model: getTotalBorderLinePass()");
        return totalBorderLinePass;
    }

    /**
     * get totalFullPaperPass
     * @return
     */
    public int getTotalFullPaperPass(){
        if(dMode) System.out.println("Model: getTotalFullPaperPass()");
        return totalFullPaperPass;
    }

    /**
     * get percFailSec
     * @param section
     * @return
     */
    public double getPercFailSec(int section){
        if(dMode) System.out.println("Model: getPercFailSec()");
        return switch (section) {
            case 1 -> percFailSecOne;
            case 2 -> percFailSecTwo;
            case 3 -> percFailSecThree;
            default -> 0;
        };  
    }

    /**
     * get percPass
     * @return
     */
    public double getPercPass(){
        if(dMode) System.out.println("Model: getPercPass()");
        return percPass;
    }

    /**
     * get percFail
     * @return
     */
    public double getPercFail(){
        if(dMode) System.out.println("Model: getPercFail()");
        return percFail;
    }   
}
