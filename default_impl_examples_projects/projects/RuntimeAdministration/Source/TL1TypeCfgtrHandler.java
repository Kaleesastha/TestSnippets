/*$Id: TL1TypeCfgtrHandler.java,v 1.1 2006/08/29 13:57:02 build Exp $*/

//package com.adventnet.nms.studio.typecfgtr.tl1 ; 
package com.adventnet.nms.runtimeconfig;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.util.*;

import com.adventnet.builder.property.MiniWrapper;
import com.adventnet.nms.common.tl1.TL1ClientSocketConn;
import com.adventnet.nms.topodb.tl1.TL1SeedReader;

public class TL1TypeCfgtrHandler 
{
    private Object[][] tl1TypeDetails;
    private Properties sampleData;
    static com.adventnet.apiutils.BuilderResourceBundle resourceBundle = null;
	
    private String[] groupTHeaders = {"Device Group","Type","Port","Status Poller","Connection Handler", "APPEND_FRONT", "APPEND_END", "Properties"};
	
    private String[] ipTHeaders = {"Device IP","Type","Port","Status Poller","Connection Handler", "APPEND_FRONT", "APPEND_END", "Properties"};

    private String[] gneTHeaders = {"GNEIP","Type","Port","Status Poller","Connection Handler", "APPEND_FRONT", "APPEND_END", "Properties"};
    
    private String[] tidTHeaders = {"TID","Type","Port","Status Poller","Connection Handler", "APPEND_FRONT", "APPEND_END", "Properties"};

	
    private DefaultTableModel gmodel ;
	
    private TL1SeedReader reader;
	
    private boolean debug = false;
	
    private HashMap map = new HashMap();
    
    private boolean isTl1SeedPresent = true;
    
    HashMap Gmap = new HashMap();
    
    HashMap Tmap = new HashMap();

    HashMap gvmodel = new HashMap();

    //    private DefaultTableModel gnemodel ;
	
    public TL1TypeCfgtrHandler()
    {
         if (TL1MainScreen.isClient)
        {
            // TL1ClientSocketConn.getInstance()
        }
        else
        {
            try 
            {
                reader = new TL1SeedReader();
                reader.readFile();
            }
            catch(Throwable e)
            {
                TL1MainScreen.isTL1 = false;
            }
        }
       
        
    }

	
    void createNewGroup(Hashtable props)
    {
        Object[] row = new Object[8];
        row[0] = props.get("deviceGroupName");
        row[1] = props.get("type");
        row[2] = props.get("tl1port");
        row[3] = props.get("uClass");
        row[4] = props.get("connectionHandler");
        row[5] = props.get("APPEND_FRONT");
        row[6] = props.get("APPEND_END");
        row[7] = props;
        gmodel.addRow(row);		
    }

    void createNewGNE(Hashtable props,DefaultTableModel ipmodel)
    {
        
        Object[] row = new Object[8];
        row[0] = props.get("ipaddress");
        row[1] = props.get("type");
        row[2] = props.get("tl1port");
        row[3] = props.get("uClass");
        row[4] = props.get("connectionHandler");
        row[5] = props.get("APPEND_FRONT");
        row[6] = props.get("APPEND_END");
        row[7] = props;
        ipmodel.addRow(row);
    }

