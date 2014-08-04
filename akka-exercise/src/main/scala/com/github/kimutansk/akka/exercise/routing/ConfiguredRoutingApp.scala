package com.github.kimutansk.akka.exercise.routing

import akka.actor.ActorSystem
import com.github.kimutansk.akka.exercise.message.ChildActor
import com.typesafe.config.ConfigFactory


/**
 * 設定ファイルからルーティング定義を読み込むサンプル
 */
object ConfiguredRoutingApp extends App {
  override def main(args: Array[String]): Unit = {
    val config = ConfigFactory.load()
    val system = ActorSystem.apply("ConfiguredRoutingApp", config.getConfig("ConfiguredRoutingApp"))
    println(system.settings);
  }
}
