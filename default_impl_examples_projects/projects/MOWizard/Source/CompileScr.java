//$:Id$
package com.adventnet.nms.tools.mowizard;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.applet.*;
import com.adventnet.nms.tools.utils.PrintToTextArea;
import org.w3c.dom.Element;
import org.w3c.dom.Document;
import javax.xml.parsers.*;
import java.util.StringTokenizer;
import com.adventnet.apiutils.BuilderResourceBundle;
import com.adventnet.nms.tools.utils.ToolsUtil;
public class CompileScr extends JDialog implements ActionListener
{
    //<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	javax.swing.JPanel  Top = null;
	javax.swing.JPanel  okCancelPanel = null;
	javax.swing.JButton  okButton = null;
	javax.swing.JScrollPane  errorScroller = null;
	javax.swing.JTextArea  errorArea = null;
	//<End_Variable_Declarations>
    String Classpath=null;
    String SourcePath=null;
    String JDKPath=null;
    String ExtDir=null;
    String OutputDir=null;
    String Encoding=null;
    String Target=null;
    int compileStatus=0;
	private BuilderResourceBundle resourceBundle=ToolsUtil.getBundle();
    public CompileScr()
  {
        //<Begin_CompileScr>
    pack();
    this.setTitle(resourceBundle.getString("CompileScr"));
  
    //<End_CompileScr>
        this.addWindowListener(new WindowAdapter(){
                public void windowClosing(WindowEvent we){
                    dispose();
                }
            });
    }
    

    public CompileScr(JFrame parent,String title) {
        super(parent,title,true);
    }
    
    public CompileScr(java.applet.Applet applet)
  {
        //<Begin_CompileScr_java.applet.Applet>
    this.applet = applet;
    pack();
    this.setTitle("CompileScr");
    this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
  
    //<End_CompileScr_java.applet.Applet>
    }

    public void setUpProperties()throws Exception
  { 
        //<Begin_setUpProperties>

          try
          {
            okButton.setLabel(resourceBundle.getString("Ok"));
            okButton.setBorder(new javax.swing.border.BevelBorder(0));
            okButton.setText(resourceBundle.getString("OK"));
            okButton.setActionCommand("Ok");
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+okButton,ex); 
          }

