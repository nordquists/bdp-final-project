# Build the jar file and run

# Remove class and jar files
rm *.class
rm *.jar

# Compile
javac -classpath `yarn classpath` -d . ProfilingMapper.java
javac -classpath `yarn classpath` -d . ProfilingReducer.java
javac -classpath `yarn classpath`:. -d . Profiling.java

# Create jar file
jar -cvf profiling.jar *.class

# Run the program
hadoop jar profiling.jar Profiling /user/"$USER"/final/input_preprocessed /user/"$USER"/final/profiling_output/not_preprocessed

