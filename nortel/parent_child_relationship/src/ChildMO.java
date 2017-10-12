	package test;

	import java.util.Properties;

	public class ChildMO extends com.adventnet.nms.topodb.ManagedObject {

		private String sysDesc;

		public ChildMO() {
			setClassname("ChildMO");
		}

		public void setSysDesc(String sysDesc) {
			this.sysDesc = sysDesc;
		}

		public String getSysDesc() {
			return sysDesc;
		}

		public void setProperties(Properties props) {
			String sysDesc_value = (String) props.remove("sysDesc");
			if (sysDesc_value != null) {
				sysDesc = sysDesc_value;
			}
			super.setProperties(props);
		}

		public Properties getProperties() {
			Properties props = super.getProperties();
			if (getSysDesc() != null) {
				props.put("sysDesc", getSysDesc());
			}
			return props;
		}

	}
