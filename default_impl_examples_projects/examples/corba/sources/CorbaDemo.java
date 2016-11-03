import javax.swing.*;
import com.adventnet.snmp.corba.*;
import org.omg.CosNaming.*;
import org.omg.CORBA.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;

public class CorbaDemo extends JFrame implements ActionListener, ItemListener{

	JTextField hosttext = null;
	JTextField communitytext = null;
	JTextField wcommunitytext = null;
	JTextField porttext = null;
	JComboBox versionChoice = null;
	JTextField oidtext = null;
	JTextField usernametext = null;
	JTextField contextidtext= null;
	JTextField contextnametext = null;
	JTextField privpasswordtext = null;
	JTextField authpasswordtext = null;
	
	JButton browseBut = null;
	
	JComboBox secChoice = null;
	JComboBox authprotocolChoice = null;
	JTextArea resulttext = null;
	JLabel statuslabel = null;
	
	JComboBox operationChoice = null;
	JButton resultBut = null;

	JButton loadBut = null;
	JTextField mibtext = null;
		

	JTextField maxreptext = null;
	JTextField settext = null;
	
	FileDialog localFd = null;

	String []versioncontents = {"v1","v2c","v3"};
	String []seccontents = {"noAuth,NoPriv","Auth,NoPriv","Auth,Priv"};
	String []authprotocolcontents = {"MD5","SHA"};
	String []operationcontents = {"GET","GETNEXT","GET_BULK","TABLE","SET"};
	

	int VERSION1 = 0;
	int VERSION2C = 1;
	int VERSION3 = 3;

	int GET = 0;
	int GETNEXT = 1;
	int SET = 2;
	int GET_BULK = 3;
	int TABLE = 4;

	int version = VERSION1;

	int operation = GET; 
	String authpassword = "";
	String privpassword = "";
	int authprotocol = 21;
	com.adventnet.snmp.corba.SnmpTarget target = null;
	com.adventnet.snmp.corba.SnmpFactory f = null;
	org.omg.CORBA.ORB orb = null;

	boolean loading = false;
	
	public CorbaDemo(String args[]) {
		this.args = args;

		JPanel panel = new JPanel();
		setPanel(panel);
		GridBagLayout l = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		getContentPane().setLayout(l);
		c.fill = GridBagConstraints.BOTH;
		c.gridwidth = 1;
		c.weightx = 1;
		c.weighty = 1;
		c.gridwidth = 0;
		l.setConstraints(panel,c);
		getContentPane().add(panel);
		setVisible(true);
		setSize(700,450);

		try{
		resulttext.setText("Initializing the ORB");
		orb = org.omg.CORBA.ORB.init(args,null);
		org.omg.CORBA.Object obj = orb.resolve_initial_references("NameService");
		resulttext.setText("Resolved initial references");
		NamingContext ncRef = NamingContextHelper.narrow(obj);
		NameComponent nc = new NameComponent( "AdventnetSnmpFactory","");
		NameComponent [] path = {nc};
		
		f = com.adventnet.snmp.corba.SnmpFactoryHelper.narrow( ncRef.resolve(path));
		resulttext.setText("Got the SnmpFacotry");

		//com.adventnet.snmp.corba.SnmpTarget target = f.createTarget();
		target = f.createTarget();
		resulttext.setText("Got the SnmpTarget");
		
		resultBut.setActionCommand("Result");
		versionChoice.setActionCommand("Version");
		operationChoice.setActionCommand("Operation");
		secChoice.setActionCommand("SecLevel");
		loadBut.setActionCommand("Load"); 
		browseBut.setActionCommand("Browse");
		authprotocolChoice.setActionCommand("AuthProtocol");
		resultBut.addActionListener(this);
		versionChoice.addItemListener(this);
		operationChoice.addItemListener(this);
		secChoice.addItemListener(this);
		loadBut.addActionListener(this); 
		browseBut.addActionListener(this);
		authprotocolChoice.addItemListener(this);
		} catch(Exception e) {
			resulttext.setText("Exception:"+e.toString());
		}
		

	}

