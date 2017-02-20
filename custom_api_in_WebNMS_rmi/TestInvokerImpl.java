package com.adventnet.nms.test;

import java.rmi.*;
import java.rmi.server.*;

import com.adventnet.nms.rmi.RMIUtil;
import com.adventnet.nms.util.*;


public class TestInvokerImpl implements TestInvoker,java.io.Serializable,CommonModuleAPI
{
    private static TestInvokerImpl test = null;    

    TestInvokerImpl() throws RemoteException
    {
        //It is best to use this method to bind the object   
        NmsUtil.bindObjectInRegistry(this,"TestInvoker",true);
	System.out.println("############### TestInvokerImpl bound in RMI Registry");
    }
    
    public static TestInvokerImpl getInstance()
    {
        if(test==null)
        {
            try
            {
                test= new TestInvokerImpl();
            }
            catch(RemoteException r)
            {
                r.printStackTrace();
            }
        }
        return test;
    }
    
    public void testInvoke() throws RemoteException
    {
        try
        {       
            System.out.println("Test Invoked ");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
