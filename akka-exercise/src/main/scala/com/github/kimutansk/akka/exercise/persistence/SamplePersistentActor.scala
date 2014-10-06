package com.github.kimutansk.akka.exercise.persistence

import akka.actor.ActorLogging
import akka.persistence.{SnapshotOffer, PersistentActor}

/**
 * Akka-Persistenceの確認を行うサンプルActor
 */
class SamplePersistentActor(name: String) extends PersistentActor with ActorLogging {

  override def persistenceId: String = "SamplePersistentActor" + name

  var stateCount = 0

  override def receiveCommand: SamplePersistentActor#Receive = {
    case "path" => context.sender ! self.path
    case "print" => println(self.path + ":" + stateCount)
    case "snap" => saveSnapshot(stateCount)
    case "view" => context.sender ! self.path + ":" + stateCount
    case message: String => stateCount += message.length
  }

  override def receiveRecover: SamplePersistentActor#Receive = {
    case SnapshotOffer(_, snapshot: Int) => stateCount = snapshot
    case other:Any => println(self.path + "(" + stateCount + "):" + other)
  }
}
