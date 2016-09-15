/* $Id: TestAPI.java,v 1.2 2003/06/09 06:37:59 rajalakshmytr Exp $
 *
 * File Name      : TestAPI.java
 * Description    : RMI Testing Tool based on UI. Basically this lists all the methods in TrapAPI,EventAPI,AlertAPI,
 * EventFilterAPI,EventParserAPI,AlertFilterAPI and TrapAPI. 
 * Other Info     :
 *
 * USAGE          : java TestAPI RMIPort SecureFlag
 * Parameter Desc : RMIPort <--> RMI Port of WebNMS Server <--> int
 *                  SecureFlag <--> Secured Server Mode or not <--> (true/false)
 *
 * Owner Name     : 
 * Change History(Author Date(dd-mm-yyy) and Description of methods added/modifed/deleted):
 */

import java.io.*;
import java.util.*;
import java.lang.reflect.Method;
import java.lang.Class;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.awt.*;
import java.awt.event.*;
import java.rmi.Naming;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;

import com.adventnet.nms.util.PureUtils;
import com.adventnet.nms.server.FilterObject;
import com.adventnet.nms.util.CommonAPI;
import com.adventnet.nms.server.CommonFilterAPI;
import com.adventnet.nms.alertdb.*;
import com.adventnet.nms.eventdb.*;
import com.adventnet.security.authentication.*;

public class TestAPI implements ListSelectionListener,ActionListener,FilenameFilter
{
    Object myAPI = null;
    JFrame frame =null;
    JPanel panel3 = null;
    JPanel panel1=null;
    JPanel panel2=null;
    JScrollPane scroll = null;
    JButton invoke = null;
    JButton exit = null;
    JButton search = null;
    JTextField[] array =null;
    JComboBox interfaceBox = null;
    JComboBox hostBox = null;
    JList list = null;
    Vector hosts = null;
    Vector interfaces = null;
    Thread myThread = null;    
    int port=0;
    boolean secureFlag=false;

    Insets inset;
    GridBagConstraints cons = new GridBagConstraints();

	
    public TestAPI(int port,boolean flag)
    {
        this.port=port;
        this.secureFlag=flag;
        initializeInterfacesVector();
        startFrame();
        setUpFirstPanel();
        frame.addWindowListener(new WindowAdapter()
            {
                public void windowClosing(WindowEvent ee)
                {
                    System.exit(0);
                }
            });
        frame.getContentPane().add(panel1);
        showSelectedMethods((String)interfaceBox.getSelectedItem());
        frame.getContentPane().add(scroll);
        setLowerPanel();
        frame.getContentPane().add(panel2);
        addButtons();
        frame.getContentPane().add(panel3);
        
        frame.show();
    }
	
    private void initializeInterfacesVector()
    {
        interfaces = new Vector();
        interfaces.add("com.adventnet.nms.alertdb.AlertAPI");
        interfaces.add("com.adventnet.nms.alertdb.AlertFilterAPI");
        interfaces.add("com.adventnet.nms.eventdb.EventAPI");
        interfaces.add("com.adventnet.nms.eventdb.EventFilterAPI");
        interfaces.add("com.adventnet.nms.eventdb.TrapAPI");
        interfaces.add("com.adventnet.nms.eventdb.EventParserAPI");
        
        hosts = new Vector();
        hosts.add("localhost");
    }
    
    public void setUpFirstPanel()
    {
        panel1.setLayout(new GridBagLayout());
        
        JLabel lab1 = new JLabel("Select API");
        inset = new Insets(2,5,2,5);
        setConstraints(0,0,1,1,0.0,0.0,cons.CENTER,cons.BOTH,inset,0,0);
        panel1.add(lab1,cons);

        interfaceBox = new JComboBox(interfaces);
        interfaceBox.setBackground(Color.white);
        interfaceBox.setEditable(true);
        interfaceBox.setSelectedIndex(0);
        interfaceBox.addActionListener(this);
        interfaceBox.setActionCommand("interface");
        inset = new Insets(2,5,2,5);
        setConstraints(1,0,1,1,0.0,0.0,cons.CENTER,cons.BOTH,inset,0,0);
        panel1.add(interfaceBox,cons);

        JLabel lab2 = new JLabel("Enter hostName");
        inset = new Insets(2,5,2,5);
        setConstraints(0,1,1,1,0.0,0.0,cons.CENTER,cons.BOTH,inset,0,0);
        panel1.add(lab2,cons);

        hostBox = new JComboBox(hosts);
        hostBox.setBackground(Color.white);
        hostBox.setEditable(true);
        hostBox.setSelectedIndex(0);
        hostBox.addActionListener(this);
        hostBox.setActionCommand("host");
        inset = new Insets(2,5,2,5);
        setConstraints(1,1,1,1,0.0,0.0,cons.CENTER,cons.BOTH,inset,0,0);
        panel1.add(hostBox,cons);
    }

