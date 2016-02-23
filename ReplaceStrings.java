import java.io.*;
import java.lang.*;
import java.sql.*;
import java.util.*;
public class ReplaceStrings
{
   public static void main(String args[])
   {
	   try{
		   String a = "team\\";
		   System.err.println("replaceAll string is "+a.replaceAll("\\","\\\\"));
			System.err.println("InternalUtil replaceAll string is::"+com.adventnet.nms.util.InternalUtil.replaceAll(a,"\\","\\\\"));
	      }
	   catch(Exception e)
	   {e.printStackTrace();}
   }
}

