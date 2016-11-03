//$Id :$
package com.adventnet.nms.tools.CustomView;
    class ListEntry {
        Object object;
        int    level;
        boolean isNode;
		String imageName;
		String treeId;
        public ListEntry(Object anObject,String s1,String s2,int aLevel,boolean isNode) {
            object = anObject;
            level = aLevel;
            this.isNode = isNode;
			imageName=s1;
			treeId=s2;
        }

        public Object object() {
            return object;
        }
		public String image()
		{
			return imageName;
		}
		public String getTreeId()
		{
			return treeId;
		}	
        public int level() {
            return level;
        }

        public boolean isNode() {
            return isNode;
        }
    }
