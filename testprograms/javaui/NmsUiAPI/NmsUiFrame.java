package test;

import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;

import com.adventnet.nms.startclient.*;
import com.adventnet.nms.util.*;

import org.w3c.dom.Element;
import org.w3c.dom.Document;

public class NmsUiFrame implements NmsFrame
{
    private JFrame jf;
    private JTextField jtf=new JTextField(10);
    private JTextField jtff=new JTextField(2);
    private JButton addnode=new JButton("addTreeNode");
    private JButton removenode=new JButton("removeTreeNode");
    private JButton modifynode=new JButton("modifytreeNode");
    private JButton movenode=new JButton("moveTreeNode");
    private JLabel movelabel=new JLabel("Enter New Parent");
    private JButton addcustom=new JButton("addCustomView");
    private JButton removecustom=new JButton("removeCustomView");
    private JButton modifycustom=new JButton("modifyCustomview");
    private JButton renamecustom=new JButton("renameCustomView");
    private JButton customid=new JButton("getCurrentCustomView");
    private JButton moduid=new JButton("getCurrentModuleId");
    private JButton nodeprop=new JButton("getNodePropertiess");
    private JButton panelprops=new JButton("getPanelProps.");
    private JButton customviewprop=new JButton("getCustomViewProps.");
    private JButton customviewid=new JButton("getCustomViewID");
    private JButton custviews=new JButton("getCustomViews");
    private JButton moduleids=new JButton("getModuleIds");
    private JButton cusviewroot=new JButton("getCustomViewRoot");
    private JButton addnodestotree=new JButton("addNodesToTree");
    private JButton getnmstree=new JButton("getNmsTree");
    private JButton mainapplet=new JButton("getMainApplet");
    private JButton selectnode=new JButton("selectNode");
    private JButton setselectionrow=new JButton("setSelectionRow");
    private JButton removenodefromtree=new JButton("removeNodeFromTree");
    private JButton menubar=new JButton("getJMenuBar");
    private JButton xmlnodes=new JButton("getXMLNodes");
    private JButton getmenuvector=new JButton("getMenuVector");
    private JButton setmenuvector=new JButton("setMenuVector");
    private JButton detachPanel=new JButton("detachPanel");
    private JButton detPanFromNodeid=new JButton("detachPanel-nodeid");
    private JButton detachedFrame=new JButton("getDetachedFrame");
    private JButton selectTreeNode=new JButton("selectTreeNode");
    private JButton setTheStatus=new JButton("setTheStatusOnLabel");
    private JButton getDetachedFrameid=new JButton("getDetachedFrameFromNodeId");
    private JButton getTreeNodesCorr=new JButton("getTreeNodesCorrespondingTaddActionListeneroPanelkey");
    private JButton isPanel=new JButton("isPanelInitialized");
    private JButton isPanelInit=new JButton("isPanelInitializedFromNodeid");
    private JButton insertTree=new JButton("insertTreeNode");
    private JButton getAllPanel=new JButton("getAllPanelInst.");
    private JButton getcurrent=new JButton("getCurrentNodeId");
 



