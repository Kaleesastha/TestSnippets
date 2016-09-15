
import com.adventnet.nms.startclient.*;
import com.adventnet.nms.startclient.WebNMSClient;
import com.adventnet.nms.startclient.ClientConnectionException;

class TestClass
{ 
    TestClass()
    { 
        WebNMSClient n = new WebNMSClient("root", "public", "localhost", "9090", "US","English", true,true);
        //username, password, host, port, language, country, enable Java console window
        n.doconnect();
    } 
    
    public static void main(String[] args)
    { 
        TestClass tst = new TestClass();
    } 
} 








