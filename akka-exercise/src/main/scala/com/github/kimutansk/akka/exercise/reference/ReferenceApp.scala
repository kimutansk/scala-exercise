package com.github.kimutansk.akka.exercise.reference

import akka.actor.{ActorDSL, Props, ActorSystem}
import com.github.kimutansk.akka.exercise.routing.MessagePrintActor
import scala.concurrent.duration._
import java.util.concurrent.TimeUnit
import akka.routing.FromConfig

/**
 * Akkaのパス確認を行うサンプル
 */
object ReferenceApp extends App {
  override def main(args: Array[String]): Unit = {
    implicit val system = ActorSystem.apply("ConfiguredRoutingApp")
    val router1 = system.actorOf(FromConfig.props(Props[MessagePrintActor]),
      "router1")

    router1 ! "Test1"
    router1 ! "Test2"
    router1 ! "Test3"
    router1 ! "Test4"

    Thread.sleep(5000)
    system.shutdown()
  }
}
