//$Id: EventsHandling.java,v 1.1 2002/12/19 05:04:30 muthuganeshj Exp $

package com.adventnet.nms.clinet.examples;

import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.util.Vector;
import java.util.Properties;
import java.util.Hashtable;
import java.awt.Event;

import com.adventnet.nms.startclient.*;
import com.adventnet.nms.util.*;

/** This class is used to handle the  TreeNodeOperations programatically ie operations such as Adding node to the tree, Removing the node from the tree and etc.The code snippet (by code snippet we mean the methods used for firing the events alone)provided here can be used only in class which extends AbstractBaseNMSPanel.For example these code snippets can be used in ExampleNMSPanel(which extends AbstractBaseNmsPanel) present under <WebNMS/example/panel-example>.But for the purpose of testing some workaround is provided in each method,so please refer to the comments given in each method and make the corresponding changes.

If more information regarding the various Event ID's of NmsPanel(corresponding to the operation to be performed ,for example -ADD_LEAF_MENU) are needed please refer to the documentation */ 



public class EventsHandling implements CustomClassInterface 
{
    public void setProperties(Properties[] prop)
    {
	 System.out.println("TreeLabel for Events going to Change New");
         ModifyTreeLabel(); 
	 System.out.println("TreeLabel Succesfully Changed");
         System.out.println("Events Icon Going to Change");
	 ModifyTreeIcon(); 
	 System.out.println("EventsIcon Succesfully Changed");
         System.out.println("NewNode ToBe Added in NetWorkNodes");
	 AddLeafToNode(); 
	 System.out.println("NewNode Succesfully added in NetWorkNodes");
	 System.out.println("NewNode going to delete from NetWorkNodes");
         RemoveLeaffromNode(); 
	 System.out.println("NewNode Succesfully deleted in NetWorkNodes");
	 System.out.println("Panel Event Display will be changing now");
         ChangePanelEvent();
 	 System.out.println("Display Change is done");
 	 System.out.println("TreeSelection Change Testcase");
         ChangeTreeSelection();
	 System.out.println("Changing of Tree Selection Done");
 	 System.out.println("Changing TreeNode By Index Option");
         MoveNodeToNewIndex();  
	 System.out.println("Tree Change Done");
	 System.out.println("Events Move into NetWorkDB");
         MoveTreeNode(); 
	 System.out.println("Successfully Moved");
	 System.out.println("PanelEvent Closing TestCase");
         ClosePanelEvent();
	 System.out.println("Panel Closed Successfully");
	 System.out.println("Events Prop. is Change Now");
         ModifyTreeNodeProperties(); 
	 System.out.println("Property Change is Completed");
    }

/* ** Saran ** This Method is used to reinvoke the contents into Original Form  */  

   public void againSetProperties(Properties[] prop)
   {
         AgainModifyTreeLabel();
         AgainModifyTreeIcon();
      // AgainAddLeafToNode();
      // AgainRemoveLeaffromNode();
      // AgainChangePanelEvent();
      // AgainChangeTreeSelection();
      // AgainMoveNodeToNewIndex();
         AgainMoveTreeNode();
      // AgainClosePanelEvent();
      // AgainModifyTreeNodeProperties();
    }

    /** This method is used to modify the dispaly name of a tree node
        
    id    - id of the node whose display name has to be modified
     
    label - the new display name 

    hash  -  it is the HashTable which takes in the above said property names as it's key
    and the property values as it's value.    */

    public void ModifyTreeLabel()
    {
        String id      = "Events";
        String label   = "new"; 
        Hashtable hash =new Hashtable();
        hash.put("id",id);
        hash.put ("label",label);
        Event modify = new Event(this,NmsPanel.MODIFY_TREE_LABEL,hash);
        NmsPanelEvent nmsevt = new NmsPanelEvent(modify);

        //The following peace of code is just used for the purpose of testing , so in case when this method is used in any other class please remove these lines and replace it by ----> fireNmsPanelEvent(nmsevt);

        NmsPanel exam = NmsUiAPI.getNmsPanel(id);
        ((AbstractBaseNmsPanel)exam).fireNmsPanelEvent(nmsevt);
    }


