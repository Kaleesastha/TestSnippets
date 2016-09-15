<HTML>
<HEAD>
<meta http-equiv="Pragma" content="no-cache">
<TITLE>PROVISION RESULT CUSTOM VIEW</TITLE>
<!---------------------This file is AF_14_PRRESTCV.jsp------- -->
</HEAD>
<BODY>

<%@page  import="java.util.*" %>
<%@page  import="java.io.*" %>
<%@page  import="java.net.*" %> 
<%@page  import="javax.servlet.*" %>      
<%@page  import="javax.servlet.http.*" %>     

<%@page  import="com.adventnet.nms.fe.provisioning.ProvisionResultFE" %>
<%@page  import="com.adventnet.nms.fe.provisioning.ProvisionResultSessionBean" %>
<%@page  import="com.adventnet.nms.fe.common.*" %>
<%@page  import="com.adventnet.nms.util.*" %>
<%@page  import="test.*" %>

<style>
p {color: blue}
</style>
<p>
<%
String tcn = request.getParameter("testnumber");
if (tcn ==null)
{

	out.println("Sorry, required parameters missing");
	return;

}
String userName = "root";
String prviewid = "ProvisionResult";
CustomSessionBean csb=null;
try
{

	csb = ProvisionResultFE.getProvisionResultSessionBean();

}
catch(ModuleNotInitializedException e)
{

	System.out.println("Module ProvisionResultFE is not yet initialized");

}
ViewCriteria criteria=null;

