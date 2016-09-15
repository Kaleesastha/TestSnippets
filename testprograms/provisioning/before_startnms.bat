@echo off
rem $Id: before_startnms.bat,v 1.1 2006/02/18 06:23:46 sasidhar Exp $
rem This script may need to be modified for your environment before starting the WebNMS server.



xcopy /E /Q /Y testsetup ..\

cd ..

rmdir /S /Q test13 test14
del /S /Q  test15 test16
cd prov_org_config_act_Validate

