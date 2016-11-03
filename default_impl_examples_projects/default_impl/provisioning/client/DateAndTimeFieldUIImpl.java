//$Id: DateAndTimeFieldUIImpl.java,v 1.1.1.1 2006/11/16 15:39:58 gramkumar Exp $
package com.adventnet.nms.provisioning.ui.uielements;

import javax.swing.*;
import java.awt.*;
import com.adventnet.nms.util.DateTimeComponent;
import com.adventnet.nms.util.NmsClientUtil;
import com.adventnet.nms.provisioning.ui.TemplateUIPanel;
import com.adventnet.nms.provisioning.ui.ProvClientUtils;
import java.util.Calendar;

public class DateAndTimeFieldUIImpl extends AbstractXmlUIElement
{
	DateTimeComponent dateandtime = null;

	public DateAndTimeFieldUIImpl()
	{
		dateandtime = new DateTimeComponent();
        Calendar calendar = Calendar.getInstance();
		calendar.setTimeZone(TemplateUIPanel.perfTimeZone);
        dateandtime.setYear(calendar.get(Calendar.YEAR));
        dateandtime.setMonth(calendar.get(Calendar.MONTH));
        dateandtime.setDay(calendar.get(Calendar.DATE));
        dateandtime.setHour(calendar.get(Calendar.HOUR));
        dateandtime.setMinute(calendar.get(Calendar.MINUTE)+5);
        dateandtime.setSecond(calendar.get(Calendar.SECOND));
	}

	public void setEditable(boolean b)
	{
		dateandtime.setEnabled(b);
	}

	public String getValue()
	{
		return dateandtime.getDateTime();
	}

	public void setValue(String value)
	{
		if (!(dateandtime.setDateTime(value)))
		{
			JOptionPane.showMessageDialog(dateandtime, ProvClientUtils.getString("Invalid date"),ProvClientUtils.getString("Error Message"),JOptionPane.ERROR_MESSAGE);

		}
	}

	public JComponent getComponent()
	{
		return dateandtime;
	}
    public int addComponents(JComponent parentComp,GridBagConstraints gc,int x,int y,int maxX)
		{
        gc.gridx = x;
        gc.gridy = y;

        gc.weightx = 0.0;
        parentComp.add(label,gc);

        gc.weightx = 1.0;
        gc.gridx = x + 1;
				JPanel p = new JPanel();
				p.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
				p.add(getComponent());
        parentComp.add(p,gc);
        return 1;    
    }
}
