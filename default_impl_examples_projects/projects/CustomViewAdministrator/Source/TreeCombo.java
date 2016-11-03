//$Id :$
package com.adventnet.nms.tools.CustomView;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import javax.swing.plaf.basic.*;
import java.util.*;
import java.awt.*;
import java.io.File;
import javax.swing.plaf.*;
import javax.swing.tree.*;
import com.adventnet.nms.tools.utils.ImgConv;
import java.net.URL;
public class TreeCombo extends JComboBox {
    static final int OFFSET = 16;
	Hashtable treeVsImage=null;
	Hashtable treeVsId=null;
	String image="";
	String parentid="";
	//the following two variables are used while modification since we have to set the already selected parent
	ListEntry listEntry=null;
Properties listEntryVsXMLNodeID = new Properties();

    public TreeCombo() {
			super();
    }
	public void setProperties(TreeModel aTreeModel,Hashtable hash1,Hashtable hash2)
	{
		treeVsImage=hash1;
		treeVsId=hash2;

        setModel(new TreeToListModel(aTreeModel));
        setRenderer(new ListEntryRenderer());
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
                  currentValue = cache.elementAt(1);
                invalid = false;             
                fireContentsChanged(this, 0, 0);
            }
        }

        void cacheTree(Object anObject,int level) {
            if(source.isLeaf(anObject))
				{
                	image=(String)treeVsImage.get(anObject.toString());
					parentid=(String)treeVsId.get(anObject.toString());
					addListEntry(anObject,image,parentid,level,false);
				}
            else {
                int c = source.getChildCount(anObject);
                int i;
                Object child;
                 image=(String)(treeVsImage.get(anObject.toString()));
				parentid=(String)treeVsId.get(anObject.toString());
                addListEntry(anObject,image,parentid,level,true);
                level++;

                for(i=0;i<c;i++) {
                    child = source.getChild(anObject,i);
                    cacheTree(child,level);
                }

                level--;
            }
        }

        void addListEntry(Object anObject,String image,String parentid,int level,boolean isNode) 
	{
           Object listEntry=new ListEntry(anObject,image,parentid,level,isNode);
	cache.addElement(listEntry);
	listEntryVsXMLNodeID.put(parentid,listEntry);	
	
        
    	}
}
	public void setParentNode(String parentNodeId)
	{
		if(parentNodeId == null || parentNodeId.trim().equals("") )
			return;
			
		Object obj = listEntryVsXMLNodeID.get(parentNodeId);
		
		if( obj == null )
			return;

		setSelectedItem(obj );
	}
	
    static Border emptyBorder = new EmptyBorder(0,0,0,0);

    class ListEntryRenderer extends JLabel implements ListCellRenderer  
	{
        public ListEntryRenderer() 
		{
            setOpaque(true);
        }

        public Component getListCellRendererComponent(JList listbox,Object value,int index,boolean isSelected,boolean cellHasFocus)
		{
            ListEntry listEntry = (ListEntry)value;
            if(listEntry != null) 
			{
                Border border;
                setText(listEntry.object().toString());
				String icon=listEntry.image();
            	if(icon!=null)
			   	{
			   		icon.replace('/',File.separatorChar);
			      	String iconfile="file:///"+System.getProperty("user.dir")+File.separator+icon;
					setIcon(new ImageIcon(ImgConv.getImage(iconfile)));
				} 
                if(index != -1)
                    border = new EmptyBorder(0, OFFSET * listEntry.level(), 0, 0);
                else 
                    border = emptyBorder;

                if(UIManager.getLookAndFeel().getName().equals("CDE/Motif"))
				{
                    if(index == -1 )
                        setOpaque(false);
                    else
                        setOpaque(true);
                }
				else 
                    setOpaque(true);
               	setBorder(border); 
                if (isSelected) 
				{
                    setBackground(UIManager.getColor("ComboBox.selectionBackground"));
                    setForeground(UIManager.getColor("ComboBox.selectionForeground"));
                } 
				else
				{
                    setBackground(UIManager.getColor("ComboBox.background"));
                    setForeground(UIManager.getColor("ComboBox.foreground"));
                }
            }
			else
			{
                setText("");
            }
	    return this;
	}
  }
}









