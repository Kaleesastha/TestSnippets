/*
  $Id: MenuFileParser.java,v 1.4 2008/09/05 18:26:10 govardhini Exp $
 */
package com.adventnet.nms.servlets;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.Properties;
import org.w3c.dom.*;
import javax.xml.transform.stream.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import com.adventnet.nms.util.*;
import java.util.*;
import com.adventnet.nms.fe.utils.*;



public class MenuFileParser extends HttpServlet 
{

    
    /**Provides Information string.
     */
    public String getServletInfo() 
    {
        return "This servlet returns an Element to construct a tree";
    }
    /**
     * Handle POST the same as GET.
     * This method is simply a call to doGet().
     *
     * @param req encapsulates the request to the servlet
     * @param res encapsulates the response from the servlet
     * @see javax.servlet.http.HttpServletRequest#getPathTranslated
     * @exception ServletException will be passed on from included servlets
     * @see #doGet
     */
   
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
    {
        doGet(req, res);
    }
    /**doGet() is the overridden method of HttpServlet.
     * 
     * @param req HttpServletRequest object. 
     * @param res HttpServletResponse object.
     * 
     * @return void.
     * 
     * @exception IOException produced by failed or interrupted I/O operations.
     */
    public synchronized void doGet(HttpServletRequest req, HttpServletResponse res)
        throws ServletException, IOException 
    {	
        PrintWriter out=null;
        String userName = req.getParameter("userName");
        String menuFilename = req.getParameter("menuFilename");
        String type = req.getParameter("menuType");
        String client =req.getParameter("client");
        String operationType=req.getParameter("ACTION-ON-NO-PRIVILEGE");
        String checkForFile=req.getParameter("checkForFile");
        try{
            boolean parseFile=true;
            if (checkForFile!=null && checkForFile.equals("TRUE"))
                parseFile=false;
            res.setStatus( HttpServletResponse.SC_OK );
            res.setContentType( "text/html" );
            NmsMenuFileParser menuParser= new NmsMenuFileParser();
            if (menuFilename==null)
                menuFilename="null";
            if (userName==null)
                userName="null";
            String url = getFilePath(menuFilename,userName,type);
            Element root=null;
            out = res.getWriter();
            File file=new File(url);
            if (parseFile)
                {
                    if(file.exists())
                        {
							root = menuParser.getRootElement(userName,menuFilename,type,operationType,client);
							StringWriter writer = new StringWriter();
							try
							{
								TransformerFactory fac = TransformerFactory.newInstance();
								Transformer trans = fac.newTransformer();
								trans.setOutputProperty("indent","yes");//No I18N
								trans.setOutputProperty("encoding","UTF-8");//No I18N
								DOMSource domSource = new DOMSource(root);
								StreamResult streamResult = new StreamResult(writer);
								trans.transform(domSource, streamResult);
							}
							catch(Exception e1)
							{
								e1.printStackTrace();
							}
                            if (root != null)
                                {
                                    out.println("Parse Successful");
                                    String toString=writer.toString();
                                    if(toString!=null && (toString.indexOf("null")==-1))
                                        {
                                            out.println(toString);
                                        }
                                    else
                                        {
                                            StringBuffer sb = new StringBuffer();
                                            String ress =getElementString(root,sb).toString();
                                            out.println(ress);
                                        }
                                }
                            else
                                {
                                    out.println("Parse Failed");
                                    System.err.println(" Root node is null in MenuFileParser for "+menuFilename);
                                }
                        }
                    else
                        {
                            //System.err.println("File "+url+"Doesn't Exist with parameters as username :"+userName+" File : "+menuFilename+"type : "+type+" type : "+operationType+" client : "+client);
                            out.println("File Not Found");
                            
                        }
			
                }
            else
                {
                    if(file.exists())
                        {
                            out.println("File Found");
                        }
                    else
                        out.println("File Not Found");   

                }
        }
        catch(IOException e)
            {
                e.printStackTrace();
                throw e;
            }
        catch(Exception e)
            {
                e.printStackTrace();
            }

        out.flush();
        out.close();
    }
    private String getFilePath(String menuName,String userName, String type)throws IOException
    {
        String url=null;
        File file=null;
        if (type==null)
            {
		url=PureUtils.usersDir+"users"+File.separator+userName+File.separator+menuName+".xml";

                try
                    {
                        file=new File(url);
                        if(!file.exists())
                            {
								url=PureUtils.rootDir+"html"+File.separator+"defaultsToAllUsers"+File.separator+menuName+".xml";
                            }
                    }catch(Exception e)
			{
                            e.printStackTrace();	
			}
                return url;
            }
        else if (type.equals("map"))
            {
		url=PureUtils.usersDir+"users"+File.separator+userName+File.separator+"mapmenus"+File.separator+menuName+".xml"; //No I18N

                try
                    {
                        file=new File(url);
                        if(!file.exists())
                            {
                                url=PureUtils.usersDir+"mapdata"+File.separator+"menus"+File.separator+menuName+".xml";
                            }
                    }catch(Exception e)
			{
                            e.printStackTrace();	
			}
                return url;

            }
        else if (type.equals("list"))
            {
		url=PureUtils.usersDir+"users"+File.separator+userName+File.separator+"listmenus"+File.separator+menuName+".xml"; //No I18N

                try
                    {
                        file=new File(url);
                        if(!file.exists())
                            {
                                url=PureUtils.usersDir+"listmenus"+File.separator+menuName+".xml";

                            }
                    }catch(Exception e)
			{
                            e.printStackTrace();	
			}
                return url;

            }
        else if (type.equals("policy"))
            {
		url=PureUtils.usersDir+"users"+File.separator+userName+File.separator+"policymenus"+File.separator+menuName+".xml"; //No I18N

                try
                    {
                        file=new File(url);
			 if(!file.exists())
			 {
			 	url=PureUtils.rootDir+"html"+File.separator+"defaultsToAllUsers"+File.separator+"policymenus"+File.separator+menuName+".xml"; //No I18N
			 }
			
			return url;
				   
                    }catch(Exception e)
			{
                            e.printStackTrace();	
			}
			  
            }
        return url;
    }
  

