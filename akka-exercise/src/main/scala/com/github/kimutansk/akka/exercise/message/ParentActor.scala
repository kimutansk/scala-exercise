package com.github.kimutansk.akka.exercise.message

import akka.actor.{ActorRef, Actor}
import akka.routing.{ActorRefRoutee, RoundRobinRoutingLogic, Router}
import scala.collection.immutable

/**
 * メッセージ送受信確認用親Actor
 *
 * @author kimutansk
 */
class ParentActor(name: String, childActorList: immutable.IndexedSeq[ActorRef]) extends Actor {
  // 初期化時に与えられたActorListに対して順に送信するよう初期化
  val routees = immutable.IndexedSeq.tabulate(childActorList.size)(i => new ActorRefRoutee(childActorList(i)))
  val router = new Router(new RoundRobinRoutingLogic, routees)

  /** メッセージ受信時処理 */
  def receive = {
    case msg: String => {
      println("ParentActor: Received String " + msg + " My name is " + name)
      router.route(msg, self)
    }
    case msg: Int => {
      println("ParentActor: Received Int " + msg + " My name is " + name)
    }
  }
}
