//$Id: ConfigureParams.java,v 1.2 2007/02/22 15:02:57 srajeswari Exp $

package com.adventnet.nms.example.config;
//Snmp Imports
import com.adventnet.snmp.mibs.MibOperations;
import com.adventnet.snmp.mibs.LeafSyntax;
import com.adventnet.snmp.mibs.MibNode;
import com.adventnet.snmp.mibs.MibException;
import com.adventnet.snmp.snmp2.SnmpOID;
import com.adventnet.snmp.snmp2.SnmpAPI;
import com.adventnet.management.config.snmp.SnmpDevice;

//Java imports
import java.util.Vector;
import java.util.Properties;
import java.util.Enumeration;
import java.util.StringTokenizer;
import java.util.Hashtable;

class ConfigureParams
{
    ConfigParserOptions commandParser;
    String[] oid;
    String[] label;
    String[] type;
    String[] value;
    byte[] contype;
    SnmpDevice[] deviceObject;
    String taskName;
    int port;
    // Default value for the ConfigServer host.
    String[] valuesforConfigSet={"false","localhost","NULL","false","false","false","1","NULL", "true"};
    String[] valuesforRMIConfigSet={"false","localhost","1099","NULL","false","false","false","1","NULL", "true"};
    // Stores the userName.
    String userName=null;
    // Stores the password.
    String password=null;
    String usage=null;
    String isRollBackRequired;
    String rollBackType;
    String rollBackDocument;
    // Hashtable where username,password,taskName,device,snmpattributes etc are stored
    Hashtable partab=new Hashtable();
    Hashtable ht; 
    public Hashtable getParams(String diff,String args[])
    {

        parseCommandLineArguments(diff,args);   
        Hashtable ht=assignValues(diff);
        return ht;    

    }    

    private void parseCommandLineArguments(String diff,String args[])
    {

        if(diff.equals("ConfigSet"))
        {
            usage="java com.adventnet.nms.example.config.ConfigSet [-d] [-m \"path of the mib file(s)\"] -u userName -P password [-h ConfigServerHost] -p port  deviceName [-S snmpPort] [device [-S snmpPort] ... ] "+
                "-t taskName [-o true/false] [-s true/false] [-r true/false] [ -rbtype 1/2] [-rbdoc taskName] [-persistence true/false] [-A oid [ label [type INTEGER/STRING/GUAGE/TIMETICKS/OPAQUE/IPADDRESS/COUNTER/OID] ] value ] \n"+
                " Options: \n"+
                "    -d\t\t\t\tGenerates debugging information\n" +
                "    -m <path>\t\t\tSpecify where to find MIB files\n"+
                "    -u <userName>\t\tSpecify the user\n"+
                "    -P <password>\t\tPassword\n"+
                "    -h <hostName>\t\tSpecify the host where ConfigServer is running\n"+
                "    -p <portNumber>\t\tSpecify the port where ConfigServer is running\n"+
                "    deviceName\t\t\tSpecify the name of the device\n"+
                "    -S <SnmpPort>\t\tSpecify the port where snmp agent is running\n"+            
                "    -t <taskName>\t\tGenerates Configuration information\n"+
                "    -o <true/false>\t\tSpecify whether to override the existing configuration or not\n"+
                "    -s <true/false>\t\tSpecify whether to configure the devices in the same order or not \n"+
                "    -r <true/false>\t\tSpecify whether the rollback is required or not\n"+
                "    -rbtype <1/2>\t\tSpecify the rollback type which can be 1 for Current Configuration and 2 for using rollback Document\n"+
                "    -rbdoc <taskName>\t\t Specify the taskName which is an already existing task\n"+
                "    -persistence <true/false>\tSpecify whether the task should be stored or not\n"+ 
                "    -A <OID [ label [type\t Specify the type as INTEGER,STRING,GUAGE,TIMETICKS,OPAQUE,IPADDRESS,COUNTER,OID]] value>Specify the attribute for configuration";



            String [] options={"-d","-h","-m","-o","-s","-r","-rbtype","-rbdoc","-persistence"};
            String []  mOptions={"-u","-P","-p","-t"};

            commandParser=new ConfigParserOptions(mOptions,options,valuesforConfigSet,args,usage);
            String[] remArgs=commandParser.getRemArguments();

            if ( remArgs.length < 9 ) 
            {
                System.out.println("Usage: "+usage);
                System.exit(0);
            }
            commandParser.checkForFormat();  

        }
        else if(diff.equals("RMIConfigSet"))
        {
            usage="java com.adventnet.nms.example.config.RMIConfigSet [-d] [-m \"path of the mib file(s)\"] -u userName -P password [-h ConfigServerHost] [-p RMIport ] deviceName [-S snmpPort] [deviceName [-S snmpPort] ... ] "+
                "-t taskName [-o true/false] [-s true/false] [-r true/false] [ -rbtype 1/2] [-rbdoc taskName] [-persistence true/false] [-A oid [ label [type] ] value]  ... \n"+
                " Options: \n"+
                "    -d\t\t\t\tGenerates debugging information\n" +
                "    -m <path>\t\t\tSpecify where to find MIB files\n"+
                "    -u <userName>\t\tSpecify the user\n"+
                "    -P <password>\t\tPassword\n"+
                "    -h <hostName>\t\tSpecify the host where ConfigServer is running\n"+
                "    -p <portNumber>\t\tSpecify the port where RMI Registry is running\n"+
                "    deviceName\t\t\tSpecify the name of the device\n"+
                "    -S <SnmpPort>\t\tSpecify the port where snmp agent is running\n"+            
                "    -t <taskName>\t\tGenerates Configuration information\n"+
                "    -o <true/false>\t\tSpecify whether to override the existing configuration or not\n"+
                "    -s <true/false>\t\tSpecify whether to configure the devices in the same order or not \n"+
                "    -r <true/false>\t\tSpecify whether the rollback is required or not\n"+
                "    -rbtype <1/2>\t\tSpecify the rollback type which can be 1 for Current Configuration and 2 for using rollback Document\n"+
                "    -rbdoc <taskName>\t\t Specify the taskName which is an already existing task\n"+
                "    -persistence <true/false>\tSpecify whether the task should be stored or not\n"+ 
                "    -A <OID [ label [type INTEGER,STRING,GUAGE,TIMETICKS,OPAQUE,IPADDRESS,COUNTER,OID ]] value>Specify the attribute for configuration";

            String [] options={"-d", "-h","-p","-m","-o","-s","-r","-rbtype","-rbdoc","-persistence"};
            String [] mOptions={"-u","-P","-t"};

            commandParser=new ConfigParserOptions(mOptions,options,valuesforRMIConfigSet,args,usage);
            String[] remArgs=commandParser.getRemArguments();
            if ( remArgs.length < 7 ) 
            {
                System.out.println("Usage: "+usage);
                System.exit(0);
            }	
            commandParser.checkForFormat();
        }

    }

