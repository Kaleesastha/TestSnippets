//$Id: SwitchMapRenderer.java,v 1.3 2007/08/28 09:04:57 karanmercy Exp $

package com.adventnet.nms.mapui;

import java.awt.*;
import java.awt.image.*;

import com.adventnet.nms.severity.SeverityInfo;
import com.adventnet.nms.util.NmsClientUtil;
import java.util.Properties;
public class SwitchMapRenderer  extends MapSymbolRendererImpl_3 
{
   private int portCount = 0;
   private String level = "";
   private boolean isPreviousClicked=false;
   private boolean isNextClicked=false;
    public void paintShapeAndSeverity(Graphics g,MapSymbolComponent mapSymbol,Point p,Dimension d, Color bg,int type)
    {
               if ( (p==null) || (d==null) || (g==null) )
                {
                        System.err.println("Invalid values to paint icon."); //No Internationalization
                        return;
                }
                if(mapSymbol.getParent()==null) //Switch
                {   //do nothing
                }
                else if((mapSymbol.getName()).equalsIgnoreCase("next")) //next button
                {
                        //do nothing
                }
                else if((mapSymbol.getName()).equalsIgnoreCase("previous")) // previous button
                {
                        //do nothing
                }
                else
                {
                        portCount++;
                        level = ((MapContainerComponent)mapSymbol.getParent()).getUserProperty("level");//No Internationalization
                        if ( level == null)
                        {
                                level = "1";//No Internationalization
                                ((MapContainerComponent)mapSymbol.getParent()).setUserProperty("level",level);//No Internationalization

                        }
                        int lev = Integer.parseInt(level);
                        if ( portCount >= ((lev-1)*24)+1 && portCount <= (lev*24))
                        {
                                //To avoid patches while painting the severity
                                if ( bg != null )
                                {
                                        g.setColor(bg);
                                        g.fillRect(p.x+7,p.y+1,6,10);
                                }
                        }
                }

    }   
    
