
//$Id: AuditScreen.java,v 1.1 2006/08/29 13:57:02 build Exp $
// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// For more details please see EditingSourceFiles.html present in the docs directory

package com.adventnet.security.ui;




import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Properties;
import java.util.Vector;
import java.util.StringTokenizer;
import com.adventnet.nms.util.NmsClientUtil;
import java.util.Calendar;
import java.util.TimeZone;
public class AuditScreen extends JDialog 
{
	//<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	private String localePropertiesFileName = "SecurityAdministrationResources";
	static com.adventnet.apiutils.BuilderResourceBundle resourceBundle = null;
	private boolean running=false;
	javax.swing.JPanel Top = null;
	javax.swing.JScrollPane JScrollPane1 = null;
	com.adventnet.beans.table.SortTable SortTable1 = null;
	javax.swing.JButton JButton1 = null;
	javax.swing.JButton JButton2 = null;
	javax.swing.JButton JButton3 = null;
	com.adventnet.beans.table.SortTableModel SortTableModel1 = null;
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
	//<End_Variable_Declarations>



  

  


  


 

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
	//setIconImage(AuthMain.getBuilderUiIfInstance().getFrameIcon());

	//Setting the dummy data.
	/*
	Vector v = new Vector();
	Properties prop1 = new Properties();
	prop1.put("username","dwarka");
	prop1.put("audittime","90.90");
	prop1.put("operation","alert");
	prop1.put("status","Failed");
	for(int j=0;j<12;j++)
	{
		v.addElement(prop1);
	}
	*/
	//setAuditData(v);
 }

    public void stop()
  {
       //<Begin_stop> 
       if(!running)
 return;
 running=false;

  
       //<End_stop>
  } 
  public void start()
  {
       //<Begin_start> 
       if(running)
 return;
 running=true;

  
       //<End_start>
  } 
  public void init()
  {
         //<Begin_init> 
	if(getParameter("RESOURCE_PROPERTIES" ) != null)
	{
		localePropertiesFileName = getParameter("RESOURCE_PROPERTIES");
	}
	resourceBundle = com.adventnet.apiutils.Utility.getBundle(localePropertiesFileName,getParameter("RESOURCE_LOCALE"),applet);
        if (initialized) return; 
        this.setSize(getPreferredSize().width+597,getPreferredSize().height+422); 
          setTitle(resourceBundle.getString("AuditScreen"));
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
        // let us set the initialized variable to true so  
        // we dont initialize again even if init is called 
        initialized = true; 

  
         //<End_init>
	setTitle(resourceBundle.getString("AuditDetails"));
	AuthMain.getBuilderUiIfInstance().centerWindow(this);
		
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
            if (input.equals("MS_MODE")) value = "3"; 
            if (input.equals("RESOURCE_PROPERTIES")) value = "SecurityAdministrationResources"; 
            }
        return value;

  
           //<End_getParameter_String>
  } 
  public void setUpProperties()
  {
  //<Begin_setUpProperties> 

          try
          {
            JScrollPane1.setForeground(new Color(-13224394));
            JScrollPane1.setBorder(new javax.swing.border.TitledBorder(new javax.swing.border.LineBorder(new Color(-16777216),1),resourceBundle.getString("Audit Details"),0,0,new Font("Dialog",0,12),new Color(-10066279)));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JScrollPane1,ex); 
          }

//<UserCode_Begin_Bean_JScrollPane1>

//<UserCode_End_Bean_JScrollPane1>

          try
          {
            SortTable1.setModel(SortTableModel1);
            SortTable1.setForeground(new Color(-16777216));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+SortTable1,ex); 
          }

//<UserCode_Begin_Bean_SortTable1>

//<UserCode_End_Bean_SortTable1>

          try
          {
            JButton1.setFont(new Font("Dialog",0,12));
            JButton1.setText(resourceBundle.getString("Save To File"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JButton1,ex); 
          }

//<UserCode_Begin_Bean_JButton1>
JButton1.setMnemonic('S');
//<UserCode_End_Bean_JButton1>

          try
          {
            JButton2.setFont(new Font("Dialog",0,12));
            JButton2.setText(resourceBundle.getString("   Clear    "));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JButton2,ex); 
          }

