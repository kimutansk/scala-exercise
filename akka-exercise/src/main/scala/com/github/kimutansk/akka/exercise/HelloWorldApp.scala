package com.github.kimutansk.akka.exercise

import akka.pattern.ask
import akka.actor.{ActorSystem, Props}
import scala.concurrent.Await
import akka.util.Timeout
import java.util.concurrent.TimeUnit

/**
 * Akka Actor用のHello World起動クラス
 *
 * @author kimutansk
 */
object HelloWorldApp extends App {
  override def main(args: Array[String]): Unit = {
    val system = ActorSystem("HelloWorldApp")
    val helloWorldActor = system.actorOf(Props.apply(new HelloWorldActor("actor1")), "HelloWorldActor")
    implicit val timeout = Timeout(5000, TimeUnit.MILLISECONDS) // ? 実行時の暗黙タイムアウト設定
    val time = Timeout(5000, TimeUnit.MILLISECONDS)

    val futureTest1 = helloWorldActor ? """Test1"""
    val futureTest2 = helloWorldActor.?("""Test2""")(time)
    val unitTest3  = helloWorldActor ! """Test3"""

    println("Test1 future is " + futureTest1)
    val resultTest1 = Await.result(futureTest1, timeout.duration).asInstanceOf[String]
    println("Test1 result is " + resultTest1)

    println("Test2 future is " + futureTest2)
    val resultTest2 = Await.result(futureTest2, timeout.duration).asInstanceOf[String]
    println("Test2 result is " + resultTest2)

    println("Test3 unit is " + unitTest3)
    println("Test3 unit class is " + unitTest3.getClass)

    Thread.sleep(5000)
    system.shutdown()
  }
}
