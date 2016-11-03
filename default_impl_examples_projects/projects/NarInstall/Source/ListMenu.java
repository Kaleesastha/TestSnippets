

/**
 * $Id:
 */


// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// For more details please see EditingSourceFiles.html present in the docs directory

//<Begin_Version>
//version$1
//<End_Version>
package com.adventnet.nms.tools.nar;

import com.adventnet.nms.util.XMLDataReader;
import com.adventnet.nms.util.XMLNode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.event.*;
import com.adventnet.nms.tools.utils.FilePreviewer;
import com.adventnet.nms.tools.utils.ImgConv;
import javax.swing.table.*;
import java.net.URL;
import com.adventnet.nms.tools.utils.ImgConv;
import com.adventnet.nms.tools.utils.ToolsUtil;

public class ListMenu extends JPanel implements ItemListener,MouseListener,ActionListener
{
	//<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	private String localePropertiesFileName = "DeploymentWizard";
	static com.adventnet.apiutils.BuilderResourceBundle resourceBundle = null;
	private boolean running=false;
	javax.swing.JPanel Top = null;
	javax.swing.JPanel JPanel4 = null;
	javax.swing.JPanel JPanel1 = null;
	javax.swing.JLabel configureList = null;
	javax.swing.JLabel menuName = null;
	javax.swing.JLabel itemName = null;
	javax.swing.JCheckBox checkList = null;
	javax.swing.JTextField menuNameField = null;
	javax.swing.JTextField itemNameField = null;
	javax.swing.JPanel JPanel3 = null;
	javax.swing.JPanel JPanel2 = null;
	javax.swing.JLabel severity = null;
	javax.swing.JLabel image = null;
	javax.swing.JTextField severityField = null;
	javax.swing.JTextField imageField = null;
	javax.swing.JButton browse = null;
	javax.swing.JButton update = null;
	javax.swing.JCheckBox checkImage = null;
	javax.swing.JScrollPane JScrollPane1 = null;
	javax.swing.JTable JTable1 = null;
	javax.swing.JLabel JLabel1 = null;
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
	//<End_Variable_Declarations>
    Vector columnNames;
    TreeTableModel tableModel;
    JLabel imageLabel=null;
  
    ImageRenderer renderer;
    Vector transparentImage;
    Vector  nonTransparentImage;
    Vector listInformation;
    Hashtable attribute;

















   


  public ListMenu()
  {
    //<Begin_ListMenu>
    this.init();
  
    //<End_ListMenu>
  }
    public ListMenu(Hashtable attrib)
    {
        
        attribute=attrib;
        this.init();
    }

