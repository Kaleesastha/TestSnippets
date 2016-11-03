//$Id: AddNewMap.java,v 1.2 2007/02/22 15:02:57 srajeswari Exp $
package com.adventnet.nms.example;

//util imports 
import java.util.*;

//awt imports 
import java.awt.*;
import java.awt.GridLayout;
import java.awt.event.*;

//swing imports
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;
import javax.swing.text.*;
import javax.swing.JDialog;

//WebNMS imports
import com.adventnet.nms.util.*;
import com.adventnet.nms.startclient.*;
import com.adventnet.nms.mapui.*;
import com.adventnet.nms.mapui.MapApplet;

/** 
 * This is an example class invoked in the map module to Add a New Map.The
 * class extends the abstract class com.adventnet.nms.util.DataFormCustomizer
 * and overrides the methods getCustomizer(),setFormProperties() and 
 * getFormProperties() in it.  
 *
 *  <p>The Web NMS on instantiating this class registers itself as a PropertyChangeListener
 * to this class and calls the setFormProperties() method on this class passing 
 * a Vector of FormObjects as a parameter. The class using this,constructs 
 * a Dialog. Then the Web NMS subsequently calls the getCustomizer() method
 * to get the Dialog UI and displays it. 
 * 
 * <p>After the user had finished entering the criterias to add a new map 
 * and presses the 'AddMap' button then following action performed
 *		 Calling the firePropertyChange() method which will intimate the Web NMS
 *		 that the customizer class has completed its function. The Web NMS
 *		 on receiving this intimation will call the getFormProperties()
 *		 method to get the modified FormObjects vector and then proceeds
 *	     to add a new map. 
 */


public class AddNewMap extends DataFormCustomizer implements ActionListener
{
	
	//dialogs for holding UI components
	private JDialog dialog;					
	private JDialog selectedMoreDialog;
	private JDialog moreDialog;
	
	//for hold the formObjects
	private Vector objects;                     
	
	private Vector valueList = null;
	
	private Vector addNewMapUpdateVec       = new Vector();//for updating the addnewmap components value 
	private Vector addNewMapComponentVec    = new Vector();//for hold the addnewmap components 
	
	//for selectedMoreDialog dialog
	private Vector selectedUpdateVec         = null;//for update the values
	private Vector selectedComponentVec      = null;//for components
	private Vector selectedCheckComponentVec = null;//for checkbox components
	
	private GridBagLayout       gbLayout    = new GridBagLayout();
	private GridBagConstraints  constraints = new GridBagConstraints();;
	
	private Font font                       = NmsClientUtil.getFont();
	
	private int mapMoreCount  = 1;
	private int mapMore_y     = 200;
	
	private boolean setForSelectedCriterias = false;
	private boolean setDialog = false;
	
	private Vector mapMoreComponentVec   = new Vector();
	private Properties mapMoreProperties;
	
   /** 
	 * This method overrides its corresponding couterpart in com.adventnet.nms.util.DataFormCustomizer. 
	 * Web NMS calls this method after this class fires a 
	 * firePropertyChange() method to Web NMS. On receiving the
	 * firePropertyChange() intimation Web NMS calls this 
	 * method to get the Vector of FormObjects which will contain
	 * the criteria for the new map to be Added and and 
	 * proceeds to Add it.
	 * 
	 * @return "Vector"   Returns a Vector of FormObjects which gives the properties
	 *                    of the new map updated by the user.
	 */
	
	public Vector getFormProperties()
	{		
		return objects;
	}
	
	/**  
	 * This method overrides the method in com.adventnet.nms.util.DataFormCustomizer 
	 * and is used for initialisation
	 * 
	 * @param formObjects  a Vector containing FormObjects of map properties.
	 */
	
	public void setFormProperties(Vector formObjects)
	{
		objects = formObjects;
	}
	
	/**  
	 * This method overrides the method in com.adventnet.nms.util.DataFormCustomizer. 
	 * This method is called by Web NMS after passing FormObjects through setFormProperties()
	 * and gets back a mainDialog containing UI
	 * 
	 * @return "Window"  Returns a Window that contains UI
	 */
	
	public Window getCustomizer()
	{
		return init();	
	}
	

	
	/** 
	 * init() method constructs the UI that contain property name of the FormObject
	 * and it's value as it's columns. Value is displayed  either in textfield or textarea or 
	 * combobox or checkBox upon it's nature.
	 * 
	 * @return "Window"    a Window
	 */
	
