package com.github.kimutansk.akka.exercise.message

import akka.actor.Actor

/**
 * メッセージ送受信確認用子Actor
 *
 * @author kimutansk
 */
class ChildActor(name :String) extends Actor {

  /**
   * メッセージ受信時処理
   */
  def receive = {
    case msg: String  =>
  }
}
