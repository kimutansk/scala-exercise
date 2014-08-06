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
    val router1 = system.actorOf(FromConfig.getInstance().props(Props.apply(new MessagePrintActor) ),
    "router1")

    router1 ! "Test1"
    router1 ! "Test2"
    router1 ! "Test3"
    router1 ! "Test4"

    Thread.sleep(5000)
    system.shutdown()
  }
}
