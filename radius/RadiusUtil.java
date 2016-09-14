package com.adventnet.security.authentication;

import java.rmi.*;
import com.adventnet.security.authentication.AuthenticationAPI;
import com.adventnet.security.authentication.AuthenticationException;
import java.util.*;
import java.security.*;
import java.io.*;
import java.net.*;

import com.adventnet.nms.util.PureUtils;
import com.adventnet.nms.util.NmsUtil;
import com.adventnet.security.authorization.AuthorizationAdmin;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import java.io.File;

public class RadiusUtil 
{
	private static String hostIPAddress = null;
	private static String secretKey = null;
	private static int radiusPort = 0;
	private static int accountPort = 0;
	private static boolean radiusEnabled = false;
	private static RadiusUtil radiusUtil = null;
	private NodeList nl =null;
	private int length = 0;

    public synchronized static RadiusUtil getInstance()
    {
        if ( radiusUtil != null )
        {
            return radiusUtil;
        }
        else
        {
            radiusUtil = new RadiusUtil();
            radiusUtil.init();
            return radiusUtil;
        }
    }
    
	public static void setRadiusServerIP(String ip)
	{
		hostIPAddress = ip;
	}

	public static String getRadiusServerIP()
	{
		return hostIPAddress;
	}
	public static void setSecretKey(String secret )
	{
		secretKey = secret;
	}

	public static String getSecretKey()
	{
		return secretKey;
	}

	public static void setRadiusAccountPort(int port)
	{
		accountPort = port;
	}

	public static int getRadiusaccountPort()
	{
		return accountPort;
	}
	public static void setRadiusPort(int port)
	{
		radiusPort = port;
	}

	public static int getRadiusPort()
	{
		return radiusPort;
	}

	public boolean isRadiusServerEnabled()
	{
		return radiusEnabled;
	}
    
	private void init()
	{
		String confHome =  PureUtils.usersDir + "conf";//No Internationalisation
		String fileName = PureUtils.rootDir + System.getProperty("file.separator") + "conf" + System.getProperty("file.separator") + "RadiusServerDetails.xml" ;//No Internationalisation
		File radiusFile = new File ( fileName );
		if ( !radiusFile.exists() )
		{
			return ;
		}

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try 
		{
			DocumentBuilder db = dbf.newDocumentBuilder();

			Document doc = db.parse(radiusFile);

			Element root = doc.getDocumentElement();

			Node detailsNod = (Node)(root.getElementsByTagName("Details")).item(0); //No internationalization
			nl = detailsNod.getChildNodes();
			length = nl.getLength();
			

		}
		catch (Exception e )
		{
			e.printStackTrace();
		}
	}	

	public boolean authenticateUser(String userName,String userPass) throws  AuthenticationException 
	{
		int result = 0;
			try
			{
			for (int i=0; i < length; i++)
			{
				String exception = "";
				Node snode = nl.item(i);
				if ( snode.getNodeType() != Node.ELEMENT_NODE )
				{
					continue;
				}
				NamedNodeMap nnm = snode.getAttributes();
				hostIPAddress = nnm.getNamedItem("name").getNodeValue(); //No internationalization
				radiusPort = Integer.parseInt(nnm.getNamedItem("radiusport").getNodeValue());//No internationalization
				secretKey = nnm.getNamedItem("secret").getNodeValue(); //No internationalization
				try
				{
				//	radiusClient = new NmsRadiusClient(hostIPAddress,secretKey,"root");
				NmsRadiusClient radiusClient = new NmsRadiusClient(hostIPAddress,secretKey,userName,radiusPort);
				result = radiusClient.authenticate(userPass);
//	radiusClient.authenticate("public");
				}
				catch ( Exception st)
				{
					exception = st.toString();
					
				}
				if (exception.equals("java.io.InterruptedIOException: Receive timed out"))
				{
					continue;
				}
				else
				{
					break;
				}                                
			}
			if (  result == 2 ) 
			{
				System.out.println("RADIUS"+result);//No Internationalisation 
				System.out.println("RADIUS: ACCESS_ACCEPT");//No Internationalisation 
				AuthorizationAdmin admin = (AuthorizationAdmin)NmsUtil.getAPI("NmsAuthAdminAPI");
				boolean isPresent = admin.isUserNamePresent(userName);
				System.err.println("isPresent is::"+isPresent);//No Internationalisation 
				if(! isPresent)
					admin.createUser(userName,"Admin",userPass);
				return true;
			}
			else if ( result == 3 ) 
			{
				System.out.println("RADIUS: ACCESS_REJECT");//No Internationalisation 
				return false;
			}
			else
			{
				System.out.println("RADIUS"+result);//No Internationalisation 
			}
		}
		catch ( Exception e )
		{
			e.printStackTrace(); 
			throw new AuthenticationException(e.toString());
		}
		return false;
	}
}

