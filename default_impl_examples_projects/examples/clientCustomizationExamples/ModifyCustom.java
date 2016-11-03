//$Id: ModifyCustom.java,v 1.1.1.1 2006/11/16 15:39:58 gramkumar Exp $
package com.adventnet.nms.example;

//swing imports
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.text.*;

//awt imports
import java.awt.*;
import java.awt.event.*;

//util imports
import java.util.*;

//WebNMS imports
import com.adventnet.nms.util.*;
import com.adventnet.nms.util.CustomViewObject;
import com.adventnet.nms.startclient.NmsPanel;
//import com.adventnet.nms.eventui.EventBrowser;
/** 
 * This is an example data form customizer class invoked in the Events module
 * to Modify a Custom View. The class extends the abstract class com.adventnet.nms.util.DataFormCustomizer
 * and overrides the methods getCustomizer(), setCustomViewProperties() and getCustomViewProperties()
 * of it.
 * 
 * <p>The Web NMS on instantiating this class registers itself as a PropertyChangeListener
 * to this class and calls the setCustomViewProperties() method on this class
 * passing a Vector of CustomViewObjects as a parameter. The class using
 * this constructs a Dialog as the UI. Then the Web NMS subsequently calls
 * the getCustomizer() method to get the Dialog UI and displays it. 
 * 
 * <p>After the user had finished entering the criteria for the  Custom View
 * the and presses the 'Modify' button firePropertyChange() method 
 *  in the actionPerformed() method will be called.
 * 
 *<pre>
 *		 Calling the firePropertyChange() method will intimate the Web NMS
 *		 that the customizer class has completed its function. The Web NMS
 *		 on receiving this intimation will call the getCustomViewProperties()
 *		 method to get the modified CustomViewObjects vector and then proceeds
 *		 to modify the Custom View.
 * </pre>
 * 
 */
public class ModifyCustom extends DataFormCustomizer implements ActionListener
{
		
	private JDialog  dialog;     //dialog used to construt UI
	private JFrame   parentFrame; //parent frame for the dialog
    //private NmsPanel parentpanel;
    //private EventBrowser eb;
	private JPanel   displayPanel;  // panel for displaying all components
	private JPanel   buttonPanel;   // panel for adding buttons
	private JPanel   parentPanel;   // this acts as the contentpane of dialog 
	private JPanel   titlePanel;    //  to display CustomViewName 

	private GridBagLayout gbLayout;
	private GridBagConstraints constraints;
	private JButton butModify;           //  button for adding customViews
	private JButton butCancel;

	private Vector objects;       // a handle to hold CustomViewObjects

	private Vector componentVector=new Vector();  // For having all the handles of the Components created
	private Vector checkBoxVector=new Vector();    // for adding checkBoxes that are used to select display name to be dispalyed or not
	
	private Vector disNameVector=new Vector();     // The headers for the columns will be stored

	private Color color=new Color(10,50,120);
	private int size,offset=0;

	private String customViewName="";//No Internationalisation
    
	private JTextField customNameTextField;   // to display customViewName
	
    /**
     * This method overrides the method in com.adventnet.nms.util.DataFormCustomizer 
     * and is called by Web NMS to get the UI as a Window Object.
     * The Window Object returned will contain the necessary information to get the 
     * new criteria to modify a Custom View constructed with the CustomViewObjects
     * passed by NMS using setCustomViewProperties() method.
     * 
     * 
     * return "Window"  a window where the UI is present
     */	
    public Window getCustomizer()
    {
        return init(objects);  //constructs UI and returns a window
    }
	
	/** 
	 * This method overrides its corresponding counterpart  
	 * in com.adventnet.nms.util.DataFormCustomizer. This method will be called by Web NMS 
	 * as soon as this class is instantiated and Web NMS has registered itself
	 * as a PropertyChangeListener to the instantiated object. Web NMS passes
	 * the properties of the Custom View as a Vector of CustomViewObjects using
	 * which we construct the UI to be shown for modifying the Custom View.
	 * 
	 * 
	 * @param property   This is a Vector of CustomViewObjects which will be 
	 *                   passed to this class by Web NMS.
	 * 
	 */
    
