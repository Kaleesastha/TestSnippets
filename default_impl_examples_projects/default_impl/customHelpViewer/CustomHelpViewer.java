/* $Id: CustomHelpViewer.java,v 1.1.1.1 2006/11/16 15:39:58 gramkumar Exp $ */

package test;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import javax.swing.text.html.*;

import java.awt.*;
import java.awt.event.*;

import java.net.*;

import java.io.*;
import java.util.Stack;

import com.adventnet.nms.util.HelpUserClassInterface;
import com.adventnet.nms.util.NmsClientUtil;


/** This class implements the HelpUserClassInterface from the util package <br>
 *  implements the showHelpURL(String,String) method to display the context <br>
 *  sensitive help in a JEditorPane.
 **/


public class CustomHelpViewer implements ActionListener, HelpUserClassInterface
{
    JEditorPane  editPane = null;
    JPanel  southPanel = null;
    JButton  backButton = null;
    JButton loadButton = null;
    JButton closeButton = null;
    JButton refreshButton = null;
    JFrame helpFrame = null;

    JScrollPane scrollPane = null;
    JTextField textField = null;
    Stack backUrls = new Stack();
    URL url = null;
    JMenuBar mbar;
    JMenu file;
    JMenuItem quit;


    public void showHelpURL(String key,String doc)
    {
        try 
        { 
            if(NmsClientUtil.applet.getParameter("WebNMSClient")!=null)
            { 
                String filedoc =null; 
                File root = new File("."); 
                String path = root.getCanonicalPath(); 
                path = path.replace('\\','/');  
                path = path+"/";   
                doc = doc.trim();  
                        
                if(doc.startsWith("../"))  
                {  
                    doc = doc.substring(3); 
                } 
                        
                if(!doc.endsWith(".html"))  
                {  
                    int ptr = doc.indexOf(".html"); 
                    filedoc = doc.substring(0,ptr+5); 
                } 
                else 
                {  
                    filedoc = doc; 
                } 
                        
                File helpFile = new File(path+filedoc); 
                if(helpFile.exists()) 
                {  
                    String urltext = path+doc;
                    url = new URL("file","",urltext);
                    //NmsClientUtil.applet.getAppletContext().showDocument(url, "_blank");
                }  
                else
                {
                    doc = "../"+doc;
                    url = new URL(NmsClientUtil.applet.getDocumentBase(), doc);
                    //NmsClientUtil.applet.getAppletContext().showDocument(url, "_blank");
                }
            }            
        }catch (Exception e)
        {
            System.err.println (NmsClientUtil.GetString ("Cannot open new document:") + e);
        }
    }

    /** This method is provided whenever it is not possible to show the url in 
      * Browser this method will be called to show the URL in JEditorPane in 
      * stand alone Client.
      */
    public void setURLString( String str)
    {
        try
        {
        url = new URL(str);
        createUI();     
        }
        catch( Exception e)
        {
            System.err.println (NmsClientUtil.GetString ("Cannot open new document:") + e);
        }
    }

