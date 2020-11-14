# Setup the HDFS directory stucture and populate it. 

# Create new directory structure in HDFS
hdfs dfs -mkdir final
hdfs dfs -mkdir final/input

# Populate the input directory in HDFS with the input file
hdfs dfs -put input.txt hw/input

# Verify what is in the input data directory
hdfs dfs -ls hw/input
