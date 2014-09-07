package com.github.kimutansk.akka.exercise.typed

import akka.actor.Actor

import scala.concurrent.Future

/**
 * Typed Actor検証用のActorコード
 */
trait Calculator {
  def squareDontCare(i: Int): Unit //fire-forget

  def square(i: Int): Future[Int] //non-blocking send-request-reply

  def squareNowPlease(i: Int): Option[Int] //blocking send-request-reply

  def squareNow(i: Int): Int //blocking send-request-reply

  def pathNow(str: String): String

  @throws(classOf[Exception]) //declare it or you will get an UndeclaredThrowableException
  def squareTry(i: Int): Int //blocking send-request-reply with possible exception
}

