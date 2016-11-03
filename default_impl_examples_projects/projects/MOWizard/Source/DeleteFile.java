//$Id: DeleteFile.java,v 1.1.1.1 2006/12/08 11:57:39 gramkumar Exp $
package com.adventnet.nms.tools.mowizard;

import com.adventnet.nms.tools.utils.ZipTool;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.*;
import java.io.*;
import java.applet.*;
import java.util.*;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import com.adventnet.apiutils.BuilderResourceBundle;
import com.adventnet.nms.tools.utils.ToolsUtil;
public class DeleteFile extends JDialog implements ActionListener
{
	//<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	javax.swing.JPanel  Top = null;
	javax.swing.JPanel  OkCancelPanel = null;
	javax.swing.JButton  okButton = null;
	javax.swing.JButton  cancelButton = null;
	javax.swing.JScrollPane  tableScroll = null;
	javax.swing.JTable  Table = null;
	//<End_Variable_Declarations>
	 javax.swing.JPanel descPanel=null;		
	 javax.swing.JLabel descLabel=null;	
	 javax.swing.JPanel descAndButtonPanel=null;
	 Border blackline;
	 TitledBorder title1;
     DeleteTableModel dtm=null;  
     String filePathToDelete=null;
     com.adventnet.nms.tools.utils.ZipTool zipfile=null;	
	 boolean delete=false;
	 boolean makezip=false;
	private BuilderResourceBundle resourceBundle =ToolsUtil.getBundle();							

