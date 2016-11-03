//$Id: SecurityModelRMIImpl.java,v 1.3 2007/07/26 06:20:44 tinku Exp $


package com.adventnet.security.ui;

import javax.swing.tree.*;
import java.util.Vector;
import java.util.Hashtable;
import java.util.Properties;
import java.rmi.*;
import java.util.Enumeration;


//Security Imports
import com.adventnet.security.authorization.*;
import com.adventnet.security.authentication.RMIAccessAPI;
import com.adventnet.security.audit.*;
import com.adventnet.security.authentication.AuthenticationTicket; 
import com.adventnet.security.authentication.AuthTicket; 
import com.adventnet.security.AuthUtil;
import com.adventnet.security.crypto.CryptoGraphAPI;

/**
  The Abstract base class for all the SecurityModels  . Any class which will be used as the model for the Security GUI 
  Will need to extend AbstractSecurityModel
 */

public  class SecurityModelRMIImpl extends AbstractSecurityModel
{

    /* users_vector should follow the following format. 1st element string username , next element 
       a vector of all the groups associated with that user, and so on*/
    protected Vector users_vector = null;

    /*groups_vector should be  similar to users_vector. with 1st element being string groupname and 
      next element a vector of associated viewnames for this group, and so on*/
    protected  Vector groups_vector = null;

    /*views_vector is a vector of AuthorizedViewObjects which contain the view name, a Properties 
      void containing the view properties. and a hashtable of operations associated with the view.*/
    protected  Vector views_vector = null;

    protected Vector operations = null;	

    DefaultMutableTreeNode treeNode = null;		

    /*The operationstree is parsed by the model and a TreeModel is constructed which will be used by the 
      GUI to populate the JTrees which depict the operations.*/
    protected  DefaultTreeModel treeModel = null;

    //Pperation to be added and/or removed are cached in these hashtable and set in one shot .	
    Hashtable addoperations = null;
    Vector addOper = null;
    Hashtable remoperations = null;	


    //Refrence of the remote object AuthorizationAdmin .
    protected CustomViewScopeAPI authAdmin = null;

    //Refrence of the remote object AuthorizationEngine	
    AuthorizationEngine authEngine = null;

    //Reference of the remote object AuditAPI .
    AuditAPI authAudit = null;


    // RMIAccessAPI for getting api in RMI Secure mode.
    protected RMIAccessAPI rmiaccessapi = null;

    private String userName = null;
    private String passWord = null;            
    private String cryptoClass = null;            
    /* An interface implemented by the independent GUI screens such as User group screen etc. 
       This is used to fire data changes into these screens when they are invoked for the first time 
       or when the updated data is recieved after making a configuration.*/

    protected  com.adventnet.security.ui.SecurityCommonInterface scInter = null;

    public SecurityModelRMIImpl()
    {
        users_vector = new Vector();	
        groups_vector = new Vector();			
        views_vector = new Vector();
        addoperations = new Hashtable();	
        addOper = new Vector();
        remoperations = new Hashtable();			
    }
    String host = "localhost";
    String port ="9090";
    
    public void init(java.util.Properties prop)
    {
        host = prop.get("HOST").toString();
        port = prop.get("PORT").toString();	
        userName = prop.get("userName").toString();
        passWord = prop.get("password").toString();            
        if (prop.containsKey("CRYPTO_CLASS"))
        {
            cryptoClass = (String) prop.get("CRYPTO_CLASS");//No Internationalisation
        }
        //Update the CURRENTUSER variable of AuthMain ...
        AuthMain.CURRENTUSER = userName;


        if(port.equals("") || port == null)	
        {
            port = "1099";
        }	
        try {

            rmiaccessapi = (RMIAccessAPI) Naming. lookup ("//"+ host +":"+port+ "/RMIAccessAPI");
            authAdmin = (CustomViewScopeAPI) getRemoteAPI(userName,passWord,"NmsAuthAdminAPI");
            authEngine = (AuthorizationEngine) getRemoteAPI(userName,passWord,"NmsAuthEngineAPI");
            authAudit= (AuditAPI) getRemoteAPI(userName,passWord,"NmsAuditAPI");

            if ( (! authEngine.isAuthorized(userName,"Security Administration")) && AuthMain.rmistandalone )
            {
                System.out.println("User is not authorized to use the Security Admin Tool.");
                System.exit(1);
            }

        } catch ( Exception e ) {
            //e.printStackTrace();
            System.err.println("Error in getting the handle for the Remote APIs exiting with message : "+e.getMessage());
            if (AuthMain.rmistandalone)
            {
                System.exit(1);    
            }
        }
        try
        {
            operations = authAdmin.getAllOperations();	
        }		
        catch(Exception e)	
        {
            System.out.println(e.getMessage());	
            //e.printStackTrace();
        }
    }
    
    protected Remote getRemoteAPI(String user,String pass, String api) throws Exception 
    {
        try 
        {
            AuthenticationTicket ticket = new AuthTicket();
            String challenge = rmiaccessapi.getChallenge(user);
            String key = AuthUtil.getChallengeKey(user,pass,challenge);

            //set the encrypted password in AuthTicket if custom authentication
            //is used

            CryptoGraphAPI crypto = null;

            if (cryptoClass != null)
            {
                crypto = (CryptoGraphAPI) Class.forName(cryptoClass).newInstance();
                ticket.setPassword(crypto.enCrypt(pass));
            }
            ticket.setKey(key);
            return rmiaccessapi.getAPI(user,ticket,api);	
        } catch ( Exception e )
        {
            throw e;
        }
    }

