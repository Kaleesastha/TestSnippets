package com.adventnet.nms.test;

import java.rmi.*;

/**
 * Describe interface <code>ShutDownInvoker</code> here.
 
 */
public interface TestInvoker extends Remote
{	
    public void testInvoke() throws RemoteException;
}
