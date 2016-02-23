/*$Id: AuthSessionBE.java,v 1.25.6.4.8.4 2006/02/23 14:12:17 venkatramanan Exp $*/
/*
 * @(#)AuthSessionBE.java
 * Copyright (c) 2001 Adventnet, Inc. All Rights Reserved.
 * Please read the associated COPYRIGHTS file for more details.
 *
 * ADVENTNET, INC. MAKES NO REPRESENTATIONS OR WARRANTIES  ABOUT THE
 * SUITABILITY OF THE SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING
 * BUT NOT LIMITED TO THE IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE, OR NON-INFRINGEMENT.  ADVENTNET, INC. SHALL
 * NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF
 * USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE  OR ITS DERIVATIVES.
 */
/*
 * AuthSessionBE class
 */

package com.adventnet.nms.security.authorization;

//Nms imports
import com.adventnet.nms.security.common.AuthConstants;
import com.adventnet.security.authorization.*;
import com.adventnet.security.audit.*;
import com.adventnet.nms.startnms.SocketSessionConnectionBE;
import com.adventnet.nms.startnms.MainSocketSessionBE;
import com.adventnet.security.common.SecurityXMLParser;
import com.adventnet.nms.util.*;
import com.adventnet.nms.util.NmsUtil;
import com.adventnet.nms.util.PureUtils;
import com.adventnet.nms.util.CommonUtil;
import com.adventnet.nms.util.NmsLogMgr;
import com.adventnet.management.log.Log;
import com.adventnet.management.log.LogMgr;
import com.adventnet.management.log.LogUser;
import com.adventnet.nms.commonbe.GenericBEAPI;
import com.adventnet.nms.commonbe.GenericBEAPIImpl;
import com.adventnet.nms.commonbe.FileData;
import com.adventnet.nms.db.util.DBXmlUtility;
import java.sql.Connection;


//Jaxp imports
import org.w3c.dom.*;
import org.xml.sax.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.stream.StreamResult;   
import javax.xml.parsers.*;
import javax.xml.transform.stream.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;


//Java imports
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Vector;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Properties;
import java.util.StringTokenizer;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.StringWriter;


class AuthSessionBE implements SocketSessionConnectionBE, AuthConstants {

    String uid = null;
    CustomViewScopeAPI authAdmin = null;
    AuditAPI auditAPI = null;
    MainSocketSessionBE mainSession = null;
    private static String Auth="AUTHORIZATION-DATA";//No Internationalisation
    private String errMessage = null;
    static String SECURITY_ID = "SECURITY_ADMIN";//No Internationalisation
    String filename=PureUtils.rootDir+"conf/securitydbData.xml";//No Internationalisation
    String backupFileName = PureUtils.rootDir + "conf/backup/securitydbData.xml";//No Internationalisation
    SecurityXMLParser serverParser= null;
    Document xmlDoc = null;
    SecurityDataFetcher dataFetcher = null;
    boolean readFromDB = false;
    AuthorizationEngine authEngine=null;

    boolean doAuthorization=false;

    public AuthSessionBE(AuthorizationAdmin authAdmin, AuthorizationEngine authEngine,AuditAPI auditAPI, MainSocketSessionBE mss, boolean persistDataInXML)
    {
        this.authAdmin = (CustomViewScopeAPI) authAdmin;
        this.auditAPI = auditAPI;
        this.authEngine=authEngine;
        doAuthorization=NmsAuthManager.doAuthorization;
        if (!persistDataInXML)
        {
            readFromDB = true;
        }
        else
        {
            readFromDB = false;
        }

        try
        {
            dataFetcher = new SecurityDataFetcher(NmsUtil.relapi);
        }
        catch (AuthorizationException e)
        {
            NmsLogMgr.MISCERR.fail(NmsUtil.GetString("AuthSessionBE : Exception while constructing SecurityDataFetcher ") + e.getMessage(),null); 
        }

        mainSession = mss;
        mainSession.registerForResponses(this,SECURITY_ID);
    }


    public void receive(String uid, byte[] data)
    {
        this.uid = uid;
        try{
            DataInputStream dis = new DataInputStream(new ByteArrayInputStream(data));
            int reqType = dis.readInt();
            readData(uid,reqType,dis);
        }catch(Exception ioe){
            ioe.printStackTrace();
            NmsLogMgr.MISCERR.fail(NmsUtil.GetString("AuthSessionBE : Exception while receive ") + ioe.getMessage(),null); 
        }
    }