	String args[] = null;
	public static void main(String args [] ) {

		new CorbaDemo(args);
	}

	private void setPanel(JPanel panel) {

		JPanel p0 = new JPanel();
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		JPanel p3 = new JPanel();
		JPanel p4 = new JPanel();
		JPanel p5 = new JPanel();
		GridBagLayout l = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		GridBagLayout l0 = new GridBagLayout();
		GridBagConstraints c0 = new GridBagConstraints();
		GridBagLayout l1 = new GridBagLayout();
		GridBagConstraints c1 = new GridBagConstraints();
		GridBagLayout l2 = new GridBagLayout();
		GridBagConstraints c2 = new GridBagConstraints();
		GridBagLayout l3 = new GridBagLayout();
		GridBagConstraints c3 = new GridBagConstraints();
		GridBagLayout l4 = new GridBagLayout();
		GridBagConstraints c4 = new GridBagConstraints();
		GridBagLayout l5 = new GridBagLayout();
		GridBagConstraints c5 = new GridBagConstraints();
		panel.setLayout(l);
		p0.setLayout(l0);
		p1.setLayout(l1);
		p2.setLayout(l2);
		p3.setLayout(l3);
		p4.setLayout(l4);
		p4.setLayout(l5);
		JLabel hostlabel = new JLabel("Host");
		hosttext = new JTextField("localhost");
		c1.gridwidth = 1;
		c1.weightx = 1;
		c1.gridx = 0;
		c1.fill = GridBagConstraints.HORIZONTAL;
		l1.setConstraints(hostlabel,c1);
		c1.gridx = 1;
		c1.insets = new Insets(0,0,0,50);
		l1.setConstraints(hosttext,c1);
		JLabel communitylabel = new JLabel("Community");
		communitytext = new JTextField("public");
		c1.gridx = 2;
		c1.weightx = 0;
		c1.insets = new Insets(0,0,0,0);
		l1.setConstraints(communitylabel,c1);
		c1.gridx = 3;
		c1.weightx = 1;
		l1.setConstraints(communitytext,c1);
		JLabel portlabel = new JLabel("Port");
		porttext = new JTextField("161");
		c1.weightx = 0;
		c1.gridx = 4;
		c1.insets = new Insets(0,0,0,0);
		l1.setConstraints(portlabel,c1);
		c1.insets = new Insets(0,0,0,0);
		c1.weightx = 1;
		c1.gridx = 5;
		l1.setConstraints(porttext,c1);
	
		p1.add(hostlabel);
		p1.add(hosttext);
		p1.add(communitylabel);
		p1.add(communitytext);
		p1.add(portlabel);
		p1.add(porttext);
		
		c1.gridy = 1;
		JLabel oidlabel = new JLabel("Object ID");
		oidtext = new JTextField("tcpConnTable",20);
		c1.gridx = 0;
		c1.weightx = 0;
		l1.setConstraints(oidlabel,c1);
		c1.gridx = 1;
		c1.weightx = 1;
		c1.insets = new Insets(0,0,0,0);
		l1.setConstraints(oidtext,c1);
		JLabel wcommunitylabel = new JLabel("WriteComm");
		wcommunitytext = new JTextField();
		c1.gridx = 2;
		c1.weightx = 0;
		c1.insets = new Insets(0,0,0,0);
		l1.setConstraints(wcommunitylabel,c1);
		c1.gridx = 3;
		c1.weightx = 1;
		l1.setConstraints(wcommunitytext,c1);
		JLabel versionlabel = new JLabel("Version");
		versionChoice = new JComboBox();
		c1.gridx = 4;
		c1.weightx = 0;
		c1.insets = new Insets(0,0,0,0);
		l1.setConstraints(versionlabel,c1);
		c1.insets = new Insets(0,0,0,0);
		c1.gridx = 5;
		c1.weightx = 1;
		l1.setConstraints(versionChoice,c1);
	
		p1.add(oidlabel);
		p1.add(oidtext);
		p1.add(wcommunitylabel);
		p1.add(wcommunitytext);
		p1.add(versionlabel);
		p1.add(versionChoice);


		loadBut = new JButton("load mibs");
		browseBut = new JButton("browse mibfile");
		mibtext = new JTextField("mibs/rfc1213-MIB");
		c2.gridwidth = 1;
		c2.fill = GridBagConstraints.NONE;
		c2.weightx = 0;
		c2.gridx = 0;	
		c2.insets = new Insets(0,0,0,10);
		l2.setConstraints(browseBut,c2);
		c2.gridx = 1;	
		c2.weightx = 1;
		c2.insets = new Insets(0,0,0,0);
		c2.fill = GridBagConstraints.HORIZONTAL;
		l2.setConstraints(mibtext,c2);
		c2.gridx = 2;	
		c2.weightx = 0;
		c2.fill = GridBagConstraints.NONE;
		c2.insets = new Insets(0,10,0,10);
		l2.setConstraints(loadBut,c2);
		
		JLabel usernamelabel = new JLabel("UserName");
		usernametext = new JTextField();
		c2.gridx = 3;	
		c2.insets = new Insets(0,0,0,0);
		l2.setConstraints(usernamelabel,c2);
		c2.fill = GridBagConstraints.HORIZONTAL;
		c2.gridx = 4;	
		c2.weightx = 1;
		l2.setConstraints(usernametext,c2);
		p2.add(browseBut);
		p2.add(mibtext);
		p2.add(loadBut);
		p2.add(usernamelabel);
		p2.add(usernametext);

		
		JLabel contextidlabel = new JLabel("Context ID");
		contextidtext = new JTextField();
		JLabel contextnamelabel = new JLabel("Context Name");
		contextnametext = new JTextField();
		JLabel seclabel = new JLabel("Security Level");
		secChoice = new JComboBox();
		JLabel authprotocollabel = new JLabel("AuthProtocol");
		authprotocolChoice = new JComboBox();
		JLabel authpasswordlabel = new JLabel("Auth Password");
		authpasswordtext = new JPasswordField();
		JLabel privpasswordlabel = new JLabel("Priv Password");
		privpasswordtext = new JPasswordField();

		c3.gridwidth = 1;
		c3.fill = GridBagConstraints.HORIZONTAL;
		c3.weightx = 1;
		c3.gridx = 0;
		l3.setConstraints(contextidlabel,c3);
		c3.gridx = 1;
		l3.setConstraints(contextidtext,c3);
		c3.gridx = 2;
		l3.setConstraints(contextnamelabel,c3);
		c3.gridx = 3;
		l3.setConstraints(contextnametext,c3);
		
		c3.gridy = 1;
		c3.gridx = 0;
		l3.setConstraints(seclabel,c3);
		c3.gridx = 1;
		l3.setConstraints(secChoice,c3);
		c3.gridx = 2;
		l3.setConstraints(authprotocollabel,c3);
		c3.gridx = 3;
		l3.setConstraints(authprotocolChoice,c3);
		
		c3.gridy = 2;
		c3.gridx = 0;
		l3.setConstraints(authpasswordlabel,c3);
		c3.gridx = 1;
		l3.setConstraints(authpasswordtext,c3);
		c3.gridx = 2;
		l3.setConstraints(privpasswordlabel,c3);
		c3.gridx = 3;
		l3.setConstraints(privpasswordtext,c3);

		p3.add(contextidlabel);
		p3.add(contextidtext);
		p3.add(contextnamelabel);
		p3.add(contextnametext);
		p3.add(seclabel);
		p3.add(secChoice);
		p3.add(authprotocollabel);
		p3.add(authprotocolChoice);
		p3.add(authpasswordlabel);
		p3.add(authpasswordtext);
		p3.add(privpasswordlabel);
		p3.add(privpasswordtext);

		JLabel maxreplabel = new JLabel("Max-Repeatitions");
		maxreptext = new JTextField("50",3);
		JLabel setlabel = new JLabel("Set Value");
		settext = new JTextField(10);
		settext.setEditable(false);
		resultBut = new JButton("Click to GET");
		operationChoice = new JComboBox();
		c4.gridwidth = 1;
		c4.weightx = 1;
		c4.gridy = 0;
		c4.fill = GridBagConstraints.HORIZONTAL;
		l.setConstraints(maxreplabel,c4);
		c4.gridx = 1;
		l.setConstraints(maxreptext,c4);
		c4.gridx = 2;
		c4.insets = new Insets(0,3,0,0);
		l.setConstraints(setlabel,c4);
		c4.gridx = 3;
		c4.insets = new Insets(0,0,0,4);
		l.setConstraints(settext,c4);
		c4.gridx = 4;
		l.setConstraints(operationChoice,c4);
		c4.gridx = 5;
		l.setConstraints(maxreplabel,c4);
		p4.add(maxreplabel);
		p4.add(maxreptext);
		p4.add(setlabel);
		p4.add(settext);
		p4.add(operationChoice);
		p4.add(resultBut);
		
			
		c.gridy = 0;
		c.gridwidth = 0;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.weighty = 1;
		c.insets = new Insets(0,0,20,0);
		l.setConstraints(p0,c);
		c.gridy = 1;
		c.insets = new Insets(0,0,20,0);
		l.setConstraints(p1,c);
		c.gridy = 2;
		c.insets = new Insets(0,0,20,0);
		l.setConstraints(p2,c);
		c.gridy = 3;
		c.insets = new Insets(0,0,20,0);
		l.setConstraints(p3,c);
		c.gridy = 4;
		c.insets = new Insets(0,0,20,0);
		l.setConstraints(p4,c);
		c.gridy = 5;
		c.insets = new Insets(0,0,0,0);
		l.setConstraints(p5,c);
		panel.add(p0);
		panel.add(p1);
		panel.add(p2);
		panel.add(p3);
		panel.add(p4);
		panel.add(p5);
		
		JLabel title = new JLabel("Demo for Corba Support in AdventNetSnmp API");
		title.setOpaque(true);
		title.setBackground(Color.pink);
		p0.add(title);
		
		c5.fill = GridBagConstraints.HORIZONTAL;	
		c5.gridwidth = 1;
		c5.weightx = 1;
		resulttext = new JTextArea(1,50);
		resulttext.setEditable(false);
		JScrollPane pan = new JScrollPane(resulttext);
		resulttext.setLineWrap(true);
		l5.setConstraints(resulttext,c5);
		p5.add(pan);
		panel.setBorder(BorderFactory.createTitledBorder( new EtchedBorder(Color.white, Color.gray), "")); 
		p5.setBorder(BorderFactory.createTitledBorder( new EtchedBorder(Color.white, Color.gray), "")); 

		setComboBoxModel(versionChoice,versioncontents);
		setComboBoxModel(secChoice,seccontents);
		setComboBoxModel(authprotocolChoice,authprotocolcontents);
		setComboBoxModel(operationChoice,operationcontents);

		contextidtext.setEnabled(false);
		contextnametext.setEnabled(false);
		usernametext.setEnabled(false);
		secChoice.setEnabled(false);
		authprotocolChoice.setEnabled(false);
		authpasswordtext.setEnabled(false);
		privpasswordtext.setEnabled(false);
	}

