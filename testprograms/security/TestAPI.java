//$Id: TestAPI.java,v 1.2 2002/07/16 06:14:25 dwaraka Exp $/

import com.adventnet.nms.util.CommonAPI;
import com.adventnet.nms.server.CommonFilterAPI;
import com.adventnet.nms.alertdb.*;
import com.adventnet.nms.eventdb.*;
import java.lang.reflect.Method;
import java.lang.Class;
import javax.swing.*;
import java.awt.*;
import java.rmi.Naming;
import javax.swing.event.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.io.*;
import com.adventnet.nms.util.PureUtils;
import java.util.*;
import java.net.InetAddress;
import com.adventnet.nms.server.FilterObject;
//Security Imports
import com.adventnet.security.authorization.*;
import com.adventnet.security.authentication.*;
import com.adventnet.security.crypto.*;
import com.adventnet.security.common.*;
import com.adventnet.security.audit.*;
import com.adventnet.nms.authentication.*;
import com.adventnet.security.AuthUtil;

/** The following source has the intelligence to test the
 * security API's as of now the Admin API's will work properly,
 * the Engine and the other API's need to be verified properly.
 */

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
    JComboBox portBox = null;
    JLabel desc = null;
    JLabel desc1 = null;
    JLabel desc2 = null;
    JList list = null;
    Vector hosts = null;
    Vector ports = null;
    Vector interfaces = null;
    Thread myThread = null;    
    private CryptoGraphAPI cryptoAPI = null;
    private boolean crypto = false;
    public TestAPI()
	{
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
        try
        {
            interfaces = new Vector();
            interfaces.add("com.adventnet.security.authorization.CustomViewScopeAPI");
            interfaces.add("com.adventnet.security.authorization.AuthorizationEngine");
            interfaces.add("com.adventnet.security.authentication.AuthenticationAPI");
            interfaces.add("com.adventnet.nms.authentication.UserConfigAPI");
            interfaces.add("com.adventnet.security.audit.AuditAPI");
            interfaces.add("com.adventnet.security.authorization.SecuredAdminAPI");
            hosts = new Vector();
            /*hosts.add("shanmugamk");
            hosts.add("localhost");
            hosts.add("dwaraka");
            hosts.add("mohamedm");
            hosts.add("kumaragurun");
            hosts.add("skumar");*/
	    hosts.add("192.168.108.170");
	    ports = new Vector();
	    ports.add("1099");
	    //ports.add("1090");
	    //ports.add("2099");

        }
        catch(Exception e)
        {
            e.printStackTrace();
        } 

    }
    
    public void setUpFirstPanel()
    {
        interfaceBox = new JComboBox(interfaces);
        interfaceBox.setBackground(Color.white);
        hostBox = new JComboBox(hosts);
        portBox = new JComboBox(ports);
        interfaceBox.setEditable(true);
        interfaceBox.setSelectedIndex(0);
        interfaceBox.addActionListener(this);
        interfaceBox.setActionCommand("interface");
        hostBox.setEditable(true);
        hostBox.setActionCommand("host");
        hostBox.addActionListener(this);
        hostBox.setSelectedIndex(0);
	portBox.setEditable(true);
        portBox.setActionCommand("host");
        portBox.addActionListener(this);
        portBox.setSelectedIndex(0);
        //System.out.println("dwarala "+System.getProperty("file.seperator"));
        desc = new JLabel("The Vector has to be entered with the seperator as '&' and The Properties has to be entered with the seperator & and the key1=value1");
        desc1 = new JLabel("The AuthorizedViewObject has the first element as the view name and from the second onwards it will be as the properties");
        desc2 = new JLabel("The AuthenticationTicket has the first element as the key and the second and third onwards it will be as the properties");
        
        panel1.add(interfaceBox);
        panel1.add(hostBox);
        panel1.add(portBox);
        panel1.add(desc);
        panel1.add(desc1);
        panel1.add(desc2);
        
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
        panel3.add(search);
		panel3.add(invoke);
        panel3.add(exit);
        invoke.registerKeyboardAction(this,KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,0),JComponent.WHEN_IN_FOCUSED_WINDOW);
        exit.registerKeyboardAction(this,KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE,0),JComponent.WHEN_IN_FOCUSED_WINDOW);
        search.registerKeyboardAction(this,KeyStroke.getKeyStroke(KeyEvent.VK_S,0),JComponent.WHEN_IN_FOCUSED_WINDOW);
        frame.setTitle("SecurityAPI RemoteTester");
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
	    if(selected.equals("com.adventnet.security.authorization.SecuredAdminAPI"))
	    {
		   crypto = true;
	    }
	    else
	    {
		    crypto = false;
	    }
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
        //System.out.println("DWARAKA "+classname);
        return classname;
        
    }
    
    public void setLowerPanel()
    {
        Method selectedMethod = (Method)list.getSelectedValue();
        //int index = list.getSelectedIndex();
		//Method[] methodArray = getAllMethods();
		//Method selectedMethod = methodArray[index];
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
        if(name.equals("String") || name.equals("Vector") || name.equals("Properties") || name.equals("Hashtable") || name.equals("AuthorizedViewObject") || name.equals("boolean") || name.equals("int") || name.equals("AuthenticationTicket"))
        {
            if(field.getText().equals("null"))
                {
                return null;
                }
        
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
            
            /*
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
                AlertAPI tempapi = (AlertAPI)Naming.lookup("//"+(String)hostBox.getSelectedItem()+"/AlertAPI");
                Alert alt = tempapi.checkOutIfAvailable(field.getText().trim());
				tempapi.unlock(alt);
				return alt;
                }
                }
            */
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
            if(name.equals("Hashtable"))
                {
                    Hashtable p = new Hashtable();
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
            if(name.equals("AuthorizedViewObject"))
                {
                    AuthorizedViewObject authView = new AuthorizedViewObject();
                    Object[] authViewObj = tokenizeComma(field.getText());
                    
                    Properties prop = new Properties();
                    for(int m=0;m<authViewObj.length;m++)
                        {
                            //System.out.println("SIZE "+authViewObj.length);
                            String su = (String)authViewObj[m];
                            
                            if(m == 0)
                                {
                                    authView.setAuthorizedViewName(su);
                                }
                            
                            else
                                {
                                    StringTokenizer tok = new StringTokenizer(su,"=",false);
                                    {
                                        prop.put(tok.nextToken(),tok.nextToken());
                                    }
                                }
                        }
                    authView.setViewProperties(prop);
                    //System.out.println("DWARAKA :"+authView.getAuthorizedViewName()+"             "+authView.getViewProperties());
                    return authView;
                }    

	    if(name.equals("AuthenticationTicket"))
                {
                    AuthTicket authView = new AuthTicket();
                    Object[] authViewObj = tokenizeComma(field.getText());
                    
                    Properties prop = new Properties();
                    for(int m=0;m<authViewObj.length;m++)
                        {
                            //System.out.println("SIZE "+authViewObj.length);
                            String su = (String)authViewObj[m];
                            
                            if(m == 0)
                                {
                                    authView.setKey(su);
                                }
			    else if(m==1)
			    {
				    authView.setPassword(su);
			    }
                            
                            else
                                {
                                    StringTokenizer tok = new StringTokenizer(su,"=",false);
                                    {
                                        prop.put(tok.nextToken(),tok.nextToken());
                                    }
                                }
                        }
                    authView.setUserProperties(prop);
                    //System.out.println("DWARAKA :"+authView.getAuthorizedViewName()+"             "+authView.getViewProperties());
                    return authView;
                }
            
            
            if(name.equals("Vector"))
                {
                    Vector v = new Vector();
                    Object[] arrayVec = tokenizeComma(field.getText());
                    for(int j=0;j<arrayVec.length;j++)
                        {
                            String subVec = (String) arrayVec[j];
                            if(subVec.equals("null"))
                                {
                                    subVec = null;
                                }
                            v.addElement(subVec);
                        }
                    //System.out.println("dwaraka :"+v);
                    return v;
                }
            
            
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
        list.setBackground(Color.black);
        list.setForeground(Color.green);
        list.addListSelectionListener(this);
        list.setSelectedIndex(0);
        
		if( scroll == null)
		{
            scroll = new JScrollPane(list,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
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
		if(crypto)
		{
			String user = (String)objs[0];
			cryptoAPI = (CryptoGraphAPI)((SecuredAdminAPI)myAPI).getCryptoAPI(user);
			System.out.println("CryptoGraphAPI "+cryptoAPI);
		}
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
                    System.out.println("Exception on invoking "+ee);
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
        area.append("The Object returned is :"+result+'\n');
        if(result instanceof Vector)
        {
            
            Vector r = (Vector)result;
            area.append("The size of Vector is ==="+r.size() );
            area.append('\n'+"%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%"+'\n');
            /*
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
            */
        }
        
        else if (result instanceof Properties)
        {
            area.append("The Properties are =============================="+'\n');
            area.append(((Properties)result).toString());
        }
        /*
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
        */
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
		System.out.println("This is string");
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
        //okbutton.registerKeyboardAction(dialog,KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,0),JComponent.WHEN_IN_FOCUSED_WINDOW);
        
        
    }
    
    public void showSelectedMethods(String apiname)
    {
        
        String tempString = (String) hostBox.getSelectedItem(); 
	String portString = (String) portBox.getSelectedItem();
        try
        {
            String hostString = InetAddress.getByName(tempString).getHostAddress()+":"+portString;
            
            if(apiname.equals("com.adventnet.security.authorization.CustomViewScopeAPI"))
            {
                myAPI = (CustomViewScopeAPI)Naming.lookup("//"+hostString+"/NmsAuthAdminAPI");
            }
            if(apiname.equals("com.adventnet.security.authorization.AuthorizationEngine"))
            {
                myAPI = (AuthorizationEngine)Naming.lookup("//"+hostString+"/NmsAuthEngineAPI");
            }
            if(apiname.equals("com.adventnet.security.authentication.AuthenticationAPI"))
            {
                myAPI = (AuthenticationAPI)Naming.lookup("//"+hostString+"/NmsAuthenticationAPI");
            }
            if(apiname.equals("com.adventnet.security.audit.AuditAPI"))
            {
                myAPI = (AuditAPI)Naming.lookup("//"+hostString+"/NmsAuditAPI");
            }
            if(apiname.equals("com.adventnet.nms.authentication.UserConfigAPI"))
            {
                myAPI = (UserConfigAPI)Naming.lookup("//"+hostString+"/UserConfigAPI");
            }
            if(apiname.equals("com.adventnet.security.authorization.SecuredAdminAPI"))
            {
                RMIAccessAPI myAPI1 = (RMIAccessAPI)Naming.lookup("//"+hostString+"/RMIAccessAPI");
		String challenge = myAPI1.getChallenge( "root" );
		String key = AuthUtil.getChallengeKey( "root", "public", challenge);
		AuthenticationTicket ticket = new AuthTicket();
		ticket.setKey(key);
		myAPI = ( SecuredAdminAPI ) myAPI1.getAPI( "root", "public", "SecuredAdminAPI");
            }
        }
        
        catch(Exception ce)
        {
            System.out.println("Not able to connect "+tempString+'\n'+ce);
            ce.printStackTrace();
        }
        //System.out.println("myapi is =="+myAPI);
        setList();
    }
        
    public void valueChanged(ListSelectionEvent e)
    {
		setLowerPanel();
    }
	
    
	public static void main (String[] a)
	{
		TestAPI test= new TestAPI();
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












