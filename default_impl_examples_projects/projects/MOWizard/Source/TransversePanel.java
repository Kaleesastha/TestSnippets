//$Id: TransversePanel.java,v 1.1.1.1 2006/12/08 11:57:39 gramkumar Exp $
/* $Id: TransversePanel.java,v 1.1.1.1 2006/12/08 11:57:39 gramkumar Exp $ */

/*
 * @(#)TransversePanel.java
 * Copyright (c) 1999-1999 Advent Network Management, Inc. 
 * All Rights Reserved.
 * Please read the associated COPYRIGHTS file for more details.
 * 
 * This software is the confidential and proprietary information
 * of Advent Network Management, Inc. ("Confidential Information").  
 * You shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement
 * you entered into with Advent Network Management Inc.
 */

package com.adventnet.nms.tools.mowizard;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;
import javax.swing.border.*;
import com.adventnet.nms.tools.objtorel.TransverseContainer;
import com.adventnet.nms.tools.objtorel.TransverseListener;
import com.adventnet.nms.tools.utils.ToolsUtil;
import com.adventnet.apiutils.BuilderResourceBundle;

public class TransversePanel extends JPanel implements ActionListener { 
    
    //componets used in cardPanel
    private JPanel cardPanel        = new JPanel();
    private CardLayout card;
    private int nextCounter = 0;
    private int previousCounter;
    private int noOfPanels = 0;

    private Vector panelList = new Vector();
	private Vector imageList = new Vector();


	private BuilderResourceBundle resourceBundle=ToolsUtil.getBundle();     
    //components used in buttonPanel
    private JButton nextButton     = new JButton(ToolsUtil.getMenuName(resourceBundle.getString("Next >>"),'N'));
    private JButton previousButton = new JButton(ToolsUtil.getMenuName(resourceBundle.getString("<< Previous"),'P'));
    private JButton finishButton    = new JButton(ToolsUtil.getMenuName(resourceBundle.getString("Finish"),'F'));
    private JButton cancelButton    = new JButton(ToolsUtil.getMenuName(resourceBundle.getString("Cancel"),'C'));
    private JButton helpButton = new JButton(resourceBundle.getString("Help"));
    private boolean finished=false;
    private boolean cancelled=false;
	private boolean addTransCon=true;
	private JLabel  imgLabel=null;

    private Vector listenerList = new Vector();
    private TransverseContainer TransCon;
    public TransversePanel()
    {
        this(null);
    }

    public TransversePanel(String helpUrl)
    {
        //panel declarations
        JPanel buttonPanel = new JPanel();

        //declaring gridbag
        GridBagLayout gridbag = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
	    
        //setting border and layout for the panels
        this.setLayout(new BorderLayout());
		
        buttonPanel.setLayout(gridbag);

        card = new CardLayout();
        cardPanel.setLayout(card);

        //adding components to buttonPanel 
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(10,5,5,5);

        c.gridx = 0;
        c.gridy = 0;
        gridbag.setConstraints(previousButton,c);
        buttonPanel.add(previousButton);

        c.gridx = 1;
        c.gridy = 0;
        gridbag.setConstraints(nextButton,c);
        buttonPanel.add(nextButton);

        c.gridx = 2;
        c.gridy = 0;
        gridbag.setConstraints(finishButton,c);
        buttonPanel.add(finishButton);

        c.gridx = 3;
        c.gridy = 0;
        gridbag.setConstraints(cancelButton,c);
        buttonPanel.add(cancelButton);
        
		
        c.gridx = 4;
        c.gridy = 0;
        gridbag.setConstraints(helpButton,c);

        if(helpUrl != null)
        {
            //helpButton.addActionListener(new BrowserAction(helpUrl, false));
            buttonPanel.add(helpButton);
        }
		
        //adding all the panels to this class

        this.add(cardPanel,BorderLayout.CENTER);
        this.add(buttonPanel,BorderLayout.SOUTH);

        previousButton.setEnabled(false);
        //finishButton.setEnabled(false);
		
        previousButton.addActionListener(this);
        nextButton.addActionListener(this);
        finishButton.addActionListener(this);
        cancelButton.addActionListener(this);

        previousButton.setMnemonic(KeyEvent.VK_P);
        nextButton.setMnemonic(KeyEvent.VK_N);
        finishButton.setMnemonic(KeyEvent.VK_F);
        cancelButton.setMnemonic(KeyEvent.VK_C);

        finishButton.setEnabled(false);            
        TransCon=new TransverseContainer();
        nextButton.setPreferredSize(previousButton.getPreferredSize());
        finishButton.setPreferredSize(previousButton.getPreferredSize());
        //cancelButton.setPreferredSize(previousButton.getPreferredSize());
    }

    
    public boolean isFinished() {
        return finished;
    }
    
