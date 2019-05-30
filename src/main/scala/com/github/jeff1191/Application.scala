package com.github.jeff1191

import java.io.Serializable

import com.github.jeff1191.datasource.WebSocketStreamReceiver
import org.apache.log4j.{Level, LogManager, Logger}
import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import org.apache.spark.streaming.dstream.DStream
import org.apache.spark.streaming.{Seconds, StreamingContext}

object Application extends App{

  val url = "wss://stream.meetup.com/2/rsvps"

  Logger.getLogger(this.getClass.getName).setLevel(Level.WARN)

  val spark = SparkSession.builder.appName("Meetup app")
    .config("spark.master", "local[2]")
    .getOrCreate()

  val ssc = new StreamingContext(spark.sparkContext, Seconds(1))


  val lines = ssc.receiverStream(new WebSocketStreamReceiver(url))

  lines.map(x => {
    x.toString
  }).print()

  ssc.start()
  ssc.awaitTermination()

}
