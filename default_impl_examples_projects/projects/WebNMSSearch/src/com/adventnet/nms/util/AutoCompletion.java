//$Id: AutoCompletion.java,v 1.2 2007/07/09 12:56:02 sureshbabu Exp $
package com.adventnet.nms.util;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.*;
import com.adventnet.nms.util.ErrorDialog;
import com.adventnet.nms.startclient.NmsPanel;

public class AutoCompletion extends PlainDocument {
    JComboBox comboBox;
    ComboBoxModel model;
    JTextComponent editor;
    boolean selecting=false;
    boolean hidePopupOnFocusLoss;
    boolean hitBackspace=false;
    boolean hitBackspaceOnSelection;
    boolean initialize=true;
    
    public AutoCompletion(final JComboBox comboBox) {
        this.comboBox = comboBox;
        model = comboBox.getModel();
        editor = (JTextComponent) comboBox.getEditor().getEditorComponent();
        editor.setDocument(this);
        comboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!selecting) highlightCompletedText(0);
            }
        });
        editor.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (comboBox.isDisplayable()) comboBox.setPopupVisible(true);
                hitBackspace=false;
            }
        });
        hidePopupOnFocusLoss=System.getProperty("java.version").startsWith("1.5");
          editor.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                highlightCompletedText(0);
            }
            public void focusLost(FocusEvent e) {
                if (hidePopupOnFocusLoss) comboBox.setPopupVisible(false);
            }
        });
        Object selected = comboBox.getSelectedItem();
        if (selected!=null) setText(selected.toString());
        highlightCompletedText(0);
        //Search Text fix
        editor.requestFocus();
        editor.setDocument(new LimitDocument(50));
    }
    
    public void remove(int offs, int len) throws BadLocationException {
        if (selecting) return;
        if (hitBackspace) {
            if (offs>0) {
                if (hitBackspaceOnSelection) offs--;
            } else {
                  comboBox.getToolkit().beep(); 
            }
            highlightCompletedText(offs);
        } else {
            super.remove(offs, len);
        }
    }
    
    public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
       if (selecting) return;
        super.insertString(offs, str, a);
        Object item = lookupItem(getText(0, getLength()));
        boolean listContainsSelectedItem = true;
        if (item == null) {
            item=getText(0, getLength());
            listContainsSelectedItem=false;
        }
        setSelectedItem(item);
        setText(item.toString());
        if (listContainsSelectedItem) highlightCompletedText(offs+str.length());
    }
    
    private void setText(String text) {
        try {
            super.remove(0, getLength());
            super.insertString(0, text, null);
        } catch (BadLocationException e) {
            throw new RuntimeException(e.toString());
        }
    }
    
    private void highlightCompletedText(int start) {
        //editor.setCaretPosition(getLength());
        editor.moveCaretPosition(start);
    }
    
    private void setSelectedItem(Object item) {
        selecting = true;
        model.setSelectedItem(item);
        selecting = false;
    }
    
    private Object lookupItem(String pattern) {
        Object selectedItem = model.getSelectedItem();
        if (selectedItem != null && startsWithIgnoreCase(selectedItem.toString(), pattern)) {
            return selectedItem;
        } else {
           for (int i=0, n=model.getSize(); i < n; i++) {
                Object currentItem = model.getElementAt(i);
                if (startsWithIgnoreCase(currentItem.toString(), pattern)) {
                    return currentItem;
                }
            }
        }
          return null;
    }
    
    private boolean startsWithIgnoreCase(String str1, String str2) {
        return str1.startsWith(str2);
    }
    public class LimitDocument extends PlainDocument //Search Text fix
    {
        private int limit;
        private ErrorDialog errorWin = null;
        public char[] parr = null;
        
        
        
        
        public LimitDocument(int limit)
        {
            super();
            setLimit(limit);
        }
        public final int getLimit()
        {
            return limit;
        }
        public void insertString(int offset, String s, AttributeSet attributeSet)
        {
            if(offset < limit)
            {
                try{
                    super.insertString(offset,s,attributeSet);
                    parr = s.toCharArray();
                    int j = 0;
                    for(int i=0;i<parr.length;i++)
                    {
                        j = parr[i];
                    
                    
                    if (j == 39) //ascii of ' (single quote is 39)
                    {
                        //showMessageDialog(Component parentComponent, Object message, String title, int messageType)
                        JOptionPane.showMessageDialog(null, NmsClientUtil.GetString("The character entered is not allowed in Search Dialog"), NmsClientUtil.GetString("Invalid Entry"), JOptionPane.ERROR_MESSAGE);// No I18N
                        editor.setText(null);
						break;
                    }
                    }
                }
                catch(Exception e){}
            }
        }
        
        public final void setLimit(int newValue)
        {
            this.limit = newValue;
        }
    }
    
}