    public void AgainModifyTreeLabel()
    {
        String id      = "new";
        String label   = "Events";
        Hashtable hash =new Hashtable();
        hash.put("id",id);
        hash.put ("label",label);
        Event modify = new Event(this,NmsPanel.MODIFY_TREE_LABEL,hash);
        NmsPanelEvent nmsevt = new NmsPanelEvent(modify);

/*        The following peace of code is just used for the purpose of testing ,
so in case when this method is used in any other class please remove these lines and replace it by ----> fireNmsPanelEvent(nmsevt);
*/

        NmsPanel exam = NmsUiAPI.getNmsPanel(id);
        ((AbstractBaseNmsPanel)exam).fireNmsPanelEvent(nmsevt);
    }

    /** This method is used to modify the tree icon of a tree node

      id              - id of the node whose tree icon has to be modified

      newLeafIconFile - the new tree icon 
     
      hash            -  it is the HashTable which takes in the above said property names as it's key
                         and the property values as it's value.
     */


    public void ModifyTreeIcon()
    {
        String id              = "Events";
        String newLeafIconFile = "images/redDot.png";
        Hashtable hash         =  new Hashtable();
        hash.put("id",id);
        hash.put ("newLeafIconFile",newLeafIconFile);
        Event modify = new Event(this,NmsPanel.MODIFY_TREE_ICON,hash);
        NmsPanelEvent nmsevt = new NmsPanelEvent(modify);

        //The following peace of code is just used for the purpose of testing , so in case when this method is used in any other class please remove these lines and replace it by ----> fireNmsPanelEvent(nmsevt);

        NmsPanel exam = NmsUiAPI.getNmsPanel(id);
        ((AbstractBaseNmsPanel)exam).fireNmsPanelEvent(nmsevt);
    }


    public void AgainModifyTreeIcon()
    {
        String id              = "Events";
        String newLeafIconFile = "images/event.png";
        Hashtable hash         =  new Hashtable();
        hash.put("id",id);
        hash.put ("newLeafIconFile",newLeafIconFile);
        Event modify = new Event(this,NmsPanel.MODIFY_TREE_ICON,hash);
        NmsPanelEvent nmsevt = new NmsPanelEvent(modify);

/*       The following peace of code is just used for the purpose of testing ,
so in case when this method is used in any other class please remove these lines and replace it by ----> fireNmsPanelEvent(nmsevt);
*/

        NmsPanel exam = NmsUiAPI.getNmsPanel(id);
        ((AbstractBaseNmsPanel)exam).fireNmsPanelEvent(nmsevt);
    }


    /* This method is used for adding a new node in the  tree
    
    newleafs - is a vector which contains the id of the to be added 

    tobeseletced - id of the node which is to be selected after adding the given node

    panelkey  - the key of the panel to be selected

    parent - the parent Node ID under which the given node is to be added
   
    attributelist - this is a property array which takes in all attributes asscociated with the node     *  to    be added .
           Following are some of the attribute :
           TREE-NAME       : the actual display name of the node on the tree
           ICON-FILE       : icon to be displayed for the node in the tree ,
           MENU-FILE-NAME  : the menu that is to be associate with the panel,it is the menu 
                             that is shown in the menu bar when you select the newly added node
           TREE-POPUP-MENU : it is the menu that pops up when you right cick on the newly added node
     
     table - this is a HashTable which takes in all the above said property name as 
     it's key and property value as it's value
    */
 
