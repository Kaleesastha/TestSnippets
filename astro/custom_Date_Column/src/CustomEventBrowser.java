package com.adventnet.nms.eventui;
import com.adventnet.nms.util.NmsUiAPI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import com.adventnet.nms.util.*;

public class CustomEventBrowser extends EventBrowser {
	public void handleCallForFilter (String command) {
		spfilter    = new AstroSpecialEventFilter (this);
		spfilter.showDetails (command);
	}
}
