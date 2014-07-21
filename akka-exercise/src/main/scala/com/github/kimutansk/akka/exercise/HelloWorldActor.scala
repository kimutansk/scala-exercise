package com.github.kimutansk.akka.exercise

import akka.actor.Actor

/**
 * Akka Actor用のHello Worldクラス
 * 受け取った内容の頭にメッセージを付与してコンソールに出力する。
 *
 * @author kimutansk
 */
class HelloWorldActor extends Actor {
  override def receive: Actor.Receive = {
    case x => println("Hello world! " + x)
  }
}