    void createNewTID(Hashtable props,DefaultTableModel ipmodel)
    {
        Object[] row = new Object[8];
        row[0] = props.get("tid");
        row[1] = props.get("type");
        row[3] = props.get("uClass");
        row[4] = props.get("connectionHandler");
        row[5] = props.get("APPEND_FRONT");
        row[6] = props.get("APPEND_END");
        row[7] = props;
        ipmodel.addRow(row);
    }



	
    void createNewIP(Hashtable props,DefaultTableModel ipmodel)
    {
        Object[] row = new Object[8];
        row[0] = props.get("ipaddress");
        row[1] = props.get("type");
        row[2] = props.get("tl1port");
        row[3] = props.get("uClass");
        row[4] = props.get("connectionHandler");
        row[5] = props.get("APPEND_FRONT");
        row[6] = props.get("APPEND_END");
        row[7] = props;
        ipmodel.addRow(row);
    }
    
	
    void modifyGroup(int row)
    {
        Hashtable p = (Hashtable)gmodel.getValueAt(row,7);
        gmodel.setValueAt(p.get("deviceGroupName"),row,0);
        gmodel.setValueAt(p.get("type"),row,1);
        gmodel.setValueAt(p.get("tl1port"),row,2);
        gmodel.setValueAt(p.get("uClass"),row,3);
        gmodel.setValueAt(p.get("connectionHandler"),row,4);		
        gmodel.setValueAt(p.get("APPEND_FRONT"),row,5);		
        gmodel.setValueAt(p.get("APPEND_END"),row,6);		
        DefaultTableModel ipmodel = getDeviceIPModel((String)p.get("deviceGroupName"));
        if (ipmodel == null)
        {
            return;
        }
        for (int i=0; i<ipmodel.getRowCount(); i++)
        {
            ipmodel.setValueAt(updateProperties((Hashtable)p.clone(), (Hashtable)ipmodel.getValueAt(i,7)), i, 7);
        }
        DefaultTableModel gnemodel = getGNEIPModel((String)p.get("deviceGroupName"));
        if (gnemodel == null)
        {
            return;
        }
       
        for (int k=0; k<gnemodel.getRowCount(); k++)
        {
            
            Hashtable g = (Hashtable)gnemodel.getValueAt(k,7);
            if( g == null)
            {
                continue;
            }
            String gne = (String)g.get("ipaddress");
            String port = (String)g.get("tl1port");
            String group = (String)g.get("deviceGroupName");
            DefaultTableModel tidmodel =null;
            if(group != null && port != null && gne != null)
            {
                tidmodel = getTIDIPModel(gne,port,group);
            }
            if(tidmodel == null)
            {
                continue ;
            }
            for (int i=0; i<tidmodel.getRowCount(); i++)
            {
                tidmodel.setValueAt(updateProperties((Hashtable)p.clone(), (Hashtable)tidmodel.getValueAt(i,7)), i, 7);
            }
        }
    }
    
	
    void modifyIP(DefaultTableModel ipmodel,int row)
    {
        Hashtable p = (Hashtable)ipmodel.getValueAt(row,7);
        ipmodel.setValueAt(p.get("ipaddress"),row,0);
        ipmodel.setValueAt(p.get("type"),row,1);
        ipmodel.setValueAt(p.get("tl1port"),row,2);
        ipmodel.setValueAt(p.get("uClass"),row,3);
        ipmodel.setValueAt(p.get("connectionHandler"),row,4);				
        ipmodel.setValueAt(p.get("APPEND_FRONT"),row,5);		
        ipmodel.setValueAt(p.get("APPEND_END"),row,6);		
    }

    void modifyGNE(DefaultTableModel gnemodel,int row)
    {
        Hashtable p = (Hashtable)gnemodel.getValueAt(row,7);
        gnemodel.setValueAt(p.get("ipaddress"),row,0);
        gnemodel.setValueAt(p.get("type"),row,1);
        gnemodel.setValueAt(p.get("tl1port"),row,2);
        gnemodel.setValueAt(p.get("uClass"),row,3);
        gnemodel.setValueAt(p.get("connectionHandler"),row,4);				
        gnemodel.setValueAt(p.get("APPEND_FRONT"),row,5);		
        gnemodel.setValueAt(p.get("APPEND_END"),row,6);		
    }
    
    void modifyTID(DefaultTableModel tidmodel,int row)
    {
        Hashtable p = (Hashtable)tidmodel.getValueAt(row,7);
        tidmodel.setValueAt(p.get("tid"),row,0);
        tidmodel.setValueAt(p.get("type"),row,1);
        //tidmodel.setValueAt(p.get("tl1port"),row,2);
        tidmodel.setValueAt(p.get("uClass"),row,3);
        tidmodel.setValueAt(p.get("connectionHandler"),row,4);				
        tidmodel.setValueAt(p.get("APPEND_FRONT"),row,5);		
        tidmodel.setValueAt(p.get("APPEND_END"),row,6);		
    }

