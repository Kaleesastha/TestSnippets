
//$Id: TreeCombo.java,v 1.1 2006/08/29 13:57:02 build Exp $

package com.adventnet.nms.runtimeconfig;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import javax.swing.plaf.basic.*;
import java.util.Vector;
import java.awt.*;
import javax.swing.plaf.*;
import javax.swing.tree.*;
import java.util.Hashtable;//Added by Balan

public class TreeCombo extends JComboBox {
    static final int OFFSET = 16;

    public TreeCombo(TreeModel aTreeModel) {
        super();
        setModel(new TreeToListModel(aTreeModel));
		setFont(RuntimeConfigFrame.getCommonBuilderUIImpl().getFont());
        setRenderer(new ListEntryRenderer());
    }

        public Object getSelectedNode() {
				    return ((ListEntry)getSelectedItem()).object();
		}
        public void setSelectedNode(String nodeName) {

               if(nodeName == null) return;
		Vector vec = ((TreeToListModel)getModel()).cache;

                //Added by Balan on 24/03/03
                DefaultMutableTreeNode  treeNode    = null ;
                Object                  obj         = null ;
                Hashtable               attributes  = null ; 
               
                //Add Ends
		for(int i =0 ; i < vec.size();i++)
		{
			ListEntry listEntry = (ListEntry)vec.elementAt(i);
			Object tempObj = listEntry.object();
			if (tempObj != null)
			{
                            treeNode = (DefaultMutableTreeNode) tempObj;
                            obj      =  treeNode.getUserObject();
                            
                            String str = null;
                            if(obj != null &&  obj instanceof Hashtable)
                            {
                                   attributes = (Hashtable)obj;
                                   str        = (String)((Hashtable)obj).get("TREE-NAME"); 
                            }
                            
                            //String str = tempObj.toString(); //Commented by Balan on 24/03/03

				if(str != null && str.equals(nodeName))
				{

					setSelectedItem(listEntry);
					break;
				}
			}
		}


		}
    class TreeToListModel extends AbstractListModel implements ComboBoxModel,TreeModelListener {
        TreeModel source;
        boolean invalid = true;
        Object currentValue;
        Vector cache = new Vector();

        public TreeToListModel(TreeModel aTreeModel) {
            source = aTreeModel;
            aTreeModel.addTreeModelListener(this);
            setRenderer(new ListEntryRenderer());
            validate();
        }

        public void setSelectedItem(Object anObject) {
            currentValue = anObject;
            fireContentsChanged(this, -1, -1);
        }

        public Object getSelectedItem() {
            return currentValue;
        }

        public int getSize() {
            validate();
            return cache.size();
        }

        public Object getElementAt(int index) {
            return cache.elementAt(index);
        }

        public void treeNodesChanged(TreeModelEvent e) {
            invalid = true;
        }

        public void treeNodesInserted(TreeModelEvent e) {
            invalid = true;
        }

        public void treeNodesRemoved(TreeModelEvent e) {
            invalid = true;
        }

        public void treeStructureChanged(TreeModelEvent e) {
            invalid = true;
        }

        void validate() {
            if(invalid) {
                cache = new Vector();
                cacheTree(source.getRoot(),0);
                if(cache.size() > 0)
                    currentValue = cache.elementAt(0);
                invalid = false;             
                fireContentsChanged(this, 0, 0);
            }
        }

        void cacheTree(Object anObject,int level) {
            if(source.isLeaf(anObject))
                addListEntry(anObject,level,false);
            else {
                int c = source.getChildCount(anObject);
                int i;
                Object child;

                addListEntry(anObject,level,true);
                level++;

                for(i=0;i<c;i++) {
                    child = source.getChild(anObject,i);
                    cacheTree(child,level);
                }

                level--;
            }
        }

        void addListEntry(Object anObject,int level,boolean isNode) {
            cache.addElement(new ListEntry(anObject,level,isNode));
        }
    }

    public class ListEntry {
        Object object;
        int    level;
        boolean isNode;

        public ListEntry(Object anObject,int aLevel,boolean isNode) {
            object = anObject;
            level = aLevel;
            this.isNode = isNode;
        }

        public Object object() {
            return object;
        }

        public int level() {
            return level;
        }

        public boolean isNode() {
            return isNode;
        }
    }

    static Border emptyBorder = new EmptyBorder(0,0,0,0);