    static int i=11;
    public void init(JApplet japp)
    {
        jf=new JFrame();
        jf.setBounds(200,200,500,600);
        jf.setVisible(true);
        Container cc=jf.getContentPane();
        cc.setLayout(new FlowLayout());
        cc.add(addnode); 
        cc.add(removenode);
        cc.add(modifynode);
        cc.add(movelabel);
        cc.add(jtf);
        cc.add(movenode);
        cc.add(addcustom);
        cc.add(removecustom);
        cc.add(modifycustom);
        cc.add(renamecustom);
        cc.add(customid);
        cc.add(moduid);
        cc.add(nodeprop);
        cc.add(panelprops);
        cc.add(customviewprop);
        cc.add(customviewid);
        cc.add(custviews);
        cc.add(moduleids);
        cc.add(cusviewroot);
        cc.add(addnodestotree);
        cc.add(getnmstree);
        cc.add(mainapplet);
        cc.add(selectnode);
        cc.add(setselectionrow);
        cc.add(jtff);
        cc.add(removenodefromtree);
        cc.add(menubar);
        cc.add(xmlnodes);
        cc.add(getmenuvector);
        cc.add(setmenuvector);
	cc.add(detachPanel);
	cc.add(detPanFromNodeid);
	cc.add(detachedFrame);
	cc.add(selectTreeNode);
	cc.add(setTheStatus);
	cc.add(getDetachedFrameid);
	cc.add(getTreeNodesCorr);
	cc.add(isPanel);
	cc.add(isPanelInit);
	cc.add(insertTree);
	cc.add(getAllPanel);
	cc.add(getcurrent);

        addnode.addActionListener(new AddL()); 
        removenode.addActionListener(new RemoveL());
        modifynode.addActionListener(new ModifyL());
        movenode.addActionListener(new MoveL());
        addcustom.addActionListener(new AddCustomL());
        removecustom.addActionListener(new RemoveCustomL());
        modifycustom.addActionListener(new ModifyCustomL());
        renamecustom.addActionListener(new RenameCustomL());
        customid.addActionListener(new CustomIdL());
        moduid.addActionListener(new ModuL());
        nodeprop.addActionListener(new NodeL());
        panelprops.addActionListener(new PanelPropL());
        customviewprop.addActionListener(new CustomPropL());
        customviewid.addActionListener(new CustomViewL());
        custviews.addActionListener(new CustViewsL());
        moduleids.addActionListener(new ModuleIdL());
        cusviewroot.addActionListener(new CusViewRootL());
        addnodestotree.addActionListener(new AddNodesTrL());
        getnmstree.addActionListener(new TreeL());
        mainapplet.addActionListener(new AppL());
        selectnode.addActionListener(new SelectL());
        setselectionrow.addActionListener(new SetSelectionL());
        removenodefromtree.addActionListener(new RemoveNodeL());
        menubar.addActionListener(new MenuL());
        xmlnodes.addActionListener(new XmlNodeL());
        getmenuvector.addActionListener(new GetMenuVectorL());
        setmenuvector.addActionListener(new SetMenuVectorL());
	detachPanel.addActionListener(new DetachL());
	detPanFromNodeid.addActionListener(new DetachPanL());
	detachedFrame.addActionListener(new DetachFrameL());
	selectTreeNode.addActionListener(new SelectTreeL());
	setTheStatus.addActionListener(new StatusL());
	getDetachedFrameid.addActionListener(new DetachedFrameIdL());
	getTreeNodesCorr.addActionListener(new TreeNodesCorrL());
	isPanel.addActionListener(new PanelInitL());
	isPanelInit.addActionListener(new PanelInitNodeL());
	insertTree.addActionListener(new InsertL());
	getAllPanel.addActionListener(new GetAllL());
	getcurrent.addActionListener(new GetCurrentL());
		

        /*try{
          Class.forName("SecondPanel");
          //System.out.println("class loaded");
          }catch(Exception e){e.printStackTrace();}*/
    }

    class GetCurrentL implements ActionListener
    {
        public void actionPerformed(ActionEvent ae)
        {
            System.out.println("calling current node");
            System.out.println(NmsUiAPI.getCurrentNodeId());
            System.out.println("got you have clicked? if no.. try again");
        }
    }

    class GetAllL implements ActionListener
    {
        public void actionPerformed(ActionEvent ae)
        {
            System.out.println("Getting all instances.....");
            NmsPanel[] arr=NmsUiAPI.getAllPanelInstances("com.adventnet.nms.util.ImagePanel");
            for(int i=0;i<arr.length;i++)
                System.out.println(arr[i].key());
            System.out.println("All panel ins..  got");		
        }
    }
    

