package com.github.kimutansk.akka.exercise.routing

import akka.actor.Actor


/**
 * 受信したメッセージを出力するActor
 */
class MessagePrintActor extends Actor {

  def receive = {
    case msg: String => {
      val message = self.path + ": Received String " + msg
      println(message)
    }
  }
}