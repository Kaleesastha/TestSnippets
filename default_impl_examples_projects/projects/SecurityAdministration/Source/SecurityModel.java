//$Id: SecurityModel.java,v 1.9 2010/10/29 13:46:42 swaminathap Exp $

package com.adventnet.security.ui;

//WebNMS imports
import com.adventnet.nms.util.ResultEventListener;
import com.adventnet.nms.util.ResultEventObject;
import com.adventnet.nms.util.NmsClientUtil;

//Auth Imports 
import com.adventnet.security.common.SecurityXMLParser;
import com.adventnet.nms.security.common.AuthConstants;

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


public class SecurityModel extends AbstractSecurityModel implements ResultEventListener
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
    private Properties propNames = null;

    private com.adventnet.nms.util.ResultEventAdapter adapter = null;	
    //Implementation class for ResultEventAdapter for Client-Server communication.

    String COMMN_IMPL = "";

    //variable used for first time not to call fireDataChanged when response has
    //come, as this will be done in AuthMain itself
    private boolean firstTime = false;
    
    public SecurityModel( String commn_impl)
    {

        COMMN_IMPL = commn_impl;
        //calling init from constr for the time being	
        init(new java.util.Properties());     
    }
    public void init(java.util.Properties prop)
    {

        getAdapterInstance().register((ResultEventListener)this);
        xmlParser = new SecurityXMLParser();
        users_vector = new Vector();
        groups_vector = new Vector();
        views_vector = new Vector();
        newgroup = new Vector();
        fetchSecurityInfo();
        fetchAuditInfo();
        sendRequestForPropertyNames();
        //fetchAuthScope();			
    }		

    public com.adventnet.nms.util.ResultEventAdapter getAdapterInstance()
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
    }


    public void deRegister()
    {
        //SecuritySession.getInstance().deRegister(this);
        getAdapterInstance().deRegister(this);
    }

    com.adventnet.security.ui.SecurityCommonInterface scInter = null;	
    public void registerWithModel(com.adventnet.security.ui.SecurityCommonInterface scInter)
    {
        this.scInter = scInter;	 
    }	

    public void fetchUserDetails()
    {	
        ByteArrayOutputStream byteStream=null;
        DataOutputStream outp=null;
        try	
        {
            byteStream = new ByteArrayOutputStream ();
            outp = new DataOutputStream (byteStream);
            //send the request type
            outp.writeInt(AuthConstants.GET_USERS_FOR_GROUPS);
            byte[] bytes = byteStream.toByteArray();
            getAdapterInstance().send(bytes);
            outp.close();
            byteStream.reset();
            byteStream.close();
        }
        catch(Exception ioex)
        {
            System.err.println(NmsClientUtil.GetString("Unable to send the request to the server to fetch user details")); //No I18N
            
        }
        finally
        {
            try
            {
                if(outp!=null)
                {
                    outp.close();
                }
            }
            catch(Exception e){}
            finally
            {
                try
                {
                    if(byteStream!=null)
                    {
                        byteStream.close();
                    }
                }
                catch(Exception e){}
            }
        }
    }
    
    public void callBack(ResultEventObject evt)
    {

        try
        {
            int reqType = evt.getEventType();
            ByteArrayInputStream bai_stream = evt.getResultEventData();
            DataInputStream di_stream = new DataInputStream(bai_stream);
            if(reqType==AuthConstants.GET_USERS_FOR_GROUPS)
	    {
                int dataLength = di_stream.readInt();
                byte [] dataBytes = new byte [dataLength];
                di_stream.readFully(dataBytes);
                xmlParser.createXmlDocument(dataBytes);
                AuthMain.main.canAddUser=true;	
	    }

            if(reqType == AuthConstants.GET_ALL_OPERATIONS)
            {
                int dataLength = di_stream.readInt();
                byte [] dataBytes = new byte [dataLength];
                di_stream.readFully(dataBytes);
                xmlParser.createXmlDocument(dataBytes);
                Node mainNode = xmlParser.getOperationsTree();

                if (!isInitialized)
                {
                    firstTime = true;
                }
                isInitialized = true;
                constructOperationsTree(mainNode);
                //xmlParser.updateCustomAuthViews();

                //if it is first time, don't call fireDataChanged as this will
                //be handled in AuthMain itself
                if (!firstTime)
                {
                    scInter.fireDataChanged();	
                }
                else
                {
                    firstTime = false;
                }
            }
	    else if(reqType == AuthConstants.CHECK_OPERATIONS)
		{
		    int dataLength = di_stream.readInt();
		    byte[] dataBytes = new byte[dataLength];
		    di_stream.readFully(dataBytes, 0, dataLength);
		    Properties prop = NmsClientUtil.deSerializeProperties(dataBytes);
                    AuthMain.main.enableButtonsAndMenus(prop);
		}
            else if(reqType == AuthConstants.GET_ALL_ATTRIBUTES)
            {
                int dataLength = di_stream.readInt();
                byte [] dataBytes = new byte [dataLength];
                di_stream.readFully(dataBytes); 
                xmlParser.createXmlDocument(dataBytes);
                // Node mainNode = xmlParser.getOperationsTree();
                //isInitialized = true;
                //constructOperationsTree(mainNode);
                //scInter.fireDataChanged();	

            }
            else if(reqType == AuthConstants.CLEAR_AUDIT_TRAILS)
            {
                int status = di_stream.readInt();
                if(status == AuthConstants.SUCCESS)
                {
                    int dataLength = di_stream.readInt();
                    byte [] dataBytes = new byte [dataLength];
                    di_stream.readFully(dataBytes); 
                    xmlParser.updateAuditData(dataBytes);
                    scInter.fireDataChanged();
                    //xmlParser.createXmlDocument(dataBytes);
                    // Node mainNode = xmlParser.getOperationsTree();
                    //isInitialized = true;
                    //constructOperationsTree(mainNode);
                    //scInter.fireDataChanged();	
                }
                else
                {
                    int len = di_stream.readInt();
                    byte[] d = new byte[len];
                    di_stream.readFully(d);
                    String err = new String(d);
                    scInter.showError(err);
                }
            }

            else if(reqType == AuthConstants.GET_AUDIT_TRAILS)
            {
                int dataLength = di_stream.readInt();
                byte [] dataBytes = new byte [dataLength];
                di_stream.readFully(dataBytes); 
                xmlParser.updateAuditData(dataBytes);
                //xmlParser.createXmlDocument(dataBytes);
                // Node mainNode = xmlParser.getOperationsTree();
                //isInitialized = true;
                //constructOperationsTree(mainNode);
                //scInter.fireDataChanged();	

            }

            else if(reqType == AuthConstants.GET_AUTH_VIEWS)
            {
                //System.out.println(" Within GET_AUTH_VIEWS ");
                int dataLength = di_stream.readInt();
                byte [] dataBytes = new byte [dataLength];
                di_stream.readFully(dataBytes); 
                //System.out.println( " Length  "+dataLength);
                //System.out.println(" Databytes   "+new String(dataBytes));
                xmlParser.updateCustomAuthViews(dataBytes);
                //xmlParser.createXmlDocument(dataBytes);
                // Node mainNode = xmlParser.getOperationsTree();
                //isInitialized = true;
                //constructOperationsTree(mainNode);
                scInter.fireDataChanged();	

            }	

            else if (reqType == AuthConstants.CREATE_CUSTOM_VIEW_RESP)
            {
                int data_length = di_stream.readInt();
                if(data_length == AuthConstants.SUCCESS)
                {
                    int len = di_stream.readInt();
                    byte [] data_bytes = new byte[len];
                    di_stream.readFully(data_bytes);
                    xmlParser.createXmlDocumentForReturn(data_bytes,AuthConstants.CREATE_CUSTOM_VIEW_RESP);
                    xmlParser.updateCustomAuthViews(data_bytes);
                    //fetchCustomScope();
                    fetchAuthScope();		
                }
                else
                {
                    int len = di_stream.readInt();
                    byte[] d = new byte[len];
                    di_stream.readFully(d);
                    String err = new String(d);
                    scInter.showError(err);
                }
            }	


            else if (reqType == AuthConstants.ADD_USER_WITH_PROPS_RESP)
            {
                int data_length = di_stream.readInt();
                if(data_length == AuthConstants.SUCCESS)
                {
                    int len = di_stream.readInt();
                    byte [] data_bytes = new byte[len];
                    di_stream.readFully(data_bytes);
                    xmlParser.createXmlDocumentForReturn(data_bytes,AuthConstants.ADD_USER_WITH_PROPS_RESP);
                    users_vector = xmlParser.getAllUsers();
                    fetchSecurityInfo();
                    //fetchAuthScope();
                }
                else
                {
                    int len = di_stream.readInt();
                    byte[] d = new byte[len];
                    di_stream.readFully(d);
                    String err = new String(d);
                    scInter.showError(err);
                }

            }	
            else if (reqType == AuthConstants.MODIFY_USER_WITH_PROPS_RESP)
            {
                int data_length = di_stream.readInt();
                if(data_length == AuthConstants.SUCCESS)
                {
                    int len = di_stream.readInt();
                    byte [] data_bytes = new byte[len];
                    di_stream.readFully(data_bytes);
                    xmlParser.createXmlDocumentForReturn(data_bytes,AuthConstants.MODIFY_USER_WITH_PROPS_RESP);
                    users_vector = xmlParser.getAllUsers();
                    fetchSecurityInfo();	
                    //fetchAuthScope();
                }
                else
                {
                    int len = di_stream.readInt();
                    byte[] d = new byte[len];
                    di_stream.readFully(d);
                    String err = new String(d);
                    scInter.showError(err);
                }

            }	
            else if (reqType == AuthConstants.ADD_GROUP_VIEWS_RESP)
            {
                int data_length = di_stream.readInt();
                if(data_length == AuthConstants.SUCCESS)
                {
                    int len = di_stream.readInt();
                    byte [] data_bytes = new byte[len];
                    di_stream.readFully(data_bytes);
                    xmlParser.createXmlDocumentForReturn(data_bytes,AuthConstants.ADD_GROUP_VIEWS_RESP);
                    groups_vector = xmlParser.getAllGroups();
                    scInter.fireDataChanged();
                    fetchAuthScope();
                }
                else
                {
                    int len = di_stream.readInt();
                    byte[] d = new byte[len];
                    di_stream.readFully(d);
                    String err = new String(d);
                    scInter.showError(err);
                }
            }	
            else if (reqType == AuthConstants.MODIFY_GROUP_VIEWS_RESP)
            {
                int data_length = di_stream.readInt();
                if(data_length == AuthConstants.SUCCESS)
                {
                    int len = di_stream.readInt();
                    byte [] data_bytes = new byte[len];
                    di_stream.readFully(data_bytes);
                    xmlParser.createXmlDocumentForReturn(data_bytes,AuthConstants.MODIFY_GROUP_VIEWS_RESP);
                    groups_vector = xmlParser.getAllGroups();
                    scInter.fireDataChanged();
                    fetchAuthScope();
                }
                else
                {
                    int len = di_stream.readInt();
                    byte[] d = new byte[len];
                    di_stream.readFully(d);
                    String err = new String(d);
                    scInter.showError(err);
                }

            }	
            else if (reqType == AuthConstants.ADD_AUTH_VIEWS_WITH_OPER_RESP)
            {
                int data_length = di_stream.readInt();
                if(data_length == AuthConstants.SUCCESS)
                {
                    int len = di_stream.readInt();
                    byte [] data_bytes = new byte[len];
                    di_stream.readFully(data_bytes);
                    xmlParser.createXmlDocumentForReturn(data_bytes,AuthConstants.ADD_AUTH_VIEWS_WITH_OPER_RESP);
                    views_vector = xmlParser.getViews();
                    scInter.fireDataChanged();
                }

                else
                {
                    int len = di_stream.readInt();
                    byte[] d = new byte[len];
                    di_stream.readFully(d);
                    String err = new String(d);
                    scInter.showError(err);

                }

            }	
            else if (reqType == AuthConstants.MODIFY_AUTH_VIEWS_WITH_OPER_RESP)
            {
                int data_length = di_stream.readInt();
                if(data_length == AuthConstants.SUCCESS)
                {
                    int len = di_stream.readInt();

                    byte [] data_bytes = new byte[len];
                    di_stream.readFully(data_bytes);
                    xmlParser.createXmlDocumentForReturn(data_bytes,AuthConstants.MODIFY_AUTH_VIEWS_WITH_OPER_RESP);
                    views_vector = xmlParser.getViews();
                    scInter.fireDataChanged();
                }

                else
                {
                    int len = di_stream.readInt();
                    byte[] d = new byte[len];
                    di_stream.readFully(d);
                    String err = new String(d);
                    scInter.showError(err);

                }

            }		

            else if (reqType == AuthConstants.ADD_OPERATIONS_RESP)
            {
                int data_length = di_stream.readInt();
                if(data_length == AuthConstants.SUCCESS)
                {
                    int len = di_stream.readInt();
                    byte [] data_bytes = new byte[len];
                    di_stream.readFully(data_bytes);
                    xmlParser.createXmlDocumentForReturn(data_bytes,AuthConstants.ADD_OPERATIONS_RESP);
                    Node mainNode= xmlParser.getOperationsTree();
                    constructOperationsTree(mainNode);
                    scInter.fireDataChanged();
                }

                else
                {
                    int len = di_stream.readInt();
                    byte[] d = new byte[len];
                    di_stream.readFully(d);
                    String err = new String(d);
                    scInter.fireDataChanged();
                    scInter.showError(err);
                }

            }	
            else if (reqType == AuthConstants.DELETE_OPERATIONS_RESP)
            {
                int data_length = di_stream.readInt();
                if(data_length == AuthConstants.SUCCESS)
                {
                    int len = di_stream.readInt();
                    byte [] data_bytes = new byte[len];
                    di_stream.readFully(data_bytes);
                    xmlParser.createXmlDocumentForReturn(data_bytes,AuthConstants.DELETE_OPERATIONS_RESP);
                    Node mainNode= xmlParser.getOperationsTree();
                    constructOperationsTree(mainNode);
                    scInter.fireDataChanged();
                }

                else
                {
                    int len = di_stream.readInt();
                    byte[] d = new byte[len];
                    di_stream.readFully(d);
                    String err = new String(d);
                    scInter.fireDataChanged();
                    scInter.showError(err);
                }


            }		

            else if (reqType == AuthConstants.DELETE_USER_RESP)
            {
                //System.out.println("succes");
                int data_length = di_stream.readInt();
                if(data_length == AuthConstants.SUCCESS)
                {
                    int len = di_stream.readInt();
                    byte [] data_bytes = new byte[len];
                    di_stream.readFully(data_bytes);
                    xmlParser.createXmlDocumentForReturn(data_bytes,AuthConstants.DELETE_USER_RESP);
                    scInter.fireDataChanged();
                    fetchAuthScope();
                    //System.out.println("succes");
                }

                else
                {
                    int len = di_stream.readInt();
                    byte[] d = new byte[len];
                    di_stream.readFully(d);
                    String err = new String(d);
                    scInter.showError(err);

                }		
            }

            else if (reqType == AuthConstants.DELETE_AUTH_VIEWS_WITH_OPER_RESP)
            {
                int data_length = di_stream.readInt();
                if(data_length == AuthConstants.SUCCESS)
                {
                    int len = di_stream.readInt();
                    byte [] data_bytes = new byte[len];
                    di_stream.readFully(data_bytes);
                    xmlParser.createXmlDocumentForReturn(data_bytes,AuthConstants.DELETE_AUTH_VIEWS_WITH_OPER_RESP);
                    scInter.fireDataChanged();
                }

                else
                {
                    int len = di_stream.readInt();
                    byte[] d = new byte[len];
                    di_stream.readFully(d);
                    String err = new String(d);
                    scInter.showError(err);

                }		
            }


            else if (reqType == AuthConstants.DELETE_GROUPS_RESP)
            {
                int data_length = di_stream.readInt();
                if(data_length == AuthConstants.SUCCESS)
                {
                    int len = di_stream.readInt();
                    byte [] data_bytes = new byte[len];
                    di_stream.readFully(data_bytes);
                    xmlParser.createXmlDocumentForReturn(data_bytes,AuthConstants.DELETE_GROUPS_RESP);
                    scInter.fireDataChanged();
                    fetchAuthScope();
                }

                else
                {
                    int len = di_stream.readInt();
                    byte[] d = new byte[len];
                    di_stream.readFully(d);
                    String err = new String(d);
                    scInter.showError(err);

                }		
            }

            else if (reqType == AuthConstants.SET_ALL_ATTRIBUTES_RESP)
            {
                int data_length = di_stream.readInt();
                if(data_length == AuthConstants.SUCCESS)
                {
                    int len = di_stream.readInt();
                    byte [] data_bytes = new byte[len];
                    di_stream.readFully(data_bytes);
                    xmlParser.createXmlDocumentForReturn(data_bytes,AuthConstants.SET_ALL_ATTRIBUTES_RESP);
                    scInter.fireDataChanged();	
                }
                else
                {
                    int len = di_stream.readInt();
                    byte[] d = new byte[len];
                    di_stream.readFully(d);
                    String err = new String(d);
                    scInter.showError(err);
                }

            }	

            else if (reqType == AuthConstants.ADD_OPER_GROUP_RESP)
            {
                int data_length = di_stream.readInt();
                if(data_length == AuthConstants.SUCCESS)
                {
                    int len = di_stream.readInt();
                    byte [] data_bytes = new byte[len];
                    di_stream.readFully(data_bytes);
                    xmlParser.createXmlDocumentForReturn(data_bytes,AuthConstants.ADD_OPER_GROUP_RESP);
                    users_vector = xmlParser.getAllUsers();
                    fetchSecurityInfo();
                    //fetchAuthScope();
                }
                else
                {
                    int len = di_stream.readInt();
                    byte[] d = new byte[len];
                    di_stream.readFully(d);
                    String err = new String(d);
                    scInter.showError(err);
                }

            }	

            else if (reqType == AuthConstants.UPDATE_AUTH_VIEWS_RESP)
            {
                int data_length = di_stream.readInt();
                if(data_length == AuthConstants.SUCCESS)
                {
                    int len = di_stream.readInt();
                    byte [] data_bytes = new byte[len];
                    di_stream.readFully(data_bytes);
                    xmlParser.createXmlDocumentForReturn(data_bytes,AuthConstants.UPDATE_AUTH_VIEWS_RESP);
                    //scInter.fireDataChanged();
                    fetchAuthScope();
                }

                else
                {
                    int len = di_stream.readInt();
                    byte[] d = new byte[len];
                    di_stream.readFully(d);
                    String err = new String(d);
                    scInter.showError(err);

                }		
            }

            else if (reqType == AuthConstants.ADD_SCOPE_RESP)
            {
                int data_length = di_stream.readInt();
                if(data_length == AuthConstants.SUCCESS)
                {
                    int len = di_stream.readInt();
                    byte [] data_bytes = new byte[len];
                    di_stream.readFully(data_bytes);
                    xmlParser.createXmlDocumentForReturn(data_bytes,AuthConstants.ADD_SCOPE_RESP);
                    //	users_vector = xmlParser.getAllUsers();
                    fetchSecurityInfo();
                }
                else
                {
                    int len = di_stream.readInt();
                    byte[] d = new byte[len];
                    di_stream.readFully(d);
                    String err = new String(d);
                    scInter.showError(err);
                }

            }	

            //Last method
            else if (reqType == AuthConstants.MODIFY_OPER_FOR_USER_RESP)
            {
                int data_length = di_stream.readInt();
                if(data_length == AuthConstants.SUCCESS)
                {
                    int len = di_stream.readInt();
                    byte [] data_bytes = new byte[len];
                    di_stream.readFully(data_bytes);
                    xmlParser.createXmlDocumentForReturn(data_bytes,AuthConstants.MODIFY_OPER_FOR_USER_RESP);
                    //	        Node mainNode= xmlParser.getOperationsTree();
                    //	constructOperationsTree(mainNode);
                    //	scInter.fireDataChanged();
                    fetchSecurityInfo();	
                }

                else	
                {
                    int len = di_stream.readInt();
                    byte[] d = new byte[len];
                    di_stream.readFully(d);
                    String err = new String(d);
                    scInter.fireDataChanged();
                    scInter.showError(err);
                }

            }

            //One more ..
            else if (reqType == AuthConstants.SAVE_AUDIT_TRAILS_RESP)
            {
                int data_length = di_stream.readInt();
                if(data_length == AuthConstants.SUCCESS)
                {
                    int len = di_stream.readInt();
                    byte [] data_bytes = new byte[len];
                    di_stream.readFully(data_bytes);
                }
                else	
                {
                    int len = di_stream.readInt();
                    byte[] d = new byte[len];
                    di_stream.readFully(d);
                    String err = new String(d);
                    scInter.showError(err);
                }
            }
            else if (reqType == AuthConstants.GET_PROP_NAMES_FOR_CVS)
            {
                int dataLength = di_stream.readInt();
                byte[] dataBytes = new byte[dataLength];
                di_stream.readFully(dataBytes, 0, dataLength);
                propNames = NmsClientUtil.deSerializeProperties(dataBytes);
            }
        }
        catch(Exception e)
        {
            //e.printStackTrace();
            //System.out.println("Exception Thrown in on reading the Security information ");
        }
    }





    /* constructs a Default Tree Model from XML node */





    public DefaultTreeModel constructOperationsTree(Node xmlNode)
    {
        DefaultTreeModel treeModel;
        DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("Operation-Tree");
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
                    Node parent = nodeMap.getNamedItem("parent");
                    Node child = nodeMap.getNamedItem("child");
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
	    for(Enumeration enumeration = hash.keys();enumeration.hasMoreElements();)
            {
		String parent = (String)enumeration.nextElement();
                DefaultMutableTreeNode childNode = getChildNode(parent,hash,nodeHash);
                nodeHash.put(parent,childNode);
            }

            //for(Enumeration enum1 = nodeHash.elements(); enum1.hasMoreElements();)
            //{
            //       DefaultMutableTreeNode childNode = (DefaultMutableTreeNode) enum1.nextElement();
            //        TreeObject childOb = new TreeObject(childNode.getUserObject());
            //        childNode.setUserObject(childOb); 
            //        rootNode.add(childNode); 
            //}

	    for(Enumeration enum1 = nodeHash.keys(); enum1.hasMoreElements();)
            {
		String element = (String)enum1.nextElement();
                DefaultMutableTreeNode childNode = (DefaultMutableTreeNode)nodeHash.get(element);
                TreeObject childOb = new TreeObject(childNode.getUserObject(), element);
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
	for(Enumeration enumeration = hash.keys();enumeration.hasMoreElements();)
        {
	    String parent = (String)enumeration.nextElement();
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
        DefaultMutableTreeNode treeNode = new DefaultMutableTreeNode(getAlias(parent));
	for(Enumeration enumeration = vect.elements();enumeration.hasMoreElements();)
        {
	    String element = (String) enumeration.nextElement();
            DefaultMutableTreeNode childNode;
            if(nodeHash.containsKey(element))
            {
                childNode = (DefaultMutableTreeNode) nodeHash.remove(element);
                //TreeObject treeOb = new TreeObject(childNode.getUserObject());
                TreeObject treeOb = new TreeObject(childNode.getUserObject(),element);
                childNode.setUserObject(treeOb);	
            }
            else
            {
                if(hash.containsKey(element))
                {
                    childNode = getChildNode(element,hash,nodeHash);
                    TreeObject treeOb = new TreeObject(childNode.getUserObject(),element);
                    childNode.setUserObject(treeOb);		
                }
                else
                {
                    childNode = new DefaultMutableTreeNode(getAlias(element));
                    TreeObject treeOb = new TreeObject(childNode.getUserObject(),element);
                    childNode.setUserObject(treeOb);		
                }
            }
            treeNode.add(childNode);
        }
        //treeNode.add(childNode);
        return treeNode;
    }




    /*
     * 
     * Sends request to fetch the entire Security Information from the server.
     */


    public void fetchSecurityInfo()
    {
        try
        {
            ByteArrayOutputStream byteStream = new ByteArrayOutputStream ();
            DataOutputStream outp = new DataOutputStream (byteStream);
            //send the request type
            outp.writeInt(AuthConstants.GET_ALL_OPERATIONS);
            byte[] bytes = byteStream.toByteArray();
            getAdapterInstance().send(bytes);
            outp.close();
            byteStream.reset();
            byteStream.close();
        }
        catch(Exception ioex)
        {

        }
    }


    public void fetchAttributeInfo()
    {
        try
        {
            ByteArrayOutputStream byteStream = new ByteArrayOutputStream ();
            DataOutputStream outp = new DataOutputStream (byteStream);
            //send the request type
            outp.writeInt(AuthConstants.GET_ALL_ATTRIBUTES);
            byte[] bytes = byteStream.toByteArray();
            getAdapterInstance().send(bytes);
            outp.close();
            byteStream.reset();
            byteStream.close();
        }
        catch(Exception ioex)
        {

        }
    }

    public void checkAuthorizationForButtons()
    {
	ByteArrayOutputStream byteStream = null;
	DataOutputStream outp = null;
	try
	    {
		byteStream = new ByteArrayOutputStream ();
		outp = new DataOutputStream (byteStream);
		outp.writeInt(AuthConstants.CHECK_OPERATIONS);
		byte[] bytes = byteStream.toByteArray();
		getAdapterInstance().send(bytes);
		outp.close();
		byteStream.reset();
		byteStream.close();
	    }
	catch(Exception e) {}
	finally
	    {
		try
		    {
			outp.close();
		    }
		catch(Exception e){}
		finally
		    {
			try
			    {
				byteStream.close();
			    }
			catch(Exception e){}
		    }
	    }
    }
    
    public Properties getPropertyNamesForCustomViewScope()
    {
        return propNames;
    }

    public void sendRequestForPropertyNames()
    {
        try
        {
            ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
            DataOutputStream outp = new DataOutputStream(byteStream);
            //send the request type
            outp.writeInt(AuthConstants.GET_PROP_NAMES_FOR_CVS);
            byte[] bytes = byteStream.toByteArray();
            getAdapterInstance().send(bytes);
            outp.close();
            byteStream.reset();
            byteStream.close();
        }
        catch(Exception ioex)
        {
            ioex.printStackTrace();
        }
    }

    public void clearAudit(String userName){
        try
        {
            ByteArrayOutputStream byteStream = new ByteArrayOutputStream ();
            DataOutputStream outp = new DataOutputStream (byteStream);
            outp.writeInt(AuthConstants.CLEAR_AUDIT_TRAILS);
            byte[] userData = xmlParser.clearAudit(userName);
            int len = userData.length;
            outp.writeInt(len);
            //outp.writeBytes(new String(userData));
            outp.write(userData,0,len);
            byte[] bytes = byteStream.toByteArray();
            getAdapterInstance().send(bytes);
            outp.close();
            byteStream.reset();
            byteStream.close();
        }
        catch(Exception ioex)
        {

        }
    }


    //	public void setUserData(String uname,String pwd,Vector groups)	
    public void setUserData(String uname,String pwd,Vector groups, Hashtable addoper,Hashtable modoper,String descName)	
    {

        try
        {

            ByteArrayOutputStream byteStream = new ByteArrayOutputStream ();
            DataOutputStream outp = new DataOutputStream (byteStream);
            outp.writeInt(AuthConstants.ADD_USER_WITH_PROPS);
            byte[] userData = xmlParser.addOrModifyUser(uname,pwd,groups, addoper, modoper,descName);
            int len = userData.length;
            outp.writeInt(len);
            //outp.writeBytes(new String(userData));
            outp.write(userData,0,len);
            byte[] bytes = byteStream.toByteArray();
            getAdapterInstance().send(bytes);
            outp.close();
            byteStream.reset();
            byteStream.close();
        }
        catch(Exception ioex)
        {

        }

    }	

    public void modifyUserData(String uname,String pwd, Vector groups, Hashtable addoper,Hashtable modoper,String descName)	
    {


        try
        {
            ByteArrayOutputStream byteStream = new ByteArrayOutputStream ();
            DataOutputStream outp = new DataOutputStream (byteStream);
            //send the request type
            outp.writeInt(AuthConstants.MODIFY_USER_WITH_PROPS);
            byte[] userData = xmlParser.addOrModifyUser(uname,pwd,groups, addoper, modoper,descName);
            int len = userData.length;
            outp.writeInt(len);
            //outp.writeBytes(new String(userData));
            outp.write(userData,0,len);
            byte[] bytes = byteStream.toByteArray();
            getAdapterInstance().send(bytes);
            outp.close();
            byteStream.reset();
            byteStream.close();
        }
        catch(Exception ioex)
        {

        }


    }	

    public void modifyUserGroupData(String group,Vector users)	
    {

        try
        {

            ByteArrayOutputStream byteStream = new ByteArrayOutputStream ();
            DataOutputStream outp = new DataOutputStream (byteStream);
            outp.writeInt(AuthConstants.ADD_USERS);
            byte[] userData = xmlParser.addUsers(group, users);
            int len = userData.length;
            outp.writeInt(len);
            //outp.writeBytes(new String(userData));
            outp.write(userData,0,len);
            byte[] bytes = byteStream.toByteArray();
            getAdapterInstance().send(bytes);
            outp.close();
            byteStream.reset();
            byteStream.close();
        }
        catch(Exception ioex)
        {

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
            //outp.writeBytes(new String(groupData));
            outp.write(groupData,0,len);
            byte[] bytes = byteStream.toByteArray();
            getAdapterInstance().send(bytes);
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
            ByteArrayOutputStream byteStream = new ByteArrayOutputStream ();
            DataOutputStream outp = new DataOutputStream (byteStream);
            byte[] groupData;
            if(addoper == null){
                outp.writeInt(AuthConstants.MODIFY_OPER_GROUP);
                groupData = xmlParser.addOrModifyOperationsForGroup(gname,modoper,addoper);
            }	
            else{
                outp.writeInt(AuthConstants.ADD_OPER_GROUP);
                groupData = xmlParser.addOrModifyOperationsForGroup(gname,addoper,modoper);
            }	
            int len = groupData.length;
            outp.writeInt(len);
            //outp.writeBytes(new String(groupData));
            outp.write(groupData,0,len);
            byte[] bytes = byteStream.toByteArray();
            getAdapterInstance().send(bytes);
            outp.close();
            byteStream.reset();
            byteStream.close();
        }
        catch(Exception ioex)
        {

        }


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
            //outp.writeBytes(new String(groupData));
            outp.write(groupData,0,len);
            byte[] bytes = byteStream.toByteArray();
            getAdapterInstance().send(bytes);
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
            ByteArrayOutputStream byteStream = new ByteArrayOutputStream ();
            DataOutputStream outp = new DataOutputStream (byteStream);
            outp.writeInt(AuthConstants.DELETE_USER);
            byte[] userData = xmlParser.deleteUser(uname);
            int len = userData.length;
            outp.writeInt(len);
            //outp.writeBytes(new String(userData));
            outp.write(userData,0,len);
            byte[] bytes = byteStream.toByteArray();
            getAdapterInstance().send(bytes);
            outp.close();
            byteStream.reset();
            byteStream.close();

        }
        catch(Exception ioex)
        {

        }


    }

    public void changePassword(String uname, String newpass)
    {

        try
        {
            ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
            DataOutputStream outp = new DataOutputStream (byteStream);
            outp.writeInt(AuthConstants.CHANGE_PASSWORD);
            byte[] groupData = xmlParser.changePassword(uname, newpass,null);
            int len = groupData.length;
            outp.writeInt(len);
            //outp.writeBytes(new String(groupData));
            outp.write(groupData,0,len);
            byte[] bytes = byteStream.toByteArray();
            getAdapterInstance().send(bytes);
            outp.close();
            byteStream.reset();
            byteStream.close();
        }
        catch(Exception ioex)
        {

        }


    }	

    public void deleteGroup(String gname)
    {

        try
        {
            ByteArrayOutputStream byteStream = new ByteArrayOutputStream ();
            DataOutputStream outp = new DataOutputStream (byteStream);
            outp.writeInt(AuthConstants.DELETE_GROUPS);
            byte[] groupData = xmlParser.deleteGroup(gname);
            int len = groupData.length;
            outp.writeInt(len);
            //outp.writeBytes(new String(groupData));
            outp.write(groupData,0,len);
            byte[] bytes = byteStream.toByteArray();
            getAdapterInstance().send(bytes);
            outp.close();
            byteStream.reset();
            byteStream.close();
        }
        catch(Exception ioex)
        {

        }


    }	

    public void addViewProp(String gpname,String opname,Properties modoper)	
    {

        try
        {

            ByteArrayOutputStream byteStream = new ByteArrayOutputStream ();
            DataOutputStream outp = new DataOutputStream (byteStream);
            outp.writeInt(AuthConstants.ADD_SCOPE);
            byte[] userData = xmlParser.createScope(gpname,opname, modoper);
            int len = userData.length;
            outp.writeInt(len);
            //outp.writeBytes(new String(userData));
            outp.write(userData,0,len);
            byte[] bytes = byteStream.toByteArray();
            getAdapterInstance().send(bytes);
            outp.close();
            byteStream.reset();
            byteStream.close();
        }
        catch(Exception ioex)
        {

        }

    }	

    public void addAuthViewProp(String gpname,Properties modoper,String groupName)	
    {

        try
        {

            ByteArrayOutputStream byteStream = new ByteArrayOutputStream ();
            DataOutputStream outp = new DataOutputStream (byteStream);
            outp.writeInt(AuthConstants.ADD_SCOPE);
            byte[] userData = xmlParser.createScope(gpname, modoper,groupName);
            int len = userData.length;
            outp.writeInt(len);
            //outp.writeBytes(new String(userData));
            outp.write(userData,0,len);
            byte[] bytes = byteStream.toByteArray();
            getAdapterInstance().send(bytes);
            outp.close();
            byteStream.reset();
            byteStream.close();
        }
        catch(Exception ioex)
        {

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
            //outp.writeBytes(new String(viewData));
            outp.write(viewData,0,len);
            byte[] bytes = byteStream.toByteArray();
            getAdapterInstance().send(bytes);
            outp.close();
            byteStream.reset();
            byteStream.close();
        }
        catch(Exception ioex)
        {
            //ioex.printStackTrace();						
        }


    }	

    public void modViewProp(String groupName,boolean forDelete, Vector newViewsVec, String cvscope)
    {
        try
        {
            //System.out.println("Group Name : "+groupName);
            //System.out.println("For Delete : "+forDelete);
            //System.out.println("Views Vector : "+newViewsVec);
            //System.out.println(" CVScope : "+cvscope);
            ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
            DataOutputStream outp = new DataOutputStream(byteStream);
            outp.writeInt(AuthConstants.UPDATE_AUTH_VIEWS);

            byte[] viewData = xmlParser.modifyAuthorizedViewForGroup(groupName,forDelete,newViewsVec,cvscope);
            int len = viewData.length;
            outp.writeInt(len);
            //outp.writeBytes(new String(viewData));
            outp.write(viewData,0,len);
            byte[] bytes = byteStream.toByteArray();
            getAdapterInstance().send(bytes);
            outp.close();
            byteStream.reset();
            byteStream.close();
        }
        catch(Exception ioex)
        {
            ioex.printStackTrace();					
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
            //outp.writeBytes(new String(viewData));
            outp.write(viewData,0,len);
            byte[] bytes = byteStream.toByteArray();
            getAdapterInstance().send(bytes);
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
            //outp.writeBytes(new String(viewData));
            outp.write(viewData,0,len);
            byte[] bytes = byteStream.toByteArray();
            getAdapterInstance().send(bytes);
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
            ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
            DataOutputStream outp = new DataOutputStream (byteStream);

            outp.writeInt(AuthConstants.MODIFY_OPER_FOR_USER);

            byte[] viewData = xmlParser.addOrModifyUser_new(vname,hash);
            int len = viewData.length;
            outp.writeInt(len);
            //outp.writeBytes(new String(viewData));
            outp.write(viewData,0,len);
            byte[] bytes = byteStream.toByteArray();
            getAdapterInstance().send(bytes);
            outp.close();
            byteStream.reset();
            byteStream.close();
        }
        catch(Exception ioex)
        {

        }

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


    public void addOperation(String oper, String parent, String leaf)
    {
        operNode = xmlParser.addOper(oper,parent,leaf);

    }	



    public void removeOperation(String oper, String parent, String leaf)
    {
        operDelete = xmlParser.deleteOper(oper,parent,leaf);	
    }	



    public void setOperations()	
    {

        if(operNode != null)
        {
            try
            {
                ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
                DataOutputStream outp = new DataOutputStream (byteStream);

                if(operNode == null)
                {
                    return;
                }		
                outp.writeInt(AuthConstants.ADD_OPERATIONS);

                byte[] operData = xmlParser.setOperTree(operNode);		
                int len = operData.length;
                outp.writeInt(len);
                //outp.writeBytes(new String(operData));
                outp.write(operData,0,len);	
                byte[] bytes = byteStream.toByteArray();
                getAdapterInstance().send(bytes);
                outp.close();
                byteStream.reset();
                byteStream.close();
            }
            catch(Exception ioex)
            {

            }
        }

        if(operDelete != null)
        {

            try
            {
                ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
                DataOutputStream outp = new DataOutputStream (byteStream);


                outp.writeInt(AuthConstants.DELETE_OPERATIONS);

                byte[] operData = xmlParser.setOperTree(operDelete);		
                int len = operData.length;
                outp.writeInt(len);
                //outp.writeBytes(new String(operData));
                outp.write(operData,0,len);	
                byte[] bytes = byteStream.toByteArray();
                getAdapterInstance().send(bytes);
                outp.close();
                byteStream.reset();
                byteStream.close();
            }
            catch(Exception ioex)
            {

            }

        }


        operNode = null;	
        operDelete = null;

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
	    boolean showAllUsers = false;
	    boolean check_For_Authorization_AtUserLevel = false;
	    String currentUserName = com.adventnet.nms.util.NmsClientUtil.getUserName();            

	    Vector currentUserGroups = getGroupsForUser(currentUserName);
	    Hashtable operationsList = null;
	    for(int i=0;i<currentUserGroups.size();i++)
	    {
		    operationsList = getOperationsForUser((String)currentUserGroups.elementAt(i));
		    showAllUsers = isAuthorisedToViewUsers(operationsList);
		    if(showAllUsers)
		    {
		    	break;
		    }
	    }
	    
	    if(!showAllUsers)
	    {
	    	operationsList = getOperations(currentUserName);
		    showAllUsers = isAuthorisedToViewUsers(operationsList);
	    }

	    if(showAllUsers)
	    {
        users_vector = xmlParser.getAllUsers();
        return users_vector;
    }	   
   return new Vector();
  } 

    private boolean isAuthorisedToViewUsers(Hashtable operationsList)
    {
    	boolean showAllUsers = false;
    	String userAdminPerm = (String)operationsList.get("User Administration");
	    String usersListPerm = (String)operationsList.get("Get List of Users");
	    if(usersListPerm != null)
	    {
		    if((new Integer(usersListPerm)).intValue() == 1)
		    {
			    showAllUsers = true;
		    }
	    }
	    else
	    {
		    if(userAdminPerm != null && (new Integer(userAdminPerm)).intValue() == 1)              
		    {
			    showAllUsers = true;
		    }
	    }
	    
	    return showAllUsers;
    }
    
    public DefaultTreeModel getTreeModel()
    {
        Node mainNode= xmlParser.getOperationsTree();
        return constructOperationsTree(mainNode);
    }

    String getAlias(String nameStr)
    {
        //return AuthMain.resourceBundle.getString(nameStr);	
	String I18str = AuthMain.resourceBundle.getString("security.operation.tree." + nameStr);
	if(I18str.equals("security.operation.tree." + nameStr))
		return (nameStr);
	return I18str;
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
	if(index != -1)
	{
        	usergroups = (Vector)users_vec.elementAt(index+1);
	}
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

    //Method for audit
    public void fetchAuditInfo()
    {
        try
        {
            ByteArrayOutputStream byteStream = new ByteArrayOutputStream ();
            DataOutputStream outp = new DataOutputStream (byteStream);
            //send the request type
            outp.writeInt(AuthConstants.GET_AUDIT_TRAILS);
            byte[] bytes = byteStream.toByteArray();
            getAdapterInstance().send(bytes);
            outp.close();
            byteStream.reset();
            byteStream.close();
        }
        catch(Exception ioex)
        {
            ioex.printStackTrace();
        }
    }	




    public Vector getAudit(String userName){
        fetchAuditInfo();
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

    public Hashtable getOperations(String user)
    {
        return xmlParser.getAuthOperationsForUser(user);
    }

    /*public Hashtable getOperations(String user){

      java.util.Vector groups = xmlParser.getGroupsForUser(user);
      for(int i =0;i<groups.size();i++){
      groups.addElement("default "+groups.elementAt(i).toString()+" View");
      }
      groups.addElement("default "+user+" View");
    //System.out.println(groups);
    return getOperationsForGroup(groups);


    java.util.Vector views = xmlParser.getViewsForUser(user);
    //System.out.println("THE VIEWS DIRECT "+views);
    views.addElement("default "+user+" View");
    java.util.Hashtable oper = null;
    allOper1 = new java.util.Hashtable();
    int size = views.size();
    for(int i=0;i<size;i++)
    {
    oper = new java.util.Hashtable();
    oper = xmlParser.getOperationsForViews(views.elementAt(i).toString());	
    //	System.out.println("OPERATIONS "+oper);

    //int siz = allOper.size();
    if(size != 0 && oper.size()!=0)
    for(Enumeration e= oper.keys();e.hasMoreElements();)
    {
    //String operName = e.nextElement().toString();
    //for(Enumeration en = oper.keys().en.hasMoreElements();){
    //	String name = en.nextElement().toString();
    //if(operName.e
    String name = e.nextElement().toString();	
    //System.out.println("THE OPERATION "+name);
    if(allOper1.containsKey(name) && (allOper1.get(name).toString()).equals("1"))
    {	
        //	break;
    }		
    else if(allOper1.containsKey(name) && (allOper1.get(name).toString()).equals("0"))
    {	
        //System.out.println("DON OT PUT ");	
        allOper1.put(name,oper.get(name));
    }
    else if(!allOper1.containsKey(name))
    {
        allOper1.put(name,oper.get(name));
    }	
    //System.out.println("THE HASHTABLE "+allOper1);

    if(allOper.contains(name) && (oper.get(name).toString()).equals("1"))
        break;
    else if(	
            allOper.put(name,oper.get(name));

            }
            }	

            //System.out.println("USER VIA "+allOper1);
            //return allOper;
            return allOper1;
            }*/	

            public Hashtable getOperationsForGroup(Vector groups){
            java.util.Vector views = xmlParser.getViewsForGroups(groups);
            //System.out.println("THE VIEWS VIA THE GROUP "+views);
            java.util.Hashtable oper = null;
            allOper = new java.util.Hashtable();
            int size = views.size();
            for(int i=0;i<size;i++){
            oper = new java.util.Hashtable();
            oper = xmlParser.getOperationsForViews(views.elementAt(i).toString());	
            //System.out.println("OPERP"+oper);	


            //int siz = allOper.size();
            if(size !=0 && oper.size()!=0)
                for(Enumeration e= oper.keys();e.hasMoreElements();){
                    //String operName = e.nextElement().toString();
                    //for(Enumeration en = oper.keys().en.hasMoreElements();){
                    //	String name = en.nextElement().toString();
                    //if(operName.e
                    String name = e.nextElement().toString();	
                    if(allOper.contains(name) && (allOper.get(name).toString()).equals("1"))
                    {	
                        //	break;
                    }		
                    else if(allOper.contains(name) && (allOper.get(name).toString()).equals("0"))
                    {	
                        //System.out.println("DON OT PUT ");	
                        allOper.put(name,oper.get(name));
                    }
                    else if(!allOper.containsKey(name))
                    {	
                        allOper.put(name,oper.get(name));
                    }		
                }
                }
            //return allOper;
            //System.out.println(" GROUP's "+allOper);
            return allOper;
            }	




            public Vector getGroups()
            {
                return xmlParser.getAllGroupsFromUserGroupTable();
            }	


            public Vector getNewGroup()
            {
                Vector temp = newgroup;
                return newgroup;
            }

            public Hashtable getUserAttributes(String username)
            {
                fetchAttributeInfo();

                return xmlParser.getAllAttributes(username);
            }

            public Hashtable getAllUserAttributes()
            {
                fetchAttributeInfo();

                Hashtable userAttributes = new Hashtable();
                Vector users = getAllUserNames();

                if (users == null)
                {
                    return null;
                }

                for (int i = 0; i < users.size(); i++)
                {
                    String userName = (String) users.elementAt(i);
                    userAttributes.put(userName, xmlParser.getAllAttributes(userName));
                }
                return userAttributes;
            }

            public void setUserAttributes(String username, String status, Integer userage, Integer passwordage,String descName )
            {
                try
                {
                    ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
                    DataOutputStream outp = new DataOutputStream(byteStream);
                    outp.writeInt(AuthConstants.SET_ALL_ATTRIBUTES);
                    byte[] ageData = xmlParser.setAllAttributes(username,null, userage, passwordage,descName);
                    int len = ageData.length;
                    outp.writeInt(len);
                    //outp.writeBytes(new String(ageData));
                    outp.write(ageData,0,len);
                    byte[] bytes = byteStream.toByteArray();
                    getAdapterInstance().send(bytes);
                    outp.close();
                    byteStream.reset();
                    byteStream.close();
                }
                catch(Exception e)
                {
                    e.printStackTrace();			
                }


            }
            public void setUserStatus(String username, String status)
            {
                try
                {
                    ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
                    DataOutputStream outp = new DataOutputStream(byteStream);
                    outp.writeInt(AuthConstants.SET_USER_STATUS);
                    byte[] ageData = xmlParser.setAllAttributes(username,status,null,null,null);
                    //byte[] ageData = xmlParser.setAllAttributes("test","disabled",null,null);
                    int len = ageData.length;
                    outp.writeInt(len);
                    //outp.writeBytes(new String(ageData));
                    outp.write(ageData,0,len);
                    byte[] bytes = byteStream.toByteArray();
                    getAdapterInstance().send(bytes);
                    outp.close();
                    byteStream.reset();
                    byteStream.close();
                }
                catch(Exception e)
                {
                    e.printStackTrace();			
                }


            }	

            public void fetchAuthScope()
            {
                //System.out.println(" Inside ");
                try
                {
                    ByteArrayOutputStream byteStream = new ByteArrayOutputStream ();
                    DataOutputStream outp = new DataOutputStream (byteStream);
                    //send the request type
                    outp.writeInt(AuthConstants.GET_AUTH_VIEWS);
                    byte[] bytes = byteStream.toByteArray();
                    //System.out.println(" Before sending req  ");
                    getAdapterInstance().send(bytes);
                    //System.out.println(" After sending Reqqq ");
                    outp.close();
                    byteStream.reset();
                    byteStream.close();
                }
                catch(Exception ioex)
                {
                    ioex.printStackTrace();
                }
            }	

            public void assignAuthScope(String custView,Vector authScopes,String groupName)
            {
                //System.out.println(" Group Name in Assign Auth Scope "+groupName);
                ByteArrayOutputStream byteStream = null;
                DataOutputStream outp = null;
                try
                {
                    byteStream = new ByteArrayOutputStream ();
                    outp = new DataOutputStream (byteStream);
                    outp.writeInt(AuthConstants.CREATE_CUSTOM_VIEW);
                    byte[] userData = xmlParser.createCustomViewScope(custView,authScopes,groupName);
                    int len = userData.length;
                    outp.writeInt(len);
                    //outp.writeBytes(new String(userData));
                    outp.write(userData,0,len);
                    byte[] bytes = byteStream.toByteArray();
                    getAdapterInstance().send(bytes);
                    outp.close();
                    byteStream.reset();
                    byteStream.close();
                }catch(Exception ioex){	
                    ioex.printStackTrace();
                }finally{
                    try{
                        byteStream.close();
                        outp.close();
                    }catch(IOException ioe){
                    }
                }

            }


            public Hashtable getAuthScope(String groupName)
            {
                //System.out.println( " Group Name    "+groupName);
                //System.out.println(" In GET AUTH SCOPE "+ xmlParser.getAuthViewsForCustom());
                //fetchCustomScope();
                //Vector toReturn = (Vector)(xmlParser.getAuthViewsForCustom().get(userName));

                Hashtable  toReturn = (Hashtable)xmlParser.getAuthViewsForCustom().get(groupName);
                //System.out.println( " To Return      " + toReturn);

                if(toReturn != null)
                {
                    return toReturn;
                }
                else
                {
                    return new Hashtable();
                }
            }

            public Vector getAllAuthScopes()
            {
                return xmlParser.getAllAuthorizedView();
                //return new Vector();
            }

            public Vector getAllAuthScopes(String customView)
            {
                return xmlParser.getAllAuthorizedView(customView);
            }

            //Method added for saving the audit details.
            public void saveAuditDetails(String fileName,String auditDetails)
            {
                try
                {
                    ByteArrayOutputStream byteStream = new ByteArrayOutputStream ();
                    DataOutputStream outp = new DataOutputStream (byteStream);
                    outp.writeInt(AuthConstants.SAVE_AUDIT_TRAILS);
                    byte[] userData = xmlParser.saveToFile(fileName,auditDetails);
                    int len = userData.length;
                    outp.writeInt(len);
                    //outp.writeBytes(new String(userData));
                    outp.write(userData,0,len);
                    byte[] bytes = byteStream.toByteArray();
                    getAdapterInstance().send(bytes);
                    outp.close();
                    byteStream.reset();
                    byteStream.close();
                }
                catch(Exception ioex)
                {

                }
            }
            //Method added for showing help.
            public void showHelp(String urlKey)
            {
                com.adventnet.nms.util.NmsClientUtil.showHelpBasedOnKey(urlKey);
            }


            public void cleanUp()
            {

                deRegister();
                users_vector = null;
                groups_vector = null;
                views_vector = null;
                treeModel = null;
            }

	    public void refetchData()
	    {
	    	fetchSecurityInfo();
		fetchAuditInfo();
		sendRequestForPropertyNames();            	
	    }		

            }










































































