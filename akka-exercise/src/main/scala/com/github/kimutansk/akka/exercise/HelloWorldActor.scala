package com.github.kimutansk.akka.exercise

import akka.actor.Actor

/**
 * Akka Actor用のHello Worldクラス
 * 受け取った内容の頭にメッセージを付与してコンソールに出力する。
 *
 * @author kimutansk
 */
class HelloWorldActor(name :String) extends Actor {
  override def preStart = {println(name + " is started.")  }

  def receive = {
    case msg: String  => { println("HelloWorldActor: Hello world! " + msg + " My name is " + name) }
  }

  override def postStop = {println(name + " is stopped.")  }
}
