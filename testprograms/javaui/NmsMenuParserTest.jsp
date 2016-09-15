 <%
         
    <%@ page import=" javax.xml.parsers.DocumentBuilderFactory" %>
    <%@ page import= "javax.xml.parsers.DocumentBuilder" %>
    <%@ page import= "org.w3c.dom.Element"%>
    <%@ page import= "com.adventnet.nms.util.NmsUtil" %>
    <%@ page import= "com.adventnet.nms.fe.utils.*"%>

 <%

    	try
	  {
           NmsMenuFileParser nmfp = new NmsMenuFileParser();
           
	   //For test case CATS-JCF-API-MENU-007 
	   //Element result=nmfp.getRootElement("root","filemenu","list",null,null);

	   //For test case CATS-JCF-API-MENU-009
      	   //Element result=nmfp.getRootElement("root","filemenu",null,null,null);

	   //For test case CATS-JCF-API-MENU-010
	   //Element result=nmfp.getRootElement("root","filemenu","map",null,null);

	   //For test case CATS-JCF-API-MENU-011
	   //Element result=nmfp.getRootElement("root","filemenu","list",null,null);

	   //For test case CATS-JCF-API-MENU-012
	   //Element result=nmfp.getRootElement("root","filemenu","policy",null,null);

    System.out.println( "<xmp>" + result + "</xmp>");
    out.println( "<xmp>" + result + "</xmp>");
          }
	catch(Exception e)
          { 
	   System.err.println("Error occured createcustomview");
           e.printStackTrace();
          }
 %> 