    void removeGroup(int row)
    {
        gmodel.removeRow(row);
    }
	
    void removeIP(DefaultTableModel ipmodel,int row)
    {
        ipmodel.removeRow(row);
    }
    void removeGNE(DefaultTableModel gnemodel,int row)
    {
        gnemodel.removeRow(row);
    }

    void removeTID(DefaultTableModel tidmodel,int row)
    {
        tidmodel.removeRow(row);
    }
	
    Hashtable getGlobalProperties()
    {
        if (TL1MainScreen.isClient)
        {
            return TL1ClientSocketConn.getInstance().getCommonProperties();
        }
        return reader.getCommonProperties();
    }
	
    Hashtable getIPGroupProperties(String gname,int grouprow)
    {
        return (Hashtable)gmodel.getValueAt(grouprow,7);
    }
    Hashtable getGneGroupProperties(DefaultTableModel gnemodel, int grouprow)
    {
        return (Hashtable)gnemodel.getValueAt(grouprow,7);
    }
    
    Hashtable getTidGroupProperties(DefaultTableModel gnemodel, int grouprow)
    {
        return (Hashtable)gnemodel.getValueAt(grouprow,7);
    }

    
	
    DefaultTableModel getDeviceGroupModel()
    {
        gmodel = new DefaultTableModel();
        gmodel.setColumnIdentifiers(locArray(groupTHeaders));
        Vector gnames = new Vector();
        if (TL1MainScreen.isClient)
        {
            gnames = TL1ClientSocketConn.getInstance().getDeviceGroupNames();
        }
        else
        {
            gnames = reader.getDeviceGroupNames();
        }
        if(debug)
        {
            System.out.println("Groups .. " + gnames);
        }
        if (gnames != null)
        {       
            for(int i = 0; i < gnames.size() ; i++)
            {
                String gname = gnames.elementAt(i).toString();
                Hashtable p = new Hashtable();
                if (TL1MainScreen.isClient)
                {
                    p = TL1ClientSocketConn.getInstance().getGroupProperties(gname);
                }
                else
                {
                    p = reader.getGroupProperties(gname);
                }
                if(debug)
                {
                    System.out.println("Group :: " + gname + "Properties ::" + p);
                }
                createNewGroup(p);
                getDeviceIPModel(gname);
                getGNEIPModel(gname);
            }
        }
        return gmodel;
    }
	
