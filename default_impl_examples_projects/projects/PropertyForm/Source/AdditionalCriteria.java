//$Id: AdditionalCriteria.java,v 1.2 2008/05/07 12:54:36 sureshm Exp $
// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// Kindly refer to tcats_edit_source_files.html file under help/source_generation directory for details




//<Begin_Version>
//version$9
//<End_Version>
package com.adventnet.nms.mapui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import org.w3c.dom.*;
import com.adventnet.nms.util.NmsClientUtil;
import java.util.Vector;

public class AdditionalCriteria extends JDialog  implements ActionListener,com.adventnet.apiutils.ParameterChangeListener 
{
	//<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	private boolean running=false;
	javax.swing.JPanel Top = null;
	javax.swing.JPanel JPanel2 = null;
	javax.swing.JButton ok = null;
	javax.swing.JButton cancel = null;
	javax.swing.JPanel JPanel1 = null;
	javax.swing.JButton more = null;
	javax.swing.JButton fewer = null;
	javax.swing.JScrollPane JScrollPane1 = null;
	javax.swing.JPanel criteriaPanel = null;
	private com.adventnet.apiutils.ParameterObject po= null;
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
	//<End_Variable_Declarations>
	Element group=null;
	private boolean isModified=false;
           private Vector finalVector=null;
  	private int count=0;

  


  


 

  

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
        if (initialized) return; 
        this.setSize(getPreferredSize().width+445,getPreferredSize().height+375); 
          setTitle("new_screen1");
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
          showStatus("Error in init method",ex); 
        } 
        // let us set the initialized variable to true so  
        // we dont initialize again even if init is called 
        initialized = true; 

  
        //<End_init>
  NmsClientUtil.setScreenSize(Toolkit.getDefaultToolkit().getScreenSize());
  NmsClientUtil.centerWindow(this);
  this.setTitle(NmsClientUtil.GetString("javaclient.propform.map.criteria.title"));
  	constructGUI();
  } 
  public String getParameter(String input)
  {
           //<Begin_getParameter_String> 
	if (po != null) 
	 { 
	   if(po.getParameter(input) != null) 
	     {
	       return (String)po.getParameter(input); 
	     }
	 }
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
            }
        return value;

  
         //<End_getParameter_String>
  } 
  public void setUpProperties()
  {
  //<Begin_setUpProperties> 

          try
          {
            ok.setFont(new Font("SansSerif",0,12));
            ok.setHorizontalTextPosition(4);
            ok.setText("OK");
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+ok,ex); 
          }

//<UserCode_Begin_Bean_ok>
ok.addActionListener(this);
ok.setText(NmsClientUtil.GetString("javaclient.propform.map.okbutton"));
ok.setActionCommand("ok");
ok.setFont(new Font((NmsClientUtil.getFont()).getName(),Font.PLAIN,(NmsClientUtil.getFont()).getSize()));
//<UserCode_End_Bean_ok>

          try
          {
            cancel.setFont(new Font("SansSerif",0,12));
            cancel.setHorizontalTextPosition(4);
            cancel.setText("Cancel");
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+cancel,ex); 
          }

//<UserCode_Begin_Bean_cancel>
cancel.addActionListener(this);
cancel.setActionCommand("cancel");
cancel.setText(NmsClientUtil.GetString("javaclient.propform.map.cancelbutton"));
cancel.setFont(new Font((NmsClientUtil.getFont()).getName(),Font.PLAIN,(NmsClientUtil.getFont()).getSize()));
//<UserCode_End_Bean_cancel>

          try
          {
            more.setFont(new Font("SansSerif",0,12));
            more.setHorizontalTextPosition(4);
            more.setText("More");
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+more,ex); 
          }

//<UserCode_Begin_Bean_more>
more.addActionListener(this);
more.setActionCommand("more");
more.setText(NmsClientUtil.GetString("javaclient.propform.map.morebutton"));
more.setFont(new Font((NmsClientUtil.getFont()).getName(),Font.PLAIN,(NmsClientUtil.getFont()).getSize()));
//<UserCode_End_Bean_more>

          try
          {
            fewer.setFont(new Font("SansSerif",0,12));
            fewer.setHorizontalTextPosition(4);
            fewer.setText("Fewer");
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+fewer,ex); 
          }