class NmsRadiusClient
{
    private  String userName = "";
    private  String sharedSecret = "";
    private  String hostname = "";
    private int radiusPort = 0;
    private  DatagramSocket socket = null;
    private  int authenticationPort = 1812;
    private  int accountingPort = 1813;
    private  MessageDigest md5MessageDigest;
    private  Object nextIdentifierLock = new Object();
    private  byte nextIdentifier = (byte)0;
    private  Hashtable responseAttributes = new Hashtable();
    private  static byte [] NAS_ID;
    private  static byte [] NAS_IP;

    static 
    {
        try
        {
            InetAddress localHost = InetAddress.getLocalHost();
            NAS_ID = (localHost.getHostName()).getBytes();
            NAS_IP = (localHost.getHostAddress()).getBytes();
        }
        catch (Exception uhex)
        {
            //If this happens the host has no IP address, what can we do???
            //everything will be fouled up anyway!!
            throw new RuntimeException(uhex.getMessage());
        }
    }
    public NmsRadiusClient (String hostname,String sharedSecret,String userName, int radiusPort) throws Exception  
    {
        this.hostname = hostname;
	this.radiusPort = radiusPort;
        this.userName = userName;
        this.sharedSecret = sharedSecret;
        //set up the socket for this client
        this.socket = new DatagramSocket();
        this.socket.setSoTimeout(6000);
        //set up the md5 engine
        this.md5MessageDigest = MessageDigest.getInstance("MD5");//No Internationalisation
    }

