import java.io.*;
import com.webnms.nms.ha.SystemCommands;
public class StartMySql{
	public static void main(String[] s){
		try{
			String path = System.getProperty("webnms.rootdir");
			if(path == null || path.equals("")){
				System.err.println("\nUSAGE : java -Dwebnms.rootdir=<ABSOLUTE_PATH_OF_NMS_HOME_WITHOUT_SPACES> StartMySql <option>\n");
				System.exit(126);
			}
			String osName = System.getProperty("os.name");
			osName = osName.toLowerCase();
			String dbHome = path+File.separator+"mysql";
			//String dbHome = path+File.separator+"pgsql";
			String commandToExecute = "";
			if((osName.indexOf("windows")) != -1) {
				commandToExecute = "cmd /c \"\"" +path+"\\bin\\startMySQL\" \"" +dbHome+"\"\"";
				//commandToExecute = "cmd /c \"\"" +path+"\\bin\\startPostgreSQL\" \"" +dbHome+"\"\"";
			}
			else{
				commandToExecute = "sh "+path+"/bin/startMySQL.sh "+dbHome;
				//commandToExecute = "sh "+path+"/bin/startPostgreSQL.sh "+dbHome;
			}
			System.err.println("s is : "+s+  "and length: "+s.length);
			Runtime.getRuntime().exec(commandToExecute);
			if(s.length!= 0){
				SystemCommands sysUtil = new SystemCommands(path);
				sysUtil.executeCommandFile(commandToExecute, dbHome);
			}
		}catch(Exception e){e.printStackTrace();}
	}
}
