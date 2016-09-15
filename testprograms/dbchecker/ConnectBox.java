import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
import java.io.*;

public class ConnectBox implements ActionListener
{
    JLabel dataBase, hostName, userName, passWord, url = null;
    JTextField userField, passField, urlField = null;
    JComboBox dbCombo,hostCombo = null;
    JButton conBut, clearBut = null;
    Vector hostNamesList = new Vector();
	
    public JDialog connectFrame = null;
    JPanel mainPanel, botPanel, westPanel,centPanel  = null;
	
    private String dataBaseUsed = null;
    private String host = null;
    private Connection conn = null;
   	
    public ConnectBox(Frame comp) 
    {
	connectFrame = new JDialog(comp,true);
	connectFrame.setTitle("ConnectBox");
	connectFrame.getContentPane().setLayout(new BorderLayout());
		    
	hostName = new JLabel("Enter Hostname ");
	dataBase = new JLabel("Select Database ");
	userName = new JLabel("Enter UserName ");
	passWord = new JLabel("Enter Password ");
	url      = new JLabel("Enter url ");
		    
	String[] str = {"-Select one-","MySql", "Oracle", "MSSql","HSql"};
	dbCombo = new JComboBox(str);
	dbCombo.addActionListener(this);
		    
	hostCombo = new JComboBox();
	hostCombo.setEditable(true);
	hostCombo.addActionListener(this);

	userField = new JTextField();
	passField = new JTextField();
	urlField  = new JTextField();
		    
	mainPanel = new JPanel(new BorderLayout());
		    
	westPanel = new JPanel(new GridLayout(0,1));
	centPanel = new JPanel(new GridLayout(0,1));
		    
	westPanel.add(hostName);
	westPanel.add(dataBase);
	westPanel.add(userName);
	westPanel.add(passWord);
	westPanel.add(url);
		    
	centPanel.add(hostCombo);
	centPanel.add(dbCombo);
	centPanel.add(userField);
	centPanel.add(passField);
	centPanel.add(urlField);
		    
	mainPanel.add(westPanel,BorderLayout.WEST);
	mainPanel.add(centPanel,BorderLayout.CENTER);
		    
	connectFrame.getContentPane().add(mainPanel,BorderLayout.NORTH);
		    
	botPanel = new JPanel(new FlowLayout());
		    
	conBut = new JButton("Connect");
	conBut.addActionListener(this);
		    
	clearBut= new JButton("Clear");
	clearBut.addActionListener(this);
		    
	botPanel.add(conBut);
	botPanel.add(clearBut);
		    
	connectFrame.getContentPane().add(botPanel,BorderLayout.SOUTH);
	connectFrame.setBounds(300,200,400,200);
	//connectFrame.setVisible(true);
    }
	
   	
    public String getHost() { return host; }
    public String getdb() { return dataBaseUsed; }
    public Connection getConnection(){ return conn;}
	
    public  void unShow()
    {
	connectFrame.setVisible(false);
		    
    }
	
    public  void show()
    {
	connectFrame.setVisible(true);
    }
	
    private void clearAll()
    {
	//hostField.setText("");
	userField.setText("");
	passField.setText("");
	urlField.setText("");
	dbCombo.setSelectedIndex(0);
    }
    
    private void checkAndAdd(String hostName)
    {
	if( !(hostNamesList.contains(hostName.toString())) )
	{
	    hostNamesList.addElement(hostName);
	    hostCombo.addItem(hostName);
	}
    }
    
    public void actionPerformed(ActionEvent e)
    {
	if(e.getSource().equals(hostCombo))
	{
	    checkAndAdd(hostCombo.getSelectedItem().toString().trim());
	    getDatabaseInfo();
	}
	if(e.getSource().equals(dbCombo))
	    getDatabaseInfo();		    
	else if(e.getSource().equals(clearBut))
	    clearAll();		    
	else  if(e.getSource().equals(conBut))
	    connect();
    }
    
    private void connect(String db, String host, String user, String passwd, String url) throws Exception
    {	
	String driver = null;

	if(db.equalsIgnoreCase("Mysql"))
	    driver = "org.gjt.mm.mysql.Driver";
	else if(db.equalsIgnoreCase("Oracle"))
	    driver = "oracle.jdbc.driver.OracleDriver";
	else if(db.equalsIgnoreCase("MSSql"))
	    driver = "com.ashna.jturbo.driver.Driver";
	else if(db.equalsIgnoreCase("HSql"))
	    driver = "org.hsql.jdbcDriver";

	//Instantiate the corresponding driver

	Class.forName(driver);
	if(user.equals(""))
	    conn = DriverManager.getConnection(url);
	else 
	    conn = DriverManager.getConnection(url, user, passwd);

	System.out.println("Successfully connected to " + db + " database at host: " + host);
    }
		
    private void connect()
    {
	//first close the existing connection
	if(conn != null)
	{
	    try
	    {
		conn.close();
	    }
	    catch(Exception e)
	    {
		System.out.println(" cannot close connection "+e);
	    }
	}


	try
	{
	    connect(dataBaseUsed, host, userField.getText().toString(), 
		    passField.getText().trim().toString(), urlField.getText().trim().toString());
	    connectFrame.setVisible(false);
	}
	catch(ClassNotFoundException e1)
	{
	    JOptionPane.showMessageDialog(null, "Cannot connect to "+hostCombo.getSelectedItem()+". "+e1.toString(),"Connection Failure",JOptionPane.ERROR_MESSAGE);
	    conn=null;
	}
	
	catch(Exception e2)
	{
	    JOptionPane.showMessageDialog(null, "Cannot connect to "+hostCombo.getSelectedItem()+". Please check if "+dataBaseUsed+" is running.","Connection Failure",JOptionPane.ERROR_MESSAGE);   
	    System.out.println(" Connection problem "+e2);
	    conn=null;
	}
    }
	
    private void getDatabaseInfo()
    {
		
	if( ((hostCombo.getSelectedItem()) == null) || ((host= (hostCombo.getSelectedItem().toString())).equals("")) )
	    host= "localhost";
				
	if (((dbCombo.getSelectedItem()).toString()).equals("MySql"))
	{
	    urlField.setText("jdbc:mysql://"+host+"/WebNmsDB");
	    dataBaseUsed = "Mysql";
	}
	else if(((dbCombo.getSelectedItem()).toString()).equals("Oracle"))
	{
	    urlField.setText("jdbc:oracle:thin:@"+host+":1521:orcl");
	    dataBaseUsed = "Oracle";
	}
	else if(((dbCombo.getSelectedItem()).toString()).equals("MSSql"))
	{
	    urlField.setText("jdbc:JTurbo://"+host+":1433/WebNmsDB");
	    dataBaseUsed = "MSSql";
	}
	else if(((dbCombo.getSelectedItem()).toString()).equals("HSql"))
	{
	    urlField.setText("jdbc:HypersonicSQL:HSQL://"+host+":9001/WebNmsDB");
	    dataBaseUsed = "HSql";
	}
    }
}