    class InsertL implements ActionListener
    {
        public void actionPerformed(ActionEvent ae)
        {
            Properties p=new Properties();
            p.setProperty("TREE-NAME","brahma");
            p.setProperty("ICON-FILE","images/zoomin.png");
            p.setProperty("MENU-FILE-NAME","alertsmenu.xml");
            p.setProperty("ID","riverem");
            p.setProperty("NODE-NAME","LEVEL");
            p.setProperty("PANEL-NAME","ExampleNMSPanel");
            //p.setProperty("index","1");
            boolean test=NmsUiAPI.insertTreeNode("Network Database",p,"Network Database",1,true);
            System.out.println("inserted successfully");
            System.out.println(test);
        }
    }



    class PanelInitNodeL implements ActionListener
    {
        public void actionPerformed(ActionEvent ae)
        {
            System.out.println("panel initialised..");
            boolean test=NmsUiAPI.isPanelInitializedFromNodeId("Configuration");
            System.out.println("panel ended...");
            System.out.println(test);
        }
    }
		

    class PanelInitL implements ActionListener
    {
        public void actionPerformed(ActionEvent ae)
        {

            boolean	test=NmsUiAPI.isPanelInitialized("Fault");
            System.out.println(test);
        }
    }

    class TreeNodesCorrL implements ActionListener
    {
        public void actionPerformed(ActionEvent ae)
        {
            String[] arr=NmsUiAPI.getTreeNodesCorrespondingToPanelKey("NmsListView");
            if(arr==null){System.out.println("null");return;}
            for(int i=0;i<arr.length;i++)
                System.out.println(arr[i]);
        }
    }

    class DetachedFrameIdL implements ActionListener
    {
        public void actionPerformed(ActionEvent ae)
        {
            JFrame jf=NmsUiAPI.getDetachedFrameFromNodeId("Network Database");
            if(jf==null){System.out.println("null");return;}
            jf.addWindowListener(new WindowAdapter()
                {
                    public void windowClosing(WindowEvent we)
                    {
                        System.out.println("Window Closing called");
                    }
                });
        }
    }
    
    
    class StatusL implements ActionListener
    {
        public void actionPerformed(ActionEvent ae)
        {
            NmsUiAPI.setTheStatusOnLabel("EventBrowser","hi da","0-255-0");
        }
	
    }
    class SelectTreeL implements ActionListener
    {
        public void actionPerformed(ActionEvent ae)
        {
            System.out.println("select tree node calling...");
            NmsUiAPI.selectTreeNode("Fault",true);
            System.out.println("called ...");
        }
    }

    class DetachFrameL implements ActionListener
    {
        public void actionPerformed(ActionEvent ae)
        {
            JFrame jf=NmsUiAPI.getDetachedFrame("EventBrowser");
            if(jf==null) { System.out.println("null");return;}
            jf.addWindowListener(new WindowAdapter()
                {
                    public void windowClosing(WindowEvent we)
                    {
                        System.out.println("Window closing");
                    }
                });
        }
    }
				

    class DetachPanL implements ActionListener
    {
        public void actionPerformed(ActionEvent ae)
        {
            NmsUiAPI.detachPanelFromNodeId("Network Database");
        }
    }

    class DetachL implements ActionListener
    {
        public void actionPerformed(ActionEvent ae)
        {
		
            NmsUiAPI.detachPanel("EventBrowser");
        }
    }
    class SetMenuVectorL implements ActionListener
    {
        public void actionPerformed(ActionEvent ae)
        {
            Vector v=new Vector();
            v.addElement(new JMenu("Test1"));
            v.addElement(new JMenu("Test2"));
            // Hashtable h=NmsUiAPI.getTreeNodeProperties(NmsUiAPI.getCurrentModuleId());
            //String s=(String)h.get("PANEL-KEY");
            NmsUiAPI.setMenuVector("Example_ID",v);
	    System.out.println("over..");
        }
    }

