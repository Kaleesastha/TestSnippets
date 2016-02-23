import java.util.*;
public class GetLocale
{
    public static void main(String[] s)
    {
            try
            {
		    //Locale.setDefault(new Locale("zh", "CN"));
                    System.err.println("The machine locale is: "+Locale.getDefault().toString());
            }
            catch(Exception e)
            {
                    e.printStackTrace();
            }
    }
}
