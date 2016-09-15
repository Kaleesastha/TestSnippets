------------------------------------------------------------------------------
AdventNet Web NMS 4 - Modulewise TestPrograms Used for Memory Analysing.
								ReadMe
					Updated on December 17, 2002
------------------------------------------------------------------------------
README Contents:
------------------------------------------------------------------------------
Following are the explanation about the test programs used with OptimizeIt for Memory Analysing operation.

Module	:	Database

1. TestPrepStat.java

	This Program is useful for checking whether any PreparedStatement leak is 	there when running with Web NMS. This checks the leak by issuing same query in a loop to the method getPreparedStatementID() to get the PreparedStatement ID. This implements RunProcessInterface, so it can be run as a process in Web NMS environment.