    class GetMenuVectorL implements ActionListener
    {
        public void actionPerformed(ActionEvent ae)
        {
            String current=NmsUiAPI.getCurrentModuleId();
            Hashtable h=NmsUiAPI.getTreeNodeProperties(current);
            String s=(String)h.get("PANEL-KEY");
            Vector v=NmsUiAPI.getMenuVector("EventBrowser");
            for(int i=0;i<v.size();i++)
                {
                    System.out.println("menu vector.....");
                    JMenu jm=(JMenu)v.elementAt(i); 
                    System.out.println(jm.getText()); 
                    //System.out.println(((JMenu)v.elementAt(i)).getText());
                }
        }
    }

    class XmlNodeL implements ActionListener
    {
        public void actionPerformed(ActionEvent ae)
        {
            XMLNode[] arr=NmsUiAPI.getXMLNodes("Network Database");
            //Element[] arr=NmsUiAPI.getXMLNodes("Network Database");
            for(int i=0;i<arr.length;i++)
                System.out.println(arr[i]);
            System.out.println("nodes nil");
            //XMLNode[] arr1=NmsUiAPI.getXMLNodes("xyz");
            //Element[] arr1=NmsUiAPI.getXMLNodes("Fault");
            //	if(arr1==null) System.out.println("null node");
		
            //	else
            //for(int i=0;i<arr1.length;i++)
            //  System.out.println(arr1[i]);

        }
    }

    class MenuL implements ActionListener
    {
        public void actionPerformed(ActionEvent ae)
        {
            JMenuBar jmb=NmsUiAPI.getJMenuBar();
            int count=jmb.getMenuCount();
            for(int i=0;i<count;i++)
                System.out.println(jmb.getMenu(i).getText());
            
        }
    }
    class RemoveNodeL implements ActionListener
    {
        public void actionPerformed(ActionEvent ae)
        {
            System.out.println("removing started...");
            NmsUiAPI.removeNodeFromTree("Maps","Ganga");
            System.out.println("removing ended..."); 
        }
    }


    class SetSelectionL implements ActionListener
    {
        public void actionPerformed(ActionEvent ae)
        {
            NmsUiAPI.setSelectionRow(5);
            
            long l=NmsUiAPI.getTimeOut();
            System.out.println("get time.."+l);
            NmsUiAPI.setTimeOut(l+1000);
            long l1=NmsUiAPI.getTimeOut();
            System.out.println(l1);
        }
    }


    class SelectL implements ActionListener
    {
        public void actionPerformed(ActionEvent ae)
        {
            NmsUiAPI.selectNode("Network Database","Network Database.Nodes");
        }
    }


    class AppL implements ActionListener
    {
        public void actionPerformed(ActionEvent ae)
        {
            JApplet japp=NmsUiAPI.getMainApplet();
            System.out.println("applet......"+japp);
        }
    }


    class TreeL implements ActionListener
    {
        public void actionPerformed(ActionEvent ae)
        {
            JTree jt=NmsUiAPI.getNmsTree();
            System.out.println("......Tree"+jt);
        }
    }


    class AddNodesTrL implements ActionListener
    {
        public void actionPerformed(ActionEvent ae)
        {
            Properties p=new Properties();
            p.setProperty("newleafs","ID121");
            p.setProperty("TREE-NAME","Exp");
	    p.setProperty("ICON-FILE","images/pc.png");
	    p.setProperty("PANEL-KEY","EXAMPLE_ID");
            p.setProperty("PANEL-NAME","ExampleNMSPanel");
            Properties arr[]={p};
            boolean test=NmsUiAPI.addNodesToTree(arr,"Network Database");
            System.out.println(test);
        }
    }

    class CusViewRootL implements ActionListener
    {
        public void actionPerformed(ActionEvent ae)
        {
            
            String root=NmsUiAPI.getCustomViewRoot("Network Database","Network Database.Nodes");
            System.out.println(root);
            //String root1=NmsUiAPI.getCustomViewRoot("xyz","abc");
            /*
              System.out.println(root1);

              //String root2=NmsUiAPI.getCustomViewRoot("xyz",null);
              System.out.println(root2);
              //String root3=NmsUiAPI.getCustomViewRoot(null,"Network Database.Nodes");
              System.out.println(root3);
            */
        }
    }


