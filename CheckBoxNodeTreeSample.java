import java.util.EventObject;
import java.util.Vector;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.Component;
import java.awt.Font;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.event.ItemEvent;

import javax.swing.AbstractCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeCellEditor;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.TreePath;

public class CheckBoxNodeTreeSample {
    public static void main(String args[]) {
        JFrame frame = new JFrame("CheckBox Tree");

        CheckBoxNode accessibilityOptions[] = { new CheckBoxNode("TagMeasurement 1", false), new CheckBoxNode("TagMeasurement 2", true) };
        CheckBoxNode browsingOptions[] = { new CheckBoxNode("TagMeasurement 1", true), new CheckBoxNode("TagMeasurement 2", true), new CheckBoxNode("TagMeasurement 3", true), new CheckBoxNode("TagMeasurement 4", false) };


        CheckBoxNode2 cbnA = new CheckBoxNode2("MeasurementSeries 0", false, accessibilityOptions);
        CheckBoxNode2 cbnB = new CheckBoxNode2("MeasurementSeries 1", false, browsingOptions);

        Object rootNodes[] = { cbnA, cbnB };
        Vector rootVector = new NamedVector("Root", rootNodes);
        JTree tree = new JTree(rootVector);

        CheckBoxNodeRenderer renderer = new CheckBoxNodeRenderer();
        tree.setCellRenderer(renderer);

        tree.setCellEditor(new CheckBoxNodeEditor(tree));
        tree.setEditable(true);

        JScrollPane scrollPane = new JScrollPane(tree);
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
        frame.setSize(300, 150);
        frame.setVisible(true);
    }
}

class CheckBoxNodeRenderer implements TreeCellRenderer {
    private JCheckBox renderer = new JCheckBox();
    Color selectionBorderColor, selectionForeground, selectionBackground,
textForeground, textBackground;

    protected JCheckBox getRenderer() {
        return renderer; 
    }

    public CheckBoxNodeRenderer() {
        Font fontValue;
        fontValue = UIManager.getFont("Tree.font");
        if (fontValue != null) {
            renderer.setFont(fontValue);
        }
        Boolean booleanValue = (Boolean) UIManager.get("Tree.drawsFocusBorderAroundIcon");
        renderer.setFocusPainted((booleanValue != null)&& (booleanValue.booleanValue()));

        selectionBorderColor = UIManager.getColor("Tree.selectionBorderColor");
        selectionForeground = UIManager.getColor("Tree.selectionForeground");
        selectionBackground = UIManager.getColor("Tree.selectionBackground");
        textForeground = UIManager.getColor("Tree.textForeground");
        textBackground = UIManager.getColor("Tree.textBackground");
    }

public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
    Component returnValue;
    if (leaf) {
        renderer.setEnabled(tree.isEnabled());
        if (selected) {
            renderer.setForeground(selectionForeground);
            renderer.setBackground(selectionBackground);
        } else {
            renderer.setForeground(textForeground);
            renderer.setBackground(textBackground);
        }

        if ((value != null) && (value instanceof DefaultMutableTreeNode)) {
            Object userObject = ((DefaultMutableTreeNode) value).getUserObject();
            if (userObject instanceof CheckBoxNode) {
                CheckBoxNode node = (CheckBoxNode) userObject;
                renderer.setText(node.getText());
                renderer.setSelected(node.isSelected());
            }
        }
        returnValue = renderer;
    } else {
        renderer.setEnabled(tree.isEnabled());
        if (selected) {
            renderer.setForeground(selectionForeground);
            renderer.setBackground(selectionBackground);
         } else {
            renderer.setForeground(textForeground);
            renderer.setBackground(textBackground);
         }

         if ((value != null) && (value instanceof DefaultMutableTreeNode)) {
             Object userObject = ((DefaultMutableTreeNode) value).getUserObject();
             if (userObject instanceof CheckBoxNode2) {
                 CheckBoxNode2 node = (CheckBoxNode2) userObject;
                 renderer.setText(node.getText());
                 renderer.setSelected(node.isSelected());
             }
        }
        returnValue = renderer;
    }
    return returnValue;
    }
}

