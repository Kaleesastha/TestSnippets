
import java.util.*;
import com.adventnet.nms.util.*;
import com.adventnet.nms.alertdb.*;
import java.io.*;
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

public class Commandlinejar implements CustomClassInterface
{
    
public void setProperties(Properties p[]){
        
      // System.out.println(" the properties passes is "+p[0]);
         /* JFrame f=new JFrame();   
        JOptionPane joptionPane = new JOptionPane("No AlarmSelected",JOptionPane.ERROR_MESSAGE);
        JDialog dialog = joptionPane.createDialog(f,"Error Message");
        dialog.setModal(false);
        dialog.setVisible(true); */
	
	//String urlString = "/mainLayout.do;jsessionid="+ NmsClientUtil.applet.getParameter("jsessionid") + "?selectedNode=admincomplete&selectedTab=admin";
		//NmsClientUtil.showURLInNW(NmsClientUtil.applet, urlString);
		try{
			System.out.println("CCCCCCCCCCCCC");
//  Runtime.getRuntime().exec("C:"+File.separator+"jdk1.5.0_06"+File.separator+"bin"+File.separator+"java -jar C:"+File.separator+"NMS"+File.separator+"classes"+File.separator+"jimmy.jar");
  Runtime.getRuntime().exec("java -jar C:"+File.separator+"NMS"+File.separator+"classes"+File.separator+"jimmy.jar");
		}
		catch(Exception e){System.err.println("YYYYYYYYYYYYYY");e.printStackTrace();}
}

        }