    public void registerWithModel(com.adventnet.security.ui.SecurityCommonInterface scInter)
    {
        this.scInter = scInter;	 
    }

    
    public void fetchUserDetails()
    {	
    }

    // constructs a DefaultTreeModel from the operations data.
    public DefaultTreeModel constructOperationsTree( )
    {
        treeModel = new DefaultTreeModel(new DefaultMutableTreeNode("Operation Tree Root"));
        treeNode = (javax.swing.tree.DefaultMutableTreeNode)treeModel.getRoot();
        TreeObject treeob = new TreeObject(treeNode.getUserObject());	
        treeNode.setUserObject(treeob);	
        generateTree(treeNode);
        return treeModel;
    }



    public void generateTree(DefaultMutableTreeNode node)
    {
        for(int i=0;i<operations.size();i++)
        {
            OperationObject operOb  = (OperationObject)operations.elementAt(i);
            if(operOb.getParentName().equals(node.getUserObject().toString()))
            {
                DefaultMutableTreeNode child = new DefaultMutableTreeNode(operOb.getOperationName());
                TreeObject treeob = new TreeObject(child.getUserObject());		
                child.setUserObject(treeob);		
                addNode(node,child, operOb.isIsLeaf());
            }
        }
    }



    public void addNode(DefaultMutableTreeNode parent,DefaultMutableTreeNode child, boolean isLeaf )
    {
        parent.add(child);
        if(!isLeaf)
        {
            generateTree(child);
        }	
    }



    /* Begin Configuration methods */ 

    //BEGIN USER CONFIGURATION METHODS

    public  void setUserData(String uname,String pwd,Vector groups, Hashtable addoper,Hashtable modoper,String descName)
    {
    }