    public void setConstraints(int x,int y,int width,int height,double wtX,double wtY,int anchor,int fill,Insets inset,int padX,int padY )
    {
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
    }
        
    public void startFrame()
    {
        frame = new JFrame();
        frame.setSize(800,500);
        frame.setName("API Tester");
        frame.getContentPane().setLayout(new GridLayout(4,1));

        panel1 = new JPanel();
        panel2 = new JPanel();
        panel3 = new JPanel();        
    }
    
    public void addButtons()
    {
        invoke = new JButton("Invoke");
        exit = new JButton("Exit");
        search = new JButton("Search");
        
        invoke.setPreferredSize(new Dimension(120,30));
        exit.setPreferredSize(new Dimension(120,30));
        search.setPreferredSize( new Dimension(120,30));
        
        invoke.setBackground(Color.white);
        invoke.setForeground(Color.black);
        exit.setBackground(Color.white);
        exit.setForeground(Color.black);
        search.setBackground(Color.white);
        search.setForeground(Color.black);
        
        invoke.setBorder(new BevelBorder(BevelBorder.RAISED));
        exit.setBorder(new BevelBorder(BevelBorder.RAISED)); 
        search.setBorder(new BevelBorder(BevelBorder.RAISED));
        
        invoke.addActionListener(this);
        exit.addActionListener(this);
        search.addActionListener(this);
		
        invoke.setMnemonic(KeyEvent.VK_I);
        search.setMnemonic(KeyEvent.VK_S);
        exit.setMnemonic(KeyEvent.VK_X);

        panel3.add(invoke);
        panel3.add(search);
        panel3.add(exit);
    }
    
    public void setValues(String args[])
    {
        for(int i=0;i<args.length;i++)
        {
            JPanel innerPanel = new JPanel();
            JTextField field = new JTextField(20);
            JLabel label = new JLabel(args[i]);
            label.setSize(20,20);
            innerPanel.add(label);
            innerPanel.add(field);
            panel1.add(innerPanel);
        }
        
    } 

    public Method[] getAllMethods()
    {
        Class methodClass = null;
        Method[] methods=null;
        try
        {
            String selected = (String) interfaceBox.getSelectedItem();
            methodClass = Class.forName(selected);
            methods = methodClass.getMethods();
        }
        catch(Exception ee)  
        {
            System.out.println("Exception in getting the method names");
            ee.printStackTrace();
        }
        return methods;
    }
    
    public String getSimpleName(String classname)
    {

        int index=classname.lastIndexOf('.')+1;
        if(index !=-1)
        {
            classname = classname.substring(index);
        }
        return classname;
    }
    
