/*
$Id: MapSearchPanel.java,v 1.6 2008/10/21 05:39:09 aravinds Exp $
*/
/*
 * MapSearchPanel.java
 *
 * Created on July 23, 2005, 12:07 PM
 */

package com.adventnet.nms.mapui;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.tree.*;
import java.util.*;
import java.awt.*;
import java.net.*;
import javax.swing.text.*;
import javax.swing.plaf.basic.*;

import com.adventnet.nms.util.*;



/**
 *
 * @author  balachandar
 */
public class MapSearchPanel extends javax.swing.JDialog implements Runnable,ActionListener,KeyListener{
    
    /**
     * Creates new form MapSearchPanel 
     */    
    public MapSearchPanel(String currentMapName,Frame parentWindow,MapApplet app)
    {
        super(parentWindow);
             selectedMap = currentMapName;
        this.mapApplet = app;
        initComponents();
        searchHistory = new Vector();
        searchHistory = MapApplet.getSearchHistory();
        addKeyListener(this);
       /* jPanel8.setVisible(false);
               jPanel1.setSize(new Dimension(410,150));
               jPanel1.updateUI();
               setSize(410,245);
               validate();*/
               //advPanelVisible = false;
               KeyStroke escStroke = KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ESCAPE,0);
                ((JComponent)getRootPane()).registerKeyboardAction(this,"close",escStroke,JComponent.WHEN_IN_FOCUSED_WINDOW);
                