//<UserCode_Begin_Bean_JButton2>
JButton2.setMnemonic('l');
//<UserCode_End_Bean_JButton2>

          try
          {
            JButton3.setFont(new Font("Dialog",0,12));
            JButton3.setText(resourceBundle.getString("  Close  "));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JButton3,ex); 
          }

//<UserCode_Begin_Bean_JButton3>
JButton3.setMnemonic('C');
//<UserCode_End_Bean_JButton3>

  
          //<End_setUpProperties>
         JScrollPane1.setBorder(new javax.swing.border.TitledBorder(new javax.swing.border.LineBorder(new Color(-10066279),1),resourceBundle.getString("Audit Details"),0,0,new Font("Dialog",0,12),new Color(-10066279)));
  } 
  public void initVariables()
  {
        //<Begin_initVariables> 
        Top= new javax.swing.JPanel();
        JScrollPane1= new javax.swing.JScrollPane();
        SortTable1= new com.adventnet.beans.table.SortTable();
        JButton1= new javax.swing.JButton();
        JButton2= new javax.swing.JButton();
        JButton3= new javax.swing.JButton();
        SortTableModel1= new com.adventnet.beans.table.SortTableModel();

  
        //<End_initVariables>
  } 
  public void setUpGUI(Container container)
  {
//<Begin_setUpGUI_Container> 
container.add(Top,BorderLayout.CENTER);
Top.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(0,0,3,1,0.0,0.01,cons.CENTER,cons.BOTH,inset,0,0);
Top.add(JScrollPane1,cons);
JScrollPane1.getViewport().add(SortTable1);
inset = new Insets(5,5,5,5);
setConstraints(0,1,1,1,0.01,0.0,cons.EAST,cons.NONE,inset,0,0);
Top.add(JButton1,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,1,1,1,0.0,0.0,cons.EAST,cons.NONE,inset,0,0);
Top.add(JButton2,cons);
inset = new Insets(5,5,5,5);
setConstraints(2,1,1,1,0.0,0.0,cons.EAST,cons.NONE,inset,0,0);
Top.add(JButton3,cons);

  
//<End_setUpGUI_Container>
  } 
  public void setUpConnections()
  {
  //<Begin_setUpConnections> 

      JButton2_SortTableModel1_conn JButton2_SortTableModel1_conn1 =  new JButton2_SortTableModel1_conn();
      JButton2.addActionListener(JButton2_SortTableModel1_conn1);
      JButton3_JButton3_conn JButton3_JButton3_conn1 =  new JButton3_JButton3_conn();
      JButton3.addActionListener(JButton3_JButton3_conn1);
      JButton1_JButton1_conn JButton1_JButton1_conn1 =  new JButton1_JButton1_conn();
      JButton1.addActionListener(JButton1_JButton1_conn1);
      JButton2_JButton2_conn JButton2_JButton2_conn1 =  new JButton2_JButton2_conn();
      JButton2.addActionListener(JButton2_JButton2_conn1);
  
      //<End_setUpConnections>
  } 



  
  public void showStatus(String message)
  {
     //<Begin_showStatus_String>
     System.out.println("Internal Error :"+ message);
     //<End_showStatus_String>
  }
  public void showStatus(String message,Exception ex)
  {
     //<Begin_showStatus_String_Exception>
     System.out.println("Internal Error :"+ message);
     ex.printStackTrace();
     //<End_showStatus_String_Exception>
  }





  

   
  public void setConstraints(int x,int y,int width,int height,double wtX,double wtY,int anchor,int fill,Insets inset,int padX,int padY )
  {
         //<Begin_setConstraints_int_int_int_int_double_double_int_int_Insets_int_int> 
	cons.gridx = x;
	cons.gridy = y;
	cons.gridwidth = width;
	cons.gridheight = height;
	cons.weightx = wtX;
	cons.weighty = wtY;
	cons.anchor = anchor;
	cons.fill = fill;
	cons.insets = inset;
	cons.ipadx = padX;
	cons.ipady = padY;
	
  
         //<End_setConstraints_int_int_int_int_double_double_int_int_Insets_int_int>
  }




  

  
	//This method will set the data to the TableModel.
	public void setAuditData(Vector auditVec)
	{
		int size = auditVec.size();
		int count = 0;
		Properties prop = null;
		Object[][] obj1 = new Object[size][4];
		for(int i=0;i<size;i++)
		{
			prop = (Properties)auditVec.elementAt(i);
			obj1[count][0] = prop.get("username");
			obj1[count][1] = resourceBundle.getString((String)prop.get("operation"));
//			obj1[count][2] = prop.get("audittime");	
			int year=0,hrs=0,month=0,min=0,sec=0,dat=0;	
	 	       Calendar c = Calendar.getInstance();	
 		       StringTokenizer st = new StringTokenizer((String)prop.get("audittime")," ");	
	 	       String date = st.nextToken();	
 		       String time = st.nextToken();	
	 	       StringTokenizer dt = new StringTokenizer(date,"-");	
 		       StringTokenizer tm = new StringTokenizer(time,":");	
	 	       int j=0;
		       while(dt.hasMoreTokens())	
	 	       {	
 	          	  if(j==0)	
	 	          {	
 		             year = Integer.parseInt(dt.nextToken());	
 	        	     hrs = Integer.parseInt(tm.nextToken());	
	 	          }	
 		          if(j==1)	
 	        	  {	
	 	            month = Integer.parseInt(dt.nextToken());	
 		            min = Integer.parseInt(tm.nextToken());	
	 	          }	
 		          if(j==2)	
 	        	  {	
	 	             dat = Integer.parseInt(dt.nextToken());	
 		             StringTokenizer s =  new StringTokenizer(tm.nextToken(),".");
	 	             sec = Integer.parseInt(s.nextToken());	
 		          }	
 	        	  j++;	
	 	       }	
 		       c.set(year,month-1,dat,hrs,min,sec);	
	 	       String ID=(NmsClientUtil.applet.getParameter("BE_TIMEZONE_ID"));	
 		       c.setTimeZone(TimeZone.getTimeZone(ID));	
	 	       long lvalue = c.getTime().getTime();
                       obj1[count][2] = NmsClientUtil.formatDate(lvalue);
       		obj1[count][3] = resourceBundle.getString((String)prop.get("status"));
			count ++;			
		}
		
		String[] columnNames = {resourceBundle.getString("User Name"),resourceBundle.getString("Operation Name"),resourceBundle.getString("Time"),resourceBundle.getString("Status")};
		SortTableModel1.setDataVector(obj1,columnNames);
		
	}
 
	
	private void writeToFile(File myFile)
	{
		TableToString stream = new TableToString();
		String myString = stream.convertToStream(SortTable1);
		//System.out.println("THE STRING "+stream.convertToStream(SortTable1));
		StringTokenizer token = new StringTokenizer(myString,"#",false);
		FileWriter myname = null;
		try
		{
			myname = new FileWriter(myFile);
		}
		catch(IOException io)
		{
			System.out.println("Exception while creating the file :"+io.getMessage());
		}
		while(token.hasMoreElements())
		{
			String newString = token.nextToken().toString();
			try
			{
				myname.write(newString);
				myname.write(System.getProperty("line.separator"));
				myname.flush();
			}
			catch(IOException ioe)
			{
				System.out.println("Exception while writing to the file :"+ioe.getMessage());
			}
		}
	}

