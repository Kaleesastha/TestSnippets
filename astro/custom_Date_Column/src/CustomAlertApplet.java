package com.adventnet.nms.alertui;
import com.adventnet.nms.util.NmsUiAPI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import com.adventnet.nms.util.*;

public class CustomAlertApplet extends AlertApplet {
	public void handleCallForFilter (String command) {
		spfilter    = new AstroSpecialAlertFilter (this);
		spfilter.showDetails (command);
	}
}
