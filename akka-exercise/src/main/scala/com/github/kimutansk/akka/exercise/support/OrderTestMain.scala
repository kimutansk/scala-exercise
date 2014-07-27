package com.github.kimutansk.akka.exercise.support

/**
 * Intに対して暗黙メソッドを追加するクラス
 *
 * @author kimutansk
 */
object OrderTestMain {
  import  IntOrderSupport._

  def main(args: Array[String]): Unit = {
    println(10 ! 20)
    println(11 ?)
    println(12.ten)
    println(13 ten)
    println(14.hundred)
    println(15 hundred)
  }
}
