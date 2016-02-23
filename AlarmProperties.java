
import java.util.*;
import com.adventnet.nms.util.*;
import com.adventnet.nms.alertdb.*;

import javax.swing.*;

/**
 * AlarmProperties.java
 *
 *
 * Created: Wed Mar 23 16:20:35 2005
 *
 * @author <a href="mailto:tinku@tinku.india.adventnet.com"></a>
 * @version
 */

public class AlarmProperties implements CustomClassInterface
{
    public AlarmProperties() 
    {
       System.out.println("jo bole so nihaal,sasriya kaal"); 
    }
    
public void setProperties(Properties p[]){
        
      // System.out.println(" the properties passes is "+p[0]);
         /* JFrame f=new JFrame();   
        JOptionPane joptionPane = new JOptionPane("No AlarmSelected",JOptionPane.ERROR_MESSAGE);
        JDialog dialog = joptionPane.createDialog(f,"Error Message");
        dialog.setModal(false);
        dialog.setVisible(true); */
	
	//String urlString = "/mainLayout.do;jsessionid="+ NmsClientUtil.applet.getParameter("jsessionid") + "?selectedNode=admincomplete&selectedTab=admin";
		//NmsClientUtil.showURLInNW(NmsClientUtil.applet, urlString);
  Runtime.getRuntime().exec("java -jar C:"+File.separator+"4.7"+File.separator+"classes"+File.separator+"I18nTool.jar");
}
/*else{
          
System.out.println("properties of the Alarms"+p[0]);
}*/
        }
//} AlarmProperties
