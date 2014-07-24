package com.github.kimutansk.akka.exercise

import akka.actor.Actor

/**
 * Akka Actor用のHello Worldクラス
 * 受け取った内容の頭にメッセージを付与してコンソールに出力する。
 *
 * @author kimutansk
 */
class HelloWorldActor(name :String) extends Actor {
  /**
   * Actor初期化時処理
   */
  override def preStart = {println(name + " is started.")  }

  /**
   * メッセージ受信時処理
   */
  def receive = {
    case msg: String  => {
      println("HelloWorldActor: Hello world! " + msg + " My name is " + name)
      sender ! "HelloWorldActor: Hello world! " + msg + " My name is " + name
    }
  }

  /**
   * Actor終了時処理
   */
  override def postStop = {println(name + " is stopped.")  }
}
