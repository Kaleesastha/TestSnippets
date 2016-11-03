//$Id: MapListDetails.java,v 1.1.1.1 2006/12/08 11:57:39 gramkumar Exp $
package com.adventnet.nms.tools.mowizard;

import com.adventnet.nms.util.XMLDataReader;
import com.adventnet.nms.util.XMLNode;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.event.*;
import javax.swing.border.*;
import javax.swing.table.*;
import com.adventnet.nms.tools.utils.FilePreviewer;
import com.adventnet.nms.tools.utils.ImgConv;
import com.adventnet.nms.tools.utils.*;
import com.adventnet.nms.tools.objtorel.TransverseListener;
import com.adventnet.nms.tools.objtorel.TransverseContainer;
import org.w3c.dom.*;
import org.xml.sax.*;
import com.adventnet.apiutils.BuilderResourceBundle;
import com.adventnet.nms.tools.utils.ToolsUtil;

public class MapListDetails extends AbstractTransversePanel implements ItemListener,MouseListener,ActionListener,TransverseListener
{
		//<Begin_Variable_Declarations>
		private boolean initialized = false;
		private java.applet.Applet applet = null;
		javax.swing.JPanel  Top = null;
		javax.swing.JPanel  JPanel4 = null;
		javax.swing.JPanel  JPanel3 = null;
		javax.swing.JPanel  JPanel2 = null;
		javax.swing.JLabel  severity = null;
		javax.swing.JLabel  image = null;
		javax.swing.JTextField  severityField = null;
		javax.swing.JTextField  imageField = null;
		javax.swing.JButton  listIconBrowse = null;
		javax.swing.JButton  update = null;
		javax.swing.JCheckBox  checkImage = null;
		javax.swing.JScrollPane  JScrollPane1 = null;
		javax.swing.JTable  JTable1 = null;
		javax.swing.JPanel  panelMapIcon = null;
		javax.swing.JLabel  labelMapIconName = null;
		javax.swing.JTextField  textMapIconName = null;
		javax.swing.JButton  mapIconBrowse = null;
		GridBagConstraints cons = new GridBagConstraints();
		Insets inset;
		//<End_Variable_Declarations>
		CustomPropTableModel tableModel;
		JLabel imageLabel=null;
		TransverseContainer transCon=null;

		ImageRenderer renderer;
		Vector transparentImage;
		Vector  nonTransparentImage;
		Vector listInformation;
		Hashtable attribute;
		JFileChooser mapIconCh=null;
		JFileChooser listIconCh=null;
		boolean firstTime=true;
	private BuilderResourceBundle resourceBundle=ToolsUtil.getBundle();


