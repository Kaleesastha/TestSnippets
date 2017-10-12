	package test;

	import java.util.Properties;

	public class TestMO extends com.adventnet.nms.topodb.ManagedObject implements com.adventnet.nms.topodb.GroupInterface {

		private String sysNode;

		public TestMO() {
			setClassname("TestMO");
		}

		public void setSysNode(String sysNode) {
			this.sysNode = sysNode;
		}

		public String getSysNode() {
			return sysNode;
		}

		public void setProperties(Properties props) {
			String sysNode_value = (String) props.remove("sysNode");
			if (sysNode_value != null) {
				sysNode = sysNode_value;
			}
			super.setProperties(props);
		}

		public Properties getProperties() {
			Properties props = super.getProperties();
			if (getSysNode() != null) {
				props.put("sysNode", getSysNode());
			}
			return props;
		}

	}
