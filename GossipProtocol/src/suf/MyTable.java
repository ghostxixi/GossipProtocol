package suf;

import java.util.Vector;

import javax.swing.table.AbstractTableModel;

public class MyTable extends AbstractTableModel {  
    /** 
     *  
     */  
    private static final long serialVersionUID = 1L;  
    Vector<Vector<String>> rows;
    Vector<String> colum;
    
    public MyTable(Vector<Vector<String>> rows,Vector<String> colum){
    	this.rows = rows;
    	this.colum = colum;
    }

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return colum.size();
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return rows.size(); 
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return rows.get(rowIndex).get(columnIndex);
	}
	
    public boolean isCellEditable(int rowIndex, int columnIndex) {  
        return true;  
    } 
    
    public void setValueAt(Object value, int row, int col) {  
   	 	Vector<String> temp = rows.get(row);
   	 	temp.setElementAt((String)value,col);
   	 	rows.setElementAt(temp,row); 
        fireTableCellUpdated(row, col); // 该方法触发TableModelEvent 事件，tableChanged()会进行相应的处理  
   }  
 
   public void mySetValueAt(Object value, int row, int col) {  
  	 	Vector<String> temp = rows.get(row);
  	 	temp.setElementAt((String)value,col);
  	 	rows.setElementAt(temp,row);  
        //fireTableCellUpdated(row, col);  //此处不能有，否则会陷入对自身的调用，导致内存溢出。  
   } 
   
}  
