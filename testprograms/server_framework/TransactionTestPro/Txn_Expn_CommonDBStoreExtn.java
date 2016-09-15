package com.adventnet.nms.store;

import com.adventnet.nms.store.*;
import com.adventnet.nms.store.relational.*;
import com.adventnet.nms.util.NmsLogMgr;
import com.adventnet.nms.util.NmsUtil;
import com.adventnet.nms.util.PureServerUtils;

import com.adventnet.management.log.Log;

public class Txn_Expn_CommonDBStoreExtn extends CommonDBStore
{
	boolean isDB = true;
    public com.adventnet.nms.store.relational.CommonTopoToRelational comtorel;

	public Txn_Expn_CommonDBStoreExtn () throws NmsStorageException {
		super();
		isDB = PureServerUtils.useJDBC;
		setPersistenceAPI();
	}
	public Txn_Expn_CommonDBStoreExtn (boolean isDatabase) throws NmsStorageException {
		super();
		isDB = isDatabase;
		setPersistenceAPI();
	}
	public Txn_Expn_CommonDBStoreExtn (int numberInMemory) throws NmsStorageException {
		super(numberInMemory);
		isDB = PureServerUtils.useJDBC;
		setPersistenceAPI();
	}
	public Txn_Expn_CommonDBStoreExtn (int numberInMemory,boolean isDatabase) throws NmsStorageException {
		super(numberInMemory);
		isDB = isDatabase;
		setPersistenceAPI();
	}
	public Object getxx(Object key)
		{
			return table.get(key);
		}
    public void updateObjectInMemoryOnly(java.lang.Object obj)
	{
			super.updateObjectInMemoryOnly(obj);
	}
////kaschange
    public java.lang.Object getObjectByKeyOnlyFromMemory(java.lang.String key) throws NmsStorageException,com.adventnet.management.transaction.UserTransactionException
	{
			java.lang.Object abc = super.getObjectByKeyOnlyFromMemory(key);
			return abc;

	}

///kaschange


	protected void setPersistenceAPI() throws NmsStorageException {
		if(isDB) {
			comtorel = new com.adventnet.nms.store.relational.CommonTopoToRelational();
			storage = (PersistenceAPI)comtorel;
			specialTable = new DBHashtable("TestDBHashtable","NAME","VALUE",false,null,true);
			//NmsLogMgr.TOPOUSER.log(NmsUtil.GetString("Number of ManagedObjects in Memory is set to ") + numMemoryObjects + NmsUtil.GetString(" and Using RelationalDB"),Log.VERBOSE);
		}
	}

}