    public void setCustomViewProperties(Vector property)
    {
        objects=property;   // sets the customViewObjects         
    }

	/** 
	 * This method overrides its corresponding couterpart in com.adventnet.nms.util.DataFormCustomizer. 
	 * Web NMS calls this method after this class fires a 
	 * firePropertyChange() method to Web NMS. On receiving the
	 * firePropertyChange() intimation Web NMS calls this 
	 * method to get the Vector of CustomViewObjects which will contain
	 * the criteria for the Custom View to be modfied and and 
	 * proceeds to Modify it.
	 * 
	 * @return "Vector"   Returns a Vector of CustomViewObjects which gives the properties of the Custom View objects updated by the user.
	 */
	public Vector getCustomViewProperties()
    {
        return objects;       // returns modified customViewObjects
    }
	
	/** 
	 * This method constructs a UI with a given Vector of CustomViewObjects. 
	 * The UI contains four columns. The First column contains CustomViewObject propertyname. 
	 * Second column contains CustomViewObject value (single or multiple).
	 * Third column contains a checkbox which can be checked or unchecked 
	 * by the user depending upon whether he/she wants to see this 
	 * property as one of the columns displayed in the table for the Custom View.
	 * Fourth column contains textfield that which displays the table column
	 * header text that will be displayed, the user can edit it.  
	 * 
	 * <p>The UI has two Buttons Modify and Cancel which do their respective jobs 
	 * of Modifying an existing Custom View or Cancelling the funtion.
	 * 
	 * @param  objects   A Vector of CustomViewObjects which contains the 
	 *                   properties of the Custom View to be modified from which the UI will be 
	 *                   constructed.
	 * 
	 * @return "Window"  Returns a Window which has the UI
	 *                   
	 */
	private Window init (Vector objects)
	{
		this.objects=objects;
		int sizeOfObjects=objects.size();        // size of the customViewObjects
		/*
		parentpanel = getParentPanel();
		if(parentpanel instanceof EventBrowser)
		{
			eb = (EventBrowser)parentpanel;
		}
                */
		butModify=new JButton(NmsClientUtil.GetString("Modify"));
		butModify.setActionCommand("Modify");
		butCancel=new JButton(NmsClientUtil.GetString("Cancel"));
		butCancel.setActionCommand("Cancel");
		butModify.addActionListener(this);           //adding actionListeners to the buttons
		butCancel.addActionListener(this);
		
		gbLayout=new GridBagLayout();
		constraints=new GridBagConstraints();
		
		displayPanel=new JPanel(gbLayout);
	
		parentPanel=new JPanel(new BorderLayout());
		buttonPanel=new JPanel();
		titlePanel=new JPanel(gbLayout);
		
		modifyGirdConstraints(0,0);  // method to set gridx,gridy positions for constraints (constraints is a instance of GridBagConstraints)
		constraints.gridwidth=3;
	
		JLabel title_Name=new JLabel(" "+NmsClientUtil.GetString("Custom View  Name")+"   :   ");     // Label for displaying header//No Internationalisation
		title_Name.setForeground(color);
		gbLayout.setConstraints(title_Name,constraints);
		titlePanel.add(title_Name);
		
		modifyGirdConstraints(3,0); // method to set gridx,gridy positions for constraints (constraints is a instance of GridBagConstraints)
		constraints.gridwidth=1;
	
		customNameTextField=new JTextField(10);        // Textfield to display CustomViewName
		customNameTextField.setText(customViewName);
		customNameTextField.setForeground(color);
		gbLayout.setConstraints(customNameTextField,constraints);
		titlePanel.add(customNameTextField);
		modifyGirdConstraints(0,0);  // method to set gridx,gridy positions for constraints (constraints is a instance of GridBagConstraints)
		constraints.gridheight=3;
		constraints.gridwidth=8;
		constraints.insets=new Insets(10,0,10,0);
		
		gbLayout.setConstraints(titlePanel,constraints);
		displayPanel.add(titlePanel);
		
		
		constraints.gridheight=1;
		constraints.gridwidth=2;
		constraints.insets=new Insets(10,0,10,0);
		
		JLabel column_Name=new JLabel("    "+NmsClientUtil.GetString("Name")+"    ");       //  column header//No Internationalisation
		column_Name.setBorder(new BevelBorder(BevelBorder.RAISED));
		column_Name.setForeground(color);
		
		JLabel column_Value=new JLabel("                      "+NmsClientUtil.GetString("Value")+"                     ");    //column header
		column_Value.setBorder(new BevelBorder(BevelBorder.RAISED));
		column_Value.setForeground(color);
		
		JLabel column_Select=new JLabel(" "+NmsClientUtil.GetString("Select")+" ");      //  column header//No Internationalisation
		column_Select.setBorder(new BevelBorder(BevelBorder.RAISED));
		column_Select.setForeground(color);
		
	
		JLabel column_DisName=new JLabel("  "+NmsClientUtil.GetString("Display Name")+"   ");       //  column header//No Internationalisation
		column_DisName.setBorder(new BevelBorder(BevelBorder.RAISED));
		column_DisName.setForeground(color);
		
		modifyGirdConstraints(0,3);      // method to set gridx,gridy positions for constraints (constraints is a instance of GridBagConstraints)
		gbLayout.setConstraints(column_Name,constraints);
		displayPanel.add(column_Name);
		
		modifyGirdConstraints(2,3);   // method to set gridx,gridy positions for constraints (constraints is a instance of GridBagConstraints)
		gbLayout.setConstraints(column_Value,constraints);
		displayPanel.add(column_Value);
		
		
		JLabel ForAlignment=new JLabel("         ");      // only for the purpose of alignment//No Internationalisation
		
		modifyGirdConstraints(4,3);     // method to set gridx,gridy positions for constraints (constraints is a instance of GridBagConstraints)
		
		gbLayout.setConstraints(ForAlignment,constraints);
		displayPanel.add(ForAlignment);
		
		
		modifyGirdConstraints(6,3);
		gbLayout.setConstraints(column_Select,constraints);
		displayPanel.add(column_Select);
		
		modifyGirdConstraints(8,3);
		gbLayout.setConstraints(column_DisName,constraints);
		displayPanel.add(column_DisName);
		
		
		constraints.insets=new Insets(6,0,0,0);
		
		buttonPanel.add(butModify);
		buttonPanel.add(butCancel);
		parentPanel.add(buttonPanel,BorderLayout.SOUTH);

		for(size=0;size<sizeOfObjects;size++)
		{
			CustomViewObject temp_Obj=(CustomViewObject)objects.elementAt(size);  // getting CustomViewObject from vector	
			displayCustomViewObject(temp_Obj);            // displaying the properties of the CustomViewObject
		}

		JScrollPane jsp=new JScrollPane(displayPanel);
		parentPanel.add(jsp,BorderLayout.CENTER);
		
		parentFrame = getParentFrame();
		dialog=new JDialog(parentFrame," "+NmsClientUtil.GetString("Modify Custom View")+" ",false);  //No Internationalisation
		dialog.getContentPane().add(parentPanel);
		dialog.setSize(500,500);

		dialog.addWindowListener(new WindowAdapter()
			{
			public void windowClosing(WindowEvent we)
			{
				we.getWindow().dispose();
			}
			
			}
		 );
		return dialog;
	}

	
	/** 
	 * This method displays the passed error message in a JOptionPane.
	 * 
	 * 
	 * @param message  ErrorMessage as a String
	 */
	private void showErrorMessage(String message)
	{
		JOptionPane errorMessage=new JOptionPane();
		errorMessage.showMessageDialog(dialog,message,NmsClientUtil.GetString("Error"),JOptionPane.ERROR_MESSAGE);
		errorMessage.setVisible(true);
	}
	
	
	/** 
	 * This method is used to display the properties of CustomViewObjects
	 * depending upon it's properties. Object with a property name as 'Filter View Name'
	 * is taken as the form name.  If the property name is 'time' then a method called 
	 * getDateComponents() is invoked and it's fields are displayed accordingly.
	 * If the customViewObject has a list of possible values then combobox is choosen to display 
	 * it's multiple possible values. If all the above conditions are not met then textfield is 
	 * choosen for it's display .
	 * 
	 * 
	 * @param customObject   This is the CustomViewObject for which the appropriate
	 *                       display component would be shown.
	 */
	
