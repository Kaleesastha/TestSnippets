// $Id: NotificationObject.java,v 1.1 2004/05/24 14:54:15 sujithr Exp $

/**
 * NotificationObject.java
 *
 *
 * Created: Wed Feb 27 18:30:50 2002
 *
 * @author  Chitrapandian N
 * @version
 */

package txn;

/**
 * NotificationObject is used for storing objects & details that needs
 * to be processed and notified to the Observers or Notifiers.
 *
 * @author Chitrapandian N
 * @version 1.0
 * @since AdventNet WebNMS 2.3 SP (Transaction Special Operation) 
 */
public class NotificationObject 
{

    // Private Variables ----------------------------------------------

    //Stores the type to be notified, like Status update, added, deleted, etc..,
    private String type;

    //Stores the object to be notified to the notifier, like
    //ManagedObject, Event, PollData, etc.,
    private Object obj;

    // Constructors ---------------------------------------------------

    /**
     * Creates a new <code>NotificationObject</code> instance.
     *
     */
    public NotificationObject ()
    {
        this.type = null;
        this.obj = null;
    }

    /**
     * Creates a new <code>NotificationObject</code> instance.
     *
     * @param type a <code>String</code> value
     */
    public NotificationObject (String type)
    {
        this.type = type;
        this.obj = null;
    }

    /**
     * Constructs an <code>NotificationObject</code> with the
     * specified type and object.
     *
     * @param type String value of the type to be notified. 
     * @param obj an <code>Object</code> value to be notified.  
     */
    public NotificationObject (String type, Object obj)
    {
        this.type = type;
        this.obj = obj;
    }

    // Public Methods ------------------------------------------------

    /**
     * Sets the type to be notified to the notifier, like Status
     * update, added, deleted, etc.,
     *
     * @param type String value of the type to be notified. 
     */
    public void setType (String type)
    {
        this.type = type;
    }

    /**
     * Returns the type to be notified to the notifier.
     *
     * @return a <code>String</code> value of the type.
     */
    public String getType ()
    {
        return type;
    }

    /**
     * Sets the object for notification, like ManagedObject, Event,
     * PollData,etc.,
     *
     * @param obj an <code>Object</code> value to be notified.  
     */
    public void setObject (Object obj)
    {
        this.obj = obj;
    }

    /**
     * Returns the object to be notified to the notifier.
     *
     * @return an <code>Object</code> value
     */
    public Object getObject ()
    {
        return obj;
    }
	public String toString()
	{
		StringBuffer str = new StringBuffer();
		str.append("Type : " + getType());
        if(getObject() != null)
            str.append("\tObject : " + getObject().toString());
		return str.toString();
    }
}

// NotificationObject.java