    public boolean isCancelled() {
        return cancelled;
    }
	
	public void setImageLabel(JLabel lbl) {
		imgLabel=lbl;
	}
	
	public void setAddTransCon(boolean value) {
		addTransCon=value;
	}
	
	public void addTransverseContainer(TransverseContainer tCon) {
		TransCon=tCon;
	}

    public void addTransverseListener(TransverseListener l)
    {
        if(!listenerList.contains(l))
        {
            listenerList.addElement(l);
        }   
    }

    public void removeTransverseListener(TransverseListener l)
    {
        if(listenerList.contains(l))
        {
            listenerList.removeElement(l);
        }   
    }

    public void actionPerformed(ActionEvent e)
    {
        Object obj = e.getSource();

        if(obj == nextButton)
        {
            Vector v;
            int validationResult = 0;
            String index=null;
            synchronized(this)
            {
                v = (Vector)listenerList.clone();
            }

            TransverseListener l = (TransverseListener)v.elementAt(nextCounter);
            validationResult = l.nextActionPerformed((String)panelList
                                                     .elementAt(nextCounter));
            if(validationResult!=-1)
            {
                AbstractTransversePanel atp=null;
                while((nextCounter+1)<noOfPanels) {
                    if( (nextCounter+1)< (noOfPanels-1)) {
                        atp=(AbstractTransversePanel)v.elementAt(nextCounter+1);
                        if(!atp.isShowing()) {
                            nextCounter++;
                            previousCounter--;
                        }
                        else {
                            break;
                        }
                    }
                    else {
                        nextCounter++;
                        previousCounter--;
                        previousButton.setEnabled(true);
                        atp=(AbstractTransversePanel)v.elementAt(nextCounter);
                        atp.loadScreenData();
						imgLabel.setIcon(new ImageIcon((String)imageList.elementAt(nextCounter)));
                        card.show(cardPanel,(String)panelList.elementAt(nextCounter));
                        nextButton.setEnabled(false);
                        finishButton.setEnabled(true);
                        return;
                    }
                }
                previousButton.setEnabled(true);
                atp=(AbstractTransversePanel)v.elementAt(nextCounter+1);
                atp.loadScreenData();
				imgLabel.setIcon(new ImageIcon((String)imageList.elementAt(nextCounter+1)));
                card.show(cardPanel,(String)panelList.elementAt(nextCounter+1));
                nextCounter++;
                previousCounter--;
                if(nextCounter==(noOfPanels - 1))
                {
                    nextButton.setEnabled(false);
                    finishButton.setEnabled(true);
                }
                return;
            }
            else
            {
                return;
            }	
        }
        else if(obj == previousButton)
        {
            Vector v;
            int validationResult = 0;
            //System.out.println(" \n\n\n\n\n");
            synchronized(this)
            {
                v = (Vector)listenerList.clone();
            }
            //TransverseListener l = (TransverseListener)v.elementAt(nextCounter);
            TransverseListener l = (TransverseListener)v.elementAt(nextCounter);
            //CHECK IT OUT
            try
            {
                validationResult = l.previousActionPerformed((String)panelList
                                                             .elementAt(nextCounter));
                if(validationResult!=-1)
                {
                    AbstractTransversePanel atp=null;
                    while(previousCounter<noOfPanels) {
                        if((previousCounter)<(noOfPanels-1)) {
                            atp=(AbstractTransversePanel)v.elementAt(nextCounter-1);
							finishButton.setEnabled(false);
                            if(!atp.isShowing()) {
                                nextCounter--;
                                previousCounter++;
                            }
                            else {
                                break;
                            }
                        }
                        else {
                            nextCounter--;
                            previousCounter++;
                            nextButton.setEnabled(true);
                            atp=(AbstractTransversePanel)v.elementAt(nextCounter);
                            atp.loadScreenData();
							imgLabel.setIcon(new ImageIcon((String)imageList.elementAt(nextCounter)));
                            card.show(cardPanel,(String)panelList.elementAt(nextCounter));
                            previousButton.setEnabled(false);
                            finishButton.setEnabled(false);
                            return;
                        }						
                    } 
                    nextButton.setEnabled(true);
                    nextCounter--;
                    previousCounter++;
                    atp=(AbstractTransversePanel)v.elementAt(nextCounter);
					imgLabel.setIcon(new ImageIcon((String)imageList.elementAt(nextCounter)));
                    atp.loadScreenData();
                    card.show(cardPanel,(String)panelList.elementAt(nextCounter));
                    if(previousCounter == noOfPanels)
                    {
                        previousButton.setEnabled(false);
                    }
                    return;
                }
                else
                {
                    finishButton.setEnabled(false);
                    return;
                }
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }
            	 
        }
        else if(obj == cancelButton)
        {
            Vector v;
		   
            synchronized(this)
            {
                v = (Vector)listenerList.clone();
            }
            
            int cnt = v.size();
            for(int i = 0; i < cnt; i++)
            {
                TransverseListener l = (TransverseListener)v.elementAt(i);
                //CHECK IT OUT
                try
                {
                    l.cancelActionPerformed((String)panelList
                                            .elementAt(nextCounter));
                    cancelled=true;
                }
                catch(Exception ex)
                {
                  
                }
            }	  

        }

        else if(obj == finishButton)
        {
            Vector v;
            boolean validateResult = false;

            synchronized(this)
            {
                v = (Vector)listenerList.clone();
            }

            int cnt = v.size();
            for(int i = 0; i < cnt; i++)
            {
                TransverseListener l = (TransverseListener)v.elementAt(i);
                validateResult = l.finishActionPerformed();
                if(validateResult)
                {
                    finishButton.setEnabled(false);
                    finished=true;
                }	
				else{
					//This loop is added to enable the finish button if validate result is false
					finishButton.setEnabled(true);
					finished=false;
				}
            }	  
        }
    }

