//$Id: MapCustomProp.java,v 1.2 2007/02/22 15:02:56 srajeswari Exp $

package com.adventnet.nms.mapui;

//util imports 
import java.util.*;

//awt imports
import java.awt.*;
import java.awt.event.*;
import java.awt.GridLayout;

//swing imports
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;
import javax.swing.text.*;
import javax.swing.JDialog;

//WebNMS imports
import com.adventnet.nms.util.*;
import com.adventnet.nms.startclient.*;
import com.adventnet.nms.mapui.*;
import com.adventnet.nms.mapui.MapApplet;


/**
 * This is an example class that is used to show the Details of an
 * object and properties of a managed object. The purpose of this example 
 * is to exhibit the  methodology of writing user defined Data forms that could 
 * override the default data forms present in the Web NMS Client.
 * <p>The class extends com.adventnet.nms.util.DataFormCustomizer 
 * overiding the getFormProperties(), setFormProperties() and getCustomizer() methods. 
 * <p>The Web NMS on instantiating this class registers itself as a PropertyChangeListener
 * to this class and calls the setFormProperties() method on this class
 * passing a Vector of FormObjects as a parameter, where each FormObject 
 * will contain a property of the selected Event.  The class using
 * this, constructs a Dialog. Then the Web NMS subsequently calls
 * the getCustomizer() method to get the Dialog UI and displays it.
 * 
 <p>After the user had finished  modifying the properties that could be 
 * edited of the Event selected, and presses the 'Modify'
 * button firePropertyChange() method in the  actionPerformed() method
 * will be called.
 * 
 * <pre>
 *               Calling the firePropertyChange() method will intimate the Web NMS
 *               that the customizer class has completed its function. The Web NMS
 *               on receiving this intimation will call the getFormProperties()
 *               method to get the modified FormObjects vector and then proceeds
 *               to modify the properties of the Event.
 * </pre>* 
 * 
 * FormObjects as a Vector through getFormProperties() method and constructs an UI.
 * Their properties can be  modified and  updated through the modify button,finally are 
 * returned  when Web NMS calls getFormProperties() Method.
 * getCustomizer() method returns a window to the Web NMS.
 */

public class MapCustomProp extends GroupedDataFormCustomizer implements ActionListener,ChangeListener,KeyListener,WindowListener
{

        private Vector  objects;                                                        //for hold the properties                                                                                                        

        private Hashtable groupedObjects;     // for holding the grouped form properties             
        private JDialog dialog ; 

        private MapApplet applet;
        private MapSymbolComponent map;

        private Vector symbolPropVec           = new Vector();  //for hold the symbol properties
        private Vector managedObjPropVec       = new Vector();  //for hold the managed object Prop.

        private Vector userSymbolPropVec;
        private Vector userManagedObjPropVec;
        private Vector userMapPropVec;

        private Vector componentPropVec         = new Vector(); //for hold the properties component                                              
        private Vector componentManagedVec      = new Vector(); //for hold the managed object properties                                 

        private Vector symbolPropUpdateVec      = new Vector(); //for update the symbol properties values
        private Vector managedObjPropUpdateVec  = new Vector(); //for update the managed object properties values

        private Vector mapPropUpdateVec         = new Vector(); //for update the map properties value
        private Vector mapPropComponents        = new Vector(); //for update the map properties component
        private Vector mapCriteriaPropComponents = new Vector();//for update the map criteria properties components
        private Vector mapCriteriaPropsUpdateVec = new Vector(); //for update the map criteria properties vector

        private int noOfSelectedSymbols = 0;//hold the value of number of selected symbols
        private JFrame parentFrame;
        private boolean selected;

        private GridBagLayout       gbLayout    = new GridBagLayout();
        private GridBagConstraints  constraints = new GridBagConstraints();;

        private JPanel symbolCardPanel          = new JPanel(new CardLayout());//panel for symbol properties
        private JPanel managedCardPanel         = new JPanel(new CardLayout());//panel for managed object properties
        private JPanel mapCardPanel             = new JPanel(new CardLayout());
        private Font font                       = NmsClientUtil.getFont("DIALOG");//No Internationalisation
        //checkbox for save changes on server
        private JCheckBox saveCheck             = new JCheckBox(NmsClientUtil.GetString("Save changes on server"),false);
        private boolean indicator;
        private boolean networkdbForm = false;
        private boolean setForManagedObjPropDisplay = true;

        private boolean snmpDisplay = false;
    /** Boolean is added in order to identify when TL1 MO properties are received */
        private boolean tl1Display = false;
        private boolean group;
        private boolean childrenkey;
        private boolean ipPropDisplay = false;

    private static int count = 0;  //by ramanr...added to fix the problem occured while showing managedobject properties when multiple selection is made.

    private UserPropDialog propDialog; //Inner class for popping up the dialog
    private Vector criteriaPropsVec = null; 
    private JPanel holdCritPanel=null; //which holds the criteria panel and the button panel.
    private JPanel criteriaPanel = null;
    private JDialog mapPropertiesDialog;
    MapClientAPI mapClientAPI= null;
    MapModelAPI mapModelAPI = null;

    DefaultMapModel model=null;

        /**
         *This method is called by Web NMS, which overirdes it's  counterpart 
         *in com.adventnet.nms.util.GroupedDataFormCustomizer
         *
         *@return "Hashtable" returns Hashtable containing the group form properties//No Internationalisation
         */
        public Hashtable getGroupFormProperties()
        {
           
                return groupedObjects;
        }
        /**  
         * This method overrides the method in com.adventnet.nms.util.GroupedDataFormCustomizer 
         * and is used for initialisation
         * 
         * @param groupedFormObjects  a Hashtable containing the groupName as a key and FormObjects Vector as a value
         */

        public void setGroupFormProperties(Hashtable groupedFormObjects)
        {
           
           
            groupedObjects = groupedFormObjects;
        }

        /** 
         * This method is called by Web NMS, which overrides it's counterpart
         * in com.adventnet.nms.util.DataFormCustomizer  
         * 
         * @return "Vector"    Returns a Vector containing FormObjects//No Internationalisation
         */

        public Vector getFormProperties()
        {               
                return objects;
        }

        /**  
         * This method overrides the method in com.adventnet.nms.util.DataFormCustomizer 
         * and is used for initialisation
         * 
         * @param formObjects  a Vector containing FormObjects
         */

        public void setFormProperties(Vector formObjects)
        {
                objects = formObjects;
        }

        /**  
         * This method overrides the method in com.adventnet.nms.util.DataFormCustomizer. 
         * This method is called by Web NMS after passing FormObjects through setFormProperties()
         * and gets back a mainDialog containing UI
         * 
         * @return "Window"  Returns a Window that contains UI//No Internationalisation
         */

        public Window getCustomizer()
        {
                return init();  
        }



        /** 
         * init() method constructs the UI that contain property name of the FormObject
         * and it's value as it's columns. Value is displayed  either in textfield or textarea or 
         * combobox or checkBox upon it's nature.
         * 
         * @return "Window"    a Window//No Internationalisation
         */

        private Window init()
        {
                String formName;

                formName    = getFormName();
                parentFrame = getParentFrame();

                NmsPanel nmsPanel = getParentPanel();

                if(nmsPanel != null)
                {
                        if(nmsPanel instanceof MapApplet)
                        {
                                applet = (MapApplet)nmsPanel;
                                noOfSelectedSymbols = applet.getSelectedSymbols().size();
                                model=applet.getModel();
                                mapClientAPI=MapClientAPI.getInstance();
                                mapModelAPI=MapModelAPI.getInstance();
                        }
                        else if(nmsPanel instanceof NmsListView)
                        {
                                networkdbForm = true;
                        }
                }
                if(applet != null) applet.setCursor(NmsClientUtil.wait_cursor);

                if(!networkdbForm)
                {
                        if(noOfSelectedSymbols > 0 )
                        {
                            map    = (MapSymbolComponent)applet.getSelectedSymbols().elementAt(count++);//by ramanr......
                                if(noOfSelectedSymbols == count)  count = 0;//by ramanr....

                                if (formName.equalsIgnoreCase("MANAGED-OBJECT-FORM"))//No Internationalisation
                                {
                                        selected = false;
                                        getSymbolProperties();//get the symbol properties 
                                }
                                else
                                {
                                        selected = true;
                                        getManagedObjProperties();//get the managed object properties
                                }

                                dialog = getPropertiesDialog();//get the dialog with symbol & managed object properties
                                //if(noOfSelectedSymbols == count)  count = 0;//by ramanr....
                        }
                        else
                        {
                                dialog = getMapPropertiesDialog();//get the map properties dialog
                        }
                }
                else
                {
                        getSymbolProperties();
                        dialog = getPropertiesDialog();
                }
                if(applet != null) applet.setCursor(NmsClientUtil.normal_cursor);
                return(dialog);
        }


        /**
         * This method extracts the managed object property
         * names & values from the objects .
         */
        // symbol properties
        private void getManagedObjProperties()
        {
                int size;
                int noOfRows = objects.size();

                String selectedSymbolName = null;
                Properties prop = null;
                FormObject formObject;

                for(size = 0;size < noOfRows;size++)          
                {

                        formObject = (FormObject)objects.elementAt(size);        
                        String forcancel = formObject.getPropertyName().toString();
                        if((!forcancel.equalsIgnoreCase("nx"))&&(!forcancel.equalsIgnoreCase("ny"))&&(!forcancel.equalsIgnoreCase("WebNMS")))//No Internationalisation
                        {
                                if(forcancel.equalsIgnoreCase("topology"))//No Internationalisation
                                {
                                        topologyVec = translateTopologyStringToVector(formObject.getCurrentValue().toString()); 
                                }else
                                {
                                        symbolPropVec.addElement(formObject.getPropertyName().toString());
                                        symbolPropVec.addElement(formObject.getCurrentValue().toString());
                                }
                        }

                }       

                selectedSymbolName = map.getObjName();

                if(selectedSymbolName != null)
                {
                        //get the managed object properties from the selected symbol
                    prop=mapClientAPI.getManagedObjectProperties(selectedSymbolName);
                   //  prop = MapApplet.mapClientInitializer.getManagedObjectProperties(selectedSymbolName);
                }

                if(prop != null)            
                {      

                        for(Enumeration enumerate  = prop.propertyNames (); enumerate.hasMoreElements ();)
                        {
                                String propName  = (String) enumerate.nextElement ();

                                if(!(propName.equalsIgnoreCase("SubNetNetmasks"))&&!(propName.equalsIgnoreCase("SubNets"))&&!(propName.equalsIgnoreCase("stringstatus")))//No Internationalisation
                                {
                                        managedObjPropVec.addElement(propName);

                                        Object propValue = prop.get (propName);
                                        if(propName.equalsIgnoreCase("isSNMP"))//No Internationalisation
                                        {
                                                //if(((String)propValue).equalsIgnoreCase("false"))//No Internationalisation
                                                if(((String)propValue).equalsIgnoreCase("true"))//No Internationalisation
                                                {
                                                        snmpDisplay =true;
                                                }
                                        }
                                        if(propName.equalsIgnoreCase("isGroup"))//No Internationalisation
                                        {
                                                if(((String)propValue).equalsIgnoreCase("true"))//No Internationalisation
                                                {
                                                        group = true;
                                                }
                                        }
                                        if(propName.equalsIgnoreCase("isContainer"))//No Internationalisation
                                        {
                                                if(((String)propValue).equalsIgnoreCase("true"))//No Internationalisation
                                                {
                                                        childrenkey = true;
                                                }
                                        }
                                        managedObjPropVec.addElement(propValue);
                                }
                        }
                }
                else 
                {
                        setForManagedObjPropDisplay = false;
                }
        }

        /**
         * This method extracts the symbol property 
         * names & values
         */
        private void getSymbolProperties()
        {
                int size;
                int noOfRows = objects.size();

                String selectedSymbolName = null;
                Properties prop = null;
                FormObject formObject;

                for(size = 0;size < noOfRows;size++)          
                {

                        formObject = (FormObject)objects.elementAt(size);        
                        String forCancel = formObject.getPropertyName().toString();
                        if(!(forCancel.equalsIgnoreCase("SubNetNetmasks"))&&!(forCancel.equalsIgnoreCase("SubNets"))&&!(forCancel.equalsIgnoreCase("stringstatus")))//No Internationalisation
                        {
                                if(forCancel.equalsIgnoreCase("isSNMP"))//No Internationalisation
                                {
                                        //              if(((String)(formObject.getCurrentValue())).equalsIgnoreCase("false"))//No Internationalisation
                                        if(((String)(formObject.getCurrentValue())).equalsIgnoreCase("true"))//No Internationalisation
                                        {
                                                snmpDisplay =true;
                                        }
                                }
                                if(forCancel.equalsIgnoreCase("isGroup"))//No Internationalisation
                                {
                                        if(((String)(formObject.getCurrentValue())).equalsIgnoreCase("true"))//No Internationalisation
                                        {
                                                group =true;
                                        }
                                }
                                if(forCancel.equalsIgnoreCase("isContainer"))//No Internationalisation
                                {
                                        if(((String)(formObject.getCurrentValue())).equalsIgnoreCase("true"))//No Internationalisation
                                        {
                                                childrenkey =true;
                                        }
                                }
                                
                                managedObjPropVec.addElement(formObject.getPropertyName().toString());
                                managedObjPropVec.addElement(formObject.getCurrentValue().toString());
                        }

                }       


                if(!networkdbForm)
                {       
                        if(map != null)
                        {
                                //get the symbol properties from the selected symbol
                                prop = map.getProperties ();
                        }
                        if(prop!=null)
                        {
                                for(Enumeration enumerate  = prop.propertyNames (); enumerate.hasMoreElements ();)
                                {
                                        String propName  = (String) enumerate.nextElement ();
                                        if((!propName.equalsIgnoreCase("nx"))&&(!propName.equalsIgnoreCase("ny"))&&(!propName.equalsIgnoreCase("WebNMS")))//No Internationalisation
                                        {
                                                symbolPropVec.addElement(propName);

                                                Object propValue = prop.get (propName);
                                                symbolPropVec.addElement(propValue);
                                        }
                                }       
                        }
                }

        }

