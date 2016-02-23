package test;

import java.util.*;
import java.io.*;
import java.util.*;
import com.adventnet.security.authorization.*;
import com.adventnet.nms.util.*;

public class ServiceShutdown {
static String[] arg  = null;
    public static void main(String[] args) {
	    
	   arg=args;
        try
        {
	for( int k=0; k< arg.length;k=k+1)
	{
		if(arg[k].equalsIgnoreCase("-ep"))
		{
			arg[k]="-p";
			System.err.println("arg is"+arg[k]);
			arg[k+1]=Coding.convertFromBase(args[k+1]); //Encrypted password converted into normal passwsord
			System.err.println("arg is"+arg[k+1]);
		}

	}
		StandAloneShutDown.main(arg);
	
	}
        catch (Exception e)
        {
		e.printStackTrace();
        }

    }
}
