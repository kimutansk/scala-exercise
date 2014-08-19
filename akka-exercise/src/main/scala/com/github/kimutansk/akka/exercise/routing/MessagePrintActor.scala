package com.github.kimutansk.akka.exercise.routing

import akka.actor.{ActorLogging, Actor}


/**
 * 受信したメッセージを出力するActor
 */
class MessagePrintActor extends Actor with ActorLogging {

  def receive = {
    case msg: String => {
      val message = self.path + ": Received String " + msg
      log.info(message)
      sender ! message
    }
  }
}