    class ModuleIdL implements ActionListener
    {
        public void actionPerformed(ActionEvent ae)
        {
            String idarr[]=NmsUiAPI.getModuleIds();
            for(int i=0;i<idarr.length;i++) System.out.println(idarr[i]);
        }
    }

    class CustViewsL implements ActionListener
    {
        public void actionPerformed(ActionEvent ae)
        {
            String id=NmsUiAPI.getCurrentModuleId(); 
            String arr[]=NmsUiAPI.getCustomViews(id);
            //String arr1[]=NmsUiAPI.getCustomViews("xyz");
            for(int i=0;i<arr.length;i++)
                {
                    System.out.println(arr[i]);
                }
            /*
              if(arr1==null) System.out.println("null node");
              else
              for(int i=0;i<arr1.length;i++)
              {
              System.out.println(arr1[i]);
              }
            */
        }
    }

    class CustomViewL implements ActionListener
    {
        public void actionPerformed(ActionEvent ae)
        {
            String id1=NmsUiAPI.getCustomViewID("Events","Ramar");
            System.out.println(id1);

            String id2=NmsUiAPI.getCustomViewID("Alerts","Ramar");
            System.out.println("2.."+id2);
            String id3=NmsUiAPI.getCustomViewID("Network Database","Ramar");
            System.out.println("3"+id3);
            //String id2=NmsUiAPI.getCustomViewID("xyz","Nodes");
            /*
              System.out.println(id2);
              String id3=NmsUiAPI.getCustomViewID("Network Database","xyz");
              System.out.println(id3);
              String id4=NmsUiAPI.getCustomViewID("xyz","abc");
              System.out.println(id4);
              String id5=NmsUiAPI.getCustomViewID(null,"Nodes");
              System.out.println(id5);
              String id6=NmsUiAPI.getCustomViewID("Network Database",null);
              System.out.println(id6);
              String id7=NmsUiAPI.getCustomViewID(null,null);
              System.out.println(id7);

            */

        }
    }


    class CustomPropL implements ActionListener
    {
        public void actionPerformed(ActionEvent ae)
        {
            Hashtable h1=NmsUiAPI.getCustomViewProperties(NmsUiAPI.getCurrentModuleId(),NmsUiAPI.getCurrentCustomView());
            Hashtable h=NmsUiAPI.getCustomViewProperties("Network Database","Network Database.Interfaces");
            System.out.println("h1.."+h1);
            System.out.println("h.."+h);
        }
    }

    class PanelPropL implements ActionListener
    {
        public void actionPerformed(ActionEvent ae)
        {
            //Properties p=NmsUiAPI.getPanelProps("Example");
            //Properties p1=NmsUiAPI.getPanelProps(NmsUiAPI.getCurrentModuleId());
            //Properties p2=NmsUiAPI.getPanelProps(null);
            //System.out.println(p);
            //System.out.println(p1);
            //System.out.println(p2);
        }
    }
    class NodeL implements ActionListener
    {
        public void actionPerformed(ActionEvent ae)
        {
     
            Hashtable h2=NmsUiAPI.getTreeNodeProperties(NmsUiAPI.getCurrentNodeId());
            System.out.println("h2.."+h2);
            Hashtable h1=NmsUiAPI.getTreeNodeProperties("Network Database");
            Hashtable h3=NmsUiAPI.getTreeNodeProperties("xyz");
            System.out.println("h1..."+h1);
            System.out.println("h3"+h3);

        }
    }
    class ModuL implements ActionListener
    {
        public void actionPerformed(ActionEvent ae)
        {
            String s=NmsUiAPI.getCurrentModuleId();
            System.out.println(s);
        }
    }
    class CustomIdL implements ActionListener
    {
        public void actionPerformed(ActionEvent ae)
        {
            
            String s=NmsUiAPI.getCurrentCustomView();
            System.out.println(s);
            
        }
    }
    class RenameCustomL implements ActionListener 
    {
        public void actionPerformed(ActionEvent ae)
        {
            
            boolean test=NmsUiAPI.renameCustomView("Network Database.Nodes","New display","Maps");
            System.out.println(test);            
            

        }
    }

