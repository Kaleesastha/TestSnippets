@echo off
rem $Id: after_startnms.bat,v 1.1 2006/02/18 06:23:46 sasidhar Exp $
rem This script may need to modify your environment after starting the WebNMS server and running the test program.

cd ..

rmdir /S /Q test10 test42 test44
del /S /Q test11 test12 test25

cd prov_org_config_act_Validate

copy /Y example.xml ..\test12
xcopy /E /Y /Q testsetup\test1 ..\
copy /Y  testsetup\IPRoutingConfiguration.xml ..\test15
copy /Y  CrossConnect1.xml ..\test25\



set classpath=.;..\..\classes\;..\..\classes\AdventNetFramework.jar;..\..\classes\crimson.jar;..\..\classes\xalan.jar;..\..\classes\ManagementServer.jar

javac -d . prov_org_config_act_tester.java

