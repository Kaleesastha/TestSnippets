//$Id: AddObjProp.java,v 1.1.1.1 2006/12/08 11:57:39 gramkumar Exp $
package com.adventnet.nms.tools.mowizard;
 
import com.adventnet.nms.tools.objtorel.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.BorderLayout;
import java.awt.*;
import java.beans.*;
import java.util.*;
import javax.swing.table.AbstractTableModel;
import java.io.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.MalformedURLException;
import javax.swing.DefaultListModel;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.*;
import com.adventnet.apiutils.BuilderResourceBundle;
import com.adventnet.nms.tools.utils.ToolsUtil;
public class AddObjProp extends AbstractTransversePanel implements ActionListener,TransverseListener {
    
    BeanInfo bi=null;
    JLabel OBJLabel=null;
    JTextField OBJPath=null;
    JButton OBJBrowse=null;	
    JPanel tricon=null;
    JTable table=null; 
    JScrollPane tablescroller=null;
    JCheckBox   BooleanCheck=null;
    GridBagLayout  gbl=null;
    GridBagConstraints gc=null;
    JTextArea  instruction=null;
    JButton butAnalyse=null;
	TransverseContainer TransCon=null;
    
    boolean isManagedObject=false;
    
    //Hashtable raw_table_data=null;
    Object[][] selected_table_data=null;
    Hashtable datatypes=null;

    ClassAnalyser cnr=null;
    JLabel classpath = null;

    String ps = System.getProperty("path.separator");
	private BuilderResourceBundle resourceBundle=ToolsUtil.getBundle();
    public AddObjProp() {
        
        tricon = new JPanel();
        tricon.setLayout(new BorderLayout());
        Object [] SelectionTblHdr={resourceBundle.getString("Serial Number"),resourceBundle.getString("Property Name"),resourceBundle.getString("Datatype"),resourceBundle.getString("SetMethod"),resourceBundle.getString("GetMethod")};
        OBJLabel = new JLabel(resourceBundle.getString("Object Name :"));
        OBJPath  = new JTextField(30);        
        OBJBrowse = new JButton(resourceBundle.getString("Select"));
        BooleanCheck = new JCheckBox("",true);
        butAnalyse=new JButton(resourceBundle.getString("Properties"));
        
        gbl = new GridBagLayout();
        gc=new GridBagConstraints();
        setLayout(new BorderLayout());
        
        table = new JTable();
        table.setModel(new DescriptorTableModel(SelectionTblHdr,0));
        tablescroller = new JScrollPane(table);
        instruction=new JTextArea(resourceBundle.getString("Please select any ManagedObject or its subclasses for extending its behavior"));
        instruction.setWrapStyleWord(true);
        instruction.setLineWrap(true);
        instruction.setEditable(false);
        instruction.setBackground(getBackground());
        instruction.setFont(new Font("SansSerif",Font.BOLD,16));
        
        OBJBrowse.addActionListener(this);
        OBJPath.addActionListener(this);
        butAnalyse.addActionListener(this);

        tricon.setLayout(gbl);
        setConsAddComp(0,0,1,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(10,10,10,10),0,0,OBJLabel,gbl,gc,tricon);
        
        setConsAddComp(1,0,GridBagConstraints.RELATIVE,1,0.1,0.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(10,0,10,10),0,0,OBJPath,gbl,gc,tricon);
        
        setConsAddComp(2,0,GridBagConstraints.REMAINDER,1,0.0,0.0,GridBagConstraints.EAST,GridBagConstraints.BOTH,new Insets(10,0,10,10),0,0,OBJBrowse,gbl,gc,tricon);
        
        setConsAddComp(2,1,1,1,0.0,0.0,GridBagConstraints.EAST,GridBagConstraints.BOTH,new Insets(0,0,20,10),0,0,butAnalyse,gbl,gc,tricon);
        
        setConsAddComp(0,3,GridBagConstraints.REMAINDER,1,0.1,0.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(0,10,5,10),0,0,instruction,gbl,gc,tricon);
        
        tricon.setBorder(new TitledBorder(LineBorder.createBlackLineBorder(),resourceBundle.getString("Analyse Object")));
        
        add(tricon,BorderLayout.NORTH);
        add(tablescroller,BorderLayout.CENTER);
        //raw_table_data = new Hashtable(10);
        validate();
        OBJBrowse.setPreferredSize(butAnalyse.getPreferredSize());
        butAnalyse.setMnemonic(KeyEvent.VK_A);
        OBJBrowse.setMnemonic(KeyEvent.VK_B);
		//setSize(400,400);
		//setVisible(true);
    }