    public void setLowerPanel()
    {
        Method selectedMethod = (Method)list.getSelectedValue();
        Class[] argsClasses = selectedMethod.getParameterTypes();
        array = new JTextField[argsClasses.length];
        String[] argsNames =new String[argsClasses.length];
        panel2.removeAll();
        panel2.setLayout(new GridLayout(argsNames.length,2));
        for(int i=0;i<argsClasses.length;i++)
        {

            JPanel innerPanel = new JPanel();
            JTextField field = new JTextField(20);
            array[i] = field;
            String classname = getSimpleName(argsClasses[i].getName());
            JLabel label = new JLabel(classname);
            innerPanel.add(label);
            innerPanel.add(field);
            panel2.add(innerPanel);
        }
        panel2.updateUI();
    }
    
    
    public Object getArgument(String name,JTextField field) throws Exception
    {
        if(name.equals("String"))
        {
            return field.getText();
        }
        if(name.equals("boolean"))
        {
            return Boolean.valueOf(field.getText());
        }
        if(name.equals("int"))
        {
            return Integer.valueOf(field.getText());
        }
        if(name.equals("Alert"))
        {
            if(myAPI instanceof AlertAPI)
            {
                Alert alt = ((AlertAPI)myAPI).checkOutIfAvailable(field.getText().trim());
                ((AlertAPI)myAPI).unlock(alt);
                return alt;
            }
            else 
            {
                AlertAPI tempapi = null;
                if(!secureFlag)
                {
                    tempapi = (AlertAPI)Naming.lookup("//"+(String)hostBox.getSelectedItem()+port+":/AlertAPI");
                }
                else
                {
                    RMIAccessAPI api = (RMIAccessAPI)Naming.lookup("//"+(String)hostBox.getSelectedItem()+port+":/RMIAccessAPI");
                    tempapi = (AlertAPI)api.getAPI("root","public","AlertAPI");
                }
                Alert alt = tempapi.checkOutIfAvailable(field.getText().trim());
                tempapi.unlock(alt);
                return alt;
            }
        }
        if(name.equals("int;"))
        {
            return convertObjectToInt(tokenizeComma(field.getText()));
        }
        if(name.equals("String;"))
        {
            return convertObjectToString(tokenizeComma(field.getText()));
        }
        if(name.equals("Properties"))
        {
            Properties p = new Properties();
            Object[] array = tokenizeComma(field.getText());
            for(int i=0;i<array.length;i++)
            {
                String sub =(String) array[i];
                StringTokenizer equal = new StringTokenizer(sub,"=",false);
                {
                    p.put(equal.nextToken(),equal.nextToken());
                }
            }
            return p;
        }
            
        return null;
    }

    private int[] convertObjectToInt(Object[] array)
    {
        int[] intarray = new int[array.length];
        for(int i=0;i<intarray.length;i++)
        {
            intarray[i]=Integer.parseInt((String)array[i]);
        }
        return intarray;
    }

    private String[] convertObjectToString(Object[] array)
    {
        String[] stringarray = new String[array.length];
        for(int i=0;i<array.length;i++)
        {
            stringarray[i] = (String)array[i];
        }
        return stringarray;
    }

    private Object[] tokenizeComma(String victim)
    {
        StringTokenizer comma = new StringTokenizer(victim,"&",false);
        Vector temp = new Vector();
        while(comma.hasMoreElements())
        {
            temp.add(comma.nextToken());
        }
        return temp.toArray();
    }
    
    private String[] getAllMethodNames(Method[] array)
    {
        String[] names = new String[array.length];
        for(int i=0;i<names.length;i++)
        {
            names[i]=array[i].getName();
        }
        return names;
    }	
	
