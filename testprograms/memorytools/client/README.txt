README

Contents :


  1.Introduction

  2.Files to automate various tasks
 
  3.About WinrunnerScripts	  

  4.Things to do 
  		



Introduction :

	This file will help you to kick start the memory testing operation. Here we have listed the various files which we have used in the last memory testing operation. Kindly use high end machine ( 512MB ram ) for automation. Here our aim of the operation is to restrict the unnecessary object creation in other words to avoid memory leaks.


Files to automate various tasks ::


  " < CVS NmsSource dir >\testprograms\memorytools\client "  directory contains various java programs which can be used to do memory related operations.

1. AddCustomView.java

	This file adds custom views repeatedly on clicking the menu item.

2. SelectTreeNode.java

	This class selects all the tree nodes repeatedly.

3.ClientLoader.java

	This is a class which extens AbstractNMSPanel.You can load this class in "NmsPanels.conf". This will select each tree nodes dynamically. You can put the for loop to select the nodes multiple times. 



About WinRunner Scripts:

         Winrunner is a testing tool for automating the various tasks carried out in a User Interface. We can use this tool for memory testing. We have automated the common task done by the user, using Winrunner. The scripts can be extracted from "WRscripts.zip" present in " < CVS NmsSource dir >\testprograms\memorytools\client ". 



What we have done:

	This topic will let you know what are all the common tasks that can be carried out in client . And this operations must be monitored for memory leaks in the new release. Following are the list of operations we have choosen to identify memory leaks.


 We have selected following operations for memory testing . I have classified the operations as three types.

i) Menu Bar Operations

ii) Tool Bar Operations

iii) Tree Node Operations
 
 
  Identified    Operations             Descriptions Remarks 

Menu Bar Operations :

i) Node Operations  

          -  AddTreeNode
          -  ModifyTreeNode
          -  RemoveTreeNode
          -  MoveTreeNode   
                  
           Invoke the user interface for all the given operations and close the dialogs.
Test the instances are disposed properly.
 
ii) Broad Cast Message  Broad cast message to clients, do close the dialog. Test instance created were disposed properly.

iii) Custom Views

         - AddCV
         - ModifyCV
         - RemoveCV
         - RenameCV  
iv) RunTime Administration

v) Change Password

vi) Apply Themes

vii) Policy 
        - Add Policy
        - Delete Policy
        - Execute Policy
        - Stop Policy
 
Tool Bar Operations :

i) Traverse between panels
ii) Detach panels
iii) Refresh
iv)Search Sysmbol


Tree Node Operations : 

i)  In map client

          - Invoke properties dialog. 
          - Add container 
          - AddMap 
          - DeleteMap 
          - ModifyMap 
          - AddNode 
          - AddNetwork 

vi)General Operations 
    - Internal frames operation 
                  -Detach frames 
                  -Close frames 
 
    - List view operations.




Things to do :

	In the past we tested only the ApplicationClient for memory testing, we should start testing the Applet Client and
HTML client also. This is important because a customer intimates the memory leak in applet client.The issue is as follows,
When we connect multiple client in the same browser window, the browser consumes more memory. We have listed below the 
list of operations that has to be done ,


1. Test Applet Client 
             - Test connecting number of clients to the same browser window . Do the following steps to reproduce the issue.

	a.) Start a applet client in a browser with url " http://<hostname>:<port> "


	b.) Close the applet client.

	
   	c.) Open another applet client (ie., http://localhost:9090 ) in the same browser

 
       Do the steps 'a' to 'c' number of times , you can noticethat the browser memroy usage increases when we connect to a new client.











	