    public void AddLeafToNode()
    {
        Vector newleafs    = new Vector();
        String key         = "Network Database.Nodes";
        newleafs.addElement(key);
        String tobeselected=key;
        String panelkey    = key;
        String parent = "Events";
        Properties attributelist= new Properties();
        attributelist.put("ICON-FILE","images/tick.png");
        attributelist.put("MENU-FILE-NAME","dbmenu.xml");
        attributelist.put("TREE-POPUP-MENU","dbmenu.xml");
        attributelist.put("PANEL-KEY","NmsListView");
        Hashtable table    = new Hashtable();
        table.put("newleafs",newleafs);
        table.put("tobeselected",tobeselected);
        //table.put("panelkey",panelkey);
        table.put("parent",parent);
        table.put("attributeList",attributelist);    
        Event event  = new Event (this, NmsPanel.ADD_LEAF_TO_MENU, table);

        //The following peace of code is just used for the purpose of testing , so in case when this method is used in any other class please remove these lines and replace it by ----> fireNmsPanelEvent(nmsevt);

        NmsPanelEvent nmsevt = new NmsPanelEvent(event);
        NmsPanel exam = NmsUiAPI.getNmsPanel("Events");
        ((AbstractBaseNmsPanel)exam).fireNmsPanelEvent(nmsevt);
    }


   /** This method is used to remove a leaf from the tree node
     
       removeleafs - this an vector which contains the id of the node to be removed 

       panelkey - key of the panel to be removed

       parent - Parent NODE ID under which the node to be removed is present

       hash  - this is a HashTable which takes in above said proerty names as it's key and 
               value as it's value
     
     */
 
    public void RemoveLeaffromNode()
    {
        Vector removeleafs  = new Vector();
        removeleafs.addElement("Network Database.Nodes");
        String panelkey     = "Network Database.Nodes";
        String parent       = "Network Database";
        Hashtable hash      = new Hashtable();
        hash.put("removeleafs",removeleafs);
        hash.put("panelkey",panelkey);
        hash.put("parent",parent);
        Event event = new Event (this, NmsPanel.REMOVE_LEAF_FROM_MENU,hash);

        //The following peace of code is just used for the purpose of testing , so in case when this method is used in any other class please remove these lines and replace it by ----> fireNmsPanelEvent(nmsevt);

        NmsPanelEvent nmsevt = new NmsPanelEvent(event);
        NmsPanel exam = NmsUiAPI.getNmsPanel("Events");
        ((AbstractBaseNmsPanel)exam).fireNmsPanelEvent(nmsevt);
    }

   /** This method is used to change the panel selection, ie this method can be used when
       curent panel viewed has to be changed   
          
       PANEL_NAME   - panelkey of the node that is to be selected
     
       tobeselected - id of the panel to be selected
     
       hash - this is a HashTable which takes in the above said property names as it's key 
              and property value as it's value.
    */

    public void ChangePanelEvent()
    {
        String PANEL_NAME   = "EventBrowser";
        String tobeselected = "Events";
        Properties hash     =  new Properties();
        hash.put("PANEL_NAME",PANEL_NAME);
        hash.put("tobeselected",tobeselected); 
        Event event = new Event (this, NmsPanel.CHANGE_PANEL_EVENT,hash);
        NmsPanelEvent nmsevt = new NmsPanelEvent(event);

        //The following peace of code is just used for the purpose of testing , so in case when this method is used in any other class please remove these lines and replace it by ----> fireNmsPanelEvent(nmsevt);

        NmsPanel exam = NmsUiAPI.getNmsPanel("Events");
        ((AbstractBaseNmsPanel)exam).fireNmsPanelEvent(nmsevt);
    }

    /** This method is used to change the tree selection (ie the current node to be selected in the tree)
     *  provided that the panel present on it's right side remains the same as before the event was fired
     
       treename      - dispaly name of the tree node to be selected
     
       panelkey      - key of the panel associated with the treenode to be selected
     
       tobeselected  - id of the treenode to be selected
     
       hash          - this is a HashTable which takes in the above said property names as it's key 
                       and property value as it's value
    
     */


