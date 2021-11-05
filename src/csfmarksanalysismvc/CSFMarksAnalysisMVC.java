/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
//@TODO: 
//  -   public static boolean noInterfaceMode = false;
//  -   command line interface
//  -   javadoc
//  -   test
package csfmarksanalysismvc;

/**
 *
 * @author Tommy Grabowski
 */
public class CSFMarksAnalysisMVC {

    /**
     *Enabling detailed logging to console.
     */
    public static boolean developerMode = false;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Assemble all the pieces of the MVC
        if(developerMode) System.out.println("Assembling App modules...");
        CSFModel m = new CSFModel();
        CSFView v = new CSFView();
        CSFController c = new CSFController(m, v);
        c.initController();
    }
    
}
