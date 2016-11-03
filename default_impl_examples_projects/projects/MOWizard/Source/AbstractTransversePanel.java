//$Id: AbstractTransversePanel.java,v 1.1.1.1 2006/12/08 11:57:39 gramkumar Exp $
package com.adventnet.nms.tools.mowizard;
import javax.swing.JPanel;
import com.adventnet.nms.tools.objtorel.TransverseContainer;

public abstract class AbstractTransversePanel extends JPanel {
	/**
	 * This class must be extended by all screens that are added in
	 * TransversePanel.  By extending this class the user can be 
	 * able to optionally hide or make visible the screen based
	 * on the option chosen to the invocation of this screen. 
	 */

	boolean showing=true;
	
	public boolean isShowing() {
		/*
			This method is invoked to examine whether is the screen
			extending this class is visible or not. 
		*/	
		return showing;
	}

	public void setShowing(boolean makeVisible) {
		/* 
			This method is used to make a screen Visible  
		*/		
		showing=makeVisible;
	}

	/*
		Implementation should be provided by classes which extend this
		class.
	*/	
	public abstract void addTransverseContainer(TransverseContainer transCon);
	// This method will be called by TransversePanel before each screen 
	// is invoked.
	public abstract void loadScreenData();
}
