package test;

import java.io.*;
import java.lang.*;
import java.sql.*;
import java.util.*;
import java.net.*;
public class GetBytes
{
   public static void main(String args[])
   {
	   //String m1="sudhakar.adventnet.india.com192.168.111.4516500";
	   try{
	   String m1=InetAddress.getLocalHost().getHostName()+InetAddress.getLocalHost().getHostAddress()+"16500";
	   System.out.println(">>"+m1.getBytes());
	   }
	   catch (Exception e)
	   {
		   e.printStackTrace();
	   }
   }
	   
}

