//$Id: StandAloneSecurityModel.java,v 1.3 2010/11/10 10:29:01 swaminathap Exp $

package com.adventnet.security.ui;

//WebNMS imports
//import com.adventnet.nms.util.ResultEventListener;
//import com.adventnet.nms.util.ResultEventObject;

//Auth Imports
import com.adventnet.security.common.SecurityXMLParser;
import com.adventnet.nms.security.common.AuthConstants;
import com.adventnet.security.authorization.Coding;
//Java imports
import java.awt.*;
import javax.swing.*;
import javax.swing.tree.*;
import java.util.*;
import java.io.*;
import java.util.Properties;
import java.util.Hashtable;
//Java XML Imports
import org.w3c.dom.*;
import org.xml.sax.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.dom.*;
import com.adventnet.nms.security.common.AuthConstants;

import com.adventnet.security.ui.*;

//Studio imports
import com.adventnet.nms.tools.utils.FileUtil;
public class StandAloneSecurityModel extends AbstractSecurityModel implements AuthConstants
{

	Vector users_vector = null;
	Vector groups_vector = null;
	Vector views_vector = null;
	DefaultTreeModel treeModel = null;
	private SecurityXMLParser xmlParser = null;
	private boolean isInitialized = false;
	private Node operNode = null;
	private Node operDelete = null;
	private Vector newgroup = null;

	private static String Auth="AUTHORIZATION-DATA";//No I18N
	//private com.adventnet.nms.util.ResultEventAdapter adapter = null;
	//Implementation class for ResultEventAdapter for Client-Server communication.
	Node mainNode =null;
	String filename="conf"+File.separator+"securitydbData.xml";//No I18N
	String projPath=null;
	String COMMN_IMPL = "";//No I18N

	public StandAloneSecurityModel()
	{
	}
	public StandAloneSecurityModel( String commn_impl)
	{
		COMMN_IMPL = commn_impl;
		//calling init from constr for the time being
		init(new java.util.Properties());
	}

	public void fetchUserDetails()
	{
	}


    public  Hashtable getAllUserAttributes()
    {
        Hashtable userAttributes = new Hashtable();
        Vector users = getAllUserNames();
        
        if (users == null)
        {
            return null;
        }
        
        for (int i = 0; i < users.size(); i++)
        {
            String userName = (String) users.elementAt(i);
            userAttributes.put(userName, getUserAttributes(userName));
        }
        return userAttributes;
    }

    public  Properties getPropertyNamesForCustomViewScope()
    {
        Properties propNames = new Properties();
        try
        {
            String[] modules = {"PollDB", "AlertDB", "EventDB", "TopoDB", "TopoDB"};//No I18N
            String[] cvscope = {"Stats Admin", "Alerts", "Events", "Network Database", "Maps"};//No I18N
            
            for (int i = 0; i < modules.length; i++)
            {
                Vector columnNames = getAllColumnsOfModule(modules[i]);
                propNames.put(cvscope[i], columnNames);
            }
            
            Vector provisioning = new Vector();
            provisioning.addElement("TemplateName");
            propNames.put("Provisioning", provisioning);
        }
        catch (Exception e)
        {
            System.out.println(" Exception thrown :"+e.getMessage());//No I18N
        }
        return propNames;
    }

    public Vector getAllColumnsOfModule(String module)
    {
        Vector columnNames = new Vector();
        String[] moduleCalled = null;
        String[] pollDB = { "name", "id", "agent", "community", "period", "active", "oid", "logDirectly", "logFile", "save","threshold", "isMultiplePolledData", "previousSeverity", "numericType", "saveAbsolutes","timeAvg", "port", "webNMS", "groupName", "lastCounterValue", "lastTimeValue", "timeVal","policyName", "thresholdList", "dnsName", "suffix", "statsDataTableName", "pollerName","failureCount", "failureThreshold", "ownerName", "parentObj", "protocol", "savePollCount","currentSaveCount", "saveOnThreshold", "snmpVersion", "userName", "contextName" };//No I18N
        
        String[] eventDB = { "id", "text", "category", "domain", "network", "node", "entity", "severity", "time", "source","helpURL", "webNMS", "groupName", "ownerName" };//No I18N

        String[] alertDB = {"id", "groupName", "category", "severity", "previousSeverity", "entity", "createTime", "modTime","message", "source", "mapName", "who", "stage", "webNMS", "ownerName", "priority"  };//No I18N

        String[] topoDB = { "name", "displayName", "parentKey", "type", "managed", "status", "failureThreshold","failureCount", "pollInterval", "statusChangeTime", "statusUpdateTime","tester", "uClass", "classname", "webNMS", "ownerName", "statusPollEnabled","isContainer", "isGroup", "ipAddress", "netmask", "community","writeCommunity", "snmpport", "isDHCP", "baseMibs", "version", "userName","contextName", "isSNMP", "isNode", "isNetwork", "isInterface", "isRouter","discover", "discoveryStatus", "parentNetwork", "parentNetmask", "hostNetmask","sysDescr", "sysName", "sysOID", "ifIndex", "physMedia","physAddr", "ifSpeed", "ifDescr", "loginCommand", "initCommand","connectionHandler", "infoCommand", "dictionary", "sessionId", "tl1port","notifyId", "statpollCommand", "parentNode", "parentNet", "rootPort","rootCost","numPorts", "portIfIndex", "portIfDescr", "portState", "portIfSpeed","deviceStatus", "printerStatus", "printerDetectedErrStatus","consoleDispBufferText", "orbClassName", "nameServiceHost", "nameServicePort","nameRef", "interfaceName", "statusPollCommand", "statusPollIOR", "dataPollIOR" };//No I18N
        if(module.equals("PollDB"))//No I18N
        {
            moduleCalled = pollDB;
        }
        else if(module.equals("EventDB"))//No I18N
        {
            moduleCalled = eventDB;
        }
        else if(module.equals("AlertDB"))//No I18N
        {
            moduleCalled = alertDB;
        }
        else if(module.equals("TopoDB"))//No I18N
        {
            moduleCalled = topoDB; 
        }
        
        for(int i = 0; i< moduleCalled.length ; i++)
        {
            columnNames.add(moduleCalled[i]);
        }
        return columnNames;
    }
      
