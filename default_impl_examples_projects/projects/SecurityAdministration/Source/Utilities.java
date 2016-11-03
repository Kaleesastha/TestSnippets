//$Id: Utilities.java,v 1.1 2006/08/29 13:57:02 build Exp $
 package com.adventnet.security.ui ; 


 import java.awt.*;
 import java.awt.event.*;
 import javax.swing.*;
 import javax.swing.event.*;

 public class Utilities 
 {


 public Utilities()
  {

       
  }
 public static void errorMessage (String message)
  {
    Object[] options =
 {AuthMain.resourceBundle.getString("OK")};
    JOptionPane.showOptionDialog (null,
                                  message,
                                  AuthMain.resourceBundle.getString("Warning !"),
                                  JOptionPane.DEFAULT_OPTION,
                                  JOptionPane.WARNING_MESSAGE,
                                  null, options, options[0]);

  }

 }






