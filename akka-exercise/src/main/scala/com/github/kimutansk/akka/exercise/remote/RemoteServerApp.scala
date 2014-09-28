package com.github.kimutansk.akka.exercise.remote

import akka.actor.{Props, ActorSystem}
import com.github.kimutansk.akka.exercise.routing.MessagePrintActor
import com.typesafe.config.ConfigFactory
import akka.routing.FromConfig

/**
 * Akka-Remoteを用いて接続を受け付けるクラス
 */
object RemoteServerApp extends App {
  override def main(args: Array[String]): Unit = {
    val config = ConfigFactory.load("conf/remote-server-app.conf")
    implicit val system = ActorSystem.apply("RemoteServerApp", config)
    val actor1 = system.actorOf(Props[MessagePrintActor], "Receive")

    actor1 ! "Local"

    // ローカスプロセス上にRoutingされたActorを生成
    system.actorOf(FromConfig.props(Props[MessagePrintActor]),"remotePool")

    Thread.sleep(60000)
    system.shutdown()
  }
}