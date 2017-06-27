Source Code File Organization
=============================

The brief description of the functions of all source files is as follows:

AdventNet_WebNMS_JMX_MIB.java
----------------------------- 

In this file,all  instrument files for scalars, table files for tables and the utility files are registered, all the adaptors are instantiated with their corresponding ports and all NMS APIs are instantiated for providing the details of the NMS. Besides all these,observers are implemented for being intimated by the NMS of events and the implementations for sending notifications too are done in the main file.


*Instrument.java
----------------

These files are for all the scalar groups in the mib. The corresponding get and set method implementations are done here using the NMS APIs. An instrument file and an xml file are generated for each scalar group in the MIB.


*Table.java and *Entry.java
---------------------------

These files are for all the tables in the mib. The implementation parts for the get and set operations are done here using the NMS APIs. A *table.java and and *entry.java along with an *table.xml file are generated for each table in the MIB. An entry file contains the set and get methods for each element in the table.


TopoObserver.java,FaultObserver.java,GenUtils.java,FaultUtils.java,TopoUtils.java
---------------------------------------------------------------------------------

These files are used for forming the notifications. These files are called by the main file when being intimated for events in the NMS..


NotificationLogger.java
-----------------------

This file is used for logging all the notifications sent by the Agent to the Manager.All the notifications are logged in the database. The database has a table called NotificationLog in which these notifications are stored. The corresponding details for each notification is stored in the VarbindLogTable.


NMS Configuration Files 
-----------------------
The utility files being used are : TopoConfImpl.java,TrapConfImpl.java,LogUserConfImpl.java. These files contain various implementations.For example,the TopoConfImpl.java file contains the implementation details for the run time configuration of the Topology module of the NMS.
