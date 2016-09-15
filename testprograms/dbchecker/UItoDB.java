import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;

public class UItoDB implements ActionListener
{
    JFrame frame = null;
    JPanel topPanel = null;
    JLabel tableNameLabel = null;
    //JTextField tableNameText = null;
    JButton queryBut,tiltBut = null;
    JScrollPane scroll = null;
    JTable table = null;
    DefaultTableModel model = null;

    JTextArea area = null;
    JPanel botPanel = null;
    JTextField connectText = null;
    JButton connectBut = null;
    JButton cons_check_but = null;
    JTextArea statusText = null;
    
    JComboBox queryList = null;
    Vector queries = new Vector();
    Connection conn = null;
        
    int cnt = 0;
    ConnectBox conBox = null;
    
    public UItoDB()
    {
	createUI();
	System.out.println(" Started");
	
	conBox = new ConnectBox(frame);
	createConnection();
	frame.setVisible(true);
    }
    
    private void createConnection()
    {
	conBox.show();
	conn = conBox.getConnection();
	if (conn == null)
	    System.exit(0);	
	
	String host = conBox.getHost();	
	
	frame.setTitle("Successfully connected to "+host);
	statusText.setText("Successfully connected to "+host);
    }
    
    public void createUI()
    {	
	frame = new JFrame("Welcome to Govind's JDBC");
	frame.getContentPane().setLayout(new BorderLayout());
	frame.addWindowListener(new WindowAdapterImpl());
	
	topPanel = new JPanel(new BorderLayout());
	
	JPanel northOfTop = new JPanel(new BorderLayout());
	tableNameLabel = new JLabel("Query String");
	northOfTop.add(tableNameLabel,BorderLayout.WEST);
	
	queryList = new JComboBox();
	queryList.setEditable(true);
	queryList.setMaximumRowCount(10);
	queryList.addActionListener(this);
	northOfTop.add(queryList,BorderLayout.CENTER);
	
	
	JPanel dummyPanel = new JPanel(new BorderLayout());
	
	JPanel dummyPanel2 = new JPanel(new FlowLayout());
	queryBut = new JButton("Query");
	queryBut.addActionListener(this);
	dummyPanel2.add(queryBut);
	
	tiltBut = new JButton("Tilt");
	tiltBut.addActionListener(this);
	dummyPanel2.add(tiltBut);

	JPanel dummyPanel3 = new JPanel(new BorderLayout());
	dummyPanel3.add(new JLabel("Status:"),BorderLayout.WEST);
	
	statusText = new JTextArea();
	statusText.setEditable(false);
	dummyPanel3.add(statusText,BorderLayout.CENTER);
	
	dummyPanel.add(dummyPanel2,BorderLayout.NORTH);
	dummyPanel.add(dummyPanel3,BorderLayout.SOUTH);
				
	topPanel.add(northOfTop,BorderLayout.NORTH);
	topPanel.add(dummyPanel,BorderLayout.SOUTH);
	
	frame.getContentPane().add(topPanel,BorderLayout.NORTH);
	
	model = new DefaultTableModel();
	table = new JTable(model);
	scroll = new JScrollPane(table);
	
	frame.getContentPane().add(scroll,BorderLayout.CENTER);

	botPanel = new JPanel(new FlowLayout());

	connectBut = new JButton("Re-Connect");
	connectBut.addActionListener(this);
	botPanel.add(connectBut);

	cons_check_but = new JButton("CheckConsistency");
	cons_check_but.addActionListener(this);
	botPanel.add(cons_check_but);

	frame.getContentPane().add(botPanel,BorderLayout.SOUTH);

	frame.setBounds(70,100,865,465);
	//frame.setVisible(true);
    }

    public class WindowAdapterImpl extends WindowAdapter
    {
        public void windowClosing(WindowEvent we)
        {
            System.out.println("UItoDB shut down");
	    System.exit(0);
        }
    }
    
    private String trimToLength(String str)
    {
	StringTokenizer strTok = new StringTokenizer(str);
	StringBuffer strBufOld = new StringBuffer();
	StringBuffer strBufRet = new StringBuffer();
	StringBuffer line = new StringBuffer();

	while(strTok.hasMoreTokens())
	{
	    String word = strTok.nextToken();
	    line.append(word).append(" ");
	    	    
	    if(line.length() < 40)
	    {
		strBufOld = strBufOld.append(word).append(" ");
	    }
	    else
	    {
		line = new StringBuffer(strBufOld.toString());
		
		strBufRet.append(line);
		strBufRet.append("\n");
		line = new StringBuffer(word).append(" ");
		strBufOld = new StringBuffer(word).append(" ");
	    }
	    if(!strTok.hasMoreElements())
		strBufRet.append(line);
	}
	return strBufRet.toString();
    }

    class ForConsistencyCheck extends Thread
    {
	ConsistencyChecker consCheck = null;
	String hostname = null;
	String dbase = null;
	Connection con = null;

	ForConsistencyCheck(String host, String db, Connection c)
	{
	    hostname = host;
	    dbase = db;
	    con = c;
	    this.start();
	}
	
