package com.adventnet.nms.eventdb;

import com.adventnet.snmp.sas.ProtocolDataUnit;

public interface V3CredentialsValidator {
	
	public int validate(ProtocolDataUnit ppdu);

}
