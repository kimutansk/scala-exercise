package com.github.kimutansk.akka.exercise.message

import akka.actor.{Props, ActorSystem}
import com.github.kimutansk.akka.exercise.HelloWorldActor

/**
 * メッセージ送受信確認用App
 *
 * @author kimutansk
 */
object MessageSendApp extends App {
  override def main(args: Array[String]): Unit = {
    val system = ActorSystem.apply("MessageSendApp")
    val childActor = system.actorOf(Props.apply(new ChildActor("child")))
    val parentActor = system.actorOf(Props.apply(new ParentActor("actor1", childActor)))

    val result1 = parentActor ! """Test1"""
    val result2 = parentActor ! """Test2"""
    val result3 = parentActor ! 1

    println("Test1 result is " + result1)
    println("Test2 result is " + result2)
    println("1 result is " + result3)

    system.shutdown()
  }
}