    void readData(String uid,int reqType, DataInputStream dis) throws Exception
    {
        String user = mainSession.getUserNameForSession(uid);
        errMessage = "";//No Internationalisation

        if(reqType == GET_ALL_OPERATIONS){
            returnData(GET_ALL_OPERATIONS);
        }
	else if(reqType == CHECK_OPERATIONS)
	    {
		sendAuthorizationInfo(user);
	    }
        else if(reqType == GET_ALL_AUTH_VIEWS){
            returnData(GET_ALL_AUTH_VIEWS);
        }
        else if(reqType == GET_ALL_ATTRIBUTES){
            returnData(GET_ALL_ATTRIBUTES);
        }
        else if(reqType == GET_ALL_GROUPS){
            returnData(GET_ALL_GROUPS);
        }
        else if(reqType == GET_USERS_FOR_GROUPS){
            returnData(GET_USERS_FOR_GROUPS);
        }
        else if(reqType == GET_ALL_USERS){
            returnData(GET_USERS_FOR_GROUPS);
        }
        else if(reqType == ADD_USER_WITH_PROPS){

            int requestType = ADD_USER_WITH_PROPS_RESP;
            if(doAuthorization)
            {
                if ( ! authEngine.isAuthorized ( user, "Add Users") ) 
                {
                    String errormsg = user + " is not authorized to perform the operation";
                    sendDataToClient ( FAILURE, requestType, errormsg.getBytes() , uid);
                    return;
                }
            }
            int len = dis.readInt();
            byte[] userData = new byte[len];
            dis.readFully(userData);
            boolean result = processAddOrModifyUserInfo(userData,reqType);
            if(result){
                byte[] dataToSend = getUserDataToSend(requestType);
                sendDataToClient( SUCCESS, requestType, dataToSend, uid);
            } else
                sendDataToClient( FAILURE, requestType, errMessage.getBytes(), uid);
        }
        else if(reqType == MODIFY_USER_WITH_PROPS){
            int requestType = MODIFY_USER_WITH_PROPS_RESP;
            if(doAuthorization)
            {
                if ( ! (authEngine.isAuthorized ( user, "Assign User To Group") ||authEngine.isAuthorized ( user, "Remove User From Group"))  ) 
                {
                    String errormsg = user + " is not authorized to perform the operation";
                    sendDataToClient ( FAILURE, requestType, errormsg.getBytes() , uid);
                    return;
                }
            }

            int len = dis.readInt();
            byte[] userData = new byte[len];
            dis.readFully(userData);
            boolean result = processAddOrModifyUserInfo(userData,reqType);
            if(result){
                byte[] dataToSend = getUserDataToSend(requestType);
                sendDataToClient( SUCCESS, requestType, dataToSend, uid);
            } else
                sendDataToClient( FAILURE, requestType, errMessage.getBytes(), uid);
        }	 
        else if(reqType == ADD_GROUP_VIEWS){
            int requestType = ADD_GROUP_VIEWS_RESP;
            int len = dis.readInt();
            byte[] groupData = new byte[len];
            dis.readFully(groupData);
            boolean result = processAddOrModifyGroupViewInfo(groupData,reqType);
            if(result){
                byte[] dataToSend = getUserDataToSend(requestType);
                sendDataToClient( SUCCESS, requestType, dataToSend, uid);
            } else
                sendDataToClient( FAILURE, requestType, errMessage.getBytes(), uid);
        }
        else if(reqType == MODIFY_GROUP_VIEWS){
            int requestType = MODIFY_GROUP_VIEWS_RESP;
            if(doAuthorization)
            {
                if ( ! authEngine.isAuthorized ( user, "Modify Group Scope Relation") ) 
                {
                    String errormsg = user + " is not authorized to perform the operation";
                    sendDataToClient ( FAILURE, requestType, errormsg.getBytes() , uid);
                    return;
                }    
            }                

            int len = dis.readInt();
            byte[] groupData = new byte[len];
            dis.readFully(groupData);
            boolean result = processAddOrModifyGroupViewInfo(groupData,reqType);
            if(result){
                byte[] dataToSend = getUserDataToSend(requestType);
                sendDataToClient( SUCCESS, requestType, dataToSend, uid);
            } else
                sendDataToClient( FAILURE, requestType, errMessage.getBytes(), uid);
        }
        else if(reqType == ADD_AUTH_VIEWS_WITH_OPER){
            int requestType = ADD_AUTH_VIEWS_WITH_OPER_RESP;
            int len = dis.readInt();
            byte[] authViewData = new byte[len];
            dis.readFully(authViewData);
            boolean result = processAddOrModifyOrDeleteAuthViewOperInfo(authViewData,reqType);
            if(result){
                byte[] dataToSend = getUserDataToSend(requestType);
                sendDataToClient( SUCCESS, requestType, dataToSend, uid);
            } else
                sendDataToClient( FAILURE, requestType, errMessage.getBytes(), uid);
        }
        else if(reqType == MODIFY_AUTH_VIEWS_WITH_OPER){
            int requestType = MODIFY_AUTH_VIEWS_WITH_OPER_RESP;
            int len = dis.readInt();
            byte[] authViewData = new byte[len];
            dis.readFully(authViewData);
            boolean result = processAddOrModifyOrDeleteAuthViewOperInfo(authViewData,reqType);
            if(result){
                byte[] dataToSend = getUserDataToSend(requestType);
                sendDataToClient( SUCCESS, requestType, dataToSend, uid);
            } else
                sendDataToClient( FAILURE, requestType, errMessage.getBytes(), uid);
        }
        else if(reqType == DELETE_AUTH_VIEWS_WITH_OPER){
            int requestType = DELETE_AUTH_VIEWS_WITH_OPER_RESP;
            int requestTypeForGroup = DELETE_GROUPS_RESP;
            int len = dis.readInt();
            byte[] authViewData = new byte[len];
            dis.readFully(authViewData);
            boolean result = processAddOrModifyOrDeleteAuthViewOperInfo(authViewData,reqType);
            if(result){
                byte[] dataToSend = getUserDataToSend(requestType);
                byte[] dataToSendForGroup = getUserDataToSend(requestTypeForGroup);
                sendDataToClient( SUCCESS, requestType, dataToSend, uid);
                sendDataToClient( SUCCESS, requestTypeForGroup, dataToSendForGroup, uid);
            }
            else{
                sendDataToClient( FAILURE, requestType, errMessage.getBytes(), uid);
                sendDataToClient( FAILURE, requestType, errMessage.getBytes(), uid);
            }
        }
        else if(reqType == ADD_AUTH_VIEWS){
            int requestType = ADD_AUTH_VIEWS_WITH_OPER_RESP;
            int len = dis.readInt();
            byte[] authViewData = new byte[len];
            dis.readFully(authViewData);
            boolean result = processAddOrModifyOrDeleteAuthViewInfo(authViewData,reqType);
            if(result) {
                byte[] dataToSend = getUserDataToSend(requestType);
                sendDataToClient( SUCCESS, requestType, dataToSend, uid);
            } else
                sendDataToClient( FAILURE, requestType, errMessage.getBytes(), uid);
        }
        else if(reqType == DELETE_AUTHVIEWS){
            int requestType = DELETE_AUTH_VIEWS_WITH_OPER_RESP;
            int len = dis.readInt();
            byte[] authViewData = new byte[len];
            dis.readFully(authViewData);
            boolean result = processAddOrModifyOrDeleteAuthViewInfo(authViewData,reqType);
            if(result){
                byte[] dataToSend = getUserDataToSend(requestType);
                sendDataToClient( SUCCESS, requestType, dataToSend, uid);
            } else
                sendDataToClient( FAILURE, requestType, errMessage.getBytes(), uid);
        }
        else if(reqType == MODIFY_AUTH_VIEWS){
            int requestType = MODIFY_AUTH_VIEWS_WITH_OPER_RESP;
            int len = dis.readInt();
            byte[] authViewData = new byte[len];
            dis.readFully(authViewData);
            boolean result = processAddOrModifyOrDeleteAuthViewInfo(authViewData,reqType);
            if(result){
                byte[] dataToSend = getUserDataToSend(requestType);
                sendDataToClient( SUCCESS, requestType, dataToSend, uid);
            } else
                sendDataToClient( FAILURE, requestType, errMessage.getBytes(), uid);
        }
        else if(reqType == DELETE_GROUPS){
            int requestType = DELETE_GROUPS_RESP;
            if(doAuthorization)
            {
                if ( ! authEngine.isAuthorized ( user, "Remove Group") ) 
                {
                    String errormsg = user + " is not authorized to perform the operation";
                    sendDataToClient ( FAILURE, requestType, errormsg.getBytes() , uid);
                    return;
                }
            }
            int len = dis.readInt();
            byte[] groupData = new byte[len];
            dis.readFully(groupData);
            boolean result = processDeleteGroup(groupData);
            if(result){
                byte[] dataToSend = getUserDataToSend(requestType);
                sendDataToClient( SUCCESS, requestType, dataToSend, uid);
            } else
                sendDataToClient( FAILURE, requestType, errMessage.getBytes(), uid);
        }
        else if(reqType == DELETE_USER){
            int requestType = DELETE_USER_RESP;
            if(doAuthorization)
            {
                if ( ! authEngine.isAuthorized ( user, "Remove Users") ) 
                { 
                    String errormsg = user + " is not authorized to perform the operation";
                    sendDataToClient ( FAILURE, requestType, errormsg.getBytes() , uid);
                    return;
                }
            }

            int len = dis.readInt();
            byte[] userData = new byte[len];
            dis.readFully(userData);
            boolean result = processDeleteUser(userData);
            if(result){
                byte[] dataToSend = getUserDataToSend(requestType);
                sendDataToClient ( SUCCESS, requestType, dataToSend, uid);
            }
            else
                sendDataToClient ( FAILURE, requestType, errMessage.getBytes(), uid);
        }
        else if(reqType == ADD_OPERATIONS){
            int requestType = ADD_OPERATIONS_RESP;
            if(doAuthorization)
            {
                if ( ! authEngine.isAuthorized ( user, "Add Operation") ) 
                {
                    String errormsg = user + " is not authorized to perform the operation";
                    sendDataToClient ( FAILURE, requestType, errormsg.getBytes() , uid);
                    return;
                }
            }

            int len = dis.readInt();
            byte[] operData = new byte[len];
            dis.readFully(operData);
            boolean res = processAddOrDeleteOperationTree(operData,reqType);
            if(res) {
                byte[] dataToSend = getUserDataToSend(requestType);
                sendDataToClient ( SUCCESS, requestType, dataToSend, uid);
            }
            else
                sendDataToClient ( FAILURE, requestType, errMessage.getBytes(), uid);
        }
        else if(reqType == DELETE_OPERATIONS){
            int requestType = DELETE_OPERATIONS_RESP;
            if(doAuthorization)
            {
                if ( ! authEngine.isAuthorized ( user, "Remove Operation") ) 
                {
                    String errormsg = user + " is not authorized to perform the operation";
                    sendDataToClient ( FAILURE, requestType, errormsg.getBytes() , uid);
                    return;
                }
            }
            int requestTypeForView = DELETE_AUTH_VIEWS_WITH_OPER_RESP;
            int len = dis.readInt();
            byte[] operData = new byte[len];
            dis.readFully(operData);
            boolean result = processAddOrDeleteOperationTree(operData,reqType);
            if(result){
                byte[] dataToSend = getUserDataToSend(requestType);
                byte[] dataToSendForView = getUserDataToSend(requestTypeForView);
                sendDataToClient ( SUCCESS, requestType, dataToSend, uid);
                sendDataToClient ( SUCCESS, requestTypeForView, dataToSendForView, uid);
            }
            else{
                sendDataToClient ( FAILURE, requestType, errMessage.getBytes(), uid);
                sendDataToClient ( FAILURE, requestTypeForView, errMessage.getBytes(), uid);
            }
        } 
        else if(reqType == SET_ALL_ATTRIBUTES){
            int requestType = SET_ALL_ATTRIBUTES_RESP;
            if(doAuthorization)
            {
                if ( ! authEngine.isAuthorized ( user, "Set User Profile") ) 
                {
                    String errormsg = user + " is not authorized to perform the operation";
                    sendDataToClient ( FAILURE, requestType, errormsg.getBytes() , uid);
                    return;
                }
            }
            int len = dis.readInt();
            byte[] userData = new byte[len];
            dis.readFully(userData);
            boolean result = addToUserConfTable(userData,reqType);
            if(result){
                byte[] dataToSend = getUserDataToSend(requestType);
                sendDataToClient ( SUCCESS, requestType, dataToSend, uid);
            }
            else
                sendDataToClient ( FAILURE, requestType, errMessage.getBytes(), uid);
        }
        else if(reqType == SET_PASSWORD_AGE){
            if ( ! authEngine.isAuthorized ( user, "Set User Profile") ) 
            {
                return;
            }
            int requestType = SET_ALL_ATTRIBUTES_RESP;
            int len = dis.readInt();
            byte[] userData = new byte[len];
            dis.readFully(userData);
            boolean result = addToUserConfTable(userData,reqType);
            if(result){
                byte[] dataToSend = getUserDataToSend(requestType);
                sendDataToClient ( SUCCESS, requestType, dataToSend, uid);
            }
            else
                sendDataToClient ( FAILURE, requestType, errMessage.getBytes(), uid);
        }
        else if(reqType == SET_USER_EXPIRY){
            if ( ! authEngine.isAuthorized ( user, "Set User Profile") ) 
            {
                return;
            }
            int requestType = SET_ALL_ATTRIBUTES_RESP;
            int len = dis.readInt();
            byte[] userData = new byte[len];
            dis.readFully(userData);
            boolean result = addToUserConfTable(userData,reqType);
            if(result){
                byte[] dataToSend = getUserDataToSend(requestType);
                sendDataToClient ( SUCCESS, requestType, dataToSend, uid);
            }
            else
                sendDataToClient ( FAILURE, requestType, errMessage.getBytes(), uid);
        }
        else if(reqType == SET_USER_STATUS){
            if ( ! authEngine.isAuthorized ( user, "Set User Profile") ) 
            {
                return;
            }
            int requestType = SET_ALL_ATTRIBUTES_RESP;
            int len = dis.readInt();
            byte[] userData = new byte[len];
            dis.readFully(userData);
            boolean result = addToUserConfTable(userData,reqType);
            if(result){
                byte[] dataToSend = getUserDataToSend(requestType);
                sendDataToClient ( SUCCESS, requestType, dataToSend, uid);
            }
            else
                sendDataToClient ( FAILURE, requestType, errMessage.getBytes(), uid);
        }
        else if(reqType == CHANGE_PASSWORD) {
            int requestType = MODIFY_USER_WITH_PROPS_RESP;
            if(doAuthorization)
            {
                if ( ! authEngine.isAuthorized ( user, "Change Password") ) 
                {
                    String errormsg = user + " is not authorized to perform the operation";
                    sendDataToClient ( FAILURE, requestType, errormsg.getBytes() , uid);
                    return;
                }
            }
            int len = dis.readInt();
            byte[] userData = new byte[len];
            dis.readFully(userData);
            boolean result = processChangePassword(userData);
            if(result){
                byte[] dataToSend = getUserDataToSend(requestType);
                sendDataToClient ( SUCCESS, requestType, dataToSend, uid);
            }
            else
                sendDataToClient ( FAILURE, requestType, errMessage.getBytes(), uid);
        }
        else if(reqType == ADD_USERS) {

            int requestType = MODIFY_USER_WITH_PROPS_RESP;
            if(doAuthorization)
            {
                if ( ! (authEngine.isAuthorized ( user, "Assign User To Group") || authEngine.isAuthorized ( user, "Remove User From Group")) ) 
                {
                    String errormsg = user + " is not authorized to perform the operation";
                    sendDataToClient ( FAILURE, requestType, errormsg.getBytes() , uid);
                    return;
                }
            }
            int len = dis.readInt();
            byte[] userData = new byte[len];
            dis.readFully(userData);
            boolean result = processAddUsers(userData);
            if(result){
                byte[] dataToSend = getUserDataToSend(requestType);
                sendDataToClient ( SUCCESS, requestType, dataToSend, uid);
            }
            else
                sendDataToClient ( FAILURE, requestType, errMessage.getBytes(), uid);
        }
        else if(reqType == ADD_OPER_GROUP) {
            int requestType = ADD_OPER_GROUP_RESP;
            if(doAuthorization)
            {
                if ( ! authEngine.isAuthorized ( user, "Add Group") ) 
                {
                    String errormsg = user + " is not authorized to perform the operation";
                    sendDataToClient ( FAILURE, requestType, errormsg.getBytes() , uid);
                    return;
                }
            }

            int len = dis.readInt();
            byte[] userData = new byte[len];
            dis.readFully(userData);
            boolean result = processAddOperGroup(userData);
            if(result){
                byte[] dataToSend = getUserDataToSend(requestType);
                sendDataToClient ( SUCCESS, requestType, dataToSend, uid);
            }
            else
                sendDataToClient ( FAILURE, requestType, errMessage.getBytes(), uid);
        } else if ( reqType == ADD_OPER_FOR_SCOPE ) {
            int requestType = ADD_OPER_FOR_SCOPE_RESP;
            int len = dis.readInt();
            byte[] userData = new byte[len];
            dis.readFully(userData);
            boolean result = processAddOperForScope(userData);
            if(result){
                byte[] dataToSend = getUserDataToSend(requestType);
                sendDataToClient ( SUCCESS, requestType, dataToSend, uid);
            }
            else
                sendDataToClient ( FAILURE, requestType, errMessage.getBytes(), uid);
        }
        else if ( reqType == ADD_SCOPE ) {
            int requestType = ADD_SCOPE_RESP;
            if(doAuthorization)
            {
                if ( ! authEngine.isAuthorized ( user, "Create Scope For Group") ) 
                {
                    String errormsg = user + " is not authorized to perform the operation";
                    sendDataToClient ( FAILURE, requestType, errormsg.getBytes() , uid);
                    return;
                }
            }
            int len = dis.readInt();
            byte[] userData = new byte[len];
            dis.readFully(userData);
            boolean result = processCreateScope(userData);
            if(result){
                byte[] dataToSend = getUserDataToSend(requestType);
                sendDataToClient ( SUCCESS, requestType, dataToSend, uid);
            }
            else
                sendDataToClient ( FAILURE, requestType, errMessage.getBytes(), uid);
        }
        else if(reqType == MODIFY_OPER_FOR_USER){
            int requestType = MODIFY_OPER_FOR_USER_RESP;
            if(doAuthorization)
            {
                if ( ! authEngine.isAuthorized ( user, "Set User Permission") ) 
                {
                    String errormsg = user + " is not authorized to perform the operation";
                    sendDataToClient ( FAILURE, requestType, errormsg.getBytes() , uid);
                    return;
                }
            }
            int len = dis.readInt();
            byte[] userData = new byte[len];
            dis.readFully(userData);
            boolean result = processModifyOperForUserInfo(userData);
            if(result){
                byte[] dataToSend = getUserDataToSend(requestType);
                sendDataToClient( SUCCESS, requestType, dataToSend, uid);
            } else
                sendDataToClient( FAILURE, requestType, errMessage.getBytes(), uid);
        }	 
        else if(reqType == MODIFY_OPER_GROUP){
            int requestType = ADD_OPER_GROUP_RESP;
            if(doAuthorization)
            {
                if ( ! authEngine.isAuthorized ( user, "Set Group Permission") ) 
                {
                    String errormsg = user + " is not authorized to perform the operation";
                    sendDataToClient ( FAILURE, requestType, errormsg.getBytes() , uid);
                    return;
                }
            }

            int len = dis.readInt();
            byte[] userData = new byte[len];
            dis.readFully(userData);
            boolean result = processModifyOperForGroupInfo(userData);
            if(result){
                byte[] dataToSend = getUserDataToSend(requestType);
                sendDataToClient( SUCCESS, requestType, dataToSend, uid);
            } else
                sendDataToClient( FAILURE, requestType, errMessage.getBytes(), uid);
        }
        else if(reqType == GET_AUDIT_TRAILS){
            returnAuditData(GET_AUDIT_TRAILS);
        }
        else if(reqType == CLEAR_AUDIT_TRAILS)
        {
            int requestType =CLEAR_AUDIT_TRAILS;
            if(doAuthorization)
            {
                if ( ! authEngine.isAuthorized ( user, "Clear Audit Trails") ) 
                {
                    String errormsg = user + " is not authorized to perform the operation";
                    sendDataToClientForAudit( FAILURE, requestType,errormsg.getBytes() , uid);
                    return;
                }
            }
            int len = dis.readInt();
            byte[] userData = new byte[len];
            dis.readFully(userData);
            boolean result = processDeleteAudit(userData);
            if(result){
                byte[] dataToSend = getAuditData();
                sendDataToClientForAudit( SUCCESS, requestType, dataToSend, uid);
            } else
                sendDataToClientForAudit( FAILURE, requestType, errMessage.getBytes(), uid);
        }
        else if ( reqType == GET_AUTH_VIEWS ) {
            returnAuthorizedViews ( GET_AUTH_VIEWS );
        }
        else if(reqType == CREATE_CUSTOM_VIEW ){
            if(doAuthorization)
            {
                if ( ! authEngine.isAuthorized ( user, "Create Scope For Group") ) 
                {
                    return;
                }
            }

            int requestType = CREATE_CUSTOM_VIEW_RESP;
            int len = dis.readInt();
            byte[] userData = new byte[len];
            dis.readFully(userData);
            boolean result = createCustomViewScope(userData);
            if(result){
                byte[] dataToSend = getUserDataToSend(requestType);
                sendDataToClient ( SUCCESS, requestType, dataToSend, uid);
            }
            else
                sendDataToClient ( FAILURE, requestType, errMessage.getBytes(), uid);
        }
        else if(reqType == UPDATE_AUTH_VIEWS ){
            int requestType = UPDATE_AUTH_VIEWS_RESP;
            if(doAuthorization)
            {
                if ( ! authEngine.isAuthorized ( user, "Modify Group Scope Relation") ) 
                {
                    String errormsg = user + " is not authorized to perform the operation";
                    sendDataToClient ( FAILURE, requestType, errormsg.getBytes() , uid);
                    return;
                }
            }

            int len = dis.readInt();
            byte[] userData = new byte[len];
            dis.readFully(userData);
            boolean result = processUpdateAuthViews(userData);
            if(result){
                byte[] dataToSend = getUserDataToSend(requestType);
                sendDataToClient ( SUCCESS, requestType, dataToSend, uid);
            }
            else
                sendDataToClient ( FAILURE, requestType, errMessage.getBytes(), uid);
        }
        //Method added for saving the audit trails in to
        //the file on the server side..
        else if(reqType == SAVE_AUDIT_TRAILS ){
            int requestType = SAVE_AUDIT_TRAILS_RESP;
            int len = dis.readInt();
            byte[] userData = new byte[len];
            dis.readFully(userData);
            boolean result = saveToFile(userData);
            if(result){
                byte[] dataToSend = getUserDataToSend(requestType);
                sendDataToClient ( SUCCESS, requestType, dataToSend, uid);
            }
            else
                sendDataToClient ( FAILURE, requestType, errMessage.getBytes(), uid);
        }
        else if (reqType == GET_PROP_NAMES_FOR_CVS)
        {
            sendPropNamesForCustomViewScope();
        }
        else
        {
            System.err.println(NmsUtil.GetString("Unknown req type"));
        }


    }

