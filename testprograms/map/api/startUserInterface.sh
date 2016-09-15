#We have to set the classpath for AdventNet/WebNMS/classes
export CLASSPATH=.:/AdventNet/WebNMS/classes/
javac GetMapFunctions.java
javac UpdateMap.java
javac UpdateLink.java
javac UpdateContainer.java
javac UpdateSymbol.java
javac UserInterfaceMap.java
java UserInterfaceMap

