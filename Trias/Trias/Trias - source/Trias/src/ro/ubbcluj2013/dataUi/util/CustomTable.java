/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.ubbcluj2013.dataUi.util;

import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Fluffy
 */

public class CustomTable extends AbstractTableModel {
    
        private boolean DEBUG = false;
        
        private String[] columnNames;
        private Object[][] data;
        private int columnCount;
        private int rowCount;
        
        public CustomTable( Object[][] d, String[] c){
            columnNames = c;
            data = d;
            columnCount = c.length;
            rowCount = d.length;
            System.out.print(d.length);
            
   /*         for(int i=0;i<columnCount;i++){
                System.out.println(columnNames[i]);
            }
            
            for(int i=0;i<rowCount;i++){
                for (int j=0;j<columnCount;j++){
                    System.out.println(data[i][j]);
                }
            } */
            fireTableStructureChanged();
            
        }     
    
        
    

    @Override
        public int getColumnCount() {
            return columnCount;
        }

    @Override
        public int getRowCount() {
            return rowCount;
        }

    @Override
        public String getColumnName(int col) {
           // return columnNames.get(col);
            return columnNames[col];
        }

    @Override
        public Object getValueAt(int row, int col) {
            return data[row][col];
           // return data.get(row).get(col);
        }

        /*
         * method to determine the default renderer/editor for each cell
         */
    @Override
        public Class getColumnClass(int c) {
            return getValueAt(0, c).getClass();
        }


    @Override
        public boolean isCellEditable(int row, int col) {
            //Note that the data/cell address is constant,
            //no matter where the cell appears onscreen.
            if (col < 1) {
                return false;
            } else {
                return true;
            }
        }

       
    @Override
        public void setValueAt(Object value, int row, int col) {
            if (DEBUG) {
                System.out.println("Setting value at " + row + "," + col
                                   + " to " + value
                                   + " (an instance of "
                                   + value.getClass() + ")");
            }
           // data.get(row).set(col, value);
            data[row][col]= value;
            fireTableCellUpdated(row, col);

            if (DEBUG) {
                System.out.println("New value of data:");
                printDebugData();
            }
        }

        private void printDebugData() {
            int numRows = getRowCount();
            int numCols = getColumnCount();

            for (int i=0; i < numRows; i++) {
                System.out.print("    row " + i + ":");
                for (int j=0; j < numCols; j++) {
                   // System.out.print("  " + data.get(i).get(j));
                    System.out.print("  " + data[i][j]);
                }
                System.out.println();
            }
            System.out.println("--------------------------");
        }
        
}
    

