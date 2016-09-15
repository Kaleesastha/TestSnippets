package net.sourceforge.jradiusclient;

import java.sql.*;
import java.util.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.lang.*;
import java.net.*;

public class TestWritetoUser
{
URL url=null;
File fileName = null;

public TestWritetoUser()
{
}

private static String readFile(String fileName)
  {
                String content = "";//No Internationalisation
                try
                {
                        //File  f  = new File("/usr/local/etc/raddb/users");//No Internationalisation
                        File  f  = new File(fileName);//No Internationalisation
			URL url = f.toURL();
                        InputStream istream = url.openStream();
                        if(istream != null)
                        {
                                BufferedReader br = new BufferedReader(new InputStreamReader(istream));
				//System.out.println(br.readLine());
                                StringBuffer sb = new StringBuffer();
                                String temp;
                                try
                                {
                                        while ((temp = br.readLine()) != null)
					{ sb.append(temp+"\n");//No Internationalisation
					System.out.println(temp);
					}
                                }
                                catch (Exception e)
                                {
				e.printStackTrace();

                                }
                                content = sb.toString();
                        }
			
                }
                catch(Exception exc)
                {
                       exc.printStackTrace(); 
                }
                return content;
 }

/*private void updateFile(String name, String password)
{
        try
        {
	    System.out.println("INTO UPDATE FILE ");
            //Vector comVect = getCompleteLinesInVector(fileName);
            //Vector comVect = readFile(fileName);
            //Vector vect = getCommandLinesInVector(file);
            FileWriter writter = new FileWriter("/usr/local/etc/raddb/users");
            BufferedWriter  bw = new BufferedWriter(writter);
            PrintWriter pw = new PrintWriter(bw);
		System.out.println("Name  " + name + "password    " + password);
		bw.newLine();
                bw.write(name + "\n");
		bw.write(password);
                bw.newLine();
            pw.flush();
            pw.close();
            bw.close();
        }
	
        catch(Exception error)
        {error.printStackTrace();
	}
	
    }

*/
public void updateUserEntry(File users,String[] attrib){

BufferedWriter  bw = null;
Vector comVect = null;
        try
        {
	    boolean isAddNewEntry=true;
	    String newEntry="dummy";
            comVect = getCompleteLinesInVector(users);
            //ctor vect = getCommandLinesInVector(file);
            FileWriter writter = new FileWriter(users);
            bw = new BufferedWriter(writter);
            PrintWriter pw = new PrintWriter(bw);
	    bw.newLine();
            if(isAddNewEntry){
		try{
                comVect.addElement(attrib[0] + " Auth-Type:=" + attrib[1] + ", " + "User-Password  == " + '"' + attrib[2] + '"');
		comVect.addElement(" Service-Type = " + attrib[3] + ",");
		comVect.addElement(" Login-IP-Host = " + attrib[4] + ",");
		}catch(Exception e)
		{
		e.printStackTrace();
		System.out.println("Usage UserName Auth-Type PassWord Service-Type Login-IP-Host");
		System.exit(1);
		}
            } else {

                String oldLine = comVect.toString();
                int pos = comVect.indexOf(oldLine);
                comVect.set(pos,newEntry);
            }

            while( comVect.size() > 0)
            {
                bw.write((String)comVect.remove(0));
                bw.newLine();
            }
            pw.flush();
            pw.close();
            bw.close();
	System.out.println("User added successfully");
        }
        catch(Exception error)
	{
	System.err.println("$#33443344");
	error.printStackTrace();
	}
	}

public void updateClientEntry(File client,String[] attrib1){

BufferedWriter  bw = null;
Vector comVect = null;
        try
        {
	    System.out.println("#############################");
            boolean isAddNewEntry=true;
            String newEntry="dummy";
            comVect = getCompleteLinesInVector(client);
            //ctor vect = getCommandLinesInVector(file);
            FileWriter writter = new FileWriter(client);
            bw = new BufferedWriter(writter);
            PrintWriter pw = new PrintWriter(bw);
            bw.newLine();
            if(isAddNewEntry){
                try{
                comVect.addElement("client " + attrib1[0] + "{" );
                comVect.addElement("secret = " + attrib1[1] );
                comVect.addElement("shortname = " + attrib1[2]);
                comVect.addElement("}");
		
                }catch(Exception e)
                {
                e.printStackTrace();
                System.out.println("Usage UserName Auth-Type PassWord Service-Type Login-IP-Host");
                System.exit(1);
                }
            } else {

                String oldLine = comVect.toString();
                int pos = comVect.indexOf(oldLine);
                comVect.set(pos,newEntry);
            }

            while( comVect.size() > 0)
            {
                bw.write((String)comVect.remove(0));
                bw.newLine();
            }
            pw.flush();
            pw.close();
            bw.close();
        System.out.println("User added successfully");
        }
        catch(Exception error)
        {
        System.err.println("$#33443344");
	}
}
private  Vector getCompleteLinesInVector(File file )throws Exception
    {
        if(!file.exists())
        {
            System.out.println("File does not exists " + file);
            return null;
        }

        Vector tmpvect=new Vector();
        FileInputStream fin = null;
        BufferedReader din=null;

        try
        {
            fin= new FileInputStream(file);
            din = new BufferedReader(new InputStreamReader(fin));
            String line=null;

            while ((line = din.readLine()) != null)
            {
                tmpvect.addElement(line);

            }
        }
        catch(Exception er)
        {
            throw er;
        }
        finally
        {
            try
            {
                fin.close();
            }catch(Exception e){}
            try
            {
                din.close();
            }catch(Exception d){}
        }
        return tmpvect;
    }



public static void main(String [] args)
    {
	try{
	System.out.println("Main Called ");
	TestWritetoUser twu = new TestWritetoUser();	
	//twu.readFile(args[0]);
	File fl = new File("/usr/local/etc/raddb/users");
	File fl1 = new File("/usr/local/etc/raddb/clients.conf");
	String[] args1={args[0],args[1],args[2],args[3],args[4]};
	String[] args2={args[5],args[6],args[7]};
	twu.updateUserEntry(fl,args1);
	twu.updateClientEntry(fl1,args2);
	System.out.println("User added successfully");
	}catch(Exception e)
	{
	e.printStackTrace();
	System.out.println("Usage UserName Auth-Type (Eg:Local,System etc)  PassWord Service-Type (Eg:Callback-Login-User,Framed-User) Login-IP-Host");
	}

}

}
