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
    
    private boolean dMode = CSFMarksAnalysisMVC.developerMode;
    private int totalFullMarksPass;
    private int totalBorderLinePass;
    private int totalFullPaperPass;
    private double percFailSecOne;
    private double percFailSecTwo;
    private double percFailSecThree;
    private double percPass;
    private double percFail;
    
    public CSFModel() {
        if(dMode) System.out.println("Model: CSFModel() Constructor");
    }
    public void setGradesArray(DefaultListModel gradesArray){
        if(dMode) System.out.println("Model: setGradeArray()");
        this.gradesArray = gradesArray;
    }
    public DefaultListModel getGradesArray(){
        if(dMode) System.out.println("Model: getGradeArray()");
        return gradesArray;
    }
    public void setTotalFullMarksPass(int score){
        if(dMode) System.out.println("Model: setTotalFullMarksPass(" + score +")");
        this.totalFullMarksPass = score;
    }
    public void setTotalBorderLinePass(int score){
        if(dMode) System.out.println("Model: setTotalBorderLinePass(" + score +")");
        this.totalBorderLinePass = score;        
    }
    public void setTotalFullPaperPass(int score){
        if(dMode) System.out.println("Model: setTotalFullPaperPass(" + score +")");
        this.totalFullPaperPass = score;   
    }
    public void setPercFailSec(double score, int section){
        if(dMode) System.out.println("Model: setPercFailSec(" + score + ", " + section + ")");
        if(!(score>=0)) score = 0;
        switch(section){
            case 1 -> this.percFailSecOne = score;
            case 2 -> this.percFailSecTwo = score;
            case 3 -> this.percFailSecThree = score;
        }  
    }
    public void setPercPass(double score){
        if(dMode) System.out.println("Model: setPercPass(" + score +")");
        if(!(score>=0)) score = 0;
        this.percPass = score;
    }
    public void setPercFail(double score){
        if(dMode) System.out.println("Model: setPercFail(" + score +")");
        if(!(score>=0)) score = 0;
        this.percFail = score;
    }
    public int getTotalFullMarksPass(){
        if(dMode) System.out.println("Model: getTotalFullMarksPass()");
        return totalFullMarksPass;
    }
    public int getTotalBorderLinePass(){
        if(dMode) System.out.println("Model: getTotalBorderLinePass()");
        return totalBorderLinePass;
    }
    public int getTotalFullPaperPass(){
        if(dMode) System.out.println("Model: getTotalFullPaperPass()");
        return totalFullPaperPass;
    }
    public double getPercFailSec(int section){
        if(dMode) System.out.println("Model: getPercFailSec()");
        return switch (section) {
            case 1 -> percFailSecOne;
            case 2 -> percFailSecTwo;
            case 3 -> percFailSecThree;
            default -> 0;
        };  
    }
    public double getPercPass(){
        if(dMode) System.out.println("Model: getPercPass()");
        return percPass;
    }
    public double getPercFail(){
        if(dMode) System.out.println("Model: getPercFail()");
        return percFail;
    }   
}
