/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package csfmarksanalysismvc;

/**
 *
 * @author tg
 */
public class CSFMarksAnalysisMVC {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Assemble all the pieces of the MVC
        CSFModel m = new CSFModel();
        CSFView v = new CSFView();
        CSFController c = new CSFController(m, v);
        c.initController();
    }
    
}
