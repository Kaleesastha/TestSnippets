package test;

import java.io.*;
import java.util.*;
import java.net.URL;
import com.adventnet.nms.db.util.DBXmlTool;


public class DBXmlAllUsers
{
        static private Vector getAllUsers()
        {   
                String userDir = System.getProperty("webnms.rootdir"); //returns the current directory
                String absolutePath = userDir+File.separator+"users";

                Vector v = new Vector();
                File file = new File(absolutePath);
                if(file.exists())
                {   
                        String [] array = file.list();
                        String value = null;
                        for(int i=0;i<array.length;i++)
                        {   
                                File temp = new File(absolutePath+File.separator+array[i]);
                                if(temp.isDirectory())
                                {   

                                        value = array[i];
                                        v.add(value);

                                }   
                        }   
                }   
                else
                {   
                        System.out.println("Exiting....");
                        System.exit(0);
                }   
                return v;
        }   
        public static void main(String args[])
        {   
                DBXmlAllUsers dbx;
                Vector result = new Vector();
                String option=args[0];
                String user = args[1];
                if(user.equalsIgnoreCase("all"))
                {   
                        dbx = new DBXmlAllUsers();
                        result=dbx.getAllUsers();
                }   
                else
                {   
                        result.addElement(args[1]);
                }   
                System.out.println(option+" called for users : "+result);
                DBXmlTool dbTool = new DBXmlTool(option,result);
                dbTool.runTool();
                System.exit(0);
        }   
}
