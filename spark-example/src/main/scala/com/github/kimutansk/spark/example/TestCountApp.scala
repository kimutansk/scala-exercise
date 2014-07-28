package com.github.kimutansk.spark.example

import org.apache.spark.{SparkConf, SparkContext}

/**
 * テキストファイルをRDDに読み込み、RDDに対して繰り返しテキストカウントを行うSparkサンプルアプリケーション
 */
object TextCountApp {
  def main(args: Array[String]) {
    val logFile = "C:/pom.xml"
    val conf = new SparkConf().setAppName("TextCountApp").setMaster("local")
    val sc = new SparkContext(conf)
    val logData = sc.textFile(logFile, 2).cache()
    val numAs = logData.filter(line => line.contains("a")).count()
    val numBs = logData.filter(line => line.contains("b")).count()
    val numSparks = logData.filter(line => line.contains("Spark")).count()
    println("Lines with a: %s, Lines with b: %s, Lines with Spark: %s".format(numAs, numBs, numSparks))
  }
}