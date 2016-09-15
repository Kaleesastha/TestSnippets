package gctest;

import com.adventnet.nms.util.RunProcessInterface;

public class RunSystemGC implements RunProcessInterface
{
  private boolean initialized=false;

  public void callMain(String args[])
  {
	initialized = true;
	while(true)
	{
	  try
	  {
		System.gc();
		Thread.sleep(10000);
	  }
	  catch(InterruptedException excp)
	  {
	  }
	}
  }
  public boolean isInitialized()
  {
	return initialized;
  }
  public void shutDown()
  {
  }
}
