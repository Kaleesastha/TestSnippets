//$Id: NumericTextFieldUIImpl.java,v 1.1.1.1 2006/11/16 15:39:58 gramkumar Exp $
package com.adventnet.nms.provisioning.ui.uielements;

//import com.adventnet.beans.text.NumericTextField;

import java.awt. *;
import java.awt.event.*;
import java.text.MessageFormat;
import javax.swing.*;
import javax.swing.text.*;
import com.adventnet.nms.provisioning.ui.ProvClientUtils;
import com.adventnet.nms.provisioning.ConstraintsViolatedException;

public class NumericTextFieldUIImpl extends AbstractXmlUIElement 
{

    NumericTextField numtext=new NumericTextField();
    boolean flag=false;

    public NumericTextFieldUIImpl()
    {
    }

    public void setEditable(boolean b)
    {
		numtext.setEditable(b);
    }

    public int getNumberOfColumns()
    {
		return 2;
    }

    public String getValue()
    {
        return numtext.getText();
    }


    public void setRange(String range)
    {
        long min=0;
        long max=0;
        int hiphenindex=range.lastIndexOf("-");
        int doubleHiphen=range.indexOf("--");
        if(hiphenindex==-1)
			{
				max=Integer.parseInt(range);
			}
        else
			{
                if(doubleHiphen == -1)
                {
                    min=Long.parseLong(range.substring(0,hiphenindex));
                    max=Long.parseLong(range.substring(hiphenindex+1,range.length()));
                }
                else
                {
                    flag=true;
                    min=Long.parseLong(range.substring(0,doubleHiphen));
                    max=Long.parseLong(range.substring(doubleHiphen+1,range.length()));
                }
                if(min>max)
                {
                    System.out.println(ProvClientUtils.getString("Minimum value and maximum value have been interchanged"));
                    min=min-max;
                    max=min+max;
                    min=max-min;
                }
                else if(min==max)
                {
                    System.out.println(ProvClientUtils.getString("Since minimum and maximum value of range are equal, minimum value is decremented by 1 and maximum value is incremented by 1"));
                    min=min-1;
                    max=max+1;
                }
            }
        numtext.setMinValue(min);
        numtext.setMaxValue(max);
    }
    public void checkConstraints() throws ConstraintsViolatedException
    {
        if (!(numtext.getText().equals("")))
        {
            if (Long.parseLong(numtext.getText())<numtext.getMinValue() || Long.parseLong(numtext.getText())>numtext.getMaxValue())
            {
                int min = (int)numtext.getMinValue();
                int max = (int)numtext.getMaxValue();
                String labelName = getLabelName();
                String message = MessageFormat.format(ProvClientUtils.getString("{0} should be between {1} and {2}"), new Object[]{labelName, new Integer(min), new Integer(max)});
                throw new ConstraintsViolatedException(message);
            }
        }	
    }
    public int addComponents(JComponent parentComp,GridBagConstraints gc,int row,int col,int maxCols)
    {
        if (maxCols <= 2) 
			{
				numtext.setColumns(22);
				numtext.setPreferredSize(new Dimension(120,30));
				numtext.setMinimumSize(new Dimension(120,30));
			}
        else 
			{
				numtext.setColumns(14);
				numtext.setPreferredSize(new Dimension(75,30));
				numtext.setMinimumSize(new Dimension(75,30));
			}
        return super.addComponents( parentComp, gc, row, col, maxCols);
    }



    public void setValue(String value)
    {
        numtext.setText(value);
    }

    public JComponent getComponent()
    {
        return numtext;
    }




    /**
     * This class extends TextField and it allows the user to type only numeric
     * values i.e, it allows user to type values from 0 to 9 and it wont allow
     * to type any characters other than 0-9
     */

    public class NumericTextField extends JTextField
    {
	
		private long maxValue = Long.MAX_VALUE/10;
		private long minValue = 0;
	
		/**
		 * Default constructor for NumericTextField.
		 */
		public NumericTextField()
		{
			super();
	    
		}
	
		protected Document createDefaultModel()
		{
			return new NumericDocument();
		}
	
		/**
		 * Set the given text to the TextField.
		 */
/*		public void setText(String newTextArg)
		{
			long typedValue = -1;
			newTextArg = newTextArg.trim();
			if((newTextArg == null) || (newTextArg.equals("")))
				{
					super.setText("");
					return;
				}
			else
				{
					typedValue = Long.parseLong(newTextArg);
				}
	    
			if((typedValue > maxValue))//||(typedValue < minValue))
				{
					throw new IllegalArgumentException("The Value "+typedValue+" is greater than the "+ maxValue);
		    
				}
			super.setText(newTextArg);
		}
		*/
	
		public void setMinValue(long value)
		{
			minValue = value;
		}
	
		public long getMinValue()
		{
	    
			return minValue;
		}
	
		public void setMaxValue(long value)
		{
			maxValue = value;
		}
	
		public long getMaxValue()
		{
			return maxValue;
		}


		class NumericDocument extends PlainDocument
		{
			public void insertString(int offs, String str, AttributeSet a) throws BadLocationException
			{
				long typedValue = -1;

				StringBuffer textBuffer = new StringBuffer(NumericTextField.this.getText().trim());
				textBuffer.insert(offs,str);
				String textValue = textBuffer.toString();
				if((textValue == null) || (textValue.equals("")))
					{
						remove(0,getLength());
						super.insertString(0, "", null);
						return;
					}
                if(textValue.equals("-"))
                {
                    super.insertString(offs,new String(str), a);
                    return;
                }
				try
					{
						typedValue = Long.parseLong(textValue);
                        if(!flag)
                        {
                            if((typedValue > maxValue))//||(typedValue < minValue))
							{
								JOptionPane.showMessageDialog(NumericTextField.this, MessageFormat.format(ProvClientUtils.getString("The Value should be lesser than {0}"), new Object[]{new Long(getMaxValue())}), "Error Message", JOptionPane.ERROR_MESSAGE);
							}
                            else
							{
								super.insertString(offs,new String(str), a);
							}
                        }
                        else
                        { 
                            super.insertString(offs,new String(str), a);
                        }
                    }
				catch(NumberFormatException ex)
					{
						Toolkit.getDefaultToolkit().beep();
						//JOptionPane.showMessageDialog(NumericTextField.this, "Only numeric values allowed.", "Error Message", JOptionPane.ERROR_MESSAGE);
					}
			}
		}
    }
    
}
