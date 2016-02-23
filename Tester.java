import java.text.*;
import java.util.*;
import java.text.MessageFormat;

public class Tester
{
 public static void main(String[] args) throws Exception
    {
  /*SimpleDateFormat simpledateformat = new SimpleDateFormat("MMM dd,yyyy hh:mm:ss a");
  TimeZone timezone = TimeZone.getDefault();
  simpledateformat.setTimeZone(timezone);

  DateFormatSymbols dateformatsymbols = simpledateformat.getDateFormatSymbols();
  String as[] = dateformatsymbols.getMonths();
  String as1[] = dateformatsymbols.getShortMonths();
  int i = as.length;
        for(int j = 0; j < i; j++)
        {
            System.out.println(as[j]);
   System.out.println(as1[j]);
        }
  
  String as2[] = dateformatsymbols.getAmPmStrings();
  i = as2.length;
        for(int j = 0; j < i; j++)
        {
            System.out.println(as2[j]);
        }
*/
			String SSLSocketFactory="test.SocketFactoryImpl";//NO I18N
			Class socketFactory;
			try{
				socketFactory = Class.forName(SSLSocketFactory);
			}
			catch(ClassNotFoundException cnf)
			{
				System.err.println(MessageFormat.format(("socketfactory.not.available.defaultfactory"),new String[]{SSLSocketFactory}));//NO I18N
				Class.forName("test.SocketFactoryImpl");
			}
			catch(Exception e)
			{
				System.err.println(e.getMessage()+":\n"+"socketfactory.not.available");//NO I18N
			}

 }
}
