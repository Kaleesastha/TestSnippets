import java.io.*; 
import java.util.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 

import com.adventnet.nms.fe.map.MapFE; 
import com.adventnet.nms.fe.map.*; 

public class msb extends HttpServlet 
{ 
    HttpServletRequest req = null; 
    PrintWriter out=null;
    
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
    { 
        doGet(req, res); 
    } 
    public synchronized void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException,IOException 
    { 
        out = res.getWriter();
        try
        {
            MapSessionBean msb = MapFE.getMapSession(); 
            Properties p = new Properties();
            // boolean del = msb.deleteMap( "root", "Failed_Objects_Map.netmap");
            //192.168.5.0Failed_Objects_Map.netmap 
            // boolean del2 = msb.deleteMap( "root", "192.168.4.0Failed_Objects_Map.netmap");
            //	 boolean del3 = msb.deleteMap( "root", "192.168.5.0Failed_Objects_Map.netmap");
            // 	 boolean del4 = msb.deleteMap( "root","");
            //	out.println("++++++++++"+del+"++++++++++"+del2+"++++++++++++"+del3+"+++++++++++++++"+del4+"++++++++++");
            
            Vector mapN = msb.getDefaultMapNames("root");
            out.println("__________DefaultMapNames_______________"+ mapN);
            Vector mapC = msb.getCustomMapNames("root");
            out.println("__________CustomMapNames__________________"+ mapC);
            Vector map = msb.getMapNames("root");
            out.println("_____________MapNames_______________"+ map);
            
            //	boolean ma = msb.doesTheMapExist("root","ipnt.netmap");     
            //	out.println("___________________________"+ ma);
        }
        catch(Exception e)
        {
            out.println(e);
        }
        //boolean addMap("root", mapname,p)
        
    }    //End of  main Program..... 
}         
