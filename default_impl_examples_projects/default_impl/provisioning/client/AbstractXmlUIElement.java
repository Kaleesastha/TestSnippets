//$Id: AbstractXmlUIElement.java,v 1.1.1.1 2006/11/16 15:39:58 gramkumar Exp $
package com.adventnet.nms.provisioning.ui.uielements;

import javax.swing.*;
import java.awt.*;
import com.adventnet.nms.provisioning.ConstraintsViolatedException;

/**
 * The abstract implementation of the interface XmlUIElement. Users are requested to extends this class
 * rather than implementing the interface XmlUIElement. This class provides a Label by default and takes
 * care of setting and getting the text on the label. 
 * So, the component alone can be created in the sub class. The implementation of addComponents() in this
 * method calls the method getComponent() present in its subclass so that the subclass can return the 
 * component alone which will be added to the Provisioning Client.<br><br>
 * A Quick implementation of SliderUIImpl: <br>
 * <code> <pre>
 * public class SliderUIImpl extends AbstractXmlUIElement
 * {
 *      private JSlider slider = new JSlider();
 *
 *      public SliderUIImpl()
 *      {
 *      }
 *
 *      public JComponent getComponent()
 *      {
 *          return slider;
 *      }
 *
 *      public void setRange(String range)
 *      {
 *         int min=0;
 *         int max=0;
 *         int hiphenindex=range.lastIndexOf("-");
 *         {
 *              min=Integer.parseInt(range.substring(0,hiphenindex));
 *              max=Integer.parseInt(range.substring(hiphenindex+1,range.length()));
 *         }
 *         slider.setMinimum(min);
 *         slider.setMaximum(max);
 *      }
 *
 *      public String getValue()
 *      {
 *          String value = "" + slider.getValue();
 *          return value;
 *      }
 *
 *      public void setValue(String value)
 *      {
 *          int val = Integer.parseInt(value);
 *          slider.setValue(val);
 *      }
 *      public void setEditable(boolean b){}
 *
 * }
 * </pre> </code>
 * The slider can be used in the client by giving an entry in the File TypeToUIElementMapping.txt,
 * which is in the classes directory, like,<br> slider=packagename.SliderUIImpl<br>
 * Then the slider can be given in the Qualifier tag.<br>
 * &lt;UserInput label="test" default="test"&gt; <br>
 *   &lt;Qualifier type="slider" range="10-20" /&gt; <br>
 *   &lt;/UserInput&gt;
 *
 * <br><br>
 * By default, each component occupies one row in the Template UI. The method addComponents() 
 * can be overriden so that components which span multiple rows can be added to the UI.  The following
 * piece of code adds a label and a component each occupying three rows.<br>
 * <code> <pre>
 *  public int addComponents(JComponent parentComp,GridBagConstraints gc,int row,int col,int maxCols)
 *  {
 *        gc.gridx = row;
 *        gc.gridy = col;
 *
 *        gc.weightx = 0.0;
 *        gc.gridwidth=1;
 *        gc.gridheight=3;
 *        parentComp.add(label,gc);
 *
 *        gc.gridx = row + 1;
 *        gc.gridheight=3;
 *        gc.weightx=0.75;
 *        parentComp.add(component,gc);
 *        return 3;
 *  }
 *  </pre> </code>
 * 
 * 
 */
public abstract class AbstractXmlUIElement implements XmlUIElement
{

    /**
     * The label that appears for each element in the client.
     */
    protected JLabel label = new JLabel();
    
	/**
     * Used to mark this element as mandatory in the client, using the method setRequired().
     */
    protected boolean valueRequired = false ;
		public AbstractXmlUIElement()
    {
        label.setHorizontalAlignment(SwingConstants.LEFT);  
    }


    public int getNumberOfColumns()
    {
        return 2;
    }

    public void setLabelName(String name)
    {
				if (valueRequired)
				{
				  label.setText(name+"(*)");
				}
				else
				{
          label.setText(name);
				}
    }
		public String getLabelName()
		{
		  String str=label.getText();
			if (valueRequired)
			{
			  str = str.substring(0,str.length()-3);
			}
			return str;
		}
    
    public int addComponents(JComponent parentComp,GridBagConstraints gc,int x,int y,int maxX)
    {
        gc.gridx = x;
        gc.gridy = y;

        gc.weightx = 0.0;
        parentComp.add(label,gc);

        gc.weightx = 1.0;
        gc.gridx = x + 1;
        parentComp.add(getComponent(),gc);
        return 1;    
    }

    public void setDescription(String description)
    {
        label.setName(description);
        getComponent().setName(description);
    }
		
    public void setRequired(boolean b)
		{
		  valueRequired = b;
		}

    public boolean isRequired()
		{
		  return valueRequired;
		}
		public boolean isValueNull()
		{
		  if ((getValue()==null)||(getValue().equals("")))
			{
			  return true;
			}
			return false;
		}

    public void setRange(String range){}

    public void checkConstraints() throws ConstraintsViolatedException
    {
    }

    public void setEnumeratedValues(String names[],String values[]){}

    /**
     * Returns the component this UIElement represents. Users can simply override this method to return
     * their component instead of implementing the method addComponents(). The AbstractXmlUIElement
     * takes care of adding the component to the Provisioning Client.
     * 
     */
    public abstract JComponent getComponent(); 

    /**
     * Returns the JLabel instance associated with this UI Element.
     * @return the JLabel instance 
     */
    public JLabel getLabel()
    {
        return label;
    }

	/**
	 * Returns the value of UIElement to be displayed in the client. Users can override this
	 * method if their UIElement has its display value different from the actual value to be
	 * taken for provisioning
	 * @return the value of UIElement to be shown in the client
     */
    public String getDisplayName()
    {
    	return getValue();
    }
    
}
