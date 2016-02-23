public class Occurrence{
	public static void main(String[] s){
		try{
			String original = s[0];
			String test = original;
			String output="";
			while(test != null && !test.equals("")){
				String a = test.substring(0,1);
				int k=test.lastIndexOf(a);
				output=output+a+(k+1);
				test=test.substring(k+1,test.length());
			}
			System.err.println("output is:"+output);
		} catch(Exception e){
			e.printStackTrace();
		}
	}
}
