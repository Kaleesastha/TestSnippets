//$Id: XmlUIElement.java,v 1.1.1.1 2006/11/16 15:39:58 gramkumar Exp $
package com.adventnet.nms.provisioning.ui.uielements;

import javax.swing.*;
import java.awt.*;
import com.adventnet.nms.provisioning.ConstraintsViolatedException;

/** 
 * The Interface that is implemented by all the elements that appear when a Template is rendered.
 * A Label and a component pair present in the UI constitutes one XmlUIElement.
 * For each UserInput tag present in the template, based on the Qualifier tag, the corresponding
 * implementation of the XmlUIElement instances will be created and added to the Provisioning client.
 * For example, if the Qualifier type is "combo", then the instance of ComboUIImpl will be created.
 * The ComboUIImpl will have a Label and a ComboBox as its instance variables which it will return
 * when asked.<br><br>
 * 
 * This interface should be implemented by the users if they need their UI components to appear
 * in the Provisioning client(i.e., to plugin their components.).
 * After creating the class, an entry should be given in the file TypeToUIElementMapping.txt,
 * which is in the classes directory, specifying the name of the component and the corresponding class name.
 * Then, the name of the component can be given in the Qualifier tag for UserInputs present in the Template.
 * An abstract implementation of this interface, <a href=AbstractXmlUIElement.html>AbstractXmlUIElement</a>
 * have been provided, which the users are requested to extend  rather than implementing 
 * all the methods in this interface.
 */
public interface XmlUIElement
{
    /**
     * Sets whether the component can be edited or not. Users can make their component editable or not 
     * in their implementation of this method. If the UserInput tag present in the Template has an "editable"
     * attribute, this method will be called with the corresponding boolean value.
     *
     * @param b the boolean value of the attribute "editable"
     */
    void setEditable(boolean b);

    /**
     * Returns the number of columns the UI element will be occupying. This value will be passed to the method 
     * addComponents where the users can lay their components. The implementation in
     * AbstractXmlUIElement returns the value 2, as there will be two columns, one for a Label and
     * the other for the components like TextField or Combo etc.
     *
     * @return the number of columns this UI element will be occupying.
     */
    int getNumberOfColumns();

    /**
     * Returns the value of this XmlUIElement. The implementing method should return the value of the
     * component it represents. For example, the TextFieldUIImpl returns the text 
     * present in the text field.
     *
     * @return The value of this XmlUIElement.
     */
    String getValue();

    /**
     * Sets the passed argument as the value of this XmlUIElement. The implementing method should set
     * the value of the argument for the component it represents. For example, the TextFieldUIImpl sets
     * the passed value to its text field.
     *
     */
    void setValue(String value);

    /**
     * Adds the component that is present in this element to the passed parent component and returns
     * the number of rows added by this method. The implementing class should add the 
     * component it contains, to the parent component that is passed to this
     * method. The default implementation in AbstractXmlUIElement adds a JLabel in the 
     * first column and the component as returned by the method getComponent() in the second column.
     * The extending class can override the getComponent() and can simply return a component without
     * creating any Label.
     * This method can be effectively utilised for laying the components in any preferred way.
     *
     * @param parentComp the parent component to which the component should be added.
     *
     * @param gc the gridbag constraints that can be used to add the component.
     *
     * @param row the row to which the element should add the components. The gridbag constraint should be set
     * accordingly.
     *
     * @param col the column to which the element should add the components. The gridbag constraint should be
     * set accordingly.
     *
     * @param maxCol the maximum value as returned by the method getNumberOfColumns() of each element.
     */
    int addComponents(JComponent parentComp,GridBagConstraints gc,int row,int col,int maxCol);


    /**
     * Sets the text to the Label.
     * 
     * @param name The value of the "label" attribute present in the UserInput tag of the template.
     */
    void setLabelName(String name);

    /**
     * Returns the text present in the Label.
     */
    String getLabelName();

    /**
     * Sets the range for this element. The value of the "range" attribute of the Qualifier tag will be 
     * passed to this method.
     */
    void setRange(String range);

    /**
     * Checks whether any constraint is violated. Eg., for the case of NumericTextFieldUIImpl, this method
     * checks whether the value entered is within the range or not. If not, it throws ConstraintsViolatedException.
     * The exception message will be prompted to the user.
     *
     * @throws ConstraintsViolatedException
     * 
     */
    void checkConstraints() throws ConstraintsViolatedException;
    
    /**
     * Sets the enumerated values to the element. If the Qualifier contains Enum tags, then the names and the
     * values of all the Enums will be passed to this method. The default implementation in AbstractXmlUIElement
     * simply returns without doing anything.
     *
     * @param names The String array containing the value of Enum "name" attributes.
     *
     * @param values The String array containing the vlaue of Enum "value" attributes.
     */
    void setEnumeratedValues(String names[],String values[]);

    /**
     * Sets the description to the Label and the component. The implementation in AbstractXmlUIElement
     * sets the description to the label and the component by calling their setName() method.
     * 
     */
    void setDescription(String descArg);	

    /**
     * Marks this element as mandatory in the Provisioning client. Without entering any value for this
     * element Provisioning operation cannot proceed. If the user does not enter any value, a
     * message will popup informing that the value cannot be empty.
     *
     * @param b the boolean value of the attribute "required" present in the UserInput tag.
     * 
     */
    void setRequired(boolean b);

    /**
     * Returns the boolean value set by the method setRequired().
     */
    boolean isRequired();

    /**
     * Returns whether the element is empty. This method will be called if "required" value
     * is set to true, to check the empty value of the element.
     */
    boolean isValueNull();
}
