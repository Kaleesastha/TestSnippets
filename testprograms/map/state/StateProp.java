import com.adventnet.nms.mapui.*;
import com.adventnet.nms.util.*;

import java.io.*;
import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.applet.Applet;

public class StateProp 

{
	//JFrame prop = new JFrame();
	JTextField text = null;
	JTextField text1 = null;
	GridBagLayout gridBag = new GridBagLayout();
	JPanel panel = null;
	JFrame prop = new JFrame();
	GridBagConstraints c = new GridBagConstraints();
	public StateProp(String arg[])throws IOException
	{
		String path = arg[0] ; 
		File file = new File(path);
		try
		{
			FileInputStream istream = new FileInputStream(file);
			ObjectInputStream p = new ObjectInputStream(istream);
			Object symbol = (Object)p.readObject();
			if (symbol instanceof MapLink)
			{
				Properties property =  ((MapLink)symbol).getProperties();
				int n = property.size();
				
				panel= new JPanel(gridBag);
				JScrollBar propertyView = new JScrollBar();
				c.fill = GridBagConstraints.BOTH;
				c.weightx = 0.5;
				c.weighty = 0.5;
				c.gridwidth = 1;
				c.gridheight = 1;
				int j = 1;
				
				for (Enumeration t= property.propertyNames();t.hasMoreElements();)
				{
					String key = (String)t.nextElement();
					String value = (String)property.get(key);
					text = new JTextField();
					text1 = new JTextField();
					//System.out.println("key = " + key);
					//System.out.println("value = " + value);
					c.gridx = 5;
					c.gridy = j ;
					text1.setText(key);
					gridBag.setConstraints(text1,c);
					panel.add(text);
					c.gridx = 10;
					text.setText(value);
					gridBag.setConstraints(text,c);
					
					panel.add(text1);
					
					j=j+1;
					gridBag.setConstraints(propertyView,c);
					panel.add(propertyView);
					
				}
				
				
				prop.getContentPane().add(panel);
				
				
				prop.setSize(800,500);
				prop.setVisible(true);
			}
			else if (symbol instanceof MapContainer)
			{
				Properties property =  ((MapContainer)symbol).getProperties();
				int n = property.size();
				
				panel= new JPanel(gridBag);
				JScrollBar propertyView = new JScrollBar();
				c.fill = GridBagConstraints.BOTH;
				c.weightx = 0.5;
				c.weighty = 0.5;
				c.gridwidth = 1;
				c.gridheight = 1;
				int j = 1;
				
				for (Enumeration t= property.propertyNames();t.hasMoreElements();)
				{
					String key = (String)t.nextElement();
					String value = (String)property.get(key);
					text = new JTextField();
					text1 = new JTextField();
					//System.out.println("key = " + key);
					//System.out.println("value = " + value);
					c.gridx = 5;
					c.gridy = j ;
					text1.setText(key);
					gridBag.setConstraints(text1,c);
					panel.add(text);
					c.gridx = 10;
					text.setText(value);
					gridBag.setConstraints(text,c);
					
					panel.add(text1);
					
					j=j+1;
					gridBag.setConstraints(propertyView,c);
					panel.add(propertyView);
					
				}
				
				
				prop.getContentPane().add(panel);
				
				
				prop.setSize(800,500);
				prop.setVisible(true);

			}
			else if (symbol instanceof MapGroup)
			{
				Properties property =  ((MapGroup)symbol).getProperties();
				int n = property.size();
				
				panel= new JPanel(gridBag);
				JScrollBar propertyView = new JScrollBar();
				c.fill = GridBagConstraints.BOTH;
				c.weightx = 0.5;
				c.weighty = 0.5;
				c.gridwidth = 1;
				c.gridheight = 1;
				int j = 1;
				
				for (Enumeration t= property.propertyNames();t.hasMoreElements();)
				{
					String key = (String)t.nextElement();
					String value = (String)property.get(key);
					text = new JTextField();
					text1 = new JTextField();
					//System.out.println("key = " + key);
					//System.out.println("value = " + value);
					c.gridx = 5;
					c.gridy = j ;
					text1.setText(key);
					gridBag.setConstraints(text1,c);
					panel.add(text);
					c.gridx = 10;
					text.setText(value);
					gridBag.setConstraints(text,c);
					
					panel.add(text1);
					
					j=j+1;
					gridBag.setConstraints(propertyView,c);
					panel.add(propertyView);
					
				}
				
				
				prop.getContentPane().add(panel);
				
				
				prop.setSize(800,500);
				prop.setVisible(true);

			}
			else if (symbol instanceof MapSymbol)
			{
				Properties property =  ((MapSymbol)symbol).getProperties();
				int n = property.size();
				
				panel= new JPanel(gridBag);
				JScrollBar propertyView = new JScrollBar();
				c.fill = GridBagConstraints.BOTH;
				c.weightx = 0.5;
				c.weighty = 0.5;
				c.gridwidth = 1;
				c.gridheight = 1;
				int j = 1;
				
				for (Enumeration t= property.propertyNames();t.hasMoreElements();)
				{
					String key = (String)t.nextElement();
					String value = (String)property.get(key);
					text = new JTextField();
					text1 = new JTextField();
					//System.out.println("key = " + key);
					//System.out.println("value = " + value);
					c.gridx = 5;
					c.gridy = j ;
					text1.setText(key);
					gridBag.setConstraints(text1,c);
					panel.add(text);
					c.gridx = 10;
					text.setText(value);
					gridBag.setConstraints(text,c);
					
					panel.add(text1);
					
					j=j+1;
					gridBag.setConstraints(propertyView,c);
					panel.add(propertyView);
					
				}
				
				
				prop.getContentPane().add(panel);
				
				
				prop.setSize(800,500);
				prop.setVisible(true);

			}
			
			
		}
		catch (Exception e)
		{
			System.out.println("exception = " + e);
		}
		
	}
	
	public static void main(String arg[])throws Exception
	{
		StateProp e = new StateProp(arg);
	}}