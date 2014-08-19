package com.github.kimutansk.akka.exercise.remote

import akka.actor.{ActorDSL, Props, ActorSystem}
import com.github.kimutansk.akka.exercise.routing.MessagePrintActor
import com.typesafe.config.ConfigFactory

/**
 * Akka-Remoteを用いて接続を受け付けるクラス
 */
object RemoteServerApp extends App {
  override def main(args: Array[String]): Unit = {
    val config = ConfigFactory.load("conf/remote-server-app.conf")
    implicit val system = ActorSystem.apply("RemoteServerApp", config)
    val actor1 = system.actorOf(Props[MessagePrintActor], "Receive")

    actor1 ! "Local"

    Thread.sleep(30000)

    val actors = system.actorSelection("/remote/akka.tcp/RemoteClientApp@127.0.0.1:2553/user/router1")
    val rootInbox = ActorDSL.inbox()
    actors.tell("Created1", rootInbox.getRef())
    actors.tell("Created2", rootInbox.getRef())

    Thread.sleep(1000)

    val received1 = rootInbox.receive()
    println("received1:" + received1)
    val received2 = rootInbox.receive()
    println("received2:" + received2)

    Thread.sleep(10000)
    system.shutdown()
  }
}