    /** This paints the icon symbol at specified point, and dimension. */
    public void paintIcon(Graphics g,MapSymbolComponent mapSymbol,Point p, Dimension d, ImageObserver io,Image img,boolean selected) {
		if((mapSymbol.getName()).equalsIgnoreCase("next"))
               {
                        String tempLevel = ((MapContainerComponent)mapSymbol.getParent()).getUserProperty("level");//No Internationalization
                        String tempMaxLevel = ((MapContainerComponent)mapSymbol.getParent()).getUserProperty("maxLevel");//No Internationalization
                        if ( Integer.parseInt(tempLevel) < Integer.parseInt(tempMaxLevel) )
                        {
                                if (img != null)
                                        g.drawImage(img,p.x,p.y,d.width,d.height,io);
                                g.setColor(Color.white);
				if(selected){
					//Next button clicked
					changeMapSymbols(mapSymbol);
					mapSymbol.setSelected(false);
				}
                        
			}
                }
                else if ((mapSymbol.getName()).equalsIgnoreCase("previous"))
                {
                        String tempLevel = ((MapContainerComponent)mapSymbol.getParent()).getUserProperty("level");//No Internationalization

                        if ( Integer.parseInt(tempLevel) > 1 )
                        {
                                if (img != null)
                                        g.drawImage(img,p.x,p.y,d.width,d.height,io);
                                g.setColor(Color.white);
				if(selected){
					//Previous button Clicked
					changeMapSymbols(mapSymbol);
					mapSymbol.setSelected(false);
				}                        
			}
                }
  	        else if(mapSymbol.getParent()!= null) //ports
                {
                        int lev = Integer.parseInt(level);
                        if ( portCount >=((lev-1)*24)+1 && portCount<=(lev*24))
                        {
                                if (img != null)
                                        g.drawImage(img,p.x,p.y,d.width,d.height,io);
                                g.setColor(Color.white);
                        }

                        //Port selection
                        if ( selected )
                        {
                                g.setColor(Color.white);
                                int markHt = 1;
                                g.drawLine(p.x-markHt,p.y-markHt,p.x+d.width+markHt,p.y-markHt);
                                g.drawLine(p.x-markHt,p.y+d.height+markHt,p.x+d.width+markHt,p.y+d.height+markHt);
                                g.drawLine(p.x-markHt,p.y-markHt,p.x-markHt,p.y+d.height+markHt);
                                g.drawLine(p.x+d.width+markHt,p.y-markHt,p.x+d.width+markHt,p.y+d.height+markHt);
                        }
                }
        	else 
	        {
                super.paintIcon(g,mapSymbol,p,d,io,img,false); //For painting the switch status icon at the top right corner
                portCount = 0;
        	g.drawImage(img,p.x,p.y,d.width,d.height,io);
                g.setColor(new Color(53,17,159));
                
                
                String s1 = mapSymbol.getLabel(); 
                
                              
                String temp="";
                for(int i=0;i<s1.length();i++)
                    {
                        if(s1.charAt(i)=='\n'|| 
                           s1.charAt(i)=='\t'|| 
                           s1.charAt(i)=='\b'|| 
                           s1.charAt(i)=='\r'|| 
                           s1.charAt(i)=='\f'|| 
                           s1.charAt(i)=='\"'|| 
                           s1.charAt(i)=='\''|| 
                           s1.charAt(i)=='\\')
                        {
                            continue;
                        }
                        temp=temp+s1.charAt(i); //Fix for square type character
                    }
  	                s1=temp;
                        Font f= NmsClientUtil.getFont();//new Font ("Dialog",Font.BOLD,d.width/65);    // For I18N
                        g.setFont(f);

                        FontMetrics fm = g.getFontMetrics();
                        int width = fm.stringWidth(s1);
                        if(width> 3*d.width/4)
                        {
                                int len = s1.length();
                                int l = width/len+1;
                                int index = (d.width*3/4)/l;
                                s1 = s1.substring(0,index)+"...."; //No Internationalization
                        }
                        int x = p.x+5;
                        int y = p.y + d.height-2;
                        g.setColor(Color.white);

                        //Displaying the name of the switch
                        g.drawString(mapSymbol.getName(),x+12,y-15);

                        //Status string being replaced by type
                        //Type was initially stored in the label as a third token, now it is obtained from the mapSymbol property itself
                        String switchType = mapSymbol.getProperty("type");
                        x=p.x+d.width -4 * fm.stringWidth(switchType)/2;
                        g.drawString(switchType,x,y-15);
                        g.setColor(new Color(94,47,47));
                
            
               if ( selected )
                        {
                                Color tempColor = g.getColor();

                                int markHt = d.height/10;
                                if (markHt < 3) markHt = 3 ;
                                g.setColor(new Color(0,0,128));
                                //15 is the small line length   +7 and -9 is for adjustment ie to bring the arcs closer to the object
                                g.drawLine(p.x-markHt+7,p.y-markHt,p.x-markHt+15+7,p.y-markHt);
                                g.drawLine(p.x-markHt+7,p.y-markHt,p.x-markHt+7,p.y-markHt+15);

                                g.drawLine(p.x-markHt+7,p.y+d.height+markHt-10,p.x-markHt+7,p.y+d.height+markHt-15-10);
                                g.drawLine(p.x-markHt+7,p.y+d.height+markHt-10,p.x-markHt+15+7,p.y+d.height+markHt-10);
                                g.drawLine(p.x+d.width+markHt-9,p.y-markHt,p.x+d.width+markHt-15-9,p.y-markHt);
                                g.drawLine(p.x+d.width+markHt-9,p.y-markHt,p.x+d.width+markHt-9,p.y-markHt+15);

                                g.drawLine(p.x+d.width+markHt-9,p.y+d.height-10+markHt,p.x+d.width+markHt-15-9,p.y+d.height+markHt-10);
                                g.drawLine(p.x+d.width+markHt-9,p.y+d.height-10+markHt,p.x+d.width+markHt-9,p.y+d.height-10+markHt-15);
                                g.setColor(tempColor);
                        }
	}
        
    } /* end paintIcon() */
    
    
    
