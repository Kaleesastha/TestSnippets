<%@ page import="java.io.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.rmi.*" %>
<%@ page import="com.adventnet.nms.util.PureServerUtilsFE" %>
<%@ page import="com.adventnet.security.authentication.AuthenticationAPI"%>
<%@ page import="com.adventnet.security.authentication.AuthenticationException"%>
<%@ page import="com.adventnet.security.authentication.AuthenticationTicket"%>
<%@ page import="com.adventnet.security.AuthUtil"%>
<%@ page import="com.adventnet.nms.util.NmsUtil"%>
<%

String key=null;
String userName="root";
String password="public"; 
String challenge = "";
boolean secondStatus=false;
String regAPIName = "NmsAuthenticationAPI";
boolean status=PureServerUtilsFE.isPasswordCorrect(userName,password);
out.println("The isPasswordCorrect() STATUS: " +status);
AuthenticationAPI authenAPI = null;
authenAPI = (AuthenticationAPI)NmsUtil.getAPI(regAPIName);
try
{
    if (authenAPI != null)
        {
            challenge = authenAPI.getChallenge(userName);
        }
    else
        {
            out.println("AuthenticationAPI is null!! Cannot validate the password");
        }
}
catch (AuthenticationException e)
{
   
}
catch (RemoteException re)
{
out.println("Exception in getting the challenge");
}
try
{
    key    = AuthUtil.getChallengeKey(userName,password,challenge);
}
catch (AuthenticationException e)
{
    out.println("Exception in verifying the password");
}
 secondStatus=PureServerUtilsFE.authenticateUser(userName,key);

out.println("<p>  The authenticateUser()*** STATUS: "+secondStatus);
%>









