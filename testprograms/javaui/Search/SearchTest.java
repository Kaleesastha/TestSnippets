/*This is an test program for testing Search. This can be executed separately.

1.Save the file as SearchTest.java.

2.Set the classpath to NmsClientClasses.jar and compile the SearchTest.java file.

3.Run the compiled SearchTest.java file.
Usage:
Java SearchTest

4.The Search dialog will be opened and the testing can be started.

5.Comment and UnComment the lines according to the test case description and do the testing as StandAlone.*/

import java.util.*; 
import javax.swing.*; 
import java.awt.*; 
import java.awt.event.*; 

import com.adventnet.nms.util.*; 

public class SearchTest extends JFrame   implements Searchable, ActionListener 
{ 
    public static void   main(String[] p) 
    { 

          SearchTest searchTest = new SearchTest(); 
    } 

        public SearchTest() 
    { 
          JPanel panel = new JPanel(); 
          panel.setLayout(new BorderLayout()); 
          JButton but = new JButton("Search"); 
          but.addActionListener(this); 
          panel.add(BorderLayout.SOUTH, but); 

          getContentPane().add(panel); 
          this.setSize(500,400); 
          this.setLocation(250,250); 
          ImageIcon  iconFile = new ImageIcon("c:/AdventNet.jpg"); 
          setIconImage(iconFile.getImage()); 
          setVisible(true); 
    } 

    public void   doSearch(SearchAttributes[] att, boolean match) 
    { 
          System.out.println(" Match all  "+match); 
          for(int i =0; i<att.length; i++ ) 
            { 
                System.out.println(att[i].columnName + "    "+att[i].type+"       "+att[i].value); 
            } 

    } 

    SearchDialog sd; 
        public void dialogClosed() 
    { 
          System.out.println(" closed ....."); 
          sd.setVisible(false); 
          sd.dispose(); 
    } 

    public String   getString(String key) 
    { 
          return key; 
    } 

    public void   actionPerformed(ActionEvent event) 
    { 
          if(event.getActionCommand().equals("Search")) 
            { 
          SearchAttributes[] attributes = new SearchAttributes[10]; 
          for(int i =0; i < 10 ; i++) 
            { 
                attributes[i] = new SearchAttributes(); 
              String   value = String.valueOf(i); 
              //value   = "NAME"+value; 
                attributes[i].columnName = "NAME"+value; 
                attributes[i].key = "KEY"+value; 
                attributes[i].type = i % 7; 
                attributes[i].value = "VALUE"+value; 
            } 
              sd = new   SearchDialog(this,"Search",attributes,this); 
                sd.setIconHeader(new ImageIcon("c:/AdventNet.jpg"), "Search Test"); 
                sd.setVisible(true); 
            } 
    } 
} 
