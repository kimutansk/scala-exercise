package com.github.kimutansk.akka.exercise.cluster

import akka.actor.{Address, ActorDSL, Props, ActorSystem}
import com.github.kimutansk.akka.exercise.routing.MessagePrintActor
import scala.concurrent.duration._
import java.util.concurrent.TimeUnit
import com.typesafe.config.ConfigFactory
import akka.cluster.Cluster
import scala.collection.immutable

/**
 * Akka-Clusterを用いてクラスタリングの確認を行うクラス(Server)
 */
object ClusterClientApp extends App {
  override def main(args: Array[String]): Unit = {
    val config = ConfigFactory.load("conf/cluster-client-app.conf")
    val system = ActorSystem.apply("ClusterApp", config)

    Cluster(system).joinSeedNodes(immutable.Seq(Address("akka.tcp", "ClusterApp", "localhost", 2552)))

    val actor = system.actorOf(Props[ClusterEventListener], "ClusterEventListener")


    Thread.sleep(30000)
    system.shutdown()
  }
}