    /*    boolean result ;
          if ( groups == null || groups.size() == 0 )
          {
          groups = new Vector();
          groups.addElement("default "+uname+" Group");
          }
          try {
          if(pwd == null || pwd.equals(""))	
          {
    //authAdmin.createUser(uname, groups.elementAt(0).toString());
    result = userconfigapi.createNewAccount(uname,uname,groups.elementAt(0).toString());
    }
    else
    {
    //authAdmin.createUser(uname, groups.elementAt(0).toString(), pwd);
    result = userconfigapi.createNewAccount(uname,pwd,groups.elementAt(0).toString());
    }
    for(int i=1;i<groups.size();i++)
    {
    authAdmin.assignUserToGroup(uname, groups.elementAt(i).toString());	
    }	
    if(addoper != null && addoper.size()!=0){
    String viewName = "default "+uname+" View";
    authAdmin.assignUserToGroup(uname,"default "+uname+" Group");
    authAdmin.assignViewToGroup("default "+uname+" Group",viewName);
    for(Enumeration en=addoper.keys();en.hasMoreElements();){
    String type = en.nextElement().toString();
    if(addoper.get(type).toString().equals("0"))
    authAdmin.assignOperationToView(type,viewName,true);
    else
    authAdmin.assignOperationToView(type,viewName,false);

}
}
    }
catch(Exception ex)	
{
    scInter.showError(ex.getMessage());
}try{
    Thread.sleep(100);
}catch(Exception e){}

scInter.fireDataChanged();

}*/

public  void modifyUserData(String uname,String pwd, Vector groups, Hashtable addoper,Hashtable modoper,String descName)
{
    if(groups==null || groups.size()==0)
    {
        groups = new Vector();
        groups.addElement("default "+uname+" Group");
    }
    if ( !groups.contains("default "+uname+" Group") )
    {
        groups.addElement("default "+uname+" Group");
    }
    String message = "";
    boolean result = false;
    try {
        // remove the existing user from the all groups.
        authAdmin.removeUserFromGroup(uname,authAdmin.getAllGroupNames(uname));	
        // recreate with new properties.
        /* 
           if(pwd == null)	
           {
           result = authAdmin.createUser(uname, groups.elementAt(0).toString());
           }
           else
           {
           result = authAdmin.createUser(uname, groups.elementAt(0).toString(), pwd);
           }

         */
        result =  authAdmin.assignUserToGroup(uname, groups);
	authAdmin.setDescriptiveName(uname,descName);

    }
    catch(Exception ex)	
    {
        message = ex.getMessage();
    }

    if(result)	
    {
        scInter.fireDataChanged();
    }	
    else
    {
        scInter.showError(message);
    }		

}	



//Method Ready.....
public  void deleteUser(String uname)
{
}
/*	try 
    {
//authAdmin.removeUser(uname);	
userconfigapi.removeUser(uname);
String group ="default "+uname+" Group"; 
deleteGroup(group);
}
catch(Exception ex)	
{
scInter.showError(ex.getMessage().toString());
}	
scInter.fireDataChanged();
}*/



//Method Ready.....
public void changePassword(String uname, String newpass)
{
    String message = "";
    boolean result = false;	
    try 
    {
        result = authAdmin.changePassword(uname, newpass);	
    }
    catch(Exception ex)	
    {
        message = ex.getMessage();
    }	

    if(result)	
    {try{
            Thread.sleep(100);
        }catch(Exception e){}

    scInter.fireDataChanged();
    }	
    else
    {
        scInter.showError(message);
    }	

}	

//END USER CONFIGURATION METHODS

//BEGIN GROUP CONFIGURATION METHODS
public  void setGroupData(String gname, Vector views)
{
    String message = "";
    boolean result = false;		
    try
    {
        int size = views.size();
        for(int i=0;i<size;i++)
        {
            String viewName = (String)views.elementAt(i);
            authAdmin.assignViewToGroup(gname,viewName);
        }
    }
    catch(Exception ex)
    {
        message = ex.getMessage();			 
        scInter.showError(message);	
    }

    scInter.fireDataChanged();

}


public  void modifyGroupData(String groupName,Vector groupViewVec)
{
    try
    {

        Vector viewsVec = authAdmin.getAllViewNames(groupName);
        int viewsVecSize = viewsVec.size();
        for(int i=0;i<viewsVecSize;i++)
        {
            authAdmin.removeViewFromGroup(groupName,(String)viewsVec.elementAt(i));
        }
        int size = groupViewVec.size();
        for(int i=0;i<size;i++)
        {
            String viewName = (String)groupViewVec.elementAt(i);
            authAdmin.assignViewToGroup(groupName,viewName);
        }

    }
    catch(Exception ae)
    {
        scInter.showError(ae.getMessage());			
    }		
    scInter.fireDataChanged();	

}



//Method Ready.....

public  void deleteGroup(String gname)
{
    try
    {
        Vector viewNames = authAdmin.getAllViewNames(gname);
        for(int i=0;i<viewNames.size();i++){
            //String viewName=viewNames.elementAt(i).toString();
            authAdmin.removeViewFromGroup(gname,(String)viewNames.elementAt(i));
        }

        String viewName = "default "+gname+" View";
        if(viewNames.contains(viewName))
        {
            authAdmin.removeOperationFromView(null,viewName);
        }
        // Changes for removing from UserGroupTable.

        Vector users = authAdmin.getUsers(gname);	
        for ( int j=0; j<users.size(); j++ ) {
            String userName = (String) users.elementAt(j);
            authAdmin.removeUserFromGroup(userName, gname);
        }

        Vector allUsers = getAllUserNames();
        String userName = "";
        int usersSize = users.size();
        for ( int j=0; j<usersSize; j++ ) {
            userName = (String) users.elementAt(j);
            if(allUsers.contains(userName))
            {
                continue;
            }
            else 
            {
                authAdmin.assignUserToGroup(userName, "default "+userName+" Group");
            }
        }	
    }
    catch(Exception ae)
    {
        scInter.showError(ae.getMessage());
    }		
    scInter.fireDataChanged();	
}

//END GROUP CONFIGURATION METHODS

//BEGIN VIEW CONFIGURATION METHODS
public void addViewOp(String vname,java.util.Properties prop,Hashtable hash)
{
    try
    {
        AuthorizedViewObject viewOb = new AuthorizedViewObject();
        viewOb.setAuthorizedViewName(vname);
        viewOb.setViewProperties(prop);
        authAdmin.createAuthorizedView(viewOb);
        for(Enumeration e = hash.keys();e.hasMoreElements();)
        {
            Object operName = e.nextElement();
            if(hash.get(operName).equals("1"))
            {
                authAdmin.assignOperationToView(operName.toString(), vname,  false); 
            }	
            else
            {
                authAdmin.assignOperationToView(operName.toString(), vname, true); 		
            }	
        }	
    }
    catch(Exception ex)	
    {
        scInter.showError(ex.getMessage());			
    }		
    scInter.fireDataChanged();
}

public void  modViewOp(String vname,java.util.Properties prop,Hashtable hash)
{
    AuthorizedViewObject  authviewob = new AuthorizedViewObject();
    try{

        Vector vec = authEngine.getAuthorizedViewObjectsForView(vname);
        if(vec.size() == 1)
        {
            authAdmin.removeAuthorizedView((AuthorizedViewObject)vec.elementAt(0));
        }	


        authAdmin.removeOperationFromView(null,vname);
        if(prop != null)	
        {
            authviewob.setAuthorizedViewName(vname);
            authviewob.setViewProperties(prop);
            authAdmin.createAuthorizedView(authviewob);
        }

        if(hash.size() > 0)
        {
            for(Enumeration e = hash.keys();e.hasMoreElements();)
            {
                Object operName = e.nextElement();
                if(hash.get(operName).equals("1"))
                {
                    authAdmin.assignOperationToView(operName.toString(), vname,  false); 
                }	
                else
                {
                    authAdmin.assignOperationToView(operName.toString(), vname, true); 		
                }	
            }	
        }	


    }
    catch(Exception ex)
    {
        scInter.showError(ex.getMessage());			
    }		
    scInter.fireDataChanged();	

}

public void delViewOp(String vname,java.util.Properties prop,Hashtable hash)
{
    try
    {
        AuthorizedViewObject  viewob = new AuthorizedViewObject();
        if(prop != null)
        {
            viewob.setAuthorizedViewName(vname);
            viewob.setViewProperties(prop);
            //authAdmin.removeAuthorizedView(viewob);  
        }

        if(hash.size() != 0)
        {
            authAdmin.removeOperationFromView(null,vname);
        }	
    }
    catch(Exception ex)	
    {
        scInter.showError(ex.getMessage());			
    }		
    scInter.fireDataChanged();	
}

public void modViewProp(String groupName,boolean forDelete, Vector newViewsVec, String cvscope)
{
    if(forDelete)
    {
        try
        {
            String authViewName = (String)newViewsVec.elementAt(0);
            authAdmin.removeAuthorizedView(authViewName);
        }
        catch(Exception ex)	
        {
            scInter.showError(ex.getMessage());			
        }	
    }
    else
    {
        Vector oldViewsVec = getAllAuthScopes(cvscope);
        int oldSize = oldViewsVec.size();
        int newSize = newViewsVec.size();
        try
        {
            Vector viewsForTheGroup = authAdmin.getAllViewNames(groupName);
            for(int i=0; i<oldSize; i++)
            {
                String theViewName = (String)oldViewsVec.elementAt(i);
                if(viewsForTheGroup.contains(theViewName))
                {
                    authAdmin.removeViewFromGroup(groupName,theViewName);
                }	
            }
            for(int i=0; i<newSize; i++)
            {
                authAdmin.assignViewToGroup(groupName,(String)newViewsVec.elementAt(i));
            }
        }
        catch(Exception ex)	
        {
            scInter.showError(ex.getMessage());			
        }
    }
    scInter.fireDataChanged();		
}				



//END VIEW CONFIGURATION METHODS

//BEGIN OPERATIONS CONFIGURATION METHODS

public void setOperations()
{
    String message = "";
    boolean result = false;	
    //System.out.println("THE OPERART "+addOper+"          "+addoperations);
    if(((addoperations.size() == 0) || (addOper.size() == 0)) && (remoperations.size() == 0))
    {
        return;
    }	
    try
    {
        if(remoperations.size() > 0)
        {
            for(Enumeration e = remoperations.keys();e.hasMoreElements();)
            {
                authAdmin.removeOperation(e.nextElement().toString());
            }
        }

        /*if(addoperations.size() > 0)
          {
          for(Enumeration e1 = addoperations.keys();e1.hasMoreElements();)
          {
          String obj = (String)e1.nextElement();
          if((addoperations.get(obj).toString()).equals("Operation Tree Root")){
          authAdmin.addOperation(obj, null);				
          }else{
          authAdmin.addOperation(obj,addoperations.get(obj).toString());
          }
          }	
          }*/	

        int size = addOper.size();
        if(size > 0)
        {
            for(int i=0; i<size; i++)
            {
                String oper = (String)addOper.elementAt(i);
                if((addoperations.get(oper).toString()).equals("Operation Tree Root"))
                {
                    authAdmin.addOperation(oper, null);				
                }
                else
                {
                    authAdmin.addOperation(oper,addoperations.get(oper).toString());
                }	
            }
        }
    }
    catch(Exception e)
    {
        message  = e.getMessage();
        scInter.showError(message);
    }
    try
    {
        operations = authAdmin.getAllOperations();	
    }		
    catch(Exception e)	
    {
        scInter.showError(e.getMessage());	
    }	

    scInter.fireDataChanged();
    addoperations.clear();
    addOper.clear();
    remoperations.clear();
}


public void  addOperation(String oper, String parent, String leaf)
{
    addoperations.put(parent,oper);	
    if(!addOper.contains(parent))
    {
        addOper.addElement(parent);
    }
}

public void removeOperation(String oper, String parent, String leaf)
{
    if((addOper != null) && (addOper.contains(parent)))
    {
        addOper.remove(parent);		
        addoperations.remove(parent);
        return;
    }		
    remoperations.put(parent,oper);
}



//END  OPERATIONS CONFIGURATION METHODS

/* End Configuration Methods */ 


/*-------------- Begin Accessor Methods-------------------- */ 

public Vector getAllUserNames()
{
    Vector uservec = new Vector();		

    Hashtable users = new Hashtable();
    try
    {
        Vector groups = authAdmin.getAllGroups();		
        for(int i=0;i<groups.size();i++)   
        {
            Vector groupusers = authAdmin.getUsers(groups.elementAt(i).toString());
            for(int j=0;j<groupusers.size();j++)
            {
                users.put(groupusers.elementAt(j).toString(),"");
            }
        }	
        for(Enumeration e = users.keys();e.hasMoreElements();)
        {
            uservec.add(e.nextElement());	
        }
    }
    catch(Exception e)	
    {

    }	

    return uservec;
}



//Method Ready.....

public Vector getAllUsers()
{
    users_vector = new Vector();	
    try
    {

        Vector us = getAllUserNames();
        for(int i=0;i<us.size();i++)
        {
            users_vector.add(us.elementAt(i)); 
            Vector temp = authAdmin.getAllGroupNames(us.elementAt(i).toString());
            for(int j=0;j<temp.size();j++){
                String tempString = temp.elementAt(j).toString();
                if(tempString.startsWith("default ") && tempString.endsWith(" Group"))
                    temp.removeElement(tempString);
            }
            users_vector.addElement(temp); 
        }
    }
    catch(Exception ex)	{
    }
    return users_vector;

}



//Method Ready.....

public Vector getGroupsForUser(String uname)
{
    Vector gforuser = null;	
    Vector toReturn = new Vector();
    try{
        gforuser = authAdmin.getAllGroupNames(uname);	
        for(int i=0;i<gforuser.size();i++){
            String group = gforuser.elementAt(i).toString();
            if(!(group.startsWith("default ") && group.endsWith(" Group")))
                toReturn.addElement(group);
        }
    }catch(Exception ex){}		
    return toReturn;        
}

public Hashtable getOperationsForUser(String uname)
{
    try
    {

    }
    catch(Exception e)
    {

    }		

    return new Hashtable();
}



//Method Ready.....

public Vector getAllGroupNames()
{
    Vector groups =null;	
    Vector toReturn = new Vector();
    try
    {
        groups = authAdmin.getAllGroups();	
        for(int i=0;i<groups.size();i++){
            String group = groups.elementAt(i).toString();
            if(!(group.startsWith("default ") && group.endsWith(" Group")))
                toReturn.addElement(group);
        }
    }
    catch(Exception ex)	
    {
    }	

    return toReturn;
}



public Vector getAllGroups()
{
    groups_vector = new Vector();		
    try
    {
        Vector groups = authAdmin.getAllGroups();
        for(int i=0;i<groups.size();i++)
        {
            String gp = groups.elementAt(i).toString();
            groups_vector.add(gp);
            groups_vector.add(authAdmin.getAllViewNames(gp));

        }
    }
    catch(Exception ex)		
    {

    }
    return groups_vector;
}

public Vector getViewsForGroup(String gname)
{
    Vector views = null;
    try
    {
        views = authAdmin.getAllViewNames(gname);	
    }
    catch(Exception ex)
    {
    }	
    return views;
}

public Vector getUserGroupsWithDefault(String uname)
{

    return null;
}

public Hashtable getViewOperations(String viewname)
{
    Hashtable opers = null;
    try
    {
        opers = authAdmin.getOperationsForViewInHash(viewname);
    }
    catch(Exception ex)
    {
    }	
    return opers;
}

public Vector getAllViews()
{

    Vector views = new Vector();
    Hashtable allviews = new Hashtable();	
    try
    {
        Vector gp = getAllGroups();
        for(int i=0;i<gp.size();i++)
        {
            Vector vi = authAdmin.getAllViewNames(gp.elementAt(i).toString());
            for(int j=0;j<vi.size();j++)
            {
                allviews.put(vi.elementAt(j),"");
            }

        }
        for(Enumeration e = allviews.keys();e.hasMoreElements();)	
        {
            String str = e.nextElement().toString();
            AuthViewWithOperations viewOper = new  AuthViewWithOperations();	
            viewOper.setAuthorizedViewName(str);
            Vector viewob = authEngine.getAuthorizedViewObjectsForView(str);
            if(viewob.size() == 1)
            {	
                viewOper.setViewProperties(((AuthorizedViewObject)viewob.elementAt(0)).getViewProperties());	
            }
            Hashtable oper = authAdmin.getOperationsForViewInHash(str);
            if(oper.size() > 0)
            {
                viewOper.setOperations(oper);	
            }	
            views.add(viewOper);					
        }


    }
    catch(Exception e)
    {
    }	
    return  views;
}

public DefaultTreeModel getTreeModel()
{		
    return constructOperationsTree();    
}



//New Method.....
public Vector getAudit(String userName)
{
    Vector toReturn = new Vector(); 
    try{
        toReturn = authAudit.getAuditTrails(userName,null);
    }catch(Exception e){
        e.printStackTrace();
    }
    return toReturn;
}		


//New Method.....
public void clearAudit(String userName)
{
    try{
        authAudit.clearAudit(userName,null);
    }catch(Exception e){
        scInter.showError(e.getMessage());
    }
    scInter.fireDataChanged();
}

//Method Ready.....

public Hashtable getUserAttributes(String username)
{
    Hashtable attributes = null;
    try
    {
        attributes = authAdmin.getAllAttributes(username);
        attributes.put("passwdexpirytime",attributes.get("passwordexpirytime"));
    }	
    catch(Exception e)
    {
        e.printStackTrace();
    }
    if(attributes == null)
    {
        return new Hashtable();
    }
    return attributes;
}	

public Hashtable getAllUserAttributes()
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


public void setUserAttributes(String username, String status, Integer userage, Integer passwordage,String descName )
{

    try
    {
        authAdmin.setUserExpirationTime(username, userage.intValue());
        authAdmin.setPasswordAge(username, passwordage.intValue());
	authAdmin.setDescriptiveName(username,descName);
        //authAdmin.setUserStatus(username,status);
    }
    catch(Exception ex)
    {
        scInter.showError(ex.getMessage());			
    }		
    scInter.fireDataChanged();		
}	
public void setUserStatus(String username, String status)
{
    try
    {
        authAdmin.setUserStatus(username,status);
    }
    catch(Exception ex)
    {
        scInter.showError(ex.getMessage());			
    }		
    scInter.fireDataChanged();		
}	




//Method Ready.....
public void addGroupOperData(String groupName,Hashtable oper,Hashtable operHash)
{
    String message = "";
    String viewName = "default "+groupName+" View";
    try{
        if(oper == null)
        {
            Vector views = authAdmin.getAllViewNames(groupName);

            for ( int i=0; i<views.size(); i++ ) {
                String tempViewName = (String) views.elementAt(i);
                authAdmin.removeViewFromGroup(groupName, tempViewName);
            }

            authAdmin.assignViewToGroup(groupName, viewName);
            if ( operHash != null ) {
                authAdmin.removeOperationFromView(null, viewName);
                for ( Enumeration e = operHash.keys(); e.hasMoreElements(); ) {
                    String name = (String) e.nextElement();
                    String type = (String) operHash.get(name);

                    if ( type.equals("1") )
                        authAdmin.assignOperationToView(name,viewName,false);
                    else
                        authAdmin.assignOperationToView(name,viewName,true);
                }
            }
            else if (operHash == null) {
                authAdmin.removeOperationFromView(null, viewName);
            }

        }
        else
        {
            operHash = oper;
            Vector allGroups = authAdmin.getAllGroups();

            for ( Enumeration e = allGroups.elements(); e.hasMoreElements(); ) 
            {                
                String gn = (String) e.nextElement();
                //if ( gn.equalsIgnoreCase(groupName) ) {
                if ( gn.equals(groupName) ) {
                    message="Error while adding Group.Group already present.";
                    scInter.showError(message);
                    return;
                }
            }
            authAdmin.assignViewToGroup(groupName, viewName);

            if ( operHash != null ) {
                authAdmin.removeOperationFromView(null, viewName);
                for ( Enumeration e = operHash.keys(); e.hasMoreElements(); ) {
                    String name = (String) e.nextElement();
                    String type = (String) operHash.get(name);
                    if ( type.equals("1") )
                        authAdmin.assignOperationToView(name,viewName,false);
                    else
                        authAdmin.assignOperationToView(name,viewName,true);
                }
            }
            else if (operHash == null) {
                authAdmin.removeOperationFromView(null, viewName);
            }
            }
        }catch(Exception e){
            message = e.getMessage();
            scInter.showError(message);
        }
        finally{
            scInter.fireDataChanged();
        }

    }



