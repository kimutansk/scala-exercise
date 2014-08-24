package com.github.kimutansk.akka.exercise.remote

import akka.actor._
import com.typesafe.config.ConfigFactory
import com.github.kimutansk.akka.exercise.routing.MessagePrintActor
import akka.remote.RemoteScope

/**
 * Akka-Remoteを用いて接続を受け付けるクラス
 */
object RemoteRouterApp extends App {
  override def main(args: Array[String]): Unit = {
    val config = ConfigFactory.load("conf/remote-router-app.conf")
    implicit val system = ActorSystem.apply("RemoteRouterApp", config)

    val inbox = ActorDSL.inbox()
    // RemoteRouterCreate
    val remoteRouterRef = system.actorOf(Props[MessagePrintActor],
      "remotePool")
    remoteRouterRef.tell("RemoteRouter1", inbox.getRef())
    remoteRouterRef.tell("RemoteRouter2", inbox.getRef())
    remoteRouterRef.tell("RemoteRouter3", inbox.getRef())
    remoteRouterRef.tell("RemoteRouter4", inbox.getRef())
    val received1 = inbox.receive()
    println("received1:" + received1)


    Thread.sleep(30000)
    system.shutdown()
  }
}
