//$Id: ComboUIImpl.java,v 1.1.1.1 2006/11/16 15:39:58 gramkumar Exp $
package com.adventnet.nms.provisioning.ui.uielements;

import javax.swing.*;


public class ComboUIImpl extends AbstractXmlUIElement 
{

   JComboBox cb=new JComboBox();
   String names[] = new String[0];
   String values[] = new String[0];

   public ComboUIImpl()
    {

    }

    public void setEditable(boolean b)
    {
	 cb.setEditable(b);
    }

    public int getNumberOfColumns()
    {
      return 2;
    }

    public String getValue()
    {
	  String propvalue= (String)cb.getSelectedItem();
        for(int i = 0; i < names.length; i++)
        {
           if(names[i].equals(propvalue))
           {
               propvalue = values[i];
               break;
           }
        }

        return propvalue;
    }

    public void setValue(String value)
    {
        cb.setSelectedItem(value);
				for(int i = 0; i <values.length; i++)
		    {
			    if(value.equals(values[i]))
			    {
            cb.setSelectedItem(names[i]);
						break;
			    }
		    }
    }

    public void setEnumeratedValues(String namesArg[],String valuesArg[])
    {
	 names = namesArg;
	 values = valuesArg;
	 for(int i = 0; i < names.length; i++)
  	 {
		cb.addItem(names[i]);
	 }
    }

    public JComponent getComponent()
    {
        return cb;
    }
    
	/*Overriding getDisplayName() of AbstractXmlUIElement to handle correct display of name/value attributes of Choice type when present inside the Table tag of Provisioning Template*/

	public String getDisplayName()
	{
	   String propvalue= (String)cb.getSelectedItem();
	   for(int i = 0; i < names.length; i++)
	   {
		  if(names[i].equals(propvalue))
		  {
			  propvalue = names[i];
			  break;
		  }
	   }   
	   return propvalue;	    
	}

}
