// $Id: RediscoveryScheduler.java,v 1.2 2010/10/29 13:46:41 swaminathap Exp $
// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// For more details please see EditingSourceFiles.html present in the docs directory

//<Begin_Version>
//version$95
//<End_Version>
package com.adventnet.nms.runtimeconfig;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.applet.*;
import java.lang.*;

import com.adventnet.nms.util.XMLNode;
import com.adventnet.nms.util.SeedParser;

public class RediscoveryScheduler extends JDialog implements ActionListener
{
    //<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	private String localePropertiesFileName = "RuntimeAdministrationResources";
	static com.adventnet.apiutils.BuilderResourceBundle resourceBundle = null;
	private boolean running=false;
	javax.swing.JPanel Top = null;
	javax.swing.JPanel mainPanel = null;
	javax.swing.JPanel JPanel1 = null;
	javax.swing.JRadioButton rbRegInt = null;
	javax.swing.JRadioButton rbDays = null;
	javax.swing.JRadioButton rbDates = null;
	javax.swing.JScrollPane JScrollPane1 = null;
	javax.swing.JPanel mainCardPanel = null;
	javax.swing.JPanel userSchPanel = null;
	javax.swing.JPanel JPanel31 = null;
	javax.swing.JButton udokButton = null;
	javax.swing.JButton udcancelButton = null;
	javax.swing.JLabel lfHTML1 = null;
	javax.swing.JPanel JPanel2 = null;
	javax.swing.JPanel innerCardPanel = null;
	javax.swing.JPanel daysPanel = null;
	javax.swing.JPanel dayButPanel = null;
	javax.swing.JLabel JLabel32 = null;
	javax.swing.JLabel JLabel33 = null;
	javax.swing.JLabel JLabel34 = null;
	javax.swing.JLabel JLabel35 = null;
	javax.swing.JLabel JLabel36 = null;
	javax.swing.JLabel JLabel37 = null;
	javax.swing.JLabel JLabel38 = null;
	javax.swing.JPanel daysOptPanel = null;
	javax.swing.JLabel Dates11 = null;
	javax.swing.JRadioButton rbAllDays = null;
	javax.swing.JRadioButton rbSpecificDays = null;
	javax.swing.JPanel datesPanel = null;
	javax.swing.JPanel dateButPanel = null;
	javax.swing.JLabel JLabel1 = null;
	javax.swing.JLabel JLabel2 = null;
	javax.swing.JLabel JLabel3 = null;
	javax.swing.JLabel JLabel4 = null;
	javax.swing.JLabel JLabel5 = null;
	javax.swing.JLabel JLabel6 = null;
	javax.swing.JLabel JLabel7 = null;
	javax.swing.JLabel JLabel8 = null;
	javax.swing.JLabel JLabel9 = null;
	javax.swing.JLabel JLabel10 = null;
	javax.swing.JLabel JLabel11 = null;
	javax.swing.JLabel JLabel12 = null;
	javax.swing.JLabel JLabel13 = null;
	javax.swing.JLabel JLabel14 = null;
	javax.swing.JLabel JLabel15 = null;
	javax.swing.JLabel JLabel16 = null;
	javax.swing.JLabel JLabel17 = null;
	javax.swing.JLabel JLabel18 = null;
	javax.swing.JLabel JLabel19 = null;
	javax.swing.JLabel JLabel20 = null;
	javax.swing.JLabel JLabel21 = null;
	javax.swing.JLabel JLabel22 = null;
	javax.swing.JLabel JLabel23 = null;
	javax.swing.JLabel JLabel24 = null;
	javax.swing.JLabel JLabel25 = null;
	javax.swing.JLabel JLabel26 = null;
	javax.swing.JLabel JLabel27 = null;
	javax.swing.JLabel JLabel28 = null;
	javax.swing.JLabel JLabel29 = null;
	javax.swing.JLabel JLabel30 = null;
	javax.swing.JLabel JLabel31 = null;
	javax.swing.JPanel daysOptPanel1 = null;
	javax.swing.JLabel Dates111 = null;
	javax.swing.JRadioButton rbAllDates = null;
	javax.swing.JRadioButton rbSpecificDates = null;
	javax.swing.JPanel hoursPanel = null;
	javax.swing.JPanel hoursOptPanel = null;
	javax.swing.JLabel Dates1 = null;
	javax.swing.JRadioButton rbAllHours = null;
	javax.swing.JRadioButton rbSpecificHours = null;
	javax.swing.JPanel hourButPanel = null;
	javax.swing.JLabel lfHr0 = null;
	javax.swing.JLabel lfHr1 = null;
	javax.swing.JLabel lfHr2 = null;
	javax.swing.JLabel lfHr3 = null;
	javax.swing.JLabel lfHr4 = null;
	javax.swing.JLabel lfHr5 = null;
	javax.swing.JLabel lfHr6 = null;
	javax.swing.JLabel lfHr7 = null;
	javax.swing.JLabel lfHr8 = null;
	javax.swing.JLabel lfHr9 = null;
	javax.swing.JLabel lfHr10 = null;
	javax.swing.JLabel lfHr11 = null;
	javax.swing.JLabel lfHr12 = null;
	javax.swing.JLabel lfHr13 = null;
	javax.swing.JLabel lfHr14 = null;
	javax.swing.JLabel lfHr15 = null;
	javax.swing.JLabel lfHr16 = null;
	javax.swing.JLabel lfHr17 = null;
	javax.swing.JLabel lfHr18 = null;
	javax.swing.JLabel lfHr19 = null;
	javax.swing.JLabel lfHr20 = null;
	javax.swing.JLabel lfHr21 = null;
	javax.swing.JLabel lfHr22 = null;
	javax.swing.JLabel lfHr23 = null;
	javax.swing.JPanel regIntPanel = null;
	javax.swing.JLabel lfImage = null;
	javax.swing.JPanel innerPanel = null;
	javax.swing.JLabel lfHTML = null;
	javax.swing.JPanel lfEmpty1 = null;
	javax.swing.JLabel minSecSepLabel = null;
	javax.swing.JLabel ndSec = null;
	javax.swing.JLabel hrMinSepLabel = null;
	javax.swing.JLabel ndMins = null;
	javax.swing.JLabel minSecSep = null;
	javax.swing.JLabel hrMinSep = null;
	javax.swing.JLabel ndHours = null;
	javax.swing.JLabel netDiscInterval = null;
	com.adventnet.beans.utilbeans.NumericSpinControl tfRediscIntMin = null;
	com.adventnet.beans.utilbeans.NumericSpinControl tfRediscIntSec = null;
	com.adventnet.beans.utilbeans.NumericSpinControl tfRediscInt = null;
	javax.swing.JPanel JPanel3 = null;
	javax.swing.JButton okButton = null;
	javax.swing.JButton cancelButton = null;
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
	//<End_Variable_Declarations>
    SeedParser sParser = null;
    ButtonGroup bGroup1 = null;
    ButtonGroup bGroup2 = null;
    ButtonGroup bGroup3 = null;
    ButtonGroup bGroup4 = null;
    ButtonGroup bGroup5 = null;
    String RediscoveryInterval;
    boolean activateMouseListener = false;
    boolean isOKclicked = false;
    boolean isModified = false;
    XMLNode tempNode = null;
    static int count =0;


    public RediscoveryScheduler(SeedParser parser,JFrame frame)
    {
        //mtest	       resourceBundle = com.adventnet.apiutils.Utility.getBundle(getParameter("RESOURCE_PROPERTIES"),applet);
        super(frame,true);
        sParser = parser;
        pack();
    }

    public RediscoveryScheduler()
  {
        //<Begin_RediscoveryScheduler>
    pack();
  
    //<End_RediscoveryScheduler>
    }

    public RediscoveryScheduler(java.applet.Applet applet)
  {
        //<Begin_RediscoveryScheduler_java.applet.Applet>
    this.applet = applet;
    pack();
    this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
  
    //<End_RediscoveryScheduler_java.applet.Applet>
    }

 
    public void start()
  { 

        //<Begin_start> 
       if(running)
 return;
 running=true;

  
       //<End_start>
		populatingScreen();
       	populatingMonth();
       	populatingWeek();
       	populatingHour();
    }

    public boolean isOK_Clicked()
    {
        return isOKclicked;
    }
 
    public void initVariables()
  { 

        //<Begin_initVariables> 
        Top= new javax.swing.JPanel();
        mainPanel= new javax.swing.JPanel();
        JPanel1= new javax.swing.JPanel();
        rbRegInt= new javax.swing.JRadioButton();
        rbDays= new javax.swing.JRadioButton();
        rbDates= new javax.swing.JRadioButton();
        JScrollPane1= new javax.swing.JScrollPane();
        mainCardPanel= new javax.swing.JPanel();
        userSchPanel= new javax.swing.JPanel();
        JPanel31= new javax.swing.JPanel();
        udokButton= new javax.swing.JButton();
        udcancelButton= new javax.swing.JButton();
        lfHTML1= new javax.swing.JLabel();
        JPanel2= new javax.swing.JPanel();
        innerCardPanel= new javax.swing.JPanel();
        daysPanel= new javax.swing.JPanel();
        dayButPanel= new javax.swing.JPanel();
        JLabel32= new javax.swing.JLabel();
        JLabel33= new javax.swing.JLabel();
        JLabel34= new javax.swing.JLabel();
        JLabel35= new javax.swing.JLabel();
        JLabel36= new javax.swing.JLabel();
        JLabel37= new javax.swing.JLabel();
        JLabel38= new javax.swing.JLabel();
        daysOptPanel= new javax.swing.JPanel();
        Dates11= new javax.swing.JLabel();
        rbAllDays= new javax.swing.JRadioButton();
        rbSpecificDays= new javax.swing.JRadioButton();
        datesPanel= new javax.swing.JPanel();
        dateButPanel= new javax.swing.JPanel();
        JLabel1= new javax.swing.JLabel();
        JLabel2= new javax.swing.JLabel();
        JLabel3= new javax.swing.JLabel();
        JLabel4= new javax.swing.JLabel();
        JLabel5= new javax.swing.JLabel();
        JLabel6= new javax.swing.JLabel();
        JLabel7= new javax.swing.JLabel();
        JLabel8= new javax.swing.JLabel();
        JLabel9= new javax.swing.JLabel();
        JLabel10= new javax.swing.JLabel();
        JLabel11= new javax.swing.JLabel();
        JLabel12= new javax.swing.JLabel();
        JLabel13= new javax.swing.JLabel();
        JLabel14= new javax.swing.JLabel();
        JLabel15= new javax.swing.JLabel();
        JLabel16= new javax.swing.JLabel();
        JLabel17= new javax.swing.JLabel();
        JLabel18= new javax.swing.JLabel();
        JLabel19= new javax.swing.JLabel();
        JLabel20= new javax.swing.JLabel();
        JLabel21= new javax.swing.JLabel();
        JLabel22= new javax.swing.JLabel();
        JLabel23= new javax.swing.JLabel();
        JLabel24= new javax.swing.JLabel();
        JLabel25= new javax.swing.JLabel();
        JLabel26= new javax.swing.JLabel();
        JLabel27= new javax.swing.JLabel();
        JLabel28= new javax.swing.JLabel();
        JLabel29= new javax.swing.JLabel();
        JLabel30= new javax.swing.JLabel();
        JLabel31= new javax.swing.JLabel();
        daysOptPanel1= new javax.swing.JPanel();
        Dates111= new javax.swing.JLabel();
        rbAllDates= new javax.swing.JRadioButton();
        rbSpecificDates= new javax.swing.JRadioButton();
        hoursPanel= new javax.swing.JPanel();
        hoursOptPanel= new javax.swing.JPanel();
        Dates1= new javax.swing.JLabel();
        rbAllHours= new javax.swing.JRadioButton();
        rbSpecificHours= new javax.swing.JRadioButton();
        hourButPanel= new javax.swing.JPanel();
        lfHr0= new javax.swing.JLabel();
        lfHr1= new javax.swing.JLabel();
        lfHr2= new javax.swing.JLabel();
        lfHr3= new javax.swing.JLabel();
        lfHr4= new javax.swing.JLabel();
        lfHr5= new javax.swing.JLabel();
        lfHr6= new javax.swing.JLabel();
        lfHr7= new javax.swing.JLabel();
        lfHr8= new javax.swing.JLabel();
        lfHr9= new javax.swing.JLabel();
        lfHr10= new javax.swing.JLabel();
        lfHr11= new javax.swing.JLabel();
        lfHr12= new javax.swing.JLabel();
        lfHr13= new javax.swing.JLabel();
        lfHr14= new javax.swing.JLabel();
        lfHr15= new javax.swing.JLabel();
        lfHr16= new javax.swing.JLabel();
        lfHr17= new javax.swing.JLabel();
        lfHr18= new javax.swing.JLabel();
        lfHr19= new javax.swing.JLabel();
        lfHr20= new javax.swing.JLabel();
        lfHr21= new javax.swing.JLabel();
        lfHr22= new javax.swing.JLabel();
        lfHr23= new javax.swing.JLabel();
        regIntPanel= new javax.swing.JPanel();
        lfImage= new javax.swing.JLabel();
        innerPanel= new javax.swing.JPanel();
        lfHTML= new javax.swing.JLabel();
        lfEmpty1= new javax.swing.JPanel();
        minSecSepLabel= new javax.swing.JLabel();
        ndSec= new javax.swing.JLabel();
        hrMinSepLabel= new javax.swing.JLabel();
        ndMins= new javax.swing.JLabel();
        minSecSep= new javax.swing.JLabel();
        hrMinSep= new javax.swing.JLabel();
        ndHours= new javax.swing.JLabel();
        netDiscInterval= new javax.swing.JLabel();
        tfRediscIntMin= new com.adventnet.beans.utilbeans.NumericSpinControl();
        tfRediscIntSec= new com.adventnet.beans.utilbeans.NumericSpinControl();
        tfRediscInt= new com.adventnet.beans.utilbeans.NumericSpinControl();
        JPanel3= new javax.swing.JPanel();
        okButton= new javax.swing.JButton();
        cancelButton= new javax.swing.JButton();

  
        //<End_initVariables>
        bGroup1 = new javax.swing.ButtonGroup();
        bGroup2 = new javax.swing.ButtonGroup();
        bGroup3 = new javax.swing.ButtonGroup();
        bGroup4 = new javax.swing.ButtonGroup();
        bGroup5 = new javax.swing.ButtonGroup();
//        hourSpinBox.setMin();
    } 

