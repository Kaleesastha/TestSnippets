set NMS_CLASSES=C:\WebNMS\21Sep\classes
set CLASSPATH=%NMS_CLASSES%;%NMS_CLASSES%\NmsServerClasses.jar;.C:\Work_that_Works\testprograms\map\api\2
javac -d 2 GetMapFunctions.java UpdateMap.java UpdateLink.java UpdateContainer.java UpdateSymbol.java UserInterfaceMap.java
java UserInterfaceMap
