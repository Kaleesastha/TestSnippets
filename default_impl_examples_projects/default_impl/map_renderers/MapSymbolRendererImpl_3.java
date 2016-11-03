//$Id: MapSymbolRendererImpl_3.java,v 1.3 2007/08/29 13:13:09 karanmercy Exp $
/**
 * MapSymbolRendererImpl_3.java
 *
 *
 * Created: Fri Mar 30 23:12:42 2001
 *
 * @author <a href="mailto: "</a>
 * @version
 */

package com.adventnet.nms.mapui;

import java.awt.*;
import java.awt.image.*;
import java.lang.*;
import java.util.*;
import java.net.*;
import com.adventnet.nms.util.NmsClientUtil;
import com.adventnet.nms.severity.*;
import javax.swing.ImageIcon;
public class MapSymbolRendererImpl_3  extends MapSymbolRendererImpl 
{
    private char data[];
    
    protected Hashtable  statusImages=null;
    public MapSymbolRendererImpl_3(){
	storeImages();
    } 
	public void paintShapeAndSeverity(Graphics g,MapSymbolComponent mapSymbol,Point p,Dimension d, Color bg,int type)
	{
		//Commented since the status will be represented by an image
		//in the paintIcon method.
		/*if ( (p==null) || (d==null) || (g==null) )
		{
			System.err.println((NmsClientUtil.GetString("Invalid values to paint icon.")));
			return;
		}
			
		if (bg != null)
		{ // we have an object symbol
			
                    g.setColor(new Color(128,0,128));
                    g.drawRect(p.x-10,p.y-10,d.width/4,d.height/8);
                    g.setColor(bg);
                    g.fillRect(p.x-10,p.y-10,d.width/4,d.height/8);
                        
                }*/
	} 
	/* end paint() */
    
	/* This paints the icon symbol at specified point, and dimension. */
	public void paintIcon(Graphics g,MapSymbolComponent mapSymbol,Point p, Dimension d,
			ImageObserver io,Image img,boolean selected) 
	{
		if ( img != null )
		{
			g.drawImage(img,p.x,p.y,d.width,d.height,io);

			SeverityInfo si = SeverityInfo.getInstance();
			String status = si.getName(mapSymbol.getStatus());
			Image statusImage =(Image)statusImages.get(status);
			int innerRatio = 2;
			int statusWidth = statusImage.getWidth(io);
			int statusHeight = statusImage.getHeight(io);
			int sX = p.x  -  (statusWidth/innerRatio);
			int sY = p.y  -  (statusHeight/innerRatio);
			g.drawImage( statusImage, sX, sY, statusWidth, statusHeight, io );
		}

		if (selected)
		{
				g.setColor(Color.blue);
				Stroke oldStroke = ((Graphics2D)g).getStroke();
				BasicStroke strokeType = new BasicStroke(1.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
				((Graphics2D)g).setStroke(strokeType);
				g.drawRect(p.x-6,p.y-6,d.width+12,d.height+12);
				((Graphics2D)g).setStroke(oldStroke);
		}

	} /* end paintIcon() */

	//code added so that the status image is also included in calculating the down in object.fix for: 1195386
	public boolean downInObject(int x, int y,MapSymbolComponent symb)
	{
		if ( (symb.getP0() == null) || (symb.getD0() == null) ) return false;
		Rectangle rect = null;
		if (symb.getObjType()==0)
			rect= new Rectangle(symb.getP0().x + (symb.getD0().width*3)/8,
					symb.getP0().y + (symb.getD0().height*3)/8,
					symb.getD0().width/4, symb.getD0().height/4);
		else
		{
			SeverityInfo si = SeverityInfo.getInstance();
			String status = si.getName(symb.getStatus());
			Image statusImage =(Image)statusImages.get(status);
			int innerRatio = 2;
			int statusWidth = statusImage.getWidth(null);
			int statusHeight = statusImage.getHeight(null);
			int sX = symb.getP0().x  -  (statusWidth/innerRatio);
			int sY = symb.getP0().y  -  (statusHeight/innerRatio);
			int sW = symb.getD0().width + (statusWidth/innerRatio);
			int sH = symb.getD0().height + (statusHeight/innerRatio);
			rect = new Rectangle(sX, sY, sW, sH);
		}


		if (rect.contains(x,y)) 
			return true;
		else if(symb.getLabelSize().contains(x,y))
			return true;
		return false;
	}


    public void paintLabelString(Graphics g,MapSymbolComponent mapSymbol, String s0, Point p,Font font ) 
	{
	/* This draws a centered label below specified point */

                String temp="";
                for(int i=0;i<s0.length();i++)
                    {
                        if(s0.charAt(i)=='\n' || s0.charAt(i)=='\t')
                        {
                            continue;
                        }
                        temp=temp+s0.charAt(i);
                    }

                s0=temp;
		String s=s0;
		if ( (s == null) || (font == null) ) return;
		if (s.length() > MapConstants.MAX_LABEL_LENGTH) 
			s = s0.substring(0,MapConstants.MAX_LABEL_LENGTH) + "...";//No Internationalisation

		
		FontMetrics fm = g.getFontMetrics();
		int width = fm.stringWidth(s);
		int height = fm.getHeight();
		int x = p.x - (width/2);
		int y = p.y + height - (height/4);
                int symbWidth = mapSymbol.getWidth();
        
                //       f= new Font (null,Font.PLAIN,symbWidth/4);
                
                

                //g.setFont(f); //It will take the default one
                g.setColor(Color.blue);

                mapSymbol.setLabelSize(new Rectangle(x,p.y,width+4, height-1));
		g.drawString(s,x+3,y);
	} 
	// end of paintLabelString()

    //Reads the severity images from SeverityInfo.conf file and store it in a hashtable.
    private void storeImages()
    {
        try
        {
            SeverityInfo sevInfo = NmsClientUtil.severityInfo;
            Vector sevNames = sevInfo.getNames();
            int totSevs = sevNames.size();
            statusImages = new Hashtable();
            for(int i=0;i<totSevs;i++)
            {
                String sevName = sevNames.elementAt(i).toString();
                SeverityNode sevNode = sevInfo.getSeverityNode(sevName);
                Object obj = sevNode.getAttribute("IMAGE");
                String sevImageName = "";
                if(obj != null)
                {
                    sevImageName = obj.toString();
                }
                Image sevImage = getImage(sevImageName);
                if(sevImage == null)
                {
                    sevImage = getImage("./images/defaultstatus.png");//No Internationalization
                }
               statusImages.put(sevName,sevImage);
            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }


    private Image getImage(String imageName) throws Exception {
       if(NmsClientUtil.applet != null)
        {
            //ImageIcon ic = com.adventnet.apiutils.Utility.findImage(imageName, NmsClientUtil.applet, true);
            
            // Utility from ApiUtils.jar is not used as the protocol name
            // "http" is hardcoded there and won't work for "https" protocol.
            URL url 
                = new URL(NmsClientUtil.applet.getDocumentBase() 
                          + "../" + imageName); // No I18N
            ImageIcon ic = NmsClientUtil.getImageIcon(url);
            
            if(ic == null)
            {
                return null;
            }
            else
            {
                return ic.getImage();
            }
        }
        else
        {
            System.err.println("NMS Applet not yet initialized"); //No Internationalization
            return null;
        }

    }
    
}