	public void run()
	{
	    statusText.setText("Checking consistency of the WebNMS database..");
	    consCheck = new ConsistencyChecker(hostname,dbase,con);
	    statusText.setText("Consistency check complete");
	}
    }

    public void query(String query)
    {
	Vector big = new Vector();
	Vector small = null;
	Vector colNames = new Vector();

	Statement stm = null;
	try
	{
	    stm = conn.createStatement();
	    if(query.startsWith("delete") || query.startsWith("update") || query.startsWith("insert") || query.startsWith("create"))
	    {
		System.out.println("Attempting to -- "+query);
		statusText.setText(" Result "+ stm.executeUpdate(query));
		model.setDataVector(new Vector(), new Vector());
		return;
	    }
	    
	    ResultSet rs = stm.executeQuery(query);
	    ResultSetMetaData rsmd = rs.getMetaData();
	    int count  = 1;
		    
	    for(int i = 1; i<= rsmd.getColumnCount(); i++)
		colNames.addElement(rsmd.getColumnName(i));

	    while(rs.next())
	    { 
		small = new Vector();
		for(int i = 1; i<= rsmd.getColumnCount(); i++)
		{
		    small.addElement(rs.getString(i));
		}
		big.addElement(small);
	    }
	    model.setDataVector(big,colNames);
	    statusText.setText("Query Successful..");
		if (!queries.contains(query))
		{
			queries.addElement(query);
			queryList.addItem(query);
		}
	}
	catch(Exception ex)
	{
	    statusText.setText("Query Falied..");
	    String msg = ex.toString();
	    JOptionPane.showMessageDialog(null, "Sorry.. Cannot execute query!\n"+trimToLength(msg.substring(msg.lastIndexOf("Exception")+9)),"Query Failure",JOptionPane.ERROR_MESSAGE);   
	    
	}
	finally
	{
	    try{ stm.close();}catch(Exception e){System.out.println(" Cannot close statement "+e);}
	}
    }

    public void tilt(String query)
    {
	Vector big = new Vector();
	Vector small = null;
	Vector colNames = new Vector();
	Vector colNames1 = new Vector();
	Vector elements = new Vector();
	Statement stm = null;
	
	try
	{
	    stm = conn.createStatement();
	    if(query.startsWith("delete") || query.startsWith("update") || query.startsWith("insert") || query.startsWith("create"))
	    {
		System.out.println("Attempting to -- "+query);
		statusText.setText(" Result "+ stm.executeUpdate(query));
		model.setDataVector(new Vector(), new Vector());
		return;
	    }
	    
	    ResultSet rs = stm.executeQuery(query);
	    ResultSetMetaData rsmd = rs.getMetaData();
	    int count  = 1;
	    
	    for(int i = 1; i<= rsmd.getColumnCount(); i++)
		colNames.addElement(rsmd.getColumnName(i));
	    
	    colNames1.addElement("colName");
	    colNames1.addElement("Value");

	    Vector forBlank = new Vector();
	    forBlank.addElement(new String("####################################################"));
	    forBlank.addElement(new String("####################################################"));
	    
	    while(rs.next())
	    { 
		for(int i = 1; i<= rsmd.getColumnCount(); i++)
		{
		    small = new Vector();
		    small.addElement(colNames.elementAt(i-1));
		    small.addElement(rs.getString(i));
		    big.addElement(small);
		}
		big.addElement(forBlank);
	    }
	    model.setDataVector(big,colNames1);
	    statusText.setText("Query Successful..");
		if (!queries.contains(query))
		{
			queries.addElement(query);
			queryList.addItem(query);
		}
	}
	catch(Exception ex)
	{
	    statusText.setText(ex.toString());
	    String msg = ex.toString();
	    JOptionPane.showMessageDialog(null, "Sorry.. Cannot execute query!\n"+trimToLength(msg.substring(msg.lastIndexOf("Exception")+9)),"Query Failure",JOptionPane.ERROR_MESSAGE);   
	}
	finally
	{
	    try{
		stm.close();
	    }catch(Exception e){System.out.println("Exception while trying to close statement");}
	}
	
    }
    
    public void actionPerformed(ActionEvent evt)
    {
	if((evt.getSource()).equals(connectBut))
	{
	    model.setDataVector(new Vector(), new Vector());
	    statusText.setText("");
	    createConnection();
	}
	else if(evt.getSource().equals(queryBut))
	{
	    statusText.setText("");
	    query((queryList.getSelectedItem().toString()).trim());
	}
	else if(evt.getSource().equals(tiltBut))
	{
	    statusText.setText("");
	    tilt((queryList.getSelectedItem().toString()).trim());
	}
	
	/*else if(evt.getSource().equals(queryList))
	{
	    System.out.println(" ID "+ evt.getID());
	    System.out.println(" paramString "+evt.paramString()+"\n");
	    statusText.setText("");
	    query((queryList.getSelectedItem().toString()).trim());
	}*/
	else if(evt.getSource().equals(cons_check_but))
	{
	    ForConsistencyCheck cons = new ForConsistencyCheck(conBox.getHost(),conBox.getdb(),conn);
	}
	
    }
    
    public static void main(String[] args)
    {
	new UItoDB();
    }
}









