package com.github.kimutansk.akka.exercise.remote

import akka.actor.{ActorDSL, ActorSystem}
import com.typesafe.config.ConfigFactory

/**
 * Akka-Remoteを用いて接続を受け付けるクラス
 */
object RemoteClientApp extends App {
  override def main(args: Array[String]): Unit = {
    val config = ConfigFactory.load("conf/remote-client-app.conf")
    implicit val system = ActorSystem.apply("RemoteClientApp", config)
    // val remoteActorRef = system.actorSelection("akka.tcp://RemoteServerApp@127.0.0.1:2552/user/Receive")
    val remoteActorRef = system.actorSelection("akka.tcp://RemoteServerApp@127.0.0.1:2552/user/Receive")

    val inbox = ActorDSL.inbox()
    remoteActorRef.tell("Remote", inbox.getRef())

    val received1 = inbox.receive()
    println("received1:" + received1)

    Thread.sleep(10000)
    system.shutdown()
  }
}
