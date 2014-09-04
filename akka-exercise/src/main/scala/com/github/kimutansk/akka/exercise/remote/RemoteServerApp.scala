package com.github.kimutansk.akka.exercise.remote

import akka.actor.{ActorDSL, Props, ActorSystem}
import com.github.kimutansk.akka.exercise.routing.MessagePrintActor
import com.typesafe.config.ConfigFactory
import akka.routing.FromConfig

/**
 * Akka-Remoteを用いて接続を受け付けるクラス
 */
object RemoteServerApp extends App {
  override def main(args: Array[String]): Unit = {
    val config = ConfigFactory.load("conf/remote-server-app.conf")
    implicit val system = ActorSystem.apply("RemoteServerApp", config)
    Thread.sleep(10000)

    Thread.sleep(30000)

    system.shutdown()
  }
}