    //Method Ready.....
    public Vector getUsersForGroup(String group)
    {
        Vector toReturn = new Vector();
        try{
            toReturn = authAdmin.getUsers(group);
        }catch(Exception e){}

        return toReturn;
    }


    //Method Ready.....
    public void modifyUserGroupData(String group, Vector users)
    {
        Vector interSect = new Vector();
        try{
            Vector oldUsers = authAdmin.getUsers(group);
            int size=0;

            size = oldUsers.size();
            for(int i=0;i<size;i++)
            {
                authAdmin.removeUserFromGroup((String)oldUsers.elementAt(i),group);
            }
            if(users != null && users.size() != 0)
            {
                int usersSize = users.size();
                for(int j=0;j<usersSize;j++)
                {
                    authAdmin.assignUserToGroup((String)users.elementAt(j),group);
                }
            }
            for ( int i=0; i<size; i++)
            {
                if(users.contains(oldUsers.elementAt(i)))
                {
                    continue;
                }
                else 
                {
                    interSect.addElement(oldUsers.elementAt(i));
                }
            }

            Vector allUsers = getAllUserNames();
            String userName = "";

            int interSize = interSect.size();
            for ( int i=0; i<interSize; i++)
            {
                userName = (String)interSect.elementAt(i);
                if(allUsers.contains(userName))
                    continue;
                else 
                {
                    authAdmin.assignUserToGroup(userName, "default "+userName+" Group");
                }
            }

        }catch(Exception e){
            scInter.showError(e.getMessage());
        }
        finally{
            scInter.fireDataChanged();
        }
    }


