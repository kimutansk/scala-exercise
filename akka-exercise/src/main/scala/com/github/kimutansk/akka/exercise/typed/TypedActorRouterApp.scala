package com.github.kimutansk.akka.exercise.typed

import akka.actor._
import akka.remote.RemoteScope
import akka.routing.RoundRobinGroup
import scala.concurrent.Await
import scala.concurrent.duration.FiniteDuration
import java.util.concurrent.TimeUnit
import com.typesafe.config.ConfigFactory

/**
 * TypedActorRouterの確認を行うサンプル
 */
object TypedActorRouterApp extends App {
  override def main(args: Array[String]): Unit = {
    val config = ConfigFactory.load("conf/remote-client-app.conf")
    implicit val system = ActorSystem.apply("TypedActorClientApp", config)

    val remoteAddress = AddressFromURIString("akka.tcp://TypedActorServerApp@127.0.0.1:2552")

    val calculator: Calculator = createCalc(system, remoteAddress)
    val pathResult = calculator.pathNow("STR")
    println("pathResult:" + pathResult)

    val routees: List[Calculator] = List.fill(5)(createCalc(system, remoteAddress))

    routees.foreach{_.pathNow("Test")}

    val routeePaths = routees map {
      r =>
        TypedActor(system).getActorRefFor(r).path.address.toString
    }

    val router: ActorRef = system.actorOf(RoundRobinGroup(routeePaths).props())

    val typedRouter: Calculator =
      TypedActor(system).typedActorOf(TypedProps[CalculatorImpl](), actorRef = router)

    // TODO TimeoutException Occur. why?
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

    system.shutdown()
  }

  def createCalc(system: ActorSystem, address: Address): Calculator = {
    val result: Calculator = TypedActor(system).typedActorOf(TypedProps[CalculatorImpl].withDeploy(Deploy(scope = RemoteScope(address))))
    return result
  }
}