    public void saveChanges()
    {
        Hashtable propHash = null;
        Hashtable groupHash = null;
        Hashtable ipHash = null;
        Hashtable gneHash = new Hashtable();
        Hashtable tidHash = new Hashtable();
        if (TL1MainScreen.isClient)
        {
            propHash = new Hashtable();
            groupHash = new Hashtable();
            ipHash = new Hashtable();
        }
        else
        {
            reader.removeAllGroups();
            reader.removeAllIpAddress();
        }
        if (gmodel != null)
        {
            for (int i=0; i<gmodel.getRowCount(); i++)
            {
                if (TL1MainScreen.isClient)
                {
                    groupHash.put((String)gmodel.getValueAt(i, 0), (Hashtable)gmodel.getValueAt(i, 7));
                }
                else
                {
                    reader.setGroupProperties((String)gmodel.getValueAt(i,0), (Hashtable)gmodel.getValueAt(i, 7));
                }
            }
            if (propHash != null)
            {
                propHash.put("group", groupHash);
            }
        }
        if (map != null)
        {
            Set s = map.keySet();
            Iterator itr = s.iterator();
            while (itr.hasNext())
            {
                String grpName = (String)itr.next();
                DefaultTableModel ipmodel = (DefaultTableModel)map.get(grpName);
                for (int j=0; j<ipmodel.getRowCount(); j++)
                {
                    if (TL1MainScreen.isClient)
                    {
                        ipHash.put((String)ipmodel.getValueAt(j,0), (Hashtable)ipmodel.getValueAt(j, 7));
                    }
                    else
                    {
                        reader.setDeviceProperties((String)ipmodel.getValueAt(j,0), (Hashtable)ipmodel.getValueAt(j,7));
                    }
                }
            }
            //}
            if (propHash != null)
            {
                propHash.put("ip", ipHash);
            }
        }

        
        // For Gne 
        
        if (Gmap != null)
        {
            Set s = Gmap.keySet();
            Iterator itr = s.iterator();
            while (itr.hasNext())
            {
                String grpName = (String)itr.next();
                
                DefaultTableModel gnemodel = (DefaultTableModel)Gmap.get(grpName);
                
                
                for (int j=0; j<gnemodel.getRowCount(); j++)
                {
                    
                    String gneM = (String)gnemodel.getValueAt(j,0);
                    Hashtable temp = (Hashtable)gnemodel.getValueAt(j, 7);
                    String port = (String)temp.get("tl1port");
                    
                    DefaultTableModel tidMo = (DefaultTableModel)Tmap.get(gneM+":"+port+":"+grpName);
                    
                    temp.remove("tidlist");
                    if( tidMo != null && tidMo.getRowCount()!=0)
                    {
                        Hashtable tidList = new Hashtable();
                        for ( int h = 0; h < tidMo.getRowCount();h++)
                        {
                            Hashtable top = (Hashtable)tidMo.getValueAt(h, 7);
                            tidList.put((String)top.get("tid"),top);
                        }
                        if(tidList.size()>0)
                        {
                            temp.put("tidlist",tidList);
                        }
                    }
                    else
                    {
                        temp.put("group",grpName);
                    }
                    
                    Hashtable gneProp =(Hashtable)gneHash.get(gneM);
                    if(gneProp == null)
                    {
                        gneProp = new Hashtable();
                        gneProp.put(port,temp);
                        gneHash.put((String)gnemodel.getValueAt(j,0),gneProp);
                    }
                    else
                    {
                        Hashtable ht =(Hashtable)gneProp.get(port);
                        if(ht == null)
                        {
                            gneProp.put(port,temp);
                        }
                        else
                        {
                            Hashtable gt = (Hashtable)ht.get("tidlist");
                            Hashtable tid = (Hashtable)temp.get("tidlist");
                            if( tid != null)
                            {
                                updateProperties(gt,tid);   
                            }
                            
                        }
                    }
                }
            }
            if (TL1MainScreen.isClient)
            {
                if (propHash != null)
                {
                    propHash.put("GNE", gneHash);
                }
            }
            else 
            {
                reader.setGNEProperties(gneHash);
            }
        }
        
        if (TL1MainScreen.isClient)
        {
	   	    
            TL1ClientSocketConn.getInstance().handleRunTime(propHash);
        }
        else
        {
            reader.updateTL1SeedFile();
        }
    }

    
    DefaultTableModel getDeviceIPModel(String groupName)
    {
        if(map.containsKey(groupName))
        {
            return (DefaultTableModel)map.get(groupName);
        }
		
        DefaultTableModel ipmodel = new DefaultTableModel();
        ipmodel.setColumnIdentifiers(locArray(ipTHeaders));
        Vector ipnames = new Vector();
        if (TL1MainScreen.isClient)
        {
            ipnames = TL1ClientSocketConn.getInstance().getIpList(groupName);
        }
        else
        {
            ipnames = reader.getIpList(groupName);
        }
        if (ipnames != null)
        {
            for(int i = 0; i < ipnames.size();i++)
            {
                String ipname = ipnames.elementAt(i).toString();
                Hashtable p = new Hashtable();
                if (TL1MainScreen.isClient)
                {
                    p = TL1ClientSocketConn.getInstance().getDeviceProperties(ipname);
                }
                else
                {
                    p = reader.getDeviceProperties(ipname);
                }
                if(debug)
                {
                    System.out.println("IP :: " + ipname + " Properties :: " + p);
                }
                createNewIP(p,ipmodel);
            }
        }
        map.put(groupName,ipmodel);
        return ipmodel;
    }
    
    
    DefaultTableModel getGNEIPModel(String groupName)
    {
        if(Gmap.containsKey(groupName))
        {
            return (DefaultTableModel)Gmap.get(groupName);
        }
		
        DefaultTableModel gnemodel = new DefaultTableModel();
        gnemodel.setColumnIdentifiers(locArray(gneTHeaders));
        Vector ipnames = new Vector();
        
        if (TL1MainScreen.isClient)
        {
            ipnames = TL1ClientSocketConn.getInstance().getGNEList(groupName);
        }
        else
        {
            ipnames = reader.getGNEList(groupName);
        }
        
        if (ipnames != null)
        {
	    
            for(int i = 0; i < ipnames.size();i++)
            {
                String ipname = ipnames.elementAt(i).toString();
                StringTokenizer stk = new StringTokenizer(ipname,":");
                String gne = stk.nextToken();
                String port = stk.nextToken();
                
                Hashtable p ;
                
                //Hashtable p = new Hashtable();
                if (TL1MainScreen.isClient)
                {
                    
                    p = TL1ClientSocketConn.getInstance().getGatewayProperties(gne,port,groupName);
                    // code to get the Gne properties.
                    
                }
                else
                {
                    p = reader.getGatewayProperties(gne,port,groupName);
                    // code to get the Gne Properties.
                }
                
                
                if(debug)
                {
                    System.out.println("GNE :: " + ipname + " Properties :: " + p);
                }
                /* if( p != null && t != null) 
                {
                    //updateProperties(t,p);
                    createNewGNE(t,gnemodel);
                    getTIDIPModel(gne,port,groupName);
                }
                else*/
                
                if ( p != null)
                {
                    createNewGNE(p,gnemodel);
                    getTIDIPModel(gne,port,groupName);
                }
            }
        }
        Gmap.put(groupName,gnemodel);
        gvmodel.put(gnemodel,groupName);
        return gnemodel;
    }
    
    
    DefaultTableModel getTIDIPModel(String gneName,String port,String groupName)
    {
	
        DefaultTableModel tidmodel = (DefaultTableModel)Tmap.get((gneName+":"+port+":"+groupName));
        
        if(tidmodel != null)
        {
            return tidmodel;
        }
        else
        {
            tidmodel = new DefaultTableModel();
        }
        tidmodel.setColumnIdentifiers(locArray(tidTHeaders));
        
        String ipname = port;
        Vector v = new Vector();
        if (TL1MainScreen.isClient)
        {
            v = TL1ClientSocketConn.getInstance().getTargetIds(gneName,ipname);
        }
        else
        {
            v = reader.getTargetIds(gneName,ipname);
        }
	    
	       
        if(v == null || v.size() == 0)
	    {
			Tmap.put((gneName+":"+port+":"+groupName),tidmodel);
	    }
        else
        {
            for(int j = 0; j < v.size();j++)
            {
                Hashtable p = new Hashtable();
                String tid = v.elementAt(j).toString();
                if (TL1MainScreen.isClient)
                {
                    p = TL1ClientSocketConn.getInstance().getTargetIdProperties(gneName,ipname,tid);
        		}
		else
		    {
                p = reader.getTargetIdProperties(gneName,ipname,tid);
		    }
                
		
                if(debug)
                {
                    System.out.println("TID :: " + ipname + " Properties :: " + p);
                }
                String tidGroup = (String)p.get("deviceGroupName");
                if(tidGroup != null&& tidGroup.equalsIgnoreCase(groupName))
                {
                    createNewTID(p,tidmodel);
                }
                
            }
	   	    Tmap.put((gneName+":"+port+":"+groupName),tidmodel);
        }
        
        return tidmodel;
    }
    
    
    

    Hashtable updateProperties (Hashtable propToUpdate, Hashtable property)
    {
        for (Enumeration e = property.keys(); e.hasMoreElements() ;) 
        {
            Object key=e.nextElement();
            propToUpdate.put(key, property.get(key));
            
        }
        return propToUpdate;
    }

String[] locArray(String[] locarr){


for(int i=0;i <locarr.length;i++){

        locarr[i]=resourceBundle.getString(locarr[i]);

        }
return locarr ;

}

   
}



















