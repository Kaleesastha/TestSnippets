//     This program is used to check the provision in Map Tool Bar for adding extra action and 
//     changing the existing action by replacing action command with the new one. 
//     For example implemenating the new cut/copy action by specfying new actioncommand

package com.adventnet.nms.mapui;

import com.adventnet.nms.mapui.DefaultMapModel;
import com.adventnet.nms.mapui.MapClientAPI;
import com.adventnet.nms.util.NmsUiAPI; 

import java.awt.event.*;
import javax.swing.*;

public class ExtendedMap extends MapApplet
{

    public void constructUI(EditableMap emap)
    {
        emap.addActionListener(new MyListener());
        super.constructUI(emap);
    }
    
    public class MyListener implements ActionListener
    {
        public MyListener()
        {
        }
        public void actionPerformed(ActionEvent ae)
        {
            String actionComm=ae.getActionCommand();
            if(actionComm.equals("Select All"))
                {
                    String mapName=NmsUiAPI.getCurrentNodeId();
                    System.out.println(" mapName==>"+mapName);
                    System.out.println(" Selecting all map components");
                    MapClientAPI mapclientapi = MapClientAPI.getInstance();
                    DefaultMapModel defaultmapmodel = mapclientapi.getMapModel(mapName);
                    java.util.Vector vector = defaultmapmodel.getSymbols();
                    defaultmapmodel.setSelectedSymbols(vector);
                }
            
        }
    }

}
