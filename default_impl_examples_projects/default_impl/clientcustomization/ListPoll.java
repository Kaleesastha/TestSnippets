/*
   $Id: ListPoll.java,v 1.1.1.1 2006/11/16 15:39:58 gramkumar Exp $
 */  
package com.adventnet.nms.mapui;
/* imports java related classes */
import java.util.*;
import javax.swing.JOptionPane;
/*import WebNMS related classes */
import com.adventnet.nms.util.*;

/**
  * This file allows the user to list staistics of selected MOs. As this class 
  * implements CustomClassInterface properties of the selected objects gets passed
  * to this class. As in the menu files only name and type are defined only those
  * values gets passed in the properties of the selected objects.
  */

public class ListPoll implements com.adventnet.nms.util.CustomClassInterface
{
Vector entityvect = new Vector ();
    boolean isAllNetwork = true;
String  name=null;
    public void setProperties(Properties p[])
   {
       
       /** Shows a joption pane and returns when no objects is selected. 
         */
       if (p == null || p.length ==0)
           {
               JOptionPane.showMessageDialog(null,NmsClientUtil.GetString("Please select the objects for which the statistics are to be listed."),NmsClientUtil.GetString("Information Message"),JOptionPane.INFORMATION_MESSAGE); 
               return;
           }
       
       for(int count =0;count < p.length;count ++)
           {
               String type =(String) p[count].get("type");
       /** If type is network there won't be statistics for Network MOs. 
         * So a check is done to see whether selected MO is network type. 
         * If network type it proceeds with other objects leaving this.
         */
             if(type != null && type.trim().equalsIgnoreCase("network"))
                   continue; 

               String name = (String)p[count].get("name");//gets the name of the selected MO.

        /** If type is interface it removes first three letter. As interface
          * name will contain IF-name it will remove IF-. Because agent can't
          *  be interfaces. If you select an interface namely IF-Linux, there
          *  will be agent Linux only. So we remove IF- and store it.
          */
               if(type != null && type.trim().equalsIgnoreCase("interface"))
                   name=name.trim().substring(3);
   

        /** If the name is already available it won't add the name in the vector.
         */

               if(!entityvect.contains(name))
                   entityvect.add(name);
         /** If atleast one among the selected objects is not an network this will
           * be set to false.
           */
               isAllNetwork = false;
           }
     
       /** If all the selected objects is network this will show an message stating
         * that all are networks. This check is done as there won't be any Statistics 
         * for networks.
         */
       if(isAllNetwork)
           {
               JOptionPane.showMessageDialog(null,NmsClientUtil.GetString("Please select the individual Nodes in the Network to view their Statistics"),NmsClientUtil.GetString("Information Message"),JOptionPane.INFORMATION_MESSAGE);  
               return;
           }
       showPoll();	
   }
    
    /** this method assigns the delimeter, in order to separate names.
      * This deleimeter are interpretted as OR operator by the server 
      */
    public void showPoll()
    {
        String str="",entitystring="";
        boolean b=true;
        
        Enumeration enume=entityvect.elements();
        while(enume.hasMoreElements())
            {
                str=(String)enume.nextElement();
                if(!b)
                    {
                        entitystring=entitystring+'>'+','+'<'+str;
                    }
                if(b)
                    {
                        entitystring=str;
                        b=false;                                        
                    }
            }
        showPoll (entitystring);
    
    }
    /** This method fires an event in order to show Performance Views
      * with statistics corresponds to selected objects in Map or 
      * Network Database. 
      */    
    public void showPoll (String entity)
    {
        if(entity == null || entity.equals(""))
            return;
        Properties prop = new Properties ();
        prop.put ("PANEL_NAME", "StatsAdminPanel");//sets the panel key
        prop.put ("agent", "<" + entity + ">");// supplies the parameter for agent
        /** sets the tree node to be selected(Performance Views)
         */
        prop.put ("tobeselected", "Stats Admin");
        /** Forms an event object to  change the panel to Performance Views.
          */
        java.awt.Event event = new java.awt.Event (this, com.adventnet.nms.startclient.NmsPanel.CHANGE_PANEL_EVENT, prop);
        NmsPanelEvent nmsevt = new NmsPanelEvent (event);// forms NmsEvent object.
        NmsClientUtil.getMainPanel().handleNmsPanelEvent(nmsevt);//fires the NmsEvent
    }
}
  
 








