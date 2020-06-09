name := "StreamingWordcount"

version := "0.1"

scalaVersion := "2.12.8"

//libraryDependencies += "org.apache.spark" %% "spark-sql" % "2.4.4"


//libraryDependencies ++= Seq("org.apache.spark" %% "spark-sql" % "2.4.4",
//                            "org.apache.spark" %% "spark-sql-kafka" % "2.3.1",
//                            "org.apache.kafka" % "kafka-clients" % "0.11.0.1")

libraryDependencies ++= Seq("org.apache.spark" %% "spark-core" % "2.4.4",
                            "org.apache.spark" %% "spark-sql" % "2.4.4",
                            "org.apache.spark" %% "spark-sql-kafka-0-10" % "2.4.4")