    public void setConstraints(int x,int y,int width,int height,double wtX,double wtY,int anchor,int fill,Insets inset,int padX,int padY )
  { 

        //<Begin_setConstraints_int_int_int_int_double_double_int_int_Insets_int_int> 
	cons.gridx = x;
	cons.gridy = y;
	cons.gridwidth = width;
	cons.gridheight = height;
	cons.weightx = wtX;
	cons.weighty = wtY;
	cons.anchor = anchor;
	cons.fill = fill;
	cons.insets = inset;
	cons.ipadx = padX;
	cons.ipady = padY;
	
  
         //<End_setConstraints_int_int_int_int_double_double_int_int_Insets_int_int>
    } 
     
    public void setUpGUI(Container container)
  { 

        //<Begin_setUpGUI_Container> 
container.add(Top,BorderLayout.CENTER);
Top.setLayout(new CardLayout(5,5));
Top.add(mainPanel,"mainPanel");
mainPanel.setLayout(new GridBagLayout());
inset = new Insets(0,0,0,0);
setConstraints(0,0,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
mainPanel.add(JPanel1,cons);
JPanel1.setLayout(new GridBagLayout());
inset = new Insets(0,0,0,0);
setConstraints(0,0,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
JPanel1.add(rbRegInt,cons);
inset = new Insets(0,0,0,0);
setConstraints(2,0,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
JPanel1.add(rbDays,cons);
inset = new Insets(0,0,0,0);
setConstraints(1,0,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
JPanel1.add(rbDates,cons);
inset = new Insets(0,0,0,0);
setConstraints(0,1,1,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
mainPanel.add(JScrollPane1,cons);
JScrollPane1.getViewport().add(mainCardPanel);
mainCardPanel.setLayout(new CardLayout(5,5));
mainCardPanel.add(userSchPanel,"userSchPanel");
userSchPanel.setLayout(new GridBagLayout());
inset = new Insets(0,0,0,0);
setConstraints(1,2,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
userSchPanel.add(JPanel31,cons);
JPanel31.setLayout(new GridBagLayout());
inset = new Insets(0,0,0,0);
setConstraints(0,0,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
JPanel31.add(udokButton,cons);
inset = new Insets(0,0,0,0);
setConstraints(1,0,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
JPanel31.add(udcancelButton,cons);
inset = new Insets(0,0,0,0);
setConstraints(1,0,1,1,0.0,0.0,cons.CENTER,cons.BOTH,inset,0,0);
userSchPanel.add(lfHTML1,cons);
inset = new Insets(0,0,0,0);
setConstraints(1,1,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
userSchPanel.add(JPanel2,cons);
JPanel2.setLayout(new GridBagLayout());
inset = new Insets(0,0,0,0);
setConstraints(0,0,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
JPanel2.add(innerCardPanel,cons);
innerCardPanel.setLayout(new CardLayout(1,1));
innerCardPanel.add(daysPanel,"daysPanel");
daysPanel.setLayout(new GridBagLayout());
inset = new Insets(0,0,0,0);
setConstraints(0,1,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,60,0);
daysPanel.add(dayButPanel,cons);
dayButPanel.setLayout(new GridBagLayout());
inset = new Insets(0,0,15,0);
setConstraints(2,0,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
dayButPanel.add(JLabel32,cons);
inset = new Insets(0,0,15,0);
setConstraints(4,0,1,1,0.0,0.0,cons.CENTER,cons.BOTH,inset,0,0);
dayButPanel.add(JLabel33,cons);
inset = new Insets(0,0,15,0);
setConstraints(6,0,1,1,0.0,0.0,cons.CENTER,cons.BOTH,inset,0,0);
dayButPanel.add(JLabel34,cons);
inset = new Insets(15,0,0,0);
setConstraints(1,1,1,1,0.0,0.0,cons.CENTER,cons.BOTH,inset,0,0);
dayButPanel.add(JLabel35,cons);
inset = new Insets(15,0,0,0);
setConstraints(3,1,1,1,0.0,0.0,cons.CENTER,cons.BOTH,inset,0,0);
dayButPanel.add(JLabel36,cons);
inset = new Insets(15,0,0,0);
setConstraints(5,1,1,1,0.0,0.0,cons.CENTER,cons.BOTH,inset,0,0);
dayButPanel.add(JLabel37,cons);
inset = new Insets(0,0,15,0);
setConstraints(0,0,1,1,0.0,0.0,cons.CENTER,cons.BOTH,inset,0,0);
dayButPanel.add(JLabel38,cons);
inset = new Insets(35,0,0,0);
setConstraints(0,0,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
daysPanel.add(daysOptPanel,cons);
daysOptPanel.setLayout(new GridBagLayout());
inset = new Insets(0,0,0,15);
setConstraints(0,0,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
daysOptPanel.add(Dates11,cons);
inset = new Insets(0,0,0,0);
setConstraints(1,0,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
daysOptPanel.add(rbAllDays,cons);
inset = new Insets(0,0,0,0);
setConstraints(2,0,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
daysOptPanel.add(rbSpecificDays,cons);
innerCardPanel.add(datesPanel,"datesPanel");
datesPanel.setLayout(new GridBagLayout());
inset = new Insets(0,0,0,0);
setConstraints(0,1,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
datesPanel.add(dateButPanel,cons);
dateButPanel.setLayout(new GridBagLayout());
inset = new Insets(0,10,5,10);
setConstraints(0,0,1,1,0.0,0.0,cons.CENTER,cons.BOTH,inset,0,0);
dateButPanel.add(JLabel1,cons);
inset = new Insets(0,10,5,10);
setConstraints(1,0,1,1,0.0,0.0,cons.CENTER,cons.BOTH,inset,0,0);
dateButPanel.add(JLabel2,cons);
inset = new Insets(0,10,5,10);
setConstraints(2,0,1,1,0.0,0.0,cons.CENTER,cons.BOTH,inset,0,0);
dateButPanel.add(JLabel3,cons);
inset = new Insets(0,10,5,10);
setConstraints(3,0,1,1,0.0,0.0,cons.CENTER,cons.BOTH,inset,0,0);
dateButPanel.add(JLabel4,cons);
inset = new Insets(0,10,5,10);
setConstraints(4,0,1,1,0.0,0.0,cons.CENTER,cons.BOTH,inset,0,0);
dateButPanel.add(JLabel5,cons);
inset = new Insets(0,10,5,10);
setConstraints(5,0,1,1,0.0,0.0,cons.CENTER,cons.BOTH,inset,0,0);
dateButPanel.add(JLabel6,cons);
inset = new Insets(0,10,5,10);
setConstraints(6,0,1,1,0.0,0.0,cons.CENTER,cons.BOTH,inset,0,0);
dateButPanel.add(JLabel7,cons);
inset = new Insets(5,10,5,10);
setConstraints(0,1,1,1,0.0,0.0,cons.CENTER,cons.BOTH,inset,0,0);
dateButPanel.add(JLabel8,cons);
inset = new Insets(5,10,5,10);
setConstraints(1,1,1,1,0.0,0.0,cons.CENTER,cons.BOTH,inset,0,0);
dateButPanel.add(JLabel9,cons);
inset = new Insets(5,10,5,10);
setConstraints(2,1,1,1,0.0,0.0,cons.CENTER,cons.BOTH,inset,0,0);
dateButPanel.add(JLabel10,cons);
inset = new Insets(5,10,5,10);
setConstraints(3,1,1,1,0.0,0.0,cons.CENTER,cons.BOTH,inset,0,0);
dateButPanel.add(JLabel11,cons);
inset = new Insets(5,10,5,10);
setConstraints(4,1,1,1,0.0,0.0,cons.CENTER,cons.BOTH,inset,0,0);
dateButPanel.add(JLabel12,cons);
inset = new Insets(5,10,5,10);
setConstraints(5,1,1,1,0.0,0.0,cons.CENTER,cons.BOTH,inset,0,0);
dateButPanel.add(JLabel13,cons);
inset = new Insets(5,10,5,10);
setConstraints(6,1,1,1,0.0,0.0,cons.CENTER,cons.BOTH,inset,0,0);
dateButPanel.add(JLabel14,cons);
inset = new Insets(5,10,5,10);
setConstraints(0,2,1,1,0.0,0.0,cons.CENTER,cons.BOTH,inset,0,0);
dateButPanel.add(JLabel15,cons);
inset = new Insets(5,10,5,10);
setConstraints(1,2,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
dateButPanel.add(JLabel16,cons);
inset = new Insets(5,10,5,10);
setConstraints(2,2,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
dateButPanel.add(JLabel17,cons);
inset = new Insets(5,10,5,10);
setConstraints(3,2,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
dateButPanel.add(JLabel18,cons);
inset = new Insets(5,10,5,10);
setConstraints(4,2,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
dateButPanel.add(JLabel19,cons);
inset = new Insets(5,10,5,10);
setConstraints(5,2,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
dateButPanel.add(JLabel20,cons);
inset = new Insets(5,10,5,10);
setConstraints(6,2,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
dateButPanel.add(JLabel21,cons);
inset = new Insets(5,10,5,10);
setConstraints(0,3,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
dateButPanel.add(JLabel22,cons);
inset = new Insets(5,10,5,10);
setConstraints(1,3,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
dateButPanel.add(JLabel23,cons);
inset = new Insets(5,10,5,10);
setConstraints(2,3,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
dateButPanel.add(JLabel24,cons);
inset = new Insets(5,10,5,10);
setConstraints(3,3,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
dateButPanel.add(JLabel25,cons);
inset = new Insets(5,10,5,10);
setConstraints(4,3,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
dateButPanel.add(JLabel26,cons);
inset = new Insets(5,10,5,10);
setConstraints(5,3,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
dateButPanel.add(JLabel27,cons);
inset = new Insets(5,10,5,10);
setConstraints(6,3,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
dateButPanel.add(JLabel28,cons);
inset = new Insets(5,10,5,10);
setConstraints(0,4,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
dateButPanel.add(JLabel29,cons);
inset = new Insets(5,10,5,10);
setConstraints(1,4,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
dateButPanel.add(JLabel30,cons);
inset = new Insets(5,10,5,10);
setConstraints(2,4,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
dateButPanel.add(JLabel31,cons);
inset = new Insets(0,0,45,0);
setConstraints(0,0,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
datesPanel.add(daysOptPanel1,cons);
daysOptPanel1.setLayout(new GridBagLayout());
inset = new Insets(0,0,0,15);
setConstraints(0,0,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
daysOptPanel1.add(Dates111,cons);
inset = new Insets(0,0,0,0);
setConstraints(1,0,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
daysOptPanel1.add(rbAllDates,cons);
inset = new Insets(0,0,0,0);
setConstraints(2,0,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
daysOptPanel1.add(rbSpecificDates,cons);
inset = new Insets(15,15,16,0);
setConstraints(1,0,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,67);
JPanel2.add(hoursPanel,cons);
hoursPanel.setLayout(new GridBagLayout());
inset = new Insets(0,0,10,0);
setConstraints(0,0,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
hoursPanel.add(hoursOptPanel,cons);
hoursOptPanel.setLayout(new FlowLayout(1,5,5));
hoursOptPanel.add(Dates1);
hoursOptPanel.add(rbAllHours);
hoursOptPanel.add(rbSpecificHours);
inset = new Insets(0,0,0,0);
setConstraints(0,1,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
hoursPanel.add(hourButPanel,cons);
hourButPanel.setLayout(new GridBagLayout());
inset = new Insets(5,15,5,15);
setConstraints(0,0,1,1,0.0,0.0,cons.CENTER,cons.BOTH,inset,0,0);
hourButPanel.add(lfHr0,cons);
inset = new Insets(5,15,5,15);
setConstraints(1,0,1,1,0.0,0.0,cons.CENTER,cons.BOTH,inset,0,0);
hourButPanel.add(lfHr1,cons);
inset = new Insets(5,15,5,15);
setConstraints(2,0,1,1,0.0,0.0,cons.CENTER,cons.BOTH,inset,0,0);
hourButPanel.add(lfHr2,cons);
inset = new Insets(5,15,5,15);
setConstraints(3,0,1,1,0.0,0.0,cons.CENTER,cons.BOTH,inset,0,0);
hourButPanel.add(lfHr3,cons);
inset = new Insets(5,15,5,15);
setConstraints(0,1,1,1,0.0,0.0,cons.CENTER,cons.BOTH,inset,0,0);
hourButPanel.add(lfHr4,cons);
inset = new Insets(5,15,5,15);
setConstraints(1,1,1,1,0.0,0.0,cons.CENTER,cons.BOTH,inset,0,0);
hourButPanel.add(lfHr5,cons);
inset = new Insets(5,15,5,15);
setConstraints(2,1,1,1,0.0,0.0,cons.CENTER,cons.BOTH,inset,0,0);
hourButPanel.add(lfHr6,cons);
inset = new Insets(5,15,5,15);
setConstraints(3,1,1,1,0.0,0.0,cons.CENTER,cons.BOTH,inset,0,0);
hourButPanel.add(lfHr7,cons);
inset = new Insets(5,15,5,15);
setConstraints(0,2,1,1,0.0,0.0,cons.CENTER,cons.BOTH,inset,0,0);
hourButPanel.add(lfHr8,cons);
inset = new Insets(5,15,5,15);
setConstraints(1,2,1,1,0.0,0.0,cons.CENTER,cons.BOTH,inset,0,0);
hourButPanel.add(lfHr9,cons);
inset = new Insets(5,15,5,15);
setConstraints(2,2,1,1,0.0,0.0,cons.CENTER,cons.BOTH,inset,0,0);
hourButPanel.add(lfHr10,cons);
inset = new Insets(5,15,5,15);
setConstraints(3,2,1,1,0.0,0.0,cons.CENTER,cons.BOTH,inset,0,0);
hourButPanel.add(lfHr11,cons);
inset = new Insets(5,15,5,15);
setConstraints(0,3,1,1,0.0,0.0,cons.CENTER,cons.BOTH,inset,0,0);
hourButPanel.add(lfHr12,cons);
inset = new Insets(5,15,5,15);
setConstraints(1,3,1,1,0.0,0.0,cons.CENTER,cons.BOTH,inset,0,0);
hourButPanel.add(lfHr13,cons);
inset = new Insets(5,15,5,15);
setConstraints(2,3,1,1,0.0,0.0,cons.CENTER,cons.BOTH,inset,0,0);
hourButPanel.add(lfHr14,cons);
inset = new Insets(5,15,5,15);
setConstraints(3,3,1,1,0.0,0.0,cons.CENTER,cons.BOTH,inset,0,0);
hourButPanel.add(lfHr15,cons);
inset = new Insets(5,15,5,15);
setConstraints(0,4,1,1,0.0,0.0,cons.CENTER,cons.BOTH,inset,0,0);
hourButPanel.add(lfHr16,cons);
inset = new Insets(5,15,5,15);
setConstraints(1,4,1,1,0.0,0.0,cons.CENTER,cons.BOTH,inset,0,0);
hourButPanel.add(lfHr17,cons);
inset = new Insets(5,15,5,15);
setConstraints(2,4,1,1,0.0,0.0,cons.CENTER,cons.BOTH,inset,0,0);
hourButPanel.add(lfHr18,cons);
inset = new Insets(5,15,5,15);
setConstraints(3,4,1,1,0.0,0.0,cons.CENTER,cons.BOTH,inset,0,0);
hourButPanel.add(lfHr19,cons);
inset = new Insets(5,15,5,15);
setConstraints(0,5,1,1,0.0,0.0,cons.CENTER,cons.BOTH,inset,0,0);
hourButPanel.add(lfHr20,cons);
inset = new Insets(5,15,5,15);
setConstraints(1,5,1,1,0.0,0.0,cons.CENTER,cons.BOTH,inset,0,0);
hourButPanel.add(lfHr21,cons);
inset = new Insets(5,15,5,15);
setConstraints(2,5,1,1,0.0,0.0,cons.CENTER,cons.BOTH,inset,0,0);
hourButPanel.add(lfHr22,cons);
inset = new Insets(5,15,5,15);
setConstraints(3,5,1,1,0.0,0.0,cons.CENTER,cons.BOTH,inset,0,0);
hourButPanel.add(lfHr23,cons);
mainCardPanel.add(regIntPanel,"regIntPanel");
regIntPanel.setLayout(new GridBagLayout());
inset = new Insets(0,0,0,0);
setConstraints(0,0,1,1,0.0,0.1,cons.CENTER,cons.VERTICAL,inset,0,0);
regIntPanel.add(lfImage,cons);
inset = new Insets(0,0,0,0);
setConstraints(1,0,1,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
regIntPanel.add(innerPanel,cons);
innerPanel.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(0,0,0,1,0.1,0.0,cons.NORTH,cons.HORIZONTAL,inset,0,0);
innerPanel.add(lfHTML,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,1,0,1,0.1,0.1,cons.WEST,cons.BOTH,inset,0,0);
innerPanel.add(lfEmpty1,cons);
lfEmpty1.setLayout(new GridBagLayout());
inset = new Insets(0,0,0,0);
setConstraints(6,0,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
lfEmpty1.add(minSecSepLabel,cons);
inset = new Insets(0,0,0,0);
setConstraints(7,0,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
lfEmpty1.add(ndSec,cons);
inset = new Insets(0,0,0,0);
setConstraints(4,0,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
lfEmpty1.add(hrMinSepLabel,cons);
inset = new Insets(0,0,0,0);
setConstraints(5,0,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
lfEmpty1.add(ndMins,cons);
inset = new Insets(0,0,0,0);
setConstraints(6,2,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
lfEmpty1.add(minSecSep,cons);
inset = new Insets(0,0,0,0);
setConstraints(4,2,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
lfEmpty1.add(hrMinSep,cons);
inset = new Insets(0,0,0,0);
setConstraints(3,0,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
lfEmpty1.add(ndHours,cons);
inset = new Insets(0,0,0,0);
setConstraints(0,2,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
lfEmpty1.add(netDiscInterval,cons);
inset = new Insets(0,0,0,0);
setConstraints(5,2,1,1,0.1,0.0,cons.SOUTH,cons.HORIZONTAL,inset,0,0);
lfEmpty1.add(tfRediscIntMin,cons);
inset = new Insets(0,0,0,0);
setConstraints(7,2,1,1,0.1,0.0,cons.WEST,cons.HORIZONTAL,inset,0,0);
lfEmpty1.add(tfRediscIntSec,cons);
inset = new Insets(0,0,0,0);
setConstraints(3,2,1,1,0.1,0.0,cons.SOUTH,cons.HORIZONTAL,inset,0,0);
lfEmpty1.add(tfRediscInt,cons);
inset = new Insets(0,0,0,0);
setConstraints(-1,-1,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
innerPanel.add(JPanel3,cons);
JPanel3.setLayout(new GridBagLayout());
inset = new Insets(0,0,0,0);
setConstraints(-1,-1,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
JPanel3.add(okButton,cons);
inset = new Insets(0,0,0,0);
setConstraints(-1,-1,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
JPanel3.add(cancelButton,cons);

  
//<End_setUpGUI_Container>

	bGroup2.add(rbRegInt);
	bGroup2.add(rbDates);
	bGroup2.add(rbDays);
	
	
	bGroup3.add(rbAllDates);
	bGroup3.add(rbSpecificDates);
	
	bGroup4.add(rbAllDays);
	bGroup4.add(rbSpecificDays);
	
	bGroup5.add(rbAllHours);
	bGroup5.add(rbSpecificHours);
	tfRediscInt.setMin(-1);
	tfRediscInt.setValue(-1);
	tfRediscIntMin.setMin(-1);
	tfRediscIntMin.setValue(-1);
	tfRediscIntSec.setMin(-1);
	tfRediscIntSec.setValue(-1);
	mainCardPanel.add(regIntPanel,"RegularIntervalPanel");
	mainCardPanel.add(userSchPanel,"UserSchedulerPanel");
 	CardLayout cardLay =(CardLayout)mainCardPanel.getLayout();
	cardLay.show(mainCardPanel,"RegularIntervalPanel");
	innerCardPanel.add(datesPanel,"DatesPanel");
	innerCardPanel.add(daysPanel,"DaysPanel");
 	CardLayout cardLayout =(CardLayout)innerCardPanel.getLayout();
	cardLayout.show(innerCardPanel,"DatesPanel");
	rbRegInt.addActionListener(this);
	//rbUserSch.addActionListener(this);
	rbDates.addActionListener(this);
	rbDays.addActionListener(this);
	rbAllDates.addActionListener(this);
	rbSpecificDates.addActionListener(this);
	
	rbAllDays.addActionListener(this);
  	rbSpecificDays.addActionListener(this);
  	
	rbAllHours.addActionListener(this);
  	rbSpecificHours.addActionListener(this);
  	
	okButton.addActionListener(this);
	udokButton.addActionListener(this);
	cancelButton.addActionListener(this);
	udcancelButton.addActionListener(this);
	okButton.setMnemonic(KeyEvent.VK_O); 
	cancelButton.setMnemonic(KeyEvent.VK_C); 
	udokButton.setMnemonic(KeyEvent.VK_O);
	udcancelButton.setMnemonic(KeyEvent.VK_C); 

        
	Object[] labelDt = dateButPanel.getComponents();
	Object[] labelDay = dayButPanel.getComponents();
	Object[] labelHr = hourButPanel.getComponents();
        
        //.out.println ( "labelDt" + labelDt+ "  labelDay "+labelDay+"  labelHr= "+labelHr );
        
        activateMouseListener = true;
        
	for(int i = 0;i < labelDt.length;i++)
	{
        ((JLabel)labelDt[i]).addMouseListener(new MouseAdapter()
            {
                           public void mouseClicked(MouseEvent me) 
                    {
                    
                        if(activateMouseListener)
                        {
                            JLabel label = (JLabel)me.getSource();
                            if(label.getBackground()== dateButPanel.getBackground()) 
                            {
                                /* This is done to disallow the user from selecting more than
                                   two days. and this will fix the issue of 
                                   " if more than two days are selected like 2, 4 and 6 then
                                   the range od days is populated as  2-4-6" reported by a 
                                   customer.*/
                                if(count < 2)
                                {
                                    count++;
                                    label.setBackground(Color.blue);
                                    label.setForeground(Color.white);
                                }
                            }
                            else 
                            {
                                if( count > 0)
                                {
                                    count--;
                                }
                                label.setBackground(dateButPanel.getBackground());
                                label.setForeground(Color.black);
                            }
                        }
                    }
            });
        
	}
        
	for(int i = 0;i < labelDay.length;i++)
	{
            ((JLabel)labelDay[i]).addMouseListener(new MouseAdapter()
                {
                    public void mouseClicked(MouseEvent me) 
                    {
			if(activateMouseListener)
			{
                            JLabel label = (JLabel)me.getSource();
                            if(label.getBackground()== dayButPanel.getBackground()) 
                            {
                                label.setBackground(Color.blue);
                                label.setForeground(Color.white);
                            }
                            else 
                            {
                                label.setBackground(dayButPanel.getBackground());
                                label.setForeground(Color.black);
                            }
			}
                    }
                });
	}
        
	for(int i = 0;i < labelHr.length;i++)
	{
            ((JLabel)labelHr[i]).addMouseListener(new MouseAdapter()
                {
                    public void mouseClicked(MouseEvent me) 
                    {
                        if(activateMouseListener)
			{
                            JLabel label = (JLabel)me.getSource();
                            if(label.getBackground()== hourButPanel.getBackground()) 
                            {
                                label.setBackground(Color.blue);
                                label.setForeground(Color.white);
                            }
                            else 
                            {
                                label.setBackground(hourButPanel.getBackground());
                                label.setForeground(Color.black);
                            }
			}
                    }
                });
	}
        
        tfRediscInt.addKeyListener(new KeyAdapter()
            {
				public void keyTyped(KeyEvent ke) 
				{ 
                	if((ke.getKeyChar() >= '0' && ke.getKeyChar() <='9') || (ke.getKeyChar()== ke.VK_BACK_SPACE) || (ke.getKeyChar()=='-'))
					{ 
                		//DO Nothing 
                	} 
                	else
					{ 
                    	ke.consume(); 
                    	Toolkit.getDefaultToolkit().beep(); 
                	} 
				}               
				public void keyPressed(KeyEvent ke)
                {
                    if(ke.getKeyCode()== ke.VK_ENTER)
                    {
                        OK_Clicked();
                    }
                } 
            });




	tfRediscInt.addFocusListener(new FocusAdapter()
		{
			public void focusLost(FocusEvent fe)
			{
				String text=Long.toString(tfRediscInt.getValue());
				int len =text.length();

				if(len==2)
				{
					 char firstIndex=text.charAt(0);
					 char secondIndex = text.charAt(1);
					if(firstIndex=='-' && secondIndex=='1')
					{
						//Nil

					}	

					else if (firstIndex!='-')
					{
						//Nil
					}
					
					else 
					{
                        				Toolkit.getDefaultToolkit().beep(); 
                        				tfRediscInt.setValue(-1);
						tfRediscIntMin.setValue(-1);
						tfRediscIntSec.setValue(-1);
						tfRediscInt.requestFocus();
					}	
				}		




				else if(len>2)
				{
				     
					 char first=text.charAt(0);
					if(first=='-')
					{
                    				Toolkit.getDefaultToolkit().beep(); 
						tfRediscInt.setValue(-1);
						tfRediscIntMin.setValue(-1);
						tfRediscIntSec.setValue(-1);
						tfRediscInt.requestFocus();
				
					}



				}
					






			}
		});
	

















        okButton.addKeyListener(new KeyAdapter()
            {
                public void keyPressed(KeyEvent ke)
                {
                    if(ke.getKeyCode()== ke.VK_ENTER)
                    {
                        OK_Clicked();
                    }
                } 
            });
        
        cancelButton.addKeyListener(new KeyAdapter()
            {
                public void keyPressed(KeyEvent ke)
                {
                    if(ke.getKeyCode()== ke.VK_ESCAPE)
                    {
                        Cancel_Clicked();
                    }
                } 
            });
            
            udokButton.addKeyListener(new KeyAdapter()
            {
                public void keyPressed(KeyEvent ke)
                {
                    if(ke.getKeyCode()== ke.VK_ENTER)
                    {
                        	tfRediscInt.setValue(-1);
	                         tfRediscIntMin.setValue(-1);
                        	 tfRediscIntSec.setValue(-1);
		
                        OK_Clicked();
                    }
                } 
            });
        
        udcancelButton.addKeyListener(new KeyAdapter()
            {
                public void keyPressed(KeyEvent ke)
                {
                    if(ke.getKeyCode()== ke.VK_ESCAPE)
                    {
                        Cancel_Clicked();
                    }
                } 
            });

        this.setSize(700,520);
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension d = kit.getScreenSize();
        this.setLocation((d.width-600)/2,(d.height-507)/2);

    } 

    public void setUpProperties()
  { 

        //<Begin_setUpProperties> 

          try
          {
            rbRegInt.setText(resourceBundle.getString("Regular Interval"));
            rbRegInt.setSelected(true);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+rbRegInt,ex); 
          }

//<UserCode_Begin_Bean_rbRegInt>
//rbRegInt.setSelected(false);
//<UserCode_End_Bean_rbRegInt>

          try
          {
            rbDays.setLabel(resourceBundle.getString("Days"));
            rbDays.setText(resourceBundle.getString("Days of Week"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+rbDays,ex); 
          }

//<UserCode_Begin_Bean_rbDays>

//<UserCode_End_Bean_rbDays>

          try
          {
            rbDates.setText(resourceBundle.getString("Specific Dates"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+rbDates,ex); 
          }

//<UserCode_Begin_Bean_rbDates>

//<UserCode_End_Bean_rbDates>

          try
          {
            JScrollPane1.setMaximumSize(new Dimension(500,500));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JScrollPane1,ex); 
          }

//<UserCode_Begin_Bean_JScrollPane1>

//<UserCode_End_Bean_JScrollPane1>

          try
          {
            mainCardPanel.setMinimumSize(new Dimension(500,375));
            mainCardPanel.setPreferredSize(new Dimension(500,375));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+mainCardPanel,ex); 
          }

//<UserCode_Begin_Bean_mainCardPanel>

//<UserCode_End_Bean_mainCardPanel>

          try
          {
            udokButton.setText(resourceBundle.getString("OK"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+udokButton,ex); 
          }

//<UserCode_Begin_Bean_udokButton>

//<UserCode_End_Bean_udokButton>

          try
          {
            udcancelButton.setText(resourceBundle.getString("CANCEL"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+udcancelButton,ex); 
          }

//<UserCode_Begin_Bean_udcancelButton>

//<UserCode_End_Bean_udcancelButton>

          try
          {
            lfHTML1.setBorder(new javax.swing.border.EtchedBorder(0));
            lfHTML1.setForeground(new Color(-16777214));
            lfHTML1.setFont(new Font("Dialog",1,12));
            lfHTML1.setText(resourceBundle.getString("<html><body><ul><li><font color=000000 size=-1>When the Specific Dates option is selected for Rediscovery interval, then after a complete discovery, the rediscovery process will happen,  in the specified date of the month and in the hour of that specified date.<li><font color=000000  size=-1>When the Day of week option is selected, then after a complete discovery, the rediscovery process will happen, in the specified day of the week, in the hour of that selected day.</font></ul></body></html>"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+lfHTML1,ex); 
          }

//<UserCode_Begin_Bean_lfHTML1>

//<UserCode_End_Bean_lfHTML1>

          try
          {
            innerCardPanel.setBorder(new javax.swing.border.BevelBorder(0));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+innerCardPanel,ex); 
          }

//<UserCode_Begin_Bean_innerCardPanel>

//<UserCode_End_Bean_innerCardPanel>

          try
          {
            dayButPanel.setMaximumSize(new Dimension(219,80));
            dayButPanel.setMinimumSize(new Dimension(219,200));
            dayButPanel.setPreferredSize(new Dimension(219,200));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+dayButPanel,ex); 
          }

//<UserCode_Begin_Bean_dayButPanel>

//<UserCode_End_Bean_dayButPanel>

          try
          {
            JLabel32.setText(resourceBundle.getString("MON"));
            JLabel32.setOpaque(true);
            JLabel32.setHorizontalTextPosition(0);
            JLabel32.setHorizontalAlignment(0);
            JLabel32.setForeground(new Color(-16777215));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel32,ex); 
          }

//<UserCode_Begin_Bean_JLabel32>

//<UserCode_End_Bean_JLabel32>

          try
          {
            JLabel33.setText(resourceBundle.getString("TUE"));
            JLabel33.setHorizontalTextPosition(0);
            JLabel33.setHorizontalAlignment(0);
            JLabel33.setForeground(new Color(-16777211));
            JLabel33.setOpaque(true);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel33,ex); 
          }

//<UserCode_Begin_Bean_JLabel33>

//<UserCode_End_Bean_JLabel33>

          try
          {
            JLabel34.setText(resourceBundle.getString("WED"));
            JLabel34.setHorizontalTextPosition(0);
            JLabel34.setHorizontalAlignment(0);
            JLabel34.setForeground(new Color(-16777215));
            JLabel34.setOpaque(true);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel34,ex); 
          }

//<UserCode_Begin_Bean_JLabel34>

//<UserCode_End_Bean_JLabel34>

          try
          {
            JLabel35.setOpaque(true);
            JLabel35.setText(resourceBundle.getString("THU"));
            JLabel35.setHorizontalTextPosition(0);
            JLabel35.setHorizontalAlignment(0);
            JLabel35.setForeground(new Color(-16777214));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel35,ex); 
          }

//<UserCode_Begin_Bean_JLabel35>

//<UserCode_End_Bean_JLabel35>

          try
          {
            JLabel36.setText(resourceBundle.getString("FRI"));
            JLabel36.setOpaque(true);
            JLabel36.setHorizontalTextPosition(0);
            JLabel36.setHorizontalAlignment(0);
            JLabel36.setForeground(new Color(-16777213));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel36,ex); 
          }

//<UserCode_Begin_Bean_JLabel36>

//<UserCode_End_Bean_JLabel36>

          try
          {
            JLabel37.setOpaque(true);
            JLabel37.setText(resourceBundle.getString("SAT"));
            JLabel37.setHorizontalTextPosition(0);
            JLabel37.setHorizontalAlignment(0);
            JLabel37.setForeground(new Color(-16777213));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel37,ex); 
          }

//<UserCode_Begin_Bean_JLabel37>

//<UserCode_End_Bean_JLabel37>

          try
          {
            JLabel38.setText(resourceBundle.getString("SUN"));
            JLabel38.setOpaque(true);
            JLabel38.setHorizontalTextPosition(0);
            JLabel38.setHorizontalAlignment(0);
            JLabel38.setForeground(new Color(-16580096));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel38,ex); 
          }

//<UserCode_Begin_Bean_JLabel38>

//<UserCode_End_Bean_JLabel38>

          try
          {
            Dates11.setForeground(new Color(-16777216));
            Dates11.setFont(new Font("Lucida Sans",1,14));
            Dates11.setText(resourceBundle.getString("Days"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+Dates11,ex); 
          }

//<UserCode_Begin_Bean_Dates11>

//<UserCode_End_Bean_Dates11>

          try
          {
            rbAllDays.setText(resourceBundle.getString("All"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+rbAllDays,ex); 
          }

//<UserCode_Begin_Bean_rbAllDays>
	//rbAllDays.setSelected(false);
//<UserCode_End_Bean_rbAllDays>

          try
          {
            rbSpecificDays.setSelected(true);
            rbSpecificDays.setText(resourceBundle.getString("Specific"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+rbSpecificDays,ex); 
          }

//<UserCode_Begin_Bean_rbSpecificDays>

//<UserCode_End_Bean_rbSpecificDays>

          try
          {
            JLabel1.setOpaque(true);
            JLabel1.setHorizontalTextPosition(0);
            JLabel1.setHorizontalAlignment(0);
            JLabel1.setText(resourceBundle.getString("1"));
            JLabel1.setForeground(new Color(-16777210));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel1,ex); 
          }

//<UserCode_Begin_Bean_JLabel1>

//<UserCode_End_Bean_JLabel1>

          try
          {
            JLabel2.setOpaque(true);
            JLabel2.setHorizontalTextPosition(0);
            JLabel2.setHorizontalAlignment(0);
            JLabel2.setForeground(new Color(-16777213));
            JLabel2.setText(resourceBundle.getString("2"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel2,ex); 
          }

//<UserCode_Begin_Bean_JLabel2>

//<UserCode_End_Bean_JLabel2>

          try
          {
            JLabel3.setOpaque(true);
            JLabel3.setForeground(new Color(-16777210));
            JLabel3.setHorizontalTextPosition(0);
            JLabel3.setHorizontalAlignment(0);
            JLabel3.setText(resourceBundle.getString("3"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel3,ex); 
          }

//<UserCode_Begin_Bean_JLabel3>

//<UserCode_End_Bean_JLabel3>

          try
          {
            JLabel4.setOpaque(true);
            JLabel4.setHorizontalTextPosition(0);
            JLabel4.setHorizontalAlignment(0);
            JLabel4.setText(resourceBundle.getString("4"));
            JLabel4.setForeground(new Color(-16777211));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel4,ex); 
          }

//<UserCode_Begin_Bean_JLabel4>

//<UserCode_End_Bean_JLabel4>

          try
          {
            JLabel5.setOpaque(true);
            JLabel5.setForeground(new Color(-16777215));
            JLabel5.setText(resourceBundle.getString("5"));
            JLabel5.setHorizontalTextPosition(0);
            JLabel5.setHorizontalAlignment(0);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel5,ex); 
          }

//<UserCode_Begin_Bean_JLabel5>

//<UserCode_End_Bean_JLabel5>

          try
          {
            JLabel6.setOpaque(true);
            JLabel6.setHorizontalTextPosition(0);
            JLabel6.setHorizontalAlignment(0);
            JLabel6.setText(resourceBundle.getString("6"));
            JLabel6.setForeground(new Color(-16777211));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel6,ex); 
          }

//<UserCode_Begin_Bean_JLabel6>

//<UserCode_End_Bean_JLabel6>

          try
          {
            JLabel7.setOpaque(true);
            JLabel7.setForeground(new Color(-16777215));
            JLabel7.setText(resourceBundle.getString("7"));
            JLabel7.setHorizontalTextPosition(0);
            JLabel7.setHorizontalAlignment(0);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel7,ex); 
          }

//<UserCode_Begin_Bean_JLabel7>

//<UserCode_End_Bean_JLabel7>

          try
          {
            JLabel8.setOpaque(true);
            JLabel8.setHorizontalTextPosition(0);
            JLabel8.setHorizontalAlignment(0);
            JLabel8.setText(resourceBundle.getString("8"));
            JLabel8.setForeground(new Color(-16777210));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel8,ex); 
          }

//<UserCode_Begin_Bean_JLabel8>

//<UserCode_End_Bean_JLabel8>

          try
          {
            JLabel9.setOpaque(true);
            JLabel9.setForeground(new Color(-16777211));
            JLabel9.setText(resourceBundle.getString("9"));
            JLabel9.setHorizontalTextPosition(0);
            JLabel9.setHorizontalAlignment(0);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel9,ex); 
          }

//<UserCode_Begin_Bean_JLabel9>

//<UserCode_End_Bean_JLabel9>

          try
          {
            JLabel10.setText(resourceBundle.getString("10"));
            JLabel10.setOpaque(true);
            JLabel10.setHorizontalTextPosition(0);
            JLabel10.setHorizontalAlignment(0);
            JLabel10.setForeground(new Color(-16777213));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel10,ex); 
          }

//<UserCode_Begin_Bean_JLabel10>

//<UserCode_End_Bean_JLabel10>

          try
          {
            JLabel11.setOpaque(true);
            JLabel11.setText(resourceBundle.getString("11"));
            JLabel11.setForeground(new Color(-16777214));
            JLabel11.setHorizontalTextPosition(0);
            JLabel11.setHorizontalAlignment(0);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel11,ex); 
          }

//<UserCode_Begin_Bean_JLabel11>

//<UserCode_End_Bean_JLabel11>

          try
          {
            JLabel12.setText(resourceBundle.getString("12"));
            JLabel12.setOpaque(true);
            JLabel12.setHorizontalTextPosition(0);
            JLabel12.setHorizontalAlignment(0);
            JLabel12.setForeground(new Color(-16777213));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel12,ex); 
          }

//<UserCode_Begin_Bean_JLabel12>

//<UserCode_End_Bean_JLabel12>

          try
          {
            JLabel13.setOpaque(true);
            JLabel13.setText(resourceBundle.getString("13"));
            JLabel13.setForeground(new Color(-16777215));
            JLabel13.setHorizontalTextPosition(0);
            JLabel13.setHorizontalAlignment(0);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel13,ex); 
          }

//<UserCode_Begin_Bean_JLabel13>

//<UserCode_End_Bean_JLabel13>

          try
          {
            JLabel14.setText(resourceBundle.getString("14"));
            JLabel14.setOpaque(true);
            JLabel14.setHorizontalTextPosition(0);
            JLabel14.setHorizontalAlignment(0);
            JLabel14.setForeground(new Color(-16777214));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel14,ex); 
          }

//<UserCode_Begin_Bean_JLabel14>

//<UserCode_End_Bean_JLabel14>

          try
          {
            JLabel15.setOpaque(true);
            JLabel15.setText(resourceBundle.getString("15"));
            JLabel15.setForeground(new Color(-16777211));
            JLabel15.setHorizontalTextPosition(0);
            JLabel15.setHorizontalAlignment(0);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel15,ex); 
          }

//<UserCode_Begin_Bean_JLabel15>

//<UserCode_End_Bean_JLabel15>

          try
          {
            JLabel16.setText(resourceBundle.getString("16"));
            JLabel16.setOpaque(true);
            JLabel16.setHorizontalTextPosition(0);
            JLabel16.setHorizontalAlignment(0);
            JLabel16.setForeground(new Color(-16777208));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel16,ex); 
          }

//<UserCode_Begin_Bean_JLabel16>

//<UserCode_End_Bean_JLabel16>

          try
          {
            JLabel17.setOpaque(true);
            JLabel17.setText(resourceBundle.getString("17"));
            JLabel17.setHorizontalTextPosition(0);
            JLabel17.setHorizontalAlignment(0);
            JLabel17.setForeground(new Color(-16777215));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel17,ex); 
          }

//<UserCode_Begin_Bean_JLabel17>

//<UserCode_End_Bean_JLabel17>

          try
          {
            JLabel18.setText(resourceBundle.getString("18"));
            JLabel18.setOpaque(true);
            JLabel18.setForeground(new Color(-16777213));
            JLabel18.setHorizontalTextPosition(0);
            JLabel18.setHorizontalAlignment(0);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel18,ex); 
          }

//<UserCode_Begin_Bean_JLabel18>

//<UserCode_End_Bean_JLabel18>

          try
          {
            JLabel19.setOpaque(true);
            JLabel19.setText(resourceBundle.getString("19"));
            JLabel19.setHorizontalTextPosition(0);
            JLabel19.setHorizontalAlignment(0);
            JLabel19.setForeground(new Color(-16777213));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel19,ex); 
          }

//<UserCode_Begin_Bean_JLabel19>

//<UserCode_End_Bean_JLabel19>

          try
          {
            JLabel20.setText(resourceBundle.getString("20"));
            JLabel20.setOpaque(true);
            JLabel20.setForeground(new Color(-16777213));
            JLabel20.setHorizontalTextPosition(0);
            JLabel20.setHorizontalAlignment(0);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel20,ex); 
          }

//<UserCode_Begin_Bean_JLabel20>

//<UserCode_End_Bean_JLabel20>

          try
          {
            JLabel21.setOpaque(true);
            JLabel21.setText(resourceBundle.getString("21"));
            JLabel21.setHorizontalTextPosition(0);
            JLabel21.setHorizontalAlignment(0);
            JLabel21.setForeground(new Color(-16777213));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel21,ex); 
          }

//<UserCode_Begin_Bean_JLabel21>

//<UserCode_End_Bean_JLabel21>

          try
          {
            JLabel22.setText(resourceBundle.getString("22"));
            JLabel22.setOpaque(true);
            JLabel22.setForeground(new Color(-16777213));
            JLabel22.setHorizontalTextPosition(0);
            JLabel22.setHorizontalAlignment(0);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel22,ex); 
          }

//<UserCode_Begin_Bean_JLabel22>

//<UserCode_End_Bean_JLabel22>

          try
          {
            JLabel23.setOpaque(true);
            JLabel23.setText(resourceBundle.getString("23"));
            JLabel23.setHorizontalTextPosition(0);
            JLabel23.setHorizontalAlignment(0);
            JLabel23.setForeground(new Color(-16777214));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel23,ex); 
          }

//<UserCode_Begin_Bean_JLabel23>

//<UserCode_End_Bean_JLabel23>

          try
          {
            JLabel24.setText(resourceBundle.getString("24"));
            JLabel24.setOpaque(true);
            JLabel24.setHorizontalTextPosition(0);
            JLabel24.setHorizontalAlignment(0);
            JLabel24.setForeground(new Color(-16777210));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel24,ex); 
          }

//<UserCode_Begin_Bean_JLabel24>

//<UserCode_End_Bean_JLabel24>

          try
          {
            JLabel25.setOpaque(true);
            JLabel25.setText(resourceBundle.getString("25"));
            JLabel25.setForeground(new Color(-16777210));
            JLabel25.setHorizontalTextPosition(0);
            JLabel25.setHorizontalAlignment(0);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel25,ex); 
          }

//<UserCode_Begin_Bean_JLabel25>

//<UserCode_End_Bean_JLabel25>

          try
          {
            JLabel26.setText(resourceBundle.getString("26"));
            JLabel26.setOpaque(true);
            JLabel26.setHorizontalTextPosition(0);
            JLabel26.setHorizontalAlignment(0);
            JLabel26.setForeground(new Color(-16777211));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel26,ex); 
          }

//<UserCode_Begin_Bean_JLabel26>

//<UserCode_End_Bean_JLabel26>

          try
          {
            JLabel27.setOpaque(true);
            JLabel27.setText(resourceBundle.getString("27"));
            JLabel27.setForeground(new Color(-16777211));
            JLabel27.setHorizontalTextPosition(0);
            JLabel27.setHorizontalAlignment(0);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel27,ex); 
          }

//<UserCode_Begin_Bean_JLabel27>

//<UserCode_End_Bean_JLabel27>

          try
          {
            JLabel28.setText(resourceBundle.getString("28"));
            JLabel28.setOpaque(true);
            JLabel28.setHorizontalTextPosition(0);
            JLabel28.setHorizontalAlignment(0);
            JLabel28.setForeground(new Color(-16777211));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel28,ex); 
          }

//<UserCode_Begin_Bean_JLabel28>

//<UserCode_End_Bean_JLabel28>

          try
          {
            JLabel29.setOpaque(true);
            JLabel29.setText(resourceBundle.getString("29"));
            JLabel29.setForeground(new Color(-16777214));
            JLabel29.setHorizontalTextPosition(0);
            JLabel29.setHorizontalAlignment(0);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel29,ex); 
          }

//<UserCode_Begin_Bean_JLabel29>

//<UserCode_End_Bean_JLabel29>

          try
          {
            JLabel30.setText(resourceBundle.getString("30"));
            JLabel30.setOpaque(true);
            JLabel30.setForeground(new Color(-16777214));
            JLabel30.setHorizontalTextPosition(0);
            JLabel30.setHorizontalAlignment(0);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel30,ex); 
          }

//<UserCode_Begin_Bean_JLabel30>

//<UserCode_End_Bean_JLabel30>

          try
          {
            JLabel31.setOpaque(true);
            JLabel31.setText(resourceBundle.getString("31"));
            JLabel31.setForeground(new Color(-16777211));
            JLabel31.setHorizontalTextPosition(0);
            JLabel31.setHorizontalAlignment(0);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel31,ex); 
          }

//<UserCode_Begin_Bean_JLabel31>

//<UserCode_End_Bean_JLabel31>

          try
          {
            Dates111.setForeground(new Color(-16777216));
            Dates111.setText(resourceBundle.getString("Dates"));
            Dates111.setFont(new Font("dialog",1,14));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+Dates111,ex); 
          }

//<UserCode_Begin_Bean_Dates111>

//<UserCode_End_Bean_Dates111>

          try
          {
            rbAllDates.setText(resourceBundle.getString("All"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+rbAllDates,ex); 
          }

//<UserCode_Begin_Bean_rbAllDates>
	//rbAllDates.setSelected(false);
//<UserCode_End_Bean_rbAllDates>

          try
          {
            rbSpecificDates.setSelected(true);
            rbSpecificDates.setText(resourceBundle.getString("Specific"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+rbSpecificDates,ex); 
          }

//<UserCode_Begin_Bean_rbSpecificDates>

//<UserCode_End_Bean_rbSpecificDates>

          try
          {
            hoursPanel.setBorder(new javax.swing.border.BevelBorder(0));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+hoursPanel,ex); 
          }

//<UserCode_Begin_Bean_hoursPanel>

//<UserCode_End_Bean_hoursPanel>

          try
          {
            Dates1.setForeground(new Color(-16777216));
            Dates1.setText(resourceBundle.getString("Hours"));
            Dates1.setFont(new Font("dialog",1,14));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+Dates1,ex); 
          }

//<UserCode_Begin_Bean_Dates1>

//<UserCode_End_Bean_Dates1>

          try
          {
            rbAllHours.setText(resourceBundle.getString("All"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+rbAllHours,ex); 
          }

//<UserCode_Begin_Bean_rbAllHours>
	//rbAllHours.setSelected(false);
//<UserCode_End_Bean_rbAllHours>

          try
          {
            rbSpecificHours.setSelected(true);
            rbSpecificHours.setText(resourceBundle.getString("Specific"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+rbSpecificHours,ex); 
          }

//<UserCode_Begin_Bean_rbSpecificHours>

//<UserCode_End_Bean_rbSpecificHours>

          try
          {
            hourButPanel.setMaximumSize(new Dimension(200,200));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+hourButPanel,ex); 
          }

//<UserCode_Begin_Bean_hourButPanel>

//<UserCode_End_Bean_hourButPanel>

          try
          {
            lfHr0.setOpaque(true);
            lfHr0.setForeground(new Color(-16777213));
            lfHr0.setHorizontalTextPosition(0);
            lfHr0.setHorizontalAlignment(0);
            lfHr0.setText(resourceBundle.getString("00:00"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+lfHr0,ex); 
          }

//<UserCode_Begin_Bean_lfHr0>

//<UserCode_End_Bean_lfHr0>

          try
          {
            lfHr1.setOpaque(true);
            lfHr1.setHorizontalTextPosition(0);
            lfHr1.setHorizontalAlignment(0);
            lfHr1.setForeground(new Color(-16777208));
            lfHr1.setText(resourceBundle.getString("01:00"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+lfHr1,ex); 
          }

//<UserCode_Begin_Bean_lfHr1>

//<UserCode_End_Bean_lfHr1>

          try
          {
            lfHr2.setOpaque(true);
            lfHr2.setForeground(new Color(-16777211));
            lfHr2.setHorizontalTextPosition(0);
            lfHr2.setHorizontalAlignment(0);
            lfHr2.setText(resourceBundle.getString("02:00"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+lfHr2,ex); 
          }

//<UserCode_Begin_Bean_lfHr2>

//<UserCode_End_Bean_lfHr2>

          try
          {
            lfHr3.setOpaque(true);
            lfHr3.setHorizontalTextPosition(0);
            lfHr3.setHorizontalAlignment(0);
            lfHr3.setForeground(new Color(-16777211));
            lfHr3.setText(resourceBundle.getString("03:00"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+lfHr3,ex); 
          }

//<UserCode_Begin_Bean_lfHr3>

//<UserCode_End_Bean_lfHr3>

          try
          {
            lfHr4.setOpaque(true);
            lfHr4.setForeground(new Color(-16777213));
            lfHr4.setHorizontalTextPosition(0);
            lfHr4.setHorizontalAlignment(0);
            lfHr4.setText(resourceBundle.getString("04:00"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+lfHr4,ex); 
          }

//<UserCode_Begin_Bean_lfHr4>

//<UserCode_End_Bean_lfHr4>

          try
          {
            lfHr5.setOpaque(true);
            lfHr5.setHorizontalTextPosition(0);
            lfHr5.setHorizontalAlignment(0);
            lfHr5.setForeground(new Color(-16777213));
            lfHr5.setText(resourceBundle.getString("05:00"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+lfHr5,ex); 
          }

//<UserCode_Begin_Bean_lfHr5>

//<UserCode_End_Bean_lfHr5>

          try
          {
            lfHr6.setOpaque(true);
            lfHr6.setHorizontalTextPosition(0);
            lfHr6.setHorizontalAlignment(0);
            lfHr6.setForeground(new Color(-16777211));
            lfHr6.setText(resourceBundle.getString("06:00"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+lfHr6,ex); 
          }

//<UserCode_Begin_Bean_lfHr6>

//<UserCode_End_Bean_lfHr6>

          try
          {
            lfHr7.setOpaque(true);
            lfHr7.setForeground(new Color(-16777216));
            lfHr7.setHorizontalTextPosition(0);
            lfHr7.setHorizontalAlignment(0);
            lfHr7.setText(resourceBundle.getString("07:00"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+lfHr7,ex); 
          }

//<UserCode_Begin_Bean_lfHr7>

//<UserCode_End_Bean_lfHr7>

          try
          {
            lfHr8.setOpaque(true);
            lfHr8.setHorizontalTextPosition(0);
            lfHr8.setHorizontalAlignment(0);
            lfHr8.setForeground(new Color(-16777216));
            lfHr8.setText(resourceBundle.getString("08:00"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+lfHr8,ex); 
          }

//<UserCode_Begin_Bean_lfHr8>

//<UserCode_End_Bean_lfHr8>

          try
          {
            lfHr9.setOpaque(true);
            lfHr9.setForeground(new Color(-16777213));
            lfHr9.setHorizontalTextPosition(0);
            lfHr9.setHorizontalAlignment(0);
            lfHr9.setText(resourceBundle.getString("09:00"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+lfHr9,ex); 
          }

//<UserCode_Begin_Bean_lfHr9>

//<UserCode_End_Bean_lfHr9>

          try
          {
            lfHr10.setOpaque(true);
            lfHr10.setHorizontalTextPosition(0);
            lfHr10.setHorizontalAlignment(0);
            lfHr10.setForeground(new Color(-16777214));
            lfHr10.setText(resourceBundle.getString("10:00"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+lfHr10,ex); 
          }

//<UserCode_Begin_Bean_lfHr10>

//<UserCode_End_Bean_lfHr10>

          try
          {
            lfHr11.setOpaque(true);
            lfHr11.setForeground(new Color(-16777213));
            lfHr11.setHorizontalTextPosition(0);
            lfHr11.setHorizontalAlignment(0);
            lfHr11.setText(resourceBundle.getString("11:00"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+lfHr11,ex); 
          }

//<UserCode_Begin_Bean_lfHr11>

//<UserCode_End_Bean_lfHr11>

          try
          {
            lfHr12.setOpaque(true);
            lfHr12.setHorizontalTextPosition(0);
            lfHr12.setHorizontalAlignment(0);
            lfHr12.setForeground(new Color(-16777210));
            lfHr12.setText(resourceBundle.getString("12:00"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+lfHr12,ex); 
          }

//<UserCode_Begin_Bean_lfHr12>

//<UserCode_End_Bean_lfHr12>

          try
          {
            lfHr13.setOpaque(true);
            lfHr13.setHorizontalTextPosition(0);
            lfHr13.setHorizontalAlignment(0);
            lfHr13.setForeground(new Color(-16777211));
            lfHr13.setText(resourceBundle.getString("13:00"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+lfHr13,ex); 
          }

//<UserCode_Begin_Bean_lfHr13>

//<UserCode_End_Bean_lfHr13>

          try
          {
            lfHr14.setOpaque(true);
            lfHr14.setForeground(new Color(-16777215));
            lfHr14.setHorizontalTextPosition(0);
            lfHr14.setHorizontalAlignment(0);
            lfHr14.setText(resourceBundle.getString("14:00"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+lfHr14,ex); 
          }

//<UserCode_Begin_Bean_lfHr14>

//<UserCode_End_Bean_lfHr14>

          try
          {
            lfHr15.setOpaque(true);
            lfHr15.setHorizontalTextPosition(0);
            lfHr15.setHorizontalAlignment(0);
            lfHr15.setForeground(new Color(-16777214));
            lfHr15.setText(resourceBundle.getString("15:00"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+lfHr15,ex); 
          }

//<UserCode_Begin_Bean_lfHr15>

//<UserCode_End_Bean_lfHr15>

          try
          {
            lfHr16.setOpaque(true);
            lfHr16.setHorizontalTextPosition(0);
            lfHr16.setHorizontalAlignment(0);
            lfHr16.setForeground(new Color(-16777213));
            lfHr16.setText(resourceBundle.getString("16:00"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+lfHr16,ex); 
          }

//<UserCode_Begin_Bean_lfHr16>

//<UserCode_End_Bean_lfHr16>

          try
          {
            lfHr17.setOpaque(true);
            lfHr17.setForeground(new Color(-16777213));
            lfHr17.setHorizontalTextPosition(0);
            lfHr17.setHorizontalAlignment(0);
            lfHr17.setText(resourceBundle.getString("17:00"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+lfHr17,ex); 
          }

//<UserCode_Begin_Bean_lfHr17>

//<UserCode_End_Bean_lfHr17>

          try
          {
            lfHr18.setOpaque(true);
            lfHr18.setHorizontalTextPosition(0);
            lfHr18.setHorizontalAlignment(0);
            lfHr18.setForeground(new Color(-16777213));
            lfHr18.setText(resourceBundle.getString("18:00"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+lfHr18,ex); 
          }

//<UserCode_Begin_Bean_lfHr18>

//<UserCode_End_Bean_lfHr18>

          try
          {
            lfHr19.setOpaque(true);
            lfHr19.setForeground(new Color(-16777214));
            lfHr19.setHorizontalTextPosition(0);
            lfHr19.setHorizontalAlignment(0);
            lfHr19.setText(resourceBundle.getString("19:00"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+lfHr19,ex); 
          }

//<UserCode_Begin_Bean_lfHr19>

//<UserCode_End_Bean_lfHr19>

          try
          {
            lfHr20.setOpaque(true);
            lfHr20.setForeground(new Color(-16777211));
            lfHr20.setHorizontalTextPosition(0);
            lfHr20.setHorizontalAlignment(0);
            lfHr20.setText(resourceBundle.getString("20:00"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+lfHr20,ex); 
          }

//<UserCode_Begin_Bean_lfHr20>

//<UserCode_End_Bean_lfHr20>

          try
          {
            lfHr21.setOpaque(true);
            lfHr21.setHorizontalTextPosition(0);
            lfHr21.setHorizontalAlignment(0);
            lfHr21.setForeground(new Color(-16777211));
            lfHr21.setText(resourceBundle.getString("21:00"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+lfHr21,ex); 
          }

//<UserCode_Begin_Bean_lfHr21>

//<UserCode_End_Bean_lfHr21>

          try
          {
            lfHr22.setOpaque(true);
            lfHr22.setForeground(new Color(-16777211));
            lfHr22.setHorizontalTextPosition(0);
            lfHr22.setHorizontalAlignment(0);
            lfHr22.setText(resourceBundle.getString("22:00"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+lfHr22,ex); 
          }

//<UserCode_Begin_Bean_lfHr22>

//<UserCode_End_Bean_lfHr22>

          try
          {
            lfHr23.setOpaque(true);
            lfHr23.setHorizontalTextPosition(0);
            lfHr23.setHorizontalAlignment(0);
            lfHr23.setForeground(new Color(-16777214));
            lfHr23.setText(resourceBundle.getString("23:00"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+lfHr23,ex); 
          }

//<UserCode_Begin_Bean_lfHr23>

//<UserCode_End_Bean_lfHr23>

          try
          {
            regIntPanel.setBorder(new javax.swing.border.BevelBorder(0));
            regIntPanel.setMaximumSize(new Dimension(327,327));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+regIntPanel,ex); 
          }

//<UserCode_Begin_Bean_regIntPanel>

//<UserCode_End_Bean_regIntPanel>

          try
          {
            lfImage.setHorizontalTextPosition(0);
            lfImage.setHorizontalAlignment(0);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+lfImage,ex); 
          }

//<UserCode_Begin_Bean_lfImage>

//<UserCode_End_Bean_lfImage>

          try
          {
            lfHTML.setBorder(new javax.swing.border.EtchedBorder(0));
            lfHTML.setForeground(new Color(-16777214));
            lfHTML.setFont(new Font("Dialog",1,12));
            lfHTML.setText(resourceBundle.getString("<html><body><ul><li><font color=000000 size=-1>Network Rediscovery Interval is the time interval between two complete discovery of networks in hours. This Network Rediscovery Interval can be configured using this Regular Interval Form.When the discovery engine has finished pinging all the ipaddresses in a network,it will wait for the time configured here and start rediscovery on the network.<li><font color=000000  size=-1>The user can schedule the rediscovery process by specifying the rediscovery interval in hours or minutes or seconds or in any of the combination as given in the below given Form.The rediscovery process can even be configured for particular hours on a particular date/day. This can be done using the User's Scheduler option</font></ul></body></html>"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+lfHTML,ex); 
          }

//<UserCode_Begin_Bean_lfHTML>

//<UserCode_End_Bean_lfHTML>

          try
          {
            minSecSepLabel.setText(resourceBundle.getString(" :"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+minSecSepLabel,ex); 
          }

//<UserCode_Begin_Bean_minSecSepLabel>

//<UserCode_End_Bean_minSecSepLabel>

          try
          {
            ndSec.setText(resourceBundle.getString("Seconds"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+ndSec,ex); 
          }

//<UserCode_Begin_Bean_ndSec>

//<UserCode_End_Bean_ndSec>

          try
          {
            hrMinSepLabel.setText(resourceBundle.getString(" :"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+hrMinSepLabel,ex); 
          }

//<UserCode_Begin_Bean_hrMinSepLabel>

//<UserCode_End_Bean_hrMinSepLabel>

          try
          {
            ndMins.setText(resourceBundle.getString("Minutes"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+ndMins,ex); 
          }

//<UserCode_Begin_Bean_ndMins>

//<UserCode_End_Bean_ndMins>

          try
          {
            minSecSep.setText(resourceBundle.getString(" :"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+minSecSep,ex); 
          }

//<UserCode_Begin_Bean_minSecSep>

//<UserCode_End_Bean_minSecSep>

          try
          {
            hrMinSep.setText(resourceBundle.getString(" :"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+hrMinSep,ex); 
          }

//<UserCode_Begin_Bean_hrMinSep>

//<UserCode_End_Bean_hrMinSep>

          try
          {
            ndHours.setText(resourceBundle.getString("Hours"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+ndHours,ex); 
          }

//<UserCode_Begin_Bean_ndHours>

//<UserCode_End_Bean_ndHours>

          try
          {
            netDiscInterval.setText(resourceBundle.getString("Network Rediscovery Interval"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+netDiscInterval,ex); 
          }

//<UserCode_Begin_Bean_netDiscInterval>

//<UserCode_End_Bean_netDiscInterval>

          try
          {
            okButton.setText(resourceBundle.getString("OK"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+okButton,ex); 
          }

//<UserCode_Begin_Bean_okButton>

//<UserCode_End_Bean_okButton>

          try
          {
            cancelButton.setText(resourceBundle.getString("CANCEL"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+cancelButton,ex); 
          }

//<UserCode_Begin_Bean_cancelButton>

//<UserCode_End_Bean_cancelButton>
		Dates1.setPreferredSize(new Dimension(Dates1.getPreferredSize().width+19,Dates1.getPreferredSize().height+0));

  
          //<End_setUpProperties>
		lfHTML.setForeground(Color.black);
		okButton.setPreferredSize(cancelButton.getPreferredSize());
       	lfImage.setIcon(MainScreen.getCommonInterface().getImage("rediscoveryinterval.png","images/runtimeadmin")); 
		
    }

/**
     * Get the value of RediscoveryInterval.
     * @return value of RediscoveryInterval.
     */
    public String getRediscoveryInterval() 
    {
        return RediscoveryInterval;
    }
    
    /**
     * Set the value of RediscoveryInterval.
     * @param v  Value to assign to RediscoveryInterval.
     */
    public void setRediscoveryInterval(String rediscInt)
    {
        this.RediscoveryInterval = rediscInt;
    }
    
    public Hashtable addRediscoveryInterval()
    {
        Hashtable hash = new Hashtable();
        String rediscValue=null;
        
        if (!rbRegInt.isSelected())
        {
             
         if ((dayButPanel.getComponents().length>0) || (dayButPanel.getComponents().length>0))
              {
               
             setRediscoveryInterval("-1:-1:-1");
              }
             else
             {
                
                 setRediscoveryInterval("24:0:0");  
             }
        }	        
        else if ((tfRediscInt.getValue()>=0) && (tfRediscIntMin.getValue()>=0) && (tfRediscIntSec.getValue()>=0))
        
              {
             rediscValue=(Long.toString(tfRediscInt.getValue()))+":"+(Long.toString(tfRediscIntMin.getValue()))+":"+(tfRediscIntSec.getValue());
                        
            setRediscoveryInterval(rediscValue);
        }
        else
        {
             
            if ((dayButPanel.getComponents().length>0) || (dayButPanel.getComponents().length>0))
              {
                   
             setRediscoveryInterval("-1:-1:-1");
              }
             else
             {
                  
                 setRediscoveryInterval("24:0:0");  
             }
           
        }
        if(getRediscoveryInterval()== null)
        {
            hash.put("REDISCOVER_INTERVAL","");
        }
        else
        {
            hash.put("REDISCOVER_INTERVAL",getRediscoveryInterval().trim());
        }
       
        return hash;
    }

    public void populateObject() 
    {
        if(tempNode == null)
        {
          tempNode = giveXMLNode();
        }
    }

    public Hashtable oldAttributeValues()
    {
        populateObject();
        Hashtable hash = new Hashtable();
        if(tempNode != null)
        {
            if((String)tempNode.getAttribute("REDISCOVER_INTERVAL") != null)
            {
                hash.put("REDISCOVER_INTERVAL",(String)tempNode.getAttribute("REDISCOVER_INTERVAL"));
            }
            if((String)tempNode.getAttribute("HOUR") != null)
            {
                hash.put("HOUR",(String)tempNode.getAttribute("HOUR"));
            }
            if((String)tempNode.getAttribute("DAY_OF_THE_MONTH") != null)
            {
                hash.put("DAY_OF_THE_MONTH",(String)tempNode.getAttribute("DAY_OF_THE_MONTH"));
            }
            if((String)tempNode.getAttribute("DAY_OF_THE_WEEK") != null)
            {
                hash.put("DAY_OF_THE_WEEK",(String)tempNode.getAttribute("DAY_OF_THE_WEEK"));
            }
        }
        return hash;
    }
    
    public void populatingScreen() 
    {
        populateObject();
        if(tempNode != null)
        {
            String rediscInt = (String)tempNode.getAttribute("REDISCOVER_INTERVAL");
            
            if(rediscInt != null)
 
            {
                 
               if (rediscInt.equals("-1") || rediscInt.equals("-1:-1")  || rediscInt.equals("-1:-1:-1"))
               {
                   // rbUserSch.doClick();
                    String dayOfTheMonth = (String)tempNode.getAttribute("DAY_OF_THE_MONTH");
                    String dayOfTheWeek = (String)tempNode.getAttribute("DAY_OF_THE_WEEK");
                   
	        if (dayOfTheMonth != null && !dayOfTheMonth.equals("-1")) 
	   	     {
		
		        rbDates.doClick();
		
		     } 
		
                   
		else if (dayOfTheWeek != null && !dayOfTheWeek.equals("-1")) 
		      {
		           		
		    rbDays.doClick();
		    		
		     }
		else 
		
				{
		
			tfRediscInt.setValue(24);   // Set the value of the Rediscover Interval text field to factory default 
			tfRediscIntMin.setValue(0);
		             tfRediscIntSec.setValue(0);
		       rbRegInt.doClick();     
			}
               }
             else
             {
 
                 
                     StringTokenizer rediscTokens= new StringTokenizer(rediscInt,":");
                     
                     int numOfParams=rediscTokens.countTokens();
                     
                     if (numOfParams==1)
                     	{
                     	
                     	  int rediscInterval= Integer.parseInt(rediscInt);
		
                     	  tfRediscInt.setValue(rediscInterval);
                     	  if (rediscInterval==-1)
                     	  {                  	       
                     	         	 
                     	       tfRediscIntMin.setValue(-1);
                     	       tfRediscIntSec.setValue(-1);
                     	       
                     	  }
                     	  else
                     	  {
                     	  
                     	  tfRediscIntMin.setValue(0);
                     	  tfRediscIntSec.setValue(0);
                     	  }
                     	}
           	          else
           	 	{
           	 	  
            	 int rediscInterval= Integer.parseInt(rediscTokens.nextToken());
            	 int rediscInterMin=Integer.parseInt(rediscTokens.nextToken());
            	 int rediscInterSec=Integer.parseInt(rediscTokens.nextToken());
            	 
            	 if (rediscInterval==-1 || rediscInterMin==-1 || rediscInterSec==-1)
            	{
            	     tfRediscInt.setValue(-1);
            	     tfRediscIntMin.setValue(-1);
            	     tfRediscIntSec.setValue(-1);
            	}
            	else
            	{
            	     
            	 tfRediscInt.setValue(rediscInterval);
            	
            	  	 tfRediscIntMin.setValue(rediscInterMin);
            	
            	
            	
            	
            	 if (numOfParams>2)
            	 {
            	    
            	      
            	 tfRediscIntSec.setValue(rediscInterSec);           	             	 
            	
            	 }
            	 }
           	 	}
           	 	  rbRegInt.doClick();
             }
                
                
            }
            else
            {
                 
                 
                 tfRediscInt.setValue(24);
                 tfRediscIntMin.setValue(0);
                 tfRediscIntSec.setValue(0);
            }
        }
    }
    public Hashtable addDayOfTheWeek()
    {
        
        Hashtable hash = new Hashtable();
        String key = "DAY_OF_THE_WEEK"; 
        Object[] labelDay = dayButPanel.getComponents();       
        String value = null;
        boolean first = true;

if ((hourButPanel.getComponents()).length>0)
	{
	if (!rbDays.isSelected()) 
	{
	     if  (!rbDates.isSelected())
	     {
	          
	          
	         value = "-1";
	        
	     }
	  
	}
	
        else if(rbAllDays.isSelected())
        {
            value = "*";
        }
        else if(rbSpecificDays.isSelected() && labelDay != null)
        {
            for(int i=0;i<labelDay.length;i++)
            {
                if(((JLabel)labelDay[i]).getForeground() == Color.white && ((JLabel)labelDay[i]).getBackground() == Color.blue)
                {
                    if(first)
                    {
                        value = ((JLabel)labelDay[i]).getText().trim();
                        first = false;
                    }
                    else
                    {
                        value = value + ","+((JLabel)labelDay[i]).getText().trim();
                    }
                }
            }
            if(first == true)
            {
                value = "-1";
            }
        }
        }
        if(key!=null && value!=null)
        {
            hash.put(key,value);
        }
        return hash;
    }
   
    
    public Hashtable addDayOfTheMonth()
    {
        Hashtable hash = new Hashtable();
        String key = "DAY_OF_THE_MONTH";
        Object[] labelDt = dateButPanel.getComponents();    
        String value = null;
        boolean first = true;
        	
        	if (!rbDates.isSelected()) {
        	     if (!rbDays.isSelected()) 
	{
	     //tfRediscInt.setValue(24);
                 //tfRediscIntMin.setValue(0);
                 //tfRediscIntSec.setValue(0);
        	  
	  value = "-1";
	}
        	}
        else if(rbAllDates.isSelected())
        {
             
            value = "*";
        }
        else if(rbSpecificDates.isSelected()&& labelDt != null)
        {
             
            for(int i=0;i<labelDt.length;i++)
            {
                if(((JLabel)labelDt[i]).getForeground() == Color.white && ((JLabel)labelDt[i]).getBackground() == Color.blue)
                {
                     
                    if(first)
                    {
                         
                        value = ((JLabel)labelDt[i]).getText().trim();
                        first=false;
                    }
                    else
                    {
                         
                        value = value+"-"+((JLabel)labelDt[i]).getText().trim();
                    }
                }
                
            }
            if(first)
            {
                value = "-1";
            }
        }
        if(key!=null && value!=null)
        {
            hash.put(key,value);
        }
        return hash;
    }
    
    public Hashtable addHourOfTheDay()
    {
        Hashtable hash = new Hashtable();
        String key = "HOUR";
        Object[] labelHr = hourButPanel.getComponents();    
        String value = null;
        boolean first = true;
        
       if ((rbDates.isSelected()) || (rbDays.isSelected()))
  	{
	     
        if(rbAllHours.isSelected())
        {
            value = "*";
        }
        else if(rbSpecificHours.isSelected()&& labelHr != null)
        {
            for(int i=0;i<labelHr.length;i++)
            {
                if(((JLabel)labelHr[i]).getForeground().equals(Color.white) && ((JLabel)labelHr[i]).getBackground().equals(Color.blue))
                {
                    if(first)
                    {
                        value = ((JLabel)labelHr[i]).getText().trim();
                        
                        first = false;
                    }
                    else
                    {
                        value = value+","+((JLabel)labelHr[i]).getText().trim();
                    }
                }
            }
            value=convertTimeToInteger(value);
            if(first == true)
            {
                value = "-1";
            }
        } 
	}
	else
	{
	     value="-1";
	}
        
       	if(key != null && value != null)
        {
            hash.put(key,value);
        }	 
        return hash;
    }


   
    public XMLNode giveXMLNode()
    {
        Vector temp = sParser.getXMLNode("DISCOVERY");
        XMLNode rootNode = null;

        if(temp!=null) 
        {
            for (int i=0; i<temp.size(); i++)
            {
                rootNode = (XMLNode)temp.elementAt(i);
            }
        }
        
        return rootNode;
        
    }

    public void populatingMonth() 
    {
        populateObject();
        if(tempNode != null)
        {
            Object[] labelDt = dateButPanel.getComponents();  
            String newStr = (String)tempNode.getAttribute("DAY_OF_THE_MONTH");
            StringTokenizer tok = null;
            count = 0;
            if(newStr != null && newStr.trim().equals("*"))
            {
                rbAllDates.setSelected(true);
                
                activateMouseListener = false;
                for(int i = 0;i<labelDt.length;i++)
                {
                    ((JLabel)labelDt[i]).setBackground(Color.blue);
                    ((JLabel)labelDt[i]).setForeground(Color.white); 
                }
            }
            
            if((newStr != null && newStr.trim().equals("-1")) || newStr == null)
            {
                rbSpecificDates.setSelected(true);
                activateMouseListener = true;
                for(int i = 0;i<labelDt.length;i++)
                {
                    ((JLabel)labelDt[i]).setBackground(dateButPanel.getBackground());
                    ((JLabel)labelDt[i]).setForeground(Color.black); 
                }
            }
            
            else if(newStr != null && newStr.trim() != "*" && newStr.trim() != "-1")
            {
                tok = new StringTokenizer(newStr.trim(),"-");
                
                while(tok.hasMoreTokens())
                {
                    String str1 = tok.nextToken().trim();
                    if(str1!=null)
                    {
                        for(int i = 0;i<labelDt.length;i++)
                        {
                            String text = ((JLabel)labelDt[i]).getText().trim();
                            if(text.equals(str1.trim()))
                            {
                                rbSpecificDates.setSelected(true);
                                activateMouseListener = true;
                                count++;
                                ((JLabel)labelDt[i]).setBackground(Color.blue);
                                ((JLabel)labelDt[i]).setForeground(Color.white); 
                                break;
                            }
                        }
                    }
                    
                }
            }
        }
    }
    
    public void  populatingWeek() 
    {
        populateObject();
        if(tempNode != null)
        {
            Object[] labelDay = dayButPanel.getComponents(); 
            String newStr = (String)tempNode.getAttribute("DAY_OF_THE_WEEK");
            StringTokenizer tok = null;
           
            if(newStr != null && newStr.trim().equals("*"))
            {
                rbAllDays.setSelected(true);
                activateMouseListener = false;
                for(int i = 0;i<labelDay.length;i++)
                {
                    ((JLabel)labelDay[i]).setBackground(Color.blue);
                    ((JLabel)labelDay[i]).setForeground(Color.white); 
                }
            }
            
            if((newStr != null && newStr.trim().equals("-1")) || newStr == null)
            {
                rbSpecificDays.setSelected(true);
                activateMouseListener = true;
                for(int i = 0;i<labelDay.length;i++)
                {
                    ((JLabel)labelDay[i]).setBackground(dayButPanel.getBackground());
                    ((JLabel)labelDay[i]).setForeground(Color.black); 
                }
            }
            
            if(newStr!=null && newStr.trim() != "*" && newStr.trim() != "-1")
            {
                tok = new StringTokenizer(newStr.trim(),",");
                while(tok.hasMoreTokens())
                {
                    String str1 = tok.nextToken().trim();
                    if(str1!=null)
                    {
                        for(int i = 0;i<labelDay.length;i++)
                        {
                            String text = ((JLabel)labelDay[i]).getText().trim();
                            if(text.equals(str1.trim()))
                            {
                                rbSpecificDays.setSelected(true);
                                activateMouseListener = true;
                                ((JLabel)labelDay[i]).setBackground(Color.blue);
                                ((JLabel)labelDay[i]).setForeground(Color.white); 
                                break;
                            }
                        }  
                    }
                }
            }
        }
    }
  
    public void populatingHour() 
    {
        populateObject();
        if(tempNode != null)
        {
	

            Object[] labelHr = hourButPanel.getComponents(); 
            
            String newStr = (String)tempNode.getAttribute("HOUR");
            String newStr1= (String)tempNode.getAttribute("DAY_OF_THE_WEEK");
            String newStr2 = (String)tempNode.getAttribute("DAY_OF_THE_MONTH");
            
            if (newStr.equals("-1") && ((!newStr1.equals("-1")) || (!newStr2.equals("-1"))))
            {
                
                 
             newStr="10:00";
            }
	    // Fix to avoid number format exception when * set for HOUR field in seed.file
         else if(( !(newStr.equals("*") || newStr.equals(""))))
         {
          newStr=convertIntegerToTime(newStr);    
         }
         
         
         
            StringTokenizer tok = null;

	// if  (rbDates.isSelected()) || (rbDays.isSelected())
            if(newStr != null && newStr.trim().equals("*"))
            {
                 
                rbAllHours.setSelected(true);
                activateMouseListener = false;
                for(int i = 0;i<labelHr.length;i++)
               
                {
                   
                    ((JLabel)labelHr[i]).setBackground(Color.blue);
                    ((JLabel)labelHr[i]).setForeground(Color.white); 
                }
            }
            
            if((newStr != null && newStr.trim().equals("-1")) || newStr == null)
            {
                rbSpecificHours.setSelected(true);
                activateMouseListener = true;
                for(int i = 0;i<labelHr.length;i++)
                {
                    ((JLabel)labelHr[i]).setBackground(hourButPanel.getBackground());
                    ((JLabel)labelHr[i]).setForeground(Color.black); 
                }
            }
            
            if(newStr != null && newStr.trim() != "*" && newStr.trim() != "-1")
            {
                tok = new StringTokenizer(newStr.trim(),",");
                while(tok.hasMoreTokens())
                {
                    String str1 = tok.nextToken().trim();
                    if(str1!=null)
                    {
                        for(int i = 0;i<labelHr.length;i++)
                        {
                            String text = ((JLabel)labelHr[i]).getText().trim();
                            if(text.equals(str1.trim()))
                            {
                                rbSpecificHours.setSelected(true);
                                activateMouseListener = true;
                                ((JLabel)labelHr[i]).setBackground(Color.blue);
                                ((JLabel)labelHr[i]).setForeground(Color.white); 
                                break;
                            }
                        }
                    }
                }
            }
        }
    }

    public void actionPerformed(ActionEvent ae)
    {
        
         
         
        if(ae.getSource()== rbRegInt && rbRegInt.isSelected())
        {
            CardLayout cardLay =(CardLayout)mainCardPanel.getLayout();
            cardLay.show(mainCardPanel,"RegularIntervalPanel");
        }
      else 
      //if(ae.getSource()== rbUserSch && rbUserSch.isSelected())
        {
            CardLayout cardLay =(CardLayout)mainCardPanel.getLayout();
            cardLay.show(mainCardPanel,"UserSchedulerPanel");
        }

        if(ae.getSource()== rbDates && rbDates.isSelected())
        {
            CardLayout cardLayout =(CardLayout)innerCardPanel.getLayout();
            cardLayout.show(innerCardPanel,"DatesPanel");
        }
        else if(ae.getSource()== rbDays && rbDays.isSelected())
        {
            CardLayout cardLayout =(CardLayout)innerCardPanel.getLayout();
            cardLayout.show(innerCardPanel,"DaysPanel");
        }
        Object[] labelDt = dateButPanel.getComponents();
        
              
        if(ae.getSource()==rbAllDates)
        {
             
            for(int i = 0;i < labelDt.length;i++)
            {
                activateMouseListener = false;
                ((JLabel)labelDt[i]).setBackground(Color.blue);
                ((JLabel)labelDt[i]).setForeground(Color.white);
            }
            count = 0;
        }
              
        else if (ae.getSource()==rbSpecificDates)
        {
             
            
            for(int i = 0;i < labelDt.length;i++)
            {
                activateMouseListener = true;
                ((JLabel)labelDt[i]).setBackground(dateButPanel.getBackground());
                ((JLabel)labelDt[i]).setForeground(Color.black);
                
            }
            count=0;
            
        }

        Object[] labelDay = dayButPanel.getComponents();
        if(ae.getSource()==rbAllDays)
        {
             
            
            for(int i = 0;i < labelDay.length;i++)
            {
                activateMouseListener = false;
                ((JLabel)labelDay[i]).setBackground(Color.blue);
                ((JLabel)labelDay[i]).setForeground(Color.white);
               
            }
            
        }
        if (ae.getSource()==rbSpecificDays )
        {
         
            for(int i = 0;i < labelDay.length;i++)
            {
                activateMouseListener = true;
                ((JLabel)labelDay[i]).setBackground(dayButPanel.getBackground());
                ((JLabel)labelDay[i]).setForeground(Color.black);
            }
        }
         if (labelDt.length!=0 && labelDay.length!=0)
		     {
        Object[] labelHr = hourButPanel.getComponents();
        if(ae.getSource()== rbAllHours)
        {
             
            for(int i = 0;i < labelHr.length;i++)
            {
                activateMouseListener = false;
                ((JLabel)labelHr[i]).setBackground(Color.blue);
                ((JLabel)labelHr[i]).setForeground(Color.white);
            }
        }
        else if (ae.getSource()== rbSpecificHours)
        {
             
             
            for(int i = 0;i < labelHr.length;i++)
            {
                activateMouseListener = true;
                ((JLabel)labelHr[i]).setBackground(hourButPanel.getBackground());
                ((JLabel)labelHr[i]).setForeground(Color.black);
            }
        }



		     }
        if((ae.getSource()== okButton) || (ae.getSource()== udokButton))
        {

	if (ae.getSource()== udokButton)
		{
		     if (labelDt.length!=0 && labelDay.length!=0)
		     {
			tfRediscInt.setValue(-1);
	                        tfRediscIntMin.setValue(-1);
                        	tfRediscIntSec.setValue(-1);
		     }
		     else
		     
		     {
		          //tfRediscInt.setValue(24);
		          //tfRediscIntMin.setValue(0);
		          //tfRediscIntMin.setValue(0);
		     }
		     
		}

             
            OK_Clicked();
        }
        else if((ae.getSource() == cancelButton) || (ae.getSource()== udcancelButton))
        {
            Cancel_Clicked();
        }
    }

    public void OK_Clicked()
    {
        isOKclicked = true;
        isModified = true;
        Hashtable tableGen = new Hashtable(1);
        
        Hashtable tableHour = addHourOfTheDay();
        Hashtable tableWeek = addDayOfTheWeek();
        Hashtable tableMonth = addDayOfTheMonth();
        
           
     
 Hashtable tableRedisc = addRediscoveryInterval();
      
        
     
        for(Enumeration en = tableRedisc.keys();en.hasMoreElements();) 
        {
            String key = (String)en.nextElement();
            String value = (String)tableRedisc.get(key);
            tableGen.put(key,value);
        }
 
     for(Enumeration en = tableWeek.keys();en.hasMoreElements();) 
        {
            String key = (String)en.nextElement();
            String value = (String)tableWeek.get(key);
            tableGen.put(key,value);
        }
        
        for(Enumeration en = tableMonth.keys();en.hasMoreElements();) 
        {
            String key = (String)en.nextElement();
            String value = (String)tableMonth.get(key);
            tableGen.put(key,value);
        }
        
        for(Enumeration en = tableHour.keys();en.hasMoreElements();) 
        {
            String key = (String)en.nextElement();
            String value = (String)tableHour.get(key);
            tableGen.put(key,value);
        }
        for(Enumeration en = tableGen.keys();en.hasMoreElements();) 
        {
            String key = (String)en.nextElement();
            String value = (String)tableGen.get(key);
            if(tempNode != null)
            {
                tempNode.setAttribute(key,value);
            }
        }
        this.setVisible(false);
       // hoursSpinBox.setValue(-1);
        MainScreen.setApplyButton(true);
    }

    public void Cancel_Clicked()
    {
        isOKclicked = false;
        rbRegInt.setSelected(true);
        CardLayout cardLay =(CardLayout)mainCardPanel.getLayout();
        cardLay.show(mainCardPanel,"RegularIntervalPanel");
        isModified = false;
        this.setVisible(false);
    }

    
    public void setUpConnections()
  { 

        //<Begin_setUpConnections> 

  
  //<End_setUpConnections>
    } 
    public void stop()
  { 

        //<Begin_stop> 
       if(!running)
 return;
 running=false;

  
       //<End_stop>
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
            if (input.equals("RESOURCE_PROPERTIES")) value = "RuntimeAdministrationResources"; 
            if (input.equals("PORT")) value = "161"; 
            }
        return value;

  
           //<End_getParameter_String>
    } 

    public void init(java.applet.Applet app)
	{
		this.applet = app;
		init();
	}
    public void init()
  { 
        //<Begin_init> 
	if(getParameter("RESOURCE_PROPERTIES" ) != null)
	{
		localePropertiesFileName = getParameter("RESOURCE_PROPERTIES");
	}
	resourceBundle = com.adventnet.apiutils.Utility.getBundle(localePropertiesFileName,getParameter("RESOURCE_LOCALE"),applet);
        if (initialized) return; 
        this.setSize(getPreferredSize().width+803,getPreferredSize().height+911); 
          setTitle(resourceBundle.getString("RediscoveryScheduler"));
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
        // let us set the initialized variable to true so  
        // we dont initialize again even if init is called 
        initialized = true; 

  
         //<End_init>
        setTitle(resourceBundle.getString("Rediscovery Scheduler"));
    } 

     

    public void showStatus(String message)
  {
        //<Begin_showStatus_String>
     System.out.println("Internal Error :"+ message);
     //<End_showStatus_String>
    }
    public void showStatus(String message,Exception ex)
  {
        //<Begin_showStatus_String_Exception>
     System.out.println("Internal Error :"+ message);
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

 
  public void setProperties(java.util.Properties props)
  {
  //<Begin_setProperties_java.util.Properties> 

  
  //<End_setProperties_java.util.Properties>
  }
  public void close()
  {
      this.dispose();
  }
  
  
  public String convertIntegerToTime(String intTime)
{
String StringTime=intTime;
StringTokenizer st= new StringTokenizer(StringTime,",");
String constructedValues="";
while (st.hasMoreElements())
{
String allValuesToPut="";
String hour=(String)st.nextElement();
if (hour.equals("-1"))
{
     allValuesToPut="10";
}
else if (hour.equals("24"))
{
   
    allValuesToPut="00"; 
}
else if (Integer.parseInt(hour)<10)
{
     if (!hour.startsWith("0"))
     {
         allValuesToPut="0"+hour;    
     }
    else
    {
    allValuesToPut=""+hour;
    }
}
else
    {
    allValuesToPut=""+hour;
    }

allValuesToPut+=":00,";
constructedValues+=allValuesToPut;

}
int s=constructedValues.length()-1;
String toPut=constructedValues.substring(0,s);
return toPut;
}



public String convertTimeToInteger(String StrTime)
{
String StringTime=StrTime;
if (StrTime==null)
{
return "10";    
}

StringTokenizer st= new StringTokenizer(StringTime,",");

String allValuesToPut="";
while (st.hasMoreElements())
{
String hourWithColon=(String)st.nextElement();
StringTokenizer st1= new StringTokenizer(hourWithColon,":");
String hourwoColon=(String)st1.nextElement();
if (hourwoColon.equals("00"))
{
    hourwoColon="24"; 
}

hourwoColon+=",";
allValuesToPut+=hourwoColon;
}

int s=allValuesToPut.length()-1;
String toPut=allValuesToPut.substring(0,s);

return toPut;
}
}


