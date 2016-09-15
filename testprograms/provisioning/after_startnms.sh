#$Id: after_startnms.sh,v 1.1 2006/02/18 06:23:46 sasidhar Exp $
# This script may need to modify your environment after starting the WebNMS server and running the test program.

cd ..

rm -rf test10 test42 test44
rm -rf test11/* test12/* test25/*

cd prov_org_config_act_Validate
cp -rf example.xml ../test12/
cp -rf testsetup/test14 ../
cp -rf testsetup/IPRoutingConfiguration.xml ../test15
cp -rf CrossConnect1.xml ../test25/
cp -rf testsetup/test42 ../
cp -rf  CrossConnect1.xml ../test25/


CLASSPATH=.:../../classes/:../../classes/AdventNetFramework.jar:../../classes/crimson.jar:../../classes/xalan.jar:../../classes:../../classes/ManagementServer.jar

export CLASSPATH

javac -d . prov_org_config_act_tester.java
