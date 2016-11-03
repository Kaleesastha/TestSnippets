//$Id: NewProj.java,v 1.1.1.1 2006/12/08 11:57:39 gramkumar Exp $
package com.adventnet.nms.tools.mowizard;

import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;

public class NewProj extends JPanel implements MouseListener
{
	/**
	 * Since all the components of this class are added at runtime
	 * this screen is not done using Builder.  
	 */
	 
	Color   mycolor=null;
	MyLabel []label;
	int selectedIndex=-1;
	public NewProj()
	{  
		setLayout(new GridLayout(0,3));
		setBackground(Color.white);
		addMouseListener(this);
	}

	public void mouseEntered(MouseEvent me){}
	public void mousePressed(MouseEvent me){}
	public void mouseReleased(MouseEvent me){}
	public void mouseExited(MouseEvent me){}

	public void mouseClicked(MouseEvent me)
	{
		//Here we are checking as to which label has been clicked and 
		//thus providing a look that he has indeed selected the icon.
		if(me.getSource() instanceof JLabel){
			JLabel temp_Label=(JLabel)me.getSource();
			int index=getWhichLabel(temp_Label);
			if(index!=-1){
				//temp_Label.requestFocus();
				for(int i=0;i<label.length;i++){
					if(index==i) {
						label[i].setSelected(true);
					}
					else{
						label[i].setSelected(false);
					}
				}
				selectedIndex=index;
			}
			repaintall();
		}
	}

	private int getWhichLabel(JLabel temp_Label)
	{
		for(int i=0;i<label.length;i++){
			//check whether the objects are equal
			if(label[i].equals(temp_Label)){
				return i;
			}
		}
		return -1;
	}
	
	public int getSelectedIndex() {
		return selectedIndex;
	}

	private void repaintall()
	{
		for(int i=0;i<label.length;i++){
			label[i].repaint();
		}
	}

	public  void update(Graphics g)
	{
		paint(g);
	}

	public void addProjType(String ProjTypeName,Icon icon) {
		if(label==null) {
			label=new MyLabel[1];
			label[0]=new MyLabel(ProjTypeName,icon);
			label[0].addMouseListener(this);	
			add(label[0]);	
			revalidate();
		}
		else {
			int noOfLabels=label.length+1;
			MyLabel[] mlabel=new MyLabel[noOfLabels];
			System.arraycopy(label,0,mlabel,0,this.label.length);
			mlabel[noOfLabels-1]=new MyLabel(ProjTypeName,icon);
			mlabel[noOfLabels-1].addMouseListener(this);
			label=mlabel;
			add(mlabel[noOfLabels-1]);	
			revalidate();
		}
	}

	public void addProjectComponents() {
        /*    addProjType("Singl MO",new ImageIcon("images"+File.separator+"Installer.jpg"));
            addProjType("Group MO",new ImageIcon("images"+File.separator+"Installer.jpg"));
            addProjType("Testing  MO",new ImageIcon("images"+File.separator+"Installer.jpg"));
            addProjType("Single MO",new ImageIcon("."+File.separator+"images"+File.separator+"container.png"));
            addProjType("Single MO",new ImageIcon("."+File.separator+"images"+File.separator+"container.png"));*/
	}

	public static void main(String ar[])
	{
		NewProj np=new NewProj();
		/*np.addProjType("Single MO",new ImageIcon("tm.gif"));
		np.addProjType("Grouped MO",new ImageIcon("tm.gif"));
		np.addProjType("Testing1",new ImageIcon("tm.gif"));
		np.addProjType("Testing1",new ImageIcon("tm.gif"));
		np.addProjType("Testing1",new ImageIcon("tm.gif")); */
		JFrame frame=new JFrame();
		frame.getContentPane().add(np,BorderLayout.CENTER);
		frame.setSize(400,300);
		frame.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent we) { 	 
				System.exit(0);
				}
				});
		frame.setVisible(true);
	}
}

class MyLabel extends JLabel
{
	private boolean isSelectedFlag = false;
	private String  description=null;

	// Selection color ala windows explorer selection color

	private Color selectedColor = new Color(80);

	public MyLabel(String caption,Icon icon){
		super(caption,icon,JLabel.CENTER);
		setHorizontalAlignment(JLabel.CENTER);
		setHorizontalTextPosition(JLabel.CENTER);
		setVerticalAlignment(JLabel.CENTER);
		setVerticalTextPosition(JLabel.BOTTOM);
	}

	/**
	 * Paints the value.  The background is filled based on selected.
	 */

	public void paint(Graphics g) {

		Color bColor;

		bColor = getBackgroundColor();

		int imageOffset = -1;

		if(bColor != null) {
			Icon currentI = getIcon();

			imageOffset = getLabelStart();
			g.setColor(bColor);

			FontMetrics fm = getFontMetrics(getFont());
			int stringWidth = 
				SwingUtilities.computeStringWidth(fm,getText());	
			if(currentI != null){
				g.fillRect((getWidth()-stringWidth)/2,getHeight()/2+currentI.getIconHeight()/2-getIconTextGap(),stringWidth,fm.getHeight()-2);
			}
		}

		g.setColor(getForegroundColor());
		if(isSelectedFlag)
			setForeground(Color.white);
		else
			setForeground(Color.black);
		super.paint(g);

	}//End of Paint

	/**
	 *  Get the position of the starting of the
	 *  text in the label.
	 */
	private int getLabelStart() {
		Icon currentI = getIcon();
		if(currentI != null && getText() != null) {
			return currentI.getIconHeight() + Math.max(0, getIconTextGap() - 1);
		}
		return 0;
	}

	/**
	 *  Get the background color 
	 *  @returns Color selectedColor if selected
	 *  Color.white if deselected
	 */

	public Color getBackgroundColor(){
		if(isSelectedFlag){
			return selectedColor;
		}
		else{
			return Color.white;
		}
	}//End of getBackgroundColor

	/**
	 *  Get the foreground color 
	 *  @returns Color. white if selected
	 *  Color.black if deselected
	 */

	public Color getForegroundColor(){
		if(isSelectedFlag){
			return Color.white;
		}
		else{
			return Color.black;
		}
	}//End of getForegroundColor

	/**
	 *   Set the selected flag.
	 *   This is useful in painting proper color
	 *   to the label on selection.
	 */
	public void setSelected(boolean flag){
		isSelectedFlag = flag;
	}//End of setSelected

	public String getDescription() {
		return description;
	}
}//End of IconLabel