    public void ChangeTreeSelection()
    {   
        String treename = "Network Events";
        String panelkey = "EventBrowser";
        String tobeselected = "Events";
        Hashtable hash  =  new Hashtable();
        hash.put("treename",treename);
        hash.put("panelkey",panelkey);
        hash.put("tobeselected",tobeselected);
        Event event = new Event (this, NmsPanel.CHANGE_TREE_SELECTION,hash);
        NmsPanelEvent nmsevt = new NmsPanelEvent(event);

        //The following peace of code is just used for the purpose of testing , so in case when this method is used in any other class please remove these lines and replace it by ----> fireNmsPanelEvent(nmsevt);

        NmsPanel exam = NmsUiAPI.getNmsPanel("Events");
        ((AbstractBaseNmsPanel)exam).fireNmsPanelEvent(nmsevt);
    }

   /** This method is used to move a node from it's present position 
     * to a specified position in the tree ie to move it under a new parent node
    

      nodetobemoved  - this is the id of the node which is to be moved

      newparentnode  - this is the id of the parent under which the specified to 
                       is to be moved
     
      index          - it used to specify  the position of the node under it's new parent
     
      hash           -  it is the HashTable which takes in the above said property names as it's key
                       an the property values as it's value
     
     */
 
    public void MoveTreeNode()
    {   String nodetobemoved = "Events";
        String newparentnode = "Network Database";
        Integer index = new Integer(0);
        Hashtable hash       =  new Hashtable();
        hash.put("nodetobemoved",nodetobemoved);
        hash.put("newparentnode",newparentnode);
        hash.put("index",index);
        Event event = new Event (this, NmsPanel.MOVE_TREE_NODE,hash);
        NmsPanelEvent nmsevt = new NmsPanelEvent(event);

        //The following peace of code is just used for the purpose of testing , so in case when this method is used in any other class please remove these lines and replace it by ----> fireNmsPanelEvent(nmsevt);

        NmsPanel exam = NmsUiAPI.getNmsPanel("Network Database");
        ((AbstractBaseNmsPanel)exam).fireNmsPanelEvent(nmsevt); 
    }



 public void AgainMoveTreeNode()
    {   String nodetobemoved = "Network Database";
        String newparentnode = "Events";
        Integer index = new Integer(0);
        Hashtable hash       =  new Hashtable();
        hash.put("nodetobemoved",nodetobemoved);
        hash.put("newparentnode",newparentnode);
        hash.put("index",index);
        Event event = new Event (this, NmsPanel.MOVE_TREE_NODE,hash);
        NmsPanelEvent nmsevt = new NmsPanelEvent(event);

/*        The following peace of code is just used for the purpose of testing ,
so in case when this method is used in any other class please remove these lines and replace it by ----> fireNmsPanelEvent(nmsevt);
*/

        NmsPanel exam = NmsUiAPI.getNmsPanel("Events");
        ((AbstractBaseNmsPanel)exam).fireNmsPanelEvent(nmsevt);
    }


  /** This method is used to move a leaf from it's present position  to a specified position 
     *  under it's parent node
     
     id           - this is the id of the node which is to be moved.

     tobeselected - this is the id of the node which is to be selected after moving
                    the leaf to the specified index .
    
     index        - it used to specify  the position of the leaf under it's parent.

     hash         -  it is the HashTable which takes in the above said property names as it's key
                      and the property values as it's value.
    
     */
    
 
    public void MoveNodeToNewIndex()
    {
        String id           = "Failed_Objects_Map.netmap";
        String tobeselected = "Failed_Objects_Map.netmap";
        Integer newIndex    =  new Integer(3) ;
        Hashtable hash      =  new Hashtable();
        hash.put("id",id);
        hash.put("tobeselected",tobeselected);
        hash.put("newIndex",newIndex);
        Event event = new Event (this, NmsPanel.MOVE_NODE_TO_NEWINDEX,hash);
        NmsPanelEvent nmsevt = new NmsPanelEvent(event);

        //The following peace of code is just used for the purpose of testing , so in case when this method is used in any other class please remove these lines and replace it by ----> fireNmsPanelEvent(nmsevt);

        NmsPanel exam = NmsUiAPI.getNmsPanel("Events");
        ((AbstractBaseNmsPanel)exam).fireNmsPanelEvent(nmsevt); 
    }


