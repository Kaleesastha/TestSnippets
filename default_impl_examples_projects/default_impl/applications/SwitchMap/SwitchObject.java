/* $Id: SwitchObject.java,v 1.2 2010/10/29 13:45:39 swaminathap Exp $ */

package test;

import java.util.Properties;

import com.adventnet.nms.util.NmsLogMgr;
import com.adventnet.management.log.Log;

import com.adventnet.nms.topodb.ContainerInterface;
import com.adventnet.nms.topodb.SnmpNode;

public class SwitchObject extends SnmpNode implements ContainerInterface {
    
    //Constructor
    public SwitchObject () {
        setType("Switch");
        setClassname("SwitchObject");
        setBaseMibs("BRIDGE-MIB");
    }

    private int rootPort = -1;

    public void setRootPort( int root ) {
        this.rootPort = root;
    }

    public int getRootPort() {
        return rootPort;
    }
    
    private int rootCost = -1;

    public void setRootCost( int cost ) {
        this.rootCost = cost;
    }

    public int getRootCost() {
        return rootCost;
    }
    
    private int numPorts = 0;

    public void setNumPorts( int ports ) {
        this.numPorts = ports;
    }

    public int getNumPorts() {
        return numPorts;
    }

    public Properties getProperties() {
        
        Properties p = super.getProperties();       	

        p.setProperty("rootPort", String.valueOf( rootPort ));
        p.setProperty("rootCost", String.valueOf( rootCost ));
        p.setProperty("numPorts", String.valueOf( numPorts ));

	return p;
    }

    public void setProperties ( Properties p ) {

        if ( p == null ) {
            NmsLogMgr.TOPOUSER.log("Error while setting Switch properties: Properties object is null", Log.INTERMEDIATE_DETAIL);
            return;
        }
        
        Properties prop = p;
        String s;	
        
        if ((s = p.getProperty("rootPort")) != null) {
            try {
                rootPort = Integer.parseInt( s );
            } catch ( NumberFormatException nfe ) {
                NmsLogMgr.TOPOUSER.log("Invalid value specification for rootPort while setting Switch properties." + nfe, Log.INTERMEDIATE_DETAIL);
                return;
            }
            prop.remove("rootPort");
        }

        if ((s = p.getProperty("rootCost")) != null) {
            try {
                rootCost = Integer.parseInt( s );
            } catch ( NumberFormatException nfe ) {
                NmsLogMgr.TOPOUSER.log("Invalid value specification for rootCost while setting Switch properties." + nfe, Log.INTERMEDIATE_DETAIL);
                return;
            }
            prop.remove("rootCost");
        }

        if ((s = p.getProperty("numPorts")) != null) {
            try {
                numPorts = Integer.parseInt( s );
            } catch ( NumberFormatException nfe ) {
                NmsLogMgr.TOPOUSER.log("Invalid value specification for numPorts while setting Switch properties." + nfe, Log.INTERMEDIATE_DETAIL);
                return;
            }
            prop.remove("numPorts");
        }

        super.setProperties(prop);
        
        return;
    }
}