                findNextButton.getRootPane().setDefaultButton(findNextButton);

    }
    
    public void show()
    {
        initCombo();
        super.show();
    }
    public void keyPressed(KeyEvent e)
    {
       
    }
      
   public void initializeAll()
   {
       JTextComponent editor = (JTextComponent)searchCriteria.getEditor().getEditorComponent();
       //editor.addKeyListener(this);
       
       editor.setText("");
       searchCriteria.setEditable(true);
       searchCriteria.requestFocus();
       //symbolSearchRadio.setSelected(true);
         
       treeModel = null;
       mapClientApi = null;
       nodes = null;
       symbolsFound = null;
       DefaultMapModel dmm  = mapApplet.model;
       if (dmm!=null)
       {
    	   selectedMap = mapApplet.model.getMapName();
       }else
       {
    	   String nodeid = NmsUiAPI.getCurrentNodeId();
    	   if (nodeid.indexOf(".netmap")>0)
    	   {
    		   mapApplet = MapClientAPI.getInstance().getMapCanvas(nodeid);
    		   selectedMap = mapApplet.model.getMapName();
    	   }else
    	   {
    		   showFindStatus(NmsClientUtil.GetString(" Please select a Map in which Search must be done"), 1);//No internationalization
    	   }
       }
       selectedMapIndex = 0;
       selectedSymbolIndex = 0;
       showFindStatus("");                     
   }
   
   public void initCombo()
   {
       initializeAll(); 
       KeyStroke escStroke = KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ESCAPE,0);
                ((JComponent)getRootPane()).registerKeyboardAction(this,"close",escStroke,JComponent.WHEN_IN_FOCUSED_WINDOW);
                
                findNextButton.getRootPane().setDefaultButton(findNextButton);
       searchHistory = MapApplet.getSearchHistory();
       jPanel6.remove(searchCriteria);
       
       searchCriteria = new JComboBox(searchHistory);
       searchCriteria.setEditable(true);
       searchCriteria.setFont(NmsClientUtil.getFont("DIALOG"));
       searchCriteria.setPreferredSize(new java.awt.Dimension(190, 20));
       searchCriteria.setBorder(new javax.swing.border.EtchedBorder());
       jPanel6.add(searchCriteria,1);
        searchCriteria.setUI(xCombo);
       jPanel6.updateUI();
       validate();
       if(searchHistory.size()>0)
       {
            new AutoCompletion(searchCriteria);
       }
   }
      
   public void actionPerformed(ActionEvent e)
   {
       String command = e.getActionCommand();
        if(command.equals("findNext"))//No Internationalisation
        {
        	//MapSearch issue fix. Patch integration 22200
            if (mapApplet.model==null)
             {
            	String nodeid = NmsUiAPI.getCurrentNodeId();
            	if (nodeid.indexOf(".netmap")>0)
            	{
            		mapApplet = MapClientAPI.getInstance().getMapCanvas(nodeid);
            		selectedMap = mapApplet.model.getMapName();
            	}else
            	{
            		showFindStatus(NmsClientUtil.GetString(" Please select a Map in which Search must be done"), 1);//No internationalization
            		return;
            	}
             }
            if(searchString != null)
            {
                JTextComponent editor = (JTextComponent)searchCriteria.getEditor().getEditorComponent();
                   if(searchString.equals(editor.getText()))
                   {
                             find(false);
                   }else
                   {
                             searchString = editor.getText();
                             initializeAtTheMiddle();
                             find(false);
                    }
                //symbolSearchRadio.setFocusable(true);
                
                MapApplet.setSearchHistory(editor.getText());
              }
            
        }
        else if(command.equals("nextMap"))
        {
           
            if(searchString != null)
            {
                JTextComponent editor = (JTextComponent)searchCriteria.getEditor().getEditorComponent();
                     if(searchString.equals(editor.getText()))
                     {
                            find(true);
                     }else
                     {
                            searchString = editor.getText();
                            initializeAtTheMiddle();
                            find(true);
                     }
             }

        }
       
        else if(command.equals("mapSymbolSearch"))
        {
           isSymbolSearch = true;
           jLabel1.setText(NmsClientUtil.GetString("javaui.Search.MapSearch.SymbolName"));
        }
        else if(command.equals("mapLinkSearch"))
        {
           isSymbolSearch = false;
           jLabel1.setText(NmsClientUtil.GetString("javaui.Search.MapSearch.LinkName"));
        }
        else if(command.equals("close"))
        {
           dispose();
        }
        else if(command.equals("newSearch"))
        {
           
           if(searchString != null)
           {
               initializeAll();
               
           }
           
        }
        else if(command.equals("help"))
        {
           NmsClientUtil.showHelpBasedOnKey("Map_Search");//No Internationalisation
        }
        else if(command.equals("options"))
        {
           if(!advPanelVisible)
           {
           
           jPanel8.setVisible(true);
           advPanelVisible = true;
           setSize(410, 285);
           jPanel1.updateUI();
           //optionsButton.setText("<<"+NmsClientUtil.GetString("javaui.Search.MapSearch.CloseOptions"));
           }
           else
           {
               //jPanel1.setSize(new Dimension(400,150));
               jPanel8.setVisible(false);
               jPanel1.setSize(new Dimension(410,150));
               jPanel1.updateUI();
               setSize(410,245);
               //optionsButton.setText(NmsClientUtil.GetString("javaui.Search.MapSearch.Options"));
               advPanelVisible = false;
           }
           validate();
        }
        else if(command.equals("up"))
        {
           moveUp = true;
        }
        else if(command.equals("down"))
        {
           moveUp = false;
        }
        else if(command.equals("wholeword"))
        {
           if(matchWholeWord == false)
           {
                   matchWholeWord = true;
           }
           else
           {
                   matchWholeWord = false;
           }
           //Start the search as new
           initializeAtTheMiddle();

        }
        else if(command.equals("ignoreCase"))
        {
           if(matchCase == false)
           {
                     matchCase = true;
           }
          else
           {
                    matchCase = false;
           }
            //Start the search as new
           initializeAtTheMiddle();
        }
   }
   
   public void dispose()
   {
       super.dispose();
       //JOptionPane.showMessageDialog(null,"AD","BM",JOptionPane.PLAIN_MESSAGE);
   }
   
   private void initializeAtTheMiddle()
   {
             treeModel = null;
             mapClientAPI = null;
             nodes = null;
             symbolsFound = null;
             selectedMapIndex = 0;
             selectedSymbolIndex = 0;

    }

   public void run()
   {
	   		 findNextButton.setEnabled(false);
             NmsClientUtil.busyCursor(this);
             setCursor(new Cursor(Cursor.WAIT_CURSOR));
             DefaultMapModel m = mapClientAPI.loadMap(selectedMap);
             MapApplet.firstInstance.openSubMapInSameWindow(selectedMap);
             if((MapApplet.openMultipleMaps) && (MapApplet.mapAppletObjects.containsKey(selectedMap)))
             {
                      mapApplet = (MapApplet) MapApplet.mapAppletObjects.get(selectedMap);
             }
             if(m!=null)
             {
                      m.reLayoutMap();
             }
             startSearchingInNewMap(searchString);
             setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
             findNextButton.setEnabled(true);
             NmsClientUtil.normalCursor(this);
     }

     public Icon getNmsIcon(String image)
     {
         String imagePath = null;
         if(NmsClientUtil.applet.getDocumentBase() != null)
         {
             imagePath = NmsClientUtil.applet.getDocumentBase()+"../images/"+image; //No Internationalization
             
         }
         else
             imagePath = image;
         try
         {
             
             return NmsClientUtil.getImageIcon(new URL(imagePath));
         }
         catch(Exception e)
         {
             System.out.println("Exception while loading the image file for the search panel");
             return null;
         }
     }
   private void showFindStatus(String str)
                {
                        showFindStatus(str , 0);
                }

                private void showFindStatus(String str , int critical)
                {
                        statusPanel.setOpaque(true);//To fix the issue background color is not visible in GTK
			status.setText(NmsClientUtil.GetString (str));
                        if(critical < 0)
                        {
                                //Green - succes
                                // negative
                                statusPanel.setBackground(new Color(0,128,0));
                                //statusPanel.setBackground(new Color(0,215,0));
                        }else if(critical == 0)
                        {
                                //Blue - INFO : DEFAULT
                                // zero
                                statusPanel.setBackground(new Color(0,0,128));
                                //statusPanel.setBackground(new Color(0,0,215));
                        }else
                        {
                                //Red - failure
                                //positive
                                statusPanel.setBackground(new Color(128,0,0));
                                //statusPanel.setBackground(new Color(215,0,0));
                                Toolkit.getDefaultToolkit().beep();
                        }
                        //NmsClientUtil.showStatusOnLabel (NmsClientUtil.GetString (str) , NmsClientUtil.ERROR_COLOR);
                }

   public void find(boolean findInNextMap)
   {
         if(searchString == null)
         {
                    showFindStatus(NmsClientUtil.GetString(" Please Specify the Search Criteria"), 1);
                    searchCriteria.requestFocus();
                    return;
          }else if("".equals(searchString.trim()))//No Internationalisation
          {
                    showFindStatus(NmsClientUtil.GetString(" Please Specify the Search Criteria") , 1);
                    searchCriteria.requestFocus();
                    return;
          }
         //MapSearch issue fix. Patch integration 22200
         findNextButton.setEnabled(false);
         newSearchButton.setEnabled(false);
          //very first time
         //MapProblemDueToChangesOnServer        if(treeModel == null)
          if(nodes == null)
          {
                   //nodes = (Vector)((MapApplet.mapNames).clone());
                   nodes = (Vector)MapClientAPI.getInstance().getTheMapTreeAlone().clone();
                   for (int i=0;i<nodes.size();i++)
                   {
                              if( selectedMap.trim().equals(((String)nodes.elementAt(i)).trim()))
                              {
                                       selectedMapIndex = i;
                                        break;
                               }
                     }
                     if(!findInNextMap)
                     {
                               if( ( (symbolsFound = doSearch(searchString)) != null)&&(symbolsFound.size() > 0))
                               {
                                        /*if(isSymbolSearch)
                                        {*/
                                            MapSymbolComponent mapobj = (MapSymbolComponent)symbolsFound.firstElement();
                                            showSelectedSymbol(mapobj);
                                        /*}
                                        else
                                        {
                                            MapLinkComponent mapLinkObj = (MapLinkComponent)symbolsFound.firstElement();
                                            showSelectedSymbol(mapLinkObj);
                                        }*/
                                }else
                                {
                                    //MapSearch issue fix. Patch integration 22200
                                	findNextButton.setEnabled(true);
                                    newSearchButton.setEnabled(true);
                                	showFindStatus(NmsClientUtil.GetString(" No Matching Symbol Found In ")+removeDotNetmap(selectedMap)+NmsClientUtil.GetString(" Continue In Next Map?") , 1 );
                                        //nextMapButton.requestFocus();
                                }
           }else
           {
                       searchInNextMap(searchString);
           }

          }else if( (symbolsFound != null)&&(symbolsFound.size() > 0) )
          {
                        if(!findInNextMap)
                       {
                                 if(!moveUp)
                                 {
                                          if((++selectedSymbolIndex) > (symbolsFound.size()-1))
                                          {
                                                  searchInNextMap(searchString);
                                          }else
                                          {
                                                   MapSymbolComponent mapobj = (MapSymbolComponent)symbolsFound.elementAt(selectedSymbolIndex);
                                                   showSelectedSymbol(mapobj);
                                           }
                                     }
                                    else
                                    {
                                            if((--selectedSymbolIndex) < 0)
                                            {
                                                   searchInNextMap(searchString);
                                           }else
                                            {
                                                    MapSymbolComponent mapobj = (MapSymbolComponent)symbolsFound.elementAt(selectedSymbolIndex);
                                                    showSelectedSymbol(mapobj);
                                             }
                                    }
                                }else
                                {
                                        searchInNextMap(searchString);
                                }

                        }else
                        {

                                searchInNextMap(searchString);
                        }
                }


   private void searchInNextMap( String searchString )
                {
                        //I don't Think this will appear.
                        showFindStatus(NmsClientUtil.GetString(" Start searching in Next Map ......"));
                        if(!moveUp)
                        {
                                selectedMap = (String)nodes.elementAt((++selectedMapIndex)%nodes.size());
                        }
                        else
                        {
                                if((selectedMapIndex-1)<0)
                                {
                                        selectedMapIndex = nodes.size();
                                }
                                selectedMap = (String)nodes.elementAt((--selectedMapIndex)%nodes.size());
                        }
                        if(MapApplet.mapAppletObjects.containsKey(selectedMap))
                        {
                                mapApplet = (MapApplet) MapApplet.mapAppletObjects.get(selectedMap);
                                //applet.openSubMapInSameWindow(selectedMap);
                                MapApplet.firstInstance.openSubMapInSameWindow(selectedMap);
                                startSearchingInNewMap(searchString);
                        }else
                        {
                                showFindStatus(NmsClientUtil.GetString("Loading . . . . ")+removeDotNetmap(selectedMap)  );
                                //map is not yet loaded.
                                //This Thread is needed to show the status.
                                if(mapClientAPI == null)
                                        mapClientAPI = MapClientAPI.getInstance();
                                //Commenting below lines for MapSearch Issue fix. Patch integration 22200
                                //Multithreading causes problem due to conflicting mapapplet instances hence run method directly called with out instantiating a seperate thread.
                                //Thread loadThread = new Thread(this);
                                //loadThread.start();
                                run();                        
                        }
                }

   private void startSearchingInNewMap(String searchString )
                {
                        if( ( (symbolsFound = doSearch(searchString)) != null)&&(symbolsFound.size() > 0))
                        {
                                MapSymbolComponent mapobj = null;
                                if(!moveUp)
                                {
                                        selectedSymbolIndex = 0;
                                        mapobj = (MapSymbolComponent)symbolsFound.firstElement();
                                }else
                                {
                                        selectedSymbolIndex = symbolsFound.size() - 1;
                                        mapobj = (MapSymbolComponent)symbolsFound.lastElement();
                                }
                                showSelectedSymbol(mapobj);
                        }else
                        {
                        	//MapSearch issue fix. Patch integration 22200
                        	findNextButton.setEnabled(true);
                            newSearchButton.setEnabled(true);
                        	showFindStatus(NmsClientUtil.GetString(" No Matching Symbol Found In ")+removeDotNetmap(selectedMap)+NmsClientUtil.GetString(
" Continue In Next Map?") , 1);
                                //nextMapButton.requestFocus();
                        }
                }

   
   private Vector doSearch (String s1)
                {
                        //To deselect all the symbols
                        
                        mapApplet.clearAllSelectedSymbols ();
                        mapApplet.containSymbols.removeAllElements();
                        Vector returnVec = new Vector();
                        //Patch integration 22200
                        if ((mapApplet.mapClientInitializer.masclient != null) && (mapApplet.mapClientInitializer.masclient.connected))
                        {
                                // To select the symbols
                                boolean foundGroup = false;
                                DefaultMapModel mapModel = (DefaultMapModel) mapApplet.mapClientInitializer.cacheMapTable.get (mapApplet.mapFile);

                                if(mapModel != null)
                                {
                                        //Vector mapSymbols = mapModel.symbols;
                    //  To make the find tool to search the Groups also.
                    Vector mapSymbols = mapModel.getMapObjects(true,true,false);
                                        Vector temp = new Vector();
                                        int view = mapApplet.model.currentView;
                                        boolean open = false;
                                        if(mapSymbols != null)
                                        {
                                       /* if(isSymbolSearch)
                                        {*/
                                        for (int cnt = 0; cnt < mapSymbols.size (); cnt++)
                                        {

                                                {
                                                        view = mapApplet.model.currentView;
                                                        MapSymbolComponent mapobj = (MapSymbolComponent)mapSymbols.elementAt(cnt);
                                                        if (mapobj.getProperty ("label") != null &&//No Internationalisation
                                                                        checkOneString(s1,mapobj.getProperty("label")))//No Internationalisation
                                                        {
                                                                String grpName = mapobj.getGroupName();
                                                                MapGroupComponent grp = mapModel.getGroupByName(grpName);

                                                                if(view == MapConstants.EXPANDED_VIEW)
                                                                {

                                                                        if( mapModel.currentlyOpenGroupSymbols.contains(grp))
                                                                        {
                                                                                temp.addElement(mapobj);
                                                                                //returnVec.addElement(mapobj);
                                                                        }
                                                                        else
                                                                        {
                                                                                open =true;
                                                                                MapSymbolComponent mSymb = changeViewAndReturnSymbol(mapModel,grp,mapobj);
                                                                                returnVec.addElement(mSymb);
                                                                        }
                                                                }
else
                                                                {

                                   
                                    //                                    if(grp==null)
                                    returnVec.addElement(mapobj);
                                                                //      else
                                    //  returnVec.addElement(grp);

                                                                }


                                                }
                                        }
                                        
                                  }
                                       
                                       /* }//end if(isSymbolSearch)    
                                        else
                                        {*/
                                            Vector mapLinks = mapModel.getMapObjects(false,false,true);
                                            
                                            for(int cnt = 0;cnt<mapLinks.size();cnt++)
                                            {
                                                MapSymbolComponent mapObj = (MapSymbolComponent)mapLinks.elementAt(cnt);
                                                if (mapObj.getProperty ("name") != null && checkOneString(s1,mapObj.getProperty("name")))
                                                {
                                                   returnVec.addElement(mapObj);
                                                }
                                            }
                                        //}
                                 }
                    if(temp!=null && temp.size()!=0 && open)
                    {
                        for(int j=0 ;j<temp.size();j++)
                                          {
                                                  returnVec.addElement(mapModel.getGroupByName(((MapSymbolComponent)temp.elementAt(j)).getGroupName()));
                                          }
                                  }
                                  else if(temp!=null && temp.size()!=0)
                                  {
                                          for(int k=0 ;k<temp.size();k++)
                                          {
                                                returnVec.addElement((MapSymbolComponent)temp.elementAt(k));
                                          }
                                  }
                                }
                        }
                        
                        return returnVec;
                }
                
                private MapSymbolComponent changeViewAndReturnSymbol(DefaultMapModel mapModel,MapGroupComponent grp,MapSymbolComponent mapobj)
                {
                    mapModel.setCurrentView(MapConstants.MIXED_VIEW);
                        mapModel.setCurrentGroup(null);
                        mapModel.layoutMap();
                        ((DefaultMapModel)(mapApplet.mapClientInitializer.cacheMapTable.get(mapApplet.currentMapViewed))).currentView = MapConstants.MIXED_VIEW;
                        ((DefaultMapModel)(mapApplet.mapClientInitializer.cacheMapTable.get(mapApplet.currentMapViewed))).currentGroup = null;
                        mapApplet.clearAllSelectedSymbols();
                        mapApplet.updateMenuBar();
                        mapApplet.emap.basicMap.alterBuffer();
                        if(grp!=null)
                        {
                                return grp;
                        }
                        else
                        {
                                return mapobj;
                                
                        }

                }
   
                private boolean checkOneString(String s1,String s2)
                {
                     if(!matchCase)
                     {
                         s1 = s1.toLowerCase();
                         s2 = s2.toLowerCase();
                     }
                     if(matchWholeWord)
                     {
                         s1 = "<"+s1+">";
                         
                     }
                     return NmsClientUtil.checkOneString(s1,s2);
                }
   
                private String removeDotNetmap(String mapName)
                {
                    if(mapName == null)
                        return "";
                    int index = mapName.indexOf(".netmap");
                    if(index != -1)
                        return mapName.substring(0,index);
                    return mapName;
                        
                }
  
 //This function is to make auto scroll to show selected symbol when do a search
                //This is called for the first selected symbol from search result.
                private void showSelectedSymbol (MapSymbolComponent mapobj)
                {
                	//MapSearch issue fix. Patch integration 22200 
                	findNextButton.setEnabled(true);
                     newSearchButton.setEnabled(true);
                		if(mapobj != null)
                        {
                                mapApplet.clearAllSelectedSymbols ();
                                mapApplet.emap.basicMap.alterBuffer();
                                mapApplet.containSymbols.removeAllElements();

                // In order to search Groups also .. balaji.r.
                if(mapobj instanceof MapGroupComponent)
                {
                    showFindStatus(NmsClientUtil.GetString(" Symbol Found ")+"( "+mapobj.getName()+" )" , -1 );
                    mapobj.setSelected (true);
                    mapApplet.selectedSymbols.addElement (mapobj);

                }
                else
                {
                    int view = mapApplet.model.currentView;
                    String grpName = mapobj.getGroupName();
                    DefaultMapModel mapModel = (DefaultMapModel) mapApplet.mapClientInitializer.cacheMapTable.get (mapApplet.mapFile);
                    MapGroupComponent grp = mapModel.getGroupByName(grpName);

                    if(grp!=null &&view != MapConstants.EXPANDED_VIEW)
                    {
                        showFindStatus(NmsClientUtil.GetString(" Symbol Found ")+"( "+mapobj.getName()+" )" +NmsClientUtil.GetString(" in Group ")+"( "+grp.getName()+" )" , -1 );
                        grp.setSelected (true);
                        mapApplet.selectedSymbols.addElement (grp);
                    }
                    else if(grp==null || view == MapConstants.EXPANDED_VIEW)
                    {
                        showFindStatus(NmsClientUtil.GetString(" Symbol Found ")+"( "+mapobj.getName()+" )" , -1 );
                        mapobj.setSelected (true);
                        mapApplet.selectedSymbols.addElement (mapobj);
			mapApplet.selectedSymbols1.addElement(mapobj.getKey());

                    }
                }
boolean isZooming = mapApplet.model.virtualWidth > mapApplet.model.viewWidth
                                        || mapApplet.model.virtualHeight > mapApplet.model.viewHeight;
                                //Temporary Fix
                                if((mapApplet.model.windowHeight == 0)||(mapApplet.model.windowWidth == 0))
                                {
                                        isZooming = false;
                                }

                                if (isZooming)
                                {
                                        int zx = mapobj.p.x+mapobj.dim.width/2-mapApplet.model.viewWidth/2;
                                        if(zx < 0)
                                        {
                                                if(mapApplet.model.virtualWidth > (mapobj.p.x + mapApplet.model.viewWidth))
                            mapApplet.model.zoomX = mapobj.p.x;
                                                else
                            mapApplet.model.zoomX = mapobj.p.x - (mapobj.p.x + mapApplet.model.viewWidth - mapApplet.model.virtualWidth);
                                        }
                                        else if(zx > (mapApplet.model.virtualWidth - mapApplet.model.viewWidth))
                                        {
                                                mapApplet.model.zoomX = mapApplet.model.virtualWidth - mapApplet.model.viewWidth;
                                        }
                                        else
                                        {
                                                mapApplet.model.zoomX = zx;
                                        }

                                        int zy = mapobj.p.y+mapobj.dim.height/2-mapApplet.model.viewHeight/2;
                                        if(zy < 0 )
                                        {
                                                if(mapApplet.model.virtualHeight > (mapobj.p.y + mapApplet.model.viewHeight))
                            mapApplet.model.zoomY =mapobj.p.y;
                                                else
                                                {
                                                        mapApplet.model.zoomY = mapobj.p.y - (mapobj.p.y + mapApplet.model.viewHeight - mapApplet.model.virtualHeight);
                                                }
                                        }
                                        else if(zy > (mapApplet.model.virtualHeight - mapApplet.model.viewHeight))
                                        {
                                                mapApplet.model.zoomY = mapApplet.model.virtualHeight - mapApplet.model.viewHeight;
}
                                        else
                                        {
                                                mapApplet.model.zoomY = zy;
                                        }


                                        mapApplet.emap.setupScrollBars ();
                                }
                                //mapApplet.repaint ();

                                mapApplet.emap.setupScrollBars ();
                                mapApplet.emap.basicMap.alterBuffer();
                                mapApplet.updateMenuBar ();
                        }
                }
                
                public void focusGained(FocusEvent e)
                {
                   
                }
                
                public void focusLost(FocusEvent e)
                {
                    
                }
                
               /* public void keyPressed(KeyEvent e)
                {
                 //   JOptionPane.showMessageDialog(null,"KeyPressed","Key",JOptionPane.PLAIN_MESSAGE);
                    //JDialog jd = (JDialog)e.getSource();
                    if(e.getKeyCode () == KeyEvent.VK_ESCAPE)
                    {
                        
                        dispose();
                    }

                }*/
                public void keyTyped(KeyEvent e)
                {
                    
                }
                
                public void keyReleased(KeyEvent e)
                {
                    
                }
                public void dispose(JDialog jd)
                {
                    super.dispose();
                }
                
                

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        searchCriteria = new javax.swing.JComboBox();
        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        upButton = new javax.swing.JRadioButton();
        downButton = new javax.swing.JRadioButton();
        jPanel10 = new javax.swing.JPanel();
        ignoreCaseCheckBox = new javax.swing.JCheckBox();
        matchWordCheckBox = new javax.swing.JCheckBox();
        jPanel7 = new javax.swing.JPanel();
        findNextButton = new javax.swing.JButton();
        newSearchButton = new javax.swing.JButton();
        closeButton = new javax.swing.JButton();
        statusPanel = new javax.swing.JPanel();
        status = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Search");
        jPanel1.setMaximumSize(new java.awt.Dimension(400, 200));
        jPanel1.setPreferredSize(new java.awt.Dimension(400, 190));
        jPanel1.addKeyListener(this);
        jPanel2.setLayout(new java.awt.BorderLayout());

        jPanel2.setPreferredSize(new java.awt.Dimension(400, 30));
        jLabel2.setPreferredSize(new java.awt.Dimension(400, 30));
        jLabel2.setIcon(getNmsIcon("map_search_topband.png"));
        jPanel2.add(jLabel2, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel2);

        jPanel3.setBorder(new javax.swing.border.EtchedBorder());
        jPanel3.setPreferredSize(new java.awt.Dimension(400, 90));
        jPanel6.setPreferredSize(new java.awt.Dimension(390, 80));
        jLabel1.setFont(NmsClientUtil.getFont("DIALOG"));
        jLabel1.setText(NmsClientUtil.GetString("javaui.Search.MapSearch.SymbolName"));
        jLabel1.setPreferredSize(new java.awt.Dimension(175, 20));
        jPanel6.add(jLabel1);

        searchCriteria.setBackground(javax.swing.UIManager.getDefaults().getColor("InternalFrame.borderLight"));
        searchCriteria.setEditable(true);
        searchCriteria.setFont(NmsClientUtil.getFont("DIALOG"));
        searchCriteria.setBorder(new javax.swing.border.EtchedBorder());
        searchCriteria.setPreferredSize(new java.awt.Dimension(200, 20));
        xCombo = new ExtendedBasicComboBoxUI();
        searchCriteria.setUI(xCombo);
        searchCriteria.addKeyListener(this);
        //new AutoCompletion(searchCriteria);
        jPanel6.add(searchCriteria);

        jPanel8.setLayout(new java.awt.GridBagLayout());

        jPanel8.setBorder(new javax.swing.border.BevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jPanel8.setPreferredSize(new java.awt.Dimension(390, 40));
        jPanel9.setPreferredSize(new java.awt.Dimension(107, 30));
        buttonGroup2.add(upButton);
        upButton.setFont(NmsClientUtil.getFont("DIALOG"));
        upButton.setMnemonic('U');
        upButton.setText(NmsClientUtil.GetString("javaui.Search.MapSearch.Up"));
        upButton.setActionCommand("up");
        upButton.addActionListener(this);
        jPanel9.add(upButton);

        buttonGroup2.add(downButton);
        downButton.setFont(NmsClientUtil.getFont("DIALOG"));
        downButton.setMnemonic('D');
        downButton.setSelected(true);
        downButton.setText(NmsClientUtil.GetString("javaui.Search.MapSearch.Down"));
        downButton.setActionCommand("down");
        downButton.addActionListener(this);
        downButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                downButtonActionPerformed(evt);
            }
        });

        jPanel9.add(downButton);

        jPanel8.add(jPanel9, new java.awt.GridBagConstraints());

        jPanel10.setBorder(new javax.swing.border.EmptyBorder(new java.awt.Insets(1, 1, 1, 1)));
        jPanel10.setMaximumSize(null);
        jPanel10.setMinimumSize(null);
        ignoreCaseCheckBox.setFont(NmsClientUtil.getFont("DIALOG"));
        ignoreCaseCheckBox.setMnemonic('I');
        ignoreCaseCheckBox.setText(NmsClientUtil.GetString("javaui.Search.MapSearch.IgnoreCase"));
        ignoreCaseCheckBox.setActionCommand("ignoreCase");
        ignoreCaseCheckBox.addActionListener(this);
        jPanel10.add(ignoreCaseCheckBox);

        matchWordCheckBox.setFont(NmsClientUtil.getFont("DIALOG"));
        matchWordCheckBox.setMnemonic('W');
        matchWordCheckBox.setText(NmsClientUtil.GetString("javaui.Search.MapSearch.WholeWord"));
        matchWordCheckBox.setActionCommand("wholeword");
        matchWordCheckBox.addActionListener(this);
        jPanel10.add(matchWordCheckBox);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 18, 0, 0);
        jPanel8.add(jPanel10, gridBagConstraints);

        jPanel6.add(jPanel8);

        jPanel3.add(jPanel6);

        jPanel1.add(jPanel3);

        jPanel7.setLayout(new java.awt.GridBagLayout());

        jPanel7.setFont(new java.awt.Font("Dialog", 0, 10));
        jPanel7.setPreferredSize(new java.awt.Dimension(390, 35));
        findNextButton.setFont(NmsClientUtil.getFont("DIALOG"));
        findNextButton.setMnemonic('n');
        findNextButton.setText(NmsClientUtil.GetString("javaui.Search.MapSearch.FindNext"));
        findNextButton.setActionCommand("findNext");
        findNextButton.addActionListener(this);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel7.add(findNextButton, gridBagConstraints);

        newSearchButton.setFont(NmsClientUtil.getFont("DIALOG"));
        newSearchButton.setText(NmsClientUtil.GetString("javaui.Search.MapSearch.NewSearch"));
        newSearchButton.setActionCommand("newSearch");
        newSearchButton.addActionListener(this);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 0);
        jPanel7.add(newSearchButton, gridBagConstraints);

        closeButton.setFont(NmsClientUtil.getFont("DIALOG"));
        closeButton.setText(NmsClientUtil.GetString("javaui.Search.MapSearch.Close"));
        closeButton.setActionCommand("close");
        closeButton.addActionListener(this);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 0);
        jPanel7.add(closeButton, gridBagConstraints);

        jPanel1.add(jPanel7);

        statusPanel.setLayout(new java.awt.BorderLayout());

        statusPanel.setPreferredSize(new java.awt.Dimension(400, 15));
        status.setBackground(new java.awt.Color(0, 255, 204));
        status.setFont(new java.awt.Font("Dialog", 0, 10));
        status.setForeground(new java.awt.Color(255, 255, 255));
        status.setText(NmsClientUtil.GetString("javaui.Search.MapSearch.Ready"));
        status.setPreferredSize(new java.awt.Dimension(400, 15));
        statusPanel.add(status, java.awt.BorderLayout.CENTER);

        jPanel1.add(statusPanel);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }
    // </editor-fold>//GEN-END:initComponents

    private void downButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_downButtonActionPerformed
