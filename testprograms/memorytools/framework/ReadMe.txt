------------------------------------------------------------------------------
AdventNet Web NMS 4 - Modulewise TestPrograms Used for Memory Analysing.
								ReadMe
					Updated on December 17, 2002
------------------------------------------------------------------------------
README Contents:
------------------------------------------------------------------------------
Following are the explanation about the test programs used with OptimizeIt for Memory Analysing operation.

Module	:	Framework

1. RunSystemGC.java:

	This Program is useful for running System.gc ( on periodical basis ) in java programing for effective analysis of exact memory occupied by objects in the memory. This implements RunProcessInterface, so it can be run as a process in Web NMS environment.

2. startFEInLoop.sh:

	This is the script used for running FE in a loop. This script will start FE in the background and sleep for 200s, then shutdown FE. This will happen in a loop till we give CTRL+C. This program should be run from <WebNms_FE_Home>/bin directory. Before running this script, appropriate change should be done for startnmsFE script so that it can connect to appropriate BE.

