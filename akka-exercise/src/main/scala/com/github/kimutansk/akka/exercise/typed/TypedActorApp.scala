package com.github.kimutansk.akka.exercise.typed

import akka.actor._
import com.github.kimutansk.akka.exercise.routing.MessagePrintActor
import akka.routing.{RoundRobinGroup, FromConfig}
import scala.concurrent.Await
import scala.concurrent.duration.FiniteDuration
import java.util.concurrent.TimeUnit
import akka.remote.RemoteScope
import akka.actor.TypedActor.MethodCall

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

    // TypedActorが例外を投げるとそのActorは終了した扱いとなる。
    // そのため、再度初期化(preStart)が呼ばれる。
    try {
      val tryResult = calculator.squareTry(103)
      println("tryResult:" + tryResult)
    }
    catch {
      case ex:Exception => { println("Exception Occured." + ex.getMessage)}
    }

    val path = calculator.pathNow("STR")
    println("pathResult:" + path)


    val routees: List[Calculator] = List.fill(5)(createCalc(system))
    routees.foreach{_.pathNow("Test")}
    val routeePaths = routees map {
      r =>
        TypedActor(system).getActorRefFor(r).path.address.toString
    }
    val router: ActorRef = system.actorOf(RoundRobinGroup(routeePaths).props())

    // prepare typed proxy, forwarding MethodCall messages to `router`
    // TODO TimeoutException Occur. why?
    val typedRouter: Calculator =
      TypedActor(system).typedActorOf(TypedProps[CalculatorImpl](), actorRef = router)
    val pathResult1 = typedRouter.pathNow("STR1")
    println("pathResult1:" + pathResult1)
    val pathResult2 = typedRouter.pathNow("STR2")
    println("pathResult2:" + pathResult2)
    val pathResult3 = typedRouter.pathNow("STR3")
    println("pathResult3:" + pathResult3)
    val pathResult4 = typedRouter.pathNow("STR4")
    println("pathResult4:" + pathResult4)
    val pathResult5 = typedRouter.pathNow("STR5")
    println("pathResult5:" + pathResult5)
    val pathResult6 = typedRouter.pathNow("STR6")
    println("pathResult6:" + pathResult6)

    TypedActor(system).poisonPill(calculator)

    system.shutdown()
  }

  def createCalc(system: ActorSystem): Calculator = {
    val result: Calculator = TypedActor(system).typedActorOf(TypedProps[CalculatorImpl]())
    return result
  }
}
