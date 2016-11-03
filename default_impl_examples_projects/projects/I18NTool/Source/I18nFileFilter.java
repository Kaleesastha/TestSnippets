// $Id: I18nFileFilter.java,v 1.1.1.1 2006/12/08 11:57:39 gramkumar Exp $
 package com.adventnet.management.i18n.tools ; 


 import java.awt.*;
 import java.awt.event.*;
 import java.io.*;
 import javax.swing.*;
 import javax.swing.event.*;
 import javax.swing.filechooser.FileFilter;

 public class I18nFileFilter extends FileFilter
 {
     java.util.ResourceBundle resourceBundle = null;

     public I18nFileFilter()
     {
     }

     public I18nFileFilter(java.util.ResourceBundle bundle)
     {
         resourceBundle = bundle;
     }
   
	public boolean accept(File f)
	{
		if(f.isDirectory())
		{
			return true;
		}	
		String extension = getExtension(f);
		if(extension.equals("properties"))
		{
			return true;
		}	
		else if(extension.equals("txt"))
		{
			return true;
		}	
		
		return false;
	
	}
		
	public String getDescription()
	{
            return resourceBundle.getString("Properties and text files only");
	}	
		
     private String getExtension(File f)
	{
		String ext = "";
		ext = f.toString();
		
		int index = ext.lastIndexOf('.');
		ext = ext.substring(index+1);
		return ext;
		
	}	
	
	
 }




