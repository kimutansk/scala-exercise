package com.github.kimutansk.akka.exercise.reference

import akka.actor.{ActorDSL, Props, ActorSystem}
import com.github.kimutansk.akka.exercise.routing.MessagePrintActor
import scala.concurrent.duration._
import java.util.concurrent.{TimeoutException, TimeUnit}
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

    // 非同期処理のため、以後のreceiveがPath2の到着前に実行されるのを防止するために待ちを入れる
    Thread.sleep(5000)

    val received1 = rootInbox.receive()
    println("received1:" + received1)
    val received2 = rootInbox.receive()
    println("received2:" + received2)
    val received3 = rootInbox.receive()
    println("received3:" + received3)
    try {
      val received4 = rootInbox.receive()
      println("received4:" + received4)
    }
    catch {
      case ex:TimeoutException => { println("Exception Occured." + ex.getMessage)}
    }

    actor1.tell("Path3", rootInbox.getRef())
    val received5 = rootInbox.receive()
    println("received5:" + received5)

    router1 ! "Test1"

    Thread.sleep(5000)
    system.shutdown()
  }
}