          try
          {
            errorArea.setSelectionColor(new Color(-13434829));
            errorArea.setBorder(new javax.swing.border.TitledBorder(resourceBundle.getString("Compile Status")));
            errorArea.setSelectedTextColor(new Color(-1));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+errorArea,ex); 
          }
		okButton.setPreferredSize(new Dimension(okButton.getPreferredSize().width+58,okButton.getPreferredSize().height+6));
		okCancelPanel.setPreferredSize(new Dimension(okCancelPanel.getPreferredSize().width+105,okCancelPanel.getPreferredSize().height+0));

  //<End_setUpProperties>
    } 
    public void init()
  { 
        //<Begin_init>
        if (initialized == true) return; 
        this.setSize(getPreferredSize().width+723,getPreferredSize().height+486); 
          setTitle("new_screen1");
        Container container = getContentPane();
        container.setLayout(new BorderLayout()); 
        try 
        { 
          initVariables(); 
          setUpGUI(container); 
          setUpProperties(); 
          setUpConnections(); 
        } 
        catch(Exception ex) 
        { 
          showStatus(resourceBundle.getString("Error in init method"),ex); 
        } 
        // let us set the initialzed variable to true so  
        // we dont initialize again even if init is called 
        initialized = true; 
      setUpMenus();
      setUpToolBar();
        

  //<End_init>
		setTitle(resourceBundle.getString("Compile Status"));
    } 
    public void setUpConnections()throws Exception
  { 
        //<Begin_setUpConnections>

  //<End_setUpConnections>
    } 
  
    public void initVariables()throws Exception
  { 
        //<Begin_initVariables>
        Top= new javax.swing.JPanel();
        okCancelPanel= new javax.swing.JPanel();
        okButton= new javax.swing.JButton();
        errorScroller= new javax.swing.JScrollPane();
        errorArea= new javax.swing.JTextArea();

  //<End_initVariables>
	okButton.setMnemonic(KeyEvent.VK_O);
	errorArea.setEditable(false);
	errorArea.setWrapStyleWord(true);
	errorArea.setLineWrap(true);
	okButton.addActionListener(this);
	//errorArea.setBackground(Color.blue);
    } 
    public void setUpToolBar()
  { 

        //<Begin_setUpToolBar>

  //<End_setUpToolBar>
    } 
    public void setUpGUI(Container container)throws Exception
  { 

        //<Begin_setUpGUI_Container>
container.add(Top,BorderLayout.CENTER);
	Top.setLayout(new BorderLayout(5,5));
	Top.add(okCancelPanel,BorderLayout.SOUTH);
	okCancelPanel.setLayout(new FlowLayout(2,10,5));
	okCancelPanel.add(okButton);
	Top.add(errorScroller,BorderLayout.CENTER);
	errorScroller.getViewport().add(errorArea);
	
  //<End_setUpGUI_Container>
    } 
    public void setUpMenus()
  { 

        //<Begin_setUpMenus>

  //<End_setUpMenus>
    } 
    public void stop()
  { 

        //<Begin_stop>

  //<End_stop>
    } 
    public void start()
  { 

        //<Begin_start>

  //<End_start>
    } 
    public String getParameter(String input)
  { 

        //<Begin_getParameter_String>
           String value = null;
           if ( applet != null)
           {    
                 value = applet.getParameter(input);
           }    
           else
           {    
                 value = (String)com.adventnet.apiutils.Utility.getParameter(input);
           }    
           if(value == null)
           {
            if (input.equals("HOST")) value = "localhost"; 
            if (input.equals("PORT")) value = "161"; 
            }
        return value;

  //<End_getParameter_String>
    }

	public Point getScrLoc(Component comp) {
		// This method will return the x,y that will place a container in the center 
		// of the screen. 
		Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
		int width=(int)(d.getWidth()-comp.getSize().getWidth())/2;
		int height=(int)(d.getHeight()-comp.getSize().getHeight())/2;
		return new Point(width,height);
	}

	
    public void showStatus(String message)
  {
        //<Begin_showStatus_String>
     System.out.println(resourceBundle.getString("Internal Error :")+ message);
     //<End_showStatus_String>
    }
    public void showStatus(String message,Exception ex)
  {
        //<Begin_showStatus_String_Exception>
     System.out.println(resourceBundle.getString("Internal Error :")+ message);
     ex.printStackTrace();
     //<End_showStatus_String_Exception>
    }

    public void setVisible(boolean bl)
  {
        //<Begin_setVisible_boolean>
       	if(bl)
       	{
       	  init();
          start();
        }
        else
        {
          stop();
        }
        super.setVisible(bl);
  
        //<End_setVisible_boolean>
    }
    public void setTextArea(JTextArea textStatus)
    {
        errorArea=textStatus;
    }

    public void clearText(){
        errorArea.setText(" ");
    }

    public void compileClicked(String filepath,String jdkPath,String classPath,String outputDir)
    {
        Document doc=null;	
        File f=new File(System.getProperty("user.dir")+File.separator+"projects"+File.separator+"coptions.xml");
        if(!f.exists()){
            JOptionPane.showMessageDialog(null,resourceBundle.getString("Please enter the compile options"),resourceBundle.getString("Message"),JOptionPane.OK_OPTION);
            COptions fw=new COptions(this,null);
            fw.init();
			fw.setLocation(getScrLoc(fw));
            fw.setVisible(true);
            return;
        }    
		Classpath=classPath+System.getProperty("path.separator")+"."+File.separator+"classes";
		JDKPath=jdkPath;
		OutputDir=outputDir;
		if(System.getProperty("os.name").toLowerCase().startsWith("win")) {
			filepath="\""+filepath+"\"";
		}
        String cArgs="  ";
        cArgs=cArgs + JDKPath.trim()+File.separator+"bin"+File.separator+"javac";
        if(Classpath.trim().length()!=0) {
			if(System.getProperty("os.name").toLowerCase().startsWith("win")){
				StringTokenizer stk=new StringTokenizer(Classpath.trim(),";");
				String curTok="";
				Classpath="";

				while(stk.hasMoreTokens()){
					curTok="\""+stk.nextToken()+"\";";
					Classpath=Classpath + curTok;
				}
			}
			cArgs = cArgs+ " -classpath "+Classpath.trim();
        }	
		if(System.getProperty("os.name").toLowerCase().startsWith("win")){
			cArgs=cArgs+" -d  " +"\"" +OutputDir+"\"";
		}
		else{
			cArgs = cArgs + " -d  "+OutputDir;
		}
			errorArea.setText(resourceBundle.getString("Compiling Source file ")+filepath+"\n");
        WorkerThread wth=new WorkerThread(cArgs+"  "+filepath);
    }  

    class WorkerThread extends Thread {
        String compileTimeArgs;
        WorkerThread(String compileArgs) {
            compileTimeArgs=compileArgs;
            this.start();
        }

        public void run() {
            try {
                Process compileProc= Runtime.getRuntime().exec(compileTimeArgs);
                InputStream compStatus=compileProc.getErrorStream();
                PrintToTextArea pttt=new PrintToTextArea(errorArea);
                int ch=0;
                while((ch=compStatus.read())!=-1) {
                    pttt.print((char)ch);
                }
				while(true) {
					try {
						if((compileStatus=compileProc.exitValue())==0) {
							errorArea.setText(errorArea.getText()+"\n"+resourceBundle.getString("Successfully Compiled"));
							break;
						}
						else if( (compileStatus=compileProc.exitValue())!=0) {
							break;
						}
					}
					catch(IllegalThreadStateException itse) {
					}
				}
            }
            catch(IOException ioe) {
                JOptionPane.showMessageDialog(null,resourceBundle.getString("Error occurred while compiling ")+ioe.getMessage(),resourceBundle.getString("Error"),JOptionPane.ERROR_MESSAGE);
                return;
            }
            catch(SecurityException ioe) {
                JOptionPane.showMessageDialog(null,resourceBundle.getString("Security Error occurred while compiling ")+ioe.getMessage(),resourceBundle.getString("Security Error"),JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
    }
  
    public int getCompileStatus(){
        return compileStatus;
    }
	
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource()==okButton){
            this.dispose();
        }
    }
}

