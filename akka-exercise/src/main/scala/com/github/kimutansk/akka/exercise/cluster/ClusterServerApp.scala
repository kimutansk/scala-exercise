package com.github.kimutansk.akka.exercise.cluster

import akka.actor.{ActorDSL, Props, ActorSystem}
import com.github.kimutansk.akka.exercise.routing.MessagePrintActor
import scala.concurrent.duration._
import java.util.concurrent.TimeUnit
import com.typesafe.config.ConfigFactory

/**
 * Akka-Clusterを用いてクラスタリングの確認を行うクラス(Server)
 */
object ClusterServerApp extends App {
  override def main(args: Array[String]): Unit = {
    val config = ConfigFactory.load("conf/cluster-server-app.conf")
    val system = ActorSystem.apply("ClusterApp", config)

    val actor = system.actorOf(Props[ClusterEventListener], "ClusterEventListener")


    Thread.sleep(60000)
    system.shutdown()
  }
}
