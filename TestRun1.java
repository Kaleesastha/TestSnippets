package test;
import java.io.*;

public class TestRun1
{
	public static void main(String args[])
	{
		String slash = "/";
		String webs = "../server/default/deploy/webnms.ear/webnms.war/../server/default/deploy/webnms.ear/webnms.war/";
		String sepStr = File.separatorChar == '\\' ? "\\\\" : String.valueOf ( File.separatorChar );
		//System.out.println("---"+ sepStr);
			webs=webs.replace("/",File.separator);
			System.out.println(webs);
	}
}
