package com.github.kimutansk.akka.exercise.remote

import akka.actor.{ActorDSL, Props, ActorSystem}
import com.github.kimutansk.akka.exercise.routing.MessagePrintActor
import com.typesafe.config.ConfigFactory

/**
 * Akka-Remoteを用いて接続を受け付けるクラス
 */
object RemoteServerApp extends App {
  override def main(args: Array[String]): Unit = {
    val config = ConfigFactory.load("conf/remote-server-app.conf")
    val system = ActorSystem.apply("RemoteServerApp", config)
    val actor1 = system.actorOf(Props[MessagePrintActor], "Receive")

    actor1 ! "Local"

    Thread.sleep(60000)
    system.shutdown()
  }
}
