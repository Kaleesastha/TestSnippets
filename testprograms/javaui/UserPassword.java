//package test;

import com.adventnet.nms.util.NmsClientUtil;
import com.adventnet.nms.util.CustomClassInterface;
import java.util.*;

public class UserPassword implements CustomClassInterface
{ 
    public void UserPassword()
    { 
        System.out.println(" *** UserPassword *** ");
    } 
    
    public void setProperties(Properties[] prop)
    { 
        System.out.println(" Inside Setproperties  ");
        String pass=NmsClientUtil.getUserPassword();
        System.out.println(" Current User Password ***"+pass);
        String dummy=NmsClientUtil.applet.getParameter("PASSWORD");
        System.out.println(" Dummy Password in applet***"+dummy+"%%%"); 
    } 
} 
  
  
