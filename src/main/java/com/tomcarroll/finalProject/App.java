package com.tomcarroll.finalProject;

import static org.apache.spark.sql.functions.col;
import static org.apache.spark.sql.functions.when;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
//import org.apache.spark.api.java.JavaDoubleRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.ml.classification.LogisticRegression;
import org.apache.spark.ml.classification.RandomForestClassificationModel;
import org.apache.spark.ml.classification.RandomForestClassifier;
import org.apache.spark.ml.evaluation.BinaryClassificationEvaluator;
//import org.apache.spark.ml.feature.OneHotEncoder;
import org.apache.spark.ml.feature.OneHotEncoderEstimator;
import org.apache.spark.ml.feature.OneHotEncoderModel;
import org.apache.spark.ml.feature.StringIndexer;
import org.apache.spark.ml.feature.VectorAssembler;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import com.tomcarroll.finalProject.dao.Loan;
import com.tomcarroll.finalProject.dao.Observation;

//import scala.Tuple2;

//import org.apache.spark.sql.Encoder;
//import org.apache.spark.sql.Encoders;

public class App {
	public static void main(String[] args) {

		Logger.getLogger("org").setLevel(Level.ERROR);
		
		//use for running locally
		//SparkSession session = SparkSession.builder().appName("LoanAnalysis").master("local[1]").getOrCreate();
		
		//use when running on cloud cluster
		SparkSession session = SparkSession.builder().appName("LoanAnalysis").getOrCreate();
		
		JavaRDD<Loan> loanRDD = session.read()
				//google
				//.textFile("gs://tcarroll10finalproject/in/loan/*").javaRDD()
				
				//Amazon EMR
				.textFile("s3n://finalproject2/in/loan/*").javaRDD()
				
				//local
				//.textFile("/Users/tcarroll10/Dropbox/GradClasses/SYS8995/finalProject2/in/loan/*").javaRDD()
				
				//.textFile("/Users/tcarroll10/Dropbox/GradClasses/SYS8995/finalProject2/in/loan/sample_orig_1999.txt").javaRDD()
				
				
				.map(line -> {
					// since "|" is a regular expression you need to ensure split parses properly
					String[] parts = line.split("\\|");
					Loan ln = new Loan();
					try {
						ln.setCredit_score(Integer.parseInt(parts[0]));
						ln.setMaturity_date(Integer.parseInt(parts[3]));
						ln.setMsa(Integer.parseInt(parts[4]));
						ln.setMip(Integer.parseInt(parts[5]));
						ln.setNumber_of_units(Integer.parseInt(parts[6]));
						ln.setOcltv(Integer.parseInt(parts[8]));
						ln.setOriginal_upb(Double.parseDouble(parts[10]));
						ln.setOltv(Integer.parseInt(parts[11]));
						ln.setOriginal_interest_rate(Double.parseDouble(parts[12]));
						ln.setDti(Integer.parseInt(parts[9]));
						ln.setOriginal_loan_term(Integer.parseInt(parts[21]));
						ln.setNumber_of_borrowers(Integer.parseInt(parts[22]));

					} catch (NumberFormatException ex) { // handle your exception

					}

					ln.setFirst_payment_date(Integer.parseInt(parts[1]));
					ln.setFirst_time_homebuyer_flag(parts[2]);
					ln.setOccupancy_status(parts[7]);

					ln.setChannel(parts[13]);
					ln.setPrepayment_penalty_flag(parts[14]);
					ln.setProduct_type(parts[15]);
					ln.setProperty_state(parts[16]);
					ln.setProperty_type(parts[17]);
					ln.setPostal_code(parts[18]);
					ln.setLoan_purpose(parts[20]);
					ln.setSeller_name(parts[23]);
					ln.setServicer_name(parts[24]);
					ln.setLoan_sequence_number(parts[19]);
					// ln.setSuper_conforming_flag(parts[25]);
					// ln.setPre_harp_loan_sequence_number(parts[26]);

					return ln;
				});

		// Apply a schema to an RDD of JavaBeans to get a DataFrame
		Dataset<Row> loanDF = session.createDataFrame(loanRDD, Loan.class);

		// Register the DataFrame as a temporary view
		loanDF.createOrReplaceTempView("loan");

		Dataset<Row> loanSubsetDF = session.sql("SELECT * FROM loan");

		loanSubsetDF.show();
		System.out.println("the total number of records in the loan dataset is  " + loanSubsetDF.count());

		JavaRDD<Observation> ObservationRDD = session.read()
				
				//google
				//textFile("gs://tcarroll10finalproject/in/observation/*")
				
				//Amazon EMR
				.textFile("s3n://finalproject2/in/observation/*")
				
				
				//local
				//.textFile("/Users/tcarroll10/Dropbox/GradClasses/SYS8995/finalProject2/in/observation/*")
				//.textFile("/Users/tcarroll10/Dropbox/GradClasses/SYS8995/finalProject2/in/observation/sample_svcg_1999.txt")
				
				
				.javaRDD().map(line -> {
					// since "|" is a regular expression you need to ensure split parses properly
					String[] split = line.split("\\|");
					Observation obs = new Observation();

					try {
						obs.setReporting_period(Integer.parseInt(split[1]));
						obs.setCurrent_upb(Double.parseDouble(split[2]));
						obs.setLoan_age(Integer.parseInt(split[4]));
						obs.setRmm(Integer.parseInt(split[5]));
						obs.setZero_balance_effective_date(Integer.parseInt(split[9]));
						obs.setCurrent_interest_rate(Double.parseDouble(split[10]));

					} catch (NumberFormatException ex) { // handle your exception
						// System.out.println("there has been an error");
					}
					obs.setLoan_sequence_number(split[0]);
					obs.setDq_status(split[3]);
					obs.setRepurchase_flag(split[6]);
					obs.setModification_flag(split[7]);
					obs.setZero_balance_code(split[8]);

					return obs;
				});

		// Apply a schema to an RDD of JavaBeans to get a DataFrame
		Dataset<Row> observationDF = session.createDataFrame(ObservationRDD, Observation.class);

		// Register the DataFrame as a temporary view
		observationDF.createOrReplaceTempView("observation");

		// SQL statements can be run by using the sql methods provided by spark
		Dataset<Row> observationSubsetDF = session.sql("SELECT * FROM observation");

		observationSubsetDF.show();
		System.out.println(
				"the total number of records in the monthly observation dataset is  " + observationSubsetDF.count());

		Dataset<Row> defaultDF = session.sql(
				"SELECT ln.credit_score, original_upb, original_interest_rate, oltv, dti,loan_purpose, property_type, channel,property_state, first_payment_date,"
						+ "IF(md.first_serious_dq_date IS NOT NULL,1,0) AS class\n"
						+ "FROM loan ln LEFT JOIN (SELECT loan_sequence_number,\n"
						+ "MIN(reporting_period) AS first_serious_dq_date\n" + "FROM observation\n"
						+ "WHERE dq_status NOT IN ('0')\n" + "GROUP BY loan_sequence_number) md\n"
						+ "ON ln.loan_sequence_number = md.loan_sequence_number\n");

		System.out.println("This is the default dataset: ");
		defaultDF.show();
		defaultDF.groupBy("class").count().show();
		defaultDF.printSchema();

	

		System.out.println("the total count of defaultDF before filtering is: " + defaultDF.count());
		defaultDF = defaultDF.filter(defaultDF.col("credit_score").isNotNull());
		defaultDF = defaultDF.filter(defaultDF.col("original_upb").isNotNull());
		defaultDF = defaultDF.filter(defaultDF.col("original_interest_rate").isNotNull());
		defaultDF = defaultDF.filter(defaultDF.col("oltv").isNotNull());
		defaultDF = defaultDF.filter(defaultDF.col("dti").isNotNull());
		defaultDF = defaultDF.filter(defaultDF.col("loan_purpose").isNotNull());
		defaultDF = defaultDF.filter(defaultDF.col("property_type").isNotNull());
		defaultDF = defaultDF.filter(defaultDF.col("channel").isNotNull());
		defaultDF = defaultDF.filter(defaultDF.col("property_state").isNotNull());
		defaultDF = defaultDF.filter(defaultDF.col("first_payment_date").isNotNull());
		
		System.out.println("the total count of defaultDF after filtering is: " + defaultDF.count());

		System.out.println("class count after filtering the default dataset: ");

		defaultDF.groupBy("class").count().show();
		
		
		
		System.out.println("below are some summary stats on the numerical columns in the default dataset: ");
		defaultDF.describe("credit_score", "original_upb","original_interest_rate","oltv","dti").show();

		
		

		// Register the DataFrame as a temporary view to run queries against it
		defaultDF.createOrReplaceTempView("defaultDF");
		
		Dataset<Row> defaultByST = session.sql(
				"with defaultCount as (SELECT property_state, count(*) as defCt\n"
				+ "FROM defaultDF\n"
				+ "Where class = 1 and (LEFT(first_payment_date, 4) in (2005,2006,2007))\n"
				+ "GROUP BY property_state\n"
				+ "ORDER BY count(*) desc),\n"
				+ "totalCount as (SELECT property_state, count(*)as totalCt\n" 
				+ "FROM defaultDF\n"
				+ "Where (LEFT(first_payment_date, 4) in (2005,2006,2007))\n"
				+ "GROUP BY property_state\n"
				+ "ORDER BY count(*) desc)\n"
				+ "Select a.property_state, a.defCt,b.totalCt,(a.defCt/b.totalCt) as defRate\n"
				+ "From defaultCount a join totalCount b on a.property_state=b.property_state\n"
				+ "order by (a.defCt/b.totalCt) desc"
				);
		defaultByST.show(100,0);
		
		System.out.println("Defaults rate by year");
		Dataset<Row> defaultByYr = session.sql(
				"with defaultCount as (SELECT LEFT(first_payment_date, 4) as Year, count(*) as defCt\n"
				+ "FROM defaultDF\n"
				+ "Where class = 1\n"
				+ "GROUP BY LEFT(first_payment_date, 4)\n"
				+ "ORDER BY LEFT(first_payment_date, 4)),\n"
				+ "totalCount as (SELECT LEFT(first_payment_date, 4) as Year, count(*) as totalCt\n"
				+ "FROM defaultDF\n" 
				+ "GROUP BY LEFT(first_payment_date, 4))\n"
				+ "select a.year, a.defCt, b.totalCt,(a.defCt/b.totalCt) as defRate\n"
				+ "From defaultCount a join totalCount b on a.year=b.year\n"			
				+ "order by a.year"
				);
		
		defaultByYr.show();

		System.out.println("Defaults by credit score range");
		Dataset<Row> defaultByCr = session.sql(
				"with defaultCount as (SELECT LEFT(credit_score, 2)*10 as credit_score, count(*) as defCt\n"
				+ "FROM defaultDF\n"
				+ "Where class = 1 and credit_score BETWEEN 300 AND 850\n"
				+ "GROUP BY LEFT(credit_score, 2)\n"
				+ "ORDER BY LEFT(credit_score, 2)),\n "
				+ "totalCount as (SELECT LEFT(credit_score, 2)*10 as credit_score, count(*) as totalCt\n"
				+ "FROM defaultDF\n"
				+ "Where credit_score BETWEEN 300 AND 850\n"
				+ "GROUP BY LEFT(credit_score, 2)\n"
				+ "ORDER BY LEFT(credit_score, 2))\n"
				+ "select a.credit_score, a.defCt, b.totalCt,(a.defCt/b.totalCt) as defRate\n"
				+ "From defaultCount a join totalCount b on a.credit_score=b.credit_score\n"			
				+ "order by a.credit_score"

				);

		defaultByCr.show(100,0);
		
		//xfm categorical variables into one-hot encoded features
		StringIndexer indexer = new StringIndexer().setInputCol("loan_purpose").setOutputCol("loan_purpose_indexer");
		Dataset<Row> indexed = indexer.fit(defaultDF).transform(defaultDF);

		StringIndexer indexer2 = new StringIndexer().setInputCol("property_type").setOutputCol("property_type_indexer");
		Dataset<Row> indexed2 = indexer2.fit(indexed).transform(indexed);

		StringIndexer indexer3 = new StringIndexer().setInputCol("channel").setOutputCol("channel_indexer");
		Dataset<Row> indexed3 = indexer3.fit(indexed2).transform(indexed2);

		StringIndexer indexer4 = new StringIndexer().setInputCol("property_state").setOutputCol("property_state_indexer");
		Dataset<Row> indexed4 = indexer4.fit(indexed3).transform(indexed3);
		
	
		indexed4.show(20, 0);

		OneHotEncoderEstimator encoder = new OneHotEncoderEstimator()
			.setInputCols(new String[] { "loan_purpose_indexer", "property_type_indexer", "channel_indexer", "property_state_indexer" })
			.setOutputCols(new String[] { "loan_purpose_one_hot", "property_type_one_hot", "channel_one_hot", "property_state_one_hot" });

		OneHotEncoderModel model1 = encoder.fit(indexed4);
		Dataset<Row> encoded = model1.transform(indexed4);
		
	
		VectorAssembler assembler = new VectorAssembler()
				.setInputCols(new String[] { "credit_score", "original_upb", "original_interest_rate", "oltv", "dti",
						"loan_purpose_one_hot", "property_type_one_hot", "channel_one_hot", "property_state_one_hot" })
				.setOutputCol("features");

		Dataset<Row> df2 = assembler.transform(encoded);

		System.out.println(
				"this is a new dataset that extracts the features that most contribute to the classification, it results in a new dataframe with all of the feature columns in a vector column:  ");
		df2.show();

		System.out.println("this the schema for the dataset with the features in a vector column:  ");
		df2.printSchema();

		StringIndexer labelIndexer = new StringIndexer().setInputCol("class").setOutputCol("label");
		Dataset<Row> df3 = labelIndexer.fit(df2).transform(df2);

		// the transform method produced a new column: label.**

		System.out.println("Next use a StringIndexer to return a Dataframe with the class column added as a label .  ");
		df3.show();

		System.out.println("Below is the schema for the Dataframe with the class column added as a label .  ");
		df3.printSchema();

		System.out.println("view the features vector that are part of the final dataset.");
		df3.select("features").show(20, 0);

		
		//split data into training and testing
		Dataset<Row>[] splits = df3.randomSplit(new double[] { 0.7, 0.3 });
		Dataset<Row> trainingData = splits[0];
		Dataset<Row> testData = splits[1];
	
		RandomForestClassifier classifier = new RandomForestClassifier().setLabelCol("label")
				.setFeaturesCol("features");

		// print out the random forest trees
		RandomForestClassificationModel model = classifier.fit(trainingData);

		// System.out.println("Learned classification forest model:\n" +
		// model.toDebugString());

		// run the model on test features to get predictions**
		Dataset<Row> predictions = model.transform(testData);

		// As you can see, the previous model transform produced a new columns:
		// rawPrediction, probablity and prediction.**
		System.out.println("printing the predictions and class count from the RF model: ");
		
		predictions.show();
		predictions.groupBy("class").count().show();

		// create an Evaluator for binary classification, which expects two input
		BinaryClassificationEvaluator evaluator = new BinaryClassificationEvaluator().setLabelCol("label")
				  .setMetricName("areaUnderPR");
		double precision = evaluator.evaluate(predictions);
		System.out.println("the precision of the Random Forrest model is: " + precision);
		
		//another evaluator to look at accuracy, no need to set metric type accuracy by default
		BinaryClassificationEvaluator evaluator1 = new BinaryClassificationEvaluator().setLabelCol("label");
		double accuracy = evaluator1.evaluate(predictions);
		System.out.println("the accuracy of the Random Forrest model is: " + accuracy);
		
		
		//imbalanced dataset so want to try weighed linear regression as a type to see if that improves accuracy
		trainingData.show();
		trainingData.groupBy("class").count().show();
		trainingData.printSchema();
		
		System.out.println("the total number or defaults in the training dataset is:  " +trainingData.filter("label = 1.0").count());
		
		double numPositives = trainingData.filter("label = 1.0").count();
		double datasetSize = trainingData.count();
		double balancingRatio = (datasetSize - numPositives)/ datasetSize;
		
		System.out.println("the balancing ratio for the training dataset is:  " +balancingRatio);
		
		//create a weighted data set based on the training data. Logistic regression is the only current model that supports weighting
		Dataset<Row> weightedDataset = trainingData.withColumn("classWeightCol", when(col("label").equalTo(1),(1 * balancingRatio)).otherwise(1.0 - balancingRatio));
	
		weightedDataset.show();
		
		LogisticRegression lr = new LogisticRegression().setWeightCol("classWeightCol").setLabelCol("label").setFeaturesCol("features");
		
		org.apache.spark.ml.classification.LogisticRegressionModel lrModel = lr.fit(weightedDataset);

		Dataset<Row> predictions2 = lrModel.transform(testData);
		
		predictions2.show();
		
		System.out.println("The class count after the weighted logistic regression is: ");
		predictions2.groupBy("class").count().show();
	
		BinaryClassificationEvaluator evaluator3 = new BinaryClassificationEvaluator().setLabelCol("label")
				  .setMetricName("areaUnderPR");
		double precision3 = evaluator3.evaluate(predictions2);
		System.out.println("the precision of the weighted logistic regression model is: " + precision3);
		
		BinaryClassificationEvaluator evaluator4 = new BinaryClassificationEvaluator().setLabelCol("label");
		double accuracy4 = evaluator4.evaluate(predictions2);
		System.out.println("the accuracy of the weighted logistic regression model is: " + accuracy4);
		
		session.stop();

		System.out.println("end");
		
	}
}