  public ListMenu(java.applet.Applet applet)
  {
    //<Begin_ListMenu_java.applet.Applet>
    this.applet = applet;
    this.init();
  
    //<End_ListMenu_java.applet.Applet>
  }

 
    public void start()
  { 

  //<Begin_start> 
       if(running)
 return;
 running=true;

  
       //<End_start>
  } 
  public void initVariables()
  { 

  //<Begin_initVariables> 
        Top= new javax.swing.JPanel();
        JPanel4= new javax.swing.JPanel();
        JPanel1= new javax.swing.JPanel();
        configureList= new javax.swing.JLabel();
        menuName= new javax.swing.JLabel();
        itemName= new javax.swing.JLabel();
        checkList= new javax.swing.JCheckBox();
        menuNameField= new javax.swing.JTextField();
        itemNameField= new javax.swing.JTextField();
        JPanel3= new javax.swing.JPanel();
        JPanel2= new javax.swing.JPanel();
        severity= new javax.swing.JLabel();
        image= new javax.swing.JLabel();
        severityField= new javax.swing.JTextField();
        imageField= new javax.swing.JTextField();
        browse= new javax.swing.JButton();
        update= new javax.swing.JButton();
        checkImage= new javax.swing.JCheckBox();
        JScrollPane1= new javax.swing.JScrollPane();
        JTable1= new javax.swing.JTable();
        JLabel1= new javax.swing.JLabel();

  
        //<End_initVariables>
        severityField.setEditable(false);
        columnNames=new Vector();
       
        columnNames.add(resourceBundle.getString("ImageCategory"));
        columnNames.add(resourceBundle.getString("ImagePreview"));
        imageLabel=new JLabel();
        tableModel=new TreeTableModel(columnNames,0);
        update.addActionListener(this);
        update.setActionCommand("update");
        browse.addActionListener(this);
        browse.setActionCommand("browse");
        renderer=new ImageRenderer();
        
       
        disableAll();
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
   
  public void setUpGUI(Container container)
  { 

  //<Begin_setUpGUI_Container> 
container.add(Top,BorderLayout.CENTER);
Top.setLayout(new BorderLayout(5,5));
Top.add(JPanel4,BorderLayout.CENTER);
JPanel4.setLayout(new BorderLayout(5,5));
JPanel4.add(JPanel1,BorderLayout.NORTH);
JPanel1.setLayout(new GridBagLayout());
inset = new Insets(4,4,4,4);
setConstraints(0,0,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
JPanel1.add(configureList,cons);
inset = new Insets(4,4,4,4);
setConstraints(0,1,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
JPanel1.add(menuName,cons);
inset = new Insets(4,4,4,4);
setConstraints(0,2,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
JPanel1.add(itemName,cons);
inset = new Insets(4,4,4,4);
setConstraints(1,0,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel1.add(checkList,cons);
inset = new Insets(4,4,4,4);
setConstraints(1,1,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel1.add(menuNameField,cons);
inset = new Insets(4,4,4,4);
setConstraints(1,2,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel1.add(itemNameField,cons);
JPanel4.add(JPanel3,BorderLayout.CENTER);
JPanel3.setLayout(new BorderLayout(5,5));
JPanel3.add(JPanel2,BorderLayout.SOUTH);
JPanel2.setLayout(new GridBagLayout());
inset = new Insets(2,2,2,2);
setConstraints(0,0,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
JPanel2.add(severity,cons);
inset = new Insets(2,2,2,2);
setConstraints(0,1,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
JPanel2.add(image,cons);
inset = new Insets(2,2,2,2);
setConstraints(1,0,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel2.add(severityField,cons);
inset = new Insets(2,2,2,2);
setConstraints(1,1,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel2.add(imageField,cons);
inset = new Insets(2,2,2,2);
setConstraints(2,1,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
JPanel2.add(browse,cons);
inset = new Insets(2,2,2,2);
setConstraints(1,2,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
JPanel2.add(update,cons);
JPanel3.add(checkImage,BorderLayout.NORTH);
JPanel3.add(JScrollPane1,BorderLayout.CENTER);
JScrollPane1.getViewport().add(JTable1);
Top.add(JLabel1,BorderLayout.WEST);

  
//<End_setUpGUI_Container>
        JTable1.setModel(tableModel);
        JTable1.setSelectionMode(0);
        JTable1.getColumnModel().getColumn(1).setCellRenderer(renderer);
        JTable1.addMouseListener(this);
        checkList.addItemListener(this);
         checkImage.addItemListener(this);
         setTransparentImage("");
         setImage();

  } 
  public void setUpProperties()
  { 

  //<Begin_setUpProperties> 

          try
          {
            Top.setBorder(new javax.swing.border.TitledBorder(new javax.swing.border.LineBorder(new Color(-16777216),1),resourceBundle.getString("List Menu Information"),2,0,new Font("Dialog",0,12),new Color(-16777216)));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+Top,ex); 
          }

//<UserCode_Begin_Bean_components>

//<UserCode_End_Bean_components>

          try
          {
            JPanel4.setBorder(new javax.swing.border.BevelBorder(0));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel4,ex); 
          }

//<UserCode_Begin_Bean_JPanel4>

//<UserCode_End_Bean_JPanel4>

          try
          {
            configureList.setText(resourceBundle.getString("Configure List Menu"));
            configureList.setFont(new Font("Dialog",0,12));
            configureList.setForeground(new Color(-16777216));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+configureList,ex); 
          }

//<UserCode_Begin_Bean_configureList>

//<UserCode_End_Bean_configureList>

          try
          {
            menuName.setFont(new Font("Dialog",0,12));
            menuName.setText(resourceBundle.getString("Menu Name"));
            menuName.setForeground(new Color(-16777216));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+menuName,ex); 
          }

//<UserCode_Begin_Bean_menuName>

//<UserCode_End_Bean_menuName>

          try
          {
            itemName.setFont(new Font("Dialog",0,12));
            itemName.setText(resourceBundle.getString("Menu Item Name"));
            itemName.setForeground(new Color(-16777216));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+itemName,ex); 
          }

//<UserCode_Begin_Bean_itemName>

//<UserCode_End_Bean_itemName>

          try
          {
            checkList.setSelected(false);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+checkList,ex); 
          }

//<UserCode_Begin_Bean_checkList>

//<UserCode_End_Bean_checkList>

          try
          {
            JPanel3.setBorder(new javax.swing.border.TitledBorder(new javax.swing.border.EtchedBorder(0),resourceBundle.getString("Image Details"),0,0,new Font("Dialog",0,12),new Color(-16777216)));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel3,ex); 
          }

//<UserCode_Begin_Bean_JPanel3>

//<UserCode_End_Bean_JPanel3>

          try
          {
            severity.setText(resourceBundle.getString("Severity"));
            severity.setFont(new Font("Dialog",0,12));
            severity.setForeground(new Color(-16777216));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+severity,ex); 
          }

//<UserCode_Begin_Bean_severity>

//<UserCode_End_Bean_severity>

          try
          {
            image.setText(resourceBundle.getString("Image Name"));
            image.setFont(new Font("Dialog",0,12));
            image.setForeground(new Color(-16777216));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+image,ex); 
          }

//<UserCode_Begin_Bean_image>

//<UserCode_End_Bean_image>

          try
          {
            browse.setText(resourceBundle.getString("..."));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+browse,ex); 
          }

//<UserCode_Begin_Bean_browse>

//<UserCode_End_Bean_browse>

          try
          {
            update.setText(resourceBundle.getString("Update"));
            update.setFont(new Font("Dialog",0,12));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+update,ex); 
          }

//<UserCode_Begin_Bean_update>

//<UserCode_End_Bean_update>

          try
          {
            checkImage.setText(resourceBundle.getString("Use Transparaent Image"));
            checkImage.setFont(new Font("Dialog",0,12));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+checkImage,ex); 
          }

//<UserCode_Begin_Bean_checkImage>

//<UserCode_End_Bean_checkImage>
		JLabel1.setPreferredSize(new Dimension(JLabel1.getPreferredSize().width+168,JLabel1.getPreferredSize().height+388));
		JScrollPane1.setPreferredSize(new Dimension(JScrollPane1.getPreferredSize().width+15,JScrollPane1.getPreferredSize().height+15));
		JPanel3.setPreferredSize(new Dimension(JPanel3.getPreferredSize().width+10,JPanel3.getPreferredSize().height+10));
		JPanel4.setPreferredSize(new Dimension(JPanel4.getPreferredSize().width+10,JPanel4.getPreferredSize().height+10));

  
          //<End_setUpProperties>

		checkImage.setText(resourceBundle.getString("Use Transparent Image"));
                 URL url=getClass().getResource("images"+File.separator+"listmenu.jpg");
                 JLabel1.setIcon(new ImageIcon(ImgConv.getImage(url.toString())));
  } 
	public void itemStateChanged(ItemEvent ie)
	{
            if(ie.getSource().equals(checkList))
            {
                if(checkList.isSelected())
		{
                    enableAll();
                     checkImage.setSelected(false);
                    
		}
		else
		{
                    disableAll();
		}
            }
            if(ie.getSource().equals(checkImage))
            {
                if(checkImage.isSelected())
                {
                    Vector v1=getTransparentImage();
                    if(tableModel.getRowCount()!= -1)
                    {
                        int rows=tableModel.getRowCount();
                        for(int j=0;j<rows;j++)
                        {
                            tableModel.removeRow(0);
                        }
                    }
                    tableModel.addRow(v1);
                    
                }
                else
                {
                    Vector v2=getNonTransparentImage();
                    if(tableModel.getRowCount()!= -1)
                    {
                        int rows=tableModel.getRowCount();
                        for(int j=0;j<rows;j++)
                        {
                            tableModel.removeRow(0);
                        }
                    }
                    for(int i=0;i<v2.size();i++)
                    {
                        tableModel.addRow((Vector)v2.elementAt(i));
                    }
                   
                }
            }
	}



    public Vector getTransparentImage()
    {
        return transparentImage;
    }
    public Vector getNonTransparentImage()
    {
        return nonTransparentImage;
    }
    public void setTransparentImage(String s)
    {
        Vector image=new Vector();
        image.add("TransParent Image");
        image.add(s);
        transparentImage=image;
    }
    public void setNonTransParentImage(String[] s1)
    {   
        if(s1!=null)
        {
            if(nonTransparentImage!=null)
            {
                for(int i=0;i<nonTransparentImage.size();i++)
                {
                    Vector v=(Vector)nonTransparentImage.elementAt(i);
                    if(v.elementAt(0).toString().equals(s1[0]))
                    {
                        
                        v.setElementAt(s1[1],1);
                        nonTransparentImage.removeElementAt(i);
                        nonTransparentImage.add(i,v);
                    }

                }
            }
        }
    }
    
    
    public  void setImage()
    {
        Vector v=getSeverity();
        File f=new File(System.getProperty("user.dir")+File.separator+"conf"+File.separator+"listIcon.data");
        String type="";
        if(attribute!=null)
        {
             type=attribute.get("DEVICE_TYPE").toString();
        }
        XMLDataReader read=new XMLDataReader(f.toString(),false);
        XMLNode root=read.getRootNode();
        Vector severityImage=new Vector();
        Vector childs=root.getChildNodes();
        boolean present=false;
        for(int i=0;i< childs.size();i++)
        {
            XMLNode node=(XMLNode)childs.elementAt(i);
            if(node.getNodeType()==XMLNode.ELEMENT)
            {
                if(node.getAttribute("TYPE").toString().equalsIgnoreCase(type))
                {
                    for(int j=0;j<v.size();j++)
                    {
                        Vector image=new Vector();
                        present=true;
                        image.add(v.elementAt(j));
                        if(node.getAttribute(v.elementAt(j).toString().toUpperCase()+"_IMG")!=null)
                        {
                            image.add(System.getProperty("user.dir")+File.separator+"icons"+File.separator+node.getAttribute(v.elementAt(j).toString().toUpperCase()+"_IMG"));
                        }
                        
                        severityImage.add(image);
                    }
					if(node.getAttribute("DEFAULT_TRANSPARENT_IMG")!=null)
					{
						
					   setTransparentImage(System.getProperty("user.dir")+File.separator+"icons"+File.separator+node.getAttribute("DEFAULT_TRANSPARENT_IMG").toString());
					}
                }
            }
        }
        if(present)
        {
            nonTransparentImage=severityImage;
        }
        else
        {
            //Vector severityImage
            for(int j=0;j<v.size();j++)
            {
                Vector image=new Vector();
                image.add(v.elementAt(j));
                image.add("");
                severityImage.add(image);
            }
            nonTransparentImage=severityImage;
        }
       
    }
    public Vector getSeverity()
    {
        String file=System.getProperty("user.dir")+File.separator+"conf"+File.separator+"SeverityInfo.conf";
        File f= new File(file);
        XMLDataReader reader=new XMLDataReader(f.toString(),false);
        XMLNode root=reader.getRootNode();
        Vector childs=root.getChildNodes();
        Vector severity=new Vector();
        for(int i=0;i<childs.size();i++)
        {
            XMLNode node=(XMLNode)childs.elementAt(i);
            if(node.getNodeType()==XMLNode.ELEMENT)
            {
                if(node.getAttribute("ID").toString().equalsIgnoreCase("unknown")||node.getAttribute("ID").toString().equalsIgnoreCase("info"))
                {
                    continue;
                }
                else
                {
                    severity.add(node.getAttribute("ID"));
                }
            }
        }
        return severity;
    }
	public void enableAll()
	{
		menuNameField.setEnabled(true);
		itemNameField.setEnabled(true);	
		checkImage.setEnabled(true);
		JTable1.setEnabled(true);
                severityField.setEnabled(true);

                imageField.setEnabled(true);
		update.setEnabled(true);
		browse.setEnabled(true);
		
	}
	public void disableAll()
	{
		menuNameField.setEnabled(false);
		itemNameField.setEnabled(false);
		checkImage.setEnabled(false);
		JTable1.setEnabled(false);
		severityField.setEnabled(false);
		imageField.setEnabled(false);
		update.setEnabled(false);
		browse.setEnabled(false);
	}
  public void setUpConnections()
  { 

  //<Begin_setUpConnections> 

  
  //<End_setUpConnections>
  } 
  public void stop()
  { 

  //<Begin_stop> 
       if(!running)
 return;
 running=false;

  
       //<End_stop>
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
  public void init()
  { 

  //<Begin_init> 
	if(getParameter("RESOURCE_PROPERTIES" ) != null)
	{
		localePropertiesFileName = getParameter("RESOURCE_PROPERTIES");
	}
	resourceBundle = com.adventnet.apiutils.Utility.getBundle(localePropertiesFileName,getParameter("RESOURCE_LOCALE"),applet);
        if (initialized) return; 
        setPreferredSize(new Dimension(getPreferredSize().width+684,getPreferredSize().height+413)); 
        setSize(getPreferredSize()); 
        Container container = this;
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
  }
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getActionCommand().equals("update"))
        {
            if(imageField.getText()!=null)
            {
                File f=new File(imageField.getText());
                if(f.exists())
                {
                    int index=JTable1.getSelectedRow();
                    if(index!=-1)
                    {
                        tableModel.setValueAt(imageField.getText(),index,1);
                    }
            
                    if(checkImage.isSelected())
                    {
                        setTransparentImage(imageField.getText());
                    }
                    else
                    {
                        String[] s=new String[2];
                        s[0]=severityField.getText();
                        s[1]=imageField.getText();
                        setNonTransParentImage(s);
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(this,resourceBundle.getString("Select an existing icon"),resourceBundle.getString("Message"),JOptionPane.INFORMATION_MESSAGE);
                }
            }
            
        }
            if(ae.getActionCommand().equals("browse"))
            {
                JFileChooser jfc=null;
                NarFileFilter ff = new NarFileFilter();
                ff.addExtension("png");
                ff.addExtension("jpg");
                ff.setDescription(resourceBundle.getString("Image files" ));
                int ret;
                jfc = new JFileChooser(System.getProperty("user.dir")+File.separator+"icons");   
                FilePreviewer previewer = new FilePreviewer(jfc);
                jfc.setAccessory(previewer);
                jfc.setDialogType(JFileChooser.CUSTOM_DIALOG);
                jfc.setDialogTitle(resourceBundle.getString("Select the Icon File"));
                jfc.setApproveButtonMnemonic('s');
                jfc.setFileFilter(ff);
                ret = jfc.showDialog(null,ToolsUtil.getMenuName(resourceBundle.getString("Select"),'s'));
                if ( ret == 0)
                {
                    File f_icon=jfc.getSelectedFile();
                    imageField.setText(f_icon.toString() );
                }
            }
        }
    public void mouseClicked(MouseEvent me)
    {
        if(me.getSource().equals(JTable1))
        {

            int rowindex=JTable1.getSelectedRow();
            if(rowindex!=-1)
            {
                if(tableModel.getValueAt(rowindex,0) != null)
                {
                    severityField.setText(tableModel.getValueAt(rowindex,0).toString());
                }
                else
                    severityField.setText("");
                if(tableModel.getValueAt(rowindex,1)!=null)
                    imageField.setText(tableModel.getValueAt(rowindex,1).toString());
                else
                    imageField.setText("");
            }
            else
            {
                imageField.setText("");
                severityField.setText("");
            }
        }
    }
    
     public void mouseEntered(MouseEvent me)
	{
	}
	public void mouseExited(MouseEvent me)
	{
	}
	public void mousePressed(MouseEvent me)
	{
	}
	public void mouseReleased(MouseEvent me)
	{
	}
    public void setListMenuInformations()
    {
        listInformation=new Vector();
        if(checkList.isSelected())
        {
            listInformation.add(menuNameField.getText());
            listInformation.add(itemNameField.getText());
            if(checkImage.isSelected())
            {
                listInformation.add("true");
                listInformation.add(transparentImage);
            }
            else
            {
                listInformation.add("false");
                listInformation.add(nonTransparentImage);
            }
        }
    }
    public Vector getListMenuInformations()
    {
        return listInformation;
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
    public boolean validateList()
    {
        if(checkList.isSelected())
        {
            
            
            if(menuNameField.getText()==null||menuNameField.getText().equals(""))
            {
                
                JOptionPane.showMessageDialog(null,resourceBundle.getString("Enter a menu name"),resourceBundle.getString("Message"),JOptionPane.INFORMATION_MESSAGE);
                return false;
                                         
            }
            else if(itemNameField.getText()==null||itemNameField.getText().equals(""))
            {
                JOptionPane.showMessageDialog(null,resourceBundle.getString("Enter a menu item "),resourceBundle.getString("Message"),JOptionPane.INFORMATION_MESSAGE);
                return false;
            }
            else if(checkImage.isSelected() && transparentImage.elementAt(1).toString().equals(""))
            {
                JOptionPane.showMessageDialog(null,resourceBundle.getString("Select icon for list"),resourceBundle.getString("Message"),JOptionPane.INFORMATION_MESSAGE);
                return false;
                                         
            }

        }
        return true;
    }


    class ImageRenderer implements TableCellRenderer
    {
        public Component getTableCellRendererComponent(JTable table,Object value,boolean isSelected,boolean hasFocus,int row,int column)
        {
            if(value!=null)
            {
                String image = "file:///" +value.toString();
                File f=new File(value.toString());
                if(f.exists() && (value.toString().endsWith("jpg")||value.toString().endsWith("png")))
                {
                    imageLabel.setIcon(new ImageIcon(ImgConv.getImage(image)));
                    imageLabel.setText(value.toString().substring(value.toString().lastIndexOf(File.separator)+1));
                    imageLabel.setFont(new Font("Dialog",0,12));
                }
                else
                {
                    imageLabel.setText("");
                    imageLabel.setIcon(null);
                }
                                    
            }
            else
            {
                 imageLabel.setText("");
                 imageLabel.setIcon(null);
            }
            return imageLabel;
        }
       
    }
 

 
  public void setProperties(java.util.Properties props)
  {
  //<Begin_setProperties_java.util.Properties> 

  
  //<End_setProperties_java.util.Properties>
  }
}