	private Window init()
	{
		JFrame parentFrame;//for parent frame
		
		parentFrame = getParentFrame();
		dialog      = getAddNewMapForm(parentFrame);
		
		return dialog;
	}
	/**
	 * This method extracts the property names from the received formobjects vector and 
	 * presented as the UI components in the dialog.
	 * 
	 * @param "parentFrame"  JFrame for dialog's parentframe.
	 * 
	 * @return "JDialog"     returns a dialog that contains the UI components.
	 */
	
	private JDialog getAddNewMapForm(JFrame parentFrame)
	{
		int size = 0;
		boolean editable = true;
		FormObject formObject;
		
		JDialog addNewMapDialog = new JDialog(parentFrame,NmsClientUtil.GetString("Add New Map Form"),false);
		
		//borders for panels
		TitledBorder namePanelBorder   = BorderFactory.createTitledBorder("");
		TitledBorder otherPanelBorder  = BorderFactory.createTitledBorder("");
		
		JPanel namePanel        = new JPanel(gbLayout);//for hold the name type properties in the panel
		JPanel otherPanel       = new JPanel(gbLayout);//for hold other than name type properties in the panel 
		
		//panels for combining 
		JPanel combiningPanel1  = new JPanel(new GridLayout(2,1));
		JPanel combiningPanel2  = new JPanel(new BorderLayout());
		
		//panel for hold the save changes check box
		JPanel saveChanges      = new JPanel(new FlowLayout(FlowLayout.CENTER ));
		//panel for hold the buttons
		JPanel buttonPanel		= new JPanel(new FlowLayout(FlowLayout.CENTER,20,5));	
		
		//more button
		JButton butMore         = new JButton(NmsClientUtil.GetString("More.."));
		butMore.setActionCommand("butMore");
		
		//addmap button
		JButton butAddMap         = new JButton(NmsClientUtil.GetString("Add Map"));//modified by rameshp
		butAddMap.setActionCommand("butAddMap");
		//cancel button
		JButton butCancel         = new JButton(NmsClientUtil.GetString("Cancel")); 
		butCancel.setActionCommand("butCancel");
		
		//help button
		JButton help = new JButton(NmsClientUtil.GetString("Help"));
		help.setActionCommand("help");
		help.addActionListener(this);
		
		//scrollpane for the panel
		JScrollPane scrollPane ;
		
		//array for ordering the display of add new map form
		String  namearrayformap[] = {"name","label","objname","menuname","imagename",
									 "groupname","webnms","maplinkrenderer","mapsymbolrenderer",
									 "topology","anchored","autoplacement","helpdoc"};
		
		
		int namearrayformaplen = namearrayformap.length;	//for above array length
		int incrementformap    = 0;//for increment the array position
		int sizeformap         = 0;//for components position in the panel
		
		//loop for display the components according to the sequence present in the array
		for(size = 0; size < objects.size(); size++)
		{
			formObject       = (FormObject)objects.elementAt(size);
			String propName  = (String)formObject.getPropertyName();//for property name
			String propValue =  formObject.getCurrentValue().toString();//for property value
			
			propName  = propName.trim();
			propValue = propValue.trim();
			
			//property name should be match with the any one of the element in the array
			//then only goes inside
			if(propName.equalsIgnoreCase(namearrayformap[incrementformap]))
			{
				
				if(propName.equalsIgnoreCase("name")    ||
				   propName.equalsIgnoreCase("label")   ||
				   propName.equalsIgnoreCase("objname"))
				{
					formObject = new FormObject(propName,valueList,editable,propValue);
					addNewMapUpdateVec.addElement(formObject);
					displayFormObject(formObject,namePanel,sizeformap);	
				}
				else if(propName.equalsIgnoreCase("menuname")	   ||
						propName.equalsIgnoreCase("imagename")     ||
						propName.equalsIgnoreCase("webnms")        ||
						propName.equalsIgnoreCase("groupname")  ||
						propName.equalsIgnoreCase("index"))
				{
					formObject = new FormObject(propName,valueList,editable,propValue);
					addNewMapUpdateVec.addElement(formObject);
					displayFormObject(formObject,namePanel,sizeformap);	
				}
				else if(propName.equalsIgnoreCase("topology"))
				{
					valueList = MapApplet.getMapLayouts();
					formObject      = new FormObject (propName, valueList, editable,propValue);
					addNewMapUpdateVec.addElement(formObject);
					displayFormObject(formObject,otherPanel,sizeformap);
				}
				else if(propName.equalsIgnoreCase("anchored")  ||
						propName.equalsIgnoreCase("autoplacement"))
				{
					valueList = new Vector(10,2);
					valueList.addElement ("true");
					valueList.addElement ("false");
					formObject      = new FormObject (propName, valueList, editable, propValue);
					addNewMapUpdateVec.addElement(formObject);
					displayFormObject(formObject,otherPanel,sizeformap);
				}
				else if(propName.equalsIgnoreCase("helpdoc")) 
				{
					formObject = new FormObject(propName,valueList,editable,propValue);
					addNewMapUpdateVec.addElement(formObject);
					displayFormObject(formObject,otherPanel,sizeformap);
				}
				
				else if(propName.equalsIgnoreCase("maplinkrenderer")       ||
						propName.equalsIgnoreCase("mapsymbolrenderer")	   ||
						propName.equalsIgnoreCase("treeiconfilename")      ||
						propName.equalsIgnoreCase("classname")) 
				{
					formObject = new FormObject(propName,valueList,editable,propValue);
					addNewMapUpdateVec.addElement(formObject);
					displayFormObject(formObject,otherPanel,sizeformap);
				}
				
				valueList = null;
				incrementformap++;
				sizeformap++;
				//check for the array index
				if(incrementformap == namearrayformaplen)
				{
					break;
				}
				else
				{
					size = -1;
				}
				
			}
			else
			{
				if(size == (objects.size()-1))
				{
					if(incrementformap < (namearrayformaplen-1))
					{
						size = -1;
						incrementformap++;
					}
				}
			}
		}
		
		namePanel.setBorder(namePanelBorder);
		otherPanel.setBorder(otherPanelBorder);
		
		saveChanges.add(butMore);
		
		//listeners are added for the buttons
		butMore.addActionListener(this);
		butAddMap.addActionListener(this);
		butCancel.addActionListener(this);
		
		//buttons are added in the panel
		buttonPanel.add(butAddMap);
		buttonPanel.add(butCancel);
		buttonPanel.add(help);
		
		//panels are combined
		combiningPanel1.add(namePanel);
		combiningPanel1.add(otherPanel);
		
		combiningPanel2.add("Center",combiningPanel1);
		combiningPanel2.add("South",saveChanges);
		
		//scrollpane added for the final panel
		scrollPane = new JScrollPane(combiningPanel2);
		
		//final panel with scrollpane added in the dialog
		Container c = addNewMapDialog.getContentPane();
		c.setLayout(new BorderLayout());
		c.add(scrollPane,BorderLayout.CENTER);
		c.add(buttonPanel,BorderLayout.SOUTH);
		
		addNewMapDialog.setSize(450,575);//set the size for the dialog
			
		addNewMapDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		return (addNewMapDialog);
	}
	/**
	 * 
	 * All button & checkbox actions are handled here.
	 * If the user had clicked the addMap button , new map will be added. 
	 * 
	 */
	
