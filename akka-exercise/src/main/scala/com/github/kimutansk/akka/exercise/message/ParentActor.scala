package com.github.kimutansk.akka.exercise.message

import akka.actor.{ActorRef, Actor}

/**
 * メッセージ送受信確認用親Actor
 *
 * @author kimutansk
 */
class ParentActor(name: String, child: ActorRef) extends Actor {

  /** メッセージ受信時処理 */
  def receive = {
    case msg: String => {
      println("ParentActor: Received String " + msg + " My name is " + name)
      child ! "Hello world! " + msg + " My name is " + name
    }
    case msg: Int => {
      println("ParentActor: Received Int " + msg + " My name is " + name)
    }
  }
}
