               Example implementation of UserDiscovery process.
			   ***********************************************

	 ServiceDiscovery is an example implementation of userdiscovery process for 
	 testing purposes. To test the userdiscovery process using the ServiceDiscovery, 
	 specify the entries in the userdiscovery.conf as given in the ServiceDiscovery.java file.
	 
	 The ServiceDiscovery can be used to add objects of class 'ServiceObject' (that 
	 extends TopoObject). Please follow the following procedures for adding objects 
	 of class ServiceObject using ServiceDiscovery (and cold start the NMS
	 server).
	 
     1)	 Copy and paste the databaseschema and aliases present in this directory to 
	 the WebNMS/conf/DatabaseSchema.conf and DatabaseAliases.conf to create a database 
	 table for the ServiceObject.

	 2) Include the following  entry in the WebNMS/conf/relationalclasses.conf file.

   com.adventnet.nms.topodb.ServiceObject   topodb  RelationalServiceObject   ServiceObject 

	 3) Compile all the java files in this directory and direct the .class to WebNMS/classes/ 
	 directory.
	 
	 4) Include the entries in the WebNMS/conf/userdiscovery.conf file for performing 
	 ServiceDiscovery. (refer ServiceDiscovery.java file)
