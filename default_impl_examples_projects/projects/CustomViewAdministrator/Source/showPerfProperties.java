
// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// For more details please see EditingSourceFiles.html present in the docs directory

package com.adventnet.nms.tools.CustomView;




import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.event.*;


public class showPerfProperties extends JDialog implements ActionListener
{
	//<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	javax.swing.JPanel Top = null;
	javax.swing.JPanel JPanel4 = null;
	javax.swing.JScrollPane JScrollPane1 = null;
	javax.swing.JPanel JPanel3 = null;
	javax.swing.JPanel JPanel2 = null;
	javax.swing.JCheckBox StatTblChek = null;
	javax.swing.JCheckBox DnsNameChek = null;
	javax.swing.JCheckBox ComChek = null;
	javax.swing.JCheckBox ActiveChek = null;
	javax.swing.JCheckBox SnmpVerChek = null;
	javax.swing.JCheckBox PortChek = null;
	javax.swing.JCheckBox lastCounterChek = null;
	javax.swing.JCheckBox numTypeChek = null;
	javax.swing.JCheckBox savePollChek = null;
	javax.swing.JCheckBox PollerNameChek = null;
	javax.swing.JCheckBox ThesholdChek = null;
	javax.swing.JCheckBox FailCountChek = null;
	javax.swing.JCheckBox logFileChek = null;
	javax.swing.JCheckBox saveAbsChek = null;
	javax.swing.JCheckBox grpNameChek = null;
	javax.swing.JTextField tFailCount = null;
	javax.swing.JTextField tPollerName = null;
	javax.swing.JTextField tNumType = null;
	javax.swing.JTextField tLastcounter = null;
	javax.swing.JTextField tLogFile = null;
	javax.swing.JTextField tSuffix = null;
	javax.swing.JTextField tSnmpVersion = null;
	javax.swing.JTextField tgrpName = null;
	javax.swing.JTextField tStatsTable = null;
	javax.swing.JTextField tDnsName = null;
	javax.swing.JTextField tCommunity = null;
	javax.swing.JTextField tActive = null;
	javax.swing.JTextField tPort = null;
	javax.swing.JTextField tThresholdList = null;
	javax.swing.JTextField tSavePoll = null;
	javax.swing.JTextField tSaveAbs = null;
	javax.swing.JCheckBox suffixChek = null;
	javax.swing.JCheckBox saveOnThChek = null;
	javax.swing.JTextField tSaveOnThreshold = null;
	javax.swing.JPanel JPanel21 = null;
	javax.swing.JCheckBox OwnerChek = null;
	javax.swing.JCheckBox PollidChek = null;
	javax.swing.JCheckBox ObjectIdChek = null;
	javax.swing.JCheckBox IntervalChek = null;
	javax.swing.JCheckBox MultipleChek = null;
	javax.swing.JCheckBox AgentChek = null;
	javax.swing.JCheckBox ThresholdChek = null;
	javax.swing.JCheckBox PrevSevChek = null;
	javax.swing.JCheckBox StatTableNameChek = null;
	javax.swing.JCheckBox ProtocolChek = null;
	javax.swing.JCheckBox ParentObjChek = null;
	javax.swing.JCheckBox CurrSaveChek = null;
	javax.swing.JCheckBox LogDirChek = null;
	javax.swing.JCheckBox SaveChek = null;
	javax.swing.JCheckBox PolicyChek = null;
	javax.swing.JTextField tCurrSaveCount = null;
	javax.swing.JTextField tProtocol = null;
	javax.swing.JTextField tPrevSev = null;
	javax.swing.JTextField tThreshold = null;
	javax.swing.JTextField tLogDir = null;
	javax.swing.JTextField tFailThreshold = null;
	javax.swing.JTextField tMultiple = null;
	javax.swing.JTextField tPolicy = null;
	javax.swing.JTextField tOwnerName = null;
	javax.swing.JTextField tPollId = null;
	javax.swing.JTextField tObjectId = null;
	javax.swing.JTextField tInterval = null;
	javax.swing.JTextField tAgent = null;
	javax.swing.JTextField tParentObj = null;
	javax.swing.JTextField tStatsTableName = null;
	javax.swing.JTextField tSave = null;
	javax.swing.JCheckBox FailThresholdChek = null;
	javax.swing.JCheckBox timeAvgChek = null;
	javax.swing.JTextField tTimeAvg = null;
	javax.swing.JPanel JPanel1 = null;
	javax.swing.JLabel JLabel1 = null;
	javax.swing.JButton bAdditionalProps = null;
	javax.swing.JPanel JPanel5 = null;
	javax.swing.JButton ok = null;
	javax.swing.JButton cancel = null;
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
	//<End_Variable_Declarations>
	AdditionalCriteria criteria=null;
	Properties additionalColumns=null;
	Vector selectedProps=null;
	Properties selectedColumns=null;
  

  


  


 

  public void setVisible(boolean bl)
  {
        //<Begin_setVisible_boolean>
       	if(bl)
       	{
       	  init();
          start();
        }
        else
        {
          stop();
        }
        super.setVisible(bl);
  
        //<End_setVisible_boolean>
  }

    public void init()
  {
        //<Begin_init> 
        if (initialized == true) return; 
        this.setSize(getPreferredSize().width+536,getPreferredSize().height+553); 
          setTitle("");
        Container container = getContentPane();
        container.setLayout(new BorderLayout()); 
        try 
        { 
          initVariables(); 
          setUpGUI(container); 
          setUpProperties(); 
          setUpConnections(); 
        } 
        catch(Exception ex) 
        { 
          showStatus("Error in init method",ex); 
        } 
        // let us set the initialzed variable to true so  
        // we dont initialize again even if init is called 
        initialized = true; 


  
        //<End_init>
	this.setModal(true);
	Toolkit kit=Toolkit.getDefaultToolkit();
	Dimension dim=kit.getScreenSize();
	
	this.setLocation((dim.width-(int)this.getSize().getWidth())/2,(dim.height-(int)this.getSize().getHeight())/2);
  } 
  public String getParameter(String input)
  {
           //<Begin_getParameter_String> 
           String value = null;
           if ( applet != null)
           {    
                 value = applet.getParameter(input);
           }    
           else
           {    
                 value = (String)com.adventnet.apiutils.Utility.getParameter(input);
           }    
           if(value == null)
           {
            }
        return value;

  
           //<End_getParameter_String>
  } 
  public void setUpProperties()
  {
  //<Begin_setUpProperties> 

          try
          {
            JPanel4.setBorder(new javax.swing.border.EtchedBorder(0));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+JPanel4,ex); 
          }