    byte[] getUserDataToSend(int resType)
    {
        if (!readFromDB)
        {
            try{
                serverParser = new SecurityXMLParser();
                InputStream is = openFile(new File(filename));
                serverParser.parseInputStream(is);
                Node rootNode = serverParser.getRootNode();
                if ((rootNode == null) || (rootNode.getChildNodes() == null))
                {
                    InputStream backupFileStream = openFile(new File(backupFileName));
                    serverParser.parseInputStream(backupFileStream);
                }
                updateSysTime(is);
            }catch(Exception ioe){
                NmsLogMgr.MISCERR.fail(NmsUtil.GetString("AuthSessionBE : Exception while parsing XML file ") + ioe.getMessage(),null); 
                return null;
            }
        }

        if((resType == SET_ALL_ATTRIBUTES_RESP)|| (resType == ADD_USER_WITH_PROPS_RESP) || (resType == MODIFY_USER_WITH_PROPS_RESP) || (resType == DELETE_USER_RESP))
        {
            byte[] data = null;
            Node groupNode = null;
            Node confNode = null;

            try{

                if (!readFromDB)
                {
                    groupNode = serverParser.getAuthNode("USER-GROUP");//No Internationalisation
                    confNode = serverParser.getAuthNode("USER-CONF");//No Internationalisation
                }
                else
                {
                    groupNode = dataFetcher.getDataFromUserGroupTable();
                    confNode = dataFetcher.getDataFromUserConfTable();
                    updateUserConfNode(confNode);
                }

                DocumentBuilderFactory docBuilderfactorys = DocumentBuilderFactory.newInstance();
                DocumentBuilder docBuilder = docBuilderfactorys.newDocumentBuilder();
                xmlDoc=docBuilder.newDocument();
                Element rootNode = xmlDoc.createElement(Auth);
                Node impo=xmlDoc.importNode(groupNode,true);
                Node myNode=xmlDoc.importNode(confNode,true);
                rootNode.appendChild(impo);
                rootNode.appendChild(myNode);

                if (!readFromDB)
                {
                    data = serverParser.returnBytes(rootNode);
                }
                else
                {
                    data = getBytes(rootNode);
                }

            }catch(Exception e){
                NmsLogMgr.MISCERR.fail(NmsUtil.GetString("AuthSessionBE : Exception while constructing data for sending to client ") + e.getMessage(),null); 
            }
            return data;
        }
        else if((resType == ADD_GROUP_VIEWS_RESP) || (resType == MODIFY_GROUP_VIEWS_RESP) || (resType == DELETE_GROUPS_RESP) || (resType == MODIFY_OPER_FOR_USER_RESP) )
        {
            byte[] data = null;
            Node viewNode = null;
            Node userNode = null;
            Node viewOprNode = null;

            try{
                if (!readFromDB)
                {
                    viewNode = serverParser.getAuthNode("VIEW-GROUP");//No Internationalisation
                    userNode = serverParser.getAuthNode("USER-GROUP");//No Internationalisation
                    viewOprNode = serverParser.getAuthNode("VIEW-OPERATION");//No Internationalisation
                }
                else
                {
                    viewNode = dataFetcher.getDataFromViewsToGroupTable();
                    userNode = dataFetcher.getDataFromUserGroupTable();
                    viewOprNode = dataFetcher.getDataFromViewToOperationsTable();
                }

                DocumentBuilderFactory docBuilderfactorys = DocumentBuilderFactory.newInstance();
                DocumentBuilder docBuilder = docBuilderfactorys.newDocumentBuilder();
                xmlDoc=docBuilder.newDocument();
                Element rootNode = xmlDoc.createElement(Auth);

                Node node1 = xmlDoc.importNode(viewNode,true);
                Node node2 = xmlDoc.importNode(userNode,true);
                Node node3 = xmlDoc.importNode(viewOprNode,true);

                rootNode.appendChild(node1);
                rootNode.appendChild(node2);
                rootNode.appendChild(node3);

                if (!readFromDB)
                {
                    data = serverParser.returnBytes(rootNode);
                }
                else
                {
                    data = getBytes(rootNode);
                }

            }catch(Exception e){
                NmsLogMgr.MISCERR.fail(NmsUtil.GetString("AuthSessionBE : Exception while constructing data for sending to client ") + e.getMessage(),null); 
            }
            return data;
        }
        else if((resType==ADD_AUTH_VIEWS_WITH_OPER_RESP) || (resType==MODIFY_AUTH_VIEWS_WITH_OPER_RESP) || (resType==DELETE_AUTH_VIEWS_WITH_OPER_RESP))
        {
            byte[] data = null;
            Node viewNode = null;
            Node viewPropNode = null;

            try{

                if (!readFromDB)
                {
                    viewNode = serverParser.getAuthNode("VIEW-OPERATION");//No Internationalisation
                    viewPropNode = serverParser.getAuthNode("VIEW-PROPERTY");//No Internationalisation
                }
                else
                {
                    viewNode = dataFetcher.getDataFromViewToOperationsTable();
                    viewPropNode = dataFetcher.getDataFromViewPropertiesTable();
                }

                DocumentBuilderFactory docBuilderfactorys = DocumentBuilderFactory.newInstance();
                DocumentBuilder docBuilder = docBuilderfactorys.newDocumentBuilder();
                xmlDoc=docBuilder.newDocument();
                Element rootNode = xmlDoc.createElement(Auth);
                Node impo=xmlDoc.importNode(viewNode,true);
                Node myNode=xmlDoc.importNode(viewPropNode,true);
                rootNode.appendChild(impo);
                rootNode.appendChild(myNode);

                if (!readFromDB)
                {
                    data = serverParser.returnBytes(rootNode);
                }
                else
                {
                    data = getBytes(rootNode);
                }

            }catch(Exception e){
                NmsLogMgr.MISCERR.fail(NmsUtil.GetString("AuthSessionBE : Exception while constructing data for sending to client ") + e.getMessage(),null); 
            }
            return data;
        }
        else if((resType == ADD_OPERATIONS_RESP) || (resType == DELETE_OPERATIONS_RESP))
        {
            byte[] data = null;
            Node operNode = null;
            try{
                if (!readFromDB)
                {
                    operNode = serverParser.getAuthNode("OPERATION-TREE");//No Internationalisation
                }
                else
                {
                    operNode = dataFetcher.getDataFromOperationsTreeTable();
                }

                DocumentBuilderFactory docBuilderfactorys = DocumentBuilderFactory.newInstance();
                DocumentBuilder docBuilder = docBuilderfactorys.newDocumentBuilder();
                xmlDoc=docBuilder.newDocument();
                Element rootNode = xmlDoc.createElement(Auth);
                Node impo=xmlDoc.importNode(operNode,true);
                rootNode.appendChild(impo);

                if (!readFromDB)
                {
                    data = serverParser.returnBytes(rootNode);
                }
                else
                {
                    data = getBytes(rootNode);
                }
            }catch(Exception e){
                NmsLogMgr.MISCERR.fail(NmsUtil.GetString("AuthSessionBE : Exception while constructing data for sending to client ") + e.getMessage(),null); 
            }
            return data;
        }
        else if(resType == ADD_OPER_GROUP_RESP) {
            byte[] data = null;
            Node viewNode = null;
            Node viewGrpNode = null;

            try{
                if (!readFromDB)
                {
                    viewNode = serverParser.getAuthNode("VIEW-OPERATION");//No Internationalisation
                    viewGrpNode = serverParser.getAuthNode("VIEW-GROUP");//No Internationalisation
                }
                else
                {
                    viewNode = dataFetcher.getDataFromViewToOperationsTable();
                    viewGrpNode = dataFetcher.getDataFromViewsToGroupTable();
                }

                DocumentBuilderFactory docBuilderfactorys = DocumentBuilderFactory.newInstance();
                DocumentBuilder docBuilder = docBuilderfactorys.newDocumentBuilder();
                xmlDoc=docBuilder.newDocument();
                Element rootNode = xmlDoc.createElement(Auth);
                Node impo=xmlDoc.importNode(viewNode,true);
                Node myNode=xmlDoc.importNode(viewGrpNode,true);
                rootNode.appendChild(impo);
                rootNode.appendChild(myNode);

                if (!readFromDB)
                {
                    data = serverParser.returnBytes(rootNode);
                }
                else
                {
                    data = getBytes(rootNode);
                }
            }catch(Exception e){
                NmsLogMgr.MISCERR.fail(NmsUtil.GetString("AuthSessionBE : Exception while constructing data for sending to client ") + e.getMessage(),null); 
            }
            return data;
        } 			
        else if(resType == UPDATE_AUTH_VIEWS_RESP ) {
            byte[] data = null;
            Node viewGrpNode = null;
            Node namedAuth = null;
            Node viewProp = null;

            try{
                if (!readFromDB)
                {
                    viewGrpNode = serverParser.getAuthNode("VIEW-GROUP");//No Internationalisation
                    namedAuth = serverParser.getAuthNode("NAMED-AUTH");//No Internationalisation
                    viewProp = serverParser.getAuthNode("VIEW-PROPERTY");//No Internationalisation
                }
                else
                {
                    viewGrpNode = dataFetcher.getDataFromViewsToGroupTable();
                    namedAuth = dataFetcher.getDataFromNamedViewToAuthorizedViewTable();
                    viewProp = dataFetcher.getDataFromViewPropertiesTable();
                }

                DocumentBuilderFactory docBuilderfactorys = DocumentBuilderFactory.newInstance();
                DocumentBuilder docBuilder = docBuilderfactorys.newDocumentBuilder();
                xmlDoc=docBuilder.newDocument();
                Element rootNode = xmlDoc.createElement(Auth);
                Node node1 = xmlDoc.importNode(viewGrpNode,true);
                Node node2 = xmlDoc.importNode(namedAuth,true);
                Node node3 = xmlDoc.importNode(viewProp,true);

                rootNode.appendChild(node1);
                rootNode.appendChild(node2);
                rootNode.appendChild(node3);

                if (!readFromDB)
                {
                    data = serverParser.returnBytes(rootNode);
                }
                else
                {
                    data = getBytes(rootNode);
                }

            }catch(Exception e){
                NmsLogMgr.MISCERR.fail(NmsUtil.GetString("AuthSessionBE : Exception while constructing data for sending to client ") + e.getMessage(),null); 
            }
            return data;
        }
        else if(resType == ADD_OPER_FOR_SCOPE_RESP || resType == ADD_SCOPE_RESP ) {
            byte[] data = null;
            Node viewOprNode = null;
            Node viewGrpNode = null;
            Node viewPrpNode = null;

            try{
                if (!readFromDB)
                {
                    viewOprNode = serverParser.getAuthNode("VIEW-OPERATION");//No Internationalisation
                    viewGrpNode = serverParser.getAuthNode("VIEW-GROUP");//No Internationalisation
                    viewPrpNode = serverParser.getAuthNode("VIEW-PROPERTY");//No Internationalisation
                }
                else
                {
                    viewOprNode = dataFetcher.getDataFromViewToOperationsTable();
                    viewGrpNode = dataFetcher.getDataFromViewsToGroupTable();
                    viewPrpNode = dataFetcher.getDataFromViewPropertiesTable();
                }

                DocumentBuilderFactory docBuilderfactorys = DocumentBuilderFactory.newInstance();
                DocumentBuilder docBuilder = docBuilderfactorys.newDocumentBuilder();
                xmlDoc=docBuilder.newDocument();
                Element rootNode = xmlDoc.createElement(Auth);
                Node node1 = xmlDoc.importNode(viewOprNode,true);
                Node node2 = xmlDoc.importNode(viewGrpNode,true);
                Node node3 = xmlDoc.importNode(viewPrpNode,true);

                rootNode.appendChild(node1);
                rootNode.appendChild(node2);
                rootNode.appendChild(node3);

                if (!readFromDB)
                {
                    data = serverParser.returnBytes(rootNode);
                }
                else
                {
                    data = getBytes(rootNode);
                }

            }catch(Exception e){
                NmsLogMgr.MISCERR.fail(NmsUtil.GetString("AuthSessionBE : Exception while constructing data for sending to client ") + e.getMessage(),null); 
            }
            return data;
        }
        else if(resType == DELETE_GROUPS_RESP) {
            byte[] data = null;
            Node rootNode = null;

            try {

                if (!readFromDB)
                {
                    rootNode = serverParser.getRootNode();
                    data = serverParser.returnBytes(rootNode);
                }
                else
                {
                    rootNode = dataFetcher.getRootNode();
                    data = getBytes(rootNode);
                }
            } catch (Exception e ) {
                NmsLogMgr.MISCERR.fail(NmsUtil.GetString("AuthSessionBE : Exception while constructing data for sending to client ") + e.getMessage(),null); 
            }
            return data;
        }
        else if ( resType == CREATE_CUSTOM_VIEW_RESP || resType == SAVE_AUDIT_TRAILS_RESP) {
            byte[] data = null;
            Node customNode = null;

            try{
                if (!readFromDB)
                {
                    customNode = serverParser.getAuthNode("NAMED-AUTH");//No Internationalisation
                }
                else
                {
                    customNode = dataFetcher.getDataFromNamedViewToAuthorizedViewTable();
                }

                DocumentBuilderFactory docBuilderfactorys = DocumentBuilderFactory.newInstance();
                DocumentBuilder docBuilder = docBuilderfactorys.newDocumentBuilder();
                xmlDoc=docBuilder.newDocument();
                Element rootNode = xmlDoc.createElement(Auth);
                Node impo=xmlDoc.importNode(customNode,true);
                rootNode.appendChild(impo);

                if (!readFromDB)
                {
                    data = serverParser.returnBytes(rootNode);
                }
                else
                {
                    data = getBytes(rootNode);
                }
            }catch(Exception e){
                NmsLogMgr.MISCERR.fail(NmsUtil.GetString("AuthSessionBE : Exception while constructing data for sending to client ") + e.getMessage(),null); 
            }
            return data;
        }
        return null;
    }
    void sendPropNamesForCustomViewScope()
    {
        ByteArrayOutputStream byteStream = null;
        DataOutputStream outputStream = null;

        try
        {
            Properties propNames = authAdmin.getPropertyNamesForCustomViewScope();

            byteStream = new ByteArrayOutputStream();
            outputStream = new DataOutputStream(byteStream);

            byte[] dataToSend = NmsUtil.serializeProperties(propNames);

            int length = dataToSend.length;
            outputStream.writeInt(GET_PROP_NAMES_FOR_CVS);
            outputStream.writeInt(length);
            outputStream.write(dataToSend, 0, length);
            outputStream.flush();
            mainSession.send(SECURITY_ID, uid, byteStream.toByteArray());
        }
        catch (Exception e)
        {
            NmsLogMgr.MISCERR.fail(NmsUtil.GetString("AuthSessionBE : Exception while sending data ") + e.getMessage(),null)
;
        }
        finally
        {
            try
            {
                byteStream.close();
                outputStream.close();
            } catch ( Exception e ) {}
        }
    }

