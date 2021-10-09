run: compile
	echo "Add command to run the program here"

compile: dataWrangler
	echo "Use this rule to compile all necessary java source files"

dataWrangler: SongData.class SongLoader.java
	

SongLoader.class: SongLoader.java
	javac SongLoader.java

SongData.class: SongData.java
	javac SongData.java
