package com.github.kimutansk.akka.exercise.remote

import akka.actor.{ActorLogging, Actor}


/**
 * 受信したメッセージを出力するActor
 */
class RemoteAccessActor extends Actor with ActorLogging {
  val actor = context.actorSelection("akka.tcp://RemoteServerApp@127.0.0.1:2552/user/Receive")

  def receive = {
    case msg: String => {
      val message = self.path + ": Received String " + msg
      println(message)
      actor ! message
    }
  }
}