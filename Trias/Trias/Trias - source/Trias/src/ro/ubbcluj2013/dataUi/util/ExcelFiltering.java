/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.ubbcluj2013.dataUi.util;

import java.io.File;

/**
 *
 * @author Fluffy
 */
// class implements a FileFilter for Excel files (.xls)
public class ExcelFiltering extends javax.swing.filechooser.FileFilter {
    @Override
      public boolean accept(File f) {
        //accepted files are .xls files, and directories
        return f.isFile() && (f.getName().toLowerCase().endsWith(".xls")||f.isFile() && f.getName().toLowerCase().endsWith(".xlsx"))||f.isDirectory();
      }

    @Override
      public String getDescription() {
        return "*.xls, *.xlsx";
      }
    
    
}
