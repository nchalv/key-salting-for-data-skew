//import org.apache.spark.{SparkConf, SparkContext}
//
//object StreamingWordcount {
//  def main(args: Array[String]): Unit ={
//    //Create a Spark Context to initialize Spark
//    val conf = new SparkConf()
//    conf.setMaster("yarn")
//    conf.setAppName("Word Count Static")
//    val sc = new SparkContext(conf)
//
//    //Load the text into a Spark RDD, which is a distributed representation of each line of text
//    val textFile = sc.textFile("hdfs:///user/ubuntu/books/frankenstein.txt")
//    //val textFile = sc.textFile("src/main/resources/pg7178.txt")
//
//    //word count
//    val counts = textFile.flatMap(line => line.split(" "))
//      .map(word => (word,1))
//      .reduceByKey(_ + _)
//
//    counts.foreach(println)
//    System.out.println("Total words: " + counts.count());
//    counts.saveAsTextFile("hdfs:///tmp/wordcount");
//  }
//}



import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.streaming.Trigger

object StreamingWordcount {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession
      .builder
      .appName("StructuredKafkaWordCount")
      .getOrCreate()


    import spark.implicits._
    val lines = spark.readStream.format("kafka").option("kafka.bootstrap.servers","10.0.1.88:9092,10.0.1.88:9093").option("subscribe", "test-final-aug").option("startingOffsets", "latest").load().selectExpr("CAST(value AS STRING)").as[String]
    val wordCounts = lines.flatMap(_.split(" ")).groupBy("value").count()

    //val query = wordCounts.writeStream.outputMode("complete").option("checkpointLocation", "/user/ubuntu/checkpoint").format("console").start()
    val query = wordCounts.writeStream.trigger(Trigger.ProcessingTime("2 seconds")).outputMode("complete").format("console").start()
    query.awaitTermination()
    //spark.sql("select * from counts").show()   // interactively query in-memory table
  }
}

//
//import org.apache.spark.sql.SparkSession
//
//object StreamingWordcount {
//
//  def main(args: Array[String]): Unit = {
//
//
//    val host = "10.0.1.125"
//    val port = 9999
//
//    val spark = SparkSession
//      .builder
//      .appName("StructuredNetworkWordCount")
//      .getOrCreate()
//
//    import spark.implicits._
//
//    // Create DataFrame representing the stream of input lines from connection to host:port
//    val lines = spark.readStream
//      .format("socket")
//      .option("host", host)
//      .option("port", port)
//      .load()
//
//    // Split the lines into words
//    val words = lines.as[String].flatMap(_.split(" "))
//
//    // Generate running word count
//    val wordCounts = words.groupBy("value").count()
//
//    // Start running the query that prints the running counts to the console
//    val query = wordCounts.writeStream
//      .outputMode("complete")
//      .format("console")
//      .start()
//
//    query.awaitTermination()
//  }
//}