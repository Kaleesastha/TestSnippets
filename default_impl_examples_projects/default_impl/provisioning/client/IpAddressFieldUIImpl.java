//$Id: IpAddressFieldUIImpl.java,v 1.1.1.1 2006/11/16 15:39:58 gramkumar Exp $
package com.adventnet.nms.provisioning.ui.uielements;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import com.adventnet.nms.provisioning.ui.ProvClientUtils;
public class IpAddressFieldUIImpl extends AbstractXmlUIElement
{
	IpAddressField ipaddress = null;

	 public IpAddressFieldUIImpl()
	 {
		 ipaddress = new IpAddressField();
	 }

	 public void setEditable(boolean b)
	 {
		 for(int i=0;i<4;i++)
		 {
		   ipaddress.jt[i].setEditable(b);
		 }
	 }

	 public String getValue()
	 {
		 return ipaddress.getIpAddress();
	 }

	 public void setValue(String value)
	 {
	   ipaddress.setIpAddress(value.trim());
	 }

	 public JComponent getComponent()
	 {
		 return ipaddress;
	 }

}
  class IpAddressField extends JComponent implements FocusListener
  {
		public JTextField [] jt = new JTextField[4];
    public IpAddressField()
    {
		  setLayout(new FlowLayout(FlowLayout.LEFT,0,5));
		  setBackground(new Color(255,255,255));
		  setSize(200,100);
		  Dimension d = new Dimension(30,20);
		  for(int i=0;i<4;i++)
		  {
		    jt[i] = new JTextField(new IpTextDocument(),"0",3);
		    jt[i].setBorder(new javax.swing.border.BevelBorder(1));
        jt[i].setHorizontalAlignment(JTextField.RIGHT);
		    jt[i].setPreferredSize(d);
		    jt[i].addFocusListener(this);
		    add(jt[i]);
		  }	
		  jt[0].requestFocus();
		  jt[0].setNextFocusableComponent(jt[1]);
		  jt[1].setNextFocusableComponent(jt[2]);
		  jt[2].setNextFocusableComponent(jt[3]);
		  setVisible(true);
    }

	  public String getIpAddress()
	  {
		  if ((jt[0].getText().equals(""))||(jt[1].getText().equals(""))||(jt[2].getText().equals(""))||(jt[3].getText().equals("")))
			{
			  return "";
			}
			return new String(jt[0].getText()+"."+jt[1].getText()+"."+jt[2].getText()+"."+jt[3].getText());
	  }
    public void setIpAddress(String ipAdd)
	  {
		  boolean numbers = true;
		  char [] ipChars= ipAdd.toCharArray();
		  for (int i=0;i<ipChars.length;i++)
		  {
		    if ((ipChars[i]!=46)&&(!((ipChars[i]>47)&&(ipChars[i]<58))))
			  {
			    numbers = false;
				  break;
			  }
		  }
		  if (numbers)
		  {
		    StringTokenizer st = new StringTokenizer(ipAdd,"."); 
		    if (st.countTokens()==4)
		    {
		      for(int j=0;st.hasMoreTokens();j++)
			    {
					  jt[j].setText(st.nextToken());
			    }
		    }
		    else 
		    {
			    //JOptionPane.showMessageDialog(IpAddressField.this, "IP Address should be four bytes long","Error Message",JOptionPane.ERROR_MESSAGE);
					jt[0].setText("0");
					jt[1].setText("0");
					jt[2].setText("0");
					jt[3].setText("0");
					jt[0].requestFocus();
		    }
		  }
		  else
		  {
			  //JOptionPane.showMessageDialog(IpAddressField.this, "Non-numeric values are not allowed","Error Message",JOptionPane.ERROR_MESSAGE);
				jt[0].setText("0");
				jt[1].setText("0");
				jt[2].setText("0");
				jt[3].setText("0");
				jt[0].requestFocus();
		  }
	  }
	  public void focusGained(FocusEvent fe)
	  {
		  ((JTextField)fe.getSource()).selectAll();
	  }
	  public void focusLost(FocusEvent fe)
	  {
	  }
    class IpTextDocument extends PlainDocument
	  {
	    String validValues = "0123456789";
      boolean tab;
		  public IpTextDocument()
		  {
	 	    super();
		  }

		  public IpTextDocument(int i)
		  {
		    super();
		  }
		  public void insertString(int offset,String str,AttributeSet attrib) throws BadLocationException
		  {
		    if(str == null)
			  {
			    return;
			  }
				if (str.equals("."))
				{
				  if (getLength()>0)
					{
					  tab=true;
					}
				}
				else
				{
          for(int i=0;i < str.length();i++)
	  		  {
		  	    if(validValues.indexOf(str.valueOf(str.charAt(i))) == -1)
			  	  {
				  	  Toolkit.getDefaultToolkit().beep();
					    JOptionPane.showMessageDialog(IpAddressField.this, ProvClientUtils.getString("Non-numeric values are not allowed"), ProvClientUtils.getString("Error Message"),JOptionPane.ERROR_MESSAGE);
						  getTextField().requestFocus();
					    return;
				    }
			    }

			  

			    if(getLength() ==2)
  			  {
  				  tab = true;
	  		  }

		  	  if(getLength() > 2)
			    {
			  	  tab = false;
				    Toolkit.getDefaultToolkit().beep();	
				    JOptionPane.showMessageDialog( IpAddressField.this, ProvClientUtils.getString("Length should not be more than three"), ProvClientUtils.getString("Error Message"),JOptionPane.ERROR_MESSAGE);
						getTextField().requestFocus();
  				  return;
	   		  }

		  	  String s = getText(0,getLength());

  			  if(getLength() == 0)
	  		  {
		  		  s=str;
			    }	
			    else
			    {
				    StringBuffer buffer = new StringBuffer(getText(0,getLength()));	
  				  buffer.insert(offset,str);
	  			  s = buffer.toString();
		  	  }

  			  int i = Integer.parseInt(s);

	  		  if(i >= 256)
		  	  {
			  	  tab = false;
				  	Toolkit.getDefaultToolkit().beep();	
				    JOptionPane.showMessageDialog( IpAddressField.this, ProvClientUtils.getString("Value cannot exceed 255"), ProvClientUtils.getString("Error Message"),JOptionPane.ERROR_MESSAGE);
						getTextField().requestFocus();
  				  return;
	  		  }

		  	  super.insertString(offset,str,attrib);
			  }
				if(tab)
			  {
				  JTextField temp =getTextField();
					if(temp.getNextFocusableComponent() != null)
				  {
					  temp.getNextFocusableComponent().requestFocus();
				  }	
				  tab = false;
			  }
		  }

			private JTextField getTextField()
			{
			  if(jt[0].getDocument() == this)
				{
				 return jt[0];
				}
				else if(jt[1].getDocument() == this)
				{
				  return jt[1];
				}
				else if(jt[2].getDocument() == this)
				{
				  return jt[2];
				}
				return jt[3];
			}

		  public void remove(int offs,int len) throws BadLocationException
		  {
			  super.remove(offs,len);
		  }
	  }

  }