    void setConsAddComp(int gridx,int gridy,int gridwidth,int gridheight,double weightx,double weighty,int anchor,int fill,Insets insets,int ipadx,int ipady,JComponent comp,GridBagLayout gridbaglayout,GridBagConstraints gridbagconstraints,Container contain) {
        
        // Using this function we can use GridBagLayout and GridBagConstraints

        gridbagconstraints.gridx=gridx;
        gridbagconstraints.gridy=gridy;
        gridbagconstraints.gridwidth=gridwidth;
        gridbagconstraints.gridheight=gridheight;
        gridbagconstraints.weightx=weightx;
        gridbagconstraints.weighty=weighty;
        gridbagconstraints.anchor=anchor;
        gridbagconstraints.fill=fill;
        gridbagconstraints.insets=insets;
        gridbagconstraints.ipadx=ipadx;
        gridbagconstraints.ipady=ipady;
        gridbaglayout.setConstraints(comp,gridbagconstraints);
        contain.add(comp);
    }
	
	private Window getParentContainer() {
		Container con=getParent();
		for(;!(con instanceof Window);con=con.getParent());
		return (Window)con;
	}
	
	
    public void cancelActionPerformed(String arg) {
        if(arg.trim().equals("Screen3")) {
			int returnVal=JOptionPane.showConfirmDialog(null,resourceBundle.getString("MO Generation is not complete. Do you want to cancel ???"),resourceBundle.getString("Confirm"),JOptionPane.YES_NO_OPTION);
			if(returnVal==JOptionPane.YES_OPTION) {
				getParentContainer().dispose();
			}
        }    
    }    
    
    public boolean finishActionPerformed() {
        return true;
    }    
    
    public void closeActionPerformed() {
        
    }    

    public void loadScreenData() {
        // This method will be called by TransverePanel to load screen
        // data.
        Document doc=(Document)TransCon.getTransverseComponent("XMLMODEL");
        NodeList nodeList=doc.getElementsByTagName("CLASS");
        Element node=(Element)nodeList.item(0);
        if(node.getAttribute("superClassPath")==null) {
            return;
        }
        OBJPath.setText(node.getAttribute("superClassPath"));
    }
    
    public int nextActionPerformed(String arg) {
        if(OBJPath.getText().length()==0) {
            JOptionPane.showMessageDialog(null,resourceBundle.getString("Please select a class"),resourceBundle.getString("Error"),JOptionPane.ERROR_MESSAGE);
            return -1;
        }
        Document doc=(Document)TransCon.getTransverseComponent("XMLMODEL");
        NodeList nodelist=doc.getElementsByTagName("CLASS");
        Element node=(Element)nodelist.item(0);
		if(cnr==null){
			String classpath=readFromFile();
			try{
				URL[] urlarray=parseFile(classpath);
				cnr=new ClassAnalyser();
				String classname=cnr.getFullClassName(OBJPath.getText().trim(),urlarray);
			}
			catch(ClassNotFoundException cnfe){
				cnfe.printStackTrace();
			}
			if(cnr.getAnalysedClass()==null) {
				return 1;
			}
		}
        node.setAttribute("extends",cnr.getAnalysedClass().getName());
        node.setAttribute("superClassPath",OBJPath.getText().trim());
        return 1;
    }
    
    private int findIndexOfMandatoryRows(int mandatoryRows[],int i) {
        for(int a=0;a<mandatoryRows.length;a++) {
            if(mandatoryRows[a]==i) {
                return a;
            }
        }
        return -1;
    }
    
    /*public Object[][] getRawTableData() {
        Object rtd[][] = null;
        
        rtd = new Object[raw_table_data.size()][5];
        for(int i=0;i<raw_table_data.size();i++) {
            rtd[i]=(Object [])raw_table_data.get(Integer.toString(i));
        }
        return rtd;
    } */   

    public Object[][] getSelectedRows() {
        return(selected_table_data);
    }    
    
    public int previousActionPerformed(String arg) {
        return 0;
    }
	
	public void addTransverseContainer(TransverseContainer tcon) throws NullPointerException{
		if(tcon==null){
			throw new NullPointerException(resourceBundle.getString("Trying to add a null Container"));
		}
		TransCon=tcon;
		return;
	}	
    
