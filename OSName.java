public class OSName{
	public static void main(String[] s)   {
		try { System.err.println("OS.Name of this machine &  arch  is "+System.getProperty("os.name") +", "+ System.getProperty("os.arch"));
			System.err.println("Encoding for this machine is : "+System.getProperty("file.encoding") +", "+ System.getProperty("os.arch"));
 }
		catch(Exception e) {e.printStackTrace();}
	}
}
