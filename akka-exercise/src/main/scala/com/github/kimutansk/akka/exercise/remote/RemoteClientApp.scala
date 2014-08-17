package com.github.kimutansk.akka.exercise.remote

import akka.actor.{ActorDSL, Props, ActorSystem}
import com.github.kimutansk.akka.exercise.routing.MessagePrintActor
import com.typesafe.config.ConfigFactory

/**
 * Akka-Remoteを用いて接続を受け付けるクラス
 */
object RemoteClientApp extends App {
  override def main(args: Array[String]): Unit = {
    val config = ConfigFactory.load("conf/remote-client-app.conf")
    val system = ActorSystem.apply("RemoteClientApp", config)
    // val remoteActorRef = system.actorSelection("akka.tcp://RemoteServerApp@127.0.0.1:2552/user/Receive")
    val remoteActorRef = system.actorSelection("akka.tcp://RemoteServerApp@127.0.0.1:2552/user/Receive")
    remoteActorRef ! "Remote"

    Thread.sleep(10000)
    system.shutdown()
  }
}
