import java.util. *;
import com.adventnet.nms.util.NmsUiAPI;

public class TempCV implements com.adventnet.nms.util.CustomClassInterface 
{ 
    public TempCV() 
    { 
        System.out.println(" LIST CALLED"); 

    } 
    
    public void setProperties(Properties[] arg) 
    { 
        int length = arg.length; 
        String[] eventSource = new String[length]; 
        for(int i = 0 ; i < length ; i++) 
            { 
                eventSource[i] = arg[i].getProperty("name"); 
        } 
        
        String criteriaString = ""; 
        boolean first = true; 
        
        for(int i = 0 ; i <length ; i++) 
            { 
            if(first) 
                { 
                criteriaString = eventSource[i]; 
                first=false; 
                } 
            else 
                { 
                criteriaString = criteriaString+ '>' + ',' + '<' + eventSource[i] ; 
                } 
            } 
        
        
          Properties prop = new Properties(); 
          prop.put("TREE-NAME","new"); 
          Properties prop1 = new Properties(); 
          prop1.put ("source", '<' + criteriaString + '>' ); 
          prop1.put("FieldsWanted","Status=severity=55;Source=source=135;Date=time=155;Message=text=275;"); 
          String tempCV=NmsUiAPI.addTemporaryCustomView(null,"Events",prop1,prop,true,"Alerts");  
          System.out.println(" the return string is " + tempCV); 
          //String cvId = NmsUiAPI.getCustomViewID("Events","new"); 
          //boolean save = NmsUiAPI.saveTemporaryCustomView(cvId); 
          //boolean remove = NmsUiAPI.removeTemporaryCustomView(cvId);
          //boolean tempCV = NmsUiAPI.modifyTemporaryCustomView("Events",cvId,prop1,prop);            
          //System.out.println(" save ......"+save);   
 
    } 
} 























