package com.github.kimutansk.akka.exercise.persistence

import akka.actor.{ActorDSL, Props, ActorSystem}
import com.typesafe.config.ConfigFactory
import akka.persistence.Recover

/**
 * Akka-Persistenceの確認を行うサンプルアプリケーション
 */
object PersistenceApp extends App {
  override def main(args: Array[String]): Unit = {
    val config = ConfigFactory.load("conf/persistence-app.conf")
    implicit val system = ActorSystem.apply("PersistentApp", config)

    val actor = system.actorOf(Props.apply(new SamplePersistentActor("actor")))
    val rootInbox = ActorDSL.inbox()

    rootInbox.send(actor, "view")
    val msg1 = rootInbox.receive()
    println("Receive1:" + msg1)

    actor ! "Test1"
    // メッセージ送信後、snap無しに再読み込みを実施
    actor ! Recover()

    rootInbox.send(actor, "view")
    val msg2 = rootInbox.receive()
    // snap無しに再読み込みを実施した場合の状態を表示
    println("Receive2:" + msg2)

    actor ! "Test2"

    Thread.sleep(5000)
    system.shutdown()
  }
}