    private void displayCustomViewObject(CustomViewObject customObject)
    {
        Component component;
        
        Vector values=customObject.getValuesList();
        
        
        //  if the CustomViewObject name is Filter property name then sets 
        //  the current value to the customViewName
        //  textfield which is nothing but the name of CustomView
        
		        
		if(customObject.getPropertyName().equalsIgnoreCase("filter View Name"))//No Internationalisation
        {
            //String rootName = eb.getDisplayName(eb.getDisplayType ());
            String rootName = (String)customObject.getCurrentValue();
			customNameTextField.setText(rootName);
			customNameTextField.setEnabled(customObject.isEditable());	
        }
        
        
        else if(customObject.getPropertyName().equalsIgnoreCase("time"))//No Internationalisation
        {
			// if the CustomViewObject name is date then getDateComponents method is called
            getDateComponents(customObject,"");
        } else if(customObject.getPropertyName().equalsIgnoreCase("modTime") || customObject.getPropertyName().equalsIgnoreCase("createTime"))
        {
            getDateComponents(customObject, customObject.getPropertyName());            
        }
        else
        {
            getJlabel(NmsClientUtil.GetString(customObject.getPropertyName()));  // display property name in label
            if(values!=null && customObject.isEditable()==true)
            {	
                component=getComboBox(customObject);      // if the CustomViewObject contains list of values then values
                //  are displayed in comboBox
            }
            else
            // if list of values are null then current value is displayed in textfield
               component=getJtextField(customObject.getCurrentValue().toString(),customObject.isEditable());
            
            
            componentVector.addElement(component);    //  created components are added to component Vector
            //   which are used to upadate modified values
            putComponent(component);
            
            getDisOptions(customObject);     // this method displays checkbox and textfield  that is used to get display name
        }
        
    }

	
	
