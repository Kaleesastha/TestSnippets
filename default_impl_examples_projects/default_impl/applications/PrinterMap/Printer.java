// $Id: Printer.java,v 1.3 2010/10/29 13:45:39 swaminathap Exp $ 

/**
 * Printer.java 
 */

package test;

import com.adventnet.nms.topodb.SnmpNode;

import java.util.Properties;

import com.adventnet.nms.util.NmsLogMgr;
import com.adventnet.management.log.Log;

public class Printer extends SnmpNode
{

    private int printerStatus;
    
    /**
     * Get the value of printerStatus.
     * @return value of printerStatus.
     */
    public int getPrinterStatus() 
    {
        return printerStatus;
    }
    
    /**
     * Set the value of printerStatus.
     * @param v  Value to assign to printerStatus.
     */
    public void setPrinterStatus(int  v) 
    {
        this.printerStatus = v;
    }
    
    private int deviceStatus;
    
    /**
     * Get the value of deviceStatus.
     * @return value of deviceStatus.
     */
    public int getDeviceStatus() 
    {
        return deviceStatus;
    }
    
    /**
     * Set the value of deviceStatus.
     * @param v  Value to assign to deviceStatus.
     */
    public void setDeviceStatus(int  v) 
    {
        this.deviceStatus = v;
    }
       
    private int printerDetectedErrStatus;

    
    /**
     * Get the value of printerDetectedErrStatus.
     * @return value of printerDetectedErrStatus.
     */
    public int getPrinterDetectedErrStatus() 
    {
        return printerDetectedErrStatus;
    }
    
    /**
     * Set the value of printerDetectedErrStatus.
     * @param v  Value to assign to printerDetectedErrStatus.
     */

    public void setPrinterDetectedErrStatus(int v) 
    {
        this.printerDetectedErrStatus = v;
    }

    private String consoleDispBufferText;
    
    /**
     * Get the value of consoleDispBufferText.
     * @return value of consoleDispBufferText.
     */
    public String getConsoleDispBufferText() 
    {
        return consoleDispBufferText;
    }
    
    /**
     * Set the value of consoleDispBufferText.
     * @param v  Value to assign to consoleDispBufferText.
     */
    public void setConsoleDispBufferText(String  v) 
    {
        this.consoleDispBufferText = v;
    }
    
    private String consoleLightString;

    /**
     * Get the value of consoleLightString.
     * @return value of consoleLightString.
     */
    public String getConsoleLightString()
    {
        return consoleLightString;
    }

    /**
     * Set the value of consoleLightString.
     * @param v  Value to assign to consoleLightString.
     */
    public void setConsoleLightString(String  v)
    {
        this.consoleLightString = v;
    }


    /**
     * Returns the status of the printer in the first 8 bit of the integer.
     *
     * @return an <code>int</code> value
     */
    public int getStatusInt()
    {
        int bitInt = 0;
          
        if(deviceStatus==2 || deviceStatus == 3) {
            if(printerStatus==4)//Printing
                bitInt |= 2;
            if(printerStatus==3)//Ready
                bitInt |= 1;
        }
        else if (printerDetectedErrStatus!=-1) {
            if(isBitOn(printerDetectedErrStatus, 7)||//Paper Low
               isBitOn(printerDetectedErrStatus, 6))//Paper Empty
                bitInt |= 32;
            if(isBitOn(printerDetectedErrStatus, 5)||//Toner Low
               isBitOn(printerDetectedErrStatus, 4))//Toner Empty
                bitInt |= 16;
            if(isBitOn(printerDetectedErrStatus,3))//Door Open
                bitInt |= 8;
            if(isBitOn(printerDetectedErrStatus, 2))//Jam
                bitInt |= 4;
            if(isBitOn(printerDetectedErrStatus, 1))//Off Line
                bitInt |= 64;
            if(isBitOn(printerDetectedErrStatus, 0))//Service Call
                bitInt |= 128;
        }
        return bitInt;
    }

    /**
     * Get the properties of the Printer
     *
     */
    public Properties getProperties()
    {
        Properties prop = super.getProperties();

        prop.setProperty("printerStatus",String.valueOf(printerStatus));//No I18N
        prop.setProperty("deviceStatus",String.valueOf(deviceStatus));//No I18N
        prop.setProperty("printerDetectedErrStatus",String.valueOf(printerDetectedErrStatus));//No I18N

        if(consoleDispBufferText != null)
        {
            prop.setProperty("consoleDispBufferText",consoleDispBufferText);//No I18N
        }
		if(consoleLightString != null)
		{
			prop.setProperty("consoleLightString",consoleLightString);//No I18N
		}
        return prop;
    }

    /**
     * sets the properties of the printer
     *
     * @param prop a <code>Properties</code> value
     */
    public void setProperties(Properties prop)
    {
        if(prop == null)
        {
            NmsLogMgr.MISCERR.log("Error :: Properties null",Log.SUMMARY);
            return;
        }

        Properties p = prop;
        String value; int val;

        if((value = prop.getProperty("deviceStatus")) != null)
        {
            try{
                deviceStatus = Integer.parseInt(value);
            }catch(NumberFormatException nfe)
            {
                NmsLogMgr.TOPOUSER.log("incorrect NumberFormat while setting deviceStatus in setProperties" + nfe, Log.SUMMARY);
                return;
            }
            p.remove("deviceStatus");
        }

        if((value = prop.getProperty("printerStatus")) != null)
        {
            try {
                printerStatus = Integer.parseInt(value);
            }catch(NumberFormatException nfe)
            {
                NmsLogMgr.TOPOUSER.log("incorrect NumberFormat while setting printerStatus in setProperties" + nfe, Log.SUMMARY);
                return;
            }
            p.remove("printerStatus");
        }
            
        if((value = prop.getProperty("printerDetectedErrStatus")) != null)
        {
            try {
                printerDetectedErrStatus = Integer.parseInt(value);
            }catch(NumberFormatException nfe)
            {
                NmsLogMgr.TOPOUSER.log("incorrect NumberFormat while setting printerDetectedErrStatus in setProperties" + nfe, Log.SUMMARY);
                return;
            }
            p.remove("printerDetectedErrStatus");
        }

        if((value = prop.getProperty("consoleDispBufferText")) != null)
        {
            consoleDispBufferText = value;
            p.remove("consoleDispBufferText");
        }

        if((value = prop.getProperty("consoleLightString")) != null)
        {
            consoleLightString = value;
            p.remove("consoleLightString");//NO I18N
        }

        super.setProperties(p);
        return;
    }
    
    public Printer() {
        setType("Printer");
        setClassname("Printer");
        setBaseMibs("Printer-MIB");
        setPollInterval(30);
        setTester("usertest");
        setUClass("test.PrinterStatusPoller");
    }

    public static boolean isBitOn(int valueInt, int bitNum)
    {
        if(bitNum<0) return false;
        int enumvalue=1<<bitNum;
        
        int result=(valueInt & enumvalue);
        if(result==enumvalue) return true;
        else return false;
    }

}

