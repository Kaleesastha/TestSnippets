import java.util.Date;

public class LongDate
{
	public static void main(String args[])
	{
		long l = Long.parseLong(args[0]);
		Date d = new Date(l);
		System.out.println(d);
	}
}
