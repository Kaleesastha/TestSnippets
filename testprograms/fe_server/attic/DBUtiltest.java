package com.adventnet.nms.store;

import java.sql.*;
import com.adventnet.nms.util.PureUtils;

public class DBUtiltest
{
	public static void main(String args[])throws Exception
	{
		 PureUtils.rootDir="/home/rajeshk/be-server/AdventNet/WebNMS/";
		 DBUtil db=new DBUtil();
		 Connection con=db.getData();
		 String sql="create table TestTable(no varchar(20))";
		 PreparedStatement ps=con.prepareStatement(sql);
		 db.executeTheStatement(ps);
		 ps.close();
		 con.close();
	}
}