	public  void actionPerformed(ActionEvent ae)
	{
		String action = ae.getActionCommand();
		
		if(action.equalsIgnoreCase("butAddMap"))
		{
			//update the modified values and fire the propertychangelistener
			updateFormValues(addNewMapUpdateVec,addNewMapComponentVec);
			firePropertyChange();
			dialog.dispose();//dispose the dialog
			dialog.setVisible(false);
		}
		else if (action.equalsIgnoreCase("butcancel"))
		{
			//dispose the dialog 
			dialog.dispose();
			dialog.setVisible(false);
		}
		else if (action.equalsIgnoreCase("help"))
		{
			//open the help url
			String helpURL = "../help/User_Guide/Java_UI/userguide_introduction.html";
			NmsClientUtil.showHelp(helpURL);
			//dialog.dispose();
			//dialog.setVisible(false);
		}
		else if(action.equalsIgnoreCase("butmore"))
		{
			if(!setForSelectedCriterias)
			{
				setForSelectedCriterias = true;
				getSelectedMoreDialog();
			}
		}
		else if(action.equalsIgnoreCase("selectbutmore"))
		{
			if(!setDialog)
			{
				setDialog = true;
				moreDialog = new JDialog(selectedMoreDialog,NmsClientUtil.GetString("Please fill the map criterias"),false);//modified by rameshp
				getMoreDialog();
				moreDialog.setSize(500,mapMore_y);
				moreDialog.repaint();
				moreDialog.validate();
				moreDialog.setVisible(true);
			}
			
		}
		else if(action.equalsIgnoreCase("selectbutok"))
		{
			if(setForSelectedCriterias)
			{
				String propName;
				String propValue;
				FormObject formObject;
				Object obj;
				JTextField tempField;
				JComboBox  tempBox;
				if(mapMoreProperties == null)
				{
					mapMoreProperties = new Properties();
				}
				for(int size = 0; size < selectedUpdateVec.size(); size++)
				{
					formObject = (FormObject)selectedUpdateVec.elementAt(size);
					obj = selectedComponentVec.elementAt(size);
					if(obj instanceof JTextField)
					{
						tempField = (JTextField)obj;
						if(tempField.isEnabled())
						{
							propName = formObject.getPropertyName();
							propValue = tempField.getText();
							mapMoreProperties.put(propName,propValue);
						}
					}
					else if(obj instanceof JComboBox)
					{
						tempBox = (JComboBox)obj;
						if(tempBox.isEnabled())
						{
							propName = formObject.getPropertyName();
							if(propName.equalsIgnoreCase("ismanaged"))
							{
								propName = "managed";
							}
							propValue = (String)tempBox.getSelectedItem();
							mapMoreProperties.put(propName,propValue);
						}
					}
				}
				setForSelectedCriterias = false;
				selectedMoreDialog.dispose();
				selectedMoreDialog.setVisible(false);
			}
		}
		else if(action.equalsIgnoreCase("selectbutcancel"))
		{
			if(setForSelectedCriterias)
			{
				setForSelectedCriterias = false;
				selectedMoreDialog.dispose();
				selectedMoreDialog.setVisible(false);
			}
		}
		else if (action.equalsIgnoreCase("more"))
		{
			JTextField textfield;
			String text;
			int vecSize = mapMoreComponentVec.size();
			
			if(vecSize >= 2)
			{
				textfield = (JTextField)mapMoreComponentVec.elementAt(vecSize-1);
				text = textfield.getText().trim();
				
				if(text.length() != 0)
				{
					textfield = (JTextField)mapMoreComponentVec.elementAt(vecSize-2);
					text = textfield.getText();
					
					if(text.length() != 0)
					{											
						mapMoreCount++;
						mapMore_y = mapMore_y + 50;
						
						moreDialog.getContentPane().removeAll();
						moreDialog.getContentPane().repaint();
						
						moreDialog.setSize(500,mapMore_y);
						moreDialog.repaint();
						moreDialog.validate();
						
						getMoreDialog();
						
						moreDialog.getContentPane().validate();				
						
					}
					
				}
				else
				{
					JOptionPane.showMessageDialog(moreDialog,NmsClientUtil.GetString("Please fill the map criterias"),NmsClientUtil.GetString("Please fill the map criterias"),JOptionPane.INFORMATION_MESSAGE);//modified by rameshp - internationalization fix
				}
			}
			
		}
		else if (action.equalsIgnoreCase("ok"))
		{
			if(setDialog)
			{
				JTextField textfield,tempfield;
				String text;
				int vecSize = mapMoreComponentVec.size();
				
				if(vecSize >= 2)
				{
					textfield = (JTextField)mapMoreComponentVec.elementAt(vecSize-1);
					text = textfield.getText().trim();
					
					if(text.length() != 0)
					{
						textfield = (JTextField)mapMoreComponentVec.elementAt(vecSize-2);
						text = textfield.getText();
						
						if(text.length() != 0)
						{											
							
							for(int count = 0; count<mapMoreComponentVec.size(); count=(count+2))
							{
								String propName;
								String propValue;
								
								mapMoreProperties = new Properties();
								valueList = null;
								
								tempfield = (JTextField)mapMoreComponentVec.elementAt(count);
								propName  = tempfield.getText();
								tempfield = (JTextField)mapMoreComponentVec.elementAt(count+1);
								propValue = tempfield.getText();
								
								mapMoreProperties.put(propName,propValue);
								
							}
							
							mapMoreComponentVec = new Vector();
							mapMoreCount  = 1;
							mapMore_y     = 200;
							
							moreDialog.dispose();
							moreDialog.setVisible(false);
							setDialog = false;
						}
					}
					else
					{
						JOptionPane.showMessageDialog(moreDialog,NmsClientUtil.GetString("Please fill the map criterias"),NmsClientUtil.GetString("Please fill the map criterias"),JOptionPane.INFORMATION_MESSAGE);//modified by rameshp - internationalization fix
					}
					
				}
			}
			
		}
		else if(action.equalsIgnoreCase("cancel"))
		{
			if(setDialog)
			{
				mapMoreCount  = 1;
				mapMore_y     = 200;
				mapMoreComponentVec = new Vector();
				moreDialog.dispose();
				moreDialog.setVisible(false);
				setDialog = false;
			}
		}
		
		else if(action.startsWith("checkbox"))
		{
			JCheckBox tempCheckBox;
			JTextField tempField;
			JComboBox tempComboBox;
			int componentsize = -1;
			boolean state = false;
			String text = action.substring(8);
			for(int size=0; size<selectedCheckComponentVec.size(); size++)
			{
				tempCheckBox = (JCheckBox)selectedCheckComponentVec.elementAt(size);
				if((tempCheckBox.getText()).equalsIgnoreCase(text))
				{
					componentsize = size;
					state = tempCheckBox.isSelected();
					break;
				}
			}
			if(componentsize != -1)
			{
				Object obj;
				obj = selectedComponentVec.elementAt(componentsize);
				if(obj instanceof JTextField)
				{
					tempField = (JTextField)obj;
					if(state)
					{
						tempField.setEnabled(true);
					}
					else
					{
						tempField.setEnabled(false);
					}
				}
				else if(obj instanceof JComboBox)
				{
					tempComboBox = (JComboBox)obj;
					if(state)
					{
						tempComboBox.setEnabled(true);
					}
					else
					{
						tempComboBox.setEnabled(false);
					}
				}
				
			}
			
		}
	}
	