    class ModifyCustomL implements ActionListener
    {
        public void actionPerformed(ActionEvent ae)
        {
            
            //String cvid=NmsUiAPI.getCurrentCustomView();
            String modid=NmsUiAPI.getCurrentModuleId();
            Properties p=new Properties();
            Properties p1=new Properties();
            Hashtable h=new Hashtable();
            
            //p.put("TABLE-COLUMNS","Name=name;Node=NODEID;Shelf=SHELFID;Card=CARDID;"+"Port=PORTID;Bandwidth=bandWidth;");
            
            p.setProperty("TREE-NAME","Modified");
            p.setProperty("ICON-FILE","images/zoomin.png");
            p.setProperty("TREE-POPUP-MENU","Custom Views");
            p.setProperty("FRAME-TITLE","New Title");
            p.setProperty("MENU-FILE-NAME","framemenu.xml ");
            //h.put("FieldsWanted","Failure Objecct=entity");
            //h.put("FieldsWanted","Name=name=150;IPAddress=ipAddress=100;Status=status=70;");
            h.put("FieldsWanted","Name=name=150;");
            p.setProperty("tobeSelected","Network Database");
            h.put("attributeList",p);
            //boolean test=NmsUiAPI.modifyCustomView(modid,cvid,h,true);
            boolean test=NmsUiAPI.modifyCustomView(modid,"Network Database.Nodes",h,true);
            //boolean test=NmsUiAPI.modifyCustomView(modid,NmsUiAPI.getCurrentCustomView(),h,true);
            System.out.println(test);
            
        }
    }
    class RemoveCustomL implements ActionListener
    {
        public void actionPerformed(ActionEvent ae)
        {
            boolean test=NmsUiAPI.removeCustomView(NmsUiAPI.getCurrentModuleId(),NmsUiAPI.getCurrentCustomView(),true);
            System.out.println(test);
            
        }
    }
    class AddCustomL implements ActionListener
    {
        public void actionPerformed(ActionEvent ae)
        {
            Properties p=new Properties();
            p.setProperty("ICON-FILE","images/configmgmt.png");
            p.setProperty("TREE-POPUP-MENU","Custom views");
            p.setProperty("TABLE-POPUP-MENU","Custom views");
            p.setProperty("MENU-FILE-NAME","alertsmenu.xml");
            p.setProperty("TREE-NAME","Kaveri");
            Properties p1=new Properties();
            p1.put("FieldsWanted","Status=severity=55;Failure Object=entity=55;Alarm Group=groupName=150;Date/Time=modTime=155;Alarm Message=message=275;" ); 
            //p1.setProperty("FieldsWanted","Status=severity;Failure Object=entity");
            String root = "Network Database";
            //String root=NmsUiAPI.getCurrentModuleId();
            String s=NmsUiAPI.addCustomView("Events",root,p1,p,true);
            System.out.println(s);
            
        }
    }
    class MoveL implements ActionListener
    {
        public void actionPerformed(ActionEvent ae)
        {
            String current=NmsUiAPI.getCurrentModuleId();
            String parent=jtf.getText().trim();
            boolean test=NmsUiAPI.moveTreeNode(current,parent,"Events",1,true);
            System.out.println(test);
        }
    }
    class ModifyL implements ActionListener
    {
        public void actionPerformed(ActionEvent ae)
        {


            Properties h1=new Properties();
            h1.put("ICON-FILE","images/pc.png");
            h1.setProperty("TREE-NAME","ModifiedNodeee");
                String nodeid = "AddedNode";
            System.out.println("Parent is .... " +nodeid);
            boolean test2 = NmsUiAPI.modifyTreeNode(nodeid,"Network Database",h1,true);
            System.out.println("Tree node modified successfully..."+test2);  

                /**
            try
                {
                    
                    for (int i=0; i<10 ;i++)
                        {
                            if( i%2 == 0 )
                                {
                                    System.out.println("Node i name " +i);
                                    Properties h1=new Properties();
                                    h1.put("ICON-FILE","images/pc.png");
                                    h1.setProperty("TREE-NAME","ModifiedNodeee"+i);
                                    //h.setProperty("parent","Network Database");
                                    //h.setProperty("ID","ExampleId11");
                                    //h.setProperty("PANEL-NAME","SecondPanel");
                                    //h.setProperty("FRAME-TITLE","Modified");
                                    //h.setProperty("MENU-FILE-NAME","alertsmenu.xml");
                                    //System.out.println("Tree node props"+h);
                                    
                                    String nodeid = "ram"+i;
                                    System.out.println("Parent is .... " +nodeid);
                                    boolean test2 = NmsUiAPI.modifyTreeNode(nodeid,"Network Database",h1,true);
                                    System.out.println("Tree node modified successfully..."+test2);
                                    Thread.sleep(100);
                                    //boolean test = NmsUiAPI.removeTreeNode("Ganga","Netwodeork Database",true); 
                                    //System.out.println("Add NODE ...added ..."+test);            
                            
                                }
                            else if(i==3 || i==5 || i==7)
                                { 
                                    //Thread.sleep(1000);
                                    //System.out.println("Node i inside remove name " +i);
                                    //boolean test3 = NmsUiAPI.removeTreeNode("ram"+i,"Alerts",true);
                                    //System.out.println("Tree node modified successfully..."+test3);
                                    Thread.sleep(100);
                                }
                        }
                }catch(Exception e)
                    {
                    }
            **/
        }
        
