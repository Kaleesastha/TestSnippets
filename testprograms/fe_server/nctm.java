import java.io.*; 
import java.util.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 

import com.adventnet.nms.fe.alert.AlertFE; 
import com.adventnet.nms.fe.alert.AlertSessionBean; 
import com.adventnet.nms.fe.common.*;
import com.adventnet.nms.fe.common.ViewCriteria; 
import com.adventnet.nms.client.*;
import com.adventnet.nms.util.*;
import com.adventnet.nms.alertui.*;
import com.adventnet.nms.eventui.*;
import com.adventnet.nms.topoui.*;
import com.adventnet.nms.pollui.*;
import com.adventnet.nms.config.*;

public class nctm extends HttpServlet 
{ 
    HttpServletRequest req = null;
    PrintWriter out=null;
    AlertSessionBean asb = null;
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
    { 
        doGet(req, res); 
    } 
    
    public synchronized void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException,IOException   
    { 
        out = res.getWriter();	
        try{
            //  ClientAlertAPI capi = new ClientAlertAPIImpl("localhost","root"); 
            // ClientEventAPI capi = new ClientEventAPIImpl("localhost","root");
            ClientTopoAPI capi = new ClientTopoAPIImpl("sujithr",1099,"root");
            //  ClientPollAPI capi = new ClientPollAPIImpl("localhost","root");
            //  ClientAuditAPI capi = new ClientAuditAPIImpl("localhost","root");
            
            String viewId = "top14";
            Properties criteria = new Properties(); 
            // criteria.put("severity","2"); 
            // criteria.put("source","a*");         
            // criteria.put("category","Topology");
            criteria.put("name","*");
            criteria.put("type","Node"); 
            int increment= 10;
            String[] displayFields={"name","type"};
            String[] displayLables={"Name","Type"};
            Properties PanelProps = new Properties();
            PanelProps.put("parent","Network Database");  
            //PanelProps.put("temporaryCustomView","true"); 
            NmsClientTableModel cv = capi.createCustomView(viewId,criteria); 
            //NmsClientTableModel cv =capi.createCustomView(viewId,criteria,increment,displayFields,displayLables,PanelProps);
            //	out.println("saved"+capi.saveCustomView(viewId));
            out.println("------------------***-------------------");
            out.println("--------the cv is created-----"+cv);
          
            out.println(" getTotalRowCount() - " + cv.getTotalRowCount());
            out.println(" --------------------------------");                        

            /*out.println(" getAllProperties(true) method -" + cv.getAllProperties(true));
              out.println(" getCacheData() - " +cv.getCacheData());
              out.println("getAllProperties(false) method -" + cv.getAllProperties(false));
              out.println(" getCacheData() - " +cv.getCacheData());
              
              
              Vector v = cv.getBottom();
              for(int i=0;i<v.size();i++)
              {
              out.println(" getBottom() - " + v.elementAt(i));
              }
              
              Vector v1 = cv.getBottom(3);
              for(int i=0;i<v1.size();i++)
              {
              out.println(" getBottom(int) - " + v.elementAt(i));
              }
              out.println(" getCriteriaProperties() - " + cv.getDisplayFields());
            */
            
            String strin[] = cv.getDisplayFields();
            for(int i=0;i<strin.length;i++)
            {
                out.println(" getDisplayFields() - " + strin[i]);
            }

            String str[] = cv.getDisplayLabels();
            for(int i=0;i<str.length;i++)
            {
                out.println(" getDisplayLabels() - " + str[i]);
            }

             out.println(" getEndIndex() - " + cv.getEndIndex());
             out.println(" getIncrement() - " + cv.getIncrement());
             out.println(" getKey(int index) "+ cv.getKey(2));
             out.println(" getKeyFieldName() - "+cv.getKeyFieldName()); 
             out.println(" getName() " + cv.getName());
             out.println(" changeName() " + cv.changeName("sail"));
             out.println(" getName() " + cv.getName());

             
             String str1[] = capi.getDefaultDisplayFields();
             for (int i=0;i<str1.length;i++)
             {
                 out.println(" default display field - "+str1[i]);
             }
             

            out.println(" getEndIndex() - " + cv.getEndIndex());
            Vector v4 = cv.getNext();
            for(int i=0;i<v4.size();i++)
            {
                out.println(" getNext() - " + v4.elementAt(i));
            }
            out.println(" getEndIndex() - " + cv.getEndIndex());
            out.println(" --------------------------------");            

            out.println(" getEndIndex() - " + cv.getEndIndex());            
            Vector v5 = cv.getNext(5);
            for(int i=0;i<v5.size();i++)
            {
                out.println(" getNext(5) - " + v5.elementAt(i));
            }
            out.println(" getEndIndex() - " + cv.getEndIndex());
            out.println(" --------------------------------");            

            out.println(" getEndIndex() - " + cv.getEndIndex());
            Vector v6 = cv.getNext(5,true);
            for(int i=0;i<v6.size();i++)
            {
                out.println(" getNext(5,true) - " + v6.elementAt(i));
            }
            out.println(" getEndIndex() - " + cv.getEndIndex());
            out.println(" --------------------------------");

            out.println(" getEndIndex() - " + cv.getEndIndex());
            Vector v7 = cv.getNext(5,false);
            for(int i=0;i<v6.size();i++)
            {
                out.println(" getNext(5,false) - " + v7.elementAt(i));
            }
            out.println(" getEndIndex() - " + cv.getEndIndex());            
            out.println(" --------------------------------");

            out.println(" getOrderByColumn() - " + cv.getOrderByColumn());            
            out.println(" getRow(3) - " + cv.getRow(3));            
            
            out.println(" getStartIndex() - " + cv.getStartIndex());
            Vector vec1 = cv.getPrevious();
            for(int i=0;i<vec1.size();i++)
            {
                out.println(" getPrevious() - " + vec1.elementAt(i));
            }
            out.println(" getStartIndex() - " + cv.getStartIndex());
            out.println(" --------------------------------");            
            
            out.println(" getStartIndex() - " + cv.getStartIndex());
            Vector vec2 = cv.getPrevious(5);
            for(int i=0;i<vec2.size();i++)
            {
                out.println(" getPrevious(5) - " + vec2.elementAt(i));
            }
            out.println(" getStartIndex() - " + cv.getStartIndex());
            out.println(" --------------------------------");            
            
            out.println(" getStartIndex() - " + cv.getStartIndex());
            Vector vec3 = cv.getPrevious(5,true);
            for(int i=0;i<vec3.size();i++)
            {
                out.println(" getPrevious(5,true) - " + vec3.elementAt(i));
            }
            out.println(" getStartIndex() - " + cv.getStartIndex());
            out.println(" --------------------------------");            
          
            out.println(" getStartIndex() - " + cv.getStartIndex());
            Vector vec4 = cv.getPrevious(5,false);
            for(int i=0;i<vec4.size();i++)
            {
                out.println(" getPrevious(5,false) - " + vec4.elementAt(i));
            }
            out.println(" getStartIndex() - " + cv.getStartIndex());
            out.println(" --------------------------------");            
            
            out.println(" getStartIndex() - " + cv.getStartIndex());
            out.println(" getincrement() - " + cv.getIncrement());
            Vector vec5 = cv.getTop();
            for(int i=0;i<vec5.size();i++)
            {
                out.println(" getTop() - " + vec5.elementAt(i));
            }
            out.println(" getEndIndex() - " + cv.getEndIndex());
            out.println(" --------------------------------"); 
            
            out.println(" getStartIndex() - " + cv.getStartIndex());
            out.println(" getincrement() - " + cv.getIncrement());
            Vector vec6 = cv.getTop(4);
            for(int i=0;i<vec6.size();i++)
            {
                out.println(" getTop(4) - " + vec6.elementAt(i));
            }
            out.println(" getEndIndex() - " + cv.getEndIndex());
            out.println(" --------------------------------");            
           
            out.println(" getTotalRowCount() - " + cv.getTotalRowCount());
            out.println(" --------------------------------");                        

            out.println(" isAscendingOrder() - " + cv.isAscendingOrder());
            out.println(" --------------------------------");

            out.println(" isBottomEnabled() - " + cv.isBottomEnabled());
            out.println(" --------------------------------");
            
            out.println(" isNextEnabled() - " + cv.isNextEnabled());
            out.println(" --------------------------------");
            
            out.println(" isPreviousEnabled() - " + cv.isPreviousEnabled());
            out.println(" --------------------------------"); 
 
            out.println(" isTopEnabled() - " + cv.isTopEnabled());
            out.println(" --------------------------------");
 
            out.println(" isShowLatest() - " + cv.isShowLatest());
            out.println(" --------------------------------");
          
            String[] strin3 = {"name","type","oid"};
            out.println(" set display fields(fields) "+cv.setDisplayFields(strin3));
            out.println("get all properties with false "+ cv.getAllProperties(false));
            out.println("get CACHE DATA "+cv.getCacheData());
            out.println(" --------------------------------");           

            String[] strin1 = {"name","type","oid"};
            String[] strin2 = {"Name","Type","OID"};
            out.println(" set display fields(fields,labels "+cv.setDisplayFields(strin1,strin2));
            out.println("get all properties with false "+ cv.getAllProperties(false));
            out.println("get CACHE DATA "+cv.getCacheData());
            out.println(" --------------------------------");

            out.println(" setIncrement - " +cv.setIncrement(3));
            out.println(" getIncrement - "+cv.getIncrement());
            out.println(" --------------------------------");

            out.println(" cv.setKeyFieldName() - " + cv.setKeyFieldName("sail"));
            out.println(" cv.getKeyFieldName() - "+cv.getKeyFieldName());
            out.println(" --------------------------------");

            out.println(" cv.setOrderByColumn(name,true) - "+cv.setOrderByColumn("name", true));
            out.println(" cv.getOrderByColumn() - "+cv.getOrderByColumn());
            out.println(" cv.isAscendingOrder() - "+cv.isAscendingOrder());
            out.println("get CACHE DATA "+cv.getCacheData());
            out.println(" --------------------------------");
            
            out.println(" cv.setOrderByColumn(name,false) - "+cv.setOrderByColumn("name", false));
            out.println(" cv.getOrderByColumn() - "+cv.getOrderByColumn());
            out.println(" cv.isAscendingOrder() - "+cv.isAscendingOrder());
            out.println("get CACHE DATA "+cv.getCacheData());
            out.println(" --------------------------------");
            
            out.println(" cv.setViewRange(1,20) - " +cv.setViewRange(1,20));
            out.println(" cv.getStartIndex()  - " + cv.getStartIndex());
            out.println("  cv.getEndIndex() - " +cv.getEndIndex());
            out.println(" --------------------------------");

            out.println("show latest - " + cv.showLatest(true));
            out.println(" --------------------------------");

            out.println("   -----*  THE END  *------   ");
            
            
        }
        catch(Exception e)
        {
            out.println("Exception is" + e);
        	e.printStackTrace();
		}
        
    } 
}        //End of Program..... 

