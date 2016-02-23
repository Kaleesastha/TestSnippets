package test;

import com.adventnet.nms.util.*;
import java.util.*;
import java.io.*;
import com.adventnet.management.log.SystemUtil;
public class RedirectCoutCerr implements RunProcessInterface
{
	public void callMain(String args[])
	{
		try{
			File logOutFile = new File(PureUtils.rootDir+"logs"+File.separator+"TestOut.txt");
			File logErrFile = new File(PureUtils.rootDir+"logs"+File.separator+"TestErr.txt");
			logOutFile.createNewFile();
			logErrFile.createNewFile();
			PrintStream logout =new PrintStream(new FileOutputStream(logOutFile));
			PrintStream logerr =new PrintStream(new FileOutputStream(logErrFile));
			SystemUtil.cout = logout;
			SystemUtil.cerr = logerr;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	public boolean isInitialized()
	{
		return true;
	}
	public void shutDown()
	{

	}
}