		public void start()
		{ 

				//<Begin_start>

				//<End_start>
		} 
		public void initVariables()throws Exception
		{ 

				//<Begin_initVariables>
				Top= new javax.swing.JPanel();
				JPanel4= new javax.swing.JPanel();
				JPanel3= new javax.swing.JPanel();
				JPanel2= new javax.swing.JPanel();
				severity= new javax.swing.JLabel();
				image= new javax.swing.JLabel();
				severityField= new javax.swing.JTextField();
				imageField= new javax.swing.JTextField();
				listIconBrowse= new javax.swing.JButton();
				update= new javax.swing.JButton();
				checkImage= new javax.swing.JCheckBox();
				JScrollPane1= new javax.swing.JScrollPane();
				JTable1= new javax.swing.JTable();
				panelMapIcon= new javax.swing.JPanel();
				labelMapIconName= new javax.swing.JLabel();
				textMapIconName= new javax.swing.JTextField();
				mapIconBrowse= new javax.swing.JButton();

				//<End_initVariables>
				severityField.setEditable(false);
				Object tableHdr[] = {resourceBundle.getString("ImageCategory"),resourceBundle.getString("ImagePreview") };
				imageLabel=new JLabel();
				tableModel=new CustomPropTableModel(tableHdr,0);
				update.addActionListener(this);
				update.setActionCommand("update");
				renderer=new ImageRenderer();

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
		public void setUpToolBar()
		{ 

				//<Begin_setUpToolBar>

				//<End_setUpToolBar>
		} 
		public void setUpGUI(Container container)throws Exception
		{ 

				//<Begin_setUpGUI_Container>
				container.add(Top,BorderLayout.CENTER);
				Top.setLayout(new BorderLayout(5,5));
				Top.add(JPanel4,BorderLayout.CENTER);
				JPanel4.setLayout(new BorderLayout(5,5));
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
				JPanel2.add(listIconBrowse,cons);
				inset = new Insets(2,2,2,2);
				setConstraints(1,2,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
				JPanel2.add(update,cons);
				JPanel3.add(checkImage,BorderLayout.NORTH);
				JPanel3.add(JScrollPane1,BorderLayout.CENTER);
				JScrollPane1.getViewport().add(JTable1);
				JPanel4.add(panelMapIcon,BorderLayout.NORTH);
				panelMapIcon.setLayout(new GridBagLayout());
				inset = new Insets(0,0,0,0);
				setConstraints(0,0,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
				panelMapIcon.add(labelMapIconName,cons);
				inset = new Insets(0,0,0,0);
				setConstraints(1,0,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
				panelMapIcon.add(textMapIconName,cons);
				inset = new Insets(0,0,0,0);
				setConstraints(2,0,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
				panelMapIcon.add(mapIconBrowse,cons);

				//<End_setUpGUI_Container>
				JTable1.setModel(tableModel);
				JTable1.setSelectionMode(0);
				JTable1.getColumnModel().getColumn(1).setCellRenderer(renderer);
				JTable1.addMouseListener(this);
				checkImage.addItemListener(this);
				setTransparentImage("");

		} 
		public void setUpProperties()throws Exception
		{ 

				//<Begin_setUpProperties>

				try
				{
						Top.setBorder(new javax.swing.border.TitledBorder(new javax.swing.border.LineBorder(new Color(-16777216),1),resourceBundle.getString("List Menu Information"),2,0,new Font("Dialog",0,12),new Color(-10066279)));
				}
				catch(Exception ex)
				{
						showStatus(resourceBundle.getString("Exception while setting properties for bean ")+Top,ex); 
				}

				try
				{
						JPanel4.setBorder(new javax.swing.border.BevelBorder(0));
				}
				catch(Exception ex)
				{
						showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel4,ex); 
				}

				try
				{
						JPanel3.setBorder(new javax.swing.border.TitledBorder(new javax.swing.border.EtchedBorder(0),resourceBundle.getString("ListIcon Details"),1,2,new Font("Dialog",0,12),new Color(-16764109)));
				}
				catch(Exception ex)
				{
						showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel3,ex); 
				}

				try
				{
						severity.setText(resourceBundle.getString("Severity"));
						severity.setFont(new Font("Dialog",0,12));
						severity.setForeground(new Color(-16764109));
				}
				catch(Exception ex)
				{
						showStatus(resourceBundle.getString("Exception while setting properties for bean ")+severity,ex); 
				}

				try
				{
						image.setText(resourceBundle.getString("Image Name"));
						image.setFont(new Font("Dialog",0,12));
						image.setForeground(new Color(-16764109));
				}
				catch(Exception ex)
				{
						showStatus(resourceBundle.getString("Exception while setting properties for bean ")+image,ex); 
				}

				try
				{
						listIconBrowse.setText("...");
				}
				catch(Exception ex)
				{
						showStatus(resourceBundle.getString("Exception while setting properties for bean ")+listIconBrowse,ex); 
				}

				try
				{
						update.setText(resourceBundle.getString("Update"));
						update.setFont(new Font("Dialog",0,12));
				}
				catch(Exception ex)
				{
						showStatus(resourceBundle.getString("Exception while setting properties for bean ")+update,ex); 
				}

				try
				{
						checkImage.setText(resourceBundle.getString("Use Transparaent Image"));
						checkImage.setFont(new Font("Dialog",0,12));
				}
				catch(Exception ex)
				{
						showStatus(resourceBundle.getString("Exception while setting properties for bean ")+checkImage,ex); 
				}

				try
				{
						panelMapIcon.setBorder(new javax.swing.border.TitledBorder(new javax.swing.border.LineBorder(new Color(-16777216),1),resourceBundle.getString("Map Icon Details"),1,2,new Font("Dialog",0,12),new Color(-16764109)));
				}
				catch(Exception ex)
				{
						showStatus(resourceBundle.getString("Exception while setting properties for bean ")+panelMapIcon,ex); 
				}

				try
				{
						labelMapIconName.setFont(new Font("Dialog",0,12));
						labelMapIconName.setForeground(new Color(-16764109));
						labelMapIconName.setText(resourceBundle.getString("Icon Name"));
				}
				catch(Exception ex)
				{
						showStatus(resourceBundle.getString("Exception while setting properties for bean ")+labelMapIconName,ex); 
				}

				try
				{
						mapIconBrowse.setFont(new Font("Dialog",0,12));
						mapIconBrowse.setForeground(new Color(-16764109));
						mapIconBrowse.setText(resourceBundle.getString("Browse"));
				}
				catch(Exception ex)
				{
						showStatus(resourceBundle.getString("Exception while setting properties for bean ")+mapIconBrowse,ex); 
				}
				/*
					 checkImage.setPreferredSize(new Dimension(checkImage.getPreferredSize().width+0,checkImage.getPreferredSize().height+4));
					 JPanel3.setPreferredSize(new Dimension(JPanel3.getPreferredSize().width+10,JPanel3.getPreferredSize().height+13));
					 JPanel4.setPreferredSize(new Dimension(JPanel4.getPreferredSize().width+10,JPanel4.getPreferredSize().height+67));
				 */

				//<End_setUpProperties>
				listIconBrowse.addActionListener(this);
				mapIconBrowse.addActionListener(this);

		} 


		public void itemStateChanged(ItemEvent ie)
		{
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
						JTable1.updateUI();
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


		public  void setImage(String[] s)
		{
				Vector v=getSeverity();
				File f=new File(System.getProperty("user.dir")+File.separator+"conf"+File.separator+"listIcon.data");
				String type="";
				if(attribute!=null)
				{
						type=attribute.get("DEVICE_TYPE").toString();
				}
				// Assign type here. 
				Document doc=(Document)transCon.getTransverseComponent("XMLMODEL");
				Element deviceNode=(Element)doc.getElementsByTagName("DEVICE_PARAMS").item(0);
				type=deviceNode.getAttribute("devType");

				XMLDataReader read=new XMLDataReader(f.toString(),false);
				XMLNode root=read.getRootNode();
				Vector severityImage=new Vector();
				Vector childs=root.getChildNodes();
				boolean typeFound=false;
				for(int i=0;i< childs.size();i++)
				{
						XMLNode node=(XMLNode)childs.elementAt(i);
						if(node.getNodeType()==XMLNode.ELEMENT)
						{
								if(node.getAttribute("TYPE").toString().equalsIgnoreCase(type))
								{
									typeFound=true;
									for(int j=0;j<v.size();j++)
									{
										Vector image=new Vector();
												image.add(v.elementAt(j));
												if(node.getAttribute(v.elementAt(j).toString().toUpperCase()+"_IMG")!=null)
												{
														image.add(System.getProperty("user.dir")+File.separator+"icons"+File.separator+node.getAttribute(v.elementAt(j).toString().toUpperCase()+"_IMG"));
												}

												severityImage.add(image);
										}
										break;
								}
						}
				}
				if(!typeFound) {
					for(int j=0;j<v.size();j++) {
						Vector image =new Vector();
						image.add(v.elementAt(j));
						image.add(resourceBundle.getString("Not Defined"));
						severityImage.add(image);
					}	
				}	
				nonTransparentImage=severityImage;
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

		public void setUpConnections()throws Exception
		{ 

				//<Begin_setUpConnections>

				//<End_setUpConnections>
		} 
		public void stop()
		{ 

				//<Begin_stop>

				//<End_stop>
		} 
		public void setUpMenus()
		{ 

				//<Begin_setUpMenus>

				//<End_setUpMenus>
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
				if (initialized == true) return; 
				/*
					 setPreferredSize(new Dimension(getPreferredSize().width+557,getPreferredSize().height+552)); 
					 setSize(getPreferredSize()); 
				 */
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
						showStatus("Error in init method",ex); 
				} 
				// let us set the initialzed variable to true so  
				// we dont initialize again even if init is called 
				initialized = true; 
				setUpMenus();
				setUpToolBar();


				//<End_init>
		}

		private boolean isImageDuplicated(String imageName) {
				String str=null;
				for(int i=0;i<JTable1.getRowCount();i++) {
						str=(String)JTable1.getValueAt(i,1);
						if(str==null || str.trim().length()==0) {
								continue;
						}
						str=(new File(str)).getName();
						if(str.toLowerCase().trim().equals((new File(imageName.toLowerCase().trim())).getName())) {
								JOptionPane.showMessageDialog(null,resourceBundle.getString("Sorry !!! Image Name is already found "),resourceBundle.getString("Error"),JOptionPane.ERROR_MESSAGE);
								return true;
						}
				}
				return false;
		}


		public void actionPerformed(ActionEvent ae)
		{
				if(ae.getActionCommand().equals("update"))
				{
						if(imageField.getText()!=null)
						{
								int index=JTable1.getSelectedRow();
								if(index!=-1)
								{
										if(!isImageDuplicated(imageField.getText())) {
												tableModel.setValueAt(imageField.getText(),index,1);
												JTable1.updateUI();
										}
								}
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
				if(ae.getSource()==listIconBrowse)
				{
						int ret=0;
						if(listIconCh==null) {
								ToolsFileFilter ff = new  ToolsFileFilter();
								ff.addExtension("png");
								ff.addExtension("jpg");
								ff.setDescription("Image files" );
								listIconCh = new JFileChooser(System.getProperty("user.dir")+File.separator+"icons");   // new
								FilePreviewer previewer = new FilePreviewer(listIconCh);
								previewer.setBorder(new TitledBorder(new LineBorder(Color.black,1),"Preview",1,2,new Font("Dialog",0,12),Color.black));
								listIconCh.setAccessory(previewer);
								listIconCh.setDialogType(JFileChooser.CUSTOM_DIALOG);
								listIconCh.setDialogTitle("Select the Icon File");
								listIconCh.setApproveButtonMnemonic('s');
								listIconCh.setFileFilter(ff);
						}
						ret = listIconCh.showDialog(null,"Select");
						if (ret == 0)
						{
								File f_icon=listIconCh.getSelectedFile();
								imageField.setText(f_icon.toString() );
						}
				}
				if(ae.getSource()==mapIconBrowse) {
						int retval=-1;
						if(mapIconCh==null) {
								mapIconCh=new JFileChooser();
								ToolsFileFilter tff=new ToolsFileFilter();
								tff.addExtension("jpg");
								tff.addExtension("png");
								tff.setDescription(resourceBundle.getString("Image Files"));
								mapIconCh.setCurrentDirectory(new File("."+File.separator+"images"));	
								FilePreviewer mapPreview=new FilePreviewer(mapIconCh);
								mapPreview.setBorder(new TitledBorder(new LineBorder(Color.black,1),resourceBundle.getString("Preview"),1,2,new Font("Dialog",0,12),Color.black));
								mapIconCh.setAccessory(mapPreview);
								mapIconCh.setDialogType(JFileChooser.CUSTOM_DIALOG);
								mapIconCh.setDialogTitle(resourceBundle.getString("Icon for Map Representation"));
								mapIconCh.setApproveButtonMnemonic('s');
								mapIconCh.setFileFilter(tff);
						}
						retval=mapIconCh.showDialog(null,resourceBundle.getString("Select"));
						if(retval==0) {
								textMapIconName.setText(mapIconCh.getSelectedFile().toString());
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

		public MapListDetails()
		{
				//<Begin_MapListDetails>
				this.init();

				//<End_MapListDetails>
		}

		public MapListDetails(java.applet.Applet applet)
		{
				//<Begin_MapListDetails_java.applet.Applet>
				this.applet = applet;
				this.init();

				//<End_MapListDetails_java.applet.Applet>
		}

		private void initializeDataStructures() {
				transCon.removeTransverseComponent("transparentImage");
				transCon.removeTransverseComponent("SEVERITY_LEVELS");
				transCon.removeTransverseComponent("SEVERITY_IMAGES");
				transCon.removeTransverseComponent("MAPICONNAME");
		}

		public int nextActionPerformed(String str)
		{
				// validate for all fields.  
				initializeDataStructures();
				if(textMapIconName.getText().trim().length()==0) {
						JOptionPane.showMessageDialog(null,resourceBundle.getString("Please enter mapIcon Name"),resourceBundle.getString("Error"),JOptionPane.ERROR_MESSAGE);
						return -1;
				}	
				transCon.addTransverseComponent("MAPICONNAME",textMapIconName.getText());
				transCon.addTransverseComponent("transparentImage",String.valueOf(checkImage.isSelected()));
				if(JTable1.getRowCount()!=0) {
						transCon.addTransverseComponent("SEVERITY_LEVELS",String.valueOf(JTable1.getRowCount()));
						String imageNames[]=new String[JTable1.getRowCount()];
						String severityNames[]=new String[JTable1.getRowCount()];
						for(int i=0;i<JTable1.getRowCount();i++) {
								if(JTable1.getValueAt(i,1).toString().trim().length()==0 || JTable1.getValueAt(i,1).toString().trim().equals("Not Defined")) {
										JOptionPane.showMessageDialog(null,java.text.MessageFormat.format(resourceBundle.getString("Sorry !!! you have missed to add an image Name for {0}"),new String[]{JTable1.getValueAt(i,0).toString()}),resourceBundle.getString("Error"),JOptionPane.ERROR_MESSAGE);
										initializeDataStructures();
										return -1;
								}
								imageNames[i]=JTable1.getValueAt(i,1).toString();
								severityNames[i]=JTable1.getValueAt(i,0).toString();
						}
						transCon.addTransverseComponent("SEVERITY_IMAGES",imageNames);
						transCon.addTransverseComponent("SEVERITY_NAMES",severityNames);
				}
				return 1;
		}

		public int previousActionPerformed(String str)
		{
				return 0;
		}

		public boolean finishActionPerformed()
		{
				return false;
		}

		public String getNmsHome() {
				return "."+File.separator;
		}

		public void cancelActionPerformed(String str)
		{
				if(str.trim().equals("Screen3")) {
						int returnVal=JOptionPane.showConfirmDialog(null,resourceBundle.getString("ManagedObject Deployment is not complete. Do you want to cancel ???"),resourceBundle.getString("Confirm"),JOptionPane.YES_NO_OPTION);
						if(returnVal==JOptionPane.YES_OPTION) {
								((JDialog)getParentContainer(this)).dispose();
						}
				}
		}
		public void closeActionPerformed()
		{ }

		public Container getParentContainer(Container con){
				while(!(con instanceof JDialog)){
						con=con.getParent();
				}
				return con;
		}

		public void loadScreenData() {
				if(firstTime) {
						String s1[]=new String[1];
						setImage(s1);
						checkImage.setSelected(false);
						firstTime=false;
				}
		}

		public void addTransverseContainer(TransverseContainer tCon) {
				transCon=tCon;	
		}

}
