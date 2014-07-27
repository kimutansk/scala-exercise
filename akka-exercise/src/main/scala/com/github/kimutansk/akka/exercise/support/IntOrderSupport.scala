package com.github.kimutansk.akka.exercise.support

/**
 * Intに対して暗黙メソッドを追加するクラス
 *
 * @author kimutansk
 */
object IntOrderSupport {
  class IntOrderSupport(intValue: Int) {

    def ten(): Int = intValue * 10

    def hundred(): Int = intValue * 100

    def !(targetInt : Int): Int = (intValue * targetInt)

    def ?(): Int = hundred()
  }

  implicit def convertOrder(intValue: Int) = new IntOrderSupport(intValue)
}