    //Method Ready.....

    public Vector getAllGroupsForUser(java.lang.String user)	
    {
        Vector usersVec = getGroupsForUser(user); 
        /*
           Vector toReturn = new Vector();
           int index = usersVec.indexOf(user);
           toReturn = (Vector)usersVec.elementAt(index+1);
         */
        return usersVec;

    }


    //Method Ready.....

    public Hashtable getOperations(java.lang.String name)
    {
        Hashtable toReturnHash = new Hashtable();
        try{
            Vector groups = authAdmin.getAllGroupNames(name);
            toReturnHash = getOperationsForGroup(groups);
        }catch(Exception e){}
        return toReturnHash;
    }



    //Method Ready.....

    public Hashtable getOperationsForGroup(java.util.Vector groups)
    {
        Hashtable allOper = new Hashtable();
        try{
            Vector viewVec = new Vector();
            Hashtable oper = null;
            for(int i=0;i<groups.size();i++){
                String groupName = groups.elementAt(i).toString();
                Vector tempVec = authAdmin.getAllViewNames(groupName);
                for(int j=0;j<tempVec.size();j++){
                    String viewName = tempVec.elementAt(j).toString();
                    if(!viewVec.contains(viewName))
                        viewVec.addElement(viewName);
                }
            }
            int size = viewVec.size();
            for(int k=0;k<size;k++){
                oper = new java.util.Hashtable();
                oper = authAdmin.getOperationsForViewInHash(viewVec.elementAt(k).toString());
                if(size !=0 && oper.size()!=0)
                    for(Enumeration e= oper.keys();e.hasMoreElements();){
                        String name = e.nextElement().toString();
                        if(allOper.containsKey(name) && (allOper.get(name).toString()).equals("1"))
                        {	
                            //	break;
                        }
                        else if(allOper.containsKey(name) && (allOper.get(name).toString()).equals("0"))
                        {
                            allOper.put(name,oper.get(name));
                        }
                        else if(!allOper.containsKey(name))
                        {
                            allOper.put(name,oper.get(name));
                        }
                    }
            }
        }catch(Exception e){}

        return allOper;

    }



