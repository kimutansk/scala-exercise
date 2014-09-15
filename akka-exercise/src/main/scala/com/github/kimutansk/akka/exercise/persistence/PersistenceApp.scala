package com.github.kimutansk.akka.exercise.persistence

import akka.actor.{ActorDSL, Props, ActorSystem}
import com.github.kimutansk.akka.exercise.routing.MessagePrintActor
import java.util.concurrent.TimeoutException
import akka.routing.FromConfig
import com.typesafe.config.ConfigFactory

/**
 * Akka-Persistenceの確認を行うサンプルアプリケーション
 */
object PersistenceApp extends App {
  override def main(args: Array[String]): Unit = {
    val config = ConfigFactory.load("conf/persistence-app.conf")
    implicit val system = ActorSystem.apply("PersistentApp", config)



    Thread.sleep(1000)
    system.shutdown()
  }
}
