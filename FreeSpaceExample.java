package org.kodejava.sample.java.io;
import java.lang.management.*;

import java.io.File;

public class FreeSpaceExample
{
    public static void main(String[] args)
    {
        // We create an instance of a File to represent a partition
        // of our file system. For instance here we used a drive D:
        // as in Windows operating system.
        File file = new File("/home/local/ZOHOCORP/venkat-0773/webnms/5.2_astro/state/traps");
        File file2 = new File("/home/local/ZOHOCORP/venkat-0773/webnms/5.2_astro/state/");
        File file3 = new File("/home/local/ZOHOCORP/venkat-0773/webnms/5.2_astro");

        // Using the getTotalSpace() we can get an information of
        // the actual size of the partition, and we convert it to
        // mega bytes. 
        long totalSpace = file.getTotalSpace() / (1024 * 1024);
	/*System.err.println("file.length :: "+getFolderSize(file));
	System.err.println("file2.length :: "+getFolderSize(file2));
	System.err.println("file3.length :: "+getFolderSize(file3));*/
	System.err.println("file.sizeOfDirectory :: "+org.apache.commons.io.FileUtils.sizeOfDirectory(file3));


        // Next we get the free disk space as the name of the
        // method shown us, and also get the size in mega bytes.
        long freeSpace = file.getFreeSpace() / (1024 * 1024);

        // Just print out the values.
        System.out.println("Total Space1 = " + totalSpace + " Mega Bytes");
        System.out.println("Free Space1 = " + freeSpace+ " Mega Bytes");
        
        System.out.println("Free Space3 = " + Runtime.getRuntime().totalMemory()+ " Mega Bytes");
        System.out.println("Free Space4 = " + Runtime.getRuntime().freeMemory()+ " Mega Bytes");
        System.out.println("Free Space5 = " + Runtime.getRuntime().maxMemory()+ "Bytes");
        System.out.println("Free Space6 = " + ManagementFactory.getMemoryMXBean().getNonHeapMemoryUsage()+ "Bytes");
        System.out.println("Free Space7 = " + ManagementFactory.getMemoryMXBean().getHeapMemoryUsage()+ "Bytes");
	System.out.println("Free Space8 = " + file.getUsableSpace()/1024/1024+ " Mega Bytes");
    }
    public static long getFolderSize(File dir) {
	    long size = 0;
	    for (File file : dir.listFiles()) {
		    if (file.isFile()) {
			    //System.out.println(file.getName() + " " + file.length());
			    size += file.length();
		    }
		    else
			    size += getFolderSize(file);
	    }
	    return size;
    }

}
