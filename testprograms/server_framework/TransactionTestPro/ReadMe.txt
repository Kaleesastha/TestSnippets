Use the below mentioned Test Programs for testing UserTransactionException


Compile the below mentioned source file and place the class files under WebNMS/classes dir

Txn_Expn_DBIndexedVector.java
Txn_Expn_RelationalAPI.java
Txn_Expn_DBPropertyTable.java
Txn_Expn_UserStorageAPI.java
Txn_Expn_CommonDBStoreExtn.java 
Txn_Expn_DBSchedulerVector.java  
Txn_Expn_CommonDBStoreTest.java
Txn_Expn_DBVector.java           
Txn_Expn_ConnectionPool.java    
Txn_Expn_DBVectorTable.java
Txn_Expn_DBHashtable.java
Txn_Expn_NmsPolicyAPI.java  

Make entry in the WebNMS/conf/NmsProcessesBE.conf for the processes you wish
to test.
eg:
PROCESS   txn.Txn_Expn_DBHashtable
ARGS      NULL 


The Result will be printed in the stdout.txt under WebNMS/logs dir

eg:
DB-TXN-EXP-001 :: PASSED
DB-TXN-EXP-001 :: FAILED


