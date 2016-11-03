

package com.adventnet.nms.runtimeconfig;

public interface ApplyToServerInterface 
{
    public boolean isModified();
    //Added by Balan on 21/06/03 for SPP Team Requirements when the exception arises this method is called
    //and it should return false 
    //public void applyToServer();
     public boolean applyToServer(); 
    // Add Ends
    public void refetchData();
    // Added by Balan on 08/07/03 for SPP for Memory Leak issue
    public void dispose();
    //Add Ends
}























