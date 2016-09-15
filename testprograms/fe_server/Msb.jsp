
<HTML>
<HEAD>
<meta http-equiv="Pragma" content="no-cache">
<TITLE>Reports</TITLE>
<!---------------------This file is Report.jsp------------------------------>
<LINK REL=STYLESHEET TYPE="text/css" HREF="../template/nmshtmlui.css">
<!-- this is the html file that gets called to all types  of reports-->
</HEAD>
<%@page  import="java.util.*" %>
<%@page  import="java.net.*" %>
<%@page  import="com.adventnet.nms.fe.map.*" %>
<%@page  import="com.adventnet.nms.fe.map.ejb.*" %>
<%@page  import="com.adventnet.nms.fe.common.*" %>

<%

     MapSessionBean mapSession=null;
     mapSession = MapFE.getMapSession();
		try{
				Vector v = mapSession.getMapNames();
				for (int i=0;i<v.size;i++)
						System.out.println(v.elementAt(i));
			}catch(Exception e)
			{
				e.printStackTrace();
			}
/*                Properties panelProps = new Properties();
                //panelProps.put("PANEL-NAME", "com.adventnet.nms.mapui.MapApplet");
                panelProps.put("TreeIconFileName", "images/Windows.png");                                    		
                //for add  customMap,symbol cp
    
                Properties cp = new Properties();
		    	cp.put("label","Chithu");
			    cp.put("iconName","printer1.png");
    			cp.put("objectName","Sidya");
	    		cp.put("name","symb12");	
    			cp.put("mapName","ipnet.netmap");		
	    		cp.put("menuName","chithuuu");
		    	cp.put("status","Managed");
    
                Properties pr = new Properties();
                pr.put("name","v*");

                Properties ucp = new Properties();
                ucp.put("anchored","true");
	    		ucp.put("menuName","chithuuu");

                Properties ucpr = new Properties();
                ucpr.put("name","chihhhh");



    boolean ok=false;
         //ok =mapSession.deleteMap("root","vff.netmap");    


            	//ok=mapSession.addCustomMap("root","ipt.netmap",cp,pr);
                //ok =mapSession.addMap("root","mapinde",cp,1);
                //ok =mapSession.updateMap("root","ipnet",ucp);
                //ok =mapSession.updateMap("root","ipt",ucp, ucpr);
                
                Properties uac=new Properties();
    			uac.put("name","switch9");
                uac.put("label","Chithu");	

                //ok=mapSession.addSymbol("root","ipnet.netmap",uac);
               //ok =mapSession.deleteSymbol("root","mapinde.netmap","switch-rc1_port2");    
               // ok=mapSession.updateSymbol("root","ipnet.netmap",uac);
        
                Properties p  = new Properties();
                p.put("name","javab");
                p.put("label","jjsdsajjjj");
                
                Properties up  = new Properties();
                up.put("name","javab");
                up.put("label","zzzzzzzzz");


                Properties pl  = new Properties();
                pl.put("name","Chithulink");
/*pl.put("label","****");
                pl.put("source","ipnet");
                pl.put("dest","null");*/
                Properties upl  = new Properties();
                upl.put("name","newlink");
                upl.put("label","Chithu");
                upl.put("source","ipnet");
                upl.put("dest","null");

               // pl.put("","switch-rc_port2");

             //   ok=mapSession.addGroup("root","ipnet.netmap",p);
            //	ok=mapSession.deleteGroup("root","ipnet.netmap","newgroup1");
            //	ok=mapSession.updateGroup("root","ipnet.netmap",up);

//               ok=mapSession.addLink("root","192.168.111.0.netmap",pl);    
              //ok=mapSession.deleteLink("root","192.168.4.0.netmap","newlink");
             // ok=mapSession.updateLink("root","192.168.4.0.netmap",upl);    


            Properties ac=new Properties();
			ac.put("name","ContainerChithu");	
            //ac.put("label","bbbbbbb");

            Properties cac=new Properties();
	    	cac.put("name","switch-rc1_port1");	
            cac.put("label","Chithuu");	

            
            //ok=mapSession.addContainer("root", "ipnet.netmap",ac);   
            //ok=mapSession.deleteContainer("root", "ipnet.netmap","switch-rc1_port1");   
            //ok=mapSession.updateContainer("root", "ipnet.netmap",cac);   
/*
        Vector mapN = mapSession.getDefaultMapNames("root");
        Vector mapCN = mapSession.getCustomMapNames("root");    
        
    
         Vector vec = mapSession.getSymbolNames("root","ipnet.netmap");  
    //        Properties proo = mapSession.getPropertiesOfObject("root","label");

        Vector comVec = mapSession.getCompleteList("root");
        String key = "192.168.4.0"+"\t"+"ipnet.netmap";
// Properties objPro = mapSession.getPropertiesOfObject("root",key);
*/
       // Properties proob=new Properties();
        //proob.put("name","aravind");
        //proob.put("PropName","community");

       // Vector objVec = mapSession.getObjectNamesWithProps("root",proob);
       // int si = objVec.size();

     // Vector syn = mapSession.getSymbolNamesAssociatedWithObject("root","symbol","192.168.4.0");
        
      // Properties synpr = mapSession.getSymbolProperties("root","Windows_Systems_Map.netmap","saravananm");
     //   Properties mat=new Properties();
     //  mat.put("label","aravind");
//mat.put("user","***");
  //      Vector get = mapSession.getObjects("root","MapSymbol",mat);
   boolean ma = mapSession.doesTheMapExist("root","SWA.netmap");     
   */ 
%>    
<br><b> MAP ok : </b> <%=mapSession%>
<br><b>Get Obj. Names ok : </b> <%=ma%>