        boolean linkset = false;
        /**
         * This method constructs the UI component for the selected symbol's property
         * names&values and put all the components in the dialog.
         * 
         * @return "JDialog" dialog with the UI components for properties //No Internationalisation
         */
        private JDialog getPropertiesDialog()
        {
                //JDialog propertiesDialog = new JDialog(parentFrame,"AdventNet"+" "+"Object Properties",false);//No Internationalisation
                JDialog propertiesDialog = null; 
                if(!networkdbForm)
                {
                        propertiesDialog = new JDialog(parentFrame,NmsClientUtil.GetString("AdventNet")+" "+NmsClientUtil.GetString("Object Properties"),false);
                }
                else
                {
                        propertiesDialog = new JDialog(parentFrame,NmsClientUtil.GetString("AdventNet")+" "+NmsClientUtil.GetString("Managed Object Properties"),false);
                }

                //pane for symbol & managed properties
                JTabbedPane tabbedPane = new JTabbedPane();

                //panels used for display the managed object properties 
                JPanel baseManagedProp    = new JPanel(gbLayout);
                JPanel ipRelatedProp      = new JPanel(gbLayout);
                //JPanel snmpRelatedProp    = new JPanel(gbLayout);
                JPanel statusRelatedProp  = new JPanel(gbLayout);
                JPanel otherProp          = new JPanel(gbLayout);

                //borders for panels
                TitledBorder baseManagedBorder    = BorderFactory.createTitledBorder(NmsClientUtil.GetString("BASE PROPERTIES"));
                TitledBorder ipRelatedBorder      = BorderFactory.createTitledBorder(NmsClientUtil.GetString("IP RELATED PROPERTIES"));
                TitledBorder statusRelatedBorder  = BorderFactory.createTitledBorder(NmsClientUtil.GetString("STATUS RELATED PROPERTIES"));
                //TitledBorder snmpRelatedBorder    = BorderFactory.createTitledBorder(NmsClientUtil.GetString("SNMP RELATED PROPERTIES"));
                TitledBorder otherBorder          = BorderFactory.createTitledBorder(NmsClientUtil.GetString("OTHER PROPERTIES"));

                //justify the border positions
                baseManagedBorder.setTitleJustification(TitledBorder.LEFT);
                ipRelatedBorder.setTitleJustification(TitledBorder.LEFT);
                statusRelatedBorder.setTitleJustification(TitledBorder.LEFT);
                //snmpRelatedBorder.setTitleJustification(TitledBorder.LEFT);   
                otherBorder.setTitleJustification(TitledBorder.LEFT);   

                JPanel snmpRelatedProp = null;
                TitledBorder snmpRelatedBorder = null;
                JPanel displayManage = null;

                if(snmpDisplay)
                {
                        snmpRelatedProp    = new JPanel(gbLayout);      
                        snmpRelatedBorder    = BorderFactory.createTitledBorder(NmsClientUtil.GetString("SNMP RELATED PROPERTIES"));
                        snmpRelatedBorder.setTitleJustification(TitledBorder.LEFT);   
                        displayManage     = new JPanel(new BorderLayout());
                }
                else
                {
                        displayManage     = new JPanel(new GridLayout(2,1));
                }
                /** If tl1port property presents in vector 
                    we set the tl1Display as true 
                */                
                if(managedObjPropVec != null)
                {
                    if(managedObjPropVec.contains("tl1port"))
                        tl1Display = true;
                }
                JPanel managedObject     = new JPanel(new BorderLayout());  

                JPanel manageDisplay     = new JPanel(new BorderLayout());
                JPanel moreManage        = new JPanel();

                //panels for button
                JPanel managedButton     = new JPanel(new FlowLayout(FlowLayout.CENTER,20,5));
                JPanel buttonPanel       = new JPanel(new FlowLayout(FlowLayout.CENTER,20,5));

                JPanel managedNextPanel  = new JPanel(new BorderLayout());

                //panel for back button
                JPanel managedBackButPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,90,5)); 
                //panel for next button
                JPanel managedNextButPanel  = new JPanel(new FlowLayout(FlowLayout.CENTER,90,5));

                //Modify button
                JButton butManModify      = new JButton(NmsClientUtil.GetString("Modify"));
                butManModify.setFont(font);
                
                butManModify.setActionCommand("butManModify");//No Internationalisation
                //Cancel button
                JButton butManCancel      = new JButton(NmsClientUtil.GetString("Close"));
                butManCancel.setFont(font);
                
                butManCancel.setActionCommand("butManCancel");//No Internationalisation

                //help button 
                JButton managedHelp              = new JButton(NmsClientUtil.GetString("Help"));
                managedHelp.setFont(font);
                
                managedHelp.setActionCommand("help");//No Internationalisation
                managedHelp.addActionListener(this);

                //next button
                JButton butManNext                = new JButton(NmsClientUtil.GetString("Next>>"));
                butManNext.setFont(font);
                
                butManNext.setActionCommand("butManNext");//No Internationalisation
                JButton butManAnotherNext                 = new JButton(NmsClientUtil.GetString("Next>>"));
                butManAnotherNext.setFont(font);
        
                butManAnotherNext.setActionCommand("butManNext");//No Internationalisation
                //back button
                JButton butManBack                = new JButton(NmsClientUtil.GetString("<<Back"));
                butManBack.setFont(font);
                
                butManBack.setActionCommand("butManBack");//No Internationalisation

                JButton dummyButton;

                  
                /** If there is TL1 Properties we will set the layout of second card
                    as BorderLayout otherwise it will be gridLayout of TwoRows */
                JPanel tl1RelatedProp = null;
                if(tl1Display)
                {
                    moreManage.setLayout(new BorderLayout());
                    tl1RelatedProp    = new JPanel(gbLayout);   
                    TitledBorder tl1RelatedBorder    = BorderFactory.createTitledBorder(NmsClientUtil.GetString("TL1 RELATED PROPERTIES"));
                    tl1RelatedBorder.setTitleJustification(TitledBorder.LEFT);   
                    tl1RelatedProp.setBorder(tl1RelatedBorder);
                    
                }
                else 
                    moreManage.setLayout(new GridLayout(2,1));


                //scrollpanes for panels
                JScrollPane managedPropPane ;
                JScrollPane moreManagedPropPane;

                int size         = 0;
                int userManagedObjPropVecSize = 0;
                boolean editable = false;
                FormObject formObject;
                Vector valueList = null;
                if(setForManagedObjPropDisplay)
                {
                        if(managedObjPropVec != null)
                        {
                                indicator = false;      
                                size = 0;
                                String testerArr[]    = {"max","ping","snmpping","usertest"};//No Internationalisation
                                /** Some more TL1 Specific Properties are added 
                                 */
                                String namearrayformp[] = {"name","displayname","type","classname","status","managed","discover","ipaddress","netmask","parentnet",
                                                                                   "interfacelist","nodelist","statusupdatetime","statuschangetime","pollinterval",
                                                                                   "failurecount","snmpport","community","writecommunity","sysname","sysdescr","sysoid",
                                                                                   "basemibs","parentkey","hostnetmask","uClass","tester","failurethreshold","ConnectionHandler","Dictionary","Tl1port","PortList","SessionId","NotifyId","StatPollCommand","LoginCommand","InitCommand","InfoCommand"};

                                int incrementformp    = 0;
                                int namearrayformplen = namearrayformp.length;
                                int loopcountformp    = 0;
                                int managedObjPropVecSize = managedObjPropVec.size();

                                if(managedObjPropVecSize > namearrayformplen)
                                {
                                        userManagedObjPropVec = getUserProperties(managedObjPropVec,namearrayformp);
                                        userManagedObjPropVecSize = userManagedObjPropVec.size();
                                }

                                for(loopcountformp=0;loopcountformp < managedObjPropVec.size();loopcountformp=(loopcountformp+2))
                                {       
                                    
                                        String propName  = (String) managedObjPropVec.elementAt(loopcountformp);
                                        String propValue = (String) managedObjPropVec.elementAt((loopcountformp+1));
                                        editable         = true;
                                        propName  = propName.trim();
                                        propValue = propValue.trim();

                                        if(propName.equalsIgnoreCase(namearrayformp[incrementformp]))   
                                        {
                                            if(propName.equalsIgnoreCase (("name"))         ||//No Internationalisation
                                                   propName.equalsIgnoreCase (("type"))         ||//No Internationalisation
                                                   propName.equalsIgnoreCase (("displayname")))//No Internationalisation

                                                {
                                                    if((propName.equalsIgnoreCase("type"))&&(NmsClientUtil.listIcon.getStringForType(propValue,"HELP_FILE")!=null))//No Internationalisation
                                                        {       
                                                                helpURL = NmsClientUtil.getAIM_ROOT()+"help/"+NmsClientUtil.listIcon.getStringForType(propValue,"HELP_FILE");//No Internationalisation
                                                        }
                                                        else
                                                        {
                                                            if(networkdbForm)
                                                            {
                                                                helpURL =NmsClientUtil.getHelpURL("NWDB_Details");
                                                            }
                                                        }
                                                        editable = false;
                                                        if(propName.equalsIgnoreCase("displayname"))//No Internationalisation
                                                        {
                                                                editable = true;
                                                        }
                                                        formObject     = new FormObject (propName, valueList, editable, propValue);
                                                        managedObjPropUpdateVec.addElement(formObject);
                                                        displayFormObject(formObject,baseManagedProp,size,componentManagedVec);                       // displaying it's properties
                                                }
                                                else if(propName.equalsIgnoreCase (("managed")) ||//No Internationalisation
                                                                propName.equalsIgnoreCase (("discover")))//No Internationalisation
                                                {
                                                        editable  = true;
                                                        valueList = new Vector(10,2);

                                                        valueList.addElement ("true");//No Internationalisation
                                                        valueList.addElement ("false");//No Internationalisation

                                                        formObject      = new FormObject (propName, valueList, editable, propValue);
                                                        managedObjPropUpdateVec.addElement(formObject);
                                                        displayFormObject(formObject,baseManagedProp,size,componentManagedVec);
                                                }
                                                else if (propName.equalsIgnoreCase ("status"))//No Internationalisation
                                                {
                                                        editable = false;
                                                        propName = "Status";//No Internationalisation
                                                        if(!networkdbForm)
                                                        {
                                                          int severity = 5;//by default for Clear
                                                          try
                                                          {
                                                                severity = Integer.parseInt(propValue.trim());
                                                          }
                                                          catch(NumberFormatException nfe)
                                                          {
                                                          }
                                                          propValue      = NmsClientUtil.severityInfo.getName(severity);
                                                        }
                                                        formObject      = new FormObject (propName, valueList, editable, propValue);
                                                        managedObjPropUpdateVec.addElement(formObject);
                                                        displayFormObject(formObject,baseManagedProp,size,componentManagedVec);                       // displaying it's properties
                                                }
                                                else if(propName.equalsIgnoreCase (("subNetNetMasks"))   ||//No Internationalisation
                                                                propName.equalsIgnoreCase (("subnets"))          || //No Internationalisation
                                                                propName.equalsIgnoreCase (("ipaddress"))        ||//No Internationalisation
                                                                propName.equalsIgnoreCase (("NetMask"))          ||//No Internationalisation
                                                                propName.equalsIgnoreCase (("parentNet")))//No Internationalisation
                                                {
                                                        ipPropDisplay = true; 
                                                        editable       = false;
                                                        formObject     = new FormObject (propName, valueList, editable, propValue);
                                                        managedObjPropUpdateVec.addElement(formObject);
                                                        displayFormObject(formObject,ipRelatedProp,size,componentManagedVec);
                                                }
                                                else if(propName.equalsIgnoreCase (("interfacelist")))//No Internationalisation
                                                {                               
                                                        ipPropDisplay = true;

                                                        editable       = true;
                                                        valueList = new Vector (10, 1);

                                                        valueList = parseAndGetTheTokens((String) propValue," ");//No Internationalisation

                                                        formObject = new FormObject (propName, valueList, editable, propValue);
                                                        managedObjPropUpdateVec.addElement(formObject);
                                                        displayFormObject(formObject,ipRelatedProp,size,componentManagedVec);                         // displaying it's properties
                                                }
                                                else if(propName.equalsIgnoreCase (("Status"))           ||//No Internationalisation
                                                                propName.equalsIgnoreCase (("statusUpDateTime")) ||//No Internationalisation
                                                                propName.equalsIgnoreCase (("statusChangeTime")) ||//No Internationalisation
                                                                propName.equalsIgnoreCase (("pollinterval"))     ||//No Internationalisation
                                                                propName.equalsIgnoreCase (("FailureCount")))//No Internationalisation
                                                {
                                                        editable = false;
                                                        if((propName.equalsIgnoreCase ("pollinterval"))||(propName.equalsIgnoreCase ("FailureCount")))//No Internationalisation
                                                        {
                                                                editable = true;
                                                        }
                                                        if(propName.equalsIgnoreCase("status"))//No Internationalisation
                                                        {
                                                                propName = "Status Value";//No Internationalisation
                                                        }
                                                        formObject     = new FormObject (propName, valueList, editable, propValue);
                                                        managedObjPropUpdateVec.addElement(formObject);
                                                        displayFormObject(formObject,statusRelatedProp,size,componentManagedVec);
                                                }
                                                else if (propName.equalsIgnoreCase (("snmpport"))       ||//No Internationalisation
                                                                 propName.equalsIgnoreCase (("BaseMibs"))       ||//No Internationalisation
                                                                 propName.equalsIgnoreCase (("community"))          ||//No Internationalisation
                                                                 propName.equalsIgnoreCase (("Writecommunity")) ||//No Internationalisation
                                                                 propName.equalsIgnoreCase (("Sysname"))            ||//No Internationalisation
                                                                 propName.equalsIgnoreCase (("Sysdescr"))           ||//No Internationalisation
                                                                 propName.equalsIgnoreCase (("Sysoid")))//No Internationalisation
                                                {
                                                        if(snmpDisplay)
                                                        {
                                                                if(propName.equalsIgnoreCase (("snmpport"))       ||//No Internationalisation
                                                                   propName.equalsIgnoreCase (("BaseMibs"))       ||//No Internationalisation
                                                                   propName.equalsIgnoreCase (("community"))      ||   //No Internationalisation
                                                                   propName.equalsIgnoreCase (("Writecommunity")) //No Internationalisation
                                                                   )
                                                                {
                                                                        editable = true;
                                                                }
                                                                else
                                                                {
                                                                        editable = false;
                                                                }
                                                                formObject     = new FormObject (propName, valueList, editable, propValue);
                                                                managedObjPropUpdateVec.addElement(formObject);
                                                                displayFormObject(formObject,snmpRelatedProp,size,componentManagedVec);                       // displaying it's properties
                                                        }
                                                }
                                            /** Following Block is added when TL1 Specific 
                                                Properties are received. By now Dictionary is 
                                                set as Editable but it will be of no use. As 
                                                the WebNMS don't support this Parameter as of now.
                                            */
                                            else if (propName.equalsIgnoreCase (("Tl1port"))    
                                                     ||       propName.equalsIgnoreCase (("ConnectionHandler"))
                                                     ||  propName.equalsIgnoreCase (("InfoCommand"))
                                                     ||  propName.equalsIgnoreCase (("SessionId"))
                                                     ||  propName.equalsIgnoreCase (("Dictionary"))
                                                     ||  propName.equalsIgnoreCase (("LoginCommand"))
                                                     ||  propName.equalsIgnoreCase (("NotifyId"))
                                                     ||  propName.equalsIgnoreCase (("InitCommand"))
                                                     ||  propName.equalsIgnoreCase (("PortList"))
                                                     ||  propName.equalsIgnoreCase (("StatPollCommand"))
                                                     )

                                                {
                                                        if(tl1Display)
                                                        {
                                                            if(propName.equalsIgnoreCase (("ConnectionHandler"))       ||
                                                               propName.equalsIgnoreCase (("InfoCommand"))       ||
                                                               propName.equalsIgnoreCase (("LoginCommand"))      ||                                                              
                                                               propName.equalsIgnoreCase (("InitCommand")) ||  
                                                               propName.equalsIgnoreCase (("Dictionary")) || 
                                                               propName.equalsIgnoreCase (("PortList")) ||
                                                               propName.equalsIgnoreCase (("StatPollCommand"))
                                                               )
                                                                   {
                                                                        editable = true;
                                                                   }
                                                                else
                                                                {
                                                                        editable = false;
                                                                }
                                                                formObject     = new FormObject (propName, valueList, editable, propValue);
                                                                managedObjPropUpdateVec.addElement(formObject);
                                                                displayFormObject(formObject,tl1RelatedProp,size,componentManagedVec);                        // displaying it's properties
                                                        }
                                                }

                                                else if (propName.equalsIgnoreCase (("className")))//No Internationalisation
                                                {
                                                        editable = false;
                                                        formObject     = new FormObject (propName, valueList, editable, propValue);
                                                        managedObjPropUpdateVec.addElement(formObject);
                                                        displayFormObject(formObject,baseManagedProp,size,componentManagedVec);                       // displaying it's properties
                                                }
                                                else if( propName.equalsIgnoreCase (("uClass"))           ||//No Internationalisation
                                                                 propName.equalsIgnoreCase (("failurethreshold")) ||//No Internationalisation
                                                                 propName.equalsIgnoreCase (("hostnetmask"))      ||//No Internationalisation
                                                                 propName.equalsIgnoreCase (("parentkey")))//No Internationalisation
                                                {
                                                        editable = true;
                                                        if(propName.equalsIgnoreCase("uClass"))//No Internationalisation
                                                        {
                                                                propName = "UserClass"; //No Internationalisation
                                                        }

                                                        formObject     = new FormObject (propName, valueList, editable, propValue);
                                                        displayFormObject(formObject,otherProp,size,componentManagedVec);

                                                        if(propName.equalsIgnoreCase("UserClass"))//No Internationalisation
                                                        {
                                                                formObject     = new FormObject ("uClass", valueList, editable, propValue);//No Internationalisation
                                                                managedObjPropUpdateVec.addElement(formObject);
                                                        }
                                                        else
                                                        {
                                                                managedObjPropUpdateVec.addElement(formObject);
                                                        }

                                                }
                                                else if(propName.equalsIgnoreCase (("tester")))//No Internationalisation
                                                {
                                                        editable  = true;
                                                        valueList = new Vector();
                                                        for(int testSize =0; testSize < testerArr.length; testSize++)
                                                        {
                                                                valueList.addElement(testerArr[testSize]);
                                                        }
                                                        formObject     = new FormObject (propName, valueList, editable, propValue);
                                                        managedObjPropUpdateVec.addElement(formObject);
                                                        displayFormObject(formObject,otherProp,size,componentManagedVec);
                                                }
                                                else if(propName.equalsIgnoreCase (("nodelist")))//No Internationalisation
                                                {

                                                        ipPropDisplay = true;
                                                        
                                                        valueList = new Vector (10, 1);
                                                        valueList = parseAndGetTheTokens((String) propValue," ");//No Internationalisation
                                                        formObject = new FormObject (propName, valueList, editable, propValue);
                                                        managedObjPropUpdateVec.addElement(formObject);
                                                        displayFormObject(formObject,ipRelatedProp,size,componentManagedVec);                         // displaying it's properties
                                                }

                                                valueList = null;
                                                size++;
                                                incrementformp++;
                                                loopcountformp = -2;
                                                if(incrementformp == namearrayformplen)
                                                {
                                                        break;
                                                }

                                        }
                                        else
                                        {
                                                if(loopcountformp==(managedObjPropVec.size()-2))
                                                {
                                                        if((incrementformp) < (namearrayformplen-1))
                                                        {
                                                                incrementformp++;
                                                                loopcountformp = -2;

                                                        }
                                                }

                                                if((loopcountformp==(managedObjPropVec.size()-2))&&(incrementformp == (namearrayformplen-1)))
                                                {
                                                        break;
                                                }

                                        }
                                }
                        }
                }

                if(userManagedObjPropVecSize > 0)
                {
                        managedBackButPanel.add(butManBack);
                        managedBackButPanel.add(butManAnotherNext);
                }
                else
                {
                        managedBackButPanel.add(butManBack);
                        butManAnotherNext.setEnabled(false);
                        managedBackButPanel.add(butManAnotherNext);
                }

                //adding buttons
                dummyButton= new JButton(NmsClientUtil.GetString("<<Back"));//No Internationalisation // internationalization fix by rameshp
                dummyButton.setFont(font);
                dummyButton.setEnabled(false);
                managedNextButPanel.add(dummyButton);
                managedNextButPanel.add(butManNext);

                //adding buttons
                managedButton.add(butManModify);
                managedButton.add(butManCancel);
                managedButton.add(managedHelp);         

                //adding actionlisteners to the buttons
                butManNext.addActionListener(this);
                butManAnotherNext.addActionListener(this);
                butManBack.addActionListener(this);

                //adding actionlisteners to the buttons
                butManModify.addActionListener(this);
                butManCancel.addActionListener(this);

                //set the border for the panels
                baseManagedProp.setBorder(baseManagedBorder);
                ipRelatedProp.setBorder(ipRelatedBorder);
                statusRelatedProp.setBorder(statusRelatedBorder);
                otherProp.setBorder(otherBorder);
                if(snmpDisplay)
                {
                        snmpRelatedProp.setBorder(snmpRelatedBorder);
                        displayManage.add(baseManagedProp,BorderLayout.NORTH );
                        if(ipPropDisplay)       
                                displayManage.add(ipRelatedProp,BorderLayout.CENTER );
                                
                        displayManage.add(statusRelatedProp,BorderLayout.SOUTH );
                }
                else
                {

                        if(ipPropDisplay)       
                        {
                                displayManage.add(baseManagedProp);    
                                displayManage.add(ipRelatedProp);
                        }
                        else
                        {
                                displayManage.setLayout(new BorderLayout());
                            displayManage.add(baseManagedProp,BorderLayout.NORTH);
                        }
                }

                manageDisplay.add(displayManage,BorderLayout.CENTER);
                manageDisplay.add(managedNextButPanel,BorderLayout.SOUTH);

               managedPropPane     = new JScrollPane(manageDisplay);
    
               managedCardPanel.add("firstpanel",managedPropPane);//No Internationalisation
               
                if(snmpDisplay)
                {    
                    if(tl1Display)
                    {
                        moreManage.add(snmpRelatedProp,BorderLayout.NORTH);
                        moreManage.add(otherProp,BorderLayout.SOUTH);
                    }
                    else
                        {
                            moreManage.add(snmpRelatedProp);
                            moreManage.add(otherProp);
                        }
                }
                else
                {
                        if(!ipPropDisplay)
                        {
                                displayManage.add(statusRelatedProp,BorderLayout.CENTER);
                                displayManage.add(otherProp,BorderLayout.SOUTH);
                        }
                        else
                        {
                            if(tl1Display)
                            {
                                moreManage.add(statusRelatedProp,BorderLayout.NORTH);
                                moreManage.add(otherProp,BorderLayout.SOUTH);
                            }
                            else
                            {
                                moreManage.add(statusRelatedProp);
                                moreManage.add(otherProp);
                            }
 
                        }
                }
                
             if(tl1Display)
             {
                   if(tl1RelatedProp !=null)
                   { 
                       moreManage.add(tl1RelatedProp,BorderLayout.CENTER);
                   }
             }
                      managedNextPanel.add("Center",moreManage);//No Internationalisation
                managedNextPanel.add("South",managedBackButPanel);                                                       //No Internationalisation
                moreManagedPropPane = new JScrollPane(managedNextPanel);
            if(ipPropDisplay)   
                        managedCardPanel.add("secondpanel",moreManagedPropPane);//No Internationalisation

                if(userManagedObjPropVecSize > 0)
                {
                        addUserPropertiesPanel(userManagedObjPropVec , "managed");//No Internationalisation
                }
                // fields for symbolproperties.

                //borders for panels
                TitledBorder displaypanel1Border = BorderFactory.createTitledBorder(NmsClientUtil.GetString("BASE PROPERTIES"));
                TitledBorder displaypanel2Border = BorderFactory.createTitledBorder(NmsClientUtil.GetString("GENERAL PROPERTIES"));
                TitledBorder morepanel1Border = BorderFactory.createTitledBorder(NmsClientUtil.GetString("OBJECT RELATED PROPERTIES"));
                TitledBorder morepanel3Border = BorderFactory.createTitledBorder(NmsClientUtil.GetString("LAYOUT PROPERTIES"));

                //set the borders for the panels
                displaypanel1Border.setTitleJustification(TitledBorder.LEFT);
                displaypanel2Border.setTitleJustification(TitledBorder.LEFT);
                morepanel1Border.setTitleJustification(TitledBorder.LEFT);
                morepanel3Border.setTitleJustification(TitledBorder.LEFT);

                JPanel parentPanel       = new JPanel(new BorderLayout());  

                //panels for holding the symbol properties
                JPanel displaypanel1 = new JPanel(gbLayout);
                JPanel displaypanel2 = new JPanel(gbLayout);
                JPanel morepanel1    = new JPanel(gbLayout);
                JPanel morepanel3    = new JPanel(gbLayout);

                JPanel symbolNextPanel = new JPanel(new BorderLayout());

                //panel for hold the next button
                JPanel symbolNextButPanel      = new JPanel(new FlowLayout(FlowLayout.CENTER,90,5));
                //panel for hold the back button
                JPanel symbolBackButPanel      = new JPanel(new FlowLayout(FlowLayout.CENTER,90,5));

                //panel for hold the save change checkbox
                JPanel saveChanges       = new JPanel(new FlowLayout(FlowLayout.CENTER ));

                JPanel displayPanelifp   = new JPanel(new GridLayout(2,1));
                JPanel morePanelifp      = new JPanel(new GridLayout(2,1));

                JPanel dispPanel         = new JPanel(new BorderLayout());
                JPanel viewDisplay       = new JPanel(new BorderLayout());

                JScrollPane symbolPropPane  ;

                //modify button
                JButton butModify         = new JButton(NmsClientUtil.GetString("Modify"));
        butModify.setFont(font);
        butModify.setActionCommand("butModify");//No Internationalisation
                //cancel button
                JButton butCancel         = new JButton(NmsClientUtil.GetString("Close"));
        butCancel.setFont(font);
                butCancel.setActionCommand("butCancel");//No Internationalisation

                //next button
                JButton butNext                   = new JButton(NmsClientUtil.GetString("Next>>"));
        butNext.setFont(font);
                butNext.setActionCommand("butNext");//No Internationalisation
                
                JButton butAnotherNext                    = new JButton(NmsClientUtil.GetString("Next>>"));
        butAnotherNext.setFont(font);
                butAnotherNext.setActionCommand("butNext");                             //No Internationalisation
                //back button
                JButton butBack                   = new JButton(NmsClientUtil.GetString("<<Back"));
        butBack.setFont(font);
                butBack.setActionCommand("butBack");//No Internationalisation

                //help button
                JButton symbolHelp              = new JButton(NmsClientUtil.GetString("Help"));
        symbolHelp.setFont(font);
                symbolHelp.setActionCommand("help");//No Internationalisation
                symbolHelp.addActionListener(this);
                int userSymbolPropVecSize = 0;
                if(symbolPropVec != null && !networkdbForm)
                {
                        indicator = true;
                        size = 0;
                        //boolean linkset = false;
                        String[] namearrayforsp;

                        for(Enumeration enumerate  = symbolPropVec.elements(); enumerate.hasMoreElements();)
                        {
                                String link;
                                link = (String)enumerate.nextElement();
                                if(link.equalsIgnoreCase("thickness"))//No Internationalisation
                                {
                                        linkset = true;
                                        break;
                                }
                        }

                        if(linkset)
                        {
                                String namearray[ ] = { "name", "label", "objname", "source", "dest",//No Internationalisation
                                                                                "mapname", "menuname", //No Internationalisation
                                                                                "objtype","linktype","mo_type", //No Internationalisation
                                                                                "community", "status", "managed", "initial_mibs",//No Internationalisation
                                                                                "thickness"};//No Internationalisation
                                namearrayforsp = new String[namearray.length];
                                for (int length = 0; length < namearray.length ; length++)
                                {
                                        namearrayforsp[length]=namearray[length];
                                }
                        }
                        else
                        {
                                String namearray[ ] = { "name", "label", "objname","currentTopology","containment" , "mapname",//No Internationalisation
                                                                                "iconname", "menuname", "parentname","groupname", "objtype",//No Internationalisation
                                                                                "mo_type", "community", "status", "managed", "discover",//No Internationalisation
                                                                                "initial_mibs", "anchored", "x", "y", "height", //No Internationalisation
                                                                                "width"};//No Internationalisation

                                namearrayforsp = new String[namearray.length];
                                for (int length = 0; length < namearray.length ; length++)
                                {
                                        namearrayforsp[length]=namearray[length];
                                }
                        }

                        int incrementforsp    = 0;
                        int namearrayforsplen = namearrayforsp.length;
                        int loopcountforsp    = 0;      
                        int symbolPropVecSize = symbolPropVec.size();

                        if(symbolPropVecSize > namearrayforsplen)
                        {
                                userSymbolPropVec = getUserProperties(symbolPropVec , namearrayforsp);
                                userSymbolPropVecSize = userSymbolPropVec.size();
                        }

                        for(loopcountforsp=0; loopcountforsp < symbolPropVec.size(); loopcountforsp=(loopcountforsp+2))
                        {

                                String propName  = (String) symbolPropVec.elementAt(loopcountforsp);
                                String propValue = (String) symbolPropVec.elementAt((loopcountforsp+1));
                                editable         = true;

                                propName = propName.trim();
                                propValue = propValue.trim();

                                if((propName.equalsIgnoreCase(namearrayforsp[incrementforsp])))
                                {
                                        if(propName.equalsIgnoreCase(("objName"))  ||//No Internationalisation
                                           propName.equalsIgnoreCase(("label"))    ||//No Internationalisation
                                           propName.equalsIgnoreCase(("name"))     ||//No Internationalisation
                                           propName.equalsIgnoreCase(("source"))   ||//No Internationalisation
                                           propName.equalsIgnoreCase(("dest")))    //No Internationalisation
                                        {
                                                editable       = true;
                                                if(propName.equalsIgnoreCase(("name")))//No Internationalisation
                                                {
                                                        editable = false;
                                                }
                                                if(propName.equalsIgnoreCase("dest"))//No Internationalisation
                                                {
                                                        propName = "destination";//No Internationalisation
                                                }

                                                formObject     = new FormObject (propName, valueList, editable, propValue);
                                                displayFormObject(formObject,displaypanel1,size,componentPropVec);

                                                if(propName.equalsIgnoreCase("destination"))//No Internationalisation
                                                {
                                                        formObject     = new FormObject ("dest", valueList, editable, propValue);//No Internationalisation
                                                        symbolPropUpdateVec.addElement(formObject);
                                                }
                                                else
                                                {
                                                        symbolPropUpdateVec.addElement(formObject);
                                                }
                                        }
                                        else
                                        {
                                                if(propName.equalsIgnoreCase(("Managed"))  ||//No Internationalisation
                                                   propName.equalsIgnoreCase(("discover")))//No Internationalisation
                                                {
                                                        editable  = false;
                                                        valueList = new Vector(10,2);

                                                        valueList.addElement ("true");//No Internationalisation
                                                        valueList.addElement ("false");//No Internationalisation

                                                        formObject      = new FormObject (propName, valueList, editable, propValue);
                                                        symbolPropUpdateVec.addElement(formObject);

                                                        displayFormObject(formObject,morepanel1,size,componentPropVec);
                                                }
                                                else if(propName.equalsIgnoreCase(("anchored")))//No Internationalisation
                                                {
                                                        editable  = true;
                                                        valueList = new Vector(10,2);

                                                        valueList.addElement ("true");//No Internationalisation
                                                        valueList.addElement ("false");//No Internationalisation

                                                        formObject      = new FormObject (propName, valueList, editable, propValue);
                                                        symbolPropUpdateVec.addElement(formObject);

                                                        displayFormObject(formObject,morepanel3,size,componentPropVec);
                                                }       
                                                else if(propName.equalsIgnoreCase("status"))//No Internationalisation
                                                {
                                                        editable = false;
                                                        valueList = null;
                                                        int severity = 5;//by default for Clear
                                                        try
                                                        {
                                                                severity = Integer.parseInt(propValue.trim());
                                                        }
                                                        catch(NumberFormatException nfe)
                                                        {
                                                        }
                                                        propValue      = NmsClientUtil.severityInfo.getName(severity);

                                                        formObject     = new FormObject (propName, valueList, editable,propValue);// statArr[java.lang.Integer.parseInt(propValue)]);
                                                        symbolPropUpdateVec.addElement(formObject);
                                                        displayFormObject(formObject,morepanel1,size,componentPropVec);
                                                }
                                                else if(propName.equalsIgnoreCase(("menuname")) ||//No Internationalisation
                                                                propName.equalsIgnoreCase(("iconname"))     ||//No Internationalisation
                                                                propName.equalsIgnoreCase(("groupname"))    ||//No Internationalisation
                                                                propName.equalsIgnoreCase(("parentname"))   ||//No Internationalisation
                                                                propName.equalsIgnoreCase(("mapname"))      ||//No Internationalisation
                                                                propName.equalsIgnoreCase(("imageName")))//No Internationalisation
                                                {
                                                        editable = true;
                                                        if(propName.equalsIgnoreCase(("mapname")) ) //No Internationalisation
                                                        {
                                                                editable = false;
                                                        }
                                                        formObject     = new FormObject (propName, valueList, editable, propValue);
                                                        symbolPropUpdateVec.addElement(formObject);
                                                        displayFormObject(formObject,displaypanel2,size,componentPropVec);
                                                }
                                                else if(propName.equalsIgnoreCase(("MO_TYPE"))  ||//No Internationalisation
                                                                propName.equalsIgnoreCase(("community"))    || //No Internationalisation
                                                                propName.equalsIgnoreCase(("objType"))      ||//No Internationalisation
                                                                propName.equalsIgnoreCase(("initial_mibs")) ||//No Internationalisation
                                                                propName.equalsIgnoreCase(("linktype")))    //No Internationalisation
                                                {
                                                        editable = true;
                                                        if(propName.equalsIgnoreCase("objtype"))//No Internationalisation
                                                        {
                                                                propValue = MapSymbolComponent.getStrObjType(Integer.parseInt(propValue));
                                                                valueList = new Vector();
                                                                for(Enumeration en = MapSymbolComponent.objTypes.keys();en.hasMoreElements();)
                                                                {
                                                                        String objType = (String)en.nextElement();
                                                                        //if(!objType.equalsIgnoreCase("background"))no more check for this. symbol objtype is removed.//No Internationalisation
                                                                        {
                                                                                valueList.addElement(objType);
                                                                        }
                                                                }

                                                        }

                                                        if ((propName.equalsIgnoreCase(("MO_TYPE"))  ||//No Internationalisation
                                                                 propName.equalsIgnoreCase(("community"))    || //No Internationalisation
                                                                 propName.equalsIgnoreCase(("initial_mibs"))))//No Internationalisation
                                                        {
                                                                editable = false;
                                                        }
                                                        formObject     = new FormObject (propName, valueList, editable, propValue);
                                                        symbolPropUpdateVec.addElement(formObject);
                                                        displayFormObject(formObject,morepanel1,size,componentPropVec);
                                                }
                                                else if(propName.equalsIgnoreCase("currentTopology") || (propName.equalsIgnoreCase("containment")))//No Internationalisation
                                                {

                                                        editable = true;
                                                        if(propName.equalsIgnoreCase("currentTopology"))//No Internationalisation
                                                        {
                                                                valueList = topologyVec; 
                                                        }else
                                                        {
                                                                valueList = new Vector();
                                                                valueList.addElement("true");//No Internationalisation
                                                                valueList.addElement("false");//No Internationalisation
                                                        }
                                                        formObject     = new FormObject (propName, valueList, editable, propValue);
                                                        symbolPropUpdateVec.addElement(formObject);
                                                        displayFormObject(formObject,displaypanel1,size,componentPropVec);
                                                }
                                                else
                                                {
                                                        editable = true;
                                                        formObject     = new FormObject (propName, valueList, editable, propValue);
                                                        symbolPropUpdateVec.addElement(formObject);
                                                        displayFormObject(formObject,morepanel3,size,componentPropVec);
                                                }
                                        }

                                        valueList = null;
                                        incrementforsp++;
                                        size++;
                                        loopcountforsp = -2;
                                        if(incrementforsp == namearrayforsplen)
                                        {
                                                break;
                                        }
                                }
                                else 
                                {
                                        if(loopcountforsp==(symbolPropVec.size()-2))
                                        {
                                                if((incrementforsp) < (namearrayforsplen-1))
                                                {
                                                        incrementforsp++;
                                                        loopcountforsp = -2;

                                                }
                                        }

                                        if((loopcountforsp==(symbolPropVec.size()-2))&&(incrementforsp == (namearrayforsplen-1)))
                                        {
                                                break;
                                        }

                                }
                        }
                }

                if(userSymbolPropVecSize > 0 )
                {
                        symbolBackButPanel.add(butBack);
                        symbolBackButPanel.add(butAnotherNext);
                }
                else 
                {
                        symbolBackButPanel.add(butBack);
                        butAnotherNext.setEnabled(false);
                        symbolBackButPanel.add(butAnotherNext);
                }

                //set the border for the corresponding panel
                displaypanel1.setBorder(displaypanel1Border);
                displaypanel2.setBorder(displaypanel2Border);
                morepanel1.setBorder(morepanel1Border);
                morepanel3.setBorder(morepanel3Border);

                //adding the buttons
                buttonPanel.add(butModify);      
                buttonPanel.add(butCancel);
                buttonPanel.add(symbolHelp);

                dummyButton = new JButton(NmsClientUtil.GetString("<<Back"));
        dummyButton.setFont(font);
                dummyButton.setEnabled(false);
                symbolNextButPanel.add(dummyButton);
                symbolNextButPanel.add(butNext);

                //adding listeners to the buttons
                butNext.addActionListener(this);
                butAnotherNext.addActionListener(this);
                butBack.addActionListener(this);
                butModify.addActionListener(this);
                butCancel.addActionListener(this);

                //save changes check box added to the panel

                saveCheck.setFont(font);
                saveChanges.add(saveCheck);

                //combine the panels
                displayPanelifp.add(displaypanel1);
                displayPanelifp.add(displaypanel2);

                morePanelifp.add(morepanel1);
                morePanelifp.add(morepanel3);

                symbolNextPanel.add("Center",morePanelifp);//No Internationalisation
                symbolNextPanel.add("South",symbolBackButPanel);//No Internationalisation

                dispPanel.add(displayPanelifp,BorderLayout.CENTER);
                dispPanel.add(saveChanges,BorderLayout.SOUTH);

                viewDisplay.add(dispPanel,BorderLayout.CENTER);
                viewDisplay.add(symbolNextButPanel,BorderLayout.SOUTH);

                symbolPropPane      = new JScrollPane(viewDisplay);

                JScrollPane symbolNextPane = new JScrollPane(symbolNextPanel);

                symbolCardPanel.add("firstpanel",symbolPropPane);//No Internationalisation
                symbolCardPanel.add("scondpanel",symbolNextPane);//No Internationalisation

                if(userSymbolPropVecSize > 0)
                {
                        addUserPropertiesPanel(userSymbolPropVec,"symbol");//No Internationalisation
                }

                parentPanel.add(symbolCardPanel,BorderLayout.CENTER);
                managedObject.add(managedCardPanel,BorderLayout.CENTER);
                parentPanel.add(buttonPanel,BorderLayout.SOUTH);
                managedObject.add(managedButton,BorderLayout.SOUTH);
                CardLayout cd;

                cd  = (CardLayout) symbolCardPanel.getLayout();
                cd.show(symbolCardPanel,"firstpanel");//No Internationalisation

                cd  = (CardLayout) managedCardPanel.getLayout();
                cd.show(managedCardPanel,"firstpanel");//No Internationalisation

                Container c;    
                c = propertiesDialog.getContentPane();
                c.setLayout(new BorderLayout());

                if(!networkdbForm)
                {
                        if(!setForManagedObjPropDisplay)
                        {
                                tabbedPane.addTab(NmsClientUtil.GetString("Symbol Properties"), null, 
                                                                  (Component)parentPanel,
                                                                  null);
                        }
                        else
                        {
                                //panels added in the tabbed pane
                                tabbedPane.addTab(NmsClientUtil.GetString("Managed Object Properties"), null, 
                                                                  (Component)managedObject,
                                                                  null); 
                                tabbedPane.addTab(NmsClientUtil.GetString("Symbol Properties"), null, 
                                                                  (Component)parentPanel,
                                                                  null); 
                                tabbedPane.addChangeListener(this);
                                //tabbed pane selection
                                if(selected)
                                {
                                        tabbedPane.setSelectedIndex(1);
                                }
                                else
                                {
                                        tabbedPane.setSelectedIndex(0);
                                }       
                        }
                        //panels added in the dialog 

                        c.add(tabbedPane, BorderLayout.CENTER);
                }
                else
                {
                        c.add(managedObject, BorderLayout.CENTER);
                }


                propertiesDialog.setSize(460,660);      


                // WindowListener for the Dialog
                propertiesDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                propertiesDialog.addKeyListener(this);
                propertiesDialog.addWindowListener(this);
                return (propertiesDialog);
        }

        //method invoked when the tab selection had changed
        public void stateChanged(ChangeEvent ce)
        {
                CardLayout cd;

                cd  = (CardLayout) symbolCardPanel.getLayout();
                cd.show(symbolCardPanel,"firstpanel");//No Internationalisation

                cd  = (CardLayout) managedCardPanel.getLayout();
                cd.show(managedCardPanel,"firstpanel");//No Internationalisation
        }


        private Vector translateTopologyStringToVector (String val )
        {
                String s = val.trim();
                String value = new String();
                //This is for backward comaptability
                if ( (!s.equals("")) && (!s.equalsIgnoreCase("null"))  )//No Internationalisation
                {
                        if( s.startsWith("$") )//No Internationalisation
                        {
                                while ( s.startsWith("$") )//No Internationalisation
                                {
                                        s = s.substring(1);
                                }
                                if(!s.equals(""))//No Internationalisation
                                {
                                        value = s.trim();
                                }
                                else 
                                {
                                        int out = 0;
                                        for (Enumeration e =  MapClientInitializer.mapLayouts.keys(); e.hasMoreElements() ;) 
                                        {
                                                if(out++ == 0)
                                                        value = ((String)e.nextElement()).trim();
                                                else
                                                        value = value.trim() +","+ ((String)e.nextElement()).trim();//No Internationalisation
                                        }

                                }
                        }else
                        {
                                StringTokenizer st = new StringTokenizer(s,",");//No Internationalisation
                                if(st.countTokens() != 1)
                                {
                                        value = s.trim();
                                }else
                                {
                                        int out = 0;
                                        for (Enumeration e =  MapClientInitializer.mapLayouts.keys(); e.hasMoreElements() ;) 
                                        {
                                                if(out++ == 0)
                                                        value = ((String)e.nextElement()).trim();
                                                else
                                                        value = value.trim() +","+ ((String)e.nextElement()).trim();//No Internationalisation
                                        }

                                }

                        }
                }
                else 
                {
                        int out = 0;
                        for (Enumeration e =  MapClientInitializer.mapLayouts.keys(); e.hasMoreElements() ;) 
                        {
                                if(out++ == 0)
                                        value = ((String)e.nextElement()).trim();
                                else
                                        value = value.trim() +","+ ((String)e.nextElement()).trim();//No Internationalisation
                        }
                }


                Vector returnVector = new Vector();
                StringTokenizer st = new StringTokenizer(value,",");//No Internationalisation
                int i=0;
                while ( st.hasMoreTokens() )
                {
                        String returnValue = ((String) st.nextToken()).trim();
                        if(returnValue.indexOf("(") > -1 &&  returnValue.indexOf(")") > -1)//No Internationalisation
                                returnValue = returnValue.substring(0,returnValue.indexOf("("));//No Internationalisation
                        returnVector.addElement(returnValue);   
                    
                }
                return returnVector;    

        }

        /**
         * This method constructs the UI component for the map property names&values 
         * and put all the UI components in the dialog
         * 
         * @return "JDialog" dialog with UI components for map properties//No Internationalisation
         */
        Vector topologyVec = new Vector();
        private JDialog getMapPropertiesDialog()
        {
            
            
                if(groupedObjects != null)
                {
                        Vector formObj =(Vector) groupedObjects.get("MAP_PROPERTIES");//No Internationalisation
                        if( formObj != null)
                        {
                                objects= formObj;
                        }
                        criteriaPropsVec =(Vector)groupedObjects.get("MAP_CRITERIA_PROPERTIES");//No Internationalisation
                        
                        
                }

                int size = 0;
                boolean editable;
                FormObject formObject;
                Vector valueList = null;
                Vector mapPropVec = new Vector();
                mapPropertiesDialog = new JDialog(parentFrame,NmsClientUtil.GetString("AdventNet")+" "+NmsClientUtil.GetString("Map Properties"),false);

                //border for panels
                TitledBorder namePanelBorder   = BorderFactory.createTitledBorder("");//No Internationalisation
                TitledBorder otherPanelBorder  = BorderFactory.createTitledBorder("");//No Internationalisation

                //panels to hold the map property names & values 
                JPanel namePanel        = new JPanel(gbLayout);
                JPanel otherPanel       = new JPanel(gbLayout);

                //panel for combine the other panels
                JPanel combiningPanel1  = new JPanel(new GridLayout(2,1));
                JPanel combiningPanel2  = new JPanel(new BorderLayout());

                //panel for hold the save changes on server checkbox
                JPanel saveChanges      = new JPanel(new FlowLayout(FlowLayout.CENTER ));
                //panel to hold the buttons
                JPanel buttonPanel              = new JPanel(new FlowLayout(FlowLayout.CENTER,20,5));   

                JPanel mapPropPanel     = new JPanel(new BorderLayout());
                JPanel mapNextPanel     = new JPanel(new FlowLayout(FlowLayout.CENTER,90,5));

                JButton butMapNext      = new JButton(NmsClientUtil.GetString("Next>>"));
        butMapNext.setFont(font);
                butMapNext.setActionCommand("butMapNext");//No Internationalisation

                JButton dummyButton = new JButton(NmsClientUtil.GetString("<<Back"));//modified by rameshp - for consistency
        dummyButton.setFont(font);

                dummyButton.setEnabled(false);

                //modify button
                JButton butModify         = new JButton(NmsClientUtil.GetString("Modify"));
        butModify.setFont(font);
                
                butModify.setActionCommand("butModify");//No Internationalisation
                //cancel button
                JButton butCancel         = new JButton(NmsClientUtil.GetString("Close")); 
        butCancel.setFont(font);
                butCancel.setActionCommand("butCancel");//No Internationalisation

                //help button
                JButton help = new JButton(NmsClientUtil.GetString("Help"));
        help.setFont(font);

                help.setActionCommand("help");//No Internationalisation
                help.addActionListener(this);

                //scrollpane for the final panel
                JScrollPane scrollPane ;

                //array for ordering the display of properties in the form

                for(int objSize = 0; objSize < objects.size(); objSize++)
                {
                        formObject       = (FormObject)objects.elementAt(objSize);
                        String propName  = (String)formObject.getPropertyName();
                        String propValue = (String)formObject.getCurrentValue();

                        if((!propName.equalsIgnoreCase("WebNMS"))&&(!propName.equalsIgnoreCase("className")))//No Internationalisation
                        {
                                if(propName.equalsIgnoreCase("topology"))//No Internationalisation
                                {
                                        topologyVec = translateTopologyStringToVector(propValue); 
                                }else
                                {
                                        mapPropVec.addElement(propName);
                                        mapPropVec.addElement(propValue);
                                }
                        }

                }

                String  namearrayformap[] = {"name","label","objname","treeiconfilename","imagename",//No Internationalisation
                                                                         "maplinkrenderer","mapsymbolrenderer",//No Internationalisation
                                                                         "currentTopology","anchored","autoplacement","helpdoc"};//No Internationalisation

                int namearrayformaplen = namearrayformap.length;        //to hold the length of the array
                int incrementformap    = 0;
                int sizeformap         = 0;
                int userMapPropVecSize = 0;

                if(mapPropVec.size() > namearrayformaplen)
                {
                        userMapPropVec = getUserProperties(mapPropVec , namearrayformap);
                        userMapPropVecSize = userMapPropVec.size();
                }


                for(size = 0; size < objects.size(); size++)
                {
                        formObject       = (FormObject)objects.elementAt(size);
                        String propName  = (String)formObject.getPropertyName();
                        String propValue = (String)formObject.getCurrentValue();
                        
                        propName  = propName.trim();
                        propValue = propValue.trim();

                        if(propName.equalsIgnoreCase(namearrayformap[incrementformap]))
                        {

                                if(propName.equalsIgnoreCase("name")    ||//No Internationalisation
                                   propName.equalsIgnoreCase("label")   ||//No Internationalisation
                                   propName.equalsIgnoreCase("treeiconfilename")     ||//No Internationalisation
                                   propName.equalsIgnoreCase("objname"))//No Internationalisation
                                {
                                        editable   = true;
                                        if(propName.equalsIgnoreCase(("name")))//No Internationalisation
                                        {
                                                editable = false;
                                        }
                                        valueList  = null;
                                        formObject = new FormObject(propName,valueList,editable,propValue);
                                        mapPropUpdateVec.addElement(formObject);
                                        displayFormObject(formObject,namePanel,sizeformap,mapPropComponents);   
                                }
                                else if(propName.equalsIgnoreCase("menuname")      ||//No Internationalisation
                                                propName.equalsIgnoreCase("imagename")     ||//No Internationalisation
                                                propName.equalsIgnoreCase("webnms")        ||//No Internationalisation
                                                propName.equalsIgnoreCase("parentmapkey")  ||//No Internationalisation
                                                propName.equalsIgnoreCase("index"))//No Internationalisation
                                {
                                        editable   = true;
                                        valueList  = null;
                                        formObject = new FormObject(propName,valueList,editable,propValue);
                                        mapPropUpdateVec.addElement(formObject);
                                        displayFormObject(formObject,namePanel,sizeformap,mapPropComponents);   
                                }
                                else if(propName.equalsIgnoreCase("currentTopology"))//No Internationalisation
                                {
                                        editable  = true;
                                        //if(propValue == "null")//later//No Internationalisation

                                        formObject = new FormObject (propName, topologyVec, editable,propValue);
                                        //formObject = new FormObject (propName, topologyVec, editable,getValidToplogy(propValue));
                                        mapPropUpdateVec.addElement(formObject);
                                        displayFormObject(formObject,otherPanel,sizeformap,mapPropComponents);
                                }
                                else if(propName.equalsIgnoreCase("anchored")  ||//No Internationalisation
                                                propName.equalsIgnoreCase("autoplacement"))//No Internationalisation
                                {
                                        editable = true ;
                                        valueList = new Vector(10,2);
                                        valueList.addElement ("true");//No Internationalisation
                                        valueList.addElement ("false");//No Internationalisation
                                        formObject      = new FormObject (propName, valueList, editable, propValue);
                                        mapPropUpdateVec.addElement(formObject);
                                        displayFormObject(formObject,otherPanel,sizeformap,mapPropComponents);
                                }
                                else if(propName.equalsIgnoreCase("helpdoc")) //No Internationalisation
                                {
                                    editable   = true;
                                    valueList  = null;
                                    if(propValue != null)
                                        helpURL = NmsClientUtil.getAIM_ROOT()+propValue;
                                    else
                                        helpURL="../" + NmsClientUtil.getHelpURL("Map_CustomViews_Details");//No Internationalisation
                                        //helpURL = "../help/user_guide/java_ui/maps/use_map_client.html";//No Internationalisation
                                        //      helpURL = "../"+propValue;//No Internationalisation
                                        formObject = new FormObject(propName,valueList,editable,propValue);
                                        mapPropUpdateVec.addElement(formObject);
                                        displayFormObject(formObject,otherPanel,sizeformap,mapPropComponents);
                                }

                                else if(propName.equalsIgnoreCase("maplinkrenderer")       ||//No Internationalisation
                                                propName.equalsIgnoreCase("mapsymbolrenderer")     ||//No Internationalisation
                                                propName.equalsIgnoreCase("classname")) //No Internationalisation
                                {
                                        editable   = true;
                                        valueList  = null;
                                        if(propName.equalsIgnoreCase("mapsymbolrenderer")&& model.getSymbolRendererProps() != null && (model.getSymbolRendererProps()).size()!=0)//No Internationalisation
                                                propValue = propValue +  model.getSymbolRendererValues();
                                        if(propName.equalsIgnoreCase("maplinkrenderer")&& model.getLinkRendererProps() != null && (model.getLinkRendererProps()).size()!=0)//No Internationalisation
                                                propValue = propValue +  model.getLinkRendererValues();
                                                    
                                        formObject = new FormObject(propName,valueList,editable,propValue);
                                        mapPropUpdateVec.addElement(formObject);
                                        displayFormObject(formObject,otherPanel,sizeformap,mapPropComponents);
                                }

                                valueList = null;
                                incrementformap++;
                                sizeformap++;
                                if(incrementformap == namearrayformaplen)
                                {
                                        break;
                                }
                                else
                                {
                                        size = -1;
                                }

                        }
                        else
                        {
                                if(size == (objects.size()-1))
                                {
                                        if(incrementformap < (namearrayformaplen-1))
                                        {
                                                size = -1;
                                                incrementformap++;
                                        }
                                }
                        }
                }
                // the following code  is used  for update the user propereties of the map through the client
                for( int vecSize =0 ; vecSize < userMapPropVecSize; vecSize++ )
                {
                        editable   = true;
                        valueList  = null;
                        String propName = (String) userMapPropVec.elementAt(vecSize);
                        propName  = propName.trim();
                        String propValue = (String) userMapPropVec.elementAt( ++vecSize );
                        propValue = propValue.trim();

                        formObject = new FormObject(propName,valueList,editable,propValue);
                        mapPropUpdateVec.addElement(formObject);
                } 

                saveCheck.setFont(font);
                //set the border for panels     
                namePanel.setBorder(namePanelBorder);
                otherPanel.setBorder(otherPanelBorder);

                saveChanges.add(saveCheck);//add savecheck checkbox

                //actionlisteners added to the buttons
                butModify.addActionListener(this);
                butCancel.addActionListener(this);

                //buttons added 
                buttonPanel.add(butModify);
                buttonPanel.add(butCancel);
                buttonPanel.add(help);

                //combine the panels
                combiningPanel1.add(namePanel);
                combiningPanel1.add(otherPanel);

                mapPropPanel.add("Center",combiningPanel1);//No Internationalisation

                if(userMapPropVecSize > 0)
                {
                        mapNextPanel.add(dummyButton);
                        mapNextPanel.add(butMapNext);
                        butMapNext.addActionListener(this);
                        mapPropPanel.add("South",mapNextPanel);         //No Internationalisation
                }
                mapCardPanel.add("firstmappanel",mapPropPanel);//No Internationalisation
                if(userMapPropVecSize > 0)
                {
                        addUserPropertiesPanel(userMapPropVec,"map");//No Internationalisation
                }               

                CardLayout cd;
                cd  = (CardLayout)mapCardPanel.getLayout();
                cd.show(mapCardPanel,"firstmappanel");//No Internationalisation
                        
                JScrollPane mapScrollPane = new JScrollPane(mapCardPanel);
                        
                
                JTabbedPane tabbedPane = null;
                
                JPanel morePanel=null;
                JButton morebut=null;

                if (criteriaPropsVec != null && criteriaPropsVec.size()>0 )
                {
                        tabbedPane = new JTabbedPane(); 
                        holdCritPanel= new JPanel(new BorderLayout());
                        morePanel=new JPanel(new FlowLayout());
                        morebut = new JButton(NmsClientUtil.GetString("Edit")); //More button for adding or changing th Criteria properties.
                        morebut.setFont(font);
                        criteriaPanel = getCriteriaPanel(criteriaPropsVec);
                        tabbedPane.addTab(NmsClientUtil.GetString("Map Properties"),mapScrollPane);
                        tabbedPane.addTab(NmsClientUtil.GetString("Map Criteria Properties"),holdCritPanel);
                        holdCritPanel.add(criteriaPanel,BorderLayout.CENTER);
                        holdCritPanel.add(morePanel,BorderLayout.SOUTH);
                        morebut.addActionListener(new ActionListener()
                            {
                                public void actionPerformed(ActionEvent ae)
                                {
                                    Properties prop=new Properties();
                                    Properties pr=convertVecToProp(criteriaPropsVec);
                                    propDialog=new UserPropDialog(pr,mapPropertiesDialog);

                                }
                            });
                        morePanel.add(morebut);

                        tabbedPane.setSelectedIndex(0);

                        combiningPanel2.add("Center",   tabbedPane);//No Internationalisation
                }
                else
                {
                        combiningPanel2.add("Center",mapScrollPane);//No Internationalisation
                }
                combiningPanel2.add("South",saveChanges);//No Internationalisation
                        
                scrollPane = new JScrollPane(combiningPanel2);
                        
                //get the dialog and add the panels
                Container c = mapPropertiesDialog.getContentPane();
                c.setLayout(new BorderLayout());
                c.add(scrollPane,BorderLayout.CENTER);
                c.add(buttonPanel,BorderLayout.SOUTH);

                //set the size & visibility of the dialog
                mapPropertiesDialog.setSize(460,600);

                mapPropertiesDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                mapPropertiesDialog.addKeyListener(this);
                return (mapPropertiesDialog);
        }

        private JPanel getCriteriaPanel(Vector criteriaObjects)
        {
                if( criteriaObjects == null || criteriaObjects.size() == 0 )
                        return null;

                JPanel criteriaPanel = new JPanel( gbLayout);
                for(int criteriaSize = 0; criteriaSize < criteriaObjects.size(); criteriaSize++)
                {
                        FormObject      formObject = (FormObject)criteriaObjects.elementAt(criteriaSize);
                        String propName  = (String)formObject.getPropertyName();
                        String propValue = (String)formObject.getCurrentValue();
                        propName  = propName.trim();
                        propValue = propValue.trim();
                        formObject = new FormObject(propName,null,true,propValue);
                        mapCriteriaPropsUpdateVec.addElement(formObject);
                        displayFormObject(formObject,criteriaPanel,criteriaSize,mapCriteriaPropComponents);     
                }
                return criteriaPanel;
        }
        /**
         * actionPerformed() routine is called when Modify/Cancel button is pressed.
         * If the actionCommand is "Modify" then  FormObjects values are updated and //No Internationalisation
         * fireProperty change method is triggered. If the actionCommand is "Cancel"
         * then mainDialog is made invisible without updating FormObjects.//No Internationalisation
         */

        public void actionPerformed(ActionEvent ae)
        {
                Properties managedProp ;
                Properties symbolProp;
                Properties mapProp;

                CardLayout symbolCard=null;
                CardLayout managedCard=null;
                CardLayout mapCard=null;

                if(!networkdbForm)
                {
                    if(noOfSelectedSymbols > 0)
                    {
                        symbolCard  = (CardLayout)symbolCardPanel.getLayout();
                        managedCard = (CardLayout)managedCardPanel.getLayout();
                    }
                    else
                    {
                        mapCard = (CardLayout)mapCardPanel.getLayout();
                    }
                }
                else
                {
                    managedCard = (CardLayout)managedCardPanel.getLayout();
                }

                String action       = ae.getActionCommand();

                if(action.equalsIgnoreCase("butCancel")    ||//No Internationalisation
                   action.equalsIgnoreCase("butManCancel"))//No Internationalisation
                {
                    count = 0;//by ramanr
                    dialog.setVisible(false);       
                    dialog.dispose();
                }
                else if(action.equalsIgnoreCase("butModify"))//No Internationalisation
                {
                    

                    count = 0;//by ramanr.......
                    if(noOfSelectedSymbols > 0)
                    {
                        
                        updatePropValues(symbolPropUpdateVec,componentPropVec);                    
                        setFormProperties(symbolPropUpdateVec);
                        symbolProp = convertVecToProp(symbolPropUpdateVec);
                        String  name = symbolProp.getProperty("name");
                        map = model.getSymbolByName(name);
                        if(map == null)//This is just a workaround. in future the getLinkByName method can be replaced by getSymbolByName method itself.
                        {
                            map = model.getLinkByName(name);
                        }
						if(map == null)
						{
							map = model.getGroupByName(name);
						}
                                //map.setProperties (symbolProp);
                        String key = map.getKey();
                                //changes start
                        boolean saveStatus = saveCheck.isSelected(); 
                        symbolProp.put("mapName", model.getName());
                        if(map instanceof MapContainerComponent)
                        {
                            String error[] = new String[10];
                            int num= 0;
                            num = checkLocationAndDimension(symbolProp,error,num);
                            num = checkSymbolProperties(symbolProp,error,num);
                            
                            if(num>0)
                            {
                                showErrorMessage(num, error);
                                return;
                            }   
                            model.updateContainer(key,symbolProp,saveStatus);
                        }
                        else if(map instanceof MapLinkComponent)
                        {
                            //validation for symbolproperties
                            String error[] = new String[10];
                            int num= 0;
                            num = checkSymbolProperties(symbolProp,error,num);
                            String source = symbolProp.getProperty("source");//No Internationalisation
                            String dest = symbolProp.getProperty("dest");//No Internationalisation
                            String thickness = symbolProp.getProperty("thickness");//No Internationalisation
                            if(thickness != null)
                            {
                                try
                                {
                                    int thicknessValue = Integer.parseInt(thickness);
                                    if(thicknessValue <= 0)
                                    {
                                        error[num]="thickness";//No Internationalisation
                                        num++;
                                    }
                                }
                                catch(NumberFormatException ex)
                                {
                                    error[num]="thickness";//No Internationalisation
                                    num++;
                                }

                            }
                            else
                            {
                                symbolProp.put("thickness","3");//No Internationalisation
                            }
                            String currentTopology =model.getCurrentTopology();
                            if((currentTopology != null ) && (currentTopology.trim().equals("ethernet")))//No Internationalisation
                            {
                                if(source == null || (model.getSymbolByName(source) == null))
                                {
                                    error[num] ="source";//No Internationalisation
                                    num++;
                                }
                            }
                            else
                            {
                                if(source == null || (model.getSymbolByName(source) == null))
                                {
                                    error[num] ="source";//No Internationalisation
                                    num++;
                                }
                                if( dest ==null ||(model.getSymbolByName(dest) == null)) 
                                {
                                    error[num] ="destination";//No Internationalisation
                                    num++;
                                }
                            }
                            if(num>0)
                            {
                                showErrorMessage(num, error);
                                return;
                            }       

                            model.updateLink(key,symbolProp,saveStatus);
                            
                        }
                        else if(map instanceof MapGroupComponent)
                        {
                            String error[] = new String[10];
                            int num= 0;
                            num = checkLocationAndDimension(symbolProp,error,num);
                            num = checkSymbolProperties(symbolProp,error,num);
                            if(num>0)
                            {
                                showErrorMessage(num, error);
                                return;
                            }
                            model.updateGroup(key,symbolProp,saveStatus );
                        } 
                        else
                        {//validation for symbolproperties
                            String error[] = new String[10];
                            int num= 0;
                            num = checkLocationAndDimension(symbolProp,error,num);
                            num = checkSymbolProperties(symbolProp,error,num);

                            if(num>0)
                            {
                                showErrorMessage(num, error);
                                return;
                            }

                            model.updateSymbol(key,symbolProp,saveStatus);
                        }
                     
                    }
                    else
                    {
                        updatePropValues(mapPropUpdateVec,mapPropComponents);
                        mapProp = convertVecToProp(mapPropUpdateVec);
                                
                        Properties criteriaProps = null;
                        if(mapCriteriaPropsUpdateVec != null )
                        {
                            
                            updatePropValues(mapCriteriaPropsUpdateVec,mapCriteriaPropComponents);
                            criteriaProps =convertVecToProp(mapCriteriaPropsUpdateVec);
                        }
                                
                        Hashtable groupForm = new Hashtable();
                        groupForm.put("MAP_PROPERTIES",mapProp);  //No Internationalisation
                        if( criteriaProps !=null )
                        {
                            groupForm.put("MAP_CRITERIA_PROPERTIES",criteriaProps);  //No Internationalisation
                        }
                        setGroupFormProperties(groupForm);
                        boolean saveStatus = saveCheck.isSelected();
                        String label =  (String)mapProp.get("label");//No Internationalisation
                        String mapName = (String)mapProp.get("name");//No Internationalisation
                        boolean emptyLabel = false;
                        if( label == null || label.equals("") || label.equals("null") )//No Internationalisation
                        {
                            emptyLabel = true;
                            if( mapName != null )
                            {
                                label = mapName.substring(0,mapName.indexOf(".netmap"));//No Internationalisation
                            }
                        }

                        if( applet.checkTreeName(mapName,label))
                        {
                            if( emptyLabel)
                            {
								// DP - I18N
								NmsClientUtil.showError( NmsClientUtil.getFrame(applet),java.text.MessageFormat.format(NmsClientUtil.GetString(" Invalid map label : map with label {0} already exists at the same level in the tree.")+" \n"+NmsClientUtil.GetString("Map label property is taken from the map name if no label is specified while \nadding map. Maps with same label can only exists at the different levels in the tree."),new String[]{label}));
								                            
															
                            }
                            else
                            {
							   // DP - I18N
                               NmsClientUtil.showError( NmsClientUtil.getFrame(applet),java.text.MessageFormat.format( NmsClientUtil.GetString(" Invalid map label : map with label {0} already exists at the same level in the tree.")+" \n"+ NmsClientUtil.GetString("Maps with same label can exists at the different levels in the tree."), new String[]{label}));
																
                            }
                            return ;

                        }
                        if (saveStatus)
                        {
                            if( criteriaProps != null && criteriaProps.size() >0)
                            {
                                mapModelAPI.updateMap((String)mapProp.getProperty("name"), mapProp,criteriaProps);
                            }
                            else
                            {
                                mapModelAPI.updateMap((String)mapProp.getProperty("name"), mapProp,null);
                            }
                        }
                        else
                        {
                            applet.setProperties(mapProp);
                            // applet.updateModel(); // for user properties problem this line is commented
                        }
                    }
                    dialog.setVisible(false);       
                    dialog.dispose();
                    //applet.emap.basicMap.repaint();       
                    //   applet.emap.basicMap.alterBuffer();
                    model.updateListeners();
                }
                else if(action.equalsIgnoreCase("butManModify")) //No Internationalisation
                {
                    count = 0;//by ramanr....
                    updatePropValues(managedObjPropUpdateVec,componentManagedVec);        // update FormObjects
                    setFormProperties(managedObjPropUpdateVec);

                    if(!networkdbForm)
                    {
                        managedProp = convertVecToProp(managedObjPropUpdateVec);
                        managedProp.put("mapName", model.getName());
                        mapClientAPI.setManagedObjectProperties (managedProp);                   
                    }
                    else
                    {
                        firePropertyChange();
                    }
                    dialog.setVisible(false);       
                    dialog.dispose();
                }

                else if(action.equalsIgnoreCase("butManNext"))//No Internationalisation
                {
                    managedCard.next(managedCardPanel);
                }

                else if(action.equalsIgnoreCase("butManBack"))//No Internationalisation
                {
                    managedCard.previous(managedCardPanel);
                }

                else if(action.equalsIgnoreCase("butMapNext"))//No Internationalisation
                {
                    mapCard.next(mapCardPanel);
                }

                else if(action.equalsIgnoreCase("butMapBack"))//No Internationalisation
                {
                    mapCard.previous(mapCardPanel);
                }

                else if(action.equalsIgnoreCase("butNext"))//No Internationalisation
                {
                    symbolCard.next(symbolCardPanel);
                }

                else if(action.equalsIgnoreCase("butBack"))//No Internationalisation
                {
                    symbolCard.previous(symbolCardPanel);
                }
                else if(action.equalsIgnoreCase("help"))//No Internationalisation
                {
                    java.applet.Applet applet = null;
                    NmsClientUtil.showHelp(helpURL);
                    //NmsClientUtil.showURLInNW(applet,helpURL);

                }
        }       
    public void keyPressed(KeyEvent ke)
    {
        count = 0;//by ramanr.......
        JDialog dialog = (JDialog)ke.getSource(); 
        if(ke.getKeyCode () == KeyEvent.VK_ESCAPE)
        {    
            dialog.dispose();
        }
    }
    public void keyReleased(KeyEvent ke){}
    public void keyTyped(KeyEvent ke){}
    //window listener added by ramanr........
    public void windowActivated(WindowEvent we){}
    public void windowClosed(WindowEvent we)
    {
        count = 0;
    }
    public void windowClosing(WindowEvent we){}
    public void windowDeactivated(WindowEvent we){}
    public void windowDeiconified(WindowEvent we){}
    public void windowIconified(WindowEvent we){}
    public void windowOpened(WindowEvent we){}
    //changes for check the position and dimension of mapsymbol
    private int checkLocationAndDimension(Properties p, String[] error,int num)
    {
        String Location[] = {"x","y","height","width"};//No Internationalisation
        for(int i=0;i<4;i++)
        {
            String val = p.getProperty(Location[i]);
            if( val != null)
            {
                try
                {
                    int value = Integer.parseInt(val);
                    if(Location[i].equals("x"))//No Internationalisation
                    {
                        if((value <0 )||(value > model.getWidth()))
                        {
                            error[num] = Location[i];
                            num++;
                        }
                    }
                    else if(Location[i].equals("y"))//No Internationalisation
                    {
                        if((value <0 )||(value > model.getHeight()))
                        {
                            error[num] = Location[i];
                            num++;
                        }
                    }
                    else if(Location[i].equals("height"))//No Internationalisation
                    {
                        if((value <0 )||(value > model.getHeight()))
                        {
                            error[num] = Location[i];
                            num++;
                        }
                    }
                    else
                    {
                        if((value <0 )||(value > model.getWidth()))
                        {
                            error[num] = Location[i];
                            num++;
                        }
                    }
                }
                catch(NumberFormatException ex)
                {
                    error[num] = Location[i];
                    num++;
                }
            }
        }
        return num;
    }
    private int checkSymbolProperties(Properties props, String[] error, int num)
    {
        String groupName = props.getProperty("groupName"); //No Internationalisation
        String parentName = props.getProperty("parentName");//No Internationalisation
        if(groupName != null &&  !groupName.trim().equals("") &&! groupName.trim().equals("null")) //No Internationalisation
        {
            if( model.getGroupByName(groupName) == null)
            {
                error[num] = "groupName";//No Internationalisation
                num++;
            }
        }
        if( parentName != null &&  !parentName.trim().equals("") && ! parentName.trim().equals("null")) //No Internationalisation
        {

            if( !((model.getSymbolByName(parentName)) instanceof MapContainerComponent))
            {
                error[num] = "parentName";//No Internationalisation
                num++;

            }       
        }
        String objType = props.getProperty("objType");//No Internationalisation
        if( objType == null )   
            props.put("objType","1");//No Internationalisation
        else
        {
            Properties objTypes = MapSymbolComponent.objTypes;

            if( !(objTypes.contains(objType))&&!(objTypes.containsKey(objType) ) )  
            {
                error[num] = "objType";//No Internationalisation
                num++;
            }

        }
        return num; 
    }
    //this method used to throw the error message
    private void showErrorMessage(int num, String[] error )
    {
        StringBuffer errorMessage = new StringBuffer();
        errorMessage.append(NmsClientUtil.GetString(" Invalid inputs for following attribute(s)")+"\n ");
        for(int k=0; k<num; k++)
        {
            if(k== num-2)
            {
                errorMessage.append(error[k]);
            }
            else if(k==num-1 && num != 1)
                errorMessage.append(NmsClientUtil.GetString(" and ")+error[k]+".");//No Internationalisation
            else if(num ==1)
                errorMessage.append(error[k]+".");//No Internationalisation
            else    
                errorMessage.append(error[k]+", ");//No Internationalisation
        }
        JOptionPane.showMessageDialog(null,errorMessage.toString(),NmsClientUtil.GetString("ErrorMessage"),JOptionPane.ERROR_MESSAGE); 
        return;
    }
    //String helpURL = "../help/user_guide/java_ui/maps/mapmenus.html";//No Internationalisation
    String helpURL = "../" + NmsClientUtil.getHelpURL("Map_Symbol_Details");//No Internationalisation

    /**
         * This method is used for convert the received vector 
         * object in to the properties object
         * @param "vec" a Vector , to be converted to properties //No Internationalisation
         * @return "Properties" coverted object//No Internationalisation
         */
    private Properties convertVecToProp (Vector vec)
    {
        Properties prop = new Properties ();
        for (int count = 0; count < vec.size (); count++)
        {
            FormObject fobj = (FormObject) vec.elementAt (count);
            if(fobj.getPropertyName().equals("name") || fobj.isEditable() == true)//No Internationalisation
            {
                String propName = fobj.getPropertyName ();
                String propValue = (String) fobj.getCurrentValue ();
                prop.put (propName, propValue);
            }
        }
        return prop;
    }

    /**
         * This method is used for update the modified propertiy values
         * @param "propertiesUpdateVector" a Vector , for holding the latest values//No Internationalisation
         * @param "propComponentVector" a Vector , for hold the properties components//No Internationalisation
         */
    private void updatePropValues(Vector propertiesUpdateVector , Vector propComponentVector)
    {
        FormObject form;
        Object obj;
        Vector valueList = null;

        JTextField tempTextField;
        JCheckBox  tempCheckBox;
        JComboBox  tempComboBox;

        int count = 0;
        int size;
        
        size = propComponentVector.size();
        
        for(Enumeration enumerate  = propertiesUpdateVector.elements();enumerate.hasMoreElements();)
        {
            form =(FormObject) enumerate.nextElement();
            if(form.isEditable() == true)
            {
                if(count < size)
                {
                    obj = propComponentVector.elementAt(count);
                    if(obj instanceof JTextField)
                    {
                        tempTextField = (JTextField) obj;
                        Object temp   = tempTextField.getText();
                        form.setCurrentValue(temp);
                        propertiesUpdateVector.setElementAt(form,count);        
                    }
                    else if(obj instanceof JCheckBox)
                    {
                        Object temp;
                        tempCheckBox = (JCheckBox) obj;
                        if(tempCheckBox.isSelected() == true)
                        {
                            temp = "true";//No Internationalisation
                        }
                        else
                        {
                            temp = "false";//No Internationalisation
                        }

                        form.setCurrentValue(temp);
                        propertiesUpdateVector.setElementAt(form,count);        

                    }
                    else if(obj instanceof JComboBox)
                    {
                        tempComboBox = (JComboBox)obj;
                        Object temp  = tempComboBox.getSelectedItem();
                        if(!(form.getPropertyName().toString().trim().equalsIgnoreCase("nodelist") ||
                             form.getPropertyName().toString().trim().equalsIgnoreCase("interfacelist"))) //Fix for issue id:116123 
                        {
                            form.setCurrentValue(temp);
                        }
                        propertiesUpdateVector.setElementAt(form,count);        
                    }
                    else if(obj instanceof BrowseComponent)
                    {
                        String resultStr =((BrowseComponent)obj).getValue();
                        String propName = form.getPropertyName().toString().trim();
                        if( "treeIconFileName".equalsIgnoreCase(propName) )//No Internationalisation
                        {
                            if ( (resultStr != null) && (resultStr.indexOf("images/") == -1) )//No Internationalisation
                            {
                                resultStr = "images/"+resultStr;//No Internationalisation
                            }
                        }
                        form.setCurrentValue(resultStr);
                        propertiesUpdateVector.setElementAt(form,count);        
                    }

                }
                else
                {
                    break;
                }
            }
            count++;
        }

        //      setFormProperties(propertiesUpdateVector);      
    }

    private Vector parseAndGetTheTokens (String str, String delim)
    {
        try
        {
            if ((str == null) || (str.equals ("")))//No Internationalisation
                return null;
            Vector retarr = new Vector (10,1);
            String main   = new String (str);
            int delimlength = delim.length ();
            if (delimlength == 0)
            {
                retarr.addElement (main);
                return retarr;
            }
            while (true)
            {
                if (main.equals (""))//No Internationalisation
                    break;
                int start = main.indexOf (delim);
                if (start < 0)
                {
                    retarr.addElement (main);
                    break;
                }
                String s = main.substring (0, start);
                if ((s != null) && (!s.equals ("")))//No Internationalisation
                {
                    retarr.addElement (s);
                }
                int end = start + delimlength;
                if (end < main.length ())
                {
                    main = main.substring (end);
                }
                else
                {
                    break;
                }
            }
            return retarr;
        }
        catch (Exception ee)
        {
            System.err.println (NmsClientUtil.GetString(" Error trying to tokenize list in ListFullProp:") + str + " " + ee);//No Internationalisation
        }
        return null;
    }


    /**
         *  This method is used for construct the UI component on the given panel
         *
         *  @param  "formObject" holds the value & name for the property//No Internationalisation
         *  @param  "disp"   a JPanel , holder for the component//No Internationalisation
         *  @param  "size"   a int value , where the component to be placed in the panel //No Internationalisation
         */

    private void displayFormObject(FormObject formObject,JPanel disp,int size, Vector componentVec)
    {

        Component component ;

        String propName = formObject.getPropertyName().toString().trim();
        String propValue = formObject.getCurrentValue().toString().trim();
        Vector values = formObject.getValuesList();
        if(!group)
        {
            if(propName.equalsIgnoreCase("GroupMembers"))//No Internationalisation
            {
                managedObjPropUpdateVec.removeElement(formObject);
                return;
            }
        }

        if(!childrenkey)
        {
            if(propName.equalsIgnoreCase("ChildrenKeys"))//No Internationalisation
            {
                managedObjPropUpdateVec.removeElement(formObject);
                return;
            }
        }


        getJlabel(propName,disp,size);

        if(values != null)
        {
            int valueSize = values.size();
            if(valueSize == 2)
            {

                if((propName.equalsIgnoreCase("anchored")  ||//No Internationalisation
                    propName.equalsIgnoreCase("managed") ||//No Internationalisation
                    propName.equalsIgnoreCase("managed")   ||//No Internationalisation
                    propName.equalsIgnoreCase("discover")   ||//No Internationalisation
                    propName.equalsIgnoreCase("containment")   ||//No Internationalisation
                    propName.equalsIgnoreCase("autoPlacement")))//No Internationalisation
                {
                    component = getCheckBox(formObject,disp,componentVec);
                }
                else
                {
                    component = getComboBox(formObject,disp,componentVec);
                }
            }
            else 
            {
                component = getComboBox(formObject,disp,componentVec);
            }
        }
        else  if(propName.equalsIgnoreCase("iconname"))//No Internationalisation
        {
            component = getBrowseComponent(formObject,disp,componentVec);
        }                    
        else  if(propName.equalsIgnoreCase("treeiconfilename"))//No Internationalisation
        {
            component = getBrowseComponent(formObject,disp,componentVec);
        }                    
        else if(propName.equalsIgnoreCase("imagename"))//No Internationalisation
        {
            component = getBrowseComponent(formObject,disp,componentVec);
        }
	//*********************************************************************************
	// Uncomment the following code snippet if the community and writeCommunity values to be displayed in "*" format
	//********************************************************************************

	/* //For Openwave operation - rameshj
        else if(propName.equalsIgnoreCase("community") || propName.equalsIgnoreCase("writeCommunity"))//No Internationalization
        {
            component = getJPasswordField(propValue,formObject.isEditable(),disp,componentVec);
        }
        //end rameshj */
        else
        {
            component = getJtextField(propValue,formObject.isEditable(),disp,componentVec);
        }
        putComponent(component,disp,size);
    }
    private Component getBrowseComponent(FormObject formObject ,JPanel disp,Vector componentVec)
    {
        String val = formObject.getCurrentValue().toString().trim();
        com.adventnet.nms.util.BrowseComponent bc = new com.adventnet.nms.util.BrowseComponent(MapApplet.applet,val);
        bc.setPreferredSize(new Dimension(200,40));
        componentVec.addElement(bc);
        return bc;

    } 


    /**
         * method used for construct the combobox component and put that component in the
         * given panel
         * @param "formObject" holds the details for the component//No Internationalisation
         * @param "disp" a JPanel , to hold the combobox//No Internationalisation
         */
    private Component getComboBox(FormObject formObject,JPanel disp,Vector componentVec)
    {

        JComboBox combo = new JComboBox(formObject.getValuesList());

        combo.setSelectedItem(formObject.getCurrentValue().toString().trim());
        combo.setEnabled(formObject.isEditable());
        componentVec.addElement(combo);
        return combo;
    }

    /**
         * method used for construct the checkbox component and put that component in the
         * given panel
         * @param "formObject" holds the details for the component//No Internationalisation
         * @param "disp" a JPanel , to hold the combobox//No Internationalisation
         */
    private Component getCheckBox(FormObject formObject,JPanel disp,Vector componentVec)
    {

        JCheckBox isTrue     = new JCheckBox("",true);//No Internationalisation
        String selectedValue = formObject.getCurrentValue().toString();

        if(selectedValue.equalsIgnoreCase("false"))//No Internationalisation
        {
            isTrue.setSelected(false);
        }
        isTrue.setEnabled(formObject.isEditable());
        isTrue.setFont(font);
        componentVec.addElement(isTrue);
        return(isTrue);
    }
    /**
         * method to get the JLabel as for the value passed and put that label in the 
         * given panel.
         * @param "text" a String , text for JLabel.//No Internationalisation
         * @param "disp" a JPanel , panel to hold the label//No Internationalisation
         * @param "size" a int , position to place the label in the panel//No Internationalisation
         */
    private void getJlabel(String text,JPanel disp,int size)
    {
        text             = text.substring(0,1).toUpperCase()+text.substring(1);// DP -I18N

        text  = NmsClientUtil.GetString(text);
        JLabel label = new JLabel(text);
        label.setForeground(Color.black);
        label.setPreferredSize(new Dimension(125,15));
        label.setFont(font);

        constraints.fill       = GridBagConstraints.HORIZONTAL;
        constraints.weightx        = 1;
        constraints.insets         = new Insets(5,15,3,5);//new Insets(5,15,3,5);
        constraints.gridx      = 0;
        constraints.gridy      = (size+2);
        gbLayout.setConstraints(label,constraints);
        disp.add(label);                
    }
    /**
         * This method put the component in the given panel at a given position
         * @param "comp" a Component type //No Internationalisation
         * @param "disp" a JPanel , to hold the component//No Internationalisation
         * @param "size" a int , the position where to place the component //No Internationalisation
         */
    private void putComponent(Component comp,JPanel disp,int size)
    {
        constraints.fill       = GridBagConstraints.HORIZONTAL;
        constraints.insets         = new Insets(5,15,3,5);
        constraints.weightx        = 1;
        constraints.gridx      = 2;
        constraints.gridy      = (size+2);
        gbLayout.setConstraints(comp,constraints);
        comp.setFont(font);
        disp.add(comp);
    }

    /**
         * This method is used for get the textfield 
         * @param "text" String , for text field value//No Internationalisation
         * @param "editable" boolean , to say textfield is editable or not//No Internationalisation
         * @param "disp" JPanel , holder for the textfield//No Internationalisation
         */
    private Component getJtextField(String text ,boolean editable,JPanel disp,Vector componentVec)
    {
        JTextField tfTemp = new JTextField(20);
        tfTemp.setDisabledTextColor(Color.black);
        tfTemp.setText(text);
        tfTemp.setFont(font);
        tfTemp.setEnabled(editable);
        componentVec.addElement(tfTemp);
        return tfTemp;
    }

    //For Openwave Operation -rameshj
    private Component getJPasswordField(String text ,boolean editable,JPanel disp,Vector componentVec)
    {
        JPasswordField tfTemp = new JPasswordField(20);
        tfTemp.setDisabledTextColor(Color.black);
        tfTemp.setText(text);
        tfTemp.setFont(font);
        tfTemp.setEnabled(editable);
        componentVec.addElement(tfTemp);
        return tfTemp;
    }
    
    public Vector getUserProperties(Vector propVec , String [] array)
    {
        Vector retVec = new Vector();
        for(int vecSize = 0; vecSize < propVec.size(); vecSize = vecSize+2)
        {
            boolean set = true;
            String name = (String)propVec.elementAt(vecSize);

            for(int arrSize = 0; arrSize < array.length; arrSize++)
            {
                String arrele = array[arrSize];

                if(arrele.equalsIgnoreCase(name))
                {
                    set = false;
                    break;
                }
            }
            if(set)
            {
                String value = (String)propVec.elementAt(vecSize+1);

                retVec.addElement(name);
                retVec.addElement(value);
            }
        }
        return retVec;
    }

    public void addUserPropertiesPanel(Vector userPropVec , String token)
    {
        JPanel parentPanel;
        JPanel propPanel;
        JPanel buttonPanel;
        JScrollPane propScrollPane;
        FormObject formObject = null;
        Vector valueList = null;
        JButton butNext;
        JButton butBack;

        TitledBorder propPanelBorder    = BorderFactory.createTitledBorder(NmsClientUtil.GetString("USER PROPERTIES"));
        propPanelBorder.setTitleJustification(TitledBorder.LEFT);

        int userPropVecSize = userPropVec.size();
        int repeatcount;
        int noOfProp = 20;
        int quotient = (userPropVecSize/20);
        int remainder = 0;
        boolean network = false;
        int indexfornetwork = userPropVec.indexOf("isNetwork");//No Internationalisation
        if(userPropVec.elementAt(indexfornetwork+1).equals("true"))//No Internationalisation
            network = true;

        if(quotient > 0)
        {
            remainder = (userPropVecSize%20);
            if(remainder < 10)
            {
                repeatcount = quotient;
            }
            else
            {
                repeatcount = quotient + 1;
            }
        }
        else
        {
            repeatcount = 1;
        }

        for(int repeat = 0;repeat < repeatcount; repeat++)
        {
            parentPanel = new JPanel(new BorderLayout());
            propPanel = new JPanel(gbLayout);

            if((remainder > 0)&&(remainder < 10))
            {
                if((repeat+1) == repeatcount)
                {
                    noOfProp = noOfProp + remainder;
                }
            }

            for(int size = 0;size<noOfProp;size = size+2)
            {
                int position;
                String propName = null;
                String propValue = null;

                position = (repeat*20)+size;

                propName = (String)userPropVec.elementAt(position);
                propValue = (String)userPropVec.elementAt(position+1);

                if(linkset == true && propName.equals("groupName"))//No Internationalisation
                {
                    formObject = new FormObject(propName,valueList,false ,propValue);
                }
                else if(linkset == true && propName.equals("parentName"))//No Internationalisation
                {
                    formObject = new FormObject(propName,valueList,false ,propValue);
                }

                else if(linkset == true && propName.equals("height"))//No Internationalisation
                {
                    continue;
                }
                else if(linkset == true && propName.equals("width"))//No Internationalisation
                {
                    continue;
                }
                else if(linkset == true && propName.equals("x"))//No Internationalisation
                {
                    formObject = new FormObject(propName,valueList,false ,propValue);
                }
                else if(linkset == true && propName.equals("y"))//No Internationalisation
                {
                    formObject = new FormObject(propName,valueList,false ,propValue);
                }

                else 
                {
                    formObject = new FormObject(propName,valueList,true ,propValue);
                    if(token.equalsIgnoreCase("symbol"))//No Internationalisation
                    {
                        symbolPropUpdateVec.addElement(formObject);
                    }
                    if(token.equalsIgnoreCase("managed"))//No Internationalisation
                    {
                        managedObjPropUpdateVec.addElement(formObject);
                        if(network == true)
                        {
                            if(!propName.equals("isSNMP") && !propName.equals("isNode"))//No Internationalisation
                                displayFormObject(formObject,propPanel,size,componentManagedVec);
                            else
                                managedObjPropUpdateVec.removeElement(formObject);//added
                                                        
                        }
                        else
                            displayFormObject(formObject,propPanel,size,componentManagedVec);
                    }
                                        
                }
                if(token.equals("map"))//No Internationalisation
                {
                    displayFormObject(formObject,propPanel,size,mapPropComponents);
                }
                else if(token.equals("symbol"))//No Internationalisation
                {
                    displayFormObject(formObject,propPanel,size,componentPropVec);
                }
                                
                if((position+2) == userPropVecSize)
                {
                    break;
                }
            }
            propPanel.setBorder(propPanelBorder);
            parentPanel.add("Center",propPanel);//No Internationalisation
                        
            buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,90,5));               
            if((repeat+1) == repeatcount)
            {
                butBack     = new JButton(NmsClientUtil.GetString("<<Back"));
                JButton dummyButton = new JButton(NmsClientUtil.GetString("Next>>"));
                butBack.setFont(font);
                dummyButton.setFont(font);
                                
                dummyButton.setEnabled(false);
                if(token.equalsIgnoreCase("managed"))//No Internationalisation
                {
                    butBack.setActionCommand("butManBack");//No Internationalisation
                }
                else if(token.equalsIgnoreCase("symbol"))//No Internationalisation
                {
                    butBack.setActionCommand("butBack");//No Internationalisation
                }
                else if(token.equalsIgnoreCase("map"))//No Internationalisation
                {
                    butBack.setActionCommand("butMapBack");//No Internationalisation
                }
                butBack.addActionListener(this);
                buttonPanel.add(butBack);
                buttonPanel.add(dummyButton);

                parentPanel.add("South",buttonPanel);//No Internationalisation
            }
            else
            {
                butNext     = new JButton(NmsClientUtil.GetString("Next>>"));
                butBack     = new JButton(NmsClientUtil.GetString("<<Back"));
                butNext.setFont(font);
                butBack.setFont(font);

                if(token.equalsIgnoreCase("managed"))//No Internationalisation
                {
                    butNext.setActionCommand("butManNext");//No Internationalisation
                    butBack.setActionCommand("butManBack");//No Internationalisation
                }
                else if(token.equalsIgnoreCase("symbol"))//No Internationalisation
                {
                    butNext.setActionCommand("butNext");//No Internationalisation
                    butBack.setActionCommand("butBack");//No Internationalisation
                }
                else if(token.equalsIgnoreCase("map"))//No Internationalisation
                {
                    butNext.setActionCommand("butMapNext");//No Internationalisation
                    butBack.setActionCommand("butMapBack");//No Internationalisation
                }
                butNext.addActionListener(this);
                butBack.addActionListener(this);

                buttonPanel.add(butBack);
                buttonPanel.add(butNext);

                parentPanel.add("South",buttonPanel);//No Internationalisation
            }
            propScrollPane = new JScrollPane(parentPanel);

            if(token.equalsIgnoreCase("managed"))//No Internationalisation
            {
                managedCardPanel.add("userManagedpanel"+repeat,propScrollPane); //No Internationalisation
            }
            else if(token.equalsIgnoreCase("symbol"))//No Internationalisation
            {
                symbolCardPanel.add("userSymbolPanel"+repeat,propScrollPane);//No Internationalisation
            }
            else if(token.equalsIgnoreCase("map"))//No Internationalisation
            {
                mapCardPanel.add("userMapPanel"+repeat,propScrollPane);//No Internationalisation
            }
        }

    }
    /**This inner class is for popping up the dialog where user can specify or modify Map 
     *Criteria Properties
     */
    private class UserPropDialog extends JDialog implements ActionListener
	{
        MapApplet app=null;
        java.awt.GridLayout layout = new java.awt.GridLayout ();
		JPanel p = new JPanel (layout);
		Vector keys = new Vector ();
		Vector values = new Vector ();
		int countOfRows = 0;
        Properties temp=null;

		private UserPropDialog (Properties existProp, JDialog mapPropertiesDialog)
		{
            super(mapPropertiesDialog);
            setTitle (NmsClientUtil.GetString("AdventNet")+" "+NmsClientUtil.GetString ("Custom Map Properties"));
            getContentPane ().setLayout (new BorderLayout ());
			layout.setColumns (2);
			layout.setHgap (20);
			layout.setVgap (20);
			
			countOfRows =0;
            
            /* This will show the existing criteria properties in the dialog */
            for (Enumeration enumerate  = existProp.propertyNames (); enumerate.hasMoreElements ();)
            {
                String propName = (String) enumerate.nextElement ();
                String  propValue = (String)existProp.get (propName);
                
                JTextField keyTxt = new JTextField (20);
                keyTxt.setText(propName);
               
                keyTxt.setFont(NmsClientUtil.getFont("DIALOG"));
                JTextField valueTxt = new JTextField (20);
                valueTxt.setText(propValue);
               
                valueTxt.setFont(NmsClientUtil.getFont("DIALOG"));
                
                layout.setRows (++countOfRows);
                keys.addElement (keyTxt);
                values.addElement (valueTxt);
                p.add (keyTxt);
                p.add (valueTxt);
                
                pack();
            }

			JPanel centerPanel = new JPanel ();
			JButton b1 = new JButton(NmsClientUtil.GetString("More"));
            b1.setActionCommand("More");
        	b1.setFont(NmsClientUtil.getFont("DIALOG"));
			JButton b2 = new JButton(NmsClientUtil.GetString("Fewer"));
            b2.setActionCommand("Fewer");
        	b2.setFont(NmsClientUtil.getFont("DIALOG"));
			centerPanel.setLayout (new BorderLayout ());
			centerPanel.add (p, "Center");
			JPanel p2 = new JPanel ();
			p2.add (b1);
			p2.add (b2);
			centerPanel.add (p2, "South");
			centerPanel.setBorder (new javax.swing.border.TitledBorder (NmsClientUtil.GetString ("Please fill in the Custom Map Criteria")));
			JButton b3 = new JButton (NmsClientUtil.GetString ("OK"));
			b3.setActionCommand("OK");	
        	b3.setFont(NmsClientUtil.getFont("DIALOG"));
			JButton b4 = new JButton (NmsClientUtil.GetString ("Cancel"));
			b4.setActionCommand("Cancel");
        	b4.setFont(NmsClientUtil.getFont("DIALOG"));
			
			b1.addActionListener (this);
			b2.addActionListener (this);
			b3.addActionListener (this);
			b4.addActionListener (this);

			getContentPane ().add (centerPanel, "Center");
			JPanel bottomPanel = new JPanel ();
			bottomPanel.add (b3);
			bottomPanel.add (b4);
			getContentPane ().add (bottomPanel, "South");
			pack ();
			setLocationRelativeTo (mapPropertiesDialog);//NmsClientUtil.getFrame (app));
      		setFont(NmsClientUtil.getFont("DIALOG"));
			show ();
		}

		public void actionPerformed (ActionEvent e)
		{
			if (e.getActionCommand ().equals ("More"))
			{
				JTextField keyTxt = new JTextField (20);
      			keyTxt.setFont(NmsClientUtil.getFont("DIALOG"));
				JTextField valueTxt = new JTextField (20);
      			valueTxt.setFont(NmsClientUtil.getFont("DIALOG"));
				keys.addElement (keyTxt);
				values.addElement (valueTxt);
				layout.setRows (++countOfRows);
				p.add (keyTxt);
				p.add (valueTxt);
				pack ();
				setLocationRelativeTo (mapPropertiesDialog); //;NmsClientUtil.getFrame (app));
			}
			else if (e.getActionCommand ().equals ("Fewer"))
			{
				if (keys.size () == 0)
					return;
				JTextField keyTxt = (JTextField) keys.elementAt (keys.size () - 1);
				JTextField valueTxt = (JTextField) values.elementAt (values.size () - 1);
				keys.removeElementAt (keys.size () - 1);
				values.removeElementAt (values.size () - 1);
				layout.setRows (--countOfRows);
				p.remove (keyTxt);
				p.remove (valueTxt);
				pack ();
				setLocationRelativeTo (mapPropertiesDialog); //NmsClientUtil.getFrame (app));
			}
			else if (e.getActionCommand ().equals ("OK"))
			{
				temp = new Properties ();
				for (int i = 0; i < keys.size (); i++)
				{
					JTextField keyTxt = (JTextField) keys.elementAt (i);
					JTextField valueTxt = (JTextField) values.elementAt (i);
					if (!keyTxt.getText ().trim ().equals ("") && !valueTxt.getText ().trim ().equals (""))
						temp.put (keyTxt.getText ().trim (), valueTxt.getText ().trim ());
				}
              
                if(temp!=null && temp.size()>0) //If the specified prop is null, then it will not change the existing properties
                {
                    mapCriteriaPropsUpdateVec.removeAllElements();
                    mapCriteriaPropComponents.removeAllElements();
                    holdCritPanel.remove(criteriaPanel);
                    
                    criteriaPanel= getCriteriaPanel(convPropToFormObject(temp));
                    holdCritPanel.add(criteriaPanel,BorderLayout.CENTER);
                    
                }
                dispose ();
            }
			else if (e.getActionCommand ().equals ("Cancel"))
			{
				dispose ();
			}
		}
        
	}

    /**
     * This method is used for convert the received properties 
     * object in to the vector of form object
     * @param "p" a properties , to be converted to vector of Form object //No Internationalisation
     * @return "Vector of Form object" coverted object//No Internationalisation
     */
    
    private Vector convPropToFormObject(Properties p)
    {
        Vector returnVec = new Vector (10, 2);
        for (Enumeration enumerate  = p.propertyNames (); enumerate.hasMoreElements ();)
        {
            String propName = (String) enumerate.nextElement ();
            Object propValue = p.get (propName);
            FormObject fobj = new FormObject (propName, null, true, propValue);
            returnVec.addElement (fobj);
        }
        return returnVec;
    }

    
}       