    public void setUpProperties()throws Exception
  { 

  //<Begin_setUpProperties>

          try
          {
            okButton.setBorder(new javax.swing.border.BevelBorder(0));
            okButton.setPreferredSize(new Dimension(59,25));
            okButton.setMaximumSize(new Dimension(59,25));
            okButton.setMinimumSize(new Dimension(59,25));
            okButton.setActionCommand("Ok");
            okButton.setLabel("");
            okButton.setText(resourceBundle.getString("OK"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+okButton,ex); 
          }

          try
          {
            cancelButton.setBorder(new javax.swing.border.BevelBorder(0));
            cancelButton.setPreferredSize(new Dimension(59,25));
            cancelButton.setMaximumSize(new Dimension(59,25));
            cancelButton.setMinimumSize(new Dimension(59,25));
            cancelButton.setActionCommand("Cancel");
            cancelButton.setLabel("");
            cancelButton.setText(resourceBundle.getString("Cancel"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+cancelButton,ex); 
          }
		cancelButton.setPreferredSize(new Dimension(cancelButton.getPreferredSize().width+8,cancelButton.getPreferredSize().height+0));

  //<End_setUpProperties>
  } 
  public void init()
  { 

  //<Begin_init>
        if (initialized == true) return; 
        this.setSize(getPreferredSize().width+620,getPreferredSize().height+414); 
          setTitle(resourceBundle.getString("File Deletion Dialog"));
        Container container = getContentPane();
        container.setLayout(new BorderLayout()); 
        try 
        { 
          initVariables(); 
          setUpGUI(container); 
          setUpProperties(); 
          setUpConnections(); 
        } 
        catch(Exception ex) 
        { 
          showStatus(resourceBundle.getString("Error in init method"),ex); 
        } 
        // let us set the initialzed variable to true so  
        // we dont initialize again even if init is called 
        initialized = true; 
      setUpMenus();
      setUpToolBar();
        

  //<End_init>
  } 
  public void setUpConnections()throws Exception
  { 

  //<Begin_setUpConnections>

  //<End_setUpConnections>
  } 
  public void initVariables()throws Exception
  { 

  //<Begin_initVariables>
        Top= new javax.swing.JPanel();
        OkCancelPanel= new javax.swing.JPanel();
        okButton= new javax.swing.JButton();
        cancelButton= new javax.swing.JButton();
        tableScroll= new javax.swing.JScrollPane();
        Table= new javax.swing.JTable();

  //<End_initVariables>
  		descLabel=new javax.swing.JLabel();
		descPanel=new javax.swing.JPanel();
		descAndButtonPanel=new javax.swing.JPanel();
		
    Object tableHeader[]={resourceBundle.getString("Select"),resourceBundle.getString("FileName")};
    dtm=new DeleteTableModel(tableHeader,0);
	cancelButton.addActionListener(this);
	okButton.addActionListener(this);
	okButton.setMnemonic(KeyEvent.VK_O);
	cancelButton.setMnemonic(KeyEvent.VK_C);
	Table.setModel(dtm);
//	String zipclassname=getFilePath().substring(getFilePath().lastIndexOf("/")+1,getFilePath().length());

	descLabel.setText(resourceBundle.getString("<HTML><BODY> <Font size=4 color=RED> The Selected files under the specified project are deleted and unselected files are maintained as .zip file and stored under the <WebNMS HOME>/projects directory</FONT></BODY></HTML>"));	
	/* okButton.addkeyListener(new KeyAdapter(){
		public void keyPressed(KeyEvent kevt)
		{
			
			if(kevt.getKeyCode()==VK_ESCAPE)
			{
				setVisible(false);
			}
		}
	});
	*/
	javax.swing.table.TableColumn column=null;
	for(int i=0;i<2;i++)
	{
		column=Table.getColumnModel().getColumn(i);
		if(i==0)
		{
			column.setPreferredWidth(20);
		}
	}
  }
  
  public void setUpToolBar()
  { 

  //<Begin_setUpToolBar>

  //<End_setUpToolBar>
  } 
  public void setUpGUI(Container container)throws Exception
  { 
	if(false)
	{
  //<Begin_setUpGUI_Container>
container.add(Top,BorderLayout.CENTER);
	Top.setLayout(new BorderLayout(5,5));
	Top.add(OkCancelPanel,BorderLayout.SOUTH);
	OkCancelPanel.setLayout(new FlowLayout(1,5,5));
	OkCancelPanel.add(okButton);
	OkCancelPanel.add(cancelButton);
	Top.add(tableScroll,BorderLayout.CENTER);
	tableScroll.getViewport().add(Table);
	
  //<End_setUpGUI_Container>
	}
	
	blackline=BorderFactory.createLineBorder(Color.black);
	title1=BorderFactory.createTitledBorder(blackline,resourceBundle.getString("Information"));
	descPanel.setBorder(title1);
	container.add(Top,BorderLayout.CENTER);
	Top.setLayout(new BorderLayout(5,5));
	Top.add(descAndButtonPanel,BorderLayout.SOUTH);
	OkCancelPanel.setLayout(new FlowLayout(1,5,5));
	OkCancelPanel.add(okButton);
	OkCancelPanel.add(cancelButton);
	descPanel.setLayout(new BorderLayout());
	descPanel.add(descLabel,BorderLayout.CENTER);
	descAndButtonPanel.setLayout(new BorderLayout());
	descAndButtonPanel.add(descPanel,BorderLayout.CENTER);
	descAndButtonPanel.add(OkCancelPanel,BorderLayout.SOUTH);
	Top.add(tableScroll,BorderLayout.CENTER);
	tableScroll.getViewport().add(Table);

 	  
  } 
  public void setUpMenus()
  { 

  //<Begin_setUpMenus>

  //<End_setUpMenus>
  } 
  public void stop()
  { 

  //<Begin_stop>

  //<End_stop>
  } 
  public void start()
  { 

  //<Begin_start>

  //<End_start>
  } 
  public String getParameter(String input)
  { 

  //<Begin_getParameter_String>
           String value = null;
           if ( applet != null)
           {    
                 value = applet.getParameter(input);
           }    
           else
           {    
                 value = (String)com.adventnet.apiutils.Utility.getParameter(input);
           }    
           if(value == null)
           {
            if (input.equals("HOST")) value = "localhost"; 
            if (input.equals("PORT")) value = "161"; 
            }
        return value;

  //<End_getParameter_String>
  } 
  
  public void showStatus(String message)
  {
     //<Begin_showStatus_String>
     System.out.println(resourceBundle.getString("Internal Error :")+ message);
     //<End_showStatus_String>
  }
  public void showStatus(String message,Exception ex)
  {
     //<Begin_showStatus_String_Exception>
     System.out.println(resourceBundle.getString("Internal Error :")+ message);
     ex.printStackTrace();
     //<End_showStatus_String_Exception>
  }

  public DeleteFile()
  {
    //<Begin_DeleteFile>
    pack();
    this.setTitle(resourceBundle.getString("DeleteFile"));
  
    //<End_DeleteFile>
  }

  public DeleteFile(java.applet.Applet applet)
  {
    //<Begin_DeleteFile_java.applet.Applet>
    this.applet = applet;
    pack();
    this.setTitle(resourceBundle.getString("DeleteFile"));
    this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
  
    //<End_DeleteFile_java.applet.Applet>
  }
  public DeleteFile(JFrame parent,String title)
  {
	  super(parent,title,true);
  }
  public void setVisible(boolean bl)
  {
        //<Begin_setVisible_boolean>
       	if(bl)
       	{
       	  init();
          start();
        }
        else
        {
          stop();
        }
        super.setVisible(bl);
  
        //<End_setVisible_boolean>
	String zipclassname=getFilePath().substring(getFilePath().lastIndexOf(File.separator)+1,getFilePath().length());

	descLabel.setText(resourceBundle.getString("<HTML><BODY> <Font size=4 color=RED> The Selected files in the check box are deleted from the projects and unselected files will be mades as .zip file and saved in the projects directory</FONT></BODY></HTML>"));	
  }
  public void setFilePath(String filename)
  {
	filePathToDelete=filename;  
  }
  public String getFilePath()
  {
	  return filePathToDelete;
  }
  public void populateTable(String filename)
  {
	File[] filelist=new File(filename).listFiles();
	if(filelist!=null)
	{
		for(int i=0;i<filelist.length;i++)
		{
			Object[] obj={new Boolean(true),filelist[i].toString()};
			if(filelist[i].isDirectory())
			{
				populateTable(filelist[i].toString());
			}
			else
			{
       		((javax.swing.table.DefaultTableModel)Table.getModel()).addRow(obj);
			}
		}
  	}
  }
  public void MakeZipandDelete()
  {
				

	  
	  try{
		String zipname=getFilePath().substring(getFilePath().lastIndexOf(File.separator)+1,getFilePath().length());
  
	 	  for(int i=0;i<Table.getRowCount();i++)
	  {
		File f=new File(((javax.swing.table.DefaultTableModel)Table.getModel()).getValueAt(i,1).toString());
		//f.delete();
		if(!(new Boolean(((javax.swing.table.DefaultTableModel)Table.getModel()).getValueAt(i,0).toString()).booleanValue()))
		{
			if(!makezip)
			{
			 zipfile=new com.adventnet.nms.tools.utils.ZipTool(System.getProperty("user.dir")+File.separator+"projects"+File.separator+zipname+".zip","zip"); 
			}
			if(f.isFile()){
			makezip=true;
			zipfile.addFile(((javax.swing.table.DefaultTableModel)Table.getModel()).getValueAt(i,1).toString());	
		    }
		}
	  }
	  if(makezip){
	  zipfile.stopZipProcess();
	  }
	  }
	  catch(Exception e)
	  {
		 e.printStackTrace(); 	
	  }
  }
 public class DeleteTableModel extends javax.swing.table.DefaultTableModel
 {
    DeleteTableModel()
	{}
    DeleteTableModel(Object[] tableHdr,int rowNum)
	{
		super(tableHdr,rowNum);
	}
	
	public Class getColumnClass(int col)
	{
	  if (col==0){
		return new Boolean(true).getClass(); 	
	  }
	  else{
		return new Object().getClass();  	
	  }
	}
 }

	public boolean isDelete() {
		return delete;
	}
 
 public void actionPerformed(ActionEvent aevt)
 {
	 if(aevt.getSource()==okButton)
	 {
		if(Table.getRowCount()!=0)
		{
			StringBuffer message=new StringBuffer();
			message.append(resourceBundle.getString("The project is successfully deleted"));
			MakeZipandDelete();
			String classname=getFilePath().substring(getFilePath().lastIndexOf(File.separator)+1,getFilePath().length());
			if(makezip)
			{
				message.append(" and "+classname+".zip file has been saved under <WebNMSHome>/projects Directory");
			}
			JOptionPane.showMessageDialog(null,message,resourceBundle.getString("Message"),JOptionPane.ERROR_MESSAGE);  

		}
		delete=true;
		File DeleteProj=new File(getFilePath());
		DeleteProj.delete();
		setVisible(false); 
	 }
	 else if(aevt.getSource()==cancelButton)
	 {
		 delete=false;
		 setVisible(false); 
	 }
 } 
}
