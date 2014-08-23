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
    val config = ConfigFactory.load("conf/remote-client-app.conf")
    implicit val system = ActorSystem.apply("RemoteClientApp", config)
    // Remote Lookup
    val remoteActorRef = system.actorSelection("akka.tcp://RemoteServerApp@127.0.0.1:2552/user/Receive")

    val inbox = ActorDSL.inbox()
    remoteActorRef.tell("Remote", inbox.getRef())

    val received1 = inbox.receive()
    println("received1:" + received1)

    // RemoteCreate
    val remoteRouterRef = system.actorOf(Props[MessagePrintActor],
      "router1")
    remoteRouterRef.tell("RemoteRouter", inbox.getRef())
    val received2 = inbox.receive()
    println("received2:" + received2)

    val remoteRouterSelectRef = system.actorSelection("akka.tcp://RemoteServerApp@127.0.0.1:2552/remote/akka.tcp/RemoteClientApp@127.0.0.1:2553/user/router1");
    remoteRouterSelectRef.tell("RemoteSelection", inbox.getRef())
    val received3 = inbox.receive()
    println("received3:" + received3)

    val remoteAddress = AddressFromURIString("akka.tcp://RemoteServerApp@127.0.0.1:2552")
    val programRemoteRef = system.actorOf(Props[MessagePrintActor].
      withDeploy(Deploy(scope = RemoteScope(remoteAddress))))
    programRemoteRef.tell("RemoteRouter", inbox.getRef())
    val received4 = inbox.receive()
    println("received4:" + received4)

    Thread.sleep(30000)
    system.shutdown()
  }
}