    private StringBuffer  getElementString(Element menu,StringBuffer sb) 
    {
        // Hashtable attr = new Hashtable();
        //        Node nodeName = childProp.getNamedItem("name");

        if(menu==null)
        {
            return sb;
        }
        String nodeType =menu.getNodeName();
        sb.append("<");
        sb.append(nodeType);
        NamedNodeMap childProp = menu.getAttributes();
        for (int i=0; i < childProp.getLength();i++)
        {
            Node list = childProp.item(i);
            sb.append(" ");
            sb.append(list.getNodeName());
            sb.append(" ");
            sb.append("=");
            sb.append(" ");
            sb.append("\"");
            String nodeValue=list.getNodeValue();
            Properties replaceWith = new Properties();
            replaceWith.put("<","&lt;");
            replaceWith.put(">","&gt;");
            replaceWith.put("&","&amp;");
            replaceWith.put("\'","&apos;");
            replaceWith.put("\"","&quot;");
            Enumeration en = replaceWith.keys();
            boolean changed=false;
            String toReturn="";
            while (en.hasMoreElements())
            {
                String repToken =(String) en.nextElement();
                if (nodeValue.indexOf(repToken)!=-1)
                {
                    String replace=(String)replaceWith.get(repToken);
                    String token;
                    StringTokenizer st = new StringTokenizer(nodeValue,repToken,true);
                    while(st.hasMoreTokens())
                    {
                        token = st.nextToken();
                        if(token.equals(repToken))
                        {
                            toReturn = toReturn + replace;
                        }
                        else
                        {
                            toReturn = toReturn + token;
                        }
                    }
                    nodeValue=toReturn;
                }
                                              
            }
            sb.append(nodeValue);

            sb.append("\"");
            sb.append(" ");
           
        }
        if (!menu.hasChildNodes())
            sb.append("/>"); 
                   
        else
        {
            sb.append(">");
            NodeList  itemList = menu.getChildNodes();
            for( int j=0;j<itemList.getLength();j++)
            {
                Node temp=itemList.item(j);
                if (temp.getNodeType()!=temp.ELEMENT_NODE)
                {
                    (temp.getParentNode()).removeChild(temp);   
                    j--;
                    continue;
                }
                Element menuitem = (Element)itemList.item(j);
                if (menuitem ==null)
                {
                    continue;    
                }
                getElementString(menuitem,sb);
                        
            }
            sb.append(" ");
            sb.append("</");
            sb.append(nodeType);
            sb.append(">");
                                
        }

        return sb;   
                
    }
           

}