    public void setList()
    {
        list = null;
        list = new JList(getAllMethods());
        list.setBackground(Color.white);
        list.setForeground(Color.black);
        list.addListSelectionListener(this);
        list.setSelectedIndex(0);
        
        if( scroll == null)
        {
            scroll = new JScrollPane(list,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            scroll.setBorder(new CompoundBorder(new EmptyBorder(2,2,2,2),new BevelBorder(BevelBorder.RAISED)));
			
        }
        else
        {
            scroll.setViewportView(list);
        }
        scroll.updateUI();
        frame.invalidate();
        frame.validate();        
    }
    
    
    public void invokeSelectedMethod()throws Exception
    { 
        Method selectedMethod =(Method)list.getSelectedValue();
        Class[] args=selectedMethod.getParameterTypes();
        Object[] objs = new Object[args.length];
        for(int i=0;i<args.length;i++)
        {
            String classname = getSimpleName(args[i].getName());
            objs[i]=getArgument(classname,array[i]);
        }
        Object result = selectedMethod.invoke(myAPI,objs);
        if(result != null)
        {
            takeActionOnResult(result);
        }
    }
    
    public void actionPerformed(ActionEvent e) 
    {
        Object source = e.getSource();
        if(source instanceof JButton)
        {
            JButton bsource =(JButton)source;
            if(bsource.getText().equals("Exit"))
            {
                System.exit(0);
            }
            if(bsource.getText().equals("Invoke"))
            {
                try
                {
                    invokeSelectedMethod();
                }
                catch(Exception ee)
                {
                    System.out.println("Exception on invoking");
                    ee.printStackTrace();
                }       
            }
            if(bsource.getText().equals("Search"))
            {
                invokeSearchUI();
            }	
        }
        else if(source instanceof JComboBox)
        {
            if(source.equals(interfaceBox))
            {
                String interfaceString = (String)interfaceBox.getSelectedItem();
                if(!interfaces.contains(interfaceString))
                {
                    interfaceBox.addItem(interfaceString);
                }
            }
            if(source.equals(hostBox))
            {
                String host = (String)hostBox.getSelectedItem();
                if(!hosts.contains(host))
                {
                    hostBox.addItem(host);
                }
            }
            showSelectedMethods((String)interfaceBox.getSelectedItem());
        }
    }
    
    private void invokeSearchUI()
    {
        String findString = JOptionPane.showInputDialog(frame,"Enter string to Search","Search Window",JOptionPane.INFORMATION_MESSAGE);
        if(findString == null)
        {
            return;
        }
        Method[] array = getAllMethods();
        for(int i=0;i<array.length;i++)
        {
            String name = array[i].getName();
            if(name.indexOf(findString) != -1)
            {
                list.setSelectedIndex(i);
                int temp = i*100/array.length;
                JScrollBar bar = scroll.getVerticalScrollBar();
                int max = bar.getMaximum();
                int value = max*temp/100;
                bar.setValue(value);
                break;
            }
        }	
    }	
	
    private void takeActionOnResult(Object result)
    {
        JTextArea area = new JTextArea();
        area.setColumns(90);
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        String message = "";
        if(result instanceof Vector)
        {
            Vector r = (Vector)result;
            area.append("The size of Vector is ==="+r.size() );
            area.append('\n'+"%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%"+'\n');
            for(int i =0;i<r.size();i++)
            {
                Object element =r.elementAt(i);
                if(element instanceof com.adventnet.nms.eventdb.Event)
                {
                    area.append('\n'+(((com.adventnet.nms.eventdb.Event)element).getProperties()).toString());
                    area.append('\n'+"$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$"+'\n');
                }
                if(element instanceof Alert)
                {
                    area.append((((Alert)element).getProperties().toString()));
                    area.append('\n'+"#########################################"+'\n');
                }
                if(element instanceof String)
                {
                    area.append((String)element);
                    area.append('\n'+"#########################################"+'\n');
                }
                else
                {
                    area.append(element.toString());
                    area.append('\n'+"#########################################"+'\n');            
                }

            }
        }
        else if (result instanceof Properties)
        {
            area.append("The Properties are =============================="+'\n');
            area.append(((Properties)result).toString());
        }
        else if (result instanceof Alert)
        {
            area.append("The Properties of Alert are ====="+'\n'+((Alert)result).getProperties());
        }        
        else if (result instanceof com.adventnet.nms.eventdb.Event)
        {
            area.append("The Properties of Event are ===="+'\n'+((com.adventnet.nms.eventdb.Event)result).getProperties());
        }
        else if (result instanceof TrapParser[])
        {
            TrapParser[] array = (TrapParser[])result;
            area.append("Number of TrapParsers ============="+array.length+'\n');
            for(int i=0;i<array.length;i++)
            {
                area.append('\n'+"*********************************************************************");
                area.append('\n'+array[i].getProperties().toString());
            }
        }
        else if (result instanceof TrapFilterHolder[])
        {
            TrapFilterHolder[] array = (TrapFilterHolder[])result;
            area.append("Number of TrapFilters ============="+array.length+'\n');
            for(int i=0;i<array.length;i++)
            {
                area.append('\n'+"*********************************************************************");
                area.append('\n'+array[i].getProperties().toString());
            }
        }        
        else if (result instanceof FilterObject[])
        {
            FilterObject[] array = (FilterObject[])result;
            area.append("Number of FilterObjects ============="+array.length+'\n');
            for(int i=0;i<array.length;i++)
            {
                FilterObject filter = array[i];
                area.append('\n'+"*********************************************************************");
                area.append('\n'+"Name of the Filter = "+filter.filtername);
                area.append('\n'+"The matching criteria is ==  "+filter.criteria.toString());
                FilterAction[] actionarray = filter.actions;
                for(int j=0;j<actionarray.length;j++)
                {
                    area.append('\n'+"&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
                    area.append('\n'+actionarray[j].getProperties().toString());
                }
            }
        }        
        else if (result instanceof int[])
        {
            area.append("The numbers are ==================="+'\n');
            int[] array = (int[])result;
            for(int i=0;i<array.length;i++)
            {
                area.append('\n'+String.valueOf(array[i]));
            }
        }        
        else if (result instanceof String[])
        {
            area.append("The names are ======================"+'\n');
            String[] array = (String[])result;
            for(int i=0;i<array.length;i++)
            {
                area.append('\n'+array[i]);
            }
        }        
        else if (result instanceof Properties[])
        {
            Properties[] array = (Properties[])result;
            area.append("Length of array========"+array.length);
            for(int i=0;i<array.length;i++)
            {
                area.append('\n'+"*********************************************************************");
                area.append('\n'+array[i].toString());
            }
        }        
        else
        {
            area.append(result.toString());
        }

        formResult(area);        
    }
    
    private void formResult(JTextArea area)
    {
        final JDialog dialog = new JDialog(frame,"Result");
        JScrollPane pane = new JScrollPane(area,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        dialog.getContentPane().setLayout(new BorderLayout());
        dialog.getContentPane().add(pane,BorderLayout.CENTER);
        area.setForeground(Color.blue);
        area.setBackground(Color.white);
        dialog.setSize(600,400);
        JPanel lowerPanel = new JPanel();
        lowerPanel.setLayout(new FlowLayout());
        JButton okbutton = new JButton("OK");
        okbutton.setPreferredSize(new Dimension(120,30));
        okbutton.setBorder(new BevelBorder(BevelBorder.RAISED));
        okbutton.setBackground(Color.white);
        okbutton.setForeground(Color.red);
        okbutton.addActionListener(new ActionListener()
            {
        
                public void actionPerformed(ActionEvent ee)
                {
                    dialog.dispose();
                }
            });
                    
        lowerPanel.add(okbutton);
        dialog.getContentPane().add(lowerPanel,BorderLayout.SOUTH);
        dialog.show();        
    }
    
    public void showSelectedMethods(String apiname)
    {        
        String tempString = (String) hostBox.getSelectedItem(); 
        try
        {
            String hostString = InetAddress.getByName(tempString).getHostAddress();
            if(secureFlag)
            {			
                RMIAccessAPI api = (RMIAccessAPI)Naming.lookup("//"+hostString+":"+port+"/RMIAccessAPI");

                if(apiname.equals("com.adventnet.nms.alertdb.AlertAPI"))
                {
                    myAPI = (AlertAPI)api.getAPI("root","public","AlertAPI");
                }
                if(apiname.equals("com.adventnet.nms.alertdb.AlertFilterAPI"))
                {
                    myAPI = (AlertFilterAPI)api.getAPI("root","public","AlertFilterAPI");
                }
                if(apiname.equals("com.adventnet.nms.eventdb.EventFilterAPI"))
                {
                    myAPI = (EventFilterAPI)api.getAPI("root","public","EventFilterAPI");
                }
                if(apiname.equals("com.adventnet.nms.eventdb.EventParserAPI"))
                {
                    myAPI = (EventParserAPI)api.getAPI("root","public","EventParserAPI");
                }
                if(apiname.equals("com.adventnet.nms.eventdb.EventAPI"))
                {
                    myAPI = (EventAPI)api.getAPI("root","public","EventAPI");
                }
                if(apiname.equals("com.adventnet.nms.eventdb.TrapAPI"))
                {
                    myAPI = (TrapAPI)api.getAPI("root","public","TrapAPI");
                }			
            }
            else
            {
                if(apiname.equals("com.adventnet.nms.alertdb.AlertAPI"))
                {
                    myAPI = (AlertAPI)Naming.lookup("//"+hostString+":"+port+"/AlertAPI");
                }
                if(apiname.equals("com.adventnet.nms.alertdb.AlertFilterAPI"))
                {
                    myAPI = (AlertFilterAPI)Naming.lookup("//"+hostString+":"+port+"/AlertFilterAPI");
                }
                if(apiname.equals("com.adventnet.nms.eventdb.EventFilterAPI"))
                {
                    myAPI = (EventFilterAPI)Naming.lookup("//"+hostString+":"+port+"/EventFilterAPI");
                }
                if(apiname.equals("com.adventnet.nms.eventdb.EventParserAPI"))
                {
                    myAPI = (EventParserAPI)Naming.lookup("//"+hostString+":"+port+"/EventParserAPI");
                }
                if(apiname.equals("com.adventnet.nms.eventdb.EventAPI"))
                {
                    myAPI = (EventAPI)Naming.lookup("//"+hostString+":"+port+"/EventAPI");
                }
                if(apiname.equals("com.adventnet.nms.eventdb.TrapAPI"))
                {
                    myAPI = (TrapAPI)Naming.lookup("//"+hostString+":"+port+"/TrapAPI");
                }
            }
        }  
        catch(UnknownHostException ce)
        {
            String msg = "Could not connect to host :: " + (String) hostBox.getSelectedItem() + "\nCheck if server is running";
            JOptionPane.showMessageDialog(frame, msg,"Error: Not Connected",JOptionPane.ERROR_MESSAGE);
        }
        catch(Exception ce)
        {
            System.out.println("Not able to connect "+tempString);
            ce.printStackTrace();
        }
        setList();
    }
        
    public void valueChanged(ListSelectionEvent e)
    {
        setLowerPanel();
    }	
    
    public static void main (String[] a)
    {
        if(a.length !=2)
        {
            System.out.println("Usage: java TestAPI RMIPort SecureFlag");
            return;
        }
        TestAPI test= new TestAPI(Integer.parseInt(a[0]),Boolean.valueOf(a[1]).booleanValue());
    }
    
    public boolean accept(File file, String name)
    {
        int index = name.lastIndexOf('.');
        if(index ==-1)
        {
            return false;
        }
        else
        {
            name = name.substring(index+1);
            if(name.equals("class")|| name.equalsIgnoreCase("jpg") || name.equalsIgnoreCase("png") || name.equalsIgnoreCase("gif") || name.equalsIgnoreCase("jpeg"))
            {
                return true;
            }
            else
            {
                return false;
            }
        }
    }
               
    public String getClassNameFromFileName(String name)
    {
        int index = name.lastIndexOf("classes"+File.separator)+8;
        String classname = null;
        if(index !=-1)
        {
            classname = name.substring(index);
        }
        classname = classname.replace(File.separatorChar,'.');
        classname = classname.substring(0,classname.lastIndexOf('.'));
        return classname;
    }
        
    public Vector getAllInterfaces(String name)
    {
        name = name.replace('.',File.separatorChar);
        File directory = new File(PureUtils.rootDir+File.separator+"classes"+File.separator+name);
        File[] files = null;
        Vector interfaces = new Vector();
        if(directory.isDirectory())
        {
            files = directory.listFiles(this);
        }
        try
        {
            if(files !=null && files.length!=0)
            {
                for(int i=0;i<files.length;i++)
                {
                    String classname = getClassNameFromFileName(files[i].getAbsolutePath());
                    Class myclass = Class.forName(classname);
                    classname = myclass.getName();
                    String check = classname.substring(0,classname.length()-3);
                    if(myclass.isInterface() && check.equals("API") )
                    {
                        interfaces.addElement(classname);
                    }
                }
            }
        }
        catch(Exception ee)
        {
            System.out.println("Exception in initilizing class");
            ee.printStackTrace();
        }
        return interfaces;
    }
    
    private FilenameFilter getInstance()
    {
        return this; 
    }
    
}
