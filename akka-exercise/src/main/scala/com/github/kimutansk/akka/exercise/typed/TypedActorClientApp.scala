package com.github.kimutansk.akka.exercise.typed

import akka.actor._
import akka.remote.RemoteScope
import com.github.kimutansk.akka.exercise.routing.MessagePrintActor
import akka.routing.FromConfig
import scala.concurrent.Await
import scala.concurrent.duration.FiniteDuration
import java.util.concurrent.TimeUnit
import com.typesafe.config.ConfigFactory

/**
 * TypedActorの確認を行うサンプル
 */
object TypedActorClientApp extends App {
  override def main(args: Array[String]): Unit = {
    val config = ConfigFactory.load("conf/remote-client-app.conf")
    implicit val system = ActorSystem.apply("TypedActorClientApp", config)

    val remoteAddress = AddressFromURIString("akka.tcp://TypedActorServerApp@127.0.0.1:2552")
    val calculator:Calculator = TypedActor(system).typedActorOf(TypedProps[CalculatorImpl].
      withDeploy(Deploy(scope = RemoteScope(remoteAddress))))

    val dontCareResult = calculator.squareDontCare(99)
    println("dontCareResult:" + dontCareResult)
    val nowResult = calculator.squareNow(100)
    println("nowResult:" + nowResult)
    val futureResult = calculator.square(101)
    println("futureResult:" + Await.result(futureResult, FiniteDuration(10, TimeUnit.SECONDS)))
    val pleaseResult = calculator.squareNowPlease(102)
    println("pleaseResult:" + pleaseResult.get)

    val path = calculator.pathNow("STR")
    println("pathResult:" + path)

    val remoteAddress2 = AddressFromURIString("akka.tcp://TypedActorServerApp@127.0.0.1:2552")
    val calculator2:Calculator = TypedActor(system).typedActorOf(TypedProps[CalculatorImpl].
      withDeploy(Deploy(scope = RemoteScope(remoteAddress))))
    val path2 = calculator2.pathNow("STR")
    println("pathResult:" + path2)

    TypedActor(system).poisonPill(calculator)

    system.shutdown()
  }
}
