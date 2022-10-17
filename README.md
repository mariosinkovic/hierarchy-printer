# hierarchy-printer
   java app for printing <child name> <parent name> hierarchical structures
   
# Instructions to compile with maven:
   To compile: CMD to project location and type: "mvn install" (compile will run the tests also)
   To run all tests: CMD to project location and type: "mvn -Dtest=*Test* test"

# Instructions to start application
   After compiling with maven you will get .jar file in maven user directory.
   To this directory paste Test.txt from {project location}\hierarchy-printer\Test.txt.
   After you paste Test.txt file into same folder where .jar is located you can cmd: "java -jar Altima_demo_01-1.0-SNAPSHOT.jar",
   this will start application and hierarchy of contents from Test.txt will be printed as:
Ivan
    Adam
        Stjepan
            Marko
            Robert
    Fran
Luka
    Leopold
        Marko
   Circular dependency in given data will rise an error.
   
   
# For printing custom data you can replace content of Test.txt from folder where .jar is located
   
