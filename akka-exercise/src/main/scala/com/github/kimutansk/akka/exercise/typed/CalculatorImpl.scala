package com.github.kimutansk.akka.exercise.typed

import scala.concurrent.Future
import akka.actor.{TypedProps, TypedActor}
import akka.actor.TypedActor._
import scala.Some

/**
 * Typed Actor検証用のActorコード
 */
class CalculatorImpl(val id: String) extends Calculator with PreStart {
  // Actorをextendsした場合、TypedActorofメソッドは使用できなくなる。
  // そのため、Actorがもともと持っていたContextやSenderは使用できなくなる？
  def this() = this("default")

  override def preStart = {
    println(context.self.path + " is started.")
  }

  def squareDontCare(i: Int): Unit = i * i //Nobody cares :(

  def square(i: Int): Future[Int] = Future.successful(i * i)

  def squareNowPlease(i: Int): Option[Int] = Some(i * i)

  def squareNow(i: Int): Int = {i * i}

  def pathNow(str: String): String = {
    println(id + ":" + context.self.path + ", Received:" + str)
    id + ":" + context.self.path + ", Received:" + str}

  def squareTry(i: Int): Int = throw new Exception("Throw Exception!")
}