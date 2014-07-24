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

    val result1 = helloWorldActor ! """Test1"""
    val result2 = helloWorldActor ! """Test2"""

    println("Test1 result is " + result1)
    println("Test2 result is " + result2)

    Thread.sleep(5000)
    system.shutdown()
  }
}
