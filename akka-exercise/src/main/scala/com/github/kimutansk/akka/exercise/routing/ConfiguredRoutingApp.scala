package com.github.kimutansk.akka.exercise.routing

import akka.actor.{Props, ActorSystem}
import com.github.kimutansk.akka.exercise.message.{ParentActor, ChildActor}
import scala.collection.immutable
import akka.routing.RoundRobinRoutingLogic


/**
 * 設定ファイルからルーティング定義を読み込むサンプル
 */
object ConfiguredRoutingApp extends App {
  override def main(args: Array[String]): Unit = {
    val system = ActorSystem.apply("MessageSendApp")
    println(system.settings);
  }
}