//<Begin__class_JButton1_JButton1_conn>

 class JButton1_JButton1_conn implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_JButton1_JButton1_conn>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
  showFileChooser();
 dispose();
     }
//<UserCode_End_Connection_JButton1_JButton1_conn>
 }//<End__class_JButton1_JButton1_conn>

 


//<Begin__class_JButton2_SortTableModel1_conn>

 class JButton2_SortTableModel1_conn implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_JButton2_SortTableModel1_conn>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
     }
//<UserCode_End_Connection_JButton2_SortTableModel1_conn>
 }//<End__class_JButton2_SortTableModel1_conn>

	
	
	JFileChooser saveFileChooser = null;
	SaveDialog svDialog = null;
	public void showFileChooser()
	{
		if(AuthMain.standalone)
		{
			File file = null;
			saveFileChooser = new JFileChooser(".");//No Internationalisation
			saveFileChooser.setDialogType(JFileChooser.SAVE_DIALOG);
			saveFileChooser.setDialogTitle(resourceBundle.getString("Save Contents"));
		
	int retVal = saveFileChooser.showSaveDialog(this);
		
	if(retVal == JFileChooser.APPROVE_OPTION)
			{
		
		file = saveFileChooser.getSelectedFile();
				if(file.exists())
				{
					int Val = JOptionPane.showConfirmDialog(this,resourceBundle.getString("File Already Exists")+"\n"+resourceBundle.getString(" Over-write Existing file"),resourceBundle.getString("Confirm Over write"),JOptionPane.YES_NO_OPTION);
				
	if(Val == JOptionPane.YES_OPTION)
					{
				//	saveContents(file);
						writeToFile(file);
					}
				}
				else 
				{
				//saveContents(file);
					writeToFile(file);
				}
			}
		}
		else
		{
			svDialog = new SaveDialog(AuthMain.main,applet);
			svDialog.setVisible(true);
			TableToString stream = new TableToString();
			String myString = stream.convertToStream(SortTable1);
			svDialog.setDetails(myString);
		}
	}
	/*
	private void saveContents(File file)
	{
		//this will not happen..
		if(file == null)
		{
			return;
		}


		// Uncomment the below piece of code if we really want to
		// intimate the user about over-writing the contents of the
		// file.As I felt it to be a bit uncomfortable in bringing
		// dialogs at different levels, I am commenting this code.

		/*
		   else if(file.exists())
		   {
		   boolean okSave = showWarn();
		   if(!okSave)
		   {
		   showFileChooser();
		   return;
		   }
		   }
		 */
		/*
		try
		{
			FileOutputStream fos = new FileOutputStream(file);
			DataOutputStream dfs = new DataOutputStream(fos);

			String contents = messageArea.getText();
			dfs.writeBytes(contents);

			dfs.close();
			fos.close();
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(messageArea,resourceBundle.getString("Error while writing to the file") ,resourceBundle.getString("Save Error"),JOptionPane.ERROR_MESSAGE);
		}
	}

	*/


  public AuditScreen()
  {
    //<Begin_AuditScreen>
    pack();
  
    //<End_AuditScreen>
  }

  public AuditScreen(java.applet.Applet applet)
  {
    //<Begin_AuditScreen_java.applet.Applet>
    this.applet = applet;
    pack();
    this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
  
    //<End_AuditScreen_java.applet.Applet>
  }

  public AuditScreen( Frame owner, java.applet.Applet applet)
  {
    super(owner);	 
    this.applet = applet;
    pack();
    this.setTitle("AuditScreen");
    this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
  }

//<Begin__class_JButton2_JButton2_conn>

 class JButton2_JButton2_conn implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_JButton2_JButton2_conn>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
     AuthMain.main.disableButtons();
   AuthMain.model.clearAudit(null);
   //setAuditData(new Vector());//Commented as we have to clear the table once server responds
   AuthMain.main.enableButtons();
   SwingUtilities.updateComponentTreeUI(JScrollPane1);
     }
//<UserCode_End_Connection_JButton2_JButton2_conn>
 }//<End__class_JButton2_JButton2_conn>

 


//<Begin__class_JButton3_JButton3_conn>

 class JButton3_JButton3_conn implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_JButton3_JButton3_conn>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
   dispose();
 AuthMain.main.enableButtons();
     }
//<UserCode_End_Connection_JButton3_JButton3_conn>
 }//<End__class_JButton3_JButton3_conn>

 
  public void setProperties(java.util.Properties props)
  {
  //<Begin_setProperties_java.util.Properties> 

  
  //<End_setProperties_java.util.Properties>
  }
public void valueChanged()
{
setAuditData(AuthMain.model.getAudit(null) );  
} 

}









