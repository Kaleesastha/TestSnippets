// $Id: PortObject.java,v 1.3 2010/10/29 13:45:39 swaminathap Exp $ 

package test;

import java.util.Properties;

import com.adventnet.nms.util.NmsLogMgr;
import com.adventnet.management.log.Log;

import com.adventnet.nms.topodb.ManagedObject;

public class PortObject extends ManagedObject {

    public PortObject() {
        setType("Port");
        setClassname("PortObject");
    }
    
    private int portIfIndex = -1;
    
    public void setPortIfIndex( int index ) {
        this.portIfIndex = index;
    }

    public int getPortIfIndex() {
        return portIfIndex;
    }

    private String portIfDescr = "";

    public void setPortIfDescr( String value ) {
        this.portIfDescr = value;
    }

    public String getPortIfDescr() {
        return portIfDescr;
    }

    private String portState = "";

    public void setPortState( String state ) {
        this.portState = state;
    }

    public String getPortState() {
        return portState;
    }

    private int portIfSpeed = -1;

    public void setPortIfSpeed( int speed ) {
        this.portIfSpeed = speed;
    }

    public int getPortIfSpeed() {
        return portIfSpeed;
    }

    public Properties getProperties() {
        
        Properties p = super.getProperties();       	

        p.setProperty("portIfIndex", String.valueOf( portIfIndex ));
        if(portIfDescr!=null)
		{
			p.setProperty("portIfDescr",  portIfDescr);
		}
		else
		{
			p.setProperty("portIfDescr",  "NULL");
		}
		if(portState!=null)
		{
        p.setProperty("portState",  portState);
		}
		else
		{
        	p.setProperty("portState",  "NULL");
		}
        p.setProperty("portIfSpeed", String.valueOf( portIfSpeed ));

	return p;
    }

    public void setProperties ( Properties p ) {

        if ( p == null ) {
            NmsLogMgr.TOPOUSER.log("Error while setting Port properties: Properties object is null", Log.INTERMEDIATE_DETAIL);
            return;
        }
        
        Properties prop = p;
        String s;	

        if ((s = p.getProperty("portIfIndex")) != null) {
            try {
                portIfIndex = Integer.parseInt( s );
            } catch ( NumberFormatException nfe ) {
                NmsLogMgr.TOPOUSER.log("Invalid value specification for portIfIndex while setting Port properties." + nfe, Log.INTERMEDIATE_DETAIL);
                return;
            }
            prop.remove("portIfIndex");
        }

        if ((s = p.getProperty("portIfDescr")) != null) {
            portIfDescr = s;
            prop.remove("portIfDescr");
        }
        
        if ((s = p.getProperty("portState")) != null) {
            portState = s;
            prop.remove("portState");
        }

        if ((s = p.getProperty("portIfSpeed")) != null) {
            try {
                portIfSpeed = Integer.parseInt( s );
            } catch ( NumberFormatException nfe ) {
                NmsLogMgr.TOPOUSER.log("Invalid value specification for portIfSpeed while setting Port properties." + nfe, Log.INTERMEDIATE_DETAIL);
                return;
            }
            prop.remove("portIfSpeed");
        }

        super.setProperties(prop);
        
        return;
    }
}
