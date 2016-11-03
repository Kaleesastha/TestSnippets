//$Id: AbstractSecurityModel.java,v 1.4 2008/08/26 14:33:41 aravinds Exp $

package com.adventnet.security.ui;


import javax.swing.tree.*;
import java.util.Vector;
import java.util.Hashtable;
import java.util.Properties;

/**
  The Abstract base class for all the SecurityModels  . Any class which will be used as the model for the Security GUI 
  Will need to extend AbstractSecurityModel
 */


public abstract class AbstractSecurityModel 
{

    /* users_vector should follow the following format. 1st element string username , next element 
       a vector of all the groups associated with that user, and so on*/
    protected Vector users_vector = null;

    /*groups_vector should be  similar to users_vector. with 1st element being string groupname and 
      next element a vector of associated viewnames for this group, and so on*/
    protected  Vector groups_vector = null;

    /*views_vector is a vector of AuthorizedViewvoids which contain the view name, a Properties 
      void containing the view properties. and a hashtable of operations associated with the view.*/
    protected  Vector views_vector = null;

    /*The operationstree is parsed by the model and a TreeModel is constructed which will be used by the 
      GUI to populate the JTrees which depict the operations.*/
    protected  DefaultTreeModel treeModel = null;


    /*  An interface implemented by the independent GUI screens such as User group screen etc. 
        This is used to fire data changes into these screens when they are invoked for the first time 
        or when the updated data is recieved after making a configuration.*/
    protected  com.adventnet.security.ui.SecurityCommonInterface scInter = null;

    //Initialization method
    public abstract void init(java.util.Properties prop);

    /*the instance of the presently viewed screen(which implements the SecurityCommonInterface) is registered with the model to update the data or give information*/

    public void registerWithModel(com.adventnet.security.ui.SecurityCommonInterface scInter)
    {
        this.scInter = scInter;	 
    }


    // constructs a DefaultTreeModel 
    public DefaultTreeModel constructOperationsTree( )
    {
        return treeModel;
    }

    /* Begin Configuration methods */ 

    //BEGIN USER CONFIGURATION METHODS

    public abstract void setUserData(String uname,String pwd,Vector groups, Hashtable addoper,Hashtable modoper,String descName);	

    public abstract void modifyUserData(String uname,String pwd, Vector groups, Hashtable addoper,Hashtable modoper,String descName);	
    public abstract void deleteUser(String uname);
    public abstract void changePassword(String uname, String newpass);	

    //END USER CONFIGURATION METHODS

    //BEGIN GROUP CONFIGURATION METHODS
    public abstract void setGroupData(String gname, Vector views);

    public abstract void modifyGroupData(String gname,Vector views);

    public abstract void deleteGroup(String gname);

    //END GROUP CONFIGURATION METHODS

    //BEGIN VIEW CONFIGURATION METHODS
    public abstract void addViewOp(String vname,java.util.Properties prop,Hashtable hash);

    public abstract void  modViewOp(String vname,java.util.Properties prop,Hashtable hash);

    public abstract void delViewOp(String vname,java.util.Properties prop,Hashtable hash);
    public abstract void addViewProp(String gpname,String opname,Properties modoper);		
    public abstract void modViewProp(String groupName, boolean forDelete, Vector newViewsVec, String cvscope);

    //END VIEW CONFIGURATION METHODS


    //BEGIN OPERATIONS CONFIGURATION METHODS

    public abstract void setOperations();

    public abstract void  addOperation(String oper, String parent, String leaf);

    public abstract void removeOperation(String oper, String parent, String leaf);
    public abstract void modifyOper_new(String vname,Hashtable hash);


    //END  OPERATIONS CONFIGURATION METHODS

    /* End Configuration Methods */ 


    /*-------------- Begin Accessor Methods-------------------- */ 

    public abstract Vector getAllUserNames(); //priviously getUsersWithoutGroups()

    public abstract Vector getAllUsers();

    public abstract Vector getGroupsForUser(String uname);

    public abstract Hashtable getOperationsForUser(String uname);

    public abstract Vector getAllGroupNames(); //previously getGroupsWithoutViews()

    public abstract Vector getAllGroups();

    public abstract Vector getViewsForGroup(String gname);

    public abstract Vector getUserGroupsWithDefault(String uname);

    public abstract Hashtable getViewOperations(String viewname);

    public abstract Vector getAllViews();

    public abstract DefaultTreeModel getTreeModel();

    public abstract Vector getUsersForGroup(String name);		

    public abstract Vector getGroups();		

    public abstract Hashtable getOperations(String userName);	

    public abstract Hashtable getOperationsForGroup(Vector groups);		
    public abstract Properties getViewProperties(String viewName);

    public abstract Vector getAudit(String userName);
    public abstract void clearAudit(String userName);
    /* End Accessor Methods */ 

    /* Other methods */

    public abstract Vector getAllGroupsForUser(String userName);		
    public abstract Hashtable getUserAttributes(String username);

    public abstract void setUserAttributes(String username, String status, Integer userage, Integer passwordage,String descName );
    public abstract void setUserStatus(String username, String status);
    public abstract void modifyUserGroupData(String group,Vector users);	
    public abstract void addGroupOperData(String group,Hashtable addoper,Hashtable modoper);	
    public abstract void cleanUp();

    public abstract Hashtable getAuthScope(String groupName);
    public abstract Vector getAllAuthScopes();	
    public abstract Vector getAllAuthScopes(String customView);	
    public abstract void assignAuthScope(String name,Vector names,String groupName);

    public abstract void addAuthViewProp(String gpname,Properties modoper,String groupName);

    //Method added for saving the audit details.
    public abstract void saveAuditDetails(String fileName,String auditDetails);
    //Method added for showing help.
    public abstract void showHelp(String urlKey);

    // Method for getting the property names for CustomViewScope 
    public abstract Properties getPropertyNamesForCustomViewScope();

    public abstract Hashtable getAllUserAttributes();

    public abstract boolean isInitialized();
    public void checkAuthorizationForButtons(){}
    public abstract void fetchUserDetails();

    //Method to refetch data
    public void refetchData()
    {
	return;
    }
}




