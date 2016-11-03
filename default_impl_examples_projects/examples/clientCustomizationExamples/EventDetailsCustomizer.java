//$Id: EventDetailsCustomizer.java,v 1.1.1.1 2006/11/16 15:39:58 gramkumar Exp $
package com.adventnet.nms.example;

//swing imports
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.border.*;
import javax.swing.text.*;

// awt imports
import java.awt.*;
import java.awt.event.*;

//util imports
import java.util.*;

//WebNMS imports
import com.adventnet.nms.util.*;
import com.adventnet.nms.severity.*;
//import com.adventnet.nms.example.*;


/**
 * This is an example class that is used to show the Details of a
 * selected Event in the Events module, instead of the default form
 * used by Web NMS. The purpose of this example is to exhibit the 
 * methodology of writing user defined Data forms that could 
 * override the default data forms present in the Web NMS Client.
 *  
 * <p>The class extends com.adventnet.nms.util.DataFormCustomizer 
 * overiding the getFormProperties(), setFormProperties() and getCustomizer() methods. 
 * <p>The Web NMS on instantiating this class registers itself as a PropertyChangeListener
 * to this class and calls the setFormProperties() method on this class
 * passing a Vector of FormObjects as a parameter, where each FormObject 
 * will contain a property of the selected Event.  The class using
 * this, constructs a Dialog. Then the Web NMS subsequently calls
 * the getCustomizer() method to get the Dialog UI and displays it.
 * 
 <p>After the user had finished  modifying the properties that could be 
 * edited of the Event selected, and presses the 'Modify'
 * button firePropertyChange() method in the  actionPerformed() method
 * will be called.
 * 
 *<pre>
 *		 Calling the firePropertyChange() method will intimate the Web NMS
 *		 that the customizer class has completed its function. The Web NMS
 *		 on receiving this intimation will call the getFormProperties()
 *		 method to get the modified FormObjects vector and then proceeds
 *		 to modify the properties of the Event.
 * </pre>* 
 * 
 * FormObjects as a Vector through getFormProperties() method and constructs an UI.
 * Their properties can be  modified and  updated through the modify button,finally are 
 * returned  when Web NMS calls getFormProperties() Method.
 * getCustomizer() method returns a window to the Web NMS.
 */
public class EventDetailsCustomizer extends DataFormCustomizer implements ActionListener
{
    private JDialog dialog;                       // dialog that contains UI
    private FormObject formObject;                // a reference for FormObject
    private int severityCount = 0;
    private int indexCount =0;

    private Vector objects;			  // List of  FormObjects  
    private Vector componentVector=new Vector();  // for storing references of
                                                  // the components used like 
    private Vector clonedobjects;
    private Vector rearrange;
    private JPanel parentPanel;     
    private JPanel buttonPanel;   //  panel to hold modify and cancel buttons
    private JPanel displayPanel;  // panel that contains components 
    
    private JButton butOk;
    private JButton butHelp;
    private GridBagLayout gbLayout;
    private GridBagConstraints constraints;

    String fontcolor=null;
    private Color color = new Color(10,50,120);
    Color classColor=null;    

    private Font font =  NmsClientUtil.getFont("DIALOG");//No Internationalisation
    private Font value_font =  NmsClientUtil.getFont("DIALOG");//No Internationalisation
    
    private int size;     // number of FormObjects 
    private int val;     
    private Object[][] row_datas = null;
    private Object[] col_names = null;

  
    /** 
     * This method is called by Web NMS, which overrides it's counterpart
     * in com.adventnet.nms.util.DataFormCustomizer  
     * 
     * @return "Vector"    Returns a Vector containing FormObjects
     */
	
	
    public Vector getFormProperties()
    {		
	return objects;
    }
	
	
    /**  
     * This method overrides the method in 
     * com.adventnet.nms.util.DataFormCustomizer 
     * and is used for initialisation
     * 
     * @param formObjects  a Vector containing FormObjects
     */
    
