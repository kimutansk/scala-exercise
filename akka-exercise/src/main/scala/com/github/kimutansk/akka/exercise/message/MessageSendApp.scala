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
    val childActor = system.actorOf(Props.apply(new ChildActor("child1")))
    val parentActor = system.actorOf(Props.apply(new ParentActor("parent1", childActor)))

    parentActor ! """Test1"""
    parentActor ! """Test2"""

    Thread.sleep(5000)
    system.shutdown()
  }
}
