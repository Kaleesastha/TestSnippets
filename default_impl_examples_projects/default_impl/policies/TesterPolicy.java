/* $Id: TesterPolicy.java,v 1.1.1.1 2006/11/16 15:39:58 gramkumar Exp $ */

package com.adventnet.nms.policies;

import java.util.Properties;
import java.util.Vector;
import java.util.Enumeration;
import com.adventnet.management.policydb.PolicyEvent;
import com.adventnet.management.policydb.PolicyObject;
import com.adventnet.nms.topodb.TopoAPI;
import com.adventnet.nms.topodb.ManagedObject;
import com.adventnet.nms.util.NmsLogMgr;
import com.adventnet.nms.util.NmsUtil ;
import com.adventnet.management.log.Log;
import com.adventnet.nms.store.relational.RelationalUtil;

 // An Object that extends PolicyObject, serves as a simple
 // example for the user to create a PolicyObject of his own.
 // This policy primarily walks through all the nodes in the 
 // database. Picks up the nodes which matches the criterias 
 // given by the user, and changes their tester to the tester 
 // specified by the user.
	 
public class TesterPolicy extends PolicyObject
{
	static final long serialVersionUID = 3626064944305772472L;
	/**
	 * Sets the properties for this policy object
	 * @param p the property to be set.
	 */

    public TesterPolicy()
    {
        policyObjectCustomizer= "com.adventnet.nms.policyui.TesterPolicyCustomizer";//No Internationalisation
    }

	public void setProperties(Properties p)
	{
            NmsLogMgr.POLICYUSER.log(NmsUtil.GetString("POLICY DEBUG :in TesterPolicy setProperties ")+p , Log.DEBUG); 
		String temp;
		temp = (String)p.remove("type");//No Internationalisation
		if(temp != null)
		{
                    setUserProperty("type",temp.trim());//No Internationalisation
		}

		temp = (String)p.remove("community");//No Internationalisation
		if(temp != null)
		{
                    setUserProperty("community",temp.trim());//No Internationalisation
		}

		temp = (String)p.remove("tester");//No Internationalisation
		if(temp != null)
		{
                    setUserProperty("tester",temp.trim());//No Internationalisation
		}

		temp = (String)p.remove("parentNet");//No Internationalisation
		if(temp != null)
		{
                    setUserProperty("parentNet",temp.trim());//No Internationalisation
		}
		super.setBaseProperties(p);
                NmsLogMgr.POLICYUSER.log(NmsUtil.GetString("POLICY DEBUG :  End of set Properties method") , Log.DEBUG); 
	}
	/**
	 * Returns the properties of this policy object
	 * @return Properties
	 */

    public Properties getProperties()
    {
        Properties p = super.getBaseProperties();
        String temp;
        temp = getUserProperty("type");//No Internationalisation
        if(temp != null)
        {
            p.put("type",temp);//No Internationalisation
        }
        temp = getUserProperty("community");//No Internationalisation
        if(temp != null)
        {
            p.put("community",temp);//No Internationalisation

        }
        temp = getUserProperty("tester");//No Internationalisation
        if(temp != null)
        {
            p.put("tester",temp);//No Internationalisation
        }
        temp = getUserProperty("parentNet");//No Internationalisation
        if(temp != null)
        {
            p.put("parentNet",temp);//No Internationalisation

        }
        return p;
    }
	

	/**
	 * Returns the customizer class of this policy object
	 * @return "Customizer"//No Internationalisation
	 */

	public String getPolicyObjectCustomizer()
	{
		return policyObjectCustomizer;
	}

    /**
     * Returns the help url of this policy object. As, the default customizer associated
     * with this policy uses the help url directly from the help.conf, this method
     * returns null.
     */
	public String getHelpURL()
	{
        return null;
	}


	/**
	 * executes action of this policy object.
	 * @param policyEvt PolicyEvent
	 */
	
	public	 void executeAction(PolicyEvent policyEvt)
	{
		TopoAPI topoapi = (TopoAPI)NmsUtil.getAPI("TopoAPI");//No Internationalisation
                if (topoapi == null)
                {
                    NmsLogMgr.POLICYERR.log(NmsUtil.GetString("Can't able to get TopoAPI in TesterPolicy "), Log.SUMMARY);
                    return;
                }
		Properties p = new Properties();
		String temp = null;
                temp = getUserProperty("type");//No Internationalisation
                if(temp != null && temp.length() > 0)
                {
                    p.put("type",temp);//No Internationalisation
                }
                temp = getUserProperty("community");//No Internationalisation
                if(temp != null && temp.length() > 0)
                {
                    p.put("community",temp);//No Internationalisation
                }
                String tester = getUserProperty("tester");//No Internationalisation
                
		try
		{
                    Vector v = null;
			Vector obj_with_props = topoapi.getObjectNamesWithProps(p);
                        temp = getUserProperty("parentNet");//No Internationalisation
                        if(temp != null && temp.length() > 0)
                        {
                            Vector nodeVector = topoapi.getNodesOfNetwork(temp);
                            v = RelationalUtil.getIntersectionOfTwoVectors(obj_with_props , nodeVector);
                        }
                        else
                        {
                            v = obj_with_props;
                        }

                        if(v == null || v.size() == 0)
                        {
                            System.err.println(NmsUtil.GetString("In Tester PolicyObject "+name+" no object found with the given matching criteria")); 
                        }
			for(Enumeration en= v.elements();en.hasMoreElements();)
			{	
				String nodeName= (String)en.nextElement();
				ManagedObject mgObj = topoapi.checkOut(nodeName,2);
                                if(tester.equals("max") || tester.equals("ping") || tester.equals("snmpping"))//No Internationalisation
                                {
                                    mgObj.setTester(tester);
                                }
                                else
                                {
                                    mgObj.setTester("usertest");//No Internationalisation
                                    mgObj.setUClass(tester);
                                }
				boolean bool = topoapi.updateObject(mgObj , false , true);
				if(!bool)
				{
					NmsLogMgr.POLICYERR.log(NmsUtil.GetString("unable to update the node - TesterPolicy"),Log.SUMMARY);
				}
			}
		}
		catch(Exception e)
		{
				NmsLogMgr.POLICYERR.fail(NmsUtil.GetString("Exception invoking API - TesterPolicy"), e);
		}
	}

}

