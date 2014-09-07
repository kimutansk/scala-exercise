package com.github.kimutansk.akka.exercise.typed

import akka.actor._
import com.github.kimutansk.akka.exercise.routing.MessagePrintActor
import akka.routing.FromConfig
import scala.concurrent.Await
import scala.concurrent.duration.FiniteDuration
import java.util.concurrent.TimeUnit
import com.typesafe.config.ConfigFactory

/**
 * TypedActorの確認を行うサンプル
 */
object TypedActorServerApp extends App {
  override def main(args: Array[String]): Unit = {
    val config = ConfigFactory.load("conf/remote-server-app.conf")
    implicit val system = ActorSystem.apply("TypedActorServerApp", config)

    val calculator:Calculator =
      TypedActor(system).typedActorOf(TypedProps[CalculatorImpl]())

    Thread.sleep(60000)

    TypedActor(system).poisonPill(calculator)

    system.shutdown()
  }
}