// TODO add your handling code here:
    }//GEN-LAST:event_downButtonActionPerformed
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new MapSearchPanel(new javax.swing.JFrame(), true).setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JButton closeButton;
    private javax.swing.JRadioButton downButton;
    private javax.swing.JButton findNextButton;
    private javax.swing.JCheckBox ignoreCaseCheckBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JCheckBox matchWordCheckBox;
    private javax.swing.JButton newSearchButton;
    private javax.swing.JComboBox searchCriteria;
    private javax.swing.JLabel status;
    private javax.swing.JPanel statusPanel;
    private javax.swing.JRadioButton upButton;
    // End of variables declaration//GEN-END:variables
    boolean moveUp = false;
    boolean matchCase = true;
    DefaultTreeModel treeModel;
    MapClientAPI mapClientApi;
    Vector nodes, symbolsFound;
    String selectedMap;
    MapApplet applet,mapApplet;
    int selectedMapIndex;
    int selectedSymbolIndex;
    boolean matchWholeWord = false;
    static String searchString = "";//No Internationalisation
    MapClientAPI mapClientAPI;
    private boolean isSymbolSearch = true;
    private Vector searchHistory = null;
    private boolean advPanelVisible = false;
    private boolean firstTime = true;
    private ExtendedBasicComboBoxUI xCombo= null; //to remove the button that is available in the jcombobox
}

class ExtendedBasicComboBoxUI extends BasicComboBoxUI
{
     public ExtendedBasicComboBoxUI()
     {
         
     }
     public void installComponents()
     {
         super.installComponents();
         comboBox.setBackground(new Color(255,255,255));
         comboBox.remove(arrowButton);
   
       
     }
}