    public void setFormProperties(Vector formObjects)
    {
	objects=formObjects;
    }
    
    /**  
     * This method overrides the method in com.adventnet.nms.util.DataFormCustomizer. 
     * This method is called my Web NMS after passing FormObjects through setFormProperties()
     * and gets back a dialog containing UI
     * 
     * @return "Window"   Returns a Window that contains UI
     */
	public Window getCustomizer()
	{
		return init();	
	}

	/** 
	 * init() method constructs the UI that contain property name of the FormObject
	 *  and it's value as it's columns. Value is displayed  either in textfield or textarea or 
	 * combobox or checkBox upon it's nature.
	 * 
	 * @return "Window"    a Window
	 */
	private Window init()
	{
		final int noOfRows = objects.size();   //holds the size of Vector 
		int indicator = 0;
		clonedobjects = new Vector();

		String message=null;
	


		butOk = new JButton(NmsClientUtil.GetString("OK")); 
        butOk.setActionCommand("OK");//No Internationalisation
		butHelp   = new JButton(NmsClientUtil.GetString("Help")); 
        butHelp.setActionCommand("Help");//No Internationalisation

		butOk.addActionListener(this);
		butHelp.addActionListener(this);
		
		displayPanel=new  JPanel();
		displayPanel.setLayout(new GridLayout(2,1));
		parentPanel=new JPanel(new BorderLayout());
		buttonPanel=new JPanel();
		JPanel messagePanel = new JPanel();
		messagePanel.setLayout(new BorderLayout());
		JLabel messageLabel = new JLabel(NmsClientUtil.GetString("Message"));
		messageLabel.setBorder(new BevelBorder(BevelBorder.RAISED));
		messageLabel.setFont(value_font);
		messageLabel.setForeground(Color.black);
		JTextArea messageTextArea = new JTextArea();
		messageTextArea.setLineWrap(true);
        messageTextArea.setWrapStyleWord(true);      
        messageTextArea.setEditable(false);
		buttonPanel.add(butOk);
		buttonPanel.add(butHelp);
		int counter = 0;
		for(size=0;size<noOfRows;size++)
		    {
			formObject=(FormObject)objects.elementAt(size);
				// getting FormObjects
			if((formObject.getPropertyName().equalsIgnoreCase("message"))) //No Internationalisation
			    {
				message=formObject.getCurrentValue().toString();
                continue;
			    }
            if((formObject.getPropertyName().equalsIgnoreCase("severity"))) //No Internationalisation
                {
                    fontcolor = formObject.getCurrentValue().toString();
                    classColor = SeverityInfo.getInstance().getColor(fontcolor);
                    severityCount = counter;
                }
            else if((formObject.getPropertyName().equalsIgnoreCase("index")))
                {
                    indexCount = counter;
                }
            
            clonedobjects.addElement(formObject);
            counter++;
            }
        

	
		TableModel dataModel = new AbstractTableModel() {

			public int getColumnCount() { 
			    
			    return 2;
			    
			}
			
			public int getRowCount() { 
			    
			    return (noOfRows-1);
			    
			}
			
			public Object getValueAt(int row, int col) {
			  
			    formObject=(FormObject)clonedobjects.elementAt(row);
			    if(col==0) {
				
				return NmsClientUtil.GetString(formObject.getPropertyName());
				
			    } 
			    
			    else 
				{
				    return formObject.getCurrentValue().toString();
			      }
                
			}
	      
	      public String getColumnName(int col) {

		  if(col==0) 
		      {
			  return NmsClientUtil.GetString("PropertyName");//No Internationalisation
		      } 
		  
		  else 
		      {
			  return NmsClientUtil.GetString("Value");//No Internationalisation
		      }
	      }
	      
	      public boolean isCellEditable(int row ,int col) 
	      {
		  formObject=(FormObject)clonedobjects.elementAt(row);
		  return formObject.isEditable();//false;
	      }
	  };

      messageTextArea.setText(message);
      messageTextArea.setPreferredSize(new Dimension(420,175));
      messageTextArea.setForeground(Color.black);
      messageTextArea.setFont(value_font);
      messagePanel.add("North",messageLabel);//No Internationalisation
      messagePanel.add("Center",messageTextArea);//No Internationalisation
		
      parentPanel.add(buttonPanel,BorderLayout.SOUTH);
      
      final JTable table = new JTable(dataModel);      //row_datas,col_names);
      
      DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() {
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
	      {
		  JLabel trtemp = new JLabel();
		  if((row == severityCount )&&(column==1))
		      {
			  trtemp.setFont(value_font);
			  trtemp.setBackground(classColor);
			  trtemp.setText(fontcolor);
		      }	     
		  else if((row == indexCount)&&(column==0))
		      {
			  trtemp.setFont(value_font);
			  trtemp.setForeground(Color.black);
			  trtemp.setText(NmsClientUtil.GetString("Event ID"));//No Internationalisation
		      }
		  else if(column==0)
		      {
			  trtemp.setFont(value_font);
			  trtemp.setForeground(Color.black);
			  trtemp.setText(NmsClientUtil.GetString(formObject.getPropertyName()));
		      }
		      else 
		      {
			  trtemp.setFont(value_font);
			  trtemp.setForeground(Color.black);
			  trtemp.setText(formObject.getCurrentValue().toString());
		      }
		  trtemp.setOpaque(true);
		  return trtemp;
	      }
	  };

      for (int col = 0; col < 2; col++) 
	  {
	      TableColumn idy = table.getColumnModel().getColumn (col);
		  idy.setCellRenderer (renderer);
	  }
	
      JScrollPane jsp=new JScrollPane(table);
      displayPanel.add(jsp);
      displayPanel.add(messagePanel);
      parentPanel.add(displayPanel,BorderLayout.CENTER);
      
      JFrame parentFrame = getParentFrame();
	  
	  dialog=new JDialog(parentFrame," "+NmsClientUtil.GetString("Event Object Details")+" ",false);  
      dialog.getContentPane().add(parentPanel);

      dialog.pack();
      dialog.setSize(420,500);
      
      dialog.addWindowListener(new WindowAdapter()          // WindowListener for the Dialog
	  {
	      public void windowClosing(WindowEvent we)
	      {
		  we.getWindow().dispose();
		  we.getWindow().setVisible(false);
	      }

	  });
      table.addMouseListener(new MouseAdapter()
	  {
	      public void mouseClicked(MouseEvent e)
		  {
		      Point pt = e.getPoint();
		      int col = table.columnAtPoint(pt);
		      int row = table.rowAtPoint(pt);
		      if((row == 10)&&(col == 1))
			  {

			      String helpURL =formObject.getCurrentValue().toString();
			      if (helpURL != "")//No Internationalisation
				  {

				      java.applet.Applet applet = null;
				      NmsClientUtil.showURLInNW(applet,helpURL);
				  }
			  }
		  }
	  });								 
      return(dialog);     // return dialog
	}
	
	
	/**
	 * actionPerformed() routine is called when OK/Help button is pressed.
	 * If the actionCommand is "OK" then dialog is made invisible.
	 * 
	 */
	public void actionPerformed(ActionEvent ae)
	{
		String action=ae.getActionCommand();
		if(action.equals("OK"))//No Internationalisation
		dialog.setVisible(false);	
		else if (action.equals("Help"))//No Internationalisation
		    {
						String helpURL = "../help/User_Guide/Java_UI/userguide_introduction.html";//No Internationalisation
                        java.applet.Applet applet = null;
                        NmsClientUtil.showURLInNW(applet,helpURL);
		    }
		dialog.dispose();
	}
	
}