	/**
	 * This method construct a UI for selected map criteria
	 */
	private void getSelectedMoreDialog()
	{
		mapMoreProperties = null;
		selectedUpdateVec = new Vector();
		selectedComponentVec = new Vector();
		selectedCheckComponentVec = new Vector();
		
		//for display the UI component
		JPanel selectedMorePanel = new JPanel(gbLayout);
		//for hold the all panels
		JPanel parentPanel = new JPanel(new BorderLayout());
		
		//for hold the more button
		JPanel selectMorePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		//panel for hold buttons
		JPanel selectButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,20,5));
		//more button
		JButton selectButMore = new JButton(NmsClientUtil.GetString("More.."));
		selectButMore.setActionCommand("selectbutmore");
		//ok button 
		JButton selectButOk = new JButton(NmsClientUtil.GetString("Ok"));
		selectButOk.setActionCommand("selectbutok");
		//cancel button
		JButton selectButCancel = new JButton(NmsClientUtil.GetString("Cancel"));
		selectButCancel.setActionCommand("selectbutcancel");	
		//dialog for hold the parentpanel 
		selectedMoreDialog = new JDialog(dialog,NmsClientUtil.GetString("Selected map criterias"),false); 
		
		// names are ordered for display
		String selectedCriterias[] = {"type","status","ipAddress","netmask","baseMIBs","community",
									  "writeCommunity","isNetwork","isRouter","isInterface","isNode",
									  "isSNMP","isDHCP","managed"};
		
		//this loop for select the formobject for the ordered properties name
		for(int size=0; size<selectedCriterias.length; size++)
		{
			FormObject formObject;
			String propName = selectedCriterias[size];
			String propValue = "";
			boolean editable = false;
			
			if(propName.equalsIgnoreCase("isnetwork")   ||
			   propName.equalsIgnoreCase("isrouter")    ||
			   propName.equalsIgnoreCase("isinterface") ||
			   propName.equalsIgnoreCase("isnode")      ||
			   propName.equalsIgnoreCase("issnmp")      ||
			   propName.equalsIgnoreCase("isdhcp")      ||
			   propName.equalsIgnoreCase("managed") 
			   )
			{
				if(propName.equalsIgnoreCase("managed"))
				{
					propName = "isManaged";
				}
				valueList = new Vector();
				
				propValue = "false";
				valueList.addElement("true");
				valueList.addElement("false");
			}
			else 
			{
				valueList = null;
			}
			
			formObject = new FormObject(propName,valueList,editable,propValue);
			displayFormObject(formObject,selectedMorePanel,size);
			selectedUpdateVec.addElement(formObject);
			valueList = null;
		}
		
		//listeners are added for the buttons
		selectButMore.addActionListener(this);
		selectButOk.addActionListener(this);
		selectButCancel.addActionListener(this);
		
		//buttons are added in the appropriate panels
		selectMorePanel.add(selectButMore);
		selectButtonPanel.add(selectButOk);
		selectButtonPanel.add(selectButCancel);
		
		//scrollpane added for the panel
		JScrollPane scrollPane = new JScrollPane(selectedMorePanel);
		
		//panels are added in the parent panel
		parentPanel.add("Center",scrollPane);
		parentPanel.add("South",selectMorePanel);
		
		//panels are added in the dialog
		Container c = selectedMoreDialog.getContentPane();
		c.setLayout(new BorderLayout());
		c.add("Center",parentPanel);
		c.add("South",selectButtonPanel);
		
		selectedMoreDialog.setSize(450,575);//set the size for the dialog 
		NmsClientUtil.centerWindow(selectedMoreDialog);
		selectedMoreDialog.setVisible(true);
		
		//window listener added for the dialog 
		selectedMoreDialog.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent we)
			{
			selectedMoreDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			setForSelectedCriterias = false;	
			}
			});
	}

	/**
	 * This method constructs a dialog for fill the map criteria.
	 * If the user find to fill the map criteria other than the criteria
	 * given in the selected dialog.Then this dialog comes.
	 */
	private void getMoreDialog()
	{
		
		GridBagLayout       gbLayout    = new GridBagLayout();
		GridBagConstraints  constraints = new GridBagConstraints();;
		
		moreDialog.getContentPane().setLayout(gbLayout);
		
		JTextField textfield,tempfield;
		String text;
		
		JButton more  = new JButton(NmsClientUtil.GetString("More.."));
		more.addActionListener(this);
		more.setActionCommand("more");
		
		JButton ok    = new JButton(NmsClientUtil.GetString("Ok"));
		ok.addActionListener(this);
		ok.setActionCommand("ok");
		
		JButton cancel= new JButton(NmsClientUtil.GetString("Cancel"));
		cancel.addActionListener(this);
		cancel.setActionCommand("cancel");
		
		constraints.insets= new Insets(10,5,10,5);
		constraints.fill  = GridBagConstraints.HORIZONTAL;
		constraints.weightx = 1;
		
		for (int size = 0; size<mapMoreCount; size++)
		{
			constraints.gridwidth = GridBagConstraints.RELATIVE;
			
			textfield = new JTextField(20);
			if(size == (mapMoreCount-1))
			{
				mapMoreComponentVec.addElement(textfield);
			}
			else
			{
				tempfield = (JTextField)mapMoreComponentVec.elementAt((size*2));
				text = tempfield.getText();
				textfield.setText(text);
			}
			gbLayout.setConstraints(textfield,constraints);
			moreDialog.getContentPane().add(textfield);
			
			constraints.gridwidth = GridBagConstraints.REMAINDER;
			textfield = new JTextField(20);
			if(size == (mapMoreCount-1))
			{
				mapMoreComponentVec.addElement(textfield);
			}
			else
			{
				tempfield = (JTextField)mapMoreComponentVec.elementAt(((size*2)+1));
				text = tempfield.getText();
				textfield.setText(text);
			}
			
			gbLayout.setConstraints(textfield,constraints);
			moreDialog.getContentPane().add(textfield);
			
		}
		
		constraints.insets = new Insets(10,20,10,20);
		constraints.fill   =GridBagConstraints.NONE;
		constraints.gridwidth = GridBagConstraints.REMAINDER;
		constraints.weightx=0;
		
		more.setPreferredSize(new Dimension(100,25));
		gbLayout.setConstraints(more,constraints);
		moreDialog.getContentPane().add(more);
		
		constraints.insets = new Insets(5,5,5,5);
		constraints.fill   =GridBagConstraints.NONE;
		constraints.gridwidth = GridBagConstraints.RELATIVE;
		constraints.weightx=0;
		
		ok.setPreferredSize(new Dimension(100,25));
		gbLayout.setConstraints(ok,constraints);
		moreDialog.getContentPane().add(ok);
		
		constraints.gridwidth = GridBagConstraints.REMAINDER;
		
		cancel.setPreferredSize(new Dimension(100,25));
		gbLayout.setConstraints(cancel,constraints);
		moreDialog.getContentPane().add(cancel);
		
		moreDialog.setSize(500,mapMore_y);
		moreDialog.setLocation(250,250);
		//window listener added for the dialog
		moreDialog.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent we)
			{
			moreDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			setDialog = false;
			}
			});
		
	}
	
	/**
	 * This method is used for construct the UI components on the given panel
	 * @param  "formObject" holds the value & name for the property
	 * @param  "disp"   a JPanel , holder for the component
	 * @param  "size"   a int value , where the component is placed in the panel 
	 */
	
	private void displayFormObject(FormObject formObject,JPanel disp,int size)
	{
		
		Component component ;
		
		String propName = formObject.getPropertyName().toString().trim();//property name
		String propValue = formObject.getCurrentValue().toString().trim();//property value
		Vector values = formObject.getValuesList();
		
		if(setForSelectedCriterias)
		{
			getCheckBoxForSelected(propName,disp,size);
			if(values != null)
			{
				component = getComboBox(formObject,disp);
			}
			else
			{
				component = getJtextField("",formObject.isEditable(),disp,false);
			}
		}
		else
		{
			getJlabel(propName,disp,size);
			
			if(values != null)
			{
				int valueSize = values.size();
				if(valueSize == 2)
				{
					
					if((propName.equalsIgnoreCase("anchored")  ||
						propName.equalsIgnoreCase("ismanaged") ||
						propName.equalsIgnoreCase("managed")   ||
						propName.equalsIgnoreCase("autoPlacement")))
					{
						component = getCheckBox(formObject,disp);
					}
					else
					{
						component = getComboBox(formObject,disp);
					}
				}
				else 
				{
					component = getComboBox(formObject,disp);
				}
			}
			else
			{
				if(propName.equalsIgnoreCase("maplinkrenderer")        ||
				   propName.equalsIgnoreCase("mapsymbolrenderer")	   ||
				   propName.equalsIgnoreCase("helpdoc"))
				{				
					component = getJtextField(propValue,formObject.isEditable(),disp,true);
				}
				else
				{
					component = getJtextField(propValue,formObject.isEditable(),disp,false);
				}
			}
		}
		putComponent(component,disp,size);
	}
	/**
	 * method used for construct the combobox component and put that component in the
	 * given panel
	 * @param "formObject" holds the details for the component
	 * @param "disp" a JPanel , to hold the combobox
	 */
	private Component getComboBox(FormObject formObject,JPanel disp)
	{
		
		JComboBox combo = new JComboBox(formObject.getValuesList());
		
		combo.setSelectedItem(formObject.getCurrentValue().toString().trim());
		combo.setEnabled(formObject.isEditable());
		
		if(setForSelectedCriterias)
		{
			selectedComponentVec.addElement(combo);
		}
		else
		{
			addNewMapComponentVec.addElement(combo);
		}
		
		return combo;
	}
	
	/**
	 * method used for construct the checkbox component and put that component in the
	 * given panel
	 * @param "formObject" holds the details for the component
	 * @param "disp" a JPanel , to hold the combobox
	 */
	private Component getCheckBox(FormObject formObject,JPanel disp)
	{
		
		JCheckBox isTrue     = new JCheckBox("",true);
		String selectedValue = formObject.getCurrentValue().toString();
		
		if(selectedValue.equalsIgnoreCase("false"))
		{
			isTrue.setSelected(false);
		}
		isTrue.setEnabled(formObject.isEditable());

		addNewMapComponentVec.addElement(isTrue);
		
		return(isTrue);
	}
	/**
	 * This method is used for create the checkbox with the label and  put that
	 * component in the given panel as in the position of specified size
	 * @param "propName" a String , label for the checkbox
	 * @param "disp" a JPanel , panel to hold the checkbox
	 * @param "size" a int, to specify where to place the component in the panel
	 */
	private void  getCheckBoxForSelected(String propName,JPanel disp,int size)
	{
		propName	 = propName.substring(0,1).toUpperCase()+propName.substring(1);
		propName = NmsClientUtil.GetString(propName);
		JCheckBox isTrue     = new JCheckBox(propName,false);
		String ActionCommand = "checkbox"+propName;
		isTrue.setActionCommand(ActionCommand);
		isTrue.addActionListener(this);
		isTrue.setEnabled(true);
		isTrue.setFont(font);
		
		selectedCheckComponentVec.addElement(isTrue);
		
		constraints.fill       = GridBagConstraints.HORIZONTAL;
		constraints.weightx	   = 1;
		constraints.insets	   = new Insets(5,15,3,5);//new Insets(5,15,3,5);
		constraints.gridx      = 0;
		constraints.gridy      = (size+2);
		gbLayout.setConstraints(isTrue,constraints);
		disp.add(isTrue);		
	}
	/**
	 * method to get the JLabel as for the value passed and put that label in the 
	 * given panel.
	 * @param "text" a String , text for JLabel.
	 * @param "disp" a JPanel , panel to hold the label
	 * @param "size" a int , position to place the label in the panel
	 */
	
	private void getJlabel(String text,JPanel disp,int size)
	{
		text		 = text.substring(0,1).toUpperCase()+text.substring(1);
		
		text  = NmsClientUtil.GetString(text);
		JLabel label = new JLabel(text);
		label.setForeground(Color.black);
		label.setPreferredSize(new Dimension(125,15));
		label.setFont(font);
		
		constraints.fill       = GridBagConstraints.HORIZONTAL;
		constraints.weightx	   = 1;
		constraints.insets	   = new Insets(5,15,3,5);//new Insets(5,15,3,5);
		constraints.gridx      = 0;
		constraints.gridy      = (size+2);
		gbLayout.setConstraints(label,constraints);
		disp.add(label);		
	}
	/**
	 * This method put the component in the given panel at a given position
	 * @param "comp" a Component type 
	 * @param "disp" a JPanel , to hold the component
	 * @param "size" a int , the position where to place the component 
	 */
	private void putComponent(Component comp,JPanel disp,int size)
	{
		constraints.fill       = GridBagConstraints.HORIZONTAL;
		constraints.insets	   = new Insets(5,15,3,5);
		constraints.weightx	   = 1;
		constraints.gridx      = 2;
		constraints.gridy      = (size+2);
		gbLayout.setConstraints(comp,constraints);
		comp.setFont(font);
		disp.add(comp);
	}
	/**
	 * This method is used for get the textfield 
	 * @param "text" String , for text field value
	 * @param "editable" boolean , to say textfield is editable or not
	 * @param "disp" JPanel , holder for the textfield
	 */
	private Component getJtextField(String text ,boolean editable,JPanel disp,boolean display)
	{
		JTextField tfTemp = new JTextField(20);
		
		if(display)
		{
			//text = NmsClientUtil.GetString(text);// delete by rameshp - text field should not be localized.
			tfTemp.setText(text);
		}
		tfTemp.setEnabled(editable);
		if(setForSelectedCriterias)
		{
			selectedComponentVec.addElement(tfTemp);
		}
		else
		{
			addNewMapComponentVec.addElement(tfTemp);
		}
		
		return tfTemp;
	}
	
	/**
	 * This method is used for update the modified add new map properties
	 * @param "propertiesUpdateVector" a Vector , for holding the latest values
	 * @param "propComponentVector" a Vector , for hold the add map properties components
	 */
	private void updateFormValues(Vector propertiesUpdateVector , Vector propComponentVector)
	{
		FormObject form;
		Object obj;
		Vector valueList = null;
		
		JTextField tempTextField;
		JCheckBox  tempCheckBox;
		JComboBox  tempComboBox;
		
		int count = 0;
		int size;
		
		size = propComponentVector.size();
		
		for(Enumeration enumerate = propertiesUpdateVector.elements();enumerate.hasMoreElements();)
		{
			form =(FormObject) enumerate.nextElement();
			if(form.isEditable() == true)
			{
				if(count < size)
				{
					obj = propComponentVector.elementAt(count);
					if(obj instanceof JTextField)
					{
						tempTextField = (JTextField) obj;
						Object temp   = tempTextField.getText();
						
						form.setCurrentValue(temp);
						propertiesUpdateVector.setElementAt(form,count);	
					}
					else if(obj instanceof JCheckBox)
					{
						Object temp;
						tempCheckBox = (JCheckBox) obj;
						if(tempCheckBox.isSelected() == true)
						{
							temp = "true";
						}
						else
						{
							temp = "false";
						}
						
						form.setCurrentValue(temp);
						propertiesUpdateVector.setElementAt(form,count);	
						
					}
					else if(obj instanceof JComboBox)
					{
						tempComboBox = (JComboBox)obj;
						Object temp  = tempComboBox.getSelectedItem();
						
						form.setCurrentValue(temp);
						propertiesUpdateVector.setElementAt(form,count);	
					}
				}
				else
				{
					break;
				}
			}
			count++;
		}
		// add map criteria as a properties class type in the update vector
		if(mapMoreProperties != null)
		{
			
			FormObject formObject = new FormObject("mapcriteria",valueList,true,mapMoreProperties);
			propertiesUpdateVector.addElement(formObject);
			
		}
		//set the updated or modified map properties value
		setFormProperties(propertiesUpdateVector);
	}
	
	
}