    public int authenticate(String userPass) throws Exception 
    {
        return this.authenticate(userPass, null, 3 );
    }
    public int authenticate(String userPass, ByteArrayOutputStream requestAttributes, int retries) throws Exception 
    {

        //test for validity of userPass
        if (userPass == null)
        {
            throw new Exception("Password can not be null!");//No Internationalisation
        }//else password is a-ok for passing to RADIUS Server
        if(retries < 0)
        {
            throw new Exception("retries must be zero or greater!");//No Internationalisation
        }
        else if (retries == 0)
        {
            retries = 3;
        }
        byte code = 1 ; //RadiusClient.ACCESS_REQUEST;  //1 byte: code
        byte identifier = this.getNextIdentifier();  //1 byte: Identifier can be anything, so should not be constant

        //16 bytes: Request Authenticator
        byte [] requestAuthenticator = this.makeRFC2865RequestAuthenticator();

      
      
      
        if (requestAttributes == null){
            requestAttributes = new ByteArrayOutputStream();
        }
        // USER_NAME
        this.setAttribute(1, this.userName.getBytes(), requestAttributes);
        // USER_PASSWORD
        if(userPass.length() > 0){//otherwise we don't add it to the Attributes
            /*if (userPass.length() > 16){
              userPass = userPass.substring(0, 16);
              }*/
            byte [] encryptedPass = this.encryptPass(userPass, requestAuthenticator);
            //(encryptPass gives ArrayIndexOutOfBioundsException if password is of zero length)
            this.setAttribute(2, encryptedPass.length, encryptedPass, requestAttributes);
        }
        //set a STATE attribute IF it is there (for Challenge responses)
        try{
            this.setAttribute(24, this.getStateAttributeFromResponse(), requestAttributes);
        }catch(Exception rex){
            //no state attribute was set, so we go merrily on our way
        }
        // Set the NAS-Identifier
        this.setAttribute(32,NAS_ID, requestAttributes);
        // Length of Packet is computed as follows, 20 bytes (corresponding to
        // length of code + Identifier + Length + Request Authenticator) +
        // each attribute has a length computed as follows: 1 byte for the type +
        // 1 byte for the length of the attribute + length of attribute bytes
        short length = (short) (20 + requestAttributes.size() );

        DatagramPacket packet =
            this.composeRadiusPacket(this.radiusPort, code, identifier, length, requestAuthenticator, requestAttributes.toByteArray());
        // now send the request and recieve the response
        int responseCode = 0;
        if ((packet = this.sendReceivePacket(packet, retries)) != null){
            switch(this.checkRadiusPacket(packet,identifier, requestAuthenticator)){
                case 2:
                    responseCode = 2;
                    break;
                case 3:
                    responseCode = 3;
                    break;
		case 11:
                    responseCode = 11;
                    break;
                default:
                    throw new Exception("Invalid response recieved from the RADIUS Server.");//No Internationalisation
            }
        }
        //destroy userPass in memory?
        return responseCode;
    }
    private int checkRadiusPacket(DatagramPacket packet, byte requestIdentifier, byte[] requestAuthenticator) throws Exception
    {
        int returnCode = -1;
        int packetLength = packet.getLength();
        ByteArrayInputStream bais = new ByteArrayInputStream(packet.getData());
        DataInputStream input = new DataInputStream(bais);

        byte code = input.readByte();
        returnCode = code & 0xff;
        //now check the identifiers to see if they match
        byte identifierByte = input.readByte();
        //int identifier = identifierByte & 0xff;//don't need this
        if (identifierByte != requestIdentifier){
            //wrong packet asshole!
            throw new Exception("The RADIUS Server returned the wrong Identifier.");//No Internationalisation
        }
        //read the length
        short length = (short)((int)input.readShort() & 0xffff);
        //now check the response authenticator to validate the packet
        byte [] responseAuthenticator = new byte[16];
        input.readFully(responseAuthenticator);
        //get the attributes as a byte[]
        byte[] responseAttributeBytes = new byte[length - 20];
        input.readFully(responseAttributeBytes);
        byte [] myResponseAuthenticator = this.makeRFC2865ResponseAuthenticator(code, identifierByte, length, requestAuthenticator, responseAttributeBytes);
        //now compare them
        if((responseAuthenticator.length != 16) ||
            (myResponseAuthenticator.length != 16)){
            //wrong authenticator length asshole!
            throw new Exception("Authenticator length is incorrect.");//No Internationalisation
        }else{
            for (int i = 0; i<responseAuthenticator.length;i++){
                if (responseAuthenticator[i] != myResponseAuthenticator[i]){
                    throw new Exception("Authenticators do not match, response packet not validated!");//No Internationalisation
                }
            }
        }
        //now parse out the responseAttributeBytes into the responseAttributes hashtable
        int attributesLength = responseAttributeBytes.length;
        if (attributesLength > 0){
            Integer attributeType;
            int attributeLength;
            byte[] attributeValue;
            DataInputStream attributeInput = new DataInputStream(new ByteArrayInputStream(responseAttributeBytes));
            this.responseAttributes.clear();//thread issues???
            for (int left=0; left < attributesLength; ){
                attributeType = new Integer(attributeInput.readByte() & 0xff);
                attributeLength = attributeInput.readByte() & 0xff;
                attributeValue = new byte[attributeLength - 2];
                attributeInput.read(attributeValue, 0, attributeLength - 2);
                this.responseAttributes.put(attributeType, attributeValue);
                left += attributeLength;
            }
            attributeInput.close();
        }
        input.close();
        bais.close();
        return returnCode;
    }
    private byte[] makeRFC2865ResponseAuthenticator(byte code, byte identifier, short length, byte [] requestAuthenticator, byte[] responseAttributeBytes) 
    {
        this.md5MessageDigest.reset();

        this.md5MessageDigest.update((byte)code);
        this.md5MessageDigest.update((byte)identifier);
        this.md5MessageDigest.update((byte)(length >> 8));
        this.md5MessageDigest.update((byte)(length & 0xff));
        this.md5MessageDigest.update(requestAuthenticator, 0, requestAuthenticator.length);
        this.md5MessageDigest.update(responseAttributeBytes, 0, responseAttributeBytes.length);
        this.md5MessageDigest.update(this.sharedSecret.getBytes());

        return this.md5MessageDigest.digest();
    }
    private DatagramPacket sendReceivePacket(DatagramPacket packet_out, int retry) throws Exception
    {
        if (packet_out.getLength() > 4096)
        {
            throw new Exception("Packet too big!");//No Internationalisation
        }
        else if (packet_out.getLength() < 20)
        {			
            throw new Exception("Packet too short !");//No Internationalisation
        }
        else
        {
            DatagramPacket packet_in = new DatagramPacket(new byte[4096], 4096);
            for (int i = 1; i <= retry; i++)
            {
                try
                {
                    this.socket.send(packet_out);
                    this.socket.receive(packet_in);
                    return packet_in;
                }
                catch (Exception ioex)
                {
                    //if we reach the max number of retries throw it back up the stack
                    if (i == retry)
                    {
                        throw ioex;
                    }
                }
            }
        }

        return null;
    }
    private byte getNextIdentifier()
    {
        byte identifier = 0;
        synchronized(this.nextIdentifierLock)
        {
            identifier = this.nextIdentifier;
            this.nextIdentifier++;
        }
        return identifier;
    }
    private DatagramPacket composeRadiusPacket(int port, byte code,
                                                byte identifier,
                                                short length,
                                                byte[] requestAuthenticator,
                                                byte[] requestAttributes)
    throws Exception{
        ByteArrayOutputStream baos 	= new ByteArrayOutputStream();
        DataOutputStream output 	= new DataOutputStream(baos);
        DatagramPacket packet_out 	= null;
//1 byte: Code
        output.writeByte(code);
//1 byte: identifier
        output.writeByte(identifier);
//2 byte: Length
        output.writeShort(length);
//16 bytes: Request Authenticator
//only write 16 of them if there are more, which there better not be
        output.write(requestAuthenticator, 0, 16);

        output.write(requestAttributes, 0, requestAttributes.length);

        packet_out = new DatagramPacket(new byte[length], length);
        packet_out.setPort(port);
        packet_out.setAddress(InetAddress.getByName(this.hostname));
        packet_out.setLength(length);

        packet_out.setData(baos.toByteArray());
        output.close();
        baos.close();
        //won't get here in the case of an exception so we won't return return null or a malformed packet
        return packet_out;
    }
    private byte[] getStateAttributeFromResponse() throws Exception
    {
        if ( this.responseAttributes == null )
        {
            throw new Exception("No Response Attributes have been set.");//No Internationalisation
        }
        byte[] stateBytes = (byte[]) this.responseAttributes.get(new Integer(24));
        if ((stateBytes == null) || (stateBytes.length == 0))
        {
            throw new Exception("No State Attribute has been set.");//No Internationalisation
        }
        return stateBytes;
    }
    private void setAttribute(int type, byte [] attribute, ByteArrayOutputStream requestAttributes) 
    {
        this.setAttribute(type, attribute.length, attribute, requestAttributes);
    }
    private void setAttribute(int type, int length, byte [] attribute, ByteArrayOutputStream requestAttributes) 
    {
        //1 byte: Type
        requestAttributes.write(type);

        //1 byte: length of the Type plus 2 bytes for the rest of this attirbute.
        requestAttributes.write(length + 2);

        //Value.length() bytes: the actual Value.
        requestAttributes.write(attribute, 0, length);
    }
    private byte[] makeRFC2865RequestAuthenticator() 
    {
        byte [] requestAuthenticator = new byte [16];

        Random r = new Random();

        for (int i = 0; i < 16; i++)
        {
            requestAuthenticator[i] = (byte) r.nextInt();
        }
        this.md5MessageDigest.reset();
        this.md5MessageDigest.update(this.sharedSecret.getBytes());
        this.md5MessageDigest.update(requestAuthenticator);

        return this.md5MessageDigest.digest();
    }
    private byte [] encryptPass(String userPass, byte [] requestAuthenticator) {
        // encrypt the password
        //the password must be a multiple of 16 bytes and less than or equal
        //to 128 bytes. If it isn't a multiple of 16 bytes fill it out with zeroes
        //to make it a multiple of 16 bytes. If it is greater than 128 bytes
        //truncate it at 128

        if (userPass.length() > 128){
            userPass = userPass.substring(0, 128);
        }
        // Transformation de la chaine en tableau d'octet pour hachage MD5.
        byte userPassBytes[] = userPass.getBytes();
        // declare the byte array to hold the final product
        byte encryptedPass[] = null;

        if (userPassBytes.length < 128) 
        {
            if (userPassBytes.length % 16 == 0) 
            {
                // It is already a multiple of 16 bytes
                encryptedPass = new byte[userPassBytes.length];
            } 
            else 
            {
                // Make it a multiple of 16 bytes
                encryptedPass = new byte[((userPassBytes.length / 16) * 16) + 16];
            }
        } 
        else 
        {
            // the encrypted password must be between 16 and 128 bytes
            encryptedPass = new byte[128];
        }

        // copy the userPass into the encrypted pass and then fill it out with zeroes
        System.arraycopy(userPassBytes, 0, encryptedPass, 0, userPassBytes.length);
        for(int i = userPassBytes.length; i < encryptedPass.length; i++) 
        {
            encryptedPass[i] = 0;  //fill it out with zeroes
        }

        this.md5MessageDigest.reset();
        // add the shared secret
        this.md5MessageDigest.update(this.sharedSecret.getBytes());
        // add the  Request Authenticator.
        this.md5MessageDigest.update(requestAuthenticator);
        // get the md5 hash( b1 = MD5(S + RA) ).
        byte bn[] = this.md5MessageDigest.digest();

        for (int i = 0; i < 16; i++){
            // perform the XOR as specified by RFC 2865.
            encryptedPass[i] = (byte)(bn[i] ^ encryptedPass[i]);
        }

       	 if (encryptedPass.length > 16){
       	 for (int i = 16; i < encryptedPass.length; i+=16){
       	 this.md5MessageDigest.reset();
       // add the shared secret
       	  this.md5MessageDigest.update(this.sharedSecret.getBytes());
       //add the previous(encrypted) 16 bytes of the user password
           this.md5MessageDigest.update(encryptedPass, i - 16, 16);
       // get the md5 hash( bn = MD5(S + c(i-1)) ).
            bn = this.md5MessageDigest.digest();
            for (int j = 0; j < 16; j++) {
       // perform the XOR as specified by RFC 2865.
                    encryptedPass[i+j] = (byte)(bn[j] ^ encryptedPass[i+j]);
                }
            }
        }
        return encryptedPass;
    }
}


