package com.github.kimutansk.akka.exercise

import akka.actor.{ActorSystem, Props}

/**
 * Akka Actor用のHello World起動クラス
 *
 * @author kimutansk
 */
object HelloWorldApp extends App {
  override def main(args: Array[String]): Unit = {
    val system = ActorSystem.apply("HelloWorldApp")
    val helloWorldActor = system.actorOf(Props.apply(new HelloWorldActor("actor1")), "HelloWorldActor")

    helloWorldActor ! """Test1"""
    helloWorldActor ! """Test2"""
    helloWorldActor ! 1

    system.shutdown()
  }
}
