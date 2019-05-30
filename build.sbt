name := "SparkStructuredMeetup"

version := "0.1"

scalaVersion := "2.12.2"

val sparkVersion = "2.4.3"
val scalaWebSocketVersion = "0.2.1"
val scalaTestVersion = "3.0.7"
val liftJsonVersion = "3.3.0"

libraryDependencies += "org.apache.spark" %% "spark-core" % sparkVersion
libraryDependencies += "org.apache.spark" %% "spark-streaming" % sparkVersion
libraryDependencies += "org.apache.spark" %% "spark-sql-kafka-0-10" % sparkVersion
libraryDependencies += "org.apache.spark" %% "spark-sql" % sparkVersion
libraryDependencies += "org.saegesser" %% "scalawebsocket" % scalaWebSocketVersion
libraryDependencies += "org.scalatest" %% "scalatest" % scalaTestVersion % Test
libraryDependencies += "net.liftweb" %% "lift-json" % liftJsonVersion