     /** This draws a centered label below specified point */
        public void paintLabelString(Graphics g,MapSymbolComponent mapSymbol, String s0, Point p,Font font )
        {
                Font f = font;
                String  s1;
                int symbWidth = mapSymbol.getWidth();
                int symbHeight= mapSymbol.getHeight();
                int width;
                int height;
                int x;
                int y;

                if(mapSymbol.getParent() != null)
                {
                        if((mapSymbol.getName()).indexOf("Port")>-1)
                        {
                                int lev = Integer.parseInt(level);
                                if ( portCount >= ((lev-1)*24)+1 && portCount <= (lev*24))
                                {
                                        s1 = (String)mapSymbol.getUserProperty("ifIndex"); //No Internationalization
                                        if(s1 != null)
                                        {
                                            // f= new Font (s1,Font.PLAIN,symbWidth/75);
                                            FontMetrics fm = g.getFontMetrics();
                                            width = fm.stringWidth(s1);
                                            height = fm.getHeight();
                                            x = p.x - (width/2);
                                            if(symbHeight <= height)return;
                                            y = p.y-symbHeight;
                                            g.setColor(Color.white);
                                            g.drawString(s1,x,y-6); //lifting the label by 6 pixels
                                        }
                                }
                        }
                }
        }
 
    
    private String getShortie(String label)
    {
        int indx = -1;
        if (label != null) indx = label.indexOf(".");//No Internationalisation
        if (indx != -1)
            try 
                {
                    Integer.parseInt(label.substring(0,indx));
                }
        
            catch (NumberFormatException ne) 
                {
                    label=label.substring(0,indx);  // only if not a number
                }
        return label;
    }
     public boolean downInObject(int x, int y,MapSymbolComponent symb)
        {
                if( (symb.getName()).equalsIgnoreCase("previous") )
                {
                        String levelStr = ((MapContainerComponent)symb.getParent()).getUserProperty("level"); //No Internationalization
                        int lev = Integer.parseInt(levelStr);
                        if(lev == 1)
                        {
                                return false;
                        }
                        boolean result= super.downInObject(x,y,symb);
			return result;
                }

                if( (symb.getName()).equalsIgnoreCase("next") )
                {
			
                        String levelStr = ((MapContainerComponent)symb.getParent()).getUserProperty("level"); //No Internationalization
                        int lev = Integer.parseInt(levelStr);
                        String maxLevelStr = ((MapContainerComponent)symb.getParent()).getUserProperty("maxLevel");//No Internationalization
                        int maxLev = Integer.parseInt(maxLevelStr);
                        if(lev == maxLev)
                        {
                                return false;
                        }
                        boolean result= super.downInObject(x,y,symb);
			return result;
                }

                else if ( symb.getParent() != null )
                {
                        String position = symb.getUserProperty("Position");//No Internationalization
                        level = ((MapContainerComponent)symb.getParent()).getUserProperty("level");//No Internationalization
                        int lev = Integer.parseInt(level);
                        if ( position == null)
                        {
                                return false;
                        }
                        int symPos = Integer.parseInt(position);
                        if ( symPos >= ((lev-1)*24)+1 && symPos <= (lev*24))
			 {
                                boolean result = super.downInObject(x,y,symb);
                                return result;
                        }
                        else
                        {
                                return false;
                        }
                }
                //For both ports and switches
                return super.downInObject(x,y,symb);
        }
     private void changeMapSymbols(MapSymbolComponent portSymComp){
	     
	     MapContainerComponent parentComp = portSymComp.getParent();
	     if ( parentComp != null)
	     {
		     String mapName=portSymComp.getMapName();
		     MapApplet mapApplet=(MapApplet)((MapApplet.mapAppletObjects).get(mapName));
		     DefaultMapModel model=mapApplet.getModel();		
		     int tempLevel = Integer.parseInt(parentComp.getUserProperty("level")); //No Internationalization
		     int maxLevel = Integer.parseInt(parentComp.getUserProperty("maxLevel")) ; //No Internationalization
		     if ((portSymComp.getName()).equalsIgnoreCase("next") && tempLevel<maxLevel)
		     {
			     parentComp.setUserProperty("level",Integer.toString(tempLevel+1)); //No Internationalization
			     Properties p = parentComp.getProperties();
				
			     boolean updateRes = model.updateSymbol(parentComp.getKey(),p,false);

		     }
		     if ((portSymComp.getName()).equalsIgnoreCase("previous") && tempLevel > 1)
		     {
			     parentComp.setUserProperty("level",Integer.toString(tempLevel-1));//No Internationalization
			     Properties p = parentComp.getProperties();
			     boolean updateRes = model.updateSymbol(parentComp.getKey(),p,false);
		     }

	     }
     }

	
}



