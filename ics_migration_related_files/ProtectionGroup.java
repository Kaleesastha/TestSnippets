package com.motorola.emh.core.modeling;

import com.adventnet.nms.topodb.ManagedObject;
import java.math.BigInteger;
import java.util.Properties;

public class ProtectionGroup extends ManagedObject
{
  private BigInteger nValue;
  private int opState;
  private BigInteger entityIdentifier;
  private BigInteger entityType;

  public ProtectionGroup()
  {
    setClassname("ProtectionGroup");
  }

  public void setnvalue(BigInteger nValue) {
    this.nValue = nValue;
  }

  public void setOpState(int opState) {
    this.opState = opState;
  }

  public void setEntityIdentifier(BigInteger entityIdentifier) {
    this.entityIdentifier = entityIdentifier;
  }

  public void setEntityType(BigInteger entityType) {
    this.entityType = entityType;
  }

  public BigInteger getnvalue() {
    return this.nValue;
  }

  public int getOpState() {
    return this.opState;
  }

  public BigInteger getEntityIdentifier() {
    return this.entityIdentifier;
  }

  public BigInteger getEntityType() {
    return this.entityType;
  }

  public void setProperties(Properties props) {
    BigInteger nValue_value = (BigInteger)props.remove("nValue");
    if (nValue_value != null) {
      this.nValue = nValue_value;
    }

    String opState_value = (String)props.remove("opState");
    if (opState_value != null) {
      this.opState = Integer.parseInt(opState_value);
    }

    BigInteger entityIdentifier_value = (BigInteger)props.remove("entityIdentifier");
    if (entityIdentifier_value != null) {
      this.entityIdentifier = entityIdentifier_value;
    }

    BigInteger entityType_value = (BigInteger)props.remove("entityType");
    if (entityType_value != null) {
      this.entityType = entityType_value;
    }
    super.setProperties(props);
  }

  public Properties getProperties() {
    Properties props = super.getProperties();
    if (getnvalue() != null) {
      props.put("nValue", getnvalue().toString());
    }

    props.put("opState", String.valueOf(getOpState()));

    if (getEntityIdentifier() != null) {
      props.put("entityIdentifier", getEntityIdentifier().toString());
    }

    if (getEntityType() != null) {
      props.put("entityType", getEntityType().toString());
    }
    return props;
  }
}
