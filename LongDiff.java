package test;

import java.io.*;
import java.lang.*;
import java.sql.*;
import java.util.*;
public class LongDiff
{
   public static void main(String args[])
   {
	   long diff = 9428616-508782548;
	   if (diff <0)
	   {
		   /*String msg = collinfo.getInfoMessage(mpd.getKey());
		   if(msg!=null)
		   {
			   collinfo.setInfoMessage(mpd, msg + " , " + key + instance);
		   }
		   else
		   {
			   collinfo.setInfoMessage(mpd, "Counter is reset for " + key + instance);
		   }*/
		   //diff = (9428616 + 0xFFFFFFFFL - 508782548)+1;
		   diff = (508782548 + 0xFFFFFFFFL - 9428616)+1;
	   System.err.println (" Actual diff is:"+diff);
	   }

	   if ((diff < 0) || (diff > 0xFFFFFFFFL))
	   {
		   diff = 0;
	   }
	   System.err.println (" diff is:"+diff);
   }
}