    class ListEntryRenderer extends JLabel implements ListCellRenderer  {

        Hashtable hstImageIcon = null;
        
        public ListEntryRenderer() {
            setOpaque(true);
            hstImageIcon = new Hashtable(20);
        }

        public Component getListCellRendererComponent(
            JList listbox, 
	    Object value, 
	    int index,
	    boolean isSelected,
	    boolean cellHasFocus)
	{
            //Added by Balan for SPP for Null Pointer Exception when lookand Feel is Changed
            if(RuntimeConfigFrame.getInstance() == null ) return this;
            //Add Ends
            
            ListEntry listEntry = (ListEntry)value;
            if(listEntry != null) {
                Border border;

                 // Added by Balan on 15/03/03
                DefaultMutableTreeNode treeNode =(DefaultMutableTreeNode) listEntry.object();
                Object obj = treeNode.getUserObject();

                Hashtable attributes = new Hashtable();


            String strImagePath       = null;
            int nLastIndexofSlah      = -1;
            String strNodeId          = null; 

                if(obj instanceof Hashtable)
                {
                    attributes = (Hashtable)obj;
                    String treeName = (String)((Hashtable)obj).get("TREE-NAME");
                    setText(RuntimeConfigFrame.resourceBundle.getString(treeName));

                    strImagePath       = (String)attributes.get("ICON-FILE");
                    strNodeId          = (String)attributes.get("NODE-ID");
                }
                                  
                if(strImagePath  != null &&  (strImagePath=strImagePath.trim()).length()!=0)
                {
                   nLastIndexofSlah = strImagePath.lastIndexOf("/");
                }
                
                if(nLastIndexofSlah != -1)
                {
                String     strImageName          = strImagePath.substring(nLastIndexofSlah+1).trim(); 
                String     strImageFolder        = strImagePath.substring(0,nLastIndexofSlah);
                           
                if(!hstImageIcon.containsKey(strNodeId))
                {
                    hstImageIcon.put(strNodeId,
                    RuntimeConfigFrame.getCommonBuilderUIImpl().getScaledImage(strImageName,strImageFolder , 15 , 15 ,Image.SCALE_DEFAULT ) );
                }
                    setIcon((ImageIcon) hstImageIcon.get(strNodeId));
                }
                   
                // Add Ends

                 //Commented by Balan on 15/03/03
                
                /*   setText(listEntry.object().toString());
			String node = listEntry.object().toString();
	             //Already Comented setIcon( listEntry.isNode() ? nodeIcon : leafIcon );
			if(node.equals(RuntimeConfigFrame.resourceBundle.getString("Discovery Filters")))
			{
				setIcon( RuntimeConfigFrame.getCommonBuilderUIImpl().getScaledImage("discoveryfilters.png","images/runtimeadmin" , 15 , 15 ,Image.SCALE_DEFAULT ) );
			}
			else if(node.equals(RuntimeConfigFrame.resourceBundle.getString("Discovery Configurator")))
			{
					setIcon(  RuntimeConfigFrame.getCommonBuilderUIImpl().getScaledImage("discoveryconfigurator.png","images/runtimeadmin" , 15 , 15 ,Image.SCALE_DEFAULT ) );
			}
			else if(node.equals(RuntimeConfigFrame.resourceBundle.getString("MO UI Settings")))
			{
					setIcon(  RuntimeConfigFrame.getCommonBuilderUIImpl().getScaledImage("listsettings.png","images/runtimeadmin" , 15 , 15 ,Image.SCALE_DEFAULT ) );
			}
			else if(node.equals(RuntimeConfigFrame.resourceBundle.getString("Map Filters")))
			{
					setIcon(  RuntimeConfigFrame.getCommonBuilderUIImpl().getScaledImage("mapfilter.png","images/runtimeadmin" , 15 , 15 ,Image.SCALE_DEFAULT ) );
			}
			else if(node.equals(RuntimeConfigFrame.resourceBundle.getString("Map UI Settings")))
			{
					setIcon(  RuntimeConfigFrame.getCommonBuilderUIImpl().getScaledImage("mapsetting.png","images/runtimeadmin" , 15 , 15 ,Image.SCALE_DEFAULT ) );			
			}
			else if(node.equals(RuntimeConfigFrame.resourceBundle.getString("Log Settings")))
			{
					setIcon(  RuntimeConfigFrame.getCommonBuilderUIImpl().getScaledImage("logsettings.png","images/runtimeadmin" , 15 , 15 ,Image.SCALE_DEFAULT ) );			
			}
			else if(node.equals(RuntimeConfigFrame.resourceBundle.getString("Trap Filters")))
			{
					setIcon(  RuntimeConfigFrame.getCommonBuilderUIImpl().getScaledImage("trapfilter.png","images/runtimeadmin" , 15 , 15 ,Image.SCALE_DEFAULT ) );			
			}
			else if(node.equals(RuntimeConfigFrame.resourceBundle.getString("Policy Configuration")))
			{
					setIcon(  RuntimeConfigFrame.getCommonBuilderUIImpl().getScaledImage("policyfilters.png","images/runtimeadmin", 15 , 15 ,Image.SCALE_DEFAULT ) );
			}
			else if(node.equals(RuntimeConfigFrame.resourceBundle.getString("Polling Filters")))
			{
					setIcon(  RuntimeConfigFrame.getCommonBuilderUIImpl().getScaledImage("performancefilters.png","images/runtimeadmin" , 15 , 15 ,Image.SCALE_DEFAULT ) );
			}
			else if(node.equals(RuntimeConfigFrame.resourceBundle.getString("Topology")))
			{
					setIcon(  RuntimeConfigFrame.getCommonBuilderUIImpl().getScaledImage("topology.png","images/runtimeadmin" , 15 , 15 ,Image.SCALE_DEFAULT ) );			
			}
			else if(node.equals(RuntimeConfigFrame.resourceBundle.getString("Map")))
			{
					setIcon(  RuntimeConfigFrame.getCommonBuilderUIImpl().getScaledImage("maps.png","images/runtimeadmin" , 15 , 15 ,Image.SCALE_DEFAULT ) );			
			}
			else if(node.equals(RuntimeConfigFrame.resourceBundle.getString("Fault")))
			{
					setIcon(  RuntimeConfigFrame.getCommonBuilderUIImpl().getScaledImage("fault.png","images/runtimeadmin" , 15 , 15 ,Image.SCALE_DEFAULT ) );			
			}
			else if(node.equals(RuntimeConfigFrame.resourceBundle.getString("Policy")))
			{
					setIcon(  RuntimeConfigFrame.getCommonBuilderUIImpl().getScaledImage("policy.png","images/runtimeadmin" , 15 , 15 ,Image.SCALE_DEFAULT ) );			
			}
			else if(node.equals(RuntimeConfigFrame.resourceBundle.getString("Performance")))
			{
					setIcon(  RuntimeConfigFrame.getCommonBuilderUIImpl().getScaledImage("performance.png","images/runtimeadmin" , 15 , 15 ,Image.SCALE_DEFAULT ) );			
			}
			else if(node.equals(RuntimeConfigFrame.resourceBundle.getString("Miscellaneous")))
			{
					setIcon(  RuntimeConfigFrame.getCommonBuilderUIImpl().getScaledImage("misc.png","images/runtimeadmin", 15 , 15 ,Image.SCALE_DEFAULT ) );			
			}
			else if(node.equals(RuntimeConfigFrame.resourceBundle.getString("Categories")))
			{
					setIcon(  RuntimeConfigFrame.getCommonBuilderUIImpl().getScaledImage("categories.png","images/runtimeadmin" , 15 , 15 ,Image.SCALE_DEFAULT ) );			
                                        }*/
                //Comment Ends

                if(index != -1)
                    border = new EmptyBorder(0, OFFSET * listEntry.level(), 0, 0);
                else 
                    border = emptyBorder;

                if(UIManager.getLookAndFeel().getName().equals("CDE/Motif")) {
                    if(index == -1 )
                        setOpaque(false);
                    else
                        setOpaque(true);
                } else 
                    setOpaque(true);
                
		setBorder(border); 
                if (isSelected) {
                    setBackground(UIManager.getColor("ComboBox.selectionBackground"));
                    setForeground(UIManager.getColor("ComboBox.selectionForeground"));
                } else {
                    setBackground(UIManager.getColor("ComboBox.background"));
                    setForeground(UIManager.getColor("ComboBox.foreground"));
                }
            } else {
                setText("");
            }
	    return this;
	}
    }
}






