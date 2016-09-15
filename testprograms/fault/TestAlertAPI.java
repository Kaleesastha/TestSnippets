import java.rmi.Naming;
import java.util.*;

import com.adventnet.nms.alertdb.*;

public class TestAlertAPI
{
    String hostName = null;
    AlertAPI api = null;

    public static void main(String a[])
    {
        if(a.length !=1)
        {
            System.out.println("Usage: java TestAlertAPI hostName");
            return;
        }
        TestAlertAPI tapi = new TestAlertAPI(a[0]);
        tapi.getAlert();
        //tapi.getObjects();
        //tapi.getCompleteList();
        //tapi.getNextAlertBasedOnGroupViewMode();
        //tapi.getObjectNamesWithProps();
        //tapi.getTotalAlertCount();
        //tapi.clearAlert();
        //tapi.getupdtcycle();
        //tapi.updateNotes();
    }

    public TestAlertAPI(String hostName)
    {
        this.hostName=hostName;
        getAlertAPI();
    }

    private void getAlertAPI()
    {
        try
        {
            api = (AlertAPI)Naming.lookup("//"+hostName+":1099/AlertAPI");
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
        }
    }

	private void getAlert()
	{
		try
		{
			String entity = "Test";
			Alert alt = api.getAlert(entity);

			if(alt != null)
			{
				System.out.println(alt.getProperties());
			}
			else
			{
				System.out.println("null");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	private void getObjects()
	{
		try
		{
			Properties prop = new Properties();
			prop.setProperty("source","192.168.110.179");
			Vector vec = api.getObjects("SpecialAlert",prop);
			if(vec != null)
			{
				System.out.println(vec.size());
			}
			else
			{
				System.out.println("null");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	private void getCompleteList()
	{
		try
		{
                    Vector vec = api.getCompleteList();
                    if(vec != null)
		    {
                        System.out.println(vec.size());
                    }
                    else
                    {
                        System.out.println("null");
                    }
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	private void getNextAlertBasedOnGroupViewMode()
	{
		try
		{
			String name = "beach";
			Vector vec = api.getNextAlertBasedOnGroupViewMode(name);

                        if(vec != null)
                        {
                            for(int i=0;i<vec.size();i++)
                            {
                                System.out.println(vec.get(i));
                            }
                            System.out.println(vec.size());
                        }
                        else
                        {
                            System.out.println("null");
                        }
                        System.out.println();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

    private void getTotalAlertCount()
    {
        try
            {
                System.out.println(api.getTotalAlertCount());
            }
        catch(Exception e)
            {
                e.printStackTrace();
            }
    }

    private void getObjectNamesWithProps()
    {
        try
            {
                Properties prop = new Properties();
                prop.setProperty("source","192.168.110.179");
                Vector vec = api.getObjectNamesWithProps(prop);
                if(vec != null)
                    {
                        System.out.println(vec.size());
                    }
                else
                    {
                        System.out.println("null");
                    }
            }
        catch(Exception e)
            {
                e.printStackTrace();
            }
    }

    private void clearAlert()
    {
        try
            {
                /*
                Alert alt = api.checkOutIfAvailable("anjana.india.adventnet.com");
                alt.setUserProperty("<ALERTAPIUSERNAME>","root");
                api.clearAlert(alt);
                api.unlock(alt);
                */

                //Alert alt = api.checkOutIfAvailable("192.168.112.78");
                //Alert alt = api.checkOutIfAvailable("proximap_Port3");
                Alert alt = api.checkOutIfAvailable("cbalaji.india.adventnet.com");
                System.out.println(alt.getEntity());
                alt.setUserProperty("<ALERTAPIUSERNAME>","root");
                //api.clearAlert(alt, false);
                api.deleteAlert(alt, true);
                //api.clearAlert(alt);
                //api.deleteAlert(alt);
                api.unlock(alt);


                System.out.println("Alert cleared");

            }
        catch(Exception e)
            {
                e.printStackTrace();
            }
    }

    private void getupdtcycle()
    {
        try
        {
            Alert al=new Alert();
            al.setEntity("192.168.110.214");
            Alert al1=api.getAlert("192.168.110.214");
            for(int i=0;i<50;i++)
            {
                al1.setWebNMS("test" +i);
                System.out.println(al1.getWebNMS());
                api.updateAlert(al1);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    private void updateNotes()
    {
        try
            {
				for(int i=0;i<100;i++)
				{
					Alert alt = api.checkOutIfAvailable("test"+i);
					api.updateNotes(alt, "root", "Added Notes for "+alt.getEntity());
					api.unlock(alt);
				}

            }
        catch(Exception e)
            {
                e.printStackTrace();
            }
    }
}