	/**
	 * This method gets called to display time component (From,To) .
	 * This has a "From" Date and "To" Date TextFileds.
	 * 
	 * @param customObject  This is a CustomViewObject for which date
	 *			            component is to be shown
	 */
	private void getDateComponents(CustomViewObject customObject, String str)
	{
		Component component;
		
		Date dt1,dt2;  // two Date instances one for "From" and another is for "To"//No Internationalisation
		
		String dateValue=customObject.getCurrentValue().toString();
		
		String arr[]=NmsClientUtil.splitDate(dateValue);  // this method splits date into two parts (From ,To)
		
		getJlabel(str+" "+NmsClientUtil.GetString("From")+" ");	            // label to display From
		component=getJtextField(arr[0],customObject.isEditable());   //textcomponent to display From date value
		
		componentVector.addElement(component);  //"From" date component is added to component vector//No Internationalisation
		
		putComponent(component);   // method to add JTextComponent to displaypanel
		
		constraints.gridheight=2;
		getDisOptions(customObject);      //  Display name option for Date component
		constraints.gridheight=1;
		
		offset++;        //  for two  textcomponents(From and To) only one display name option is provided
						// so offset is incremented . This offset is used to correctly update values
						//  in updating CustomViewObjects
		
		getJlabel(str+" "+NmsClientUtil.GetString("To")+" ");	  // Label for "To" component//No Internationalisation
		component=getJtextField(arr[1],customObject.isEditable());  //textfield to display "To"  date value//No Internationalisation
		componentVector.addElement(component);   // "To" date component is added to component vector//No Internationalisation
		putComponent(component);  // method to add JTextComponent to displaypanel
	
		
	}
	
	
	/**
	 * This method is used to set gridx and gridy positions
	 * to constraints (an instance of GridBagConstraints)
	 * 
	 * @param x   This is to set gridx of GridBagConstraints
	 * @param y   This is to set gridy of GridBagConstraints
	 * 
	 */
	
