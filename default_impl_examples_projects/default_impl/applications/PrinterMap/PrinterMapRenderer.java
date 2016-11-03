
package com.adventnet.nms.mapui;

import java.awt.*;
import java.awt.image.*;
import javax.swing.*;
import java.util.*;
import com.adventnet.nms.util.NmsClientUtil;
import com.adventnet.nms.severity.SeverityInfo;
/**
 * PrinterMapRenderer.java
**/

public class PrinterMapRenderer  extends MapSymbolRendererImpl 
                                         
{
    
    public void paintShapeAndSeverity(Graphics g,MapSymbolComponent mapSymbol,Point p,Dimension d, Color bg,int type)
    {
	  
    } /* end paint() */
    
    /**
     * To paint Icon on the Map Symbol
	 * @param     g 	  - Graphics of MapSymbolComponent
	 * @param     mapSymbol - Reference of MapSymbolComponent object
	 * @param     location  - Top Left Corner point of MapIcon
	 * @param     size      - Size of the Map Icon 
	 * @param     io        - ImageObserver  
	 * @param     imageIcon - Image to be displayed on the Map Symbol
	 * @param     selected  - Whether the symbol is selected or not
	 */
    
    /** This paints the icon symbol at specified point, and dimension.*/
    public void paintIcon(Graphics g,MapSymbolComponent mapSymbol,Point p, Dimension d, ImageObserver io,Image img,boolean selected) 
    {
        
        if (img != null)
        {
            if(mapSymbol.getUserProperty("statusInt")!=null)
             {
                 g.drawImage(img,p.x,p.y,d.width*2/3,d.height*2/3,io);
                 String [] on  = {"icn_ready.png","icn_printing.png","icn_paperjam.png","icn_dooropen.png","icn_tonerlow.png","icn_paperlow.png","icn_offline.png","icn_service.png"};
        String [] off  = {"icn_ready_off.png","icn_printing_off.png","icn_paperjam_off.png","icn_dooropen_off.png","icn_tonerlow_off.png","icn_paperlow_off.png","icn_offline_off.png","icn_service_off.png"};
        int dx = mapSymbol.getWidth()/6;
        int dy = mapSymbol.getHeight()/6;
        Image img1=null;
        for(int j=0;j<=1;j++)
        {
            for(int k=0;k<=3;k++)
            {
                if(mapSymbol.getUserProperty("statusInt")!=null)
                {
                if(isBitOn(Integer.parseInt((String)mapSymbol.getUserProperty("statusInt")),k+4*j))
                {
                   img1 = MapClientAPI.getInstance().getImageLoader().getImage(on[k+j*3+j], (Component)io);
                }
                 else
                 {
                     img1 = MapClientAPI.getInstance().getImageLoader().getImage(off[k+j*3+j], (Component)io);
                 }
                g.drawImage(img1,p.x+dx*k,p.y+(2*mapSymbol.getHeight())/3+ dy*j,d.width/6,d.height/6,io);
                }
            }
        }
             }
             else g.drawImage(img,p.x,p.y,d.width,d.height,io); 
        }
        if (selected) 
        {  
            /*g.setColor(new Color(0,0,160));
              g.drawArc(p.x-30,p.y-20, 20, 20, 90, 90);
              g.fillArc(p.x-30,p.y-20, 20, 20, 90, 90);
              g.drawRect(p.x-30,p.y-10,10,mapSymbol.getHeight()+40);
              g.fillRect(p.x-30,p.y-10,10,mapSymbol.getHeight()+40);
              g.drawArc(p.x-30,p.y+mapSymbol.getHeight()+20, 20, 20, 90, 90);
              int markHt = d.height/20;
              if (markHt < 5) markHt = 5;
              g.fillRect(p.x-markHt, p.y-markHt, markHt,markHt);
              g.fillRect(p.x-markHt, p.y+d.height, markHt,markHt);
              g.setColor(Color.white);
              g.fillArc(p.x-28,p.y+mapSymbol.getHeight()+20, 22, 22, 90, 110);*/
            g.setColor(new Color(0,0,160));
            int markHt = d.height/20;
            if (markHt < 5) markHt = 5;
            g.fillRect(p.x-markHt, p.y-markHt, markHt,markHt);
            g.fillRect(p.x+d.width, p.y-markHt, markHt,markHt);
            g.fillRect(p.x-markHt, p.y+d.height, markHt,markHt);
            g.fillRect(p.x+d.width, p.y+d.height, markHt,markHt);
        }	
        
    } 
    /* end paintIcon() */
    