    //assigns the value
    Hashtable  assignValues(String diff)
    {
        userName=commandParser.getUserName();
        partab.put("UserName",userName);

        password=commandParser.getPassword();
        partab.put("PassWord",password);

        Vector deviceVec = commandParser.getDevices();

        deviceObject = new SnmpDevice[deviceVec.size()/2];
        Enumeration enumerate = deviceVec.elements();
        int index=0;
        if ( ConfigParserOptions.DEBUG)
        {
            System.out.println("Devices     SnmpPort\n");
        }
        while( enumerate.hasMoreElements())
        {
            String deviceName=(String)enumerate.nextElement();
            String devicePort = (String)enumerate.nextElement();
            try
            {
                Integer.parseInt(devicePort);
            }
            catch(NumberFormatException nfe)
            {
                System.out.println("Invalid port for "+ deviceName+" , Taking default port 161");
                devicePort="161";
            }
            if ( ConfigParserOptions.DEBUG)
            {
                System.out.println(deviceName+"    "+devicePort);
            }
            deviceObject[index]=new SnmpDevice(deviceName,devicePort);


            deviceName=null;
            devicePort=null;
            index++;
        }

        partab.put("device",deviceObject);

        taskName=commandParser.getTaskName();
        partab.put("taskName",taskName);

        if (diff.equals("ConfigSet"))
        {
            port=commandParser.getPort();
            String pr=String.valueOf(port);
            partab.put("port",pr);
        }
        else if(diff.equals("RMIConfigSet"))
        {
            port=Integer.parseInt(valuesforRMIConfigSet[2]);
            partab.put("port",valuesforRMIConfigSet[2]);
        }  

        if (valuesforConfigSet[3].equalsIgnoreCase("true") || valuesforRMIConfigSet[4].equalsIgnoreCase("true"))
            partab.put("overwrite","true");
        else
            partab.put("overwrite","false");

        if (valuesforConfigSet[4].equalsIgnoreCase("true") || valuesforRMIConfigSet[5].equalsIgnoreCase("true"))
            partab.put("sequential","true");
        else
            partab.put("sequential","false");

        if(diff.equals("ConfigSet"))
        {
            String valc=valuesforConfigSet[1];
            partab.put("hostName",valc);
        }

        else if(diff.equals("RMIConfigSet"))
        {
            String valr=valuesforRMIConfigSet[1];
            partab.put("hostName",valr);
        }

        if(valuesforConfigSet[5].equalsIgnoreCase("true") || valuesforRMIConfigSet[6].equalsIgnoreCase("true"))
            partab.put("isRollBackNeeded","true");
        else
            partab.put("isRollBackNeeded","false");


        if(diff.equals("ConfigSet"))
        {
            rollBackType=valuesforConfigSet[6];
            partab.put("rollBackType",rollBackType);
        }
        else if (diff.equals("RMIConfigSet"))
        {
            rollBackType=valuesforRMIConfigSet[7];
            partab.put("rollBackType",rollBackType);
        }




        if(diff.equals("ConfigSet"))
        {
            rollBackDocument=valuesforConfigSet[7];
            partab.put("rollBackDocument",rollBackDocument);
        }
        else if  (diff.equals("RMIConfigSet"))
        {
            rollBackDocument=valuesforRMIConfigSet[8];
            partab.put("rollBackDocument",rollBackDocument);
        }

        if (valuesforConfigSet[8].equalsIgnoreCase("true") &&  valuesforRMIConfigSet[9].equalsIgnoreCase("true"))
        {
            partab.put("persistence","true");
        }
        else
        {
            partab.put("persistence","false");
        }
      


        Vector vec=commandParser.getSnmpAttributes();

        //If the SnmpAttribute is null then return the stored values in the HashTable
        if ( vec == null || vec.size() ==0 )
            return partab;

        String[] attributes=new String[vec.size()];
        vec.copyInto(attributes);
        oid=new String[vec.size()];
        label=new String[vec.size()];
        type=new String[vec.size()];
        contype=new byte[vec.size()];
        value=new String[vec.size()];


        for( int i=0;i<attributes.length;++i)
        {
            StringTokenizer st=new StringTokenizer(attributes[i]," ");
            type[i]=null;

            if( st.countTokens() == 4)
            {
                oid[i]=st.nextToken();
                label[i]=st.nextToken();
                type[i]=st.nextToken();
                value[i]=st.nextToken();
            }
            else if (st.countTokens()==3)
            {
                oid[i]=st.nextToken();
                label[i]=st.nextToken();
                value[i]=st.nextToken();
            }
            else if ( st.countTokens() ==2)
            {
                oid[i]=st.nextToken();
                value[i]=st.nextToken();
            }
            else 
            {
                System.out.println("Error in parsing SnmpAttributes,SnmpAttributes should begin with \"-A\"\n");
                System.out.println("Usage: "+usage);
                System.exit(0);
            }

        }

        assignTypes(diff);

        partab.put("oid",oid);

        partab.put("label",label);

        partab.put("type",type);

        partab.put("value",value);

        return partab;


    }