        //  Properties h=new Properties();
        //  	    h.put("ICON-FILE","images/pc.png");
        //              h.setProperty("TREE-NAME","New NodeName");
        //              //h.setProperty("parent","Network Database");
        //              //h.setProperty("ID","ExampleId11");
        //              //h.setProperty("PANEL-NAME","SecondPanel");
        //              //h.setProperty("FRAME-TITLE","Modified");
        //              //h.setProperty("MENU-FILE-NAME","alertsmenu.xml");
        //              System.out.println("Tree node props"+h);
        //              boolean test=NmsUiAPI.modifyTreeNode("ram3","Network Database",h,true);
        //              System.out.println("Tree node modified successfully..."+test);
        //          }
    }
    
    class RemoveL implements ActionListener
    {
        public void actionPerformed(ActionEvent ae)
        {
            
            Hashtable h=NmsUiAPI.getTreeNodeProperties(NmsUiAPI.getCurrentModuleId());
            System.out.println("..prop"+h);
            System.out.println(NmsUiAPI.getCurrentModuleId());
            String parent=(String)h.get("parent");
            String id =(String)h.get("ID");
            boolean test=NmsUiAPI.removeTreeNode(id,parent,true);
            //boolean test = NmsUiAPI.removeTreeNode("kaveri","Network Database",true);
	    System.out.println(test);
        }
    }

    class AddL implements ActionListener
    {
        
        public void actionPerformed(ActionEvent ae)
        {

            //for(int k=0; k<50 ;k++)
              
            String id="AddedNode";
            Properties h= new Properties();
            
            h.put("ID",id);            
            h.put("PANEL-NAME","ExampleNMSPanel");
            h.put("FRAME-TITLE","Hi hello");
            h.put("ICON-FILE","images/configmgmt.png");
            boolean test1 = NmsUiAPI.addTreeNode("Alerts","Events",h,false);
            System.out.println("Added successfulliy..."+ test1);
            
        }
        
    }    

    public void setVisible(boolean b)
    {
        jf.setVisible(b);
    }
}















