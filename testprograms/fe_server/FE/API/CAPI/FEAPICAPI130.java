package com.adventnet.nms.client;

import com.adventnet.nms.topoui.*;

import java.util.*;

public class FEAPICAPI130
{
    public static void main(String[] args) throws Exception
    {
        FEAPICAPI130 as = new FEAPICAPI130();
        if (args.length != 2)
        {
            System.out.println("USAGE : java FEAPICAPI130 hostName userName");
            System.exit(0);
        }
        String hostName = args[0];
        String userName = args[1];

        ClientTopoAPI topoAPI = new ClientTopoAPIImpl(hostName, userName);

        ClientData data = (ClientData) topoAPI.createCustomView("TestCustomView");

        as.testSingleDisableEnable(data);
		as.testNestedDisableEnable(data);

        topoAPI.removeCustomView("TestCustomView");
        System.exit(0);
    }

    private void testSingleDisableEnable(ClientData data)
    {
        System.out.println("---------------------------------------------------------------------------------------------------\n");
        System.out.println("****************Testing SingleDisableEnable****************\n\n");
        Properties props = new Properties();
        props.setProperty("name", "a*");
        data.changeCriteriaProperties(props);
        System.out.println("CRITERIA OF CUSTOMVIEW IS SET AS a*");
        System.out.println("The keys for the data in the view before disabling data fetching :\n " + data.keys);

        data.disableRefresh();
        System.out.println("---------------------- DISABLED DATA FETCHING OPERATIONS ------------------------------------------\n");

        props.setProperty("name", "s*");
        data.changeCriteriaProperties(props);
        System.out.println("CRITERIA OF CUSTOMVIEW IS CHANGED AS s*");

        data.getBottom();
        System.out.println("SWITCHED THE VIEW TO LAST PAGE");

        data.setIncrement(2);
        System.out.println("SET PAGE LENGTH AS 2");

        System.out.println("The final set of keys after all these above operations & also after disabling data fetching:\n " + data.keys);


        System.out.println("---------------------- ENABLED DATA FETCHING OPERATIONS -------------------------------------------\n");
        data.enableRefresh();
        System.out.println("The keys after Enable refresh :\n " + data.keys);
        System.out.println("------------------------------------------------------------------------------------------------\n\n");
    }


    int counter = 3;

    private void testNestedDisableEnable(ClientData data)
    {
        System.out.println("----------------------------------------------------------------------------------------------------\n");
        System.out.println("****************Testing NestedDisableEnable****************\n\n");
        Properties props = new Properties();
        props.setProperty("name", "a*");
        data.changeCriteriaProperties(props);
        System.out.println("CRITERIA OF CUSTOMVIEW IS CHANGED AS a*");
        System.out.println("The keys for the data in the custom View before disabling data fetching : \n" + data.keys);


        for (int i = 1; i <= counter; i ++)
        {
            System.out.println("---------------------- DISABLED DATA FETCHING OPERATIONS -----------------------------------\n");
            data.disableRefresh();

            switch (i % 4)
            {
            case 1:
                System.out.println("CRITERIA OF CUSTOMVIEW IS CHANGED AS s*");
                props.setProperty("name", "s*");
                data.changeCriteriaProperties(props);
                
                System.out.println("SWITCHED TO THE LAST PAGE");
                data.getBottom();
                break;
            case 2:
                System.out.println("CRITERIA OF CUSTOMVIEW IS CHANGED AS m*");
                props.setProperty("name", "m*");
                data.changeCriteriaProperties(props);

                System.out.println("SWITCHED TO THE NEXT PAGE");
                data.getNext();
                break;
            case 3:
                System.out.println("CRITERIA OF CUSTOMVIEW IS CHANGED AS IF*");
                props.setProperty("name", "IF*");
                data.changeCriteriaProperties(props);

                System.out.println("SWITCHED TO THE NEXT PAGE WITH VIEW LENGTH AS 10");
                data.getNext(10);
                break;
            }

            System.out.println("SET THE VIEW LENGTH AS : " + 2 * i);
            data.setIncrement(2 * i);
        }

        System.out.println("The keys for the data in the view after above operations :\n " + data.keys);


        for (int i = 0; i < counter - 1; i++)
        {
            System.out.println("---------------------- ENABLED DATA FETCHING OPERATIONS ------------------------------------------\n");
            data.enableRefresh();
        }

        System.out.println("The keys after " + (counter - 1) + " Enable Refresh is : " + data.keys);

        System.out.println("---------------------- ENABLED DATA FETCHING OPERATIONS ----------------------------------------\n");        
        data.enableRefresh();
        System.out.println("The keys after final/last Enable Refresh : \n" + data.keys);
        System.out.println("-----------------------------------------------------------------------------------------------------\n");
    }
}