    private void assignTypes(String diff)
    {
        if ( diff.equals( "ConfigSet" ) && valuesforConfigSet[2].equals("NULL"))
            return;
		
        if ( diff.equals("RMIConfigSet") && valuesforRMIConfigSet[3].equals("NULL"))
            return;
			
        MibOperations mibOps=new MibOperations();

        try
        {
            if (diff.equals("ConfigSet"))
                mibOps.loadMibModules(valuesforConfigSet[2]);
            else if (diff.equals("RMIConfigSet"))
                mibOps.loadMibModules(valuesforRMIConfigSet[3]);
        }
        catch(Exception ex)
        {
            System.err.println("Exception in loading the mib modules");
            System.exit(0);
        }
        SnmpOID soid=null;
        LeafSyntax lf=null;
        for( int i=0;i<oid.length;++i)
        {
            soid=mibOps.getSnmpOID(oid[i]);
            if ( soid==null)
            {
                System.err.println("Label specified is not present in the loaded Mibs");
                System.exit(0);
            }
            oid[i]=soid.toString();
            if ( mibOps.getMibNode(soid).isScalar())
            {
                if ( ! oid[i].endsWith(".0"))
                    oid[i]=oid[i]+".0";
            }

            lf=mibOps.getLeafSyntax(soid);
            if ( lf==null)
                continue;

            contype[i]=lf.getType();
            type[i]=Byte.toString(contype[i]);

            if ( label[i]==null)
            {
                label[i]=mibOps.getMibNode(soid).getLabel();
            }

            soid=null;
            lf=null;
        }
    }


}


