	private void setComboBoxModel(JComboBox c,String [] str) {
		for(int i=0;i<str.length;i++)
			c.addItem(str[i]);
	}
	public void actionPerformed(ActionEvent e) {	
		if(e.getActionCommand().equals("Result")){

			resulttext.setText("");
			if (hosttext.getText().equals("")) {
				resulttext.setText("Host Field is empty");
				return;
			}
			else
				if( operation == GET || operation == GETNEXT || operation == GET_BULK || operation == SET)
				target.setTargetHost(hosttext.getText());
			if (oidtext.getText().equals("")) {
				resulttext.setText("Object ID Field is empty");
				return;
			}
			else
				if( operation == GET || operation == GETNEXT || operation == GET_BULK || operation == SET)
				target.setObjectID(oidtext.getText());
				if( operation == GET || operation == GETNEXT || operation == GET_BULK || operation == SET)
			target.setCommunity(communitytext.getText());
			try{
				if (!porttext.getText().equals(""))
				if( operation == GET || operation == GETNEXT || operation == GET_BULK || operation == SET)
					target.setTargetPort(Integer.parseInt(porttext.getText()));
			} catch(NumberFormatException nfe) {
				resulttext.setText("Numberformat Exception :"+nfe.toString());
				return;
			}

				if( operation == GET || operation == GETNEXT || operation == GET_BULK || operation == SET)
			{
			if (versionChoice.getSelectedItem().toString().equals("v1"))
				target.setSnmpVersion(0);
			else if (versionChoice.getSelectedItem().toString().equals("v2c"))
				target.setSnmpVersion(1);
			else if (versionChoice.getSelectedItem().toString().equals("v3")) {
				target.setSnmpVersion(3);
				target.setContextName(contextnametext.getText());
				target.setContextID(contextidtext.getText());
				if (usernametext.getText().equals("")) {
					resulttext.setText("UserName Field is empty");
					return;
				}
				else
					target.setPrincipal(usernametext.getText());
				target.setAuthProtocol(authprotocol);
				target.setAuthPassword(authpasswordtext.getText());
				target.setPrivPassword(privpasswordtext.getText());
			}
			}
			
			if ( operation == GET) {
				String result = target.snmpGet();
				if (result != null)
					resulttext.setText(result);
				else {
					resulttext.setText("Response is null");
					return;
				}
			}
			else if ( operation == GETNEXT) {
				String result = target.snmpGetNext();
				if (result != null)
					resulttext.setText(result);
				else {
					resulttext.setText("Response is null");
					return;
				}
			}
			else if ( operation == TABLE) {
				try{
					com.adventnet.snmp.corba.SnmpTable corbatable = f.createTable();
				CorbaTable t = new CorbaTable(orb,corbatable,args,versionChoice.getSelectedItem().toString(),hosttext.getText(),communitytext.getText(),porttext.getText(),null,null,usernametext.getText(),authprotocolChoice.getSelectedItem().toString(),authpasswordtext.getText(),privpasswordtext.getText(),contextidtext.getText(),contextnametext.getText(),mibtext.getText(),oidtext.getText());
				}catch(Exception eee){
					System.out.println("Error in creating table "+eee.toString());
				}
			}
			else if ( operation == SET) {
				try {
					if (!wcommunitytext.getText().equals("")) 
						target.setWriteCommunity(wcommunitytext.getText());
					String result = target.snmpSet(settext.getText());
					if( result != null)
						resulttext.setText(result);
					else {
						resulttext.setText("Response is null");
						return;
					}
				}catch(Exception se) {
					resulttext.setText("Exception in setting :"+se.toString());
				}
			}
			else if ( operation == GET_BULK && version > VERSION1) {
				try{
					if (maxreptext.getText().equals("")) {
						resulttext.setText("Max-Rep Field is empty");
						return;
					}
					else
						target.setMaxRepetitions(Integer.parseInt(maxreptext.getText()));
				} catch(NumberFormatException nfe) {
					resulttext.setText("Numberformat Exception :"+nfe.toString());
				}
				String[][] resultstr =  target.snmpGetBulkList();
				resulttext.setText("");
				if (resultstr == null)
				{
					resulttext.setText("Response is null");
					return;
				}
				for(int i=0;i<resultstr[0].length;i++)
					resulttext.append(resultstr[0][i]+"\n");
			}
		}
		else if(e.getActionCommand().equals("Load")){
			try{
			target.loadMibs(mibtext.getText());
			}catch(Exception ee) {
				resulttext.setText("Exception in loading Mibs : "+ee.toString());
                ee.printStackTrace();
			}
		}
		else if(e.getActionCommand().equals("Browse")){
			if (loading) return;
			loading = true;
			if(localFd == null) {
				JFrame frm =(JFrame)SwingUtilities.getAncestorOfClass(java.awt.Frame.class,browseBut);  
				localFd = new FileDialog(frm, "Config Load Dialog", FileDialog.LOAD);
				localFd.setSize(700,300);
				//Utils.centerWindow(localFd);
			}
			localFd.show();
			String dir = localFd.getDirectory();
			String file = localFd.getFile();
			if((dir != null)&&(file != null))
			{
				String mibname = dir + file;
				mibname = mibname.trim();
				if(mibname.indexOf(" ")>=0) {
					mibname = "\""+mibname+"\"";
				}
				mibtext.setText(mibname);
			}              
			loading = false;
		}
	}


