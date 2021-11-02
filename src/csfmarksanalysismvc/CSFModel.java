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
    
    private int totalFullMarksPass;
    private int totalBorderLinePass;
    private int totalFullPaperPass;
    private double percFailSecOne;
    private double percFailSecTwo;
    private double percFailSecThree;
    private double percPass;
    private double percFail;
    
    public CSFModel() {}
    public void setGradesArray(DefaultListModel gradesArray){
        this.gradesArray = gradesArray;
    }
    public DefaultListModel getGradesArray(){
        return gradesArray;
    }
    public void setTotalFullMarksPass(int score){
        this.totalFullMarksPass = score;
    }
    public void setTotalBorderLinePass(int score){
        this.totalBorderLinePass = score;        
    }
    public void setTotalFullPaperPass(int score){
        this.totalFullPaperPass = score;   
    }
    public void setPercFailSec(double score, int section){
        if(!(score>=0)) score = 0;
        switch(section){
            case 1 -> this.percFailSecOne = score;
            case 2 -> this.percFailSecTwo = score;
            case 3 -> this.percFailSecThree = score;
        }  
    }
    public void setPercPass(double score){
        if(!(score>=0)) score = 0;
        this.percPass = score;
    }
    public void setPercFail(double score){
        if(!(score>=0)) score = 0;
        this.percFail = score;
    }
    public int getTotalFullMarksPass(){
        return totalFullMarksPass;
    }
    public int getTotalBorderLinePass(){
        return totalBorderLinePass;
    }
    public int getTotalFullPaperPass(){
        return totalFullPaperPass;
    }
    public double getPercFailSec(int section){
        return switch (section) {
            case 1 -> percFailSecOne;
            case 2 -> percFailSecTwo;
            case 3 -> percFailSecThree;
            default -> 0;
        };  
    }
    public double getPercPass(){
        return percPass;
    }
    public double getPercFail(){
        return percFail;
    }   
}
