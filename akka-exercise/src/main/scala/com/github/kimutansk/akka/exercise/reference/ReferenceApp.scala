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

    // val actor1 = system.actorSelection("akka://ConfiguredRoutingApp/user/router1/$b")
    // val actor1 = system.actorSelection("/user/router1/$b")
    val actor1 = system.actorSelection("/user/router1/*")
    val rootInbox = ActorDSL.inbox()
    actor1.tell("Path1", rootInbox.getRef())
    actor1.tell("Path2", rootInbox.getRef())

    val received = rootInbox.receive()
    println(received)

    router1 ! "Test1"
    router1 ! "Test2"
    router1 ! "Test3"
    router1 ! "Test4"

    Thread.sleep(5000)
    system.shutdown()
  }
}
