/*
   $Id: ConnectMap.java,v 1.1 2003/02/03 07:07:52 balajir Exp 
 */


package com.adventnet.nms.mapui;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.event.*;
 
public class ConnectMap extends com.adventnet.nms.mapui.MapApplet  

{ 

        MapSymbolRendererInterface renderer; 

        MapClientAPI mcapi; 

        Vector symbols; 

        MapSymbolComponent source; 

        MapSymbolComponent dest; 

        boolean connectMode = false; 

        boolean firstTime = true; 

        Point startPoint; 

        BasicMap bmap; 

        public void constructUI(EditableMap emap) 

        { 

                super.constructUI(emap); 

                MouseInputHandler mh = new MouseInputHandler(); 

                bmap = emap.basicMap; 

                bmap.addMouseListener(mh);  

                bmap.addMouseMotionListener(mh);  

        } 

        class MouseInputHandler implements  MouseInputListener 

        { 

               public void mouseClicked(MouseEvent me) 

                { 

            /*  

            if(firstTime) 

                { 

                        model=  getModel(); 

                        renderer = model.mapSymbolRenderer; 

                        firstTime = false; 

                } 

            */ 

            // ALT is the HOT KEY for connection 

            if(me.isAltDown()) 

                { 

                    symbols = model.getSymbols(); 

                    int len = symbols.size();  

                    for(int i=0;i<len;i++) 

               { 

                MapSymbolComponent sym = model.getSymbol(i);  

                if(model.mapSymbolRenderer.downInObject(me.getX(),me.getY(),sym)) 

                {  

                        if(source == null) 

                         { 

                                source = sym; 

                                startPoint = getCenterPoint(sym); 

                                connectMode = true; 

                                setCursor(new Cursor(Cursor.HAND_CURSOR)); 

                                return; 

                        } 

                     else if((source != null) && (dest == null)) 

                        {  

                           dest = sym; 

                           connect(source,dest); 

                           source = null; 

                           dest = null; 

                           connectMode = false; 

                           me.consume(); 

                           setCursor(new Cursor(Cursor.DEFAULT_CURSOR));  

                        } 

                else 

                        { 

                                connectMode = false; 

                                setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); 

                                if(source != null) source = null; 

                        } 

                 } 

            }  

     }  

                else  

                        { 

                                connectMode = false; 

                                setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); 

                                if(source != null) source = null; 

                        } 

                    if(connectMode) connectMode = false; 

                    setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); 

                    source = null; dest = null;  

         }  

  

          public void mouseMoved(MouseEvent me)   {  

                  if(connectMode) 

                  {  

                  repaint();  

                 // paint a line on the BasicMap  

                Graphics2D g2 = (Graphics2D)bmap.getGraphics();  

                g2.setStroke(new BasicStroke(2.0f)); 

                g2.drawLine(startPoint.x,startPoint.y, me.getX(), me.getY());  

         //repaint(); 

        } 

   }  

        public void mouseDragged(MouseEvent me)  

        { 

         //do nothing here 

        }  

        public void mouseEntered(MouseEvent me) {}  

        public void mouseReleased(MouseEvent me ){} 

        public void mousePressed(MouseEvent me ){} 

        public void mouseExited(MouseEvent me ){} 

} 

     void connect(MapSymbolComponent source,MapSymbolComponent dest) 

     { 

       MapLinkComponent link = new MapLinkComponent(); 

       String sName = source.getName(); 

       String dName = dest.getName(); 

       String linkName = sName+"---"+dName;  

        // for adding > 1 links between same src and dest.  

        if(model.getLinkByName(linkName)!=null) 

        { 

                    linkName += model.getLinkCount() + 1; 

        } 

        link.setName(linkName); 

        link.setMapName(model.getName());  

        link.setSource(sName); 

        link.setDest(dName);  

        link.setSourceObj(source); 

        link.setDestObj(dest); 

      //Temporary addition, save manually 

        model.addLink(link); 

      } 

      // for getting the center point of a map symbol 

      Point getCenterPoint(MapSymbolComponent sym) 

      { 

      return new Point(sym.getX()+ (sym.getWidth()/2), sym.getY() + (sym.getHeight()/2)); 

      } 

 } 