    void sendAuthorizationInfo(String user)
    {
	ByteArrayOutputStream byteStream = null;
	DataOutputStream outputStream = null;
	
	try
	    {
		Properties prop = new Properties();
		
		if(doAuthorization)
		    {
			String addUser      = authEngine.isAuthorized ( user, "Add Users") + "";//No i18n
			String addGroup     = authEngine.isAuthorized ( user, "Add Group") + "";//No i18n
			String addOperation = authEngine.isAuthorized(user, "Add Operation") + "";//No i18n
			prop.setProperty("Add Users", addUser);//No i18n
			prop.setProperty("Add Group", addGroup);//No i18n
			prop.setProperty("Add Operation", addOperation);//No i18n
		    }
		
		byteStream = new ByteArrayOutputStream();
		outputStream = new DataOutputStream(byteStream);
		
		byte[] dataToSend = NmsUtil.serializeProperties(prop);
		
		int length = dataToSend.length;
		outputStream.writeInt(CHECK_OPERATIONS);
		outputStream.writeInt(length);
		outputStream.write(dataToSend, 0, length);
		outputStream.flush();
		mainSession.send(SECURITY_ID, uid, byteStream.toByteArray());
	    }
	catch (Exception e)
	    {
		NmsLogMgr.MISCERR.fail(NmsUtil.GetString("AuthSessionBE :Exception while sending data ") + e.getMessage(),null);//No i18n
	    }
	finally
	    {
		try
		    {
			byteStream.close();
			outputStream.close();
		    } 
		catch( Exception e ) 
		    {
		    }
	    }
    }
    
    void returnData(int requestType)
    {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        DataOutputStream outp = new DataOutputStream(byteStream);
        byte[] dataToSend = null;

        if (readFromDB)
        {
            dataToSend = getXMLDataFromDB();
        }
        else
        {
            dataToSend = getXMLData();
        }

        try{
            outp.writeInt(requestType);
            outp.writeInt(dataToSend.length);
            //outp.writeBytes(new String(dataToSend));
            outp.write(dataToSend,0,dataToSend.length);
            outp.flush();
            mainSession.send(SECURITY_ID,uid,byteStream.toByteArray());
        }catch(Exception e){
            NmsLogMgr.MISCERR.fail(NmsUtil.GetString("AuthSessionBE : Exception while sending data ") + e.getMessage(),null); 
        } finally {
            try {
                byteStream.close();
                outp.close();
            } catch ( Exception e ) {}
        }
    }

    byte[] getXMLDataFromDB()
    {
        Node rootNode =  dataFetcher.getRootNode();
        updateServerTime(rootNode);
        return getBytes(rootNode);
    }

    public byte[] getBytes(Node root)
    {
        byte[] retData = null;
        try
        {
            TransformerFactory fac = TransformerFactory.newInstance();
            Transformer trans = fac.newTransformer();
            DOMSource domSource = new DOMSource(root);

            java.io.Writer writer = new StringWriter();

            StreamResult streamResult = new StreamResult(writer);

            Properties prop = new Properties();
            try
            {
                prop.put(OutputKeys.ENCODING, sun.io.ByteToCharConverter.getDefault().getCharacterEncoding()); 
            }
            catch(Throwable e)
            {
                prop.put( OutputKeys.ENCODING, System.getProperty("file.encoding") );
            }
            prop.put(OutputKeys.METHOD, "xml" ); 
            trans.setOutputProperties(prop); 
            trans.transform(domSource, streamResult);

            retData = writer.toString().getBytes();

        }
        catch (Exception e)
        {
            System.err.println(NmsUtil.GetString("Exception in getBytes ") +e); 
        }
        return retData;
    }

    /**This method is used to get the byte[] stream.
     */ 

    byte[] getXMLData()
    {
        serverParser = new SecurityXMLParser();
        byte[] data = null;
        try{
            //serverParser.parseFile(filename);
            InputStream is = openFile(new File(filename));
            serverParser.parseInputStream(is);
            Node rootNode = serverParser.getRootNode();
            if ((rootNode == null) || (rootNode.getChildNodes() == null))
            {
                InputStream backupFileStream = openFile(new File(backupFileName));
                serverParser.parseInputStream(backupFileStream);
            }
            updateSysTime(is);
            data = serverParser.getBytes();
        }catch(Exception e){
            NmsLogMgr.MISCERR.fail(NmsUtil.GetString("AuthSessionBE : Exception while xml parsing ") + e.getMessage(),null); 
        }
        return data;
    }

    //This is for the audit..
    void sendDataToClientForAudit( int status, int requestType, byte[] dataToSend, String uid)
    {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        DataOutputStream outp = new DataOutputStream(byteStream);
        try{
            outp.writeInt(requestType);
            outp.writeInt(status);
            outp.writeInt(dataToSend.length);
            outp.write(dataToSend);
            outp.flush();
            mainSession.send(SECURITY_ID,uid,byteStream.toByteArray());
        }catch(Exception e){
            NmsLogMgr.MISCERR.fail(NmsUtil.GetString("AuthSessionBE : Exception while sending data ") + e.getMessage(),null); 
        } finally {
            try {
                byteStream.close();
                outp.close();
            } catch ( Exception e ) {
                e.printStackTrace();
            }
        }
    }

    void returnAuditData(int requestType)
    {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        DataOutputStream outp = new DataOutputStream(byteStream);
        byte[] dataToSend = getAuditData();
        try{
            outp.writeInt(requestType);
            outp.writeInt(dataToSend.length);
            outp.write(dataToSend);
            outp.flush();
            mainSession.send(SECURITY_ID,uid,byteStream.toByteArray());
        }catch(Exception e){
            NmsLogMgr.MISCERR.fail(NmsUtil.GetString("AuthSessionBE : Exception while sending data ") + e.getMessage(),null); 
        } finally {
            try {
                byteStream.close();
                outp.close();
            } catch ( Exception e ) {
                e.printStackTrace();
            }
        }
    }
    //This method is for the audit details.
    byte[] getAuditData()
    {
        serverParser = new SecurityXMLParser();
        byte[] data = null;
        try{
            //serverParser.parseFile(filename);
            //InputStream is = openFile(new File(filename));
            //serverParser.parseInputStream(is);
            //updateSysTime(is);
            //Vector vec = (Vector)auditAPI.getAuditTrails(null,null);
            Vector vec = new Vector();
            data = serverParser.getAuditBytes(vec);
        }catch(Exception e){
            NmsLogMgr.MISCERR.fail(NmsUtil.GetString("AuthSessionBE : Exception while getting audit details ") + e.getMessage(),null); 
        }
        return data;
    }

    // For CVScope 
    void returnAuthorizedViews ( int requestType ) 
    {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        DataOutputStream outp = new DataOutputStream(byteStream);
        byte[] dataToSend = getCVSAuthData();
        try{
            outp.writeInt(requestType);
            outp.writeInt(dataToSend.length);
            outp.write(dataToSend);
            outp.flush();
            mainSession.send(SECURITY_ID,uid,byteStream.toByteArray());
        }catch(Exception e){
            NmsLogMgr.MISCERR.fail(NmsUtil.GetString("AuthSessionBE : Exception while sending data ") + e.getMessage(),null); 
        } finally {
            try {
                outp.close();
                byteStream.close();
            } catch ( Exception e ) {
            }
        }
    }

    byte[] getCVSAuthData ()
    {
        serverParser = new SecurityXMLParser();
        Hashtable toReturn = new Hashtable(); 
        byte[] data = null;
        try {
            Vector allGroups = authAdmin.getAllGroups();
            for ( int i=0; i<allGroups.size(); i++ )
            {
                String group = (String) allGroups.elementAt(i);
                Vector views = authAdmin.getAllViewNames(group);

                Vector customViews = authAdmin.getAllCustomViewScope();
                Hashtable custom_auth = new Hashtable(); 

                for ( int j=0; j<customViews.size(); j++ ) {
                    String cView = (String) customViews.elementAt(j);
                    Vector authViews = authAdmin.getViewsForCVScope ( cView );
                    Vector finalVector = get_Common(views,authViews);
                    if ( finalVector.size() == 0 )
                    {
                        finalVector.addElement("NULL");
                    }
                    custom_auth.put ( cView, finalVector );	
                }
                toReturn.put(group,custom_auth);
            }
            data = serverParser.getByteForCustomAuth(toReturn);

        }catch(Exception e){
            e.printStackTrace();
            NmsLogMgr.MISCERR.fail(NmsUtil.GetString("AuthSessionBE : Exception while getting audit details ") + e.getMessage(),null); 
        }
        return data;
    }

    private Vector get_Common(Vector views, Vector authViews)
    {
        Vector toReturn = new Vector();
        for ( int i=0; i<views.size(); i++ )
        {
            String temp = (String) views.elementAt(i);
            if ( authViews.contains(temp) )
            {
                toReturn.addElement(temp);
            }
        }
        return toReturn;
    }

    boolean createCustomViewScope(byte[] userData) {

        String customView = null; 
        String groupName = null; 
        Vector authViews = null;

        serverParser = new SecurityXMLParser();
        Hashtable dataFromClient = serverParser.getCustomAuthView(userData);

        for ( Enumeration e = dataFromClient.keys(); e.hasMoreElements();) {
            String key = (String) e.nextElement();
            if ( key.equals("groupname") )
            {
                groupName = (String) dataFromClient.get(key);
            }
            else
            {
                customView = key;
                authViews = (Vector) dataFromClient.get(key);
            }
        }

        try {
            //authAdmin.removeCVScope ( customView );

            if ( (authViews.size() == 1) && 
                    (authViews.elementAt(0).equals("")) ||
                    (groupName.equals("")) )
            {
                return true;
            }
            //System.out.println("kumara : gn "+groupName+" cv "+customView+" auv "+authViews);
            Vector custom_Views = authAdmin.getViewsForCVScope(customView);
            authViews.removeAll(custom_Views);
            authAdmin.assignViewToCVScope ( customView, authViews );

        } catch ( Exception e ) {
            e.printStackTrace(); 
            errMessage="Error while creating Custom Views"; //No Internationalisation
            NmsLogMgr.MISCERR.fail(NmsUtil.GetString("AuthSessionBE : Exception while creating Custom Views : ") + e.getMessage(),null); 
            return false;				
        }

        return true;
    }

    Hashtable getAllGroupCustomAuthView ()
    {
        Hashtable toReturn = new Hashtable(); 
        try {
            Vector allGroups = authAdmin.getAllGroups();
            for ( int i=0; i<allGroups.size(); i++ )
            {
                String group = (String) allGroups.elementAt(i);
                Vector views = authAdmin.getAllViewNames(group);

                Vector customViews = authAdmin.getAllCustomViewScope();
                Hashtable custom_auth = new Hashtable(); 

                for ( int j=0; j<customViews.size(); j++ ) {
                    String cView = (String) customViews.elementAt(j);
                    Vector authViews = authAdmin.getViewsForCVScope ( cView );
                    Vector finalVector = get_Common(views,authViews);
                    if ( finalVector.size() == 0 )
                    {
                        finalVector.addElement("NULL");
                    }
                    custom_auth.put ( cView, finalVector );	
                }
                toReturn.put(group,custom_auth);
            }
            //data = serverParser.getByteForCustomAuth(toReturn);

        }catch(Exception e){
            e.printStackTrace();
            NmsLogMgr.MISCERR.fail(NmsUtil.GetString("AuthSessionBE : Exception while getting audit details ") + e.getMessage(),null); 
        }
        return toReturn;
    }

    boolean processUpdateAuthViews (byte[] userData) 
    {
        serverParser = new SecurityXMLParser();
        Hashtable dataFromClient = serverParser.getInfo_modifyAuthorizedViewForGroup(userData);
        String isDelete = (String)dataFromClient.get("isDelete"); 
        Vector newViews = (Vector)dataFromClient.get("new"); 
        String group = (String)dataFromClient.get("groupname"); 
        String cvsScope = (String) dataFromClient.get("cvs");
        try 
        {
            if ( isDelete.equals("TRUE") )
            {
                for ( int i=0; i<newViews.size(); i++ )
                {
                    String view = (String) newViews.elementAt(i);
                    authAdmin.removeAuthorizedView(view);
                }
            }
            else if ( isDelete.equals("FALSE") )
            {
                Vector authView_Group = authAdmin.getAllViewNames(group);
                Vector authView_CVS = authAdmin.getViewsForCVScope(cvsScope);
                Vector viewsForGroup = get_Common(authView_CVS,authView_Group);
                authAdmin.removeViewFromGroup(group,viewsForGroup);
                if (!(newViews.size() == 1 && newViews.elementAt(0).equals("")))
                {
                    authAdmin.assignViewToGroup(group,newViews);
                }
            }
        } catch ( Exception e ) {
            errMessage="Error while removing Authorized View"; //No Internationalisation
            NmsLogMgr.MISCERR.fail(NmsUtil.GetString("AuthSessionBE : Exception while removing Authorized View : ") + e.getMessage(),null); 
            return false;				
        }

        return true;
    }
    // For CVScope 


