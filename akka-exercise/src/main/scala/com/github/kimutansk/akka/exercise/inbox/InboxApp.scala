package com.github.kimutansk.akka.exercise.inbox

import akka.actor.{ActorDSL, Props, ActorSystem}
import com.github.kimutansk.akka.exercise.routing.MessagePrintActor
import scala.concurrent.duration._
import java.util.concurrent.TimeUnit

/**
 * インボックスの確認を行うサンプル
 */
object InboxApp extends App {
  override def main(args: Array[String]): Unit = {
    implicit val system = ActorSystem.apply("ConfiguredRoutingApp")

    val actor1 = system.actorOf(Props[MessagePrintActor])

    val rootInbox = ActorDSL.inbox()
    rootInbox.send(actor1, "Test1")
    rootInbox.send(actor1, "Test2")
    rootInbox.send(actor1, "Test3")
    rootInbox.send(actor1, "Test4")

    Thread.sleep(1000)

    val msg1 = rootInbox.receive()
    println(msg1)
    val msg2 = rootInbox.receive()
    println(msg2)
    val msg3 = rootInbox.receive()
    println(msg3)


    Thread.sleep(5000)
    system.shutdown()
  }
}
