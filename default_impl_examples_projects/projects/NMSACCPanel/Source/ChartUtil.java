// $Id: ChartUtil.java,v 1.1 2006/08/29 13:57:01 build Exp $
package com.adventnet.nms.alertui.accpanel;

import java.awt.*;
import java.io.*;
import java.awt.image.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.net.URL;

public class ChartUtil
{
	public static boolean DEBUG = true;

	public static String getString(String op)
	{
		return op;
	}

	public static void centerWindow( Window win )
	{
		Dimension scrSize = Toolkit.getDefaultToolkit().getScreenSize() ;
		int x = (scrSize.width - win.getWidth()) / 2 ;
		int y = (scrSize.height - win.getHeight()) / 2 ;
		win.setLocation( x,y );
	}

	public static ImageIcon getImage(String imageName)
	{
		if (imageName == null || imageName.equals(""))
		{
			return null;
		}

		ImageIcon imageIcon = null;
		URL imageURL = null;

		// Get the image from the jars in classpath
		imageURL = ChartUtil.class.getResource( "/" +imageName );
		if ( imageURL != null )
		{
			String urlString = imageURL.toString();
			imageIcon = new ImageIcon(java.awt.Toolkit.getDefaultToolkit().getImage(imageURL));
		}
		return imageIcon;
	}

	/*
	* Saves the 'saveableComponent' in a .png or .jpg file.
	*/
	public static void saveComponent(Component parent, Component saveableComponent)
	{
		JFileChooser chooser = new JFileChooser();
		chooser.setAcceptAllFileFilterUsed(false);

		SingleTypeFileFilter filter = new SingleTypeFileFilter();
		filter.setExtension("jpg");
		chooser.addChoosableFileFilter(filter);
		filter = new SingleTypeFileFilter();
		filter.setExtension("png");
		chooser.addChoosableFileFilter(filter);

		int returnVal = chooser.showSaveDialog(parent);
		if(returnVal == JFileChooser.CANCEL_OPTION)
		{
			return; 
		}
		String fileName = chooser.getSelectedFile().toString();
		SingleTypeFileFilter seleFilter = (SingleTypeFileFilter ) chooser.getFileFilter(); 
		String seleFormat = seleFilter.getExtension(); 
		if(fileName == null || fileName.trim().equals("") || seleFormat == null || seleFormat.trim().equals(""))
		{
		     return; 
		}
		int width = saveableComponent.getWidth();
		int height = saveableComponent.getHeight();
		String oriFileName = fileName;

		if(oriFileName.indexOf(".") == -1)
		{
			oriFileName += "."+seleFormat;
		}
		File oriFile = new File(oriFileName);
		if(oriFile.exists())
		{
			int overWrite = JOptionPane.showConfirmDialog(parent, oriFileName+ChartUtil.getString(" already exists. Do you want to save?"), ChartUtil.getString("NMS"), JOptionPane.YES_NO_OPTION);
			if(overWrite == JOptionPane.NO_OPTION) return;
		}

		BufferedImage image = new BufferedImage(width,height,1);
		Graphics grap = image.getGraphics();
		saveableComponent.paint(grap);
		try
		{
			FileOutputStream imageFile = new FileOutputStream(oriFileName);
			if(seleFormat.equals("png"))
			{
				org.jfree.chart.ChartUtilities.writeBufferedImageAsPNG(imageFile, image);
			}
			else if(seleFormat.equals("jpg"))
			{
				org.jfree.chart.ChartUtilities.writeBufferedImageAsJPEG(imageFile, image);
			}
			imageFile.close();
		}
		catch(FileNotFoundException e)
		{
			JOptionPane.showMessageDialog(parent, ChartUtil.getString("Specified file is not found")+oriFileName, ChartUtil.getString("File not found"), JOptionPane.ERROR_MESSAGE);
		}
		catch(IOException e)
		{
			JOptionPane.showMessageDialog(parent, ChartUtil.getString("Problem in writing into the file")+oriFileName, ChartUtil.getString("Error in file writing"), JOptionPane.ERROR_MESSAGE);
		}
	}

	static class SingleTypeFileFilter extends javax.swing.filechooser.FileFilter
	{
		String extension;
		public boolean accept(File file)
		{
			if(file.toString().trim().endsWith(extension) || file.isDirectory())
			{
				return true;
			}
			return false;
		}

		public String getDescription()
		{
			return "."+extension;
		}

		public void setExtension(String ext)
		{
			extension = ext;
		}

		public String getExtension()
		{
			return extension;
		}
	}
}

