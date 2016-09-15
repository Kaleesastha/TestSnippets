										README

This directory contains examples to test various memory related functionalities.

1.  AddEvent.java

	This class can be used to add Events at a very faster rate.

2.  FaultObserver.java

	This class implements all the observers/listeners in Fault. Can be used
	while testing Observers/Listeners clean up testing.

3.  CompleteObserver.java

	This class implements all observers/listeners in Fault and Performance
	modules.

4.  ObserverTest.java

	Utility class which registers and deregisters all the observers defined in
	FaultObserver.

5.  FaultOperations.java

	Class which starts various threads to do all fault related operations
	simultaneously.

6.  FrameworkOperations.java

	Class which does various RMI client testing.

7.  TopoOperations.java

	Class which does add/update and delete operations on Topo Objects
	simultaneously.

8.	StartMemoryTesting.java

	Overall wrapper of all the above operations.
	
9.  GetTrapParsers.java

	Utility class which gets TrapParsers from file repeatedly.

10.  MultiSendTrap.java

	Utility class to send traps at desired rate.

11.  SecondApp.java

	Utility class which acts as an TL1Agent and sends TL1AutonomousMessages.

12. SystemInfoWithDelete.java

	Utility class which prints system memory and purges Events periodically.

13. FaultMemory.html

	File which lists all the memory intensive operations in Fault







































