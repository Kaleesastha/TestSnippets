import com.adventnet.nms.eventdb.*;
import com.adventnet.nms.alertdb.*;
import java.util.*;
import java.rmi.Naming;
import java.util.Calendar;

public class EventTester
{
    public static void main(String[] a) throws Exception
    {
	if(a.length !=5)
	    {
		System.out.println("Usage : java EventTester BEHost severity No ObjectName Time");
		return;
	    }	
	EventAPI api = (EventAPI)Naming.lookup("//"+a[0]+"/EventAPI");
	int count = Integer.parseInt(a[2]);
	int severityCount = Integer.parseInt(a[1]);
        Random rand = new Random();
        String text = "AAAAA is example tezt";
        //for (int xx= 0; xx < 2; xx++)
        Event evt = new Event();
        evt.setText(text);
        evt.setSeverity(severityCount);
        System.out.println("Started sending events");
        long startTime = System.currentTimeMillis();

	Date date = getDate(a[4]);
        String name  = a[3];
        for(int i=1;i<=count;i++)
	    {
		evt.setSource(name + i);
		evt.setEntity(name + i);
		evt.setTime(date.getTime());
		//evt.setUserProperty("alertDoubleColumn", "" + System.currentTimeMillis());
		api.addEvent(evt);
		if (i % 1000 == 0)
		    {
			long endTime = (System.currentTimeMillis() - startTime) / 1000L;
			if (endTime > 0)
			    {
				System.out.println("Added : " + i + " events in " + (endTime) + " Sec. at the rate of " + (i / endTime));
			    }
		    }
	    }
        long endTime = System.currentTimeMillis();
        System.out.println("Finished sending the events");
    }

    private static Date getDate(String stringDate)
    {
	StringTokenizer tokenizer = new StringTokenizer(stringDate, ",", false);
	int noOfToks = tokenizer.countTokens();
	if (noOfToks != 6)
        {
	    System.out.println("The date format is : month,date,year,hour,min,sec");
	}
	int month = Integer.parseInt(tokenizer.nextToken()) - 1;
	int date = Integer.parseInt(tokenizer.nextToken());
	int year = Integer.parseInt(tokenizer.nextToken());
	int hour = Integer.parseInt(tokenizer.nextToken());
	int min = Integer.parseInt(tokenizer.nextToken());
	int sec = Integer.parseInt(tokenizer.nextToken());
	Calendar cal = Calendar.getInstance();
	cal.set(year, month, date, hour, min, sec);
	return cal.getTime(); 
    }

}	
		

