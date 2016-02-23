package jdbc;
import java.io.*;
import java.lang.*;
import java.sql.*;
import com.adventnet.nms.util.*;
import jdbc.*;

public class CreateTables
{ 
   public static void main(String args[])
   {
	   CreateSchema cschema = null;
	   try{

		   cschema=new CreateSchema();
		   cschema.init(new String[]{"NMS_HOME",PureUtils.rootDir});
	   }
	   catch(Exception e){e.printStackTrace();}
	   if(cschema!=null)
	   {
		   cschema.closeAllConnections();
	   }
   }
}
