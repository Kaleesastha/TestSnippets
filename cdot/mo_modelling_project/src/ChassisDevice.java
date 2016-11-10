package common.mo.model;

import java.util.Properties;

public class ChassisDevice extends com.adventnet.nms.topodb.TopoObject {

	private String ChassisId;
	private String Region;
	private long AddTime;
	private String MacId;
	private String ModelNumber;
	private String SerialNumber;

	public ChassisDevice() {
		setClassname("ChassisDevice");
	}

	public void setChassisId(String ChassisId) {
		this.ChassisId = ChassisId;
	}

	public void setRegion(String Region) {
		this.Region = Region;
	}

	public void setAddTime(long AddTime) {
		this.AddTime = AddTime;
	}

	public void setMacId(String MacId) {
		this.MacId = MacId;
	}

	public void setModelNumber(String ModelNumber) {
		this.ModelNumber = ModelNumber;
	}

	public void setSerialNumber(String SerialNumber) {
		this.SerialNumber = SerialNumber;
	}

	public String getChassisId() {
		return ChassisId;
	}

	public String getRegion() {
		return Region;
	}

	public long getAddTime() {
		return AddTime;
	}

	public String getMacId() {
		return MacId;
	}

	public String getModelNumber() {
		return ModelNumber;
	}

	public String getSerialNumber() {
		return SerialNumber;
	}

	public void setProperties(Properties props) {
		String ChassisId_value = (String) props.remove("ChassisId");
		if (ChassisId_value != null) {
			ChassisId = ChassisId_value;
		}

		String Region_value = (String) props.remove("Region");
		if (Region_value != null) {
			Region = Region_value;
		}

		String AddTime_value = (String) props.remove("AddTime");
		if (AddTime_value != null) {
			AddTime = Long.parseLong(AddTime_value);
		}

		String MacId_value = (String) props.remove("MacId");
		if (MacId_value != null) {
			MacId = MacId_value;
		}

		String ModelNumber_value = (String) props.remove("ModelNumber");
		if (ModelNumber_value != null) {
			ModelNumber = ModelNumber_value;
		}

		String SerialNumber_value = (String) props.remove("SerialNumber");
		if (SerialNumber_value != null) {
			SerialNumber = SerialNumber_value;
		}
		super.setProperties(props);
	}

	public Properties getProperties() {
		Properties props = super.getProperties();
		if (getChassisId() != null) {
			props.put("ChassisId", getChassisId());
		}

		if (getRegion() != null) {
			props.put("Region", getRegion());
		}

		props.put("AddTime", String.valueOf(getAddTime()));

		if (getMacId() != null) {
			props.put("MacId", getMacId());
		}

		if (getModelNumber() != null) {
			props.put("ModelNumber", getModelNumber());
		}

		if (getSerialNumber() != null) {
			props.put("SerialNumber", getSerialNumber());
		}
		return props;
	}

}

