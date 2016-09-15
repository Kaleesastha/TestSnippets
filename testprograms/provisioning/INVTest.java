/* $Id: INVTest.java,v 1.1 2003/01/22 07:31:40 rajalakshmytr Exp $
 *
 * This program can be used to test testcases as given in the INVTest_README.html file.
 * Please go through the Readme file for more information on the usage of this file.
 *
 * Usage: java hostName port methodName 
 *
 * hostName : name of the machine where WebNMS is running. (String)
 *
 */

import java.rmi.*;
import java.util.*;
import java.rmi.server.*;

import com.adventnet.nms.provisioning.inventorydb.*;

public class INVTest 
{
    public static void main(String args[]) 
    {
        if(args.length !=3)
        {
            System.out.println("Usage: java hostName port methodName");
            return;
        }
        try
        {
            InventoryAPI api = (InventoryAPI)Naming.lookup("rmi://"+args[0]+":"+args[1]+"/InventoryAPI");
            System.out.println("Inventory API : "+api);
            System.out.println();   
            if(args[2].equalsIgnoreCase("addInventory"))
            {
                NetworkInventory inv = new NetworkInventory();
                inv.setName(args[3]);
                Properties prop = new Properties();
                inv.setProperties(prop);
                inv.setType("NetworkInventoryTestme");
                inv.setDescription("ok tested");
                inv.setModel("EPSON");
                inv.setContact("println");
                inv.setClassname("NetworkInventory");
                inv.setManagedObjectName("chinnaraj");
                api.addInventory(inv);
            }

            else if(args[2].equalsIgnoreCase("getInventory"))
            {
                NetworkInventory inv = api.getInventory(args[3]);
                System.out.println("Name : "+inv.getName());
                System.out.println(inv.getProperties());
            }

            else if(args[2].equalsIgnoreCase("updateInventory"))
            {
                NetworkInventory inv = api.getInventory(args[2]);
                inv.setLocation("chennai");
                inv.setVendor("openjms");
                api.updateInventory(inv);
            }

            else if(args[2].equalsIgnoreCase("deleteInventory"))
            {
                api.deleteInventory(args[3]);
            }

            else if(args[2].equalsIgnoreCase("addVariant"))
            {
                ProvisioningVariant var = new ProvisioningVariant(args[3]);
                Properties prop = new Properties();
                prop.put("prop1", "name1");
                prop.put("prop2", "name2");
                var.setProperties(prop);
                var.setDescription("TestVariant");
                api.addVariant(var);
            }

            else if(args[2].equalsIgnoreCase("getVariant"))
            {
                ProvisioningVariant pv = api.getVariant(args[3]);
                System.out.println("variantId : "+pv.getVariantId());
                System.out.println(pv.getProperties());
            }

            else if(args[2].equalsIgnoreCase("updateVariant"))
            {
                ProvisioningVariant pv = api.getVariant(args[2]);
                Properties prop = new Properties();
                prop.put("prop3", "value");
                prop.put("prop4", "val2");
                pv.setProperties(prop);
                pv.setDescription("Test");
                api.updateVariant(pv);
            }

            else if(args[2].equalsIgnoreCase("deleteVariant"))
            {
                api.deleteVariant(args[3]);
            }

            else if(args[2].equalsIgnoreCase("getCompleteInventoryList"))
            {
                System.out.println(api.getCompleteInventoryList());     
            }

            else if(args[2].equalsIgnoreCase("getCompleteVariantList"))
            {
                System.out.println(api.getCompleteVariantList());       
            }

            else if(args[2].equalsIgnoreCase("getInventoryNamesWithProps"))
            {
                Properties prop = new Properties();
                prop.put("name", args[3]);
                System.out.println(api.getInventoryNamesWithProps(prop));
            }
            else if(args[2].equalsIgnoreCase("EmsInventory"))
            {
                NetworkInventory nv = api.getInventory(args[3], args[4]);
                System.out.println(nv.getProperties());
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
