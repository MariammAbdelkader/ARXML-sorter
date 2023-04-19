@ECHO OFF
javac ARXML.java

ECHO Normal case test:
java ARXML.java Normal.arxml
ECHO normal_mod.arxml generated successfully

ECHO Not valid file extension case test:
java ARXML.java wrongExtention.txt

ECHO Empty file case test:
java ARXML.java Empty.arxml

pause