    public void setEnableFinishButton(boolean bool)
    {
        finishButton.setEnabled(bool);
    }

    // DO CLICK BUSINESS WONT BE GOOD FOR MORE SCREENS
    public void showFirstComponent()
    {
        while(nextCounter > 0)
        {
            previousButton.doClick();
        }
       
    }

    public void showLastComponent()
    {
        while(nextCounter < noOfPanels-1)
        {
            nextButton.doClick();
        }
       
    }
    
    public void initialize() {
        finished=false;
        cancelled=false;
        previousCounter=noOfPanels;
        nextCounter=0;
        previousButton.setEnabled(false);
        nextButton.setEnabled(true);
        finishButton.setEnabled(false);
		imgLabel.setIcon(new ImageIcon((String)imageList.elementAt(nextCounter)));
        card.show(cardPanel,(String)panelList.elementAt(nextCounter));
		AbstractTransversePanel atp=(AbstractTransversePanel)listenerList.elementAt(nextCounter);
		atp.loadScreenData();
    }
    
    public void addComponents(String Identifier,Object comp,String imagePath) throws NullPointerException
    {
        addTransverseListener((TransverseListener)comp);	
		if(addTransCon) {
			TransCon.addTransverseComponent(Identifier,comp);
	        ((AbstractTransversePanel)comp).addTransverseContainer(TransCon);
		}
		imageList.addElement(imagePath);
        cardPanel.add(Identifier,(Component)comp);
        panelList.addElement(Identifier);
        noOfPanels++;
        previousCounter = noOfPanels;
    }
}