	public void itemStateChanged(ItemEvent e) {	
		if(((JComboBox)e.getSource()).getActionCommand().equals("Version")){
			contextidtext.setEnabled(false);
			contextnametext.setEnabled(false);
			usernametext.setEnabled(false);
			secChoice.setEnabled(false);
			authprotocolChoice.setEnabled(false);
			authpasswordtext.setEnabled(false);
			privpasswordtext.setEnabled(false);
			communitytext.setEnabled(true);
			if (e.getItem().toString().equals("v1")){
				version = VERSION1;
			}
			else if (e.getItem().toString().equals("v2c")){
				version = VERSION2C;
			}
			else if (e.getItem().toString().equals("v3")) {
				version = VERSION3;
				contextidtext.setEnabled(true);
				contextnametext.setEnabled(true);
				usernametext.setEnabled(true);
				secChoice.setEnabled(true);
				secChoice.setSelectedItem("noAuth,NoPriv");
				communitytext.setEnabled(false);
			}
		}

		else if(((JComboBox)e.getSource()).getActionCommand().equals("SecLevel")){
			authprotocolChoice.setEnabled(false);
			authpasswordtext.setEnabled(false);
			privpasswordtext.setEnabled(false);
			if (e.getItem().toString().equals("Auth,NoPriv")) {
				authprotocolChoice.setEnabled(true);
				authpasswordtext.setEnabled(true);
				privpasswordtext.setText("");
			}
			else if (e.getItem().toString().equals("Auth,Priv")) {
				privpasswordtext.setEnabled(true);
				authprotocolChoice.setEnabled(true);
				authpasswordtext.setEnabled(true);
			}
			else {
				authpasswordtext.setText("");
				privpasswordtext.setText("");
			}
		}
		else if(((JComboBox)e.getSource()).getActionCommand().equals("AuthProtocol")){
			if (e.getItem().toString().equals("MD5"))
				authprotocol = 21;
			else if (e.getItem().toString().equals("SHA"))
				authprotocol = 22;
		}

		else if(((JComboBox)e.getSource()).getActionCommand().equals("Operation")){
			
			settext.setEditable(false);
			resultBut.setText("Click to "+e.getItem().toString());
			
			if (e.getItem().toString().equals("GET"))
				operation = GET;
			else if (e.getItem().toString().equals("GETNEXT"))
				operation = GETNEXT;
			else if (e.getItem().toString().equals("GET_BULK"))
				operation = GET_BULK;
			else if (e.getItem().toString().equals("TABLE"))
				operation = TABLE;
			else if (e.getItem().toString().equals("SET")){
				operation = SET;
				settext.setEditable(true);
			}
		}
	}
}