          try
          {
            StatTblChek.setFont(new Font("SansSerif",0,12));
            StatTblChek.setHorizontalTextPosition(4);
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+StatTblChek,ex); 
          }

          try
          {
            DnsNameChek.setFont(new Font("SansSerif",0,12));
            DnsNameChek.setHorizontalTextPosition(4);
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+DnsNameChek,ex); 
          }

          try
          {
            ComChek.setFont(new Font("SansSerif",0,12));
            ComChek.setHorizontalTextPosition(4);
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+ComChek,ex); 
          }

          try
          {
            ActiveChek.setFont(new Font("SansSerif",0,12));
            ActiveChek.setHorizontalTextPosition(4);
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+ActiveChek,ex); 
          }

          try
          {
            SnmpVerChek.setFont(new Font("SansSerif",0,12));
            SnmpVerChek.setHorizontalTextPosition(4);
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+SnmpVerChek,ex); 
          }

          try
          {
            PortChek.setFont(new Font("SansSerif",0,12));
            PortChek.setHorizontalTextPosition(4);
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+PortChek,ex); 
          }

          try
          {
            lastCounterChek.setFont(new Font("SansSerif",0,12));
            lastCounterChek.setHorizontalTextPosition(4);
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+lastCounterChek,ex); 
          }

          try
          {
            numTypeChek.setFont(new Font("SansSerif",0,12));
            numTypeChek.setHorizontalTextPosition(4);
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+numTypeChek,ex); 
          }

          try
          {
            savePollChek.setFont(new Font("SansSerif",0,12));
            savePollChek.setHorizontalTextPosition(4);
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+savePollChek,ex); 
          }

          try
          {
            PollerNameChek.setFont(new Font("SansSerif",0,12));
            PollerNameChek.setHorizontalTextPosition(4);
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+PollerNameChek,ex); 
          }

          try
          {
            ThesholdChek.setFont(new Font("SansSerif",0,12));
            ThesholdChek.setHorizontalTextPosition(4);
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+ThesholdChek,ex); 
          }

          try
          {
            FailCountChek.setFont(new Font("SansSerif",0,12));
            FailCountChek.setHorizontalTextPosition(4);
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+FailCountChek,ex); 
          }

          try
          {
            logFileChek.setFont(new Font("SansSerif",0,12));
            logFileChek.setHorizontalTextPosition(4);
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+logFileChek,ex); 
          }

          try
          {
            saveAbsChek.setFont(new Font("SansSerif",0,12));
            saveAbsChek.setHorizontalTextPosition(4);
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+saveAbsChek,ex); 
          }

          try
          {
            grpNameChek.setFont(new Font("SansSerif",0,12));
            grpNameChek.setHorizontalTextPosition(4);
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+grpNameChek,ex); 
          }

          try
          {
            tFailCount.setHorizontalAlignment(2);
            tFailCount.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+tFailCount,ex); 
          }

          try
          {
            tPollerName.setHorizontalAlignment(2);
            tPollerName.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+tPollerName,ex); 
          }

          try
          {
            tNumType.setHorizontalAlignment(2);
            tNumType.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+tNumType,ex); 
          }

          try
          {
            tLastcounter.setHorizontalAlignment(2);
            tLastcounter.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+tLastcounter,ex); 
          }

          try
          {
            tLogFile.setHorizontalAlignment(2);
            tLogFile.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+tLogFile,ex); 
          }

          try
          {
            tSuffix.setHorizontalAlignment(2);
            tSuffix.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+tSuffix,ex); 
          }

          try
          {
            tSnmpVersion.setHorizontalAlignment(2);
            tSnmpVersion.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+tSnmpVersion,ex); 
          }

          try
          {
            tgrpName.setHorizontalAlignment(2);
            tgrpName.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+tgrpName,ex); 
          }

          try
          {
            tStatsTable.setHorizontalAlignment(2);
            tStatsTable.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+tStatsTable,ex); 
          }

          try
          {
            tDnsName.setHorizontalAlignment(2);
            tDnsName.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+tDnsName,ex); 
          }

          try
          {
            tCommunity.setHorizontalAlignment(2);
            tCommunity.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+tCommunity,ex); 
          }

          try
          {
            tActive.setHorizontalAlignment(2);
            tActive.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+tActive,ex); 
          }

          try
          {
            tPort.setHorizontalAlignment(2);
            tPort.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+tPort,ex); 
          }

          try
          {
            tThresholdList.setHorizontalAlignment(2);
            tThresholdList.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+tThresholdList,ex); 
          }

          try
          {
            tSavePoll.setHorizontalAlignment(2);
            tSavePoll.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+tSavePoll,ex); 
          }

          try
          {
            tSaveAbs.setHorizontalAlignment(2);
            tSaveAbs.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+tSaveAbs,ex); 
          }

          try
          {
            suffixChek.setFont(new Font("SansSerif",0,12));
            suffixChek.setHorizontalTextPosition(4);
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+suffixChek,ex); 
          }

          try
          {
            saveOnThChek.setFont(new Font("SansSerif",0,12));
            saveOnThChek.setHorizontalTextPosition(4);
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+saveOnThChek,ex); 
          }

          try
          {
            tSaveOnThreshold.setHorizontalAlignment(2);
            tSaveOnThreshold.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+tSaveOnThreshold,ex); 
          }

          try
          {
            OwnerChek.setFont(new Font("SansSerif",0,12));
            OwnerChek.setHorizontalTextPosition(4);
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+OwnerChek,ex); 
          }

          try
          {
            PollidChek.setFont(new Font("SansSerif",0,12));
            PollidChek.setHorizontalTextPosition(4);
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+PollidChek,ex); 
          }

          try
          {
            ObjectIdChek.setFont(new Font("SansSerif",0,12));
            ObjectIdChek.setHorizontalTextPosition(4);
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+ObjectIdChek,ex); 
          }

          try
          {
            IntervalChek.setFont(new Font("SansSerif",0,12));
            IntervalChek.setHorizontalTextPosition(4);
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+IntervalChek,ex); 
          }

          try
          {
            MultipleChek.setFont(new Font("SansSerif",0,12));
            MultipleChek.setHorizontalTextPosition(4);
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+MultipleChek,ex); 
          }

          try
          {
            AgentChek.setFont(new Font("SansSerif",0,12));
            AgentChek.setHorizontalTextPosition(4);
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+AgentChek,ex); 
          }

          try
          {
            ThresholdChek.setFont(new Font("SansSerif",0,12));
            ThresholdChek.setHorizontalTextPosition(4);
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+ThresholdChek,ex); 
          }

          try
          {
            PrevSevChek.setFont(new Font("SansSerif",0,12));
            PrevSevChek.setHorizontalTextPosition(4);
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+PrevSevChek,ex); 
          }

          try
          {
            StatTableNameChek.setFont(new Font("SansSerif",0,12));
            StatTableNameChek.setHorizontalTextPosition(4);
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+StatTableNameChek,ex); 
          }

          try
          {
            ProtocolChek.setFont(new Font("SansSerif",0,12));
            ProtocolChek.setHorizontalTextPosition(4);
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+ProtocolChek,ex); 
          }

          try
          {
            ParentObjChek.setFont(new Font("SansSerif",0,12));
            ParentObjChek.setHorizontalTextPosition(4);
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+ParentObjChek,ex); 
          }

          try
          {
            CurrSaveChek.setFont(new Font("SansSerif",0,12));
            CurrSaveChek.setHorizontalTextPosition(4);
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+CurrSaveChek,ex); 
          }

          try
          {
            LogDirChek.setFont(new Font("SansSerif",0,12));
            LogDirChek.setHorizontalTextPosition(4);
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+LogDirChek,ex); 
          }

          try
          {
            SaveChek.setFont(new Font("SansSerif",0,12));
            SaveChek.setHorizontalTextPosition(4);
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+SaveChek,ex); 
          }

          try
          {
            PolicyChek.setFont(new Font("SansSerif",0,12));
            PolicyChek.setHorizontalTextPosition(4);
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+PolicyChek,ex); 
          }

          try
          {
            tCurrSaveCount.setHorizontalAlignment(2);
            tCurrSaveCount.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+tCurrSaveCount,ex); 
          }

          try
          {
            tProtocol.setHorizontalAlignment(2);
            tProtocol.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+tProtocol,ex); 
          }

          try
          {
            tPrevSev.setHorizontalAlignment(2);
            tPrevSev.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+tPrevSev,ex); 
          }

          try
          {
            tThreshold.setHorizontalAlignment(2);
            tThreshold.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+tThreshold,ex); 
          }

          try
          {
            tLogDir.setHorizontalAlignment(2);
            tLogDir.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+tLogDir,ex); 
          }

          try
          {
            tFailThreshold.setHorizontalAlignment(2);
            tFailThreshold.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+tFailThreshold,ex); 
          }

          try
          {
            tMultiple.setHorizontalAlignment(2);
            tMultiple.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+tMultiple,ex); 
          }

          try
          {
            tPolicy.setHorizontalAlignment(2);
            tPolicy.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+tPolicy,ex); 
          }

          try
          {
            tOwnerName.setHorizontalAlignment(2);
            tOwnerName.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+tOwnerName,ex); 
          }

          try
          {
            tPollId.setHorizontalAlignment(2);
            tPollId.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+tPollId,ex); 
          }

          try
          {
            tObjectId.setHorizontalAlignment(2);
            tObjectId.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+tObjectId,ex); 
          }

          try
          {
            tInterval.setHorizontalAlignment(2);
            tInterval.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+tInterval,ex); 
          }

          try
          {
            tAgent.setHorizontalAlignment(2);
            tAgent.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+tAgent,ex); 
          }

          try
          {
            tParentObj.setHorizontalAlignment(2);
            tParentObj.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+tParentObj,ex); 
          }

          try
          {
            tStatsTableName.setHorizontalAlignment(2);
            tStatsTableName.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+tStatsTableName,ex); 
          }

          try
          {
            tSave.setHorizontalAlignment(2);
            tSave.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+tSave,ex); 
          }

          try
          {
            FailThresholdChek.setFont(new Font("SansSerif",0,12));
            FailThresholdChek.setHorizontalTextPosition(4);
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+FailThresholdChek,ex); 
          }

          try
          {
            timeAvgChek.setFont(new Font("SansSerif",0,12));
            timeAvgChek.setHorizontalTextPosition(4);
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+timeAvgChek,ex); 
          }

          try
          {
            tTimeAvg.setHorizontalAlignment(2);
            tTimeAvg.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+tTimeAvg,ex); 
          }

          try
          {
            JLabel1.setHorizontalAlignment(2);
            JLabel1.setFont(new Font("SansSerif",0,12));
            JLabel1.setForeground(new Color(-16777216));
            JLabel1.setHorizontalTextPosition(4);
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+JLabel1,ex); 
          }

          try
          {
            bAdditionalProps.setFont(new Font("SansSerif",0,12));
            bAdditionalProps.setHorizontalTextPosition(4);
            bAdditionalProps.setText("Additional Properties");
            bAdditionalProps.setBorder(new javax.swing.border.BevelBorder(0));
            bAdditionalProps.setMaximumSize(new Dimension(157,25));
            bAdditionalProps.setMinimumSize(new Dimension(157,25));
            bAdditionalProps.setPreferredSize(new Dimension(157,25));
            bAdditionalProps.setLabel("Additional table Columns");
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+bAdditionalProps,ex); 
          }

          try
          {
            ok.setFont(new Font("SansSerif",0,12));
            ok.setHorizontalTextPosition(4);
            ok.setText("OK");
            ok.setBorder(new javax.swing.border.BevelBorder(0));
            ok.setMinimumSize(new Dimension(43,21));
            ok.setMaximumSize(new Dimension(43,21));
            ok.setPreferredSize(new Dimension(43,21));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+ok,ex); 
          }

          try
          {
            cancel.setFont(new Font("SansSerif",0,12));
            cancel.setHorizontalTextPosition(4);
            cancel.setText("Cancel");
            cancel.setBorder(new javax.swing.border.BevelBorder(0));
            cancel.setMaximumSize(new Dimension(43,21));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+cancel,ex); 
          }
		cancel.setPreferredSize(new Dimension(cancel.getPreferredSize().width+24,cancel.getPreferredSize().height+4));
		ok.setPreferredSize(new Dimension(ok.getPreferredSize().width+24,ok.getPreferredSize().height+4));
		JPanel5.setPreferredSize(new Dimension(JPanel5.getPreferredSize().width+0,JPanel5.getPreferredSize().height+4));
		JPanel1.setPreferredSize(new Dimension(JPanel1.getPreferredSize().width+319,JPanel1.getPreferredSize().height+0));
		JScrollPane1.setPreferredSize(new Dimension(JScrollPane1.getPreferredSize().width+492,JScrollPane1.getPreferredSize().height+0));
		JPanel4.setPreferredSize(new Dimension(JPanel4.getPreferredSize().width+10,JPanel4.getPreferredSize().height+12));

  
          //<End_setUpProperties>
  } 
  public void start()
  {
  //<Begin_start> 

  
  //<End_start>
  } 
  public void stop()
  {
  //<Begin_stop> 

  
  //<End_stop>
  } 
  public void initVariables()
  {
        //<Begin_initVariables> 
        Top= new javax.swing.JPanel();
        JPanel4= new javax.swing.JPanel();
        JScrollPane1= new javax.swing.JScrollPane();
        JPanel3= new javax.swing.JPanel();
        JPanel2= new javax.swing.JPanel();
        StatTblChek= new javax.swing.JCheckBox();
        DnsNameChek= new javax.swing.JCheckBox();
        ComChek= new javax.swing.JCheckBox();
        ActiveChek= new javax.swing.JCheckBox();
        SnmpVerChek= new javax.swing.JCheckBox();
        PortChek= new javax.swing.JCheckBox();
        lastCounterChek= new javax.swing.JCheckBox();
        numTypeChek= new javax.swing.JCheckBox();
        savePollChek= new javax.swing.JCheckBox();
        PollerNameChek= new javax.swing.JCheckBox();
        ThesholdChek= new javax.swing.JCheckBox();
        FailCountChek= new javax.swing.JCheckBox();
        logFileChek= new javax.swing.JCheckBox();
        saveAbsChek= new javax.swing.JCheckBox();
        grpNameChek= new javax.swing.JCheckBox();
        tFailCount= new javax.swing.JTextField();
        tPollerName= new javax.swing.JTextField();
        tNumType= new javax.swing.JTextField();
        tLastcounter= new javax.swing.JTextField();
        tLogFile= new javax.swing.JTextField();
        tSuffix= new javax.swing.JTextField();
        tSnmpVersion= new javax.swing.JTextField();
        tgrpName= new javax.swing.JTextField();
        tStatsTable= new javax.swing.JTextField();
        tDnsName= new javax.swing.JTextField();
        tCommunity= new javax.swing.JTextField();
        tActive= new javax.swing.JTextField();
        tPort= new javax.swing.JTextField();
        tThresholdList= new javax.swing.JTextField();
        tSavePoll= new javax.swing.JTextField();
        tSaveAbs= new javax.swing.JTextField();
        suffixChek= new javax.swing.JCheckBox();
        saveOnThChek= new javax.swing.JCheckBox();
        tSaveOnThreshold= new javax.swing.JTextField();
        JPanel21= new javax.swing.JPanel();
        OwnerChek= new javax.swing.JCheckBox();
        PollidChek= new javax.swing.JCheckBox();
        ObjectIdChek= new javax.swing.JCheckBox();
        IntervalChek= new javax.swing.JCheckBox();
        MultipleChek= new javax.swing.JCheckBox();
        AgentChek= new javax.swing.JCheckBox();
        ThresholdChek= new javax.swing.JCheckBox();
        PrevSevChek= new javax.swing.JCheckBox();
        StatTableNameChek= new javax.swing.JCheckBox();
        ProtocolChek= new javax.swing.JCheckBox();
        ParentObjChek= new javax.swing.JCheckBox();
        CurrSaveChek= new javax.swing.JCheckBox();
        LogDirChek= new javax.swing.JCheckBox();
        SaveChek= new javax.swing.JCheckBox();
        PolicyChek= new javax.swing.JCheckBox();
        tCurrSaveCount= new javax.swing.JTextField();
        tProtocol= new javax.swing.JTextField();
        tPrevSev= new javax.swing.JTextField();
        tThreshold= new javax.swing.JTextField();
        tLogDir= new javax.swing.JTextField();
        tFailThreshold= new javax.swing.JTextField();
        tMultiple= new javax.swing.JTextField();
        tPolicy= new javax.swing.JTextField();
        tOwnerName= new javax.swing.JTextField();
        tPollId= new javax.swing.JTextField();
        tObjectId= new javax.swing.JTextField();
        tInterval= new javax.swing.JTextField();
        tAgent= new javax.swing.JTextField();
        tParentObj= new javax.swing.JTextField();
        tStatsTableName= new javax.swing.JTextField();
        tSave= new javax.swing.JTextField();
        FailThresholdChek= new javax.swing.JCheckBox();
        timeAvgChek= new javax.swing.JCheckBox();
        tTimeAvg= new javax.swing.JTextField();
        JPanel1= new javax.swing.JPanel();
        JLabel1= new javax.swing.JLabel();
        bAdditionalProps= new javax.swing.JButton();
        JPanel5= new javax.swing.JPanel();
        ok= new javax.swing.JButton();
        cancel= new javax.swing.JButton();

  
        //<End_initVariables>
	bAdditionalProps.addActionListener(this);
	ok.addActionListener(this);
	cancel.addActionListener(this);	
  } 
  public void setUpGUI(Container container)
  {
//<Begin_setUpGUI_Container> 
container.add(Top,BorderLayout.CENTER);
Top.setLayout(new BorderLayout(5,5));
Top.add(JPanel4,BorderLayout.CENTER);
JPanel4.setLayout(new BorderLayout(5,5));
JPanel4.add(JScrollPane1,BorderLayout.CENTER);
JScrollPane1.getViewport().add(JPanel3);
JPanel3.setLayout(new GridLayout(1,0,5,5));
JPanel3.add(JPanel2);
JPanel2.setLayout(new GridBagLayout());
inset = new Insets(3,3,3,3);
setConstraints(0,0,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
JPanel2.add(StatTblChek,cons);
inset = new Insets(3,3,3,3);
setConstraints(0,1,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
JPanel2.add(DnsNameChek,cons);
inset = new Insets(3,3,3,3);
setConstraints(0,2,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
JPanel2.add(ComChek,cons);
inset = new Insets(3,3,3,3);
setConstraints(0,3,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
JPanel2.add(ActiveChek,cons);
inset = new Insets(3,3,3,3);
setConstraints(0,4,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
JPanel2.add(SnmpVerChek,cons);
inset = new Insets(3,3,3,3);
setConstraints(0,5,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
JPanel2.add(PortChek,cons);
inset = new Insets(3,3,3,3);
setConstraints(0,6,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
JPanel2.add(lastCounterChek,cons);
inset = new Insets(3,3,3,3);
setConstraints(0,7,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
JPanel2.add(numTypeChek,cons);
inset = new Insets(3,3,3,3);
setConstraints(0,14,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
JPanel2.add(savePollChek,cons);
inset = new Insets(3,3,3,3);
setConstraints(0,13,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
JPanel2.add(PollerNameChek,cons);
inset = new Insets(3,3,3,3);
setConstraints(0,11,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
JPanel2.add(ThesholdChek,cons);
inset = new Insets(3,3,3,3);
setConstraints(0,12,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
JPanel2.add(FailCountChek,cons);
inset = new Insets(3,3,3,3);
setConstraints(0,10,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
JPanel2.add(logFileChek,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,9,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
JPanel2.add(saveAbsChek,cons);
inset = new Insets(3,3,3,3);
setConstraints(0,8,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
JPanel2.add(grpNameChek,cons);
inset = new Insets(3,3,3,3);
setConstraints(1,12,0,1,0.1,0.0,cons.CENTER,cons.BOTH,inset,0,0);
JPanel2.add(tFailCount,cons);
inset = new Insets(3,3,3,3);
setConstraints(1,13,0,1,0.1,0.0,cons.CENTER,cons.BOTH,inset,0,0);
JPanel2.add(tPollerName,cons);
inset = new Insets(3,3,3,3);
setConstraints(1,7,0,1,0.1,0.0,cons.CENTER,cons.BOTH,inset,0,0);
JPanel2.add(tNumType,cons);
inset = new Insets(3,3,3,3);
setConstraints(1,6,0,1,0.1,0.0,cons.CENTER,cons.BOTH,inset,0,0);
JPanel2.add(tLastcounter,cons);
inset = new Insets(3,3,3,3);
setConstraints(1,10,0,1,0.1,0.0,cons.CENTER,cons.BOTH,inset,0,0);
JPanel2.add(tLogFile,cons);
inset = new Insets(3,3,3,3);
setConstraints(1,15,0,1,0.1,0.0,cons.CENTER,cons.BOTH,inset,0,0);
JPanel2.add(tSuffix,cons);
inset = new Insets(3,3,3,3);
setConstraints(1,4,0,1,0.1,0.0,cons.CENTER,cons.BOTH,inset,0,0);
JPanel2.add(tSnmpVersion,cons);
inset = new Insets(3,3,3,3);
setConstraints(1,8,0,1,0.1,0.0,cons.CENTER,cons.BOTH,inset,0,0);
JPanel2.add(tgrpName,cons);
inset = new Insets(3,3,3,3);
setConstraints(1,0,0,1,0.1,0.0,cons.CENTER,cons.BOTH,inset,0,0);
JPanel2.add(tStatsTable,cons);
inset = new Insets(3,3,3,3);
setConstraints(1,1,0,1,0.1,0.0,cons.CENTER,cons.BOTH,inset,0,0);
JPanel2.add(tDnsName,cons);
inset = new Insets(3,3,3,3);
setConstraints(1,2,0,1,0.1,0.0,cons.CENTER,cons.BOTH,inset,0,0);
JPanel2.add(tCommunity,cons);
inset = new Insets(3,3,3,3);
setConstraints(1,3,0,1,0.1,0.0,cons.CENTER,cons.BOTH,inset,0,0);
JPanel2.add(tActive,cons);
inset = new Insets(3,3,3,3);
setConstraints(1,5,0,1,0.1,0.0,cons.CENTER,cons.BOTH,inset,0,0);
JPanel2.add(tPort,cons);
inset = new Insets(3,3,3,3);
setConstraints(1,11,0,1,0.1,0.0,cons.CENTER,cons.BOTH,inset,0,0);
JPanel2.add(tThresholdList,cons);
inset = new Insets(3,3,3,3);
setConstraints(1,14,0,1,0.1,0.0,cons.CENTER,cons.BOTH,inset,0,0);
JPanel2.add(tSavePoll,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,9,0,1,0.1,0.0,cons.CENTER,cons.BOTH,inset,0,0);
JPanel2.add(tSaveAbs,cons);
inset = new Insets(3,3,3,3);
setConstraints(0,15,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
JPanel2.add(suffixChek,cons);
inset = new Insets(3,3,3,3);
setConstraints(0,16,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
JPanel2.add(saveOnThChek,cons);
inset = new Insets(3,3,3,3);
setConstraints(2,16,0,1,0.1,0.0,cons.CENTER,cons.BOTH,inset,0,0);
JPanel2.add(tSaveOnThreshold,cons);
JPanel3.add(JPanel21);
JPanel21.setLayout(new GridBagLayout());
inset = new Insets(3,3,3,3);
setConstraints(0,0,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
JPanel21.add(OwnerChek,cons);
inset = new Insets(3,3,3,3);
setConstraints(0,1,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
JPanel21.add(PollidChek,cons);
inset = new Insets(3,3,3,3);
setConstraints(0,2,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
JPanel21.add(ObjectIdChek,cons);
inset = new Insets(3,3,3,3);
setConstraints(0,3,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
JPanel21.add(IntervalChek,cons);
inset = new Insets(3,3,3,3);
setConstraints(0,4,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
JPanel21.add(MultipleChek,cons);
inset = new Insets(3,3,3,3);
setConstraints(0,5,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
JPanel21.add(AgentChek,cons);
inset = new Insets(3,3,3,3);
setConstraints(0,6,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
JPanel21.add(ThresholdChek,cons);
inset = new Insets(3,3,3,3);
setConstraints(0,7,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
JPanel21.add(PrevSevChek,cons);
inset = new Insets(3,3,3,3);
setConstraints(0,14,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
JPanel21.add(StatTableNameChek,cons);
inset = new Insets(3,3,3,3);
setConstraints(0,13,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
JPanel21.add(ProtocolChek,cons);
inset = new Insets(3,3,3,3);
setConstraints(0,11,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
JPanel21.add(ParentObjChek,cons);
inset = new Insets(3,3,3,3);
setConstraints(0,12,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
JPanel21.add(CurrSaveChek,cons);
inset = new Insets(3,3,3,3);
setConstraints(0,10,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
JPanel21.add(LogDirChek,cons);
inset = new Insets(3,3,3,3);
setConstraints(0,9,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
JPanel21.add(SaveChek,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,8,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
JPanel21.add(PolicyChek,cons);
inset = new Insets(3,3,3,3);
setConstraints(1,12,0,1,0.1,0.0,cons.CENTER,cons.BOTH,inset,0,0);
JPanel21.add(tCurrSaveCount,cons);
inset = new Insets(3,3,3,3);
setConstraints(1,13,0,1,0.1,0.0,cons.CENTER,cons.BOTH,inset,0,0);
JPanel21.add(tProtocol,cons);
inset = new Insets(3,3,3,3);
setConstraints(1,7,0,1,0.1,0.0,cons.CENTER,cons.BOTH,inset,0,0);
JPanel21.add(tPrevSev,cons);
inset = new Insets(3,3,3,3);
setConstraints(1,6,0,1,0.1,0.0,cons.CENTER,cons.BOTH,inset,0,0);
JPanel21.add(tThreshold,cons);
inset = new Insets(3,3,3,3);
setConstraints(1,10,0,1,0.1,0.0,cons.CENTER,cons.BOTH,inset,0,0);
JPanel21.add(tLogDir,cons);
inset = new Insets(3,3,3,3);
setConstraints(1,15,0,1,0.1,0.0,cons.CENTER,cons.BOTH,inset,0,0);
JPanel21.add(tFailThreshold,cons);
inset = new Insets(3,3,3,3);
setConstraints(1,4,0,1,0.1,0.0,cons.CENTER,cons.BOTH,inset,0,0);
JPanel21.add(tMultiple,cons);
inset = new Insets(3,3,3,3);
setConstraints(1,8,0,1,0.1,0.0,cons.CENTER,cons.BOTH,inset,0,0);
JPanel21.add(tPolicy,cons);
inset = new Insets(3,3,3,3);
setConstraints(1,0,0,1,0.1,0.0,cons.CENTER,cons.BOTH,inset,0,0);
JPanel21.add(tOwnerName,cons);
inset = new Insets(3,3,3,3);
setConstraints(1,1,0,1,0.1,0.0,cons.CENTER,cons.BOTH,inset,0,0);
JPanel21.add(tPollId,cons);
inset = new Insets(3,3,3,3);
setConstraints(1,2,0,1,0.1,0.0,cons.CENTER,cons.BOTH,inset,0,0);
JPanel21.add(tObjectId,cons);
inset = new Insets(3,3,3,3);
setConstraints(1,3,0,1,0.1,0.0,cons.CENTER,cons.BOTH,inset,0,0);
JPanel21.add(tInterval,cons);
inset = new Insets(3,3,3,3);
setConstraints(1,5,0,1,0.1,0.0,cons.CENTER,cons.BOTH,inset,0,0);
JPanel21.add(tAgent,cons);
inset = new Insets(3,3,3,3);
setConstraints(1,11,0,1,0.1,0.0,cons.CENTER,cons.BOTH,inset,0,0);
JPanel21.add(tParentObj,cons);
inset = new Insets(3,3,3,3);
setConstraints(1,14,0,1,0.1,0.0,cons.CENTER,cons.BOTH,inset,0,0);
JPanel21.add(tStatsTableName,cons);
inset = new Insets(3,3,3,3);
setConstraints(1,9,0,1,0.1,0.0,cons.CENTER,cons.BOTH,inset,0,0);
JPanel21.add(tSave,cons);
inset = new Insets(3,3,3,3);
setConstraints(0,15,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
JPanel21.add(FailThresholdChek,cons);
inset = new Insets(3,3,3,3);
setConstraints(0,16,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
JPanel21.add(timeAvgChek,cons);
inset = new Insets(3,3,3,3);
setConstraints(1,16,1,1,0.1,0.0,cons.CENTER,cons.BOTH,inset,0,0);
JPanel21.add(tTimeAvg,cons);
JPanel4.add(JPanel1,BorderLayout.SOUTH);
JPanel1.setLayout(new GridBagLayout());
inset = new Insets(0,0,0,0);
setConstraints(0,0,1,1,0.1,0.0,cons.WEST,cons.BOTH,inset,0,0);
JPanel1.add(JLabel1,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,0,1,1,0.0,0.0,cons.EAST,cons.NONE,inset,0,0);
JPanel1.add(bAdditionalProps,cons);
Top.add(JPanel5,BorderLayout.SOUTH);
JPanel5.setLayout(new FlowLayout(1,5,5));
JPanel5.add(ok);
JPanel5.add(cancel);

  
//<End_setUpGUI_Container>
  } 
  public void setUpConnections()
  {
  //<Begin_setUpConnections> 

  
  //<End_setUpConnections>
  } 



  
  public void showStatus(String message)
  {
     //<Begin_showStatus_String>
     System.out.println("Internal Error :"+ message);
     //<End_showStatus_String>
  }
  public void showStatus(String message,Exception ex)
  {
     //<Begin_showStatus_String_Exception>
     System.out.println("Internal Error :"+ message);
     ex.printStackTrace();
     //<End_showStatus_String_Exception>
  }





  public showPerfProperties()
  {
    //<Begin_showPerfProperties>
    pack();
    this.setTitle("showPerfProperties");
  
    //<End_showPerfProperties>
	this.init();
  }

  public showPerfProperties(java.applet.Applet applet)
  {
    //<Begin_showPerfProperties_java.applet.Applet>
    this.applet = applet;
    pack();
    this.setTitle("showPerfProperties");
    this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
  
    //<End_showPerfProperties_java.applet.Applet>
  }

 
  public void setConstraints(int x,int y,int width,int height,double wtX,double wtY,int anchor,int fill,Insets inset,int padX,int padY )
  {
        //<Begin_setConstraints_int_int_int_int_double_double_int_int_Insets_int_int> 
	cons.gridx = x;
	cons.gridy = y;
	cons.gridwidth = width;
	cons.gridheight = height;
	cons.weightx = wtX;
	cons.weighty = wtY;
	cons.anchor = anchor;
	cons.fill = fill;
	cons.insets = inset;
	cons.ipadx = padX;
	cons.ipady = padY;
	
  
        //<End_setConstraints_int_int_int_int_double_double_int_int_Insets_int_int>
  }
public void actionPerformed(ActionEvent e)
{
	if(e.getSource().equals(bAdditionalProps))
	{
		if(criteria==null)
		{
			criteria=new AdditionalCriteria("Additional Table Columns",additionalColumns,selectedProps);
		}
		else
		{
			Properties prop=criteria.getProps();
			criteria=new AdditionalCriteria("Additonal Table Columns",prop,null);
		}
		criteria.setVisible(true);
	}
	else if(e.getSource().equals(ok))
	{
		selectedColumns=getSelectedColumns();
		setVisible(false);
	}
	else if(e.getSource().equals(cancel))
	{
		setVisible(false);
	}
}
public Properties getColumns()
{
	return selectedColumns;		
}
public void setProperties(Properties displayName,Properties checkedColumns)
	{
		setDisplayNames(displayName);
		setSelection(checkedColumns);

	}
	public void setDisplayNames(Properties displayName)
	{
		Vector vec=new Vector();
		tStatsTable.setEnabled(false);
		if(displayName.get("name")!=null)
		{
			tStatsTable.setText((String)displayName.get("name"));
			vec.add("name");
		}
		if(displayName.get("dnsName")!=null)
		{
			tDnsName.setText((String)displayName.get("dnsName"));
			vec.add("dnsName");
		}
		if(displayName.get("community")!=null)
		{
			tCommunity.setText((String)displayName.get("community"));
			vec.add("community");
		}
		if(displayName.get("active")!=null)
		{
			tActive.setText((String)displayName.get("active"));
			vec.add("active");
		}
		if(displayName.get("snmpVersion")!=null)
		{
			tSnmpVersion.setText((String)displayName.get("snmpVersion"));
			vec.add("snmpVersion");
		}
		if(displayName.get("port")!=null)
		{
	          		tPort.setText((String)displayName.get("port"));
			vec.add("port");
		}
		if(displayName.get("lastCounterValue")!=null)
		{
			tLastcounter.setText((String)displayName.get("lastCounterValue"));
			vec.add("lastCounterValue");
		}
		if(displayName.get("numericType")!=null)
		{
			tNumType.setText((String)displayName.get("numericType"));
			vec.add("numericType");
		}
		if(displayName.get("groupName")!=null)
		{
			tgrpName.setText((String)displayName.get("groupName"));
			vec.add("groupName");
		}
		if(displayName.get("saveAbsolutes")!=null)
		{
			tSaveAbs.setText((String)displayName.get("saveAbsolutes"));
			vec.add("saveAbsolutes");
		}
		if(displayName.get("logFile")!=null)
		{
			tLogFile.setText((String)displayName.get("logFile"));
			vec.add("logFile");
		}
		if(displayName.get("thresholdList")!=null)
		{
			tThresholdList.setText((String)displayName.get("thresholdList"));
			vec.add("thresholdList");
		}
		if(displayName.get("failureCount")!=null)
		{
			tFailCount.setText((String)displayName.get("failureCount"));
			vec.add("failureCount");
		}
		if(displayName.get("pollerName")!=null)
		{
			tPollerName.setText((String)displayName.get("pollerName"));
			vec.add("pollerName");
		}
		if(displayName.get("savePollCount")!=null)
		{
			tSavePoll.setText((String)displayName.get("savePollCount"));		
			vec.add("savePollCount");
		}
		if(displayName.get("suffix")!=null)
		{
	          		tSuffix.setText((String)displayName.get("suffix"));
			vec.add("suffix");
		}
		if(displayName.get("ownerName")!=null)
		{
			tOwnerName.setText((String)displayName.get("ownerName"));
			vec.add("ownerName");
		}
		if(displayName.get("id")!=null)
		{
			tPollId.setText((String)displayName.get("id"));
			vec.add("id");
		}
		if(displayName.get("oid")!=null)
		{
			tObjectId.setText((String)displayName.get("oid"));
			vec.add("oid");
		}
		if(displayName.get("period")!=null)
		{
			tInterval.setText((String)displayName.get("period"));
			vec.add("period");
		}
		if(displayName.get("isMultiplePolledData")!=null)
		{
			tMultiple.setText((String)displayName.get("isMultiplePolledData"));
			vec.add("isMultiplePolledData");
		}
		if(displayName.get("agent")!=null)
		{
			tAgent.setText((String)displayName.get("agent"));
			vec.add("agent");
		}
		if(displayName.get("threshold")!=null)
		{
			tThreshold.setText((String)displayName.get("threshold"));
			vec.add("threshold");
		}
		if(displayName.get("previousSeverity")!=null)
		{
			tPrevSev.setText((String)displayName.get("previousSeverity"));
			vec.add("previousSeverity");
		}
		if(displayName.get("policyName")!=null)
		{
			tPolicy.setText((String)displayName.get("policyName"));
			vec.add("policyName");
		}
		if(displayName.get("save")!=null)
		{
	          		tSave.setText((String)displayName.get("save"));
			vec.add("save");
		}
		if(displayName.get("logDirectly")!=null)
		{
			tLogDir.setText((String)displayName.get("logDirectly"));
			vec.add("logDirectly");
		}
		if(displayName.get("parentObj")!=null)
		{
			tParentObj.setText((String)displayName.get("parentObj"));
			vec.add("parentObj");
		}
		if(displayName.get("currentSaveCount")!=null)
		{
			tCurrSaveCount.setText((String)displayName.get("currentSaveCount"));
			vec.add("currentSaveCount");
		}
		if(displayName.get("protocol")!=null)
		{
			tProtocol.setText((String)displayName.get("protocol"));
			vec.add("protocol");
		}
		if(displayName.get("statsDataTableName")!=null)
		{
			tStatsTableName.setText((String)displayName.get("statsDataTableName"));
			vec.add("statsDataTableName");
		}
		if(displayName.get("failureThreshold")!=null)
		{
			tFailThreshold.setText((String)displayName.get("failureThreshold"));
			vec.add("failureThreshold");
		}
		if(displayName.get("saveOnThreshold")!=null)
		{
			tSaveOnThreshold.setText((String)displayName.get("saveOnThreshold"));
			vec.add("saveOnThreshold");
		}
		if(displayName.get("timeAvg")!=null)
		{
			tTimeAvg.setText((String)displayName.get("timeAvg"));
			vec.add("timeAvg");
		}
		selectedProps=vec;
		additionalColumns=displayName;
     }

	public void setSelection(Properties checkedColumns)
	{
		selectedColumns=checkedColumns;
		StatTblChek.setEnabled(false);
		if(checkedColumns.get("name")!=null)
		{
			StatTblChek.setSelected(true);
		}
		else
		{
			StatTblChek.setSelected(false);
		}
		if(checkedColumns.get("dnsName")!=null)
		{
			DnsNameChek.setSelected(true);
		}
		else
		{
			DnsNameChek.setSelected(false);
		}
		if(checkedColumns.get("community")!=null)
		{
			ComChek.setSelected(true);
		}
		else
		{
			ComChek.setSelected(false);
		}
		if(checkedColumns.get("active")!=null)
		{
			ActiveChek.setSelected(true);
		}
		else
		{
			ActiveChek.setSelected(false);
		}
		if(checkedColumns.get("SnmpVersion")!=null)
		{
			SnmpVerChek.setSelected(true);
		}
		else
		{
			SnmpVerChek.setSelected(false);
		}
		if(checkedColumns.get("port")!=null)
		{
			PortChek.setSelected(true);
		}
		else
		{
			PortChek.setSelected(false);
		}
		if(checkedColumns.get("lastCounterValue")!=null)
		{
			lastCounterChek.setSelected(true);
		}
		else
		{
			lastCounterChek.setSelected(false);
		}
		if(checkedColumns.get("numericType")!=null)
		{
			numTypeChek.setSelected(true);
		}
		else
		{
			numTypeChek.setSelected(false);
		}
		if(checkedColumns.get("groupName")!=null)
		{
			grpNameChek.setSelected(true);
		}
		else
		{
			grpNameChek.setSelected(false);
		}
		if(checkedColumns.get("saveAbsolutes")!=null)
		{
			saveAbsChek.setSelected(true);
		}
		else
		{
			saveAbsChek.setSelected(false);
		}
		if(checkedColumns.get("logFile")!=null)
		{
			logFileChek.setSelected(true);
		}
		else
		{
			logFileChek.setSelected(false);
		}
		if(checkedColumns.get("thresholdList")!=null)
		{
			ThresholdChek.setSelected(true);
		}
		else
		{
			ThresholdChek.setSelected(false);
		}
		if(checkedColumns.get("failureCount")!=null)
		{
			FailCountChek.setSelected(true);
		}
		else
		{
			FailCountChek.setSelected(false);
		}
		if(checkedColumns.get("pollerName")!=null)
		{
			PollerNameChek.setSelected(true);
		}
		else
		{
			PollerNameChek.setSelected(false);
		}
		if(checkedColumns.get("savePollCount")!=null)
		{
			savePollChek.setSelected(true);
		}
		else
		{
			savePollChek.setSelected(false);
		}
		if(checkedColumns.get("suffix")!=null)
		{
			suffixChek.setSelected(true);
		}
		else
		{
			suffixChek.setSelected(false);
		}
		if(checkedColumns.get("ownerName")!=null)
		{
			OwnerChek.setSelected(true);
		}
		else
		{
			OwnerChek.setSelected(false);
		}
		if(checkedColumns.get("id")!=null)
		{
			PollidChek.setSelected(true);
		}
		else
		{
			PollidChek.setSelected(false);
		}
		if(checkedColumns.get("oid")!=null)
		{
			ObjectIdChek.setSelected(true);
		}
		else
		{
			ObjectIdChek.setSelected(false);
		}
		if(checkedColumns.get("period")!=null)
		{
			IntervalChek.setSelected(true);
		}
		else
		{
			IntervalChek.setSelected(false);
		}	
		if(checkedColumns.get("isMultiplePolledData")!=null)
		{
			MultipleChek.setSelected(true);
		}
		else
		{
			MultipleChek.setSelected(false);
		}
		if(checkedColumns.get("agent")!=null)
		{
			AgentChek.setSelected(true);
		}
		else
		{
			AgentChek.setSelected(false);
		}
		if(checkedColumns.get("threshold")!=null)
		{
			ThresholdChek.setSelected(true);
		}
		else
		{
			ThresholdChek.setSelected(false);
		}
		if(checkedColumns.get("previousSeverity")!=null)
		{
			PrevSevChek.setSelected(true);
		}
		else
		{
			PrevSevChek.setSelected(false);
		}
		if(checkedColumns.get("policyName")!=null)
		{
			PolicyChek.setSelected(true);
		}
		else
		{
			PolicyChek.setSelected(false);
		}
		if(checkedColumns.get("save")!=null)
		{
			SaveChek.setSelected(true);
		}
		else
		{
			SaveChek.setSelected(false);
		}
		if(checkedColumns.get("logDirectly")!=null)
		{
			LogDirChek.setSelected(true);
		}
		else
		{
			LogDirChek.setSelected(false);
		}
		if(checkedColumns.get("parentObj")!=null)
		{
			ParentObjChek.setSelected(true);
		}
		else
		{
			ParentObjChek.setSelected(false);
		}
		if(checkedColumns.get("currentSaveCount")!=null)
		{
			CurrSaveChek.setSelected(true);
		}
		else
		{
			CurrSaveChek.setSelected(false);
		}
		if(checkedColumns.get("period")!=null)
		{
			ProtocolChek.setSelected(true);
		}
		else
		{
			ProtocolChek.setSelected(false);
		}
		if(checkedColumns.get("statsDataTableName")!=null)
		{
			StatTableNameChek.setSelected(true);
		}
		else
		{
			StatTableNameChek.setSelected(false);
		}
		if(checkedColumns.get("failureThreshold")!=null)
		{
			FailThresholdChek.setSelected(true);
		}
		else
		{
			FailThresholdChek.setSelected(false);
		}
		if(checkedColumns.get("saveOnThreshold")!=null)
		{
			saveOnThChek.setSelected(true);
		}
		else
		{
			saveOnThChek.setSelected(false);
		}
		if(checkedColumns.get("timeAvg")!=null)
		{
			timeAvgChek.setSelected(true);
		}
		else
		{
			timeAvgChek.setSelected(false);
		}
		
}
	public Properties getSelectedColumns()
	{
		selectedColumns=new Properties();
		if(StatTblChek.isSelected())
		{
			selectedColumns.put("name",tStatsTable.getText());
		}
		if(DnsNameChek.isSelected())
		{
			selectedColumns.put("dnsName",tDnsName.getText());
		}
		if(ComChek.isSelected())
		{
			selectedColumns.put("community",tCommunity.getText());
		}
		if(ActiveChek.isSelected())
		{
			selectedColumns.put("active",tActive.getText());
		}
		if(SnmpVerChek.isSelected())
		{
			selectedColumns.put("snmpVersion",tSnmpVersion.getText());
		}
		if(PortChek.isSelected())
		{
			selectedColumns.put("port",tPort.getText());
		}
		if(lastCounterChek.isSelected())
		{
			selectedColumns.put("lastCounterValue",tLastcounter.getText());
		}
		if(numTypeChek.isSelected())
		{
			selectedColumns.put("numericType",tNumType.getText());
		}
		if(grpNameChek.isSelected())
		{
			selectedColumns.put("groupName",tgrpName.getText());
		}
		if(saveAbsChek.isSelected())
		{
			selectedColumns.put("saveAbsolutes",tSaveAbs.getText());
		}
		if(logFileChek.isSelected())
		{
			selectedColumns.put("logFile",tLogFile.getText());
		}
		if(ThresholdChek.isSelected())
		{
			selectedColumns.put("threshold",tThreshold.getText());
		}
		if(FailCountChek.isSelected())
		{
			selectedColumns.put("failureCount",tFailCount.getText());
		}
		if(PollerNameChek.isSelected())
		{
			selectedColumns.put("pollerName",tPollerName.getText());
		}
		if(savePollChek.isSelected())
		{
			selectedColumns.put("savePollCount",tSavePoll.getText());
		}
		if(suffixChek.isSelected())
		{
			selectedColumns.put("suffix",tSuffix.getText());
		}
		if(OwnerChek.isSelected())
		{
			selectedColumns.put("ownerName",tOwnerName.getText());
		}
		if(PollidChek.isSelected())
		{
			selectedColumns.put("id",tPollId.getText());
		}
		if(ObjectIdChek.isSelected())
		{
			selectedColumns.put("oid",tObjectId.getText());
		}
		if(IntervalChek.isSelected())
		{
			selectedColumns.put("period",tInterval.getText());
		}
		if(MultipleChek.isSelected())
		{
			selectedColumns.put("isMultiplePolledData",tMultiple.getText());
		}
		if(AgentChek.isSelected())
		{
			selectedColumns.put("agent",tAgent.getText());
		}
		if(ThresholdChek.isSelected())
		{
			selectedColumns.put("threshold",tThreshold.getText());
		}
		if(PrevSevChek.isSelected())
		{
			selectedColumns.put("previousSeverity",tPrevSev.getText());
		}
		if(PolicyChek.isSelected())
		{
			selectedColumns.put("policyName",tPolicy.getText());
		}
		if(SaveChek.isSelected())
		{
			selectedColumns.put("save",tSave.getText());
		}
		if(LogDirChek.isSelected())
		{
			selectedColumns.put("logDirectly",tLogDir.getText());
		}
		if(ParentObjChek.isSelected())
		{
			selectedColumns.put("parentObj",tParentObj.getText());
		}
		if(CurrSaveChek.isSelected())
		{
			selectedColumns.put("currentSaveCount",tCurrSaveCount.getText());
		}
		if(ProtocolChek.isSelected())
		{
			selectedColumns.put("protocol",tProtocol.getText());
		}
		if(StatTableNameChek.isSelected())
		{
			selectedColumns.put("statsDataTableName",tStatsTableName.getText());
		}
		if(FailThresholdChek.isSelected())
		{
			selectedColumns.put("failureThreshold",tFailThreshold.getText());
		}
		if(saveOnThChek.isSelected())
		{
			selectedColumns.put("saveOnThreshold",tSaveOnThreshold.getText());
		}
		if(timeAvgChek.isSelected())
		{
			selectedColumns.put("timeAvg",tTimeAvg.getText());
		}
		if(criteria!=null && criteria.getProps().size()>0)
		{
	        		Properties properties=criteria.getProps();
	  	          for(Enumeration e=properties.propertyNames();e.hasMoreElements();)
	                    {
		              String str=(String)e.nextElement();
		              selectedColumns.put(str,properties.get(str));
	                    }
	          }
		return selectedColumns;
	}

}

























