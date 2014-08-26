package com.github.kimutansk.akka.exercise.remote

import akka.actor._
import com.typesafe.config.ConfigFactory
import com.github.kimutansk.akka.exercise.routing.MessagePrintActor
import akka.remote.RemoteScope
import akka.routing.FromConfig

/**
 * Akka-Remoteを用いて接続を受け付けるクラス
 */
object RemoteRouterApp extends App {
  override def main(args: Array[String]): Unit = {
    val config = ConfigFactory.load("conf/remote-router-app.conf")
    implicit val system = ActorSystem.apply("RemoteRouterApp", config)

    val inbox = ActorDSL.inbox()
    // RemoteRouterCreate
    val remoteRouterRef = system.actorOf(FromConfig.props(Props[MessagePrintActor]),
      "remotePool")
    remoteRouterRef.tell("RemoteRouter1", inbox.getRef())
    remoteRouterRef.tell("RemoteRouter2", inbox.getRef())
    remoteRouterRef.tell("RemoteRouter3", inbox.getRef())
    remoteRouterRef.tell("RemoteRouter4", inbox.getRef())
    val received1 = inbox.receive()
    println("received1:" + received1)
    val received2 = inbox.receive()
    println("received2:" + received2)
    val received3 = inbox.receive()
    println("received3:" + received3)
    val received4 = inbox.receive()
    println("received4:" + received4)

    val remoteGroupRef = system.actorOf(FromConfig.props(Props[MessagePrintActor]),
      "remoteGroup")
    remoteGroupRef.tell("RemoteGroup1", inbox.getRef())
    remoteGroupRef.tell("RemoteGroup2", inbox.getRef())
    remoteGroupRef.tell("RemoteGroup3", inbox.getRef())
    remoteGroupRef.tell("RemoteGroup4", inbox.getRef())
    val received5 = inbox.receive()
    println("received5:" + received5)
    val received6 = inbox.receive()
    println("received6:" + received6)
    val received7 = inbox.receive()
    println("received7:" + received7)
    val received8 = inbox.receive()
    println("received8:" + received8)


    Thread.sleep(30000)
    system.shutdown()
  }
}
