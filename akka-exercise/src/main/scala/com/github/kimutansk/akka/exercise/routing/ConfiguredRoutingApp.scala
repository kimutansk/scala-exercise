package com.github.kimutansk.akka.exercise.routing

import akka.actor.{Props, ActorRef, ActorSystem}
import com.typesafe.config.ConfigFactory
import akka.routing.FromConfig


/**
 * 設定ファイルからルーティング定義を読み込むサンプル
 */
object ConfiguredRoutingApp extends App {
  override def main(args: Array[String]): Unit = {
    val system = ActorSystem.apply("ConfiguredRoutingApp")
    val router = system.actorOf(FromConfig.getInstance().props(Props.apply(new MessagePrintActor) ),
    "router")

    router ! "Test1"
    router ! "Test2"
    router ! "Test3"
    router ! "Test4"

    Thread.sleep(5000)
    system.shutdown()
  }
}
