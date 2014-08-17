package com.github.kimutansk.akka.exercise.cluster

import akka.actor.Actor
import akka.cluster.Cluster
import akka.cluster.ClusterEvent.{ClusterDomainEvent, InitialStateAsEvents}

/**
 * ClusterEventを受信して結果を出力するActor
 */
class ClusterEventListener extends Actor {
  val cluster = Cluster.get(context.system)

  override def preStart() = {
    cluster.subscribe(self, initialStateMode = InitialStateAsEvents,
      classOf[ClusterDomainEvent])
    println("Subscribe start. : Path=" + self.path)
  }

  override def receive() = {
    case event: ClusterDomainEvent => {
      println("Received event :" + event)
    }
  }

  override def postStop() = {
    cluster.unsubscribe(self)
    println("Subscribe stop. : Path=" + self.path)
  }
}