class CheckBoxNodeEditor extends AbstractCellEditor implements TreeCellEditor {
    CheckBoxNodeRenderer renderer = new CheckBoxNodeRenderer();
    ChangeEvent changeEvent = null;
    JTree tree;
    int nodeType = 0;

    public CheckBoxNodeEditor(JTree tree) {
        this.tree = tree;
    }

    public Object getCellEditorValue() {
        JCheckBox checkbox = renderer.getRenderer();
        if(nodeType == 1) {
            CheckBoxNode checkBoxNode = new CheckBoxNode(checkbox.getText(), checkbox.isSelected());
            return checkBoxNode;
        } else {
            CheckBoxNode2 checkBoxNode2 = new CheckBoxNode2(checkbox.getText(), checkbox.isSelected());
            return checkBoxNode2;
        }
    }

    public boolean isCellEditable(EventObject event) {
        boolean returnValue = false;
        if (event instanceof MouseEvent) {
        MouseEvent mouseEvent = (MouseEvent) event;
        TreePath path = tree.getPathForLocation(mouseEvent.getX(),
        mouseEvent.getY());
        if (path != null) {
            Object node = path.getLastPathComponent();
            if ((node != null) && (node instanceof DefaultMutableTreeNode)) {
                DefaultMutableTreeNode treeNode = (DefaultMutableTreeNode) node;
                Object userObject = treeNode.getUserObject();
                returnValue = ((userObject instanceof CheckBoxNode) || (userObject instanceof CheckBoxNode2));
                 if(userObject instanceof CheckBoxNode)
                    nodeType = 1;
                else if (userObject instanceof CheckBoxNode2)
                    nodeType = 2;
                else
                    nodeType = 0;
        }
    }
}
return returnValue;
}

public Component getTreeCellEditorComponent(JTree tree, Object value,
        boolean selected, boolean expanded, boolean leaf, int row) {

    System.out.println("getTreeCellEditorComponent");

    //tree.getSelectionPath().getPathComponent()

    Component editor = renderer.getTreeCellRendererComponent(tree, value,
            true, expanded, leaf, row, true);

    // editor always selected / focused
    ItemListener itemListener = new ItemListener() {
        public void itemStateChanged(ItemEvent itemEvent) {
            if (stopCellEditing()) {
                fireEditingStopped();
            }
        }
    };
    if (editor instanceof JCheckBox) {
        ((JCheckBox) editor).addItemListener(itemListener);
    }

    return editor;
}
}

class CheckBoxNode2 extends Vector{
String text;
boolean selected;

public CheckBoxNode2(String text, boolean selected) {
    this.text = text;
    this.selected = selected;
}

public CheckBoxNode2(String text, boolean selected, Object elements[]) {
    this.text = text;
    this.selected = selected;
    for (int i = 0, n = elements.length; i < n; i++) {
        add(elements[i]);
    }
}

public boolean isSelected() {
    return selected;
}

public void setSelected(boolean newValue) {
    selected = newValue;
}

public String getText() {
    //return "[" + text + "]";
    return text;
}

public String toString() {
    return getClass().getName() + "[" + text + "/" + selected + "]";
}
}

class CheckBoxNode {
String text;
boolean selected;

public CheckBoxNode(String text, boolean selected) {
    this.text = text;
    this.selected = selected;
}

public boolean isSelected() {
    return selected;
}

public void setSelected(boolean newValue) {
    selected = newValue;
}

public String getText() {
    return text;
}

public void setText(String newValue) {
    text = newValue;
}

public String toString() {
    return getClass().getName() + "[" + text + "/" + selected + "]";
}
}

class NamedVector extends Vector {
String name;

public NamedVector(String name) {
    this.name = name;
}

public NamedVector(String name, Object elements[]) {
    this.name = name;
    for (int i = 0, n = elements.length; i < n; i++) {
        add(elements[i]);
    }
}

public String toString() {
    return "[" + name + "]";
}
}