try
{
	prov_rslt_cv_XmlReader xm=new prov_rslt_cv_XmlReader("jsp/prov_rslt_cv_Testinput.xml");
	Properties crprops,setprops,getdat,getprops=null;
	setprops=xm.getprops(tcn,"setproperties");
	crprops=xm.getprops(tcn,"criteriaproperties");
	getdat=xm.getprops(tcn,"getdata");
	getprops=xm.getprops(tcn,"getprops");	
	Properties panelprops = new Properties();
	TableColumn[] tc=null; 
	tc = new TableColumn[0];
	Properties pcrit = new Properties();
	if(tcn.equals("tc003") || tcn.equals("tc036") || tcn.equals("tc038") || tcn.equals("tc050") )
	{
		tc=new TableColumn[4];
		tc[0]= new TableColumn("prId", "PRID", 50);
		tc[1]= new TableColumn("type", "Type", 50);
		tc[2]= new TableColumn("templateName", "TemplateName", 100);
		tc[3]= new TableColumn("scheduledTime", "Scheduled Time", 50);
		pcrit.put("type","SIMPLE");

	}

	boolean bs=false;
	if (!tcn.equals("tc001"))
		bs=csb.createCustomView(userName ,prviewid,prviewid,"DEVICE",tc,panelprops,pcrit);
	else
		prviewid=csb.createCustomView(userName,prviewid,"DEVICE",tc,panelprops,pcrit);

	out.println("The custom session bean ViewId of user \t"+userName+ " is \t" +prviewid );
	String username=userName;
	String uname=userName;
	String name=userName;
	String viewid=prviewid;
	String vid=prviewid;

	if (getdat!=null)
	{
		username=getdat.getProperty("username");	
		viewid=getdat.getProperty("viewid");
	}
	if(username.equals("null"))
	{
		criteria = new ViewCriteria(null,viewid);
	}
	else if(viewid.equals("null"))
	{  criteria = new ViewCriteria(username,null);
	}
	else 
	{ 
		criteria = new ViewCriteria(username,viewid);
	}

	if (setprops!=null)
	{
		if((setprops.getProperty("setTemporaryCustomView")).equalsIgnoreCase("true"))
		{
			criteria.setTemporaryCustomView((setprops.getProperty("setTemporaryCustomView")).equalsIgnoreCase("true"));
		}
		else criteria.setTemporaryCustomView(false);
		criteria.setViewLength(Integer.parseInt(setprops.getProperty("setViewLength")));
		criteria.setFromIndex(Integer.parseInt(setprops.getProperty("setFromIndex")));
		criteria.setAscendingOrder((setprops.getProperty("setAscendingOrder")).equalsIgnoreCase("true"));
		criteria.setPerformOR((setprops.getProperty("setPerformOR")).equalsIgnoreCase("true"));
		if (setprops.getProperty("setOrderbyColumn")!= "null")
		{
			criteria.setOrderbyColumn(setprops.getProperty("setOrderbyColumn"));
		}
		else
		{
			criteria.setOrderbyColumn(null);
		}
	}

	if (crprops!=null)
	{
		criteria.setCriteria(crprops);
	}
	else
	{
		criteria.setCriteria(null);
	}
	ViewData vd = csb.getData(criteria);
	Vector vec=   vd.getData();
	if (getprops!=null)
	{
		uname=getprops.getProperty("uname");
		vid=getprops.getProperty("vid");
		name=getprops.getProperty("name");
	}

	if (tcn.equals("tc048") )
	{
		out.println("The total number of Objects are    "+csb.getTotalCount(username,viewid));
	}


	Hashtable ht =null;
	ht= csb.getAllViewIDsAndProps(uname);
	if(ht.isEmpty() )
	{
	%>
	<br>
	<%
	out.println("Empty Hashtable for this user\t" + uname);
	return;
	}
	Enumeration enumid= ht.keys();
	Enumeration enumprop= ht.elements();
	TableColumn[] etc=null;
	for (int l=0;enumid.hasMoreElements();l++)
	{
		String vids=(String)enumid.nextElement();
		CustomViewProperties crpropall= (CustomViewProperties)enumprop.nextElement();
		etc=crpropall.getTableColumns();
	}
	CustomViewProperties cvprops=null;
	if(name.equals("null"))
	{
		cvprops = csb.getViewProperties(null,vid);
	}
	else if(vid.equals("null"))
	{  	cvprops = csb.getViewProperties(name,null);
	}
	else 
	{ 
		cvprops = csb.getViewProperties(name,vid);
	}
	%>
	<br>
	<%
	out.println("The criteria properties obtained from  getViewProperties method are  \t ");
	Properties crprop=cvprops.getCriteriaProperties();
	if(crprop == null )
	{
		out.println("Empty criteria properties for this user \t");
	}
	out.println(crprop.toString());
	%>
	<br>
	<%
	out.println("The table columns obtained from getAllViewIDsAndProps method are \t  ");

	for (int f=0;(etc!=null)&&(f<etc.length);f++)
	{
	%>
	<br>
	<%
		out.println(etc[f].toString());
	}
	%>

		<!--<b>Get Obj. vecotrOFvd :   </b> <% %> -->
		<table border="1" >
		<tr  align="center" bgcolor="grey">
		<td width="10%" ><%="PRID" %></td>
		<td width="10%" ><%="TYPE" %></td>
		<td width="10%" ><%="TEMPLATENAME" %></td>
		<td width="10%"  ><%="SCHEDULEDTIME" %></td>
		<td width="10%" ><%="STARTTIME" %></td>
		<td width="10%" ><%="DURATION" %></td>
		<td width="10%" ><%="USERNAME" %></td>
		<td width="10%" ><%="STATUS" %></td>
		<td width="10%" ><%="DETAILS" %></td>
		<td width="10%" ><%="PROGRESS" %></td>

		</tr>

	<%
	Properties prop=new  Properties();
	for (int i=0;i<vec.size();i++) 
	{

		prop=(Properties)vec.elementAt(i);
		%>
			<tr  align="center" bgcolor="white">
			<td ><%=prop.getProperty("prId")%></td>
			<td ><%=prop.getProperty("type")%></td>
			<td ><%=prop.getProperty("templateName")%></td>
			<td ><%=prop.getProperty("scheduledTime")%></td>
			<td ><%=prop.getProperty("startTime")%></td>
			<td ><%=prop.getProperty("duration")%></td>
			<td ><%=prop.getProperty("userName")%></td>
			<td ><%=prop.getProperty("status")%></td>
			<td ><%=prop.getProperty("details")%></td>
			<td ><%=prop.getProperty("progress")%></td>
			</tr>
			<%
	}
	%>
		</table>
	<%
	boolean mcv=false;
	boolean rcv=false;
	if(tcn.equals("tc049") || tcn.equals("tc050"))
	{
		mcv=csb.modifyCustomView("root",prviewid,tc,panelprops,pcrit);
		cvprops = csb.getViewProperties(name,vid);
	%>
	<br>
	<%out.println("\nthe custom view has been modified\t"+mcv);%>
	<br>
	<%
		crprop=cvprops.getCriteriaProperties();
		if(crprop == null )
		{
			out.println("Empty criteria properties for this user\t");
		}
		out.println("The criteria properties of a modified custom view criteria are "+ crprop.toString());
	}
	if(tcn.equals("tc051"))
	{
		rcv=csb.renameCustomView("root",prviewid,"prresult1");

	%>
	<br>
	<%
	out.println("\nthe custom view has been renamed\t"+rcv);
	}
	%>
	<br>
	</p>
	<%
}
catch(Exception ex)
{
	%>
	<br>
	<style>
	h4 {color: blue}
	</style>
	<table height="100%" align ="center" >
	<tr>
	<td >
	<h4>
	<% 
	ex.printStackTrace();
	out.println("Exception: \t"+ex.getMessage());
	%>
	</h4>
	</td>
	</tr>
	</table>
	<%

}
finally
{
	csb.removeCustomView(userName,prviewid);
}
%>

</BODY>
</HTML>  