    //Method Ready.....

    public Vector getGroups(){
        Vector toReturn = new Vector();
        try{
            Vector groupVec = authAdmin.getAllGroups();
            for(int i=0;i<groupVec.size();i++){
                String groupName = groupVec.elementAt(i).toString();
                if(!(groupName.startsWith("default ") && groupName.endsWith(" Group")))
                {
                    toReturn.addElement(groupName);
                    toReturn.addElement(authAdmin.getUsers(groupName));
                }
            }
            /*for(int j=0;j<toReturn.size();j++){
              String tempString = toReturn.elementAt(j).toString();
              if(tempString.startsWith("default ") && tempString.endsWith(" Group"))	
              {			
            //toReturn.removeElement(tempString);
            //toReturn.removeElementAt(j);
            toReturn.remove(j);
            toReturn.remove(j);
            j-=2;
            }
            }*/
        }catch(Exception e){}

        return toReturn;
    }



    //Method Ready.....
    public void addViewProp(java.lang.String groupName, java.lang.String operationName, java.util.Properties viewProp)
    {
        String message = "";
        try{
            String viewName = groupName+" "+operationName+" Scope";
            if ( viewProp == null || viewProp.size() == 0 ) 
            {
                /* remove all properties */
                AuthorizedViewObject avo = new AuthorizedViewObject();
                avo.setAuthorizedViewName(viewName);
                avo.setViewProperties(null);
                //so that it will deletes all the properties associated with this view.
                authAdmin.removeAuthorizedView(avo);

                authAdmin.removeOperationFromView(operationName,groupName+" "+operationName+" Scope");
                authAdmin.assignOperationToView(operationName,"default "+groupName+" View");
                authAdmin.removeViewFromGroup(groupName,viewName);
                return;	
            }
            authAdmin.removeOperationFromView(operationName,viewName);
            authAdmin.removeOperationFromView(operationName,"default "+groupName+" View");
            authAdmin.assignOperationToView(operationName,viewName,false);
            authAdmin.removeViewFromGroup(groupName,viewName);
            authAdmin.assignViewToGroup(groupName,viewName);
            AuthorizedViewObject avo = new AuthorizedViewObject();
            avo.setAuthorizedViewName(viewName);
            avo.setViewProperties(null);
            //so that it will deletes all the properties associated with this view.
            authAdmin.removeAuthorizedView(avo);

            if ( viewProp != null || viewProp.size() != 0) {
                AuthorizedViewObject avo1 = new AuthorizedViewObject();
                avo1.setAuthorizedViewName(viewName);
                avo1.setViewProperties(viewProp);
                authAdmin.createAuthorizedView(avo1);
            }
        }catch(Exception e){
            message = e.getMessage();
            scInter.showError(message);
        }
        finally{
            scInter.fireDataChanged();
        }
    }


