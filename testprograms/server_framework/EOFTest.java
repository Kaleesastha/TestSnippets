import java.net.*;
import java.io.*;
import com.adventnet.nms.protocol.NmsPDU;
public class EOFTest
{
    public static void main(String args[])
    {
        try
            {   //Construct a improper data
                String id="EOF";                
                String uniqueID="EOF";
                String str="HI";
                byte[] data=str.getBytes();
                
		int length = id.length();
		length += uniqueID.length();
		length +=data.length + 4;
		ByteArrayOutputStream baos = new ByteArrayOutputStream(length);
		DataOutputStream dos= new DataOutputStream(baos);
		dos.writeUTF(id);
		dos.writeUTF(uniqueID);
		int dataLength=data.length;
		dos.writeInt(dataLength+7);//changing the length
		dos.write(data,0,dataLength);
		byte barr[] = baos.toByteArray();
		baos.reset();
		baos.close();
                
                NmsPDU.deSerializeNmsPDU(barr);
            }
        
        catch(Exception e)
            {
                e.printStackTrace();
            }
    }


}





















