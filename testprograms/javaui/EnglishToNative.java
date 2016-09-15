import java.io.*;
import java.util.*;
import java.net.*;



/**
 *This file is to get EnglishToNative.txt. Give a equivalent string on the
 *right hand side of || in each line.
 *Append with chinese char by default.
 *put the current file in the /backup dir.
 *The current file is replaced with the modified file
 *Useful for i18n.
 */
public class EnglishToNative {

	static String sourceFile = null;
	static String appendString = null;
	
	public static void main(String args[]) 
	{
		if(args.length < 1)
		{
			System.out.println("USAGE : java EnglishToNative sourceFile [StringToAppend]");
			return;
		}	
	
		//get the arguments.
		sourceFile = args[0];
		System.out.println("The source file : "+sourceFile);
		if(args.length>1)
			appendString=args[1];
		else 
			appendString="append";
		EnglishToNative en = new EnglishToNative();

	}
	
	EnglishToNative()
	{		

		try{
			parseAndAdd();
		}catch(Exception e){
			e.printStackTrace();
		}
	}


	private void parseAndAdd() throws IOException
	{

		//get the writers	
		PrintWriter writer;
		BufferedReader r;
		
		//get the reading file
		File source = new File(sourceFile);
		System.out.println("get Absolute path "+source.getAbsolutePath());
		FileReader reader =new FileReader(source);
        r = new BufferedReader(reader);
		
		//copy this file to the back up dir.

		//get the backup dir
		File backupDir = 	new File((new File(source.getAbsolutePath())).getParent()+"/backup/");
		if(!backupDir.mkdir())
		{
			System.out.println("Unable to create backup Dir "+backupDir);
			System.out.println("The file might be present or the program was unable to create it");
		}	
		
		File backup = new File(backupDir,source.getName());
		System.out.println("backup file : "+backup);	
		
		//get the backup writing file.
		writer = new PrintWriter(new BufferedWriter( new FileWriter(backup)));
		
		//get the modify writing file
		File modFile =new File( sourceFile+"Mod");
		PrintWriter writer1= new PrintWriter(new BufferedWriter(
				     new FileWriter(modFile)));

		try
		{
			String st = new String();
			String temp=r.readLine();
            
            while(temp!=null)
            {
				//write the line in back up file
				 writer.println(temp);	

				//write the line in modified file
				if(temp.indexOf("||")!= -1 )
					st = temp+temp.substring(0,temp.indexOf("||")).trim()+appendString.trim();
				else
					st = temp;
	            writer1.println(st);
				
				//fetch the next line	
    	        temp=r.readLine();

			}
			writer.close();
			writer1.close();
		r.close();
		System.out.println("file backuped and modified file written in "+modFile);
		}
		catch(Exception e)
		{
			System.out.println("error while taking the back up or modifying "+e.getMessage());
			writer.close();
			writer1.close();
			r.close();
		}


		//rename the modified file to the original name 
		if(! modFile.renameTo(source))
			System.out.println("unable to rename the file "+modFile+" to "+sourceFile);	
		else
			System.out.println("file renamed");

			
	}


}
