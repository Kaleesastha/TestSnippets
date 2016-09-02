package com.adventnet.nms.mapui;
import com.adventnet.nms.util.NmsUiAPI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import com.adventnet.nms.util.*;

public class CustomListView extends NmsListView {
	public void handleCallForFilter (String command) {
		spfilter    = new AstroSpecialFilter (this);
		spfilter.showDetails (command);
	}
}
