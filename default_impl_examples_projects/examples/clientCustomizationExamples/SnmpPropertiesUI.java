//$Id: SnmpPropertiesUI.java,v 1.2 2007/02/22 15:02:57 srajeswari Exp $
//sample file to be invoked as custom action

package com.adventnet.nms.example;

import java.util.Enumeration;
import java.util.Properties;
import java.util.Vector;

import com.adventnet.nms.util.*;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;

import com.adventnet.snmp.beans.SnmpTarget;

/**
 * This class just displays the properties given to it 
 * by the WebNMS.
 */
public class SnmpPropertiesUI extends JDialog implements ActionListener, CustomClassInterface
{
    /**
     * Implementation of CustomClassInterface.
     */
    GridBagConstraints constraint;
    JTextField jtextfield;
    JLabel jlabel;
    SnmpTarget target =new SnmpTarget();
    JPanel jpanel = new JPanel();
    JPanel buttonpanel = new JPanel();

    Vector keyVector = new Vector();
    Vector valueVector = new Vector();
    
    String sysoid = "";
    int i = -1,j=-1;
    InetAddress inet=null;
  
    public SnmpPropertiesUI()
    {
        super(NmsClientUtil.getParentFrame(),"Object Properties");
        getContentPane().setLayout(new BorderLayout());

        jpanel.setLayout(new GridBagLayout());
        constraint = new GridBagConstraints();
        jpanel.setBorder(new EtchedBorder());
        getContentPane().add(jpanel,BorderLayout.NORTH);

        buttonpanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonpanel.setBorder(new EtchedBorder());
        getContentPane().add(buttonpanel);

    }
    /**
This is the implementation of the setProperties method present in the CustomClassInterface, and this will be called by the nms with the arguments set in the XML file(for example SnmpMenu.xml).
     */    

    public void setProperties(Properties p[])
    {
        if (p == null)
        {
            return;
        }
        for(int count =0;count < p.length;count ++)
        {
            for(Enumeration enumerate = p[count].keys();enumerate.hasMoreElements();)
            {
                String propertyName =(String)enumerate.nextElement();
                keyVector.addElement(propertyName);
                String propertyValue =(String)p[count].get(propertyName);
                valueVector.addElement(propertyValue);
            }
        }
        
        drawUI();
    }
    
    public void drawUI()
    {
        for(int size=0;size<keyVector.size();size++)
        {
            try
            {
                if(((String)keyVector.elementAt(size)).equalsIgnoreCase("name"))
                {
                    inet = InetAddress.getByName((String)valueVector.elementAt(size));
                    target.setTargetHost((String)valueVector.elementAt(size));
                    target.setObjectID("sysObjectID.0");
                    sysoid = target.snmpGet();
                }
            }catch(Exception e){ e.printStackTrace();}
            if(size < (keyVector.size()+2)/2)
            {
                constraint.anchor = GridBagConstraints.WEST;
                constraint.gridx=0;
                constraint.gridy=(i+=1);
            }          
            else
            {
                constraint.gridx=2;
                constraint.gridy=(j+=1);
            }  
            jlabel = new JLabel((String)keyVector.elementAt(size)+":");
            constraint.insets = new Insets(10,10,10,10); 
            jpanel.add(jlabel,constraint);
            
            jtextfield = new JTextField(10);
            jtextfield.setEditable(false);
            jtextfield.setBackground(Color.white);
            jtextfield.setForeground(Color.black);
            jtextfield.setText((String)valueVector.elementAt(size));
            constraint.gridx += 1;
            constraint.insets = new Insets(10,0,10,10);
            jpanel.add(jtextfield,constraint);
        }
        
        jlabel =new JLabel("IPAddress:");
        constraint.gridx = 2;
        constraint.gridy +=1;
        constraint.insets = new Insets(10,10,10,10); 
        jpanel.add(jlabel,constraint);
        
        constraint.gridx += 1;
        jtextfield = new JTextField(10);
        jtextfield.setEditable(false);
        jtextfield.setBackground(Color.white);
        jtextfield.setForeground(Color.black);
        jtextfield.setText(inet.getHostAddress());
        constraint.insets = new Insets(10,0,10,10);
        jpanel.add(jtextfield,constraint);
        
        jlabel =new JLabel("Sys ObjectID:");
        constraint.gridx = 2;
        constraint.gridy += 1;
        constraint.insets = new Insets(10,10,10,10); 
        jpanel.add(jlabel,constraint);
        
        constraint.gridx += 1;
        jtextfield = new JTextField(10);
        jtextfield.setEditable(false);
        jtextfield.setBackground(Color.white);
        jtextfield.setForeground(Color.black);
        jtextfield.setText(sysoid);
        constraint.insets = new Insets(10,0,10,10);
        jpanel.add(jtextfield,constraint);
        
        JButton jbutton = new JButton(" OK ");
        buttonpanel.add(jbutton);
        jbutton.addActionListener(this);
        
        addWindowListener(new WindowAdapter()
            {
                public void windowClosing(WindowEvent we)
                {
                    dispose();
                }
            });
        
        pack();
        Dimension dimention = Toolkit.getDefaultToolkit().getScreenSize();
        int width =(int) dimention.getWidth();
        int height =(int) dimention.getHeight();
        int w =(int)(width-getWidth())/2;
        int h = (int)(height-getHeight())/2;
        setLocation(w,h);
        setResizable(true);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent ae)
    {
        this.dispose();
    }
}