	public void init(java.util.Properties prop)
	{
		if(prop.get("projPath")!=null)//No I18N
		{
			projPath=(String)prop.get("projPath");//No I18N
		}
		xmlParser = new SecurityXMLParser();
		try{
			//String secFilePath = projPath+File.separator+ "WebNMS"+File.separator+filename;
			String secFilePath = projPath+File.separator+ "resources"+File.separator+filename;//No I18N
			if(!(new File(secFilePath).exists()))
			{
				FileUtil.copyFile(new File(System.getProperty("user.dir")+File.separator+filename),new File(secFilePath));//No I18N
			}
			xmlParser.parseFile(secFilePath);
			InputStream in = new FileInputStream(secFilePath);
			updateSysTime(in);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		mainNode = xmlParser.getOperationsTree();
		isInitialized = true;

		constructOperationsTree(mainNode);
	}

/*	public com.adventnet.nms.util.ResultEventAdapter getAdapterInstance()
	{
		if(adapter == null)
		{
			try
			{
				Class adClass = Class.forName(COMMN_IMPL );
				adapter = (com.adventnet.nms.util.ResultEventAdapter)adClass.newInstance();
			}
			catch(Exception e)
			{
			}
		}
		return adapter;
	}*/

	public void deRegister()
	{
		//SecuritySession.getInstance().deRegister(this);
	}

	com.adventnet.security.ui.SecurityCommonInterface scInter = null;
	public void registerWithModel(com.adventnet.security.ui.SecurityCommonInterface scInter)
	{
		this.scInter = scInter;
	}

	/* constructs a Default Tree Model from XML node */

	public DefaultTreeModel constructOperationsTree(Node xmlNode)
	{
		DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("Operation-Tree");//No I18N
		TreeObject treeOb = new TreeObject(rootNode.getUserObject());
		rootNode.setUserObject(treeOb);
		NodeList list = xmlNode.getChildNodes();
		int childCount = list.getLength();
		if(childCount > 0)
		{
			Hashtable hash = new Hashtable(5);
			for(int i = 0; i < childCount; ++i)
			{
				Node node = list.item(i);
				NamedNodeMap nodeMap = node.getAttributes();
				if(nodeMap != null)
				{
					Node parent = nodeMap.getNamedItem("parent");//No I18N
					Node child = nodeMap.getNamedItem("child");//No I18N
					Vector vect = (Vector) hash.get(parent.getNodeValue());
					if(vect != null)
					{
						vect.addElement(child.getNodeValue());
					}
					else
					{
						vect = new Vector(5,5);
						vect.addElement(child.getNodeValue());
						hash.put(parent.getNodeValue(),vect);
					}
				}
			}
			removeDuplicateEntries(hash);
			Hashtable nodeHash = new Hashtable(5);
			for(Enumeration enumerate = hash.keys();enumerate.hasMoreElements();)
			{
				String parent = (String)enumerate.nextElement();
				DefaultMutableTreeNode childNode = getChildNode(parent,hash,nodeHash);
				nodeHash.put(parent,childNode);
			}

			for(Enumeration enum1 = nodeHash.elements(); enum1.hasMoreElements();)
			{
				DefaultMutableTreeNode childNode = (DefaultMutableTreeNode) enum1.nextElement();
				TreeObject childOb = new TreeObject(childNode.getUserObject());
				childNode.setUserObject(childOb);
				rootNode.add(childNode);
			}
		}
		treeModel = new DefaultTreeModel(rootNode);
		this.treeModel = treeModel;

		return treeModel;
	}
	private void removeDuplicateEntries(Hashtable hash)
	{
		for(Enumeration enumerate = hash.keys();enumerate.hasMoreElements();)
		{
			String parent = (String)enumerate.nextElement();
			Vector childVect = (Vector)hash.get(parent);
			for(Enumeration enum1 = hash.elements();enum1.hasMoreElements();)
			{
				Vector childVect1 = (Vector)enum1.nextElement();
				if(childVect1.contains(parent))
				{
					for(int i=0; i < childVect.size() ; i++)
					{
						String child = (String)childVect.elementAt(i);
						childVect1.remove(child);
					}
				}
			}
		}
	}

	private DefaultMutableTreeNode getChildNode(String parent, Hashtable hash, Hashtable nodeHash)
	{
		Vector vect =(Vector)hash.remove(parent);
		DefaultMutableTreeNode treeNode = new DefaultMutableTreeNode(parent);
		for(Enumeration enumerate = vect.elements();enumerate.hasMoreElements();)
		{
			String element = (String) enumerate.nextElement();
			DefaultMutableTreeNode childNode;
			if(nodeHash.containsKey(element))
			{
				childNode = (DefaultMutableTreeNode) nodeHash.remove(element);
				TreeObject treeOb = new TreeObject(childNode.getUserObject());
				childNode.setUserObject(treeOb);
			}
			else
			{
				if(hash.containsKey(element))
				{
					childNode = getChildNode(element,hash,nodeHash);
					TreeObject treeOb = new TreeObject(childNode.getUserObject());
					childNode.setUserObject(treeOb);
				}
				else
				{
					childNode = new DefaultMutableTreeNode(element);
					TreeObject treeOb = new TreeObject(childNode.getUserObject());
					childNode.setUserObject(treeOb);
				}
			}
			treeNode.add(childNode);
		}
		return treeNode;
	}
	//	public void setUserData(String uname,String pwd,Vector groups)
	public void setUserData(String uname,String pwd,Vector groups, Hashtable addoper,Hashtable modoper,String descName)
	{
		try
		{
            if(pwd.equals(""))
            {
                pwd = uname;
            }
            byte[] userData = xmlParser.addOrModifyUser(uname,pwd,groups, addoper, modoper,descName);
			Node output=getByteAsXMLNode(userData);
          
			if(addUserData(output,uname))
			{
				addUserGroupData(output,uname);
				addViewOperations(output);
				addViewGroupData(output);
				addUserConfData(output,uname);
			}
			else
			{
				JOptionPane.showMessageDialog(null,"The user "+ uname + " already exists","Message",JOptionPane.ERROR_MESSAGE);//No I18N
			}
			//isInitialized = true;
			constructOperationsTree(mainNode);
			scInter.fireDataChanged();
		}
		catch(Exception ioex)
		{
				ioex.printStackTrace();
		}
	}

	public void modifyUserData(String uname,String pwd, Vector groups, Hashtable addoper,Hashtable modoper,String descName)
	{
		try
		{
			byte[] userData = xmlParser.addOrModifyUser(uname,pwd,groups, addoper, modoper,descName);
			Node output=getByteAsXMLNode(userData);
			addUserGroupData(output,uname);
			constructOperationsTree(mainNode);
			scInter.fireDataChanged();
		}
		catch(Exception ioex)
		{

		}


	}

	public void modifyUserGroupData(String group,Vector users)
	{
		try
		{
			byte[] userData = xmlParser.addUsers(group, users);
			Node output=getByteAsXMLNode(userData);
			removeGroupName(group);
			addUserGroupData(output,group);
			constructOperationsTree(mainNode);
			scInter.fireDataChanged();
		}
		catch(Exception ioex)
		{
			ioex.printStackTrace();
		}
	}
	private void removeGroupName(String groupName)
	{
		Node groupNode=xmlParser.getAuthNode("USER-GROUP");//No I18N
		NodeList childList=groupNode.getChildNodes();
		for(int i=childList.getLength()-1;i>=0;i--)
		{
			Node child=childList.item(i);
			if(child.getNodeType()==Node.ELEMENT_NODE && ((Element)child).getAttribute("groupname").equals(groupName))//No I18N
			{
				groupNode.removeChild(child);
			}
		}

	}
	public void modifyGroupData(String gname,Vector views)
	{
		try
		{
			ByteArrayOutputStream byteStream = new ByteArrayOutputStream ();
			DataOutputStream outp = new DataOutputStream (byteStream);
			outp.writeInt(AuthConstants.MODIFY_GROUP_VIEWS);
			byte[] groupData = xmlParser.addOrModifyGroupView(gname,views);
			int len = groupData.length;
			outp.writeInt(len);
			outp.writeBytes(new String(groupData));
			byte[] bytes = byteStream.toByteArray();
			//getAdapterInstance().send(bytes);
			outp.close();
			byteStream.reset();
			byteStream.close();
		}
		catch(Exception ioex)
		{

		}


	}


	public void addGroupOperData(String gname,Hashtable addoper,Hashtable modoper)
	{
		try
		{
			byte[] groupData;
			if(isGroupExist(gname) && (addoper != null))
			{
				JOptionPane.showMessageDialog(null,"The group "+ gname + " already exists","Message",JOptionPane.ERROR_MESSAGE);//No I18N
			}
			else
			{
			   	if(addoper == null)
				{	
					groupData = xmlParser.addOrModifyOperationsForGroup(gname,modoper,addoper);					
				}
				else
				{				
					groupData = xmlParser.addOrModifyOperationsForGroup(gname,addoper,modoper);				
				}

				Node output=getByteAsXMLNode(groupData);	

                updateOwnerName(output);
				addViewGroupData(output);
				addViewOperations(output);
			}
		}
		catch(Exception ioex)
		{
			ioex.printStackTrace();
		}
		constructOperationsTree(mainNode);
		scInter.fireDataChanged();
	}
    
    private void updateOwnerName(Node rootNode) {

        NodeList mainpasswdlist=((Element)rootNode).getElementsByTagName("VIEW-GROUP");//No Internationalisation
        //Node mainpasswdNode=mainpasswdlist.item(0);
        NodeList nlist=((Element)mainpasswdlist.item(0)).getElementsByTagName("DATA");//No Internationalisation
        for(int i =0;i<nlist.getLength();i++) {
            Node dataNode=nlist.item(i);
            ((Element)dataNode).setAttribute("ownername","NULL");//No Internationalisation
        }
    }

	private boolean isGroupExist(String gname)
	{
		Vector vec=xmlParser.getAllGroups();
		if(vec.contains(gname))
		{
			return true;
		}
		return false;
	}

	public void setGroupData(String gname, Vector views)
	{
		try
		{
			ByteArrayOutputStream byteStream = new ByteArrayOutputStream ();
			DataOutputStream outp = new DataOutputStream (byteStream);
			outp.writeInt(AuthConstants.ADD_GROUP_VIEWS);
			byte[] groupData = xmlParser.addOrModifyGroupView(gname,views);

			int len = groupData.length;
			outp.writeInt(len);
			outp.writeBytes(new String(groupData));
			byte[] bytes = byteStream.toByteArray();
			//ngetAdapterInstance().send(bytes);
			outp.close();
			byteStream.reset();
			byteStream.close();
		}
		catch(Exception ioex)
		{
		}
	}

	public void deleteUser(String uname)
	{
		try
		{
			//Code to delete the user.
			Node userPwd = xmlParser.getAuthNode("USER-PASSWD");//No I18N
			Node groupNode = xmlParser.getAuthNode("USER-GROUP");//No I18N
			Node confNode = xmlParser.getAuthNode("USER-CONF");//No I18N
			deleteUserNames(userPwd,uname);
			deleteUserNames(groupNode,uname);
			deleteUserNames(confNode,uname);
			//It came to deleting the user files.
			deleteUsersFiles(uname);
		}
		catch(Exception ioex)
		{
			ioex.printStackTrace();
		}
		constructOperationsTree(mainNode);
		scInter.fireDataChanged();
	}
	private void deleteUserNames(Node userNode,String username)
	{
		NodeList dataList= ((Element)userNode).getElementsByTagName("DATA");//No I18N
		for(int i=dataList.getLength()-1;i>=0;i--)
		{
			Node dataNode=dataList.item(i);
			if(((Element)dataNode).getAttribute("username").equals(username))//No I18N
			{
				userNode.removeChild(dataNode);
			}
		}
	}
	public void changePassword(String uname, String newpass)
	{
		try
		{
			//code to change the password.
			Node userPwdNode=xmlParser.getAuthNode("USER-PASSWD");//No I18N
			NodeList userList=((Element)userPwdNode).getElementsByTagName("DATA");//No I18N
			for(int i=0;i<userList.getLength();i++)
			{
				Node userNode = userList.item(i);
				if(((Element)userNode).getAttribute("username").equals(uname))//No I18N
				{
					((Element)userNode).setAttribute("password",Coding.convertToNewBase(newpass));//No I18N
				}
			}
			constructOperationsTree(mainNode);
			scInter.fireDataChanged();
		}
		catch(Exception ioex)
		{

		}


	}

	public void deleteGroup(String gname)
	{
		try
		{
			//Code for deleting the group name from the user group node.
			Node viewGroupNode=xmlParser.getAuthNode("USER-GROUP");//No I18N
			NodeList userList=((Element)viewGroupNode).getElementsByTagName("DATA");//No I18N
			for(int i=userList.getLength()-1;i>=0;i--)
			{
				Node userNode = userList.item(i);
				if(((Element)userNode).getAttribute("groupname").equals(gname))//No I18N
				{
					viewGroupNode.removeChild(userNode);
				}
			}
			viewGroupNode=xmlParser.getAuthNode("VIEW-GROUP");//No I18N
			userList=((Element)viewGroupNode).getElementsByTagName("DATA");//No I18N
			for(int i=userList.getLength()-1;i>=0;i--)
			{
				Node userNode = userList.item(i);
				if(((Element)userNode).getAttribute("groupname").equals(gname))//No I18N
				{
					viewGroupNode.removeChild(userNode);
				}
			}

		}
		catch(Exception ioex)
		{
			ioex.printStackTrace();
		}
		constructOperationsTree(mainNode);
		scInter.fireDataChanged();
	}

	public void addViewProp(String gpname,String opname,Properties modoper)
	{
		try
		{
			byte[] userData = xmlParser.createScope(gpname,opname, modoper);
			Node output=getByteAsXMLNode(userData);
			addViewGroupData(output);
			addViewOperations(output);
			addViewPropertyNode(output,gpname+" "+opname+" "+"Scope");//No I18N
			constructOperationsTree(mainNode);
			scInter.fireDataChanged();
		}
		catch(Exception ioex)
		{

		}

	}
	private void addViewPropertyNode(Node viewProp,String viewname)
	{
		Node viewPropNode=xmlParser.getAuthNode("VIEW-PROPERTY");//No I18N
		NodeList viewnode=((Element)viewProp).getElementsByTagName("VIEW-PROPERTY");//No I18N
		if(viewnode==null || viewnode.item(0)==null)
			 return;
		NodeList nl2=((Element)viewnode.item(0)).getElementsByTagName("DATA");//No I18N
		removeViewPropertyNode(viewPropNode,viewname);
		Element dataNode=viewPropNode.getOwnerDocument().createElement("DATA");//No I18N
		dataNode.setAttribute("viewname",viewname);//No I18N
	    for(int i=0;i<nl2.getLength();i++)
		{
		Element propNode=viewPropNode.getOwnerDocument().createElement("PROPERTY");//No I18N
		propNode.setAttribute("propname",((Element)nl2.item(i)).getAttribute("propertyname"));//No I18N
		propNode.setAttribute("propvalue",((Element)nl2.item(i)).getAttribute("propertyvalue"));//No I18N
		dataNode.appendChild(propNode);
		}
		viewPropNode.appendChild(dataNode);
	}

	public void removeViewPropertyNode(Node viewNode,String viewname)
	{
		NodeList nl= ((Element)viewNode).getElementsByTagName("DATA");//No I18N
		if(nl==null)
		{
			return;
		}
		for(int i=nl.getLength()-1;i>=0;i--)
		{
			Node tempNode=nl.item(i);
			if(((Element)tempNode).getAttribute("viewname").equals(viewname))//No I18N
			{
				viewNode.removeChild(tempNode);
			}
		}
	}
	public void addViewOp(String vname,java.util.Properties prop,Hashtable hash)
	{

		try
		{
			ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
			DataOutputStream outp = new DataOutputStream (byteStream);
			if( (prop.size() == 0) || (prop == null))
			{
				prop = null;
			}
			if(hash.size() == 0)
			{
				outp.writeInt(AuthConstants.ADD_AUTH_VIEWS);
				hash = null;
			}
			else
			{
				outp.writeInt(AuthConstants.ADD_AUTH_VIEWS_WITH_OPER);
			}
			byte[] viewData = xmlParser.addOrModifyOrDeleteViewPropWithOper(vname,prop,hash);
			int len = viewData.length;
			outp.writeInt(len);
			outp.writeBytes(new String(viewData));
			byte[] bytes = byteStream.toByteArray();
			//getAdapterInstance().send(bytes);
			outp.close();
			byteStream.reset();
			byteStream.close();
		}
		catch(Exception ioex)
		{
			//ioex.printStackTrace();
		}


	}


	public void modViewOp(String vname,java.util.Properties prop,Hashtable hash)
	{


		try
		{
			ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
			DataOutputStream outp = new DataOutputStream (byteStream);
			if( (prop.size() == 0) || (prop == null))
			{
				prop = null;
			}
			if(hash.size() == 0)
			{

				outp.writeInt(AuthConstants.MODIFY_AUTH_VIEWS);
				hash = null;
			}
			else
			{
				outp.writeInt(AuthConstants.MODIFY_AUTH_VIEWS_WITH_OPER);
			}
			byte[] viewData = xmlParser.addOrModifyOrDeleteViewPropWithOper(vname,prop,hash);
			int len = viewData.length;
			outp.writeInt(len);
			outp.writeBytes(new String(viewData));
			byte[] bytes = byteStream.toByteArray();
			//getAdapterInstance().send(bytes);
			outp.close();
			byteStream.reset();
			byteStream.close();
		}
		catch(Exception ioex)
		{
			ioex.printStackTrace();
		}


	}

	public void delViewOp(String vname,java.util.Properties prop,Hashtable hash)
	{

		try
		{
			ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
			DataOutputStream outp = new DataOutputStream (byteStream);

			if(hash.size() == 0)
			{
				outp.writeInt(AuthConstants.DELETE_AUTHVIEWS);
				hash = null;
			}
			else
			{
				outp.writeInt(AuthConstants.DELETE_AUTH_VIEWS_WITH_OPER);
			}

			byte[] viewData = xmlParser.addOrModifyOrDeleteViewPropWithOper(vname,prop,hash);
			int len = viewData.length;
			outp.writeInt(len);
			outp.writeBytes(new String(viewData));
			byte[] bytes = byteStream.toByteArray();
			//getAdapterInstance().send(bytes);
			outp.close();
			byteStream.reset();
			byteStream.close();
		}
		catch(Exception ioex)
		{

		}

	}


	//My methods

	public void modifyOper_new(String vname,Hashtable hash)
	{

		try
		{
			byte[] viewData = xmlParser.addOrModifyUser_new(vname,hash);
			Node output=getByteAsXMLNode(viewData);
			addViewGroupData(output);
			addViewOperations(output);

		}
		catch(Exception ioex)
		{

		}
		constructOperationsTree(mainNode);
		scInter.fireDataChanged();

	}






	public DefaultTreeModel getOperationsTree()
	{

		Node mainNode= xmlParser.getOperationsTree();
		return constructOperationsTree(mainNode);

	}



	public Hashtable getViewOperations(String userview)
	{
		return xmlParser.getOperationsForViews(userview);
	}

	public Properties getViewProperties(String viewName){
		return xmlParser.getAllProperties(viewName);
	}



    /* Added Newly by asiva */

    private static String OPERATION_TREE = "OPERATION-TREE";//No Internationalisation
    private static String DATA = "DATA";//No Internationalisation

    Document xmlData=null;
	Element rootNodeOper=null;
	Element deleteCacheNode=null;
    
    DocumentBuilderFactory docBuilderfactorys = null;
	DocumentBuilder docBuilder = null;
	Document xmlDoc = null;

    
	private Node addOperTreeToCache(String parentOper,String childOper,String isLeaf)
	{
		Element node=null;
		if(parentOper!=null && childOper!=null && isLeaf!=null)
		{
			node = xmlData.createElement(DATA);
            node.setAttribute("parent",parentOper);//No I18N
			node.setAttribute("child",childOper);//No I18N
			node.setAttribute("leaf",isLeaf);//No I18N
		}
		return node;
	}	

    public Node getOperationTreeFromCache()
	{
		Node rootNode=xmlParser.getOperationsTree();
		Document owner=rootNode.getOwnerDocument();
		NodeList nl= rootNodeOper.getChildNodes();
		for(int i=0;i<nl.getLength();i++)
		{
			Node node1=nl.item(i);
            if(!isNodePresent(rootNode, node1)) {
                Node nodes=owner.importNode(node1,true);
                rootNode.appendChild(nodes);
            }
		}
        

		return rootNode;
	}


	public void addOperation( String parent, String child, String leaf)
	{
        if(xmlData == null) {
            try{
                docBuilderfactorys = DocumentBuilderFactory.newInstance();
                docBuilder = docBuilderfactorys.newDocumentBuilder();
                xmlData=docBuilder.newDocument();
                rootNodeOper=xmlData.createElement(OPERATION_TREE); 
                
            }catch(Exception e) {
            }       
        }
        if(rootNodeOper == null)
			rootNodeOper=xmlData.createElement(OPERATION_TREE); 
		Document owner=rootNodeOper.getOwnerDocument();
		if(parent!=null && child!=null && leaf!=null)
		{
			Node n=addOperTreeToCache(parent,child,leaf);
			Node node1=owner.importNode(n,true);
			rootNodeOper.appendChild(node1);
            Node mainNode= getOperationTreeFromCache();
            getParentNode(mainNode, parent, child, owner);

		}
        operNode = rootNodeOper;
	}



	public void removeOperation(String parent, String child, String leaf)
	{
        if(xmlData == null) {
            try{
                docBuilderfactorys = DocumentBuilderFactory.newInstance();
                docBuilder = docBuilderfactorys.newDocumentBuilder();
                xmlData=docBuilder.newDocument();
                deleteCacheNode=xmlData.createElement(OPERATION_TREE); 
                
            }catch(Exception e) {
            }       
        }
        
        if(deleteCacheNode == null)
			deleteCacheNode=xmlData.createElement(OPERATION_TREE); 
		Document owner=deleteCacheNode.getOwnerDocument();
		if(parent!=null && child!=null && leaf!=null)
		{
			Node n=addOperTreeToCache(parent,child,leaf);
            if(operNode != null && isNodePresent(operNode,n)) {
                removeChildNodeFromCache(operNode,child);
            }
            Node mainNode= xmlParser.getOperationsTree();
            getChildNode(mainNode, child);
		}
        operDelete = deleteCacheNode;
    }

    private void removeChildNodeFromCache(Node node, String nodeName)
    {
		if (node == null || nodeName == null)
			return;
		NodeList nodeList=node.getChildNodes();
		if ( nodeList == null)
			return;

		for( int i=0;i<nodeList.getLength();++i)
		{
			Node childNode=nodeList.item(i);
			if ( childNode.getNodeType() == Node.ELEMENT_NODE)
			{
                NamedNodeMap nmn = childNode.getAttributes();
                String parentNode = nmn.getNamedItem("parent").getNodeValue();//No I18N
                String childNode1 = nmn.getNamedItem("child").getNodeValue();//No I18N
                String leaf = nmn.getNamedItem("leaf").getNodeValue();//No I18N

                if(childNode1.equals(nodeName)) {
                    node.removeChild(childNode);
                }
                if( parentNode.equals(nodeName)) 
                {
                    removeChildNodeFromCache(node, childNode1);
                }
            }
		}
	}



    private void getParentNode(Node node, String parent, String nodeName, Document owner)
	{
		if (node == null || nodeName == null)
			return;
		NodeList nodeList=node.getChildNodes();
		if ( nodeList == null)
			return;

		for( int i=0;i<nodeList.getLength();++i)
		{
			Node childNode=nodeList.item(i);
			if ( childNode.getNodeType() == Node.ELEMENT_NODE)
			{
                NamedNodeMap nmn = childNode.getAttributes();
                String parentNode = nmn.getNamedItem("parent").getNodeValue();//No I18N
                String childNode1 = nmn.getNamedItem("child").getNodeValue();//No I18N
                String leaf = nmn.getNamedItem("leaf").getNodeValue();//No I18N

                if(childNode1.equals(parent) && (!parentNode.equals("Operation Tree Root"))) {//No I18N
                    Node n=addOperTreeToCache(parentNode,nodeName,leaf);
                  
                    if(!isNodePresent(rootNodeOper, n)) {
                        rootNodeOper.appendChild(n);
                    }
                    getParentNode(node, parentNode, nodeName, owner);
                }
            }
		}

	}

    private boolean isNodePresent(Node mainNode, Node subNode)
	{
        NodeList mainNodeList = mainNode.getChildNodes();
        NamedNodeMap nnm=subNode.getAttributes();

        for(int i=mainNodeList.getLength()-1;i>=0;i--) {
            Node child=mainNodeList.item(i);
            if(child.getNodeType()==Node.ELEMENT_NODE) {
                int flag = 1;
                boolean nodeExist = true;
                for(int j=0;j<nnm.getLength();j++) {
                    Node attrNode=nnm.item(j);
                    String nodename=attrNode.getNodeName();
                    String value=attrNode.getNodeValue();
                    if(!((Element)child).getAttribute(nodename).equals(value) && !nodename.equals("leaf")) {//No I18N
                        nodeExist = false;
                    }
                    flag = 0;
                }
                if(flag ==0 && nodeExist == true)
                {
                    return true;
                }
            }
        }
		return false;
	}


    private void getChildNode(Node node,String nodeName)
	{
		if (node == null || nodeName == null)
			return;
		NodeList nodeList=node.getChildNodes();
		if ( nodeList == null)
			return;

		for( int i=0;i<nodeList.getLength();++i)
		{
			Node childNode=nodeList.item(i);
			if ( childNode.getNodeType() == Node.ELEMENT_NODE)
			{
                NamedNodeMap nmn = childNode.getAttributes();
                String parentNode = nmn.getNamedItem("parent").getNodeValue();//No I18N
                String childNode1 = nmn.getNamedItem("child").getNodeValue();//No I18N
                String leaf = nmn.getNamedItem("leaf").getNodeValue();//No I18N

                if(childNode1.equals(nodeName)) {
                    Node n=addOperTreeToCache(parentNode,childNode1,leaf);
                    deleteCacheNode.appendChild(n);
                }
                if( parentNode.equals(nodeName))
                {
                    getChildNode(node, childNode1);
                }
            }
		}

	}

	public void setOperations()
	{

		if(operNode != null)
		{
			try
			{

				byte[] operData = xmlParser.setOperTree(operNode);
				//Write code here to process the operationtree node
				NodeList nl=((Element)operNode).getElementsByTagName("DATA");//No I18N
				Node rootNode =xmlParser.getAuthNode("OPERATION-TREE");//No Internationalisation
				for(int i=0;i<nl.getLength();i++)
				{
					Node impo=rootNode.getOwnerDocument().importNode(nl.item(i),true);
                    if(!isNodePresent(rootNode, impo)) {
                        rootNode.appendChild(impo);
                    }
				}
				Node mainNode= xmlParser.getOperationsTree();

				constructOperationsTree(mainNode);
                saveToFile();
			}
			catch(Exception ioex)
			{

			}
		}
		if(operDelete != null)
		{
			try
			{
				byte[] operData = xmlParser.setOperTree(operDelete);
				NodeList nl=((Element)operDelete).getElementsByTagName("DATA");//No I18N
				Node rootNode =xmlParser.getAuthNode("OPERATION-TREE");//No Internationalisation
				for(int i=0;i<nl.getLength();i++)
				{
					Node impo=nl.item(i);
					NodeList nl1= rootNode.getChildNodes();
					for(int j=nl1.getLength()-1;j>=0;j--)
					{
						Node comp=nl1.item(j);
						if(NodeCompare(impo,comp))
						{
							//Write code to delete this operation name in all the view operations tag also.
							String opername=((Element)comp).getAttribute("child");//No I18N
							deleteOperationFromView(opername,xmlParser.getAuthNode("VIEW-OPERATION"));//No I18N

							rootNode.removeChild(comp);
						}
					}
				}
                Node mainNode= xmlParser.getOperationsTree();
				constructOperationsTree(mainNode);
                saveToFile();
			}
			catch(Exception ioex)
			{
				ioex.printStackTrace();
			}

		}
		operNode = null;
		operDelete = null;
        deleteCacheNode = null;
        rootNodeOper = null;
		constructOperationsTree(mainNode);
		scInter.fireDataChanged();

	}
	private void deleteOperationFromView(String opername,Node operNode)
	{
		NodeList viewNodeList= ((Element)operNode).getElementsByTagName("DATA");//No I18N
		if(viewNodeList==null)
			return;
		int length=viewNodeList.getLength();
		for(int i=length-1;i>=0;i--)
		{
			Node operation=viewNodeList.item(i);
			if(((Element)operation).getAttribute("operationname").equals(opername))//No I18N
			{
				operNode.removeChild(operation);
			}
		}
	}
	public void clearAudit(String s)
	{
		constructOperationsTree(mainNode);
		scInter.fireDataChanged();
	}
	private boolean NodeCompare(Node first,Node Second)
	{
		if(!first.getNodeName().equals(Second.getNodeName()))
		{
			return false;
		}
		NamedNodeMap nnm=first.getAttributes();
		for(int i=0;i<nnm.getLength();i++)
		{
			Node attrNode=nnm.item(i);
			String nodename=attrNode.getNodeName();
			String value=attrNode.getNodeValue();
			if(!((Element)Second).getAttribute(nodename).equals(value))
			{
				return false;
			}
		}
		return true;
	}
	public Vector getAllViews()
	{
		views_vector = xmlParser.getViews();
		return views_vector;
	}

	public Vector getAllGroups()
	{
		groups_vector = xmlParser.getAllGroups();
		return groups_vector;
	}

	public Vector getAllUsers()
	{
		users_vector = xmlParser.getAllUsers();
		return users_vector;
	}

	public DefaultTreeModel getTreeModel()
	{
		Node mainNode= xmlParser.getOperationsTree();
		return constructOperationsTree(mainNode);
	}

	public boolean isInitialized()
	{
		return isInitialized;
	}

	public Vector getGroupsForUser(String uname)
	{
		Vector users_vec = xmlParser.getAllUsers();
		Vector usergroups = new Vector();
		int index = users_vec.indexOf(uname);
		String elementOfUserVec = (String)users_vec.elementAt(index+1);
		//usergroups = (Vector)users_vec.elementAt(index+1);
		usergroups.addElement(elementOfUserVec);
		return usergroups;
	}

	public 	Vector getUserGroupsWithDefault(String uname)
	{
		return xmlParser.getGroupsForUser(uname);
	}

	public Vector getViewsForGroup(String gname)
	{
		Vector groups_vec = xmlParser.getAllGroups();
		int index = groups_vec.indexOf(gname);
		if(index == -1)
		{
			return new Vector();
		}
		Vector views = (Vector)groups_vec.elementAt(index+1);
		return views;
	}

	public Vector getAllGroupNames()
	{
		Vector gwviews = new Vector();
		Vector groups = xmlParser.getAllGroups();

		for(int i=0;i < groups.size();i+=2)
		{
			gwviews.add(groups.elementAt(i));
		}

		return gwviews;

	}

	public  Vector getAllUserNames()
	{
		Vector allusers = new Vector();
		Vector users = xmlParser.getAllUsers();

		for(int i=0;i < users.size();i+=2)
		{
			allusers.add(users.elementAt(i));
		}

		return allusers;


	}

	public Vector getAudit(String userName){
		Vector toReturn = new Vector();
		toReturn = xmlParser.getAuditTrails(userName);
		return toReturn;
	}


	public Vector getUsersForGroup(String groupName)
	{
		return xmlParser.getUserForGroup(groupName);
	}
	public Vector getAllGroupsForUser(String username)
	{
		return xmlParser.getGroupsForUser(username);
	}

	public Hashtable getOperationsForUser(String uname)
	{
		return xmlParser.getOperationsForUser(uname);
	}
	public void setNewGroup(String gname)
	{
		newgroup.add(gname);
	}

	//My methods
	java.util.Hashtable allOper = null;
	java.util.Hashtable allOper1 = null;
	public Hashtable getOperations(String user){

		java.util.Vector views = xmlParser.getViewsForUser(user);
		views.addElement("default "+user+" View");//No I18N
		java.util.Hashtable oper = null;
		allOper1 = new java.util.Hashtable();
		int size = views.size();
		for(int i=0;i<size;i++)
		{
			oper = new java.util.Hashtable();
			oper = xmlParser.getOperationsForViews(views.elementAt(i).toString());
			if(size != 0 && oper.size()!=0)
				for(Enumeration e= oper.keys();e.hasMoreElements();)
				{
					String name = e.nextElement().toString();
					if(allOper1.containsKey(name) && (allOper1.get(name).toString()).equals("1"))//No I18N
					{
						//	break;
					}
					else if(allOper1.containsKey(name) && (allOper1.get(name).toString()).equals("0"))//No I18N
					{
						allOper1.put(name,oper.get(name));
					}
					else if(!allOper1.containsKey(name))
					{
						allOper1.put(name,oper.get(name));
					}
				}
				}

			return allOper1;
		}

		public Hashtable getOperationsForGroup(Vector groups){
			java.util.Vector views = xmlParser.getViewsForGroups(groups);
			java.util.Hashtable oper = null;
			allOper = new java.util.Hashtable();
			int size = views.size();
			for(int i=0;i<size;i++){
				oper = new java.util.Hashtable();
				oper = xmlParser.getOperationsForViews(views.elementAt(i).toString());
				if(size !=0 && oper.size()!=0)
					for(Enumeration e= oper.keys();e.hasMoreElements();){
						String name = e.nextElement().toString();
						if(allOper.contains(name) && (allOper.get(name).toString()).equals("1"))//No I18N
						{
							//	break;
						}
						else if(allOper.contains(name) && (allOper.get(name).toString()).equals("0"))//No I18N
						{
							allOper.put(name,oper.get(name));
						}
						else if(!allOper.containsKey(name))
						{
							allOper.put(name,oper.get(name));
						}
					}
					}
				return allOper;
			}

			public Vector getGroups()
			{
				return xmlParser.getAllGroupsFromUserGroupTable();
				//return xmlParser.getAllGroups();
			}
			public Vector getNewGroup()
			{
				Vector temp = newgroup;
				return newgroup;
			}

			public Hashtable getUserAttributes(String username)
			{
				Hashtable userAttributes = xmlParser.getAllAttributes(username);
				if(userAttributes.get("passwdexpirytime")!=null)//No I18N
				{
					userAttributes.put("passwordexpirytime",userAttributes.get("passwdexpirytime"));//No I18N
				}
				return userAttributes;
			}

			public void setUserAttributes(String username, String status, Integer userage, Integer passwordage,String descName )
			{
				try
				{
					byte[] ageData = xmlParser.setAllAttributes(username,null, userage, passwordage,descName);
					Node output=getByteAsXMLNode(ageData);
					addUserConfData(output,username);
					constructOperationsTree(mainNode);
					scInter.fireDataChanged();
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
			private boolean addUserData(Node userNode,String username)
			{
				Vector vec=xmlParser.getAllUsers();
				if(vec.contains(username))
				{
					return false;
				}
				NodeList nl= ((Element)userNode).getChildNodes();
				if(nl==null)
					return false;
				Node rootNode=xmlParser.getRootNode();

				for(int j=0;j<nl.getLength();j++)
				{
					if(nl.item(j).getNodeType()!=Node.ELEMENT_NODE)
					{
						continue;
					}
					if(((Element)nl.item(j)).getTagName().equals("USER-PASSWD"))//No I18N
					{
						Node passwdNode= nl.item(j);
						NodeList dlist=passwdNode.getChildNodes();
						//getting the user-passwd node from the main file.
						NodeList mainpasswdlist=((Element)rootNode).getElementsByTagName("USER-PASSWD");//No I18N
						Node mainpasswdNode=mainpasswdlist.item(0);
						for(int i =0;i<dlist.getLength();i++)
						{
							Node dataNode = mainNode.getOwnerDocument().importNode((Node)dlist.item(i),true);
						if(dataNode.getNodeType()!=Node.ELEMENT_NODE)
						{
							continue;
						}
							String password=((Element)dataNode).getAttribute("password");//No I18N
                           
							((Element)dataNode).setAttribute("password",Coding.convertToNewBase(password));//No I18N
							((Element)dataNode).setAttribute("ownername","NULL");//No I18N
							mainpasswdNode.appendChild(dataNode);
							copyAllFilesToTheUser(((Element)dataNode).getAttribute("username"));//No I18N
						}
					}
				}
				return true;
			}
			private boolean addUserGroupData(Node userNode,String username)
			{
                NodeList nl= ((Element)userNode).getChildNodes();
                
				if(nl==null)
					return false;
				Node rootNode=xmlParser.getRootNode();

				for(int j=0;j<nl.getLength();j++)
				{
					if(nl.item(j).getNodeType()!=Node.ELEMENT_NODE)
					{
						continue;
					}
					if(((Element)nl.item(j)).getTagName().equals("USER-GROUP"))//No I18N
					{
						Node passwdNode= nl.item(j);
						NodeList dlist=passwdNode.getChildNodes();
						//getting the user-passwd node from the main file.
						NodeList mainpasswdlist=((Element)rootNode).getElementsByTagName("USER-GROUP");//No I18N
						Node mainpasswdNode=mainpasswdlist.item(0);
                      
                        removeGroupsForUserName(mainpasswdNode,username);
						for(int i =0;i<dlist.getLength();i++)
						{
							Node dataNode = mainNode.getOwnerDocument().importNode((Node)dlist.item(i),true);
							if(dataNode.getNodeType()!=Node.ELEMENT_NODE)
							{
								continue;
							}
							if(!((Element)dataNode).getAttribute("username").toString().trim().equals(""))//No I18N
							{
                                Element dataNodeElement = (Element) dataNode;
                                dataNodeElement.setAttribute("ownername","NULL");//No I18N
                                if(!isNodeExist(mainpasswdNode,(Node)dataNodeElement))
                                {
                                    mainpasswdNode.appendChild((Node)dataNodeElement);
                                }
                              
							}
						}
					}
				}
                
				return true;
			}
            private boolean isNodeExist(Node mainNode,Node dataNode)
            {
                String uname=((Element)dataNode).getAttribute("username");//No I18N
                String groupname=((Element)dataNode).getAttribute("groupname");//No I18N
                String ownername = ((Element)dataNode).getAttribute("ownername");//No I18N
                
                NodeList mainNodeList = mainNode.getChildNodes();
                for(int i=mainNodeList.getLength()-1;i>=0;i--)
				{
					Node child=mainNodeList.item(i);
					if(child.getNodeType()==Node.ELEMENT_NODE && ((Element)child).getAttribute("username").equals(uname))//No I18N
					{
                        if( ((Element)child).getAttribute("groupname").equals(groupname))//No I18N
                        {
                            if( ((Element)child).getAttribute("ownername").equals(ownername))//No I18N
                            {
                                return true;
                            }
                        }
                    }
                }
                return false;
            }
                
			private void removeGroupsForUserName(Node groupNode,String uname)
			{
				NodeList childList=groupNode.getChildNodes();
				for(int i=childList.getLength()-1;i>=0;i--)
				{
					Node child=childList.item(i);
					if(child.getNodeType()==Node.ELEMENT_NODE && ((Element)child).getAttribute("username").equals(uname))//No I18N
					{
						groupNode.removeChild(child);
					}
				}
			}
			private void removeOperationsForTheView(Node groupNode,String viewname)
			{
				NodeList childList=groupNode.getChildNodes();
				for(int i=childList.getLength()-1;i>=0;i--)
				{
					Node child=childList.item(i);
					if(child.getNodeType()==Node.ELEMENT_NODE && ((Element)child).getAttribute("viewname").equals(viewname))//No I18N
					{
						groupNode.removeChild(child);
					}
				}
			}

			private boolean addViewGroupData(Node userNode)
			{
				NodeList nl= ((Element)userNode).getChildNodes();
				if(nl==null)
					return false;
				Node rootNode=xmlParser.getRootNode();

				for(int j=0;j<nl.getLength();j++)
				{
					if(nl.item(j).getNodeType()!=Node.ELEMENT_NODE)
					{
						continue;
					}
					if(((Element)nl.item(j)).getTagName().equals("VIEW-GROUP"))//No I18N
					{
						Node passwdNode= nl.item(j);
						NodeList dlist=passwdNode.getChildNodes();
						//getting the user-passwd node from the main file.
						NodeList mainpasswdlist=((Element)rootNode).getElementsByTagName("VIEW-GROUP");//No I18N
						Node mainpasswdNode=mainpasswdlist.item(0);
						for(int i =0;i<dlist.getLength();i++)
						{
					if(dlist.item(i).getNodeType()!=Node.ELEMENT_NODE)
					{
						continue;
					}

                            if(!isViewGroupExist(mainpasswdNode,(Node)dlist.item(i) )) {
                                Node dataNode = mainNode.getOwnerDocument().importNode((Node)dlist.item(i),true);
                                mainpasswdNode.appendChild(dataNode);
                            }
						}
					}
				}
				return true;
			}
    
            private boolean isViewGroupExist(Node mainNode,Node dataNode)
            {
                String vname=((Element)dataNode).getAttribute("viewname");//No Internationalisation
                String groupname=((Element)dataNode).getAttribute("groupname");//No Internationalisation
                //String ownername = ((Element)dataNode).getAttribute("ownername");//No Internationalisation
                
                NodeList mainNodeList = mainNode.getChildNodes();
                for(int i=mainNodeList.getLength()-1;i>=0;i--)
				{
					Node child=mainNodeList.item(i);
					if(child.getNodeType()==Node.ELEMENT_NODE && ((Element)child).getAttribute("viewname").equals(vname))//No Internationalisation
					{
                        if( ((Element)child).getAttribute("groupname").equals(groupname))//No Internationalisation
                        {
                            return true;
                            /*
                            if( ((Element)child).getAttribute("ownername").equals(ownername))//No Internationalisation
                            {
                                return true;
                            }
                            */
                        }
                    }
                }
                return false;
            }

			private boolean addViewOperations(Node userNode)
			{
				NodeList nl= ((Element)userNode).getChildNodes();
				if(nl==null)
					return false;
				Node rootNode=xmlParser.getRootNode();

				for(int j=0;j<nl.getLength();j++)
				{
					if(nl.item(j).getNodeType()!=Node.ELEMENT_NODE)
					{
						continue;
					}
					String tagName=((Element)nl.item(j)).getTagName();
					if(tagName.equals("ADD_VIEW_OPERATIONS") || tagName.equals("VIEW-OPERATION"))//No I18N
					{
						Node passwdNode= nl.item(j);
						NodeList dlist=passwdNode.getChildNodes();
						//getting the user-passwd node from the main file.
						NodeList mainpasswdlist=((Element)rootNode).getElementsByTagName("VIEW-OPERATION");//No I18N
						Node mainpasswdNode=mainpasswdlist.item(0);
						String viewname=getViewName(passwdNode);
						removeOperationsForTheView(mainpasswdNode,viewname);
						for(int i =0;i<dlist.getLength();i++)
						{
							Node dataNode = mainNode.getOwnerDocument().importNode((Node)dlist.item(i),true);
							if(dataNode.getNodeType()==Node.ELEMENT_NODE && !((Element)dataNode).getAttribute("operationname").trim().equals("")){//No I18N
							    mainpasswdNode.appendChild(dataNode);
							}
						}
					}
				}
				return true;
			}
			private String getViewName(Node viewNode)
			{
				String view="";//No I18N
				NodeList nl=((Element)viewNode).getElementsByTagName("DATA");//No I18N
				if(nl==null || nl.item(0)==null)
					return view;
				Element ele=(Element)nl.item(0);
				return ele.getAttribute("viewname");//No I18N
			}



			private Node getByteAsXMLNode(byte[] data)
			{
				Node node=null;
				try
				{
					ByteArrayInputStream bis = new ByteArrayInputStream(data);
					DocumentBuilderFactory docbuilderFactorys = DocumentBuilderFactory.newInstance();

					DocumentBuilder docBuilders = docbuilderFactorys.newDocumentBuilder();
					Document xmlData = docBuilders.parse(bis);
					node = xmlData.getDocumentElement();
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				return node;
			}
			private boolean addUserConfData(Node userNode,String username)
			{
				NodeList nl= ((Element)userNode).getElementsByTagName("USER-CONF");//No I18N
				if(nl==null || nl.item(0)==null)
				{
					addDefaultUserAttributes(xmlParser.getAuthNode("USER-CONF"),username);//No I18N
					return false;
				}
				Node rootNode=xmlParser.getRootNode();

				Node passwdNode= nl.item(0);
				NodeList dlist=((Element)passwdNode).getElementsByTagName("DATA");//No I18N
				if(dlist==null || dlist.item(0)==null)
				return false;

				Node dataNode=dlist.item(0);
				NodeList mainpasswdlist=((Element)rootNode).getElementsByTagName("USER-CONF");//No I18N
				Node mainpasswdNode=mainpasswdlist.item(0);

				NodeList dataconflist=mainpasswdNode.getChildNodes();
				Node dNode=null;
				boolean found=false;
				Node dataNode1 = mainNode.getOwnerDocument().importNode(dataNode,true);
				if(((Element)dataNode1).getAttribute("status").trim().equals(""))//No I18N
				{
				((Element)dataNode1).setAttribute("status","enabled");//No I18N
				}

				for(int i =0;i<dataconflist.getLength();i++)
				{
					dNode = dataconflist.item(i);
					if(dNode.getNodeType()==Node.ELEMENT_NODE && ((Element)dNode).getAttribute("username").equals(username))//No I18N
					{
						mainpasswdNode.replaceChild(dataNode1,dNode);
						found = true;
						break;
					}
				}
				if(!found)
				{
					Long serverTime = new Long(System.currentTimeMillis());
                    ((Element)dataNode1).setAttribute("systime",serverTime.toString());//No I18N
					mainpasswdNode.appendChild(dataNode1);
				}
				InputStream in=new ByteArrayInputStream(xmlParser.getBytes());
				updateSysTime(in);
				return true;
			}
			public void addDefaultUserAttributes(Node userConfNode,String username)
			{
				Element ele=((Element)userConfNode).getOwnerDocument().createElement("DATA");//No I18N
				ele.setAttribute("username",username);//No I18N
				ele.setAttribute("ownername","NULL");//No I18N
				ele.setAttribute("passwordexpirytime","0");//No I18N
				ele.setAttribute("userexpirytime","0");//No I18N
				ele.setAttribute("userage","0");//No I18N
				ele.setAttribute("passwordage","0");//No I18N
				ele.setAttribute("status","enabled");//No I18N
				ele.setAttribute("descriptivename",username);//No I18N
				userConfNode.appendChild(ele);
				InputStream in=new ByteArrayInputStream(xmlParser.getBytes());
				updateSysTime(in);
			}

			public void setUserStatus(String username, String status)
			{
				try
				{
					byte[] ageData = xmlParser.setAllAttributes(username,status,null,null,null);
					Node output=getByteAsXMLNode(ageData);
					addUserConfData(output,username);
					constructOperationsTree(mainNode);
					scInter.fireDataChanged();
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}


			}


			public void cleanUp()
			{
				deRegister();
				AuthMain.standalone=false;
				saveToFile();
				users_vector = null;
				groups_vector = null;
				views_vector = null;
				treeModel = null;
			}
			public void saveToFile()
			{
				try
				{
					Node root=xmlParser.getRootNode();
					TransformerFactory fac = TransformerFactory.newInstance();
					Transformer trans = fac.newTransformer();
					DOMSource domSource = new DOMSource(root);
					StreamResult streamResult = new StreamResult(new FileOutputStream(new File(projPath+File.separator+"resources"+File.separator+filename)));//No I18N
					trans.transform(domSource, streamResult);
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}


			private void updateSysTime(InputStream in) {

		try {
			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();

			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();

			Node xmlNode = null;
			xmlNode = xmlParser.getAuthNode("USER-CONF");//No I18N
			if(xmlNode==null)
			{
				return;
			}
			NodeList nll = xmlNode.getChildNodes();
			int size1=nll.getLength();
			for( int j=0; j<size1; j++ ) {
				if(nll.item(j).getNodeType() == Node.ELEMENT_NODE) {
					if((nll.item(j).getNodeName()).equals("DATA")) {//No I18N
						Element n = (Element) nll.item(j);
						//n.setNodeType(Node.ELEMENT_NODE);
						Long serverTime = new Long(System.currentTimeMillis());
                        
						n.setAttribute("systime",serverTime.toString()); //No I18N
						//nwNode.addChildNode(n);
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			//System.err.println(AdventNetResourceBundle.getInstance().getString("SecurityXMLParser : Exception in parseFile ") + e);
		}
	}
	private void copyAllFilesToTheUser(String username)
	{
		String destinationDirectory = projPath + File.separator + "resources" + File.separator + "users" + File.separator + username;//No I18N
		String sourceDirectory = projPath  + File.separator + "resources" + File.separator + "html"+File.separator+"defaultsToNewUsers";//No I18N
		FileUtil.copyDirectory(new File(sourceDirectory),new File(destinationDirectory));
	    	
	}
	private void deleteUsersFiles(String username)
	{
		String deletedDirectory = projPath + File.separator + "resources" + File.separator + "users" + File.separator + username;//No I18N
		FileUtil.deleteDirectory(new File(deletedDirectory));	
	}
	public Hashtable getAuthScope(String groupname)
	{
		//Hashtable toReturn = (Hashtable)getCustomViews().get(groupname);
		Hashtable toReturn = (Hashtable)getCustomViews(groupname);
		if(toReturn==null)
		{
			toReturn= new Hashtable();
		}
		return toReturn;
	}
	public Vector getAllAuthScopes()
	{
		return xmlParser.getAllAuthorizedView();
	}
	public Vector getAllAuthScopes(String customView)
	{
		return xmlParser.getAllAuthorizedView(customView);
	}
	public void assignAuthScope(String name,Vector names,String groupName)
	{
		byte[] authData = xmlParser.createCustomViewScope(name,names,groupName);
		Node output=getByteAsXMLNode(authData);
		addNameViews(output,groupName,name);
		constructOperationsTree(mainNode);
		scInter.fireDataChanged();
	}
	public void addAuthViewProp(String custViewName,Properties moprops,String groupName)
	{
		byte[] authData = xmlParser.createScope(custViewName,moprops,groupName);
		Node output=getByteAsXMLNode(authData);
		removeViewProp(custViewName);
		addViewProperty(output);
		constructOperationsTree(mainNode);
		scInter.fireDataChanged();

	}
	public void modViewProp(String gpName,boolean forDelete,Vector moVector,String cvsscope)
	{
		byte[] viewData = xmlParser.modifyAuthorizedViewForGroup(gpName,forDelete,moVector,cvsscope);
		Vector viewsForCVS = getAllViewsForCustomView(cvsscope);
		Node output=getByteAsXMLNode(viewData);
		if(forDelete)
		{
			deleteViewFromNamedAuth(moVector);
			deleteViewFromViewProperty(moVector);
			deleteViewFromViewGroup(moVector);
		}
		else
		{
			modifyViewGroupDataForGroup(output,gpName,viewsForCVS);
		}
		constructOperationsTree(mainNode);
		scInter.fireDataChanged();
	}
	public Hashtable getCustomViews(String username)
	{
		Hashtable viewHash = new Hashtable(0);
		Node viewNode = xmlParser.getAuthNode("NAMED-AUTH");//No I18N
		NodeList nl = ((Element)viewNode).getElementsByTagName("DATA");//No I18N
		Vector vec1= new Vector(0);
		vec1.addElement(username);
		for(int i=0;i<nl.getLength();i++)
		{
			Element dataNode = (Element)nl.item(i);
			String viewname = dataNode.getAttribute("namedview");//No I18N
			String authView = dataNode.getAttribute("authorizedview");//No I18N
			Vector vec = new Vector(0);
			if(viewHash.get(viewname)!=null)
			{
				if(!authView.equals("NULL"))
				{
					vec = (Vector)viewHash.get(viewname);
					vec.addElement(authView);
					viewHash.put(viewname,vec);
				}
			}
			else
			{
				viewHash.put(viewname,new Vector(0));
			}
		}
		Vector viewVectorForGroup = xmlParser.getViewsForGroups(vec1);
		for(Enumeration e=viewHash.keys();e.hasMoreElements();)
		{
			String viewname = (String)e.nextElement();
			Vector viewVector = (Vector)viewHash.get(viewname);
			for(int i=viewVector.size()-1;i>=0;i--)
			{
				String searchView =(String)viewVector.elementAt(i);
				if(!viewVectorForGroup.contains(searchView))
				{
					viewVector.removeElement(searchView);
				}
			}
			viewHash.put(viewname,viewVector);
		}
		return viewHash;		
	}
	public void addNameViews(Node viewNode,String groupname,String viewname)
	{
		Node namedView = xmlParser.getAuthNode("NAMED-AUTH");//No I18N
		NodeList ViewList  = ((Element)viewNode).getElementsByTagName("DATA");//No I18N
		if(ViewList.getLength() != 1 && !groupname.trim().equals(""))//No I18N
		{
			removeAllNamedViews(namedView,viewname);
		}
		for(int i=0;i<ViewList.getLength();i++)
		{
			Element ViewDataNode = (Element)ViewList.item(i);
			String namedViewname = ViewDataNode.getAttribute("namedview");//No I18N
			if(namedViewname.trim().equals(""))//No I18N
			{
				continue;
			}
			Element dataNode=((Element)namedView).getOwnerDocument().createElement("DATA");//No I18N
			dataNode.setAttribute("namedview",namedViewname);//No I18N
			dataNode.setAttribute("authorizedview",ViewDataNode.getAttribute("authorizedview"));//No I18N

			namedView.appendChild(dataNode);
		}
	}
	public void addViewProperty(Node viewNode)
	{
		Node namedView = xmlParser.getAuthNode("VIEW-PROPERTY");//No I18N
		Element dataNode=((Element)namedView).getOwnerDocument().createElement("DATA");//No I18N
		NodeList ViewList  = ((Element)viewNode).getElementsByTagName("DATA");//No I18N
		Element ViewDataNode = (Element)ViewList.item(0);
		dataNode.setAttribute("viewname",ViewDataNode.getAttribute("viewname"));//No I18N

		namedView.appendChild(dataNode);
	    for(int i=0;i<ViewList.getLength();i++)
		{
			Element propertyNode=((Element)namedView).getOwnerDocument().createElement("PROPERTY");//No I18N
			ViewDataNode = (Element)ViewList.item(i);
			propertyNode.setAttribute("propname",ViewDataNode.getAttribute("propertyname"));//No I18N
			propertyNode.setAttribute("propvalue",ViewDataNode.getAttribute("propertyvalue"));//No I18N
			dataNode.appendChild(propertyNode);
		}
		String groupName = ViewDataNode.getAttribute("groupname");//No I18N
		if(groupName!=null && !groupName.trim().equals(""))//No I18N
		{
			Node viewGroup = xmlParser.getAuthNode("VIEW-GROUP");//No I18N
			Element dataNode1=((Element)viewGroup).getOwnerDocument().createElement("DATA");//No I18N
			dataNode1.setAttribute("viewname",ViewDataNode.getAttribute("viewname"));//No I18N
			dataNode1.setAttribute("groupname",ViewDataNode.getAttribute("groupname"));//No I18N
			dataNode1.setAttribute("ownername","NULL");//No I18N

			viewGroup.appendChild(dataNode1);
		}
	}
	public void removeAllNamedViews(Node namedViews,String viewname)
	{
		NodeList viewChilds = ((Element)namedViews).getElementsByTagName("DATA");//No I18N
		for(int i=viewChilds.getLength()-1;i>=0;i--)
		{
			Node dataNode = viewChilds.item(i);
			if(!((Element)dataNode).getAttribute("authorizedview").equals("NULL"))//No I18N
			{
				namedViews.removeChild(dataNode);
			}
		}
	}
	public void modifyViewGroupDataForGroup(Node modviewNode,String groupName,Vector viewsVector)
	{
		Node viewGroupNode = xmlParser.getAuthNode("VIEW-GROUP");//No I18N
		NodeList viewList = ((Element)viewGroupNode).getElementsByTagName("DATA");//No I18N
		//Here we are deleting the data nodes under the view group whose group name equals to the coming group name,contained in the viewsVector and not in the modifiedviews vector.
		for(int i=0;i<viewList.getLength();i++)
		{
			Element dataNode = (Element)viewList.item(i);
			String groupname = dataNode.getAttribute("groupname");//No I18N
			String viewname = dataNode.getAttribute("viewname");//No I18N
			if(groupname.equals(groupName))
			{
					viewGroupNode.removeChild(dataNode);
			}
		}
		NodeList modViewNodeList = ((Element)modviewNode).getElementsByTagName("DATA");//No I18N
		for(int i=0;i<modViewNodeList.getLength();i++)
		{
			Element dataNode=viewGroupNode.getOwnerDocument().createElement("DATA");//No I18N
			Element viewDataNode = (Element)modViewNodeList.item(i);
			if(viewDataNode.getAttribute("isDelete").trim().equalsIgnoreCase("FALSE"))//No I18N
			{
				dataNode.setAttribute("viewname",viewDataNode.getAttribute("viewname"));//No I18N
				dataNode.setAttribute("groupname",viewDataNode.getAttribute("groupname"));//No I18N
				dataNode.setAttribute("ownername","NULL");//No I18N
				viewGroupNode.appendChild(dataNode);
			}
		}
	}
	public Vector getAllViewsForCustomView(String cvsname)
	{
		Node cVNode = xmlParser.getAuthNode("NAMED-AUTH");//No I18N
		NodeList cvsList =((Element)cVNode).getElementsByTagName("DATA");//No I18N
		Vector cvVector = new Vector(0);
		for(int i=0;i<cvsList.getLength();i++)
		{
			Node dataNode = cvsList.item(i);
			String custViewName = ((Element)dataNode).getAttribute("namedview");//No I18N
			if(custViewName.equals(cvsname))
			{
				String viewname= ((Element)dataNode).getAttribute("authorizedview");//No I18N
				if(!viewname.equals("NULL"))//No I18N
				{
					cvVector.addElement(viewname);
				}
			}
		}
		return cvVector;
	}
	public void removeViewProp(String viewname)
	{
		Node namedView = xmlParser.getAuthNode("VIEW-PROPERTY");//No I18N
		NodeList ViewList  = ((Element)namedView).getElementsByTagName("DATA");//No I18N
	    for(int i=0;i<ViewList.getLength();i++)
		{
			Element ViewDataNode = (Element)ViewList.item(i);
			if(ViewDataNode.getAttribute("viewname").trim().equals(viewname))//No I18N
			{
				namedView.removeChild(ViewDataNode);
			}
		}

	}
	public void deleteViewFromNamedAuth(Vector vec)
	{
		Node cVNode = xmlParser.getAuthNode("NAMED-AUTH");//No I18N
		NodeList cvsList =((Element)cVNode).getElementsByTagName("DATA");//No I18N
		Vector cvVector = new Vector(0);
		for(int i=0;i<cvsList.getLength();i++)
		{
			Node dataNode = cvsList.item(i);
			String custViewName = ((Element)dataNode).getAttribute("authorizedview");//No I18N
			if(vec.contains(custViewName))
			{
				cVNode.removeChild(dataNode);	
			}
		}	
	}
	public void deleteViewFromViewProperty(Vector vec)
	{
		Node cVNode = xmlParser.getAuthNode("VIEW-PROPERTY");//No I18N
		NodeList cvsList =((Element)cVNode).getElementsByTagName("DATA");//No I18N
		Vector cvVector = new Vector(0);
		for(int i=0;i<cvsList.getLength();i++)
		{
			Node dataNode = cvsList.item(i);
			String custViewName = ((Element)dataNode).getAttribute("viewname");//No I18N
			if(vec.contains(custViewName))
			{
				cVNode.removeChild(dataNode);	
			}
		}	

	}
	public void deleteViewFromViewGroup(Vector vec)
	{
		Node cVNode = xmlParser.getAuthNode("VIEW-GROUP");//No I18N
		NodeList cvsList =((Element)cVNode).getElementsByTagName("DATA");//No I18N
		Vector cvVector = new Vector(0);
		for(int i=0;i<cvsList.getLength();i++)
		{
			Node dataNode = cvsList.item(i);
			String custViewName = ((Element)dataNode).getAttribute("viewname");//No I18N
			if(vec.contains(custViewName))
			{
				cVNode.removeChild(dataNode);	
			}
		}	
	}

	public void showHelp(String helpUrl)
	{
	}

	public void saveAuditDetails(String str1,String str2)
	{
	}
}











































































