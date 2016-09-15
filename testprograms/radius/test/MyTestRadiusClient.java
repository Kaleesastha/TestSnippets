package net.sourceforge.jradiusclient;

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import net.sourceforge.jradiusclient.exception.*;
import net.sourceforge.jradiusclient.RadiusClient;
/**
 * @author <a href="mailto:bloihl@users.sourceforge.net">Robert J. Loihl</a>
 * @version $Revision: 1.1 $
 */
public class MyTestRadiusClient{
    public static String getUsage(){
        return "usage: RadiusClient server authPort acctPort secret user password [calling-station-id]";
    }

static String userPass=null;
static RadiusClient rc = null;
static byte[] callingStationId = null;
public boolean returned;

    public static void main(String [] args)
    {



        if ((args.length < 3) || (args.length > 7))
        {
            MyTestRadiusClient.log(getUsage());
            System.exit(2);
        }

        try{
            rc = new RadiusClient(args[0],args[1], args[2],args[3]);
	    
            //rc = new RadiusClient("ms-linuxES1",1812,1814,"vasus","vasus");
        }catch(java.net.SocketException soex){
            MyTestRadiusClient.log("Unable to create Radius Client due to failure to create socket!");
            MyTestRadiusClient.log(getUsage());
            System.exit(4);
        }catch(java.security.NoSuchAlgorithmException nsaex){
            MyTestRadiusClient.log("Unable to create Radius Client due to failure to create MD5 MessageDigest!");
            MyTestRadiusClient.log(getUsage());
            System.exit(5);
        }catch(InvalidParameterException ivpex){
            MyTestRadiusClient.log("Unable to create Radius Client due to invalid parameter!");
            MyTestRadiusClient.log(ivpex.getMessage());
            MyTestRadiusClient.log(getUsage());
            System.exit(6);
        }
        userPass = args[3];
	System.out.println("user pass   " + userPass);
        //byte[] callingStationId = null;
        if(args.length > 2 ){//the seventh one should be calling station ID
            //callingStationId = args[4].getBytes();
	    args[3]="31";
            callingStationId = args[3].getBytes();
	    System.out.println("Calling Station ID   " +  callingStationId);
        }

    }
//public boolean returned;

public boolean auth()
	{
	  System.out.println("##################");
        try{
            returned = MyTestRadiusClient.authenticate(rc, userPass, callingStationId);
	    System.out.println("boolean     returned " + returned);
	}catch(Exception e)
	{
	e.printStackTrace();
	}
	return returned;
	}


public void acct()
	{

            if (returned){
                MyTestRadiusClient.log("------------------------------------------------------");
		try{
                //returned = rc.startAccounting(args[3]);
                returned = rc.startAccounting(userPass);
		}catch(Exception e)
		{
		e.printStackTrace();
		}
                if (returned){
                    MyTestRadiusClient.log("Accounting start succeeded.");
                }else{
                    MyTestRadiusClient.log("Accounting start failed.");
                }
                MyTestRadiusClient.log("------------------------------------------------------");
                //returned = rc.stopAccounting(args[3]);
                try{
		returned = rc.stopAccounting(userPass);
		}catch(Exception er)
		{
		er.printStackTrace();
		}
                if (returned){
                    MyTestRadiusClient.log("Accounting stop succeeded.");
                }else{
                    MyTestRadiusClient.log("Accounting stop failed.");
                }
                MyTestRadiusClient.log("------------------------------------------------------");
            }
        }
    

    public static boolean authenticate(RadiusClient rc, String userPass,  byte[] callingStationId) throws InvalidParameterException,
    java.net.UnknownHostException, java.io.IOException, RadiusException{
        int returnCode;
        if(callingStationId != null){
            ByteArrayOutputStream reqAttributes = new ByteArrayOutputStream();
            try{
                rc.setUserAttribute(RadiusClient.CALLING_STATION_ID, callingStationId, reqAttributes);
            }catch(InvalidParameterException ivpex){
                System.out.println(ivpex);
            }
            returnCode = rc.authenticate(userPass, reqAttributes);
        }else{
            returnCode = rc.authenticate(userPass);
        }
        boolean returned = false;
        MyTestRadiusClient.log("++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        switch (returnCode){
        case RadiusClient.ACCESS_ACCEPT:
            MyTestRadiusClient.log("Authenticated");
            returned = true;
            break;
        case RadiusClient.ACCESS_REJECT:
            MyTestRadiusClient.log("Not Authenticated");
            returned = false;
            break;
        case RadiusClient.ACCESS_CHALLENGE:
            MyTestRadiusClient.log(rc.getChallengeMessage());
            //wait for user input
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            userPass = br.readLine();
            returned = MyTestRadiusClient.authenticate(rc, userPass, callingStationId);
            break;
        default:
            MyTestRadiusClient.log("How the hell did we get here?");
            returned = false;
            break;
        }
        MyTestRadiusClient.log("++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        return returned;
    }
    private static void log(String message)
    {
        System.out.print  ("Radius Server : ");
        System.out.println(message);
    }

    private void setSIPAttributes(RadiusClient rc, ByteArrayOutputStream reqAttributes) throws InvalidParameterException {
        rc.setUserAttribute(RadiusClient.DIGEST_RESPONSE, "0c02a1cc5ec9a986aaa7232bb975faffa".getBytes(), reqAttributes);

             rc.setUserSubAttribute(
                 RadiusClient.DIGEST_ATTRIBUTE,
                 RadiusClient.SIP_REALM,
                 "buddyphone".getBytes(),
                 reqAttributes
             );

             rc.setUserSubAttribute(
                 RadiusClient.DIGEST_ATTRIBUTE,
                 RadiusClient.SIP_USER_NAME,
                 "koehler".getBytes(),
                 reqAttributes
             );

             rc.setUserSubAttribute(
                 RadiusClient.DIGEST_ATTRIBUTE,
                 RadiusClient.SIP_NONCE,
                 "1a80ff0a".getBytes(),
                 reqAttributes
             );

             rc.setUserSubAttribute(
                 RadiusClient.DIGEST_ATTRIBUTE,
                 RadiusClient.SIP_URI,
                 "sip:buddyphone.com:5060".getBytes(),
                 reqAttributes
             );

             rc.setUserSubAttribute(
                 RadiusClient.DIGEST_ATTRIBUTE,
                 RadiusClient.SIP_METHOD,
                 "REGISTER".getBytes(),
                 reqAttributes
             );

             rc.setUserSubAttribute(
                 RadiusClient.DIGEST_ATTRIBUTE,
                 RadiusClient.SIP_ALGORITHM,
                 "MD5".getBytes(),
                 reqAttributes
             );
    }
}
