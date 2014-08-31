package com.github.kimutansk.akka.exercise.typed

import akka.actor._
import com.github.kimutansk.akka.exercise.routing.MessagePrintActor
import akka.routing.FromConfig
import scala.concurrent.Await
import scala.concurrent.duration.FiniteDuration
import java.util.concurrent.TimeUnit

/**
 * TypedActorの確認を行うサンプル
 */
object TypedActorApp extends App {
  override def main(args: Array[String]): Unit = {
    implicit val system = ActorSystem.apply("TypedActorApp")
    val calculator:Calculator =
      TypedActor(system).typedActorOf(TypedProps[CalculatorImpl]())

    val dontCareResult = calculator.squareDontCare(99)
    println("dontCareResult:" + dontCareResult)
    val nowResult = calculator.squareNow(100)
    println("nowResult:" + nowResult)
    val futureResult = calculator.square(101)
    println("futureResult:" + Await.result(futureResult, FiniteDuration(10, TimeUnit.SECONDS)))
    val pleaseResult = calculator.squareNowPlease(102)
    println("pleaseResult:" + pleaseResult.get)

    try {
      val tryResult = calculator.squareTry(103)
      println("tryResult:" + tryResult)
    }
    catch {
      case ex:Exception => { println("Exception Occured." + ex.getMessage)}
    }

    Thread.sleep(1000)
    system.shutdown()
  }
}