	private void modifyGirdConstraints(int x,int y)
	{
		constraints.gridx=x;
		constraints.gridy=y;
	}
	
	/** 
	 * This method creates a JLabel which holds customViewObject property name
	 * 
	 * @param  text  String for JLabel
	 */
	private void getJlabel(String text)
	{
		text=text.trim();
		text=text.substring(0,1).toUpperCase()+text.substring(1).toLowerCase();
		JLabel temp=new JLabel(text);
		temp.setHorizontalAlignment(JLabel.LEFT);
		temp.setForeground(Color.black);
	
		constraints.gridx=0;
		constraints.gridy=(size+offset+4);
		constraints.gridwidth=2;
	
		constraints.anchor=GridBagConstraints.WEST;
		gbLayout.setConstraints(temp,constraints);
		constraints.anchor=GridBagConstraints.CENTER;
		
		displayPanel.add(temp);		
	}

	/**
	 * This method creates a textfield and returns a handle to the TextField
	 * 
	 * @param   text      Textfield 's Contents
	 * @param	editable  This value tells wheather the TextField is enabled or not.
	 * 
	 * @return	"JTextField"   created TextField 's handle
	 */
	private Component getJtextField(String text ,boolean editable)
	{
		JTextField tfTemp=new JTextField(20);
		tfTemp.setText(text);
		tfTemp.setEnabled(editable);
	
		BevelBorder border=new BevelBorder(BevelBorder.RAISED);
		tfTemp.setBorder(border);
		
		return tfTemp;
	}
	

	/**
	 * This method gets called if the CustomViewObject has a list of
	 * possible of values.
	 * 
	 * @param customObject   This is a CustomViewObject that has list of values to be displayed
	 * 
	 * @return "JComboBox"   Returns a Component which is a ComboBox
	 */	
	private Component getComboBox(CustomViewObject customObject)
	{
		JComboBox combo=new JComboBox(customObject.getValuesList());
		
		try
		{
			if(customObject.getCurrentValue().toString()!=null)
			{
				combo.setSelectedItem(customObject.getCurrentValue());
			}
			else
				combo.setSelectedItem(new String("all"));//No Internationalisation
		}catch (Exception e)
		{
			combo.setSelectedItem(new String("all"));//No Internationalisation
		}
		
		combo.setPreferredSize(new Dimension(220,20));


		return combo;
		
	}


	
	/** 
	 * This method is used to add components like textfield , combobox 
	 * and textarea to the displaypanel
	 * 
	 * @param comp  This is the Component to be added to displaypanel
	 */
	private void putComponent(Component comp)
	{
		
		constraints.gridx=2;
		constraints.gridy=(size+offset+4);
		gbLayout.setConstraints(comp,constraints);
		
		displayPanel.add(comp);
		constraints.gridwidth=2;
		
	}
	
