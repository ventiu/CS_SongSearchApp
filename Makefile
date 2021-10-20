run: compile
	java SongSearchApp

compile: dataWrangler searchBackEnd searchFrontEnd SongSearchApp.class
	echo "Use this rule to compile all necessary java source files"

dataWrangler: SongData.class SongLoader.class
	

SongLoader.class: SongLoader.java
	javac SongLoader.java

SongData.class: SongData.java
	javac SongData.java

searchBackEnd: SearchBackEnd.class

searchFrontEnd: SearchFrontEnd.class

SearchBackEnd.class: SearchBackEnd.java
	javac SearchBackEnd.java

SearchFrontEnd.class: SearchFrontEnd.java
	javac SearchFrontEnd.java

SongSearchApp.class: SongSearchApp.java
	javac SongSearchApp.java