//<UserCode_Begin_Bean_fewer>
fewer.addActionListener(this);
fewer.setActionCommand("fewer");
fewer.setEnabled(false);
fewer.setText(NmsClientUtil.GetString("javaclient.propform.map.fewerbutton"));
fewer.setFont(new Font((NmsClientUtil.getFont()).getName(),Font.PLAIN,(NmsClientUtil.getFont()).getSize()));
//<UserCode_End_Bean_fewer>
		ok.setPreferredSize(new Dimension(ok.getPreferredSize().width+16,ok.getPreferredSize().height+1));

  
          //<End_setUpProperties>
  } 
  public void initVariables()
  {
        //<Begin_initVariables> 
	 if(po == null)
	{
	po = new com.adventnet.apiutils.ParameterObject();
	}
        Top= new javax.swing.JPanel();
        JPanel2= new javax.swing.JPanel();
        ok= new javax.swing.JButton();
        cancel= new javax.swing.JButton();
        JPanel1= new javax.swing.JPanel();
        more= new javax.swing.JButton();
        fewer= new javax.swing.JButton();
        JScrollPane1= new javax.swing.JScrollPane();
        criteriaPanel= new javax.swing.JPanel();
        initializeParameters(); 

  
          //<End_initVariables>
  finalVector=new Vector();
  } 
  public void setUpGUI(Container container)
  {
//<Begin_setUpGUI_Container> 
container.add(Top,BorderLayout.CENTER);
Top.setLayout(new BorderLayout(5,5));
Top.add(JPanel2,BorderLayout.SOUTH);
JPanel2.setLayout(new FlowLayout(2,5,5));
JPanel2.add(ok);
JPanel2.add(cancel);
Top.add(JPanel1,BorderLayout.EAST);
JPanel1.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(0,0,1,1,0.0,0.0,cons.CENTER,cons.BOTH,inset,0,0);
JPanel1.add(more,cons);
inset = new Insets(5,5,5,5);
//setConstraints(0,1,1,1,0.0,0.0,cons.CENTER,cons.BOTH,inset,0,0);
//Issue ID:3010202
setConstraints(0,1,1,1,0.0,0.0,cons.CENTER,cons.BOTH,inset,1,0);
JPanel1.add(fewer,cons);
Top.add(JScrollPane1,BorderLayout.CENTER);
JScrollPane1.getViewport().add(criteriaPanel);
criteriaPanel.setLayout(new GridBagLayout());

  
//<End_setUpGUI_Container>
  } 
  public void setUpConnections()
  {
  //<Begin_setUpConnections> 

  
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





  

   
  public void setProperties(java.util.Properties props)
  {
         //<Begin_setProperties_java.util.Properties> 
	if(po != null)
	{
	po.setParameters(props);
	}
  
         //<End_setProperties_java.util.Properties>
  } 
  private void initializeParameters()
  {
          //<Begin_initializeParameters> 
	 if(po != null) 
	   {
	    po.addParameterChangeListener(this);
	   }

  
          //<End_initializeParameters>
  } 
  public void destroy()
  {
         //<Begin_destroy> 
	if(po != null)
	{
	 po.removeParameterChangeListener(this);
	}
  
         //<End_destroy>
  } 
  public void setParameterObject(com.adventnet.apiutils.ParameterObject paramObj)
  {
         //<Begin_setParameterObject_com.adventnet.apiutils.ParameterObject> 
	this.po=paramObj;

  
         //<End_setParameterObject_com.adventnet.apiutils.ParameterObject>
  } 
  public void parameterChanged(com.adventnet.apiutils.ParameterObject paramObj)
  {
  //<Begin_parameterChanged_com.adventnet.apiutils.ParameterObject> 

  
  //<End_parameterChanged_com.adventnet.apiutils.ParameterObject>
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
  }

public AdditionalCriteria(Element group,JDialog parent){
      super(parent,"Edit Criteria",true);
     this.group=group;
}


  public AdditionalCriteria()
  {
    //<Begin_AdditionalCriteria>
    pack();
  
    //<End_AdditionalCriteria>
  }

  public AdditionalCriteria(java.applet.Applet applet)
  {
    //<Begin_AdditionalCriteria_java.applet.Applet>
    this.applet = applet;
    pack();
    this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
  
    //<End_AdditionalCriteria_java.applet.Applet>
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

  private void constructGUI(){
       NodeList rowList=group.getElementsByTagName("ROW");
       int rowCount=rowList.getLength();
       for(int i=0;i<rowCount;i++){
            Element row=(Element)rowList.item(i);
            if(!row.getAttribute("type").equalsIgnoreCase("property")){
                continue; 
            }
           String displayName=row.getAttribute("displayName");
           String value=row.getAttribute("currentValue");

           fewer.setEnabled(true);
	   addRow(count,displayName,value);
           count++;
       }
      
  }
  
  public boolean isModified(){
       return isModified;
  }

  public void actionPerformed(ActionEvent ae){
       String actionCommand=ae.getActionCommand();
       if(actionCommand.equals("cancel")){
            setVisible(false);
       }
       else if(actionCommand.equals("ok")){
            isModified=true;
            updateXML();
            setVisible(false);
       }
       else if(actionCommand.equals("more")){	
       	 addRow(count,"","");
       	 count++;
       	 fewer.setEnabled(true);
         criteriaPanel.updateUI();
       }
       else if(actionCommand.equals("fewer")){
            count--;
            removeRow(count);
            if(count==0){
                 fewer.setEnabled(false);
            }
            criteriaPanel.updateUI();            
       }
  }
  
	private void addRow(int i,String key,String value)
	{
		JTextField field1=new JTextField();
		field1.setText(key);
		inset = new Insets(5,5,5,5);
		setConstraints(0,i,1,1,0.1,0.0,cons.WEST,cons.HORIZONTAL,inset,0,0);
		criteriaPanel.add(field1,cons);
		JTextField field2=new JTextField();
		field2.setText(value);
		inset = new Insets(5,5,5,5);
		//Issue ID:3010202
		//setConstraints(1,i,1,1,0.1,0.0,cons.WEST,cons.HORIZONTAL,inset,0,0);
		setConstraints(1,i,1,1,0.1,0.0,cons.WEST,cons.HORIZONTAL,inset,5,0);
		criteriaPanel.add(field2,cons);
		Vector v=new Vector();
		v.add(field1);
		v.add(field2);
		finalVector.add(v);

	}
 	private void removeRow(int i)
	{
	 	Vector v=(Vector)finalVector.elementAt(i);
		criteriaPanel.remove((JTextField)v.elementAt(0));
		criteriaPanel.remove((JTextField)v.elementAt(1));
		finalVector.remove(i);
	       
	}
	
	private void updateXML(){
	     NodeList childs=group.getChildNodes();
	     //Remove all the childs
	     int childLength=childs.getLength();
	     for(int i=childLength-1;i>=0;i--){
	          group.removeChild(childs.item(i));
	     }
	     
	     //Add the divider
	    Document ownerDoc=group.getOwnerDocument();
	    Element divider=ownerDoc.createElement("ROW");
               divider.setAttribute("type","divider");
               divider.setAttribute("displayName","Map Criteria");
               group.appendChild(divider);
               
               //Add the rows
              for(int j=0;j<finalVector.size();j++){
                   Vector v=(Vector)finalVector.elementAt(j);
                   String key=((JTextField)v.elementAt(0)).getText();
                  String value=((JTextField)v.elementAt(1)).getText();
                   if(key.trim().equals("")){
                        continue;
                   }
              Element row=ownerDoc.createElement("ROW");
              row.setAttribute("type","property");
              row.setAttribute("name",key);
              row.setAttribute("displayName",key);
              row.setAttribute("currentValue",value);
              row.setAttribute("isEditable","true");
              row.setAttribute("component","textfield");
              group.appendChild(row);
             }
	}
}