    /**
     * To draw a label string at the bottom of Map Symbol.
     * @param     g 	  - Graphics of MapSymbolComponent
     * @param     mapSymbol - Reference of MapSymbolComponent object
     * @param     label     - String to be displayed
     * @param     location  - Starting location of string
     * @param     font      - Font type which is used to draw the label
     */
    
    public void paintLabelString(Graphics g,MapSymbolComponent mapSymbol, String s0, Point p,Font font ) 
     {
         /* This draws a centered label below specified point */
         Font f = font;
         String s = s0;
         if ( (s == null) || (f == null) ) return;
         if (s.length() > MapConstants.MAX_LABEL_LENGTH) 
             s = s0.substring(0,MapConstants.MAX_LABEL_LENGTH) + "...";//No Internationalisation
         
         FontMetrics fm = g.getFontMetrics();
         int width;
         int height;
         String consoleLightString = mapSymbol.getUserProperty("consoleLightString");
         //String[] col = {"gray","lightGray","white","red","green","blue","cyan","magenta","yellow"};
         if((consoleLightString!=null) && !(consoleLightString.trim().equals(""))) 
         {
             
             StringTokenizer st = new StringTokenizer(consoleLightString,",");
             
             g.setColor(Color.black);
             
             int count = st.countTokens();
             int ht = mapSymbol.getHeight()/(2*count*4);//
             int hy = (mapSymbol.getHeight()-ht*3)/(count+1);//
             
             g.setColor(Color.gray);
             f= new Font (null,Font.PLAIN,mapSymbol.getWidth()/30); //Set to default font.
             for(int i=0;st.hasMoreTokens();i++)
             {
                 g.setFont(f);
                 String s1 = st.nextToken();
                 int status = Integer.parseInt(s1.substring(s1.indexOf("=")+1));
                 String display = s1.substring(0,s1.indexOf("="));
                 width = fm.stringWidth(s1);
                 height = fm.getHeight();
                 g.setColor(Color.black);
                 g.drawRect(p.x + mapSymbol.getWidth()/6+4,p.y-mapSymbol.getHeight()/2+hy*(i),mapSymbol.getWidth()/9,ht);
                 
                 if(status==1)g.setColor(Color.gray);
                 else if(status==2)g.setColor(Color.lightGray);
                 else if(status==3)g.setColor(Color.white);
                 else if(status==4)g.setColor(Color.red);
                 else if(status==5)g.setColor(Color.green);
                 else if(status==6)g.setColor(Color.blue);
                 else if(status==7)g.setColor(Color.cyan);
                 else if(status==8)g.setColor(Color.magenta);
                 else if (status==9)g.setColor(Color.yellow);
                 g.fillRect(p.x + mapSymbol.getWidth()/6+4,p.y-mapSymbol.getHeight()/2+hy*(i),mapSymbol.getWidth()/9,ht);
                 g.setColor(new Color(128,0,64));
                 
                 g.drawString(display,p.x+mapSymbol.getWidth()/6+4+mapSymbol.getWidth()/9+10,p.y-mapSymbol.getHeight()/2+hy*(i)+(mapSymbol.getWidth())/30);
             }
         }            
         String consoleDisplayText = mapSymbol.getUserProperty("consoleDisplayText");
         if(consoleDisplayText!=null)
         {
             width = fm.stringWidth(consoleDisplayText);
             height = fm.getHeight();
             f= new Font(null,Font.BOLD,mapSymbol.getWidth()*2/55);//Set to default font
             g.setFont(f);
             
             g.drawRect(p.x+mapSymbol.getWidth()/6+2,p.y-mapSymbol.getHeight()+mapSymbol.getHeight()/40,mapSymbol.getWidth()/3-4,mapSymbol.getHeight()/6);
             g.setColor(Color.white);
             g.fillRect(p.x+mapSymbol.getWidth()/6+7,p.y-mapSymbol.getHeight()+mapSymbol.getHeight()/40,60,15);
             g.setColor(Color.black);
             g.drawString("Status",p.x +mapSymbol.getWidth()/6+ 6,p.y - mapSymbol.getHeight()+10);
             
             g.setColor(new Color(128,0,64));
             g.drawString(consoleDisplayText,p.x+mapSymbol.getWidth()/6 + 6,p.y-mapSymbol.getHeight()+height/3);
             g.drawRect(p.x-mapSymbol.getWidth()/2-20,p.y-mapSymbol.getHeight()-20,mapSymbol.getWidth()+10+20,mapSymbol.getHeight()+40);

         }
         f= new Font (null,Font.BOLD,mapSymbol.getWidth()*2/45);//Set to default font
         g.setFont(f);
         fm = g.getFontMetrics();
         width = fm.stringWidth(s);
         height = fm.getHeight();
         int x = p.x - mapSymbol.getWidth()/6 - (width/6) - 1;
         int y = p.y + height - (height/4);
         
         
		 Color bg = SeverityInfo.getInstance().getColor(Integer.parseInt(mapSymbol.getProperty("status")));
         g.setColor(bg);
		 g.drawRect(x,p.y,width+4, height-1);
		 g.fillRect(x,p.y,width+4, height-1);
         g.setColor(Color.black);
         mapSymbol.setLabelSize(new Rectangle(x,p.y,width+4, height-1));
         g.drawString(s,x+3,y);
     } 
    