   /** This method is used to close the specified panel

      PANEL_NAME               -   it is the id of the panel to be closed

      DEFAULT-CLOSE-OPERATION  -   it is the string which is use to specify the action 
      associated with the close operation 
              The possible actions are :
                           1.DO_NOTHING_ON_CLOSE - this action is used when the panel has to remain                                       undisturbed when tried to close that.
                           2.HIDE_ON_CLOSE- this action is used when the panel is in need 
                           to be hidden when tried close that.But note that it would't be disposed.
                           3.DISPOSE_ON_CLOSE - this action is used when the panel is
                           in need to be disposed when you tried to close that.
                           4.DISPOSE_AND_REMOVE_TREENODE_ON_CLOSE - this action is used when the 
                           panel is in need to be disposed and it's corresponding node has to be removed from                            the tree. The client restart  will bring up the node in the tree.
                           5.DISPOSE_AND_REMOVE_TREENODE_FROM_DB_ON_CLOSE - this action is used when                                     the panel is in needto be disposed and it's corresponding node has to be removed 
                           from the tree.The client restart should't bring up the node
          */
 

    public void ClosePanelEvent()
    {
        String PANEL_NAME = "EXAMPLE_ID";
        String ACTION    = "DISPOSE_ON_CLOSE";
        Properties hash  = new Properties();
        hash.put("PANEL_NAME",PANEL_NAME);
        hash.put("DEFAULT-CLOSE-OPERATION",ACTION);
        Event event   = new Event (this, NmsPanel.CLOSE_PANEL_EVENT,hash);
        NmsPanelEvent nmsevt = new NmsPanelEvent(event);

        //The following peace of code is just used for the purpose of testing , so in case when this method is used in any other class please remove these lines and replace it by ----> fireNmsPanelEvent(nmsevt);

        NmsPanel exam = NmsUiAPI.getNmsPanel("Network Database");
        ((AbstractBaseNmsPanel)exam).fireNmsPanelEvent(nmsevt);
    }

   /** This method is used to modify the  properties of a tree node

     ID       -the id of the tree node whose properties are to be modified

     hash     -it is the HashTable which takes in all the new properties   
        Some of the possible attributes to the hash are
               TREE-NAME       : the new display name
               ICON-FILE       : the new icon file
               MENU-FILE-NAME  : the new menu file,ie it is the new menu that should come up when 
               the modified tree node is selected
               TREE-POPUP-MENU : it is the new menu that should pop up when you right cick 
               on the modified node
     */
 
    public void ModifyTreeNodeProperties()
    {
        String ID      = "Events";
        String TREENAME    = "NewLabel";
        String ICONFILE    = "images/redDot.png";
        String MENUFILENAME    = "mapmenu.xml";
        String TREEPOPUPMENU     = "mapmenu.xml,CustomView";
        Hashtable hash = new Hashtable();
        hash.put("ID",ID);  
        hash.put("TREE-NAME",TREENAME);  
        hash.put("ICON-FILE",ICONFILE);  
        hash.put("MENU-FILE-NAME",MENUFILENAME); 
        hash.put("TREE-POPUP-MENU",TREEPOPUPMENU); 
        Event event = new Event (this, NmsPanel.MODIFY_TREE_NODE_PROPERTIES,hash);
        NmsPanelEvent nmsevt = new NmsPanelEvent(event);

        //The following peace of code is just used for the purpose of testing , so in case when this method is used in any other class please remove these lines and replace it by ----> fireNmsPanelEvent(nmsevt);

        NmsPanel exam = NmsUiAPI.getNmsPanel("Network Database");
        ((AbstractBaseNmsPanel)exam).fireNmsPanelEvent(nmsevt);
    }
}