    //Method Ready.....
    public	java.util.Properties getViewProperties(java.lang.String anme)
    {
        Properties toReturn = new Properties();
        try{
            Vector authViewObjVec = authEngine.getAuthorizedViewObjectsForView(anme);
            for(int i=0;i<authViewObjVec.size();i++){
                AuthorizedViewObject authViewObj = (AuthorizedViewObject)authViewObjVec.elementAt(i);
                Properties tempProp = (Properties)authViewObj.getViewProperties();
                for(Enumeration en = tempProp.keys();en.hasMoreElements();)
                {
                    Object xyz = en.nextElement();
                    toReturn.put(xyz,tempProp.get(xyz));
                }
            }
        }catch(Exception e){}
        return toReturn;
    }



    //Method Ready.....
    public void modifyOper_new(String userName,Hashtable oper)
    {
        String message = "";
        try{
            String viewName = "default "+userName+" View";
            String groupName = "default "+userName+" Group";

            if ( userName != null && groupName != null ) {
                authAdmin.removeUserFromGroup( userName, groupName );
                authAdmin.assignUserToGroup( userName, groupName );
            }

            if ( viewName != null && groupName != null ) {
                authAdmin.removeViewFromGroup( groupName, viewName );
                authAdmin.assignViewToGroup( groupName, viewName );
            }

            if ( viewName != null && oper != null ) {
                authAdmin.removeOperationFromView(null,viewName);

                for ( Enumeration e = oper.keys(); e.hasMoreElements(); ) {
                    String operName = (String) e.nextElement();
                    String operType = (String) oper.get(operName);

                    if ( operName.equals("") && operType.equals("") )

                        break;

                    //authAdmin.removeOperationFromView(operName,viewName);

                    if ( operType.equals("1") )
                        authAdmin.assignOperationToView(operName,viewName,false);
                    else
                        authAdmin.assignOperationToView(operName,viewName,true);                
                }
            }
        }catch(Exception e){
            message = e.getMessage();
            scInter.showError(message);
        }
        finally{
            scInter.fireDataChanged();
        }
    }
    /* End Accessor Methods */ 

