run: compile
	java SongSearchApp

compile: dataWrangler searchFrontEnd searchBackEnd SongSearchApp.class
	echo "Use this rule to compile all necessary java source files"

dataWrangler: SongData.class SongLoader.java
	

SongLoader.class: SongLoader.java
	javac SongLoader.java

SongData.class: SongData.java
	javac SongData.java

searchFrontEnd: SearchFrontEnd.class SearchFrontEnd.java

SearchFrontEnd.class: SearchFrontEnd.java
	javac SearchFrontEnd.java

searchBackEnd: SearchBackEnd.class SearchBackEnd.java

SearchBackEnd.class: SearchBackEnd.java
	javac SearchBackEnd.java

SongSearchApp.class: SongSearchApp.java
	javac SongSearchApp.java