    public String getToolTipText(MapSymbolComponent symb,int x,int y)
    {
        String [] toolTip  = {"Ready","Printing","Paper Jam","Door Open","Toner Low","Paper Low","Offline","Service"};
        //if(BasicMap.toolTipText.trim().equals("status"))//No Internationalisation
        if(BasicMap.getToolTipProperty().trim().equals("status"))//No Internationalisation
            return SeverityInfo.getInstance().getName(Integer.parseInt(symb.getProperty(BasicMap.getToolTipProperty())));
        if(symb.getUserProperty("statusInt")!=null)
        {
        if ((x<symb.getX()+(2*symb.getWidth())/3))
        {
        if(y<(symb.getY()+(symb.getHeight()*2)/3))
        {
            return symb.getProperty(BasicMap.getToolTipProperty());
            
        }
        
        else
        {
        int dx = symb.getWidth()/6;
        int dy = symb.getHeight()/6;
        Image img1=null;
        for(int j=0;j<=1;j++)
        {
            for(int k=0;k<=3;k++)
            {
                if(x>= (symb.getX()+dx*k)   && (x<= symb.getX()+dx*k+symb.getWidth()/6) && y>=(symb.getY()+(2*symb.getHeight())/3)+dy*j && y <= symb.getY()+(2*symb.getHeight())/3 + dy*j + symb.getHeight()/6)
                    return(toolTip[k+j*3+j]);
                
            }
        }
        }
        //return symb.getProperty(BasicMap.toolTipText);
        return symb.getProperty(BasicMap.getToolTipProperty());
        }
        else return null;
        }
        //else return symb.getProperty(BasicMap.toolTipText);
        else return symb.getProperty(BasicMap.getToolTipProperty());

    }
    private boolean isBitOn(int valueInt, int bitNum){
        if(bitNum<0) return false;
        int enumvalue=1<<bitNum;
        
        int result=(valueInt & enumvalue);
        if(result==enumvalue) return true;
        else return false;
    }
    
    
    
}// PrinterMapRenderer
















