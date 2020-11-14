# Build the jar file and run

# Remove class and jar files
rm *.class
rm *.jar

# Compile
javac -classpath `yarn classpath` -d . CleaningMapper.java
javac -classpath `yarn classpath` -d . CleaningReducer.java
javac -classpath `yarn classpath`:. -d . Cleaning.java

# Create jar file
jar -cvf cleaning.jar *.class

# Run the program
hadoop jar cleaning.jar Cleaning /user/"$USER"/final/input /user/"$USER"/final/output

