package test;
import com.adventnet.nms.util.NmsUtil;
public class CheckString{
	public static void main(String[] args){

		try{
			if(NmsUtil.checkString(args[0], args[1], true))
			//if(NmsUtil.checkString(args[0], "*", false))
			{
				System.err.println("result for "+args[0]+" is true");
			}
			else {
				System.err.println("result for "+args[0]+" is false");
			}
			boolean flag = NmsUtil.checkOneString(args[0], args[1]);
			System.err.println("result for checkOneString is : "+flag);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
