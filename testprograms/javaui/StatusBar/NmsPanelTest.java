/*The following code can be used for testing purpose. Follow the procedure as shown below: 

1.Save the code snippet shown below as "NmsPanelTest.java" . 

2.Compile this file with "NmsClientClasses.jar"in the classpath. 

3.Place an entry for this class in NmsPanels.conf file present under the "WebNMS_HOME/conf"directory. The entry should be like as shown below:

<PANEL className="NmsPanelTest"/>

4.Edit startApplicationClient.bat/sh under "WebNMS_HOME/bin" directory to include this compiled class in the classpath. 

5.Now start the client to see the effect of changes that take place in the client. */




import javax.swing.*;
import java.awt.event.*;
import java.awt.BorderLayout;
import javax.swing.event.*;
import java.util.*;
import javax.swing.border.*;
import java.awt.*;
import javax.swing.table.*;
import com.adventnet.nms.startclient.*;
import com.adventnet.nms.util.*;

public class NmsPanelTest extends AbstractBaseNmsPanel implements ActionListener 
{ 
     public void actionPerformed(ActionEvent ae) 
    { 
         //NmsClientUtil.showStatusOnLabel("Sathish changes for Status Bar" ,Color.red, Color.blue); 
         NmsClientUtil.removeComponentFromStatusBar(NmsClientUtil.LEFT_OF_LABEL); 
         //NmsClientUtil.removeComponentFromStatusBar(NmsClientUtil.RIGHT_OF_LABEL); 
    } 

     public NmsPanelTest() 
     { 
         MainPanel panel = NmsClientUtil.getMainPanel(); 
        JLabel label = new JLabel("Sathish"); 
        JButton button = new JButton(); 
        button.setText("User Name : "+NmsClientUtil.userName); 
        button.addActionListener(this); 
        NmsClientUtil.addComponentToStatusBar(button,1,25); 
        JPanel temp = new JPanel(new BorderLayout(2,2)); 
       temp.setBorder(new javax.swing.border.BevelBorder(1)); 
       JTable table = new JTable(); 
       DefaultTableModel model = new DefaultTableModel(); 
       table.setModel(model); 
       table.setDefaultRenderer(Object.class, new RendererClass()); 
       Vector col = new Vector(); 
       col.add("a"); 
       col.add("b"); 
       col.add("c"); 
       col.add("d"); 
       col.add("e"); 
       col.add("f"); 
       Vector data = new Vector(); 
       Vector v = new Vector(); 
        v.add("10"); 
        v.add("20"); 
        v.add("30"); 
        v.add("40"); 
        v.add("50"); 
        v.add("60"); 
        data.add(v); 
        model.setDataVector(data,col); 
       table.repaint(); 
       table.setRowHeight(20); 
       temp.add(table); 
       NmsClientUtil.addComponentToStatusBar(temp,NmsClientUtil.LEFT_OF_LABEL,30); 
   } 

   class RendererClass extends JLabel implements TableCellRenderer 
   { 
       public RendererClass() 
       { 
            super(); 
            setOpaque(true); 
            setHorizontalAlignment(SwingConstants.CENTER); 
            setHorizontalTextPosition(SwingConstants.CENTER); 
            setFont(new Font("dialog",Font.PLAIN,12)); 
      } 
      public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int
row, int column) 
      { 
           Color c = null; 
           if(column == 0) 
          { 
               c = Color.red; 
         } 
         else if(column == 1) 
         { 
              c = Color.orange; 
        } 
        else if(column == 2) 
        { 
             c = Color.yellow; 
       } 
        else if(column == 3) 
        { 
              c = Color.cyan; 
        } 
        else if(column == 4) 
        { 
              c = Color.green; 
        } 
        else if(column == 5) 
        { 
             c = Color.white; 
        } 
         setBackground(c); 
         setForeground(Color.black); 
         setText(value.toString()); 
         return this; 
     } 
 } 

 public void init(JApplet applet) 
 { 

 } 

 public void setProperties(Properties p) 
 { 

 } 

 public void close() 
 { 
 } 

 public String key() 
 { 
  return "NmsPanelTest"; 
 } 
} 
