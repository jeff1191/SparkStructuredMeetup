package com.github.jeff1191.datasource

import java.util.logging.Logger

import com.github.jeff1191.datatype.DataModel.{Group, MeetupRSVGevent}
import net.liftweb.json.{JsonParser, MappingException, Serialization, ShortTypeHints}
import org.apache.spark.internal.Logging
import org.apache.spark.storage.StorageLevel
import org.apache.spark.streaming.receiver.Receiver
import scalawebsocket.WebSocket

class WebSocketStreamReceiver (webSocketURL:String) extends Receiver[MeetupRSVGevent](StorageLevel.MEMORY_AND_DISK_2) with Logging{
  var webSocket : WebSocket = _

  override def onStart(): Unit = {
  logInfo(s"Starting ${getClass.getCanonicalName}")
    ///Start the thread that receives data over a connection
    new Thread("Socket Receiver") {
      override def run() {
        receive()
      }
    }.start()
  }

  override def onStop(): Unit = {
  }

  private def receive(): Unit = {
    webSocket = WebSocket().open(webSocketURL)
    webSocket.onTextMessage(msg => {
      store(toMeetupRSVGevent(msg))
    })
  }

    //TODO it should be in the DataModel Object
  private def toMeetupRSVGevent(msg: String): MeetupRSVGevent ={
    implicit val formats = net.liftweb.json.DefaultFormats
    implicit val formats2 = Serialization.formats(ShortTypeHints(List(classOf[Group])))

    JsonParser.parse(msg).extract[MeetupRSVGevent]
  }
}
