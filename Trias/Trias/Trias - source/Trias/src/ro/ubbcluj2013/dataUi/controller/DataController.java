/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.ubbcluj2013.dataUi.controller;

import de.unikassel.cs.kde.trias.model.Triple;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import ro.ubbcluj2013.dataUi.util.CustomTable;

/**
 *
 * @author Fluffy
 */
public class DataController {
    
    /**
     * Dimension of the list of objects
     */
    private Integer objectNr;
    /**
     * Dimension of the list of attributes
     */
    private Integer attributeNr;
    /**
     * Dimension of the list of conditions
     */
    private Integer conditionNr;
    /**
     * The list of objects
     */
    private ArrayList<String> objectList;
    /**
     * The list of attributes
     */
    private ArrayList<String> attributeList;
    /**
     * The list of conditions
     */
    private ArrayList<String> conditionList;
    /**
     * Data format to populate the table
     */
    private ArrayList<ArrayList<Object>> data = new ArrayList<ArrayList<Object>>();
    /**
     * Triples of integers collected from table, each number represents the index of the
     * component from the associated list
     */
    private ArrayList<ArrayList<Triple<Integer>>> indexTriples = new ArrayList<ArrayList<Triple<Integer>>>();
    /**
     * Triples from the table, using the name of each component.
     * Generated using indexTriples and the objects, attributes and conditions lists.
     */
    protected ArrayList<Triple<String>> triples = new ArrayList<Triple<String>>();     
    
    
    /**
     * Returns the number of objects. 
     */
    public Integer getObjectNr(){
        return this.objectNr;
    }
    
    /**
     * Returns the number of attributes. 
     */
    public Integer  getAttrNr(){
        return this.attributeNr;
    }
    
    /**
     * Returns the number of conditions. 
     */
    public Integer  getCondNr(){
        return this.conditionNr;
    }
    
    
    /** 
     * Returns the attribute list as a String[] and adds the header "object\condition" for the first column
     * header, as it will mark the column containing the objects.
     */
    public String[] getAttrListString(){
        String[] stockArr = new String[attributeNr+1];
        attributeList.toArray(stockArr);
    /*    for(int i=0;i<stockArr.length;i++){
            System.out.println(stockArr[i]);
        }*/
        return stockArr;
    }
    
    /** 
     * Generates default data to populate the table.     
     */
    public ArrayList<ArrayList<Object>> generateDataForTable(){
        data = new ArrayList<ArrayList<Object>>();
        ArrayList<Object> row;
        for (int i=0;i<objectNr;i++){
            row = new ArrayList<Object>();
            data.add(row);
            data.get(i).add(objectList.get(i));
            for (int j=1;j<=attributeNr;j++){
                data.get(i).add(new Boolean(false));
            }
        }
        
        return data;
    }
        
    
    /** 
     * Transforms data obtained from generateDataForTable into Object[][] structure.    
     */
    public Object[][] getDataString(int cond){
        Object[][] d = new Object[objectNr][attributeNr+1];
        generateDataForTable();
        for (int i=0;i<objectNr;i++){
                for (int j=0;j<=attributeNr;j++){
                    d[i][j]= data.get(i).get(j);
                }
        }
            
        if (!indexTriples.get(cond).isEmpty()){
            for (int j=0;j<indexTriples.get(cond).size();j++){
                d[indexTriples.get(cond).get(j).getObject()][indexTriples.get(cond).get(j).getAttribute()] = true;
            }
            indexTriples.get(cond).clear();
        }
        return d;
    }
    
    
    
    
    
    /** 
     * collects Data introduced into InsertDialog     
     */
    public void collectLists(ArrayList<String> o, ArrayList<String> a, ArrayList<String> c){
        
        //initialize the local lists of components
        objectList = new ArrayList<String>();
        attributeList = new ArrayList<String>();
        conditionList = new ArrayList<String>();
        
        //add objects to the coresponding list
        for (int i=0;i<o.size();i++){
            objectList.add(o.get(i));
          //  System.out.println(objectList.get(i));
        }
        
        //add attributes to the coresponding list
        for (int j=0;j<a.size();j++){
            attributeList.add(a.get(j));
          //  System.out.println(attributeList.get(j));
        }
        
        //add conditions to the coresponding list
        for (int k=0;k<c.size();k++){
            conditionList.add(c.get(k));
          //  System.out.println(conditionList.get(k));
        }
        
        objectNr = objectList.size();
      //  System.out.println(objectNr);
        attributeNr = attributeList.size();
      //  System.out.println(attributeNr);
        conditionNr = conditionList.size();
      //  System.out.println(conditionNr);  
      attributeList.add(0, "object\\condition");  
        
    }
    
    
    /** 
     * Generates the table to display, containing the attributes, the objects, 
     * and empty boolean cells to fill.   
     */
    public JTable getTable(int col){        
                 
        String[] c = getAttrListString();      
        Object[][] d = getDataString(col);
  
        
        TableModel myData = new CustomTable(d,c); 
        JTable table = new JTable(myData);
        table.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        
        table.setShowGrid(true);
        table.doLayout();     
        
        return table;        
    }   
    
    /**
     * Generates a list of empty triplets for each condition
     */
    public void generateListOfTriples(){
        for (int i=0;i<conditionNr;i++){
            indexTriples.add(new ArrayList<Triple<Integer>>());
        }
    }
    
    /** 
     * Collects triples from table, by the indexes of the components, and the current condition.    
     */
    public void collectTableInfo(JTable table, int c){
        for (int i=0;i<objectNr;i++){
            for (int j=1;j<=attributeNr;j++){
                if((Boolean)table.getValueAt(i, j) == true){
                    Triple<Integer> t = new Triple<Integer>();
                    t.setObject(i);
                    t.setAttribute(j);
                    t.setCondition(c);
                    indexTriples.get(c).add(t);
                    
                }
            }
        }
        
        for (int k=0;k<indexTriples.get(c).size();k++){
            System.out.println(indexTriples.get(c).get(k).toString());
        }
        
    }
    
    
    /** 
     * Associates names to the existing values from collectTableInfo();    
     */
    public ArrayList<Triple<String>> generateTriples(){
       
        for (int i=0;i<indexTriples.size();i++){
            for (int j=0;j<indexTriples.get(i).size();j++){
                Triple<String> s = new Triple<String>();
                s.setObject(objectList.get(indexTriples.get(i).get(j).getObject()));
                s.setAttribute(attributeList.get(indexTriples.get(i).get(j).getAttribute()));
                s.setCondition(conditionList.get(indexTriples.get(i).get(j).getCondition()));
                triples.add(s);
            }
        }                      
        return triples;        
    }
    
  
    
    
}
