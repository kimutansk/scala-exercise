package com.github.kimutansk.akka.exercise.remote

import akka.actor._
import com.typesafe.config.ConfigFactory
import com.github.kimutansk.akka.exercise.routing.MessagePrintActor
import akka.remote.RemoteScope

/**
 * Akka-Remoteを用いて接続し、メッセージを送信するサンプル
 */
object RemoteClientApp extends App {
  override def main(args: Array[String]): Unit = {
    val config = ConfigFactory.load("conf/remote-client-app.conf")
    implicit val system = ActorSystem.apply("RemoteClientApp", config)

    // リモートのActorに対してメッセージ送信
    val remoteActorRef = system.actorSelection("akka.tcp://RemoteServerApp@127.0.0.1:2552/user/Receive")
    val inbox = ActorDSL.inbox()
    remoteActorRef.tell("Remote", inbox.getRef())
    val received1 = inbox.receive()
    println("received1:" + received1)

    // リモートでActorをデプロイ
    val remoteAddress = AddressFromURIString("akka.tcp://RemoteServerApp@127.0.0.1:2552")
    val programRemoteRef = system.actorOf(Props[MessagePrintActor].
      withDeploy(Deploy(scope = RemoteScope(remoteAddress))))
    programRemoteRef.tell("RemoteDeploy", inbox.getRef())
    val received2 = inbox.receive()
    println("received2:" + received2)

    Thread.sleep(30000)
    system.shutdown()
  }
}