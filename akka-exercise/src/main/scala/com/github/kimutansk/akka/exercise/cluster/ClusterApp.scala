package com.github.kimutansk.akka.exercise.cluster

import akka.actor.{ActorDSL, Props, ActorSystem}
import com.github.kimutansk.akka.exercise.routing.MessagePrintActor
import scala.concurrent.duration._
import java.util.concurrent.TimeUnit
import com.typesafe.config.ConfigFactory

/**
 * Akka-Clusterを用いてクラスタリングの確認を行うクラス
 */
object ClusterApp extends App {
  override def main(args: Array[String]): Unit = {
    val config = ConfigFactory.load("conf/cluster-app.conf")
    val system = ActorSystem.apply("ClusterApp", config)

    Thread.sleep(5000)
    system.shutdown()
  }
}
