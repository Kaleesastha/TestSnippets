/**
 * ExtendedClass.java
 *
**/

package com.adventnet.nms.example.propagationfilter;

import com.adventnet.nms.alertdb.*;

/**
 * This is an example class which extends MiniAlert that can be used in propagation filters.
 * A extended class defined in a propagation.filters conf file like
 *      <EXTENDED-CLASS className="test.ExtendedClass">
 * will be instantiated by the constructor by passing the corresponding
 * Alert object.
 * If propagation.filters file contains the above tag, all the defined
 * propagation filters will be notified by the instances of this
 * Extended Class.
 * Basically this mechanism can be used when the default properites
 * of MiniAlert object does not satisfy the user's requirement.
 * In the other words, if any of the Alert's properties (other than default MiniAlert's properties) 
 * are needed  * in the Propagation Filter, user can Extend MiniAlert.
 * This ExtendedClass has two more fields namely message and createTime.
 * During initialization, this ExtendedClass will be passed by the Alert object.
 * message and createTime fields of Alert are transferred from Alert object
 * to ExtendedClass object. Since this ExtendedClass is passed to the
 * propagation filters, message and createTime field of Alert can be 
 * referred in the propagation filters.
 * @since WebNMS 2.2
 */

public class ExtendedClass extends MiniAlert
{
	/**
	 * If the Propagation Filters are in the need of message and createTime
	 * field of Alert, both of them should be defined here.
	 */
	private String message;   // Message of the corresponding alert
	private long createTime;  // Created time of the corresponding alert
	
	/** 
	 * Constructor which will be used to instantiate the extended class
	 * and will be passed the alert which has been added/updated/deleted.
	 */
	public ExtendedClass(Alert alt)
	{
		// copy the values of alerts which will be used in the propagation filters.
		message = alt.getMessage();
		createTime = alt.getCreateTime();
	}
	
	/**
	 * Returns the created time of the corresponding alert.
	 */
	public long getCreateTime()
	{
		return createTime;
	}
	
	/**
	 * Returns the message describing the corresponding alert.
	 */
	public String getMessage()
	{
		return message;
	}
}
