package com.adventnet.nms.poll.graphs;

// import java classes

public interface CurrentGraphController
{
	public void close();
	public void stopPoll();
    	public void restartPoll();
    	public void setPollInterval(int period);
}