    void butAnalyse_Clicked(ActionEvent ae) {
        ( (DescriptorTableModel)table.getModel()).setNonEditableRows(null);
        //Construct a JTable with getter and setter methods.  
        for(int i=table.getModel().getRowCount();i>0;i--) {
            ((DefaultTableModel)table.getModel()).removeRow(i-1);
        }

        if(OBJPath.getText().trim().length() !=0) {
            // The following initialization is done because if the user selects an object which is a 
			// a subclass of ManagedObject and then he again chooses another class which is not a
            // a subclass of ManagedObject this variable should be initialzed so that it reflects the
            // status of the current object.
            String className=null;
            try {
				String classpath=readFromFile();
				URL[] urlarray=parseFile(classpath);
				cnr=new ClassAnalyser();
				className=cnr.getFullClassName(OBJPath.getText().trim(),urlarray);
            }
            catch(ClassNotFoundException cnfe) 
			{
				StringTokenizer stk=new StringTokenizer(cnfe.getMessage(),"=");
				if(stk.countTokens()==1) {
					JOptionPane.showMessageDialog(null,cnfe.getMessage(),resourceBundle.getString("Error Loading class"),JOptionPane.OK_OPTION);
					MOChooser mochooser=new MOChooser();
					mochooser.init();
					mochooser.setModal(true);
					mochooser.setVisible(true);
					OBJPath.setText(mochooser.getClassName());
					return;
				}
			}
			populateTable();
        }
    }    
    
    public void populateTable() {
        String superClassNames[]=null;
        String className=null;
		try {
			superClassNames=cnr.getSuperClassNames(className);
		}catch(ClassNotFoundException cnfe) {
			JOptionPane.showMessageDialog(null,resourceBundle.getString("The selected class is not found"),resourceBundle.getString("Error"),JOptionPane.OK_OPTION);
			return;
		}     
        if(superClassNames==null) {
            return;
        }
        for(int i=0;i<superClassNames.length;i++) {
            if(superClassNames[i].trim().equals("com.adventnet.nms.topodb.ManagedObject")) {
                isManagedObject=true;
                break;
            }    
        }
        addDataTables(className);
    }
    
    public void actionPerformed(ActionEvent ae) {
        String className=null;
        if(ae.getSource().equals(butAnalyse)) {
            butAnalyse_Clicked(ae);
        }    
        else if(ae.getSource().equals(OBJBrowse)) {
            OBJBrowse_Clicked(ae);
        }
    }	

    private void OBJBrowse_Clicked(ActionEvent ae) {
				MOChooser mochooser=new MOChooser();
				String textname=OBJPath.getText().trim();
				mochooser.init();
				mochooser.setModal(true);
				mochooser.setVisible(true);
				String textPath=mochooser.getClassName();
				if(textPath==null){
						OBJPath.setText(textname);
				}
				else{
						OBJPath.setText(textPath);
						butAnalyse_Clicked(ae);
				}
    }		

    Object[] createTableRow(String PropertyName,int rowNumber,boolean isBase,Class analyze) {
        
	// This method returns null in two cases.
        // Case :1
        //       When a PropertyName does not have a getter and setter method an exception occurs
        //       and a null is returned.
        // Case :2
        //       When the storage type is DERIVED and the property belongs to the base class of the 
        //       we just pass null since it need not be stored in Database.
        
        Object data[]=null;
        PropertyDescriptor pd=null;
        try {
            pd=new PropertyDescriptor(PropertyName,analyze);
            if(isBase) {
                data =new Object[5];
                data[0]=new String(Integer.toString(rowNumber+1));
                data[1]=new String(PropertyName);
				data[2]=new String((pd.getPropertyType().toString().indexOf("String")==-1)? pd.getPropertyType().toString():"String");
                data[3]=new String(pd.getWriteMethod().getName());
                data[4]=new String(pd.getReadMethod().getName());
            }
            return data;
        }catch(IntrospectionException ie) {
            return null;
        }    
                                              
    }    

    
    void createSelectionTable(String[] PropertyNames, String ClassName) 			{
        DescriptorTableModel dtm;
        Object data[]=null;
        PropertyDescriptor pd=null;
        data = new Object[6];
        Class analyze=null;
        Class managedObject=null;
        
        
        try {
            //analyze=Class.forName(ClassName);
            analyze=cnr.getAnalysedClass();
        }
        /*catch(ClassNotFoundException cnfe) {
          System.out.println("Please update CLASSPATH variable to include : "+ClassName);
          return;
          }*/
        catch(NullPointerException cnfe) {
            System.out.println(resourceBundle.getString("Please update CLASSPATH variable to include : ")+ClassName);
            return;
        }
        
            if(isManagedObject) { // ManagedObject not using BeanInfo
                boolean isNamePresent=false;
                int j=0;
                for(int i=0;i< PropertyNames.length;i++) {
                    if( (data=createTableRow(PropertyNames[i],j,true,analyze)) != null) {

                        if(PropertyNames[i].trim().equals("name")) {
                            isNamePresent=true;
                            int nonEditableRowsCols[][]=new int[2][1];
                            nonEditableRowsCols[0][0]=j;
                            nonEditableRowsCols[1][0]=5;
                            ((DescriptorTableModel)table.getModel()).setNonEditableRows(nonEditableRowsCols);
                        }
                        ((javax.swing.table.DefaultTableModel)table.getModel()).addRow(data);                
                        //raw_table_data.put(Integer.toString(j),data);
                        j+=1;
                    }
                }
           }
            else { // Not a managedObject and not use beanInfo
                for(int i=0,j=0;i< PropertyNames.length;i++) {
                    if( (data=createTableRow(PropertyNames[i],j,false,analyze)) != null) {
                        ((javax.swing.table.DefaultTableModel)table.getModel()).addRow(data);
                        //raw_table_data.put(Integer.toString(j),data);
                        j+=1;
                    }
                }
            }// end of Non ManagedObject    
         // End of Ignore BeanInfo*/
    }
    