	/** 
	 * This method is used to add checkbox, which is checked if the particular property is 
	 * to be displayed or not.The column's heading can de given in the TextFiled .
	 * 
	 * @param customObject  This is the CustomViewObject
	 * 
	 */	
	private void getDisOptions(CustomViewObject tempObject)
	{		
		JCheckBox select=new JCheckBox();
		select.setSelected(tempObject.isDisplayed());
		constraints.gridy=(size+offset+4);
	
		constraints.anchor=GridBagConstraints.CENTER;
		constraints.gridwidth=2;
		constraints.gridx=6;

		gbLayout.setConstraints(select,constraints);
		displayPanel.add(select);
		
		checkBoxVector.addElement(select);
		
		
		String disNameValue=tempObject.getDisplayName();   //   holds the value of optional displayname
		JTextField textField=new JTextField(9);
		textField.setText(disNameValue);
		constraints.gridx=8;
		gbLayout.setConstraints(textField,constraints);
		displayPanel.add(textField);

		disNameVector.addElement(textField);
		
	}
		

	/**
	 * In this method when if the user had clicked 'Modify' button, the existing Custom 
	 * View will be modified and  the firePropertyChange() will be called.
	 */
	public void actionPerformed(ActionEvent ae)
	{
		String actionCommand=ae.getActionCommand();
		if(actionCommand.equals("Modify") && updateValues())//No Internationalisation
        {
            firePropertyChange();            
            dialog.dispose();
            dialog.setVisible(false);            

        }	
		else if(actionCommand.equals("Cancel"))//No Internationalisation
		{
			dialog.dispose();
            dialog.setVisible(false);            
		}
        		
	}	
		
	/**
	 * This method is used for updating all customViewObjectValues and  it's 
	 * display properties. First all updated values are stored in modifiedObjects vector 
	 * and  reassigned to the original vector
	 * 
	 * @return "boolean"    a boolean value -true on it's successful completion , false otherwise
	 */
	private boolean updateValues()
	{
		int sizeofObjects=objects.size();
		int indexOffset=0;
		Vector modifiedObjects=new Vector();
		Vector values;
		int offset_For_CustomViewName=0;
		
		
		boolean result=false;
		
		JComboBox comboBox;
		JCheckBox checkBox;
		JTextComponent textComponent;
		CustomViewObject customObject;
		

		for(int i=0;i<sizeofObjects;i++)
		{
			customObject=(CustomViewObject)(objects.elementAt(i));
				
			if(customObject.getPropertyName().equalsIgnoreCase("Filter View Name"))//No Internationalisation
			{
				//String rootName = eb.getDisplayName(eb.getDisplayType ());
                            String rootName = (String)customObject.getCurrentValue();
			    Vector novec = null;
				boolean noboo = false;
				String nostr = null;
				CustomViewObject Obj = new CustomViewObject("Filter View Name",novec,true,rootName,noboo,nostr);//No Internationalisation
				modifiedObjects.addElement(Obj);
				offset_For_CustomViewName--;
				continue;
			}

			checkBox=(JCheckBox)(checkBoxVector.elementAt(i+offset_For_CustomViewName));

			if(checkBox.isSelected())
			{
				textComponent=(JTextComponent)(disNameVector.elementAt(i+offset_For_CustomViewName));
				customObject.setDisplay(true);
				customObject.setDisplayName(textComponent.getText());
			}	
			
			else
			{
				customObject.setDisplay(false);
			}
			
				if(customObject.getPropertyName().equalsIgnoreCase("time"))//No Internationalisation
				{
					JTextComponent temp1=(JTextComponent)(componentVector.elementAt(i+indexOffset+offset_For_CustomViewName));
					indexOffset++;
					JTextComponent temp2=(JTextComponent)(componentVector.elementAt(i+indexOffset+offset_For_CustomViewName));
					
					
					result=dateValidation(temp1,temp2);
					if(result==false)
						return false;
					customObject.setCurrentValue(NmsClientUtil.concateDate(temp1.getText(),temp2.getText()));
				}
                else if(customObject.getPropertyName().equalsIgnoreCase("createTime") || customObject.getPropertyName().equalsIgnoreCase("modTime"))
				{
                    JTextComponent temp1=(JTextComponent)(componentVector.elementAt(i+indexOffset+offset_For_CustomViewName));
					indexOffset++;
					JTextComponent temp2=(JTextComponent)(componentVector.elementAt(i+indexOffset+offset_For_CustomViewName));
                    result=dateValidation(temp1,temp2);
					if(result==false)
						return false;
					customObject.setCurrentValue(NmsClientUtil.concateDate(temp1.getText(),temp2.getText()));
                }
				else
				{
					values=customObject.getValuesList();
					if(!(customObject.isEditable()))
					{
						modifiedObjects.addElement(customObject);
						continue;
					}
					if(values!=null && customObject.isEditable()==true)
					{
						comboBox=(JComboBox)(componentVector.elementAt(i+indexOffset+offset_For_CustomViewName));
						customObject.setCurrentValue(comboBox.getSelectedItem());
					}					
					else
					{
						textComponent=(JTextComponent)(componentVector.elementAt(i+indexOffset+offset_For_CustomViewName));
						Object temp=new String(textComponent.getText());
						customObject.setCurrentValue(temp);	
			
					}
						
				}
			
		
			modifiedObjects.addElement(customObject);
		}
		
		objects=modifiedObjects;
				
		return true;
		
	}
	
