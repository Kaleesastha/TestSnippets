//$Id: CorbaTable.java,v 1.2 2006/12/12 05:49:39 jegannathan Exp $
import org.omg.CosNaming.*;
import java.lang.*;
import java.util.*;
import java.net.*;
import org.omg.CORBA.*;
import com.adventnet.snmp.corba.*;
// import ParseOptions; // Build Compilation Jegan
import com.adventnet.snmp.mibs.*;
import com.adventnet.snmp.snmp2.*;
import javax.swing.*;
import javax.swing.table.*;

public class CorbaTable {

    SnmpTable table;
    org.omg.CORBA.ORB orb = null;

    public CorbaTable(org.omg.CORBA.ORB o,com.adventnet.snmp.corba.SnmpTable tab,String args[],String version,String host,String community,String port,String retries,String timeout,String username,String authProtocol,
String  authPassword ,String privPassword,String contextID,String contextName,String mibs ,String tableoid
		){
        try
        {
			if(table==null || orb == null){
    		orb = org.omg.CORBA.ORB.init( args, null );
            org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init( args, null );
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService" ); 
            NamingContext ncRef = NamingContextHelper.narrow( objRef ); 
            NameComponent nc = new NameComponent( "AdventnetSnmpFactory" , "" );
            NameComponent[] path = { nc };
            
            com.adventnet.snmp.corba.SnmpFactory factory = com.adventnet.snmp.corba.SnmpFactoryHelper.narrow( ncRef.resolve( path ) );
            table = factory.createTable();
			}
			else {
			table = tab;
			orb = o;
			}
            if(version != null)
            {  // if SNMP version is specified, set it
                if(version.equals("v1"))
                    table.setSnmpVersion( com.adventnet.snmp.beans.SnmpTarget.VERSION1 ) ;
                else if(version.equals("v2c"))
                    table.setSnmpVersion( com.adventnet.snmp.beans.SnmpTarget.VERSION2C );            
                else if(version.equals("v3"))
                    table.setSnmpVersion( com.adventnet.snmp.beans.SnmpTarget.VERSION3 );
                else
                {
                    System.out.println("Invalid Version Number");
                    System.exit(1);
                }
            }
            else
                table.setSnmpVersion( com.adventnet.snmp.beans.SnmpTarget.VERSION1 );
    
            table.setTargetHost(host );  // set the agent hostname
			table.setPollInterval(10);

            if (community != null) // set the community if specified
                table.setCommunity( community );

            // set the timeout/retries/port parameters, if specified
            if (port != null)
                table.setTargetPort( Integer.parseInt(port) );
            if (retries != null)
                table.setRetries( Integer.parseInt(retries) );
            if (timeout != null)
                table.setTimeout( Integer.parseInt(timeout) );    
            
            
            if(table.getSnmpVersion() == com.adventnet.snmp.beans.SnmpTarget.VERSION3) 
            {
                table.setPrincipal(username);
                table.setAuthPassword(authPassword);
                table.setPrivPassword(privPassword);
                table.setContextID(contextID);
                table.setContextName(contextName);
                if(authProtocol.equals("SHA"))
                    table.setAuthProtocol(com.adventnet.snmp.beans.SnmpTarget.SHA_AUTH);
                else
                    table.setAuthProtocol(com.adventnet.snmp.beans.SnmpTarget.MD5_AUTH);
            }
            // instantiate this class to receive table events, and register it
            com.adventnet.snmp.corba.SnmpTableListener listener = new CorbaTableDemoListenerImpl( table );
            table.addSnmpTableListener(listener);
            orb.connect( listener );
            if (mibs != null) 
			{
            table.loadMibs(mibs);
			}

            // Set the table OID in SnmpTable.  Once specified,
            // SnmpTable will automatically get the data and send table events 
            try { 
                table.setTableOID( tableoid );
            } catch (Exception ex) {
                System.err.println("Invalid table OID: "+ex);
				ex.printStackTrace();
                System.exit(1);
            }

            com.adventnet.snmp.corba.SnmpTableListener listener1 = new CorbaTableDemoListenerImpl( table );
			TableModel model = (TableModel)listener;
			JTable t = new JTable(model);
			/*if( t.getColumnCount() > 5)
			t.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);*/
			JScrollPane pan = new JScrollPane(t);
			JFrame f = new JFrame();
			f.getContentPane().add(pan);			
			f.setSize(600,400);
		    t.revalidate();
		    t.repaint();
		    pan.updateUI();
            f.setVisible(true);
    } catch (Exception ex) {
        System.err.println("Failed: "+ ex);
        ex.printStackTrace();
    }
    }
}
