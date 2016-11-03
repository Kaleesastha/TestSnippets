/*$Id: NmsSecurityModelRMIImpl.java,v 1.1 2006/08/29 13:57:02 build Exp $*/

package com.adventnet.nms.security.ui;

import java.util.Vector;
import java.util.Hashtable;
import java.util.Enumeration;

import com.adventnet.security.ui.SecurityModelRMIImpl;
import com.adventnet.nms.authentication.UserConfigAPI;
import com.adventnet.security.authorization.AuthorizedViewObject;

public class NmsSecurityModelRMIImpl extends SecurityModelRMIImpl
{
    //Reference of the remote Object UserConfigAPI which is used for 
    //creating and deleting directories for user.
    UserConfigAPI userconfigapi = null;

    public void init(java.util.Properties prop)
    {
        super.init(prop);
        String user = (String) prop.get("userName");
        String pass = (String) prop.get("password");
        try 
        {
            userconfigapi = (UserConfigAPI) getRemoteAPI(user,pass,"UserConfigAPI");
        } 
        catch ( Exception e )
        {
            System.out.println ( "Error in getting the handle for UserConfigAPI");
        }
    }

    public void deleteUser(String uname)
    {
        try 
        {
            userconfigapi.removeUser(uname);
            String group ="default "+uname+" Group"; 
            deleteUserGroup(group);
        }
        catch(Exception ex)	
        {
            scInter.showError(ex.getMessage());
        }	
        scInter.fireDataChanged();
    }
	
    private void deleteUserGroup(String gname)
    {
	try
	{
		Vector viewNames = authAdmin.getAllViewNames(gname);
		for(int i=0;i<viewNames.size();i++){
			String viewName=viewNames.elementAt(i).toString();
			authAdmin.removeViewFromGroup(gname,viewName);
			authAdmin.removeOperationFromView(null,viewName);
			//Form the authViewObj 
			AuthorizedViewObject authViewObj = new AuthorizedViewObject();
			authViewObj.setAuthorizedViewName(viewName);
			authViewObj.setViewProperties(null);
			//Remove from the ViewPropertiesTable
			authAdmin.removeAuthorizedView(authViewObj);
		
		}
		
	}
	catch(Exception ae)
	{
		scInter.showError(ae.getMessage());
	}		
    }


    public  void setUserData(String uname,String pwd,Vector groups, Hashtable addoper,Hashtable modoper)
    {
        boolean result ;
        if ( groups == null || groups.size() == 0 )
        {
            groups = new Vector();
            groups.addElement("default "+uname+" Group");
        }
        try 
        {
            if(pwd == null || pwd.equals(""))	
            {
                result = userconfigapi.createNewAccount(uname,uname,groups.elementAt(0).toString());
            }
            else
            {
                result = userconfigapi.createNewAccount(uname,pwd,groups.elementAt(0).toString());
            }
            for(int i=1;i<groups.size();i++)
            {
                authAdmin.assignUserToGroup(uname, groups.elementAt(i).toString());	
            }	
            if(addoper != null && addoper.size()!=0)
            {
                String viewName = "default "+uname+" View";
                authAdmin.assignUserToGroup(uname,"default "+uname+" Group");
                authAdmin.assignViewToGroup("default "+uname+" Group",viewName);
                for(Enumeration en=addoper.keys();en.hasMoreElements();)
                {
                    String type = en.nextElement().toString();
                    if(addoper.get(type).toString().equals("0"))
                    {
                        authAdmin.assignOperationToView(type,viewName,true);
                    }
                    else
                    {
                        authAdmin.assignOperationToView(type,viewName,false);
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
}