    private void updateSysTime(InputStream in) {

        try {
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();

            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();

            Node xmlNode = null;
            xmlNode = serverParser.getAuthNode("USER-CONF");
            NodeList nll = xmlNode.getChildNodes();
            int size1=nll.getLength();
            for( int j=0; j<size1; j++ ) {
                if(nll.item(j).getNodeType() == Node.ELEMENT_NODE) {
                    if((nll.item(j).getNodeName()).equals("DATA")) {
                        Element n = (Element) nll.item(j);
                        //n.setNodeType(Node.ELEMENT_NODE);
                        Long serverTime = new Long(System.currentTimeMillis());
                        n.setAttribute("systime",serverTime.toString());
                        //nwNode.addChildNode(n);
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
            //System.err.println(AdventNetResourceBundle.getInstance().getString("SecurityXMLParser : Exception in parseFile ") + e); 
        }
    }

    private void updateServerTime(Node rootNode)
    {
        Node userConfNode = null;
        NodeList nodeList = rootNode.getChildNodes();
        int nodeListLength = nodeList.getLength();

        for (int i = 0; i < nodeListLength; i++)
        {
            Node node = nodeList.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE)
            {
                if (node.getNodeName().equals("USER-CONF"))
                {
                    userConfNode = node;
                }
            }
        }
        updateUserConfNode(userConfNode);
    }

    private void updateUserConfNode(Node userConfNode)
    {
        NodeList userConfList = userConfNode.getChildNodes();
        int length = userConfList.getLength();

        for (int j = 0; j < length; j++)
        {
            Node node = userConfList.item(j);

            if (node.getNodeType() == Node.ELEMENT_NODE)
            {
                if (node.getNodeName().equals("DATA"))
                {
                    Element n = (Element) userConfList.item(j);
                    Long serverTime = new Long(System.currentTimeMillis());
                    n.setAttribute("systime", serverTime.toString());
                }
            }
        }
    }


    /**This method is used to add or modify or delete 
     * the child operation from the OperationsTreeRoot.
     */
    boolean processAddOrDeleteOperationTree(byte[] operData,int rety)
    {
        serverParser = new SecurityXMLParser();
        Vector operadd = serverParser.getUserToAddOper(operData);
        if(rety==ADD_OPERATIONS){
            try
            {
                int size = operadd.size();
                for(int i=0; i<size; i++)
                {
                    String parent=(String)operadd.elementAt(i);
                    String child=(String)operadd.elementAt(i+1);
                    if(parent.equals(""))
                        authAdmin.addOperation(child,null);
                    else
                        authAdmin.addOperation(child,parent);					
                    i=i+1;

                    //	authAdmin.addOperation(child,parent);
                    //	i=i+1;
                }
                return true;
            }
            catch(Exception e){
                errMessage="Error while adding operations. "+"\n"+"Could not add the operations. ";//No Internationalisation
                NmsLogMgr.MISCERR.fail(NmsUtil.GetString("AuthSessionBE : Exception while adding operations ") + e.getMessage(),null);
                return false;
            }
        }
        else if(rety==DELETE_OPERATIONS){
            try
            {
                int size = operadd.size();
                for(int j=0; j<size; j++)
                {
                    String parent=(String)operadd.elementAt(j);
                    String child=(String)operadd.elementAt(j+1);
                    authAdmin.removeOperation(child);
                    authAdmin.removeOperationFromView(child,null);
                    //authAdmin.removeOperation(child,parent);
                    j=j+1;
                }
                return true;
            }
            catch(Exception e){
                errMessage="Error while deleting operations. "+"\n" +"Could not delete the operations. ";//No Internationalisation
                NmsLogMgr.MISCERR.fail(NmsUtil.GetString("AuthSessionBE : Exception while deleting updating operations ") + e.getMessage(),null);
                return false;
            }
        }
        return false;
    }


    /**This method is used to add or modify or delete 
     * the user,group and password information.
     */
    boolean processAddOrModifyUserInfo(byte[] userData,int requesTye)
    {
        if(requesTye == ADD_USER_WITH_PROPS)
        {
            serverParser = new SecurityXMLParser();
            Vector useradd = serverParser.getUserInfoToAddUserOper(userData);
            Hashtable userAdd =(Hashtable)useradd.elementAt(0);

            Enumeration userEn = userAdd.keys();

            String userName =  (String)userEn.nextElement();
            Vector passwdGroupVec=(Vector)userAdd.get(userName);

            try{
                if(((String)passwdGroupVec.elementAt(0)).equals("")){//No Internationalisation
                    String group1=(String)passwdGroupVec.elementAt(1);
                    authAdmin.createUser(userName,group1);
                    copyFilesForUser(userName);
                }
                else{
                    String pass = (String)passwdGroupVec.elementAt(0);
                    String group1 = (String)passwdGroupVec.elementAt(1);
                    authAdmin.createUser(userName,group1,pass);
                    copyFilesForUser(userName);
                }
            }catch(Exception ae){
                errMessage="Error while creating users. "+"\n" +"Could not create the user "+userName;//No Internationalisation
                NmsLogMgr.MISCERR.fail(NmsUtil.GetString("AuthSessionBE : Exception while creating users ") + ae.getMessage(),null); 
                return false;
            }

            try
            {
                int size = passwdGroupVec.size();
                Vector groupNames = new Vector();
                
                for (int i = 2; i < size; i++)
                {
                    String group = (String)passwdGroupVec.elementAt(i);
                    groupNames.addElement(group);
                }
                if (groupNames.size() != 0)
                {
                    authAdmin.assignUserToGroup(userName, groupNames);
                }
                addOrModifyForUserOper(useradd);
            }
            catch (Exception ae)
            {
                errMessage="Error while assigning user to group. "+"\n" +"Could not assign user to groups. ";//No Internationalisation
                NmsLogMgr.MISCERR.fail(NmsUtil.GetString("AuthSessionBE : Exception while assigning user to group ") + ae.getMessage(),null); 
                return false;
            }
            return true;
        }
        else if(requesTye == MODIFY_USER_WITH_PROPS)
        {
            serverParser = new SecurityXMLParser();
            Vector usermod = serverParser.getUserInfoToAddUserOper(userData);
            Hashtable userModify =(Hashtable)usermod.elementAt(0);

            Enumeration userEn = userModify.keys();

            String userName =  (String)userEn.nextElement();
            Vector passwdGroupVec=(Vector)userModify.get(userName);

            try{
                int size = passwdGroupVec.size();
                String passwd = (String)passwdGroupVec.elementAt(0);
                if(!(passwd.equals(""))){
                    authAdmin.removeUser(userName);
                    String group = (String)passwdGroupVec.elementAt(1);
                    authAdmin.createUser(userName,group,passwd);
                    Vector groupNames = new Vector();
                    
                    for (int i = 2; i < size; i++)
                    {
                        String group1 = (String)passwdGroupVec.elementAt(i);
                        groupNames.addElement(group1);
                    }
                    
                    if (groupNames.size() != 0)
                    {
                        authAdmin.assignUserToGroup(userName, groupNames);
                    }
                }
                else
                {
                    // All GroupNames for the user assigned prevoiusly
                    Vector groupsVec = authAdmin.getAllGroupNames(userName);
                                        
                    // The Incoming list of groups, since the first element denotes the 
                    // password we can ignore the first element.
                    Vector groupNamesNew = new Vector(passwdGroupVec.subList(1,size));

                    // will give you the list of groups to be added for the users by  
                    // ignoring previously assigned groups.  
                    Vector groupNames1 = (Vector) groupNamesNew.clone(); 
                    groupNames1.removeAll(groupsVec);

                    // will give you the groups that needs to be removed for the user. 
                    groupsVec.removeAll(groupNamesNew);  
                    if (groupNames1.size() != 0) // This will reduce the extra check in the api and unnecessary notification.

                       authAdmin.assignUserToGroup(userName, groupNames1);
                    if (groupsVec.size() != 0) // This will reduce the extra check in the api and unnecessary notification.
                        authAdmin.removeUserFromGroup(userName, groupsVec);


                    if (groupsVec.size() != 0)
                    {
                        authAdmin.removeUserFromGroup(userName, groupsVec);
                    }

                    Vector groupNames = new Vector();
                    
                    for (int i = 1; i < size; i++)
                    {
                        String group = (String)passwdGroupVec.elementAt(i);
                        groupNames.addElement(group);
                    }
                    
                    if (groupNames.size() != 0)
                    {
                        authAdmin.assignUserToGroup(userName, groupNames);
                    }
                }

                if (usermod.size() > 1)
                {
                    if(usermod.elementAt(1) instanceof Vector)
                    {
                        if(!(passwdGroupVec.contains("default "+userName+" Group")))
                        {
                            authAdmin.assignUserToGroup(userName,"default "+userName+" Group");
                        }
                    }
                }
                addOrModifyForUserOper(usermod);
            }catch(Exception ae){
                errMessage="Error while modifying users. "+"\n" +"Could not modify the user information. ";//No Internationalisation
                NmsLogMgr.MISCERR.fail(NmsUtil.GetString("AuthSessionBE : Exception while modifying users. ") + ae.getMessage(),null); 
                return false;
            }
            return true;
        }
        else{
            return false;
        }
    }

    /******kumar*****/	
    boolean addToUserConfTable(byte[] userData, int reqType) {
        if(reqType == SET_ALL_ATTRIBUTES)
        {
            serverParser = new SecurityXMLParser();
            Hashtable table = serverParser.getUserInfoForUserConf(userData);
	    System.out.println("The list is =====>" + table.toString());
            String uName = (String) table.get("username");

            String statusForUser = null; 
            if ( table.get("status") != null )
                statusForUser = (String) table.get("status");

            String u = null;
            Integer uAge = null;
            if ( table.get("userage") != null ) {
                u = (String) table.get("userage");
                uAge = new Integer( u ); //(String) table.get("userage"));
            }

            String p = null;
            Integer pAge = null;
            if ( table.get("passwordage") != null ) {
                p = (String) table.get("passwordage");
                pAge = new Integer( p ); //(String) table.get("passage"));
            }

            try {
                if ( uAge != null )	
                    authAdmin.setUserExpirationTime((String)table.get("username"),uAge.intValue());
                if ( pAge != null )
                    authAdmin.setPasswordAge((String)table.get("username"),pAge.intValue());
                if ( statusForUser != null ) 
                {
                    if ( ! statusForUser.equals("") )
                    {
                        authAdmin.setUserStatus((String)table.get("username"),statusForUser);
                    }
                }
            } catch (Exception e ) {
                errMessage="Error while adding user information"; //No Internationalisation
                NmsLogMgr.MISCERR.fail(NmsUtil.GetString("AuthSessionBE : Exception while adding user information : ") + e.getMessage(),null); 
                return false;
            }
            return true;
        } else if ( reqType == SET_USER_EXPIRY) {
            serverParser = new SecurityXMLParser();
            Hashtable table = serverParser.getUserInfoForUserConf(userData);
            String uName = (String) table.get("username");
            //String statusForUser = (String) table.get("status");
            Integer uAge = new Integer( (String) table.get("userage"));
            //Integer pAge = new Integer( (String) table.get("passage"));
            try {	
                authAdmin.setUserExpirationTime((String)table.get("username"),uAge.intValue());
                //authAdmin.setPasswordAge((String)table.get("username"),pAge.intValue());
                //authAdmin.setUserStatus((String)table.get("username"),statusForUser);
            } catch (Exception e ) {
                errMessage="Error while adding user information"; //No Internationalisation
                NmsLogMgr.MISCERR.fail(NmsUtil.GetString("AuthSessionBE : Exception while adding user information : ") + e.getMessage(),null); 
                return false;
            }
            return true;

        } else if ( reqType == SET_PASSWORD_AGE) {
            serverParser = new SecurityXMLParser();
            Hashtable table = serverParser.getUserInfoForUserConf(userData);
            String uName = (String) table.get("username");
            //String statusForUser = (String) table.get("status");
            //Integer uAge = new Integer( (String) table.get("userage"));
            Integer pAge = new Integer( (String) table.get("passage"));
            try {	
                //authAdmin.setUserExpirationTime((String)table.get("username"),uAge.intValue());
                authAdmin.setPasswordAge((String)table.get("username"),pAge.intValue());
                //authAdmin.setUserStatus((String)table.get("username"),statusForUser);
            } catch (Exception e ) {
                errMessage="Error while adding user information"; //No Internationalisation
                NmsLogMgr.MISCERR.fail(NmsUtil.GetString("AuthSessionBE : Exception while adding user information : ") + e.getMessage(),null); 
                return false;
            }
            return true;

        } else if ( reqType == SET_USER_STATUS) {
            serverParser = new SecurityXMLParser();
            Hashtable table = serverParser.getUserInfoForUserConf(userData);
            String uName = (String) table.get("username");
            String statusForUser = (String) table.get("status");
            //Integer uAge = new Integer( (String) table.get("userage"));
            //Integer pAge = new Integer( (String) table.get("passage"));
            try {	
                //authAdmin.setUserExpirationTime((String)table.get("username"),uAge.intValue());
                //authAdmin.setPasswordAge((String)table.get("username"),pAge.intValue());
                authAdmin.setUserStatus((String)table.get("username"),statusForUser);
            } catch (Exception e ) {
                errMessage="Error while adding user information"; //No Internationalisation
                NmsLogMgr.MISCERR.fail(NmsUtil.GetString("AuthSessionBE : Exception while adding user information : ") + e.getMessage(),null); 
                return false;
            }
            return true;

        }
        return false;

    }



    /** For user to add operation directly for groups by creating the new group
     * and new view how is it.
     */
    private void addOrModifyForUserOper(Vector hashVec)
    {
        try
        {
            int size =hashVec.size();

            if (size > 1)
            {
                if (hashVec.elementAt(1) instanceof Vector)
                {
                    Vector newVec = (Vector)hashVec.elementAt(1);	

                    Hashtable group = (Hashtable)newVec.elementAt(0);
                    Enumeration en = group.keys();
                    String groupName = (String)en.nextElement();
                    String viewName = (String)group.get(groupName);
                    authAdmin.removeViewFromGroup(groupName,viewName);
                    authAdmin.assignViewToGroup(groupName,viewName);

                    Hashtable view = (Hashtable) newVec.elementAt(1);
                    Enumeration enu = view.elements();
                    Hashtable newOper = (Hashtable)enu.nextElement();

                    removeOperationsFromView(newOper, viewName);
                    assignOperationsToView(newOper, viewName);

                    if (size > 2)
                    {
                        for (int i = 2; i < size; i++)
                        {
                            Hashtable modoper = (Hashtable)hashVec.elementAt(i);
                            modifyView(modoper);
                        }
                    }
                }
                else
                {
                    for (int i = 1; i < size; i++)
                    {
                        Hashtable operMod = (Hashtable)hashVec.elementAt(i);
                        modifyView(operMod);
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private void assignOperationsToView(Hashtable operHash, String viewName) throws Exception
    {
        Vector includedOperations = new Vector();
        Vector excludedOperations = new Vector();

        for (Enumeration en = operHash.keys(); en.hasMoreElements();)
        {
            String operationName = (String) en.nextElement();
            String operationType = (String) operHash.get(operationName);
            if ( operationName.equals("") && operationType.equals("") )
                continue;
            if (operationType.equals("1"))
            {
                includedOperations.addElement(operationName);
            }
            else
            {
                excludedOperations.addElement(operationName);
            }
        }

        if (excludedOperations.size() != 0)
        {
            authAdmin.assignOperationToView(excludedOperations, viewName, true);
        }

        if (includedOperations.size() != 0)
        {
            authAdmin.assignOperationToView(includedOperations, viewName, false);
        }
    }

    private void removeOperationsFromView(Hashtable operHash, String viewName) throws Exception
    {
        Vector removeOperations = new Vector();

        for (Enumeration en = operHash.keys(); en.hasMoreElements();)
        {
            removeOperations.addElement((String) en.nextElement());
        }

        if (removeOperations.size() != 0)
        {
            authAdmin.removeOperationsFromView(removeOperations, viewName);
        }
    }   

    private void modifyView(Hashtable hash)throws Exception
    {
        for (Enumeration operEn = hash.keys();operEn.hasMoreElements();)
        {
            String viewName = (String)operEn.nextElement();
            Hashtable operHa = (Hashtable)hash.get(viewName);

            removeOperationsFromView(operHa, viewName);
            assignOperationsToView(operHa, viewName);
        }	
    }


    boolean processCreateScope (byte[] userData) {
        String groupName = null;
        String operationName = null;
        String viewName = null;
        Properties viewProp = null;
        String group_cvs = null;

        serverParser = new SecurityXMLParser();
        Vector dataFromClient = serverParser.getInfo_AddScope(userData);
        int size = dataFromClient.size();
        for ( int i=0; i<size; i++ ) {
            if ( dataFromClient.elementAt(i) instanceof String )
                groupName = (String) dataFromClient.elementAt(i);
            else if ( dataFromClient.elementAt(i) instanceof Vector ) {
                Vector tem = (Vector) dataFromClient.elementAt(i);
                if ( tem.size() != 0 ) 
                    operationName = (String)tem.elementAt(0);
            }
            else if ( (dataFromClient.elementAt(i) instanceof Properties) &&
                    ((Properties)dataFromClient.elementAt(i)).size() == 1 )
            {
                group_cvs = (String) ((Properties)dataFromClient.elementAt(i)).get("groupname");
            }
            else if ( dataFromClient.elementAt(i) instanceof Hashtable ) {
                Hashtable temp = (Hashtable) dataFromClient.elementAt(i);
                for ( Enumeration e = temp.keys(); e.hasMoreElements(); ) {
                    viewName = (String) e.nextElement();
                    viewProp = (Properties) temp.get(viewName);
                }
            }
        }
        try {
            if ( groupName.equals("") &&  operationName == null ) {
                return methodForCVScope(group_cvs,viewName, viewProp ); 
                //return authAdmin.modifyViewProperty ( viewName, viewProp );
            }
            if ( viewName == null ) 
                viewName = groupName+" "+operationName+" Scope";

            if ( viewProp == null || viewProp.size() == 0 ) {
                /* remove all properties */
                AuthorizedViewObject avo = new AuthorizedViewObject();
                avo.setAuthorizedViewName(viewName);
                avo.setViewProperties(null);
                //so that it will deletes all the properties associated with this view.
                authAdmin.removeAuthorizedView(avo);

                authAdmin.removeOperationFromView(operationName,groupName+" "+operationName+" Scope");
                authAdmin.assignOperationToView(operationName,"default "+groupName+" View");
                authAdmin.removeViewFromGroup(groupName,viewName);
                return true;
            }
        } catch ( Exception e ) {
            //e.printStackTrace();
            errMessage="Error while removing views"; //No Internationalisation
            NmsLogMgr.MISCERR.fail(NmsUtil.GetString("AuthSessionBE : Exception while assigning view to group : ") + e.getMessage(),null); 
            return false;				
        }

        try {
            authAdmin.removeOperationFromView(operationName,viewName);
            authAdmin.removeOperationFromView(operationName,"default "+groupName+" View");
            authAdmin.assignOperationToView(operationName,viewName,false);
        } catch ( Exception e ) {
            errMessage="Error while adding user information"; //No Internationalisation
            NmsLogMgr.MISCERR.fail(NmsUtil.GetString("AuthSessionBE : Exception while assigning operation to view : ") + e.getMessage(),null); 
            return false;				
        }

        try {
            authAdmin.removeViewFromGroup(groupName,viewName);
            authAdmin.assignViewToGroup(groupName,viewName);
        } catch ( Exception e ) {
            errMessage="Error while adding user information"; //No Internationalisation
            NmsLogMgr.MISCERR.fail(NmsUtil.GetString("AuthSessionBE : Exception while assigning view to group : ") + e.getMessage(),null); 
            return false;				
        }


        try {
            AuthorizedViewObject avo = new AuthorizedViewObject();
            avo.setAuthorizedViewName(viewName);
            avo.setViewProperties(null);
            //so that it will deletes all the properties associated with this view.
            authAdmin.removeAuthorizedView(avo);

            if ( viewProp != null ) {
                AuthorizedViewObject avo1 = new AuthorizedViewObject();
                avo1.setAuthorizedViewName(viewName);
                avo1.setViewProperties(viewProp);
                authAdmin.createAuthorizedView(avo1);
            }

        } catch ( Exception e ) {
            errMessage="Error while Creating Authorized View"; //No Internationalisation
            NmsLogMgr.MISCERR.fail(NmsUtil.GetString("AuthSessionBE : Exception while Creating Authorized View : ") + e.getMessage(),null); 
            return false;				
        }

        return true;

    }

    private boolean methodForCVScope( String group, String viewName, Properties viewProp ) {
        //System.out.println("kumara : methodForCVScope "+viewName+"-"+viewProp);    
        String groupName = group;
        try 
        {
            if ( ! groupName.equals("") )
            {
                Vector viewVector = authAdmin.getAllViewNames(groupName);
                if ( viewVector != null && !viewVector.contains(viewName) )
                {
                    authAdmin.assignViewToGroup (groupName,viewName);
                }
            }
            authAdmin.modifyViewProperty ( viewName, viewProp );
        } 
        catch ( Exception e )
        {
            errMessage="Error while Creating Authorized View"; //No Internationalisation
            NmsLogMgr.MISCERR.fail(NmsUtil.GetString("AuthSessionBE : Exception while Creating Authorized View : ") + e.getMessage(),null); 
            return false;
        }
        return true;
    }

    private boolean processModifyOperForUserInfo(byte[] userData) {
        String groupName = null;
        String userName = null;
        String operationName = null;
        String viewName = null;
        Properties oper = null;

        serverParser = new SecurityXMLParser();
        Hashtable clientInfo = serverParser.getInfo_ModifyOperForUser(userData);


        for ( Enumeration e = clientInfo.keys(); e.hasMoreElements(); ) {
            String type = (String) e.nextElement();
            if ( type.equals("username") ) 
                userName = (String)clientInfo.get(type);
            else if ( type.equals("groupname") )
                groupName = (String) clientInfo.get(type);
            else if ( type.equals("viewname") )
                viewName = (String) clientInfo.get(type);
            else if ( type.equals("operations") )
                oper = (Properties) clientInfo.get(type); 
        }

        try {	
            if ( userName != null && groupName != null ) {
                authAdmin.removeUserFromGroup( userName, groupName );
                authAdmin.assignUserToGroup( userName, groupName );
            }

            if ( viewName != null && groupName != null ) {
                authAdmin.removeViewFromGroup( groupName, viewName );
                authAdmin.assignViewToGroup( groupName, viewName );
            }

            if ( viewName != null && oper != null ) 
            {
                authAdmin.removeOperationFromView(null,viewName);
                assignOperationsToView(oper, viewName);

            }
        } catch ( Exception e ) {
            errMessage="Error while adding user information"; //No Internationalisation
            NmsLogMgr.MISCERR.fail(NmsUtil.GetString("AuthSessionBE : Exception while assigning user to group user : ") + e.getMessage(),null); 
            return false ;
        }
        return true ;
    }

    boolean processDeleteUser(byte[] userData){
        String userName = new String(userData);
        boolean resultOfOperation=false;
        try{
            resultOfOperation = authAdmin.removeUser(userName);
            if(resultOfOperation){
                authAdmin.removeViewFromGroup("default "+userName+" Group","default "+userName+" View");
                authAdmin.removeOperationFromView(null,"default "+userName+" View");
            }
        }catch(Exception ae){
            errMessage="Error while deleting users. " +"\n"+"Could not delete the user's information. ";//No Internationalisation
            NmsLogMgr.MISCERR.fail(NmsUtil.GetString("AuthSessionBE : Exception while deleting users: ") + ae.getMessage(),null); 
            return false;
        }
        if(resultOfOperation){
            try{
                // Deleting the users' directory 
                String nmshome = PureUtils.rootDir;
                String userDir = nmshome+File.separator+"users"+File.separator+userName;//No Internationalisation
                if((new File(userDir)).exists()) {
                    //checks if the checkbox was clicked 
                    deleteTheDir(userDir);
                }

                // Deleting the user specific information from the DB.
                Connection connection = NmsUtil.relapi.getConnection();
                DBXmlUtility utility = DBXmlUtility.getInstance(connection);
                utility.removeUserEntry(userName);
            }catch(Exception e){
                errMessage="Error while deleting users. " +"\n"+"Could not delete the user information. ";//No Internationalisation
                NmsLogMgr.MISCERR.fail(NmsUtil.GetString("AuthSessionBE : Exception while deleting users: ") + e.getMessage(),null); 
                return false;
            }
        }
        return true;
    }

    boolean processChangePassword (byte[] userdata) {
        serverParser = new SecurityXMLParser();
        Vector vector = serverParser.getUserPasswordVector(userdata);
        String username = (String) vector.elementAt(0);
        String newpassword = (String) vector.elementAt(1);
        String oldpassword = (String) vector.elementAt(2);
        boolean temp;
        try {	
            if ( oldpassword.equals("") ) 
                temp = authAdmin.changePassword(username,newpassword);
            else 
                temp = authAdmin.changePassword(username,oldpassword,newpassword);

            if ( !temp ) { 	
                errMessage="Error while changing Passoword"; //No Internationalisation
                NmsLogMgr.MISCERR.fail(NmsUtil.GetString("AuthSessionBE : Exception while change password for user : ") ,null); 
                return false;
            }

        } catch (Exception e ) {
            errMessage="Error while changing Password"; //No Internationalisation
            NmsLogMgr.MISCERR.fail(NmsUtil.GetString("AuthSessionBE : Exception while change password for user : ") + e.getMessage(),null); 
            return false;
        }
        return true;

    }

    boolean processAddOperForScope ( byte[] userData ) 
    {
        serverParser = new SecurityXMLParser();
        Hashtable dataFromClient = serverParser.getInfo_AddOperForScope(userData);
        String viewName = (String) dataFromClient.get("viewname");
        String groupName = (String) dataFromClient.get("groupname");
        String old_view = "default "+groupName+" View";
        Hashtable operTable = (Hashtable) dataFromClient.get("operTable");
        Hashtable propTable = (Hashtable) dataFromClient.get("propTable");

        try
        {
            removeOperationsFromView(operTable, old_view);
            assignOperationsToView(operTable, viewName);
        }
        catch (Exception e) 
        {
            errMessage="Error while adding user information"; //No Internationalisation
            NmsLogMgr.MISCERR.fail(NmsUtil.GetString("AuthSessionBE : Exception while assigning user to group user : ") + e.getMessage(),null); 
            return false;				
        }

    try {
        AuthorizedViewObject avo = new AuthorizedViewObject();
        avo.setAuthorizedViewName(viewName);
        avo.setViewProperties(null);
        //so that it will deletes all the properties associated with this view.
        authAdmin.removeAuthorizedView(avo);

        AuthorizedViewObject avo1 = new AuthorizedViewObject();
        avo1.setAuthorizedViewName(viewName);
        avo1.setViewProperties((Properties) propTable);
        authAdmin.createAuthorizedView(avo1);

    } catch ( Exception e ) {
        errMessage="Error while Creating Authorized View"; //No Internationalisation
        NmsLogMgr.MISCERR.fail(NmsUtil.GetString("AuthSessionBE : Exception while Creating Authorized View : ") + e.getMessage(),null); 
        return false;				
    }

    return true;
    }

    boolean processAddUsers( byte[] userData) {
        serverParser = new SecurityXMLParser();
        Hashtable dataFromClient = serverParser.getDataForAddUsers(userData);

        for ( Enumeration en = dataFromClient.keys() ; en.hasMoreElements(); ) {
            String groupName = (String) en.nextElement();
            Vector users = (Vector) dataFromClient.get(groupName);

            try {
                Vector old_users = authAdmin.getUsers(groupName);

                if ( old_users != null && old_users.size() != 0 ) {
                    
                    authAdmin.removeUserFromGroup(old_users,groupName);
                    
                }

                if ( ( users.size() == 1 ) && 
                        ( (String)users.elementAt(0) ).equals("") )
                    break;
                    
                if ((users != null) && (users.size() != 0))
                {
                    authAdmin.assignUserToGroup( users, groupName);
                }
                
            } catch ( Exception e ) {
                errMessage="Error while adding user information"; //No Internationalisation
                NmsLogMgr.MISCERR.fail(NmsUtil.GetString("AuthSessionBE : Exception while assigning user to group user : ") + e.getMessage(),null); 
                return false;				
            }
        }
        return true;
    }

    boolean processModifyOperForGroupInfo(byte[] userData) {
        serverParser = new SecurityXMLParser();
        String groupName = null;
        String viewName = null;
        Hashtable operHash = null;
        Hashtable dataFromClient = serverParser.getDataForAddOperGroup(userData);
        for ( Enumeration en = dataFromClient.keys() ; en.hasMoreElements(); ) {
            String type = (String) en.nextElement();
            if ( type.equals("groupname") )
                groupName = (String)dataFromClient.get(type);
            else if ( type.equals("viewname") )
                viewName = (String)dataFromClient.get(type);
            else if ( type.equals("operations") )
                operHash = (Hashtable)dataFromClient.get(type);
        }

        try { 

            //--
            Vector views = authAdmin.getAllViewNames(groupName);
            for ( int i=0; i<views.size(); i++ ) 
            {
                String tempViewName = (String) views.elementAt(i);
                // Since it deletes all the views, the views that are 
                // associated with NamedView will get deleted.
                // now check for deleting authorizedview that are not 
                // associated with Namedviews is made.
                Vector v = authAdmin.getCVScopeForView(tempViewName);
                if (v.size() == 0)
                {
                    authAdmin.removeViewFromGroup(groupName, tempViewName);
                }
            }
            //--

            authAdmin.assignViewToGroup(groupName, viewName);

            if ( operHash != null ) {
                authAdmin.removeOperationFromView(null, viewName);

                assignOperationsToView(operHash, viewName);
            }
            else if (operHash == null) {
                authAdmin.removeOperationFromView(null, viewName);
            }
        } catch ( Exception e ) {
            errMessage="Error while adding view and operations. " +"\n"+"Could not add view and the operations. ";//No Internationalisation
            NmsLogMgr.MISCERR.fail(NmsUtil.GetString("AuthSessionBE : Exception while adding view and operations: ") + e.getMessage(),null); 
            return false;
        }
        return true;

    }

    boolean processAddOperGroup(byte[] userData) {
        serverParser = new SecurityXMLParser();
        String groupName = null;
        String viewName = null;
        Hashtable operHash = null;
        Hashtable dataFromClient = serverParser.getDataForAddOperGroup(userData);
        for ( Enumeration en = dataFromClient.keys() ; en.hasMoreElements(); ) {
            String type = (String) en.nextElement();
            if ( type.equals("groupname") )
                groupName = (String)dataFromClient.get(type);
            else if ( type.equals("viewname") )
                viewName = (String)dataFromClient.get(type);
            else if ( type.equals("operations") )
                operHash = (Hashtable)dataFromClient.get(type);
        }

        try { 

            /*
             * Vector views = authAdmin.getAllViewNames(groupName);
             * for ( int i=0; i<views.size(); i++ ) {
             * 	String tempViewName = (String) views.elementAt(i);
             * 	if ( ! tempViewName.startsWith("default ") ) {
             * 		authAdmin.removeViewFromGroup(groupName, tempViewName);
             * 	}
             * }
             */

            //authAdmin.removeViewFromGroup(groupName, viewName);
            Vector allGroups = authAdmin.getAllGroups();
            for ( Enumeration e = allGroups.elements(); e.hasMoreElements(); ) {
                String gn = (String) e.nextElement();
                if ( gn.equalsIgnoreCase(groupName) ) {
                    errMessage="Error while adding Group.Group already present.";//No Internationalisation
                    //NmsLogMgr.MISCERR.fail(NmsUtil.GetString("AuthSessionBE : Exception while adding Group. ")); 
                    return false;
                }
            }
            authAdmin.assignViewToGroup(groupName, viewName);

            if ( operHash != null ) 
            {
                authAdmin.removeOperationFromView(null, viewName);
                assignOperationsToView(operHash, viewName);
            }
            else if (operHash == null) {
                authAdmin.removeOperationFromView(null, viewName);
            }
        } catch ( Exception e ) {
            errMessage="Error while adding view and operations. " +"\n"+"Could not add view and the operations. ";//No Internationalisation
            NmsLogMgr.MISCERR.fail(NmsUtil.GetString("AuthSessionBE : Exception while adding view and operations: ") + e.getMessage(),null); 
            return false;
        }
        return true;
    }


    boolean processDeleteGroup(byte[] groupData){
        String groupName = new String(groupData); 
        try{
            Vector viewNames = authAdmin.getAllViewNames(groupName);
        /*This fix is to remove the group specific Authorized scopes when the group is deleted
         * (i.e)Once we modify an operation after the Scope is deleted, the Scope is dissociated
         * from the group. After that there is no explicit option to remove the view. It exists
         * even after deleting the group. Fix is before getting all the views for the group,
         * the authorized views specific for that group (<group> ... < Scope>also got deleted */
         Vector avs = authAdmin.getAllAuthorizedViews();  //returns all the Authorized & custom                          viewscope names irrespective of group
         for(int j=0; j < avs.size() ; j++)
         {
         String av = (String)avs.elementAt(j);
         if(av.startsWith(groupName + " ") && av.endsWith(" Scope"))
         {
         viewNames.add(avs.elementAt(j));//Will add in the vector, Authorized viewscope names 				 created for the group
         }
         }//Here the viewNames vector contains all the ViewNames associated for the group
         /*Here the fix ends! Venkatramanan*/	    

            for (int i = 0; i < viewNames.size(); i++)
            {
                String viewName = (String)viewNames.elementAt(i);

                if ( viewName.equals("default "+groupName+" View") ||
                        ( viewName.startsWith(groupName)&&viewName.endsWith(" Scope")) ) {
                    AuthorizedViewObject avo = new AuthorizedViewObject();
                    avo.setAuthorizedViewName(viewName);
                    avo.setViewProperties(null);
                    authAdmin.removeAuthorizedView(avo); //-kumara-
                    //authAdmin.removeOperationFromView(null,"default "+groupName+" View");
                    authAdmin.removeOperationFromView(null,viewName);
                }
            }
            authAdmin.removeViewFromGroup(groupName, viewNames);

            //Since groupName is the domain.So need to delete all
            //its dependencies.
            //-kumara-
            Vector users = authAdmin.getUsers(groupName);	
            if ((users != null) && (users.size() != 0))
            {
                authAdmin.removeUserFromGroup(users, groupName);
            }
            

        }catch(Exception ae){
            errMessage="Error while deleting groups. " +"\n" +"Could not delete the group "+groupName; //No Internationalisation
            NmsLogMgr.MISCERR.fail(NmsUtil.GetString("AuthSessionBE : Exception while deleting groups: ") + ae.getMessage(),null); 
            return false;
        }
        return  true;
    }

    //For the Audit Trails.
    boolean processDeleteAudit(byte[] userData){
        String userName = new String(userData); 
        try{
            if ( userName.equals(""))
            {
                auditAPI.clearAudit(null,null);
            }
            else
            {
                auditAPI.clearAudit(userName,null);
            }

        }catch(Exception ae){
            errMessage="Error while deleting audit details. " +"\n" +"Could not delete the audit details "+userName; //No Internationalisation
            NmsLogMgr.MISCERR.fail(NmsUtil.GetString("AuthSessionBE : Exception while deleting audit details: ") + ae.getMessage(),null); 
            return false;
        }
        return  true;
    }




    /**This method is used to add or modify or delete 
     * the group and view information.
     */
    boolean processAddOrModifyGroupViewInfo(byte[] groupData,int reqTye)
    {
        if(reqTye == ADD_GROUP_VIEWS)
        {
            serverParser = new SecurityXMLParser();
            Hashtable groupAdd = serverParser.getUserInfoToAdd(groupData);

            Enumeration groupEn = groupAdd.keys();


            String groupName =  (String)groupEn.nextElement();
            Vector groupViewVec=(Vector)groupAdd.get(groupName);


            try{
                authAdmin.assignViewToGroup(groupName, groupViewVec);
            }catch(Exception ae){
                errMessage="Error while assigning group to view. " +"\n"+"Could not assign the groups to view. "; //No Internationalisation
                NmsLogMgr.MISCERR.fail(NmsUtil.GetString("AuthSessionBE : Exception while assigning group to view: ") + ae.getMessage(),null); 
                return false;
            }
            return true;
        }
        else if(reqTye == MODIFY_GROUP_VIEWS)
        {
            serverParser = new SecurityXMLParser();
            Hashtable groupModify = serverParser.getUserInfoToAdd(groupData);

            Enumeration groupEn = groupModify.keys();


            String groupName =  (String)groupEn.nextElement();
            Vector groupViewVec=(Vector)groupModify.get(groupName);

            try{
                Vector viewsVec = authAdmin.getAllViewNames(groupName);
                authAdmin.removeViewFromGroup(groupName, viewsVec);
                authAdmin.assignViewToGroup(groupName, groupViewVec);
            }catch(Exception ae){
                errMessage="Error while modifying groups and views. " +"\n"+"Could not modify the groups and views. "; //No Internationalisation
                NmsLogMgr.MISCERR.fail(NmsUtil.GetString("AuthSessionBE : Exception while modifying groups and view: ") + ae.getMessage(),null); 
                return false;
            }
            return true;
        }
        else{
            return false;
        }
    }

    /**This method is used to add or modify or delete 
     * the view,properties and operations information.
     */
    boolean processAddOrModifyOrDeleteAuthViewOperInfo(byte[] authViewData,int retye)
    {
        if(retye == ADD_AUTH_VIEWS_WITH_OPER)
        {
            serverParser = new SecurityXMLParser();
            Hashtable addAuthView = serverParser.getUserInfoToAdd(authViewData);
            Enumeration viewEn=addAuthView.keys();
            Enumeration propOperEn=addAuthView.elements();

            String viewName=(String)viewEn.nextElement();
            AuthorizedViewObject authViewObj=new AuthorizedViewObject();
            authViewObj.setAuthorizedViewName(viewName);
            Vector propOperVec=(Vector)propOperEn.nextElement();
            Properties property= new Properties();
            try{
                Hashtable oper=(Hashtable)propOperVec.elementAt(0);
                if(propOperVec.size() == 2)
                {
                    Hashtable prop=(Hashtable)propOperVec.elementAt(1);
                    for(Enumeration propna=prop.keys();propna.hasMoreElements();){
                        String propname =(String)propna.nextElement();
                        String propval = (String)prop.get(propname);
                        property.put(propname,propval);
                    }
                    authViewObj.setViewProperties(property);
                    authAdmin.createAuthorizedView(authViewObj);
                    assignOperationsToView(oper, viewName);
                }
                else
                {
                    assignOperationsToView(oper, viewName);
                }
            }catch(Exception ae){
                errMessage="Error while adding view and operations. " +"\n"+"Could not add view and the operations. ";//No Internationalisation
                NmsLogMgr.MISCERR.fail(NmsUtil.GetString("AuthSessionBE : Exception while adding view and operations: ") + ae.getMessage(),null); 
                return false;
            }
            return true;
        }
        else if(retye==MODIFY_AUTH_VIEWS_WITH_OPER)
        {
            serverParser = new SecurityXMLParser();
            Hashtable addAuthView = serverParser.getUserInfoToAdd(authViewData);
            Enumeration viewEn=addAuthView.keys();
            Enumeration propOperEn=addAuthView.elements();

            String viewName=(String)viewEn.nextElement();
            AuthorizedViewObject authViewObj=new AuthorizedViewObject();
            authViewObj.setAuthorizedViewName(viewName);
            Vector propOperVec=(Vector)propOperEn.nextElement();
            Properties property= new Properties();
            try{
                Hashtable oper=(Hashtable)propOperVec.elementAt(0);
                if(propOperVec.size() == 2)
                {
                    authAdmin.removeAuthorizedView(authViewObj);
                    Hashtable prop=(Hashtable)propOperVec.elementAt(1);
                    for(Enumeration propna=prop.keys();propna.hasMoreElements();){
                        String propname =(String)propna.nextElement();
                        String propval = (String)prop.get(propname);
                        property.put(propname,propval);
                    }
                    authViewObj.setViewProperties(property);
                    authAdmin.createAuthorizedView(authViewObj);
                    authAdmin.removeOperationFromView(null,viewName);
                    assignOperationsToView(oper, viewName);
                }
                else
                {
                    authAdmin.removeAuthorizedView(authViewObj);
                    authAdmin.removeOperationFromView(null,viewName);
                    assignOperationsToView(oper, viewName);
                }
            }catch(Exception ae){
                errMessage="Error while modifying view and operations. " +"\n"+"Could not modify view and operations. ";//No Internationalisation
                NmsLogMgr.MISCERR.fail(NmsUtil.GetString("AuthSessionBE : Exception while modifying view and operations: ") + ae.getMessage(),null);
                ae.printStackTrace();
                return false;
            }
            return true;
        }
        else if(retye==DELETE_AUTH_VIEWS_WITH_OPER)
        {
            serverParser = new SecurityXMLParser();
            Hashtable addAuthView = serverParser.getUserInfoToAdd(authViewData);
            Enumeration viewEn=addAuthView.keys();
            Enumeration propOperEn=addAuthView.elements();

            String viewName=(String)viewEn.nextElement();
            AuthorizedViewObject authViewObj=new AuthorizedViewObject();
            authViewObj.setAuthorizedViewName(viewName);
            Vector propOperVec=(Vector)propOperEn.nextElement();
            try{
                InputStream is = openFile(new File(filename));
                serverParser.parseInputStream(is);
                Node rootNode = serverParser.getRootNode();
                if ((rootNode == null) || (rootNode.getChildNodes() == null))
                {
                    InputStream backupFileStream = openFile(new File(backupFileName));
                    serverParser.parseInputStream(backupFileStream);
                }
                Vector delViewGroup = serverParser.getGroupsForView(viewName); 

                authAdmin.removeAuthorizedView(authViewObj);
                Hashtable oper=(Hashtable)propOperVec.elementAt(0);
                removeOperationsFromView(oper, viewName);

                int size = delViewGroup.size();
                for(int i=0;i<size;i++){
                    String group = (String)delViewGroup.elementAt(i);
                    authAdmin.removeViewFromGroup(group,viewName);
                }
            }catch(Exception ae){
                errMessage="Error while deleting view and operations. " +"\n"+"Could not delete the view and operations. ";//No Internationalisation
                NmsLogMgr.MISCERR.fail(NmsUtil.GetString("AuthSessionBE : Exception while deletingview and operations: ") + ae.getMessage(),null); 
                return false;
            }
            return true;
        }
        else{
            return false;
        }
    }


    /**This method is used to add or modify or delete 
     * the view and properties information.
     */
    boolean processAddOrModifyOrDeleteAuthViewInfo(byte[] authViewData,int reqwTre)
    {
        if(reqwTre == ADD_AUTH_VIEWS)
        {
            serverParser = new SecurityXMLParser();
            Hashtable addAuthView = serverParser.getUserInfoToAdd(authViewData);
            Enumeration viewEn=addAuthView.keys();
            Enumeration propOperEn=addAuthView.elements();

            String viewName=(String)viewEn.nextElement();
            AuthorizedViewObject authViewObj=new AuthorizedViewObject();
            authViewObj.setAuthorizedViewName(viewName);
            Vector propOperVec=(Vector)propOperEn.nextElement();
            Properties property= new Properties();
            try{
                Properties prop=(Properties)propOperVec.elementAt(0);
                for(Enumeration propna=prop.keys();propna.hasMoreElements();){
                    String propname =(String)propna.nextElement();
                    String propval = (String)prop.get(propname);
                    property.put(propname,propval);
                }
                authViewObj.setViewProperties(property);
                authAdmin.createAuthorizedView(authViewObj);
                //authViewObj.setViewProperties(prop);
                //authAdmin.createAuthorizedView(authViewObj);
            }catch(Exception ae){
                errMessage="Error while adding view and properties. " +"\n"+"Could not add view and the properties. ";//No Internationalisation
                NmsLogMgr.MISCERR.fail(NmsUtil.GetString("AuthSessionBE : Exception while adding view and properties: ") + ae.getMessage(),null); 
                return false;
            }
            return true;
        }

        else if(reqwTre == DELETE_AUTHVIEWS)
        {
            serverParser = new SecurityXMLParser();
            Hashtable addAuthView = serverParser.getUserInfoToAdd(authViewData);
            Enumeration viewEn=addAuthView.keys();
            Enumeration propOperEn=addAuthView.elements();

            String viewName=(String)viewEn.nextElement();
            AuthorizedViewObject authViewObj=new AuthorizedViewObject();
            authViewObj.setAuthorizedViewName(viewName);
            Vector propOperVec=(Vector)propOperEn.nextElement();
            Vector vec=new Vector();
            try{
                //Hashtable prop=(Hashtable)propOperVec.elementAt(0);
                authAdmin.removeAuthorizedView(authViewObj);
            }catch(Exception ae){
                errMessage="Error while deleting view and properties. " +"\n"+"Could not delete view and the properties. " ;//No Internationalisation
                NmsLogMgr.MISCERR.fail(NmsUtil.GetString("AuthSessionBE : Exception while deletingview and properties: ") + ae.getMessage(),null); 
                return false;
            }
            return true;
        }
        else if(reqwTre == MODIFY_AUTH_VIEWS)	
        {
            serverParser = new SecurityXMLParser();
            Hashtable modifyAuthView = serverParser.getUserInfoToAdd(authViewData);

            Enumeration viewEn=modifyAuthView.keys();
            Enumeration propOperEn=modifyAuthView.elements();

            String viewName=(String)viewEn.nextElement();
            AuthorizedViewObject authViewObj=new AuthorizedViewObject();
            authViewObj.setAuthorizedViewName(viewName);
            Vector propOperVec=(Vector)propOperEn.nextElement();
            Properties property= new Properties();
            try{
                Properties prop=(Properties)propOperVec.elementAt(0);
                //authViewObj.setViewProperties(prop);
                authAdmin.removeAuthorizedView(authViewObj);
                authAdmin.removeOperationFromView(null,viewName);
                for(Enumeration propna=prop.keys();propna.hasMoreElements();){
                    String propname =(String)propna.nextElement();
                    String propval = (String)prop.get(propname);
                    property.put(propname,propval);
                }
                authViewObj.setViewProperties(property);
                authAdmin.createAuthorizedView(authViewObj);
            }catch(Exception ae){
                errMessage="Error while modifying view and properties. " +"\n"+"Could not modify view and the properties. ";//No Internationalisation
                NmsLogMgr.MISCERR.fail(NmsUtil.GetString("AuthSessionBE : Exception while modifying and properties: ") + ae.getMessage(),null); 
                return false;
            }
            return true;
        }
        else{
            return false;
        }
    }


    //Method for saving the audit contents in to the file..

    private boolean saveToFile(byte[] saveStream)
    {
        serverParser = new SecurityXMLParser();
        Hashtable infoHash = serverParser.getAudit(saveStream);
        String filename = (String)infoHash.get("filename");
        String audit = (String)infoHash.get("audittrail");
        filename = new String(PureUtils.rootDir  +filename);
        //No Internationalisation
        File f = new File(filename);
        if (f.exists())
        { 
            if (!f.canWrite())
            { 
                NmsLogMgr.MISCERR.fail(NmsUtil.GetString("AuthSessionBE :  Cannot open file for writing:")+filename ,null); 
                errMessage="Cannot open file for writing.";
                return false;
            }
        }
        StringTokenizer token = new StringTokenizer(audit,"#",false);
        FileWriter fileWriter = null;
        try
        {
            fileWriter = new FileWriter(f);
        }
        catch(IOException io)
        {
            NmsLogMgr.MISCERR.fail(NmsUtil.GetString("AuthSessionBE :  Cannot open file for writing:")+filename ,null); 
            errMessage="Cannot open file for writing.";
            return false;
        }
        while(token.hasMoreElements())
        {
            String newString = token.nextToken().toString();
            try
            {
                fileWriter.write(newString);
                fileWriter.write(System.getProperty("line.separator"));
                fileWriter.flush();
            }
            catch(IOException ie)
            {
                NmsLogMgr.MISCERR.fail(NmsUtil.GetString("AuthSessionBE :  Error writing to: ")+filename +":"+ ie.getMessage(),null); 
                errMessage="Error while writing  to: "+filename;
                return false;
            }
        }
        return true;
    }


    public void close(){}

    private void sendDataToClient(int status, int reqType, byte[] msg, String uid) throws IOException {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        DataOutputStream outp = new DataOutputStream(byteStream);
        try {
            //byte[] err = msg.getBytes();
            outp.writeInt(reqType);
            outp.writeInt(status);
            outp.writeInt(msg.length);
            //outp.writeBytes(new String(msg));
            outp.write(msg,0,msg.length);
            outp.flush();
            mainSession.send(SECURITY_ID,uid,byteStream.toByteArray());
        } finally {
            byteStream.close();
            outp.close();
        }
    }


    public static InputStream openFile(File path) throws IOException
    {
        InputStream ret = null;

        if (System.getProperty("JavaWebStart") != null)//No Internationalisation
        {
            System.out.println(NmsUtil.GetString("Java Web Start mode: ") + path);

            String filename = path.getName();

            ret = CommonUtil.class.getClassLoader().getResourceAsStream(filename);
        }
        else
        {
            ret = new FileInputStream(path);
        }

        return ret;
    }


    void copyFilesForUser(String userName) {

        boolean result = false;

        String nmshome = PureUtils.rootDir;
        String destName = nmshome+File.separator+"users"+File.separator+userName;//No Internationalisation
        String srcName=nmshome+File.separator+"html"+File.separator+"defaultsToNewUsers";//No Internationalisation
        copyDirectory(srcName , destName);
        if(true)
        {
            System.out.println("CopyFilesForUser operation is over for user "+userName);
            return;
        }
        // Following Lines of code will copy only one level of files from the
        // users dir.
        File destFile= new File(destName);
        File srcFile= new File(srcName);
        if (!destFile.exists())
        {
            destFile.mkdir();
        }
        GenericBEAPI beAPI = null;
        //String beHostName = "localhost";//No Internationalisation
        //int beRmiRegPort = 1099;
        String rootDir = destName;
        try
        {
            //beRmiRegPort=PureServerUtilsFE.be_registry_port;
            //beHostName=PureServerUtilsFE.be_host;
            rootDir =rootDir+ File.separator;
            String srcRootDir =srcName+File.separator;
            beAPI =  GenericBEAPIImpl.getInstance();//No Internationalisation
            String[] listOfFiles = srcFile.list();
            int len=listOfFiles.length;
            for (int j=0; j<listOfFiles.length; j++) {
                File f = new File(srcRootDir+listOfFiles[j]);
                if (f.isDirectory()) 
                {
                    File destdir = new File(rootDir+f.getName());
                    String dirName=f.getName();
                    dirName =dirName+ File.separator;
                    if (!destdir.exists())
                    {
                        destdir.mkdir();
                    }
                    String[] list = f.list();
                    for (int k=0;k<list.length;k++)
                        list[k]=srcRootDir+dirName+list[k];
                    FileData[] fd = beAPI.getFileData(list);
                    for (int i = 0; i < fd.length; i++)
                    {
                        FileData theFile = fd[i];
                        File temp = new File(theFile.getFileName());
                        String fileName= temp.getName();
                        FileOutputStream fos = new FileOutputStream(rootDir +dirName+ fileName);
                        byte[] barr = theFile.getFileContents();
                        fos.write(barr);
                        fos.close();
                    }
                }
                else if (f.isFile())
                {
                    FileData fd = beAPI.getFileData(srcRootDir+f.getName());
                    File temp = new File(fd.getFileName());
                    String fileName= temp.getName();
                    FileOutputStream fos = new FileOutputStream(rootDir + fileName);
                    byte[] barr = fd.getFileContents();
                    fos.write(barr);
                    fos.close();
                }

            }

            //return true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            //return false;
        }
    }
    private void copyDirectory(String srcDir , String destDir)
    {
        File destFile= new File(destDir);
        File srcFile= new File(srcDir);
        if (!destFile.exists())
        {
            destFile.mkdir();
        }
        try
        {
            String srcRootDir =srcDir+File.separator;
            String destRootDir =destDir+File.separator;
            String[] listOfFiles = srcFile.list();
            for (int j=0; j<listOfFiles.length; j++) 
            {
                File f = new File(srcRootDir+listOfFiles[j]);
                if (f.isDirectory()) 
                {
                    copyDirectory(srcRootDir+listOfFiles[j] , destRootDir+listOfFiles[j]);
                }
                else if (f.isFile())
                {
                    copyFile(srcRootDir+listOfFiles[j] , destRootDir+listOfFiles[j]);
                }

            }

            //return true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            //return false;
        }

    }
    private void copyFile(String srcName , String destName)
    {
        GenericBEAPI beAPI = null;
        try
        {
            String srcRootDir =srcName+File.separator;
            beAPI =  GenericBEAPIImpl.getInstance();//No Internationalisation
            FileData theFile = beAPI.getFileData(srcName);
            FileOutputStream fos = new FileOutputStream(destName);
            byte[] barr = theFile.getFileContents();
            fos.write(barr);
            fos.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            //return false;
        }
    }

    private void  deleteTheDir(String path) {

        File delDirs  = new File(path);
        String[] listOfFiles = delDirs.list();
        int length = listOfFiles.length;
        for (int i = 0 ; i < length ; i++) {
            String innerFilePath = path+ File.separator +listOfFiles[i];
            File innerFile       = new File(innerFilePath);
            if(innerFile.isDirectory()) {
                String innermostList[] = innerFile.list();
                int len = innermostList.length;
                for(int j = 0 ; j < len ; j++) {
                    String innerMostFilePath = innerFilePath  + File.separator + innermostList[j];
                    File innermostFile       = new File(innerMostFilePath);
                    if ( innermostFile.isDirectory() )
                    {
                        deleteTheDir (innerMostFilePath);
                    }
                    innermostFile.delete();
                }
            }
            innerFile.delete();
        }
        delDirs.delete();
    }
}

