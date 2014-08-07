package com.github.kimutansk.akka.exercise.inbox

import akka.actor.{Inbox, Props, ActorSystem}
import akka.routing.FromConfig
import com.github.kimutansk.akka.exercise.routing.MessagePrintActor
import scala.concurrent.duration._
import java.util.concurrent.TimeUnit

/**
 * インボックスの確認を行うサンプル
 */
object InboxApp extends App {
  override def main(args: Array[String]): Unit = {
    val system = ActorSystem.apply("ConfiguredRoutingApp")
    val router1 = system.actorOf(FromConfig.getInstance().props(Props[MessagePrintActor]),
      "router1")
    val rootInbox = Inbox.create(system)
    rootInbox.watch(router1)

    router1 ! "Test1"
    router1 ! "Test2"
    router1 ! "Test3"
    router1 ! "Test4"

    println(rootInbox.getRef())

    Thread.sleep(5000)
    system.shutdown()
  }
}
