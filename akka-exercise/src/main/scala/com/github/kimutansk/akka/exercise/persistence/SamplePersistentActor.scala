package com.github.kimutansk.akka.exercise.persistence

import akka.actor.{ActorLogging, Actor}
import akka.persistence.PersistentActor

/**
 *
 */
class SamplePersistentActor extends PersistentActor with ActorLogging {
  override def persistenceId: String = "SamplePersistentActor"

  override def receiveCommand: SamplePersistentActor#Receive = ???

  override def receiveRecover: SamplePersistentActor#Receive = ???
}