    public Hashtable getAuthScope(String groupName)
    {	
        Hashtable toReturn = new Hashtable();
        try 
        {
            Vector customViews = authAdmin.getAllCustomViewScope();
            Vector groupViews = authAdmin.getAllViewNames(groupName);

            Vector resultViews = null;
            String cView = "";
            Vector cvsViews = null;

            for ( int i=0; i<customViews.size(); i++ ) 
            {		
                cView = (String) customViews.elementAt(i);
                cvsViews = authAdmin.getViewsForCVScope ( cView );
                if ( cvsViews.contains ("NULL") )
                {	
                    cvsViews.remove("NULL");
                    //toReturn.put (cView,  new Vector() );	
                    //continue;
                }
                resultViews = getIntersectionViews(cvsViews, groupViews);
                if(resultViews == null)
                {
                    resultViews = new Vector();
                }
                toReturn.put (cView, resultViews );
            }

        }
        catch(Exception ex)	
        {
            scInter.showError(ex.getMessage());
        }
        return toReturn;
    }

    private Vector getIntersectionViews( Vector v1, Vector v2)
    {
        Vector intersect = new Vector();
        if( v1 == null || v2 == null)
        {
            return intersect;
        }	
        int size = v2.size();
        for ( int i=0; i < size; i++)
        {
            if(v1.contains(	v2.elementAt(i)))
            {
                intersect.addElement(v2.elementAt(i));	
            }	
        }	
        return intersect;
    }		


    public Vector getAllAuthScopes()
    {
        Vector toReturn = new Vector();
        try
        {
            toReturn = authAdmin.getAllAuthorizedViews();
        }
        catch(Exception ex)	
        {
            scInter.showError(ex.getMessage());
        }
        return toReturn;
    }	

    public Vector getAllAuthScopes(String customView)
    {
        Vector toReturn = new Vector();
        try
        {
            toReturn = authAdmin.getViewsForCVScope(customView);
        }
        catch(Exception ex)	
        {
            scInter.showError(ex.getMessage());
        }
        toReturn.remove("NULL");
        return toReturn;
    }

    public void addAuthViewProp(String gpname,Properties modoper,String groupName)
    {
        try
        {
            AuthorizedViewObject avo = new AuthorizedViewObject();
            avo.setAuthorizedViewName ( gpname );
            avo.setViewProperties ( null );
            authAdmin.removeAuthorizedView ( avo );

            AuthorizedViewObject avo1 = new AuthorizedViewObject();
            avo1.setAuthorizedViewName ( gpname );
            avo1.setViewProperties ( modoper );
            authAdmin.createAuthorizedView ( avo1 );
        }
        catch(Exception ex)	
        {
            scInter.showError(ex.getMessage());
        }

        scInter.fireDataChanged();

    }

    public void assignAuthScope(String scope,Vector authScopes,String groupName)
    {

        Vector oldAuthViews = null;
        if ( groupName != null && groupName.equals("") )
        {
            scInter.fireDataChanged();
            return ;
        }
        if ( scope == null )
        {
            return ;
        }
        if ( authScopes == null ) 
        {
            authScopes = new Vector();
            authScopes.addElement("NULL");
        }
        try 
        {
            oldAuthViews = authAdmin.getViewsForCVScope(scope);
            //authAdmin.removeCVScope ( scope );

            if(oldAuthViews == null)
            {
                oldAuthViews = new Vector();
            }

            if ( authScopes.size() == 0 )
            {
                authScopes.addElement("NULL");
            }

            String authView = "";	
            Vector groupViews = authAdmin.getAllViewNames(groupName);

            if(groupViews == null)
            {
                groupViews = new Vector();
            }
            for ( int i=0; i<authScopes.size(); i++ ) 
            {
                authView = (String) authScopes.elementAt(i);
                if(! (groupViews.contains(authView)) )
                {
                    authAdmin.assignViewToGroup(groupName,authView);
                }	
            }

            int size = oldAuthViews.size();
            for(int i=0; i<size; i++)
            {
                String view = (String)oldAuthViews.elementAt(i);
                if(authScopes.contains(view))
                {
                    authScopes.remove(view);
                }
            }
            authAdmin.assignViewToCVScope(scope, authScopes);
        }
        catch(Exception ex)	
        {
            scInter.showError(ex.getMessage());
        }
        scInter.fireDataChanged();

    }
    //Method added for saving the audit details.
    public void saveAuditDetails(String fileName,String auditDetails)
    {

    }
    //Method added for showing help.
    public void showHelp(String urlKey)
    {
        com.adventnet.nms.util.BrowserControl.displayURL(urlKey,"sameWindow"); 
    }

    public Properties getPropertyNamesForCustomViewScope()
    {
        try
        {
            return authAdmin.getPropertyNamesForCustomViewScope();
        }
        catch (Exception e)
        {
            System.err.println("Exception while getting propNames for CVS: "+e.getMessage());  
        }
        return null;
    }

    public void cleanUp()
    {

    }	

    //method does nothing, so just return true
    public boolean isInitialized()
    {
        return true;
    }
}
















