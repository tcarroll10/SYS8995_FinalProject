# SYS8995_FinalProject

# Final Paper is in the .pdf file -> Final Paper_ENMA795_Fall2017_tcarr008.pdf

# to run code, follow steps below:

0. Download the input data from http://www.freddiemac.com/research/datasets/sf_loanlevel_dataset.html and place in a directory on your local machine or on a cloud provider bucket. Recommend to put all the loan files in one directory and the monthly observation files in another. (I used the sample data but it doesn't matter you can use the full dataset files.)

1. clone git respository to a local directory on your machine and import into an IDE (e.g., Eclipse) 

2. modify the app.java file:
    a. Configure the path in the .textFile() argument to your directories of the loan and observation JavaRDDs, respectively (e.g., line 44)
    b. If running locally, uncomment line 37 and comment out 40. If running on a cluster, comment line 40 and uncomment 37. Line 37 forces Spark to run locally otherwise.

3. Install maven

4. Build the assembly .jar for the project
  4a) Open a terminal and navigate to the directory where you cloned the code. 
  4b) use the 'mvn package' command to build the jar

5. Install Spark 2.3.0 (or configure a cluster on a cloud provider with Spark 2.3.0)

7. In the terminal, navigate to where spark-submit installed and copy the .jar to this location

8. run spark-submit <jar file name>

## Questions
tcarroll10@gmail.com
