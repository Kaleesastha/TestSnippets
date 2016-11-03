//$Id: ProtocolIconRenderer.java,v 1.1 2006/08/29 13:56:51 build Exp $
package com.adventnet.nms.config ; 


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class ProtocolIconRenderer extends javax.swing.JLabel  implements javax.swing.table.TableCellRenderer
{
	private ConfigPanel configPanel = null;

	private ImageIcon snmpIcon = null;
	private ImageIcon telnetIcon = null;
	private ImageIcon tftpIcon = null;
	private ImageIcon tl1Icon = null;
	private ImageIcon combinedIcon = null;

	public ProtocolIconRenderer()
	{

	}

	public ProtocolIconRenderer(ConfigPanel panel)
	{
		this.configPanel = panel;

		setOpaque(true);
		setIconTextGap(7);

		initializeProtocolIcons();
	}

	private void initializeProtocolIcons()
	{
		snmpIcon = configPanel.configClientUtils.getImage(configPanel.applet.getDocumentBase()+"../images/snmptask.png");
		telnetIcon = configPanel.configClientUtils.getImage(configPanel.applet.getDocumentBase()+"../images/telnettask.png");
		tftpIcon = configPanel.configClientUtils.getImage(configPanel.applet.getDocumentBase()+"../images/tftptask.png");
		tl1Icon = configPanel.configClientUtils.getImage(configPanel.applet.getDocumentBase()+"../images/tl1task.png");
		combinedIcon = configPanel.configClientUtils.getImage(configPanel.applet.getDocumentBase()+"../images/combinedtask.png");		
	}

	//--Interface Implementation--//

	public java.awt.Component getTableCellRendererComponent( javax.swing.JTable table, java.lang.Object value, boolean isSelected,
			boolean hasFocus, int row, int column)
	{
		 setBorder( new javax.swing.border.EmptyBorder(0,0,0,0 ));
        setForeground(Color.black);
        if((row % 2) == 0)
        {
            setBackground(new Color(239,239,239));
        }
        else{
                setBackground(Color.white);
        }
	if(value!=null){
		String str=configPanel.configMainUI.getProtocol(value.toString());
		if(str!=null){

			if(str.equals("SNMP"))
			{
				setIcon(snmpIcon);
			}

			else if(str.equals("TELNET"))
			{
				setIcon(telnetIcon);
			}

			else if(str.equals("TFTP"))
			{
				setIcon(tftpIcon);
			}

			else if(str.equals("TL1"))
			{
				setIcon(tl1Icon);
			}

			else if(str.startsWith("COMBINED"))
			{
				setIcon(combinedIcon);
			}
		}
		else{
			setIcon(null);
		}
	  }
		setFont(table.getFont());
		if(isSelected)
		{
			setForeground(table.getSelectionForeground());
			setBackground(table.getSelectionBackground());

		}
		else
		{
			setForeground(table.getForeground());
		}
		if(value!=null){
		setText(value.toString());
		}
		return this;
	}
}

