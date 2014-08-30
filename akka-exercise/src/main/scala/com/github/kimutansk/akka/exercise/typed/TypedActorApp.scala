package com.github.kimutansk.akka.exercise.typed

import akka.actor._
import com.github.kimutansk.akka.exercise.routing.MessagePrintActor
import akka.routing.FromConfig

/**
 * TypedActorの確認を行うサンプル
 */
object TypedActorApp extends App {
  override def main(args: Array[String]): Unit = {
    implicit val system = ActorSystem.apply("TypedActorApp")
    val calculator:Calculator =
      TypedActor(system).typedActorOf(TypedProps[CalculatorImpl]())

    val result = calculator.squareNow(100);

    println("result:" + result)

    Thread.sleep(1000)
    system.shutdown()
  }
}
