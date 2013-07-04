/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.ubbcluj2013.dataUi.controller;

import javax.swing.DefaultListModel;

/**
 *
 * @author Fluffy
 */
public class InsertDialogController {
    
    DefaultListModel olm = new DefaultListModel();
    DefaultListModel alm = new DefaultListModel();
    DefaultListModel clm = new DefaultListModel();
    
    //add data to the specified list model
    public void addToListModel(String element, DefaultListModel dlm){
        dlm.addElement(element);
    }
    
    public void deleteFromListModel(int i, DefaultListModel dlm){
        dlm.remove(i);
    }
    
    public void clearListModel(DefaultListModel dlm){
        dlm.clear();
    }
    
           
}
