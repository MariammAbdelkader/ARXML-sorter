# ARXML-sorter
program that reads an ARXML file containing a list of containers, each with a unique ID, and  reorders the containers alphabetically by their name sub- container “"SHORT-NAME" with Java


# Requirements:
1-The name of the arxml file shall be an argument which needs to passed through the command line.

2-If the file is not having .arxml extension then you should trigger a user defined handled exception “NotVaildAutosarFileException”.

3-If the file is empty, then you should trigger user defined unhandled exception “EmptyAutosarFileException”

4-The output file shall be named as the same of the input file concatenated with “_mod.arxml”

           • e.g. if the input was named “Rte_Ecuc.arxml” then the output should be “Rte_Ecuc_mod.arxml”.
           
5-Assume any missing requirement and put it in your code
           
# Files:
ARXML.java: Java code containing main class ,AUTOSAR class and exceptions classes

Empty.arxml: empty arxml file to test the empty case

Normal.arxml: arxml file unordered to test the normal case

Normal_mod.arxml: arxml file after sorting

tests.bat:This is a batch file that runs your program

wrongExtention.txt:text document to test the wrong case