	/** 
	 * This method performs validation for the time component and it 
	 * returns true if the entered data is correct. Otherwise it gives out an
	 * appropriate error message and a false value is returned
	 *
	 * If time is not specified then returns true and takes the present time.
	 * If "From" time is specified and is greater then present time then an error
	 * message will appear and false is returned. If "From" time alone specified then
	 * "To" time will be taken as the present time and true is returned.
	 * If "To" time alone is specified then  
	 * Error message appears. On successful validation boolean-true is returned.
	 * 
	 * 
	 * @param from   This is the TextComponent reference of From field
	 * @param to     This is the TextComponent reference of To field
	 * 
	 * @return "boolean"  Returns a boolean value true time is valid, false otherwise
	 */
	private boolean  dateValidation(JTextComponent from,JTextComponent to)
	{

		String str_Date1=from.getText().trim();
		String str_Date2=to.getText().trim();
		
		Date dt1=null,dt2=null;
		
		
		
		if(!(str_Date1.equals("")))//No Internationalisation
		{						
	
			try
			{
				dt1=NmsClientUtil.parseDate(str_Date1);
				
			}catch (Exception e)
			{
				dt1=null;
				showErrorMessage(NmsClientUtil.GetString("Invalid From Date Value") + " " + str_Date1 + " - " + NmsClientUtil.GetString("Use the Pattern")+NmsClientUtil.GetString(NmsClientUtil.NmsFormatter.format(new Date())) );
				from.requestFocus();
				return false;
			}
        }			
				
			
		if(!(str_Date2.equals("")))//No Internationalisation
		{						
	
			try
			{
				dt2=NmsClientUtil.parseDate(str_Date2);
				
			}catch (Exception e)
			{
				dt2=null;
				showErrorMessage(NmsClientUtil.GetString("Invalid To Date Value") + " " + str_Date2 + " - " + NmsClientUtil.GetString("Use the Pattern")+NmsClientUtil.GetString(NmsClientUtil.NmsFormatter.format(new Date())));
				to.requestFocus();			
				return false ;
			}
		}			
				
		
		if(dt1!=null && dt2!=null && dt1.compareTo(dt2)==0)
		{	
			showErrorMessage(NmsClientUtil.GetString("Both dates are Equal") );
			from.requestFocus();
			return false;
		}
			
		
	    if(dt1!=null && dt2!=null && dt1.compareTo(dt2)>0)
		{
				showErrorMessage(NmsClientUtil.GetString("From date value is greater than To Date value ") );
				from.requestFocus();
				return false;
		}
		return true;
		
	}		
}	

