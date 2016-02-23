package test;

import java.util.*;
import java.io.*;
import java.rmi.*;
import java.util.*;

public class NewFile {

    public static void main(String[] argv) {
        try
        {
		File file = new File(argv[0]);
		if(file.exists())
			System.err.println("file is success!");
		else
			System.err.println("file is failure!");
        }
        catch (Exception re)
        {
            System.out.println ( "Error in getting the handle: " + re);
	    re.printStackTrace();
        }

    }
}