    private void createUI()
    {
        helpFrame = new JFrame();
        
        helpFrame.getContentPane().setLayout(new BorderLayout());

        southPanel= new JPanel(new BorderLayout());
        JPanel panel1 = new JPanel();
        panel1.setBorder(new EtchedBorder());
        JPanel panel2 = new JPanel();
        panel2.setBorder(new EtchedBorder());
        JPanel panel3 = new JPanel();
        panel3.setBorder(new EtchedBorder());
        backButton= new JButton("Back");
        loadButton = new JButton("Load");
        refreshButton = new JButton("Refresh");
        closeButton = new JButton("Close");
        backButton.addActionListener(this);
        backButton.setMnemonic(KeyEvent.VK_B);
        closeButton.setMnemonic(KeyEvent.VK_C);
        loadButton.addActionListener(this);
        closeButton.addActionListener(this);
        loadButton.setMnemonic(KeyEvent.VK_L);
        refreshButton.setMnemonic(KeyEvent.VK_R);
        refreshButton.addActionListener(this);
        textField = new JTextField("",35);
        panel1.add(textField);
        panel1.add(loadButton);

        editPane = new JEditorPane();
        editPane.setBorder(new EtchedBorder());
        int v=JScrollPane.VERTICAL_SCROLLBAR_ALWAYS;
        int h=JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS;
        scrollPane = new JScrollPane(editPane,v,h);
        HTMLEditorKit editKit = new HTMLEditorKit();

        editPane.setEditorKit(editKit);
        editPane.setEditable(false);

        editPane.addHyperlinkListener(new HyperlinkListener()
            {
                public void hyperlinkUpdate(HyperlinkEvent event)
                {
                    if(event.getEventType() == HyperlinkEvent.EventType.ACTIVATED)
                    {
                        try {
                            textField.setText(event.getURL().toString());
                            editPane.setPage(event.getURL());
                        }
                        catch(IOException ioe)
                        {
                            JOptionPane.showMessageDialog(null,"Error: " + ioe.getMessage());
                            return ;
                        }
                        backUrls.push(event.getURL().toString());
                    }
                }
            });

        panel2.add(refreshButton);
        panel2.add(backButton);
	panel3.add(closeButton);
        
        southPanel.setBorder(new EtchedBorder(1));
        southPanel.add(panel1,BorderLayout.WEST);
        southPanel.add(panel2,BorderLayout.CENTER);
        southPanel.add(panel3,BorderLayout.EAST);

        helpFrame.setTitle("AdventNet WebNMS Help Viewer");

        helpFrame.getContentPane().add(scrollPane,BorderLayout.CENTER);
        helpFrame.getContentPane().add(southPanel,BorderLayout.SOUTH);

        mbar = new JMenuBar();
        file = new JMenu("File");
        file.setMnemonic(KeyEvent.VK_F);
        quit = new JMenuItem("Quit");
        quit.addActionListener(this);
        quit.setMnemonic(KeyEvent.VK_Q);
        file.add(quit);
        mbar.add(file);
        helpFrame.setJMenuBar(mbar);

        Dimension dm = Toolkit.getDefaultToolkit().getScreenSize();
        helpFrame.setBounds((dm.width-800)/2,(dm.height-600)/2,800,600);

        try {
            editPane.setPage(url);
            backUrls.push(url.toString());
            backButton.setEnabled(true);
            textField.setText(url.toString());
        }catch(Exception ex)
        { 
            System.out.println("Error :" + ex.getMessage());
        } 

        helpFrame.addWindowListener(new WindowAdapter()
            {
                public void windowClosing(WindowEvent evt)
                {
                    helpFrame.hide();
                    helpFrame.dispose();
                }
            });
        helpFrame.show();
        Image frIcon = NmsClientUtil.getFrameIcon();
        helpFrame.setIconImage(frIcon);
    }

    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource().equals(backButton))
        {
            try 
            {
                if (! backUrls.empty())
                {
                    if (backUrls.size() == 1)
                    {
                        JOptionPane.showMessageDialog(null,"No more pages..");
                    }
                    else
                    {
                        if (backUrls.size() >= 1)
                        {
                            url =new URL((String) backUrls.pop());
                        }
                        System.out.println(" BACK : " + backUrls);
                        url =new URL((String) backUrls.peek());
                        editPane.setPage(url);
                        textField.setText(url.toString());
                    }
                }
            }catch(Exception e)
            {
                System.out.println(" Exception Occured :" + e.getMessage());
            }
        }

        if(ae.getSource().equals(loadButton))
        {
            try{
                String urlstr = textField.getText();
                URL url = new URL(urlstr);
                editPane.setPage(url);
                backUrls.push(url.toString());
            }catch(Exception excp)
            {
                JOptionPane.showMessageDialog(null,"Error : " + excp.getMessage());
            }
        }

        if(ae.getSource().equals(refreshButton))
        {
            try {
                String urlstr = null;
                urlstr = textField.getText();

                if (urlstr != null)
                {
                    url = new URL(urlstr);
                    editPane.setPage(url);
                    //backUrls.push(url.toString());               
                }
            }catch(Exception exp)
            {
                JOptionPane.showMessageDialog(null,"Error : " + exp.getMessage());
            }
        }

        if(ae.getSource().equals(quit) || ae.getSource().equals(closeButton))
        {
            helpFrame.hide();
            helpFrame.dispose();
        }
    }
}