    String getClassDetails(String className) throws ClassNotFoundException {
        return cnr.getFullClassName(className);
    }

    public void addDataTables(String className) {
        String [] Props;
        // Methods = getMethodDescriptors(bi);
        // Properties = getPropertyDescriptors(bi);
        // Instantiate Class Analyser;
        // Analyse an Object and get its properties

        // Analyse the class to the full extent and do not use bean info;
        try {
            //cnr.analyseClass(Class.forName(className),null,rdbUseBean.isSelected());
            cnr.analyseClass(cnr.getAnalysedClass(),null,false);
        }
        catch(NullPointerException cnfe) {

        }
        catch(ClassNotFoundException cnfe) {

        }
        Props=cnr.getProperties();
        createSelectionTable(Props,className);
    }

    Hashtable getFieldDataTypes() {
        return datatypes;
    }

    // The following is an innerClass which is added to load a 
    // class that is not in the Class Path.

	public String readFromFile()
	{
		File f=new File(System.getProperty("user.dir")+File.separator+"projects"+File.separator+"MOObjects.xml");
		String classpath=null;
		try{
			DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
			DocumentBuilder db=dbf.newDocumentBuilder();
			Document doc=db.parse(f);
			NodeList list=doc.getDocumentElement().getElementsByTagName("CLASS");
			for(int i=0;i<list.getLength();i++)
			{
				Element child=(Element)list.item(i);
				if(child.getAttribute("name").equals(OBJPath.getText().trim())){
					classpath=child.getAttribute("classpath");
					break;
				}
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return classpath;
	}

	public URL[] parseFile(String classpath)  
	{
		URL[] urlarray=null;
		String token=null;
		int i=0;
		StringTokenizer stk=new StringTokenizer(classpath,ps);
		try{
			urlarray=new URL[stk.countTokens()];
			while(stk.hasMoreTokens()){
				if((token=stk.nextToken()).startsWith(".")){
					String temptoken=token.substring(token.indexOf('.')+1);
					token=System.getProperty("user.dir")+temptoken;
				}
				urlarray[i++]=new File(token).toURL();
			}
		}
		catch(MalformedURLException mue){
			mue.printStackTrace();
		}
		return urlarray;
	}
}	

class DescriptorTableModel extends DefaultTableModel {
    //  This nonEditableCells is an array which is a two dimensional array with
    //  rows and columns.  nonEditableCells[0] - is set of rows and it corresponding
    //  columns are nonEditableCells[1].
    int nonEditableCells[][]=null;

    DescriptorTableModel(Object[] HeaderInfo, int num) {
        super(HeaderInfo,num);
        nonEditableCells=null;
    }

    public void setNonEditableRows(int[][] nec) {
        nonEditableCells=nec;
    }    
    
    int[] getNonEditableRows() {
        if(nonEditableCells!=null) {
            int nedr[]=new int[nonEditableCells[1].length];
            for(int i=0;i<nedr.length;i++) {
                nedr[i]=nonEditableCells[0][i];
            }
            return nedr;
        }
        return null;
    }	
    
    public boolean isCellEditable(int row,int col) {
        if(nonEditableCells!=null) {
            for(int i=0;i<nonEditableCells[0].length;i++) {
                if(nonEditableCells[0][i]==row && nonEditableCells[1][i]==col) {
                    return false;
                }
            }
        }    
        return false;
    }
}	
