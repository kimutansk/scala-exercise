package com.github.kimutansk.akka.exercise.typed

import scala.concurrent.Future

/**
 * Typed Actor検証用のActorコード
 */
class CalculatorImpl(val id: String) extends Calculator {
  // Actorをextendsした場合、TypedActorofメソッドは使用できなくなる。
  // そのため、Actorがもともと持っていたContextやSenderは使用できなくなる。
  def this() = this("default")

  def squareDontCare(i: Int): Unit = i * i //Nobody cares :(

  def square(i: Int): Future[Int] = Future.successful(i * i)

  def squareNowPlease(i: Int): Option[Int] = Some(i * i)

  def squareNow(i: Int): Int = i * i

  def squareTry(i: Int): Int = throw new Exception("Catch me!")
 }